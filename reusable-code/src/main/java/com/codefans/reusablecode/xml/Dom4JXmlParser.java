package com.codefans.reusablecode.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author caishengzhi
 * @date 2018/1/24 14:21
 */
public class Dom4JXmlParser {

    public Document getDocument(String xmlPath) throws DocumentException {
        //创建SAXReader对象
        SAXReader reader = new SAXReader();
        //读取文件 转换成Document
        Document document = reader.read(new File(xmlPath));
        return document;
    }

    public Element getRootElement(String xmlPath) throws DocumentException {
        //创建SAXReader对象
        SAXReader reader = new SAXReader();
        //读取文件 转换成Document
        Document document = reader.read(new File(xmlPath));
        //获取根节点元素对象
        Element root = document.getRootElement();
        return root;
    }

    //遍历当前节点下的所有节点
    public void listNodes(Element node){
        System.out.println("当前节点的名称：" + node.getName());
        //首先获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        //遍历属性节点
        for(Attribute attribute : list){
            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());
        }
        //如果当前节点内容不为空，则输出
        if(!(node.getTextTrim().equals(""))){
            System.out.println( node.getName() + "：" + node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e);
        }
    }

    public void format(Document document) {
        try {

            OutputFormat format = new OutputFormat();
            format.setIndent(true);
            format.setNewlines(true);
            Writer fileWriter=new FileWriter("D:/tmp/dom4j_output_format.xml");
            XMLWriter xmlWriter=new XMLWriter(fileWriter,format);
            xmlWriter.write(document);
            xmlWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
