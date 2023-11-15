package com.hzau.ThreadPool;

import com.hzau.Exception.LifecycleException;
import com.hzau.JMX.LifecycleMBeanBase;
import com.hzau.LifeCycle.LifecycleListener;
import com.hzau.LifeCycle.LifecycleState;
import com.hzau.Log.Log;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StandardThreadExecutor extends LifecycleMBeanBase implements Executor,StandardThreadExecutorMBean {
    int corePoolSize;
    int maximumSize;
    int keepAliveTime;
    // 任务队列
    private TaskQueue taskqueue = null;

    // 包装了一个ThreadPoolExecutor
    protected ThreadPoolExecutor executor = null;

    public StandardThreadExecutor(int corePoolSize,int maximumSize,int keepAliveTime){
        this.corePoolSize=corePoolSize;
        this.maximumSize=maximumSize;
        this.keepAliveTime=keepAliveTime;
    }
    @Override
    protected String getDomainInternal() {
        return null;
    }

    @Override
    protected String getObjectNameKeyProperties() {
        return "name=threadPool";
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public void init() throws LifecycleException {
        setDomain("ThreadPool");
        taskqueue=new TaskQueue();
        executor=new ThreadPoolExecutor(corePoolSize,maximumSize,keepAliveTime,TimeUnit.MILLISECONDS,taskqueue);
        register(this,getObjectNameKeyProperties());
        setState(LifecycleState.INITIALIZED);
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
    public String getName() {
        return null;
    }

    @Override
    public void execute(Runnable command, long timeout, TimeUnit unit) {

    }

    @Override
    public void execute(Runnable command) {
        if (executor != null) {
            try {
                executor.execute(command);
            } catch (RejectedExecutionException rx) {
                //there could have been contention around the queue
                if (taskqueue.add(command)) {
                    Log.error("standardThreadExecutor.queueFull");
                }
            }
        } else {
           Log.error("standardThreadExecutor.notStarted");
        }

    }
}
