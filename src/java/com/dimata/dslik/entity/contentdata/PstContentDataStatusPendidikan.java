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
public class PstContentDataStatusPendidikan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_STATUS_PENDIDIKAN = "dslik_cd_status_pendidikan";
    public static final int FLD_STATUS_PENDIDIKAN_OID = 0;
    public static final int FLD_STATUS_PENDIDIKAN = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "STATUS_PENDIDIKAN_OID",
        "STATUS_PENDIDIKAN",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataStatusPendidikan() {
    }

    public PstContentDataStatusPendidikan(int i) throws DBException {
        super(new PstContentDataStatusPendidikan());
    }

    public PstContentDataStatusPendidikan(String sOid) throws DBException {
        super(new PstContentDataStatusPendidikan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataStatusPendidikan(long lOid) throws DBException {
        super(new PstContentDataStatusPendidikan(0));
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
        return TBL_CONTENT_DATA_STATUS_PENDIDIKAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataStatusPendidikan().getClass().getName();
    }

    public static ContentDataStatusPendidikan fetchExc(long oid) throws DBException {
        try {
            ContentDataStatusPendidikan entContentDataStatusPendidikan = new ContentDataStatusPendidikan();
            PstContentDataStatusPendidikan pstContentDataStatusPendidikan = new PstContentDataStatusPendidikan(oid);
            entContentDataStatusPendidikan.setOID(oid);
            entContentDataStatusPendidikan.setStatusPendidikan(pstContentDataStatusPendidikan.getString(FLD_STATUS_PENDIDIKAN));
            entContentDataStatusPendidikan.setKodeCoreBanking(pstContentDataStatusPendidikan.getString(FLD_KODE_CORE_BANKING));
            entContentDataStatusPendidikan.setKodeOjk(pstContentDataStatusPendidikan.getString(FLD_KODE_OJK));
            return entContentDataStatusPendidikan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataStatusPendidikan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataStatusPendidikan entContentDataStatusPendidikan = fetchExc(entity.getOID());
        entity = (Entity) entContentDataStatusPendidikan;
        return entContentDataStatusPendidikan.getOID();
    }

    public static synchronized long updateExc(ContentDataStatusPendidikan entContentDataStatusPendidikan) throws DBException {
        try {
            if (entContentDataStatusPendidikan.getOID() != 0) {
                PstContentDataStatusPendidikan pstContentDataStatusPendidikan = new PstContentDataStatusPendidikan(entContentDataStatusPendidikan.getOID());
                pstContentDataStatusPendidikan.setString(FLD_STATUS_PENDIDIKAN, entContentDataStatusPendidikan.getStatusPendidikan());
                pstContentDataStatusPendidikan.setString(FLD_KODE_CORE_BANKING, entContentDataStatusPendidikan.getKodeCoreBanking());
                pstContentDataStatusPendidikan.setString(FLD_KODE_OJK, entContentDataStatusPendidikan.getKodeOjk());
                pstContentDataStatusPendidikan.update();
                return entContentDataStatusPendidikan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataStatusPendidikan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataStatusPendidikan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataStatusPendidikan pstContentDataStatusPendidikan = new PstContentDataStatusPendidikan(oid);
            pstContentDataStatusPendidikan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataStatusPendidikan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataStatusPendidikan entContentDataStatusPendidikan) throws DBException {
        try {
            PstContentDataStatusPendidikan pstContentDataStatusPendidikan = new PstContentDataStatusPendidikan(0);
            pstContentDataStatusPendidikan.setString(FLD_STATUS_PENDIDIKAN, entContentDataStatusPendidikan.getStatusPendidikan());
            pstContentDataStatusPendidikan.setString(FLD_KODE_CORE_BANKING, entContentDataStatusPendidikan.getKodeCoreBanking());
            pstContentDataStatusPendidikan.setString(FLD_KODE_OJK, entContentDataStatusPendidikan.getKodeOjk());
            pstContentDataStatusPendidikan.insert();
            entContentDataStatusPendidikan.setOID(pstContentDataStatusPendidikan.getlong(FLD_STATUS_PENDIDIKAN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataStatusPendidikan(0), DBException.UNKNOWN);
        }
        return entContentDataStatusPendidikan.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataStatusPendidikan) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataStatusPendidikan entContentDataStatusPendidikan) {
        try {
            entContentDataStatusPendidikan.setOID(rs.getLong(PstContentDataStatusPendidikan.fieldNames[PstContentDataStatusPendidikan.FLD_STATUS_PENDIDIKAN_OID]));
            entContentDataStatusPendidikan.setStatusPendidikan(rs.getString(PstContentDataStatusPendidikan.fieldNames[PstContentDataStatusPendidikan.FLD_STATUS_PENDIDIKAN]));
            entContentDataStatusPendidikan.setKodeCoreBanking(rs.getString(PstContentDataStatusPendidikan.fieldNames[PstContentDataStatusPendidikan.FLD_KODE_CORE_BANKING]));
            entContentDataStatusPendidikan.setKodeOjk(rs.getString(PstContentDataStatusPendidikan.fieldNames[PstContentDataStatusPendidikan.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWitoutOid(ResultSet rs, ContentDataStatusPendidikan entContentDataStatusPendidikan) {
        try {
            entContentDataStatusPendidikan.setStatusPendidikan(rs.getString(PstContentDataStatusPendidikan.fieldNames[PstContentDataStatusPendidikan.FLD_STATUS_PENDIDIKAN]));
            entContentDataStatusPendidikan.setKodeOjk(rs.getString(PstContentDataStatusPendidikan.fieldNames[PstContentDataStatusPendidikan.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_STATUS_PENDIDIKAN;
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
                ContentDataStatusPendidikan entContentDataStatusPendidikan = new ContentDataStatusPendidikan();
                resultToObject(rs, entContentDataStatusPendidikan);
                lists.add(entContentDataStatusPendidikan);
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
                    + fieldNames[FLD_STATUS_PENDIDIKAN]+" FROM " + TBL_CONTENT_DATA_STATUS_PENDIDIKAN;
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
                ContentDataStatusPendidikan entContentDataStatusPendidikan = new ContentDataStatusPendidikan();
                resultToObjectWitoutOid(rs, entContentDataStatusPendidikan);
                lists.add(entContentDataStatusPendidikan);
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

    public static boolean checkOID(long entContentDataStatusPendidikanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_STATUS_PENDIDIKAN + " WHERE "
                    + PstContentDataStatusPendidikan.fieldNames[PstContentDataStatusPendidikan.FLD_STATUS_PENDIDIKAN_OID] + " = " + entContentDataStatusPendidikanId;
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
            String sql = "SELECT COUNT(" + PstContentDataStatusPendidikan.fieldNames[PstContentDataStatusPendidikan.FLD_STATUS_PENDIDIKAN_OID] + ") FROM " + TBL_CONTENT_DATA_STATUS_PENDIDIKAN;
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
                    ContentDataStatusPendidikan entContentDataStatusPendidikan = (ContentDataStatusPendidikan) list.get(ls);
                    if (oid == entContentDataStatusPendidikan.getOID()) {
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
