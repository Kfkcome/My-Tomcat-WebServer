package com.hzau.Pipline;


import com.hzau.Exception.LifecycleException;
import com.hzau.JMX.LifecycleMBeanBase;
import com.hzau.LifeCycle.LifecycleListener;
import com.hzau.LifeCycle.LifecycleState;

public class pipeline extends LifecycleMBeanBase {


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
    public void init() throws LifecycleException {

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
        return null;
    }

    @Override
    public String getStateName() {
        return null;
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }
}
