package com.hzau.app;

import com.hzau.Log.Log;

public class test implements WebApp{
    public void service(){
        Log.info("服务成功执行成功");
    }

    @Override
    public void getService() {
        Log.info("服务成功执行成功");
    }

    @Override
    public void putService() {
        Log.info("服务成功执行成功");
    }

    @Override
    public void deleteService() {
        Log.info("服务成功执行成功");
    }

    @Override
    public void postService() {
        Log.info("服务成功执行成功");
    }
}
