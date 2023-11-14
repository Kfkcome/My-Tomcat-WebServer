import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 类功能描述：启动引导程序
 *
 * @author new
 * @date 2023/11/13
 */
public final class Bootstrap
{
    private static MBeanServer mBeanServer ;
    private static final Object daemonLock = new Object();//守护进程锁
    private void init() throws MalformedObjectNameException {
        ObjectName oname;
        // get the default MBeanServer from Management Factory

        mBeanServer = ManagementFactory.getPlatformMBeanServer ();
        // try {
        // create a instance of CentralHeaterImpl class


        // assign a Object name to above instance
        oname = new ObjectName( "MyHome:name=centralheater" );

        // register the instance of CentralHeaterImpl to MBeanServer


    }

    public static void main(String[] args)
    {
        synchronized (daemonLock)//守护进程锁保证唯一性
        {
            Bootstrap bootstrap = new Bootstrap();

            try {
                bootstrap.init();
            } catch (MalformedObjectNameException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
