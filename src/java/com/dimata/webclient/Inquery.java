/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimata.webclient;
import com.dimata.dtaxintegration.entity.inquery.InqueryProses;
import com.dimata.dtaxintegration.entity.tagihan.Tagihan;
import java.io.ByteArrayOutputStream;
import java.util.Vector;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import org.apache.commons.lang.StringUtils;

import org.w3c.dom.*;
/**
 *
 * @author dimata005
 */
public class Inquery {
    public static String RES_OK = "00";
    public static String RES_ERROR = "01";
    
    private String status = "00";
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * @return the logErorInquery
     */
    public static String getLogErorInquery() {
        return logErorInquery;
    }

    /**
     * @param aLogErorInquery the logErorInquery to set
     */
    public static void setLogErorInquery(String aLogErorInquery) {
        logErorInquery = aLogErorInquery;
    }
    
//    public static void main(String[] args) {
//        String resp_status = new String();
//        String resp_code = new String();
//        InqueryProses inqueryProses = new InqueryProses();
//        try {
//            // TODO code application logic here
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
//            inqueryProses.setsUser(AppSetting.USERNAME_PBB);
//            inqueryProses.setsPassword(AppSetting.PWD_PBB);
//            inqueryProses.setsInstansi(AppSetting.INSTANSI_PBB);
//            inqueryProses.setsNoId("510400200500003827");
//            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(inqueryProses), url);
//            soapResponse.writeTo(System.out);
//
//            soapResponse.writeTo(out);
//            String raw_respon = new String(out.toByteArray());
//            System.out.println("SOAP Respon = "+raw_respon);
//            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
//            resp_status = StringUtils.substringBetween(raw_respon,"<message>","</message>");
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
    private static String logErorInquery="";
    
    public String action(InqueryProses inqueryProses){
        String resp_code = new String();
        String resp_message = new String();
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(inqueryProses), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = "+raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
            resp_message=StringUtils.substringBetween(raw_respon,"<message>","</message>");
            
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = "+ resp_code);
            System.out.println("Respone Code = "+ resp_message);
            System.out.println("=============================================");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp_code;
    }
    
    
    public synchronized Vector InqueryPHR(InqueryProses inqueryProses){
        String resp_code = new String();
        String resp_message = new String();
        Vector listTagihan=new Vector();
        setLogErorInquery("Proses Inquery : "); 
        String raw_respon = "";
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createInquiryRequest(inqueryProses), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = "+raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
            resp_message=StringUtils.substringBetween(raw_respon,"<message>","</message>");
            
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = "+ resp_code);
            System.out.println("Respone Code = "+ resp_message);
            System.out.println("=============================================");
            
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            TransformerFactory tf = TransformerFactory.newInstance();  
            Transformer transformer = tf.newTransformer();  
            DOMResult result = new DOMResult();  
            transformer.transform(sourceContent, result);  
            Document doc = (Document) result.getNode(); 
            String tag = "Table";
            String attr = "diffgr:id";
            String attrValue = "Table1";
            NodeList list = doc.getElementsByTagName("Table");
            Element tableNode = null;
            int count =0;
            
            for (int i = 0; i < list.getLength(); i++) {
                 
                    Tagihan tagihan = new Tagihan();
                    
                    count=count+1;
                    tableNode = ((Element) list.item(i));

                    String childId = "id";
                    Node nodeId = (Node) tableNode.getElementsByTagName(childId).item(0);
                    String id = nodeId.getTextContent();
                    
                    String childNoId = "no_id";
                    Node nodeNoId = (Node) tableNode.getElementsByTagName(childNoId).item(0);
                    String no_id = nodeNoId.getTextContent();
                    
                    String childNama = "nama";
                    Node nodeNama = (Node) tableNode.getElementsByTagName(childNama).item(0);
                    String nama = nodeNama.getTextContent();
                    
                    String childTagihan = "tagihan";
                    Node nodeTagihan = (Node) tableNode.getElementsByTagName(childTagihan).item(0);
                    String tagihanPajak = nodeTagihan.getTextContent();
                    
                    String childInstansi = "instansi_id";
                    Node nodeInstansi = (Node) tableNode.getElementsByTagName(childInstansi).item(0);
                    String instansi = nodeInstansi.getTextContent();
                     
                    String childAlamat = "Alamat";
                    Node nodeAlamat = (Node) tableNode.getElementsByTagName(childAlamat).item(0);
                    String Alamat = nodeAlamat.getTextContent();
                    
                    String childBulan = "Bulan";
                    Node nodeBulan = (Node) tableNode.getElementsByTagName(childBulan).item(0);
                    String Bulan = nodeBulan.getTextContent();
                    
                    String childTahun = "Tahun";
                    Node nodeTahun = (Node) tableNode.getElementsByTagName(childTahun).item(0);
                    String Tahun = nodeTahun.getTextContent();
                    
                    String childPokok = "Pokok";
                    Node nodePokok = (Node) tableNode.getElementsByTagName(childPokok).item(0);
                    String Pokok = nodePokok.getTextContent();
                    
                    String childDenda = "Denda";
                    Node nodeDenda = (Node) tableNode.getElementsByTagName(childDenda).item(0);
                    String Denda = nodeDenda.getTextContent();
                    
                    String childsts_bayar = "sts_bayar";
                    Node nodests_bayar = (Node) tableNode.getElementsByTagName(childsts_bayar).item(0);
                    String sts_bayar = nodests_bayar.getTextContent();
                    
                    tagihan.setId(id);//1
                    tagihan.setNoId(no_id);
                    tagihan.setNama(nama);//2
                    tagihan.setTagihan(tagihanPajak);//3
                    tagihan.setInstansiId(instansi);//4
                    tagihan.setAlamat(Alamat);//5
                    tagihan.setBulan(Bulan);//6
                    tagihan.setTahun(Tahun);//7
                    tagihan.setPokok(Pokok);//8
                    tagihan.setDenda(Denda);//9
                    tagihan.setStsBayar(sts_bayar);//1
                    
                    listTagihan.add(tagihan);
                    setLogErorInquery(""+getLogErorInquery()+" Berhasil ");
             }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            setLogErorInquery("  Gagal Cek Koneksi, raw_respon = "+raw_respon+" Exception : "+ex.getMessage());
            setStatus(RES_ERROR);
        }
        return listTagihan;
    }

    public Vector InqueryPBB(InqueryProses inqueryProses){
        String resp_code = new String();
        String resp_message = new String();
         Vector listTagihan=new Vector();
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(inqueryProses), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = "+raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
            resp_message=StringUtils.substringBetween(raw_respon,"<message>","</message>");
            
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = "+ resp_code);
            System.out.println("Respone Code = "+ resp_message);
            System.out.println("=============================================");
            
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            TransformerFactory tf = TransformerFactory.newInstance();  
            Transformer transformer = tf.newTransformer();  
            DOMResult result = new DOMResult();  
            transformer.transform(sourceContent, result);  
            Document doc = (Document) result.getNode(); 
            String tag = "Table";
            String attr = "diffgr:id";
            String attrValue = "Table1";
            NodeList list = doc.getElementsByTagName("Table");
            Element tableNode = null;
            int count =0;
            
            for (int i = 0; i < list.getLength(); i++) {
                 
                    Tagihan tagihan = new Tagihan();
                    
                    count=count+1;
                    tableNode = ((Element) list.item(i));

                    String childId = "id";
                    Node nodeId = (Node) tableNode.getElementsByTagName(childId).item(0);
                    String id = nodeId.getTextContent();
                    
                    String childInstansi = "instansi_id";
                    Node nodeInstansi = (Node) tableNode.getElementsByTagName(childInstansi).item(0);
                    String instansi = nodeInstansi.getTextContent();
                     
                    String childNoId = "no_id";
                    Node nodeNoId = (Node) tableNode.getElementsByTagName(childNoId).item(0);
                    String no_id = nodeNoId.getTextContent();
                    
                    String childNama = "nama";
                    Node nodeNama = (Node) tableNode.getElementsByTagName(childNama).item(0);
                    String nama = nodeNama.getTextContent();
                    
                    String childTagihan = "tagihan";
                    Node nodeTagihan = (Node) tableNode.getElementsByTagName(childTagihan).item(0);
                    String tagihanPajak = nodeTagihan.getTextContent();
                    
                    String childAlamat = "AlamatWP";
                    Node nodeAlamat = (Node) tableNode.getElementsByTagName(childAlamat).item(0);
                    String Alamat = nodeAlamat.getTextContent();
                    
                    String childNPWP = "NPWP";
                    Node nodeNPWP = (Node) tableNode.getElementsByTagName(childNPWP).item(0);
                    String NPWP = nodeNPWP.getTextContent();
                    
                    String childLetakObjekPajak = "LetakObjekPajak";
                    Node nodeLetakObjekPajak = (Node) tableNode.getElementsByTagName(childLetakObjekPajak).item(0);
                    String LetakObjekPajak = nodeLetakObjekPajak.getTextContent();
                    
                    String childTahun = "Tahun";
                    Node nodeTahun = (Node) tableNode.getElementsByTagName(childTahun).item(0);
                    String Tahun = nodeTahun.getTextContent();
                    
                    String childTglJatuhTempo = "TglJatuhTempo";
                    Node nodeTglJatuhTempo = (Node) tableNode.getElementsByTagName(childTglJatuhTempo).item(0);
                    String TglJatuhTempo = nodeTglJatuhTempo.getTextContent();
                    
                    String childLuasBumi = "LuasBumi";
                    Node nodeLuasBumi = (Node) tableNode.getElementsByTagName(childLuasBumi).item(0);
                    String luasBumi = nodeLuasBumi.getTextContent();
                    
                    String childLuasBangunan = "LuasBangunan";
                    Node nodeLuasBangunan = (Node) tableNode.getElementsByTagName(childLuasBangunan).item(0);
                    String LuasBangunan = nodeLuasBangunan.getTextContent();
                    
                    String childNJOPBumi = "NJOPBumi";
                    Node nodeNJOPBumi = (Node) tableNode.getElementsByTagName(childNJOPBumi).item(0);
                    String NJOPBumi = nodeNJOPBumi.getTextContent();
                    
                    String childNJOPBangunan = "NJOPBangunan";
                    Node nodechildNJOPBangunan = (Node) tableNode.getElementsByTagName(childNJOPBangunan).item(0);
                    String nJOPBangunan = nodechildNJOPBangunan.getTextContent();
                    
                    String childNJOPTKP = "NJOPTKP";
                    Node nodeNJOPTKP = (Node) tableNode.getElementsByTagName(childNJOPTKP).item(0);
                    String nJOPTKP = nodeNJOPTKP.getTextContent();
                    
                    String childDenda = "Denda";
                    Node nodeDenda = (Node) tableNode.getElementsByTagName(childDenda).item(0);
                    String denda = nodeDenda.getTextContent();
                    
                    String childFormula = "Formula";
                    Node nodeFormula = (Node) tableNode.getElementsByTagName(childFormula).item(0);
                    String formula = nodeFormula.getTextContent();
                    
                    String childTerbilang = "Terbilang";
                    Node nodeTerbilang = (Node) tableNode.getElementsByTagName(childTerbilang).item(0);
                    String terbilang = nodeTerbilang.getTextContent();
                    
                    String childsts_bayar = "sts_bayar";
                    Node nodests_bayar = (Node) tableNode.getElementsByTagName(childsts_bayar).item(0);
                    String sts_bayar = nodests_bayar.getTextContent();
                    
                    tagihan.setId(id);//1
                    tagihan.setNoId(no_id);
                    tagihan.setNama(nama);//2
                    tagihan.setTagihan(tagihanPajak);//3
                    tagihan.setInstansiId(instansi);//4
                    tagihan.setAlamat(Alamat);//5
                    tagihan.setnPWP(NPWP);
                    tagihan.setLetakObjekPajak(LetakObjekPajak);
                    tagihan.setTahun(Tahun);//7
                    tagihan.setTglJatuhTempo(TglJatuhTempo); 
                    tagihan.setLuasBumi(luasBumi);
                    tagihan.setLuasBangunan(LuasBangunan);
                    tagihan.setnJOPBumi(NJOPBumi);
                    tagihan.setnJOPBangunan(nJOPBangunan);
                    tagihan.setnJOPTKP(nJOPTKP);
                    tagihan.setDenda(denda);//9
                    tagihan.setFormula(formula);
                    tagihan.setTerbilang(terbilang);
                    tagihan.setStsBayar(sts_bayar);//1
                    
                    listTagihan.add(tagihan);
             }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listTagihan;
    }
    
    
    public Vector InqueryBPHTB(InqueryProses inqueryProses){
        String resp_code = new String();
        String resp_message = new String();
         Vector listTagihan =new Vector();
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(inqueryProses), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = "+raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
            resp_message=StringUtils.substringBetween(raw_respon,"<message>","</message>");
            
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = "+ resp_code);
            System.out.println("Respone Code = "+ resp_message);
            System.out.println("=============================================");
            
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            TransformerFactory tf = TransformerFactory.newInstance();  
            Transformer transformer = tf.newTransformer();  
            DOMResult result = new DOMResult();  
            transformer.transform(sourceContent, result);  
            Document doc = (Document) result.getNode(); 
            String tag = "Table";
            String attr = "diffgr:id";
            String attrValue = "Table1";
            NodeList list = doc.getElementsByTagName("Table");
            Element tableNode = null;
            int count =0;
            
            for (int i = 0; i < list.getLength(); i++) {
                 
                    Tagihan tagihan = new Tagihan();
                    
                    count=count+1;
                    tableNode = ((Element) list.item(i));

                    String childId = "id";
                    Node nodeId = (Node) tableNode.getElementsByTagName(childId).item(0);
                    String id = nodeId.getTextContent();
                    
                    String childInstansi = "instansi_id";
                    Node nodeInstansi = (Node) tableNode.getElementsByTagName(childInstansi).item(0);
                    String instansi = nodeInstansi.getTextContent();
                     
                    String childNoId = "no_id";
                    Node nodeNoId = (Node) tableNode.getElementsByTagName(childNoId).item(0);
                    String no_id = nodeNoId.getTextContent();
                    
                    String childNama = "nama";
                    Node nodeNama = (Node) tableNode.getElementsByTagName(childNama).item(0);
                    String nama = nodeNama.getTextContent();
                    
                    String childTagihan = "tagihan";
                    Node nodeTagihan = (Node) tableNode.getElementsByTagName(childTagihan).item(0);
                    String tagihanPajak = nodeTagihan.getTextContent();
                    
                    String childNOP = "NOP";
                    Node nodeNOP = (Node) tableNode.getElementsByTagName(childNOP).item(0);
                    String NOP = nodeNOP.getTextContent();
                    
                    String childLetakObjekPajak = "LetakObjekPajak";
                    Node nodeLetakObjekPajak = (Node) tableNode.getElementsByTagName(childLetakObjekPajak).item(0);
                    String LetakObjekPajak = nodeLetakObjekPajak.getTextContent();
                    
                    String childPokok = "Pokok";
                    Node nodePokok = (Node) tableNode.getElementsByTagName(childPokok).item(0);
                    String pokok = nodePokok.getTextContent();
                    
                    String childDenda = "Denda";
                    Node nodeDenda = (Node) tableNode.getElementsByTagName(childDenda).item(0);
                    String denda = nodeDenda.getTextContent();
                    
                    String childsts_bayar = "sts_bayar";
                    Node nodests_bayar = (Node) tableNode.getElementsByTagName(childsts_bayar).item(0);
                    String sts_bayar = nodests_bayar.getTextContent();
                    
                    
                    tagihan.setId(id);//1
                    tagihan.setNoId(no_id);
                    tagihan.setNama(nama);//2
                    tagihan.setTagihan(tagihanPajak);//3
                    tagihan.setInstansiId(instansi);//4
                    tagihan.setNop(NOP);
                    tagihan.setLetakObjekPajak(LetakObjekPajak);
                    tagihan.setPokok(pokok);
                    tagihan.setDenda(denda);
                    tagihan.setStsBayar(sts_bayar);
                    
                    listTagihan.add(tagihan);
             }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listTagihan;
    }
    
    public Vector InqueryRetribusi(InqueryProses inqueryProses){
        String resp_code = new String();
        String resp_message = new String();
         Vector listPayment=new Vector();
        try {
            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(inqueryProses), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = "+raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
            resp_message=StringUtils.substringBetween(raw_respon,"<message>","</message>");
            
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = "+ resp_code);
            System.out.println("Respone Code = "+ resp_message);
            System.out.println("=============================================");
            
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            TransformerFactory tf = TransformerFactory.newInstance();  
            Transformer transformer = tf.newTransformer();  
            DOMResult result = new DOMResult();  
            transformer.transform(sourceContent, result);  
            Document doc = (Document) result.getNode(); 
            String tag = "Table";
            String attr = "diffgr:id";
            String attrValue = "Table1";
            NodeList list = doc.getElementsByTagName("Table");
            Element tableNode = null;
            int count =0;
            
            for (int i = 0; i < list.getLength(); i++) {
                 
                    Tagihan tagihan = new Tagihan();
                    
                    count=count+1;
                    tableNode = ((Element) list.item(i));

                    String childId = "id";
                    Node nodeId = (Node) tableNode.getElementsByTagName(childId).item(0);
                    String id = nodeId.getTextContent();
                    
                    String childInstansi = "instansi_id";
                    Node nodeInstansi = (Node) tableNode.getElementsByTagName(childInstansi).item(0);
                    String instansi = nodeInstansi.getTextContent();
                     
                    String childNoId = "no_id";
                    Node nodeNoId = (Node) tableNode.getElementsByTagName(childNoId).item(0);
                    String no_id = nodeNoId.getTextContent();
                    
                    String childNama = "nama";
                    Node nodeNama = (Node) tableNode.getElementsByTagName(childNama).item(0);
                    String nama = nodeNama.getTextContent();
                    
                    String childTagihan = "tagihan";
                    Node nodeTagihan = (Node) tableNode.getElementsByTagName(childTagihan).item(0);
                    String tagihanPajak = nodeTagihan.getTextContent();
                    
                    String childKetTagihanLain = "ket_tagihan_lain";
                    Node nodeKetTagihanLain = (Node) tableNode.getElementsByTagName(childKetTagihanLain).item(0);
                    String ketTagihanLain = nodeKetTagihanLain.getTextContent();
                    
                    String childTagihanLain = "tagihan_lain";
                    Node nodeTagihanLain = (Node) tableNode.getElementsByTagName(childTagihanLain).item(0);
                    String tagihanLain = nodeTagihanLain.getTextContent();
                    
                    String childTanggalTagihan = "TanggalTagihan";
                    Node nodeTanggalTagihan = (Node) tableNode.getElementsByTagName(childTanggalTagihan).item(0);
                    String TanggalTagihan = nodeTanggalTagihan.getTextContent();
                    
                    String childsts_bayar = "sts_bayar";
                    Node nodests_bayar = (Node) tableNode.getElementsByTagName(childsts_bayar).item(0);
                    String sts_bayar = nodests_bayar.getTextContent();
                    
                    
                    tagihan.setId(id);//1
                    tagihan.setNoId(no_id);
                    tagihan.setNama(nama);//2
                    tagihan.setTagihan(tagihanPajak);//3
                    tagihan.setInstansiId(instansi);//4
                    tagihan.setKetTagihanLain(ketTagihanLain);
                    tagihan.setTagihan(tagihanLain);
                    tagihan.setTanggalTagihan(TanggalTagihan);
                    tagihan.setStsBayar(sts_bayar);//1
                    
                    listPayment.add(tagihan);
             }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPayment;
    }

    public static SOAPMessage createSOAPRequest(InqueryProses inqueryProses) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("inquery", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sUser", "example");
        soapBodyElem1.addTextNode(""+inqueryProses.getsUser());
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("sPassword", "example");
        soapBodyElem2.addTextNode(""+inqueryProses.getsPassword());
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("sInstansi", "example");
        soapBodyElem3.addTextNode(""+inqueryProses.getsInstansi());
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("sNoId", "example");
        soapBodyElem4.addTextNode(""+inqueryProses.getsNoId());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "inquery");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("n/Request SOAP Message: n/");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
    
    public static SOAPMessage createInquiryRequest(InqueryProses inqueryProses) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        
        String username = inqueryProses.getsUser();
        String password = inqueryProses.getsPassword();
        String instansi = inqueryProses.getsInstansi();
        String noid = inqueryProses.getsNoId();
        
        String serverURI = "urn:WS_Interkoneksi";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tns", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("ws_inquiry_tagihan", "tns");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("username", "tns");
        soapBodyElem1.addTextNode(username);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password", "tns");
        soapBodyElem2.addTextNode(password);
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("instansi", "tns");
        soapBodyElem3.addTextNode(instansi);
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("noid", "tns");
        soapBodyElem4.addTextNode(noid);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "#ws_inquiry_tagihan");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }


}
