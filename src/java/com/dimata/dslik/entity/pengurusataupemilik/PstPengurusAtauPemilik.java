/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.pengurusataupemilik;

import com.dimata.dslik.entity.contentdata.PstContentDataJenisIdentitas;
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
public class PstPengurusAtauPemilik extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    private String sqlQueryHistory = "";

    public static final String TBL_PENGURUS_ATAU_PEMILIK = "dslik_pengurus_atau_pemilik";
    public static final int FLD_PENGURUS_ATAU_PEMILIK_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_IDENTITAS = 2;
    public static final int FLD_CIF = 3;
    public static final int FLD_JENIS_IDENTITAS = 4;
    public static final int FLD_NAMA_PENGURUS = 5;
    public static final int FLD_JENIS_KELAMIN = 6;
    public static final int FLD_ALAMAT = 7;
    public static final int FLD_KELURAHAN = 8;
    public static final int FLD_KECAMATAN = 9;
    public static final int FLD_KODE_KABUPATEN = 10;
    public static final int FLD_KODE_JABATAN = 11;
    public static final int FLD_PANGSA_KEPEMILIKAN = 12;
    public static final int FLD_STATUS_PENGURUS = 13;
    public static final int FLD_KODE_KANTOR_CABANG = 14;
    public static final int FLD_OPERASI_DATA = 15;
    public static final int FLD_OPEN_DATE = 16;
    public static final int FLD_STATUS_DATA = 17;
    public static final int FLD_PERIODE_ID = 18;
    public static final int FLD_STATUS_PERUBAHAN_DATA=19;
    
    public static String[] fieldNames = {
        "PENGURUS_OID",
        "FLAG_DETAIL",
        "NO_IDENTITAS",
        "CIF",
        "JENIS_IDENTITAS",
        "NAMA_PENGURUS",
        "JENIS_KELAMIN",
        "ALAMAT",
        "KELURAHAN",
        "KECAMATAN",
        "KODE_KABUPATEN",
        "KODE_JABATAN",
        "PANGSA_KEPEMILIKAN",
        "STATUS_PENGURUS",
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
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_INT,
        TYPE_LONG,
        TYPE_INT
    };

    public PstPengurusAtauPemilik() {
    }

    public PstPengurusAtauPemilik(int i) throws DBException {
        super(new PstPengurusAtauPemilik());
    }

    public PstPengurusAtauPemilik(String sOid) throws DBException {
        super(new PstPengurusAtauPemilik(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstPengurusAtauPemilik(long lOid) throws DBException {
        super(new PstPengurusAtauPemilik(0));
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
        return TBL_PENGURUS_ATAU_PEMILIK;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstPengurusAtauPemilik().getClass().getName();
    }

    public static PengurusAtauPemilik fetchExc(long oid) throws DBException {
        try {
            PengurusAtauPemilik entPengurusAtauPemilik = new PengurusAtauPemilik();
            PstPengurusAtauPemilik pstPengurusAtauPemilik = new PstPengurusAtauPemilik(oid);
            entPengurusAtauPemilik.setOID(oid);
            entPengurusAtauPemilik.setFlagDetail(pstPengurusAtauPemilik.getString(FLD_FLAG_DETAIL));
            entPengurusAtauPemilik.setNoIdentitas(pstPengurusAtauPemilik.getString(FLD_NO_IDENTITAS));
            entPengurusAtauPemilik.setCif(pstPengurusAtauPemilik.getString(FLD_CIF));
            entPengurusAtauPemilik.setJenisIdentitas(pstPengurusAtauPemilik.getString(FLD_JENIS_IDENTITAS));
            entPengurusAtauPemilik.setNamaPengurus(pstPengurusAtauPemilik.getString(FLD_NAMA_PENGURUS));
            entPengurusAtauPemilik.setJenisKelamin(pstPengurusAtauPemilik.getString(FLD_JENIS_KELAMIN));
            entPengurusAtauPemilik.setAlamat(pstPengurusAtauPemilik.getString(FLD_ALAMAT));
            entPengurusAtauPemilik.setKelurahan(pstPengurusAtauPemilik.getString(FLD_KELURAHAN));
            entPengurusAtauPemilik.setKecamatan(pstPengurusAtauPemilik.getString(FLD_KECAMATAN));
            entPengurusAtauPemilik.setKodeKabupaten(pstPengurusAtauPemilik.getString(FLD_KODE_KABUPATEN));
            entPengurusAtauPemilik.setKodeJabatan(pstPengurusAtauPemilik.getString(FLD_KODE_JABATAN));
            entPengurusAtauPemilik.setPangsaKepemilikan(pstPengurusAtauPemilik.getString(FLD_PANGSA_KEPEMILIKAN));
            entPengurusAtauPemilik.setStatusPengurus(pstPengurusAtauPemilik.getString(FLD_STATUS_PENGURUS));
            entPengurusAtauPemilik.setKodeKantorCabang(pstPengurusAtauPemilik.getString(FLD_KODE_KANTOR_CABANG));
            entPengurusAtauPemilik.setOperasiData(pstPengurusAtauPemilik.getString(FLD_OPERASI_DATA));
            entPengurusAtauPemilik.setOpenDate(pstPengurusAtauPemilik.getDate(FLD_OPEN_DATE));
            entPengurusAtauPemilik.setStatusData(pstPengurusAtauPemilik.getInt(FLD_STATUS_DATA));
            entPengurusAtauPemilik.setPeriodeId(pstPengurusAtauPemilik.getlong(FLD_PERIODE_ID));
            
            entPengurusAtauPemilik.setStatusPerubahanData(pstPengurusAtauPemilik.getInt(FLD_STATUS_PERUBAHAN_DATA));
            
            return entPengurusAtauPemilik;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPengurusAtauPemilik(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        PengurusAtauPemilik entPengurusAtauPemilik = fetchExc(entity.getOID());
        entity = (Entity) entPengurusAtauPemilik;
        return entPengurusAtauPemilik.getOID();
    }

    public static synchronized long updateExc(PengurusAtauPemilik entPengurusAtauPemilik) throws DBException {
        try {
            if (entPengurusAtauPemilik.getOID() != 0) {
                PstPengurusAtauPemilik pstPengurusAtauPemilik = new PstPengurusAtauPemilik(entPengurusAtauPemilik.getOID());
                pstPengurusAtauPemilik.setString(FLD_FLAG_DETAIL, entPengurusAtauPemilik.getFlagDetail());
                pstPengurusAtauPemilik.setString(FLD_NO_IDENTITAS, entPengurusAtauPemilik.getNoIdentitas());
                pstPengurusAtauPemilik.setString(FLD_CIF, entPengurusAtauPemilik.getCif());
                pstPengurusAtauPemilik.setString(FLD_JENIS_IDENTITAS, entPengurusAtauPemilik.getJenisIdentitas());
                pstPengurusAtauPemilik.setString(FLD_NAMA_PENGURUS, entPengurusAtauPemilik.getNamaPengurus());
                pstPengurusAtauPemilik.setString(FLD_JENIS_KELAMIN, entPengurusAtauPemilik.getJenisKelamin());
                pstPengurusAtauPemilik.setString(FLD_ALAMAT, entPengurusAtauPemilik.getAlamat());
                pstPengurusAtauPemilik.setString(FLD_KELURAHAN, entPengurusAtauPemilik.getKelurahan());
                pstPengurusAtauPemilik.setString(FLD_KECAMATAN, entPengurusAtauPemilik.getKecamatan());
                pstPengurusAtauPemilik.setString(FLD_KODE_KABUPATEN, entPengurusAtauPemilik.getKodeKabupaten());
                pstPengurusAtauPemilik.setString(FLD_KODE_JABATAN, entPengurusAtauPemilik.getKodeJabatan());
                pstPengurusAtauPemilik.setString(FLD_PANGSA_KEPEMILIKAN, entPengurusAtauPemilik.getPangsaKepemilikan());
                pstPengurusAtauPemilik.setString(FLD_STATUS_PENGURUS, entPengurusAtauPemilik.getStatusPengurus());
                pstPengurusAtauPemilik.setString(FLD_KODE_KANTOR_CABANG, entPengurusAtauPemilik.getKodeKantorCabang());
                pstPengurusAtauPemilik.setString(FLD_OPERASI_DATA, entPengurusAtauPemilik.getOperasiData());
                pstPengurusAtauPemilik.setDate(FLD_OPEN_DATE, entPengurusAtauPemilik.getOpenDate());
                pstPengurusAtauPemilik.setInt(FLD_STATUS_DATA, entPengurusAtauPemilik.getStatusData());
                pstPengurusAtauPemilik.setLong(FLD_PERIODE_ID, entPengurusAtauPemilik.getPeriodeId());
                pstPengurusAtauPemilik.setInt(FLD_STATUS_PERUBAHAN_DATA, entPengurusAtauPemilik.getStatusPerubahanData());
                
                pstPengurusAtauPemilik.update();
                
                pstPengurusAtauPemilik.setSqlQueryHistory("");
                pstPengurusAtauPemilik.setSqlQueryHistory(pstPengurusAtauPemilik.getUpdateSQL());
                
                return entPengurusAtauPemilik.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPengurusAtauPemilik(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized PengurusAtauPemilik updateExcObj(PengurusAtauPemilik entPengurusAtauPemilik) throws DBException {
        try {
            if (entPengurusAtauPemilik.getOID() != 0) {
                PstPengurusAtauPemilik pstPengurusAtauPemilik = new PstPengurusAtauPemilik(entPengurusAtauPemilik.getOID());
                pstPengurusAtauPemilik.setString(FLD_FLAG_DETAIL, entPengurusAtauPemilik.getFlagDetail());
                pstPengurusAtauPemilik.setString(FLD_NO_IDENTITAS, entPengurusAtauPemilik.getNoIdentitas());
                pstPengurusAtauPemilik.setString(FLD_CIF, entPengurusAtauPemilik.getCif());
                pstPengurusAtauPemilik.setString(FLD_JENIS_IDENTITAS, entPengurusAtauPemilik.getJenisIdentitas());
                pstPengurusAtauPemilik.setString(FLD_NAMA_PENGURUS, entPengurusAtauPemilik.getNamaPengurus());
                pstPengurusAtauPemilik.setString(FLD_JENIS_KELAMIN, entPengurusAtauPemilik.getJenisKelamin());
                pstPengurusAtauPemilik.setString(FLD_ALAMAT, entPengurusAtauPemilik.getAlamat());
                pstPengurusAtauPemilik.setString(FLD_KELURAHAN, entPengurusAtauPemilik.getKelurahan());
                pstPengurusAtauPemilik.setString(FLD_KECAMATAN, entPengurusAtauPemilik.getKecamatan());
                pstPengurusAtauPemilik.setString(FLD_KODE_KABUPATEN, entPengurusAtauPemilik.getKodeKabupaten());
                pstPengurusAtauPemilik.setString(FLD_KODE_JABATAN, entPengurusAtauPemilik.getKodeJabatan());
                pstPengurusAtauPemilik.setString(FLD_PANGSA_KEPEMILIKAN, entPengurusAtauPemilik.getPangsaKepemilikan());
                pstPengurusAtauPemilik.setString(FLD_STATUS_PENGURUS, entPengurusAtauPemilik.getStatusPengurus());
                pstPengurusAtauPemilik.setString(FLD_KODE_KANTOR_CABANG, entPengurusAtauPemilik.getKodeKantorCabang());
                pstPengurusAtauPemilik.setString(FLD_OPERASI_DATA, entPengurusAtauPemilik.getOperasiData());
                pstPengurusAtauPemilik.setDate(FLD_OPEN_DATE, entPengurusAtauPemilik.getOpenDate());
                pstPengurusAtauPemilik.setInt(FLD_STATUS_DATA, entPengurusAtauPemilik.getStatusData());
                pstPengurusAtauPemilik.setLong(FLD_PERIODE_ID, entPengurusAtauPemilik.getPeriodeId());
                pstPengurusAtauPemilik.setInt(FLD_STATUS_PERUBAHAN_DATA, entPengurusAtauPemilik.getStatusPerubahanData());
                pstPengurusAtauPemilik.update();
                
                pstPengurusAtauPemilik.setSqlQueryHistory("");
                entPengurusAtauPemilik.setSqlHistory(pstPengurusAtauPemilik.getUpdateSQL());
                pstPengurusAtauPemilik.setSqlQueryHistory(pstPengurusAtauPemilik.getUpdateSQL());
                
                return entPengurusAtauPemilik;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPengurusAtauPemilik(0), DBException.UNKNOWN);
        }
        return entPengurusAtauPemilik;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((PengurusAtauPemilik) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstPengurusAtauPemilik pstPengurusAtauPemilik = new PstPengurusAtauPemilik(oid);
            pstPengurusAtauPemilik.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPengurusAtauPemilik(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(PengurusAtauPemilik entPengurusAtauPemilik) throws DBException {
        try {
            PstPengurusAtauPemilik pstPengurusAtauPemilik = new PstPengurusAtauPemilik(0);
            pstPengurusAtauPemilik.setString(FLD_FLAG_DETAIL, entPengurusAtauPemilik.getFlagDetail());
            pstPengurusAtauPemilik.setString(FLD_NO_IDENTITAS, entPengurusAtauPemilik.getNoIdentitas());
            pstPengurusAtauPemilik.setString(FLD_CIF, entPengurusAtauPemilik.getCif());
            pstPengurusAtauPemilik.setString(FLD_JENIS_IDENTITAS, entPengurusAtauPemilik.getJenisIdentitas());
            pstPengurusAtauPemilik.setString(FLD_NAMA_PENGURUS, entPengurusAtauPemilik.getNamaPengurus());
            pstPengurusAtauPemilik.setString(FLD_JENIS_KELAMIN, entPengurusAtauPemilik.getJenisKelamin());
            pstPengurusAtauPemilik.setString(FLD_ALAMAT, entPengurusAtauPemilik.getAlamat());
            pstPengurusAtauPemilik.setString(FLD_KELURAHAN, entPengurusAtauPemilik.getKelurahan());
            pstPengurusAtauPemilik.setString(FLD_KECAMATAN, entPengurusAtauPemilik.getKecamatan());
            pstPengurusAtauPemilik.setString(FLD_KODE_KABUPATEN, entPengurusAtauPemilik.getKodeKabupaten());
            pstPengurusAtauPemilik.setString(FLD_KODE_JABATAN, entPengurusAtauPemilik.getKodeJabatan());
            pstPengurusAtauPemilik.setString(FLD_PANGSA_KEPEMILIKAN, entPengurusAtauPemilik.getPangsaKepemilikan());
            pstPengurusAtauPemilik.setString(FLD_STATUS_PENGURUS, entPengurusAtauPemilik.getStatusPengurus());
            pstPengurusAtauPemilik.setString(FLD_KODE_KANTOR_CABANG, entPengurusAtauPemilik.getKodeKantorCabang());
            pstPengurusAtauPemilik.setString(FLD_OPERASI_DATA, entPengurusAtauPemilik.getOperasiData());
            pstPengurusAtauPemilik.setDate(FLD_OPEN_DATE, entPengurusAtauPemilik.getOpenDate());
            pstPengurusAtauPemilik.setInt(FLD_STATUS_DATA, entPengurusAtauPemilik.getStatusData());
            pstPengurusAtauPemilik.setLong(FLD_PERIODE_ID, entPengurusAtauPemilik.getPeriodeId());
            pstPengurusAtauPemilik.setInt(FLD_STATUS_PERUBAHAN_DATA, entPengurusAtauPemilik.getStatusPerubahanData());
            pstPengurusAtauPemilik.insert();
            
            pstPengurusAtauPemilik.setSqlQueryHistory("");
            pstPengurusAtauPemilik.setSqlQueryHistory(pstPengurusAtauPemilik.getInsertSQL());
            
            entPengurusAtauPemilik.setOID(pstPengurusAtauPemilik.getLong(FLD_PENGURUS_ATAU_PEMILIK_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPengurusAtauPemilik(0), DBException.UNKNOWN);
        }
        return entPengurusAtauPemilik.getOID();
    }
    
    public static synchronized PengurusAtauPemilik insertExcObj(PengurusAtauPemilik entPengurusAtauPemilik) throws DBException {
        try {
            PstPengurusAtauPemilik pstPengurusAtauPemilik = new PstPengurusAtauPemilik(0);
            pstPengurusAtauPemilik.setString(FLD_FLAG_DETAIL, entPengurusAtauPemilik.getFlagDetail());
            pstPengurusAtauPemilik.setString(FLD_NO_IDENTITAS, entPengurusAtauPemilik.getNoIdentitas());
            pstPengurusAtauPemilik.setString(FLD_CIF, entPengurusAtauPemilik.getCif());
            pstPengurusAtauPemilik.setString(FLD_JENIS_IDENTITAS, entPengurusAtauPemilik.getJenisIdentitas());
            pstPengurusAtauPemilik.setString(FLD_NAMA_PENGURUS, entPengurusAtauPemilik.getNamaPengurus());
            pstPengurusAtauPemilik.setString(FLD_JENIS_KELAMIN, entPengurusAtauPemilik.getJenisKelamin());
            pstPengurusAtauPemilik.setString(FLD_ALAMAT, entPengurusAtauPemilik.getAlamat());
            pstPengurusAtauPemilik.setString(FLD_KELURAHAN, entPengurusAtauPemilik.getKelurahan());
            pstPengurusAtauPemilik.setString(FLD_KECAMATAN, entPengurusAtauPemilik.getKecamatan());
            pstPengurusAtauPemilik.setString(FLD_KODE_KABUPATEN, entPengurusAtauPemilik.getKodeKabupaten());
            pstPengurusAtauPemilik.setString(FLD_KODE_JABATAN, entPengurusAtauPemilik.getKodeJabatan());
            pstPengurusAtauPemilik.setString(FLD_PANGSA_KEPEMILIKAN, entPengurusAtauPemilik.getPangsaKepemilikan());
            pstPengurusAtauPemilik.setString(FLD_STATUS_PENGURUS, entPengurusAtauPemilik.getStatusPengurus());
            pstPengurusAtauPemilik.setString(FLD_KODE_KANTOR_CABANG, entPengurusAtauPemilik.getKodeKantorCabang());
            pstPengurusAtauPemilik.setString(FLD_OPERASI_DATA, entPengurusAtauPemilik.getOperasiData());
            pstPengurusAtauPemilik.setDate(FLD_OPEN_DATE, entPengurusAtauPemilik.getOpenDate());
            pstPengurusAtauPemilik.setInt(FLD_STATUS_DATA, entPengurusAtauPemilik.getStatusData());
            pstPengurusAtauPemilik.setLong(FLD_PERIODE_ID, entPengurusAtauPemilik.getPeriodeId());
            pstPengurusAtauPemilik.setInt(FLD_STATUS_PERUBAHAN_DATA, entPengurusAtauPemilik.getStatusPerubahanData());
            pstPengurusAtauPemilik.insert();
            
            pstPengurusAtauPemilik.setSqlQueryHistory("");
            pstPengurusAtauPemilik.setSqlQueryHistory(pstPengurusAtauPemilik.getInsertSQL());
            entPengurusAtauPemilik.setSqlHistory(pstPengurusAtauPemilik.getInsertSQL());
            entPengurusAtauPemilik.setOID(pstPengurusAtauPemilik.getlong(FLD_PENGURUS_ATAU_PEMILIK_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPengurusAtauPemilik(0), DBException.UNKNOWN);
        }
        return entPengurusAtauPemilik;
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((PengurusAtauPemilik) entity);
    }

    public static void resultToObject(ResultSet rs, PengurusAtauPemilik entPengurusAtauPemilik) {
        try {
            entPengurusAtauPemilik.setOID(rs.getLong(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID]));
            entPengurusAtauPemilik.setFlagDetail(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_FLAG_DETAIL]));
            entPengurusAtauPemilik.setNoIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]));
            entPengurusAtauPemilik.setCif(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]));
            entPengurusAtauPemilik.setJenisIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_IDENTITAS]));
            entPengurusAtauPemilik.setNamaPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]));
            entPengurusAtauPemilik.setJenisKelamin(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_KELAMIN]));
            entPengurusAtauPemilik.setAlamat(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]));
            entPengurusAtauPemilik.setKelurahan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KELURAHAN]));
            entPengurusAtauPemilik.setKecamatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KECAMATAN]));
            entPengurusAtauPemilik.setKodeKabupaten(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KABUPATEN]));
            entPengurusAtauPemilik.setKodeJabatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_JABATAN]));
            entPengurusAtauPemilik.setPangsaKepemilikan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PANGSA_KEPEMILIKAN]));
            entPengurusAtauPemilik.setStatusPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PENGURUS]));
            entPengurusAtauPemilik.setKodeKantorCabang(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]));
            entPengurusAtauPemilik.setOperasiData(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPERASI_DATA]));
            entPengurusAtauPemilik.setOpenDate(rs.getDate(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPEN_DATE]));
            entPengurusAtauPemilik.setStatusData(rs.getInt(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_DATA]));
            entPengurusAtauPemilik.setPeriodeId(rs.getLong(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]));
            
            entPengurusAtauPemilik.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));


            
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectParentCabang(ResultSet rs, PengurusAtauPemilik entPengurusAtauPemilik) {
        try {
            entPengurusAtauPemilik.setOID(rs.getLong(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID]));
            entPengurusAtauPemilik.setFlagDetail(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_FLAG_DETAIL]));
            entPengurusAtauPemilik.setNoIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]));
            entPengurusAtauPemilik.setCif(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]));
            entPengurusAtauPemilik.setJenisIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_IDENTITAS]));
            entPengurusAtauPemilik.setNamaPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]));
            entPengurusAtauPemilik.setJenisKelamin(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_KELAMIN]));
            entPengurusAtauPemilik.setAlamat(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]));
            entPengurusAtauPemilik.setKelurahan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KELURAHAN]));
            entPengurusAtauPemilik.setKecamatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KECAMATAN]));
            entPengurusAtauPemilik.setKodeKabupaten(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KABUPATEN]));
            entPengurusAtauPemilik.setKodeJabatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_JABATAN]));
            entPengurusAtauPemilik.setPangsaKepemilikan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PANGSA_KEPEMILIKAN]));
            entPengurusAtauPemilik.setStatusPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PENGURUS]));
            entPengurusAtauPemilik.setKodeKantorCabang(rs.getString("PARENT_CODE"));
            entPengurusAtauPemilik.setOperasiData(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPERASI_DATA]));
            entPengurusAtauPemilik.setOpenDate(rs.getDate(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPEN_DATE]));
            entPengurusAtauPemilik.setStatusData(rs.getInt(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_DATA]));
            entPengurusAtauPemilik.setPeriodeId(rs.getLong(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]));
            
            entPengurusAtauPemilik.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));


            
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoin(ResultSet rs, PengurusAtauPemilik entPengurusAtauPemilik) {
        try {
            entPengurusAtauPemilik.setOID(rs.getLong(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID]));
            entPengurusAtauPemilik.setFlagDetail(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_FLAG_DETAIL]));
            entPengurusAtauPemilik.setNoIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]));
            entPengurusAtauPemilik.setCif(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]));
            entPengurusAtauPemilik.setJenisIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_IDENTITAS]));
            entPengurusAtauPemilik.setNamaPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]));
            entPengurusAtauPemilik.setJenisKelamin(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_KELAMIN]));
            entPengurusAtauPemilik.setAlamat(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]));
            entPengurusAtauPemilik.setKelurahan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KELURAHAN]));
            entPengurusAtauPemilik.setKecamatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KECAMATAN]));
            entPengurusAtauPemilik.setKodeKabupaten(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KABUPATEN]));
            entPengurusAtauPemilik.setKodeJabatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_JABATAN]));
            entPengurusAtauPemilik.setPangsaKepemilikan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PANGSA_KEPEMILIKAN]));
            entPengurusAtauPemilik.setStatusPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PENGURUS]));
            entPengurusAtauPemilik.setKodeKantorCabang(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]));
            entPengurusAtauPemilik.setOperasiData(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPERASI_DATA]));
            entPengurusAtauPemilik.setOpenDate(rs.getDate(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPEN_DATE]));
            entPengurusAtauPemilik.setStatusData(rs.getInt(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_DATA]));
            entPengurusAtauPemilik.setPeriodeId(rs.getLong(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]));
            entPengurusAtauPemilik.setDebiturOid(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]));
            entPengurusAtauPemilik.setDebiturType(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
            entPengurusAtauPemilik.setNamaJenisIdentitas(rs.getString(PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_NAMA_IDENTITAS]));
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
            String sql = "SELECT DISTINCT "
                    + "TRIM(PENGURUS_OID) AS PENGURUS_OID," +
                        "TRIM( FLAG_DETAIL) AS  FLAG_DETAIL," +
                        "TRIM( NO_IDENTITAS) AS  NO_IDENTITAS," +
                        "TRIM( CIF) AS  CIF," +
                        "TRIM( JENIS_IDENTITAS) AS  JENIS_IDENTITAS," +
                        "TRIM( NAMA_PENGURUS) AS  NAMA_PENGURUS," +
                        "TRIM( JENIS_KELAMIN) AS  JENIS_KELAMIN," +
                        "TRIM( ALAMAT) AS  ALAMAT," +
                        "TRIM( KELURAHAN) AS  KELURAHAN," +
                        "TRIM( KECAMATAN) AS  KECAMATAN," +
                        "TRIM( KODE_KABUPATEN) AS  KODE_KABUPATEN," +
                        "TRIM( KODE_JABATAN) AS  KODE_JABATAN," +
                        "TRIM( PANGSA_KEPEMILIKAN) AS  PANGSA_KEPEMILIKAN," +
                        "TRIM( STATUS_PENGURUS) AS  STATUS_PENGURUS," +
                        "TRIM( KODE_KANTOR_CABANG) AS  KODE_KANTOR_CABANG," +
                        "TRIM( OPERASI_DATA) AS  OPERASI_DATA," +
                        "TRIM( OPEN_DATE) AS  OPEN_DATE," +
                        "TRIM( STATUS_DATA) AS  STATUS_DATA," +
                        "TRIM( PERIODE_ID) AS  PERIODE_ID, STATUS_OPERASI_DATA"
                    + " FROM " + TBL_PENGURUS_ATAU_PEMILIK;
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
                PengurusAtauPemilik entPengurusAtauPemilik = new PengurusAtauPemilik();
                resultToObject(rs, entPengurusAtauPemilik);
                lists.add(entPengurusAtauPemilik);
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
    
    
    public static Vector listJoinParentCabang(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "
                    + "TRIM(PENGURUS_OID) AS PENGURUS_OID," +
                        "TRIM( FLAG_DETAIL) AS  FLAG_DETAIL," +
                        "TRIM( NO_IDENTITAS) AS  NO_IDENTITAS," +
                        "TRIM( CIF) AS  CIF," +
                        "TRIM( JENIS_IDENTITAS) AS  JENIS_IDENTITAS," +
                        "TRIM( NAMA_PENGURUS) AS  NAMA_PENGURUS," +
                        "TRIM( JENIS_KELAMIN) AS  JENIS_KELAMIN," +
                        "TRIM( ALAMAT) AS  ALAMAT," +
                        "TRIM( KELURAHAN) AS  KELURAHAN," +
                        "TRIM( KECAMATAN) AS  KECAMATAN," +
                        "TRIM( KODE_KABUPATEN) AS  KODE_KABUPATEN," +
                        "TRIM( KODE_JABATAN) AS  KODE_JABATAN," +
                        "TRIM( PANGSA_KEPEMILIKAN) AS  PANGSA_KEPEMILIKAN," +
                        "TRIM( STATUS_PENGURUS) AS  STATUS_PENGURUS," +
                        "TRIM( KODE_KANTOR_CABANG) AS  KODE_KANTOR_CABANG," +
                        "TRIM( OPERASI_DATA) AS  OPERASI_DATA," +
                        "TRIM( OPEN_DATE) AS  OPEN_DATE," +
                        "TRIM( STATUS_DATA) AS  STATUS_DATA," +
                        "TRIM( PERIODE_ID) AS  PERIODE_ID, STATUS_OPERASI_DATA,"
                      + "TRIM(PARENT_CODE) AS PARENT_CODE "  
                      + "FROM " + TBL_PENGURUS_ATAU_PEMILIK+" "
                      + "INNER JOIN dslik_cabang_bank ON "+TBL_PENGURUS_ATAU_PEMILIK+"."+fieldNames[FLD_KODE_KANTOR_CABANG]+"=dslik_cabang_bank.KODE_CABANG";
                    //+ " FROM " + TBL_PENGURUS_ATAU_PEMILIK;
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
                PengurusAtauPemilik entPengurusAtauPemilik = new PengurusAtauPemilik();
                resultToObjectParentCabang(rs, entPengurusAtauPemilik);
                lists.add(entPengurusAtauPemilik);
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
            String sql = "SELECT DISTINCT debitur."+PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]+","
                    + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+","
                    + " pengurus.*,"
                    + " jenisidentitas."+PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_NAMA_IDENTITAS]+""
                    + " FROM "+TBL_PENGURUS_ATAU_PEMILIK+" AS pengurus "
                    + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                    + " ON pengurus."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                    + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                    + " INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + " ON pengurus."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                    + " AND pengurus."+fieldNames[FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+""
                    + " LEFT JOIN "+PstContentDataJenisIdentitas.TBL_CONTENT_DATA_JENIS_IDENTITAS+" as jenisidentitas"
                    + " ON pengurus."+fieldNames[FLD_JENIS_IDENTITAS]+"="+PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_KODE_OJK]+"";
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
                PengurusAtauPemilik entPengurusAtauPemilik = new PengurusAtauPemilik();
                resultToObjectJoin(rs, entPengurusAtauPemilik);
                lists.add(entPengurusAtauPemilik);
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
    
    public static boolean checkOID(long entPengurusAtauPemilikId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_PENGURUS_ATAU_PEMILIK + " WHERE "
                    + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID] + " = " + entPengurusAtauPemilikId;
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
            String sql = "SELECT DISTINCT COUNT(" + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID] + ") FROM " + TBL_PENGURUS_ATAU_PEMILIK;
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
            String sql = "SELECT DISTINCT COUNT(DISTINCT pengurus." + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID] + ") "
                    + "FROM " + TBL_PENGURUS_ATAU_PEMILIK+" AS pengurus "
                    + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                    + "ON pengurus."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                    + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "ON pengurus."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                    + "AND pengurus."+fieldNames[FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]
                    + " LEFT JOIN "+PstContentDataJenisIdentitas.TBL_CONTENT_DATA_JENIS_IDENTITAS+" as jenisidentitas"
                    + " ON pengurus."+fieldNames[FLD_JENIS_IDENTITAS]+"="+PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_KODE_OJK]+"";
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
                    PengurusAtauPemilik entPengurusAtauPemilik = (PengurusAtauPemilik) list.get(ls);
                    if (oid == entPengurusAtauPemilik.getOID()) {
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

    
    public static boolean checkCIF(String cif) {
        DBResultSet dbrs = null;
        boolean result = true;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_PENGURUS_ATAU_PEMILIK + " WHERE "
                    + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF] + " = '" + cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = false;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }

}
