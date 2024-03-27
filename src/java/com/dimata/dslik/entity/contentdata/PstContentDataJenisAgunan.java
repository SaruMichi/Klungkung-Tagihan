/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

/**
 *
 * @author m20n9
 */
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstContentDataJenisAgunan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_JENIS_AGUNAN = "dslik_cd_jenis_agunan";
    public static final int FLD_JENIS_AGUNAN_OID = 0;
    public static final int FLD_JENIS_AGUNAN = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "JENIS_AGUNAN_OID",
        "JENIS_AGUNAN",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataJenisAgunan() {
    }

    public PstContentDataJenisAgunan(int i) throws DBException {
        super(new PstContentDataJenisAgunan());
    }

    public PstContentDataJenisAgunan(String sOid) throws DBException {
        super(new PstContentDataJenisAgunan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataJenisAgunan(long lOid) throws DBException {
        super(new PstContentDataJenisAgunan(0));
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
        return TBL_CONTENT_DATA_JENIS_AGUNAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataJenisAgunan().getClass().getName();
    }

    public static ContentDataJenisAgunan fetchExc(long oid) throws DBException {
        try {
            ContentDataJenisAgunan entContentDataJenisAgunan = new ContentDataJenisAgunan();
            PstContentDataJenisAgunan pstContentDataJenisAgunan = new PstContentDataJenisAgunan(oid);
            entContentDataJenisAgunan.setOID(oid);
            entContentDataJenisAgunan.setJenisAgunan(pstContentDataJenisAgunan.getString(FLD_JENIS_AGUNAN));
            entContentDataJenisAgunan.setKodeCoreBanking(pstContentDataJenisAgunan.getString(FLD_KODE_CORE_BANKING));
            entContentDataJenisAgunan.setKodeOjk(pstContentDataJenisAgunan.getString(FLD_KODE_OJK));
            return entContentDataJenisAgunan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisAgunan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataJenisAgunan entContentDataJenisAgunan = fetchExc(entity.getOID());
        entity = (Entity) entContentDataJenisAgunan;
        return entContentDataJenisAgunan.getOID();
    }

    public static synchronized long updateExc(ContentDataJenisAgunan entContentDataJenisAgunan) throws DBException {
        try {
            if (entContentDataJenisAgunan.getOID() != 0) {
                PstContentDataJenisAgunan pstContentDataJenisAgunan = new PstContentDataJenisAgunan(entContentDataJenisAgunan.getOID());
                pstContentDataJenisAgunan.setString(FLD_JENIS_AGUNAN, entContentDataJenisAgunan.getJenisAgunan());
                pstContentDataJenisAgunan.setString(FLD_KODE_CORE_BANKING, entContentDataJenisAgunan.getKodeCoreBanking());
                pstContentDataJenisAgunan.setString(FLD_KODE_OJK, entContentDataJenisAgunan.getKodeOjk());
                pstContentDataJenisAgunan.update();
                return entContentDataJenisAgunan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisAgunan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataJenisAgunan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataJenisAgunan pstContentDataJenisAgunan = new PstContentDataJenisAgunan(oid);
            pstContentDataJenisAgunan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisAgunan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataJenisAgunan entContentDataJenisAgunan) throws DBException {
        try {
            PstContentDataJenisAgunan pstContentDataJenisAgunan = new PstContentDataJenisAgunan(0);
            pstContentDataJenisAgunan.setString(FLD_JENIS_AGUNAN, entContentDataJenisAgunan.getJenisAgunan());
            pstContentDataJenisAgunan.setString(FLD_KODE_CORE_BANKING, entContentDataJenisAgunan.getKodeCoreBanking());
            pstContentDataJenisAgunan.setString(FLD_KODE_OJK, entContentDataJenisAgunan.getKodeOjk());
            pstContentDataJenisAgunan.insert();
            entContentDataJenisAgunan.setOID(pstContentDataJenisAgunan.getlong(FLD_JENIS_AGUNAN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisAgunan(0), DBException.UNKNOWN);
        }
        return entContentDataJenisAgunan.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataJenisAgunan) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataJenisAgunan entContentDataJenisAgunan) {
        try {
            entContentDataJenisAgunan.setOID(rs.getLong(PstContentDataJenisAgunan.fieldNames[PstContentDataJenisAgunan.FLD_JENIS_AGUNAN_OID]));
            entContentDataJenisAgunan.setJenisAgunan(rs.getString(PstContentDataJenisAgunan.fieldNames[PstContentDataJenisAgunan.FLD_JENIS_AGUNAN]));
            entContentDataJenisAgunan.setKodeCoreBanking(rs.getString(PstContentDataJenisAgunan.fieldNames[PstContentDataJenisAgunan.FLD_KODE_CORE_BANKING]));
            entContentDataJenisAgunan.setKodeOjk(rs.getString(PstContentDataJenisAgunan.fieldNames[PstContentDataJenisAgunan.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectTanpaOid(ResultSet rs, ContentDataJenisAgunan entContentDataJenisAgunan) {
        try {
            entContentDataJenisAgunan.setJenisAgunan(rs.getString(PstContentDataJenisAgunan.fieldNames[PstContentDataJenisAgunan.FLD_JENIS_AGUNAN]));
            entContentDataJenisAgunan.setKodeOjk(rs.getString(PstContentDataJenisAgunan.fieldNames[PstContentDataJenisAgunan.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_AGUNAN;
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
                ContentDataJenisAgunan entContentDataJenisAgunan = new ContentDataJenisAgunan();
                resultToObject(rs, entContentDataJenisAgunan);
                lists.add(entContentDataJenisAgunan);
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
            String sql = "SELECT DISTINCT JENIS_AGUNAN, KODE_OJK FROM " + TBL_CONTENT_DATA_JENIS_AGUNAN;
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
                ContentDataJenisAgunan entContentDataJenisAgunan = new ContentDataJenisAgunan();
                resultToObjectTanpaOid(rs, entContentDataJenisAgunan);
                lists.add(entContentDataJenisAgunan);
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

    public static boolean checkOID(long entContentDataJenisAgunanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_AGUNAN + " WHERE "
                    + PstContentDataJenisAgunan.fieldNames[PstContentDataJenisAgunan.FLD_JENIS_AGUNAN_OID] + " = " + entContentDataJenisAgunanId;
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
            String sql = "SELECT COUNT(" + PstContentDataJenisAgunan.fieldNames[PstContentDataJenisAgunan.FLD_JENIS_AGUNAN_OID] + ") FROM " + TBL_CONTENT_DATA_JENIS_AGUNAN;
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
                    ContentDataJenisAgunan entContentDataJenisAgunan = (ContentDataJenisAgunan) list.get(ls);
                    if (oid == entContentDataJenisAgunan.getOID()) {
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
