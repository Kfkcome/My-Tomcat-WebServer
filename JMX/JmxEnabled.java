package JMX;

import javax.management.ObjectName;

/**
 * 类功能描述：此接口由组件实现，这些组件在创建时将注册到MBean服务器，在销毁时将注销这些组件。
 * 它主要是由实现生命周期的组件来实现的，但并不是专门为它们实现的。
 *
 * @author new
 * @date 2023/11/14
 */
public interface JmxEnabled extends MBeanRegistration {

    // 获取MBean所属于的Domain
    String getDomain();

    // 设置Domain
    void setDomain(String domain);

    // 获取MBean的名字
    ObjectName getObjectName();
}
