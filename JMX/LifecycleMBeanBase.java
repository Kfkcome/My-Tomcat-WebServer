package JMX;

import LifeCycle.LifecycleBase;
import Log.Log;

import javax.management.*;


public abstract class LifecycleMBeanBase extends LifecycleBase implements JmxEnabled {
    /* Cache components of the MBean registration. */
    private String domain = null;
    private ObjectName oname = null;
    @Deprecated
    protected MBeanServer mserver = null;

    /**
     * Specify the domain under which this component should be registered. Used
     * with components that cannot (easily) navigate the component hierarchy to
     * determine the correct domain to use.
     */
    @Override
    public final void setDomain(String domain) {
        this.domain = domain;
    }


    /**
     * Obtain the domain under which this component will be / has been
     * registered.
     */
    @Override
    public final String getDomain() {
        if (domain == null) {
            domain = getDomainInternal();
        }

        if (domain == null) {
            domain = "default-name";
        }

        return domain;
    }


    /**
     * Method implemented by sub-classes to identify the domain in which MBeans
     * should be registered.
     *
     * @return The name of the domain to use to register MBeans.
     */
    protected abstract String getDomainInternal();


    /**
     * Obtain the name under which this component has been registered with JMX.
     */
    @Override
    public final ObjectName getObjectName() {
        return oname;
    }


    /**
     * Allow sub-classes to specify the key properties component of the
     * {@link ObjectName} that will be used to register this component.
     *
     * @return The string representation of the key properties component of the
     * desired {@link ObjectName}
     */
    protected abstract String getObjectNameKeyProperties();


    protected final ObjectName register(Object obj,
                                        String objectNameKeyProperties) {

        // Construct an object name with the right domain
        StringBuilder name = new StringBuilder(getDomain());
        name.append(':');
        name.append(objectNameKeyProperties);

        ObjectName on = null;

        try {
            on = new ObjectName(name.toString());
            Registry.getRegistry().registerMBean(obj, on);
        } catch (MalformedObjectNameException e) {
            Log.warn("lifecycleMBeanBase.registerFail" + name + e.getMessage());
        } catch (Exception e) {
            Log.warn("lifecycleMBeanBase.registerFail" + name +
                    e.getMessage());
        }

        return on;
    }


    protected final void unregister(String objectNameKeyProperties) throws MalformedObjectNameException, InstanceNotFoundException, MBeanRegistrationException {
        // Construct an object name with the right domain
        StringBuilder name = new StringBuilder(getDomain());
        name.append(':');
        name.append(objectNameKeyProperties);
        ObjectName objectName = new ObjectName("com.example:type=MyMBean");
        if (Registry.getRegistry().isRegistered(objectName)) {
            Registry.getRegistry().unregisterMBean(objectName);
            System.out.println("MBean unregistered successfully.");
        } else {
            System.out.println("MBean not found or already unregistered.");
        }
    }


    protected final void unregister(ObjectName on) throws InstanceNotFoundException, MBeanRegistrationException {
        Registry.getRegistry().unregisterMBean(on);
    }


    /**
     * Not used - NOOP.
     */
    @Override
    public final void postDeregister() {
        // NOOP
    }


    /**
     * Not used - NOOP.
     */
    @Override
    public final void postRegister(Boolean registrationDone) {
        // NOOP
    }


    /**
     * Not used - NOOP.
     */
    @Override
    public final void preDeregister() throws Exception {
        // NOOP
    }


    /**
     * Allows the object to be registered with an alternative
     * {@link MBeanServer} and/or {@link ObjectName}.
     */
    @Override
    public final ObjectName preRegister(MBeanServer server, ObjectName name)
            throws Exception {

        this.mserver = server;
        this.oname = name;
        this.domain = name.getDomain().intern();

        return oname;
    }

}
