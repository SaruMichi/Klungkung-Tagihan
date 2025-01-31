/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

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
public class PstContentDataSukuBunga extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_SUKU_BUNGA = "dslik_cd_suku_bunga";
    public static final int FLD_SUKU_BUNGA_OID = 0;
    public static final int FLD_NAMA_SUKU_BUNGA = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "SUKU_BUNGA_OID",
        "NAMA_SUKU_BUNGA",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataSukuBunga() {
    }

    public PstContentDataSukuBunga(int i) throws DBException {
        super(new PstContentDataSukuBunga());
    }

    public PstContentDataSukuBunga(String sOid) throws DBException {
        super(new PstContentDataSukuBunga(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataSukuBunga(long lOid) throws DBException {
        super(new PstContentDataSukuBunga(0));
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
        return TBL_CONTENT_DATA_SUKU_BUNGA;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataSukuBunga().getClass().getName();
    }

    public static ContentDataSukuBunga fetchExc(long oid) throws DBException {
        try {
            ContentDataSukuBunga entContentDataSukuBunga = new ContentDataSukuBunga();
            PstContentDataSukuBunga pstContentDataSukuBunga = new PstContentDataSukuBunga(oid);
            entContentDataSukuBunga.setOID(oid);
            entContentDataSukuBunga.setNamaSukuBunga(pstContentDataSukuBunga.getString(FLD_NAMA_SUKU_BUNGA));
            entContentDataSukuBunga.setKodeCoreBanking(pstContentDataSukuBunga.getString(FLD_KODE_CORE_BANKING));
            entContentDataSukuBunga.setKodeOjk(pstContentDataSukuBunga.getString(FLD_KODE_OJK));
            return entContentDataSukuBunga;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSukuBunga(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataSukuBunga entContentDataSukuBunga = fetchExc(entity.getOID());
        entity = (Entity) entContentDataSukuBunga;
        return entContentDataSukuBunga.getOID();
    }

    public static synchronized long updateExc(ContentDataSukuBunga entContentDataSukuBunga) throws DBException {
        try {
            if (entContentDataSukuBunga.getOID() != 0) {
                PstContentDataSukuBunga pstContentDataSukuBunga = new PstContentDataSukuBunga(entContentDataSukuBunga.getOID());
                pstContentDataSukuBunga.setString(FLD_NAMA_SUKU_BUNGA, entContentDataSukuBunga.getNamaSukuBunga());
                pstContentDataSukuBunga.setString(FLD_KODE_CORE_BANKING, entContentDataSukuBunga.getKodeCoreBanking());
                pstContentDataSukuBunga.setString(FLD_KODE_OJK, entContentDataSukuBunga.getKodeOjk());
                pstContentDataSukuBunga.update();
                return entContentDataSukuBunga.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSukuBunga(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataSukuBunga) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataSukuBunga pstContentDataSukuBunga = new PstContentDataSukuBunga(oid);
            pstContentDataSukuBunga.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSukuBunga(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataSukuBunga entContentDataSukuBunga) throws DBException {
        try {
            PstContentDataSukuBunga pstContentDataSukuBunga = new PstContentDataSukuBunga(0);
            pstContentDataSukuBunga.setString(FLD_NAMA_SUKU_BUNGA, entContentDataSukuBunga.getNamaSukuBunga());
            pstContentDataSukuBunga.setString(FLD_KODE_CORE_BANKING, entContentDataSukuBunga.getKodeCoreBanking());
            pstContentDataSukuBunga.setString(FLD_KODE_OJK, entContentDataSukuBunga.getKodeOjk());
            pstContentDataSukuBunga.insert();
            entContentDataSukuBunga.setOID(pstContentDataSukuBunga.getlong(FLD_SUKU_BUNGA_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSukuBunga(0), DBException.UNKNOWN);
        }
        return entContentDataSukuBunga.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataSukuBunga) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataSukuBunga entContentDataSukuBunga) {
        try {
            entContentDataSukuBunga.setOID(rs.getLong(PstContentDataSukuBunga.fieldNames[PstContentDataSukuBunga.FLD_SUKU_BUNGA_OID]));
            entContentDataSukuBunga.setNamaSukuBunga(rs.getString(PstContentDataSukuBunga.fieldNames[PstContentDataSukuBunga.FLD_NAMA_SUKU_BUNGA]));
            entContentDataSukuBunga.setKodeCoreBanking(rs.getString(PstContentDataSukuBunga.fieldNames[PstContentDataSukuBunga.FLD_KODE_CORE_BANKING]));
            entContentDataSukuBunga.setKodeOjk(rs.getString(PstContentDataSukuBunga.fieldNames[PstContentDataSukuBunga.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataSukuBunga entContentDataSukuBunga) {
        try {
            entContentDataSukuBunga.setNamaSukuBunga(rs.getString(PstContentDataSukuBunga.fieldNames[PstContentDataSukuBunga.FLD_NAMA_SUKU_BUNGA]));
            entContentDataSukuBunga.setKodeOjk(rs.getString(PstContentDataSukuBunga.fieldNames[PstContentDataSukuBunga.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SUKU_BUNGA;
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
                ContentDataSukuBunga entContentDataSukuBunga = new ContentDataSukuBunga();
                resultToObject(rs, entContentDataSukuBunga);
                lists.add(entContentDataSukuBunga);
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
    
    public static Vector listWithoutOid(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "+fieldNames[FLD_KODE_OJK]+","
                    + fieldNames[FLD_NAMA_SUKU_BUNGA]+" "
                    + "FROM " + TBL_CONTENT_DATA_SUKU_BUNGA;
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
                ContentDataSukuBunga entContentDataSukuBunga = new ContentDataSukuBunga();
                resultToObjectWithoutOid(rs, entContentDataSukuBunga);
                lists.add(entContentDataSukuBunga);
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

    public static boolean checkOID(long entContentDataSukuBungaId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SUKU_BUNGA + " WHERE "
                    + PstContentDataSukuBunga.fieldNames[PstContentDataSukuBunga.FLD_SUKU_BUNGA_OID] + " = " + entContentDataSukuBungaId;
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
            String sql = "SELECT COUNT(" + PstContentDataSukuBunga.fieldNames[PstContentDataSukuBunga.FLD_SUKU_BUNGA_OID] + ") FROM " + TBL_CONTENT_DATA_SUKU_BUNGA;
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
                    ContentDataSukuBunga entContentDataSukuBunga = (ContentDataSukuBunga) list.get(ls);
                    if (oid == entContentDataSukuBunga.getOID()) {
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
