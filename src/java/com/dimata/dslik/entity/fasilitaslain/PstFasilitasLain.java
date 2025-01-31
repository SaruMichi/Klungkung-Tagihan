/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.fasilitaslain;

import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.contentdata.PstContentDataJenisFasilitas;
import com.dimata.dslik.entity.contentdata.PstContentDataSumberPenghasilan;
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
public class PstFasilitasLain extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    private String sqlQueryHistory = "";

    public static final String TBL_FASILITAS_LAIN = "dslik_fasilitas_lain";
    public static final int FLD_FASILITAS_LAIN_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_REKENING = 2;
    public static final int FLD_CIF = 3;
    public static final int FLD_KODE_JENIS_FASILITAS = 4;
    public static final int FLD_SUMBER_DANA = 5;
    public static final int FLD_TGL_MULAI = 6;
    public static final int FLD_TGL_JATUH_TEMPO = 7;
    public static final int FLD_SUKU_BUNGA = 8;
    public static final int FLD_KODE_VALUTA = 9;
    public static final int FLD_NOMINAL_JML_KEWAJIBAN = 10;
    public static final int FLD_NILAI_MATA_UANG_ASAL = 11;
    public static final int FLD_KODE_KOLEKTIBILITAS = 12;
    public static final int FLD_TGL_MACET = 13;
    public static final int FLD_KODE_SEBAB_MACET = 14;
    public static final int FLD_TUNGGAKAN = 15;
    public static final int FLD_JML_HARI_TUNGGAKAN = 16;
    public static final int FLD_KODE_KONDISI = 17;
    public static final int FLD_TGL_KONDISI = 18;
    public static final int FLD_KETERANGAN = 19;
    public static final int FLD_KODE_KANTOR_CABANG = 20;
    public static final int FLD_OPERASI_DATA = 21;
    public static final int FLD_OPEN_DATE = 22;
    public static final int FLD_STATUS_DATA = 23;
    public static final int FLD_PERIODE_ID = 24;
    public static final int FLD_STATUS_PERUBAHAN_DATA = 25;
    
    public static String[] fieldNames = {
        "FASILITAS_LAIN_OID",
        "FLAG_DETAIL",
        "NO_REKENING",
        "CIF",
        "KODE_JENIS_FASILITAS",
        "SUMBER_DANA",
        "TGL_MULAI",
        "TGL_JATUH_TEMPO",
        "SUKU_BUNGA",
        "KODE_VALUTA",
        "NOMINAL_JML_KEWAJIBAN",
        "NILAI_MATA_UANG_ASAL",
        "KODE_KOLEKTIBILITAS",
        "TGL_MACET",
        "KODE_SEBAB_MACET",
        "TUNGGAKAN",
        "JML_HARI_TUNGGAKAN",
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
        TYPE_STRING,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_INT,
        TYPE_LONG,
        TYPE_INT
    };

    public PstFasilitasLain() {
    }

    public PstFasilitasLain(int i) throws DBException {
        super(new PstFasilitasLain());
    }

    public PstFasilitasLain(String sOid) throws DBException {
        super(new PstFasilitasLain(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstFasilitasLain(long lOid) throws DBException {
        super(new PstFasilitasLain(0));
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
        return TBL_FASILITAS_LAIN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstFasilitasLain().getClass().getName();
    }

    public static FasilitasLain fetchExc(long oid) throws DBException {
        try {
            FasilitasLain entFasilitasLain = new FasilitasLain();
            PstFasilitasLain pstFasilitasLain = new PstFasilitasLain(oid);
            entFasilitasLain.setOID(oid);
            entFasilitasLain.setFlagDetail(pstFasilitasLain.getString(FLD_FLAG_DETAIL));
            entFasilitasLain.setNoRekening(pstFasilitasLain.getString(FLD_NO_REKENING));
            entFasilitasLain.setCif(pstFasilitasLain.getString(FLD_CIF));
            entFasilitasLain.setKodeJenisFasilitas(pstFasilitasLain.getString(FLD_KODE_JENIS_FASILITAS));
            entFasilitasLain.setSumberDana(pstFasilitasLain.getString(FLD_SUMBER_DANA));
            entFasilitasLain.setTglMulai(pstFasilitasLain.getDate(FLD_TGL_MULAI));
            entFasilitasLain.setTglJatuhTempo(pstFasilitasLain.getDate(FLD_TGL_JATUH_TEMPO));
            entFasilitasLain.setSukuBunga(pstFasilitasLain.getdouble(FLD_SUKU_BUNGA));
            entFasilitasLain.setKodeValuta(pstFasilitasLain.getString(FLD_KODE_VALUTA));
            entFasilitasLain.setNominalJmlKewajiban(pstFasilitasLain.getdouble(FLD_NOMINAL_JML_KEWAJIBAN));
            entFasilitasLain.setNilaiMataUangAsal(pstFasilitasLain.getdouble(FLD_NILAI_MATA_UANG_ASAL));
            entFasilitasLain.setKodeKolektibilitas(pstFasilitasLain.getString(FLD_KODE_KOLEKTIBILITAS));
            entFasilitasLain.setTglMacet(pstFasilitasLain.getDate(FLD_TGL_MACET));
            entFasilitasLain.setKodeSebabMacet(pstFasilitasLain.getString(FLD_KODE_SEBAB_MACET));
            entFasilitasLain.setTunggakan(pstFasilitasLain.getdouble(FLD_TUNGGAKAN));
            entFasilitasLain.setJmlHariTunggakan(pstFasilitasLain.getdouble(FLD_JML_HARI_TUNGGAKAN));
            entFasilitasLain.setKodeKondisi(pstFasilitasLain.getString(FLD_KODE_KONDISI));
            entFasilitasLain.setTglKondisi(pstFasilitasLain.getDate(FLD_TGL_KONDISI));
            entFasilitasLain.setKeterangan(pstFasilitasLain.getString(FLD_KETERANGAN));
            entFasilitasLain.setKodeKantorCabang(pstFasilitasLain.getString(FLD_KODE_KANTOR_CABANG));
            entFasilitasLain.setOperasiData(pstFasilitasLain.getString(FLD_OPERASI_DATA));
            entFasilitasLain.setOpenDate(pstFasilitasLain.getDate(FLD_OPEN_DATE));
            entFasilitasLain.setStatusData(pstFasilitasLain.getInt(FLD_STATUS_DATA));
            entFasilitasLain.setPeriodeId(pstFasilitasLain.getlong(FLD_PERIODE_ID));
            entFasilitasLain.setStatusPerubahanData(pstFasilitasLain.getInt(FLD_STATUS_PERUBAHAN_DATA));
            
            return entFasilitasLain;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstFasilitasLain(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        FasilitasLain entFasilitasLain = fetchExc(entity.getOID());
        entity = (Entity) entFasilitasLain;
        return entFasilitasLain.getOID();
    }

    public static synchronized long updateExc(FasilitasLain entFasilitasLain) throws DBException {
        try {
            if (entFasilitasLain.getOID() != 0) {
                PstFasilitasLain pstFasilitasLain = new PstFasilitasLain(entFasilitasLain.getOID());
                pstFasilitasLain.setString(FLD_FLAG_DETAIL, entFasilitasLain.getFlagDetail());
                pstFasilitasLain.setString(FLD_NO_REKENING, entFasilitasLain.getNoRekening());
                pstFasilitasLain.setString(FLD_CIF, entFasilitasLain.getCif());
                pstFasilitasLain.setString(FLD_KODE_JENIS_FASILITAS, entFasilitasLain.getKodeJenisFasilitas());
                pstFasilitasLain.setString(FLD_SUMBER_DANA, entFasilitasLain.getSumberDana());
                pstFasilitasLain.setDate(FLD_TGL_MULAI, entFasilitasLain.getTglMulai());
                pstFasilitasLain.setDate(FLD_TGL_JATUH_TEMPO, entFasilitasLain.getTglJatuhTempo());
                pstFasilitasLain.setDouble(FLD_SUKU_BUNGA, entFasilitasLain.getSukuBunga());
                pstFasilitasLain.setString(FLD_KODE_VALUTA, entFasilitasLain.getKodeValuta());
                pstFasilitasLain.setDouble(FLD_NOMINAL_JML_KEWAJIBAN, entFasilitasLain.getNominalJmlKewajiban());
                pstFasilitasLain.setDouble(FLD_NILAI_MATA_UANG_ASAL, entFasilitasLain.getNilaiMataUangAsal());
                pstFasilitasLain.setString(FLD_KODE_KOLEKTIBILITAS, entFasilitasLain.getKodeKolektibilitas());
                pstFasilitasLain.setDate(FLD_TGL_MACET, entFasilitasLain.getTglMacet());
                pstFasilitasLain.setString(FLD_KODE_SEBAB_MACET, entFasilitasLain.getKodeSebabMacet());
                pstFasilitasLain.setDouble(FLD_TUNGGAKAN, entFasilitasLain.getTunggakan());
                pstFasilitasLain.setDouble(FLD_JML_HARI_TUNGGAKAN, entFasilitasLain.getJmlHariTunggakan());
                pstFasilitasLain.setString(FLD_KODE_KONDISI, entFasilitasLain.getKodeKondisi());
                pstFasilitasLain.setDate(FLD_TGL_KONDISI, entFasilitasLain.getTglKondisi());
                pstFasilitasLain.setString(FLD_KETERANGAN, entFasilitasLain.getKeterangan());
                pstFasilitasLain.setString(FLD_KODE_KANTOR_CABANG, entFasilitasLain.getKodeKantorCabang());
                pstFasilitasLain.setString(FLD_OPERASI_DATA, entFasilitasLain.getOperasiData());
                pstFasilitasLain.setDate(FLD_OPEN_DATE, entFasilitasLain.getOpenDate());
                pstFasilitasLain.setInt(FLD_STATUS_DATA, entFasilitasLain.getStatusData());
                pstFasilitasLain.setLong(FLD_PERIODE_ID, entFasilitasLain.getPeriodeId());
                pstFasilitasLain.setInt( FLD_STATUS_PERUBAHAN_DATA,  entFasilitasLain.getStatusPerubahanData());
                
                pstFasilitasLain.update();
                
                pstFasilitasLain.setSqlQueryHistory("");
                pstFasilitasLain.setSqlQueryHistory(pstFasilitasLain.getUpdateSQL());
                
                return entFasilitasLain.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstFasilitasLain(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized FasilitasLain updateExcObj(FasilitasLain entFasilitasLain) throws DBException {
        try {
            if (entFasilitasLain.getOID() != 0) {
                PstFasilitasLain pstFasilitasLain = new PstFasilitasLain(entFasilitasLain.getOID());
                pstFasilitasLain.setString(FLD_FLAG_DETAIL, entFasilitasLain.getFlagDetail());
                pstFasilitasLain.setString(FLD_NO_REKENING, entFasilitasLain.getNoRekening());
                pstFasilitasLain.setString(FLD_CIF, entFasilitasLain.getCif());
                pstFasilitasLain.setString(FLD_KODE_JENIS_FASILITAS, entFasilitasLain.getKodeJenisFasilitas());
                pstFasilitasLain.setString(FLD_SUMBER_DANA, entFasilitasLain.getSumberDana());
                pstFasilitasLain.setDate(FLD_TGL_MULAI, entFasilitasLain.getTglMulai());
                pstFasilitasLain.setDate(FLD_TGL_JATUH_TEMPO, entFasilitasLain.getTglJatuhTempo());
                pstFasilitasLain.setDouble(FLD_SUKU_BUNGA, entFasilitasLain.getSukuBunga());
                pstFasilitasLain.setString(FLD_KODE_VALUTA, entFasilitasLain.getKodeValuta());
                pstFasilitasLain.setDouble(FLD_NOMINAL_JML_KEWAJIBAN, entFasilitasLain.getNominalJmlKewajiban());
                pstFasilitasLain.setDouble(FLD_NILAI_MATA_UANG_ASAL, entFasilitasLain.getNilaiMataUangAsal());
                pstFasilitasLain.setString(FLD_KODE_KOLEKTIBILITAS, entFasilitasLain.getKodeKolektibilitas());
                pstFasilitasLain.setDate(FLD_TGL_MACET, entFasilitasLain.getTglMacet());
                pstFasilitasLain.setString(FLD_KODE_SEBAB_MACET, entFasilitasLain.getKodeSebabMacet());
                pstFasilitasLain.setDouble(FLD_TUNGGAKAN, entFasilitasLain.getTunggakan());
                pstFasilitasLain.setDouble(FLD_JML_HARI_TUNGGAKAN, entFasilitasLain.getJmlHariTunggakan());
                pstFasilitasLain.setString(FLD_KODE_KONDISI, entFasilitasLain.getKodeKondisi());
                pstFasilitasLain.setDate(FLD_TGL_KONDISI, entFasilitasLain.getTglKondisi());
                pstFasilitasLain.setString(FLD_KETERANGAN, entFasilitasLain.getKeterangan());
                pstFasilitasLain.setString(FLD_KODE_KANTOR_CABANG, entFasilitasLain.getKodeKantorCabang());
                pstFasilitasLain.setString(FLD_OPERASI_DATA, entFasilitasLain.getOperasiData());
                pstFasilitasLain.setDate(FLD_OPEN_DATE, entFasilitasLain.getOpenDate());
                pstFasilitasLain.setInt(FLD_STATUS_DATA, entFasilitasLain.getStatusData());
                pstFasilitasLain.setLong(FLD_PERIODE_ID, entFasilitasLain.getPeriodeId());
                pstFasilitasLain.setInt(FLD_STATUS_PERUBAHAN_DATA,  entFasilitasLain.getStatusPerubahanData());
                pstFasilitasLain.update();
                
                pstFasilitasLain.setSqlQueryHistory("");
                pstFasilitasLain.setSqlQueryHistory(pstFasilitasLain.getUpdateSQL());
                entFasilitasLain.setSqlHistory(pstFasilitasLain.getUpdateSQL());
                return entFasilitasLain;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstFasilitasLain(0), DBException.UNKNOWN);
        }
        return entFasilitasLain;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((FasilitasLain) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstFasilitasLain pstFasilitasLain = new PstFasilitasLain(oid);
            pstFasilitasLain.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstFasilitasLain(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(FasilitasLain entFasilitasLain) throws DBException {
        try {
            PstFasilitasLain pstFasilitasLain = new PstFasilitasLain(0);
            pstFasilitasLain.setString(FLD_FLAG_DETAIL, entFasilitasLain.getFlagDetail());
            pstFasilitasLain.setString(FLD_NO_REKENING, entFasilitasLain.getNoRekening());
            pstFasilitasLain.setString(FLD_CIF, entFasilitasLain.getCif());
            pstFasilitasLain.setString(FLD_KODE_JENIS_FASILITAS, entFasilitasLain.getKodeJenisFasilitas());
            pstFasilitasLain.setString(FLD_SUMBER_DANA, entFasilitasLain.getSumberDana());
            pstFasilitasLain.setDate(FLD_TGL_MULAI, entFasilitasLain.getTglMulai());
            pstFasilitasLain.setDate(FLD_TGL_JATUH_TEMPO, entFasilitasLain.getTglJatuhTempo());
            pstFasilitasLain.setDouble(FLD_SUKU_BUNGA, entFasilitasLain.getSukuBunga());
            pstFasilitasLain.setString(FLD_KODE_VALUTA, entFasilitasLain.getKodeValuta());
            pstFasilitasLain.setDouble(FLD_NOMINAL_JML_KEWAJIBAN, entFasilitasLain.getNominalJmlKewajiban());
            pstFasilitasLain.setDouble(FLD_NILAI_MATA_UANG_ASAL, entFasilitasLain.getNilaiMataUangAsal());
            pstFasilitasLain.setString(FLD_KODE_KOLEKTIBILITAS, entFasilitasLain.getKodeKolektibilitas());
            pstFasilitasLain.setDate(FLD_TGL_MACET, entFasilitasLain.getTglMacet());
            pstFasilitasLain.setString(FLD_KODE_SEBAB_MACET, entFasilitasLain.getKodeSebabMacet());
            pstFasilitasLain.setDouble(FLD_TUNGGAKAN, entFasilitasLain.getTunggakan());
            pstFasilitasLain.setDouble(FLD_JML_HARI_TUNGGAKAN, entFasilitasLain.getJmlHariTunggakan());
            pstFasilitasLain.setString(FLD_KODE_KONDISI, entFasilitasLain.getKodeKondisi());
            pstFasilitasLain.setDate(FLD_TGL_KONDISI, entFasilitasLain.getTglKondisi());
            pstFasilitasLain.setString(FLD_KETERANGAN, entFasilitasLain.getKeterangan());
            pstFasilitasLain.setString(FLD_KODE_KANTOR_CABANG, entFasilitasLain.getKodeKantorCabang());
            pstFasilitasLain.setString(FLD_OPERASI_DATA, entFasilitasLain.getOperasiData());
            pstFasilitasLain.setDate(FLD_OPEN_DATE, entFasilitasLain.getOpenDate());
            pstFasilitasLain.setInt(FLD_STATUS_DATA, entFasilitasLain.getStatusData());
            pstFasilitasLain.setLong(FLD_PERIODE_ID, entFasilitasLain.getPeriodeId());
            pstFasilitasLain.setInt(FLD_STATUS_PERUBAHAN_DATA,  entFasilitasLain.getStatusPerubahanData());
            pstFasilitasLain.insert();
            
            pstFasilitasLain.setSqlQueryHistory("");
            pstFasilitasLain.setSqlQueryHistory(pstFasilitasLain.getInsertSQL());
                
            entFasilitasLain.setOID(pstFasilitasLain.getLong(FLD_FASILITAS_LAIN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstFasilitasLain(0), DBException.UNKNOWN);
        }
        return entFasilitasLain.getOID();
    }
    
    public static synchronized FasilitasLain insertExcObj(FasilitasLain entFasilitasLain) throws DBException {
        try {
            PstFasilitasLain pstFasilitasLain = new PstFasilitasLain(0);
            pstFasilitasLain.setString(FLD_FLAG_DETAIL, entFasilitasLain.getFlagDetail());
            pstFasilitasLain.setString(FLD_NO_REKENING, entFasilitasLain.getNoRekening());
            pstFasilitasLain.setString(FLD_CIF, entFasilitasLain.getCif());
            pstFasilitasLain.setString(FLD_KODE_JENIS_FASILITAS, entFasilitasLain.getKodeJenisFasilitas());
            pstFasilitasLain.setString(FLD_SUMBER_DANA, entFasilitasLain.getSumberDana());
            pstFasilitasLain.setDate(FLD_TGL_MULAI, entFasilitasLain.getTglMulai());
            pstFasilitasLain.setDate(FLD_TGL_JATUH_TEMPO, entFasilitasLain.getTglJatuhTempo());
            pstFasilitasLain.setDouble(FLD_SUKU_BUNGA, entFasilitasLain.getSukuBunga());
            pstFasilitasLain.setString(FLD_KODE_VALUTA, entFasilitasLain.getKodeValuta());
            pstFasilitasLain.setDouble(FLD_NOMINAL_JML_KEWAJIBAN, entFasilitasLain.getNominalJmlKewajiban());
            pstFasilitasLain.setDouble(FLD_NILAI_MATA_UANG_ASAL, entFasilitasLain.getNilaiMataUangAsal());
            pstFasilitasLain.setString(FLD_KODE_KOLEKTIBILITAS, entFasilitasLain.getKodeKolektibilitas());
            pstFasilitasLain.setDate(FLD_TGL_MACET, entFasilitasLain.getTglMacet());
            pstFasilitasLain.setString(FLD_KODE_SEBAB_MACET, entFasilitasLain.getKodeSebabMacet());
            pstFasilitasLain.setDouble(FLD_TUNGGAKAN, entFasilitasLain.getTunggakan());
            pstFasilitasLain.setDouble(FLD_JML_HARI_TUNGGAKAN, entFasilitasLain.getJmlHariTunggakan());
            pstFasilitasLain.setString(FLD_KODE_KONDISI, entFasilitasLain.getKodeKondisi());
            pstFasilitasLain.setDate(FLD_TGL_KONDISI, entFasilitasLain.getTglKondisi());
            pstFasilitasLain.setString(FLD_KETERANGAN, entFasilitasLain.getKeterangan());
            pstFasilitasLain.setString(FLD_KODE_KANTOR_CABANG, entFasilitasLain.getKodeKantorCabang());
            pstFasilitasLain.setString(FLD_OPERASI_DATA, entFasilitasLain.getOperasiData());
            pstFasilitasLain.setDate(FLD_OPEN_DATE, entFasilitasLain.getOpenDate());
            pstFasilitasLain.setInt(FLD_STATUS_DATA, entFasilitasLain.getStatusData());
            pstFasilitasLain.setLong(FLD_PERIODE_ID, entFasilitasLain.getPeriodeId());
            pstFasilitasLain.setInt(FLD_STATUS_PERUBAHAN_DATA,  entFasilitasLain.getStatusPerubahanData());
            pstFasilitasLain.insert();
            
            pstFasilitasLain.setSqlQueryHistory("");
            pstFasilitasLain.setSqlQueryHistory(pstFasilitasLain.getInsertSQL());
            
            entFasilitasLain.setSqlHistory(pstFasilitasLain.getInsertSQL());
            entFasilitasLain.setOID(pstFasilitasLain.getlong(FLD_FASILITAS_LAIN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstFasilitasLain(0), DBException.UNKNOWN);
        }
        return entFasilitasLain;
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((FasilitasLain) entity);
    }

    public static void resultToObject(ResultSet rs, FasilitasLain entFasilitasLain) {
        try {
            entFasilitasLain.setOID(rs.getLong(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FASILITAS_LAIN_OID]));
            entFasilitasLain.setFlagDetail(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FLAG_DETAIL]));
            entFasilitasLain.setNoRekening(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]));
            entFasilitasLain.setCif(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]));
            entFasilitasLain.setKodeJenisFasilitas(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]));
            entFasilitasLain.setSumberDana(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_SUMBER_DANA]));
            entFasilitasLain.setTglMulai(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TGL_MULAI]));
            entFasilitasLain.setTglJatuhTempo(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TGL_JATUH_TEMPO]));
            entFasilitasLain.setSukuBunga(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_SUKU_BUNGA]));
            entFasilitasLain.setKodeValuta(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_VALUTA]));
            entFasilitasLain.setNominalJmlKewajiban(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NOMINAL_JML_KEWAJIBAN]));
            entFasilitasLain.setNilaiMataUangAsal(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NILAI_MATA_UANG_ASAL]));
            entFasilitasLain.setKodeKolektibilitas(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KOLEKTIBILITAS]));
            entFasilitasLain.setTglMacet(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TGL_MACET]));
            entFasilitasLain.setKodeSebabMacet(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_SEBAB_MACET]));
            entFasilitasLain.setTunggakan(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TUNGGAKAN]));
            entFasilitasLain.setJmlHariTunggakan(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_JML_HARI_TUNGGAKAN]));
            entFasilitasLain.setKodeKondisi(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KONDISI]));
            entFasilitasLain.setTglKondisi(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TGL_KONDISI]));
            entFasilitasLain.setKeterangan(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KETERANGAN]));
            entFasilitasLain.setKodeKantorCabang(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]));
            entFasilitasLain.setOperasiData(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_OPERASI_DATA]));
            entFasilitasLain.setOpenDate(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_OPEN_DATE]));
            entFasilitasLain.setStatusData(rs.getInt(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_DATA]));
            entFasilitasLain.setPeriodeId(rs.getLong(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID]));
            
            
            entFasilitasLain.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));

            
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoin(ResultSet rs, FasilitasLain entFasilitasLain) {
        try {
            entFasilitasLain.setOID(rs.getLong(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FASILITAS_LAIN_OID]));
            entFasilitasLain.setFlagDetail(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FLAG_DETAIL]));
            entFasilitasLain.setNoRekening(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]));
            entFasilitasLain.setCif(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]));
            entFasilitasLain.setKodeJenisFasilitas(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]));
            entFasilitasLain.setSumberDana(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_SUMBER_DANA]));
            entFasilitasLain.setTglMulai(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TGL_MULAI]));
            entFasilitasLain.setTglJatuhTempo(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TGL_JATUH_TEMPO]));
            entFasilitasLain.setSukuBunga(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_SUKU_BUNGA]));
            entFasilitasLain.setKodeValuta(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_VALUTA]));
            entFasilitasLain.setNominalJmlKewajiban(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NOMINAL_JML_KEWAJIBAN]));
            entFasilitasLain.setNilaiMataUangAsal(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NILAI_MATA_UANG_ASAL]));
            entFasilitasLain.setKodeKolektibilitas(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KOLEKTIBILITAS]));
            entFasilitasLain.setTglMacet(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TGL_MACET]));
            entFasilitasLain.setKodeSebabMacet(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_SEBAB_MACET]));
            entFasilitasLain.setTunggakan(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TUNGGAKAN]));
            entFasilitasLain.setJmlHariTunggakan(rs.getDouble(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_JML_HARI_TUNGGAKAN]));
            entFasilitasLain.setKodeKondisi(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KONDISI]));
            entFasilitasLain.setTglKondisi(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TGL_KONDISI]));
            entFasilitasLain.setKeterangan(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KETERANGAN]));
            entFasilitasLain.setKodeKantorCabang(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]));
            entFasilitasLain.setOperasiData(rs.getString(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_OPERASI_DATA]));
            entFasilitasLain.setOpenDate(rs.getDate(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_OPEN_DATE]));
            entFasilitasLain.setStatusData(rs.getInt(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_DATA]));
            entFasilitasLain.setNamaJenisFasilitas(rs.getString(PstContentDataJenisFasilitas.fieldNames[PstContentDataJenisFasilitas.FLD_JENIS_FASILITAS]));            
            entFasilitasLain.setPeriodeId(rs.getLong(PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID]));
            entFasilitasLain.setDebiturOid(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]));
            entFasilitasLain.setDebiturType(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
            entFasilitasLain.setNamaSumberDana(rs.getString(PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN]));
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
            String sql = "SELECT DISTINCT * FROM " + TBL_FASILITAS_LAIN;
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
                FasilitasLain entFasilitasLain = new FasilitasLain();
                resultToObject(rs, entFasilitasLain);
                lists.add(entFasilitasLain);
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
                + " SELECT DISTINCT fl.*, "
                + " jf."+PstContentDataJenisFasilitas.fieldNames[PstContentDataJenisFasilitas.FLD_JENIS_FASILITAS]+","
                + " sd."+PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN]+","
                + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]+","
                + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+""
                + " FROM "+TBL_FASILITAS_LAIN+" fl "
                + " LEFT JOIN "+PstContentDataJenisFasilitas.TBL_CONTENT_DATA_JENIS_FASILITAS+" jf "
                + " ON fl."+fieldNames[FLD_KODE_JENIS_FASILITAS]+" = jf."+PstContentDataJenisFasilitas.fieldNames[PstContentDataJenisFasilitas.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataSumberPenghasilan.TBL_CONTENT_DATA_SUMBER_PENGHASILAN+" sd"
                + " ON fl."+fieldNames[FLD_SUMBER_DANA]+" = sd."+PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_KODE_OJK]+""
                + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + " ON fl."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + " INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + " ON fl."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
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
                FasilitasLain entFasilitasLain = new FasilitasLain();
                resultToObjectJoin(rs, entFasilitasLain);
                lists.add(entFasilitasLain);
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

    public static boolean checkOID(long entFasilitasLainId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_FASILITAS_LAIN + " WHERE "
                    + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FASILITAS_LAIN_OID] + " = " + entFasilitasLainId;
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
            String sql = "SELECT DISTINCT COUNT(" + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FASILITAS_LAIN_OID] + ") FROM " + TBL_FASILITAS_LAIN;
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
                + " SELECT DISTINCT COUNT(fl." + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FASILITAS_LAIN_OID] + ") "
                + " FROM "+TBL_FASILITAS_LAIN+" fl "
                + " LEFT JOIN "+PstContentDataJenisFasilitas.TBL_CONTENT_DATA_JENIS_FASILITAS+" jf "
                + " ON fl."+fieldNames[FLD_KODE_JENIS_FASILITAS]+" = jf."+PstContentDataJenisFasilitas.fieldNames[PstContentDataJenisFasilitas.FLD_KODE_OJK]+" "                 
                + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + "ON fl."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + "ON fl."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+" "
                + "LEFT JOIN "+PstContentDataSumberPenghasilan.TBL_CONTENT_DATA_SUMBER_PENGHASILAN+" AS sd "
                + "ON fl."+fieldNames[FLD_SUMBER_DANA]+"=sd."+PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_KODE_OJK];
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
                    FasilitasLain entFasilitasLain = (FasilitasLain) list.get(ls);
                    if (oid == entFasilitasLain.getOID()) {
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
