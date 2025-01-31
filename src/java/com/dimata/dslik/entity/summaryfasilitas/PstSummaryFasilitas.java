/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.summaryfasilitas;

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
public class PstSummaryFasilitas extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_SUMMARY_FASILITAS = "dslik_summary_fasilitas";
    public static final int FLD_SUMMARY_FASILITAS_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_REKENING = 2;
    public static final int FLD_CIF = 3;
    public static final int FLD_KODE_JENIS_FASILITAS = 4;
    public static final int FLD_KODE_KOLEKTIBILITAS_1 = 5;
    public static final int FLD_JML_HARI_TUNGGAKAN_1 = 6;
    public static final int FLD_KODE_KOLEKTIBILITAS_2 = 7;
    public static final int FLD_JML_HARI_TUNGGAKAN_2 = 8;
    public static final int FLD_KODE_KOLEKTIBILITAS_3 = 9;
    public static final int FLD_JML_HARI_TUNGGAKAN_3 = 10;
    public static final int FLD_KODE_KOLEKTIBILITAS_4 = 11;
    public static final int FLD_JML_HARI_TUNGGAKAN_4 = 12;
    public static final int FLD_KODE_KOLEKTIBILITAS_5 = 13;
    public static final int FLD_JML_HARI_TUNGGAKAN_5 = 14;
    public static final int FLD_KODE_KOLEKTIBILITAS_6 = 15;
    public static final int FLD_JML_HARI_TUNGGAKAN_6 = 16;
    public static final int FLD_KODE_KOLEKTIBILITAS_7 = 17;
    public static final int FLD_JML_HARI_TUNGGAKAN_7 = 18;
    public static final int FLD_KODE_KOLEKTIBILITAS_8 = 19;
    public static final int FLD_JML_HARI_TUNGGAKAN_8 = 20;
    public static final int FLD_KODE_KOLEKTIBILITAS_9 = 21;
    public static final int FLD_JML_HARI_TUNGGAKAN_9 = 22;
    public static final int FLD_KODE_KOLEKTIBILITAS_10 = 23;
    public static final int FLD_JML_HARI_TUNGGAKAN_10 = 24;
    public static final int FLD_KODE_KOLEKTIBILITAS_11 = 25;
    public static final int FLD_JML_HARI_TUNGGAKAN_11 = 26;
    public static final int FLD_KODE_KOLEKTIBILITAS_12 = 27;
    public static final int FLD_JML_HARI_TUNGGAKAN_12 = 28;
    public static final int FLD_OPEN_DATE = 29;
    public static final int FLD_KODE_KANTOR_CABANG = 30;

    public static String[] fieldNames = {
        "SUMMARY_FASILITAS_OID",
        "FLAG_DETAIL",
        "NO_REKENING",
        "CIF",
        "KODE_JENIS_FASILITAS",
        "KODE_KOLEKTIBILITAS_1",
        "JML_HARI_TUNGGAKAN_1",
        "KODE_KOLEKTIBLITAS_2",
        "JML_HARI_TUNGGAKAN_2",
        "KODE_KOLEKTIBILITAS_3",
        "JML_HARI_TUNGGAKAN_3",
        "KODE_KOLEKTIBILITAS_4",
        "JML_HARI_TUNGGAKAN_4",
        "KODE_KOLEKTIBILITAS_5",
        "JML_HARI_TUNGGAKAN_5",
        "KODE_KOLEKTIBILITAS_6",
        "JML_HARI_TUNGGAKAN_6",
        "KODE_KOLEKTIBILITAS_7",
        "JML_HARI_TUNGGAKAN_7",
        "KODE_KOLEKTIBILITAS_8",
        "JML_HARI_TUNGGAKAN_8",
        "KODE_KOLEKTIBILITAS_9",
        "JML_HARI_TUNGGAKAN_9",
        "KODE_KOLEKTIBILITAS_10",
        "JML_HARI_TUNGGAKAN_10",
        "KODE_KOLEKTIBILITAS_11",
        "JML_HARI_TUNGGAKAN_11",
        "KODE_KOLEKTIBILITAS_12",
        "JML_HARI_TUNGGAKAN_12",
        "OPEN_DATE",
        "KODE_KANTOR_CABANG"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_DATE,
        TYPE_STRING
    };

    public PstSummaryFasilitas() {
    }

    public PstSummaryFasilitas(int i) throws DBException {
        super(new PstSummaryFasilitas());
    }

    public PstSummaryFasilitas(String sOid) throws DBException {
        super(new PstSummaryFasilitas(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstSummaryFasilitas(long lOid) throws DBException {
        super(new PstSummaryFasilitas(0));
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
        return TBL_SUMMARY_FASILITAS;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstSummaryFasilitas().getClass().getName();
    }

    public static SummaryFasilitas fetchExc(long oid) throws DBException {
        try {
            SummaryFasilitas entSummaryFasilitas = new SummaryFasilitas();
            PstSummaryFasilitas pstSummaryFasilitas = new PstSummaryFasilitas(oid);
            entSummaryFasilitas.setOID(oid);
            entSummaryFasilitas.setFlagDetail(pstSummaryFasilitas.getString(FLD_FLAG_DETAIL));
            entSummaryFasilitas.setNoRekening(pstSummaryFasilitas.getString(FLD_NO_REKENING));
            entSummaryFasilitas.setCif(pstSummaryFasilitas.getString(FLD_CIF));
            entSummaryFasilitas.setKodeJenisFasilitas(pstSummaryFasilitas.getString(FLD_KODE_JENIS_FASILITAS));
            entSummaryFasilitas.setKodeKolektibilitas1(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_1));
            entSummaryFasilitas.setJmlHariTunggakan1(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_1));
            entSummaryFasilitas.setKodeKolektibilitas2(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_2));
            entSummaryFasilitas.setJmlHariTunggakan2(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_2));
            entSummaryFasilitas.setKodeKolektibilitas3(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_3));
            entSummaryFasilitas.setJmlHariTunggakan3(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_3));
            entSummaryFasilitas.setKodeKolektibilitas4(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_4));
            entSummaryFasilitas.setJmlHariTunggakan4(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_4));
            entSummaryFasilitas.setKodeKolektibilitas5(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_5));
            entSummaryFasilitas.setJmlHariTunggakan5(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_5));
            entSummaryFasilitas.setKodeKolektibilitas6(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_6));
            entSummaryFasilitas.setJmlHariTunggakan6(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_6));
            entSummaryFasilitas.setKodeKolektibilitas7(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_7));
            entSummaryFasilitas.setJmlHariTunggakan7(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_7));
            entSummaryFasilitas.setKodeKolektibilitas8(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_8));
            entSummaryFasilitas.setJmlHariTunggakan8(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_8));
            entSummaryFasilitas.setKodeKolektibilitas9(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_9));
            entSummaryFasilitas.setJmlHariTunggakan9(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_9));
            entSummaryFasilitas.setKodeKolektibilitas10(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_10));
            entSummaryFasilitas.setJmlHariTunggakan10(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_10));
            entSummaryFasilitas.setKodeKolektibilitas11(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_11));
            entSummaryFasilitas.setJmlHariTunggakan11(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_11));
            entSummaryFasilitas.setKodeKolektibilitas12(pstSummaryFasilitas.getString(FLD_KODE_KOLEKTIBILITAS_12));
            entSummaryFasilitas.setJmlHariTunggakan12(pstSummaryFasilitas.getInt(FLD_JML_HARI_TUNGGAKAN_12));
            entSummaryFasilitas.setOpenDate(pstSummaryFasilitas.getDate(FLD_OPEN_DATE));
            entSummaryFasilitas.setKodeKantorCabang(pstSummaryFasilitas.getString(FLD_KODE_KANTOR_CABANG));
            
            return entSummaryFasilitas;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSummaryFasilitas(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        SummaryFasilitas entSummaryFasilitas = fetchExc(entity.getOID());
        entity = (Entity) entSummaryFasilitas;
        return entSummaryFasilitas.getOID();
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((SummaryFasilitas) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstSummaryFasilitas pstSummaryFasilitas = new PstSummaryFasilitas(oid);
            pstSummaryFasilitas.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSummaryFasilitas(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(SummaryFasilitas entSummaryFasilitas) throws DBException {
        try {
            PstSummaryFasilitas pstSummaryFasilitas = new PstSummaryFasilitas(0);
            pstSummaryFasilitas.setString(FLD_FLAG_DETAIL, entSummaryFasilitas.getFlagDetail());
            pstSummaryFasilitas.setString(FLD_NO_REKENING, entSummaryFasilitas.getNoRekening());
            pstSummaryFasilitas.setString(FLD_CIF, entSummaryFasilitas.getCif());
            pstSummaryFasilitas.setString(FLD_KODE_JENIS_FASILITAS, entSummaryFasilitas.getKodeJenisFasilitas());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_1, entSummaryFasilitas.getKodeKolektibilitas1());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_1, entSummaryFasilitas.getJmlHariTunggakan1());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_2, entSummaryFasilitas.getKodeKolektibilitas2());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_2, entSummaryFasilitas.getJmlHariTunggakan2());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_3, entSummaryFasilitas.getKodeKolektibilitas3());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_3, entSummaryFasilitas.getJmlHariTunggakan3());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_4, entSummaryFasilitas.getKodeKolektibilitas4());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_4, entSummaryFasilitas.getJmlHariTunggakan4());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_5, entSummaryFasilitas.getKodeKolektibilitas5());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_5, entSummaryFasilitas.getJmlHariTunggakan5());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_6, entSummaryFasilitas.getKodeKolektibilitas6());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_6, entSummaryFasilitas.getJmlHariTunggakan6());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_7, entSummaryFasilitas.getKodeKolektibilitas7());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_7, entSummaryFasilitas.getJmlHariTunggakan7());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_8, entSummaryFasilitas.getKodeKolektibilitas8());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_8, entSummaryFasilitas.getJmlHariTunggakan8());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_9, entSummaryFasilitas.getKodeKolektibilitas9());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_9, entSummaryFasilitas.getJmlHariTunggakan9());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_10, entSummaryFasilitas.getKodeKolektibilitas10());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_10, entSummaryFasilitas.getJmlHariTunggakan10());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_11, entSummaryFasilitas.getKodeKolektibilitas11());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_11, entSummaryFasilitas.getJmlHariTunggakan11());
            pstSummaryFasilitas.setString(FLD_KODE_KOLEKTIBILITAS_12, entSummaryFasilitas.getKodeKolektibilitas12());
            pstSummaryFasilitas.setInt(FLD_JML_HARI_TUNGGAKAN_12, entSummaryFasilitas.getJmlHariTunggakan12());
            pstSummaryFasilitas.setDate(FLD_OPEN_DATE, entSummaryFasilitas.getOpenDate());
            pstSummaryFasilitas.setString(FLD_KODE_KANTOR_CABANG,  entSummaryFasilitas.getKodeKantorCabang());
            
            pstSummaryFasilitas.insert();
            entSummaryFasilitas.setOID(pstSummaryFasilitas.getLong(FLD_SUMMARY_FASILITAS_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSummaryFasilitas(0), DBException.UNKNOWN);
        }
        return entSummaryFasilitas.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((SummaryFasilitas) entity);
    }

    public static void resultToObject(ResultSet rs, SummaryFasilitas entSummaryFasilitas) {
        try {
            entSummaryFasilitas.setOID(rs.getLong(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_SUMMARY_FASILITAS_OID]));
            entSummaryFasilitas.setFlagDetail(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_FLAG_DETAIL]));
            entSummaryFasilitas.setNoRekening(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_NO_REKENING]));
            entSummaryFasilitas.setCif(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_CIF]));
            entSummaryFasilitas.setKodeJenisFasilitas(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_JENIS_FASILITAS]));
            entSummaryFasilitas.setKodeKolektibilitas1(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_1]));
            entSummaryFasilitas.setJmlHariTunggakan1(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_1]));
            entSummaryFasilitas.setKodeKolektibilitas2(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_2]));
            entSummaryFasilitas.setJmlHariTunggakan2(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_2]));
            entSummaryFasilitas.setKodeKolektibilitas3(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_3]));
            entSummaryFasilitas.setJmlHariTunggakan3(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_3]));
            entSummaryFasilitas.setKodeKolektibilitas4(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_4]));
            entSummaryFasilitas.setJmlHariTunggakan4(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_4]));
            entSummaryFasilitas.setKodeKolektibilitas5(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_5]));
            entSummaryFasilitas.setJmlHariTunggakan5(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_5]));
            entSummaryFasilitas.setKodeKolektibilitas6(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_6]));
            entSummaryFasilitas.setJmlHariTunggakan6(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_6]));
            entSummaryFasilitas.setKodeKolektibilitas7(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_7]));
            entSummaryFasilitas.setJmlHariTunggakan7(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_7]));
            entSummaryFasilitas.setKodeKolektibilitas8(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_8]));
            entSummaryFasilitas.setJmlHariTunggakan8(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_8]));
            entSummaryFasilitas.setKodeKolektibilitas9(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_9]));
            entSummaryFasilitas.setJmlHariTunggakan9(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_9]));
            entSummaryFasilitas.setKodeKolektibilitas10(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_10]));
            entSummaryFasilitas.setJmlHariTunggakan10(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_10]));
            entSummaryFasilitas.setKodeKolektibilitas11(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_11]));
            entSummaryFasilitas.setJmlHariTunggakan11(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_11]));
            entSummaryFasilitas.setKodeKolektibilitas12(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KOLEKTIBILITAS_12]));
            entSummaryFasilitas.setJmlHariTunggakan12(rs.getInt(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_JML_HARI_TUNGGAKAN_12]));
            entSummaryFasilitas.setOpenDate(rs.getDate(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_OPEN_DATE]));
            entSummaryFasilitas.setKodeKantorCabang(rs.getString(PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_KANTOR_CABANG]));
            
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
            String sql = "SELECT * FROM " + TBL_SUMMARY_FASILITAS;
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
                SummaryFasilitas entSummaryFasilitas = new SummaryFasilitas();
                resultToObject(rs, entSummaryFasilitas);
                lists.add(entSummaryFasilitas);
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

    public static boolean checkOID(long entSummaryFasilitasId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_SUMMARY_FASILITAS + " WHERE "
                    + PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_SUMMARY_FASILITAS_OID] + " = " + entSummaryFasilitasId;
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
    
    public static boolean checkOID(String cif, String noRekening, String kodeJenisFasilitas) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_SUMMARY_FASILITAS + " WHERE "
                    + PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_CIF] + " = '" + cif+"' AND "
                    + PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_NO_REKENING]+"= '"+ noRekening+"' AND "
                    + PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_KODE_JENIS_FASILITAS]+"= '"+ kodeJenisFasilitas+"'";
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
            String sql = "SELECT COUNT(" + PstSummaryFasilitas.fieldNames[PstSummaryFasilitas.FLD_SUMMARY_FASILITAS_OID] + ") FROM " + TBL_SUMMARY_FASILITAS;
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
                    SummaryFasilitas entSummaryFasilitas = (SummaryFasilitas) list.get(ls);
                    if (oid == entSummaryFasilitas.getOID()) {
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
}
