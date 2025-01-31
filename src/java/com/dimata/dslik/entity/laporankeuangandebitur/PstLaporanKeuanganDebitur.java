/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.laporankeuangandebitur;

import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

/**
 *
 * @author Dewa
 */
public class PstLaporanKeuanganDebitur extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    private String sqlQueryHistory = "";
    
    public static final String TBL_LAPORAN_KEUANGAN_DEBITUR = "dslik_laporan_keuangan_debitur";
    public static final int FLD_LAPORAN_KEUANGAN_DEBITUR_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_CIF = 2;
    public static final int FLD_ASET = 3;
    public static final int FLD_ASET_LANCAR = 4;
    public static final int FLD_KAS_DAN_SETARA_KAS = 5;
    public static final int FLD_PIUTANG_USAHA_AST_LNCR = 6;
    public static final int FLD_INVESTASI_AST_LNCR = 7;
    public static final int FLD_ASET_LANCAR_LAIN = 8;
    public static final int FLD_ASET_TIDAK_LANCAR = 9;
    public static final int FLD_PIUTANG_USAHA_AST_TDK_LNCR = 10;
    public static final int FLD_INVESTASI_AST_TDK_LNCR = 11;
    public static final int FLD_ASET_TDK_LNCR_LAIN = 12;
    public static final int FLD_LIABILITAS = 13;
    public static final int FLD_LIABILITAS_JNK_PNDK = 14;
    public static final int FLD_PINJAMAN_JNK_PNDK = 15;
    public static final int FLD_UTANG_USAHA_LIA_JNK_PNDK = 16;
    public static final int FLD_LIABILITAS_JNK_PNDK_LAIN = 17;
    public static final int FLD_LIABILITAS_JNK_PNJG = 18;
    public static final int FLD_PINJAMAN_JNK_PNJG = 19;
    public static final int FLD_UTANG_USAHA_LIA_JNK_PNJG = 20;
    public static final int FLD_LIABILITAS_JNK_PNJG_LAIN = 21;
    public static final int FLD_EKUITAS = 22;
    public static final int FLD_PENDAPATAN_USAHA_OPR = 23;
    public static final int FLD_BEBAN_POKOK_PEND = 24;
    public static final int FLD_LABA_RUGI_BRUTO = 25;
    public static final int FLD_PEND_LAIN_NON_OPR = 26;
    public static final int FLD_BEBAN_LAIN_NON_OPR = 27;
    public static final int FLD_LABA_RUGI_SBLM_PAJAK = 28;
    public static final int FLD_LABA_RUGI_PERIODE = 29;
    public static final int FLD_KODE_KANTOR_CABANG = 30;
    public static final int FLD_OPERASI_DATA = 31;
    public static final int FLD_OPEN_DATE = 32;
    public static final int FLD_STATUS_DATA = 33;
    public static final int FLD_PERIODE_ID = 34;
    public static final int FLD_STATUS_PERUBAHAN_DATA = 35;
    public static final int FLD_POSISI_LAPORAN_KEUANGAN_TAHUNAN = 36;

    public static String[] fieldNames = {
        "LAP_KEU_DEB_OID",
        "FLAG_DETAIL",
        "CIF",
        "ASET",
        "ASET_LANCAR",
        "KAS_DAN_SETARA_KAS",
        "PIUTANG_USAHA_AST_LNCR",
        "INVESTASI_AST_LNCR",
        "ASET_LANCAR_LAIN",
        "ASET_TIDAK_LANCAR",
        "PIUTANG_USAHA_AST_TDK_LNCR",
        "INVESTASI_AST_TDK_LNCR",
        "ASET_TDK_LNCR_LAIN",
        "LIABILITAS",
        "LIABILITAS_JNK_PNDK",
        "PINJAMAN_JNK_PNDK",
        "UTANG_USAHA_LIA_JNK_PNDK",
        "LIABILITAS_JNK_PNDK_LAIN",
        "LIABILITAS_JNK_PNJG",
        "PINJAMAN_JNK_PNJG",
        "UTANG_USAHA_LIA_JNK_PNJG",
        "LIABILITAS_JNK_PNJG_LAIN",
        "EKUITAS",
        "PENDAPATAN_USAHA_OPR",
        "BEBAN_POKOK_PEND",
        "LABA_RUGI_BRUTO",
        "PEND_LAIN_NON_OPR",
        "BEBAN_LAIN_NON_OPR",
        "LABA_RUGI_SBLM_PAJAK",
        "LABA_RUGI_PERIODE",
        "KODE_KANTOR_CABANG",
        "OPERASI_DATA",
        "OPEN_DATE",
        "STATUS_OPERASI_DATA",
        "PERIODE_ID",
        "STATUS_DATA",
        "POSISI_LAPORAN_KEUANGAN_TAHUNAN"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_INT,
        TYPE_LONG,
        TYPE_INT,
        TYPE_DATE
    };

    public PstLaporanKeuanganDebitur() {
    }

    public PstLaporanKeuanganDebitur(int i) throws DBException {
        super(new PstLaporanKeuanganDebitur());
    }

    public PstLaporanKeuanganDebitur(String sOid) throws DBException {
        super(new PstLaporanKeuanganDebitur(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstLaporanKeuanganDebitur(long lOid) throws DBException {
        super(new PstLaporanKeuanganDebitur(0));
        String sOid = "0";
        try {
            sOid = String.valueOf(lOid);
        } catch (Exception e) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public String getTableName() {
        return TBL_LAPORAN_KEUANGAN_DEBITUR;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstLaporanKeuanganDebitur().getClass().getName();
    }

    public static LaporanKeuanganDebitur fetchExc(long oid) throws DBException {
        try {
            LaporanKeuanganDebitur entLaporanKeuanganDebitur = new LaporanKeuanganDebitur();
            PstLaporanKeuanganDebitur pstLaporanKeuanganDebitur = new PstLaporanKeuanganDebitur(oid);
            entLaporanKeuanganDebitur.setOID(oid);
            entLaporanKeuanganDebitur.setFlagDetail(pstLaporanKeuanganDebitur.getString(FLD_FLAG_DETAIL));
            entLaporanKeuanganDebitur.setCif(pstLaporanKeuanganDebitur.getString(FLD_CIF));
            entLaporanKeuanganDebitur.setAset(pstLaporanKeuanganDebitur.getdouble(FLD_ASET));
            entLaporanKeuanganDebitur.setAsetLancar(pstLaporanKeuanganDebitur.getdouble(FLD_ASET_LANCAR));
            entLaporanKeuanganDebitur.setKasDanSetaraKas(pstLaporanKeuanganDebitur.getdouble(FLD_KAS_DAN_SETARA_KAS));
            entLaporanKeuanganDebitur.setPiutangUsahaAstLncr(pstLaporanKeuanganDebitur.getdouble(FLD_PIUTANG_USAHA_AST_LNCR));
            entLaporanKeuanganDebitur.setInvestasiAstLncr(pstLaporanKeuanganDebitur.getdouble(FLD_INVESTASI_AST_LNCR));
            entLaporanKeuanganDebitur.setAsetLancarLain(pstLaporanKeuanganDebitur.getdouble(FLD_ASET_LANCAR_LAIN));
            entLaporanKeuanganDebitur.setAsetTidakLancar(pstLaporanKeuanganDebitur.getdouble(FLD_ASET_TIDAK_LANCAR));
            entLaporanKeuanganDebitur.setPiutangUsahaAstTdkLncr(pstLaporanKeuanganDebitur.getdouble(FLD_PIUTANG_USAHA_AST_TDK_LNCR));
            entLaporanKeuanganDebitur.setInvestasiAstTdkLncr(pstLaporanKeuanganDebitur.getdouble(FLD_INVESTASI_AST_TDK_LNCR));
            entLaporanKeuanganDebitur.setAsetTdkLncrLain(pstLaporanKeuanganDebitur.getdouble(FLD_ASET_TDK_LNCR_LAIN));
            entLaporanKeuanganDebitur.setLiabilitas(pstLaporanKeuanganDebitur.getdouble(FLD_LIABILITAS));
            entLaporanKeuanganDebitur.setLiabilitasJnkPndk(pstLaporanKeuanganDebitur.getdouble(FLD_LIABILITAS_JNK_PNDK));
            entLaporanKeuanganDebitur.setPinjamanJnkPndk(pstLaporanKeuanganDebitur.getdouble(FLD_PINJAMAN_JNK_PNDK));
            entLaporanKeuanganDebitur.setUtangUsahaLiaJnkPndk(pstLaporanKeuanganDebitur.getdouble(FLD_UTANG_USAHA_LIA_JNK_PNDK));
            entLaporanKeuanganDebitur.setLiabilitasJnkPndkLain(pstLaporanKeuanganDebitur.getdouble(FLD_LIABILITAS_JNK_PNDK_LAIN));
            entLaporanKeuanganDebitur.setLiabilitasJnkPnjg(pstLaporanKeuanganDebitur.getdouble(FLD_LIABILITAS_JNK_PNJG));
            entLaporanKeuanganDebitur.setPinjamanJnkPnjg(pstLaporanKeuanganDebitur.getdouble(FLD_PINJAMAN_JNK_PNJG));
            entLaporanKeuanganDebitur.setUtangUsahaLiaJnkPnjg(pstLaporanKeuanganDebitur.getdouble(FLD_UTANG_USAHA_LIA_JNK_PNJG));
            entLaporanKeuanganDebitur.setLiabilitasJnkPnjgLain(pstLaporanKeuanganDebitur.getdouble(FLD_LIABILITAS_JNK_PNJG_LAIN));
            entLaporanKeuanganDebitur.setEkuitas(pstLaporanKeuanganDebitur.getdouble(FLD_EKUITAS));
            entLaporanKeuanganDebitur.setPendapatanUsahaOpr(pstLaporanKeuanganDebitur.getdouble(FLD_PENDAPATAN_USAHA_OPR));
            entLaporanKeuanganDebitur.setBebanPokokPend(pstLaporanKeuanganDebitur.getdouble(FLD_BEBAN_POKOK_PEND));
            entLaporanKeuanganDebitur.setLabaRugiBruto(pstLaporanKeuanganDebitur.getdouble(FLD_LABA_RUGI_BRUTO));
            entLaporanKeuanganDebitur.setPendLainNonOpr(pstLaporanKeuanganDebitur.getdouble(FLD_PEND_LAIN_NON_OPR));
            entLaporanKeuanganDebitur.setBebanLainNonOpr(pstLaporanKeuanganDebitur.getdouble(FLD_BEBAN_LAIN_NON_OPR));
            entLaporanKeuanganDebitur.setLabaRugiSblmPajak(pstLaporanKeuanganDebitur.getdouble(FLD_LABA_RUGI_SBLM_PAJAK));
            entLaporanKeuanganDebitur.setLabaRugiPeriode(pstLaporanKeuanganDebitur.getdouble(FLD_LABA_RUGI_PERIODE));
            entLaporanKeuanganDebitur.setKodeKantorCabang(pstLaporanKeuanganDebitur.getString(FLD_KODE_KANTOR_CABANG));
            entLaporanKeuanganDebitur.setOperasiData(pstLaporanKeuanganDebitur.getString(FLD_OPERASI_DATA));
            entLaporanKeuanganDebitur.setOpenDate(pstLaporanKeuanganDebitur.getDate(FLD_OPEN_DATE));
            entLaporanKeuanganDebitur.setStatusData(pstLaporanKeuanganDebitur.getInt(FLD_STATUS_DATA));
            entLaporanKeuanganDebitur.setPeriodeId(pstLaporanKeuanganDebitur.getlong(FLD_PERIODE_ID));
            entLaporanKeuanganDebitur.setStatusPerubahanData(pstLaporanKeuanganDebitur.getInt(FLD_STATUS_PERUBAHAN_DATA));
            entLaporanKeuanganDebitur.setPosisiLaporanKeuanganTahunan(pstLaporanKeuanganDebitur.getDate(FLD_POSISI_LAPORAN_KEUANGAN_TAHUNAN));
            return entLaporanKeuanganDebitur;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLaporanKeuanganDebitur(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        LaporanKeuanganDebitur entLaporanKeuanganDebitur = fetchExc(entity.getOID());
        entity = (Entity) entLaporanKeuanganDebitur;
        return entLaporanKeuanganDebitur.getOID();
    }

    public static synchronized long updateExc(LaporanKeuanganDebitur entLaporanKeuanganDebitur) throws DBException {
        try {
            if (entLaporanKeuanganDebitur.getOID() != 0) {
                PstLaporanKeuanganDebitur pstLaporanKeuanganDebitur = new PstLaporanKeuanganDebitur(entLaporanKeuanganDebitur.getOID());
                pstLaporanKeuanganDebitur.setString(FLD_FLAG_DETAIL, entLaporanKeuanganDebitur.getFlagDetail());
                pstLaporanKeuanganDebitur.setString(FLD_CIF, entLaporanKeuanganDebitur.getCif());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET, entLaporanKeuanganDebitur.getAset());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET_LANCAR, entLaporanKeuanganDebitur.getAsetLancar());
                pstLaporanKeuanganDebitur.setDouble(FLD_KAS_DAN_SETARA_KAS, entLaporanKeuanganDebitur.getKasDanSetaraKas());
                pstLaporanKeuanganDebitur.setDouble(FLD_PIUTANG_USAHA_AST_LNCR, entLaporanKeuanganDebitur.getPiutangUsahaAstLncr());
                pstLaporanKeuanganDebitur.setDouble(FLD_INVESTASI_AST_LNCR, entLaporanKeuanganDebitur.getInvestasiAstLncr());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET_LANCAR_LAIN, entLaporanKeuanganDebitur.getAsetLancarLain());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET_TIDAK_LANCAR, entLaporanKeuanganDebitur.getAsetTidakLancar());
                pstLaporanKeuanganDebitur.setDouble(FLD_PIUTANG_USAHA_AST_TDK_LNCR, entLaporanKeuanganDebitur.getPiutangUsahaAstTdkLncr());
                pstLaporanKeuanganDebitur.setDouble(FLD_INVESTASI_AST_TDK_LNCR, entLaporanKeuanganDebitur.getInvestasiAstTdkLncr());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET_TDK_LNCR_LAIN, entLaporanKeuanganDebitur.getAsetTdkLncrLain());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS, entLaporanKeuanganDebitur.getLiabilitas());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNDK, entLaporanKeuanganDebitur.getLiabilitasJnkPndk());
                pstLaporanKeuanganDebitur.setDouble(FLD_PINJAMAN_JNK_PNDK, entLaporanKeuanganDebitur.getPinjamanJnkPndk());
                pstLaporanKeuanganDebitur.setDouble(FLD_UTANG_USAHA_LIA_JNK_PNDK, entLaporanKeuanganDebitur.getUtangUsahaLiaJnkPndk());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNDK_LAIN, entLaporanKeuanganDebitur.getLiabilitasJnkPndkLain());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNJG, entLaporanKeuanganDebitur.getLiabilitasJnkPnjg());
                pstLaporanKeuanganDebitur.setDouble(FLD_PINJAMAN_JNK_PNJG, entLaporanKeuanganDebitur.getPinjamanJnkPnjg());
                pstLaporanKeuanganDebitur.setDouble(FLD_UTANG_USAHA_LIA_JNK_PNJG, entLaporanKeuanganDebitur.getUtangUsahaLiaJnkPnjg());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNJG_LAIN, entLaporanKeuanganDebitur.getLiabilitasJnkPnjgLain());
                pstLaporanKeuanganDebitur.setDouble(FLD_EKUITAS, entLaporanKeuanganDebitur.getEkuitas());
                pstLaporanKeuanganDebitur.setDouble(FLD_PENDAPATAN_USAHA_OPR, entLaporanKeuanganDebitur.getPendapatanUsahaOpr());
                pstLaporanKeuanganDebitur.setDouble(FLD_BEBAN_POKOK_PEND, entLaporanKeuanganDebitur.getBebanPokokPend());
                pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_BRUTO, entLaporanKeuanganDebitur.getLabaRugiBruto());
                pstLaporanKeuanganDebitur.setDouble(FLD_PEND_LAIN_NON_OPR, entLaporanKeuanganDebitur.getPendLainNonOpr());
                pstLaporanKeuanganDebitur.setDouble(FLD_BEBAN_LAIN_NON_OPR, entLaporanKeuanganDebitur.getBebanLainNonOpr());
                pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_SBLM_PAJAK, entLaporanKeuanganDebitur.getLabaRugiSblmPajak());
                pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_PERIODE, entLaporanKeuanganDebitur.getLabaRugiPeriode());
                pstLaporanKeuanganDebitur.setString(FLD_KODE_KANTOR_CABANG, entLaporanKeuanganDebitur.getKodeKantorCabang());
                pstLaporanKeuanganDebitur.setString(FLD_OPERASI_DATA, entLaporanKeuanganDebitur.getOperasiData());
                pstLaporanKeuanganDebitur.setDate(FLD_OPEN_DATE, entLaporanKeuanganDebitur.getOpenDate());
                pstLaporanKeuanganDebitur.setInt(FLD_STATUS_DATA, entLaporanKeuanganDebitur.getStatusData());
                pstLaporanKeuanganDebitur.setLong(FLD_PERIODE_ID, entLaporanKeuanganDebitur.getPeriodeId());
                pstLaporanKeuanganDebitur.setInt(FLD_STATUS_PERUBAHAN_DATA, entLaporanKeuanganDebitur.getStatusPerubahanData());
                pstLaporanKeuanganDebitur.setDate(FLD_POSISI_LAPORAN_KEUANGAN_TAHUNAN, entLaporanKeuanganDebitur.getPosisiLaporanKeuanganTahunan());
                pstLaporanKeuanganDebitur.update();
                
                pstLaporanKeuanganDebitur.setSqlQueryHistory("");
                pstLaporanKeuanganDebitur.setSqlQueryHistory(pstLaporanKeuanganDebitur.getUpdateSQL());
                
                return entLaporanKeuanganDebitur.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLaporanKeuanganDebitur(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized LaporanKeuanganDebitur updateExcObj(LaporanKeuanganDebitur entLaporanKeuanganDebitur) throws DBException {
        try {
            if (entLaporanKeuanganDebitur.getOID() != 0) {
                PstLaporanKeuanganDebitur pstLaporanKeuanganDebitur = new PstLaporanKeuanganDebitur(entLaporanKeuanganDebitur.getOID());
                pstLaporanKeuanganDebitur.setString(FLD_FLAG_DETAIL, entLaporanKeuanganDebitur.getFlagDetail());
                pstLaporanKeuanganDebitur.setString(FLD_CIF, entLaporanKeuanganDebitur.getCif());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET, entLaporanKeuanganDebitur.getAset());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET_LANCAR, entLaporanKeuanganDebitur.getAsetLancar());
                pstLaporanKeuanganDebitur.setDouble(FLD_KAS_DAN_SETARA_KAS, entLaporanKeuanganDebitur.getKasDanSetaraKas());
                pstLaporanKeuanganDebitur.setDouble(FLD_PIUTANG_USAHA_AST_LNCR, entLaporanKeuanganDebitur.getPiutangUsahaAstLncr());
                pstLaporanKeuanganDebitur.setDouble(FLD_INVESTASI_AST_LNCR, entLaporanKeuanganDebitur.getInvestasiAstLncr());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET_LANCAR_LAIN, entLaporanKeuanganDebitur.getAsetLancarLain());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET_TIDAK_LANCAR, entLaporanKeuanganDebitur.getAsetTidakLancar());
                pstLaporanKeuanganDebitur.setDouble(FLD_PIUTANG_USAHA_AST_TDK_LNCR, entLaporanKeuanganDebitur.getPiutangUsahaAstTdkLncr());
                pstLaporanKeuanganDebitur.setDouble(FLD_INVESTASI_AST_TDK_LNCR, entLaporanKeuanganDebitur.getInvestasiAstTdkLncr());
                pstLaporanKeuanganDebitur.setDouble(FLD_ASET_TDK_LNCR_LAIN, entLaporanKeuanganDebitur.getAsetTdkLncrLain());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS, entLaporanKeuanganDebitur.getLiabilitas());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNDK, entLaporanKeuanganDebitur.getLiabilitasJnkPndk());
                pstLaporanKeuanganDebitur.setDouble(FLD_PINJAMAN_JNK_PNDK, entLaporanKeuanganDebitur.getPinjamanJnkPndk());
                pstLaporanKeuanganDebitur.setDouble(FLD_UTANG_USAHA_LIA_JNK_PNDK, entLaporanKeuanganDebitur.getUtangUsahaLiaJnkPndk());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNDK_LAIN, entLaporanKeuanganDebitur.getLiabilitasJnkPndkLain());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNJG, entLaporanKeuanganDebitur.getLiabilitasJnkPnjg());
                pstLaporanKeuanganDebitur.setDouble(FLD_PINJAMAN_JNK_PNJG, entLaporanKeuanganDebitur.getPinjamanJnkPnjg());
                pstLaporanKeuanganDebitur.setDouble(FLD_UTANG_USAHA_LIA_JNK_PNJG, entLaporanKeuanganDebitur.getUtangUsahaLiaJnkPnjg());
                pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNJG_LAIN, entLaporanKeuanganDebitur.getLiabilitasJnkPnjgLain());
                pstLaporanKeuanganDebitur.setDouble(FLD_EKUITAS, entLaporanKeuanganDebitur.getEkuitas());
                pstLaporanKeuanganDebitur.setDouble(FLD_PENDAPATAN_USAHA_OPR, entLaporanKeuanganDebitur.getPendapatanUsahaOpr());
                pstLaporanKeuanganDebitur.setDouble(FLD_BEBAN_POKOK_PEND, entLaporanKeuanganDebitur.getBebanPokokPend());
                pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_BRUTO, entLaporanKeuanganDebitur.getLabaRugiBruto());
                pstLaporanKeuanganDebitur.setDouble(FLD_PEND_LAIN_NON_OPR, entLaporanKeuanganDebitur.getPendLainNonOpr());
                pstLaporanKeuanganDebitur.setDouble(FLD_BEBAN_LAIN_NON_OPR, entLaporanKeuanganDebitur.getBebanLainNonOpr());
                pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_SBLM_PAJAK, entLaporanKeuanganDebitur.getLabaRugiSblmPajak());
                pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_PERIODE, entLaporanKeuanganDebitur.getLabaRugiPeriode());
                pstLaporanKeuanganDebitur.setString(FLD_KODE_KANTOR_CABANG, entLaporanKeuanganDebitur.getKodeKantorCabang());
                pstLaporanKeuanganDebitur.setString(FLD_OPERASI_DATA, entLaporanKeuanganDebitur.getOperasiData());
                pstLaporanKeuanganDebitur.setDate(FLD_OPEN_DATE, entLaporanKeuanganDebitur.getOpenDate());
                pstLaporanKeuanganDebitur.setInt(FLD_STATUS_DATA, entLaporanKeuanganDebitur.getStatusData());
                pstLaporanKeuanganDebitur.setLong(FLD_PERIODE_ID, entLaporanKeuanganDebitur.getPeriodeId());
                pstLaporanKeuanganDebitur.setInt(FLD_STATUS_PERUBAHAN_DATA, entLaporanKeuanganDebitur.getStatusPerubahanData());
                pstLaporanKeuanganDebitur.setDate(FLD_POSISI_LAPORAN_KEUANGAN_TAHUNAN, entLaporanKeuanganDebitur.getPosisiLaporanKeuanganTahunan());
                pstLaporanKeuanganDebitur.update();
                
                pstLaporanKeuanganDebitur.setSqlQueryHistory("");
                pstLaporanKeuanganDebitur.setSqlQueryHistory(pstLaporanKeuanganDebitur.getUpdateSQL());
                entLaporanKeuanganDebitur.setSqlHIstory(pstLaporanKeuanganDebitur.getUpdateSQL());
                return entLaporanKeuanganDebitur;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLaporanKeuanganDebitur(0), DBException.UNKNOWN);
        }
        return entLaporanKeuanganDebitur;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((LaporanKeuanganDebitur) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstLaporanKeuanganDebitur pstLaporanKeuanganDebitur = new PstLaporanKeuanganDebitur(oid);
            pstLaporanKeuanganDebitur.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLaporanKeuanganDebitur(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(LaporanKeuanganDebitur entLaporanKeuanganDebitur) throws DBException {
        try {
            PstLaporanKeuanganDebitur pstLaporanKeuanganDebitur = new PstLaporanKeuanganDebitur(0);
            pstLaporanKeuanganDebitur.setString(FLD_FLAG_DETAIL, entLaporanKeuanganDebitur.getFlagDetail());
            pstLaporanKeuanganDebitur.setString(FLD_CIF, entLaporanKeuanganDebitur.getCif());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET, entLaporanKeuanganDebitur.getAset());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET_LANCAR, entLaporanKeuanganDebitur.getAsetLancar());
            pstLaporanKeuanganDebitur.setDouble(FLD_KAS_DAN_SETARA_KAS, entLaporanKeuanganDebitur.getKasDanSetaraKas());
            pstLaporanKeuanganDebitur.setDouble(FLD_PIUTANG_USAHA_AST_LNCR, entLaporanKeuanganDebitur.getPiutangUsahaAstLncr());
            pstLaporanKeuanganDebitur.setDouble(FLD_INVESTASI_AST_LNCR, entLaporanKeuanganDebitur.getInvestasiAstLncr());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET_LANCAR_LAIN, entLaporanKeuanganDebitur.getAsetLancarLain());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET_TIDAK_LANCAR, entLaporanKeuanganDebitur.getAsetTidakLancar());
            pstLaporanKeuanganDebitur.setDouble(FLD_PIUTANG_USAHA_AST_TDK_LNCR, entLaporanKeuanganDebitur.getPiutangUsahaAstTdkLncr());
            pstLaporanKeuanganDebitur.setDouble(FLD_INVESTASI_AST_TDK_LNCR, entLaporanKeuanganDebitur.getInvestasiAstTdkLncr());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET_TDK_LNCR_LAIN, entLaporanKeuanganDebitur.getAsetTdkLncrLain());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS, entLaporanKeuanganDebitur.getLiabilitas());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNDK, entLaporanKeuanganDebitur.getLiabilitasJnkPndk());
            pstLaporanKeuanganDebitur.setDouble(FLD_PINJAMAN_JNK_PNDK, entLaporanKeuanganDebitur.getPinjamanJnkPndk());
            pstLaporanKeuanganDebitur.setDouble(FLD_UTANG_USAHA_LIA_JNK_PNDK, entLaporanKeuanganDebitur.getUtangUsahaLiaJnkPndk());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNDK_LAIN, entLaporanKeuanganDebitur.getLiabilitasJnkPndkLain());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNJG, entLaporanKeuanganDebitur.getLiabilitasJnkPnjg());
            pstLaporanKeuanganDebitur.setDouble(FLD_PINJAMAN_JNK_PNJG, entLaporanKeuanganDebitur.getPinjamanJnkPnjg());
            pstLaporanKeuanganDebitur.setDouble(FLD_UTANG_USAHA_LIA_JNK_PNJG, entLaporanKeuanganDebitur.getUtangUsahaLiaJnkPnjg());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNJG_LAIN, entLaporanKeuanganDebitur.getLiabilitasJnkPnjgLain());
            pstLaporanKeuanganDebitur.setDouble(FLD_EKUITAS, entLaporanKeuanganDebitur.getEkuitas());
            pstLaporanKeuanganDebitur.setDouble(FLD_PENDAPATAN_USAHA_OPR, entLaporanKeuanganDebitur.getPendapatanUsahaOpr());
            pstLaporanKeuanganDebitur.setDouble(FLD_BEBAN_POKOK_PEND, entLaporanKeuanganDebitur.getBebanPokokPend());
            pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_BRUTO, entLaporanKeuanganDebitur.getLabaRugiBruto());
            pstLaporanKeuanganDebitur.setDouble(FLD_PEND_LAIN_NON_OPR, entLaporanKeuanganDebitur.getPendLainNonOpr());
            pstLaporanKeuanganDebitur.setDouble(FLD_BEBAN_LAIN_NON_OPR, entLaporanKeuanganDebitur.getBebanLainNonOpr());
            pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_SBLM_PAJAK, entLaporanKeuanganDebitur.getLabaRugiSblmPajak());
            pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_PERIODE, entLaporanKeuanganDebitur.getLabaRugiPeriode());
            pstLaporanKeuanganDebitur.setString(FLD_KODE_KANTOR_CABANG, entLaporanKeuanganDebitur.getKodeKantorCabang());
            pstLaporanKeuanganDebitur.setString(FLD_OPERASI_DATA, entLaporanKeuanganDebitur.getOperasiData());
            pstLaporanKeuanganDebitur.setDate(FLD_OPEN_DATE, entLaporanKeuanganDebitur.getOpenDate());
            pstLaporanKeuanganDebitur.setInt(FLD_STATUS_DATA, entLaporanKeuanganDebitur.getStatusData());
            pstLaporanKeuanganDebitur.setLong(FLD_PERIODE_ID, entLaporanKeuanganDebitur.getPeriodeId());
            pstLaporanKeuanganDebitur.setInt(FLD_STATUS_PERUBAHAN_DATA, entLaporanKeuanganDebitur.getStatusPerubahanData());
            pstLaporanKeuanganDebitur.setDate(FLD_POSISI_LAPORAN_KEUANGAN_TAHUNAN, entLaporanKeuanganDebitur.getPosisiLaporanKeuanganTahunan());
            pstLaporanKeuanganDebitur.insert();
            
            pstLaporanKeuanganDebitur.setSqlQueryHistory("");
            pstLaporanKeuanganDebitur.setSqlQueryHistory(pstLaporanKeuanganDebitur.getInsertSQL());
            
            entLaporanKeuanganDebitur.setOID(pstLaporanKeuanganDebitur.getLong(FLD_LAPORAN_KEUANGAN_DEBITUR_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLaporanKeuanganDebitur(0), DBException.UNKNOWN);
        }
        return entLaporanKeuanganDebitur.getOID();
    }
    
    public static synchronized LaporanKeuanganDebitur insertExcObj(LaporanKeuanganDebitur entLaporanKeuanganDebitur) throws DBException {
        try {
            PstLaporanKeuanganDebitur pstLaporanKeuanganDebitur = new PstLaporanKeuanganDebitur(0);
            pstLaporanKeuanganDebitur.setString(FLD_FLAG_DETAIL, entLaporanKeuanganDebitur.getFlagDetail());
            pstLaporanKeuanganDebitur.setString(FLD_CIF, entLaporanKeuanganDebitur.getCif());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET, entLaporanKeuanganDebitur.getAset());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET_LANCAR, entLaporanKeuanganDebitur.getAsetLancar());
            pstLaporanKeuanganDebitur.setDouble(FLD_KAS_DAN_SETARA_KAS, entLaporanKeuanganDebitur.getKasDanSetaraKas());
            pstLaporanKeuanganDebitur.setDouble(FLD_PIUTANG_USAHA_AST_LNCR, entLaporanKeuanganDebitur.getPiutangUsahaAstLncr());
            pstLaporanKeuanganDebitur.setDouble(FLD_INVESTASI_AST_LNCR, entLaporanKeuanganDebitur.getInvestasiAstLncr());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET_LANCAR_LAIN, entLaporanKeuanganDebitur.getAsetLancarLain());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET_TIDAK_LANCAR, entLaporanKeuanganDebitur.getAsetTidakLancar());
            pstLaporanKeuanganDebitur.setDouble(FLD_PIUTANG_USAHA_AST_TDK_LNCR, entLaporanKeuanganDebitur.getPiutangUsahaAstTdkLncr());
            pstLaporanKeuanganDebitur.setDouble(FLD_INVESTASI_AST_TDK_LNCR, entLaporanKeuanganDebitur.getInvestasiAstTdkLncr());
            pstLaporanKeuanganDebitur.setDouble(FLD_ASET_TDK_LNCR_LAIN, entLaporanKeuanganDebitur.getAsetTdkLncrLain());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS, entLaporanKeuanganDebitur.getLiabilitas());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNDK, entLaporanKeuanganDebitur.getLiabilitasJnkPndk());
            pstLaporanKeuanganDebitur.setDouble(FLD_PINJAMAN_JNK_PNDK, entLaporanKeuanganDebitur.getPinjamanJnkPndk());
            pstLaporanKeuanganDebitur.setDouble(FLD_UTANG_USAHA_LIA_JNK_PNDK, entLaporanKeuanganDebitur.getUtangUsahaLiaJnkPndk());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNDK_LAIN, entLaporanKeuanganDebitur.getLiabilitasJnkPndkLain());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNJG, entLaporanKeuanganDebitur.getLiabilitasJnkPnjg());
            pstLaporanKeuanganDebitur.setDouble(FLD_PINJAMAN_JNK_PNJG, entLaporanKeuanganDebitur.getPinjamanJnkPnjg());
            pstLaporanKeuanganDebitur.setDouble(FLD_UTANG_USAHA_LIA_JNK_PNJG, entLaporanKeuanganDebitur.getUtangUsahaLiaJnkPnjg());
            pstLaporanKeuanganDebitur.setDouble(FLD_LIABILITAS_JNK_PNJG_LAIN, entLaporanKeuanganDebitur.getLiabilitasJnkPnjgLain());
            pstLaporanKeuanganDebitur.setDouble(FLD_EKUITAS, entLaporanKeuanganDebitur.getEkuitas());
            pstLaporanKeuanganDebitur.setDouble(FLD_PENDAPATAN_USAHA_OPR, entLaporanKeuanganDebitur.getPendapatanUsahaOpr());
            pstLaporanKeuanganDebitur.setDouble(FLD_BEBAN_POKOK_PEND, entLaporanKeuanganDebitur.getBebanPokokPend());
            pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_BRUTO, entLaporanKeuanganDebitur.getLabaRugiBruto());
            pstLaporanKeuanganDebitur.setDouble(FLD_PEND_LAIN_NON_OPR, entLaporanKeuanganDebitur.getPendLainNonOpr());
            pstLaporanKeuanganDebitur.setDouble(FLD_BEBAN_LAIN_NON_OPR, entLaporanKeuanganDebitur.getBebanLainNonOpr());
            pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_SBLM_PAJAK, entLaporanKeuanganDebitur.getLabaRugiSblmPajak());
            pstLaporanKeuanganDebitur.setDouble(FLD_LABA_RUGI_PERIODE, entLaporanKeuanganDebitur.getLabaRugiPeriode());
            pstLaporanKeuanganDebitur.setString(FLD_KODE_KANTOR_CABANG, entLaporanKeuanganDebitur.getKodeKantorCabang());
            pstLaporanKeuanganDebitur.setString(FLD_OPERASI_DATA, entLaporanKeuanganDebitur.getOperasiData());
            pstLaporanKeuanganDebitur.setDate(FLD_OPEN_DATE, entLaporanKeuanganDebitur.getOpenDate());
            pstLaporanKeuanganDebitur.setInt(FLD_STATUS_DATA, entLaporanKeuanganDebitur.getStatusData());
            pstLaporanKeuanganDebitur.setLong(FLD_PERIODE_ID, entLaporanKeuanganDebitur.getPeriodeId());
            pstLaporanKeuanganDebitur.setInt(FLD_STATUS_PERUBAHAN_DATA, entLaporanKeuanganDebitur.getStatusPerubahanData());
            pstLaporanKeuanganDebitur.setDate(FLD_POSISI_LAPORAN_KEUANGAN_TAHUNAN, entLaporanKeuanganDebitur.getPosisiLaporanKeuanganTahunan());
            pstLaporanKeuanganDebitur.insert();
            
            pstLaporanKeuanganDebitur.setSqlQueryHistory("");
            pstLaporanKeuanganDebitur.setSqlQueryHistory(pstLaporanKeuanganDebitur.getInsertSQL());
            
            entLaporanKeuanganDebitur.setSqlHIstory(pstLaporanKeuanganDebitur.getInsertSQL());
            
            entLaporanKeuanganDebitur.setOID(pstLaporanKeuanganDebitur.getlong(FLD_LAPORAN_KEUANGAN_DEBITUR_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLaporanKeuanganDebitur(0), DBException.UNKNOWN);
        }
        return entLaporanKeuanganDebitur;
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((LaporanKeuanganDebitur) entity);
    }

    public static void resultToObject(ResultSet rs, LaporanKeuanganDebitur entLaporanKeuanganDebitur) {
        try {
            entLaporanKeuanganDebitur.setOID(rs.getLong(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LAPORAN_KEUANGAN_DEBITUR_OID]));
            entLaporanKeuanganDebitur.setFlagDetail(rs.getString(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_FLAG_DETAIL]));
            entLaporanKeuanganDebitur.setCif(rs.getString(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_CIF]));
            entLaporanKeuanganDebitur.setAset(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET]));
            entLaporanKeuanganDebitur.setAsetLancar(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET_LANCAR]));
            entLaporanKeuanganDebitur.setKasDanSetaraKas(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KAS_DAN_SETARA_KAS]));
            entLaporanKeuanganDebitur.setPiutangUsahaAstLncr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PIUTANG_USAHA_AST_LNCR]));
            entLaporanKeuanganDebitur.setInvestasiAstLncr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_INVESTASI_AST_LNCR]));
            entLaporanKeuanganDebitur.setAsetLancarLain(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET_LANCAR_LAIN]));
            entLaporanKeuanganDebitur.setAsetTidakLancar(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET_TIDAK_LANCAR]));
            entLaporanKeuanganDebitur.setPiutangUsahaAstTdkLncr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PIUTANG_USAHA_AST_TDK_LNCR]));
            entLaporanKeuanganDebitur.setInvestasiAstTdkLncr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_INVESTASI_AST_TDK_LNCR]));
            entLaporanKeuanganDebitur.setAsetTdkLncrLain(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET_TDK_LNCR_LAIN]));
            entLaporanKeuanganDebitur.setLiabilitas(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS]));
            entLaporanKeuanganDebitur.setLiabilitasJnkPndk(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS_JNK_PNDK]));
            entLaporanKeuanganDebitur.setPinjamanJnkPndk(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PINJAMAN_JNK_PNDK]));
            entLaporanKeuanganDebitur.setUtangUsahaLiaJnkPndk(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_UTANG_USAHA_LIA_JNK_PNDK]));
            entLaporanKeuanganDebitur.setLiabilitasJnkPndkLain(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS_JNK_PNDK_LAIN]));
            entLaporanKeuanganDebitur.setLiabilitasJnkPnjg(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS_JNK_PNJG]));
            entLaporanKeuanganDebitur.setPinjamanJnkPnjg(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PINJAMAN_JNK_PNJG]));
            entLaporanKeuanganDebitur.setUtangUsahaLiaJnkPnjg(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_UTANG_USAHA_LIA_JNK_PNJG]));
            entLaporanKeuanganDebitur.setLiabilitasJnkPnjgLain(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS_JNK_PNJG_LAIN]));
            entLaporanKeuanganDebitur.setEkuitas(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_EKUITAS]));
            entLaporanKeuanganDebitur.setPendapatanUsahaOpr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PENDAPATAN_USAHA_OPR]));
            entLaporanKeuanganDebitur.setBebanPokokPend(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_BEBAN_POKOK_PEND]));
            entLaporanKeuanganDebitur.setLabaRugiBruto(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LABA_RUGI_BRUTO]));
            entLaporanKeuanganDebitur.setPendLainNonOpr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PEND_LAIN_NON_OPR]));
            entLaporanKeuanganDebitur.setBebanLainNonOpr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_BEBAN_LAIN_NON_OPR]));
            entLaporanKeuanganDebitur.setLabaRugiSblmPajak(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LABA_RUGI_SBLM_PAJAK]));
            entLaporanKeuanganDebitur.setLabaRugiPeriode(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LABA_RUGI_PERIODE]));
            entLaporanKeuanganDebitur.setKodeKantorCabang(rs.getString(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KODE_KANTOR_CABANG]));
            entLaporanKeuanganDebitur.setOperasiData(rs.getString(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_OPERASI_DATA]));
            entLaporanKeuanganDebitur.setOpenDate(rs.getDate(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_OPEN_DATE]));
            entLaporanKeuanganDebitur.setStatusData(rs.getInt(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_STATUS_DATA]));
            entLaporanKeuanganDebitur.setPeriodeId(rs.getLong(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PERIODE_ID]));
            entLaporanKeuanganDebitur.setStatusPerubahanData(rs.getInt(fieldNames[FLD_STATUS_PERUBAHAN_DATA]));
            
            entLaporanKeuanganDebitur.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));
            entLaporanKeuanganDebitur.setPosisiLaporanKeuanganTahunan(rs.getDate(fieldNames[FLD_POSISI_LAPORAN_KEUANGAN_TAHUNAN]));

            
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoin(ResultSet rs, LaporanKeuanganDebitur entLaporanKeuanganDebitur) {
        try {
            entLaporanKeuanganDebitur.setOID(rs.getLong(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LAPORAN_KEUANGAN_DEBITUR_OID]));
            entLaporanKeuanganDebitur.setFlagDetail(rs.getString(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_FLAG_DETAIL]));
            entLaporanKeuanganDebitur.setCif(rs.getString(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_CIF]));
            entLaporanKeuanganDebitur.setAset(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET]));
            entLaporanKeuanganDebitur.setAsetLancar(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET_LANCAR]));
            entLaporanKeuanganDebitur.setKasDanSetaraKas(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KAS_DAN_SETARA_KAS]));
            entLaporanKeuanganDebitur.setPiutangUsahaAstLncr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PIUTANG_USAHA_AST_LNCR]));
            entLaporanKeuanganDebitur.setInvestasiAstLncr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_INVESTASI_AST_LNCR]));
            entLaporanKeuanganDebitur.setAsetLancarLain(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET_LANCAR_LAIN]));
            entLaporanKeuanganDebitur.setAsetTidakLancar(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET_TIDAK_LANCAR]));
            entLaporanKeuanganDebitur.setPiutangUsahaAstTdkLncr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PIUTANG_USAHA_AST_TDK_LNCR]));
            entLaporanKeuanganDebitur.setInvestasiAstTdkLncr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_INVESTASI_AST_TDK_LNCR]));
            entLaporanKeuanganDebitur.setAsetTdkLncrLain(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_ASET_TDK_LNCR_LAIN]));
            entLaporanKeuanganDebitur.setLiabilitas(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS]));
            entLaporanKeuanganDebitur.setLiabilitasJnkPndk(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS_JNK_PNDK]));
            entLaporanKeuanganDebitur.setPinjamanJnkPndk(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PINJAMAN_JNK_PNDK]));
            entLaporanKeuanganDebitur.setUtangUsahaLiaJnkPndk(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_UTANG_USAHA_LIA_JNK_PNDK]));
            entLaporanKeuanganDebitur.setLiabilitasJnkPndkLain(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS_JNK_PNDK_LAIN]));
            entLaporanKeuanganDebitur.setLiabilitasJnkPnjg(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS_JNK_PNJG]));
            entLaporanKeuanganDebitur.setPinjamanJnkPnjg(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PINJAMAN_JNK_PNJG]));
            entLaporanKeuanganDebitur.setUtangUsahaLiaJnkPnjg(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_UTANG_USAHA_LIA_JNK_PNJG]));
            entLaporanKeuanganDebitur.setLiabilitasJnkPnjgLain(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LIABILITAS_JNK_PNJG_LAIN]));
            entLaporanKeuanganDebitur.setEkuitas(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_EKUITAS]));
            entLaporanKeuanganDebitur.setPendapatanUsahaOpr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PENDAPATAN_USAHA_OPR]));
            entLaporanKeuanganDebitur.setBebanPokokPend(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_BEBAN_POKOK_PEND]));
            entLaporanKeuanganDebitur.setLabaRugiBruto(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LABA_RUGI_BRUTO]));
            entLaporanKeuanganDebitur.setPendLainNonOpr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PEND_LAIN_NON_OPR]));
            entLaporanKeuanganDebitur.setBebanLainNonOpr(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_BEBAN_LAIN_NON_OPR]));
            entLaporanKeuanganDebitur.setLabaRugiSblmPajak(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LABA_RUGI_SBLM_PAJAK]));
            entLaporanKeuanganDebitur.setLabaRugiPeriode(rs.getDouble(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LABA_RUGI_PERIODE]));
            entLaporanKeuanganDebitur.setKodeKantorCabang(rs.getString(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KODE_KANTOR_CABANG]));
            entLaporanKeuanganDebitur.setOperasiData(rs.getString(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_OPERASI_DATA]));
            entLaporanKeuanganDebitur.setOpenDate(rs.getDate(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_OPEN_DATE]));
            entLaporanKeuanganDebitur.setStatusData(rs.getInt(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_STATUS_DATA]));
            entLaporanKeuanganDebitur.setPeriodeId(rs.getLong(PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PERIODE_ID]));
            entLaporanKeuanganDebitur.setDebiturOid(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]));
            entLaporanKeuanganDebitur.setDebiturType(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
            entLaporanKeuanganDebitur.setStatusPerubahanData(rs.getInt(fieldNames[FLD_STATUS_PERUBAHAN_DATA]));
            entLaporanKeuanganDebitur.setPosisiLaporanKeuanganTahunan(rs.getDate(fieldNames[FLD_POSISI_LAPORAN_KEUANGAN_TAHUNAN]));
        } catch (Exception e) {
        }
    }

    public static Vector listAll() {
        return list(0, 500, "", "");
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_LAPORAN_KEUANGAN_DEBITUR;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                LaporanKeuanganDebitur entLaporanKeuanganDebitur = new LaporanKeuanganDebitur();
                resultToObject(rs, entLaporanKeuanganDebitur);
                lists.add(entLaporanKeuanganDebitur);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }
    
    public static Vector listJoin(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT laporan.*,"
                    + "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]+", "
                    + "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+" "
                    + "FROM " + TBL_LAPORAN_KEUANGAN_DEBITUR+" AS laporan "
                    + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                    + "ON laporan."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                    + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "ON laporan."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID];
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                LaporanKeuanganDebitur entLaporanKeuanganDebitur = new LaporanKeuanganDebitur();
                resultToObjectJoin(rs, entLaporanKeuanganDebitur);
                lists.add(entLaporanKeuanganDebitur);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static boolean checkOID(long entLaporanKeuanganDebiturId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_LAPORAN_KEUANGAN_DEBITUR + " WHERE "
                    + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LAPORAN_KEUANGAN_DEBITUR_OID] + " = " + entLaporanKeuanganDebiturId;
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }

    public static int getCount(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT COUNT(" + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LAPORAN_KEUANGAN_DEBITUR_OID] + ") FROM " + TBL_LAPORAN_KEUANGAN_DEBITUR;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            return count;
        } catch (Exception e) {
            return 0;
        } finally {
            DBResultSet.close(dbrs);
        }
    }
    
    public static int getCountJoin(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT COUNT(laporan." + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_LAPORAN_KEUANGAN_DEBITUR_OID] + ") "
                    + "FROM " + TBL_LAPORAN_KEUANGAN_DEBITUR+" AS laporan "
                    + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                    + "ON laporan."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                    + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "ON laporan."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID];
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            return count;
        } catch (Exception e) {
            return 0;
        } finally {
            DBResultSet.close(dbrs);
        }
    }

    public static int findLimitStart(long oid, int recordToGet, String whereClause, String orderClause) {
        int size = getCount(whereClause);
        int start = 0;
        boolean found = false;
        for (int i = 0; (i < size) && !found; i = i + recordToGet) {
            Vector list = list(i, recordToGet, whereClause, orderClause);
            start = i;
            if (list.size() > 0) {
                for (int ls = 0; ls < list.size(); ls++) {
                    LaporanKeuanganDebitur entLaporanKeuanganDebitur = (LaporanKeuanganDebitur) list.get(ls);
                    if (oid == entLaporanKeuanganDebitur.getOID()) {
                        found = true;
                    }
                }
            }
        }
        if ((start >= size) && (size > 0)) {
            start = start - recordToGet;
        }
        return start;
    }

    public static int findLimitCommand(int start, int recordToGet, int vectSize) {
        int cmd = Command.LIST;
        int mdl = vectSize % recordToGet;
        vectSize = vectSize + (recordToGet - mdl);
        if (start == 0) {
            cmd = Command.FIRST;
        } else {
            if (start == (vectSize - recordToGet)) {
                cmd = Command.LAST;
            } else {
                start = start + recordToGet;
                if (start <= (vectSize - recordToGet)) {
                    cmd = Command.NEXT;
                    System.out.println("next.......................");
                } else {
                    start = start - recordToGet;
                    if (start > 0) {
                        cmd = Command.PREV;
                        System.out.println("prev.......................");
                    }
                }
            }
        }
        return cmd;
    }

    /**
     * @return the sqlQueryHistory
     */
    public String getSqlQueryHistory() {
        return sqlQueryHistory;
    }

    /**
     * @param sqlQueryHistory the sqlQueryHistory to set
     */
    public void setSqlQueryHistory(String sqlQueryHistory) {
        this.sqlQueryHistory = sqlQueryHistory;
    }
}
