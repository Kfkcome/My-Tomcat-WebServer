package com.hzau.Pipline;



import java.io.IOException;
import java.net.Socket;

public interface Valve {
    // 这个方法很容易理解，阀门中处理的执行方法，传入Request和Response进行处理
    public void invoke(Socket socket)throws IOException;
}
