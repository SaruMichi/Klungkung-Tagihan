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

public class PstContentDataJenisKredit extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_JENIS_KREDIT = "dslik_cd_jenis_kredit";
    public static final int FLD_JENIS_KREDIT_OID = 0;
    public static final int FLD_NAMA_JENIS_KREDIT = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "JENIS_KREDIT_OID",
        "NAMA_JENIS_KREDIT",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataJenisKredit() {
    }

    public PstContentDataJenisKredit(int i) throws DBException {
        super(new PstContentDataJenisKredit());
    }

    public PstContentDataJenisKredit(String sOid) throws DBException {
        super(new PstContentDataJenisKredit(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataJenisKredit(long lOid) throws DBException {
        super(new PstContentDataJenisKredit(0));
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
        return TBL_CONTENT_DATA_JENIS_KREDIT;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataJenisKredit().getClass().getName();
    }

    public static ContentDataJenisKredit fetchExc(long oid) throws DBException {
        try {
            ContentDataJenisKredit entContentDataJenisKredit = new ContentDataJenisKredit();
            PstContentDataJenisKredit pstContentDataJenisKredit = new PstContentDataJenisKredit(oid);
            entContentDataJenisKredit.setOID(oid);
            entContentDataJenisKredit.setNamaJenisKredit(pstContentDataJenisKredit.getString(FLD_NAMA_JENIS_KREDIT));
            entContentDataJenisKredit.setKodeCoreBanking(pstContentDataJenisKredit.getString(FLD_KODE_CORE_BANKING));
            entContentDataJenisKredit.setKodeOjk(pstContentDataJenisKredit.getString(FLD_KODE_OJK));
            return entContentDataJenisKredit;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisKredit(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataJenisKredit entContentDataJenisKredit = fetchExc(entity.getOID());
        entity = (Entity) entContentDataJenisKredit;
        return entContentDataJenisKredit.getOID();
    }

    public static synchronized long updateExc(ContentDataJenisKredit entContentDataJenisKredit) throws DBException {
        try {
            if (entContentDataJenisKredit.getOID() != 0) {
                PstContentDataJenisKredit pstContentDataJenisKredit = new PstContentDataJenisKredit(entContentDataJenisKredit.getOID());
                pstContentDataJenisKredit.setString(FLD_NAMA_JENIS_KREDIT, entContentDataJenisKredit.getNamaJenisKredit());
                pstContentDataJenisKredit.setString(FLD_KODE_CORE_BANKING, entContentDataJenisKredit.getKodeCoreBanking());
                pstContentDataJenisKredit.setString(FLD_KODE_OJK, entContentDataJenisKredit.getKodeOjk());
                pstContentDataJenisKredit.update();
                return entContentDataJenisKredit.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisKredit(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataJenisKredit) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataJenisKredit pstContentDataJenisKredit = new PstContentDataJenisKredit(oid);
            pstContentDataJenisKredit.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisKredit(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataJenisKredit entContentDataJenisKredit) throws DBException {
        try {
            PstContentDataJenisKredit pstContentDataJenisKredit = new PstContentDataJenisKredit(0);
            pstContentDataJenisKredit.setString(FLD_NAMA_JENIS_KREDIT, entContentDataJenisKredit.getNamaJenisKredit());
            pstContentDataJenisKredit.setString(FLD_KODE_CORE_BANKING, entContentDataJenisKredit.getKodeCoreBanking());
            pstContentDataJenisKredit.setString(FLD_KODE_OJK, entContentDataJenisKredit.getKodeOjk());
            pstContentDataJenisKredit.insert();
            entContentDataJenisKredit.setOID(pstContentDataJenisKredit.getlong(FLD_JENIS_KREDIT_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisKredit(0), DBException.UNKNOWN);
        }
        return entContentDataJenisKredit.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataJenisKredit) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataJenisKredit entContentDataJenisKredit) {
        try {
            entContentDataJenisKredit.setOID(rs.getLong(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_JENIS_KREDIT_OID]));
            entContentDataJenisKredit.setNamaJenisKredit(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]));
            entContentDataJenisKredit.setKodeCoreBanking(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_KODE_CORE_BANKING]));
            entContentDataJenisKredit.setKodeOjk(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjecttanpaoid(ResultSet rs, ContentDataJenisKredit entContentDataJenisKredit) {
        try {
            entContentDataJenisKredit.setNamaJenisKredit(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]));
            entContentDataJenisKredit.setKodeOjk(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataJenisKredit entContentDataJenisKredit) {
        try {
            entContentDataJenisKredit.setNamaJenisKredit(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]));
            entContentDataJenisKredit.setKodeOjk(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_KREDIT;
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
                ContentDataJenisKredit entContentDataJenisKredit = new ContentDataJenisKredit();
                resultToObject(rs, entContentDataJenisKredit);
                lists.add(entContentDataJenisKredit);
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
                    + fieldNames[FLD_NAMA_JENIS_KREDIT]+" "
                    + "FROM " + TBL_CONTENT_DATA_JENIS_KREDIT;
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
                ContentDataJenisKredit entContentDataJenisKredit = new ContentDataJenisKredit();
                resultToObjectWithoutOid(rs, entContentDataJenisKredit);
                lists.add(entContentDataJenisKredit);
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
            String sql = "SELECT DISTINCT NAMA_JENIS_KREDIT, KODE_OJK  FROM " + TBL_CONTENT_DATA_JENIS_KREDIT;
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
                ContentDataJenisKredit entContentDataJenisKredit = new ContentDataJenisKredit();
                resultToObjecttanpaoid(rs, entContentDataJenisKredit);
                lists.add(entContentDataJenisKredit);
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

    public static boolean checkOID(long entContentDataJenisKreditId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_KREDIT + " WHERE "
                    + PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_JENIS_KREDIT_OID] + " = " + entContentDataJenisKreditId;
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
            String sql = "SELECT COUNT(" + PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_JENIS_KREDIT_OID] + ") FROM " + TBL_CONTENT_DATA_JENIS_KREDIT;
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
                    ContentDataJenisKredit entContentDataJenisKredit = (ContentDataJenisKredit) list.get(ls);
                    if (oid == entContentDataJenisKredit.getOID()) {
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
