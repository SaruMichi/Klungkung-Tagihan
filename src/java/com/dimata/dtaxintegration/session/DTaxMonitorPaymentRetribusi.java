/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session;

import com.dimata.dtaxintegration.entity.payment.PstPaymentRetribusi;
import com.dimata.dtaxintegration.entity.tagihan.FileSent;
import com.dimata.util.Formater;
import java.util.Date;
import java.util.Vector;

public class DTaxMonitorPaymentRetribusi implements Runnable {
    
    private FileSent fileSent=null;
    private String Proggess="";
    
    public DTaxMonitorPaymentRetribusi() {
            
    }
    
    public void run() {

        System.out.println("start .... ");
    
        while (DTaxIntegrationManagerPaymentRetribusi.running) {
            
            try {
                Date newDay=new Date();
                String startDate = Formater.formatDate(newDay,"yyyy-MM-dd");
                String oDate = Formater.formatDate(newDay,"yyyy-MM-dd HH:mm:ss");
                DTaxIntegrationMonitor dTaxIntegrationMonitor = new DTaxIntegrationMonitor();
                
                //Vector listPayment = new Vector();
                String whereClause = "TRUNC("+PstPaymentRetribusi.fieldNames[PstPaymentRetribusi.FLD_TANGGALPEMBAYARAN]+") = TO_DATE ('"+startDate+"','YYYY-MM-DD HH24:MI:SS')"
                        + " ORDER BY ID_PAYMENT_BANK DESC ";
                String noBuktiTerakhir = PstPaymentRetribusi.noBuktiAkhir(0, 0, whereClause, "");
    
                dTaxIntegrationMonitor.inputPaymentRetribusi(startDate, "", noBuktiTerakhir);
                Thread.sleep((long) (2 * 60000));//milisecond tiap 4 jam = 240 menit
                
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
        }
        System.out.println("stop .... ");
    }
}