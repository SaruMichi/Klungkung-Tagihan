/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.irrevocablelc;

/**
 *
 * @author m20n9
 */
import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.qdep.entity.Entity;
import com.dimata.util.Formater;
import java.util.Date;

public class IrrevocableLc extends Entity implements I_LogHistory{

    private String flagDetail = "";
    private String noRekening = "";
    private String cif = "";
    private String kodeJenis = "";
    private String kodeTujuan = "";
    private Date tglKeluar = null;
    private Date tglJatuhTempo = null;
    private String noAkadAwal = "";
    private Date tglAkadAwal = null;
    private String noAkadAkhr = "";
    private Date tglAkadAkhir = null;
    private String bankCounterparty = "";
    private String kodeValuta = "";
    private double plafon = 0;
    private double nominal = 0;
    private double setoranJaminan = 0;
    private String kodeKolektibilitas = "";
    private Date tglWanPrestasi = null;
    private String kodeKondisi = "";
    private Date tglKondisi = null;
    private String keterangan = "";
    private String kodeKantorCabang = "";
    private String operasiData = "";
    private Date openDate = null;
    private int statusData = 0;
    
    //ADD BY ARI
    private long periodeId = 0;
    private long debiturOid = 0;
    private int debiturType = 0;
    private String namaJenisLc = "";
    private String namaTujuanLc="";
    private String sqlHIstory = "";
    private int statusOperasiData = 0;
    private int statusPerubahanData = 0;
    
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

    public String getKodeJenis() {
        return kodeJenis;
    }

    public void setKodeJenis(String kodeJenis) {
        if (kodeJenis==null){
            this.kodeJenis = "";
        }else{
            this.kodeJenis = kodeJenis;
        }
        
    }

    public String getKodeTujuan() {
        return kodeTujuan;
    }

    public void setKodeTujuan(String kodeTujuan) {
        if (kodeTujuan==null){
            this.kodeTujuan = "";
        }else{
            this.kodeTujuan = kodeTujuan;
        }
        
    }

    public Date getTglKeluar() {
        return tglKeluar;
    }

    public void setTglKeluar(Date tglKeluar) {
        this.tglKeluar = tglKeluar;
    }

    public Date getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(Date tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
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

    public String getNoAkadAkhr() {
        return noAkadAkhr;
    }

    public void setNoAkadAkhr(String noAkadAkhr) {
        if (noAkadAkhr==null){
            this.noAkadAkhr = "";
        }else{
            this.noAkadAkhr = noAkadAkhr;
        }
        
    }

    public Date getTglAkadAkhir() {
        return tglAkadAkhir;
    }

    public void setTglAkadAkhir(Date tglAkadAkhir) {
        this.tglAkadAkhir = tglAkadAkhir;
    }

    public String getBankCounterparty() {
        return bankCounterparty;
    }

    public void setBankCounterparty(String bankCounterparty) {
        if (bankCounterparty==null){
            this.bankCounterparty = "";
        }else{
            this.bankCounterparty = bankCounterparty;
        }
        
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

    public double getPlafon() {
        return plafon;
    }

    public void setPlafon(double plafon) {
        this.plafon = plafon;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public double getSetoranJaminan() {
        return setoranJaminan;
    }

    public void setSetoranJaminan(double setoranJaminan) {
        this.setoranJaminan = setoranJaminan;
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

    public Date getTglWanPrestasi() {
        return tglWanPrestasi;
    }

    public void setTglWanPrestasi(Date tglWanPrestasi) {
        this.tglWanPrestasi = tglWanPrestasi;
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
     * @return the OpenDate
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * @param OpenDate the OpenDate to set
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * @return the StatusData
     */
    public int getStatusData() {
        return statusData;
    }

    /**
     * @param StatusData the StatusData to set
     */
    public void setStatusData(int statusData) {
        this.statusData = statusData;
    }

    @Override
    public String getLogDetail(Entity prevDoc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String history="";
        IrrevocableLc prevMat = (IrrevocableLc)prevDoc;
        try {
            if (prevMat == null || !prevMat.getFlagDetail().equals(this.getFlagDetail())) {
                if (prevMat == null) {
                    history = history + " Flag Detail : " + this.getFlagDetail() + "<br>";
                } else {
                    history = history + " Flag Detail diubah dari " + prevMat.getFlagDetail() + " menjadi " + this.getFlagDetail() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getNoRekening().equals(this.getNoRekening())) {
                if (prevMat == null) {
                    history = history + " Nomor Rekening : " + this.getNoRekening() + "<br>";
                } else {
                    history = history + " Nomor Rekening diubah dari " + prevMat.getNoRekening() + " menjadi " + this.getNoRekening() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getCif().equals(this.getCif())) {
                if (prevMat == null) {
                    history = history + " CIF : " + this.getCif() + "<br>";
                } else {
                    history = history + " CIF diubah dari " + prevMat.getCif() + " menjadi " + this.getCif() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeJenis().equals(this.getKodeJenis())) {
                if (prevMat == null) {
                    history = history + " Kode Jenis LC : " + this.getKodeJenis() + "<br>";
                } else {
                    history = history + " Kode Jenis LC diubah dari " + prevMat.getKodeJenis() + " menjadi " + this.getKodeJenis() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeTujuan().equals(this.getKodeTujuan())) {
                if (prevMat == null) {
                    history = history + " Kode Tujuan LC : " + this.getKodeTujuan() + "<br>";
                } else {
                    history = history + " Kode Tujuan LC diubah dari " + prevMat.getKodeTujuan() + " menjadi " + this.getKodeTujuan() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglKeluar().equals(this.getTglKeluar())) {
                if (prevMat == null) {
                    history = history + " Tanggal Keluar : " + this.getTglKeluar() + "<br>";
                } else {
                    history = history + " Tanggal Keluar diubah dari " + prevMat.getTglKeluar() + " menjadi " + this.getTglKeluar() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglJatuhTempo().equals(this.getTglJatuhTempo())) {
                if (prevMat == null) {
                    history = history + " Tanggal Jatuh Tempo : " + this.getTglJatuhTempo() + "<br>";
                } else {
                    history = history + " Tanggal Jatuh Tempo diubah dari " + prevMat.getTglJatuhTempo() + " menjadi " + this.getTglJatuhTempo() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getNoAkadAwal().equals(this.getNoAkadAwal())) {
                if (prevMat == null) {
                    history = history + " Nomor Akad Awal : " + this.getNoAkadAwal() + "<br>";
                } else {
                    history = history + " Nomor Akad Awal diubah dari " + prevMat.getNoAkadAwal() + " menjadi " + this.getNoAkadAwal() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglAkadAwal().equals(this.getTglAkadAwal())) {
                if (prevMat == null) {
                    history = history + " Tanggal Akad Awal : " + this.getTglAkadAwal() + "<br>";
                } else {
                    history = history + " Tanggal Akad Awal diubah dari " + prevMat.getTglAkadAwal() + " menjadi " + this.getTglAkadAwal() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getNoAkadAkhr().equals(this.getNoAkadAkhr())) {
                if (prevMat == null) {
                    history = history + " Nomor Akad Akhir : " + this.getNoAkadAkhr() + "<br>";
                } else {
                    history = history + " Nomor Akad Akhir diubah dari " + prevMat.getNoAkadAkhr() + " menjadi " + this.getNoAkadAkhr() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglAkadAkhir().equals(this.getTglAkadAkhir())) {
                if (prevMat == null) {
                    history = history + " Tanggal Akad Akhir : " + this.getTglAkadAkhir() + "<br>";
                } else {
                    history = history + " Tanggal Akad Akhir diubah dari " + prevMat.getTglAkadAkhir() + " menjadi " + this.getTglAkadAkhir() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getBankCounterparty().equals(this.getBankCounterparty())) {
                if (prevMat == null) {
                    history = history + " Bank Counterparty : " + this.getBankCounterparty() + "<br>";
                } else {
                    history = history + " Bank Counterparty diubah dari " + prevMat.getBankCounterparty() + " menjadi " + this.getBankCounterparty() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeValuta().equals(this.getKodeValuta())) {
                if (prevMat == null) {
                    history = history + " Kode Valuta : " + this.getKodeValuta() + "<br>";
                } else {
                    history = history + " Kode Valuta diubah dari " + prevMat.getKodeValuta() + " menjadi " + this.getKodeValuta() + "<br>";
                }
            }
            if (prevMat == null || prevMat.getPlafon() != this.getPlafon()) {
                if (prevMat == null) {
                    history = history + " Plafon : " + Formater.formatNumber(this.getPlafon(),"#,###") + "<br>";
                } else {
                    history = history + " Plafon diubah dari " + Formater.formatNumber(prevMat.getPlafon(),"#,###") + " menjadi " + Formater.formatNumber(this.getPlafon(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getNominal() != this.getNominal()) {
                if (prevMat == null) {
                    history = history + " Nominal (IDR) : " + Formater.formatNumber(this.getNominal(),"#,###") + "<br>";
                } else {
                    history = history + " Nominal (IDR) diubah dari " + Formater.formatNumber(prevMat.getNominal(),"#,###") + " menjadi " + Formater.formatNumber(this.getNominal(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getSetoranJaminan() != this.getSetoranJaminan()) {
                if (prevMat == null) {
                    history = history + " Setoran Jaminan : " + Formater.formatNumber(this.getSetoranJaminan(),"#,###") + "<br>";
                } else {
                    history = history + " Setoran Jaminan diubah dari " + Formater.formatNumber(prevMat.getSetoranJaminan(),"#,###") + " menjadi " + Formater.formatNumber(this.getSetoranJaminan(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeKolektibilitas().equals(this.getKodeKolektibilitas())) {
                if (prevMat == null) {
                    history = history + " Kode Kolektibilitas : " + this.getKodeKolektibilitas() + "<br>";
                } else {
                    history = history + " Kode Kolektibilitas diubah dari " + prevMat.getKodeKolektibilitas() + " menjadi " + this.getKodeKolektibilitas() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglWanPrestasi().equals(this.getTglWanPrestasi())) {
                if (prevMat == null) {
                    history = history + " Tanggal Wan Prestasi : " + this.getTglWanPrestasi() + "<br>";
                } else {
                    history = history + " Tanggal Wan Prestasi diubah dari " + prevMat.getTglWanPrestasi() + " menjadi " + this.getTglWanPrestasi() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeKondisi().equals(this.getKodeKondisi())) {
                if (prevMat == null) {
                    history = history + " Kode Kondisi : " + this.getKodeKondisi() + "<br>";
                } else {
                    history = history + " Kode Kondisi diubah dari " + prevMat.getKodeKondisi() + " menjadi " + this.getKodeKondisi() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglKondisi().equals(this.getTglKondisi())) {
                if (prevMat == null) {
                    history = history + " Tanggal Kondisi : " + this.getTglKondisi() + "<br>";
                } else {
                    history = history + " Tanggal Kondisi diubah dari " + prevMat.getTglKondisi() + " menjadi " + this.getTglKondisi() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKeterangan().equals(this.getKeterangan())) {
                if (prevMat == null) {
                    history = history + " Keterangan : " + this.getKeterangan() + "<br>";
                } else {
                    history = history + " Keterangan diubah dari " + prevMat.getKeterangan() + " menjadi " + this.getKeterangan() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeKantorCabang().equals(this.getKodeKantorCabang())) {
                if (prevMat == null) {
                    history = history + " Kode Kantor Cabang : " + this.getKodeKantorCabang() + "<br>";
                } else {
                    history = history + " Kode Kantor Cabang diubah dari " + prevMat.getKodeKantorCabang() + " menjadi " + this.getKodeKantorCabang() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getOperasiData().equals(this.getOperasiData())) {
                if (prevMat == null) {
                    history = history + " Operasi Data : " + this.getOperasiData() + "<br>";
                } else {
                    history = history + " Operasi Data diubah dari " + prevMat.getOperasiData() + " menjadi " + this.getOperasiData() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getOpenDate().equals(this.getOpenDate())) {
                if (prevMat == null) {
                    history = history + " Open Date : " + this.getOpenDate() + "<br>";
                } else {
                    history = history + " Open Date diubah dari " + prevMat.getOpenDate() + " menjadi " + this.getOpenDate() + "<br>";
                }
            }
            if (prevMat == null || prevMat.getStatusData() != this.getStatusData()) {
                if (prevMat == null) {
                    history = history + " Status Data : " + this.getStatusData() + "<br>";
                } else {
                    history = history + " Status Data diubah dari " + prevMat.getStatusData() + " menjadi " + this.getStatusData() + "<br>";
                }
            }
        } catch (Exception e) {
            System.out.println("" + e.toString() + "");
        }
        
        return history;
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
     * @return the namaJenisLc
     */
    public String getNamaJenisLc() {
        return namaJenisLc;
    }

    /**
     * @param namaJenisLc the namaJenisLc to set
     */
    public void setNamaJenisLc(String namaJenisLc) {
        if (namaJenisLc==null){
            this.namaJenisLc = "";
        }else{
            this.namaJenisLc = namaJenisLc;
        }
        
    }

    /**
     * @return the namaTujuanLc
     */
    public String getNamaTujuanLc() {
        return namaTujuanLc;
    }

    /**
     * @param namaTujuanLc the namaTujuanLc to set
     */
    public void setNamaTujuanLc(String namaTujuanLc) {
        if (namaTujuanLc==null){
            this.namaTujuanLc = "";
        }else{
            this.namaTujuanLc = namaTujuanLc;
        }
        
    }

    /**
     * @return the sqlHIstory
     */
    public String getSqlHIstory() {
        return sqlHIstory;
    }

    /**
     * @param sqlHIstory the sqlHIstory to set
     */
    public void setSqlHIstory(String sqlHIstory) {
        this.sqlHIstory = sqlHIstory;
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
