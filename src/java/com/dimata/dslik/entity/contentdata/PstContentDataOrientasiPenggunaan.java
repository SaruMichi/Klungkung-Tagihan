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
public class PstContentDataOrientasiPenggunaan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_ORIENTASI_PENGGUNAAN = "dslik_cd_orientasi_penggunaan";
    public static final int FLD_ORIENTASI_PENGGUNAAN_OID = 0;
    public static final int FLD_NAMA_ORIENTASI_PENGGUNAAN = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "ORIENTASI_PENGGUNAAN_OID",
        "NAMA_ORIENTASI_PENGGUNAAN",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataOrientasiPenggunaan() {
    }

    public PstContentDataOrientasiPenggunaan(int i) throws DBException {
        super(new PstContentDataOrientasiPenggunaan());
    }

    public PstContentDataOrientasiPenggunaan(String sOid) throws DBException {
        super(new PstContentDataOrientasiPenggunaan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataOrientasiPenggunaan(long lOid) throws DBException {
        super(new PstContentDataOrientasiPenggunaan(0));
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
        return TBL_CONTENT_DATA_ORIENTASI_PENGGUNAAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataOrientasiPenggunaan().getClass().getName();
    }

    public static ContentDataOrientasiPenggunaan fetchExc(long oid) throws DBException {
        try {
            ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan = new ContentDataOrientasiPenggunaan();
            PstContentDataOrientasiPenggunaan pstContentDataOrientasiPenggunaan = new PstContentDataOrientasiPenggunaan(oid);
            entContentDataOrientasiPenggunaan.setOID(oid);
            entContentDataOrientasiPenggunaan.setNamaOrientasiPenggunaan(pstContentDataOrientasiPenggunaan.getString(FLD_NAMA_ORIENTASI_PENGGUNAAN));
            entContentDataOrientasiPenggunaan.setKodeCoreBanking(pstContentDataOrientasiPenggunaan.getString(FLD_KODE_CORE_BANKING));
            entContentDataOrientasiPenggunaan.setKodeOjk(pstContentDataOrientasiPenggunaan.getString(FLD_KODE_OJK));
            return entContentDataOrientasiPenggunaan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataOrientasiPenggunaan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan = fetchExc(entity.getOID());
        entity = (Entity) entContentDataOrientasiPenggunaan;
        return entContentDataOrientasiPenggunaan.getOID();
    }

    public static synchronized long updateExc(ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan) throws DBException {
        try {
            if (entContentDataOrientasiPenggunaan.getOID() != 0) {
                PstContentDataOrientasiPenggunaan pstContentDataOrientasiPenggunaan = new PstContentDataOrientasiPenggunaan(entContentDataOrientasiPenggunaan.getOID());
                pstContentDataOrientasiPenggunaan.setString(FLD_NAMA_ORIENTASI_PENGGUNAAN, entContentDataOrientasiPenggunaan.getNamaOrientasiPenggunaan());
                pstContentDataOrientasiPenggunaan.setString(FLD_KODE_CORE_BANKING, entContentDataOrientasiPenggunaan.getKodeCoreBanking());
                pstContentDataOrientasiPenggunaan.setString(FLD_KODE_OJK, entContentDataOrientasiPenggunaan.getKodeOjk());
                pstContentDataOrientasiPenggunaan.update();
                return entContentDataOrientasiPenggunaan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataOrientasiPenggunaan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataOrientasiPenggunaan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataOrientasiPenggunaan pstContentDataOrientasiPenggunaan = new PstContentDataOrientasiPenggunaan(oid);
            pstContentDataOrientasiPenggunaan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataOrientasiPenggunaan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan) throws DBException {
        try {
            PstContentDataOrientasiPenggunaan pstContentDataOrientasiPenggunaan = new PstContentDataOrientasiPenggunaan(0);
            pstContentDataOrientasiPenggunaan.setString(FLD_NAMA_ORIENTASI_PENGGUNAAN, entContentDataOrientasiPenggunaan.getNamaOrientasiPenggunaan());
            pstContentDataOrientasiPenggunaan.setString(FLD_KODE_CORE_BANKING, entContentDataOrientasiPenggunaan.getKodeCoreBanking());
            pstContentDataOrientasiPenggunaan.setString(FLD_KODE_OJK, entContentDataOrientasiPenggunaan.getKodeOjk());
            pstContentDataOrientasiPenggunaan.insert();
            entContentDataOrientasiPenggunaan.setOID(pstContentDataOrientasiPenggunaan.getlong(FLD_ORIENTASI_PENGGUNAAN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataOrientasiPenggunaan(0), DBException.UNKNOWN);
        }
        return entContentDataOrientasiPenggunaan.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataOrientasiPenggunaan) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan) {
        try {
            entContentDataOrientasiPenggunaan.setOID(rs.getLong(PstContentDataOrientasiPenggunaan.fieldNames[PstContentDataOrientasiPenggunaan.FLD_ORIENTASI_PENGGUNAAN_OID]));
            entContentDataOrientasiPenggunaan.setNamaOrientasiPenggunaan(rs.getString(PstContentDataOrientasiPenggunaan.fieldNames[PstContentDataOrientasiPenggunaan.FLD_NAMA_ORIENTASI_PENGGUNAAN]));
            entContentDataOrientasiPenggunaan.setKodeCoreBanking(rs.getString(PstContentDataOrientasiPenggunaan.fieldNames[PstContentDataOrientasiPenggunaan.FLD_KODE_CORE_BANKING]));
            entContentDataOrientasiPenggunaan.setKodeOjk(rs.getString(PstContentDataOrientasiPenggunaan.fieldNames[PstContentDataOrientasiPenggunaan.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan) {
        try {
            entContentDataOrientasiPenggunaan.setNamaOrientasiPenggunaan(rs.getString(PstContentDataOrientasiPenggunaan.fieldNames[PstContentDataOrientasiPenggunaan.FLD_NAMA_ORIENTASI_PENGGUNAAN]));
            entContentDataOrientasiPenggunaan.setKodeOjk(rs.getString(PstContentDataOrientasiPenggunaan.fieldNames[PstContentDataOrientasiPenggunaan.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_ORIENTASI_PENGGUNAAN;
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
                ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan = new ContentDataOrientasiPenggunaan();
                resultToObject(rs, entContentDataOrientasiPenggunaan);
                lists.add(entContentDataOrientasiPenggunaan);
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
                    + fieldNames[FLD_NAMA_ORIENTASI_PENGGUNAAN]+" "
                    + "FROM " + TBL_CONTENT_DATA_ORIENTASI_PENGGUNAAN;
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
                ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan = new ContentDataOrientasiPenggunaan();
                resultToObjectWithoutOid(rs, entContentDataOrientasiPenggunaan);
                lists.add(entContentDataOrientasiPenggunaan);
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

    public static boolean checkOID(long entContentDataOrientasiPenggunaanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_ORIENTASI_PENGGUNAAN + " WHERE "
                    + PstContentDataOrientasiPenggunaan.fieldNames[PstContentDataOrientasiPenggunaan.FLD_ORIENTASI_PENGGUNAAN_OID] + " = " + entContentDataOrientasiPenggunaanId;
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
            String sql = "SELECT COUNT(" + PstContentDataOrientasiPenggunaan.fieldNames[PstContentDataOrientasiPenggunaan.FLD_ORIENTASI_PENGGUNAAN_OID] + ") FROM " + TBL_CONTENT_DATA_ORIENTASI_PENGGUNAAN;
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
                    ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan = (ContentDataOrientasiPenggunaan) list.get(ls);
                    if (oid == entContentDataOrientasiPenggunaan.getOID()) {
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
