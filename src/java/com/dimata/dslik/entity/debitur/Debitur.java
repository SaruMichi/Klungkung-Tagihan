/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.debitur;

import com.dimata.qdep.entity.Entity;
import java.util.Date;

/**
 *
 * @author dimata005
 */
public class Debitur extends  Entity{
    
    /*individu*/
    private String flagDetail = "";
    private String cif = "";
    private String jenisIdentitas = "";
    private String nik = "";
    private String namaIdentitas = "";
    private String namaLengkap = "";
    private String kodeStatusGelar = "";
    private String jekel = "";
    private String tempatLahir = "";
    private Date tglLahir = null;
    private String npwp = "";
    private String alamat = "";
    private String kelurahan = "";
    private String kecamatan = "";
    private String kodeKab = "";
    private String kodePos = "";
    private String telepon = "";
    private String nomorHp = "";
    private String email = "";
    private String kodeDomisili = "";
    private String kodePekerjaan = "";
    private String tempatBekerja = "";
    private String kodeUsahaTempatBekerja = "";
    private String alamatTempatBekerja = "";
    private double penghasilanKotor = 0;
    private String kodePenghasilan = "";
    private int jmlTanggungan = 0;
    private String kodeHub = "";
    private String kodeGol = "";
    private String status = "";
    private String nikPasangan = "";
    private String namaPasangan = "";
    private Date tglLahirPasangan = null;
    private String perjanjianPisahHarga = "";
    private String melanggarBmpk = "";
    private String melampauiBmpk = "";
    private String namaIbuKandung = "";
    private String kodeKantorCabang = "";
    private String operasiData = "";
    
    /*badan usaha*/
    private String noIdentitas = "";
    private String namaBadanUsaha = "";
    private String kodeJenisUsaha = "";
    private String tempatPendirian = "";
    private String noAkte = "";
    private Date tglAktePendirian = null;
    private String noAktePerubahan = "";
    private Date tglAktePerubahan = null;
    private String kodeBidangUsaha = "";
    private String kodeHubLjk = "";
    private String goPublic = "";
    private String peringkat = "";
    private String lembagaPemeringkat = "";
    private Date tglPemeringkat = null;
    private String namaGroup = "";
    private Date openDate=null;
    private int kodeJenisNsb=0;
    private long periodeId = 0;
    private int statusPerubahanData=0;
    
    /**
     * @return the flagDetail
     */
    public String getFlagDetail() {
        return flagDetail;
    }

    /**
     * @param flagDetail the flagDetail to set
     */
    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
    }

    /**
     * @return the cif
     */
    public String getCif() {
        return cif;
    }

    /**
     * @param cif the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * @return the jenisIdentitas
     */
    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    /**
     * @param jenisIdentitas the jenisIdentitas to set
     */
    public void setJenisIdentitas(String jenisIdentitas) {
        this.jenisIdentitas = jenisIdentitas;
    }

    /**
     * @return the nik
     */
    public String getNik() {
        return nik;
    }

    /**
     * @param nik the nik to set
     */
    public void setNik(String nik) {
        this.nik = nik;
    }

    /**
     * @return the namaIdentitas
     */
    public String getNamaIdentitas() {
        return namaIdentitas;
    }

    /**
     * @param namaIdentitas the namaIdentitas to set
     */
    public void setNamaIdentitas(String namaIdentitas) {
        this.namaIdentitas = namaIdentitas;
    }

    /**
     * @return the namaLengkap
     */
    public String getNamaLengkap() {
        return namaLengkap;
    }

    /**
     * @param namaLengkap the namaLengkap to set
     */
    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    /**
     * @return the kodeStatusGelar
     */
    public String getKodeStatusGelar() {
        return kodeStatusGelar;
    }

    /**
     * @param kodeStatusGelar the kodeStatusGelar to set
     */
    public void setKodeStatusGelar(String kodeStatusGelar) {
        this.kodeStatusGelar = kodeStatusGelar;
    }

    /**
     * @return the jekel
     */
    public String getJekel() {
        return jekel;
    }

    /**
     * @param jekel the jekel to set
     */
    public void setJekel(String jekel) {
        this.jekel = jekel;
    }

    /**
     * @return the tempatLahir
     */
    public String getTempatLahir() {
        return tempatLahir;
    }

    /**
     * @param tempatLahir the tempatLahir to set
     */
    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    /**
     * @return the tglLahir
     */
    public Date getTglLahir() {
        return tglLahir;
    }

    /**
     * @param tglLahir the tglLahir to set
     */
    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    /**
     * @return the npwp
     */
    public String getNpwp() {
        return npwp;
    }

    /**
     * @param npwp the npwp to set
     */
    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @return the kelurahan
     */
    public String getKelurahan() {
        return kelurahan;
    }

    /**
     * @param kelurahan the kelurahan to set
     */
    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    /**
     * @return the kecamatan
     */
    public String getKecamatan() {
        return kecamatan;
    }

    /**
     * @param kecamatan the kecamatan to set
     */
    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    /**
     * @return the kodeKab
     */
    public String getKodeKab() {
        return kodeKab;
    }

    /**
     * @param kodeKab the kodeKab to set
     */
    public void setKodeKab(String kodeKab) {
        this.kodeKab = kodeKab;
    }

    /**
     * @return the kodePos
     */
    public String getKodePos() {
        return kodePos;
    }

    /**
     * @param kodePos the kodePos to set
     */
    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    /**
     * @return the telepon
     */
    public String getTelepon() {
        return telepon;
    }

    /**
     * @param telepon the telepon to set
     */
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    /**
     * @return the nomorHp
     */
    public String getNomorHp() {
        return nomorHp;
    }

    /**
     * @param nomorHp the nomorHp to set
     */
    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the kodeDomisili
     */
    public String getKodeDomisili() {
        return kodeDomisili;
    }

    /**
     * @param kodeDomisili the kodeDomisili to set
     */
    public void setKodeDomisili(String kodeDomisili) {
        this.kodeDomisili = kodeDomisili;
    }

    /**
     * @return the kodePekerjaan
     */
    public String getKodePekerjaan() {
        return kodePekerjaan;
    }

    /**
     * @param kodePekerjaan the kodePekerjaan to set
     */
    public void setKodePekerjaan(String kodePekerjaan) {
        this.kodePekerjaan = kodePekerjaan;
    }

    /**
     * @return the tempatBekerja
     */
    public String getTempatBekerja() {
        return tempatBekerja;
    }

    /**
     * @param tempatBekerja the tempatBekerja to set
     */
    public void setTempatBekerja(String tempatBekerja) {
        this.tempatBekerja = tempatBekerja;
    }

    /**
     * @return the kodeUsahaTempatBekerja
     */
    public String getKodeUsahaTempatBekerja() {
        return kodeUsahaTempatBekerja;
    }

    /**
     * @param kodeUsahaTempatBekerja the kodeUsahaTempatBekerja to set
     */
    public void setKodeUsahaTempatBekerja(String kodeUsahaTempatBekerja) {
        this.kodeUsahaTempatBekerja = kodeUsahaTempatBekerja;
    }

    /**
     * @return the alamatTempatBekerja
     */
    public String getAlamatTempatBekerja() {
        return alamatTempatBekerja;
    }

    /**
     * @param alamatTempatBekerja the alamatTempatBekerja to set
     */
    public void setAlamatTempatBekerja(String alamatTempatBekerja) {
        this.alamatTempatBekerja = alamatTempatBekerja;
    }

    /**
     * @return the penghasilanKotor
     */
    public double getPenghasilanKotor() {
        return penghasilanKotor;
    }

    /**
     * @param penghasilanKotor the penghasilanKotor to set
     */
    public void setPenghasilanKotor(double penghasilanKotor) {
        this.penghasilanKotor = penghasilanKotor;
    }

    /**
     * @return the kodePenghasilan
     */
    public String getKodePenghasilan() {
        return kodePenghasilan;
    }

    /**
     * @param kodePenghasilan the kodePenghasilan to set
     */
    public void setKodePenghasilan(String kodePenghasilan) {
        this.kodePenghasilan = kodePenghasilan;
    }

    /**
     * @return the jmlTanggungan
     */
    public int getJmlTanggungan() {
        return jmlTanggungan;
    }

    /**
     * @param jmlTanggungan the jmlTanggungan to set
     */
    public void setJmlTanggungan(int jmlTanggungan) {
        this.jmlTanggungan = jmlTanggungan;
    }

    /**
     * @return the kodeHub
     */
    public String getKodeHub() {
        return kodeHub;
    }

    /**
     * @param kodeHub the kodeHub to set
     */
    public void setKodeHub(String kodeHub) {
        this.kodeHub = kodeHub;
    }

    /**
     * @return the kodeGol
     */
    public String getKodeGol() {
        return kodeGol;
    }

    /**
     * @param kodeGol the kodeGol to set
     */
    public void setKodeGol(String kodeGol) {
        this.kodeGol = kodeGol;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the nikPasangan
     */
    public String getNikPasangan() {
        return nikPasangan;
    }

    /**
     * @param nikPasangan the nikPasangan to set
     */
    public void setNikPasangan(String nikPasangan) {
        this.nikPasangan = nikPasangan;
    }

    /**
     * @return the namaPasangan
     */
    public String getNamaPasangan() {
        return namaPasangan;
    }

    /**
     * @param namaPasangan the namaPasangan to set
     */
    public void setNamaPasangan(String namaPasangan) {
        this.namaPasangan = namaPasangan;
    }

    /**
     * @return the tglLahirPasangan
     */
    public Date getTglLahirPasangan() {
        return tglLahirPasangan;
    }

    /**
     * @param tglLahirPasangan the tglLahirPasangan to set
     */
    public void setTglLahirPasangan(Date tglLahirPasangan) {
        this.tglLahirPasangan = tglLahirPasangan;
    }

    /**
     * @return the perjanjianPisahHarga
     */
    public String getPerjanjianPisahHarga() {
        return perjanjianPisahHarga;
    }

    /**
     * @param perjanjianPisahHarga the perjanjianPisahHarga to set
     */
    public void setPerjanjianPisahHarga(String perjanjianPisahHarga) {
        this.perjanjianPisahHarga = perjanjianPisahHarga;
    }

    /**
     * @return the melanggarBmpk
     */
    public String getMelanggarBmpk() {
        return melanggarBmpk;
    }

    /**
     * @param melanggarBmpk the melanggarBmpk to set
     */
    public void setMelanggarBmpk(String melanggarBmpk) {
        this.melanggarBmpk = melanggarBmpk;
    }

    /**
     * @return the melampauiBmpk
     */
    public String getMelampauiBmpk() {
        return melampauiBmpk;
    }

    /**
     * @param melampauiBmpk the melampauiBmpk to set
     */
    public void setMelampauiBmpk(String melampauiBmpk) {
        this.melampauiBmpk = melampauiBmpk;
    }

    /**
     * @return the namaIbuKandung
     */
    public String getNamaIbuKandung() {
        return namaIbuKandung;
    }

    /**
     * @param namaIbuKandung the namaIbuKandung to set
     */
    public void setNamaIbuKandung(String namaIbuKandung) {
        this.namaIbuKandung = namaIbuKandung;
    }

    /**
     * @return the kodeKantorCabang
     */
    public String getKodeKantorCabang() {
        return kodeKantorCabang;
    }

    /**
     * @param kodeKantorCabang the kodeKantorCabang to set
     */
    public void setKodeKantorCabang(String kodeKantorCabang) {
        this.kodeKantorCabang = kodeKantorCabang;
    }

    /**
     * @return the operasiData
     */
    public String getOperasiData() {
        return operasiData;
    }

    /**
     * @param operasiData the operasiData to set
     */
    public void setOperasiData(String operasiData) {
        this.operasiData = operasiData;
    }

    /**
     * @return the noIdentitas
     */
    public String getNoIdentitas() {
        return noIdentitas;
    }

    /**
     * @param noIdentitas the noIdentitas to set
     */
    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    /**
     * @return the namaBadanUsaha
     */
    public String getNamaBadanUsaha() {
        return namaBadanUsaha;
    }

    /**
     * @param namaBadanUsaha the namaBadanUsaha to set
     */
    public void setNamaBadanUsaha(String namaBadanUsaha) {
        this.namaBadanUsaha = namaBadanUsaha;
    }

    /**
     * @return the kodeJenisUsaha
     */
    public String getKodeJenisUsaha() {
        return kodeJenisUsaha;
    }

    /**
     * @param kodeJenisUsaha the kodeJenisUsaha to set
     */
    public void setKodeJenisUsaha(String kodeJenisUsaha) {
        this.kodeJenisUsaha = kodeJenisUsaha;
    }

    /**
     * @return the tempatPendirian
     */
    public String getTempatPendirian() {
        return tempatPendirian;
    }

    /**
     * @param tempatPendirian the tempatPendirian to set
     */
    public void setTempatPendirian(String tempatPendirian) {
        this.tempatPendirian = tempatPendirian;
    }

    /**
     * @return the noAkte
     */
    public String getNoAkte() {
        return noAkte;
    }

    /**
     * @param noAkte the noAkte to set
     */
    public void setNoAkte(String noAkte) {
        this.noAkte = noAkte;
    }

    /**
     * @return the tglAktePendirian
     */
    public Date getTglAktePendirian() {
        return tglAktePendirian;
    }

    /**
     * @param tglAktePendirian the tglAktePendirian to set
     */
    public void setTglAktePendirian(Date tglAktePendirian) {
        this.tglAktePendirian = tglAktePendirian;
    }

    /**
     * @return the noAktePerubahan
     */
    public String getNoAktePerubahan() {
        return noAktePerubahan;
    }

    /**
     * @param noAktePerubahan the noAktePerubahan to set
     */
    public void setNoAktePerubahan(String noAktePerubahan) {
        this.noAktePerubahan = noAktePerubahan;
    }

    /**
     * @return the tglAktePerubahan
     */
    public Date getTglAktePerubahan() {
        return tglAktePerubahan;
    }

    /**
     * @param tglAktePerubahan the tglAktePerubahan to set
     */
    public void setTglAktePerubahan(Date tglAktePerubahan) {
        this.tglAktePerubahan = tglAktePerubahan;
    }

    /**
     * @return the kodeBidangUsaha
     */
    public String getKodeBidangUsaha() {
        return kodeBidangUsaha;
    }

    /**
     * @param kodeBidangUsaha the kodeBidangUsaha to set
     */
    public void setKodeBidangUsaha(String kodeBidangUsaha) {
        this.kodeBidangUsaha = kodeBidangUsaha;
    }

    /**
     * @return the kodeHubLjk
     */
    public String getKodeHubLjk() {
        return kodeHubLjk;
    }

    /**
     * @param kodeHubLjk the kodeHubLjk to set
     */
    public void setKodeHubLjk(String kodeHubLjk) {
        this.kodeHubLjk = kodeHubLjk;
    }

    /**
     * @return the goPublic
     */
    public String getGoPublic() {
        return goPublic;
    }

    /**
     * @param goPublic the goPublic to set
     */
    public void setGoPublic(String goPublic) {
        this.goPublic = goPublic;
    }

    /**
     * @return the peringkat
     */
    public String getPeringkat() {
        return peringkat;
    }

    /**
     * @param peringkat the peringkat to set
     */
    public void setPeringkat(String peringkat) {
        this.peringkat = peringkat;
    }

    /**
     * @return the lembagaPemeringkat
     */
    public String getLembagaPemeringkat() {
        return lembagaPemeringkat;
    }

    /**
     * @param lembagaPemeringkat the lembagaPemeringkat to set
     */
    public void setLembagaPemeringkat(String lembagaPemeringkat) {
        this.lembagaPemeringkat = lembagaPemeringkat;
    }

    /**
     * @return the tglPemeringkat
     */
    public Date getTglPemeringkat() {
        return tglPemeringkat;
    }

    /**
     * @param tglPemeringkat the tglPemeringkat to set
     */
    public void setTglPemeringkat(Date tglPemeringkat) {
        this.tglPemeringkat = tglPemeringkat;
    }

    /**
     * @return the namaGroup
     */
    public String getNamaGroup() {
        return namaGroup;
    }

    /**
     * @param namaGroup the namaGroup to set
     */
    public void setNamaGroup(String namaGroup) {
        this.namaGroup = namaGroup;
    }

    /**
     * @return the openDate
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * @param openDate the openDate to set
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * @return the kodeJenisNsb
     */
    public int getKodeJenisNsb() {
        return kodeJenisNsb;
    }

    /**
     * @param kodeJenisNsb the kodeJenisNsb to set
     */
    public void setKodeJenisNsb(int kodeJenisNsb) {
        this.kodeJenisNsb = kodeJenisNsb;
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

    /**
     * @return the statusPerubahanData
     */
    public int getStatusPerubahanData() {
        return statusPerubahanData;
    }

    /**
     * @param statusPerubahanData the statusPerubahanData to set
     */
    public void setStatusPerubahanData(int statusPerubahanData) {
        this.statusPerubahanData = statusPerubahanData;
    }
    
}
