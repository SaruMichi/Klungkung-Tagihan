/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session;

import com.dimata.dtaxintegration.entity.bi.PajakTypeDetail;
import com.dimata.dtaxintegration.entity.bi.PstPajakTypeDetail;
import com.dimata.dtaxintegration.entity.bi.SearchDataPajak;
import com.dimata.dtaxintegration.entity.inquery.Bphtb;
import com.dimata.dtaxintegration.entity.inquery.Payment;
import com.dimata.dtaxintegration.entity.inquery.Pbb;
import com.dimata.dtaxintegration.entity.inquery.PbbIprotax;
import com.dimata.dtaxintegration.entity.inquery.PstPbbIprotax;
import com.dimata.dtaxintegration.entity.inquery.Retribusi;
import com.dimata.dtaxintegration.entity.inquery.Simpatda;
import com.dimata.dtaxintegration.entity.laporan.LaporanPayment;
import com.dimata.dtaxintegration.entity.loghistory.LogHistoryTransaksi;
import com.dimata.dtaxintegration.entity.loghistory.PstLogHistoryTransaksi;
import com.dimata.dtaxintegration.entity.payment.PaymentBphtb;
import com.dimata.dtaxintegration.entity.payment.PaymentPbb;
import com.dimata.dtaxintegration.entity.payment.PaymentPbbIprotax;
import com.dimata.dtaxintegration.entity.payment.PaymentPhr;
import com.dimata.dtaxintegration.entity.payment.PaymentRetribusi;
import com.dimata.dtaxintegration.entity.payment.PstPaymentBphtb;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbb;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbbHistory;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbbIprotax;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPhr;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPhrAll;
import com.dimata.dtaxintegration.entity.payment.PstPaymentRetribusi;
import com.dimata.dtaxintegration.entity.tagihan.TagihanDelete;
import com.dimata.dtaxintegration.entity.tagihan.TagihanInsert;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.util.Formater;
import com.dimata.webclient.AppSetting;
import com.dimata.webclient.EchoLaporanPaymentDetail;
import com.dimata.webclient.EchoLaporanPaymentDetailSetelahNoBukti;
import com.dimata.webclient.EchoTagihanDeleteById;
import com.dimata.webclient.EchoTagihanInsert;
import com.dimata.wpupload.entity.esptpd.PstViewAllSimpatda;
import com.dimata.wpupload.entity.esptpd.ViewAllSimpatda;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class DTaxIntegrationMonitor implements Runnable {

    /**
     * @return the logErorPosting
     */
    public static String getLogErorPosting() {
        return logErorPosting;
    }

    /**
     * @param aLogErorPosting the logErorPosting to set
     */
    public static void setLogErorPosting(String aLogErorPosting) {
        logErorPosting = aLogErorPosting;
    }
    private boolean updateFinish=false;
    private long sleepTimeMinute=(1*3600); //menit per 6 jam
    public static String massage="";
    private static String logErorPosting="";
    
    public DTaxIntegrationMonitor() {

    }

    public void run() {

        System.out.println("start .... ");

    
        while (DTaxIntegrationManager.running) {
            try {
                
               Date newDay=new Date();
               String startDate = Formater.formatDate(newDay,"yyyy-MM-dd");
               inputPaymentPHR(startDate,"");
               //inputPaymentBphtb(startDate,"");
               //inputPaymentRetribusi(startDate,"");
               //inputPaymentPBB(startDate,"");
               
               Thread.sleep((long) (2 * 60000));//milisecond tiap 4 jam = 240 menit
               
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
        }
        
        System.out.println("stop .... ");
    }
   public synchronized void prosesSimpatda(String var) {
       prosesSimpatda(var, 0);
   }
   public synchronized void prosesSimpatda(String var,int typeSend) {
       
            String[] splits = var.split(",");
            int count=0;
            String idTagihan="";
            String tahun="";
            String bulan="";
            String jumlah="";
            String idkey="";
            for(String asset: splits){
                if(asset!=""){
                       String[] splitsDua = asset.split(";");
                       for(String value: splitsDua){
                           count=count+1;
                           if(count==1){
                               idTagihan=value;
                           }
                           if(count==2){
                               tahun=value;
                           }
                           if(count==3){
                               bulan=value;
                           }
                           if(count==4){
                               jumlah=value;
                           }
                           if(count==5){
                               idkey=value;
                               String whereSent= " WHERE ID='"+idTagihan+"' AND MASA_PAJAK='"+bulan+"' AND TAHUN_PAJAK='"+tahun+"' AND JUMLAH='"+jumlah+"' AND ID_KEY='"+idkey+"'";
                               if(typeSend == 0){
                                    sentSimpatda(whereSent);
                               }else{
                                   sentSimpatdaOnly1Tagihan(whereSent);
                               }
                               count=0;
                           }
                       }
                }
            }
    }
    
    public void sentSimpatda(String where) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vSimpatda = new Vector();
                        vSimpatda=SessSimpatda.getListSimpatda(where);
                        /*
                         *  
                         *  id --> sNoId   
                            nama --> sNama   
                            jum_tagihan --> jum_tagihan   
                            instansi --> sInstansi: PHR_GIANYAR   
                            Alamat --> sKet_1   
                            Bulan --> sKet_2
                            Tahun --> sKet_3   
                            Pokok --> sKet_4   
                            Denda --> sKet_5   
                            No SSPD --> sKet_6   
                            Keterangan --> sKet_7

                         */
                        EchoTagihanInsert echo = new EchoTagihanInsert();
                        if(vSimpatda.size()>0){
                            for (int i = 0; i < vSimpatda.size(); i++) {
                                    Simpatda simpatdaSelect = (Simpatda) vSimpatda.get(i);
                                    
                                    //proses hapus data dlu
                                    //disini buatkan proses hapus id, dan proses id yang baru
                                    EchoTagihanDeleteById echoTagihanDeleteById = new EchoTagihanDeleteById();
                                    TagihanDelete tagihanDelete = new TagihanDelete();
                                    if(!simpatdaSelect.getId().equals("")){
                                        tagihanDelete.setsUser(AppSetting.USERNAME_PHR);
                                        tagihanDelete.setsPassword(AppSetting.PWD_PHR);
                                        tagihanDelete.setsInstansi(AppSetting.INSTANSI_PHR);
                                        tagihanDelete.setsNoId(simpatdaSelect.getId());
                                        tagihanDelete.setsRecordId("");
                                        echoTagihanDeleteById.action(tagihanDelete);
                                        try{
                                            int xxx = PstLogHistoryTransaksi.deleteloghistoryperid(simpatdaSelect.getId());
                                        }catch(Exception ex){
                                        }
                                    }
                                    
                                    //lakukan proses insert data keselurahan sesuai dengan id yang ada
                                    //buatkan list data yang akan di upload berdasarkan id
                                    Vector listTagihanPhr = new Vector();
                                    String whereClause = " WHERE ID='"+simpatdaSelect.getId()+"' ";
                                    listTagihanPhr = SessSimpatda.getListSimpatda(whereClause); 
                                    if(listTagihanPhr.size()>0){
                                        for (int k = 0; k < listTagihanPhr.size(); k++) {
                                            Simpatda simpatda = (Simpatda) listTagihanPhr.get(k);
                                            
                                            TagihanInsert tagihanInsert = new TagihanInsert();
                                            tagihanInsert.setsUser(AppSetting.USERNAME_PHR);//1
                                            tagihanInsert.setSPassword(AppSetting.PWD_PHR);//2
                                            tagihanInsert.setSNoId(""+simpatda.getId());//3
                                            tagihanInsert.setSNama(""+simpatda.getNamaSimpatda());//4
                                            
                                            try{
                                                tagihanInsert.setJumTagihan(Double.valueOf(simpatda.getJumlahPajakSimpatda()));//5
                                            }catch(Exception ex){
                                                tagihanInsert.setJumTagihan(0);
                                            }
                                            
                                            tagihanInsert.setSInstansi(AppSetting.INSTANSI_PHR);//6
                                            tagihanInsert.setSKet_1(""+simpatda.getAlamat());//ALAMAT//7
                                            tagihanInsert.setSKet_2(""+simpatda.getBulanSimpatda());//BULAN//8
                                            tagihanInsert.setSKet_3(""+simpatda.getTahunSimpatda());//TAHUN//9
                                            tagihanInsert.setSKet_4(""+simpatda.getPokok());//POKOK//10
                                            tagihanInsert.setSKet_5(""+simpatda.getDenda());//11
                                            tagihanInsert.setSKet_6(""+simpatda.getNoSspdSimpatda());//12

                                            if(simpatda.getVolumeKet()==null){
                                                tagihanInsert.setSKet_7(""+simpatda.getKeterangan());//13
                                            }else{
                                                tagihanInsert.setSKet_7(" Volume "+simpatda.getVolumeKet() +" M3 ");//13
                                            }

                                            tagihanInsert.setSKet_8("");//14
                                            tagihanInsert.setSKet_9("");
                                            tagihanInsert.setSKet_10("");
                                            tagihanInsert.setSKet_11("");
                                            tagihanInsert.setSKet_12("");
                                            tagihanInsert.setSKet_13("");

                                            String resp_code ="";
                                            ///cek apakah di history
                                            boolean cekHistory=false;
                                            //cekHistory = SessSimpatda.check(simpatda.getId(),simpatda.getTahunSimpatda(),simpatda.getJumlahPajakSimpatda(),simpatda.getBulanSimpatda(),simpatda.getInstansi());
                                            //jika history datanya tidak berubah dan tidak ada di history insertkan (false)
                                            if(!cekHistory){
                                                resp_code = echo.action(tagihanInsert);
                                                massage="Insert ID "+simpatda.getNpwpd()+" Proses : "+resp_code;
                                                if(resp_code.equals("00")){
                                                    //insert history
                                                    LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                                    logHistory.setNpwd(simpatda.getId());
                                                    logHistory.setNama(simpatda.getNamaSimpatda());
                                                    if(!simpatda.getJumlahPajakSimpatda().equals("")){
                                                        logHistory.setJumlahPajak(Double.valueOf(simpatda.getJumlahPajakSimpatda()));
                                                    }else{
                                                        logHistory.setJumlahPajak(0);
                                                    }
                                                    logHistory.setTahun(simpatda.getTahunSimpatda());
                                                    logHistory.setBulan(simpatda.getBulanSimpatda());
                                                    logHistory.setInstansi(simpatda.getInstansi());
                                                    if(!simpatda.getDenda().equals("")){
                                                        logHistory.setDenda(Double.valueOf(simpatda.getDenda()));
                                                    }else{
                                                        logHistory.setDenda(0);
                                                    }

                                                    if(!simpatda.getPokok().equals("")){
                                                        logHistory.setPokok(Double.valueOf(simpatda.getPokok()));
                                                    }else{
                                                        logHistory.setPokok(0);
                                                    }
                                                    logHistory.setAlamat(simpatda.getAlamat());

                                                    long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                                                }
                                            }
                                        }
                                    }  
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
            }
    }
    
    //add by 2017-132
    //up hanya 1 tagihan
    public void sentSimpatdaOnly1Tagihan(String where) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vSimpatda = new Vector();
                        where += " order by IDX ASC ";
                        vSimpatda=SessSimpatda.getListSimpatda(where);
                        /*
                         *  
                         *  id --> sNoId   
                            nama --> sNama   
                            jum_tagihan --> jum_tagihan   
                            instansi --> sInstansi: PHR_GIANYAR   
                            Alamat --> sKet_1   
                            Bulan --> sKet_2
                            Tahun --> sKet_3   
                            Pokok --> sKet_4   
                            Denda --> sKet_5   
                            No SSPD --> sKet_6   
                            Keterangan --> sKet_7

                         */
                        EchoTagihanInsert echo = new EchoTagihanInsert();
                        if(vSimpatda.size()>0){
                            for (int i = 0; i < vSimpatda.size(); i++) {
                                    Simpatda simpatdaSelect = (Simpatda) vSimpatda.get(i);
                                    
                                    //proses hapus data dlu
                                    //disini buatkan proses hapus id, dan proses id yang baru
                                    EchoTagihanDeleteById echoTagihanDeleteById = new EchoTagihanDeleteById();
                                    TagihanDelete tagihanDelete = new TagihanDelete();
                                    if(!simpatdaSelect.getId().equals("")){
                                        tagihanDelete.setsUser(AppSetting.USERNAME_PHR);
                                        tagihanDelete.setsPassword(AppSetting.PWD_PHR);
                                        tagihanDelete.setsInstansi(AppSetting.INSTANSI_PHR);
                                        tagihanDelete.setsNoId(simpatdaSelect.getId());
                                        tagihanDelete.setsRecordId("");
                                        echoTagihanDeleteById.action(tagihanDelete);
                                        try{
                                            int xxx = PstLogHistoryTransaksi.deleteloghistoryperid(simpatdaSelect.getId());
                                        }catch(Exception ex){
                                        }
                                    }
                                    
                                    //lakukan proses insert data keselurahan sesuai dengan id yang ada
                                    //buatkan list data yang akan di upload berdasarkan id
                                    Vector listTagihanPhr = new Vector();
                                    String whereClause = " WHERE ID='"+simpatdaSelect.getId()+"' ";
                                    //whereClause += " AND MASA_PAJAK='"+simpatdaSelect.getBulanSimpatda()+"' ";
                                    //whereClause += " AND TAHUN_PAJAK='"+simpatdaSelect.getTahunSimpatda()+"' ";
                                    listTagihanPhr = SessSimpatda.getListSimpatda(whereClause); 
                                    if(listTagihanPhr.size()>0){
                                        for (int k = 0; k < listTagihanPhr.size(); k++) {
                                            Simpatda simpatda = (Simpatda) listTagihanPhr.get(k);
                                            
                                            TagihanInsert tagihanInsert = new TagihanInsert();
                                            tagihanInsert.setsUser(AppSetting.USERNAME_PHR);//1
                                            tagihanInsert.setSPassword(AppSetting.PWD_PHR);//2
                                            tagihanInsert.setSNoId(""+simpatda.getId());//3
                                            tagihanInsert.setSNama(""+simpatda.getNamaSimpatda());//4
                                            
                                            try{
                                                tagihanInsert.setJumTagihan(Double.valueOf(simpatda.getJumlahPajakSimpatda()));//5
                                            }catch(Exception ex){
                                                tagihanInsert.setJumTagihan(0);
                                            }
                                            
                                            tagihanInsert.setSInstansi(AppSetting.INSTANSI_PHR);//6
                                            tagihanInsert.setSKet_1(""+simpatda.getAlamat());//ALAMAT//7
                                            tagihanInsert.setSKet_2(""+simpatda.getBulanSimpatda());//BULAN//8
                                            tagihanInsert.setSKet_3(""+simpatda.getTahunSimpatda());//TAHUN//9
                                            tagihanInsert.setSKet_4(""+simpatda.getPokok());//POKOK//10
                                            tagihanInsert.setSKet_5(""+simpatda.getDenda());//11
                                            tagihanInsert.setSKet_6(""+simpatda.getNoSspdSimpatda());//12

                                            if(simpatda.getVolumeKet()==null){
                                                tagihanInsert.setSKet_7(""+simpatda.getKeterangan());//13
                                            }else{
                                                tagihanInsert.setSKet_7(" Volume "+simpatda.getVolumeKet() +" M3 ");//13
                                            }

                                            tagihanInsert.setSKet_8("");//14
                                            tagihanInsert.setSKet_9("");
                                            tagihanInsert.setSKet_10("");
                                            tagihanInsert.setSKet_11("");
                                            tagihanInsert.setSKet_12("");
                                            tagihanInsert.setSKet_13("");

                                            String resp_code ="";
                                            ///cek apakah di history
                                            boolean cekHistory=false;
                                            //cekHistory = SessSimpatda.check(simpatda.getId(),simpatda.getTahunSimpatda(),simpatda.getJumlahPajakSimpatda(),simpatda.getBulanSimpatda(),simpatda.getInstansi());
                                            //jika history datanya tidak berubah dan tidak ada di history insertkan (false)
                                            if(!cekHistory){
                                                resp_code = echo.action(tagihanInsert);
                                                massage="Insert ID "+simpatda.getNpwpd()+" Proses : "+resp_code;
                                                if(resp_code.equals("00")){
                                                    //insert history
                                                    LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                                    logHistory.setNpwd(simpatda.getId());
                                                    logHistory.setNama(simpatda.getNamaSimpatda());
                                                    if(!simpatda.getJumlahPajakSimpatda().equals("")){
                                                        logHistory.setJumlahPajak(Double.valueOf(simpatda.getJumlahPajakSimpatda()));
                                                    }else{
                                                        logHistory.setJumlahPajak(0);
                                                    }
                                                    logHistory.setTahun(simpatda.getTahunSimpatda());
                                                    logHistory.setBulan(simpatda.getBulanSimpatda());
                                                    logHistory.setInstansi(simpatda.getInstansi());
                                                    if(!simpatda.getDenda().equals("")){
                                                        logHistory.setDenda(Double.valueOf(simpatda.getDenda()));
                                                    }else{
                                                        logHistory.setDenda(0);
                                                    }

                                                    if(!simpatda.getPokok().equals("")){
                                                        logHistory.setPokok(Double.valueOf(simpatda.getPokok()));
                                                    }else{
                                                        logHistory.setPokok(0);
                                                    }
                                                    logHistory.setAlamat(simpatda.getAlamat());

                                                    long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                                                }
                                            }
                                        }
                                    }  
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
            }
    }

    public void prosesBrr(String var) {
            String[] splits = var.split(",");
            int count=0;
            String idTagihan="";
            String tahun="";
            String bulan="";
            for(String asset: splits){
                if(asset!=""){
                       String[] splitsDua = asset.split(";");
                       for(String value: splitsDua){
                           count=count+1;
                           if(count==1){
                               idTagihan=value;
                           }
                           if(count==2){
                               tahun=value;
                               //String whereSent= " WHERE NPWPD='"+idTagihan+"' AND BULAN='"+bulan+"' AND TAHUN='"+tahun+"'";
                               String whereSent=" WHERE NOP='"+idTagihan+"' AND TAHUN='"+tahun+"'";
                               if(AppSetting.USERNAME_PBB.equals("PBB_BANGLI")){
                                   sentPBBIpRotax(whereSent);
                               }else{
                                   sentPBB(whereSent);
                               }
                               
                               count=0;
                           }
                       } 
                }
            }
    }
    
     public void sentPBB(String where) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vPBB = new Vector();
                        vPBB=SessSimpatda.getListPBB(where);
                        /*
                            Nop:517101000100200380	--> sNoId
                            nama:PANDE PUTU NITA AGUSTINI	--> sNama
                            jum_tagihan:370680.00	--> jum_tagihan
                            instansi:PBB_BADUNG	--> sInstansi
                            NPWP:1234	--> sKet_1
                            Alamat WP:JL KARTINI 77A RT 000 RW 00	--> sKet_2
                            Letak Objek Pajak:JL GN SARI GG DADI	--> sKet_3
                            Tahun:2013	--> sKet_4
                            Tgl Jatuh Tempo:28 Agustus 2013	--> sKet_5
                            Luas Bumi:400	--> sKet_6
                            Luas Bangunan:260	--> sKet_7
                            NJOP Bumi:134,000,000	--> sKet_8
                            NJOP Bangunan:251,680,000	--> sKet_9
                            NJOPTKP:15.000.000	--> sKet_10
                            Denda:0	--> sKet_11
                            Formula:(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda--> sKet_12
                            Terbilang:Tiga ratus tujuh puluh ribu enam ratus delapan puluh rupiah	--> sKet_13


                         */
                        EchoTagihanInsert echo = new EchoTagihanInsert();
                        if(vPBB.size()>0){
                            for (int i = 0; i < vPBB.size(); i++) {
                                
                                    Pbb pbb = (Pbb) vPBB.get(i);
                                    TagihanInsert tagihanInsert = new TagihanInsert();
                                    tagihanInsert.setsUser(AppSetting.USERNAME_PBB);//1
                                    tagihanInsert.setSPassword(AppSetting.PWD_PBB);//2
                                    tagihanInsert.setSNoId(""+pbb.getId());//3//sNoId
                                    tagihanInsert.setSNama(""+pbb.getNama());//4//sNama
                                    tagihanInsert.setJumTagihan(Double.valueOf(pbb.getJumlahTagihan()));//5 jum_tagihan
                                    tagihanInsert.setSInstansi(AppSetting.INSTANSI_PBB);//6//sInstansi
                                    tagihanInsert.setSKet_1(""+pbb.getNpwpd());//NPWP//7 //sKet_1
                                    tagihanInsert.setSKet_2(""+pbb.getAlamat());//sKet_2//Alamat
                                    tagihanInsert.setSKet_3(""+pbb.getLetakObjectPajak());//Letak Objek Pajak
                                    tagihanInsert.setSKet_4(""+pbb.getTahun());//tahun//10
                                    tagihanInsert.setSKet_5(""+pbb.getTglJatuhTempo());//11//jatuh tempo
                                    tagihanInsert.setSKet_6(""+pbb.getLuasBumi());//12//luas bangunan
                                    tagihanInsert.setSKet_7(""+pbb.getLuasBangunan());//13
                                    tagihanInsert.setSKet_8(""+pbb.getnJOPBumi());//14
                                    tagihanInsert.setSKet_9(""+pbb.getnJOPBangunan());
                                    tagihanInsert.setSKet_10(""+pbb.getnJOPTKP());
                                    tagihanInsert.setSKet_11(""+pbb.getDenda());
                                    tagihanInsert.setSKet_12(""+pbb.getFormula());
                                    
                                    if(!pbb.getJumlahTagihan().equals("")){
                                           double total = Double.valueOf(pbb.getJumlahTagihan());
                                           long mylong = (long)(total);
                                           ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                                           pbb.setTerbilang(convert.getText());
                                           tagihanInsert.setSKet_13(""+pbb.getTerbilang());
                                    }else{
                                       tagihanInsert.setSKet_13(""+pbb.getTerbilang());
                                    }
                                    
//                                  String resp_code = echo.action(tagihanInsert);
//                                  massage="Insert ID "+pbb.getNpwpd()+" Proses : "+resp_code;
                                    String resp_code ="";
                                    boolean cekHistory=false;
                                    //cekHistory = SessSimpatda.check(pbb.getId(),pbb.getTahun(),pbb.getJumlahTagihan(),"",pbb.getInstansi());
                                    
                                    if(!cekHistory){
                                        resp_code = echo.action(tagihanInsert);
                                        massage="Insert ID "+pbb.getNpwpd()+" Proses : "+resp_code;
                                        if(resp_code.equals("00")){
                                            //insert history
                                            LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                            logHistory.setNpwd(pbb.getId());
                                            logHistory.setNama(pbb.getNama());
                                            if(!pbb.getJumlahTagihan().equals("")){
                                                logHistory.setJumlahPajak(Double.valueOf(pbb.getJumlahTagihan()));
                                            }else{
                                                logHistory.setJumlahPajak(0);
                                            }
                                            logHistory.setTahun(pbb.getTahun());
                                            logHistory.setBulan("");
                                            logHistory.setInstansi(pbb.getInstansi());
                                            if(!pbb.getDenda().equals("")){
                                                logHistory.setDenda(Double.valueOf(pbb.getDenda()));
                                            }else{
                                                logHistory.setDenda(0);
                                            }
                                            
                                            logHistory.setAlamat(pbb.getAlamat());
                                            logHistory.setLetakObjeckPajak(pbb.getLetakObjectPajak());

                                            if(!pbb.getLuasBangunan().equals("")){
                                                logHistory.setLuasBangunan(Double.valueOf(pbb.getLuasBangunan()));
                                            }else{
                                                logHistory.setLuasBangunan(0);
                                            }

                                            if(!pbb.getLuasBumi().equals("")){
                                                logHistory.setLuasBumi(Double.valueOf(pbb.getLuasBumi()));
                                            }else{
                                                logHistory.setLuasBumi(0);
                                            }

                                            if(!pbb.getnJOPBangunan().equals("")){
                                                logHistory.setnJOPBangunan(Double.valueOf(pbb.getnJOPBangunan()));
                                            }else{
                                                logHistory.setnJOPBangunan(0);
                                            }

                                            if(!pbb.getnJOPBumi().equals("")){
                                                logHistory.setnJOPBumi(Double.valueOf(pbb.getnJOPBumi()));
                                            }else{
                                                logHistory.setnJOPBumi(0);
                                            }

                                            if(!pbb.getnJOPTKP().equals("")){
                                                logHistory.setnJOPTKP(Double.valueOf(pbb.getnJOPTKP()));
                                            }else{
                                                logHistory.setnJOPTKP(0);
                                            }
                                            
                                            long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                                        }
                                    }
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
            }
    }
     
        public void sentPBBIpRotax(String where) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vPBB = new Vector();
                        vPBB=SessSimpatda.getListPBBIprotax(where);
                        /*
                            Nop:517101000100200380	--> sNoId
                            nama:PANDE PUTU NITA AGUSTINI	--> sNama
                            jum_tagihan:370680.00	--> jum_tagihan
                            instansi:PBB_BADUNG	--> sInstansi
                            NPWP:1234	--> sKet_1
                            Alamat WP:JL KARTINI 77A RT 000 RW 00	--> sKet_2
                            Letak Objek Pajak:JL GN SARI GG DADI	--> sKet_3
                            Tahun:2013	--> sKet_4
                            Tgl Jatuh Tempo:28 Agustus 2013	--> sKet_5
                            Luas Bumi:400	--> sKet_6
                            Luas Bangunan:260	--> sKet_7
                            NJOP Bumi:134,000,000	--> sKet_8
                            NJOP Bangunan:251,680,000	--> sKet_9
                            NJOPTKP:15.000.000	--> sKet_10
                            Denda:0	--> sKet_11
                            Formula:(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda--> sKet_12
                            Terbilang:Tiga ratus tujuh puluh ribu enam ratus delapan puluh rupiah	--> sKet_13


                         */
                        EchoTagihanInsert echo = new EchoTagihanInsert();
                        if(vPBB.size()>0){
                            for (int i = 0; i < vPBB.size(); i++) {
                                
                                    Pbb pbb = (Pbb) vPBB.get(i);
                                    
                                    /*get previous payment on this SPPT, count faktor pengurang*/
                                    double totPambayaran = SessPbbIprotax.PerhitunganPbbYangHarusDibayar(pbb.getId(), pbb.getTahun(), pbb.getJumlahTagihan());

                                    /*count denda adm sppt*/
                                    double denda=SessPbbIprotax.PerhitunganDenda(pbb.getId(),  pbb.getTahun(), pbb.getTglJatuhTempo(), pbb.getJumlahTagihan(),totPambayaran);
                                    
                                    /*total yang harus dibayarkan*/
                                    double ygHarusDibayar=totPambayaran+denda;
                                    
                                    try{
                                        pbb.setJumlahTagihan(String.valueOf(ygHarusDibayar));
                                    }catch(Exception ex){
                                    }
                                    
                                    
                                    TagihanInsert tagihanInsert = new TagihanInsert();
                                    tagihanInsert.setsUser(AppSetting.USERNAME_PBB);//1
                                    tagihanInsert.setSPassword(AppSetting.PWD_PBB);//2
                                    tagihanInsert.setSNoId(""+pbb.getId());//3//sNoId
                                    tagihanInsert.setSNama(""+pbb.getNama());//4//sNama
                                    tagihanInsert.setJumTagihan(ygHarusDibayar);//5 jum_tagihan
                                    tagihanInsert.setSInstansi(AppSetting.INSTANSI_PBB);//6//sInstansi
                                    tagihanInsert.setSKet_1(""+pbb.getNpwpd());//NPWP//7 //sKet_1
                                    tagihanInsert.setSKet_2(""+pbb.getAlamat());//sKet_2//Alamat
                                    tagihanInsert.setSKet_3(""+pbb.getLetakObjectPajak());//Letak Objek Pajak
                                    tagihanInsert.setSKet_4(""+pbb.getTahun());//tahun//10
                                    tagihanInsert.setSKet_5(""+pbb.getTglJatuhTempo());//11//jatuh tempo
                                    tagihanInsert.setSKet_6(""+pbb.getLuasBumi());//12//luas bangunan
                                    tagihanInsert.setSKet_7(""+pbb.getLuasBangunan());//13
                                    tagihanInsert.setSKet_8(""+pbb.getnJOPBumi());//14
                                    tagihanInsert.setSKet_9(""+pbb.getnJOPBangunan());
                                    tagihanInsert.setSKet_10(""+pbb.getnJOPTKP());
                                    
                                    //proses denda
                                    //step 1 cari total pembayaran yang pernah dilakukan
                                    try{
                                        pbb.setDenda(Formater.formatNumber(denda, "#,###,##0"));
                                    }catch(Exception ex){
                                    }
                                    
                                    tagihanInsert.setSKet_11(""+pbb.getDenda());
                                    
                                    //proses formula
                                    pbb.setFormula("("+pbb.getnJOPBumi()+" + "+pbb.getnJOPBangunan()+" - "+pbb.getnJOPTKP()+") X "+(pbb.getTarifSppt()*(pbb.getNjkpSppt()/100))+" % + "+pbb.getDenda());
                                    tagihanInsert.setSKet_12(""+pbb.getFormula());
                                    
                                    if(!pbb.getJumlahTagihan().equals("")){
                                           double total = Double.valueOf(pbb.getJumlahTagihan());
                                           long mylong = (long)(total);
                                           ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                                           pbb.setTerbilang(convert.getText() +" rupiah ");
                                           tagihanInsert.setSKet_13(""+pbb.getTerbilang());
                                    }else{
                                       tagihanInsert.setSKet_13(""+pbb.getTerbilang());
                                    }
                                    
//                                  String resp_code = echo.action(tagihanInsert);
//                                  massage="Insert ID "+pbb.getNpwpd()+" Proses : "+resp_code;
                                    String resp_code ="";
                                    boolean cekHistory=false;
                                    //cekHistory = SessSimpatda.check(pbb.getId(),pbb.getTahun(),pbb.getJumlahTagihan(),"",pbb.getInstansi());
                                    
                                    if(!cekHistory){
                                        resp_code = echo.action(tagihanInsert);
                                        massage="Insert ID "+pbb.getNpwpd()+" Proses : "+resp_code;
                                        if(resp_code.equals("00")){
                                            //insert history
                                            LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                            logHistory.setNpwd(pbb.getId());
                                            logHistory.setNama(pbb.getNama());
                                            if(!pbb.getJumlahTagihan().equals("")){
                                                logHistory.setJumlahPajak(Double.valueOf(pbb.getJumlahTagihan()));
                                            }else{
                                                logHistory.setJumlahPajak(0);
                                            }
                                            logHistory.setTahun(pbb.getTahun());
                                            logHistory.setBulan("");
                                            logHistory.setInstansi(pbb.getInstansi());
                                            if(!pbb.getDenda().equals("")){
                                                logHistory.setDenda(denda);
                                            }else{
                                                logHistory.setDenda(0);
                                            }
                                            
                                            logHistory.setAlamat(pbb.getAlamat());
                                            logHistory.setLetakObjeckPajak(pbb.getLetakObjectPajak());

                                            if(!pbb.getLuasBangunan().equals("")){
                                                logHistory.setLuasBangunan(pbb.getDluasBangunan());
                                            }else{
                                                logHistory.setLuasBangunan(0);
                                            }

                                            if(!pbb.getLuasBumi().equals("")){
                                                logHistory.setLuasBumi(pbb.getDluasBumi());
                                            }else{
                                                logHistory.setLuasBumi(0);
                                            }

                                            if(!pbb.getnJOPBangunan().equals("")){
                                                logHistory.setnJOPBangunan(pbb.getDnJOPBangunan());
                                            }else{
                                                logHistory.setnJOPBangunan(0);
                                            }

                                            if(!pbb.getnJOPBumi().equals("")){
                                                logHistory.setnJOPBumi(pbb.getDnJOPBumi());
                                            }else{
                                                logHistory.setnJOPBumi(0);
                                            }

                                            if(!pbb.getnJOPTKP().equals("")){
                                                logHistory.setnJOPTKP(pbb.getDnJOPTKP());
                                            }else{
                                                logHistory.setnJOPTKP(0);
                                            }
                                            
                                            long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                                        }
                                    }
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
            }
    }
    
    public void prosesBphtb(String var) {
            String[] splits = var.split(",");
            int count=0;
            String idTagihan="";
            String tahun="";
            String bulan="";
            for(String asset: splits){
                if(asset!=""){
                       String[] splitsDua = asset.split(";");
                       for(String value: splitsDua){
                           count=count+1;
                           if(count==1){
                               idTagihan=value;
                           }
                           if(count==2){
                               tahun=value;
                               //String whereSent= " WHERE NPWPD='"+idTagihan+"' AND BULAN='"+bulan+"' AND TAHUN='"+tahun+"'";
                               //String whereSent=" WHERE NOP='"+idTagihan+"' AND TAHUN='"+tahun+"'";
                               String whereSent= " WHERE ID='"+idTagihan+"' AND NOP='"+tahun+"'";
                               sentBphtb(whereSent);
                               count=0;
                           }
                       } 
                }
            }
    }
    
    public void sentBphtb(String where) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vSimpatda = new Vector();
                        vSimpatda=SessSimpatda.getListBphtb(where);
                        /*
                         id --> sNoId   
                         nama --> sNama   
                         jum_tagihan --> jum_tagihan   
                         instansi --> sInstansi: BPHTB_GIANYAR   
                         NOP --> sKet_1
                         Letak Objek Pajak --> sKet2
                         */
                        EchoTagihanInsert echo = new EchoTagihanInsert();
                        if(vSimpatda.size()>0){
                            for (int i = 0; i < vSimpatda.size(); i++) {
                                    Bphtb bphtb = (Bphtb) vSimpatda.get(i);
                                    
                                    TagihanInsert tagihanInsert = new TagihanInsert();
                                    tagihanInsert.setsUser(AppSetting.USERNAME_BPHTB);//1
                                    tagihanInsert.setSPassword(AppSetting.PWD_BPHTB);//2
                                    tagihanInsert.setSNoId(""+bphtb.getId());//3
                                    tagihanInsert.setSNama(""+bphtb.getNama());//4
                                    tagihanInsert.setJumTagihan(Double.valueOf(bphtb.getJumlahTagihan()));//5
                                    tagihanInsert.setSInstansi(AppSetting.INSTANSI_BPHTB);//6
                                    tagihanInsert.setSKet_1(""+bphtb.getNop());//ALAMAT//7
                                    tagihanInsert.setSKet_2(""+bphtb.getLetakObjectPajak());//BULAN//8
                                    
                                    //tagihanInsert.setSKet_3(""+bphtb.getPokok());//TAHUN//9
                                    //tagihanInsert.setSKet_4(""+bphtb.getDenda());//POKOK//10
                                    String valStr = Formater.formatNumber(Double.valueOf(bphtb.getPokok()), "#,###");
                                    tagihanInsert.setSKet_3(""+valStr);//TAHUN//9
                                    
                                    String denda="0";
                                    if(!bphtb.getDenda().equals("")){
                                        denda = Formater.formatNumber(Double.valueOf(bphtb.getDenda()), "#,###");
                                    }
                                    tagihanInsert.setSKet_4(""+denda);//POKOK//10
                                    
                                    tagihanInsert.setSKet_5("");//11
                                    tagihanInsert.setSKet_6("");//12
                                    tagihanInsert.setSKet_7("");//13
                                    tagihanInsert.setSKet_8("");//14
                                    tagihanInsert.setSKet_9("");
                                    tagihanInsert.setSKet_10("");
                                    tagihanInsert.setSKet_11("");
                                    tagihanInsert.setSKet_12("");
                                    tagihanInsert.setSKet_13("");
                                    
//                                    String resp_code = echo.action(tagihanInsert);
//                                    massage="Insert ID "+bphtb.getId()+" Proses : "+resp_code;
                                    String resp_code ="";
                                    boolean cekHistory=false;
                                    //cekHistory = SessSimpatda.check(bphtb.getId(),"",bphtb.getJumlahTagihan(),"",bphtb.getInstansi());
                                    
                                    if(!cekHistory){
                                        resp_code = echo.action(tagihanInsert);
                                        massage="Insert ID "+bphtb.getId()+" Proses : "+resp_code;
                                        if(resp_code.equals("00")){
                                            //insert history
                                            LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                            logHistory.setNpwd(bphtb.getId());
                                            logHistory.setNama(bphtb.getNama());
                                            if(!bphtb.getJumlahTagihan().equals("")){
                                                logHistory.setJumlahPajak(Double.valueOf(bphtb.getJumlahTagihan()));
                                            }else{
                                                logHistory.setJumlahPajak(0);
                                            }
                                            logHistory.setTahun("");
                                            logHistory.setBulan("");
                                            logHistory.setInstansi(bphtb.getInstansi());
                                            logHistory.setDenda(0);
                                            
                                            long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                                        }
                                    } 
                                   
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
            }
    } 
    
    
    public void sentAutoBphtb(String where) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vSimpatda = new Vector();
                        vSimpatda=SessSimpatda.getListAutoBphtb(where);
                        /*
                         id --> sNoId   
                         nama --> sNama   
                         jum_tagihan --> jum_tagihan   
                         instansi --> sInstansi: BPHTB_GIANYAR   
                         NOP --> sKet_1
                         Letak Objek Pajak --> sKet2
                         */
                        EchoTagihanInsert echo = new EchoTagihanInsert();
                        if(vSimpatda.size()>0){
                            for (int i = 0; i < vSimpatda.size(); i++) {
                                    Bphtb bphtb = (Bphtb) vSimpatda.get(i);
                                    
                                    TagihanInsert tagihanInsert = new TagihanInsert();
                                    tagihanInsert.setsUser(AppSetting.USERNAME_BPHTB);//1
                                    tagihanInsert.setSPassword(AppSetting.PWD_BPHTB);//2
                                    tagihanInsert.setSNoId(""+bphtb.getId());//3
                                    tagihanInsert.setSNama(""+bphtb.getNama());//4
                                    tagihanInsert.setJumTagihan(Double.valueOf(bphtb.getJumlahTagihan()));//5
                                    tagihanInsert.setSInstansi(AppSetting.INSTANSI_BPHTB);//6
                                    tagihanInsert.setSKet_1(""+bphtb.getNop());//ALAMAT//7
                                    tagihanInsert.setSKet_2(""+bphtb.getLetakObjectPajak());//BULAN//8
                                    
                                    String valStr = Formater.formatNumber(Double.valueOf(bphtb.getPokok()), "#,###");
                                    tagihanInsert.setSKet_3(""+valStr);//TAHUN//9
                                    
                                    String denda="0";
                                    if(!bphtb.getDenda().equals("")){
                                        denda = Formater.formatNumber(Double.valueOf(bphtb.getDenda()), "#,###");
                                    }
                                    tagihanInsert.setSKet_4(""+denda);//POKOK//10
                                    tagihanInsert.setSKet_5("");//11
                                    tagihanInsert.setSKet_6("");//12
                                    tagihanInsert.setSKet_7("");//13
                                    tagihanInsert.setSKet_8("");//14
                                    tagihanInsert.setSKet_9("");
                                    tagihanInsert.setSKet_10("");
                                    tagihanInsert.setSKet_11("");
                                    tagihanInsert.setSKet_12("");
                                    tagihanInsert.setSKet_13("");
                                    
//                                    String resp_code = echo.action(tagihanInsert);
//                                    massage="Insert ID "+bphtb.getId()+" Proses : "+resp_code;
                                    String resp_code ="";
                                    boolean cekHistory=false;
                                    //cekHistory = SessSimpatda.check(bphtb.getId(),"",bphtb.getJumlahTagihan(),"",bphtb.getInstansi());
                                    
                                    if(!cekHistory){
                                        resp_code = echo.action(tagihanInsert);
                                        massage="Insert ID "+bphtb.getId()+" Proses : "+resp_code;
                                        if(resp_code.equals("00")){
                                            //insert history
                                            LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                            logHistory.setNpwd(bphtb.getId());
                                            logHistory.setTib(Long.parseLong(bphtb.getId()));
                                            logHistory.setNama(bphtb.getNama());
                                            if(!bphtb.getJumlahTagihan().equals("")){
                                                logHistory.setJumlahPajak(Double.valueOf(bphtb.getJumlahTagihan()));
                                            }else{
                                                logHistory.setJumlahPajak(0);
                                            }
                                            logHistory.setTahun("");
                                            logHistory.setBulan("");
                                            logHistory.setInstansi(bphtb.getInstansi());
                                            logHistory.setDenda(0);
                                            
                                            long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                                        }
                                    } 
                                   
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
            }
    }
    
    
    
    
    public synchronized void sentAutoPhr(String where) {
        
            try {
                
                try{
                       //proses query and sent simpatda ke server
                        Vector vSimpatda = new Vector();
                        vSimpatda=SessSimpatda.getListSimpatdaAuto(where);
                        EchoTagihanInsert echo = new EchoTagihanInsert();
                        if(vSimpatda.size()>0){
                            for (int i = 0; i < vSimpatda.size(); i++) {
                                    Simpatda simpatdatransfer = (Simpatda) vSimpatda.get(i);
                                    //disini buatkan proses hapus id, dan proses id yang baru
                                    EchoTagihanDeleteById echoTagihanDeleteById = new EchoTagihanDeleteById();
                                    TagihanDelete tagihanDelete = new TagihanDelete();
                                    if(!simpatdatransfer.getId().equals("")){
                                        tagihanDelete.setsUser(AppSetting.USERNAME_PHR);
                                        tagihanDelete.setsPassword(AppSetting.PWD_PHR);
                                        tagihanDelete.setsInstansi(AppSetting.INSTANSI_PHR);
                                        tagihanDelete.setsNoId(simpatdatransfer.getId());
                                        tagihanDelete.setsRecordId("");
                                        echoTagihanDeleteById.action(tagihanDelete);
                                        try{
                                            int xxx = PstLogHistoryTransaksi.deleteloghistoryperid(simpatdatransfer.getId());
                                        }catch(Exception ex){
                                        }
                                    }
                                    
                                    //lakukan proses insert data keselurahan sesuai dengan id yang ada
                                    //buatkan list data yang akan di upload berdasarkan id
                                    Vector listTagihanPhr = new Vector();
                                    String whereClause = " WHERE ID='"+simpatdatransfer.getId()+"' ";
                                    listTagihanPhr = SessSimpatda.getListSimpatda(whereClause); 
                                    if(listTagihanPhr.size()>0){
                                        for (int k = 0; k < listTagihanPhr.size(); k++) {
                                            Simpatda simpatda = (Simpatda) listTagihanPhr.get(k);
                                            TagihanInsert tagihanInsert = new TagihanInsert();
                                            tagihanInsert.setsUser(AppSetting.USERNAME_PHR);//1
                                            tagihanInsert.setSPassword(AppSetting.PWD_PHR);//2
                                            tagihanInsert.setSNoId(""+simpatda.getId());//3
                                            tagihanInsert.setSNama(""+simpatda.getNamaSimpatda());//4
                                            try{
                                                tagihanInsert.setJumTagihan(Double.valueOf(simpatda.getJumlahPajakSimpatda()));//5
                                            }catch(Exception ex){
                                                tagihanInsert.setJumTagihan(0);
                                            }
                                            tagihanInsert.setSInstansi(AppSetting.INSTANSI_PHR);//6
                                            tagihanInsert.setSKet_1(""+simpatda.getAlamat());//ALAMAT//7
                                            tagihanInsert.setSKet_2(""+simpatda.getBulanSimpatda());//BULAN//8
                                            tagihanInsert.setSKet_3(""+simpatda.getTahunSimpatda());//TAHUN//9
                                            tagihanInsert.setSKet_4(""+simpatda.getPokok());//POKOK//10
                                            try{
                                                if(simpatda.getDenda().equals("null")){
                                                    tagihanInsert.setSKet_5(""+simpatda.getDenda());//11
                                                }
                                            }catch(Exception ex){
                                                tagihanInsert.setSKet_5("0");//11
                                            }
                                            
                                            tagihanInsert.setSKet_6(""+simpatda.getNoSspdSimpatda());//12
                                            if(simpatda.getVolumeKet()==null){
                                                tagihanInsert.setSKet_7(""+simpatda.getKeterangan());//13
                                            }else{
                                                tagihanInsert.setSKet_7(" Volume "+simpatda.getVolumeKet() +" M3 ");//13
                                            }
                                            tagihanInsert.setSKet_8("");//14
                                            tagihanInsert.setSKet_9("");
                                            tagihanInsert.setSKet_10("");
                                            tagihanInsert.setSKet_11("");
                                            tagihanInsert.setSKet_12("");
                                            tagihanInsert.setSKet_13("");

                                            String resp_code ="";
                                            ///cek apakah di history
                                            boolean cekHistory=false;
                                            if(!cekHistory){
                                                try{
                                                    resp_code = echo.action(tagihanInsert);
                                                    massage="Insert ID "+simpatda.getNpwpd()+" Proses : "+resp_code;
                                                    if(resp_code.equals("00")){
                                                            //insert history
                                                            LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                                            logHistory.setNpwd(simpatda.getId());
                                                            logHistory.setNama(simpatda.getNamaSimpatda());
                                                            try{
                                                                    logHistory.setJumlahPajak(Double.valueOf(simpatda.getJumlahPajakSimpatda()));
                                                            }catch(Exception ex){
                                                                logHistory.setJumlahPajak(0);
                                                            }
                                                            logHistory.setTahun(simpatda.getTahunSimpatda());
                                                            logHistory.setBulan(simpatda.getBulanSimpatda());
                                                            logHistory.setInstansi(simpatda.getInstansi());

                                                            try{
                                                                if(!simpatda.getDenda().equals("")){
                                                                    logHistory.setDenda(Double.valueOf(simpatda.getDenda()));
                                                                }else{
                                                                    logHistory.setDenda(0);
                                                                }
                                                            }catch(Exception ex){
                                                                logHistory.setDenda(0);
                                                            }   

                                                            try{
                                                                if(!simpatda.getPokok().equals("")){
                                                                    logHistory.setPokok(Double.valueOf(simpatda.getPokok()));
                                                                }else{
                                                                    logHistory.setPokok(0);
                                                                }
                                                            }catch(Exception ex){
                                                                logHistory.setPokok(0);
                                                            }
                                                            logHistory.setAlamat(simpatda.getAlamat());

                                                            long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                                                        }
                                                }catch(Exception ex){
                                                    DTaxManagerAutomaticPhr.running=false;
                                                    DTaxManagerAutomaticPhr.note=" Service Stop Cek NPWPD : "+tagihanInsert.getSNoId();
                                                }
                                            }
                                        }
                                    }    
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                    DTaxManagerAutomaticPhr.running=false;
                    DTaxManagerAutomaticPhr.note=" Service Stop Database view Compare";
                 }
                
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
                 DTaxManagerAutomaticPhr.running=false;
            }
    }
    
    public synchronized void prosesRetribusi(String var) {
            setLogErorPosting("");
            String[] splits = var.split(",");
            int count=0;
            String idTagihan="";
            String tahun="";
            String bulan="";
            for(String asset: splits){
                if(asset!=""){
                       String[] splitsDua = asset.split(";");
                       for(String value: splitsDua){
                           count=count+1;
                           if(count==1){
                               idTagihan=value;
                           }
                           if(count==2){
                               tahun=value;
                               //String whereSent= " WHERE NPWPD='"+idTagihan+"' AND BULAN='"+bulan+"' AND TAHUN='"+tahun+"'";
                               //String whereSent=" WHERE NOP='"+idTagihan+"' AND TAHUN='"+tahun+"'";
                               //String whereSent= " WHERE ID='"+idTagihan+"' AND NOP='"+tahun+"'";
                               String whereSent= " WHERE ID='"+idTagihan+"'";
                               sentRetribusi(whereSent);
                               count=0;
                           }
                       } 
                }
            }
    }
    
    public synchronized void sentRetribusi(String where) {
            setLogErorPosting("");
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vRetribusi = new Vector();
                        vRetribusi=SessSimpatda.getListRetribusi(where);
                        /*
                         id --> sNoId   
                         nama --> sNama   
                         jum_tagihan --> jum_tagihan   
                         instansi --> sInstansi: RETRIBUSI_GIANYAR   
                         tanggal_penerimaan --> sKet_1
                         */
                        EchoTagihanInsert echo = new EchoTagihanInsert();
                        if(vRetribusi.size()>0){
                            for (int i = 0; i < vRetribusi.size(); i++) {
                                    Retribusi retribusi = (Retribusi) vRetribusi.get(i);
                                    
                                    TagihanInsert tagihanInsert = new TagihanInsert();
                                    tagihanInsert.setsUser(AppSetting.USERNAME_RETRIBUSI);//1
                                    tagihanInsert.setSPassword(AppSetting.PWD_RETRIBUSI);//2
                                    tagihanInsert.setSNoId(""+retribusi.getNoRekening());//3
                                    tagihanInsert.setSNama(""+retribusi.getNama());//4
                                    tagihanInsert.setJumTagihan(Double.valueOf(retribusi.getJumlahTagihan()));//5
                                    tagihanInsert.setSInstansi(AppSetting.INSTANSI_RETRIBUSI);//6
                                    tagihanInsert.setSKet_1(""+retribusi.getTanggalPenerimaan());//Tanggal
                                    tagihanInsert.setSKet_2("");//BULAN//8
                                    tagihanInsert.setSKet_3("");//TAHUN//9
                                    tagihanInsert.setSKet_4("");//POKOK//10
                                    tagihanInsert.setSKet_5("");//11
                                    tagihanInsert.setSKet_6("");//12
                                    tagihanInsert.setSKet_7("");//13
                                    tagihanInsert.setSKet_8("");//14
                                    tagihanInsert.setSKet_9("");
                                    tagihanInsert.setSKet_10("");
                                    tagihanInsert.setSKet_11("");
                                    tagihanInsert.setSKet_12("");
                                    tagihanInsert.setSKet_13("");
                                    
//                                  String resp_code = echo.action(tagihanInsert);
//                                  massage="Insert ID "+bphtb.getId()+" Proses : "+resp_code;
                                    String resp_code ="";
                                    boolean cekHistory=false;
                                    cekHistory = SessSimpatda.checkHistoryRetribusi(retribusi.getNoRekening(),retribusi.getTahun(),retribusi.getBulan(),retribusi.getTanggal());
                                    
                                    if(!cekHistory){
                                        
                                        resp_code = echo.action(tagihanInsert);
                                        massage="Insert ID "+retribusi.getNoRekening()+" Proses : "+resp_code;
                                        
                                        if(resp_code.equals("00")){
                                            setLogErorPosting("Posting berhasil : "+massage);
                                            //insert history
                                            LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                            logHistory.setNpwd(retribusi.getNoRekening());
                                            logHistory.setNama(retribusi.getNama());
                                            
                                            if(!retribusi.getJumlahTagihan().equals("")){
                                                logHistory.setJumlahPajak(Double.valueOf(retribusi.getJumlahTagihan()));
                                            }else{
                                                logHistory.setJumlahPajak(0);
                                            }
                                            
                                            logHistory.setTahun(retribusi.getTahun());
                                            logHistory.setBulan(retribusi.getBulan());
                                            //logHistory.setTanggal(retribusi.getTanggal());
                                            logHistory.setInstansi(retribusi.getInstansi());
                                            logHistory.setIdKey(retribusi.getIdKey());
                                            
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                            String dateStringTransaksi = ""+retribusi.getTanggalPenerimaan();
                                            try {
                                                    Date transaksiDate = formatter.parse(dateStringTransaksi);
                                                    logHistory.setTanggalRetribusi(transaksiDate);
                                            } catch (Exception e) {
                                                    e.printStackTrace();
                                            }
                                            
                                            logHistory.setDenda(0);
                                            
                                            long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                                            
                                        }else{
                                            setLogErorPosting(""+" Posting Data Pajak : "+" Gagal");
                                        }
                                        
                                    } 
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                    setLogErorPosting(""+" Posting Data Pajak : "+" Gagal "+e.getMessage());
                 }
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
                setLogErorPosting(""+" Posting Data Pajak : "+" Gagal "+e.getMessage());
            }
    }
    
    /**
     * PAYMENT PROSES
     */
    
    
    public void inputPaymentPHR(String dateLaporan, String noID) {
        
            try {
                 try{
                        
                        Vector vPaymentPhr = new Vector();
                        EchoLaporanPaymentDetail echoLaporan = new EchoLaporanPaymentDetail();
                        Date newDay=new Date();
                        LaporanPayment laporanPayment = new LaporanPayment();
                        
                        laporanPayment.setsUser(AppSetting.USERNAME_PHR);
                        laporanPayment.setsPassword(AppSetting.PWD_PHR);
                        laporanPayment.setsInstansi(AppSetting.INSTANSI_PHR);
                        laporanPayment.setsNoId(""+noID);
                        laporanPayment.setsDate(""+dateLaporan);
                        vPaymentPhr = echoLaporan.getListPaymentDetailPHR(laporanPayment);

                        if(vPaymentPhr.size()>0){
                            for (int i = 0; i < vPaymentPhr.size(); i++) {
                                
                                    Payment payment = (Payment) vPaymentPhr.get(i);
                                    PaymentPhr paymentPhr = new PaymentPhr();
                                    paymentPhr.setNoSspd(payment.getId());
                                    paymentPhr.setNpwpd(payment.getNoId());//?
                                    paymentPhr.setMasaPajak(""+payment.getBulan());
                                    paymentPhr.setTahunPajak(""+payment.getTahun());
                                    double absoluteTagihan=0;
                                    if(!payment.getTagihan().equals("")){
                                        paymentPhr.setJumlahBayar(Double.valueOf(payment.getTagihan()));
                                        absoluteTagihan= Math.abs(paymentPhr.getJumlahBayar());
                                    }else{
                                        paymentPhr.setJumlahBayar(0);
                                    }
                                    paymentPhr.setNama(payment.getNama());
                                    
                                    if(!payment.getBiayaAdm().equals("")){
                                        paymentPhr.setBiayaAdministrasi(Double.valueOf(payment.getBiayaAdm()));
                                    }else{
                                        paymentPhr.setBiayaAdministrasi(0);
                                    }
                                    
                                    if(!payment.getDenda().equals("")){
                                        paymentPhr.setDenda(Double.valueOf(payment.getDenda()));
                                    }else{
                                        paymentPhr.setDenda(0);
                                    }
                                    if(!payment.getPokok().equals("")){
                                        paymentPhr.setPokok(Double.valueOf(payment.getPokok()));
                                    }else{
                                        paymentPhr.setPokok(0);
                                    }
                                    
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
                                    
                                    paymentPhr.setStatus(payment.getStsReversal());
                                    
                                    paymentPhr.setIdRekam("090909090");
                                    
                                    try{
                                        boolean cekHistory = SessSimpatda.checkPaymentPhr(payment.getId());
                                        if(cekHistory){
                                            String idKey = SessSimpatda.checkKeyId(payment.getNoId(), payment.getTahun(), payment.getBulan(), absoluteTagihan);
                                            paymentPhr.setIdKey(idKey);
                                            
                                            String wrClause =   "\"REPLACE\"(view_all_simpatda.NPWPD, '.', '') = '"+payment.getNoId()+"' \n" +
                                                                "and view_all_simpatda.TAHUN_PAJAK = '"+payment.getTahun()+"' \n" +
                                                                "and view_all_simpatda.MASA_PAJAK = '"+payment.getBulan()+"'\n" +
                                                                "and view_all_simpatda.HARUS_DIBAYAR = '"+absoluteTagihan+"'";
                                            Vector getNoSptpd = PstViewAllSimpatda.listJoinNamaBadan(0, 0, wrClause, "");
                                            ViewAllSimpatda entSimpatda = new ViewAllSimpatda();
                                            if(getNoSptpd.size()>0){
                                                entSimpatda = (ViewAllSimpatda) getNoSptpd.get(0);
                                                paymentPhr.setNoSspd(entSimpatda.getNosptpd());
                                            }
                                            
                                            try{
                                                long oid = PstPaymentPhr.insertExc(paymentPhr);
                                            }catch(Exception ex){}
                                            try{
                                                long oid2 = PstPaymentPhrAll.insertExc(paymentPhr);
                                            }catch(Exception ex){}
                                            
                                            if(payment.getStsReversal().equals("1")){
                                                String update = SessSimpatda.updateStatusRaversal(payment.getNoId(), payment.getTahun(), payment.getBulan(), absoluteTagihan);
                                                String update_sll = SessSimpatda.updateStatusRaversalPhrAll(payment.getNoId(), payment.getTahun(), payment.getBulan(), absoluteTagihan);
                                            }
                                        }
                                          
                                    }catch(Exception ex){
                                        System.out.print("Tidak bisa proses input payment");
                                    }         
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err Sent CDR :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err Err Sent CDR :" + e);
            }
    }
    
    
    
    public void inputPaymentPBB(String dateLaporan, String noID) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        
                        Vector vPaymentPBB = new Vector();
                        EchoLaporanPaymentDetail echoLaporan = new EchoLaporanPaymentDetail();
                        Date newDay=new Date();
                        LaporanPayment laporanPayment = new LaporanPayment();
                        
                        laporanPayment.setsUser(AppSetting.USERNAME_PBB);
                        laporanPayment.setsPassword(AppSetting.PWD_PBB);
                        laporanPayment.setsInstansi(AppSetting.INSTANSI_PBB);
                        laporanPayment.setsNoId(""+noID);
                        laporanPayment.setsDate(""+dateLaporan);
                        vPaymentPBB = echoLaporan.getListPaymentDetailPBB(laporanPayment);

                        if(vPaymentPBB.size()>0){
                            for (int i = 0; i < vPaymentPBB.size(); i++) {
                                
                                    Payment payment = (Payment) vPaymentPBB.get(i);
                                    
                                    PaymentPbb paymentPbb = new PaymentPbb();
                                    
                                    paymentPbb.setIdPaymentBank(Long.parseLong(payment.getId()));
                                    
                                    paymentPbb.setKdPropinsi("");
                                    paymentPbb.setKdDati2("");
                                    paymentPbb.setKdKecamata("");
                                    paymentPbb.setKdKelurahan("");
                                    paymentPbb.setKdBlok("");
                                    paymentPbb.setNoUrut("");
                                    paymentPbb.setKdJnsOp("");
                                    
                                    paymentPbb.setThnPajakSppt(""+payment.getTahun());
                                    
                                    paymentPbb.setPembayaranSpptKe(0);
                                    paymentPbb.setKdKanwilBank("");
                                    paymentPbb.setKdKppbbBank("");
                                    paymentPbb.setKdBankTunggal("");
                                    paymentPbb.setKdBankPersepsi("");
                                    paymentPbb.setKdTp("");
                                    
                                    if(!payment.getDenda().equals("")){
                                        paymentPbb.setDendaSppt(Double.valueOf(payment.getDenda()));
                                    }
                                    if(!payment.getTagihan().equals("")){
                                        paymentPbb.setJmlSpptYgDibayar(Double.valueOf(payment.getTagihan()));
                                    } 
                                    
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    //String dateInString = ""+payment.getTanggalTagihan();
                                    String dateStringTransaksi = ""+payment.getTglTx();
                                    try {
                                            //Date date = formatter.parse(dateInString);
                                            Date transaksiDate = formatter.parse(dateStringTransaksi);
                                            
                                            paymentPbb.setTglPembayaranSppt(transaksiDate);
                                            paymentPbb.setTglRekamByrSppt(transaksiDate);
                                            
                                    } catch (Exception e) {
                                            e.printStackTrace();
                                    }
                                    
                                    paymentPbb.setNop(Long.valueOf(payment.getNoId()));
                                    
                                    paymentPbb.setNipRekamByrSppt("090909090");
                                    
                                    paymentPbb.setStatus(Integer.parseInt(payment.getStsReversal()));
                                    
                                    try{
                                        
                                        boolean cekHistory = SessSimpatda.checkPaymentPBB(payment.getId());
                                        //if(cekHistory){
                                        //    long oid = PstPaymentPbb.insertExc(paymentPbb);  
                                        //}
                                        
                                         if(cekHistory){
                                            String idKey = SessSimpatda.checkKeyIdPbb(payment.getNoId(), payment.getTahun(), payment.getBulan(), 0);
                                            paymentPbb.setIdKey(idKey);
                                            try{
                                                long oidH = PstPaymentPbbHistory.insertExc(paymentPbb);
                                            }catch(Exception ex){
                                                System.err.println("tidak masuk history payment pbb");
                                            }
                                            long oid = PstPaymentPbb.insertExc(paymentPbb);  
                                            if(payment.getStsReversal().equals("1")){
                                                String update = SessSimpatda.updateStatusRaversalPbb(payment.getNoId(), payment.getTahun(), payment.getBulan(), 0);
                                            }
                                        }
                                        
                                    }catch(Exception ex){
                                        System.out.print("Tidak bisa proses input payment");
                                    }         
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err inputPaymentPBB :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err inputPaymentPBB :" + e);
            }
    }
    
    
    
    public void inputPaymentPBBIproTax(String dateLaporan, String noID) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        
                        Vector vPaymentPBB = new Vector();
                        EchoLaporanPaymentDetail echoLaporan = new EchoLaporanPaymentDetail();
                        Date newDay=new Date();
                        LaporanPayment laporanPayment = new LaporanPayment();
                        
                        laporanPayment.setsUser(AppSetting.USERNAME_PBB);
                        laporanPayment.setsPassword(AppSetting.PWD_PBB);
                        laporanPayment.setsInstansi(AppSetting.INSTANSI_PBB);
                        laporanPayment.setsNoId(""+noID);
                        laporanPayment.setsDate(""+dateLaporan);
                        vPaymentPBB = echoLaporan.getListPaymentDetailPBB(laporanPayment);

                        if(vPaymentPBB.size()>0){
                            for (int i = 0; i < vPaymentPBB.size(); i++) {
                                    Payment payment = (Payment) vPaymentPBB.get(i);
                                    PaymentPbbIprotax paymentPbb = new PaymentPbbIprotax();
                                    
                                    //cek detail data yang dibyarkan berdasarkan nop
                                    String nop = payment.getNoId();
                                    
                                    /*cek aplikasi*/
                                    PbbIprotax pbbIprotax = PstPbbIprotax.checkNOp(nop, payment.getTahun());
                                    paymentPbb.setKdProvinsi(pbbIprotax.getKdProvinsi());
                                    paymentPbb.setKdDati2(pbbIprotax.getKdDati2());
                                    paymentPbb.setKdKecamatan(pbbIprotax.getKdKecamatan());
                                    paymentPbb.setKdKelurahan(pbbIprotax.getKdKelurahan());
                                    paymentPbb.setKdBlock(pbbIprotax.getKdBlock());
                                    paymentPbb.setNoUrut(pbbIprotax.getNoUrut());
                                    paymentPbb.setNoJnsOp(pbbIprotax.getNoJnsOp());
                                    paymentPbb.setThnPajakSppt(""+payment.getTahun());
                                    
                                    //cek no urut pembayaran
                                    int noUrut = PstPaymentPbbIprotax.getCount(" NOP ='"+nop+"' AND THN_PAJAK_SPPT='"+payment.getTahun()+"'" );
                                    if(noUrut==0){
                                        noUrut=noUrut+1;
                                    }else{
                                        noUrut=noUrut+1;
                                    }
                                     paymentPbb.setPembayaranSpptKe(noUrut);
                                    if(!payment.getDenda().equals("")){
                                        //sVal = deFormatStringDecimal(sVal);  payment.getDenda()
                                        paymentPbb.setJmlDendaSppt(Double.parseDouble(FRMHandler.deFormatStringDecimal(payment.getDenda())));
                                    }
                                    if(!payment.getTagihan().equals("")){
                                        paymentPbb.setJmlPbbYgDibayar(Double.valueOf(payment.getTagihan()));
                                    } 
                                    paymentPbb.setUraianBayarSppt("");
                                    paymentPbb.setKdSumberData("0");
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    String dateStringTransaksi = ""+payment.getTglTx();
                                    try {
                                            Date transaksiDate = formatter.parse(dateStringTransaksi);
                                            paymentPbb.setTglPembayaranSppt(transaksiDate);
                                            paymentPbb.setTglRekamByrSppt(transaksiDate);
                                    } catch (Exception e) {
                                            e.printStackTrace();
                                    }
                                    
                                    paymentPbb.setNoTransaksiByrSppt(""+payment.getKetTagihan());
                                    paymentPbb.setNoTransaksiByrSpptKolektif("");
                                    paymentPbb.setNoTransaksiByrSpptBank(""+payment.getId());
                                   
                                    paymentPbb.setKdBankTunggal(pbbIprotax.getKdBankTunggal());
                                    paymentPbb.setKdBankPersepsi(pbbIprotax.getKdBankPersepsi());
                                    paymentPbb.setKdTp(pbbIprotax.getKdTp());
                                    
                                    paymentPbb.setUserBankRekam("060000000");
                                    paymentPbb.setStatus(Integer.parseInt(payment.getStsReversal()));
                                    paymentPbb.setStatusRaversal(Integer.parseInt(payment.getStsReversal()));
                                    paymentPbb.setNamaPenyetor(payment.getNama());
                                    try{
                                        boolean cekHistory = PstPaymentPbbIprotax.checkPaymentPBBIprotax(payment.getId());
                                         if(true){
                                            if(payment.getStsReversal().equals("1")){
                                                //delete pembayaran
                                                if(paymentPbb.getJmlPbbYgDibayar()>0){
                                                    //cek apakah data sudah di inputkan di raversal
                                                    boolean cekRaversalData = SessPbbIprotax.checkPaymentPBBRaversalIprotax(payment.getId());
                                                    if(cekRaversalData){
                                                        //delete data pembayaran
                                                        int oid = SessPbbIprotax.DeleteDataPembayaran(payment.getId(), paymentPbb.getKdProvinsi(), paymentPbb.getKdDati2(), paymentPbb.getKdKecamatan(), paymentPbb.getKdKelurahan(), paymentPbb.getKdBlock(), paymentPbb.getNoUrut(), paymentPbb.getNoJnsOp(), paymentPbb.getThnPajakSppt(), "0");
                                                        
                                                        //update status tagihan
                                                        if(oid!=0){
                                                          long xxx = SessPbbIprotax.UpdateDataTagihan(paymentPbb.getKdProvinsi(), paymentPbb.getKdDati2(), paymentPbb.getKdKecamatan(), paymentPbb.getKdKelurahan(), paymentPbb.getKdBlock(), paymentPbb.getNoUrut(), paymentPbb.getNoJnsOp(), paymentPbb.getThnPajakSppt(), "0");
                                                        }
                                                        
                                                        //insert ke data raversal
                                                        boolean yy  = SessPbbIprotax.insertPaymentPbbRaversalIprotax(paymentPbb);
                                                    } 
                                                }
                                            }else{
                                                //lakukan input pembayaran
                                                if(cekHistory){
                                                    long oid = PstPaymentPbbIprotax.insertExc(paymentPbb);  
                                                    //update data kalau sudah lunas
                                                    if(oid!=0){
                                                        long xxx = SessPbbIprotax.UpdateDataTagihan(paymentPbb.getKdProvinsi(), paymentPbb.getKdDati2(), paymentPbb.getKdKecamatan(), paymentPbb.getKdKelurahan(), paymentPbb.getKdBlock(), paymentPbb.getNoUrut(), paymentPbb.getNoJnsOp(), paymentPbb.getThnPajakSppt(), "1");
                                                    }
                                                }
                                            
                                            }
                                        }
                                        
                                    }catch(Exception ex){
                                        System.out.print("Tidak bisa proses input payment");
                                    }         
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("Err inputPaymentPBB :" + e);
                 }
            } catch (Exception e) {
                System.out.println("Err inputPaymentPBB :" + e);
            }
    }
    
    
     public void inputPaymentBphtb(String dateLaporan, String noID) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vPaymentBphtb = new Vector();
                        EchoLaporanPaymentDetail echoLaporan = new EchoLaporanPaymentDetail();
                        Date newDay=new Date();
                        LaporanPayment laporanPayment = new LaporanPayment();
                        
                        laporanPayment.setsUser(AppSetting.USERNAME_BPHTB);
                        laporanPayment.setsPassword(AppSetting.PWD_BPHTB);
                        laporanPayment.setsInstansi(AppSetting.INSTANSI_BPHTB);
                        laporanPayment.setsNoId(""+noID);
                        laporanPayment.setsDate(""+dateLaporan);
                        vPaymentBphtb = echoLaporan.getListPaymentDetailBPHTB(laporanPayment);

                        if(vPaymentBphtb.size()>0){
                            
                            for (int i = 0; i < vPaymentBphtb.size(); i++) {
                                    
                                    Payment payment = (Payment) vPaymentBphtb.get(i);
                                    PaymentBphtb paymentBphtb = new PaymentBphtb();
                                    paymentBphtb.setNoTib(Long.valueOf(payment.getNoId()));
                                    paymentBphtb.setIdPaymentBank(Long.valueOf(payment.getId()));
                                    if(!payment.getTagihan().equals("")){
                                        paymentBphtb.setJumlahBayar(Double.valueOf(payment.getTagihan()));
                                    }
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    String dateStringTransaksi = ""+payment.getTglTx();
                                    try {
                                            Date transaksiDate = formatter.parse(dateStringTransaksi);
                                            paymentBphtb.setTglBayar(transaksiDate);
                                            
                                    } catch (Exception e) {
                                            e.printStackTrace();
                                    }
                                    
                                    paymentBphtb.setStatus(Integer.parseInt(payment.getStsReversal()));
                                    
                                    try{
                                        boolean cekHistory = SessSimpatda.checkPaymentBphtb(payment.getId());
                                        
                                        if(cekHistory){
                                            String idKey = SessSimpatda.checkKeyIdBphtb(payment.getNoId(), payment.getTahun(), payment.getBulan(), 0);
                                            paymentBphtb.setIdKey(idKey);
                                            long oid = PstPaymentBphtb.insertExc(paymentBphtb);   
                                            if(payment.getStsReversal().equals("1")){
                                                String update = SessSimpatda.updateStatusRaversalBphtb(payment.getNoId(), payment.getTahun(), payment.getBulan(), 0);
                                            }
                                        }
                                        
                                    }catch(Exception ex){
                                        System.out.print("Tidak bisa proses input payment");
                                    }         
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("inputPaymentBphtb :" + e);
                 }
            } catch (Exception e) {
                System.out.println("inputPaymentBphtb :" + e);
            }
    }

      public  synchronized void inputPaymentRetribusi(String dateLaporan, String noID) {
          inputPaymentRetribusi(dateLaporan,  noID,"");
      }
    
     public synchronized void inputPaymentRetribusi(String dateLaporan, String noID, String noBukti) {
        
            try {
                 try{
                       //proses query and sent simpatda ke server
                        Vector vPaymentRetribusi = new Vector();
                        EchoLaporanPaymentDetailSetelahNoBukti echoLaporanSetelahNoBukti = new EchoLaporanPaymentDetailSetelahNoBukti();
                        EchoLaporanPaymentDetail echoLaporan = new EchoLaporanPaymentDetail();
                        Date newDay=new Date();
                        LaporanPayment laporanPayment = new LaporanPayment();
                        
                        laporanPayment.setsUser(AppSetting.USERNAME_RETRIBUSI);
                        laporanPayment.setsPassword(AppSetting.PWD_RETRIBUSI);
                        laporanPayment.setsInstansi(AppSetting.INSTANSI_RETRIBUSI);
                        laporanPayment.setsNoId(""+noID);
                        laporanPayment.setsDate(""+dateLaporan);
                        
                        if(noBukti.equals("")){
                            vPaymentRetribusi = echoLaporan.getListPaymentDetailRetribusi(laporanPayment);
                        }else{
                            laporanPayment.setsNoBuktiBank(noBukti);
                           vPaymentRetribusi = echoLaporanSetelahNoBukti.getListPaymentDetailRetribusiSetelahNoBukti(laporanPayment);
                        }    
                        
                        if(vPaymentRetribusi.size()>0){
                            for (int i = 0; i < vPaymentRetribusi.size(); i++) {
                                    Payment payment = (Payment) vPaymentRetribusi.get(i);
                                    PaymentRetribusi paymentRetribusi = new PaymentRetribusi();
                                    
                                    //paymentBphtb.setNoTib(Long.valueOf(payment.getNoId()));
                                    paymentRetribusi.setIdPaymentBank(Long.parseLong(payment.getId()));
                                    paymentRetribusi.setNoRekening(payment.getNoId());
                                    paymentRetribusi.setNama(payment.getNama());
                                    
                                    
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    //SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yy", Locale.INDONESIA);
                                    DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                                    //DateFormat formatter2 = new SimpleDateFormat("dd-MMM-yy",Locale.US);
                                    String dateInString = ""+payment.getTanggalTagihan();
                                    String dateStringTransaksi = ""+payment.getTglTx();
                                    String dateSearch="";
                                    String strYear = "";
                                    String strMonth = "";
                                    String strDate ="";
                                    try {
                                            Date transaksiDate = formatter.parse(dateStringTransaksi);
                                            Date date = formatter2.parse(dateInString);
                                            
                                            paymentRetribusi.setTanggalTagihan(date);
                                            paymentRetribusi.setTanggalPembayaran(transaksiDate);
                                            dateSearch=Formater.formatDate(date, "yyyy-MM-dd");
                                            strYear = String.valueOf(date.getYear() + 1900);
                                            strMonth = String.valueOf(date.getMonth() + 1);
                                            strDate = String.valueOf(date.getDate());
                                            if(strMonth.length()==1){
                                                strMonth="0"+strMonth;
                                            }
                                            if(strDate.length()==1){
                                                strDate="0"+strDate;
                                            }
                                            paymentRetribusi.setMasaPajak(strMonth);
                                            paymentRetribusi.setTahun(strYear);
                                            
                                    } catch (Exception e) {
                                            e.printStackTrace();
                                    }
                                    
                                    if(!payment.getTagihanLain().equals("")){
                                        paymentRetribusi.setJumlahTagihan(Double.valueOf(payment.getTagihanLain()));
                                    } 
                                    paymentRetribusi.setIdRekam("090909090");
                                    paymentRetribusi.setStatusReversal(Integer.valueOf(payment.getStsReversal()));
                                    
                                    try{
                                        
                                        boolean cekHistory = SessSimpatda.checkPaymentRetribusi(payment.getId());
                                        if(cekHistory){
                                            String idKey = SessSimpatda.checkKeyIdRetribusi(payment.getNoId(),strYear,strMonth,strDate);
                                            paymentRetribusi.setIdKey(idKey);
                                            
                                            long oid = PstPaymentRetribusi.insertExc(paymentRetribusi);
                                            if(payment.getStsReversal().equals("1")){
                                                String update = SessSimpatda.updateStatusRaversalRetribusi(payment.getNoId(), dateSearch, payment.getBulan(), Math.abs(paymentRetribusi.getJumlahTagihan()));
                                            }
                                        }
                                        
                                    }catch(Exception ex){
                                        System.out.print("Tidak bisa proses input payment");
                                    }         
                            }
                        }
                 }  catch (Exception e) {
                    System.out.println("inputPaymentBphtb :" + e);
                 }
            } catch (Exception e) {
                System.out.println("inputPaymentBphtb :" + e);
            }
    }
     
    public static int getIdleSleepTime(long current, long delay, long quarter, long day, long night) {

        long gap = 0;

        if (current < delay) {

            System.out.println("_______________ start service monitoy sebelum delay");

            gap = delay - current - 100;

            return (int) gap;

        } else {

            if (current == delay) {

                System.out.println("_______________ start service monitoy sama delay");

                return 0;

            } else {

                if (current > delay && current < quarter) {

                    System.out.println("_______________ start service monitoy sebelum quarter");

                    gap = quarter - current - 100;

                    return (int) gap;

                } else {

                    if (current == quarter) {

                        System.out.println("_______________  start service monitoy sama delay");

                        return 0;

                    } else {

                        if (current > quarter && current < day) {

                            System.out.println("_______________  start service monitoy sebelum day");

                            gap = day - current - 100;

                            return (int) gap;

                        } else {

                            if (current == day) {

                                System.out.println("_______________  start service monitoy sama day");

                                return 0;

                            } else {

                                if (current > day && current < night) {

                                    System.out.println("_______________  start service monitoy sebelum night");

                                    gap = night - current - 100;

                                    return (int) gap;

                                } else {

                                    if (current == night) {

                                        System.out.println("_______________  start service monitoy sama delay");

                                        return 0;

                                    } else {

                                        System.out.println("_______________  start service monitoy sebelum delay besoknya");

                                        gap = delay - current - 100;

                                        return (int) gap;

                                    }

                                }



                            }

                        }

                    }

                }

            }

        }

    }

     public void sentDataBI(String where) {

        try {
                SearchDataPajak searchDataPajak = new SearchDataPajak();
                Date dStartDate = new Date();
                String startDate="";
                String endDate="";
                try {
                    startDate = Formater.formatDate(dStartDate, "yyyy-MM-dd");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                try {
                    endDate = Formater.formatDate(dStartDate, "yyyy-MM-dd");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                //DTaxManagerAutomaticDataPajakBI.note = DTaxManagerAutomaticDataPajakBI.note+" Proses data per tanggal "+startDate+" <br>";
                
                Vector vPajakDetail = new Vector();
                vPajakDetail = PstPajakTypeDetail.list(0, 0, "","");
                if( vPajakDetail.size()>0){
                    for (int i = 0; i < vPajakDetail.size(); i++) {
                        PajakTypeDetail pajakTypeDetail = (PajakTypeDetail) vPajakDetail.get(i);
                        searchDataPajak = new SearchDataPajak();

                        searchDataPajak.setPajakDetailId(pajakTypeDetail.getOID());
                        searchDataPajak.setStartDate(startDate);
                        searchDataPajak.setEndDate(endDate);
                        searchDataPajak.setQueryPajak(pajakTypeDetail.getPajakQuery());
                        searchDataPajak.setColomDate(pajakTypeDetail.getColomDate());
                        
                        //hapus data
                        int hasilDelete = SessDataPajak.deleteDataPajak(searchDataPajak);
                        
                        //ambil data
                        Vector vDataPajak = SessDataPajak.getListDataPajak(searchDataPajak);
                        
                        //simpan data
                        try{
                            int xxx = SessDataPajak.action(4, searchDataPajak, vDataPajak, pajakTypeDetail);
                        }catch(Exception ex){
                        
                        }
                    }
                }
        } catch (Exception e) {
        }
    }
     
    public int setSudahBayar(Payment payment, PaymentPhr paymentPhr, double absoluteTagihan){
        try{
            boolean cekHistory = SessSimpatda.checkPaymentPhr(payment.getId());
            if(cekHistory){
                String idKey = SessSimpatda.checkKeyId(payment.getNoId(), payment.getTahun(), payment.getBulan(), absoluteTagihan);
                paymentPhr.setIdKey(idKey);

                String wrClause =   "\"REPLACE\"(view_all_simpatda.NPWPD, '.', '') = '"+payment.getNoId()+"' \n" +
                                    "and view_all_simpatda.TAHUN_PAJAK = '"+payment.getTahun()+"' \n" +
                                    "and view_all_simpatda.MASA_PAJAK = '"+payment.getBulan()+"'\n" +
                                    "and view_all_simpatda.HARUS_DIBAYAR = '"+absoluteTagihan+"'";
                System.out.println(wrClause);
                Vector getNoSptpd = PstViewAllSimpatda.listJoinNamaBadan(0, 0, wrClause, "");
                ViewAllSimpatda entSimpatda = new ViewAllSimpatda();
                if(getNoSptpd.size()>0){
                    entSimpatda = (ViewAllSimpatda) getNoSptpd.get(0);
                    paymentPhr.setNoSspd(entSimpatda.getNosptpd());
                }

                try{
                    long oid = PstPaymentPhr.insertExc(paymentPhr);
                }catch(Exception ex){}
                try{
                    long oid2 = PstPaymentPhrAll.insertExc(paymentPhr);
                }catch(Exception ex){}

                if(payment.getStsReversal().equals("1")){
                    String update = SessSimpatda.updateStatusRaversal(payment.getNoId(), payment.getTahun(), payment.getBulan(), absoluteTagihan);
                    String update_sll = SessSimpatda.updateStatusRaversalPhrAll(payment.getNoId(), payment.getTahun(), payment.getBulan(), absoluteTagihan);
                }
            }
            
            return 0;
        }catch(Exception ex){
            System.out.print("Tidak bisa proses input payment");
        }   
        return 1;
    }   

    public static String dateConvert(String dateString){
        String[] arr = dateString.split("-");
        String day = arr[0];
        String month = arr[1];
        String year = arr[2];
        String date="";
        String realMonth="";
        switch (month) {
            case "JAN":
                realMonth="01";
                break;
            case "FEB":
                realMonth="02";
                break;
            case "MAR":
                realMonth="03";
                break;
            case "APR":
                realMonth="04";
                break;
            case "MEI":
                realMonth="05";
                break;
            case "JUN":
                realMonth="06";
                break;
            case "JUL":
                realMonth="07";
                break;
            case "AGT":
                realMonth="08";
                break;
            case "SEP":
                realMonth="09";
                break;    
            case "OKT":
                realMonth="10";
                break;    
            case "NOV":
                realMonth="11";
                break;
            case "DES":
                realMonth="12";
                break;
            default:
        }
        date=day+"-"+realMonth+"-"+year;
        return date;
    }

    public static int getIdleSleepTime(long current, long delay) {

        long gap = 0;

        if (current < delay) {

            System.out.println("_______________ start service monitoy sebelum delay");

            gap = delay - current - 100;

            return (int) gap;

        } 
        return (int) gap;
    }

    /**
     * @return the sleepTimeMinute
     */
    public long getSleepTimeMinute() {
        return sleepTimeMinute;
    }

    /**
     * @param sleepTimeMinute the sleepTimeMinute to set
     */
    public void setSleepTimeMinute(long sleepTimeMinute) {
        this.sleepTimeMinute = sleepTimeMinute;
    }
    
    
}

