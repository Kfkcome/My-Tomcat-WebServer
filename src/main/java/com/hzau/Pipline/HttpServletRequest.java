package com.hzau.Pipline;

import com.hzau.Log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class HttpServletRequest extends Socket {
    Map<String,String> header;
    String method;
    String requestURL;
    String protocol;

    public HttpServletRequest(Socket socket){
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] read = new byte[800];
        try {
            inputStream.read(read);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String data = new String(read);
        Log.info(data);
        String[] split = data.split("\r\n");
        method= split[0].split(" ")[0];
        requestURL=split[0].split(" ")[1];
        protocol=split[0].split(" ")[2];
        System.out.println(data);
    }
//    String getParameter(String name){
//
//    }
    String getMethod(){
        return method;
    }
//    String getHeader(String name){
//
//    }
    String getRequestURL(){
        return  requestURL;
    }
//    String getSession();
}
