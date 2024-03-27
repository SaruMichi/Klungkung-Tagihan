/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimata.webclient;

/**
 *
 * @author dimata005
 */

import java.io.ByteArrayOutputStream;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author Goes
 */
public class EchoTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String resp_status = new String();
        String resp_code = new String();
        try {
            
              String Str = new String("1.0113.04.01");

              System.out.print("Return Value :" );
              System.out.println(Str.substring(10) );

              System.out.print("Return Value :" );
              //System.out.println(Str.substring(10, 15) );
              
              System.out.print("Return Value :" );
              System.out.println(Str.substring(0,2) );
            
//            // TODO code application logic here
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
//            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
//            soapResponse.writeTo(System.out);
//
//            soapResponse.writeTo(out);
//            String raw_respon = new String(out.toByteArray());
//            System.out.println("SOAP Respon = "+raw_respon);
//            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
//
//            System.out.println("=============================================");
//            System.out.println("GET STATUS");
//            System.out.println("Respone Code = "+ resp_code);
//            System.out.println("status = "+ resp_status);
//            System.out.println("=============================================");

        } catch (Exception ex) {

            ex.printStackTrace();

          }
      }
    
      public String action(){
          
            String resp_status = new String();
            String resp_code = new String();
            String raw_respon ="";
            try {
                // TODO code application logic here
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
                SOAPConnection soapConnection = soapConnectionFactory.createConnection();
                String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
                SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
                soapResponse.writeTo(System.out);

                soapResponse.writeTo(out);
                raw_respon = new String(out.toByteArray());
                System.out.println("SOAP Respon = "+raw_respon);
                resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");

                System.out.println("=============================================");
                System.out.println("GET STATUS");
                System.out.println("Respone Code = "+ resp_code);
                System.out.println("status = "+ resp_status);
                System.out.println("=============================================");
                if(resp_code.equals("99")){
                    raw_respon="TERHUBUNG DENGAN BAIK";
                } else if(resp_code.equals("88")){    
                    raw_respon="GAGAL";  
                }else{
                    raw_respon="Tidak memiliki wewenang akses";    
                }
              } catch (Exception ex) {

                ex.printStackTrace();
                raw_respon = "cek koneksi ke BPD " + ex.getMessage();

              }
            
             return raw_respon;
      } 
    
    
     public static SOAPMessage createSOAPRequest() throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("echo_test", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sUser", "example");
        soapBodyElem1.addTextNode(""+AppSetting.USERNAME_PHR);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("sPassword", "example");
        soapBodyElem2.addTextNode(""+AppSetting.PWD_PHR);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "echo_test");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("n/Request SOAP Message: n/");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

}
