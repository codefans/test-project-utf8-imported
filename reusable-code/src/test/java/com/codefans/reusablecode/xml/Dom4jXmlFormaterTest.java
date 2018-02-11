package com.codefans.reusablecode.xml;

import org.junit.Test;

/**
 * @author caishengzhi
 * @date 2018/2/11 14:48
 */
public class Dom4jXmlFormaterTest {

    @Test
    public void formatTest() {
        try {
            String xmlSource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\"><parent><artifactId>test-project-utf8</artifactId><groupId>com.codefans</groupId><version>1.0.0-SNAPSHOT</version></parent><modelVersion>4.0.0</modelVersion><artifactId>reusable-code</artifactId></project>";
            String formatXml = Dom4jXmlFormater.format(xmlSource);
            System.out.println("formatXml:");
            System.out.println(formatXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
