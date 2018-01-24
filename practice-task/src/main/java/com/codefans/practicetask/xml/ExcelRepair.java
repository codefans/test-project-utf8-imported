package com.codefans.practicetask.xml;

import com.codefans.reusablecode.xml.XmlSaxParser;

import java.io.File;

/**
 * @author caishengzhi
 * @date 2018/1/24 11:07
 */
public class ExcelRepair {

    public static void main(String[] args) {
        ExcelRepair er = new ExcelRepair();
        er.repair();
    }

    public void repair() {
        this.validXml();
    }

    public void validXml() {
        String xmlPath = "D:\\tmp/sharedStrings.xml";
        File xmlFile = new File(xmlPath);
        XmlSaxParser xsp = new XmlSaxParser();
        xsp.parse(xmlFile);

    }

}
