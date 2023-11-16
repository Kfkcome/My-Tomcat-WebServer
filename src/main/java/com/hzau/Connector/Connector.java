package com.hzau.Connector;



import com.hzau.Exception.LifecycleException;
import com.hzau.JMX.LifecycleMBeanBase;
import com.hzau.LifeCycle.LifecycleListener;
import com.hzau.LifeCycle.LifecycleState;
import com.hzau.Log.Log;
import com.hzau.Pipline.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

;


public class Connector extends LifecycleMBeanBase implements ConnectorMBean{

    ArrayList<LifecycleListener> listeners;
    int port;
    ServerSocket serverSocket;
    HttpServletRequest httpServletRequest;
    public Connector(int port){
        this.port=port;
    }

    @Override
    protected String getDomainInternal() {
        return "Connector";
    }

    @Override
    protected String getObjectNameKeyProperties() {
        //用时间保证唯一性
        return "name=Connector"+port;
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        listeners.add(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void init() throws LifecycleException {
        Log.info(port + "号Connect正在初始化");
        register(this,getObjectNameKeyProperties());

        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            Log.error("该端口被占用无法使用");
            throw new RuntimeException(e);
        }
    }
/**
GET / HTTP/1.1
Host: 127.0.0.1
Connection: keep-alive
Cache-Control: max-age=0
sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="102"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*;q=0.8,application/signed-exchange;v=b3;q=0.9
    Sec-Fetch-Site: none
    Sec-Fetch-Mode: navigate
    Sec-Fetch-User: ?1
    Sec-Fetch-Dest: document
    Accept-Encoding: gzip, deflate, br
    Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7
 **/
    @Override
    public void start() throws LifecycleException {
        setState(LifecycleState.STARTED);
        try {
            Socket accept = serverSocket.accept();
            httpServletRequest=new HttpServletRequest(accept);
            Log.info(port+"端口接收到请求连接");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() throws LifecycleException {

    }

    @Override
    public void destroy() throws LifecycleException {

    }

    @Override
    public LifecycleState getState() {
        return lifecycleState;
    }

    @Override
    public String getStateName() {
        return lifecycleState.getLifecycleEvent();
    }



    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        listeners.remove(listener);
    }
}
