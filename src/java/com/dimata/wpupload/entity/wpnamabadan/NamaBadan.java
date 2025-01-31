/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.entity.wpnamabadan;

import com.dimata.qdep.entity.Entity;

/**
 *
 * @author Ardiadi
 */
public class NamaBadan extends Entity{
    private String nbNPWPD = "";
    private String nbNama = "";
    private String nbAlamat = "";
    private String nbRT = "";
    private String nbRW = "";
    private String nbKelurahan = "";
    private String nbKecamatan = "";
    private String nbKabupaten = "";
    private String nbNamaBadan = "";
    private String nbJenisUsaha = "";
    private String nbAlamatUsaha = "";
    private String nbKDJenisUsaha = "";
    private String nbKDJenisWP = "";
    private String nbSubjenisWP = "";
    private String nbKDLokasi = "";
    private double nbTarif = 0;
    private String nbUserId = "";
    private String nbNip = "";
    private String nUserName = "";
    private String nNoTelepon ="";
    private String noSubRekening="";
    private String namaJenisWP="";
    private String kdLokasi="";
    
    public String getNbNPWPD(){
	return nbNPWPD;
    }

    public void setNbNPWPD(String nbNPWPD){
	this.nbNPWPD = nbNPWPD;
    }

    public String getNbNama(){
	return nbNama;
    }

    public void setNbNama(String nbNama){
	this.nbNama = nbNama;
    }

    public String getNbAlamat(){
	return nbAlamat;
    }

    public void setNbAlamat(String nbAlamat){
	this.nbAlamat = nbAlamat;
    }

    public String getNbRT(){
	return nbRT;
    }

    public void setNbRT(String nbRT){
	this.nbRT = nbRT;
    }

    public String getNbRW(){
	return nbRW;
    }

    public void setNbRW(String nbRW){
	this.nbRW = nbRW;
    }

    public String getNbKelurahan(){
	return nbKelurahan;
    }

    public void setNbKelurahan(String nbKelurahan){
	this.nbKelurahan = nbKelurahan;
    }

    public String getNbKecamatan(){
	return nbKecamatan;
    }

    public void setNbKecamatan(String nbKecamatan){
	this.nbKecamatan = nbKecamatan;
    }

    public String getNbKabupaten(){
	return nbKabupaten;
    }

    public void setNbKabupaten(String nbKabupaten){
	this.nbKabupaten = nbKabupaten;
    }

    public String getNbNamaBadan(){
	return nbNamaBadan;
    }

    public void setNbNamaBadan(String nbNamaBadan){
	this.nbNamaBadan = nbNamaBadan;
    }

    public String getNbJenisUsaha(){
	return nbJenisUsaha;
    }

    public void setNbJenisUsaha(String nbJenisUsaha){
	this.nbJenisUsaha = nbJenisUsaha;
    }

    public String getNbAlamatUsaha(){
	return nbAlamatUsaha;
    }

    public void setNbAlamatUsaha(String nbAlamatUsaha){
	this.nbAlamatUsaha = nbAlamatUsaha;
    }

    public String getNbKDJenisUsaha(){
	return nbKDJenisUsaha;
    }

    public void setNbKDJenisUsaha(String nbKDJenisUsaha){
	this.nbKDJenisUsaha = nbKDJenisUsaha;
    }

    public String getNbKDJenisWP(){
	return nbKDJenisWP;
    }

    public void setNbKDJenisWP(String nbKDJenisWP){
	this.nbKDJenisWP = nbKDJenisWP;
    }

    public String getNbSubjenisWP(){
	return nbSubjenisWP;
    }

    public void setNbSubjenisWP(String nbSubjenisWP){
	this.nbSubjenisWP = nbSubjenisWP;
    }

    public String getNbKDLokasi(){
	return nbKDLokasi;
    }

    public void setNbKDLokasi(String nbKDLokasi){
	this.nbKDLokasi = nbKDLokasi;
    }

    /**
     * @return the nbTarif
     */
    public double getNbTarif() {
	return nbTarif;
    }

    /**
     * @param nbTarif the nbTarif to set
     */
    public void setNbTarif(double nbTarif) {
	this.nbTarif = nbTarif;
    }

    /**
     * @return the nbUserId
     */
    public String getNbUserId() {
	return nbUserId;
    }

    /**
     * @param nbUserId the nbUserId to set
     */
    public void setNbUserId(String nbUserId) {
	this.nbUserId = nbUserId;
    }

    /**
     * @return the nbNip
     */
    public String getNbNip() {
	return nbNip;
    }

    /**
     * @param nbNip the nbNip to set
     */
    public void setNbNip(String nbNip) {
	this.nbNip = nbNip;
    }

    /**
     * @return the nUserName
     */
    public String getnUserName() {
        return nUserName;
    }

    /**
     * @param nUserName the nUserName to set
     */
    public void setnUserName(String nUserName) {
        this.nUserName = nUserName;
    }
    
    public void setnNoTelepon(String nNoTelepon){
        if(nNoTelepon!=null){
             this.nNoTelepon = nNoTelepon;
        }else{
            this.nNoTelepon = "-";
        }
    }
    
    public String getnNoTelepon(){
        return nNoTelepon;
    }

    /**
     * @return the noSubRekening
     */
    public String getNoSubRekening() {
        return noSubRekening;
    }

    /**
     * @param noSubRekening the noSubRekening to set
     */
    public void setNoSubRekening(String noSubRekening) {
        this.noSubRekening = noSubRekening;
    }

    /**
     * @return the namaJenisWP
     */
    public String getNamaJenisWP() {
        return namaJenisWP;
    }

    /**
     * @param namaJenisWP the namaJenisWP to set
     */
    public void setNamaJenisWP(String namaJenisWP) {
        this.namaJenisWP = namaJenisWP;
    }

    /**
     * @return the kdLokasi
     */
    public String getKdLokasi() {
        if(kdLokasi==null){
            return "";
        }
        return kdLokasi;
    }

    /**
     * @param kdLokasi the kdLokasi to set
     */
    public void setKdLokasi(String kdLokasi) {
        this.kdLokasi = kdLokasi;
    }
    
    
}
