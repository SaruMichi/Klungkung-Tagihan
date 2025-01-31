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
public class PstContentDataSumberPenghasilan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_SUMBER_PENGHASILAN = "dslik_cd_sumber_penghasilan";
    public static final int FLD_SUMBER_PENGHASILAN_OID = 0;
    public static final int FLD_SUMBER_PENGHASILAN = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "SUMBER_PENGHASILAN_OID",
        "SUMBER_PENGHASILAN",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataSumberPenghasilan() {
    }

    public PstContentDataSumberPenghasilan(int i) throws DBException {
        super(new PstContentDataSumberPenghasilan());
    }

    public PstContentDataSumberPenghasilan(String sOid) throws DBException {
        super(new PstContentDataSumberPenghasilan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataSumberPenghasilan(long lOid) throws DBException {
        super(new PstContentDataSumberPenghasilan(0));
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
        return TBL_CONTENT_DATA_SUMBER_PENGHASILAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataSumberPenghasilan().getClass().getName();
    }

    public static ContentDataSumberPenghasilan fetchExc(long oid) throws DBException {
        try {
            ContentDataSumberPenghasilan entContentDataSumberPenghasilan = new ContentDataSumberPenghasilan();
            PstContentDataSumberPenghasilan pstContentDataSumberPenghasilan = new PstContentDataSumberPenghasilan(oid);
            entContentDataSumberPenghasilan.setOID(oid);
            entContentDataSumberPenghasilan.setSumberPenghasilan(pstContentDataSumberPenghasilan.getString(FLD_SUMBER_PENGHASILAN));
            entContentDataSumberPenghasilan.setKodeCoreBanking(pstContentDataSumberPenghasilan.getString(FLD_KODE_CORE_BANKING));
            entContentDataSumberPenghasilan.setKodeOjk(pstContentDataSumberPenghasilan.getString(FLD_KODE_OJK));
            return entContentDataSumberPenghasilan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSumberPenghasilan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataSumberPenghasilan entContentDataSumberPenghasilan = fetchExc(entity.getOID());
        entity = (Entity) entContentDataSumberPenghasilan;
        return entContentDataSumberPenghasilan.getOID();
    }

    public static synchronized long updateExc(ContentDataSumberPenghasilan entContentDataSumberPenghasilan) throws DBException {
        try {
            if (entContentDataSumberPenghasilan.getOID() != 0) {
                PstContentDataSumberPenghasilan pstContentDataSumberPenghasilan = new PstContentDataSumberPenghasilan(entContentDataSumberPenghasilan.getOID());
                pstContentDataSumberPenghasilan.setString(FLD_SUMBER_PENGHASILAN, entContentDataSumberPenghasilan.getSumberPenghasilan());
                pstContentDataSumberPenghasilan.setString(FLD_KODE_CORE_BANKING, entContentDataSumberPenghasilan.getKodeCoreBanking());
                pstContentDataSumberPenghasilan.setString(FLD_KODE_OJK, entContentDataSumberPenghasilan.getKodeOjk());
                pstContentDataSumberPenghasilan.update();
                return entContentDataSumberPenghasilan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSumberPenghasilan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataSumberPenghasilan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataSumberPenghasilan pstContentDataSumberPenghasilan = new PstContentDataSumberPenghasilan(oid);
            pstContentDataSumberPenghasilan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSumberPenghasilan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataSumberPenghasilan entContentDataSumberPenghasilan) throws DBException {
        try {
            PstContentDataSumberPenghasilan pstContentDataSumberPenghasilan = new PstContentDataSumberPenghasilan(0);
            pstContentDataSumberPenghasilan.setString(FLD_SUMBER_PENGHASILAN, entContentDataSumberPenghasilan.getSumberPenghasilan());
            pstContentDataSumberPenghasilan.setString(FLD_KODE_CORE_BANKING, entContentDataSumberPenghasilan.getKodeCoreBanking());
            pstContentDataSumberPenghasilan.setString(FLD_KODE_OJK, entContentDataSumberPenghasilan.getKodeOjk());
            pstContentDataSumberPenghasilan.insert();
            entContentDataSumberPenghasilan.setOID(pstContentDataSumberPenghasilan.getlong(FLD_SUMBER_PENGHASILAN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSumberPenghasilan(0), DBException.UNKNOWN);
        }
        return entContentDataSumberPenghasilan.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataSumberPenghasilan) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataSumberPenghasilan entContentDataSumberPenghasilan) {
        try {
            entContentDataSumberPenghasilan.setOID(rs.getLong(PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN_OID]));
            entContentDataSumberPenghasilan.setSumberPenghasilan(rs.getString(PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN]));
            entContentDataSumberPenghasilan.setKodeCoreBanking(rs.getString(PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_KODE_CORE_BANKING]));
            entContentDataSumberPenghasilan.setKodeOjk(rs.getString(PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataSumberPenghasilan entContentDataSumberPenghasilan) {
        try {
            entContentDataSumberPenghasilan.setSumberPenghasilan(rs.getString(PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN]));
            entContentDataSumberPenghasilan.setKodeOjk(rs.getString(PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SUMBER_PENGHASILAN;
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
                ContentDataSumberPenghasilan entContentDataSumberPenghasilan = new ContentDataSumberPenghasilan();
                resultToObject(rs, entContentDataSumberPenghasilan);
                lists.add(entContentDataSumberPenghasilan);
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
                    + fieldNames[FLD_SUMBER_PENGHASILAN]+" FROM " + TBL_CONTENT_DATA_SUMBER_PENGHASILAN;
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
                ContentDataSumberPenghasilan entContentDataSumberPenghasilan = new ContentDataSumberPenghasilan();
                resultToObjectWithoutOid(rs, entContentDataSumberPenghasilan);
                lists.add(entContentDataSumberPenghasilan);
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

    public static boolean checkOID(long entContentDataSumberPenghasilanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SUMBER_PENGHASILAN + " WHERE "
                    + PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN_OID] + " = " + entContentDataSumberPenghasilanId;
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
            String sql = "SELECT COUNT(" + PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN_OID] + ") FROM " + TBL_CONTENT_DATA_SUMBER_PENGHASILAN;
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
                    ContentDataSumberPenghasilan entContentDataSumberPenghasilan = (ContentDataSumberPenghasilan) list.get(ls);
                    if (oid == entContentDataSumberPenghasilan.getOID()) {
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
