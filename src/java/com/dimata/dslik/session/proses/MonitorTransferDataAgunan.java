/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import com.dimata.util.Formater;
import java.util.Date;

/**
 *
 * @author dimata005
 */
public class MonitorTransferDataAgunan implements Runnable {
    
    private String Proggess="";
    
    public MonitorTransferDataAgunan() {
            
    }
    
    public void run() {

        System.out.println("start .... ");
    
        while (ManagerTransferDataAgunan.running) {
            
            try {
                
                /*proses transfer*/
                ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
                //cek periode berjalan
                ManagerTransferDataAgunan.setStatusProses("Start Periode <br>");
                long periodeId = 0;
                try{
                    periodeId = prosesTransferDataBank.cekOidPeriode("",3);
                }catch(Exception ex){
                }
                
                //Cek jumlah data
                int countAgunan = prosesTransferDataBank.countTransferDataAgunan("",periodeId);
                
                ManagerTransferDataAgunan.setStatusProses("Agunan :"+countAgunan+" <br>");
                
                ManagerTransferDataAgunan.setStatusProses("Get Data Agunan <br>");
                String agunan = prosesTransferDataBank.actionTransferDataAgunan("",periodeId,countAgunan,0,"");
                
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
            
            Date newDate = new Date();
            ManagerTransferDataAgunan.statusEnd =" Proses Berakhir  .... datae/time : "+Formater.formatDate(newDate, "dd-MM-yyyy kk:mm");
            ManagerTransferDataAgunan.running=false;
        }
        System.out.println("stop .... ");
    }
    
}