package com.hzau.Service.ServiceImpl;


import com.hzau.LifeCycle.LifecycleState;

import java.util.ArrayList;

public interface StanderServiceMBean {
    int getId();
    void setId(int id);
    LifecycleState getStatement();
    ArrayList<Integer>getPort();
}
