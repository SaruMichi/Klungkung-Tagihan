/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.dimata.wpupload.ajax;

import com.dimata.dtaxintegration.entity.inquery.InqueryProses;
import com.dimata.dtaxintegration.entity.inquery.Payment;
import com.dimata.dtaxintegration.entity.inquery.Simpatda;
import com.dimata.dtaxintegration.entity.laporan.LaporanPayment;
import com.dimata.dtaxintegration.entity.payment.PaymentPhr;
import com.dimata.dtaxintegration.entity.tagihan.Tagihan;
import com.dimata.dtaxintegration.session.DTaxIntegrationMonitor;
import com.dimata.dtaxintegration.session.SessSimpatda;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import com.dimata.webclient.AppSetting;
import com.dimata.webclient.EchoLaporanPaymentDetail;
import com.dimata.webclient.Inquery;
import com.dimata.wpupload.entity.logspaymentwp.LogPaymentWp;
import com.dimata.wpupload.entity.logspaymentwp.PstLogPaymentWp;
import com.dimata.wpupload.entity.paymentchanel.PaymentChanel;
import com.dimata.wpupload.entity.paymentchanel.PstPaymentChanel;
import com.dimata.wpupload.entity.wpnamabadan.NamaBadan;
import com.dimata.wpupload.entity.wpnamabadan.PstNamaBadan;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Asus
 */
public class AjaxPaymentChanel extends HttpServlet {
    public static final int PAY_CHANEL = 1;
    public static final int CHOOSE_PAY_CHANEL = 2;
    public static final int CHECK_STATUS_PAY = 3;
    public static final int RESET_STATUS_PAY = 4;
    public static final int GENERATE_QRIS = 5;
    public static final int CHEK_PAY_ON_BANK = 6;
    
    
    public static final int STATUS_BAYAR = 1;
    public static final int STATUS_BELUM_BAYAR = 2;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        
	int iCommand = FRMQueryString.requestCommand(request);
        int typeSspForm = FRMQueryString.requestInt(request, "ssptype");
        String ip = request.getRemoteAddr();
        
        if(iCommand == Command.SSP_FORM){
            if(typeSspForm == PAY_CHANEL){
                try {
                    String userid = FRMQueryString.requestString(request, "userid");
                    
                    String wrClause = PstNamaBadan.fieldNames[PstNamaBadan.FLD_NPWPD]+"='"+userid+"'";
                    NamaBadan aboutWp = new NamaBadan();
                    Vector dataWp = PstNamaBadan.list(0, 0, wrClause, "");
                    if (dataWp.size()>0) {
                        aboutWp = (NamaBadan) dataWp.get(0);
                    }
                    Vector dataTagihan = getFirstTagihan(aboutWp);
                    
                    String data = "";
                    JSONObject Omassage = new JSONObject();
                    String order = ""+PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_IDX]+" ASC";
                    Vector dataPay = PstPaymentChanel.list(0, 0, "", order);
                    if(dataTagihan.size()>0){
                    data += "<div class='row'>\n" +
                                "<input type='hidden' value='0' name='"+PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_ID]+"' id='"+PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_ID]+"'>"+
                                "<div class='col'><center><b><h3>Silahkan Pilih Metode Pembayaran Yang Diinginkan.</h3></b></center></div>\n" +
                                "</div>\n" +
                                "<div id='payChanel'>\n" +
                                    "<div class='row datapayment' id='datapayment' style='text-align: center'>\n";
                   
                    

                    if(dataPay.size()>0){
                        for (int i = 0; i < dataPay.size(); i++) {
                            PaymentChanel payment = (PaymentChanel) dataPay.get(i);
                            data += "<div>\n" +
                                        "<button class='btn-pay-chanel removeselect' value='"+payment.getCode()+"'  id='datadom"+i+"' data-css='datadom"+i+"'>"+payment.getPaymentName()+"</button>\n" +
                                    "</div>";
                        }
                    }
                    
                    data +=         "</div>\n" +
                                    "</div>\n" +
                                    "<div id='footerpay'>\n" +
                                    "<div class='row' style='text-align: center'>\n" +
                                    "<div>\n" +
                                    "<button class='btn-succes' id='continuePay'>Lanjutkan</button>\n" +
                                    "</div>\n" +
                                "</div>\n" +
                            "</div>";
                    }else{
                        data += "<div class='row'>\n";
                        data += "<center><img src=\"../images/tidakadatgh.png\" style='width: 50%;'></img></center>\n";
                        data += "<div class='col'><center><b><h2>Anda Tidak Memiliki Tagihan</h2></b></center></div><br>\n";
                        data += "</div>";
                    }

                    Omassage.put("status", Command.RES_OK);
                    Omassage.put("data", data);
                    response.getWriter().println(Omassage);
                } catch (Exception e) {
                }
            }else if(typeSspForm == CHOOSE_PAY_CHANEL){
                String data = ""; 
                JSONObject Omassage = new JSONObject();
                int payType = FRMQueryString.requestInt(request, "payType");
                String userid = FRMQueryString.requestString(request, "userid");
                try {
                    String wrClause = PstNamaBadan.fieldNames[PstNamaBadan.FLD_NPWPD]+"='"+userid+"'";
                    NamaBadan aboutWp = new NamaBadan();
                    Vector dataWp = PstNamaBadan.list(0, 0, wrClause, "");
                    if (dataWp.size()>0) {
                        aboutWp = (NamaBadan) dataWp.get(0);
                    }
                    
                    Simpatda simpatda = new Simpatda();
                    Inquery inquery = new Inquery();
                    
                    simpatda.setJumlahPajakSimpatda("0");
                    Vector dataTagihan = getFirstTagihan(aboutWp);
                    if(dataTagihan.size()>0){
                        simpatda = (Simpatda) dataTagihan.get(0);
                        
                        /*webservice bpd*/
                        boolean checkStatus = true;
                        Element tableNode = null;
                        
                        Vector chekTagihanDiBank = new Vector();
                        InqueryProses inqueryProses = new InqueryProses();
                        inqueryProses.setsUser(AppSetting.USERNAME_PHR);
                        inqueryProses.setsPassword(AppSetting.PWD_PHR);
                        inqueryProses.setsInstansi(AppSetting.INSTANSI_PHR);
                        inqueryProses.setsNoId(simpatda.getId());
                        chekTagihanDiBank = inquery.InqueryPHR(inqueryProses);
                        if(inquery.getStatus().equals(Inquery.RES_OK)){
                            if(chekTagihanDiBank.size()>0){
                                for (int i = 0; i < chekTagihanDiBank.size(); i++) {
                                    Tagihan tagihan = (Tagihan) chekTagihanDiBank.get(i);
                                    if(tagihan.getBulan().equals(simpatda.getBulanSimpatda())){
                                        checkStatus = false;
                                        break;
                                    }
                                }
                            }

                            if(checkStatus){
                                String dataPost = simpatda.getId()+";"+simpatda.getTahunSimpatda()+";"+simpatda.getBulanSimpatda()+";"+simpatda.getJumlahPajakSimpatda()+";"+simpatda.getKeterangan();
                                DTaxIntegrationMonitor dTax = new DTaxIntegrationMonitor();
                                dTax.prosesSimpatda(dataPost,1);
                            }
                            //set log  
                            setStatusPay(aboutWp, simpatda, payType, Long.valueOf(simpatda.getId()));
                        }
                    }
                    if(inquery.getStatus().equals(Inquery.RES_OK)){
                        String jmlhTgk = "-";
                        if(dataTagihan.size() > 1){
                            jmlhTgk = ""+(dataTagihan.size() - 1)+" Tagihan";
                        }
                        if(payType == PstPaymentChanel.VA){
                            data +="<div class='row'>\n" +
                                        "<div class='span4'><button type='button' id='backPay'><i class='icon-reply' style='font-size:25px;margin-left:5%'></i></button></div> \n" +
                                    "</div><div style='width: auto;border-bottom: solid 1px #ededed;margin: 5px 0px 10px 0px;'></div>";
                            data += "<center>\n" +
                                        "<div class='row'>\n" +
                                            "<div class='col'><b><h2>Pembayaran Tagihan Virtual Account</h2></b></div>\n" +
                                            "</div>\n" +
                                            "<div class='row' id='vadivpayment' style=\"margin-top: 20px;background-color: #dbdb51;padding: 10px;border-radius: 10px;\">\n" +
                                                "<div style='font-size: 14px;margin-top=10px'>"+
                                                    "<table>"+
                                                        "<tr>"+
                                                            "<td colspan='3'><center>FORMAT VIRTUAL ACCOUNT PAJAK PHR KAB. KLUNGKUNG :</center></td>"+
                                                        "</tr>"+
                                                        "<tr>"+
                                                            "<td colspan='3'><b><center>129 [Kode Bank BPD Bali] + 5002 [Kode Prefik PAJAK PHR KAB.KLUNGKUNG] + Nomor ID/TAGIHAN<b></center></td>"+
                                                        "</tr>"+
                                                    "</table>" +
                                                "</div>"+
                                            "</div>\n" +

                                            "<div class='row' id='vadivpayment' style=\"margin-top: 20px;background-color: #e8e8e8;padding: 10px;border-radius: 10px;\">\n" +
                                                "<div style='font-size: 16px;margin-top=10px'>"+
                                                    "<table>"+
                                                        "<tr>"+
                                                            "<td colspan='2'><img src='../images/bpd_icon.png' style='width: 40px;'>&nbsp;<font style='font-size: 16px;'><b>Nomor Tagihan :   </b></font></td>"+
                                                            "<td><b>"+simpatda.getId()+"</b></td>"+
                                                        "</tr>"+
                                                        "<tr>"+
                                                            "<td colspan='3'><br></td>"+
                                                        "</tr>"+
                                                        "<tr>"+
                                                            "<td>Nama</td>"+
                                                            "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>"+
                                                            "<td><b>"+aboutWp.getNbNamaBadan()+"</b></td>"+
                                                        "</tr>"+
                                                        "<tr>"+
                                                            "<td>Alamat</td>"+
                                                            "<td></td>"+
                                                            "<td><b>"+aboutWp.getNbAlamatUsaha()+"</b></td>"+
                                                        "</tr>"+
                                                        "<tr>"+
                                                            "<td>Masa Pajak</td>"+
                                                            "<td></td>"+
                                                            "<td><b>"+simpatda.getBulanSimpatda()+"</b></td>"+
                                                        "</tr>"+
                                                        "<tr>"+
                                                            "<td>Tahun Pajak</td>"+
                                                            "<td></td>"+
                                                            "<td><b>"+simpatda.getTahunSimpatda()+"</b></td>"+
                                                        "</tr>"+
                                                    "</table>" +
                                                    "<br><div><h2>Total <font style='color: #018f01;'>"+Formater.formatIndo(Double.valueOf(simpatda.getJumlahPajakSimpatda()))+"</font></h2></div>"+
                                                    "<br><div><h4>Sisa Tunggakan : <font style='color: Red'>"+jmlhTgk+"</font></h4></div>"+
                                                "</div>"+
                                            "</div>\n" +
                                            
                                            "<div class='row' id='vadivpayment'>\n" +
                                                "<div style='font-size: 14px;margin-top=10px'>"+
                                                    "<table style='width: 100%;'>"+
                                                        "<tr>"+
                                                            "<td>"+
                                                                "<center><button id='btnCopyCodeVa' class='btnCopyCodeVa' data-code="+simpatda.getId()+">Copy Code VA</button></center>"+
                                                            "</td>"+
                                                        "</tr>"+
                                                    "</table>" +
                                                "</div>"+
                                            "</div>\n" +
                                    
                                            "<div class='row checkpay'>\n" +
                                                "<div style='font-size: 14px;margin-top=40px'>"+ 
                                                    "<table>"+
                                                        "<tr>"+
                                                            "<td>"+
                                                                "Sedang Check Pembayaran"+
                                                            "</td>"+
                                                            "<td>"+
                                                                "<img class='imgloading' src='../images/loadingpy.png'>"+
                                                            "</td>"+
                                                        "</tr>"+
                                                    "</table>" +
                                                "</div>"+
                                            "</div>\n" +
                                    
                                        "</div>\n" +
                                    "</center>\n" +
                                    "<div class='widget prosesPaymentTutor' style='margin-top: 20px;margin-bottom: 0px;'>\n" +
                                        "<div class='widget-header widHeadTutor'>\n" +
                                            "<button type='button' class='btn btn-sm btn-default showhide' style='margin-left:5px;'><i class='icon icon-caret-down' style='margin-left:0px;'></i></button>\n" +
                                            "<!--i class='icon icon-info-sign'></i--> \n" +
                                            "<h7><b>Petunjuk Pembayaran Dari Mobile / Internet Banking BANK BPD BALI</b></h7>\n" +
                                        "</div>\n" +
                                        "<div class='widget-content showhidecontent'>\n" +
                                            "<div class='form-horizontal'>\n" +
                                                "<div class='control-group'>\n" +
                                                    "<ul>\n" +
                                                    "    <li>1. Masukkan username dan password / PIN pada aplikasi Mobile / Internet Banking.</li>\n" +
                                                    "    <li>2. Pilih menu 'Pembayaran Pajak Daerah Bali'</li>\n" +
                                                    "    <li>3. Pilih 'PHR KLUNGKUNG'</li>\n" +
                                                    "    <li>4. Pilih Input Nomor ID lalu Pilih 'PAJAK PHR KAB. KLUNGKUNG'</li>\n" +
                                                    "    <li>5. Input Nomor ID/tagihan <b>"+simpatda.getId()+"</b></li>\n" + 
                                                    "    <li>6. Input PIN untuk melanjutkan transaksi.</li>\n" +
                                                    "    <li>7. Selesai.</li>\n" +
                                                    "    <li><center><button id='btnCopyCodeVa' class='btnCopyCodeVa' data-code="+simpatda.getId()+">Copy Code VA</button></center></li>"+
                                                    "</ul>\n" +
                                                    
                                                "</div> <!-- /control-group-NPWPD -->\n" +
                                            "</div>\n" +
                                        "</div> <!-- /widget-content -->\n" +
                                    "</div> \n" +

                                    "<div class='widget prosesPaymentTutor' style='margin-bottom: 0px;'>\n" +
                                        "<div class='widget-header widHeadTutor'>\n" +
                                            "<button type='button' class='btn btn-sm btn-default showhide' style='margin-left:5px;'><i class='icon icon-caret-down' style='margin-left:0px;'></i></button>\n" +
                                            "<!--i class='icon icon-info-sign'></i--> \n" +
                                            "<h3 class='removeleft'>Petunjuk Dari Atm Bersama</h3>\n" +
                                        "</div>\n" +
                                        "<div class='widget-content showhidecontent'>\n" +
                                            "<div class='form-horizontal'>\n" +
                                                "<div class='control-group'>\n" +
                                                    "<ul>\n" +
                                                    "    <li>1. Pilih Bahasa</li>\n" +
                                                    "    <li>2. Masukkan PIN</li>\n" +
                                                    "    <li>3. Pilih 'Transaksi Lainnya'</li>\n" +
                                                    "    <li>4. Pilih 'Transfer'</li>\n" +
                                                    "    <li>5. Pilih 'Ke Rekening Bank Lain'</li>\n" +
                                                    "    <li>6. Masukkan nomor rekening tujuan <b>1295002"+simpatda.getId()+"</b> (No Virtual Account),\n" +
                                                    "    lalu tekan 'Benar/Lanjut'</li>\n" +
                                                    "    <li>7. Input nominal yang ingin ditransfer sesuai tagihan yang ingin dibayar, lalu\n" +
                                                    "    tekan 'Benar/Lanjut'.</li>\n" +
                                                    "    <li>8. Silakan isi atau kosongkan nomor referensi transfer kemudian tekan 'Benar'</li>\n" +
                                                    "    <li>9. Muncul Layar Konfirmasi Transfer yang berisi nomor rekening tujuan bank\n" +
                                                    "    beserta jumlah yang dibayar,</li>\n" +
                                                    "    <li>10.Jika sudah benar, Tekan 'Benar'.</li>\n" +
                                                    "    <li>11.Selesai</li>\n" +
                                                    "    <li><br></li>\n" +
                                                    "    <li>Note : tata cara tiap bank mungkin ada sedikit berbeda namun secara gambaran umum sama seperti penjelasan di atas.</\n" +
                                                    "</ul>\n" +
                                                    "<center><button id='btnCopyCodeVa' class='btnCopyCodeVa' data-code=1295002"+simpatda.getId()+">Copy Code VA</button></center>"+
                                                "</div> <!-- /control-group-NPWPD -->\n" +
                                            "</div>\n" +
                                        "</div> <!-- /widget-content -->\n" +
                                    "</div>\n" +

                                    "<div class='widget prosesPaymentTutor' style='margin-bottom: 0px;'>\n" +
                                        "<div class='widget-header widHeadTutor'>\n" +
                                            "<button type='button' class='btn btn-sm btn-default showhide' style='margin-left:5px;'><i class='icon icon-caret-down' style='margin-left:0px;'></i></button>\n" +
                                            "<!--i class='icon icon-info-sign'></i--> \n" +
                                            "<h7>Petunjuk Pembayaran Dari Kliring  Bank Lain</h7>\n" +
                                        "</div>\n" +
                                        "<div class='widget-content showhidecontent'>\n" +
                                            "<div class='form-horizontal'>\n" +
                                                "<div class='control-group'>\n" +
                                                    "<ul>\n" +
                                                    "     <li>1. Pilih 'Transfer ke Bank Lain'</li>\n" +
                                                    "     <li>2. Pilih 'Bank BPD Bali' sebagai bank tujuan.</li>\n" +
                                                    "     <li>3. Masukkan nomor rekening tujuan <b>"+simpatda.getId()+"</b> (No Virtual Account).</li>\n" +
                                                    "     <li>4. Input nominal yang ingin ditransfer sesuai tagihan yang ingin dibayar. Mohon\n" +
                                                    "    dipastikan nominal yang akan ditransfer sama dengan jumlah tagihan yang\n" +
                                                    "    harus dibayar agar proses bisa berjalan sukses.\n" +
                                                    "     <li>5. Lanjutkan transaksi.</li>\n" +
                                                    "     <li>6. Selesai</li>\n" +
                                                    "    <li><br></li>\n" +
                                                    "    <li>Note : tata cara tiap bank mungkin ada sedikit berbeda namun secara gambaran umum sama seperti penjelasan di atas.</\n" +
                                                    "\n" +
                                                    "</ul>\n" +
                                                    "<center><button id='btnCopyCodeVa' class='btnCopyCodeVa' data-code="+simpatda.getId()+">Copy Code VA</button></center>"+
                                                "</div> <!-- /control-group-NPWPD -->\n" +
                                            "</div>\n" +
                                        "</div> <!-- /widget-content -->\n" +
                                    "</div>\n" +

                                    "<div class='widget prosesPaymentTutor' style='margin-bottom: 0px;'>\n" +
                                        "<div class='widget-header widHeadTutor'>\n" +
                                            "<button type='button' class='btn btn-sm btn-default showhide' style='margin-left:5px;'><i class='icon icon-caret-down' style='margin-left:0px;'></i></button>\n" +
                                            "<!--i class='icon icon-info-sign'></i--> \n" +
                                            "<h7>Petunjuk Pembayaran Dari Mobile / Internet Banking Bank Lain</h7>\n" +
                                        "</div>\n" +
                                        "<div class='widget-content showhidecontent'>\n" +
                                            "<div class='form-horizontal'>\n" +
                                                "<div class='control-group'>\n" +
                                                    "<ul>\n" +
                                                    "     <li>1. Pilih 'Transfer ke Bank Lain'</li>\n" +
                                                    "     <li>2. Pilih 'Bank BPD Bali' sebagai bank tujuan.</li>\n" +
                                                    "     <li>3. Masukkan nomor rekening tujuan <b>5002"+simpatda.getId()+"</b> (No Virtual Account).</li>\n" +
                                                    "     <li>4. Input nominal yang ingin ditransfer sesuai tagihan yang ingin dibayar. Mohon\n" +
                                                    "    dipastikan nominal yang akan ditransfer sama dengan jumlah tagihan yang\n" +
                                                    "    harus dibayar agar proses bisa berjalan sukses.\n" +
                                                    "     <li>5. Lanjutkan transaksi.</li>\n" +
                                                    "     <li>6. Selesai</li>\n" +
                                                    "    <li><br></li>\n" +
                                                    "    <li>Note : tata cara tiap bank mungkin ada sedikit berbeda namun secara gambaran umum sama seperti penjelasan di atas.</\n" +
                                                    "\n" +
                                                    "</ul>\n" +
                                                    "<center><button id='btnCopyCodeVa' class='btnCopyCodeVa' data-code=5002"+simpatda.getId()+">Copy Code VA</button></center>"+
                                                "</div> <!-- /control-group-NPWPD -->\n" +
                                            "</div>\n" +
                                        "</div> <!-- /widget-content -->\n" +
                                    "</div>";
                        }else if(payType == PstPaymentChanel.QRIS){
                            data +="<div class='row'>\n" +
                                        "<div class='span4'><button type='button' id='backPay'><i class='icon-reply' style='font-size:25px;margin-left:5%'></i></button></div> \n" +
                                    "</div><div style='width: auto;border-bottom: solid 1px #ededed;margin: 5px 0px 10px 0px;'></div>";
                            data += "<center id='containerqris'>\n" +
                                    "<div>\n" +
                                        "<div><b><h2>Pembayaran Tagihan QRIS Bank BPD Bali</h2></b></div>\n" +
                                    "</div>\n" +
                                    "<div class='card-body' style='width:305px;height: 420px;  background: url(\"../images/BgQris.png\");background-size: cover;'>\n" +
                                    "<div class='qr-container 'style='margin-top: 30px;'>\n" +
                                    "<div id='headerQris'>\n" +
                                    "<div id='nmQris'>NAMA MERCHANT</div>\n" +
                                    "<div id='nmidQris'>NMID</div>\n" +
                                    "</div>\n" +
                                    "<div id='qrcode' style='display: block; margin-right: auto; width: 100%;margin: 0 auto; margin-bottom: 0px'></div>\n" +
                                    "<div id='footerQris'>\n" +
                                    "<div id='doQris'>Dicetak oleh :<font id='ctkOlehQris'></font></div>\n" +
                                    "</div>\n" +
                                    "</div>\n" +
                                    "</div>"+
                                    "<div style='width: 305px; border: solid 1px #d2d2d2; border-radius: 10px;'>"+
                                        "<br><div><h2>Total : <font style='color: #018f01;'>"+Formater.formatIndo(Double.valueOf(simpatda.getJumlahPajakSimpatda()))+"</font></h2></div>"+
                                        "<div><h4>Bill Number : <font style='color: #018f01;'>"+simpatda.getId()+"</font></h4></div>"+
                                        "<div><h4>Berlaku Hingga : <font style='color: #018f01;' id='expireddate'></font></h4></div><br>"+
                                    "</div>"+
                                    "<br><div><h4>Sisa Tunggakan : <font style='color: Red'>"+jmlhTgk+"</font></h4></div>"+
                                "</center>\n" +
                                    
                                "<center><div class='row checkpay'>\n" +
                                    "<div style='font-size: 14px;margin-top=40px'>"+ 
                                        "<table>"+
                                            "<tr>"+
                                                "<td>"+
                                                    "Sedang Check Pembayaran"+
                                                "</td>"+ 
                                                "<td>"+
                                                    "<img class='imgloading' src='../images/loadingpy.png'>"+
                                                "</td>"+
                                            "</tr>"+
                                        "</table>" +
                                    "</div>"+
                                "</div></center>\n" +
                                    
                                "<div class='widget prosesPaymentTutor' style='margin-top: 20px;margin-bottom: 0px;'>\n" +
                                    "<div class='widget-header widHeadTutor'>\n" +
                                    "<button type='button' class='btn btn-sm btn-default showhide' style='margin-left:5px;'><i class='icon icon-caret-down' style='margin-left:0px;'></i></button>\n" +
                                    "<!--i class='icon icon-info-sign'></i--> \n" +
                                    "<h7><b>Petunjuk Pembayaran QRIS</b></h7>\n" +
                                    "</div>\n" +
                                    "<div class='widget-content showhidecontent' style='display: none;'>\n" +
                                    "<div class='form-horizontal'>\n" +
                                    "<div class='control-group'>\n" +
                                    "<ul>\n" +
                                    "<li>1. Buka aplikasi atau e-wallet mendukung pembayaran QRIS di ponsel Anda.</li>\n" +
                                    "<li>2. Pastikan total tagihan sudah benar.</li>\n" +
                                    "<li>3. Setelah pembayaran berhasil akan diverifikasi secara otomatis.</li>\n" +
                                    "<li>4. Selesai.</li>\n" +
                                    "</ul>\n" +
                                    "</div> <!-- /control-group-NPWPD -->\n" +
                                    "</div>\n" +
                                    "</div> <!-- /widget-content -->\n" +
                                "</div> ";
                        }

                        Omassage.put("status", Command.RES_OK);
                        Omassage.put("data", data);
                        Omassage.put("noid", simpatda.getId());
                        Omassage.put("bulan", simpatda.getBulanSimpatda());
                        Omassage.put("tahun", simpatda.getTahunSimpatda());
                        Omassage.put("tagihan", simpatda.getJumlahPajakSimpatda());
                    }else{
                        data +="<div class='row'>\n" +
                                        "<div class='span4'><button type='button' id='backPay'><i class='icon-reply' style='font-size:25px;margin-left:5%'></i></button></div> \n" +
                                    "</div><div style='width: auto;border-bottom: solid 1px #ededed;margin: 5px 0px 10px 0px;'></div>";
                        data +=    "<center>\n" +
                                        "<center><img src=\"../images/warning.png\" style='width: 30%;'></img></center>\n"+
                                        "<div style='padding: 10px;border-radius: 3px;color: red;font-weight: bold;'>\n" +
                                            "Terjadi Kesalahan Sistem<br>Silahkan Coba Lagi\n" + 
                                        "</div>\n" +
                                    "</center>";
                        Omassage.put("status", Command.RES_ERROR);
                        Omassage.put("data", data);
                    }
                    response.getWriter().println(Omassage);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }else if(iCommand == CHECK_STATUS_PAY){
            JSONObject Omassage = new JSONObject();

            String npwpd = FRMQueryString.requestString(request, "npwpd");
            String bulanPjk = FRMQueryString.requestString(request, "bulanPjk");
            String tahunPjk = FRMQueryString.requestString(request, "tahunPjk");
            int paystatus = 0,tipepay = 0;
            try {
                try {
                    String wrStatusPjk = PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_NPWPD]+" = '"+npwpd+"'";
                    if(!bulanPjk.equals("") && !tahunPjk.equals("")){
                        wrStatusPjk += " AND "+PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_MASA_PAJAK]+" = '"+bulanPjk+"'";
                        wrStatusPjk += " AND "+PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_TAHUN_PAJAK]+" = '"+tahunPjk+"'";
                    }
                    Vector statusPay = PstLogPaymentWp.list(0, 0, wrStatusPjk, "");
                    if(statusPay.size()>0){
                        LogPaymentWp logPaymentWp = (LogPaymentWp) statusPay.get(0);
                        paystatus = logPaymentWp.getStatus();
                        tipepay = logPaymentWp.getTypePaymnet();
                    }

                    Omassage.put("status", Command.RES_OK);
                    Omassage.put("msg", "Success");
                } catch (Exception e) {
                    Omassage.put("status", Command.RES_ERROR);
                    Omassage.put("msg", "Sistem Internal Error");
                }
                Omassage.put("paystatus", paystatus);
                Omassage.put("tipepay", tipepay);
                response.getWriter().println(Omassage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else if (iCommand == RESET_STATUS_PAY) {
            JSONObject Omassage = new JSONObject();
            String npwpd = FRMQueryString.requestString(request, "npwpd");
            try {
                Boolean status = PstLogPaymentWp.resetStatus(npwpd);
                if(status){
                    Omassage.put("status", Command.RES_OK);
                    Omassage.put("msg", "Success");
                }else{
                    Omassage.put("status", Command.RES_ERROR);
                    Omassage.put("msg", "Internal Error");
                }
                response.getWriter().println(Omassage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else if (iCommand == GENERATE_QRIS) {
            JSONObject Omassage = new JSONObject();
            PstLogPaymentWp logPay = new PstLogPaymentWp();
            PstPaymentChanel chkQris = new PstPaymentChanel();
            String npwpd = FRMQueryString.requestString(request, "npwpd");
            System.out.println("generate qris");
            try {
                NamaBadan aboutWp = new NamaBadan();
                aboutWp.setNbNPWPD(npwpd);
                Vector dataTagihan = getFirstTagihan(aboutWp);
                if(dataTagihan.size()>0){
                    Simpatda simpatda = (Simpatda) dataTagihan.get(0);
                    
                    boolean checkStatus = true;
                    Vector chekTagihanDiBank = new Vector();
                    Inquery inquery = new Inquery();
                    InqueryProses inqueryProses = new InqueryProses();
                    inqueryProses.setsUser(AppSetting.USERNAME_PHR);
                    inqueryProses.setsPassword(AppSetting.PWD_PHR);
                    inqueryProses.setsInstansi(AppSetting.INSTANSI_PHR);
                    inqueryProses.setsNoId(simpatda.getId());
                    chekTagihanDiBank = inquery.InqueryPHR(inqueryProses);
                    if(inquery.getStatus().equals(Inquery.RES_OK)){
                        if(chekTagihanDiBank.size()>0){
                            for (int i = 0; i < chekTagihanDiBank.size(); i++) {
                                Tagihan tagihan = (Tagihan) chekTagihanDiBank.get(i);
                                System.out.println(tagihan.getStsBayar());
                                if(tagihan.getStsBayar().equals("1")){
                                    checkStatus = false;
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println(checkStatus);
                    if(checkStatus){
                        JSONObject qrisData = PstPaymentChanel.generateQris(Long.valueOf(simpatda.getId()), simpatda, ip);
                        String errorCode = (qrisData.isNull("errorCode") ? "" : qrisData.getString("errorCode"));
                        String fullMessage = (qrisData.isNull("fullMessage") ? "" : qrisData.getString("fullMessage"));
                        
                        if(!qrisData.isNull("no_id")){
                            long status = logPay.updateQrValue(Long.valueOf(simpatda.getId()), qrisData.getString("qrValue"));
                            if(status != 0){
                                Omassage.put("stsbyr", AjaxPaymentChanel.STATUS_BELUM_BAYAR);
                                Omassage.put("status", Command.RES_OK);
                                Omassage.put("msg", "Success");
                                Omassage.put("qrValue", qrisData.getString("qrValue"));
                                Omassage.put("merchantName", qrisData.getString("merchantName"));
                                Omassage.put("nmid", qrisData.getString("nmid"));
                                Omassage.put("ctkoleh", getCtkOleh(AppSetting.MPAN));
                                Omassage.put("expireddate", qrisData.getString("expiredDate"));
                            }else{
                                String errHtml =    "<center>\n" +
                                                        "<center><img src=\"../images/warning.png\" style='width: 30%;'></img></center>\n"+
                                                        "<div style='padding: 10px;border-radius: 3px;color: red;font-weight: bold;'>\n" +
                                                            "Terjadi Kesalahan Sistem<br>Silahkan Coba Lagi\n" +
                                                        "</div>\n" +
                                                    "</center>";
                                Omassage.put("stsbyr", AjaxPaymentChanel.STATUS_BELUM_BAYAR);
                                Omassage.put("status", Command.RES_ERROR);
                                Omassage.put("msg", "Internal Error");
                                Omassage.put("msgview", errHtml); 
                            }
                        }else if(errorCode.equals("IB-8050")){
                            String errHtml =    "<center>\n" +
                                                   "<center><img src=\"../images/warning.png\" style='width: 30%;'></img></center>\n"+
                                                            "<div style='padding: 10px;border-radius: 3px;color: red;font-weight: bold;'>\n" +
                                                        "Pembayaran Dengan Metode QRIS Hanya Bisa Digunakan Dengan Tagihan Dibawah Rp.10.000.000\n" +
                                                    "</div>\n" +
                                                "</center>";
                            Omassage.put("stsbyr", AjaxPaymentChanel.STATUS_BELUM_BAYAR);
                            Omassage.put("status", Command.RES_ERROR);
                            Omassage.put("msg", "Internal Error");
                            Omassage.put("msgview", errHtml);
                        }else if (fullMessage.equals("Tagihan Sudah Terbayar")){
                                String errHtml =    "<center>\n" +
                                                        "<center><img src=\"../images/scctrx.png\" style='width: 30%;'></img></center>\n"+
                                                        "<div style='padding: 10px;border-radius: 3px;color: #00ba8b;font-weight: bold;'>\n" +
                                                            "Tagihan Sebelumnya Sudah Terbayar, Harap Tunggu....\n" +
                                                        "</div>\n" +
                                                    "</center>";
                                Omassage.put("stsbyr", AjaxPaymentChanel.STATUS_BAYAR);
                                Omassage.put("status", Command.RES_OK);
                                Omassage.put("msg", "Internal Error");
                                Omassage.put("msgview", errHtml);   
                        }else{
                            String errHtml =    "<center>\n" +
                                                    "<center><img src=\"../images/warning.png\" style='width: 30%;'></img></center>\n"+
                                                    "<div style='padding: 10px;border-radius: 3px;color: red;font-weight: bold;'>\n" +
                                                        "Terjadi Kesalahan Sistem<br>Silahkan Coba Lagi\n" + 
                                                    "</div>\n" +
                                                "</center>";
                            Omassage.put("stsbyr", AjaxPaymentChanel.STATUS_BELUM_BAYAR);
                            Omassage.put("status", Command.RES_ERROR);
                            Omassage.put("msg", "Internal Error");
                            Omassage.put("msgview", errHtml);
                        }
                    }else{
                        String errHtml =    "<center>\n" +
                                                "<center><img src=\"../images/scctrx.png\" style='width: 30%;'></img></center>\n"+
                                                "<div style='padding: 10px;border-radius: 3px;color: #00ba8b;font-weight: bold;'>\n" +
                                                    "Tagihan Sebelumnya Sudah Terbayar, Harap Tunggu....\n" +
                                                "</div>\n" +
                                            "</center>";
                        Omassage.put("stsbyr", AjaxPaymentChanel.STATUS_BAYAR);
                        Omassage.put("status", Command.RES_OK);
                        Omassage.put("msg", "Internal Error");
                        Omassage.put("msgview", errHtml);
                    }
                }else{
                    String errHtml =    "<center>\n" +
                                            "<center><img src=\"../images/scctrx.png\" style='width: 30%;'></img></center>\n"+
                                            "<div style='padding: 10px;border-radius: 3px;color: #00ba8b;font-weight: bold;'>\n" +
                                                "Harap Tunggu....\n" +
                                            "</div>\n" +
                                        "</center>";
                        
                    Omassage.put("stsbyr", AjaxPaymentChanel.STATUS_BAYAR);
                    Omassage.put("status", Command.SSP_FORM); 
                    Omassage.put("msg", "Internal Error");
                    Omassage.put("msgview", errHtml);
                }
                response.getWriter().println(Omassage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }  
        }else if (iCommand == CHEK_PAY_ON_BANK) {
            String noid = FRMQueryString.requestString(request, "noid");
            String bulan = FRMQueryString.requestString(request, "bulan");
            String tahun = FRMQueryString.requestString(request, "tahun");
            String tagihan = FRMQueryString.requestString(request, "tagihan");
            JSONObject Omassage = new JSONObject();
            PstPaymentChanel chkQris = new PstPaymentChanel();
            PaymentPhr paymentPhr = new PaymentPhr();
            Payment payment = new Payment();
            int status = 0;
            
            try {
                /*webservice bpd*/
                Omassage.put("status", Command.RES_ERROR);
                Omassage.put("msg", "Belum Bayar");
                
                EchoLaporanPaymentDetail echoLaporan = new EchoLaporanPaymentDetail();
                LaporanPayment laporanPayment = new LaporanPayment();
                laporanPayment.setsUser(AppSetting.USERNAME_PHR);
                laporanPayment.setsPassword(AppSetting.PWD_PHR);
                laporanPayment.setsInstansi(AppSetting.INSTANSI_PHR);
                laporanPayment.setsNoId(noid);
                laporanPayment.setsDate(""+Formater.formatDate(new Date(), "yyyy-MM-dd"));
                Vector listReportDetail = echoLaporan.getListPaymentDetailPHR(laporanPayment);
                for (int i = 0; i < listReportDetail.size(); i++) {
                    payment = (Payment) listReportDetail.get(i);
                    if(noid.equals(payment.getNoId()) && 
                            bulan.equals(payment.getBulan()) && 
                            tahun.equals(payment.getTahun()) && 
                            String.valueOf(""+Double.valueOf(""+tagihan)).equals(""+Double.valueOf(""+payment.getTagihan()))){
                        Omassage.put("status", Command.RES_OK);
                        Omassage.put("msg", "Terbayar");
                        
                        //set data bayar
                        paymentPhr.setNoSspd(payment.getId());
                        paymentPhr.setNpwpd(payment.getNoId());
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStringTransaksi = ""+payment.getTglTx();
                        try {
                                Date transaksiDate = formatter.parse(dateStringTransaksi);

                                paymentPhr.setTglRekam(transaksiDate);
                                paymentPhr.setTanggal(transaksiDate);

                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        paymentPhr.setIdPayment(payment.getId());
                        
                        status = 1;
                        
                        break;
                    }
                }
                 
//                JSONObject resQrisPay = chkQris.chekPayQris(Long.valueOf(noid), PstPaymentChanel.UNUSE_LOG, ip);
//                int trxId = 0;
//                try {
//                    trxId = resQrisPay.getInt("trxId");
//                } catch (Exception e) { 
//                }
//                if(trxId != 0){
//                    Omassage.put("status", Command.RES_OK);
//                    Omassage.put("msg", "Terbayar");
//                    
//                    //set data bayar recordId
//                    paymentPhr.setNoSspd(resQrisPay.getString("recordId"));
//                    paymentPhr.setNpwpd(resQrisPay.getString("billNumber"));
//                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                    String dateStringTransaksi = ""+resQrisPay.getString("trxDate");
//                    try {
//                            Date transaksiDate = formatter.parse(dateStringTransaksi);
//
//                            paymentPhr.setTglRekam(transaksiDate);
//                            paymentPhr.setTanggal(transaksiDate);
//
//                    } catch (Exception e) {
//                            e.printStackTrace();
//                    }
//                    paymentPhr.setIdPayment(resQrisPay.getString("recordId"));
//                    payment.setId(resQrisPay.getString("recordId"));
//                    payment.setStsReversal("0");
//                    status = 1;
//                }
                
                if (status == 1) {
                    
                    String wrGetTgh = " WHERE ID ='"+noid+"'";
                    wrGetTgh += " AND MASA_PAJAK ='"+bulan+"'";
                    wrGetTgh += " AND TAHUN_PAJAK ='"+tahun+"'";
                    wrGetTgh += " AND JUMLAH ='"+tagihan+"'";
                    Vector dataTagihan = SessSimpatda.getListSimpatda(wrGetTgh,"IDX ASC");
                    Simpatda simpatda = (Simpatda) dataTagihan.get(0);
                    
                    boolean cekHistory = SessSimpatda.checkPaymentPhr(payment.getId());
                    if(cekHistory){
                        setStatusBayar(paymentPhr, payment, simpatda);
                    }
                }  
                 
                response.getWriter().println(Omassage);
            } catch (Exception e) {
            }
        }
    }
    
    public static boolean setStatusPay(NamaBadan aboutWp,Simpatda simpatda,int payType, long billNumber){
        Boolean status = false;
        try {
            String wrStatusPjk = PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_NPWPD]+" = '"+aboutWp.getNbNPWPD()+"'";
            Vector statusPay = PstLogPaymentWp.list(0, 0, wrStatusPjk, "");

            LogPaymentWp dataSess = new LogPaymentWp();
            dataSess.setNpwpd(aboutWp.getNbNPWPD());
            dataSess.setMasaPajak(simpatda.getBulanSimpatda());
            dataSess.setTahunPajak(Integer.valueOf(simpatda.getTahunSimpatda()));
            dataSess.setTypePaymnet(payType);
            dataSess.setStatus(PstLogPaymentWp.PENDING_PAYMNET);
            dataSess.setBillnumber(billNumber);
            
            if(statusPay.size()>0){
                LogPaymentWp oldSess = (LogPaymentWp) statusPay.get(0);
                dataSess.setOID(oldSess.getOID());
                dataSess.setQrvalue(oldSess.getQrvalue());
                PstLogPaymentWp.updateExc(dataSess);
            }else{
                dataSess.setQrvalue("");
                PstLogPaymentWp.insertExc(dataSess);
            }
            status = true;
        } catch (Exception e) {
        }
        
        return status;
    }
    
    public int setStatusBayar(PaymentPhr paymentPhr,Payment payment, Simpatda simpatda){
        double absoluteTagihan = 0;
        DTaxIntegrationMonitor dTaxIntegrationMonitor = new DTaxIntegrationMonitor();
        
        payment.setNoId(simpatda.getId());
        payment.setTahun(simpatda.getTahunSimpatda());
        payment.setBulan(simpatda.getBulanSimpatda());
        
        paymentPhr.setMasaPajak(""+simpatda.getBulanSimpatda());
        paymentPhr.setTahunPajak(""+simpatda.getTahunSimpatda());
        if(!simpatda.getJumlahPajakSimpatda().equals("")){
            paymentPhr.setJumlahBayar(Double.valueOf(simpatda.getJumlahPajakSimpatda()));
            absoluteTagihan= Math.abs(paymentPhr.getJumlahBayar());
        }else{
            paymentPhr.setJumlahBayar(0);
        }
        paymentPhr.setNama(simpatda.getNamaSimpatda());
        if(!payment.getBiayaAdm().equals("")){
            paymentPhr.setBiayaAdministrasi(Double.valueOf(payment.getBiayaAdm()));
        }else{
            paymentPhr.setBiayaAdministrasi(0);
        }
        if(!simpatda.getDenda().equals("")){
            paymentPhr.setDenda(Double.valueOf(simpatda.getDenda()));
        }else{
            paymentPhr.setDenda(0);
        }
        if(!simpatda.getPokok().equals("")){
            paymentPhr.setPokok(Double.valueOf(simpatda.getPokok()));
        }else{
            paymentPhr.setPokok(0);
        }
        paymentPhr.setStatus(payment.getStsReversal());
        paymentPhr.setIdRekam("090909090");
        
        return dTaxIntegrationMonitor.setSudahBayar(payment, paymentPhr, absoluteTagihan);
    }
    
    public static Vector getFirstTagihan(NamaBadan aboutWp){
        String wrGetTgh = " WHERE NPWPD='"+aboutWp.getNbNPWPD()+"'";
        Vector dataTagihan = SessSimpatda.getListSimpatda(wrGetTgh,"IDX ASC");
        return dataTagihan;
    }
    
    //get npan 8 dijid awal NPAN
    public static String getCtkOleh(String nPan){
        String ctkOleh = "";
        String[] data = nPan.split("");
        for (int i = 1; i < 9; i++) {
            ctkOleh += data[i];
        }
        return ctkOleh;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
