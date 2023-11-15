package com.hzau.LifeCycle;


import com.hzau.JMX.JmxEnabled;

public abstract class LifecycleBase implements LifeCycle, JmxEnabled {
    public LifecycleState lifecycleState;
    @Override
    public void setState(LifecycleState lifecycleState){
        this.lifecycleState=lifecycleState;
    }

}
