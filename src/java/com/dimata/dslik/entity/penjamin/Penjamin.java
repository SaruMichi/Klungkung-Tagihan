/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.penjamin;

import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.qdep.entity.Entity;
import java.util.Date;

/**
 *
 * @author Dewa
 */
public class Penjamin extends Entity implements I_LogHistory {

    private String flagDetail = "";
    private String noIdPenjamin = "";
    private String noRekening = "";
    private String cif = "";
    private String jenisIdentitas = "";
    private String namaIdentitas = "";
    private String namaLengkap = "";
    private String kodeGolPenjamin = "";
    private String alamatPenjamin = "";
    private int prosentaseDijamin = 0;
    private String keterangan = "";
    private String kodeKantorCabang = "";
    private String operasiData = "";
    private Date openDate = null;
    private int statusData = 0;
    private String sqlHistory = "";
    private String kodeJenisSegmentFasilitas = "";
    
    //ADD BY ARI
    private long periodeId = 0;
    private long debiturOid = 0;
    private int debiturType = 0;
    private int statusOperasiData = 0;
    private int statusPerubahanData = 0;

    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
    }

    public String getNoIdPenjamin() {
        return noIdPenjamin;
    }

    public void setNoIdPenjamin(String noIdPenjamin) {
        if (noIdPenjamin==null){
            this.noIdPenjamin = "";
        }else{
            this.noIdPenjamin = noIdPenjamin;
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

    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    public void setJenisIdentitas(String jenisIdentitas) {
        if (jenisIdentitas==null){
            this.jenisIdentitas = "";
        }else{
            this.jenisIdentitas = jenisIdentitas;
        }
        
    }

    public String getNamaIdentitas() {
        return namaIdentitas;
    }

    public void setNamaIdentitas(String namaIdentitas) {
        if (namaIdentitas==null){
            this.namaIdentitas = "";
        }else{
            this.namaIdentitas = namaIdentitas;
        }    
        
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        if (namaLengkap==null){
            this.namaLengkap = "";
        }else{
            this.namaLengkap = namaLengkap;
        }
        
    }

    public String getKodeGolPenjamin() {
        return kodeGolPenjamin;
    }

    public void setKodeGolPenjamin(String kodeGolPenjamin) {
        if (kodeGolPenjamin==null){
            this.kodeGolPenjamin = "";
        }else{
            this.kodeGolPenjamin = kodeGolPenjamin;
        }
        
    }

    public String getAlamatPenjamin() {
        return alamatPenjamin;
    }

    public void setAlamatPenjamin(String alamatPenjamin) {
        if (alamatPenjamin==null){
            this.alamatPenjamin = "";
        }else{
            this.alamatPenjamin = alamatPenjamin;
        }
        
    }

    public int getProsentaseDijamin() {
        return prosentaseDijamin;
    }

    public void setProsentaseDijamin(int prosentaseDijamin) {
        this.prosentaseDijamin = prosentaseDijamin;
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
//         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String history = "";
        Penjamin prevMat = (Penjamin) prevDoc;
        try {
            if (prevMat == null || !prevMat.getFlagDetail().equals(this.getFlagDetail())) {
                if (prevMat == null) {
                    history = history + " Flag Detail : " + this.getFlagDetail() + "<br>";
                } else {
                    history = history + " Flag Detail diubah dari " + prevMat.getFlagDetail() + " menjadi " + this.getFlagDetail() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getNoIdPenjamin().equals(this.getNoIdPenjamin())) {
                if (prevMat == null) {
                    history = history + " Nomor ID Penjamin : " + this.getNoIdPenjamin()+ "<br>";
                } else {
                    history = history + " Nomor ID Penjamin diubah dari " + prevMat.getNoIdPenjamin()+ " menjadi " + this.getNoIdPenjamin()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getNoRekening().equals(this.getNoRekening())) {
                if (prevMat == null) {
                    history = history + " Nomor Rekening : " + this.getNoRekening()+ "<br>";
                } else {
                    history = history + " Nomor Rekening diubah dari " + prevMat.getNoRekening()+ " menjadi " + this.getNoRekening()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getCif().equals(this.getCif())) {
                if (prevMat == null) {
                    history = history + " CIF : " + this.getCif()+ "<br>";
                } else {
                    history = history + " CIF diubah dari " + prevMat.getCif()+ " menjadi " + this.getCif()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getJenisIdentitas().equals(this.getJenisIdentitas())) {
                if (prevMat == null) {
                    history = history + " Jenis Identitas : " + this.getJenisIdentitas()+ "<br>";
                } else {
                    history = history + " Jenis Identitas diubah dari " + prevMat.getJenisIdentitas()+ " menjadi " + this.getJenisIdentitas()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getNamaIdentitas().equals(this.getNamaIdentitas())) {
                if (prevMat == null) {
                    history = history + " Nama Sesuai Identitas : " + this.getNamaIdentitas()+ "<br>";
                } else {
                    history = history + " Nama Sesuai Identitas diubah dari " + prevMat.getNamaIdentitas()+ " menjadi " + this.getNamaIdentitas()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getNamaLengkap().equals(this.getNamaLengkap())) {
                if (prevMat == null) {
                    history = history + " Nama Lengkap : " + this.getNamaLengkap()+ "<br>";
                } else {
                    history = history + " Nama Lengkap diubah dari " + prevMat.getNamaLengkap()+ " menjadi " + this.getNamaLengkap()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeGolPenjamin().equals(this.getKodeGolPenjamin())) {
                if (prevMat == null) {
                    history = history + " Kode Golongan Penjamin : " + this.getKodeGolPenjamin()+ "<br>";
                } else {
                    history = history + " Kode Golongan Penjamin diubah dari " + prevMat.getKodeGolPenjamin()+ " menjadi " + this.getKodeGolPenjamin()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getAlamatPenjamin().equals(this.getAlamatPenjamin())) {
                if (prevMat == null) {
                    history = history + " Alamat Penjamin : " + this.getAlamatPenjamin()+ "<br>";
                } else {
                    history = history + " Alamat Penjamin diubah dari " + prevMat.getAlamatPenjamin()+ " menjadi " + this.getAlamatPenjamin()+ "<br>";
                }
            }
            if (prevMat == null || prevMat.getProsentaseDijamin() != this.getProsentaseDijamin()) {
                if (prevMat == null) {
                    history = history + " Prosentase Dijamin : " + this.getProsentaseDijamin()+ "<br>";
                } else {
                    history = history + " Prosentase Dijamin diubah dari " + prevMat.getProsentaseDijamin()+ " menjadi " + this.getProsentaseDijamin()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKeterangan().equals(this.getKeterangan())) {
                if (prevMat == null) {
                    history = history + " Keterangan : " + this.getKeterangan()+ "<br>";
                } else {
                    history = history + " Keterangan diubah dari " + prevMat.getKeterangan()+ " menjadi " + this.getKeterangan()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeKantorCabang().equals(this.getKodeKantorCabang())) {
                if (prevMat == null) {
                    history = history + " Kode Kantor Cabang : " + this.getKodeKantorCabang()+ "<br>";
                } else {
                    history = history + " Kode Kantor Cabang diubah dari " + prevMat.getKodeKantorCabang()+ " menjadi " + this.getKodeKantorCabang()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getOperasiData().equals(this.getOperasiData())) {
                if (prevMat == null) {
                    history = history + " Operasi Data : " + this.getOperasiData()+ "<br>";
                } else {
                    history = history + " Operasi Data diubah dari " + prevMat.getOperasiData()+ " menjadi " + this.getOperasiData()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getOpenDate().equals(this.getOpenDate())) {
                if (prevMat == null) {
                    history = history + " Open Date : " + this.getOpenDate()+ "<br>";
                } else {
                    history = history + " Open Date diubah dari " + prevMat.getOpenDate()+ " menjadi " + this.getOpenDate()+ "<br>";
                }
            }
            if (prevMat == null || prevMat.getStatusData() != this.getStatusData()) {
                if (prevMat == null) {
                    history = history + " Status Data : " + this.getStatusData()+ "<br>";
                } else {
                    history = history + " Status Data diubah dari " + prevMat.getStatusData()+ " menjadi " + this.getStatusData()+ "<br>";
                }
            }
        } catch (Exception e) {
            System.out.println("" + e.toString() + "");
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
     * @return the kodeJenisSegmentFasilitas
     */
    public String getKodeJenisSegmentFasilitas() {
        return kodeJenisSegmentFasilitas;
    }

    /**
     * @param kodeJenisSegmentFasilitas the kodeJenisSegmentFasilitas to set
     */
    public void setKodeJenisSegmentFasilitas(String kodeJenisSegmentFasilitas) {
        this.kodeJenisSegmentFasilitas = kodeJenisSegmentFasilitas;
    }

}
