/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.laporankeuangandebitur;

import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.qdep.entity.Entity;
import com.dimata.util.Formater;
import java.util.Date;

/**
 *
 * @author Dewa
 */
public class LaporanKeuanganDebitur extends Entity implements I_LogHistory{

    private String flagDetail = "";
    private String cif = "";
    private double aset = 0;
    private double asetLancar = 0;
    private double kasDanSetaraKas = 0;
    private double piutangUsahaAstLncr = 0;
    private double investasiAstLncr = 0;
    private double asetLancarLain = 0;
    private double asetTidakLancar = 0;
    private double piutangUsahaAstTdkLncr = 0;
    private double investasiAstTdkLncr = 0;
    private double asetTdkLncrLain = 0;
    private double liabilitas = 0;
    private double liabilitasJnkPndk = 0;
    private double pinjamanJnkPndk = 0;
    private double utangUsahaLiaJnkPndk = 0;
    private double liabilitasJnkPndkLain = 0;
    private double liabilitasJnkPnjg = 0;
    private double pinjamanJnkPnjg = 0;
    private double utangUsahaLiaJnkPnjg = 0;
    private double liabilitasJnkPnjgLain = 0;
    private double ekuitas = 0;
    private double pendapatanUsahaOpr = 0;
    private double bebanPokokPend = 0;
    private double labaRugiBruto = 0;
    private double pendLainNonOpr = 0;
    private double bebanLainNonOpr = 0;
    private double labaRugiSblmPajak = 0;
    private double labaRugiPeriode = 0;
    private String kodeKantorCabang = "";
    private String operasiData = "";
    private Date openDate = null;
    private int statusData = 0;
    private int statusPerubahanData = 0;
    private Date posisiLaporanKeuanganTahunan = null;
    
    //ADD BY ARI
    private long periodeId = 0;
    private long debiturOid = 0;
    private int debiturType = 0;
    
    //ADD BY DE KOYO
    private String sqlHIstory="";
    
    private int statusOperasiData = 0;

    
    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
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

    public double getAset() {
        return aset;
    }

    public void setAset(double aset) {
        this.aset = aset;
    }

    public double getAsetLancar() {
        return asetLancar;
    }

    public void setAsetLancar(double asetLancar) {
        this.asetLancar = asetLancar;
    }

    public double getKasDanSetaraKas() {
        return kasDanSetaraKas;
    }

    public void setKasDanSetaraKas(double kasDanSetaraKas) {
        this.kasDanSetaraKas = kasDanSetaraKas;
    }

    public double getPiutangUsahaAstLncr() {
        return piutangUsahaAstLncr;
    }

    public void setPiutangUsahaAstLncr(double piutangUsahaAstLncr) {
        this.piutangUsahaAstLncr = piutangUsahaAstLncr;
    }

    public double getInvestasiAstLncr() {
        return investasiAstLncr;
    }

    public void setInvestasiAstLncr(double investasiAstLncr) {
        this.investasiAstLncr = investasiAstLncr;
    }

    public double getAsetLancarLain() {
        return asetLancarLain;
    }

    public void setAsetLancarLain(double asetLancarLain) {
        this.asetLancarLain = asetLancarLain;
    }

    public double getAsetTidakLancar() {
        return asetTidakLancar;
    }

    public void setAsetTidakLancar(double asetTidakLancar) {
        this.asetTidakLancar = asetTidakLancar;
    }

    public double getPiutangUsahaAstTdkLncr() {
        return piutangUsahaAstTdkLncr;
    }

    public void setPiutangUsahaAstTdkLncr(double piutangUsahaAstTdkLncr) {
        this.piutangUsahaAstTdkLncr = piutangUsahaAstTdkLncr;
    }

    public double getInvestasiAstTdkLncr() {
        return investasiAstTdkLncr;
    }

    public void setInvestasiAstTdkLncr(double investasiAstTdkLncr) {
        this.investasiAstTdkLncr = investasiAstTdkLncr;
    }

    public double getAsetTdkLncrLain() {
        return asetTdkLncrLain;
    }

    public void setAsetTdkLncrLain(double asetTdkLncrLain) {
        this.asetTdkLncrLain = asetTdkLncrLain;
    }

    public double getLiabilitas() {
        return liabilitas;
    }

    public void setLiabilitas(double liabilitas) {
        this.liabilitas = liabilitas;
    }

    public double getLiabilitasJnkPndk() {
        return liabilitasJnkPndk;
    }

    public void setLiabilitasJnkPndk(double liabilitasJnkPndk) {
        this.liabilitasJnkPndk = liabilitasJnkPndk;
    }

    public double getPinjamanJnkPndk() {
        return pinjamanJnkPndk;
    }

    public void setPinjamanJnkPndk(double pinjamanJnkPndk) {
        this.pinjamanJnkPndk = pinjamanJnkPndk;
    }

    public double getUtangUsahaLiaJnkPndk() {
        return utangUsahaLiaJnkPndk;
    }

    public void setUtangUsahaLiaJnkPndk(double utangUsahaLiaJnkPndk) {
        this.utangUsahaLiaJnkPndk = utangUsahaLiaJnkPndk;
    }

    public double getLiabilitasJnkPndkLain() {
        return liabilitasJnkPndkLain;
    }

    public void setLiabilitasJnkPndkLain(double liabilitasJnkPndkLain) {
        this.liabilitasJnkPndkLain = liabilitasJnkPndkLain;
    }

    public double getLiabilitasJnkPnjg() {
        return liabilitasJnkPnjg;
    }

    public void setLiabilitasJnkPnjg(double liabilitasJnkPnjg) {
        this.liabilitasJnkPnjg = liabilitasJnkPnjg;
    }

    public double getPinjamanJnkPnjg() {
        return pinjamanJnkPnjg;
    }

    public void setPinjamanJnkPnjg(double pinjamanJnkPnjg) {
        this.pinjamanJnkPnjg = pinjamanJnkPnjg;
    }

    public double getUtangUsahaLiaJnkPnjg() {
        return utangUsahaLiaJnkPnjg;
    }

    public void setUtangUsahaLiaJnkPnjg(double utangUsahaLiaJnkPnjg) {
        this.utangUsahaLiaJnkPnjg = utangUsahaLiaJnkPnjg;
    }

    public double getLiabilitasJnkPnjgLain() {
        return liabilitasJnkPnjgLain;
    }

    public void setLiabilitasJnkPnjgLain(double liabilitasJnkPnjgLain) {
        this.liabilitasJnkPnjgLain = liabilitasJnkPnjgLain;
    }

    public double getEkuitas() {
        return ekuitas;
    }

    public void setEkuitas(double ekuitas) {
        this.ekuitas = ekuitas;
    }

    public double getPendapatanUsahaOpr() {
        return pendapatanUsahaOpr;
    }

    public void setPendapatanUsahaOpr(double pendapatanUsahaOpr) {
        this.pendapatanUsahaOpr = pendapatanUsahaOpr;
    }

    public double getBebanPokokPend() {
        return bebanPokokPend;
    }

    public void setBebanPokokPend(double bebanPokokPend) {
        this.bebanPokokPend = bebanPokokPend;
    }

    public double getLabaRugiBruto() {
        return labaRugiBruto;
    }

    public void setLabaRugiBruto(double labaRugiBruto) {
        this.labaRugiBruto = labaRugiBruto;
    }

    public double getPendLainNonOpr() {
        return pendLainNonOpr;
    }

    public void setPendLainNonOpr(double pendLainNonOpr) {
        this.pendLainNonOpr = pendLainNonOpr;
    }

    public double getBebanLainNonOpr() {
        return bebanLainNonOpr;
    }

    public void setBebanLainNonOpr(double bebanLainNonOpr) {
        this.bebanLainNonOpr = bebanLainNonOpr;
    }

    public double getLabaRugiSblmPajak() {
        return labaRugiSblmPajak;
    }

    public void setLabaRugiSblmPajak(double labaRugiSblmPajak) {
        this.labaRugiSblmPajak = labaRugiSblmPajak;
    }

    public double getLabaRugiPeriode() {
        return labaRugiPeriode;
    }

    public void setLabaRugiPeriode(double labaRugiPeriode) {
        this.labaRugiPeriode = labaRugiPeriode;
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
        LaporanKeuanganDebitur prevMat = (LaporanKeuanganDebitur)prevDoc;
        
        try {
            if (prevMat == null || !prevMat.getFlagDetail().equals(this.getFlagDetail())) {
                if (prevMat == null) {
                    history = history + " Flag Detail : " + this.getFlagDetail() + "<br>";
                } else {
                    history = history + " Flag Detail diubah dari " + prevMat.getFlagDetail() + " menjadi " + this.getFlagDetail() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getCif().equals(this.getCif())) {
                if (prevMat == null) {
                    history = history + " CIF : " + this.getCif()+ "<br>";
                } else {
                    history = history + " CIF diubah dari " + prevMat.getCif()+ " menjadi " + this.getCif()+ "<br>";
                }
            }
            if (prevMat == null || prevMat.getAset() != this.getAset()) {
                if (prevMat == null) {
                    history = history + " Aset : " + this.getAset() + "<br>";
                } else {
                    history = history + " Aset diubah dari " + prevMat.getAset() + " menjadi " + this.getAset() + "<br>";
                }
            }
            if (prevMat == null || prevMat.getAsetLancar() != this.getAsetLancar()) {
                if (prevMat == null) {
                    history = history + " Aset Lancar : " + Formater.formatNumber(this.getAsetLancar(),"#,###") + "<br>";
                } else {
                    history = history + " Aset Lancar diubah dari " + Formater.formatNumber(prevMat.getAsetLancar(),"#,###") + " menjadi " + Formater.formatNumber(this.getAsetLancar(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getKasDanSetaraKas() != this.getKasDanSetaraKas()) {
                if (prevMat == null) {
                    history = history + " Kas dan Setara Kas (Aset Lancar) : " + Formater.formatNumber(this.getKasDanSetaraKas(),"#,###") + "<br>";
                } else {
                    history = history + " Kas dan Setara Kas (Aset Lancar) diubah dari " + Formater.formatNumber(prevMat.getKasDanSetaraKas(),"#,###") + " menjadi " + Formater.formatNumber(this.getKasDanSetaraKas(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getPiutangUsahaAstLncr() != this.getPiutangUsahaAstLncr()) {
                if (prevMat == null) {
                    history = history + " Piutang Usaha/Pembiayaan (Aset Lancar) : " + Formater.formatNumber(this.getPiutangUsahaAstLncr(),"#,###")+ "<br>";
                } else {
                    history = history + " Piutang Usaha/Pembiayaan (Aset Lancar) diubah dari " + Formater.formatNumber(prevMat.getPiutangUsahaAstLncr(),"#,###")+ " menjadi " + Formater.formatNumber(this.getPiutangUsahaAstLncr(),"#,###")+ "<br>";
                }
            }
            if (prevMat == null || prevMat.getInvestasiAstLncr() != this.getInvestasiAstLncr()) {
                if (prevMat == null) {
                    history = history + " Investasi/Aset Keuangan Lainnya (Aset Lancar) : " + Formater.formatNumber(this.getInvestasiAstLncr(),"#,###")+ "<br>";
                } else {
                    history = history + " Investasi/Aset Keuangan Lainnya (Aset Lancar) diubah dari " + Formater.formatNumber(prevMat.getInvestasiAstLncr(),"#,###")+ " menjadi " + Formater.formatNumber(this.getInvestasiAstLncr(),"#,###")+ "<br>";
                }
            }
            if (prevMat == null || prevMat.getAsetLancarLain() != this.getAsetLancarLain()) {
                if (prevMat == null) {
                    history = history + " Aset Lancar Lainnya (Aset Lancar) : " + Formater.formatNumber(this.getAsetLancarLain(),"#,###")+ "<br>";
                } else {
                    history = history + " Aset Lancar Lainnya (Aset Lancar) diubah dari " + Formater.formatNumber(prevMat.getAsetLancarLain(),"#,###")+ " menjadi " + Formater.formatNumber(this.getAsetLancarLain(),"#,###")+ "<br>";
                }
            }
            if (prevMat == null || prevMat.getAsetTidakLancar() != this.getAsetTidakLancar()) {
                if (prevMat == null) {
                    history = history + " Aset Tidak Lancar : " + Formater.formatNumber(this.getAsetTidakLancar(),"#,###") + "<br>";
                } else {
                    history = history + " Aset Tidak Lancar diubah dari " + Formater.formatNumber(prevMat.getAsetTidakLancar(),"#,###") + " menjadi " + Formater.formatNumber(this.getAsetTidakLancar(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getPiutangUsahaAstTdkLncr() != this.getPiutangUsahaAstTdkLncr()) {
                if (prevMat == null) {
                    history = history + " Piutang Usaha Aset Tidak Lancar : " + Formater.formatNumber(this.getPiutangUsahaAstTdkLncr(),"#,###") + "<br>";
                } else {
                    history = history + " Piutang Usaha Aset Tidak Lancar diubah dari " + Formater.formatNumber(prevMat.getPiutangUsahaAstTdkLncr(),"#,###") + " menjadi " + Formater.formatNumber(this.getPiutangUsahaAstTdkLncr(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getInvestasiAstTdkLncr() != this.getInvestasiAstTdkLncr()) {
                if (prevMat == null) {
                    history = history + " Investasi/Aset Keuangan Lainnya (Aset Tidak Lancar) : " + Formater.formatNumber(this.getInvestasiAstTdkLncr(),"#,###") + "<br>";
                } else {
                    history = history + " Investasi/Aset Keuangan Lainnya (Aset Tidak Lancar) diubah dari " + Formater.formatNumber(prevMat.getInvestasiAstTdkLncr(),"#,###") + " menjadi " + Formater.formatNumber(this.getInvestasiAstTdkLncr(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getAsetTdkLncrLain() != this.getAsetTdkLncrLain()) {
                if (prevMat == null) {
                    history = history + " Aset Tidak Lancar Lainnya : " + Formater.formatNumber(this.getAsetTdkLncrLain(),"#,###") + "<br>";
                } else {
                    history = history + " Aset Tidak Lancar Lainnya diubah dari " + Formater.formatNumber(prevMat.getAsetTdkLncrLain(),"#,###") + " menjadi " + Formater.formatNumber(this.getAsetTdkLncrLain(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getLiabilitas() != this.getLiabilitas()) {
                if (prevMat == null) {
                    history = history + " Liabilitas : " + Formater.formatNumber(this.getLiabilitas(),"#,###") + "<br>";
                } else {
                    history = history + " Liabilitas diubah dari " + Formater.formatNumber(prevMat.getLiabilitas(),"#,###") + " menjadi " + Formater.formatNumber(this.getLiabilitas(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getLiabilitasJnkPndk() != this.getLiabilitasJnkPndk()) {
                if (prevMat == null) {
                    history = history + " Liabilitas Jangka Pendek : " + Formater.formatNumber(this.getLiabilitasJnkPndk(),"#,###") + "<br>";
                } else {
                    history = history + " Liabilitas Jangka Pendek diubah dari " + Formater.formatNumber(prevMat.getLiabilitasJnkPndk(),"#,###") + " menjadi " + Formater.formatNumber(this.getLiabilitasJnkPndk(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getPinjamanJnkPndk() != this.getPinjamanJnkPndk()) {
                if (prevMat == null) {
                    history = history + " Pinjaman Jangka Pendek(Liabilitas Jangka Pendek) : " + Formater.formatNumber(this.getPinjamanJnkPndk(),"#,###") + "<br>";
                } else {
                    history = history + " Pinjaman Jangka Pendek(Liabilitas Jangka Pendek) diubah dari " + Formater.formatNumber(prevMat.getPinjamanJnkPndk(),"#,###") + " menjadi " + Formater.formatNumber(this.getPinjamanJnkPndk(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getUtangUsahaLiaJnkPndk() != this.getUtangUsahaLiaJnkPndk()) {
                if (prevMat == null) {
                    history = history + " Utang Usaha(Liabilitas Jangka Pendek) : " + Formater.formatNumber(this.getUtangUsahaLiaJnkPndk(),"#,###") + "<br>";
                } else {
                    history = history + " Utang Usaha(Liabilitas Jangka Pendek) diubah dari " + Formater.formatNumber(prevMat.getUtangUsahaLiaJnkPndk(),"#,###") + " menjadi " + Formater.formatNumber(this.getUtangUsahaLiaJnkPndk(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getLiabilitasJnkPndkLain() != this.getLiabilitasJnkPndkLain()) {
                if (prevMat == null) {
                    history = history + " Liabilitas Jangka Pendek Lain : " + Formater.formatNumber(this.getLiabilitasJnkPndkLain(),"#,###") + "<br>";
                } else {
                    history = history + " Liabilitas Jangka Pendek Lain diubah dari " + Formater.formatNumber(prevMat.getLiabilitasJnkPndkLain(),"#,###") + " menjadi " + Formater.formatNumber(this.getLiabilitasJnkPndkLain(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getLiabilitasJnkPnjg() != this.getLiabilitasJnkPnjg()) {
                if (prevMat == null) {
                    history = history + " Liabilitas Jangka Panjang : " + Formater.formatNumber(this.getLiabilitasJnkPnjg(),"#,###") + "<br>";
                } else {
                    history = history + " Liabilitas Jangka Panjang diubah dari " + Formater.formatNumber(prevMat.getLiabilitasJnkPnjg (),"#,###") + " menjadi " + Formater.formatNumber(this.getLiabilitasJnkPnjg(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getPinjamanJnkPnjg() != this.getPinjamanJnkPnjg()) {
                if (prevMat == null) {
                    history = history + " Pinjaman Jangka Panjang : " + Formater.formatNumber(this.getPinjamanJnkPnjg(),"#,###") + "<br>";
                } else {
                    history = history + " Pinjaman Jangka Panjang diubah dari " + Formater.formatNumber(prevMat.getPinjamanJnkPnjg (),"#,###") + " menjadi " + Formater.formatNumber(this.getPinjamanJnkPnjg(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getUtangUsahaLiaJnkPnjg() != this.getUtangUsahaLiaJnkPnjg()) {
                if (prevMat == null) {
                    history = history + " Utang Usaha (Liabilitas Jangka Panjang) : " + Formater.formatNumber(this.getUtangUsahaLiaJnkPnjg(),"#,###") + "<br>";
                } else {
                    history = history + " Utang Usaha (Liabilitas Jangka Panjang) diubah dari " + Formater.formatNumber(prevMat.getUtangUsahaLiaJnkPnjg (),"#,###") + " menjadi " + Formater.formatNumber(this.getUtangUsahaLiaJnkPnjg(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getLiabilitasJnkPnjgLain() != this.getLiabilitasJnkPnjgLain()) {
                if (prevMat == null) {
                    history = history + " Liabilitas Jangka Panjang Lain : " + Formater.formatNumber(this.getLiabilitasJnkPnjgLain(),"#,###") + "<br>";
                } else {
                    history = history + " Liabilitas Jangka Panjang Lain diubah dari " + Formater.formatNumber(prevMat.getLiabilitasJnkPnjgLain (),"#,###") + " menjadi " + Formater.formatNumber(this.getLiabilitasJnkPnjgLain(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getEkuitas() != this.getEkuitas()) {
                if (prevMat == null) {
                    history = history + " Ekuitas : " + Formater.formatNumber(this.getEkuitas(),"#,###") + "<br>";
                } else {
                    history = history + " Ekuitas diubah dari " + Formater.formatNumber(prevMat.getEkuitas (),"#,###") + " menjadi " + Formater.formatNumber(this.getEkuitas(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getPendapatanUsahaOpr() != this.getPendapatanUsahaOpr()) {
                if (prevMat == null) {
                    history = history + " Pendapatan Usaha / Operasional : " + Formater.formatNumber(this.getPendapatanUsahaOpr(),"#,###") + "<br>";
                } else {
                    history = history + " Pendapatan Usaha / Operasional diubah dari " + Formater.formatNumber(prevMat.getPendapatanUsahaOpr (),"#,###") + " menjadi " + Formater.formatNumber(this.getPendapatanUsahaOpr(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getBebanPokokPend() != this.getBebanPokokPend()) {
                if (prevMat == null) {
                    history = history + " Beban Pokok Pendapatan/Beban Operasional : " + Formater.formatNumber(this.getBebanPokokPend(),"#,###") + "<br>";
                } else {
                    history = history + " Beban Pokok Pendapatan/Beban Operasional diubah dari " + Formater.formatNumber(prevMat.getBebanPokokPend (),"#,###") + " menjadi " + Formater.formatNumber(this.getBebanPokokPend(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getLabaRugiBruto() != this.getLabaRugiBruto()) {
                if (prevMat == null) {
                    history = history + " Laba/Rugi Bruto : " + Formater.formatNumber(this.getLabaRugiBruto(),"#,###") + "<br>";
                } else {
                    history = history + " Laba/Rugi Bruto diubah dari " + Formater.formatNumber(prevMat.getLabaRugiBruto (),"#,###") + " menjadi " + Formater.formatNumber(this.getLabaRugiBruto(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getPendLainNonOpr() != this.getPendLainNonOpr()) {
                if (prevMat == null) {
                    history = history + " Pendapatan Lain-lain/Non-Operasional : " + Formater.formatNumber(this.getPendLainNonOpr(),"#,###") + "<br>";
                } else {
                    history = history + " Pendapatan Lain-lain/Non-Operasional diubah dari " + Formater.formatNumber(prevMat.getPendLainNonOpr() ,"#,###") + " menjadi " + Formater.formatNumber(this.getPendLainNonOpr(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getBebanLainNonOpr() != this.getBebanLainNonOpr()) {
                if (prevMat == null) {
                    history = history + " Beban Lain-lain/Non-Operasional : " + Formater.formatNumber(this.getBebanLainNonOpr(),"#,###") + "<br>";
                } else {
                    history = history + " Beban Lain-lain/Non-Operasional diubah dari " + Formater.formatNumber(prevMat.getBebanLainNonOpr (),"#,###") + " menjadi " + Formater.formatNumber(this.getBebanLainNonOpr(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getLabaRugiSblmPajak() != this.getLabaRugiSblmPajak()) {
                if (prevMat == null) {
                    history = history + " Laba/Rugi Sebelum Pajak : " + Formater.formatNumber(this.getLabaRugiSblmPajak(),"#,###") + "<br>";
                } else {
                    history = history + " Laba/Rugi Sebelum Pajak diubah dari " + Formater.formatNumber(prevMat.getLabaRugiSblmPajak (),"#,###") + " menjadi " + Formater.formatNumber(this.getLabaRugiSblmPajak(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || prevMat.getLabaRugiPeriode() != this.getLabaRugiPeriode()) {
                if (prevMat == null) {
                    history = history + " Laba/Rugi Periode/Tahun Berjalan : " + Formater.formatNumber(this.getLabaRugiPeriode(),"#,###") + "<br>";
                } else {
                    history = history + " Laba/Rugi Periode/Tahun Berjalan diubah dari " + Formater.formatNumber(prevMat.getLabaRugiPeriode (),"#,###") + " menjadi " + Formater.formatNumber(this.getLabaRugiPeriode(),"#,###") + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getKodeKantorCabang().equals(this.getKodeKantorCabang())) {
                if (prevMat == null) {
                    history = history + " Kode Kantor Cabang : " + this.getKodeKantorCabang() + "<br>";
                } else {
                    history = history + " Kode Kantor Cabang diubah dari " + prevMat.getKodeKantorCabang () + " menjadi " + this.getKodeKantorCabang() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getOperasiData().equals(this.getOperasiData())) {
                if (prevMat == null) {
                    history = history + " Operasi Data : " + this.getOperasiData() + "<br>";
                } else {
                    history = history + " Operasi Data diubah dari " + prevMat.getOperasiData () + " menjadi " + this.getOperasiData() + "<br>";
                }
            }
            if (prevMat == null || !prevMat.getOpenDate().equals(this.getOpenDate())) {
                if (prevMat == null) {
                    history = history + " Open Date : " + this.getOpenDate() + "<br>";
                } else {
                    history = history + " Open Date diubah dari " + prevMat.getOpenDate () + " menjadi " + this.getOpenDate() + "<br>";
                }
            }
            if (prevMat == null || prevMat.getStatusData() != this.getStatusData()) {
                if (prevMat == null) {
                    history = history + " Status Data : " + this.getStatusData() + "<br>";
                } else {
                    history = history + " Status Data diubah dari " + prevMat.getStatusData () + " menjadi " + this.getStatusData() + "<br>";
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

    /**
     * @return the posisiLaporanKeuanganTahunan
     */
    public Date getPosisiLaporanKeuanganTahunan() {
        return posisiLaporanKeuanganTahunan;
    }

    /**
     * @param posisiLaporanKeuanganTahunan the posisiLaporanKeuanganTahunan to set
     */
    public void setPosisiLaporanKeuanganTahunan(Date posisiLaporanKeuanganTahunan) {
        this.posisiLaporanKeuanganTahunan = posisiLaporanKeuanganTahunan;
    }

}
