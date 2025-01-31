/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.masterdata;

import com.dimata.qdep.entity.Entity;

/**
 *
 * @author Dewa
 */
public class CabangBank extends Entity {

    private long bankId = 0;
    private String kodeCabang = "";
    private String namaCabang = "";
    private String alamatCabang = "";
    private String emailCabang = "";
    private String faxCabang = "";
    private String namaBank = "";
    private String namaKota="";
    private int noUrut=0;
    private String kodeParentCabang="";
    
    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getAlamatCabang() {
        return alamatCabang;
    }

    public void setAlamatCabang(String alamatCabang) {
        this.alamatCabang = alamatCabang;
    }

    public String getEmailCabang() {
        return emailCabang;
    }

    public void setEmailCabang(String emailCabang) {
        this.emailCabang = emailCabang;
    }

    public String getFaxCabang() {
        return faxCabang;
    }

    public void setFaxCabang(String faxCabang) {
        this.faxCabang = faxCabang;
    }

    /**
     * @return the namaKota
     */
    public String getNamaKota() {
        return namaKota;
    }

    /**
     * @param namaKota the namaKota to set
     */
    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    /**
     * @return the noUrut
     */
    public int getNoUrut() {
        return noUrut;
    }

    /**
     * @param noUrut the noUrut to set
     */
    public void setNoUrut(int noUrut) {
        this.noUrut = noUrut;
    }

    /**
     * @return the kodeParentCabang
     */
    public String getKodeParentCabang() {
        return kodeParentCabang;
    }

    /**
     * @param kodeParentCabang the kodeParentCabang to set
     */
    public void setKodeParentCabang(String kodeParentCabang) {
        this.kodeParentCabang = kodeParentCabang;
    }

}
