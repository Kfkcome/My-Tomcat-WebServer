package com.hzau.Service.ServiceImpl;


import com.hzau.Connector.Connector;
import com.hzau.Context.Context;
import com.hzau.Exception.LifecycleException;
import com.hzau.JMX.LifecycleMBeanBase;

import com.hzau.LifeCycle.LifecycleListener;
import com.hzau.LifeCycle.LifecycleState;
import com.hzau.Log.Log;

import com.hzau.ThreadPool.StandardThreadExecutor;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class StanderService extends LifecycleMBeanBase implements StanderServiceMBean {
    private int id;
    NodeList serviceValue;
    ArrayList<Integer> port;
    ArrayList<Connector> connectors;
    ArrayList<Context>contexts;
    StandardThreadExecutor standardThreadExecutor;

    public StanderService(int id, NodeList serviceValue) {
        this.id = id;
        this.serviceValue = serviceValue;
        port = new ArrayList<>();
        connectors = new ArrayList<>();
        contexts=new ArrayList<>();
    }

    @Override
    protected String getDomainInternal() {
        return null;
    }

    @Override
    protected String getObjectNameKeyProperties() {
        return "name=StanderService" + id;
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public void init() throws LifecycleException {
        Log.info(id + "号Service正在初始化");
        int corePoolSize = 5;
        int maxiumSize = 10;
        int keepAliveTime = 5000;
        for (int i = 0; i < serviceValue.getLength(); i++) {
            Node item = serviceValue.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                String nodeName = item.getNodeName();
                String nodeValue = item.getFirstChild().getNodeValue();
                Log.info("读取到配置文件：" + nodeName + " : " + nodeValue);
                if (nodeName.equals("port")) {
                    port.add(Integer.parseInt(nodeValue));
                    connectors.add(new Connector(Integer.parseInt(nodeValue)));
                }
                if (nodeName.equals("corePoolSize"))
                    corePoolSize = Integer.parseInt(nodeValue);
                if (nodeName.equals("maxiumSize"))
                    maxiumSize = Integer.parseInt(nodeValue);
                if (nodeName.equals("keepAliveTime"))
                    keepAliveTime = Integer.parseInt(nodeValue);
                if (nodeName.equals("webapp")) {
                    NodeList childNodes = item.getChildNodes();
                    contexts.add(new Context(childNodes));
                }
            }
        }
        standardThreadExecutor = new StandardThreadExecutor(corePoolSize, maxiumSize, keepAliveTime);
        standardThreadExecutor.init();


        this.setDomain("Service");
        this.register(this, getObjectNameKeyProperties());
        for (Connector connector : connectors) {
            connector.init();
        }
        for (Context context : contexts) {
            context.init();
        }
        setState(LifecycleState.INITIALIZED);
    }

    @Override
    public void start() throws LifecycleException {
        for (Connector connector : connectors) {

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        connector.start();
                    } catch (LifecycleException e) {
                        Log.error("运行出错");
                        throw new RuntimeException(e.getMessage());
                    }
                }
            };
            standardThreadExecutor.execute(r);
            setState(LifecycleState.STARTED);
        }
    }

    @Override
    public void stop() throws LifecycleException {

    }

    @Override
    public void destroy() throws LifecycleException {

    }

    @Override
    public LifecycleState getState() {
        return null;
    }

    @Override
    public String getStateName() {
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public LifecycleState getStatement() {
        return getState();
    }

    @Override
    public ArrayList<Integer> getPort() {
        return port;
    }
}
