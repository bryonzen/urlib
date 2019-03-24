/*
 * Copyright (c) 2019
 * @ClassName: xyz.uscnav.api.utils.net.Jxpath
 * @Description:
 * @Author: ZengBin
 * @Email: 271995865@qq.com
 * @Date: 2019/03/22 19:04:22
 */

package com.example.net;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class Jxpath {

    private XPath xPath;
    private org.w3c.dom.Document doc;

    public Jxpath(String html){
        try {

            TagNode tagNode = new HtmlCleaner().clean(html);

            org.w3c.dom.Document doc = new DomSerializer(
                    new CleanerProperties()).createDOM(tagNode);

            this.xPath = XPathFactory.newInstance().newXPath();
            this.doc = doc;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getString(String exp){
        try {
            return (String) xPath.evaluate(exp, doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean getBoolean(String exp){
        try {
            return (Boolean) xPath.evaluate(exp, doc, XPathConstants.BOOLEAN);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double getNumber(String exp){
        try {
            return (Double) xPath.evaluate(exp, doc, XPathConstants.NUMBER);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }

}
