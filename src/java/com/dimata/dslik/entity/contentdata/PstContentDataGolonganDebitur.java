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

public class PstContentDataGolonganDebitur extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_GOLONGAN_DEBITUR= "dslik_cd_golongan_debitur";
    public static final int FLD_GOLONGAN_DEBITUR_OID = 0;
    public static final int FLD_GOLONGAN_DEBITUR = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "GOLONGAN_DEBITUR_OID",
        "GOLONGAN_DEBITUR",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataGolonganDebitur() {
    }

    public PstContentDataGolonganDebitur(int i) throws DBException {
        super(new PstContentDataGolonganDebitur());
    }

    public PstContentDataGolonganDebitur(String sOid) throws DBException {
        super(new PstContentDataGolonganDebitur(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataGolonganDebitur(long lOid) throws DBException {
        super(new PstContentDataGolonganDebitur(0));
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
        return TBL_CONTENT_DATA_GOLONGAN_DEBITUR;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataGolonganDebitur().getClass().getName();
    }

    public static ContentDataGolonganDebitur fetchExc(long oid) throws DBException {
        try {
            ContentDataGolonganDebitur entContentDataGolonganDebitur = new ContentDataGolonganDebitur();
            PstContentDataGolonganDebitur pstContentDataGolonganDebitur = new PstContentDataGolonganDebitur(oid);
            entContentDataGolonganDebitur.setOID(oid);
            entContentDataGolonganDebitur.setGolonganDebitur(pstContentDataGolonganDebitur.getString(FLD_GOLONGAN_DEBITUR));
            entContentDataGolonganDebitur.setKodeCoreBanking(pstContentDataGolonganDebitur.getString(FLD_KODE_CORE_BANKING));
            entContentDataGolonganDebitur.setKodeOjk(pstContentDataGolonganDebitur.getString(FLD_KODE_OJK));
            return entContentDataGolonganDebitur;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataGolonganDebitur(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataGolonganDebitur entContentDataGolonganDebitur = fetchExc(entity.getOID());
        entity = (Entity) entContentDataGolonganDebitur;
        return entContentDataGolonganDebitur.getOID();
    }

    public static synchronized long updateExc(ContentDataGolonganDebitur entContentDataGolonganDebitur) throws DBException {
        try {
            if (entContentDataGolonganDebitur.getOID() != 0) {
                PstContentDataGolonganDebitur pstContentDataGolonganDebitur = new PstContentDataGolonganDebitur(entContentDataGolonganDebitur.getOID());
                pstContentDataGolonganDebitur.setString(FLD_GOLONGAN_DEBITUR, entContentDataGolonganDebitur.getGolonganDebitur());
                pstContentDataGolonganDebitur.setString(FLD_KODE_CORE_BANKING, entContentDataGolonganDebitur.getKodeCoreBanking());
                pstContentDataGolonganDebitur.setString(FLD_KODE_OJK, entContentDataGolonganDebitur.getKodeOjk());
                pstContentDataGolonganDebitur.update();
                return entContentDataGolonganDebitur.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataGolonganDebitur(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataGolonganDebitur) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataGolonganDebitur pstContentDataGolonganDebitur = new PstContentDataGolonganDebitur(oid);
            pstContentDataGolonganDebitur.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataGolonganDebitur(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataGolonganDebitur entContentDataGolonganDebitur) throws DBException {
        try {
            PstContentDataGolonganDebitur pstContentDataGolonganDebitur = new PstContentDataGolonganDebitur(0);
            pstContentDataGolonganDebitur.setString(FLD_GOLONGAN_DEBITUR, entContentDataGolonganDebitur.getGolonganDebitur());
            pstContentDataGolonganDebitur.setString(FLD_KODE_CORE_BANKING, entContentDataGolonganDebitur.getKodeCoreBanking());
            pstContentDataGolonganDebitur.setString(FLD_KODE_OJK, entContentDataGolonganDebitur.getKodeOjk());
            pstContentDataGolonganDebitur.insert();
            entContentDataGolonganDebitur.setOID(pstContentDataGolonganDebitur.getlong(FLD_GOLONGAN_DEBITUR_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataGolonganDebitur(0), DBException.UNKNOWN);
        }
        return entContentDataGolonganDebitur.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataGolonganDebitur) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataGolonganDebitur entContentDataGolonganDebitur) {
        try {
            entContentDataGolonganDebitur.setOID(rs.getLong(PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_GOLONGAN_DEBITUR_OID]));
            entContentDataGolonganDebitur.setGolonganDebitur(rs.getString(PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_GOLONGAN_DEBITUR]));
            entContentDataGolonganDebitur.setKodeCoreBanking(rs.getString(PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_KODE_CORE_BANKING]));
            entContentDataGolonganDebitur.setKodeOjk(rs.getString(PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataGolonganDebitur entContentDataGolonganDebitur) {
        try {
            entContentDataGolonganDebitur.setGolonganDebitur(rs.getString(PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_GOLONGAN_DEBITUR]));
            entContentDataGolonganDebitur.setKodeOjk(rs.getString(PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_GOLONGAN_DEBITUR;
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
                ContentDataGolonganDebitur entContentDataGolonganDebitur = new ContentDataGolonganDebitur();
                resultToObject(rs, entContentDataGolonganDebitur);
                lists.add(entContentDataGolonganDebitur);
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
            String sql = "SELECT DISTINCT "+fieldNames[FLD_GOLONGAN_DEBITUR]+","
                    + fieldNames[FLD_KODE_OJK]+" "
                    + "FROM " + TBL_CONTENT_DATA_GOLONGAN_DEBITUR;
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
                ContentDataGolonganDebitur entContentDataGolonganDebitur = new ContentDataGolonganDebitur();
                resultToObjectWithoutOid(rs, entContentDataGolonganDebitur);
                lists.add(entContentDataGolonganDebitur);
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

    public static boolean checkOID(long entContentDataGolonganDebiturId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_GOLONGAN_DEBITUR+ " WHERE "
                    + PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_GOLONGAN_DEBITUR_OID] + " = " + entContentDataGolonganDebiturId;
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
            String sql = "SELECT COUNT(" + PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_GOLONGAN_DEBITUR_OID] + ") FROM " + TBL_CONTENT_DATA_GOLONGAN_DEBITUR;
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
                    ContentDataGolonganDebitur entContentDataGolonganDebitur = (ContentDataGolonganDebitur) list.get(ls);
                    if (oid == entContentDataGolonganDebitur.getOID()) {
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
