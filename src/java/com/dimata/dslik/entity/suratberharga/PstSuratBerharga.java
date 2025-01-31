/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.suratberharga;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.contentdata.PstContentDataJenisSuratBerharga;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import java.sql.*;
import java.util.Vector;

public class PstSuratBerharga extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    private String sqlQueryHistory = "";

    public static final String TBL_SURAT_BERHARGA = "dslik_surat_berharga";
    public static final int FLD_SURAT_BERHARGA_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_REKENING = 2;
    public static final int FLD_CIF = 3;
    public static final int FLD_KODE_JENIS = 4;
    public static final int FLD_SOVEREIGN_RATE = 5;
    public static final int FLD_LISTING = 6;
    public static final int FLD_PERINGKAT = 7;
    public static final int FLD_KODE_TUJUAN = 8;
    public static final int FLD_TGL_DEBIT = 9;
    public static final int FLD_TGL_BELI = 10;
    public static final int FLD_TGL_JATUH_TEMPO = 11;
    public static final int FLD_KODE_VALUTA = 12;
    public static final int FLD_NOMINAL = 13;
    public static final int FLD_NILAI_UANG_ASAL = 14;
    public static final int FLD_NILAI_PASAR = 15;
    public static final int FLD_NILAI_PEROLEHAN = 16;
    public static final int FLD_SUKU_BUNGA = 17;
    public static final int FLD_TUNGGAKAN = 18;
    public static final int FLD_JML_HARI_TUNGGAKAN = 19;
    public static final int FLD_KODE_KOLEKTIBILITAS = 20;
    public static final int FLD_TGL_MACET = 21;
    public static final int FLD_KODE_SEBAB_MACET = 22;
    public static final int FLD_KODE_KONDISI = 23;
    public static final int FLD_TGL_KONDISI = 24;
    public static final int FLD_KETERANGAN = 25;
    public static final int FLD_KODE_KANTOR_CABANG = 26;
    public static final int FLD_OPERASI_DATA = 27;
    public static final int FLD_OPEN_DATE = 28;
    public static final int FLD_STATUS_DATA = 29;
    public static final int FLD_PERIODE_ID = 30;
    public static final int FLD_STATUS_PERUBAHAN_DATA=31;
    
    public static String[] fieldNames = {
        "SURAT_BERHARGA_OID",
        "FLAG_DETAIL",
        "NO_REKENING",
        "CIF",
        "KODE_JENIS",
        "SOVEREIGN_RATE",
        "LISTING",
        "PERINGKAT",
        "KODE_TUJUAN",
        "TGL_TERBIT",
        "TGL_BELI",
        "TGL_JATUH_TEMPO",
        "KODE_VALUTA",
        "NOMINAL",
        "NILAI_UANG_ASAL",
        "NILAI_PASAR",
        "NILAI_PEROLEHAN",
        "SUKU_BUNGA",
        "TUNGGAKAN",
        "JML_HARI_TUNGGAKAN",
        "KODE_KOLEKTIBILITAS",
        "TGL_MACET",
        "KODE_SEBAB_MACET",
        "KODE_KONDISI",
        "TGL_KONDISI",
        "KETERANGAN",
        "KODE_KANTOR_CABANG",
        "OPERASI_DATA",
        "OPEN_DATE",
        "STATUS_OPERASI_DATA",
        "PERIODE_ID",
        "STATUS_DATA"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_INT,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_INT, //29
        TYPE_LONG,
        TYPE_INT
    };
    public static String[] listingKey = {
        "Y",
        "T"
    };
    public static String[] listingValue = {
        "Terdaftar di pasar modal",
        "Tidak terdaftar di pasar modal"
    };
    public static String[] tujuanKepemilikanKey = {
        "1",
        "2",
        "3"
    };
    public static String[] tujuanKepemilikanVal = {
        "Dimiliki hingga jatuh tempo",
        "Diperdagangkan",
        "Tersedia untuk dijual"
    };
    
    
    public PstSuratBerharga() {
    }

    public PstSuratBerharga(int i) throws DBException {
        super(new PstSuratBerharga());
    }

    public PstSuratBerharga(String sOid) throws DBException {
        super(new PstSuratBerharga(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstSuratBerharga(long lOid) throws DBException {
        super(new PstSuratBerharga(0));
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
        return TBL_SURAT_BERHARGA;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstSuratBerharga().getClass().getName();
    }

    public static SuratBerharga fetchExc(long oid) throws DBException {
        try {
            SuratBerharga entSuratBerharga = new SuratBerharga();
            PstSuratBerharga pstSuratBerharga = new PstSuratBerharga(oid);
            entSuratBerharga.setOID(oid);
            entSuratBerharga.setFlagDetail(pstSuratBerharga.getString(FLD_FLAG_DETAIL));
            entSuratBerharga.setNoRekening(pstSuratBerharga.getString(FLD_NO_REKENING));
            entSuratBerharga.setCif(pstSuratBerharga.getString(FLD_CIF));
            entSuratBerharga.setKodeJenis(pstSuratBerharga.getString(FLD_KODE_JENIS));
            entSuratBerharga.setSovereignRate(pstSuratBerharga.getInt(FLD_SOVEREIGN_RATE));
            entSuratBerharga.setListing(pstSuratBerharga.getString(FLD_LISTING));
            entSuratBerharga.setPeringkat(pstSuratBerharga.getString(FLD_PERINGKAT));
            entSuratBerharga.setKodeTujuan(pstSuratBerharga.getString(FLD_KODE_TUJUAN));
            entSuratBerharga.setTglTerbit(pstSuratBerharga.getDate(FLD_TGL_DEBIT));
            entSuratBerharga.setTglBeli(pstSuratBerharga.getDate(FLD_TGL_BELI));
            entSuratBerharga.setTglJatuhTempo(pstSuratBerharga.getDate(FLD_TGL_JATUH_TEMPO));
            entSuratBerharga.setKodeValuta(pstSuratBerharga.getString(FLD_KODE_VALUTA));
            entSuratBerharga.setNominal(pstSuratBerharga.getdouble(FLD_NOMINAL));
            entSuratBerharga.setNilaiUangAsal(pstSuratBerharga.getdouble(FLD_NILAI_UANG_ASAL));
            entSuratBerharga.setNilaiPasar(pstSuratBerharga.getdouble(FLD_NILAI_PASAR));
            entSuratBerharga.setNilaiPerolehan(pstSuratBerharga.getdouble(FLD_NILAI_PEROLEHAN));
            entSuratBerharga.setSukuBunga(pstSuratBerharga.getdouble(FLD_SUKU_BUNGA));
            entSuratBerharga.setTunggakan(pstSuratBerharga.getdouble(FLD_TUNGGAKAN));
            entSuratBerharga.setJmlHariTunggakan(pstSuratBerharga.getInt(FLD_JML_HARI_TUNGGAKAN));
            entSuratBerharga.setKodeKolektibilitas(pstSuratBerharga.getString(FLD_KODE_KOLEKTIBILITAS));
            entSuratBerharga.setTglMacet(pstSuratBerharga.getDate(FLD_TGL_MACET));
            entSuratBerharga.setKodeSebabMacet(pstSuratBerharga.getString(FLD_KODE_SEBAB_MACET));
            entSuratBerharga.setKodeKondisi(pstSuratBerharga.getString(FLD_KODE_KONDISI));
            entSuratBerharga.setTglKondisi(pstSuratBerharga.getDate(FLD_TGL_KONDISI));
            entSuratBerharga.setKeterangan(pstSuratBerharga.getString(FLD_KETERANGAN));
            entSuratBerharga.setKodeKantorCabang(pstSuratBerharga.getString(FLD_KODE_KANTOR_CABANG));
            entSuratBerharga.setOperasiData(pstSuratBerharga.getString(FLD_OPERASI_DATA));
            entSuratBerharga.setOpenDate(pstSuratBerharga.getDate(FLD_OPEN_DATE));
            entSuratBerharga.setStatusData(pstSuratBerharga.getInt(FLD_STATUS_DATA));
            entSuratBerharga.setPeriodeId(pstSuratBerharga.getlong(FLD_PERIODE_ID));
            entSuratBerharga.setStatusPerubahanData(pstSuratBerharga.getInt(FLD_STATUS_PERUBAHAN_DATA));
            
            return entSuratBerharga;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSuratBerharga(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        SuratBerharga entSuratBerharga = fetchExc(entity.getOID());
        entity = (Entity) entSuratBerharga;
        return entSuratBerharga.getOID();
    }

    public static synchronized long updateExc(SuratBerharga entSuratBerharga) throws DBException {
        try {
            if (entSuratBerharga.getOID() != 0) {
                PstSuratBerharga pstSuratBerharga = new PstSuratBerharga(entSuratBerharga.getOID());
                pstSuratBerharga.setString(FLD_FLAG_DETAIL, entSuratBerharga.getFlagDetail());
                pstSuratBerharga.setString(FLD_NO_REKENING, entSuratBerharga.getNoRekening());
                pstSuratBerharga.setString(FLD_CIF, entSuratBerharga.getCif());
                pstSuratBerharga.setString(FLD_KODE_JENIS, entSuratBerharga.getKodeJenis());
                pstSuratBerharga.setInt(FLD_SOVEREIGN_RATE, entSuratBerharga.getSovereignRate());
                pstSuratBerharga.setString(FLD_LISTING, entSuratBerharga.getListing());
                pstSuratBerharga.setString(FLD_PERINGKAT, entSuratBerharga.getPeringkat());
                pstSuratBerharga.setString(FLD_KODE_TUJUAN, entSuratBerharga.getKodeTujuan());
                pstSuratBerharga.setDate(FLD_TGL_DEBIT, entSuratBerharga.getTglTerbit());
                pstSuratBerharga.setDate(FLD_TGL_BELI, entSuratBerharga.getTglBeli());
                pstSuratBerharga.setDate(FLD_TGL_JATUH_TEMPO, entSuratBerharga.getTglJatuhTempo());
                pstSuratBerharga.setString(FLD_KODE_VALUTA, entSuratBerharga.getKodeValuta());
                pstSuratBerharga.setDouble(FLD_NOMINAL, entSuratBerharga.getNominal());
                pstSuratBerharga.setDouble(FLD_NILAI_UANG_ASAL, entSuratBerharga.getNilaiUangAsal());
                pstSuratBerharga.setDouble(FLD_NILAI_PASAR, entSuratBerharga.getNilaiPasar());
                pstSuratBerharga.setDouble(FLD_NILAI_PEROLEHAN, entSuratBerharga.getNilaiPerolehan());
                pstSuratBerharga.setDouble(FLD_SUKU_BUNGA, entSuratBerharga.getSukuBunga());
                pstSuratBerharga.setDouble(FLD_TUNGGAKAN, entSuratBerharga.getTunggakan());
                pstSuratBerharga.setInt(FLD_JML_HARI_TUNGGAKAN, entSuratBerharga.getJmlHariTunggakan());
                pstSuratBerharga.setString(FLD_KODE_KOLEKTIBILITAS, entSuratBerharga.getKodeKolektibilitas());
                pstSuratBerharga.setDate(FLD_TGL_MACET, entSuratBerharga.getTglMacet());
                pstSuratBerharga.setString(FLD_KODE_SEBAB_MACET, entSuratBerharga.getKodeSebabMacet());
                pstSuratBerharga.setString(FLD_KODE_KONDISI, entSuratBerharga.getKodeKondisi());
                pstSuratBerharga.setDate(FLD_TGL_KONDISI, entSuratBerharga.getTglKondisi());
                pstSuratBerharga.setString(FLD_KETERANGAN, entSuratBerharga.getKeterangan());
                pstSuratBerharga.setString(FLD_KODE_KANTOR_CABANG, entSuratBerharga.getKodeKantorCabang());
                pstSuratBerharga.setString(FLD_OPERASI_DATA, entSuratBerharga.getOperasiData());
                pstSuratBerharga.setDate(FLD_OPEN_DATE, entSuratBerharga.getOpenDate());
                pstSuratBerharga.setInt(FLD_STATUS_DATA, entSuratBerharga.getStatusData());
                pstSuratBerharga.setLong(FLD_PERIODE_ID, entSuratBerharga.getPeriodeId());
                pstSuratBerharga.setInt(FLD_STATUS_PERUBAHAN_DATA, entSuratBerharga.getStatusPerubahanData());
                pstSuratBerharga.update();
                
                pstSuratBerharga.setSqlQueryHistory("");
                pstSuratBerharga.setSqlQueryHistory(pstSuratBerharga.getUpdateSQL());
                
                return entSuratBerharga.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSuratBerharga(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized SuratBerharga updateExcObj(SuratBerharga entSuratBerharga) throws DBException {
        try {
            if (entSuratBerharga.getOID() != 0) {
                PstSuratBerharga pstSuratBerharga = new PstSuratBerharga(entSuratBerharga.getOID());
                pstSuratBerharga.setString(FLD_FLAG_DETAIL, entSuratBerharga.getFlagDetail());
                pstSuratBerharga.setString(FLD_NO_REKENING, entSuratBerharga.getNoRekening());
                pstSuratBerharga.setString(FLD_CIF, entSuratBerharga.getCif());
                pstSuratBerharga.setString(FLD_KODE_JENIS, entSuratBerharga.getKodeJenis());
                pstSuratBerharga.setInt(FLD_SOVEREIGN_RATE, entSuratBerharga.getSovereignRate());
                pstSuratBerharga.setString(FLD_LISTING, entSuratBerharga.getListing());
                pstSuratBerharga.setString(FLD_PERINGKAT, entSuratBerharga.getPeringkat());
                pstSuratBerharga.setString(FLD_KODE_TUJUAN, entSuratBerharga.getKodeTujuan());
                pstSuratBerharga.setDate(FLD_TGL_DEBIT, entSuratBerharga.getTglTerbit());
                pstSuratBerharga.setDate(FLD_TGL_BELI, entSuratBerharga.getTglBeli());
                pstSuratBerharga.setDate(FLD_TGL_JATUH_TEMPO, entSuratBerharga.getTglJatuhTempo());
                pstSuratBerharga.setString(FLD_KODE_VALUTA, entSuratBerharga.getKodeValuta());
                pstSuratBerharga.setDouble(FLD_NOMINAL, entSuratBerharga.getNominal());
                pstSuratBerharga.setDouble(FLD_NILAI_UANG_ASAL, entSuratBerharga.getNilaiUangAsal());
                pstSuratBerharga.setDouble(FLD_NILAI_PASAR, entSuratBerharga.getNilaiPasar());
                pstSuratBerharga.setDouble(FLD_NILAI_PEROLEHAN, entSuratBerharga.getNilaiPerolehan());
                pstSuratBerharga.setDouble(FLD_SUKU_BUNGA, entSuratBerharga.getSukuBunga());
                pstSuratBerharga.setDouble(FLD_TUNGGAKAN, entSuratBerharga.getTunggakan());
                pstSuratBerharga.setInt(FLD_JML_HARI_TUNGGAKAN, entSuratBerharga.getJmlHariTunggakan());
                pstSuratBerharga.setString(FLD_KODE_KOLEKTIBILITAS, entSuratBerharga.getKodeKolektibilitas());
                pstSuratBerharga.setDate(FLD_TGL_MACET, entSuratBerharga.getTglMacet());
                pstSuratBerharga.setString(FLD_KODE_SEBAB_MACET, entSuratBerharga.getKodeSebabMacet());
                pstSuratBerharga.setString(FLD_KODE_KONDISI, entSuratBerharga.getKodeKondisi());
                pstSuratBerharga.setDate(FLD_TGL_KONDISI, entSuratBerharga.getTglKondisi());
                pstSuratBerharga.setString(FLD_KETERANGAN, entSuratBerharga.getKeterangan());
                pstSuratBerharga.setString(FLD_KODE_KANTOR_CABANG, entSuratBerharga.getKodeKantorCabang());
                pstSuratBerharga.setString(FLD_OPERASI_DATA, entSuratBerharga.getOperasiData());
                pstSuratBerharga.setDate(FLD_OPEN_DATE, entSuratBerharga.getOpenDate());
                pstSuratBerharga.setInt(FLD_STATUS_DATA, entSuratBerharga.getStatusData());
                pstSuratBerharga.setLong(FLD_PERIODE_ID, entSuratBerharga.getPeriodeId());
                pstSuratBerharga.setInt(FLD_STATUS_PERUBAHAN_DATA, entSuratBerharga.getStatusPerubahanData());
                pstSuratBerharga.update();
                
                pstSuratBerharga.setSqlQueryHistory("");
                pstSuratBerharga.setSqlQueryHistory(pstSuratBerharga.getUpdateSQL());
                entSuratBerharga.setSqlHistory(pstSuratBerharga.getUpdateSQL());
                
                return entSuratBerharga;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSuratBerharga(0), DBException.UNKNOWN);
        }
        return entSuratBerharga;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((SuratBerharga) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstSuratBerharga pstSuratBerharga = new PstSuratBerharga(oid);
            pstSuratBerharga.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSuratBerharga(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(SuratBerharga entSuratBerharga) throws DBException {
        try {
            PstSuratBerharga pstSuratBerharga = new PstSuratBerharga(0);
            pstSuratBerharga.setString(FLD_FLAG_DETAIL, entSuratBerharga.getFlagDetail());
            pstSuratBerharga.setString(FLD_NO_REKENING, entSuratBerharga.getNoRekening());
            pstSuratBerharga.setString(FLD_CIF, entSuratBerharga.getCif());
            pstSuratBerharga.setString(FLD_KODE_JENIS, entSuratBerharga.getKodeJenis());
            pstSuratBerharga.setInt(FLD_SOVEREIGN_RATE, entSuratBerharga.getSovereignRate());
            pstSuratBerharga.setString(FLD_LISTING, entSuratBerharga.getListing());
            pstSuratBerharga.setString(FLD_PERINGKAT, entSuratBerharga.getPeringkat());
            pstSuratBerharga.setString(FLD_KODE_TUJUAN, entSuratBerharga.getKodeTujuan());
            pstSuratBerharga.setDate(FLD_TGL_DEBIT, entSuratBerharga.getTglTerbit());
            pstSuratBerharga.setDate(FLD_TGL_BELI, entSuratBerharga.getTglBeli());
            pstSuratBerharga.setDate(FLD_TGL_JATUH_TEMPO, entSuratBerharga.getTglJatuhTempo());
            pstSuratBerharga.setString(FLD_KODE_VALUTA, entSuratBerharga.getKodeValuta());
            pstSuratBerharga.setDouble(FLD_NOMINAL, entSuratBerharga.getNominal());
            pstSuratBerharga.setDouble(FLD_NILAI_UANG_ASAL, entSuratBerharga.getNilaiUangAsal());
            pstSuratBerharga.setDouble(FLD_NILAI_PASAR, entSuratBerharga.getNilaiPasar());
            pstSuratBerharga.setDouble(FLD_NILAI_PEROLEHAN, entSuratBerharga.getNilaiPerolehan());
            pstSuratBerharga.setDouble(FLD_SUKU_BUNGA, entSuratBerharga.getSukuBunga());
            pstSuratBerharga.setDouble(FLD_TUNGGAKAN, entSuratBerharga.getTunggakan());
            pstSuratBerharga.setInt(FLD_JML_HARI_TUNGGAKAN, entSuratBerharga.getJmlHariTunggakan());
            pstSuratBerharga.setString(FLD_KODE_KOLEKTIBILITAS, entSuratBerharga.getKodeKolektibilitas());
            pstSuratBerharga.setDate(FLD_TGL_MACET, entSuratBerharga.getTglMacet());
            pstSuratBerharga.setString(FLD_KODE_SEBAB_MACET, entSuratBerharga.getKodeSebabMacet());
            pstSuratBerharga.setString(FLD_KODE_KONDISI, entSuratBerharga.getKodeKondisi());
            pstSuratBerharga.setDate(FLD_TGL_KONDISI, entSuratBerharga.getTglKondisi());
            pstSuratBerharga.setString(FLD_KETERANGAN, entSuratBerharga.getKeterangan());
            pstSuratBerharga.setString(FLD_KODE_KANTOR_CABANG, entSuratBerharga.getKodeKantorCabang());
            pstSuratBerharga.setString(FLD_OPERASI_DATA, entSuratBerharga.getOperasiData());
            pstSuratBerharga.setString(FLD_OPERASI_DATA, entSuratBerharga.getOperasiData());
            pstSuratBerharga.setString(FLD_OPERASI_DATA, entSuratBerharga.getOperasiData());
            pstSuratBerharga.setDate(FLD_OPEN_DATE, entSuratBerharga.getOpenDate());
            pstSuratBerharga.setInt(FLD_STATUS_DATA, entSuratBerharga.getStatusData());
            pstSuratBerharga.setLong(FLD_PERIODE_ID, entSuratBerharga.getPeriodeId());
            pstSuratBerharga.setInt(FLD_STATUS_PERUBAHAN_DATA, entSuratBerharga.getStatusPerubahanData());
            pstSuratBerharga.insert();
            
            pstSuratBerharga.setSqlQueryHistory("");
            pstSuratBerharga.setSqlQueryHistory(pstSuratBerharga.getInsertSQL());
                
            entSuratBerharga.setOID(pstSuratBerharga.getLong(FLD_SURAT_BERHARGA_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSuratBerharga(0), DBException.UNKNOWN);
        }
        return entSuratBerharga.getOID();
    }
    
    public static synchronized SuratBerharga insertExcObj(SuratBerharga entSuratBerharga) throws DBException {
        try {
            PstSuratBerharga pstSuratBerharga = new PstSuratBerharga(0);
            pstSuratBerharga.setString(FLD_FLAG_DETAIL, entSuratBerharga.getFlagDetail());
            pstSuratBerharga.setString(FLD_NO_REKENING, entSuratBerharga.getNoRekening());
            pstSuratBerharga.setString(FLD_CIF, entSuratBerharga.getCif());
            pstSuratBerharga.setString(FLD_KODE_JENIS, entSuratBerharga.getKodeJenis());
            pstSuratBerharga.setInt(FLD_SOVEREIGN_RATE, entSuratBerharga.getSovereignRate());
            pstSuratBerharga.setString(FLD_LISTING, entSuratBerharga.getListing());
            pstSuratBerharga.setString(FLD_PERINGKAT, entSuratBerharga.getPeringkat());
            pstSuratBerharga.setString(FLD_KODE_TUJUAN, entSuratBerharga.getKodeTujuan());
            pstSuratBerharga.setDate(FLD_TGL_DEBIT, entSuratBerharga.getTglTerbit());
            pstSuratBerharga.setDate(FLD_TGL_BELI, entSuratBerharga.getTglBeli());
            pstSuratBerharga.setDate(FLD_TGL_JATUH_TEMPO, entSuratBerharga.getTglJatuhTempo());
            pstSuratBerharga.setString(FLD_KODE_VALUTA, entSuratBerharga.getKodeValuta());
            pstSuratBerharga.setDouble(FLD_NOMINAL, entSuratBerharga.getNominal());
            pstSuratBerharga.setDouble(FLD_NILAI_UANG_ASAL, entSuratBerharga.getNilaiUangAsal());
            pstSuratBerharga.setDouble(FLD_NILAI_PASAR, entSuratBerharga.getNilaiPasar());
            pstSuratBerharga.setDouble(FLD_NILAI_PEROLEHAN, entSuratBerharga.getNilaiPerolehan());
            pstSuratBerharga.setDouble(FLD_SUKU_BUNGA, entSuratBerharga.getSukuBunga());
            pstSuratBerharga.setDouble(FLD_TUNGGAKAN, entSuratBerharga.getTunggakan());
            pstSuratBerharga.setInt(FLD_JML_HARI_TUNGGAKAN, entSuratBerharga.getJmlHariTunggakan());
            pstSuratBerharga.setString(FLD_KODE_KOLEKTIBILITAS, entSuratBerharga.getKodeKolektibilitas());
            pstSuratBerharga.setDate(FLD_TGL_MACET, entSuratBerharga.getTglMacet());
            pstSuratBerharga.setString(FLD_KODE_SEBAB_MACET, entSuratBerharga.getKodeSebabMacet());
            pstSuratBerharga.setString(FLD_KODE_KONDISI, entSuratBerharga.getKodeKondisi());
            pstSuratBerharga.setDate(FLD_TGL_KONDISI, entSuratBerharga.getTglKondisi());
            pstSuratBerharga.setString(FLD_KETERANGAN, entSuratBerharga.getKeterangan());
            pstSuratBerharga.setString(FLD_KODE_KANTOR_CABANG, entSuratBerharga.getKodeKantorCabang());
            pstSuratBerharga.setString(FLD_OPERASI_DATA, entSuratBerharga.getOperasiData());
            
            pstSuratBerharga.setDate(FLD_OPEN_DATE, entSuratBerharga.getOpenDate());
            pstSuratBerharga.setInt(FLD_STATUS_DATA, entSuratBerharga.getStatusData());
            pstSuratBerharga.setLong(FLD_PERIODE_ID, entSuratBerharga.getPeriodeId());
            pstSuratBerharga.setInt(FLD_STATUS_PERUBAHAN_DATA, entSuratBerharga.getStatusPerubahanData());
            pstSuratBerharga.insert();
            
            pstSuratBerharga.setSqlQueryHistory("");
            pstSuratBerharga.setSqlQueryHistory(pstSuratBerharga.getInsertSQL());
            entSuratBerharga.setSqlHistory(pstSuratBerharga.getInsertSQL());
                
            entSuratBerharga.setOID(pstSuratBerharga.getlong(FLD_SURAT_BERHARGA_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSuratBerharga(0), DBException.UNKNOWN);
        }
        return entSuratBerharga;
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((SuratBerharga) entity);
    }

    public static void resultToObject(ResultSet rs, SuratBerharga entSuratBerharga) {
        try {
            entSuratBerharga.setOID(rs.getLong(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID]));
            entSuratBerharga.setFlagDetail(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_FLAG_DETAIL]));
            entSuratBerharga.setNoRekening(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]));
            entSuratBerharga.setCif(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]));
            entSuratBerharga.setKodeJenis(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]));
            entSuratBerharga.setSovereignRate(rs.getInt(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SOVEREIGN_RATE]));
            entSuratBerharga.setListing(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_LISTING]));
            entSuratBerharga.setPeringkat(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERINGKAT]));
            entSuratBerharga.setKodeTujuan(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_TUJUAN]));
            entSuratBerharga.setTglTerbit(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_DEBIT]));
            entSuratBerharga.setTglBeli(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_BELI]));
            entSuratBerharga.setTglJatuhTempo(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_JATUH_TEMPO]));
            entSuratBerharga.setKodeValuta(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_VALUTA]));
            entSuratBerharga.setNominal(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NOMINAL]));
            entSuratBerharga.setNilaiUangAsal(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NILAI_UANG_ASAL]));
            entSuratBerharga.setNilaiPasar(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NILAI_PASAR]));
            entSuratBerharga.setNilaiPerolehan(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NILAI_PEROLEHAN]));
            entSuratBerharga.setSukuBunga(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SUKU_BUNGA]));
            entSuratBerharga.setTunggakan(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TUNGGAKAN]));
            entSuratBerharga.setJmlHariTunggakan(rs.getInt(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_JML_HARI_TUNGGAKAN]));
            entSuratBerharga.setKodeKolektibilitas(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KOLEKTIBILITAS]));
            entSuratBerharga.setTglMacet(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_MACET]));
            entSuratBerharga.setKodeSebabMacet(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_SEBAB_MACET]));
            entSuratBerharga.setKodeKondisi(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KONDISI]));
            entSuratBerharga.setTglKondisi(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_KONDISI]));
            entSuratBerharga.setKeterangan(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KETERANGAN]));
            entSuratBerharga.setKodeKantorCabang(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]));
            entSuratBerharga.setOperasiData(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_OPERASI_DATA]));
            entSuratBerharga.setOpenDate(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_OPEN_DATE]));
            entSuratBerharga.setStatusData(rs.getInt(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_DATA]));
            entSuratBerharga.setPeriodeId(rs.getLong(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID]));
            entSuratBerharga.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));

        } catch (Exception e) {
        }
    }
    public static void resultToObjectJoin(ResultSet rs, SuratBerharga entSuratBerharga) {
        try {
            entSuratBerharga.setOID(rs.getLong(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID]));
            entSuratBerharga.setFlagDetail(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_FLAG_DETAIL]));
            entSuratBerharga.setNoRekening(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]));
            entSuratBerharga.setCif(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]));
            entSuratBerharga.setKodeJenis(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]));
            entSuratBerharga.setSovereignRate(rs.getInt(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SOVEREIGN_RATE]));
            entSuratBerharga.setListing(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_LISTING]));
            entSuratBerharga.setPeringkat(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERINGKAT]));
            entSuratBerharga.setKodeTujuan(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_TUJUAN]));
            entSuratBerharga.setTglTerbit(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_DEBIT]));
            entSuratBerharga.setTglBeli(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_BELI]));
            entSuratBerharga.setTglJatuhTempo(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_JATUH_TEMPO]));
            entSuratBerharga.setKodeValuta(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_VALUTA]));
            entSuratBerharga.setNominal(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NOMINAL]));
            entSuratBerharga.setNilaiUangAsal(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NILAI_UANG_ASAL]));
            entSuratBerharga.setNilaiPasar(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NILAI_PASAR]));
            entSuratBerharga.setNilaiPerolehan(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NILAI_PEROLEHAN]));
            entSuratBerharga.setSukuBunga(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SUKU_BUNGA]));
            entSuratBerharga.setTunggakan(rs.getDouble(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TUNGGAKAN]));
            entSuratBerharga.setJmlHariTunggakan(rs.getInt(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_JML_HARI_TUNGGAKAN]));
            entSuratBerharga.setKodeKolektibilitas(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KOLEKTIBILITAS]));
            entSuratBerharga.setTglMacet(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_MACET]));
            entSuratBerharga.setKodeSebabMacet(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_SEBAB_MACET]));
            entSuratBerharga.setKodeKondisi(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KONDISI]));
            entSuratBerharga.setTglKondisi(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_TGL_KONDISI]));
            entSuratBerharga.setKeterangan(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KETERANGAN]));
            entSuratBerharga.setKodeKantorCabang(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]));
            entSuratBerharga.setOperasiData(rs.getString(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_OPERASI_DATA]));
            entSuratBerharga.setOpenDate(rs.getDate(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_OPEN_DATE]));
            entSuratBerharga.setStatusData(rs.getInt(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_DATA]));
            entSuratBerharga.setJenisSuratBerharga(rs.getString(PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA]));
            entSuratBerharga.setPeriodeId(rs.getLong(PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID]));
            entSuratBerharga.setDebiturOid(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]));
            entSuratBerharga.setDebiturType(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
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
            String sql = "SELECT DISTINCT * FROM " + TBL_SURAT_BERHARGA;
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
                SuratBerharga entSuratBerharga = new SuratBerharga();
                resultToObject(rs, entSuratBerharga);
                lists.add(entSuratBerharga);
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
            String sql = ""
                + " SELECT DISTINCT sb.*, "
                + " jsb."+PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA]+","
                + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]+","
                + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+" "
                + " FROM "+TBL_SURAT_BERHARGA+" AS sb "
                + " INNER JOIN "+PstContentDataJenisSuratBerharga.TBL_CONTENT_DATA_JENIS_SURAT_BERHARGA+" AS jsb "
                + " ON jsb."+PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_KODE_OJK]+" = sb."+fieldNames[FLD_KODE_JENIS]+" "
                + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + " ON sb."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + " INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + " ON sb."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID];
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
                SuratBerharga entSuratBerharga = new SuratBerharga();
                resultToObjectJoin(rs, entSuratBerharga);
                lists.add(entSuratBerharga);
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

    public static boolean checkOID(long entSuratBerhargaId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_SURAT_BERHARGA + " WHERE "
                    + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID] + " = " + entSuratBerhargaId;
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
            String sql = "SELECT DISTINCT COUNT(" + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID] + ") FROM " + TBL_SURAT_BERHARGA;
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
            String sql = ""
                + " SELECT DISTINCT COUNT(sb." + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID] + ") "
                + " FROM "+TBL_SURAT_BERHARGA+" AS sb "
                + " INNER JOIN "+PstContentDataJenisSuratBerharga.TBL_CONTENT_DATA_JENIS_SURAT_BERHARGA+" AS jsb "
                + " ON jsb."+PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_KODE_OJK]+" = sb."+fieldNames[FLD_KODE_JENIS]
                + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + " ON sb."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + " INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + " ON sb."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                + " AND periode."+PstPeriode.fieldNames[PstDebitur.FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID];
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
                    SuratBerharga entSuratBerharga = (SuratBerharga) list.get(ls);
                    if (oid == entSuratBerharga.getOID()) {
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
