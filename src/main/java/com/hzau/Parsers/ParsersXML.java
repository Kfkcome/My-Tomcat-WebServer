package com.hzau.Parsers;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * @author liu
 * @version 创建时间：2018年3月24日 下午7:32:02
 * 使用DOM读取XML文件的内容 DOMTest.java
 */
public class ParsersXML{
    public static Document getDocumentBuilder(String path){
        Document document;
        // 创建一个DocumentBuilderFactory对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 创建一个DocumentBuilder对象
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        // 使用parse方法解析xml文件
        try {
            document = db.parse(path);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }
    public static void main(String[] args) {

        Document document=getDocumentBuilder("src/main/resources/book.xml");
        NodeList nl = document.getElementsByTagName("service");
        System.out.println("文件中包含有" + nl.getLength() + "本书");
        for(int i = 0; i < nl.getLength(); i++) {
            // 通过item(i)获取一个book节点
            Node book = nl.item(i);
            // 获取一个book节点的所有属性
            NamedNodeMap nnm = book.getAttributes();
            for(int j = 0; j < nnm.getLength(); j++) {
                System.out.println(nnm.item(j).getNodeName() + ":" + nnm.item(j).getNodeValue());
            }
            NodeList childNodes = book.getChildNodes();
            // 子节点包括空格换行在内
            System.out.println("一个book节点下共有" + childNodes.getLength() + "个子节点");
            for(int m = 0; m < childNodes.getLength(); m++) {
                // 如果子节点是element类型的，就输出
                if(childNodes.item(m).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(childNodes.item(m).getNodeName() +  ":" + childNodes.item(m).getFirstChild().getNodeValue());
                }
            }
        }
    }
}