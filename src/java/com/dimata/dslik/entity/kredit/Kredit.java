/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.kredit;

import com.dimata.common.entity.logger.I_LogHistory;
import java.util.Date;
import com.dimata.qdep.entity.Entity;
import com.dimata.util.Formater;

/**
 *
 * @author m20n9
 */
public class Kredit extends Entity implements I_LogHistory {

    private String flagDetail = "";
    private String noRekening = "";
    private String cif = "";
    private String kodeSifat = "";
    private String kodeJenisKredit = "";
    private String kodeAkad = "";
    private String noAkadAwal = "";
    private Date tglAkadAwal = null;
    private String noAkadAkhir = "";
    private Date tglAkadAkhir = null;
    private int baruPerpanjangan = 0;
    private Date tglAwal = null;
    private Date tglMulai = null;
    private Date tglTempo = null;
    private String kodeKatDbitur = "";
    private String kodeJenisPenggunaan = "";
    private String kodeOrientasiPenggunaan = "";
    private String kodeSektorEkonomi = "";
    private String kodeKab = "";
    private double nilai = 0;
    private String kodeValuta = "";
    private int prosentaseBunga = 0;
    private String jenisBunga = "";
    private String kreditPemerintah = "";
    private String takeover = "";
    private String sumberDana = "";
    private double plafonAwal = 0;
    private double plafon = 0;
    private double realisasi = 0;
    private double denda = 0;
    private double bakiDebet = 0;
    private double nilaiUangAsal = 0;
    private String kodeKolektibilitas = "";
    private Date tglMacet = null;
    private String kodeSebabMacet = "";
    private double tunggakanPokok = 0;
    private double tunggakanBunga = 0;
    private int jmlHariTunggakan = 0;
    private int frekuensiTunggakan = 0;
    private int frekuensiRestrukturisasi = 0;
    private Date tglRestrukturisasiAwal = null;
    private Date tglRestrukturisasiAkhir = null;
    private String kodeCara = "";
    private String kodeKondisi = "";
    private Date tglKondisi = null;
    private String keterangan = "";
    private String kodeKantorCabang = "";
    private String operasiData = "";
    private Date openDate = null;
    private int statusData = 0;
    private String sqlHistory="";
    private String namaSifatKredit="";
    private String namaJenisKredit="";
    private String namaAKad = "";
    
    //ADD BY ARI
    private long periodeId = 0;
    private long debiturOid = 0;
    private int debiturType = 0;

    private int statusOperasiData = 0;

    private String namaSingkat = "";
    private String alamat = "";
    private int statusPerubahanData=0;
    
    
    
    //buat di report
    private int countRekening=0;
    private double sumPlafon=0.0;
    private double sumBakiDebet=0.0;
    private double sumTunggakanPokok=0.0;
    
    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        if (noRekening==null){
            this.noRekening = "";
        }else{
            this.noRekening = noRekening;
        }
        
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        if (cif==null){
            this.cif = "";
        }else{
            this.cif = cif;
        }
        
    }

    public String getKodeSifat() {
        return kodeSifat;
    }

    public void setKodeSifat(String kodeSifat) {
        if (kodeSifat==null){
            this.kodeSifat = "";
        }else{
            this.kodeSifat = kodeSifat;
        }
        
    }

    public String getKodeJenisKredit() {
        return kodeJenisKredit;
    }

    public void setKodeJenisKredit(String kodeJenisKredit) {
        if (kodeJenisKredit==null){
            this.kodeJenisKredit = "";
        }else{
            this.kodeJenisKredit = kodeJenisKredit;
        }
        
    }

    public String getKodeAkad() {
        return kodeAkad;
    }

    public void setKodeAkad(String kodeAkad) {
        if (kodeAkad==null){
            this.kodeAkad = "";
        }else{
            this.kodeAkad = kodeAkad;
        }
        
    }

    public String getNoAkadAwal() {
        return noAkadAwal;
    }

    public void setNoAkadAwal(String noAkadAwal) {
        if (noAkadAwal==null){
            this.noAkadAwal = "";
        }else{
            this.noAkadAwal = noAkadAwal;
        }
        
    }

    public Date getTglAkadAwal() {
        return tglAkadAwal;
    }

    public void setTglAkadAwal(Date tglAkadAwal) {
        this.tglAkadAwal = tglAkadAwal;
    }

    public String getNoAkadAkhir() {
        return noAkadAkhir;
    }

    public void setNoAkadAkhir(String noAkadAkhir) {
        if (noAkadAkhir==null){
            this.noAkadAkhir = "";
        }else{
            this.noAkadAkhir = noAkadAkhir;
        }
        
    }

    public Date getTglAkadAkhir() {
        return tglAkadAkhir;
    }

    public void setTglAkadAkhir(Date tglAkadAkhir) {
        this.tglAkadAkhir = tglAkadAkhir;
    }

    public int getBaruPerpanjangan() {
        return baruPerpanjangan;
    }

    public void setBaruPerpanjangan(int baruPerpanjangan) {
        this.baruPerpanjangan = baruPerpanjangan;
    }

    public Date getTglAwal() {
        return tglAwal;
    }

    public void setTglAwal(Date tglAwal) {
        this.tglAwal = tglAwal;
    }

    public Date getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(Date tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Date getTglTempo() {
        return tglTempo;
    }

    public void setTglTempo(Date tglTempo) {
        this.tglTempo = tglTempo;
    }

    public String getKodeKatDbitur() {
        return kodeKatDbitur;
    }

    public void setKodeKatDbitur(String kodeKatDbitur) {
        if (kodeKatDbitur==null){
            this.kodeKatDbitur = "";
        }else{
            this.kodeKatDbitur = kodeKatDbitur;
        }
        
    }

    public String getKodeJenisPenggunaan() {
        return kodeJenisPenggunaan;
    }

    public void setKodeJenisPenggunaan(String kodeJenisPenggunaan) {
        if (kodeJenisPenggunaan==null){
            this.kodeJenisPenggunaan = "";
        }else{
            this.kodeJenisPenggunaan = kodeJenisPenggunaan;
        }
        
    }

    public String getKodeOrientasiPenggunaan() {
        return kodeOrientasiPenggunaan;
    }

    public void setKodeOrientasiPenggunaan(String kodeOrientasiPenggunaan) {
        if (kodeOrientasiPenggunaan==null){
            this.kodeOrientasiPenggunaan = "";
        }else{
            this.kodeOrientasiPenggunaan = kodeOrientasiPenggunaan;
        }
        
    }

    public String getKodeSektorEkonomi() {
        return kodeSektorEkonomi;
    }

    public void setKodeSektorEkonomi(String kodeSektorEkonomi) {
        if (kodeSektorEkonomi==null){
            this.kodeSektorEkonomi = "";
        }else{
            this.kodeSektorEkonomi = kodeSektorEkonomi;
        }
        
    }

    public String getKodeKab() {
        return kodeKab;
    }

    public void setKodeKab(String kodeKab) {
        if (kodeKab==null){
            this.kodeKab = "";
        }else{
            this.kodeKab = kodeKab;
        }
        
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getKodeValuta() {
        return kodeValuta;
    }

    public void setKodeValuta(String kodeValuta) {
        if (kodeValuta==null){
            this.kodeValuta = "";
        }else{
            this.kodeValuta = kodeValuta;
        }
        
    }

    public int getProsentaseBunga() {
        return prosentaseBunga;
    }

    public void setProsentaseBunga(int prosentaseBunga) {
        this.prosentaseBunga = prosentaseBunga;
    }

    public String getJenisBunga() {
        return jenisBunga;
    }

    public void setJenisBunga(String jenisBunga) {
        if (jenisBunga==null){
            this.jenisBunga = "";
        }else{
            this.jenisBunga = jenisBunga;
        }
        
    }

    public String getKreditPemerintah() {
        return kreditPemerintah;
    }

    public void setKreditPemerintah(String kreditPemerintah) {
        if (kreditPemerintah==null){
            this.kreditPemerintah = "";
        }else{
            this.kreditPemerintah = kreditPemerintah;
        }
        
    }

    public String getTakeover() {
        return takeover;
    }

    public void setTakeover(String takeover) {
        if (takeover==null){
            this.takeover = "";
        }else{
            this.takeover = takeover;
        }    
        
    }

    public String getSumberDana() {
        return sumberDana;
    }

    public void setSumberDana(String sumberDana) {
        if (sumberDana==null){
            this.sumberDana = "";
        }else{
            this.sumberDana = sumberDana;
        }
        
    }

    public double getPlafonAwal() {
        return plafonAwal;
    }

    public void setPlafonAwal(double plafonAwal) {
        this.plafonAwal = plafonAwal;
    }

    public double getPlafon() {
        return plafon;
    }

    public void setPlafon(double plafon) {
        this.plafon = plafon;
    }

    public double getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(double realisasi) {
        this.realisasi = realisasi;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }

    public double getBakiDebet() {
        return bakiDebet;
    }

    public void setBakiDebet(double bakiDebet) {
        this.bakiDebet = bakiDebet;
    }

    public double getNilaiUangAsal() {
        return nilaiUangAsal;
    }

    public void setNilaiUangAsal(double nilaiUangAsal) {
        this.nilaiUangAsal = nilaiUangAsal;
    }

    public String getKodeKolektibilitas() {
        return kodeKolektibilitas;
    }

    public void setKodeKolektibilitas(String kodeKolektibilitas) {
        if (kodeKolektibilitas==null){
            this.kodeKolektibilitas = "";
        }else{
            this.kodeKolektibilitas = kodeKolektibilitas;
        }
        
    }

    public Date getTglMacet() {
        return tglMacet;
    }

    public void setTglMacet(Date tglMacet) {
        this.tglMacet = tglMacet;
    }

    public String getKodeSebabMacet() {
        return kodeSebabMacet;
    }

    public void setKodeSebabMacet(String kodeSebabMacet) {
        if (kodeSebabMacet==null){
            this.kodeSebabMacet = "";
        }else{
            this.kodeSebabMacet = kodeSebabMacet;
        }
        
    }

    public double getTunggakanPokok() {
        return tunggakanPokok;
    }

    public void setTunggakanPokok(double tunggakanPokok) {
        this.tunggakanPokok = tunggakanPokok;
    }

    public double getTunggakanBunga() {
        return tunggakanBunga;
    }

    public void setTunggakanBunga(double tunggakanBunga) {
        this.tunggakanBunga = tunggakanBunga;
    }

    public int getJmlHariTunggakan() {
        return jmlHariTunggakan;
    }

    public void setJmlHariTunggakan(int jmlHariTunggakan) {
        this.jmlHariTunggakan = jmlHariTunggakan;
    }

    public int getFrekuensiTunggakan() {
        return frekuensiTunggakan;
    }

    public void setFrekuensiTunggakan(int frekuensiTunggakan) {
        this.frekuensiTunggakan = frekuensiTunggakan;
    }

    public int getFrekuensiRestrukturisasi() {
        return frekuensiRestrukturisasi;
    }

    public void setFrekuensiRestrukturisasi(int frekuensiRestrukturisasi) {
        this.frekuensiRestrukturisasi = frekuensiRestrukturisasi;
    }

    public Date getTglRestrukturisasiAwal() {
        return tglRestrukturisasiAwal;
    }

    public void setTglRestrukturisasiAwal(Date tglRestrukturisasiAwal) {
        this.tglRestrukturisasiAwal = tglRestrukturisasiAwal;
    }

    public Date getTglRestrukturisasiAkhir() {
        return tglRestrukturisasiAkhir;
    }

    public void setTglRestrukturisasiAkhir(Date tglRestrukturisasiAkhir) {
        this.tglRestrukturisasiAkhir = tglRestrukturisasiAkhir;
    }

    public String getKodeCara() {
        return kodeCara;
    }

    public void setKodeCara(String kodeCara) {
        if (kodeCara==null){
            this.kodeCara = "";
        }else{
            this.kodeCara = kodeCara;
        }
        
    }

    public String getKodeKondisi() {
        return kodeKondisi;
    }

    public void setKodeKondisi(String kodeKondisi) {
        if (kodeKondisi==null){
            this.kodeKondisi = "";
        }else{
            this.kodeKondisi = kodeKondisi;
        }
        
    }

    public Date getTglKondisi() {
        return tglKondisi;
    }

    public void setTglKondisi(Date tglKondisi) {
        this.tglKondisi = tglKondisi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        if (keterangan==null){
            this.keterangan = "";
        }else{
            this.keterangan = keterangan;
        }
        
    }

    public String getKodeKantorCabang() {
        return kodeKantorCabang;
    }

    public void setKodeKantorCabang(String kodeKantorCabang) {
        if (kodeKantorCabang==null){
            this.kodeKantorCabang = "";
        }else{
            this.kodeKantorCabang = kodeKantorCabang;
        }
        
    }

    public String getOperasiData() {
        return operasiData;
    }

    public void setOperasiData(String operasiData) {
        if (operasiData==null){
            this.operasiData = "";
        }else{
            this.operasiData = operasiData;
        }
        
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
     * @return the statusData
     */
    public int getStatusData() {
        return statusData;
    }

    /**
     * @param statusData the statusData to set
     */
    public void setStatusData(int statusData) {
        this.statusData = statusData;
    }
    
    
    @Override
    public String getLogDetail(Entity prevDoc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String history="";
        Kredit prevMat = (Kredit)prevDoc;
        
        try {
            //0
            if(prevMat == null || !prevMat.getFlagDetail().equals(this.getFlagDetail())){
                if (prevMat== null){
                    history=history+" Flag Detail : "+this.getFlagDetail()+"<br>";
                }else{
                    history=history+" Flag Detail diubah dari "+prevMat.getFlagDetail()+" menjadi "+this.getFlagDetail()+"<br>";
                }
            }
            
            //1
            if (prevMat == null || !prevMat.getNoRekening().equals(this.getNoRekening())) {
                if (prevMat== null){
                    history=history+" No. Rekening : "+this.getNoRekening()+"<br>";
                }else{
                    history = history + " No. Rekening diubah dari " + prevMat.getNoRekening() + " menjadi " + this.getNoRekening() + "<br>";
                }
            }
            
            //2
            if (prevMat == null || !prevMat.getCif().equals(this.getCif())) {
                if (prevMat== null){
                    history=history+" No. Rekening : "+this.getNoRekening()+"<br>";
                }else{
                    history = history + " Cif diubah dari " + prevMat.getCif() + " menjadi " + this.getCif() + "<br>";
                }
            }
            
            //3
            if (prevMat == null || !prevMat.getKodeSifat().equals(this.getKodeSifat())) {
                if (prevMat== null){
                    history=history+" Kode Sifat Kredit : "+this.getKodeSifat()+"<br>";
                }else{
                    history = history + " Kode Sifat Kredit duibah dari dari " + prevMat.getKodeSifat() + " menjadi " + this.getKodeSifat() + "<br>";
                }
            }
            
            //4
            if (prevMat == null || !prevMat.getKodeJenisKredit().equals(this.getKodeJenisKredit())) {
                if (prevMat== null){
                    history=history+" Kode Jenis Kredit : "+this.getKodeJenisKredit()+"<br>";
                }else{
                    history = history + " Kode Jenis Kredit diubah dari " + prevMat.getKodeJenisKredit() + " menjadi " + this.getKodeJenisKredit() + "<br>";
                }
            }
            
            //5
            if (prevMat == null || !prevMat.getKodeAkad().equals(this.getKodeAkad())) {
                if (prevMat== null){
                    history=history+" Kode Skim/Akad Pembiayaan : "+this.getKodeAkad()+"<br>";
                }else{
                    history = history + " Kode Skim/Akad Pembiayaan diubah dari " + prevMat.getKodeAkad() + " menjadi " + this.getKodeAkad() + "<br>";
                }
            }
            
            //6
            if (prevMat == null || !prevMat.getNoAkadAwal().equals(this.getNoAkadAwal())) {
                if (prevMat== null){
                    history=history+" No. Akad Awal : "+this.getNoAkadAwal()+"<br>";
                }else{
                    history = history + " No. Akad Awal diubah dari " + prevMat.getNoAkadAwal() + " menjadi " + this.getNoAkadAwal() + "<br>";
                }
            }
             
            //7
            if(prevMat == null || !prevMat.getTglAkadAwal().equals(this.getTglAkadAwal())){
                if (prevMat== null){
                    history=history+" Tanggal Akad Awal : "+this.getTglAkadAwal()+"<br>";
                }else{
                    history=history+" Tanggal Akad Awal diubah dari "+prevMat.getTglAkadAwal()+" menjadi "+this.getTglAkadAwal()+"<br>";
                }
            }
            
            //8
            if (prevMat == null || !prevMat.getNoAkadAkhir().equals(this.getNoAkadAkhir())) {
                if (prevMat== null){
                    history=history+" No. Akad Akhir : "+this.getNoAkadAkhir()+"<br>";
                }else{
                    history = history + " No. Akad Akhir diubah dari " + prevMat.getNoAkadAkhir() + " menjadi " + this.getNoAkadAkhir() + "<br>";
                }
            }
                    
            //9
            if (prevMat == null || !prevMat.getTglAkadAkhir().equals(this.getTglAkadAkhir())) {
                if (prevMat== null){
                    history=history+" Tanggal Akad Akhir : "+this.getTglAkadAkhir()+"<br>";
                }else{
                    history = history + " Tanggal Akad Akhir diubah dari " + prevMat.getTglAkadAkhir() + " menjadi " + this.getTglAkadAkhir() + "<br>";
                }
            }
            
            //10
            if (prevMat == null || prevMat.getBaruPerpanjangan() != this.getBaruPerpanjangan()) {
                if (prevMat== null){
                    history=history+" Baru Perpanjangan : "+this.getBaruPerpanjangan()+"<br>";
                }else{
                    history = history + " Baru Perpanjangan diubah dari " + prevMat.getBaruPerpanjangan() + " menjadi " + this.getBaruPerpanjangan() + "<br>";
                }
            }
            
            //11
            if (prevMat == null || !prevMat.getTglAwal().equals(this.getTglAkadAwal())) {
                if (prevMat== null){
                    history=history+" Tanggal Awal Kredit : "+this.getTglAkadAwal()+"<br>";
                }else{
                    history = history + " Tanggal Awal Kredit diubah dari " + prevMat.getTglAwal() + " menjadi " + this.getTglAwal() + "<br>";
                }
            }
            
            //12
            if (prevMat == null || !prevMat.getTglMulai().equals(this.getTglMulai())) {
                if (prevMat== null){
                    history=history+" Tanggal Mulai : "+this.getTglMulai()+"<br>";
                }else{
                    history = history + " Tanggal Mulai diubah dari " + prevMat.getTglMulai() + " menjadi " + this.getTglMulai() + "<br>";
                }
            }
            
            //13
            if (prevMat == null || !prevMat.getTglTempo().equals(this.getTglTempo())) {
                if (prevMat== null){
                    history=history+" Tanggal Jatuh Tempo : "+this.getTglTempo()+"<br>";
                }else{
                    history = history + " Tanggal Jatuh Tempo diubah dari " + prevMat.getTglTempo() + " menjadi " + this.getTglTempo() + "<br>";
                }
            }
            
            //14
            if (prevMat == null || !prevMat.getKodeKatDbitur().equals(this.getKodeKatDbitur())) {
                if (prevMat== null){
                    history=history+" Kode Kategori Debitur : "+this.getKodeKatDbitur()+"<br>";
                }else{
                    history = history + " Kode Kategori Debitur diubah dari " + prevMat.getKodeKatDbitur() + " menjadi " + this.getKodeKatDbitur() + "<br>";
                }
            }
            
            //15
            if (prevMat == null || !prevMat.getKodeJenisPenggunaan().equals(this.getKodeJenisPenggunaan())) {
                if (prevMat== null){
                    history=history+" Kode Jenis Penggunaan : "+this.getKodeJenisPenggunaan()+"<br>";
                }else{
                    history = history + " Kode Jenis Penggunaan diubah dari " + prevMat.getKodeJenisPenggunaan() + " menjadi " + this.getKodeJenisPenggunaan() + "<br>";
                }
            }
        
            //16
            if (prevMat == null || !prevMat.getKodeOrientasiPenggunaan().equals(this.getKodeOrientasiPenggunaan())) {
                if (prevMat== null){
                    history=history+" Kode Orientasi Penggunaan : "+this.getKodeOrientasiPenggunaan()+"<br>";
                }else{
                    history = history + " Kode Orientasi Penggunaan dari " + prevMat.getKodeOrientasiPenggunaan() + " menjadi " + this.getKodeOrientasiPenggunaan() + "<br>";
                }
            }
            
            //17
            if (prevMat == null || !prevMat.getKodeSektorEkonomi().equals(this.getKodeSektorEkonomi())) {
                if (prevMat== null){
                    history=history+" Kode Sektor Ekonomi : "+this.getKodeSektorEkonomi()+"<br>";
                }else{
                    history = history + " Kode Sektor Ekonomi diubah dari " + prevMat.getKodeSektorEkonomi() + " menjadi " + this.getKodeSektorEkonomi() + "<br>";
                }
            }
            
            //18
            if(prevMat == null || !prevMat.getKodeKab().equals(this.getKodeKab())){
                if (prevMat == null){
                    history=history+" Kode Kab/Kota (DATI 2) Lokasi Proyek/Penggunaan Kredit : "+this.getKodeKab()+"<br>";
                }else{
                    history=history+" Kode Kab/Kota (DATI 2) Lokasi Proyek/Penggunaan Kredit diubah dari "+prevMat.getKodeKab()+" menjadi "+this.getKodeKab()+"<br>";
                }
            }
            
            //19
            if (prevMat == null || (prevMat.getNilai() != this.getNilai())) {
                if (prevMat == null){
                    history=history+" Nilai Proyek : "+Formater.formatNumber(this.getNilai(),"#,###")+"<br>";
                }else{
                    history = history + " Nilai Proyek diubah dari " + Formater.formatNumber(prevMat.getNilai(),"#,###") + " menjadi " + Formater.formatNumber(this.getNilai(),"#,###") + "<br>";
                }
            }
                  
            //20
            if (prevMat == null || !prevMat.getKodeValuta().equals(this.getKodeValuta())) {
                if (prevMat == null){
                    history=history+" Kode Valuta : "+this.getKodeValuta()+"<br>";
                }else{
                    history = history + " Kode Valuta diubah dari " + prevMat.getKodeValuta() + " menjadi " + this.getKodeValuta() + "<br>";
                }
            }
            
            //21
            if (prevMat== null || prevMat.getProsentaseBunga() != this.getProsentaseBunga()) {
                if (prevMat== null){
                    history=history+" Prosentase Bunga / Imbalan : "+this.getProsentaseBunga()+"<br>";
                }else{
                    history = history + " Prosentase Bunga / Imbalan diubah dari " + prevMat.getProsentaseBunga() + " menjadi " + this.getProsentaseBunga() + "<br>";
                }
            }
            
            //22
            if (prevMat == null || !prevMat.getJenisBunga().equals(this.getJenisBunga())) {
                if (prevMat == null){
                    history=history+" Jenis Suku Bunga/Imbalan : "+this.getJenisBunga()+"<br>";
                }else{
                    history = history + " Jenis Suku Bunga/Imbalan diubah dari " + prevMat.getJenisBunga() + " menjadi " + this.getJenisBunga() + "<br>";
                }
            }
            
            //23
            if (prevMat == null || !prevMat.getKreditPemerintah().equals(this.getKreditPemerintah())) {
                if (prevMat== null){
                    history=history+" Kredit Program Pemerintah : "+this.getKreditPemerintah()+"<br>";
                }else{
                    history = history + " Kredit Program Pemerintah diubah dari " + prevMat.getKreditPemerintah() + " menjadi " + this.getKreditPemerintah() + "<br>";
                }
            }
            
            //24
            if (prevMat == null || !prevMat.getTakeover().equals(this.getTakeover())) {
                if (prevMat == null){
                    history=history+" Takeover Dari : "+this.getTakeover()+"<br>";
                }else{
                    history = history + " Takeover Dari diubah dari " + prevMat.getTakeover() + " menjadi " + this.getTakeover() + "<br>";
                }
            }
            
            //25
            if (prevMat == null || !prevMat.getSumberDana().equals(this.getSumberDana())) {
                if (prevMat== null){
                    history=history+" Sumber Dana : "+this.getSumberDana()+"<br>";
                }else{
                    history = history + " Sumber Dana diubah dari " + prevMat.getSumberDana() + " menjadi " + this.getSumberDana() + "<br>";
                }
            }
             
            //26
            if (prevMat == null || (prevMat.getPlafonAwal() != this.getPlafonAwal())) {
                if (prevMat == null){
                    history=history+" Plafon Awal : "+Formater.formatNumber(this.getPlafonAwal(),"#,###")+"<br>";
                }else{
                    history = history + " Plafon Awal diubah dari " + Formater.formatNumber(prevMat.getPlafonAwal(),"#,###") + " menjadi " + Formater.formatNumber(this.getPlafonAwal(),"#,###") + "<br>";
                }
            }
            
            //27
            if (prevMat == null || (prevMat.getPlafon() != this.getPlafon())) {
                if (prevMat == null){
                    history=history+" Plafon : "+Formater.formatNumber(this.getPlafonAwal(),"#,###")+"<br>";
                }else{
                    history = history + " Palfon diubah dari " + Formater.formatNumber(prevMat.getPlafon(),"#,###") + " menjadi " + Formater.formatNumber(this.getPlafon(),"#,###") + "<br>";
                }
            }
            
            //28
            if (prevMat == null || (prevMat.getRealisasi() != this.getRealisasi())) {
                if (prevMat == null){
                    history=history+" Realisasi/Pencairan Bulan Berjalan : "+Formater.formatNumber(this.getRealisasi(),"#,###")+"<br>";
                }else{
                    history = history + " Realisasi/Pencairan Bulan Berjalan diubah dari " + Formater.formatNumber(prevMat.getRealisasi(),"#,###") + " menjadi " + Formater.formatNumber(this.getRealisasi(),"#,###") + "<br>";
                }
            }
            
            //29
            if (prevMat == null ||(prevMat.getDenda() != this.getDenda())) {
                if (prevMat == null){
                    history=history+" Denda : "+Formater.formatNumber(this.getDenda(),"#,###")+"<br>";
                }else{
                    history = history + " Denda diubah dari " + Formater.formatNumber(prevMat.getDenda(),"#,###") + " menjadi " + Formater.formatNumber(this.getDenda(),"#,###") + "<br>";
                }
            }
            
            //30
            if (prevMat == null || (prevMat.getBakiDebet() != this.getBakiDebet())) {
                if (prevMat == null){
                    history=history+" Baki Debet : "+Formater.formatNumber(this.getBakiDebet(),"#,###")+"<br>";
                }else{
                    history = history + " Baki Debet diubah dari " + Formater.formatNumber(prevMat.getBakiDebet(),"#,###") + " menjadi " + Formater.formatNumber(this.getBakiDebet(),"#,###") + "<br>";
                }
            }
            
            //31
            if (prevMat == null || (prevMat.getNilaiUangAsal() != this.getNilaiUangAsal())) {
                if (prevMat == null){
                    history=history+" Nilai Dalam Mata Uang Asal : "+Formater.formatNumber(this.getNilaiUangAsal(),"#,###")+"<br>";
                }else{
                    history = history + " Nilai Dalam Mata Uang Asal diubah dari " + Formater.formatNumber(prevMat.getNilaiUangAsal(),"#,###") + " menjadi " + Formater.formatNumber(this.getNilaiUangAsal(),"#,###") + "<br>";
                }
            }
            
            //32
            if (prevMat == null || !prevMat.getKodeKolektibilitas().equals(this.getKodeKolektibilitas())) {
                if (prevMat == null){
                    history=history+" Kode Kolektibilitas : "+this.getKodeKolektibilitas()+"<br>";
                }else{
                    history = history + " Kode Kolektibilitas diubah dari " + prevMat.getKodeKolektibilitas() + " menjadi " + this.getKodeKolektibilitas() + "<br>";
                }
            }
            
            //33
            if (prevMat == null || !prevMat.getTglMacet().equals(this.getTglMacet())) {
                if (prevMat == null){
                    history=history+" Tanggal Macet : "+this.getTglMacet()+"<br>";
                }else{
                    history = history + " Tanggal Macet diubah dari " + prevMat.getTglMacet() + " menjadi " + this.getTglMacet() + "<br>";
                }
            }
            
            //34
            if (prevMat == null || !prevMat.getKodeSebabMacet().equals(this.getKodeSebabMacet())) {
                if (prevMat == null){
                    history=history+" Kode Sebab Macet : "+this.getTglMacet()+"<br>";
                }else{
                    history = history + " Kode Sebab Macet diubah dari " + prevMat.getKodeSebabMacet() + " menjadi " + this.getKodeSebabMacet() + "<br>";
                }
            }
            
            //35
            if (prevMat == null || (prevMat.getTunggakanPokok() != this.getTunggakanPokok())) {
                if (prevMat == null){
                    history=history+" Tunggakan Pokok : "+Formater.formatNumber(this.getTunggakanPokok(),"#,###")+"<br>";
                }else{
                    history = history + " Tunggakan Pokok diubah dari " + Formater.formatNumber(prevMat.getTunggakanPokok(),"#,###") + " menjadi " + Formater.formatNumber(this.getTunggakanPokok(),"#,###") + "<br>";
                }
            }
            
            //36
            if (prevMat == null || (prevMat.getTunggakanBunga() != this.getTunggakanBunga())) {
                if (prevMat == null){
                    history=history+" Tunggakan Bunga : "+Formater.formatNumber(this.getTunggakanBunga(),"#,###")+"<br>";
                }else{
                    history = history + " Tunggakan Bunga diubah dari " + Formater.formatNumber(prevMat.getTunggakanBunga(),"#,###") + " menjadi " + Formater.formatNumber(this.getTunggakanBunga(),"#,###") + "<br>";
                }
            }
            
            //37
            if (prevMat == null || (prevMat.getJmlHariTunggakan() != this.getJmlHariTunggakan())) {
                if (prevMat == null){
                    history=history+" Jumlah Hari Tunggakan : "+this.getJmlHariTunggakan()+"<br>";
                }else{
                    history = history + " Jumlah Hari Tunggakan diubah dari " + prevMat.getJmlHariTunggakan() + " menjadi " + this.getJmlHariTunggakan() + "<br>";
                }
            }
            
            //38
            if (prevMat == null || (prevMat.getFrekuensiTunggakan() != this.getFrekuensiTunggakan())) {
                if (prevMat == null){
                    history=history+" Frkuensi Tunggakan : "+this.getFrekuensiTunggakan()+"<br>";
                }else{
                    history = history + " Frkuensi Tunggakan diubah dari " + prevMat.getFrekuensiTunggakan() + " menjadi " + this.getFrekuensiTunggakan() + "<br>";
                }
            }
            
            //39
            if (prevMat == null || (prevMat.getFrekuensiRestrukturisasi() != this.getFrekuensiRestrukturisasi())) {
                if (prevMat == null){
                    history=history+" Frkuensi Restrukturisasi : "+this.getFrekuensiRestrukturisasi()+"<br>";
                }else{
                    history = history + " Frkuensi Restrukturisasi diubah dari " + prevMat.getFrekuensiRestrukturisasi() + " menjadi " + this.getFrekuensiRestrukturisasi() + "<br>";
                }
            }
            
            //40
            if (prevMat == null || !prevMat.getTglRestrukturisasiAwal().equals(this.getTglRestrukturisasiAwal())) {
                if (prevMat == null){
                    history=history+" Tanggal Restrukturisasi Awal : "+this.getTglRestrukturisasiAwal()+"<br>";
                }else{
                    history = history + " Tanggal Restrukturisasi Awal diubah dari " + prevMat.getTglRestrukturisasiAwal() + " menjadi " + this.getTglRestrukturisasiAwal() + "<br>";
                }
            }
            
            //41
            if (prevMat == null || !prevMat.getTglRestrukturisasiAkhir().equals(this.getTglRestrukturisasiAkhir())) {
                if (prevMat == null){
                    history=history+" Tanggal Restrukturisasi Akhir : "+this.getTglRestrukturisasiAkhir()+"<br>";
                }else{
                    history = history + " Tanggal Restrukturisasi Akhir diubah dari " + prevMat.getTglRestrukturisasiAkhir() + " menjadi " + this.getTglRestrukturisasiAkhir() + "<br>";
                }
            }
            
            //42
            if (prevMat == null || !prevMat.getKodeCara().equals(this.getKodeCara())) {
                if (prevMat == null){
                    history=history+" Kode Cara Restrukturisasi : "+this.getKodeCara()+"<br>";
                }else{
                    history = history + " Kode Cara Restrukturisasi diubah dari " + prevMat.getKodeCara() + " menjadi " + this.getKodeCara() + "<br>";
                }
            }
            
            //43
            if (prevMat == null || !prevMat.getKodeKondisi().equals(this.getKodeKondisi())) {
                if (prevMat == null){
                    history=history+" Kode Kondisi : "+this.getKodeKondisi()+"<br>";
                }else{
                    history = history + " Kode Kondisi diubah dari " + prevMat.getKodeKondisi() + " menjadi " + this.getKodeKondisi() + "<br>";
                }
            }
            
            //44
            if (prevMat == null || !prevMat.getTglKondisi().equals(this.getTglKondisi())) {
                if (prevMat == null){
                    history=history+" Tanggal Kondisi : "+this.getTglKondisi()+"<br>";
                }else{
                    history = history + " Tanggal Kondisi diubah dari " + prevMat.getTglKondisi() + " menjadi " + this.getTglKondisi() + "<br>";
                }
            }
            
            //45
            if (prevMat == null || !prevMat.getKeterangan().equals(this.getKeterangan())) {
                if (prevMat == null){
                    history=history+" Keterangan : "+this.getKeterangan()+"<br>";
                }else{
                    history = history + " Keterangan diubah dari " + prevMat.getKeterangan() + " menjadi " + this.getKeterangan() + "<br>";
                }
            }
            
            //46
            if (prevMat == null || !prevMat.getKodeKantorCabang().equals(this.getKodeKantorCabang())) {
                if (prevMat == null){
                    history=history+" Kantor Cabang : "+this.getKodeKantorCabang()+"<br>";
                }else{
                    history = history + " Kantor Cabang diubah dari " + prevMat.getKodeKantorCabang() + " menjadi " + this.getKodeKantorCabang() + "<br>";
                }
            }
            
            //47
//            if (prevMat == null || !prevMat.getOperasiData().equals(this.getOperasiData())) {
//                if (prevMat == null){
//                    history=history+" Operasi Data : "+this.getOperasiData()+"<br>";
//                }else{
//                    history = history + " Operasi Data diubah dari " + prevMat.getOperasiData() + " menjadi " + this.getOperasiData() + "<br>";
//                }
//            }
//            
//            //48
//            if (prevMat == null || !prevMat.getOpenDate().equals(this.getOpenDate())) {
//                if (prevMat == null){
//                    history=history+" Open Date : "+this.getOpenDate()+"<br>";
//                }else{
//                    history = history + " Open Date diubah dari " + prevMat.getOpenDate() + " menjadi " + this.getOpenDate() + "<br>";
//                }
//            }
//            
//            //49
//            if (prevMat.getStatusData() != this.getStatusData()) {
//                if (prevMat == null){
//                    history=history+" Status Data : "+this.getStatusData()+"<br>";
//                }else{
//                    history = history + " Status Data diubah dari " + prevMat.getStatusData() + " menjadi " + this.getStatusData() + "<br>";
//                }
//            }

        }catch (Exception e) {
            System.out.println(""+e.toString()+"");
        }
        //lanjutkan
        
        return history;
    }

    /**
     * @return the sqlHistory
     */
    public String getSqlHistory() {
        return sqlHistory;
    }

    /**
     * @param sqlHistory the sqlHistory to set
     */
    public void setSqlHistory(String sqlHistory) {
        this.sqlHistory = sqlHistory;
    }

    /**
     * @return the namaSifatKredit
     */
    public String getNamaSifatKredit() {
        return namaSifatKredit;
    }

    /**
     * @param namaSifatKredit the namaSifatKredit to set
     */
    public void setNamaSifatKredit(String namaSifatKredit) {
        if (namaSifatKredit==null){
            this.namaSifatKredit = "";
        }else{
            this.namaSifatKredit = namaSifatKredit;
        }
        
    }

    /**
     * @return the namaJenisKredit
     */
    public String getNamaJenisKredit() {
        return namaJenisKredit;
    }

    /**
     * @param namaJenisKredit the namaJenisKredit to set
     */
    public void setNamaJenisKredit(String namaJenisKredit) {
        if (namaJenisKredit==null){
            this.namaJenisKredit = "";
        }else{
            this.namaJenisKredit = namaJenisKredit;
        }
        
    }

    /**
     * @return the namaAKad
     */
    public String getNamaAKad() {
        return namaAKad;
    }

    /**
     * @param namaAKad the namaAKad to set
     */
    public void setNamaAKad(String namaAKad) {
        if (namaAKad==null){
            this.namaAKad = "";
        }else{
            this.namaAKad = namaAKad;
        }
        
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
     * @return the debiturOid
     */
    public long getDebiturOid() {
        return debiturOid;
    }

    /**
     * @param debiturOid the debiturOid to set
     */
    public void setDebiturOid(long debiturOid) {
        this.debiturOid = debiturOid;
    }

    /**
     * @return the debiturType
     */
    public int getDebiturType() {
        return debiturType;
    }

    /**
     * @param debiturType the debiturType to set
     */
    public void setDebiturType(int debiturType) {
        this.debiturType = debiturType;
    }

    /**
     * @return the statusOperasiData
     */
    public int getStatusOperasiData() {
        return statusOperasiData;
    }

    /**
     * @param statusOperasiData the statusOperasiData to set
     */
    public void setStatusOperasiData(int statusOperasiData) {
        this.statusOperasiData = statusOperasiData;
    }


    /**
     * @return the namaSingkat
     */
    public String getNamaSingkat() {
        return namaSingkat;
    }

    /**
     * @param namaSingkat the namaSingkat to set
     */
    public void setNamaSingkat(String namaSingkat) {
        this.namaSingkat = namaSingkat;
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

    /**
     * @return the countRekening
     */
    public int getCountRekening() {
        return countRekening;
    }

    /**
     * @param countRekening the countRekening to set
     */
    public void setCountRekening(int countRekening) {
        this.countRekening = countRekening;
    }

    /**
     * @return the sumPlafon
     */
    public double getSumPlafon() {
        return sumPlafon;
    }

    /**
     * @param sumPlafon the sumPlafon to set
     */
    public void setSumPlafon(double sumPlafon) {
        this.sumPlafon = sumPlafon;
    }

    /**
     * @return the sumBakiDebet
     */
    public double getSumBakiDebet() {
        return sumBakiDebet;
    }

    /**
     * @param sumBakiDebet the sumBakiDebet to set
     */
    public void setSumBakiDebet(double sumBakiDebet) {
        this.sumBakiDebet = sumBakiDebet;
    }

    /**
     * @return the sumTunggakanPokok
     */
    public double getSumTunggakanPokok() {
        return sumTunggakanPokok;
    }

    /**
     * @param sumTunggakanPokok the sumTunggakanPokok to set
     */
    public void setSumTunggakanPokok(double sumTunggakanPokok) {
        this.sumTunggakanPokok = sumTunggakanPokok;
    }

}
