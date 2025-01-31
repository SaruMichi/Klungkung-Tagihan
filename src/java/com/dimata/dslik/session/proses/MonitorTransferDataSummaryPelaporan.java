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
/**
 *
 * @author dimata005
 */
public class MonitorTransferDataSummaryPelaporan implements Runnable {

    private String Proggess = "";
    private FileSent fileSent = null;
    private OutletConnection outletConnection = null;

    public MonitorTransferDataSummaryPelaporan(FileSent fileSent, OutletConnection outletConnection) {
        try {
            this.fileSent = fileSent;
            this.outletConnection = outletConnection;
            this.Proggess = "";

        } catch (Exception e) {
            System.out.println(" ! EXC : initiate thread =  " + e.toString());

        }

    }

    public void run() {

        System.out.println("start .... ");

        while (ManagerDilimitedTextSummary.running) {

            try {

                /*proses transfer*/
                ManagerDilimitedTextSummary.setStatusProses("Start Periode <br>");
                ManagerDilimitedTextSummary.setStatusSummaryPelaporan("");
                ManagerDilimitedTextSummary.setStatusSummaryPelaporanProsesTransferData("");
                /*proses transfer*/
                ProsesDilimitedText prosesDilimitedText = new ProsesDilimitedText();
                prosesDilimitedText.actionProsesSummaryData(fileSent, outletConnection);
                
                
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }

            Date newDate = new Date();
            ManagerDilimitedTextSummary.statusEnd = " Proses Berakhir  .... datae/time : " + Formater.formatDate(newDate, "dd-MM-yyyy kk:mm");
            ManagerDilimitedTextSummary.running = false;
            ManagerDilimitedTextSummary.runningReload = false;

        }
        System.out.println("stop .... ");
    }

}
