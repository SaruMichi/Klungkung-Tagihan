/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.fasilitaslain;

import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.qdep.entity.Entity;
import com.dimata.util.Formater;
import java.util.Date;

/**
 *
 * @author Dewa
 */
public class FasilitasLain extends Entity implements I_LogHistory{

    private String flagDetail = "";
    private String noRekening = "";
    private String cif = "";
    private String kodeJenisFasilitas = "";
    private String sumberDana = "";
    private Date tglMulai = null;
    private Date tglJatuhTempo = null;
    private double sukuBunga = 0;
    private String kodeValuta = "";
    private double nominalJmlKewajiban = 0;
    private double nilaiMataUangAsal = 0;
    private String kodeKolektibilitas = "";
    private Date tglMacet = null;
    private String kodeSebabMacet = "";
    private double tunggakan = 0;
    private double jmlHariTunggakan = 0;
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
    private String namaJenisFasilitas = "";
    private String sqlHistory = "";
    private String namaSumberDana = "";
    private int statusOperasiData = 0;
    private int statusPerubahanData=0;
    
    
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

    public String getKodeJenisFasilitas() {
        return kodeJenisFasilitas;
    }

    public void setKodeJenisFasilitas(String kodeJenisFasilitas) {
        if (kodeJenisFasilitas==null){
            this.kodeJenisFasilitas = "";
        }else{
            this.kodeJenisFasilitas = kodeJenisFasilitas;
        }
        
    }

    public String getSumberDana() {
        return sumberDana;
    }

    public void setSumberDana(String sumberDana) {
        if  (sumberDana==null){
            this.sumberDana = "";
        }else{
            this.sumberDana = sumberDana;
        }
        
    }

    public Date getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(Date tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Date getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(Date tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

    public double getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(double sukuBunga) {
        this.sukuBunga = sukuBunga;
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

    public double getNominalJmlKewajiban() {
        return nominalJmlKewajiban;
    }

    public void setNominalJmlKewajiban(double nominalJmlKewajiban) {
        this.nominalJmlKewajiban = nominalJmlKewajiban;
    }

    public double getNilaiMataUangAsal() {
        return nilaiMataUangAsal;
    }

    public void setNilaiMataUangAsal(double nilaiMataUangAsal) {
        this.nilaiMataUangAsal = nilaiMataUangAsal;
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

    public double getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(double tunggakan) {
        this.tunggakan = tunggakan;
    }

    public double getJmlHariTunggakan() {
        return jmlHariTunggakan;
    }

    public void setJmlHariTunggakan(double jmlHariTunggakan) {
        this.jmlHariTunggakan = jmlHariTunggakan;
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String history="";
        FasilitasLain prevMat = (FasilitasLain)prevDoc;
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
                    history = history + " Nomor Rekening : " + this.getNoRekening()+ "<br>";
                } else {
                    history = history + " Nomor Rekening diubah dari " + prevMat.getNoRekening()+ " menjadi " + this.getNoRekening()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getCif().equals(this.getCif())) {
                if (prevMat == null) {
                    history = history + " CIF : " + this.getCif() + "<br>";
                } else {
                    history = history + " CIF diubah dari " + prevMat.getCif() + " menjadi " + this.getCif() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeJenisFasilitas().equals(this.getKodeJenisFasilitas())) {
                if (prevMat == null) {
                    history = history + " Kode Jenis Fasilitas : " + this.getKodeJenisFasilitas()+ "<br>";
                } else {
                    history = history + " Kode Jenis Fasilitas diubah dari " + prevMat.getKodeJenisFasilitas()+ " menjadi " + this.getKodeJenisFasilitas()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getSumberDana().equals(this.getSumberDana())) {
                if (prevMat == null) {
                    history = history + " Sumber Dana : " + this.getSumberDana()+ "<br>";
                } else {
                    history = history + " Sumber Dana diubah dari " + prevMat.getSumberDana()+ " menjadi " + this.getSumberDana()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglMulai().equals(this.getTglMulai())) {
                if (prevMat == null) {
                    history = history + " Tanggal Mulai : " + this.getTglMulai()+ "<br>";
                } else {
                    history = history + " Tanggal Mulai diubah dari " + prevMat.getTglMulai()+ " menjadi " + this.getTglMulai()+ "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglJatuhTempo().equals(this.getTglJatuhTempo())) {
                if (prevMat == null) {
                    history = history + " Tanggal Jatuh Tempo : " + this.getTglJatuhTempo()+ "<br>";
                } else {
                    history = history + " Tanggal Jatuh Tempo diubah dari " + prevMat.getTglJatuhTempo()+ " menjadi " + this.getTglJatuhTempo()+ "<br>";
                }
            }
            if (prevMat == null || prevMat.getSukuBunga() != this.getSukuBunga()) {
                if (prevMat == null) {
                    history = history + " Suku Bunga/Imbalan : " + this.getSukuBunga() + "<br>";
                } else {
                    history = history + " Suku Bunga/Imbalan diubah dari " + prevMat.getSukuBunga() + " menjadi " + this.getSukuBunga() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeValuta().equals(this.getKodeValuta())) {
                if (prevMat == null) {
                    history = history + " Kode Valuta : " + this.getKodeValuta() + "<br>";
                } else {
                    history = history + " Kode Valuta diubah dari " + prevMat.getKodeValuta() + " menjadi " + this.getKodeValuta() + "<br>";
                }
            }
            if (prevMat == null || prevMat.getNominalJmlKewajiban() != this.getNominalJmlKewajiban()) {
                if (prevMat == null) {
                    history = history + " Nominal/Jumlah Kewajiban (IDR) : " + this.getNominalJmlKewajiban() + "<br>";
                } else {
                    history = history + " Nominal/Jumlah Kewajiban (IDR) diubah dari " + prevMat.getNominalJmlKewajiban() + " menjadi " + this.getNominalJmlKewajiban() + "<br>";
                }
            }
            if (prevMat == null || prevMat.getNilaiMataUangAsal() != this.getNilaiMataUangAsal()) {
                if (prevMat == null) {
                    history = history + " Nilai Dalam Mata Uang Asal : " + Formater.formatNumber(this.getNilaiMataUangAsal(),"#,###") + "<br>";
                } else {
                    history = history + " Nilai Dalam Mata Uang Asal diubah dari " + Formater.formatNumber(prevMat.getNilaiMataUangAsal(),"#,###") + " menjadi " + Formater.formatNumber(this.getNilaiMataUangAsal(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeKolektibilitas().equals(this.getKodeKolektibilitas())) {
                if (prevMat == null) {
                    history = history + " Kode Kolektibilitas : " + this.getKodeKolektibilitas() + "<br>";
                } else {
                    history = history + " Kode Kolektibilitas diubah dari " + prevMat.getKodeKolektibilitas() + " menjadi " + this.getKodeKolektibilitas() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getTglMacet().equals(this.getTglMacet())) {
                if (prevMat == null) {
                    history = history + " Tanggal Macet : " + this.getTglMacet() + "<br>";
                } else {
                    history = history + " Tanggal Macet diubah dari " + prevMat.getTglMacet() + " menjadi " + this.getTglMacet() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeSebabMacet().equals(this.getKodeSebabMacet())) {
                if (prevMat == null) {
                    history = history + " Kode Sebab Macet : " + this.getKodeSebabMacet() + "<br>";
                } else {
                    history = history + " Kode Sebab Macet diubah dari " + prevMat.getKodeSebabMacet() + " menjadi " + this.getKodeSebabMacet() + "<br>";
                }
            }
            if (prevMat == null || prevMat.getTunggakan() != this.getTunggakan()) {
                if (prevMat == null) {
                    history = history + " Tunggakan : " + Formater.formatNumber(this.getTunggakan(),"#,###") + "<br>";
                } else {
                    history = history + " Tunggakan diubah dari " + Formater.formatNumber(prevMat.getTunggakan(),"#,###") + " menjadi " + Formater.formatNumber(this.getTunggakan(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getJmlHariTunggakan() != this.getJmlHariTunggakan()) {
                if (prevMat == null) {
                    history = history + " Jumlah Hari Tunggakan : " + this.getJmlHariTunggakan() + "<br>";
                } else {
                    history = history + " Jumlah Hari Tunggakan diubah dari " + prevMat.getJmlHariTunggakan() + " menjadi " + this.getJmlHariTunggakan() + "<br>";
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
                    history = history + " Kode kantor Cabang diubah dari " + prevMat.getKodeKantorCabang() + " menjadi " + this.getKodeKantorCabang() + "<br>";
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
     * @return the namaJenisFasilitas
     */
    public String getNamaJenisFasilitas() {
        return namaJenisFasilitas;
    }

    /**
     * @param namaJenisFasilitas the namaJenisFasilitas to set
     */
    public void setNamaJenisFasilitas(String namaJenisFasilitas) {
        this.namaJenisFasilitas = namaJenisFasilitas;
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
     * @return the namaSumberDana
     */
    public String getNamaSumberDana() {
        return namaSumberDana;
    }

    /**
     * @param namaSumberDana the namaSumberDana to set
     */
    public void setNamaSumberDana(String namaSumberDana) {
        this.namaSumberDana = namaSumberDana;
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
