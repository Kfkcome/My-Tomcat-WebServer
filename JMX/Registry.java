package JMX;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;

public class Registry {
    public static MBeanServer getRegistry()
    {
        MBeanServer server = MBeanServerFactory.createMBeanServer();
        return server;
    }
}
