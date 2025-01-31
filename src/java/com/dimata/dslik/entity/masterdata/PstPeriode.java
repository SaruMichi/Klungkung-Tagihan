/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.masterdata;

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
public class PstPeriode extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_PERIODE = "dslik_periode";
    public static final int FLD_PERIODE_ID = 0;
    public static final int FLD_TGL_AWAL = 1;
    public static final int FLD_TGL_AKHIR = 2;
    public static final int FLD_NAMA = 3;
    public static final int FLD_KETERANGAN = 4;
    public static final int FLD_POSTED = 5;
    public static final int FLD_TGL_TERAKHIR_ENTRY = 6;

    public static String[] fieldNames = {
        "PERIODE_ID",
        "TGL_AWAL",
        "TGL_AKHIR",
        "NAMA",
        "KETERANGAN",
        "POSTED",
        "TGL_TERAKHIR_ENTRY"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_DATE
    };

    public PstPeriode() {
    }

    public PstPeriode(int i) throws DBException {
        super(new PstPeriode());
    }

    public PstPeriode(String sOid) throws DBException {
        super(new PstPeriode(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstPeriode(long lOid) throws DBException {
        super(new PstPeriode(0));
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
        return TBL_PERIODE;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstPeriode().getClass().getName();
    }

    public static Periode fetchExc(long oid) throws DBException {
        try {
            Periode entPeriode = new Periode();
            PstPeriode pstPeriode = new PstPeriode(oid);
            entPeriode.setOID(oid);
            entPeriode.setTglAwal(pstPeriode.getDate(FLD_TGL_AWAL));
            entPeriode.setTglAkhir(pstPeriode.getDate(FLD_TGL_AKHIR));
            entPeriode.setNama(pstPeriode.getString(FLD_NAMA));
            entPeriode.setKeterangan(pstPeriode.getString(FLD_KETERANGAN));
            entPeriode.setPosted(pstPeriode.getInt(FLD_POSTED));
            entPeriode.setTglTerakhirEntry(pstPeriode.getDate(FLD_TGL_TERAKHIR_ENTRY));
            return entPeriode;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPeriode(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        Periode entPeriode = fetchExc(entity.getOID());
        entity = (Entity) entPeriode;
        return entPeriode.getOID();
    }

    public static synchronized long updateExc(Periode entPeriode) throws DBException {
        try {
            if (entPeriode.getOID() != 0) {
                PstPeriode pstPeriode = new PstPeriode(entPeriode.getOID());
                pstPeriode.setDate(FLD_TGL_AWAL, entPeriode.getTglAwal());
                pstPeriode.setDate(FLD_TGL_AKHIR, entPeriode.getTglAkhir());
                pstPeriode.setString(FLD_NAMA, entPeriode.getNama());
                pstPeriode.setString(FLD_KETERANGAN, entPeriode.getKeterangan());
                pstPeriode.setInt(FLD_POSTED, entPeriode.getPosted());
                pstPeriode.setDate(FLD_TGL_TERAKHIR_ENTRY, entPeriode.getTglTerakhirEntry());
                pstPeriode.update();
                return entPeriode.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPeriode(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((Periode) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstPeriode pstPeriode = new PstPeriode(oid);
            pstPeriode.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPeriode(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(Periode entPeriode) throws DBException {
        try {
            PstPeriode pstPeriode = new PstPeriode(0);
            pstPeriode.setDate(FLD_TGL_AWAL, entPeriode.getTglAwal());
            pstPeriode.setDate(FLD_TGL_AKHIR, entPeriode.getTglAkhir());
            pstPeriode.setString(FLD_NAMA, entPeriode.getNama());
            pstPeriode.setString(FLD_KETERANGAN, entPeriode.getKeterangan());
            pstPeriode.setInt(FLD_POSTED, entPeriode.getPosted());
            pstPeriode.setDate(FLD_TGL_TERAKHIR_ENTRY, entPeriode.getTglTerakhirEntry());
            pstPeriode.insert();
            entPeriode.setOID(pstPeriode.getlong(FLD_PERIODE_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPeriode(0), DBException.UNKNOWN);
        }
        return entPeriode.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((Periode) entity);
    }

    public static void resultToObject(ResultSet rs, Periode entPeriode) {
        try {
            entPeriode.setOID(rs.getLong(PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]));
            entPeriode.setTglAwal(rs.getDate(PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]));
            entPeriode.setTglAkhir(rs.getDate(PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR]));
            entPeriode.setNama(rs.getString(PstPeriode.fieldNames[PstPeriode.FLD_NAMA]));
            entPeriode.setKeterangan(rs.getString(PstPeriode.fieldNames[PstPeriode.FLD_KETERANGAN]));
            entPeriode.setPosted(rs.getInt(PstPeriode.fieldNames[PstPeriode.FLD_POSTED]));
            entPeriode.setTglTerakhirEntry(rs.getDate(PstPeriode.fieldNames[PstPeriode.FLD_TGL_TERAKHIR_ENTRY]));
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
            String sql = "SELECT * FROM " + TBL_PERIODE;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY TGL_AWAL DESC, " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                Periode entPeriode = new Periode();
                resultToObject(rs, entPeriode);
                lists.add(entPeriode);
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

    public static boolean checkOID(long entPeriodeId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_PERIODE + " WHERE "
                    + PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID] + " = " + entPeriodeId;
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
    
    public static boolean checkNextPeriode(String date) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_PERIODE + " WHERE "
                    + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL] + " LIKE '%" + date+"%'";
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
    
    
    public static long checkOidNextPeriode(String date) {
        DBResultSet dbrs = null;
        long result = 0;
        try {
            String sql = "SELECT * FROM " + TBL_PERIODE + " WHERE "
                    + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL] + " LIKE '%" + date+"%'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getLong(1);
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
            String sql = "SELECT COUNT(" + PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID] + ") FROM " + TBL_PERIODE;
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
                    Periode entPeriode = (Periode) list.get(ls);
                    if (oid == entPeriode.getOID()) {
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
    
    public static long updateAllPeriodeClosed() throws DBException {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "UPDATE dslik_periode SET posted='1'";
        try {
            iResult = DBHandler.execUpdate(stSql);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            DBResultSet.close(dbrs);
        }
        return iResult;
    }
    
}
