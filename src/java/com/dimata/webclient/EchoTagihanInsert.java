/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimata.webclient;

import com.dimata.dtaxintegration.entity.tagihan.TagihanInsert;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
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
 * @author dimata005
 */
public class EchoTagihanInsert {


//    public static void main(String[] args) {
//        String resp_status = new String();
//        String resp_code = new String();
//        TagihanInsert tagihanInsert = new TagihanInsert();
//        try {
//            // TODO code application logic here
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
//            
//            tagihanInsert.setsUser("PHR_GIANYAR");//1
//            tagihanInsert.setSPassword("123456");//2
//            tagihanInsert.setSNoId("19891229001");//3
//            tagihanInsert.setSNama("Putu A");//4
//            tagihanInsert.setJumTagihan(Double.valueOf("100000"));//5
//            tagihanInsert.setSInstansi("PHR_GIANYAR");//6
//            tagihanInsert.setSKet_1("");//ALAMAT//7
//            tagihanInsert.setSKet_2("");//BULAN//8
//            tagihanInsert.setSKet_3("");//TAHUN//9
//            tagihanInsert.setSKet_4("");//POKOK//10
//            tagihanInsert.setSKet_5("");//11
//            tagihanInsert.setSKet_6("");//12
//            tagihanInsert.setSKet_7("");//13
//            tagihanInsert.setSKet_8("");//14
//            tagihanInsert.setSKet_9("");
//            tagihanInsert.setSKet_10("");
//            tagihanInsert.setSKet_11("");
//            tagihanInsert.setSKet_12("");
//            tagihanInsert.setSKet_13("");
//            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(tagihanInsert), url);
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
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }


    public synchronized String action(TagihanInsert tagihanInsert){
        String resp_code = new String();
        String resp_message = new String();
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest2(tagihanInsert), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = "+raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
            resp_message=StringUtils.substringBetween(raw_respon,"<message>","</message>");
            
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = "+ resp_code);
            System.out.println("Respone Message = "+ resp_message);
            System.out.println("=============================================");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp_code;
    }

    public static SOAPMessage createSOAPRequest(TagihanInsert tagihanInsert) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("tagihan_insert", "example");
        
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sUser", "example");
        soapBodyElem1.addTextNode(""+tagihanInsert.getsUser());
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("sPassword", "example");
        soapBodyElem2.addTextNode(""+tagihanInsert.getSPassword());
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("sInstansi", "example");
        soapBodyElem3.addTextNode(""+tagihanInsert.getSInstansi());
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("sNoid", "example");
        soapBodyElem4.addTextNode(""+tagihanInsert.getSNoId());
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("sNama", "example");
        soapBodyElem5.addTextNode(""+tagihanInsert.getSNama());
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("jum_tagihan", "example");
        soapBodyElem6.addTextNode(""+tagihanInsert.getJumTagihan());
        SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("sKet_1", "example");
        soapBodyElem7.addTextNode(""+tagihanInsert.getSKet_1());
        SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("sKet_2", "example");
        soapBodyElem8.addTextNode(""+tagihanInsert.getSKet_2());
        SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("sKet_3", "example");
        soapBodyElem9.addTextNode(""+tagihanInsert.getSKet_3());
        SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("sKet_4", "example");
        soapBodyElem10.addTextNode(""+tagihanInsert.getSKet_4());
        SOAPElement soapBodyElem11 = soapBodyElem.addChildElement("sKet_5", "example");
        soapBodyElem11.addTextNode(""+tagihanInsert.getSKet_5());
        SOAPElement soapBodyElem12 = soapBodyElem.addChildElement("sKet_6", "example");
        soapBodyElem12.addTextNode(""+tagihanInsert.getSKet_6());
        SOAPElement soapBodyElem13 = soapBodyElem.addChildElement("sKet_7", "example");
        soapBodyElem13.addTextNode(""+tagihanInsert.getSKet_7());
        SOAPElement soapBodyElem14 = soapBodyElem.addChildElement("sKet_8", "example");
        soapBodyElem14.addTextNode(""+tagihanInsert.getSKet_8());
        SOAPElement soapBodyElem15 = soapBodyElem.addChildElement("sKet_9", "example");
        soapBodyElem15.addTextNode(""+tagihanInsert.getSKet_9());
        SOAPElement soapBodyElem16 = soapBodyElem.addChildElement("sKet_10", "example");
        soapBodyElem16.addTextNode(""+tagihanInsert.getSKet_10());
        SOAPElement soapBodyElem17 = soapBodyElem.addChildElement("sKet_11", "example");
        soapBodyElem17.addTextNode(""+tagihanInsert.getSKet_11());
        SOAPElement soapBodyElem18 = soapBodyElem.addChildElement("sKet_12", "example");
        soapBodyElem18.addTextNode(""+tagihanInsert.getSKet_12());
        SOAPElement soapBodyElem19 = soapBodyElem.addChildElement("sKet_13", "example");
        soapBodyElem19.addTextNode(""+tagihanInsert.getSKet_13());
        SOAPElement soapBodyElem20 = soapBodyElem.addChildElement("sKet_14", "example");
        soapBodyElem20.addTextNode(""+tagihanInsert.getSKet_14());
        SOAPElement soapBodyElem21 = soapBodyElem.addChildElement("sKet_15", "example");
        soapBodyElem21.addTextNode(""+tagihanInsert.getSKet_15());
        SOAPElement soapBodyElem22 = soapBodyElem.addChildElement("sKet_16", "example");
        soapBodyElem22.addTextNode(""+tagihanInsert.getSKet_16());
        SOAPElement soapBodyElem23 = soapBodyElem.addChildElement("sKet_17", "example");
        soapBodyElem23.addTextNode(""+tagihanInsert.getSKet_17());
        SOAPElement soapBodyElem24 = soapBodyElem.addChildElement("sKet_18", "example");
        soapBodyElem24.addTextNode(""+tagihanInsert.getSKet_18());
        SOAPElement soapBodyElem26 = soapBodyElem.addChildElement("sKet_19", "example");
        soapBodyElem26.addTextNode(""+tagihanInsert.getSKet_19());
        SOAPElement soapBodyElem27 = soapBodyElem.addChildElement("sKet_20", "example");
        soapBodyElem27.addTextNode(""+tagihanInsert.getSKet_20());
        SOAPElement soapBodyElem28 = soapBodyElem.addChildElement("sKet_21", "example");
        soapBodyElem28.addTextNode(""+tagihanInsert.getSKet_21());
        SOAPElement soapBodyElem29 = soapBodyElem.addChildElement("sKet_22", "example");
        soapBodyElem29.addTextNode(""+tagihanInsert.getSKet_22());
        SOAPElement soapBodyElem30 = soapBodyElem.addChildElement("sKet_23", "example");
        soapBodyElem30.addTextNode(""+tagihanInsert.getSKet_23());
        SOAPElement soapBodyElem31 = soapBodyElem.addChildElement("sKet_24", "example");
        soapBodyElem31.addTextNode(""+tagihanInsert.getSKet_24());
        SOAPElement soapBodyElem32 = soapBodyElem.addChildElement("sKet_25", "example");
        soapBodyElem32.addTextNode(""+tagihanInsert.getSKet_25());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "tagihan_insert");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("n/Request SOAP Message: n/");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
    
    public static SOAPMessage createSOAPRequest2(TagihanInsert tagihanInsert) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "urn:WS_Interkoneksi";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tns", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("ws_tagihan_insert", "tns");
        
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("username", "tns");
        soapBodyElem1.addTextNode(""+tagihanInsert.getsUser());
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password", "tns");
        soapBodyElem2.addTextNode(""+tagihanInsert.getSPassword());
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("noid", "tns");
        soapBodyElem3.addTextNode(""+tagihanInsert.getSNoId());
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("nama", "tns");
        soapBodyElem4.addTextNode(""+tagihanInsert.getSNama());
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("tagihan", "tns");
        soapBodyElem5.addTextNode(""+tagihanInsert.getJumTagihan());
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("instansi", "tns");
        soapBodyElem6.addTextNode(""+tagihanInsert.getSInstansi());
        SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("ket_1_value", "tns");
        soapBodyElem7.addTextNode(""+tagihanInsert.getSKet_1());
        SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("ket_2_value", "tns");
        soapBodyElem8.addTextNode(""+tagihanInsert.getSKet_2());
        SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("ket_3_value", "tns");
        soapBodyElem9.addTextNode(""+tagihanInsert.getSKet_3());
        SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("ket_4_value", "tns");
        soapBodyElem10.addTextNode(""+tagihanInsert.getSKet_4());
        SOAPElement soapBodyElem11 = soapBodyElem.addChildElement("ket_5_value", "tns");
        soapBodyElem11.addTextNode(""+tagihanInsert.getSKet_5());
        SOAPElement soapBodyElem12 = soapBodyElem.addChildElement("ket_6_value", "tns");
        soapBodyElem12.addTextNode(""+tagihanInsert.getSKet_6());
        SOAPElement soapBodyElem13 = soapBodyElem.addChildElement("ket_7_value", "tns");
        soapBodyElem13.addTextNode(""+tagihanInsert.getSKet_7());
        SOAPElement soapBodyElem14 = soapBodyElem.addChildElement("ket_8_value", "tns");
        soapBodyElem14.addTextNode(""+tagihanInsert.getSKet_8());
        SOAPElement soapBodyElem15 = soapBodyElem.addChildElement("ket_9_value", "tns");
        soapBodyElem15.addTextNode(""+tagihanInsert.getSKet_9());
        SOAPElement soapBodyElem16 = soapBodyElem.addChildElement("ket_10_value", "tns");
        soapBodyElem16.addTextNode(""+tagihanInsert.getSKet_10());
        SOAPElement soapBodyElem17 = soapBodyElem.addChildElement("ket_11_value", "tns");
        soapBodyElem17.addTextNode(""+tagihanInsert.getSKet_11());
        SOAPElement soapBodyElem18 = soapBodyElem.addChildElement("ket_12_value", "tns");
        soapBodyElem18.addTextNode(""+tagihanInsert.getSKet_12());
        SOAPElement soapBodyElem19 = soapBodyElem.addChildElement("ket_13_value", "tns");
        soapBodyElem19.addTextNode(""+tagihanInsert.getSKet_13());
        SOAPElement soapBodyElem20 = soapBodyElem.addChildElement("ket_14_value", "tns");
        soapBodyElem20.addTextNode(""+tagihanInsert.getSKet_14());
        SOAPElement soapBodyElem21 = soapBodyElem.addChildElement("ket_15_value", "tns");
        soapBodyElem21.addTextNode(""+tagihanInsert.getSKet_15());
        SOAPElement soapBodyElem22 = soapBodyElem.addChildElement("ket_16_value", "tns");
        soapBodyElem22.addTextNode(""+tagihanInsert.getSKet_16());
        SOAPElement soapBodyElem23 = soapBodyElem.addChildElement("ket_17_value", "tns");
        soapBodyElem23.addTextNode(""+tagihanInsert.getSKet_17());
        SOAPElement soapBodyElem24 = soapBodyElem.addChildElement("ket_18_value", "tns");
        soapBodyElem24.addTextNode(""+tagihanInsert.getSKet_18());
        SOAPElement soapBodyElem25 = soapBodyElem.addChildElement("ket_19_value", "tns");
        soapBodyElem25.addTextNode(""+tagihanInsert.getSKet_19());
        SOAPElement soapBodyElem26 = soapBodyElem.addChildElement("ket_20_value", "tns");
        soapBodyElem26.addTextNode(""+tagihanInsert.getSKet_20());
        SOAPElement soapBodyElem27 = soapBodyElem.addChildElement("ket_21_value", "tns");
        soapBodyElem27.addTextNode(""+tagihanInsert.getSKet_21());
        SOAPElement soapBodyElem28 = soapBodyElem.addChildElement("ket_22_value", "tns");
        soapBodyElem28.addTextNode(""+tagihanInsert.getSKet_22());
        SOAPElement soapBodyElem29 = soapBodyElem.addChildElement("ket_23_value", "tns");
        soapBodyElem29.addTextNode(""+tagihanInsert.getSKet_23());
        SOAPElement soapBodyElem30 = soapBodyElem.addChildElement("ket_24_value", "tns");
        soapBodyElem30.addTextNode(""+tagihanInsert.getSKet_24());
        SOAPElement soapBodyElem31 = soapBodyElem.addChildElement("ket_25_value", "tns");
        soapBodyElem31.addTextNode(""+tagihanInsert.getSKet_25());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "#wstagihan_insert");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("n/Request SOAP Message: n/");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
}
