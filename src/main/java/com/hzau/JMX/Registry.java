package com.hzau.JMX;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import java.lang.management.ManagementFactory;

public class Registry {
    public static MBeanServer mBeanServer;
    public static MBeanServer getRegistry()
    {
        if (mBeanServer==null){
            mBeanServer= ManagementFactory.getPlatformMBeanServer();
        }
        return mBeanServer;
    }
}
