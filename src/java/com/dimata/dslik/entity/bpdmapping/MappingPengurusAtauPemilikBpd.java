/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.bpdmapping;

/**
 *
 * @author dimata005
 */
import com.dimata.qdep.entity.Entity;

public class MappingPengurusAtauPemilikBpd extends Entity {

    private String flagDetail = "";
    private String nomorIdentitas = "";
    private String cif = "";
    private String jenisIdentitas = "";
    private String namaPengurus = "";
    private String jenisKelamin = "";
    private String alamat = "";
    private String kelurahan = "";
    private String kecamatan = "";
    private String kdKabupaten = "";
    private String pangsa = "";
    private String kdCabang = "";
    private long periodeId=0;
    
    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
    }

    public String getNomorIdentitas() {
        return nomorIdentitas;
    }

    public void setNomorIdentitas(String nomorIdentitas) {
        this.nomorIdentitas = nomorIdentitas;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    public void setJenisIdentitas(String jenisIdentitas) {
        this.jenisIdentitas = jenisIdentitas;
    }

    public String getNamaPengurus() {
        return namaPengurus;
    }

    public void setNamaPengurus(String namaPengurus) {
        this.namaPengurus = namaPengurus;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKdKabupaten() {
        return kdKabupaten;
    }

    public void setKdKabupaten(String kdKabupaten) {
        this.kdKabupaten = kdKabupaten;
    }

    public String getPangsa() {
        return pangsa;
    }

    public void setPangsa(String pangsa) {
        this.pangsa = pangsa;
    }

    public String getKdCabang() {
        return kdCabang;
    }

    public void setKdCabang(String kdCabang) {
        this.kdCabang = kdCabang;
    }

    /**
     * @return the periodeId
     */
    public long getPeriodeId() {
        return periodeId;
    }

    /**
     * @param periodeId the periodeId to set
     */
    public void setPeriodeId(long periodeId) {
        this.periodeId = periodeId;
    }

}
