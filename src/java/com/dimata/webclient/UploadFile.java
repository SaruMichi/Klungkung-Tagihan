/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.webclient;

/**
 *
 * @author dimata005
 */
import com.dimata.common.session.email.SessEmail;
import com.dimata.dtaxintegration.entity.tagihan.CreateFile;
import com.dimata.dtaxintegration.entity.tagihan.FileSent;
import com.dimata.dtaxintegration.session.DTaxManagerPbb;
import com.dimata.dtaxintegration.session.DTaxManagerPhr;
import com.dimata.util.Formater;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
import com.oschrenk.io.Base64;
import java.util.Date;

public class UploadFile {

// public static void main(String[] args){
//        String resp_status = new String();
//        String resp_code = new String();
//        FileSent fileSent = new FileSent();
//       
//        try {
//            // TODO code application logic here
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
//
//            fileSent.setsUser("PBB_GIANYAR");//1
//            fileSent.setsPassword("123456");//2
//            fileSent.setsInstansi("PBB_GIANYAR");//6
//            fileSent.setFileName("master_pbb_gianyar_2.zip");
//            String patchFileUpload = "";
//            try {
//                CreateFile sent = new CreateFile();
//                String lokasi = "";
//                patchFileUpload = "E:\\Dimata\\File\\master_pbb_gianyar_2.zip";//sent.sentPbb(lokasi);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//            }
//
//            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(fileSent, patchFileUpload), url);
//            //soapResponse.writeTo(System.out);
//
//            soapResponse.writeTo(out);
//            String raw_respon = new String(out.toByteArray());
//            System.out.println("SOAP Respon = " + raw_respon);
//            resp_code = StringUtils.substringBetween(raw_respon, "<code>", "</code>");
//
//            System.out.println("=============================================");
//            System.out.println("GET STATUS");
//            System.out.println("Respone Code = " + resp_code);
//            System.out.println("status = " + resp_status);
//            System.out.println("=============================================");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public String actionPBB(FileSent fileSent) {
        String resp_status = new String();
        String resp_code = new String();
        DTaxManagerPbb dTaxManagerPbbx = new DTaxManagerPbb();
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            String patchFileUpload = "";
            String patchFileUploadZip = "";
            String statusProses="";
            try {
                CreateFile sent = new CreateFile();
                //cek type nya
                if(AppSetting.TYPE_APP_BACKOFFICE == AppSetting.APP_IPROTAX){
                     patchFileUpload = sent.sentPbbIpRotax(fileSent);
                }else{
                    patchFileUpload = sent.sentPbb(fileSent);
                }
                
                if(!DTaxManagerPbb.running){
                    resp_status="Stop";
                    return resp_status;
                }
                
                statusProses=" / Proses ZIP File on Location "+fileSent.getLocation();
                
                DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br>"+ statusProses;
                
                patchFileUploadZip=sent.zipFile(new File(patchFileUpload),fileSent,0);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            
//            stop dlu, uji coba create data saja
            if(!DTaxManagerPbb.running){
                resp_status="Stop";
                return resp_status;
            } 
            
            DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br>"+" / Proses Transfer File to BPD Jangan di STOP! ";
            
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(fileSent, patchFileUploadZip), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = " + raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon, "<code>", "</code>");
            resp_status = StringUtils.substringBetween(raw_respon, "<message>", "</message>");
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = " + resp_code);
            System.out.println("Berhasil  " + resp_status);
            System.out.println("=============================================");

        } catch (Exception ex) {
            ex.printStackTrace();
            DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br>"+ "Gagal Kirim";
        }
        return resp_status;
    }

    public String actionPHR(FileSent fileSent) {
        String resp_status = new String();
        String resp_code = new String();
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";

            String patchFileUpload = "";
            String patchFileUploadZip="";
            String statusProses="";
            try {
                
                CreateFile sent = new CreateFile();
                patchFileUpload = sent.sentPhr(fileSent);
                
                if(!DTaxManagerPhr.running){
                    resp_status="Stop";
                    return resp_status;
                } 
                statusProses=" / Proses ZIP File on Location "+fileSent.getLocation();
                DTaxManagerPhr.statusProses= statusProses;
                
                patchFileUploadZip=sent.zipFile(new File(patchFileUpload),fileSent,1);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            
            if(!DTaxManagerPhr.running){
                resp_status="Stop";
                return resp_status;
            }
            
            DTaxManagerPhr.statusProses = statusProses+" / Proses Transfer File to BPD Jangan di STOP! ";
            
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(fileSent, patchFileUploadZip), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = " + raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon, "<code>", "</code>");
            resp_status = StringUtils.substringBetween(raw_respon, "<message>", "</message>");
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = " + resp_code);
            System.out.println("status = " + resp_status);
            System.out.println("=============================================");
            
            
            Date newDatec = new Date();
            
            DTaxManagerPhr.statusProses = statusProses + "<br>"+" / Proses Transfer File Selesai "+" datae/time : "+Formater.formatDate(newDatec, "dd-MM-yyyy kk:mm")+" <br> <br>"+raw_respon;
            
            //proses kirim email
            try{
                SessEmail email = new SessEmail() ;
                email.sendEamil("", "Notifikasi Pengiriman Integrasi Pajak PHR "+fileSent.getFileName(), "Dear Team, Berikut hasil proses pengiriman tagihan <br> "+DTaxManagerPhr.statusProses);
            }catch(Exception ex){
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp_status;
    }

    public static SOAPMessage createSOAPRequest(FileSent fileSent, String lokasi) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("upload_file", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sUser", "example");
        soapBodyElem1.addTextNode("" + fileSent.getsUser());
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("sPassword", "example");
        soapBodyElem2.addTextNode("" + fileSent.getsPassword());
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("sInstansi", "example");
        soapBodyElem3.addTextNode("" + fileSent.getsInstansi());

        File file = new File(lokasi);
        //byte[] imageBytes = new byte[(int) file.length()];
        //String test = "realhowto";
        //byte[] bFile = new byte[(int) file.length()];
        //String file = readFile(lokasi);
        //String file = readFile(lokasi);
        String res1 = Base64.encodeFromFile(lokasi);
        //SOAPElement soapBodyElem4 =soapBodyElem.addChildElement("Data", "example").addAttribute(new QName("EncodingType"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("Data", "example");
        soapBodyElem4.addTextNode(res1);
        
        
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("fileName", "example");
        soapBodyElem5.addTextNode("" + fileSent.getFileNameZip());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "upload_file");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("n/Request SOAP Message: n/");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
     private static String hexEncode(String in) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < (in.length() - 2) + 1; i = i + 2) {
            int c = Integer.parseInt(in.substring(i, i + 2), 16);
            char chr = (char) c;
            sb.append(chr);
        }
        return sb.toString();
    }
    
    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
// File is too large
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }
    
   private static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
