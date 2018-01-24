package com.codefans.reusablecode.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Test;

/**
 * @author caishengzhi
 * @date 2018/1/24 14:26
 */
public class Dom4JXmlParserTest {

    @Test
    public void listAll() {

        try {
            String xmlPath = "D:/tmp/sharedStrings.xml";
            Dom4JXmlParser dom4jParser = new Dom4JXmlParser();
            Element root = dom4jParser.getRootElement(xmlPath);
            dom4jParser.listNodes(root);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void formatXml() {
        try {
            String xmlPath = "D:/tmp/sharedStrings.xml";
            Dom4JXmlParser dom4jParser = new Dom4JXmlParser();
            Document document = dom4jParser.getDocument(xmlPath);

            dom4jParser.format(document);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
