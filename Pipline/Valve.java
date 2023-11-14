package Pipline;

import com.sun.net.httpserver.Request;


import java.io.IOException;

public interface Valve {
    // 这个方法很容易理解，阀门中处理的执行方法，传入Request和Response进行处理
    public void invoke(Request request, Response response)throws IOException, ServletException;
}
