/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session;

/**
 *
 * @author xml
 */
public class DTaxMonitorBphtb implements Runnable {
    
    public DTaxMonitorBphtb() {

    }
    
    public void run() {

        System.out.println("start .... ");

    
        while (DTaxManagerBphtb.running) {
            
            try {
                DTaxIntegrationMonitor dTaxIntegrationMonitor = new DTaxIntegrationMonitor();
                dTaxIntegrationMonitor.sentAutoBphtb("");               
                Thread.sleep((long) (5 * 60000));//milisecond tiap 4 jam = 240 menit
               
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
        }
        System.out.println("stop .... ");
    }
}
