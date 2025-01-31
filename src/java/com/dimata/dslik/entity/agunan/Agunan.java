/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.agunan;

import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.qdep.entity.Entity;
import com.dimata.util.Formater;
import java.util.Date;

/**
 *
 * @author Dewa
 */
public class Agunan extends Entity implements I_LogHistory{

    private String flagDetail = "";
    private String kodeRegisterAgunan = "";
    private String noRekening = "";
    private String cif = "";
    private String kodeStatusAgunan = "";
    private String kodeJenisAgunan = "";
    private String peringkatAgunan = "";
    private String kodeLembagaPemeringkat = "";
    private String kodeJenisPengikatan = "";
    private Date tglPengikatan = null;
    private String namaPemilikAgunan = "";
    private String buktiKepemilikan = "";
    private String alamatAgunan = "";
    private String kodeKabLokasiAgunan = "";
    private double nilaiAgunanNjop = 0;
    private double nilaiAgunanLjk = 0;
    private Date tglPenilaianLjk = null;
    private double nilaiAguPenilaiIndep = 0;
    private String namaPenilaiIndep = "";
    private Date tglPenilaianPenilaiIndep = null;
    private String statusParipasu = "";
    private String statusKreditJoin = "";
    private String diasuransikan = "";
    private String keterangan = "";
    private String kodeKantorCabang = "";
    private String operasiData = "";
    private Date openDate = null;
    private int statusData = 0;
    private String sqlHistory = "";
    
    //ADD BY ARI
    private long periodeId = 0;
    private long debiturOid = 0;
    private int debiturType = 0;
    private double prosentaseParipasu = 0;
    private int statusOperasiData = 0;
    private int statusDataPerubahan=0;
    private String kodeJenisSegementFasilitas="";
    
    //report by opie-eyek 20170112
    private int countRegisterAgunan=0;
    private int sumRekeningAgunan=0;
    private double sumTotalAgunan=0;
    private double sumBakiPerAgunan=0;
    
    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
    }

    public String getKodeRegisterAgunan() {
        return kodeRegisterAgunan;
    }

    public void setKodeRegisterAgunan(String kodeRegisterAgunan) {
        if (kodeRegisterAgunan==null){
            kodeRegisterAgunan = "";
        }else{
            this.kodeRegisterAgunan = kodeRegisterAgunan;
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

    public String getKodeStatusAgunan() {
        return kodeStatusAgunan;
    }

    public void setKodeStatusAgunan(String kodeStatusAgunan) {
        if (kodeStatusAgunan==null){
            this.kodeStatusAgunan = "";
        }else{
            this.kodeStatusAgunan = kodeStatusAgunan;
        }
        
    }

    public String getKodeJenisAgunan() {
        return kodeJenisAgunan;
    }

    public void setKodeJenisAgunan(String kodeJenisAgunan) {
        if (kodeJenisAgunan==null){
            this.kodeJenisAgunan = "";
        }else{
            this.kodeJenisAgunan = kodeJenisAgunan;
        }
        
    }

    public String getPeringkatAgunan() {
        return peringkatAgunan;
    }

    public void setPeringkatAgunan(String peringkatAgunan) {
        if (peringkatAgunan==null){
            this.peringkatAgunan = "";
        }else{
            this.peringkatAgunan = peringkatAgunan;
        }
        
    }

    public String getKodeLembagaPemeringkat() {
        return kodeLembagaPemeringkat;
    }

    public void setKodeLembagaPemeringkat(String kodeLembagaPemeringkat) {
        if (kodeLembagaPemeringkat==null){
            this.kodeLembagaPemeringkat = "";
        }else{
            this.kodeLembagaPemeringkat = kodeLembagaPemeringkat;
        }
        
    }

    public String getKodeJenisPengikatan() {
        return kodeJenisPengikatan;
    }

    public void setKodeJenisPengikatan(String kodeJenisPengikatan) {
        if (kodeJenisPengikatan==null){
            this.kodeJenisPengikatan = "";
        }else{
            this.kodeJenisPengikatan = kodeJenisPengikatan;
        }
        
    }

    public Date getTglPengikatan() {
        return tglPengikatan;
    }

    public void setTglPengikatan(Date tglPengikatan) {
        this.tglPengikatan = tglPengikatan;
    }

    public String getNamaPemilikAgunan() {
        return namaPemilikAgunan;
    }

    public void setNamaPemilikAgunan(String namaPemilikAgunan) {
        if (namaPemilikAgunan==null){
            this.namaPemilikAgunan = "";
        }else{
            this.namaPemilikAgunan = namaPemilikAgunan;
        }
        
    }

    public String getBuktiKepemilikan() {
        return buktiKepemilikan;
    }

    public void setBuktiKepemilikan(String buktiKepemilikan) {
        if (buktiKepemilikan==null){
            this.buktiKepemilikan = "";
        }else{
            this.buktiKepemilikan = buktiKepemilikan;
        }
        
    }

    public String getAlamatAgunan() {
        return alamatAgunan;
    }

    public void setAlamatAgunan(String alamatAgunan) {
        if (alamatAgunan==null){
            this.alamatAgunan = "";
        }else{
            this.alamatAgunan = alamatAgunan;
        }
        
    }

    public String getKodeKabLokasiAgunan() {
        return kodeKabLokasiAgunan;
    }

    public void setKodeKabLokasiAgunan(String kodeKabLokasiAgunan) {
        if (kodeKabLokasiAgunan==null){
            this.kodeKabLokasiAgunan = "";
        }else{
            this.kodeKabLokasiAgunan = kodeKabLokasiAgunan;
        }
        
    }

    public double getNilaiAgunanNjop() {
        return nilaiAgunanNjop;
    }

    public void setNilaiAgunanNjop(double nilaiAgunanNjop) {
        this.nilaiAgunanNjop = nilaiAgunanNjop;
    }

    public double getNilaiAgunanLjk() {
        return nilaiAgunanLjk;
    }

    public void setNilaiAgunanLjk(double nilaiAgunanLjk) {
        this.nilaiAgunanLjk = nilaiAgunanLjk;
    }

    public Date getTglPenilaianLjk() {
        return tglPenilaianLjk;
    }

    public void setTglPenilaianLjk(Date tglPenilaianLjk) {
        this.tglPenilaianLjk = tglPenilaianLjk;
    }

    public double getNilaiAguPenilaiIndep() {
        return nilaiAguPenilaiIndep;
    }

    public void setNilaiAguPenilaiIndep(double nilaiAguPenilaiIndep) {
        this.nilaiAguPenilaiIndep = nilaiAguPenilaiIndep;
    }

    public String getNamaPenilaiIndep() {
        return namaPenilaiIndep;
    }

    public void setNamaPenilaiIndep(String namaPenilaiIndep) {
        if (namaPenilaiIndep==null){
            this.namaPenilaiIndep = "";
        }else{
            this.namaPenilaiIndep = namaPenilaiIndep;
        }
        
    }

    public Date getTglPenilaianPenilaiIndep() {
        return tglPenilaianPenilaiIndep;
    }

    public void setTglPenilaianPenilaiIndep(Date tglPenilaianPenilaiIndep) {
        this.tglPenilaianPenilaiIndep = tglPenilaianPenilaiIndep;
    }

    public String getStatusKreditJoin() {
        return statusKreditJoin;
    }

    public void setStatusKreditJoin(String statusKreditJoin) {
        if (statusKreditJoin==null){
            this.statusKreditJoin = "";
        }else{
            this.statusKreditJoin = statusKreditJoin;
        }
        
    }

    public String getDiasuransikan() {
        return diasuransikan;
    }

    public void setDiasuransikan(String diasuransikan) {
        if (diasuransikan==null){
            this.diasuransikan = "";
        }else{
            this.diasuransikan = diasuransikan;
        }
        
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
        Agunan prevMat = (Agunan)prevDoc;
        
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
            if (prevMat == null || !prevMat.getKodeRegisterAgunan().equals(this.getKodeRegisterAgunan())) {
                if (prevMat== null){
                    history=history+" Kode Register Agunan : "+this.getKodeRegisterAgunan()+"<br>";
                }else{
                    history = history + " Kode Register Agunan diubah dari " + prevMat.getKodeRegisterAgunan() + " menjadi " + this.getKodeRegisterAgunan() + "<br>";
                }
            }
            
            //2
            if (prevMat == null || !prevMat.getNoRekening().equals(this.getNoRekening())) {
                if (prevMat== null){
                    history=history+" No. Rekening : "+this.getNoRekening()+"<br>";
                }else{
                     history = history + " No. Rekening diubah dari " + prevMat.getNoRekening() + " menjadi " + this.getNoRekening() + "<br>";
                }
            }
            
            //3
            if (prevMat== null || !prevMat.getCif().equals(this.getCif())) {
                if (prevMat== null){
                    history=history+" CIF : "+this.getCif()+"<br>";
                }else{
                     history = history + " CIF duibah dari dari " + prevMat.getCif() + " menjadi " + this.getCif() + "<br>";
                }
            }
            
            //4
            if (prevMat == null || !prevMat.getKodeStatusAgunan().equals(this.getKodeStatusAgunan())) {
                if (prevMat== null){
                    history=history+" Kode Status Agunan : "+this.getKodeStatusAgunan()+"<br>";
                }else{
                     history = history + " Kode Status Agunan diubah dari " + prevMat.getKodeStatusAgunan() + " menjadi " + this.getKodeStatusAgunan() + "<br>";
                }
            }
            
            //5
            if (prevMat == null || !prevMat.getKodeJenisAgunan().equals(this.getKodeJenisAgunan())) {
                if (prevMat== null){
                    history=history+" Kode Jenis Agunan : "+this.getKodeStatusAgunan()+"<br>";
                }else{
                    history = history + " Kode Jenis Agunan diubah dari " + prevMat.getKodeJenisAgunan() + " menjadi " + this.getKodeJenisAgunan() + "<br>";
                }
            }
            
            //6
            if (prevMat== null || !prevMat.getPeringkatAgunan().equals(this.getPeringkatAgunan())) {
                if (prevMat== null){
                    history=history+" Peringkat Agunan : "+this.getPeringkatAgunan()+"<br>";
                }else{
                    history = history + " Peringkat Agunan diubah dari " + prevMat.getPeringkatAgunan() + " menjadi " + this.getPeringkatAgunan() + "<br>";
                }
            }
            
            //7
            if (prevMat== null || !prevMat.getKodeLembagaPemeringkat().equals(this.getKodeLembagaPemeringkat())) {
                if (prevMat== null){
                    history=history+" Kode Lembaga Pemeringkat : "+this.getKodeLembagaPemeringkat()+"<br>";
                }else{
                    history = history + " Kode Lembaga Pemeringkat diubah dari " + prevMat.getKodeLembagaPemeringkat() + " menjadi " + this.getKodeLembagaPemeringkat() + "<br>";
                }
            }
            
            //8
            if (prevMat== null || !prevMat.getKodeJenisPengikatan().equals(this.getKodeJenisPengikatan())) {
                if (prevMat== null){
                    history=history+" Kode Jenis Pengikatan : "+this.getKodeJenisPengikatan()+"<br>";
                }else{
                    history = history + " Kode Jenis Pengikatan diubah dari " + prevMat.getKodeJenisPengikatan() + " menjadi " + this.getKodeJenisPengikatan() + "<br>";
                }
            }
            
            //8
            if (prevMat== null || !prevMat.getTglPengikatan().equals(this.getTglPengikatan())) {
                if (prevMat== null){
                    history=history+" Tanggal Pengikatan : "+this.getTglPengikatan()+"<br>";
                }else{
                    history = history + " Tanggal Pengikatan diubah dari " + prevMat.getTglPengikatan() + " menjadi " + this.getTglPengikatan() + "<br>";
                    
                }
            }
            
            //9
            if (prevMat== null || !prevMat.getNamaPemilikAgunan().equals(this.getNamaPemilikAgunan())) {
                if (prevMat== null){
                    history=history+" Nama Pemilik Agunan : "+this.getNamaPemilikAgunan()+"<br>";
                }else{
                    history = history + " Nama Pemilik Agunan diubah dari " + prevMat.getNamaPemilikAgunan() + " menjadi " + this.getNamaPemilikAgunan() + "<br>";
                }
            }
            
            //10
            if (prevMat== null || !prevMat.getBuktiKepemilikan().equals(this.getBuktiKepemilikan())) {
                if (prevMat== null){
                    history=history+" Bukti Kepemilikan : "+this.getBuktiKepemilikan()+"<br>";
                }else{
                    history = history + " Bukti Kepemilikan diubah dari " + prevMat.getBuktiKepemilikan() + " menjadi " + this.getBuktiKepemilikan() + "<br>";
                }
            }
            
            //11
            if (prevMat== null || !prevMat.getAlamatAgunan().equals(this.getAlamatAgunan())) {
                if (prevMat== null){
                    history=history+" Alamat Agunan : "+this.getAlamatAgunan()+"<br>";
                }else{
                    history = history + " Alamat Agunan diubah dari " + prevMat.getAlamatAgunan() + " menjadi " + this.getAlamatAgunan() + "<br>";
                }
            }
            
            //12
            if (prevMat == null || !prevMat.getKodeKabLokasiAgunan().equals(this.getKodeKabLokasiAgunan())) {
                if (prevMat == null) {
                    history = history + " Kode Kab/Kota (DATI 2) Lokasi Agunan : " + this.getKodeKabLokasiAgunan() + "<br>";
                } else {
                    history = history + " Kode Kab/Kota (DATI 2) Lokasi Agunan diubah dari " + prevMat.getKodeKabLokasiAgunan() + " menjadi " + this.getKodeKabLokasiAgunan() + "<br>";
                }
            }
            
            //13
            if (prevMat == null || (prevMat.getNilaiAgunanNjop() != this.getNilaiAgunanNjop())) {
                if (prevMat == null) {
                    history = history + " Nilai Agunan (NJOP) : " + Formater.formatNumber(this.getNilaiAgunanNjop(),"#,###") + "<br>";
                } else {
                    history = history + " Nilai Agunan (NJOP) diubah dari " + Formater.formatNumber(prevMat.getNilaiAgunanNjop(),"#,###") + " menjadi " + Formater.formatNumber(this.getNilaiAgunanNjop(),"#,###") + "<br>";
                }
            }
            
            //14
            if (prevMat == null || prevMat.getNilaiAgunanLjk() != this.getNilaiAgunanLjk()) {
                if (prevMat == null) {
                    history = history + " Nilai Agunan Menurut LJK : " + Formater.formatNumber(this.getNilaiAgunanLjk(),"#,###") + "<br>";
                } else {
                   history = history + " Nilai Agunan Menurut LJK diubah dari " + Formater.formatNumber(prevMat.getNilaiAgunanLjk(),"#,###") + " menjadi " + Formater.formatNumber(this.getNilaiAgunanLjk(),"#,###") + "<br>";
                }
            }
            
            //15
            if (prevMat == null || !prevMat.getTglPenilaianLjk().equals(this.getTglPenilaianLjk())) {
                if (prevMat == null) {
                    history = history + " Tanggal LJK diubah : " + this.getTglPenilaianLjk() + "<br>";
                } else {
                    history = history + " Tanggal LJK diubah dari " + prevMat.getTglPenilaianLjk() + " menjadi " + this.getTglPenilaianLjk() + "<br>";
                }
            }
            
            //16
            if (prevMat == null || prevMat.getNilaiAguPenilaiIndep() != this.getNilaiAguPenilaiIndep()) {
                if (prevMat == null) {
                    history = history + " Nilai Agunan Penilai Independen : " + Formater.formatNumber(this.getNilaiAguPenilaiIndep(),"#,###") + "<br>";
                } else {
                    history = history + " Nilai Agunan Penilai Independen diubah dari " + Formater.formatNumber(prevMat.getNilaiAguPenilaiIndep(),"#,###") + " menjadi " + Formater.formatNumber(this.getNilaiAguPenilaiIndep(),"#,###") + "<br>";
                }
            }
            
            //17
            if (prevMat == null || !prevMat.getNamaPenilaiIndep().equals(this.getNamaPenilaiIndep())) {
                if (prevMat == null) {
                    history = history + " Nama Penilai Independen : " + this.getNamaPenilaiIndep() + "<br>";
                } else {
                    history = history + " Nama Penilai Independen diubah dari " + prevMat.getNamaPenilaiIndep() + " menjadi " + this.getNamaPenilaiIndep() + "<br>";
                }
            }
            
            //18
            if (prevMat == null || !prevMat.getTglPenilaianPenilaiIndep().equals(this.getTglPenilaianPenilaiIndep())) {
                if (prevMat == null) {
                    history = history + " Tanggal Penilaian Penilai Independen : " + this.getTglPenilaianPenilaiIndep() + "<br>";
                } else {
                    history = history + " Tanggal Penilaian Penilai Independen diubah dari " + prevMat.getTglPenilaianPenilaiIndep() + " menjadi " + this.getTglPenilaianPenilaiIndep() + "<br>";
                }
            }
            
            //19
            if (prevMat == null || prevMat.getStatusParipasu() != this.getStatusParipasu()) {
                if (prevMat == null) {
                    history = history + " Status Paripasu : " + this.getStatusParipasu() + "<br>";
                } else {
                    history = history + " Status Paripasu diubah dari " + prevMat.getStatusParipasu() + " menjadi " + this.getStatusParipasu() + "<br>";
                }
            }
            
            //20
            if (prevMat == null || !prevMat.getStatusKreditJoin().equals(this.getStatusKreditJoin())) {
                if (prevMat == null) {
                    history = history + " Status Kredit Join : " + this.getStatusKreditJoin() + "<br>";
                } else {
                    history = history + " Status Kredit Join diubah dari " + prevMat.getStatusKreditJoin() + " menjadi " + this.getStatusKreditJoin() + "<br>";
                }
            }
            
            //21
            if (prevMat == null || !prevMat.getDiasuransikan().equals(this.getDiasuransikan())) {
                if (prevMat == null) {
                    history = history + " Diasuransikan : " + this.getDiasuransikan() + "<br>";
                } else {
                    history = history + " Diasuransikan diubah dari " + prevMat.getDiasuransikan() + " menjadi " + this.getDiasuransikan() + "<br>";
                }
            }
            
            //22
            if (prevMat == null || !prevMat.getKeterangan().equals(this.getKeterangan())) {
                if (prevMat == null) {
                    history = history + " Keterangan : " + this.getKeterangan() + "<br>";
                } else {
                    history = history + " Keterangan diubah dari " + prevMat.getKeterangan() + " menjadi " + this.getKeterangan() + "<br>";
                }
            }
            
            //23
            if (prevMat == null || !prevMat.getKodeKantorCabang().equals(this.getKodeKantorCabang())) {
                if (prevMat == null) {
                    history = history + " Keterangan : " + this.getKodeKantorCabang() + "<br>";
                } else {
                    history = history + " Keterangan diubah dari " + prevMat.getKeterangan() + " menjadi " + this.getKeterangan() + "<br>";
                }
            }
            
            //24
            if (prevMat == null || !prevMat.getOperasiData().equals(this.getOperasiData())) {
                if (prevMat == null) {
                    history = history + " Operasi Data : " + this.getOperasiData() + "<br>";
                } else {
                    history = history + " Operasi Data diubah dari " + prevMat.getOperasiData() + " menjadi " + this.getOperasiData() + "<br>";
                }
            }
            
            //25
            if (prevMat == null || !prevMat.getOpenDate().equals(this.getOpenDate())) {
                if (prevMat == null) {
                    history = history + " Open Date : " + this.getOpenDate() + "<br>";
                } else {
                    history = history + " Open Date diubah dari " + prevMat.getOpenDate() + " menjadi " + this.getOpenDate() + "<br>";
                }
            }
            
            //26
            if (prevMat == null || prevMat.getStatusData() != this.getStatusData()) {
                if (prevMat == null) {
                    history = history + " Status Data : " + this.getStatusData() + "<br>";
                } else {
                    history = history + " Status Data diubah dari " + prevMat.getStatusData() + " menjadi " + this.getStatusData() + "<br>";
                }
                
            }
                        
        }catch(Exception e){
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
     * @return the prosentaseParipasu
     */
    public double getProsentaseParipasu() {
        return prosentaseParipasu;
    }

    /**
     * @param prosentaseParipasu the prosentaseParipasu to set
     */
    public void setProsentaseParipasu(double prosentaseParipasu) {
        this.prosentaseParipasu = prosentaseParipasu;
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
     * @return the statusParipasu
     */
    public String getStatusParipasu() {
        return statusParipasu;
    }

    /**
     * @param statusParipasu the statusParipasu to set
     */
    public void setStatusParipasu(String statusParipasu) {
        this.statusParipasu = statusParipasu;
    }

    /**
     * @return the statusDataPerubahan
     */
    public int getStatusDataPerubahan() {
        return statusDataPerubahan;
    }

    /**
     * @param statusDataPerubahan the statusDataPerubahan to set
     */
    public void setStatusDataPerubahan(int statusDataPerubahan) {
        this.statusDataPerubahan = statusDataPerubahan;
    }

    /**
     * @return the kodeJenisSegementFasilitas
     */
    public String getKodeJenisSegementFasilitas() {
        return kodeJenisSegementFasilitas;
    }

    /**
     * @param kodeJenisSegementFasilitas the kodeJenisSegementFasilitas to set
     */
    public void setKodeJenisSegementFasilitas(String kodeJenisSegementFasilitas) {
        this.kodeJenisSegementFasilitas = kodeJenisSegementFasilitas;
    }

    /**
     * @return the countRegisterAgunan
     */
    public int getCountRegisterAgunan() {
        return countRegisterAgunan;
    }

    /**
     * @param countRegisterAgunan the countRegisterAgunan to set
     */
    public void setCountRegisterAgunan(int countRegisterAgunan) {
        this.countRegisterAgunan = countRegisterAgunan;
    }

    /**
     * @return the sumRekeningAgunan
     */
    public int getSumRekeningAgunan() {
        return sumRekeningAgunan;
    }

    /**
     * @param sumRekeningAgunan the sumRekeningAgunan to set
     */
    public void setSumRekeningAgunan(int sumRekeningAgunan) {
        this.sumRekeningAgunan = sumRekeningAgunan;
    }

    /**
     * @return the sumTotalAgunan
     */
    public double getSumTotalAgunan() {
        return sumTotalAgunan;
    }

    /**
     * @param sumTotalAgunan the sumTotalAgunan to set
     */
    public void setSumTotalAgunan(double sumTotalAgunan) {
        this.sumTotalAgunan = sumTotalAgunan;
    }

    /**
     * @return the sumBakiPerAgunan
     */
    public double getSumBakiPerAgunan() {
        return sumBakiPerAgunan;
    }

    /**
     * @param sumBakiPerAgunan the sumBakiPerAgunan to set
     */
    public void setSumBakiPerAgunan(double sumBakiPerAgunan) {
        this.sumBakiPerAgunan = sumBakiPerAgunan;
    }

}
