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

public class PstContentDataJenisGaransi extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_JENIS_GARANSI = "dslik_cd_jenis_garansi";
    public static final int FLD_JENIS_GARANSI_OID = 0;
    public static final int FLD_JENIS_GARANSI = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "JENIS_GARANSI_OID",
        "JENIS_GARANSI",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataJenisGaransi() {
    }

    public PstContentDataJenisGaransi(int i) throws DBException {
        super(new PstContentDataJenisGaransi());
    }

    public PstContentDataJenisGaransi(String sOid) throws DBException {
        super(new PstContentDataJenisGaransi(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataJenisGaransi(long lOid) throws DBException {
        super(new PstContentDataJenisGaransi(0));
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
        return TBL_CONTENT_DATA_JENIS_GARANSI;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataJenisGaransi().getClass().getName();
    }

    public static ContentDataJenisGaransi fetchExc(long oid) throws DBException {
        try {
            ContentDataJenisGaransi entContentDataJenisGaransi = new ContentDataJenisGaransi();
            PstContentDataJenisGaransi pstContentDataJenisGaransi = new PstContentDataJenisGaransi(oid);
            entContentDataJenisGaransi.setOID(oid);
            entContentDataJenisGaransi.setJenisGaransi(pstContentDataJenisGaransi.getString(FLD_JENIS_GARANSI));
            entContentDataJenisGaransi.setKodeCoreBanking(pstContentDataJenisGaransi.getString(FLD_KODE_CORE_BANKING));
            entContentDataJenisGaransi.setKodeOjk(pstContentDataJenisGaransi.getString(FLD_KODE_OJK));
            return entContentDataJenisGaransi;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisGaransi(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataJenisGaransi entContentDataJenisGaransi = fetchExc(entity.getOID());
        entity = (Entity) entContentDataJenisGaransi;
        return entContentDataJenisGaransi.getOID();
    }

    public static synchronized long updateExc(ContentDataJenisGaransi entContentDataJenisGaransi) throws DBException {
        try {
            if (entContentDataJenisGaransi.getOID() != 0) {
                PstContentDataJenisGaransi pstContentDataJenisGaransi = new PstContentDataJenisGaransi(entContentDataJenisGaransi.getOID());
                pstContentDataJenisGaransi.setString(FLD_JENIS_GARANSI, entContentDataJenisGaransi.getJenisGaransi());
                pstContentDataJenisGaransi.setString(FLD_KODE_CORE_BANKING, entContentDataJenisGaransi.getKodeCoreBanking());
                pstContentDataJenisGaransi.setString(FLD_KODE_OJK, entContentDataJenisGaransi.getKodeOjk());
                pstContentDataJenisGaransi.update();
                return entContentDataJenisGaransi.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisGaransi(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataJenisGaransi) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataJenisGaransi pstContentDataJenisGaransi = new PstContentDataJenisGaransi(oid);
            pstContentDataJenisGaransi.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisGaransi(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataJenisGaransi entContentDataJenisGaransi) throws DBException {
        try {
            PstContentDataJenisGaransi pstContentDataJenisGaransi = new PstContentDataJenisGaransi(0);
            pstContentDataJenisGaransi.setString(FLD_JENIS_GARANSI, entContentDataJenisGaransi.getJenisGaransi());
            pstContentDataJenisGaransi.setString(FLD_KODE_CORE_BANKING, entContentDataJenisGaransi.getKodeCoreBanking());
            pstContentDataJenisGaransi.setString(FLD_KODE_OJK, entContentDataJenisGaransi.getKodeOjk());
            pstContentDataJenisGaransi.insert();
            entContentDataJenisGaransi.setOID(pstContentDataJenisGaransi.getlong(FLD_JENIS_GARANSI_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisGaransi(0), DBException.UNKNOWN);
        }
        return entContentDataJenisGaransi.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataJenisGaransi) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataJenisGaransi entContentDataJenisGaransi) {
        try {
            entContentDataJenisGaransi.setOID(rs.getLong(PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI_OID]));
            entContentDataJenisGaransi.setJenisGaransi(rs.getString(PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI]));
            entContentDataJenisGaransi.setKodeCoreBanking(rs.getString(PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_KODE_CORE_BANKING]));
            entContentDataJenisGaransi.setKodeOjk(rs.getString(PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectTanpaOid(ResultSet rs, ContentDataJenisGaransi entContentDataJenisGaransi) {
        try {
            entContentDataJenisGaransi.setJenisGaransi(rs.getString(PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI]));
            entContentDataJenisGaransi.setKodeOjk(rs.getString(PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataJenisGaransi entContentDataJenisGaransi) {
        try {
            entContentDataJenisGaransi.setJenisGaransi(rs.getString(PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI]));
            entContentDataJenisGaransi.setKodeOjk(rs.getString(PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_GARANSI;
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
                ContentDataJenisGaransi entContentDataJenisGaransi = new ContentDataJenisGaransi();
                resultToObject(rs, entContentDataJenisGaransi);
                lists.add(entContentDataJenisGaransi);
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
    
    public static Vector listtanpaoid(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT JENIS_GARANSI, KODE_OJK FROM " + TBL_CONTENT_DATA_JENIS_GARANSI;
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
                ContentDataJenisGaransi entContentDataJenisGaransi = new ContentDataJenisGaransi();
                resultToObjectTanpaOid(rs, entContentDataJenisGaransi);
                lists.add(entContentDataJenisGaransi);
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
            String sql = "SELECT DISTINCT "+fieldNames[FLD_JENIS_GARANSI]+","
                    + fieldNames[FLD_KODE_OJK]+" "
                    + "FROM " + TBL_CONTENT_DATA_JENIS_GARANSI;
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
                ContentDataJenisGaransi entContentDataJenisGaransi = new ContentDataJenisGaransi();
                resultToObjectWithoutOid(rs, entContentDataJenisGaransi);
                lists.add(entContentDataJenisGaransi);
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
    public static boolean checkOID(long entContentDataJenisGaransiId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_GARANSI + " WHERE "
                    + PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI_OID] + " = " + entContentDataJenisGaransiId;
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
            String sql = "SELECT COUNT(" + PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI_OID] + ") FROM " + TBL_CONTENT_DATA_JENIS_GARANSI;
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
                    ContentDataJenisGaransi entContentDataJenisGaransi = (ContentDataJenisGaransi) list.get(ls);
                    if (oid == entContentDataJenisGaransi.getOID()) {
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
