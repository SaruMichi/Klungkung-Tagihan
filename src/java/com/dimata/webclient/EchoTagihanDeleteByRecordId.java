/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimata.webclient;

import com.dimata.dtaxintegration.entity.tagihan.TagihanDelete;
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
 * @author dimata005
 */
public class EchoTagihanDeleteByRecordId {

    /**
     * @return the logErorDeteleDataTagihanByRecord
     */
    public static String getLogErorDeteleDataTagihanByRecord() {
        return logErorDeteleDataTagihanByRecord;
    }

    /**
     * @param aLogErorDeteleDataTagihanByRecord the logErorDeteleDataTagihanByRecord to set
     */
    public static void setLogErorDeteleDataTagihanByRecord(String aLogErorDeteleDataTagihanByRecord) {
        logErorDeteleDataTagihanByRecord = aLogErorDeteleDataTagihanByRecord;
    }
    private static String logErorDeteleDataTagihanByRecord="";
    
    public String action(TagihanDelete tagihanDeleteInstansi){
        String resp_code = new String();
        String resp_message = new String();
        setLogErorDeteleDataTagihanByRecord("");
        String raw_respon ="";
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(tagihanDeleteInstansi), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = "+raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
            resp_message=StringUtils.substringBetween(raw_respon,"<message>","</message>");

            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = "+ resp_code);
            System.out.println("Respone Message = "+ resp_message);
            System.out.println("=============================================");
            setLogErorDeteleDataTagihanByRecord("Delete Data di Bank Berhasil");
        } catch (Exception ex) {
            ex.printStackTrace();
            setLogErorDeteleDataTagihanByRecord("Hapus Data Gagal, Ex : "+ex.getMessage()+" Respon : "+raw_respon);
        }
        return resp_code;
    }

    public static SOAPMessage createSOAPRequest(TagihanDelete tagihanDeleteInstansi) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("tagihan_delete_by_record_id", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sUser", "example");
        soapBodyElem1.addTextNode(""+tagihanDeleteInstansi.getsUser());
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("sPassword", "example");
        soapBodyElem2.addTextNode(""+tagihanDeleteInstansi.getsPassword());
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("sInstansi", "example");
        soapBodyElem3.addTextNode(""+tagihanDeleteInstansi.getsInstansi());
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("sNoid", "example");
        soapBodyElem4.addTextNode(""+tagihanDeleteInstansi.getsNoId());
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("sRecordId", "example");
        soapBodyElem5.addTextNode(""+tagihanDeleteInstansi.getsRecordId());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "tagihan_delete_by_record_id");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("n/Request SOAP Message: n/");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
}
