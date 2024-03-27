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
public class PstContentDataSifatKredit extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_SIFAT_KREDIT = "dslik_cd_sifat_kredit";
    public static final int FLD_SIFAT_KREDIT_OID = 0;
    public static final int FLD_SIFAT_KREDIT = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "SIFAT_KREDIT_OID",
        "SIFAT_KREDIT",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataSifatKredit() {
    }

    public PstContentDataSifatKredit(int i) throws DBException {
        super(new PstContentDataSifatKredit());
    }

    public PstContentDataSifatKredit(String sOid) throws DBException {
        super(new PstContentDataSifatKredit(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataSifatKredit(long lOid) throws DBException {
        super(new PstContentDataSifatKredit(0));
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
        return TBL_CONTENT_DATA_SIFAT_KREDIT;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataSifatKredit().getClass().getName();
    }

    public static ContentDataSifatKredit fetchExc(long oid) throws DBException {
        try {
            ContentDataSifatKredit entContentDataSifatKredit = new ContentDataSifatKredit();
            PstContentDataSifatKredit pstContentDataSifatKredit = new PstContentDataSifatKredit(oid);
            entContentDataSifatKredit.setOID(oid);
            entContentDataSifatKredit.setSifatKredit(pstContentDataSifatKredit.getString(FLD_SIFAT_KREDIT));
            entContentDataSifatKredit.setKodeCoreBanking(pstContentDataSifatKredit.getString(FLD_KODE_CORE_BANKING));
            entContentDataSifatKredit.setKodeOjk(pstContentDataSifatKredit.getString(FLD_KODE_OJK));
            return entContentDataSifatKredit;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSifatKredit(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataSifatKredit entContentDataSifatKredit = fetchExc(entity.getOID());
        entity = (Entity) entContentDataSifatKredit;
        return entContentDataSifatKredit.getOID();
    }

    public static synchronized long updateExc(ContentDataSifatKredit entContentDataSifatKredit) throws DBException {
        try {
            if (entContentDataSifatKredit.getOID() != 0) {
                PstContentDataSifatKredit pstContentDataSifatKredit = new PstContentDataSifatKredit(entContentDataSifatKredit.getOID());
                pstContentDataSifatKredit.setString(FLD_SIFAT_KREDIT, entContentDataSifatKredit.getSifatKredit());
                pstContentDataSifatKredit.setString(FLD_KODE_CORE_BANKING, entContentDataSifatKredit.getKodeCoreBanking());
                pstContentDataSifatKredit.setString(FLD_KODE_OJK, entContentDataSifatKredit.getKodeOjk());
                pstContentDataSifatKredit.update();
                return entContentDataSifatKredit.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSifatKredit(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataSifatKredit) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataSifatKredit pstContentDataSifatKredit = new PstContentDataSifatKredit(oid);
            pstContentDataSifatKredit.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSifatKredit(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataSifatKredit entContentDataSifatKredit) throws DBException {
        try {
            PstContentDataSifatKredit pstContentDataSifatKredit = new PstContentDataSifatKredit(0);
            pstContentDataSifatKredit.setString(FLD_SIFAT_KREDIT, entContentDataSifatKredit.getSifatKredit());
            pstContentDataSifatKredit.setString(FLD_KODE_CORE_BANKING, entContentDataSifatKredit.getKodeCoreBanking());
            pstContentDataSifatKredit.setString(FLD_KODE_OJK, entContentDataSifatKredit.getKodeOjk());
            pstContentDataSifatKredit.insert();
            entContentDataSifatKredit.setOID(pstContentDataSifatKredit.getlong(FLD_SIFAT_KREDIT_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSifatKredit(0), DBException.UNKNOWN);
        }
        return entContentDataSifatKredit.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataSifatKredit) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataSifatKredit entContentDataSifatKredit) {
        try {
            entContentDataSifatKredit.setOID(rs.getLong(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT_OID]));
            entContentDataSifatKredit.setSifatKredit(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]));
            entContentDataSifatKredit.setKodeCoreBanking(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_KODE_CORE_BANKING]));
            entContentDataSifatKredit.setKodeOjk(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }

    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataSifatKredit entContentDataSifatKredit) {
        try {
            entContentDataSifatKredit.setSifatKredit(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]));
            entContentDataSifatKredit.setKodeOjk(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }

    
    public static void resultToObjectTanpaOid(ResultSet rs, ContentDataSifatKredit entContentDataSifatKredit) {
        try {
            entContentDataSifatKredit.setSifatKredit(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]));
            entContentDataSifatKredit.setKodeOjk(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SIFAT_KREDIT;
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
                ContentDataSifatKredit entContentDataSifatKredit = new ContentDataSifatKredit();
                resultToObject(rs, entContentDataSifatKredit);
                lists.add(entContentDataSifatKredit);
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
            String sql = "SELECT DISTINCT "+fieldNames[FLD_KODE_OJK]+", "
                    + fieldNames[FLD_SIFAT_KREDIT]+" "
                    + "FROM " + TBL_CONTENT_DATA_SIFAT_KREDIT;
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
                ContentDataSifatKredit entContentDataSifatKredit = new ContentDataSifatKredit();
                resultToObjectWithoutOid(rs, entContentDataSifatKredit);
                lists.add(entContentDataSifatKredit);
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

    
    
    public static Vector listTanpaOid(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT SIFAT_KREDIT, KODE_OJK FROM " + TBL_CONTENT_DATA_SIFAT_KREDIT;
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
                ContentDataSifatKredit entContentDataSifatKredit = new ContentDataSifatKredit();
                resultToObjectTanpaOid(rs, entContentDataSifatKredit);
                lists.add(entContentDataSifatKredit);
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


    public static boolean checkOID(long entContentDataSifatKreditId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SIFAT_KREDIT + " WHERE "
                    + PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT_OID] + " = " + entContentDataSifatKreditId;
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
            String sql = "SELECT COUNT(" + PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT_OID] + ") FROM " + TBL_CONTENT_DATA_SIFAT_KREDIT;
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
                    ContentDataSifatKredit entContentDataSifatKredit = (ContentDataSifatKredit) list.get(ls);
                    if (oid == entContentDataSifatKredit.getOID()) {
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
