/**
 * 类功能描述：启动引导程序
 *
 * @author new
 * @date 2023/11/13
 */
public final class Bootstrap
{
    private static final Object daemonLock = new Object();//守护进程锁
    private void init(){

    }

    public static void main(String[] args)
    {
        synchronized (daemonLock)//守护进程锁保证唯一性
        {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.init();
        }
    }
}
