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

public class PstContentDataJenisSuratBerharga extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_JENIS_SURAT_BERHARGA = "dslik_cd_jenis_surat_berharga";
    public static final int FLD_JENIS_SURAT_BERHARGA_OID = 0;
    public static final int FLD_JENIS_SURAT_BERHARGA = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "JENIS_SURAT_BERHARGA_OID",
        "JENIS_SURAT_BERHARGA",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataJenisSuratBerharga() {
    }

    public PstContentDataJenisSuratBerharga(int i) throws DBException {
        super(new PstContentDataJenisSuratBerharga());
    }

    public PstContentDataJenisSuratBerharga(String sOid) throws DBException {
        super(new PstContentDataJenisSuratBerharga(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataJenisSuratBerharga(long lOid) throws DBException {
        super(new PstContentDataJenisSuratBerharga(0));
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
        return TBL_CONTENT_DATA_JENIS_SURAT_BERHARGA;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataJenisSuratBerharga().getClass().getName();
    }

    public static ContentDataJenisSuratBerharga fetchExc(long oid) throws DBException {
        try {
            ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga = new ContentDataJenisSuratBerharga();
            PstContentDataJenisSuratBerharga pstContentDataJenisSuratBerharga = new PstContentDataJenisSuratBerharga(oid);
            entContentDataJenisSuratBerharga.setOID(oid);
            entContentDataJenisSuratBerharga.setJenisSuratBerharga(pstContentDataJenisSuratBerharga.getString(FLD_JENIS_SURAT_BERHARGA));
            entContentDataJenisSuratBerharga.setKodeCoreBanking(pstContentDataJenisSuratBerharga.getString(FLD_KODE_CORE_BANKING));
            entContentDataJenisSuratBerharga.setKodeOjk(pstContentDataJenisSuratBerharga.getString(FLD_KODE_OJK));
            return entContentDataJenisSuratBerharga;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisSuratBerharga(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga = fetchExc(entity.getOID());
        entity = (Entity) entContentDataJenisSuratBerharga;
        return entContentDataJenisSuratBerharga.getOID();
    }

    public static synchronized long updateExc(ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga) throws DBException {
        try {
            if (entContentDataJenisSuratBerharga.getOID() != 0) {
                PstContentDataJenisSuratBerharga pstContentDataJenisSuratBerharga = new PstContentDataJenisSuratBerharga(entContentDataJenisSuratBerharga.getOID());
                pstContentDataJenisSuratBerharga.setString(FLD_JENIS_SURAT_BERHARGA, entContentDataJenisSuratBerharga.getJenisSuratBerharga());
                pstContentDataJenisSuratBerharga.setString(FLD_KODE_CORE_BANKING, entContentDataJenisSuratBerharga.getKodeCoreBanking());
                pstContentDataJenisSuratBerharga.setString(FLD_KODE_OJK, entContentDataJenisSuratBerharga.getKodeOjk());
                pstContentDataJenisSuratBerharga.update();
                return entContentDataJenisSuratBerharga.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisSuratBerharga(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataJenisSuratBerharga) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataJenisSuratBerharga pstContentDataJenisSuratBerharga = new PstContentDataJenisSuratBerharga(oid);
            pstContentDataJenisSuratBerharga.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisSuratBerharga(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga) throws DBException {
        try {
            PstContentDataJenisSuratBerharga pstContentDataJenisSuratBerharga = new PstContentDataJenisSuratBerharga(0);
            pstContentDataJenisSuratBerharga.setString(FLD_JENIS_SURAT_BERHARGA, entContentDataJenisSuratBerharga.getJenisSuratBerharga());
            pstContentDataJenisSuratBerharga.setString(FLD_KODE_CORE_BANKING, entContentDataJenisSuratBerharga.getKodeCoreBanking());
            pstContentDataJenisSuratBerharga.setString(FLD_KODE_OJK, entContentDataJenisSuratBerharga.getKodeOjk());
            pstContentDataJenisSuratBerharga.insert();
            entContentDataJenisSuratBerharga.setOID(pstContentDataJenisSuratBerharga.getlong(FLD_JENIS_SURAT_BERHARGA_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisSuratBerharga(0), DBException.UNKNOWN);
        }
        return entContentDataJenisSuratBerharga.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataJenisSuratBerharga) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga) {
        try {
            entContentDataJenisSuratBerharga.setOID(rs.getLong(PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA_OID]));
            entContentDataJenisSuratBerharga.setJenisSuratBerharga(rs.getString(PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA]));
            entContentDataJenisSuratBerharga.setKodeCoreBanking(rs.getString(PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_KODE_CORE_BANKING]));
            entContentDataJenisSuratBerharga.setKodeOjk(rs.getString(PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga) {
        try {
            entContentDataJenisSuratBerharga.setJenisSuratBerharga(rs.getString(PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA]));
            entContentDataJenisSuratBerharga.setKodeOjk(rs.getString(PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_SURAT_BERHARGA;
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
                ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga = new ContentDataJenisSuratBerharga();
                resultToObject(rs, entContentDataJenisSuratBerharga);
                lists.add(entContentDataJenisSuratBerharga);
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
            String sql = "SELECT DISTINCT "+fieldNames[FLD_JENIS_SURAT_BERHARGA]+","
                    + fieldNames[FLD_KODE_OJK]+" "
                    + "FROM " + TBL_CONTENT_DATA_JENIS_SURAT_BERHARGA;
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
                ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga = new ContentDataJenisSuratBerharga();
                resultToObjectWithoutOid(rs, entContentDataJenisSuratBerharga);
                lists.add(entContentDataJenisSuratBerharga);
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

    public static boolean checkOID(long entContentDataJenisSuratBerhargaId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_SURAT_BERHARGA + " WHERE "
                    + PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA_OID] + " = " + entContentDataJenisSuratBerhargaId;
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
            String sql = "SELECT COUNT(" + PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA_OID] + ") FROM " + TBL_CONTENT_DATA_JENIS_SURAT_BERHARGA;
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
                    ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga = (ContentDataJenisSuratBerharga) list.get(ls);
                    if (oid == entContentDataJenisSuratBerharga.getOID()) {
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
