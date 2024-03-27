/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session;

import com.dimata.util.Formater;
import java.util.Date;

public class DTaxIntegrationManagerPaymentRetribusi {

    public static boolean running = false;
    
    public DTaxIntegrationManagerPaymentRetribusi() {

    }

    public void startMonitor() {

        if(running) return;

            DTaxIntegrationManagerPaymentRetribusi objMan = new  DTaxIntegrationManagerPaymentRetribusi();

            Thread thLocker = new Thread(new DTaxMonitorPaymentRetribusi());

            thLocker.setDaemon(false);

            running = true;

            thLocker.start();

    }





    public void stopMonitor() {

        running = false;
        Date newDate = new Date();
        System.out.println("monitoring stopped .... datae/time : "+Formater.formatDate(newDate, "dd-MM-yyyy kk:mm"));

    }





    public boolean getStatus() {

        return running;

    }

}

