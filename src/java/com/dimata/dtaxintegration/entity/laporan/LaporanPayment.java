/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.entity.laporan;

/**
 *
 * @author dimata005
 */
public class LaporanPayment {

    /**
     * @return the sNoBuktiBank
     */
    public String getsNoBuktiBank() {
        return sNoBuktiBank;
    }

    /**
     * @param sNoBuktiBank the sNoBuktiBank to set
     */
    public void setsNoBuktiBank(String sNoBuktiBank) {
        this.sNoBuktiBank = sNoBuktiBank;
    }

    private String sUser = "";
    private String sPassword = "";
    private String sInstansi = "";
    private String sDate = "";
    private String sNoId="";
    private String sNoBuktiBank="";
    /**
     * @return the sUser
     */
    public String getsUser() {
        return sUser;
    }

    /**
     * @param sUser the sUser to set
     */
    public void setsUser(String sUser) {
        this.sUser = sUser;
    }

    /**
     * @return the sPassword
     */
    public String getsPassword() {
        return sPassword;
    }

    /**
     * @param sPassword the sPassword to set
     */
    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    /**
     * @return the sInstansi
     */
    public String getsInstansi() {
        return sInstansi;
    }

    /**
     * @param sInstansi the sInstansi to set
     */
    public void setsInstansi(String sInstansi) {
        this.sInstansi = sInstansi;
    }

    /**
     * @return the sDate
     */
    public String getsDate() {
        return sDate;
    }

    /**
     * @param sDate the sDate to set
     */
    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    /**
     * @return the sNoId
     */
    public String getsNoId() {
        return sNoId;
    }

    /**
     * @param sNoId the sNoId to set
     */
    public void setsNoId(String sNoId) {
        this.sNoId = sNoId;
    }
}
