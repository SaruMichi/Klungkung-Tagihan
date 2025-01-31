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

public class PstContentDataBidangUsaha extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_BIDANG_USAHA = "dslik_cd_bidang_usaha";
    public static final int FLD_BIDANG_USAHA_OID = 0;
    public static final int FLD_NAMA_BIDANG_USAHA = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "BIDANG_USAHA_OID",
        "NAMA_BIDANG_USAHA",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataBidangUsaha() {
    }

    public PstContentDataBidangUsaha(int i) throws DBException {
        super(new PstContentDataBidangUsaha());
    }

    public PstContentDataBidangUsaha(String sOid) throws DBException {
        super(new PstContentDataBidangUsaha(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataBidangUsaha(long lOid) throws DBException {
        super(new PstContentDataBidangUsaha(0));
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
        return TBL_CONTENT_DATA_BIDANG_USAHA;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataBidangUsaha().getClass().getName();
    }

    public static ContentDataBidangUsaha fetchExc(long oid) throws DBException {
        try {
            ContentDataBidangUsaha entContentDataBidangUsaha = new ContentDataBidangUsaha();
            PstContentDataBidangUsaha pstContentDataBidangUsaha = new PstContentDataBidangUsaha(oid);
            entContentDataBidangUsaha.setOID(oid);
            entContentDataBidangUsaha.setNamaBidangUsaha(pstContentDataBidangUsaha.getString(FLD_NAMA_BIDANG_USAHA));
            entContentDataBidangUsaha.setKodeCoreBanking(pstContentDataBidangUsaha.getString(FLD_KODE_CORE_BANKING));
            entContentDataBidangUsaha.setKodeOjk(pstContentDataBidangUsaha.getString(FLD_KODE_OJK));
            return entContentDataBidangUsaha;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataBidangUsaha(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataBidangUsaha entContentDataBidangUsaha = fetchExc(entity.getOID());
        entity = (Entity) entContentDataBidangUsaha;
        return entContentDataBidangUsaha.getOID();
    }

    public static synchronized long updateExc(ContentDataBidangUsaha entContentDataBidangUsaha) throws DBException {
        try {
            if (entContentDataBidangUsaha.getOID() != 0) {
                PstContentDataBidangUsaha pstContentDataBidangUsaha = new PstContentDataBidangUsaha(entContentDataBidangUsaha.getOID());
                pstContentDataBidangUsaha.setString(FLD_NAMA_BIDANG_USAHA, entContentDataBidangUsaha.getNamaBidangUsaha());
                pstContentDataBidangUsaha.setString(FLD_KODE_CORE_BANKING, entContentDataBidangUsaha.getKodeCoreBanking());
                pstContentDataBidangUsaha.setString(FLD_KODE_OJK, entContentDataBidangUsaha.getKodeOjk());
                pstContentDataBidangUsaha.update();
                return entContentDataBidangUsaha.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataBidangUsaha(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataBidangUsaha) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataBidangUsaha pstContentDataBidangUsaha = new PstContentDataBidangUsaha(oid);
            pstContentDataBidangUsaha.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataBidangUsaha(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataBidangUsaha entContentDataBidangUsaha) throws DBException {
        try {
            PstContentDataBidangUsaha pstContentDataBidangUsaha = new PstContentDataBidangUsaha(0);
            pstContentDataBidangUsaha.setString(FLD_NAMA_BIDANG_USAHA, entContentDataBidangUsaha.getNamaBidangUsaha());
            pstContentDataBidangUsaha.setString(FLD_KODE_CORE_BANKING, entContentDataBidangUsaha.getKodeCoreBanking());
            pstContentDataBidangUsaha.setString(FLD_KODE_OJK, entContentDataBidangUsaha.getKodeOjk());
            pstContentDataBidangUsaha.insert();
            entContentDataBidangUsaha.setOID(pstContentDataBidangUsaha.getlong(FLD_BIDANG_USAHA_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataBidangUsaha(0), DBException.UNKNOWN);
        }
        return entContentDataBidangUsaha.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataBidangUsaha) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataBidangUsaha entContentDataBidangUsaha) {
        try {
            entContentDataBidangUsaha.setOID(rs.getLong(PstContentDataBidangUsaha.fieldNames[PstContentDataBidangUsaha.FLD_BIDANG_USAHA_OID]));
            entContentDataBidangUsaha.setNamaBidangUsaha(rs.getString(PstContentDataBidangUsaha.fieldNames[PstContentDataBidangUsaha.FLD_NAMA_BIDANG_USAHA]));
            entContentDataBidangUsaha.setKodeCoreBanking(rs.getString(PstContentDataBidangUsaha.fieldNames[PstContentDataBidangUsaha.FLD_KODE_CORE_BANKING]));
            entContentDataBidangUsaha.setKodeOjk(rs.getString(PstContentDataBidangUsaha.fieldNames[PstContentDataBidangUsaha.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWitoutOid(ResultSet rs, ContentDataBidangUsaha entContentDataBidangUsaha) {
        try {
            entContentDataBidangUsaha.setNamaBidangUsaha(rs.getString(PstContentDataBidangUsaha.fieldNames[PstContentDataBidangUsaha.FLD_NAMA_BIDANG_USAHA]));
            entContentDataBidangUsaha.setKodeOjk(rs.getString(PstContentDataBidangUsaha.fieldNames[PstContentDataBidangUsaha.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_BIDANG_USAHA;
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
                ContentDataBidangUsaha entContentDataBidangUsaha = new ContentDataBidangUsaha();
                resultToObject(rs, entContentDataBidangUsaha);
                lists.add(entContentDataBidangUsaha);
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
                    + fieldNames[FLD_NAMA_BIDANG_USAHA]+" "
                    + "FROM " + TBL_CONTENT_DATA_BIDANG_USAHA;
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
                ContentDataBidangUsaha entContentDataBidangUsaha = new ContentDataBidangUsaha();
                resultToObjectWitoutOid(rs, entContentDataBidangUsaha);
                lists.add(entContentDataBidangUsaha);
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

    public static boolean checkOID(long entContentDataBidangUsahaId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_BIDANG_USAHA + " WHERE "
                    + PstContentDataBidangUsaha.fieldNames[PstContentDataBidangUsaha.FLD_BIDANG_USAHA_OID] + " = " + entContentDataBidangUsahaId;
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
            String sql = "SELECT COUNT(" + PstContentDataBidangUsaha.fieldNames[PstContentDataBidangUsaha.FLD_BIDANG_USAHA_OID] + ") FROM " + TBL_CONTENT_DATA_BIDANG_USAHA;
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
                    ContentDataBidangUsaha entContentDataBidangUsaha = (ContentDataBidangUsaha) list.get(ls);
                    if (oid == entContentDataBidangUsaha.getOID()) {
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
