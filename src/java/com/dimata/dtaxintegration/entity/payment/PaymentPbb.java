/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.entity.payment;

/**
 *
 * @author dimata005
 */
import com.dimata.qdep.entity.Entity;
import java.util.Date;

public class PaymentPbb extends Entity {

    private String kdPropinsi = "";
    private String kdDati2 = "";
    private String kdKecamata = "";
    private String kdKelurahan = "";
    private String kdBlok = "";
    private String noUrut = "";
    private String kdJnsOp = "";
    private String thnPajakSppt = "";
    private double pembayaranSpptKe = 0;
    private String kdKanwilBank = "";
    private String kdKppbbBank = "";
    private String kdBankTunggal = "";
    private String kdBankPersepsi = "";
    private String kdTp = "";
    private double dendaSppt = 0;
    private double jmlSpptYgDibayar = 0;
    private Date tglPembayaranSppt = new Date();
    private Date tglRekamByrSppt = new Date();
    private String nipRekamByrSppt = "";
    private long idPaymentBank=0;
    private long nop=0;
    private int status=0;
    private String idKey="";
    
    //update opie-eyek
    private String nama="";
    private String alamat="";
    private String letakObject="";
    private String tanggalJatuhTempo="";
    private double luasBumi=0.0;
    private double luasBng=0.0;
    
    private double njopBumi=0.0;
    private double njopBng=0.0;
    private double njoptkp=0.0;
    private String nopIpprotax="";
    
    public String getKdPropinsi() {
        return kdPropinsi;
    }

    public void setKdPropinsi(String kdPropinsi) {
        this.kdPropinsi = kdPropinsi;
    }

    public String getKdDati2() {
        return kdDati2;
    }

    public void setKdDati2(String kdDati2) {
        this.kdDati2 = kdDati2;
    }

    public String getKdKecamata() {
        return kdKecamata;
    }

    public void setKdKecamata(String kdKecamata) {
        this.kdKecamata = kdKecamata;
    }

    public String getKdKelurahan() {
        return kdKelurahan;
    }

    public void setKdKelurahan(String kdKelurahan) {
        this.kdKelurahan = kdKelurahan;
    }

    public String getKdBlok() {
        return kdBlok;
    }

    public void setKdBlok(String kdBlok) {
        this.kdBlok = kdBlok;
    }

    public String getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(String noUrut) {
        this.noUrut = noUrut;
    }

    public String getKdJnsOp() {
        return kdJnsOp;
    }

    public void setKdJnsOp(String kdJnsOp) {
        this.kdJnsOp = kdJnsOp;
    }

    public String getThnPajakSppt() {
        return thnPajakSppt;
    }

    public void setThnPajakSppt(String thnPajakSppt) {
        this.thnPajakSppt = thnPajakSppt;
    }

    public double getPembayaranSpptKe() {
        return pembayaranSpptKe;
    }

    public void setPembayaranSpptKe(double pembayaranSpptKe) {
        this.pembayaranSpptKe = pembayaranSpptKe;
    }

    public String getKdKanwilBank() {
        return kdKanwilBank;
    }

    public void setKdKanwilBank(String kdKanwilBank) {
        this.kdKanwilBank = kdKanwilBank;
    }

    public String getKdKppbbBank() {
        return kdKppbbBank;
    }

    public void setKdKppbbBank(String kdKppbbBank) {
        this.kdKppbbBank = kdKppbbBank;
    }

    public String getKdBankTunggal() {
        return kdBankTunggal;
    }

    public void setKdBankTunggal(String kdBankTunggal) {
        this.kdBankTunggal = kdBankTunggal;
    }

    public String getKdBankPersepsi() {
        return kdBankPersepsi;
    }

    public void setKdBankPersepsi(String kdBankPersepsi) {
        this.kdBankPersepsi = kdBankPersepsi;
    }

    public String getKdTp() {
        return kdTp;
    }

    public void setKdTp(String kdTp) {
        this.kdTp = kdTp;
    }

    public double getDendaSppt() {
        return dendaSppt;
    }

    public void setDendaSppt(double dendaSppt) {
        this.dendaSppt = dendaSppt;
    }

    public double getJmlSpptYgDibayar() {
        return jmlSpptYgDibayar;
    }

    public void setJmlSpptYgDibayar(double jmlSpptYgDibayar) {
        this.jmlSpptYgDibayar = jmlSpptYgDibayar;
    }

    public Date getTglPembayaranSppt() {
        return tglPembayaranSppt;
    }

    public void setTglPembayaranSppt(Date tglPembayaranSppt) {
        this.tglPembayaranSppt = tglPembayaranSppt;
    }

    public Date getTglRekamByrSppt() {
        return tglRekamByrSppt;
    }

    public void setTglRekamByrSppt(Date tglRekamByrSppt) {
        this.tglRekamByrSppt = tglRekamByrSppt;
    }

    public String getNipRekamByrSppt() {
        return nipRekamByrSppt;
    }

    public void setNipRekamByrSppt(String nipRekamByrSppt) {
        this.nipRekamByrSppt = nipRekamByrSppt;
    }

    /**
     * @return the idPaymentBank
     */
    public long getIdPaymentBank() {
        return idPaymentBank;
    }

    /**
     * @param idPaymentBank the idPaymentBank to set
     */
    public void setIdPaymentBank(long idPaymentBank) {
        this.idPaymentBank = idPaymentBank;
    }

    /**
     * @return the nop
     */
    public long getNop() {
        return nop;
    }

    /**
     * @param nop the nop to set
     */
    public void setNop(long nop) {
        this.nop = nop;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the idKey
     */
    public String getIdKey() {
        return idKey;
    }

    /**
     * @param idKey the idKey to set
     */
    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
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
     * @return the letakObject
     */
    public String getLetakObject() {
        return letakObject;
    }

    /**
     * @param letakObject the letakObject to set
     */
    public void setLetakObject(String letakObject) {
        this.letakObject = letakObject;
    }

    /**
     * @return the tanggalJatuhTempo
     */
    public String getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    /**
     * @param tanggalJatuhTempo the tanggalJatuhTempo to set
     */
    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        this.tanggalJatuhTempo = tanggalJatuhTempo;
    }

    /**
     * @return the luasBumi
     */
    public double getLuasBumi() {
        return luasBumi;
    }

    /**
     * @param luasBumi the luasBumi to set
     */
    public void setLuasBumi(double luasBumi) {
        this.luasBumi = luasBumi;
    }

    /**
     * @return the luasBng
     */
    public double getLuasBng() {
        return luasBng;
    }

    /**
     * @param luasBng the luasBng to set
     */
    public void setLuasBng(double luasBng) {
        this.luasBng = luasBng;
    }

    /**
     * @return the njopBumi
     */
    public double getNjopBumi() {
        return njopBumi;
    }

    /**
     * @param njopBumi the njopBumi to set
     */
    public void setNjopBumi(double njopBumi) {
        this.njopBumi = njopBumi;
    }

    /**
     * @return the njopBng
     */
    public double getNjopBng() {
        return njopBng;
    }

    /**
     * @param njopBng the njopBng to set
     */
    public void setNjopBng(double njopBng) {
        this.njopBng = njopBng;
    }

    /**
     * @return the njoptkp
     */
    public double getNjoptkp() {
        return njoptkp;
    }

    /**
     * @param njoptkp the njoptkp to set
     */
    public void setNjoptkp(double njoptkp) {
        this.njoptkp = njoptkp;
    }

    /**
     * @return the nopIpprotax
     */
    public String getNopIpprotax() {
        return nopIpprotax;
    }

    /**
     * @param nopIpprotax the nopIpprotax to set
     */
    public void setNopIpprotax(String nopIpprotax) {
        this.nopIpprotax = nopIpprotax;
    }
}