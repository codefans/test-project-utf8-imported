package com.codefans.reusablecode.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author caishengzhi
 * @date 2018/1/24 11:43
 */
public class XmlElementParseHandle extends DefaultHandler {

    Stack<String> tagNameStack = new Stack<String>();
    Set<String> qNameSet = new HashSet<String>();
    //[rPr, rFont, b, charset, si, u, sst, t, scheme, r, color, family, phoneticPr, sz]

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start document");
    }

    @Override
    public void endDocument() throws SAXException {

        System.out.println("qName.size():" + qNameSet.size());
        System.out.println(qNameSet.toString());

        System.out.println("tagNameStack.size():" + tagNameStack.size());
        System.out.println(tagNameStack.toString());


    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("uri:" + uri);
//        System.out.println("localName: " + localName);
//        System.out.println("qName: " + qName);
        qNameSet.add(qName);
        tagNameStack.push(qName);
    }



    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String tagName = tagNameStack.peek();
//        String startTag = this.getStartTag(qName);
        if(tagName.equals(qName)) {
            tagNameStack.pop();
            System.out.println("remove from stack, tagName=" + tagName + ", qName=" + qName);
        } else {
            tagNameStack.push(qName);
        }
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }

    /**
     * rPr, rFont, b, charset, si, u, sst, t, scheme, r, color, family, phoneticPr, sz
     * @param startTag
     * @return
     */
    public String getEndTag(String startTag) {

        String endTag = "";
        if("<rPr>".equals(startTag)) {
            endTag = "</rPr>";
        } else if("<rFont>".equals(startTag)) {
            endTag = "</rFont>";
        } else if("<b>".equals(startTag)) {
            endTag = "</b>";
        } else if("<charset>".equals(startTag)) {
            endTag = "</charset>";
        } else if("<si>".equals(startTag)) {
            endTag = "</si>";
        } else if("<u>".equals(startTag)) {
            endTag = "</u>";
        } else if("<sst>".equals(startTag)) {
            endTag = "</sst>";
        } else if("<t>".equals(startTag)) {
            endTag = "</t>";
        } else if("<scheme>".equals(startTag)) {
            endTag = "</scheme>";
        } else if("<r>".equals(startTag)) {
            endTag = "</r>";
        } else if("<color>".equals(startTag)) {
            endTag = "</color>";
        } else if("<family>".equals(startTag)) {
            endTag = "</family>";
        } else if("<phoneticPr>".equals(startTag)) {
            endTag = "</phoneticPr>";
        } else if("<sz>".equals(startTag)) {
            endTag = "</sz>";
        }
        return endTag;
    }

    /**
     * rPr, rFont, b, charset, si, u, sst, t, scheme, r, color, family, phoneticPr, sz
     * @param endTag
     * @return
     */
    public String getStartTag(String endTag) {

        String startTag = "";
        if("</rPr>".equals(endTag)) {
            startTag = "<rPr>";
        } else if("</rFont>".equals(endTag)) {
            startTag = "<rFont>";
        } else if("</b>".equals(endTag)) {
            startTag = "<b>";
        } else if("</charset>".equals(endTag)) {
            startTag = "<charset>";
        } else if("</si>".equals(endTag)) {
            startTag = "<si>";
        } else if("</u>".equals(endTag)) {
            startTag = "<u>";
        } else if("</sst>".equals(endTag)) {
            startTag = "<sst>";
        } else if("</t>".equals(endTag)) {
            startTag = "<t>";
        } else if("</scheme>".equals(endTag)) {
            startTag = "<scheme>";
        } else if("</r>".equals(endTag)) {
            startTag = "<r>";
        } else if("</color>".equals(endTag)) {
            startTag = "<color>";
        } else if("</family>".equals(endTag)) {
            startTag = "<family>";
        } else if("</phoneticPr>".equals(endTag)) {
            startTag = "<phoneticPr>";
        } else if("</sz>".equals(endTag)) {
            startTag = "<sz>";
        }
        return startTag;
    }


}
