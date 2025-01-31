/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.bpdmapping;

/**
 *
 * @author dimata005
 */
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstMappingPengurusAtauPemilikBpd extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_MAPPING_PENGURUS_ATAU_PEMILIK_BPD = "dslik_pengurus_atau_pemilik";
    public static final int FLD_MAPPING_PENGURUS_ATAU_PEMILIK_BPD_ID = 0;
    public static final int FLD_FLAGDETAIL = 1;
    public static final int FLD_NOMOR_IDENTITAS = 2;
    public static final int FLD_CIF = 3;
    public static final int FLD_JENIS_IDENTITAS = 4;
    public static final int FLD_NAMA_PENGURUS = 5;
    public static final int FLD_JENIS_KELAMIN = 6;
    public static final int FLD_ALAMAT = 7;
    public static final int FLD_KELURAHAN = 8;
    public static final int FLD_KECAMATAN = 9;
    public static final int FLD_KD_KABUPATEN = 10;
    public static final int FLD_PANGSA = 11;
    public static final int FLD_KD_CABANG = 12;
    public static final int FLD_PERIODE_ID=13;
    
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
        "PANGSA_KEPEMILIKAN",
        "KODE_KANTOR_CABANG",
        "PERIODE_ID"
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
        TYPE_LONG
    };

    public PstMappingPengurusAtauPemilikBpd() {
    }

    public PstMappingPengurusAtauPemilikBpd(int i) throws DBException {
        super(new PstMappingPengurusAtauPemilikBpd());
    }

    public PstMappingPengurusAtauPemilikBpd(String sOid) throws DBException {
        super(new PstMappingPengurusAtauPemilikBpd(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstMappingPengurusAtauPemilikBpd(long lOid) throws DBException {
        super(new PstMappingPengurusAtauPemilikBpd(0));
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
        return TBL_MAPPING_PENGURUS_ATAU_PEMILIK_BPD;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstMappingPengurusAtauPemilikBpd().getClass().getName();
    }

    public static MappingPengurusAtauPemilikBpd fetchExc(long oid) throws DBException {
        try {
            MappingPengurusAtauPemilikBpd entMappingPengurusAtauPemilikBpd = new MappingPengurusAtauPemilikBpd();
            PstMappingPengurusAtauPemilikBpd pstMappingPengurusAtauPemilikBpd = new PstMappingPengurusAtauPemilikBpd(oid);
            entMappingPengurusAtauPemilikBpd.setOID(oid);
            entMappingPengurusAtauPemilikBpd.setFlagDetail(pstMappingPengurusAtauPemilikBpd.getString(FLD_FLAGDETAIL));
            entMappingPengurusAtauPemilikBpd.setNomorIdentitas(pstMappingPengurusAtauPemilikBpd.getString(FLD_NOMOR_IDENTITAS));
            entMappingPengurusAtauPemilikBpd.setCif(pstMappingPengurusAtauPemilikBpd.getString(FLD_CIF));
            entMappingPengurusAtauPemilikBpd.setJenisIdentitas(pstMappingPengurusAtauPemilikBpd.getString(FLD_JENIS_IDENTITAS));
            entMappingPengurusAtauPemilikBpd.setNamaPengurus(pstMappingPengurusAtauPemilikBpd.getString(FLD_NAMA_PENGURUS));
            entMappingPengurusAtauPemilikBpd.setJenisKelamin(pstMappingPengurusAtauPemilikBpd.getString(FLD_JENIS_KELAMIN));
            entMappingPengurusAtauPemilikBpd.setAlamat(pstMappingPengurusAtauPemilikBpd.getString(FLD_ALAMAT));
            entMappingPengurusAtauPemilikBpd.setKelurahan(pstMappingPengurusAtauPemilikBpd.getString(FLD_KELURAHAN));
            entMappingPengurusAtauPemilikBpd.setKecamatan(pstMappingPengurusAtauPemilikBpd.getString(FLD_KECAMATAN));
            entMappingPengurusAtauPemilikBpd.setKdKabupaten(pstMappingPengurusAtauPemilikBpd.getString(FLD_KD_KABUPATEN));
            entMappingPengurusAtauPemilikBpd.setPangsa(pstMappingPengurusAtauPemilikBpd.getString(FLD_PANGSA));
            entMappingPengurusAtauPemilikBpd.setKdCabang(pstMappingPengurusAtauPemilikBpd.getString(FLD_KD_CABANG));
            entMappingPengurusAtauPemilikBpd.setPeriodeId(pstMappingPengurusAtauPemilikBpd.getlong(FLD_PERIODE_ID));
            
            return entMappingPengurusAtauPemilikBpd;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingPengurusAtauPemilikBpd(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        MappingPengurusAtauPemilikBpd entMappingPengurusAtauPemilikBpd = fetchExc(entity.getOID());
        entity = (Entity) entMappingPengurusAtauPemilikBpd;
        return entMappingPengurusAtauPemilikBpd.getOID();
    }

    public static synchronized long updateExc(MappingPengurusAtauPemilikBpd entMappingPengurusAtauPemilikBpd) throws DBException {
        try {
            if (entMappingPengurusAtauPemilikBpd.getOID() != 0) {
                PstMappingPengurusAtauPemilikBpd pstMappingPengurusAtauPemilikBpd = new PstMappingPengurusAtauPemilikBpd(entMappingPengurusAtauPemilikBpd.getOID());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_FLAGDETAIL, entMappingPengurusAtauPemilikBpd.getFlagDetail());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_NOMOR_IDENTITAS, entMappingPengurusAtauPemilikBpd.getNomorIdentitas());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_CIF, entMappingPengurusAtauPemilikBpd.getCif());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_JENIS_IDENTITAS, entMappingPengurusAtauPemilikBpd.getJenisIdentitas());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_NAMA_PENGURUS, entMappingPengurusAtauPemilikBpd.getNamaPengurus());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_JENIS_KELAMIN, entMappingPengurusAtauPemilikBpd.getJenisKelamin());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_ALAMAT, entMappingPengurusAtauPemilikBpd.getAlamat());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_KELURAHAN, entMappingPengurusAtauPemilikBpd.getKelurahan());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_KECAMATAN, entMappingPengurusAtauPemilikBpd.getKecamatan());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_KD_KABUPATEN, entMappingPengurusAtauPemilikBpd.getKdKabupaten());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_PANGSA, entMappingPengurusAtauPemilikBpd.getPangsa());
                pstMappingPengurusAtauPemilikBpd.setString(FLD_KD_CABANG, entMappingPengurusAtauPemilikBpd.getKdCabang());
                pstMappingPengurusAtauPemilikBpd.setLong(FLD_PERIODE_ID,entMappingPengurusAtauPemilikBpd.getPeriodeId());
                
                pstMappingPengurusAtauPemilikBpd.update();
                return entMappingPengurusAtauPemilikBpd.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingPengurusAtauPemilikBpd(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((MappingPengurusAtauPemilikBpd) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMappingPengurusAtauPemilikBpd pstMappingPengurusAtauPemilikBpd = new PstMappingPengurusAtauPemilikBpd(oid);
            pstMappingPengurusAtauPemilikBpd.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingPengurusAtauPemilikBpd(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MappingPengurusAtauPemilikBpd entMappingPengurusAtauPemilikBpd) throws DBException {
        try {
            PstMappingPengurusAtauPemilikBpd pstMappingPengurusAtauPemilikBpd = new PstMappingPengurusAtauPemilikBpd(0);
            pstMappingPengurusAtauPemilikBpd.setString(FLD_FLAGDETAIL, entMappingPengurusAtauPemilikBpd.getFlagDetail());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_NOMOR_IDENTITAS, entMappingPengurusAtauPemilikBpd.getNomorIdentitas());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_CIF, entMappingPengurusAtauPemilikBpd.getCif());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_JENIS_IDENTITAS, entMappingPengurusAtauPemilikBpd.getJenisIdentitas());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_NAMA_PENGURUS, entMappingPengurusAtauPemilikBpd.getNamaPengurus());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_JENIS_KELAMIN, entMappingPengurusAtauPemilikBpd.getJenisKelamin());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_ALAMAT, entMappingPengurusAtauPemilikBpd.getAlamat());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_KELURAHAN, entMappingPengurusAtauPemilikBpd.getKelurahan());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_KECAMATAN, entMappingPengurusAtauPemilikBpd.getKecamatan());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_KD_KABUPATEN, entMappingPengurusAtauPemilikBpd.getKdKabupaten());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_PANGSA, entMappingPengurusAtauPemilikBpd.getPangsa());
            pstMappingPengurusAtauPemilikBpd.setString(FLD_KD_CABANG, entMappingPengurusAtauPemilikBpd.getKdCabang());
            pstMappingPengurusAtauPemilikBpd.setLong(FLD_PERIODE_ID,entMappingPengurusAtauPemilikBpd.getPeriodeId());
            
            pstMappingPengurusAtauPemilikBpd.insert();
            entMappingPengurusAtauPemilikBpd.setOID(pstMappingPengurusAtauPemilikBpd.getlong(FLD_MAPPING_PENGURUS_ATAU_PEMILIK_BPD_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingPengurusAtauPemilikBpd(0), DBException.UNKNOWN);
        }
        return entMappingPengurusAtauPemilikBpd.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((MappingPengurusAtauPemilikBpd) entity);
    }

    public static void resultToObject(ResultSet rs, MappingPengurusAtauPemilikBpd entMappingPengurusAtauPemilikBpd) {
        try {
            entMappingPengurusAtauPemilikBpd.setOID(rs.getLong(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_MAPPING_PENGURUS_ATAU_PEMILIK_BPD_ID]));
            entMappingPengurusAtauPemilikBpd.setFlagDetail(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_FLAGDETAIL]));
            entMappingPengurusAtauPemilikBpd.setNomorIdentitas(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_NOMOR_IDENTITAS]));
            entMappingPengurusAtauPemilikBpd.setCif(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_CIF]));
            entMappingPengurusAtauPemilikBpd.setJenisIdentitas(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_JENIS_IDENTITAS]));
            entMappingPengurusAtauPemilikBpd.setNamaPengurus(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_NAMA_PENGURUS]));
            entMappingPengurusAtauPemilikBpd.setJenisKelamin(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_JENIS_KELAMIN]));
            entMappingPengurusAtauPemilikBpd.setAlamat(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_ALAMAT]));
            entMappingPengurusAtauPemilikBpd.setKelurahan(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_KELURAHAN]));
            entMappingPengurusAtauPemilikBpd.setKecamatan(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_KECAMATAN]));
            entMappingPengurusAtauPemilikBpd.setKdKabupaten(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_KD_KABUPATEN]));
            entMappingPengurusAtauPemilikBpd.setPangsa(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_PANGSA]));
            entMappingPengurusAtauPemilikBpd.setKdCabang(rs.getString(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_KD_CABANG]));
            entMappingPengurusAtauPemilikBpd.setPeriodeId(rs.getLong(PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_PERIODE_ID]));
            
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
            String sql = "SELECT * FROM " + TBL_MAPPING_PENGURUS_ATAU_PEMILIK_BPD;
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
                MappingPengurusAtauPemilikBpd entMappingPengurusAtauPemilikBpd = new MappingPengurusAtauPemilikBpd();
                resultToObject(rs, entMappingPengurusAtauPemilikBpd);
                lists.add(entMappingPengurusAtauPemilikBpd);
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

    public static boolean checkOID(long entMappingPengurusAtauPemilikBpdId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_PENGURUS_ATAU_PEMILIK_BPD + " WHERE "
                    + PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_MAPPING_PENGURUS_ATAU_PEMILIK_BPD_ID] + " = " + entMappingPengurusAtauPemilikBpdId;
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
            String sql = "SELECT COUNT(" + PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_MAPPING_PENGURUS_ATAU_PEMILIK_BPD_ID] + ") FROM " + TBL_MAPPING_PENGURUS_ATAU_PEMILIK_BPD;
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
                    MappingPengurusAtauPemilikBpd entMappingPengurusAtauPemilikBpd = (MappingPengurusAtauPemilikBpd) list.get(ls);
                    if (oid == entMappingPengurusAtauPemilikBpd.getOID()) {
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
        } else if (start == (vectSize - recordToGet)) {
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
        return cmd;
    }
    
     public static int deleteSegmentPengurusPemilikPerPeriode(long periodeId, String cif) {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "DELETE FROM " + TBL_MAPPING_PENGURUS_ATAU_PEMILIK_BPD
                + " WHERE " + fieldNames[FLD_PERIODE_ID] + " = " + periodeId;
                 if(!cif.equals("")){
               stSql=stSql+" AND " + fieldNames[FLD_CIF]+"='"+cif+"'";
        }
        try {
            iResult = DBHandler.execUpdate(stSql);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            DBResultSet.close(dbrs);
        }
        return iResult;
    }
     
     
     public static long checkDataPengurus(long periodeId, String cif) {
        DBResultSet dbrs = null;
        long result = 0;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_PENGURUS_ATAU_PEMILIK_BPD+ " WHERE "
                    + PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_PERIODE_ID] + " = '" + periodeId+"'"
                    + " AND "+PstMappingPengurusAtauPemilikBpd.fieldNames[PstMappingPengurusAtauPemilikBpd.FLD_CIF]+"='"+cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getLong(fieldNames[FLD_MAPPING_PENGURUS_ATAU_PEMILIK_BPD_ID]);
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
