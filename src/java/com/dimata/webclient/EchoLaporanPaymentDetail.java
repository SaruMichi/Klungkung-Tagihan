/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.webclient;

import com.dimata.dtaxintegration.entity.inquery.Payment;
import com.dimata.dtaxintegration.entity.laporan.LaporanPayment;
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
public class EchoLaporanPaymentDetail {

//      public static void main(String[] args) {
//        String resp_status = new String();
//        String resp_code = new String();
//        //InqueryProses inqueryProses = new InqueryProses();
//        LaporanPayment laporanPayment = new LaporanPayment();
//        try {
//            // TODO code application logic here
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//            String url = "http://192.168.201.78:88/index.asmx";
//            
//            laporanPayment.setsUser("PHR_GIANYAR");
//            laporanPayment.setsPassword("123456");
//            laporanPayment.setsInstansi("PHR_GIANYAR");
//            laporanPayment.setsDate("2015-05-18");
//            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(laporanPayment), url);
//            soapResponse.writeTo(System.out);
//
//            soapResponse.writeTo(out);
//            String raw_respon = new String(out.toByteArray());
//            System.out.println("SOAP Respon = "+raw_respon);
//            resp_code = StringUtils.substringBetween(raw_respon,"<code>","</code>");
//            resp_status=StringUtils.substringBetween(raw_respon,"<no_id>","</no_id>");
//            System.out.println("=============================================");
//            System.out.println("GET STATUS");
//            System.out.println("Respone Code = "+ resp_code);
//            System.out.println("status = "+ resp_status);
//            System.out.println("=============================================");
//            
//            
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    public Vector getListPaymentDetailPBB(LaporanPayment laporanPayment) {
        String resp_code = new String();
        String resp_message = new String();
        Vector listPayment = new Vector();
        try {

            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(laporanPayment), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = " + raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon, "<code>", "</code>");
            resp_message = StringUtils.substringBetween(raw_respon, "<Table>", "</Table>");

            //StringUtils.
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = " + resp_code);
            System.out.println("Respone Message = " + resp_message);
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
            int count = 0;
            for (int i = 0; i < list.getLength(); i++) {

                Payment payment = new Payment();

                count = count + 1;
                tableNode = ((Element) list.item(i));

                String childId = "id";
                Node nodeId = (Node) tableNode.getElementsByTagName(childId).item(0);
                String id = nodeId.getTextContent();

                String childInstansi = "instansi";
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
                String tagihan = nodeTagihan.getTextContent();

                String childKetTagihanLain = "ket_tagihan_lain";
                Node nodeKetTagihanLain = (Node) tableNode.getElementsByTagName(childKetTagihanLain).item(0);
                String ketTagihanLain = nodeKetTagihanLain.getTextContent();

                String childTagihanLain = "tagihan_lain";
                Node nodeTagihanLain = (Node) tableNode.getElementsByTagName(childTagihanLain).item(0);
                String tagihanLain = nodeTagihanLain.getTextContent();

                String childBiaya = "biaya_adm";
                Node nodeBiaya = (Node) tableNode.getElementsByTagName(childBiaya).item(0);
                String biaya = nodeBiaya.getTextContent();

                String childNpwp = "NPWP";
                Node nodeNpwp = (Node) tableNode.getElementsByTagName(childNpwp).item(0);
                String npwp = nodeNpwp.getTextContent();

                String childAlamatWp = "AlamatWP";
                Node nodeAlamatWp = (Node) tableNode.getElementsByTagName(childAlamatWp).item(0);
                String alamatWp = nodeAlamatWp.getTextContent();

                String childLetakObjekPajak = "LetakObjekPajak";
                Node nodeLetakObjekPajak = (Node) tableNode.getElementsByTagName(childLetakObjekPajak).item(0);
                String letakObjekPajak = nodeLetakObjekPajak.getTextContent();

                String childTahun = "Tahun";
                Node nodeTahun = (Node) tableNode.getElementsByTagName(childTahun).item(0);
                String tahun = nodeTahun.getTextContent();

                String childTglJatuhTempo = "TglJatuhTempo";
                Node nodeTglJatuhTempo = (Node) tableNode.getElementsByTagName(childTglJatuhTempo).item(0);
                String jatuhTempo = nodeTglJatuhTempo.getTextContent();

                String childLuasBumi = "LuasBumi";
                Node nodeLuasBumi = (Node) tableNode.getElementsByTagName(childLuasBumi).item(0);
                String luasBumi = nodeLuasBumi.getTextContent();

                String childLuasBangunan = "LuasBangunan";
                Node nodeLuasBangunan = (Node) tableNode.getElementsByTagName(childLuasBangunan).item(0);
                String luasBangunan = nodeLuasBangunan.getTextContent();

                String childNJOPBumi = "NJOPBumi";
                Node nodeNJOPBumi = (Node) tableNode.getElementsByTagName(childNJOPBumi).item(0);
                String nJOPBumi = nodeNJOPBumi.getTextContent();

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

                String childval_entri_1_tagihan_lain = "val_entri_1_tagihan_lain";
                Node nodechildval_entri_1_tagihan_lain = (Node) tableNode.getElementsByTagName(childval_entri_1_tagihan_lain).item(0);
                String val_entri_1_tagihan_lain = nodechildval_entri_1_tagihan_lain.getTextContent();

                String childTgl = "tgl_tx";
                Node nodeTgl = (Node) tableNode.getElementsByTagName(childTgl).item(0);
                String tgl_tx = nodeTgl.getTextContent();

                String childsts_bayar = "sts_bayar";
                Node nodests_bayar = (Node) tableNode.getElementsByTagName(childsts_bayar).item(0);
                String sts_bayar = nodests_bayar.getTextContent();

                String childkd_cab = "kd_cab";
                Node nodekd_cab = (Node) tableNode.getElementsByTagName(childkd_cab).item(0);
                String kd_cab = nodekd_cab.getTextContent();

                String childkd_user = "kd_user";
                Node nodekd_user = (Node) tableNode.getElementsByTagName(childkd_user).item(0);
                String kd_user = nodekd_user.getTextContent();

                String childStatus = "sts_reversal";
                Node nodeStatus = (Node) tableNode.getElementsByTagName(childStatus).item(0);
                String sts_reversal = nodeStatus.getTextContent();

                payment.setId(id);
                payment.setInstansi(instansi);
                payment.setNoId(no_id);
                payment.setNama(nama);
                payment.setTagihan(tagihan);
                payment.setKetTagihan(ketTagihanLain);
                payment.setTagihanLain(tagihanLain);
                payment.setBiayaAdm(biaya);
                payment.setNpwp(npwp);
                payment.setAlamatWp(alamatWp);
                payment.setLetakObjectPajak(letakObjekPajak);
                payment.setTahun(tahun);
                payment.setTglJatuhTempo(jatuhTempo);
                payment.setLuasBumi(luasBumi);
                payment.setLuasBangunan(luasBangunan);
                payment.setNjopBumi(nJOPBumi);
                payment.setNjopBangunan(nJOPBangunan);
                payment.setNjopTKP(nJOPTKP);
                payment.setDenda(denda);
                payment.setFormula(formula);
                payment.setTerbilang(terbilang);
                payment.setValEntri1TagihanLain(val_entri_1_tagihan_lain);
                payment.setTglTx(tgl_tx);
                payment.setStsBayar(sts_bayar);
                payment.setKdCab(kd_cab);
                payment.setKdUser(kd_user);
                payment.setStsReversal(sts_reversal);

                listPayment.add(payment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPayment;
    }

    public Vector getListPaymentDetailPHR(LaporanPayment laporanPayment) {
        String resp_code = new String();
        String resp_message = new String();
        Vector listPayment = new Vector();
        try {

            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(laporanPayment), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = " + raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon, "<code>", "</code>");
            resp_message = StringUtils.substringBetween(raw_respon, "<Table>", "</Table>");

            //StringUtils.
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = " + resp_code);
            System.out.println("Respone Message = " + resp_message);
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
            int count = 0;
            for (int i = 0; i < list.getLength(); i++) {

                Payment payment = new Payment();

                count = count + 1;
                tableNode = ((Element) list.item(i));

                String childId = "id";
                Node nodeId = (Node) tableNode.getElementsByTagName(childId).item(0);
                String id = nodeId.getTextContent();

                String childInstansi = "instansi";
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
                String tagihan = nodeTagihan.getTextContent();

                String childKetTagihanLain = "ket_tagihan_lain";
                Node nodeKetTagihanLain = (Node) tableNode.getElementsByTagName(childKetTagihanLain).item(0);
                String ketTagihanLain = nodeKetTagihanLain.getTextContent();

                String childTagihanLain = "tagihan_lain";
                Node nodeTagihanLain = (Node) tableNode.getElementsByTagName(childTagihanLain).item(0);
                String tagihanLain = nodeTagihanLain.getTextContent();

                String childBiaya = "biaya_adm";
                Node nodeBiaya = (Node) tableNode.getElementsByTagName(childBiaya).item(0);
                String biaya = nodeBiaya.getTextContent();

                String childAlamatWp = "Alamat";
                Node nodeAlamatWp = (Node) tableNode.getElementsByTagName(childAlamatWp).item(0);
                String alamatWp = nodeAlamatWp.getTextContent();

                String childLetakBulan = "Bulan";
                Node nodeBulan = (Node) tableNode.getElementsByTagName(childLetakBulan).item(0);
                String bulan = nodeBulan.getTextContent();

                String childTahun = "Tahun";
                Node nodeTahun = (Node) tableNode.getElementsByTagName(childTahun).item(0);
                String tahun = nodeTahun.getTextContent();

                String childPokok = "Pokok";
                Node nodePokok = (Node) tableNode.getElementsByTagName(childPokok).item(0);
                String pokok = nodePokok.getTextContent();

                String childDenda = "Denda";
                Node nodeDenda = (Node) tableNode.getElementsByTagName(childDenda).item(0);
                String denda = nodeDenda.getTextContent();
                
                String val_entri_1_tagihan_lain = "";
                try {
                    String childval_entri_1_tagihan_lain = "val_entri_1_tagihan_lain";
                    Node nodechildval_entri_1_tagihan_lain = (Node) tableNode.getElementsByTagName(childval_entri_1_tagihan_lain).item(0);
                    val_entri_1_tagihan_lain = nodechildval_entri_1_tagihan_lain.getTextContent();
                } catch (Exception e) {
                }

                String childTgl = "tgl_tx";
                Node nodeTgl = (Node) tableNode.getElementsByTagName(childTgl).item(0);
                String tgl_tx = nodeTgl.getTextContent();

                String childsts_bayar = "sts_bayar";
                Node nodests_bayar = (Node) tableNode.getElementsByTagName(childsts_bayar).item(0);
                String sts_bayar = nodests_bayar.getTextContent();

                String childkd_cab = "kd_cab";
                Node nodekd_cab = (Node) tableNode.getElementsByTagName(childkd_cab).item(0);
                String kd_cab = nodekd_cab.getTextContent();

                String childkd_user = "kd_user";
                Node nodekd_user = (Node) tableNode.getElementsByTagName(childkd_user).item(0);
                String kd_user = nodekd_user.getTextContent();

                String childStatus = "sts_reversal";
                Node nodeStatus = (Node) tableNode.getElementsByTagName(childStatus).item(0);
                String sts_reversal = nodeStatus.getTextContent();

                payment.setId(id);
                payment.setInstansi(instansi);
                payment.setNoId(no_id);
                payment.setNama(nama);
                payment.setTagihan(tagihan);
                payment.setKetTagihan(ketTagihanLain);
                payment.setTagihanLain(tagihanLain);
                payment.setBiayaAdm(biaya);
                payment.setAlamatWp(alamatWp);
                payment.setTahun(tahun);
                payment.setDenda(denda);
                payment.setValEntri1TagihanLain(val_entri_1_tagihan_lain);
                payment.setTglTx(tgl_tx);
                payment.setStsBayar(sts_bayar);
                payment.setKdCab(kd_cab);
                payment.setKdUser(kd_user);
                payment.setStsReversal(sts_reversal);
                payment.setBulan(bulan);
                payment.setPokok(pokok);

                listPayment.add(payment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPayment;
    }

    public Vector getListPaymentDetailBPHTB(LaporanPayment laporanPayment) {
        String resp_code = new String();
        String resp_message = new String();
        Vector listPayment = new Vector();
        try {

            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(laporanPayment), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = " + raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon, "<code>", "</code>");
            resp_message = StringUtils.substringBetween(raw_respon, "<Table>", "</Table>");

            //StringUtils.
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = " + resp_code);
            System.out.println("Respone Message = " + resp_message);
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
            int count = 0;
            for (int i = 0; i < list.getLength(); i++) {

                Payment payment = new Payment();

                count = count + 1;
                tableNode = ((Element) list.item(i));

                String childId = "id";
                Node nodeId = (Node) tableNode.getElementsByTagName(childId).item(0);
                String id = nodeId.getTextContent();

                String childInstansi = "instansi";
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
                String tagihan = nodeTagihan.getTextContent();

                String childKetTagihanLain = "ket_tagihan_lain";
                Node nodeKetTagihanLain = (Node) tableNode.getElementsByTagName(childKetTagihanLain).item(0);
                String ketTagihanLain = nodeKetTagihanLain.getTextContent();

                String childTagihanLain = "tagihan_lain";
                Node nodeTagihanLain = (Node) tableNode.getElementsByTagName(childTagihanLain).item(0);
                String tagihanLain = nodeTagihanLain.getTextContent();

                String childBiaya = "biaya_adm";
                Node nodeBiaya = (Node) tableNode.getElementsByTagName(childBiaya).item(0);
                String biaya = nodeBiaya.getTextContent();

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

                String childval_entri_1_tagihan_lain = "val_entri_1_tagihan_lain";
                Node nodechildval_entri_1_tagihan_lain = (Node) tableNode.getElementsByTagName(childval_entri_1_tagihan_lain).item(0);
                String val_entri_1_tagihan_lain = nodechildval_entri_1_tagihan_lain.getTextContent();

                String childTgl = "tgl_tx";
                Node nodeTgl = (Node) tableNode.getElementsByTagName(childTgl).item(0);
                String tgl_tx = nodeTgl.getTextContent();

                String childsts_bayar = "sts_bayar";
                Node nodests_bayar = (Node) tableNode.getElementsByTagName(childsts_bayar).item(0);
                String sts_bayar = nodests_bayar.getTextContent();

                String childkd_cab = "kd_cab";
                Node nodekd_cab = (Node) tableNode.getElementsByTagName(childkd_cab).item(0);
                String kd_cab = nodekd_cab.getTextContent();

                String childkd_user = "kd_user";
                Node nodekd_user = (Node) tableNode.getElementsByTagName(childkd_user).item(0);
                String kd_user = nodekd_user.getTextContent();

                String childStatus = "sts_reversal";
                Node nodeStatus = (Node) tableNode.getElementsByTagName(childStatus).item(0);
                String sts_reversal = nodeStatus.getTextContent();

                payment.setId(id);
                payment.setInstansi(instansi);
                payment.setNoId(no_id);
                payment.setNama(nama);
                payment.setTagihan(tagihan);
                payment.setKetTagihan(ketTagihanLain);
                payment.setTagihanLain(tagihanLain);
                payment.setBiayaAdm(biaya);
                payment.setDenda(denda);
                payment.setValEntri1TagihanLain(val_entri_1_tagihan_lain);
                payment.setTglTx(tgl_tx);
                payment.setStsBayar(sts_bayar);
                payment.setKdCab(kd_cab);
                payment.setKdUser(kd_user);
                payment.setStsReversal(sts_reversal);
                payment.setPokok(pokok);
                payment.setNOP(NOP);
                payment.setLetakObjectPajak(LetakObjekPajak);

                listPayment.add(payment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPayment;
    }

    public Vector getListPaymentDetailRetribusi(LaporanPayment laporanPayment) {
        String resp_code = new String();
        String resp_message = new String();
        Vector listPayment = new Vector();
        try {

            // TODO code application logic here
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = AppSetting.IP_BANK_BPD;//"http://192.168.201.78:88/index.asmx";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(laporanPayment), url);
            soapResponse.writeTo(System.out);

            soapResponse.writeTo(out);
            String raw_respon = new String(out.toByteArray());
            System.out.println("SOAP Respon = " + raw_respon);
            resp_code = StringUtils.substringBetween(raw_respon, "<code>", "</code>");
            resp_message = StringUtils.substringBetween(raw_respon, "<Table>", "</Table>");

            //StringUtils.
            System.out.println("=============================================");
            System.out.println("GET STATUS");
            System.out.println("Respone Code = " + resp_code);
            System.out.println("Respone Message = " + resp_message);
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
            int count = 0;
            for (int i = 0; i < list.getLength(); i++) {

                Payment payment = new Payment();

                count = count + 1;
                tableNode = ((Element) list.item(i));

                String childId = "id";
                Node nodeId = (Node) tableNode.getElementsByTagName(childId).item(0);
                String id = nodeId.getTextContent();

                String childInstansi = "instansi";
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
                String tagihan = nodeTagihan.getTextContent();

                String childKetTagihanLain = "ket_tagihan_lain";
                Node nodeKetTagihanLain = (Node) tableNode.getElementsByTagName(childKetTagihanLain).item(0);
                String ketTagihanLain = nodeKetTagihanLain.getTextContent();

                String childTagihanLain = "tagihan_lain";
                Node nodeTagihanLain = (Node) tableNode.getElementsByTagName(childTagihanLain).item(0);
                String tagihanLain = nodeTagihanLain.getTextContent();

                String childBiaya = "biaya_adm";
                Node nodeBiaya = (Node) tableNode.getElementsByTagName(childBiaya).item(0);
                String biaya = nodeBiaya.getTextContent();

                String childTanggalTagihan = "TanggalTagihan";
                Node nodeTanggalTagihan = (Node) tableNode.getElementsByTagName(childTanggalTagihan).item(0);
                String TanggalTagihan = nodeTanggalTagihan.getTextContent();

                String childval_entri_1_tagihan_lain = "val_entri_1_tagihan_lain";
                Node nodechildval_entri_1_tagihan_lain = (Node) tableNode.getElementsByTagName(childval_entri_1_tagihan_lain).item(0);
                String val_entri_1_tagihan_lain = nodechildval_entri_1_tagihan_lain.getTextContent();

                String childTgl = "tgl_tx";
                Node nodeTgl = (Node) tableNode.getElementsByTagName(childTgl).item(0);
                String tgl_tx = nodeTgl.getTextContent();

                String childsts_bayar = "sts_bayar";
                Node nodests_bayar = (Node) tableNode.getElementsByTagName(childsts_bayar).item(0);
                String sts_bayar = nodests_bayar.getTextContent();

                String childkd_cab = "kd_cab";
                Node nodekd_cab = (Node) tableNode.getElementsByTagName(childkd_cab).item(0);
                String kd_cab = nodekd_cab.getTextContent();

                String childkd_user = "kd_user";
                Node nodekd_user = (Node) tableNode.getElementsByTagName(childkd_user).item(0);
                String kd_user = nodekd_user.getTextContent();

                String childStatus = "sts_reversal";
                Node nodeStatus = (Node) tableNode.getElementsByTagName(childStatus).item(0);
                String sts_reversal = nodeStatus.getTextContent();

                payment.setId(id);
                payment.setInstansi(instansi);
                payment.setNoId(no_id);
                payment.setNama(nama);
                payment.setTagihan(tagihan);
                payment.setKetTagihan(ketTagihanLain);
                payment.setTagihanLain(tagihanLain);
                payment.setBiayaAdm(biaya);
                payment.setValEntri1TagihanLain(val_entri_1_tagihan_lain);
                payment.setTglTx(tgl_tx);
                payment.setStsBayar(sts_bayar);
                payment.setKdCab(kd_cab);
                payment.setKdUser(kd_user);
                payment.setStsReversal(sts_reversal);
                payment.setTanggalTagihan(TanggalTagihan);

                listPayment.add(payment);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPayment;
    }

    public static SOAPMessage createSOAPRequest(LaporanPayment laporanPayment) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("laporan_payment_detail", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sUser", "example");
        soapBodyElem1.addTextNode("" + laporanPayment.getsUser());
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("sPassword", "example");
        soapBodyElem2.addTextNode("" + laporanPayment.getsPassword());
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("sInstansi", "example");
        soapBodyElem3.addTextNode("" + laporanPayment.getsInstansi());
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("sDate", "example");
        soapBodyElem4.addTextNode("" + laporanPayment.getsDate());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "laporan_payment_detail");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("n/Request SOAP Message: n/");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
}
