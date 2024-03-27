/* Generated by Together */

package com.dimata.dtaxintegrationsisda.db;

import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import com.sun.xml.tree.*;
import com.sun.xml.parser.Resolver;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

public class DBConfigReader2 {
    private InputStream inStream;
    private Element currentElement;
    private String dbdriver="";
    private String dburl="";
    private String dbuser="";
    private String dbpasswd="";
    private String logcon="";
    private int logsize;
    private int mincon;
    private int maxcon;
    private String fordate="";
    private String fordesimal="";
    private String forcurrency="";
    
    public DBConfigReader2(){
    }

    public DBConfigReader2(String pathXML){
		setPathConfigFile(pathXML);
        try{
        	//getValue();
        }catch(Exception e){
        	System.out.println("Err : "+e.toString());
        }
    }

    public String getConfigValue(String dbparent,String dbchild) throws IOException {
       String str="";
	try {
	    SAXBuilder builder = new SAXBuilder(); //"
            //get the Configuration Document, with validation
            inStream = new FileInputStream(getPathConfigFile());
            Document doc = builder.build(inStream);
            // get the root element
            Element root = doc.getRootElement();
            if (root == null) {
                System.out.println("NULL ROOT.......................");
                throw new IOException("NULL XML ROOT.......................");
            }
            // get Value classes
            
            List dbElement = root.getChildren(dbparent);
            Iterator iList = dbElement.iterator();
            while (iList.hasNext()){
                Element currElement = (Element)iList.next();
               	//System.out.println(currElement.getChild(dbchild).getText());
                str=currElement.getChild(dbchild).getText();    
               
            }
            
             } catch (Exception exc) {
            throw new IOException(exc.getMessage());
        }
         return str;
    }
            

    public static String getPathConfigFile(){
        return pathConfigFile;
    }

    public static void setPathConfigFile(String pathConfigFl){
        pathConfigFile = pathConfigFl;
    }

    
   

    private static String pathConfigFile="C:\\jdk1.3.1_01\\jre\\dimata\\newprochainpos.xml";
    private static Vector listPrinter = new Vector(1,1);

    public static void main(String args[]){
        try
        {
        DBConfigReader2 configReader = new DBConfigReader2(pathConfigFile);
        configReader.getConfigValue("dbdriver","driver");
        //Vector vctType=configReader.getListBarcodeType();
        //System.out.println("jumlah type ="+vctType.size());
        }catch (Exception e){}
                     
    }
}
