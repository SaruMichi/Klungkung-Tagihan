/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import com.dimata.dslik.entity.masterdata.OutletConnection;
import com.dimata.util.Formater;
import java.util.Date;

/**
 *
 * @author dimata005
 */
public class MonitorDilimitedText implements Runnable {
    
    private String Proggess="";
    private FileSent fileSent=null;
    private OutletConnection outletConnection=null;
    
    public MonitorDilimitedText(FileSent fileSent, OutletConnection outletConnection) {
        try{
            this.fileSent = fileSent;
            this.outletConnection = outletConnection;
            this.Proggess ="";

        }catch(Exception e){
            System.out.println(" ! EXC : initiate thread =  "+e.toString());

        }

    }
    
    public void run() {

        System.out.println("start .... ");
        Date newDate = new Date();
        while (ManagerDilimitedText.running) {
            
            try {
                newDate = new Date();
                ManagerDilimitedText.setStatusProses("Proses Start : " + Formater.formatDate(newDate, "dd-MM-yyyy kk:mm"));
                
                /*proses transfer*/
                ProsesDilimitedText prosesDilimitedText = new ProsesDilimitedText();
                
                /*proses debitur perorangan*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_DEBITUR_PERORANGAN);
                prosesDilimitedText.actionDilimitedText(fileSent,outletConnection);
                
                /*proses debitur badan usaha*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_DEBITUR_BADAN_USAHA);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*proses kredit*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_KREDIT);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*proses kredit join*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_KREDIT_JOIN_ACCOUNT);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*surat berharga*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_SURAT_BERHARGA);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*irrevocable*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_IRREVOCABLE_LC);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*bank garansi*/ 
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_BANK_GARANSI);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*fasilitas lainnya*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_FASILITAS_LAINNYA);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*proses agunan*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_DATA_AGUNAN);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*penjamin*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_PENJAMIN);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                
                /*proses pengurus dan pemilik*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_PENGURUS_PEMILIK);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
                /*laporan keuangan*/
                fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_LAPORAN_KEUANGAN_DEBITUR);
                prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
                
//                if(fileSent.isCreateSummary()){
//                    /*laporan keuangan*/
//                    fileSent.setTypeSegment(Configurasi.FLD_SEGMENT_SUMMARY_FASILITAS);
//                    prosesDilimitedText.actionDilimitedText(fileSent, outletConnection);
//                }
                
                
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
            
            //proses create jadi file zip
            newDate = new Date();
            ManagerDilimitedText.setStatusProses(ManagerDilimitedText.getStatusProses() + "<br> Proses ZIP : " + Formater.formatDate(newDate, "dd-MM-yyyy kk:mm"));
            try{
                    FileSent.zipFolder(""+fileSent.getLocation(), ""+fileSent.getLocation()+".zip");
            }catch(Exception ex){
            }
            
            newDate = new Date();
            ManagerDilimitedText.setStatusProses(ManagerDilimitedText.getStatusProses() + "End Proses : " + Formater.formatDate(newDate, "dd-MM-yyyy kk:mm"));
            
            ManagerDilimitedText.running=false;
        }
       
        System.out.println("stop .... ");
    }
}