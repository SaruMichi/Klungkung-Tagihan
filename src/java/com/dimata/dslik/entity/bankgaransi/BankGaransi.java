/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.bankgaransi;

import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.qdep.entity.Entity;
import com.dimata.util.Formater;
import java.util.Date;

/**
 *
 * @author Dewa
 */
public class BankGaransi extends Entity implements I_LogHistory{

    private String flagDetail = "";
    private String noRekening = "";
    private String cif = "";
    private String kodeJenisGaransi = "";
    private String kodeTujuanGaransi = "";
    private Date tglDiterbitkan = null;
    private Date tglJatuhTempo = null;
    private String noAkadAwal = "";
    private Date tglAkadAwal = null;
    private String noAkadAkhir = "";
    private Date tglAkadAkhir = null;
    private String namaYgDijamin = "";
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
    private String tujuanGransi="";
    private String jenisGaranasi="";
    private String sqlHistory = "";
    
    //ADD BY ARI
    private long periodeId = 0;
    private long debiturOid = 0;
    private int debiturType = 0;
    private int statusOperasiData = 0;
    private int statusPerubahanData=0;
    
    //report by opie-eyek
    private String kodeBankGransiReport="";
    private String namaBankGaransiReport="";
    private int totalBankGaransi=0;
    private double totNominalBankGaransi=0.0;
    
    
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

    public String getKodeJenisGaransi() {
        return kodeJenisGaransi;
    }

    public void setKodeJenisGaransi(String kodeJenisGaransi) {
        if (kodeJenisGaransi==null){
            this.kodeJenisGaransi = "";
        }else{
            this.kodeJenisGaransi = kodeJenisGaransi;
        }
        
    }

    public String getKodeTujuanGaransi() {
        return kodeTujuanGaransi;
    }

    public void setKodeTujuanGaransi(String kodeTujuanGaransi) {
        if (kodeTujuanGaransi==null){
            kodeTujuanGaransi ="";
        }else{
            this.kodeTujuanGaransi = kodeTujuanGaransi;
        }
        
    }

    public Date getTglDiterbitkan() {
        return tglDiterbitkan;
    }

    public void setTglDiterbitkan(Date tglDiterbitkan) {
        this.tglDiterbitkan = tglDiterbitkan;
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

    public String getNamaYgDijamin() {
        return namaYgDijamin;
    }

    public void setNamaYgDijamin(String namaYgDijamin) {
        if (namaYgDijamin==null){
            this.namaYgDijamin = "";
        }else{
            this.namaYgDijamin = namaYgDijamin;
        }
        
    }

    public String getKodeValuta() {
        return kodeValuta;
    }

    public void setKodeValuta(String kodeValuta) {
        if(kodeValuta==null){
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
    public void setOpenDate(Date OpenDate) {
        this.openDate = OpenDate;
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String history="";
        BankGaransi prevMat = (BankGaransi)prevDoc;
        
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
                    if (prevMat == null) {
                        history = history + " Nomor Rekening : " + this.getNoRekening() + "<br>";
                    } else {
                        history = history + " Nomor Rekening diubah dari " + prevMat.getNoRekening() + " menjadi " + this.getNoRekening() + "<br>";
                    }
            }
            
            //2
            if (prevMat == null || !prevMat.getCif().equals(this.getCif())) {
                if (prevMat == null) {
                    history = history + " Nomor Rekening : " + this.getNoRekening() + "<br>";
                } else {
                    history = history + " CIF diubah dari " + prevMat.getCif()+ " menjadi " + this.getCif()+ "<br>";
                }
            }
            
            //3
            if (prevMat == null ||!prevMat.getKodeJenisGaransi().equals(this.getKodeJenisGaransi())) {
                if (prevMat == null) {
                    history = history + " Kode Jenis Garansi : " + this.getKodeJenisGaransi() + "<br>";
                } else {
                    history = history + " Kode Jenis Garansi diubah dari " + prevMat.getKodeJenisGaransi()+ " menjadi " + this.getKodeJenisGaransi()+ "<br>";
                }
            }
            
            //4
            if (prevMat == null || !prevMat.getKodeTujuanGaransi().equals(this.getKodeTujuanGaransi())) {
                if (prevMat == null) {
                    history = history + " Kode Tujuan Garansi : " + this.getKodeTujuanGaransi() + "<br>";
                } else {
                    history = history + " Kode Tujuan Garansi diubah dari " + prevMat.getKodeTujuanGaransi()+ " menjadi " + this.getKodeTujuanGaransi()+ "<br>";
                }
            }
            
            //5
            if (prevMat == null || !prevMat.getTglDiterbitkan().equals(this.getTglDiterbitkan())) {
                if (prevMat == null) {
                    history = history + " Tanggal diterbitkan : " + this.getTglDiterbitkan() + "<br>";
                } else {
                    history = history + " Tanggal diterbitkan diubah dari " + prevMat.getTglDiterbitkan()+ " menjadi " + this.getTglDiterbitkan()+ "<br>";
                }
            }
            
            //6
            if (prevMat == null || !prevMat.getTglJatuhTempo().equals(this.getTglJatuhTempo())) {
                if (prevMat == null) {
                    history = history + " Tanggal Jatuh Tempo : " + this.getTglJatuhTempo() + "<br>";
                } else {
                    history = history + " Tanggal Jatuh Tempo diubah dari " + prevMat.getTglJatuhTempo()+ " menjadi " + this.getTglJatuhTempo()+ "<br>";
                }
            }
            
            //7
            if (prevMat == null || !prevMat.getNoAkadAwal().equals(this.getNoAkadAwal())) {
                if (prevMat == null) {
                    history = history + " Nomor akad awal : " + this.getNoAkadAwal() + "<br>";
                } else {
                    history = history + " Nomor akad awal diubah dari " + prevMat.getNoAkadAwal()+ " menjadi " + this.getNoAkadAwal()+ "<br>";
                }
            }
            
            //8
            if (prevMat == null || !prevMat.getTglAkadAwal().equals(this.getTglAkadAwal())) {
                if (prevMat == null) {
                    history = history + " Tanggal akad awal : " + this.getTglAkadAwal() + "<br>";
                } else {
                    history = history + " Tanggal akad awal diubah dari " + prevMat.getTglAkadAwal()+ " menjadi " + this.getTglAkadAwal()+ "<br>";
                }               
            }
            
            //9
            if (prevMat == null || !prevMat.getNoAkadAkhir().equals(this.getNoAkadAkhir())) {
                if (prevMat == null) {
                    history = history + " Nomor akad akhir : " + this.getNoAkadAkhir() + "<br>";
                } else {
                    history = history + " Nomor akad akhir diubah dari " + prevMat.getNoAkadAkhir()+ " menjadi " + this.getNoAkadAkhir()+ "<br>";
                }
            }
            
            //10
            if (prevMat == null || !prevMat.getTglAkadAkhir().equals(this.getTglAkadAkhir())) {
                if (prevMat == null) {
                    history = history + " Tanggal akad akhir : " + this.getTglAkadAkhir() + "<br>";
                } else {
                    history = history + " Tanggal akad akhir diubah dari " + prevMat.getTglAkadAkhir()+ " menjadi " + this.getTglAkadAkhir()+ "<br>";
                }
            }
            
            //11
            if (prevMat == null || !prevMat.getNamaYgDijamin().equals(this.getNamaYgDijamin())) {
                if (prevMat == null) {
                    history = history + " Nama yang dijamin : " + this.getNamaYgDijamin() + "<br>";
                } else {
                    history = history + " Nama yang dijamin diubah dari " + prevMat.getNamaYgDijamin()+ " menjadi " + this.getNamaYgDijamin()+ "<br>";
                }
            }
            
            //12
            if (prevMat == null || !prevMat.getKodeValuta().equals(this.getKodeValuta())) {
                if (prevMat == null) {
                    history = history + " Kode valuta : " + this.getKodeValuta() + "<br>";
                } else {
                    history = history + " Kode valuta diubah dari " + prevMat.getKodeValuta()+ " menjadi " + this.getKodeValuta()+ "<br>";
                }
            }
            
            //13
            if (prevMat == null || prevMat.getPlafon()!= this.getPlafon()) {
                if (prevMat == null) {
                    history = history + " Plafon : " + Formater.formatNumber(this.getPlafon(),"#,###") + "<br>";
                } else {
                    history = history + " Plafon diubah dari " + Formater.formatNumber(prevMat.getPlafon(),"#,###")+ " menjadi " + Formater.formatNumber(this.getPlafon(),"#,###")+ "<br>"; 
                }
            }
            
            //14
            if (prevMat == null || prevMat.getNominal()!= this.getNominal()) {
                if (prevMat == null) {
                    history = history + " Nominal : " + Formater.formatNumber(this.getNominal(),"#,###") + "<br>";
                } else {
                    history = history + " Nominal diubah dari " + Formater.formatNumber(prevMat.getNominal(),"#,###")+ " menjadi " + Formater.formatNumber(this.getNominal(),"#,###")+ "<br>";
                }
            }
            
            //15
            if (prevMat == null || prevMat.getSetoranJaminan()!= this.getSetoranJaminan()) {
                if (prevMat == null) {
                    history = history + " Setoran jaminan : " + Formater.formatNumber(this.getSetoranJaminan(),"#,###") + "<br>";
                } else {
                     history = history + " Setoran jaminan diubah dari " + Formater.formatNumber(prevMat.getSetoranJaminan(),"#,###")+ " menjadi " + Formater.formatNumber(this.getSetoranJaminan(),"#,###")+ "<br>";
                }
            }
            
            //16
            if (prevMat == null || !prevMat.getKodeKolektibilitas().equals(this.getKodeKolektibilitas())) {
                if (prevMat == null) {
                    history = history + " Kode kolektibilitas : " + this.getKodeKolektibilitas() + "<br>";
                } else {
                    history = history + " Kode kolektibilitas diubah dari " + prevMat.getKodeKolektibilitas()+ " menjadi " + this.getKodeKolektibilitas()+ "<br>";
                }
            }
            
            //17
            if (prevMat == null || !prevMat.getTglWanPrestasi().equals(this.getTglWanPrestasi())) {
                if (prevMat == null) {
                    history = history + " Tanggal wan prestasi : " + this.getTglWanPrestasi() + "<br>";
                } else {
                    history = history + " Tanggal wan prestasi diubah dari " + prevMat.getTglWanPrestasi()+ " menjadi " + this.getTglWanPrestasi()+ "<br>";
                }
            }
            
            //18
            if (prevMat == null || !prevMat.getKodeKondisi().equals(this.getKodeKondisi())) {
                if (prevMat == null) {
                    history = history + " Kode kondisi : " + this.getKodeKondisi() + "<br>";
                } else {
                    history = history + " Kode kondisi diubah dari " + prevMat.getKodeKondisi()+ " menjadi " + this.getKodeKondisi()+ "<br>";
                }
            }
            
            //19
            if (prevMat == null || !prevMat.getTglKondisi().equals(this.getTglKondisi())) {
                if (prevMat == null) {
                    history = history + " Tanggal kondisi : " + this.getTglKondisi() + "<br>";
                } else {
                    history = history + " Tanggal kondisi diubah dari " + prevMat.getTglKondisi()+ " menjadi " + this.getTglKondisi()+ "<br>";
                }
            }
            
            //20
            if (prevMat == null || !prevMat.getKeterangan().equals(this.getKeterangan())) {
                if (prevMat == null) {
                    history = history + " Keterangan : " + this.getKeterangan() + "<br>";
                } else {
                    history = history + " Keterangan diubah dari " + prevMat.getKeterangan()+ " menjadi " + this.getKeterangan()+ "<br>";
                }
            }
            
            //21
            if (prevMat == null || !prevMat.getKodeKantorCabang().equals(this.getKodeKantorCabang())) {
                if (prevMat == null) {
                    history = history + " Kode kantor : " + this.getKodeKantorCabang() + "<br>";
                } else {
                    history = history + " Kode kantor cabang diubah dari " + prevMat.getKodeKantorCabang()+ " menjadi " + this.getKodeKantorCabang()+ "<br>";
                }
            }
            
            //22
            if (prevMat == null || !prevMat.getOperasiData().equals(this.getOperasiData())) {
                if (prevMat == null) {
                    history = history + " Operasi data : " + this.getOperasiData() + "<br>";
                } else {
                     history = history + " Operasi data diubah dari " + prevMat.getOperasiData()+ " menjadi " + this.getOperasiData()+ "<br>";
                }
            }
            
            //23
            if (prevMat == null || !prevMat.getOpenDate().equals(this.getOpenDate())) {
                if (prevMat == null) {
                    history = history + " Open date  : " + this.getOpenDate() + "<br>";
                } else {
                    history = history + " Open date diubah dari " + prevMat.getOpenDate()+ " menjadi " + this.getOpenDate()+ "<br>";
                }
            }
            
            //24
            if (prevMat == null || prevMat.getStatusData() != this.getStatusData()) {
                if (prevMat == null) {
                    history = history + " Status data  : " + this.getStatusData() + "<br>";
                } else {
                    history = history + " Status data diubah dari " + prevMat.getStatusData()+ " menjadi " + this.getStatusData()+ "<br>";
                }
            }
            
        } catch (Exception e) {
            System.out.println(""+e.toString()+"");
        }
       
        
        return history;
    }

    /**
     * @return the tujuanGransi
     */
    public String getTujuanGaransi() {
        if (tujuanGransi==null){
            tujuanGransi="";
        }
        return tujuanGransi;
    }

    /**
     * @param tujuanGransi the tujuanGransi to set
     */
    public void setTujuanGaransi(String tujuanGransi) {
        if (tujuanGransi==null){
            this.tujuanGransi = "";
        }else{
            this.tujuanGransi = tujuanGransi;
        }
        
    }

    /**
     * @return the jenisGaranasi
     */
    public String getJenisGaransi() {
        if (jenisGaranasi==null){
            jenisGaranasi="";
        }
        return jenisGaranasi;
    }

    /**
     * @param jenisGaranasi the jenisGaranasi to set
     */
    public void setJenisGaransi(String jenisGaranasi) {
        if (jenisGaranasi==null){
            this.jenisGaranasi = "";
        }else{
            this.jenisGaranasi = jenisGaranasi;
        }
        
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
     * @return the periodeid
     */
    public long getPeriodeid() {
        return periodeId;
    }

    /**
     * @param periodeid the periodeid to set
     */
    public void setPeriodeid(long periodeid) {
        this.periodeId = periodeid;
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

    /**
     * @return the kodeBankGransiReport
     */
    public String getKodeBankGransiReport() {
        return kodeBankGransiReport;
    }

    /**
     * @param kodeBankGransiReport the kodeBankGransiReport to set
     */
    public void setKodeBankGransiReport(String kodeBankGransiReport) {
        this.kodeBankGransiReport = kodeBankGransiReport;
    }

    /**
     * @return the namaBankGaransiReport
     */
    public String getNamaBankGaransiReport() {
        return namaBankGaransiReport;
    }

    /**
     * @param namaBankGaransiReport the namaBankGaransiReport to set
     */
    public void setNamaBankGaransiReport(String namaBankGaransiReport) {
        this.namaBankGaransiReport = namaBankGaransiReport;
    }

    /**
     * @return the totalBankGaransi
     */
    public int getTotalBankGaransi() {
        return totalBankGaransi;
    }

    /**
     * @param totalBankGaransi the totalBankGaransi to set
     */
    public void setTotalBankGaransi(int totalBankGaransi) {
        this.totalBankGaransi = totalBankGaransi;
    }

    /**
     * @return the totNominalBankGaransi
     */
    public double getTotNominalBankGaransi() {
        return totNominalBankGaransi;
    }

    /**
     * @param totNominalBankGaransi the totNominalBankGaransi to set
     */
    public void setTotNominalBankGaransi(double totNominalBankGaransi) {
        this.totNominalBankGaransi = totNominalBankGaransi;
    }

}
