package JMX;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public interface MBeanRegistration   {

    // 在注册之前执行的方法，如果发生异常，MBean不会注册到MBean Server中
    public ObjectName preRegister(MBeanServer server,
                                  ObjectName name) throws java.lang.Exception;

    // 在注册之后执行的方法，比如注册失败提供报错信息
    public void postRegister(Boolean registrationDone);


    // 在卸载前执行的方法
    public void preDeregister() throws java.lang.Exception ;

    // 在执行卸载之后的方法
    public void postDeregister();

 }
