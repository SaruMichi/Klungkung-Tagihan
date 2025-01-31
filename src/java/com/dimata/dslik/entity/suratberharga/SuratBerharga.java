/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.suratberharga;

/**
 *
 * @author m20n9
 */
import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.qdep.entity.Entity;
import com.dimata.util.Formater;
import java.util.Date;

public class SuratBerharga extends Entity implements I_LogHistory{

    private String flagDetail = "";
    private String noRekening = "";
    private String cif = "";
    private String kodeJenis = "";
    private int sovereignRate = 0;
    private String listing = "";
    private String peringkat = "";
    private String kodeTujuan = "";
    private Date tglTerbit = null;
    private Date tglBeli = null;
    private Date tglJatuhTempo = null;
    private String kodeValuta = "";
    private double nominal = 0;
    private double nilaiUangAsal = 0;
    private double nilaiPasar = 0;
    private double nilaiPerolehan = 0;
    private double sukuBunga = 0;
    private double tunggakan = 0;
    private int jmlHariTunggakan = 0;
    private String kodeKolektibilitas = "";
    private Date tglMacet = null;
    private String kodeSebabMacet = "";
    private String kodeKondisi = "";
    private Date tglKondisi = null;
    private String keterangan = "";
    private String kodeKantorCabang = "";
    private String operasiData = "";
    private Date openDate = null;
    private int statusData = 0;
    private String jenisSuratBerharga="";
    private String sqlHistory = "";
    
    //ADD BY ARI
    private long periodeId = 0;
    private long debiturOid = 0;
    private int debiturType = 0;
    private int statusOperasiData = 0;
    
    private int statusPerubahanData=0;
    
    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        if (flagDetail==null){
            this.flagDetail = "";
        }else{
            this.flagDetail = flagDetail;
        }
        
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

    public int getSovereignRate() {
        return sovereignRate;
    }

    public void setSovereignRate(int sovereignRate) {
        this.sovereignRate = sovereignRate;
    }

    public String getListing() {
        return listing;
    }

    public void setListing(String listing) {
        if (listing==null){
            this.listing = "";
        }else{
            this.listing = listing;
        }
        
    }

    public String getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(String peringkat) {
        if (peringkat==null){
            this.peringkat = "";
        }else{
            this.peringkat = peringkat;
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

    public Date getTglTerbit() {
        return tglTerbit;
    }

    public void setTglTerbit(Date tglTerbit) {
        this.tglTerbit = tglTerbit;
    }

    public Date getTglBeli() {
        return tglBeli;
    }

    public void setTglBeli(Date tglBeli) {
        this.tglBeli = tglBeli;
    }

    public Date getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(Date tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
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

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public double getNilaiUangAsal() {
        return nilaiUangAsal;
    }

    public void setNilaiUangAsal(double nilaiUangAsal) {
        this.nilaiUangAsal = nilaiUangAsal;
    }

    public double getNilaiPasar() {
        return nilaiPasar;
    }

    public void setNilaiPasar(double nilaiPasar) {
        this.nilaiPasar = nilaiPasar;
    }

    public double getNilaiPerolehan() {
        return nilaiPerolehan;
    }

    public void setNilaiPerolehan(double nilaiPerolehan) {
        this.nilaiPerolehan = nilaiPerolehan;
    }

    public double getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(double sukuBunga) {
        this.sukuBunga = sukuBunga;
    }

    public double getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(double tunggakan) {
        this.tunggakan = tunggakan;
    }

    public int getJmlHariTunggakan() {
        return jmlHariTunggakan;
    }

    public void setJmlHariTunggakan(int jmlHariTunggakan) {
        this.jmlHariTunggakan = jmlHariTunggakan;
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String history="";
        SuratBerharga prevMat = (SuratBerharga)prevDoc;
        
        try {
            if (!prevMat.getFlagDetail().equals(this.getFlagDetail())) {
                history = history + " Flag detail diubah dari " + prevMat.getFlagDetail()+ " menjadi " + this.getFlagDetail()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getNoRekening().equals(this.getNoRekening())) {
                history = history + " Nomor rekening diubah dari " + prevMat.getNoRekening()+ " menjadi " + this.getNoRekening()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getCif().equals(this.getCif())) {
                history = history + " CIF diubah dari " + prevMat.getCif()+ " menjadi " + this.getCif()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getKodeJenis().equals(this.getKodeJenis())) {
                history = history + " Kode jenis diubah dari " + prevMat.getKodeJenis()+ " menjadi " + this.getKodeJenis()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getSovereignRate() != this.getSovereignRate()) {
                history = history + " Sovereign Rate diubah dari " + prevMat.getSovereignRate()+ " menjadi " + this.getSovereignRate()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getListing().equals(this.getListing())) {
                history = history + " Listing diubah dari " + prevMat.getListing()+ " menjadi " + this.getListing()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getPeringkat().equals(this.getPeringkat())) {
                history = history + " Peringkat diubah dari " + prevMat.getPeringkat()+ " menjadi " + this.getPeringkat()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getKodeTujuan().equals(this.getKodeTujuan())) {
                history = history + " Kode tujuan diubah dari " + prevMat.getKodeTujuan()+ " menjadi " + this.getKodeTujuan()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getTglTerbit().equals(this.getTglTerbit())) {
                history = history + " Tanggal terbit diubah dari " + prevMat.getTglTerbit()+ " menjadi " + this.getTglTerbit()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getTglBeli().equals(this.getTglBeli())) {
                history = history + " Tanggal beli diubah dari " + prevMat.getTglBeli()+ " menjadi " + this.getTglBeli()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getTglJatuhTempo().equals(this.getTglJatuhTempo())) {
                history = history + " Tanggal jatuh tempo diubah dari " + prevMat.getTglJatuhTempo()+ " menjadi " + this.getTglJatuhTempo()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getKodeValuta().equals(this.getKodeValuta())) {
                history = history + " Kode valuta diubah dari " + prevMat.getKodeValuta()+ " menjadi " + this.getKodeValuta()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getNominal() != this.getNominal()) {
                history = history + " Nominal diubah dari " + Formater.formatNumber(prevMat.getNominal(),"#,###")+ " menjadi " + Formater.formatNumber(this.getNominal(),"#,###")+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getNilaiUangAsal()!= this.getNilaiUangAsal()) {
                history = history + " Nilai uang asal diubah dari " + Formater.formatNumber(prevMat.getNilaiUangAsal(),"#,###")+ " menjadi " + Formater.formatNumber(this.getNilaiUangAsal(),"#,###")+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getNilaiPasar()!= this.getNilaiPasar()) {
                history = history + " Nilai pasar diubah dari " + Formater.formatNumber(prevMat.getNilaiPasar(),"#,###")+ " menjadi " + Formater.formatNumber(this.getNilaiPasar(),"#,###")+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getNilaiPerolehan()!= this.getNilaiPerolehan()) {
                history = history + " Nilai perolehan diubah dari " + Formater.formatNumber(prevMat.getNilaiPerolehan(),"#,###")+ " menjadi " + Formater.formatNumber(this.getNilaiPerolehan(),"#,###")+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getSukuBunga()!= this.getSukuBunga()) {
                history = history + " Suku bunga diubah dari " + prevMat.getSukuBunga()+ " menjadi " + this.getSukuBunga()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getTunggakan()!= this.getTunggakan()) {
                history = history + " Tunggakan diubah dari " + Formater.formatNumber(prevMat.getTunggakan(),"#,###")+ " menjadi " + Formater.formatNumber(this.getTunggakan(),"#,###")+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getJmlHariTunggakan()!= this.getJmlHariTunggakan()) {
                history = history + " Jumlah hari tunggakan diubah dari " + prevMat.getJmlHariTunggakan()+ " menjadi " + this.getJmlHariTunggakan()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getKodeKolektibilitas().equals(this.getKodeKolektibilitas())) {
                history = history + " Kode kolektibilitas diubah dari " + prevMat.getKodeKolektibilitas()+ " menjadi " + this.getKodeKolektibilitas()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getTglMacet().equals(this.getTglMacet())) {
                history = history + " Tanggal macet diubah dari " + prevMat.getTglMacet()+ " menjadi " + this.getTglMacet()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getKodeSebabMacet().equals(this.getKodeSebabMacet())) {
                history = history + " Kode sebab macet diubah dari " + prevMat.getKodeSebabMacet()+ " menjadi " + this.getKodeSebabMacet()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getKodeKondisi().equals(this.getKodeKondisi())) {
                history = history + " Kode kondisi diubah dari " + prevMat.getKodeKondisi()+ " menjadi " + this.getKodeKondisi()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getTglKondisi().equals(this.getTglKondisi())) {
                history = history + " Tanggal kondisi diubah dari " + prevMat.getTglKondisi()+ " menjadi " + this.getTglKondisi()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getKeterangan().equals(this.getKeterangan())) {
                history = history + " Keterangan diubah dari " + prevMat.getKeterangan()+ " menjadi " + this.getKeterangan()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getKodeKantorCabang().equals(this.getKodeKantorCabang())) {
                history = history + " Kode kantor cabang diubah dari " + prevMat.getKodeKantorCabang()+ " menjadi " + this.getKodeKantorCabang()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getOperasiData().equals(this.getOperasiData())) {
                history = history + " Operasi data diubah dari " + prevMat.getOperasiData()+ " menjadi " + this.getOperasiData()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (!prevMat.getOpenDate().equals(this.getOpenDate())) {
                history = history + " Open date diubah dari " + prevMat.getOpenDate()+ " menjadi " + this.getOpenDate()+ "<br>";
            }
        } catch (Exception es) {}
        try {
            if (prevMat.getStatusData() != this.getStatusData()) {
                history = history + " Status data diubah dari " + prevMat.getStatusData()+ " menjadi " + this.getStatusData()+ "<br>";
            }
        } catch (Exception es) {}
        
        return history;
    }

    /**
     * @return the jenisSuratBerharga
     */
    public String getJenisSuratBerharga() {
        return jenisSuratBerharga;
    }

    /**
     * @param jenisSuratBerharga the jenisSuratBerharga to set
     */
    public void setJenisSuratBerharga(String jenisSuratBerharga) {
        this.jenisSuratBerharga = jenisSuratBerharga;
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