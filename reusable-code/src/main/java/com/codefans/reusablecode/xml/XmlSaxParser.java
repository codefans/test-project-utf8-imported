package com.codefans.reusablecode.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * @author caishengzhi
 * @date 2018/1/24 11:07
 */
public class XmlSaxParser {

    public void parse(File srcFile) {
//        System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        XmlElementParseHandle handle = new XmlElementParseHandle();

        try {

            parser = factory.newSAXParser();
            parser.parse(srcFile, handle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
