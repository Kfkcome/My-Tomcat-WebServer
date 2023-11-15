package com.hzau.Connector;



import com.hzau.Exception.LifecycleException;
import com.hzau.JMX.LifecycleMBeanBase;
import com.hzau.LifeCycle.LifecycleListener;
import com.hzau.LifeCycle.LifecycleState;
import com.hzau.Log.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

;


public class Connector extends LifecycleMBeanBase implements ConnectorMBean{
    ArrayList<LifecycleListener> listeners;
    int port;
    ServerSocket serverSocket;
    public Connector(int port){
        this.port=port;
    }

    @Override
    protected String getDomainInternal() {
        return "Connector";
    }

    @Override
    protected String getObjectNameKeyProperties() {
        //用时间保证唯一性
        return "name=Connector"+port;
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        listeners.add(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void init() throws LifecycleException {
        Log.info(port + "号Connect正在初始化");
        register(this,getObjectNameKeyProperties());

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            Log.error("该端口被占用无法使用");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() throws LifecycleException {

    }

    @Override
    public void stop() throws LifecycleException {

    }

    @Override
    public void destroy() throws LifecycleException {

    }

    @Override
    public LifecycleState getState() {
        return lifecycleState;
    }

    @Override
    public String getStateName() {
        return lifecycleState.getLifecycleEvent();
    }



    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        listeners.remove(listener);
    }
}
