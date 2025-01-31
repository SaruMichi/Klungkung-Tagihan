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
public class MonitorTransferDataBankGaransi implements Runnable {
    
    private String Proggess="";
    
    public MonitorTransferDataBankGaransi() {
            
    }
    
    public void run() {

        System.out.println("start .... ");
    
        while (ManagerTransferDataBankGaransi.running) {
            
            try {
                
                /*proses transfer*/
                ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
                //cek periode berjalan
                ManagerTransferDataBankGaransi.setStatusProses("Start Periode <br>");
                long periodeId = 0;
                try{
                    periodeId = prosesTransferDataBank.cekOidPeriode("",4);
                }catch(Exception ex){
                }
                
                //Cek jumlah data
                int countBankGaransi = prosesTransferDataBank.countTransferDataBankGaransi("",periodeId);
                
                ManagerTransferDataBankGaransi.setStatusProses("Bank Garansi :"+countBankGaransi+" <br>");
                
                ManagerTransferDataBankGaransi.setStatusProses("Get Bank Garansi <br> ");
                String bankGaransi = prosesTransferDataBank.actionTransferDataBankGaransi("",periodeId,countBankGaransi,0);
                
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
            
            Date newDate = new Date();
            ManagerTransferDataDebitur.statusEnd =" Proses Berakhir  .... datae/time : "+Formater.formatDate(newDate, "dd-MM-yyyy kk:mm");
            
            ManagerTransferDataBankGaransi.running=false;
        }
        System.out.println("stop .... ");
    }
    
}
