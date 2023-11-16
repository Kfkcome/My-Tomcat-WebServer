package com.hzau;

import com.hzau.Exception.LifecycleException;
import com.hzau.JMX.Registry;
import com.hzau.Parsers.ParsersXML;
import com.hzau.Service.ServiceImpl.StanderService;
import com.hzau.Service.ServiceImpl.StanderServiceMBean;
import com.sun.jdmk.comm.HtmlAdaptorServer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

/**
 * 类功能描述：启动引导程序
 *
 * @author new
 * @date 2023/11/13
 */
public final class Bootstrap {
    private static final Object daemonLock = new Object();//守护进程锁
    private final ArrayList<StanderService> services = new ArrayList<>();

    private void init() {
        Document document = ParsersXML.getDocumentBuilder("src/main/resources/webserver.xml");
        NodeList servers = document.getElementsByTagName("service");
        for(int i=0;i<servers.getLength();i++){
            Node item = servers.item(i);
            String nodeValue = item.getAttributes().item(0).getNodeValue();
            StanderService standerService = new com.hzau.Service.ServiceImpl.StanderService(Integer.parseInt(nodeValue),item.getChildNodes());
            try {
                standerService.init();
            } catch (LifecycleException e) {
                throw new RuntimeException(e);
            }
            services.add(standerService);
        }

    }
    private void start(){
        for (StanderService service : services) {
            try {
                service.start();
            } catch (LifecycleException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
        synchronized (daemonLock)//守护进程锁保证唯一性
        {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
            //端口号默认8082
            HtmlAdaptorServer adapter = new HtmlAdaptorServer();
            adapter.setPort(9000);
            Registry.getRegistry().registerMBean(adapter, adapterName);
            bootstrap.start();
            adapter.start();
        }
    }
}
