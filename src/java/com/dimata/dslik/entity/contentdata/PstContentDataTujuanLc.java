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
public class PstContentDataTujuanLc extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_TUJUAN_LC = "dslik_cd_tujuan_lc";
    public static final int FLD_TUJUAN_LC_OID = 0;
    public static final int FLD_NAMA_TUJUAN_LC = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "TUJUAN_LC_OID",
        "NAMA_TUJUAN_LC",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataTujuanLc() {
    }

    public PstContentDataTujuanLc(int i) throws DBException {
        super(new PstContentDataTujuanLc());
    }

    public PstContentDataTujuanLc(String sOid) throws DBException {
        super(new PstContentDataTujuanLc(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataTujuanLc(long lOid) throws DBException {
        super(new PstContentDataTujuanLc(0));
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
        return TBL_CONTENT_DATA_TUJUAN_LC;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataTujuanLc().getClass().getName();
    }

    public static ContentDataTujuanLc fetchExc(long oid) throws DBException {
        try {
            ContentDataTujuanLc entContentDataTujuanLc = new ContentDataTujuanLc();
            PstContentDataTujuanLc pstContentDataTujuanLc = new PstContentDataTujuanLc(oid);
            entContentDataTujuanLc.setOID(oid);
            entContentDataTujuanLc.setNamaTujuanLc(pstContentDataTujuanLc.getString(FLD_NAMA_TUJUAN_LC));
            entContentDataTujuanLc.setKodeCoreBanking(pstContentDataTujuanLc.getString(FLD_KODE_CORE_BANKING));
            entContentDataTujuanLc.setKodeOjk(pstContentDataTujuanLc.getString(FLD_KODE_OJK));
            return entContentDataTujuanLc;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataTujuanLc(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataTujuanLc entContentDataTujuanLc = fetchExc(entity.getOID());
        entity = (Entity) entContentDataTujuanLc;
        return entContentDataTujuanLc.getOID();
    }

    public static synchronized long updateExc(ContentDataTujuanLc entContentDataTujuanLc) throws DBException {
        try {
            if (entContentDataTujuanLc.getOID() != 0) {
                PstContentDataTujuanLc pstContentDataTujuanLc = new PstContentDataTujuanLc(entContentDataTujuanLc.getOID());
                pstContentDataTujuanLc.setString(FLD_NAMA_TUJUAN_LC, entContentDataTujuanLc.getNamaTujuanLc());
                pstContentDataTujuanLc.setString(FLD_KODE_CORE_BANKING, entContentDataTujuanLc.getKodeCoreBanking());
                pstContentDataTujuanLc.setString(FLD_KODE_OJK, entContentDataTujuanLc.getKodeOjk());
                pstContentDataTujuanLc.update();
                return entContentDataTujuanLc.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataTujuanLc(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataTujuanLc) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataTujuanLc pstContentDataTujuanLc = new PstContentDataTujuanLc(oid);
            pstContentDataTujuanLc.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataTujuanLc(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataTujuanLc entContentDataTujuanLc) throws DBException {
        try {
            PstContentDataTujuanLc pstContentDataTujuanLc = new PstContentDataTujuanLc(0);
            pstContentDataTujuanLc.setString(FLD_NAMA_TUJUAN_LC, entContentDataTujuanLc.getNamaTujuanLc());
            pstContentDataTujuanLc.setString(FLD_KODE_CORE_BANKING, entContentDataTujuanLc.getKodeCoreBanking());
            pstContentDataTujuanLc.setString(FLD_KODE_OJK, entContentDataTujuanLc.getKodeOjk());
            pstContentDataTujuanLc.insert();
            entContentDataTujuanLc.setOID(pstContentDataTujuanLc.getlong(FLD_TUJUAN_LC_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataTujuanLc(0), DBException.UNKNOWN);
        }
        return entContentDataTujuanLc.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataTujuanLc) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataTujuanLc entContentDataTujuanLc) {
        try {
            entContentDataTujuanLc.setOID(rs.getLong(PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_TUJUAN_LC_OID]));
            entContentDataTujuanLc.setNamaTujuanLc(rs.getString(PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]));
            entContentDataTujuanLc.setKodeCoreBanking(rs.getString(PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_KODE_CORE_BANKING]));
            entContentDataTujuanLc.setKodeOjk(rs.getString(PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataTujuanLc entContentDataTujuanLc) {
        try {
            entContentDataTujuanLc.setNamaTujuanLc(rs.getString(PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]));
            entContentDataTujuanLc.setKodeOjk(rs.getString(PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_TUJUAN_LC;
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
                ContentDataTujuanLc entContentDataTujuanLc = new ContentDataTujuanLc();
                resultToObject(rs, entContentDataTujuanLc);
                lists.add(entContentDataTujuanLc);
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
    
    public static Vector listWitoutOid(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "+fieldNames[FLD_KODE_OJK]+","
                    + fieldNames[FLD_NAMA_TUJUAN_LC]+" "
                    + "FROM " + TBL_CONTENT_DATA_TUJUAN_LC;
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
                ContentDataTujuanLc entContentDataTujuanLc = new ContentDataTujuanLc();
                resultToObjectWithoutOid(rs, entContentDataTujuanLc);
                lists.add(entContentDataTujuanLc);
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

    public static boolean checkOID(long entContentDataTujuanLcId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_TUJUAN_LC + " WHERE "
                    + PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_TUJUAN_LC_OID] + " = " + entContentDataTujuanLcId;
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
            String sql = "SELECT COUNT(" + PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_TUJUAN_LC_OID] + ") FROM " + TBL_CONTENT_DATA_TUJUAN_LC;
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
                    ContentDataTujuanLc entContentDataTujuanLc = (ContentDataTujuanLc) list.get(ls);
                    if (oid == entContentDataTujuanLc.getOID()) {
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
