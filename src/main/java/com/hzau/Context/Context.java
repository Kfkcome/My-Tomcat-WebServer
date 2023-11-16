package com.hzau.Context;

import com.hzau.Exception.LifecycleException;
import com.hzau.JMX.LifecycleMBeanBase;
import com.hzau.LifeCycle.LifecycleListener;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Context extends LifecycleMBeanBase implements ContextMBean {
    NodeList childNodes;
    ArrayList<Class> app;

    public Context(NodeList nodeList) {
        app=new ArrayList<>();
        this.childNodes = nodeList;
    }

    @Override
    protected String getDomainInternal() {
        return null;
    }

    @Override
    protected String getObjectNameKeyProperties() {
        return null;
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
        for (int j = 0; j < childNodes.getLength(); j++) {
            if (childNodes.item(j).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            String name = childNodes.item(j).getNodeName();
            String classpath = childNodes.item(j).getFirstChild().getNodeValue();
            Class tempClass;
            try {
                tempClass = Class.forName(classpath);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Object studentObj = getObject(tempClass);
            Method service;
            try {
                service = tempClass.getMethod("service");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            try {
                service.invoke(studentObj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            app.add(tempClass);
        }
    }

    private Object getObject(Class temp) {
        Constructor studentConstructor;
        try {
            studentConstructor = temp.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Object studentObj = null;
        try {
            studentObj = studentConstructor.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return studentObj;
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
    public String getStateName() {
        return null;
    }

    @Override
    public ArrayList<Class> getAPPs() {
        return app;
    }
}
