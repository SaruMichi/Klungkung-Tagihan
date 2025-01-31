/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.entity.esptpd;

import com.dimata.qdep.entity.Entity;
import java.util.Date;

/**
 *
 * @author Ardiadi
 */
public class ESPTPD extends Entity {

private String eNPWPD = "";
private Date eTglInput = null;
private String eMasaPajak = "";
private String eTahunPajak = "";
private Date eTglRekam = null;
private String eNIPRekam = "";
private String eIdRekam = "";
private Date eTglUbah = null;
private String eNIPUbah = "";
private String eIDUbah = "";
private String eJumlahOmzet = "";
private double eTarif = 0;
private double eJumlahPajak = 0;
private double eServiceTax = 0;
private double eDenda = 0;
private double ePengurangan = 0;
private double eHarusBayar = 0;
private String eKeterangan = "";
private String eKodePajak = "";
private String eNoRekening = "";
private String eNoSubrekening = "";
private String eKodeLokasi = "";
private String eNoSPTPD = "";
private String eNoSSPD = "";
private String namaBadan="";
private double eJumlahService=0;

private String uploadDocument="";

public String getENPWPD(){
return eNPWPD;
}

public void setENPWPD(String eNPWPD){
this.eNPWPD = eNPWPD;
}

public Date getETglInput(){
return eTglInput;
}

public void setETglInput(Date eTglInput){
this.eTglInput = eTglInput;
}

public String getEMasaPajak(){
return eMasaPajak;
}

public void setEMasaPajak(String eMasaPajak){
this.eMasaPajak = eMasaPajak;
}

public String getETahunPajak(){
return eTahunPajak;
}

public void setETahunPajak(String eTahunPajak){
this.eTahunPajak = eTahunPajak;
}

public Date getETglRekam(){
return eTglRekam;
}

public void setETglRekam(Date eTglRekam){
this.eTglRekam = eTglRekam;
}

public String getENIPRekam(){
return eNIPRekam;
}

public void setENIPRekam(String eNIPRekam){
this.eNIPRekam = eNIPRekam;
}

public String getEIdRekam(){
return eIdRekam;
}

public void setEIdRekam(String eIdRekam){
this.eIdRekam = eIdRekam;
}

public Date getETglUbah(){
return eTglUbah;
}

public void setETglUbah(Date eTglUbah){
this.eTglUbah = eTglUbah;
}

public String getENIPUbah(){
return eNIPUbah;
}

public void setENIPUbah(String eNIPUbah){
this.eNIPUbah = eNIPUbah;
}

public String getEIDUbah(){
return eIDUbah;
}

public void setEIDUbah(String eIDUbah){
this.eIDUbah = eIDUbah;
}

public String getEJumlahOmzet(){
return eJumlahOmzet;
}

public void setEJumlahOmzet(String eJumlahOmzet){
this.eJumlahOmzet = eJumlahOmzet;
}

public double getETarif(){
return eTarif;
}

public void setETarif(double eTarif){
this.eTarif = eTarif;
}

public double getEJumlahPajak(){
return eJumlahPajak;
}

public void setEJumlahPajak(double eJumlahPajak){
this.eJumlahPajak = eJumlahPajak;
}

public double getEServiceTax(){
return eServiceTax;
}

public void setEServiceTax(double eServiceTax){
this.eServiceTax = eServiceTax;
}

public double getEDenda(){
return eDenda;
}

public void setEDenda(double eDenda){
this.eDenda = eDenda;
}

public double getEPengurangan(){
return ePengurangan;
}

public void setEPengurangan(double ePengurangan){
this.ePengurangan = ePengurangan;
}

public double getEHarusBayar(){
return eHarusBayar;
}

public void setEHarusBayar(double eHarusBayar){
this.eHarusBayar = eHarusBayar;
}

public String getEKeterangan(){
return eKeterangan;
}

public void setEKeterangan(String eKeterangan){
this.eKeterangan = eKeterangan;
}

public String getEKodePajak(){
return eKodePajak;
}

public void setEKodePajak(String eKodePajak){
this.eKodePajak = eKodePajak;
}

public String getENoRekening(){
return eNoRekening;
}

public void setENoRekening(String eNoRekening){
this.eNoRekening = eNoRekening;
}

public String getENoSubrekening(){
return eNoSubrekening;
}

public void setENoSubrekening(String eNoSubrekening){
this.eNoSubrekening = eNoSubrekening;
}

public String getEKodeLokasi(){
return eKodeLokasi;
}

public void setEKodeLokasi(String eKodeLokasi){
this.eKodeLokasi = eKodeLokasi;
}

public String getENoSPTPD(){
    if(eNoSPTPD==null|| eNoSPTPD.equals("")){
        eNoSPTPD="";
    }
    return eNoSPTPD;
}

public void setENoSPTPD(String eNoSPTPD){
    this.eNoSPTPD = eNoSPTPD;
}

public String getENoSSPD(){
return eNoSSPD;
}

public void setENoSSPD(String eNoSSPD){
this.eNoSSPD = eNoSSPD;
}

    /**
     * @return the namaBadan
     */
    public String getNamaBadan() {
        return namaBadan;
    }

    /**
     * @param namaBadan the namaBadan to set
     */
    public void setNamaBadan(String namaBadan) {
        this.namaBadan = namaBadan;
    }

    /**
     * @return the eJumlahService
     */
    public double geteJumlahService() {
        return eJumlahService;
    }

    /**
     * @param eJumlahService the eJumlahService to set
     */
    public void seteJumlahService(double eJumlahService) {
        this.eJumlahService = eJumlahService;
    }

    /**
     * @return the uploadDocument
     */
    public String getUploadDocument() {
        return uploadDocument;
}

    /**
     * @param uploadDocument the uploadDocument to set
     */
    public void setUploadDocument(String uploadDocument) {
        this.uploadDocument = uploadDocument;
    }

}
