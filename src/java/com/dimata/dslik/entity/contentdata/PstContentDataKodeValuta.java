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
public class PstContentDataKodeValuta extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_KODE_VALUTA = "dslik_cd_kode_valuta";
    public static final int FLD_VALUTA_OID = 0;
    public static final int FLD_NAMA_VALUTA = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "VALUTA_OID",
        "NAMA_VALUTA",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataKodeValuta() {
    }

    public PstContentDataKodeValuta(int i) throws DBException {
        super(new PstContentDataKodeValuta());
    }

    public PstContentDataKodeValuta(String sOid) throws DBException {
        super(new PstContentDataKodeValuta(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataKodeValuta(long lOid) throws DBException {
        super(new PstContentDataKodeValuta(0));
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
        return TBL_CONTENT_DATA_KODE_VALUTA;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataKodeValuta().getClass().getName();
    }

    public static ContentDataKodeValuta fetchExc(long oid) throws DBException {
        try {
            ContentDataKodeValuta entContentDataKodeValuta = new ContentDataKodeValuta();
            PstContentDataKodeValuta pstContentDataKodeValuta = new PstContentDataKodeValuta(oid);
            entContentDataKodeValuta.setOID(oid);
            entContentDataKodeValuta.setNamaValuta(pstContentDataKodeValuta.getString(FLD_NAMA_VALUTA));
            entContentDataKodeValuta.setKodeCoreBanking(pstContentDataKodeValuta.getString(FLD_KODE_CORE_BANKING));
            entContentDataKodeValuta.setKodeOjk(pstContentDataKodeValuta.getString(FLD_KODE_OJK));
            return entContentDataKodeValuta;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKodeValuta(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataKodeValuta entContentDataKodeValuta = fetchExc(entity.getOID());
        entity = (Entity) entContentDataKodeValuta;
        return entContentDataKodeValuta.getOID();
    }

    public static synchronized long updateExc(ContentDataKodeValuta entContentDataKodeValuta) throws DBException {
        try {
            if (entContentDataKodeValuta.getOID() != 0) {
                PstContentDataKodeValuta pstContentDataKodeValuta = new PstContentDataKodeValuta(entContentDataKodeValuta.getOID());
                pstContentDataKodeValuta.setString(FLD_NAMA_VALUTA, entContentDataKodeValuta.getNamaValuta());
                pstContentDataKodeValuta.setString(FLD_KODE_CORE_BANKING, entContentDataKodeValuta.getKodeCoreBanking());
                pstContentDataKodeValuta.setString(FLD_KODE_OJK, entContentDataKodeValuta.getKodeOjk());
                pstContentDataKodeValuta.update();
                return entContentDataKodeValuta.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKodeValuta(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataKodeValuta) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataKodeValuta pstContentDataKodeValuta = new PstContentDataKodeValuta(oid);
            pstContentDataKodeValuta.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKodeValuta(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataKodeValuta entContentDataKodeValuta) throws DBException {
        try {
            PstContentDataKodeValuta pstContentDataKodeValuta = new PstContentDataKodeValuta(0);
            pstContentDataKodeValuta.setString(FLD_NAMA_VALUTA, entContentDataKodeValuta.getNamaValuta());
            pstContentDataKodeValuta.setString(FLD_KODE_CORE_BANKING, entContentDataKodeValuta.getKodeCoreBanking());
            pstContentDataKodeValuta.setString(FLD_KODE_OJK, entContentDataKodeValuta.getKodeOjk());
            pstContentDataKodeValuta.insert();
            entContentDataKodeValuta.setOID(pstContentDataKodeValuta.getlong(FLD_VALUTA_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKodeValuta(0), DBException.UNKNOWN);
        }
        return entContentDataKodeValuta.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataKodeValuta) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataKodeValuta entContentDataKodeValuta) {
        try {
            entContentDataKodeValuta.setOID(rs.getLong(PstContentDataKodeValuta.fieldNames[PstContentDataKodeValuta.FLD_VALUTA_OID]));
            entContentDataKodeValuta.setNamaValuta(rs.getString(PstContentDataKodeValuta.fieldNames[PstContentDataKodeValuta.FLD_NAMA_VALUTA]));
            entContentDataKodeValuta.setKodeCoreBanking(rs.getString(PstContentDataKodeValuta.fieldNames[PstContentDataKodeValuta.FLD_KODE_CORE_BANKING]));
            entContentDataKodeValuta.setKodeOjk(rs.getString(PstContentDataKodeValuta.fieldNames[PstContentDataKodeValuta.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataKodeValuta entContentDataKodeValuta) {
        try {
            entContentDataKodeValuta.setNamaValuta(rs.getString(PstContentDataKodeValuta.fieldNames[PstContentDataKodeValuta.FLD_NAMA_VALUTA]));
            entContentDataKodeValuta.setKodeOjk(rs.getString(PstContentDataKodeValuta.fieldNames[PstContentDataKodeValuta.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_KODE_VALUTA;
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
                ContentDataKodeValuta entContentDataKodeValuta = new ContentDataKodeValuta();
                resultToObject(rs, entContentDataKodeValuta);
                lists.add(entContentDataKodeValuta);
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
    
    
    public static Vector listAngka(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM dslik_cd_kode_valuta_angka ";
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
                ContentDataKodeValuta entContentDataKodeValuta = new ContentDataKodeValuta();
                resultToObject(rs, entContentDataKodeValuta);
                lists.add(entContentDataKodeValuta);
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
                    + fieldNames[FLD_NAMA_VALUTA]+" "
                    + "FROM " + TBL_CONTENT_DATA_KODE_VALUTA;
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
                ContentDataKodeValuta entContentDataKodeValuta = new ContentDataKodeValuta();
                resultToObjectWithoutOid(rs, entContentDataKodeValuta);
                lists.add(entContentDataKodeValuta);
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

    public static boolean checkOID(long entContentDataKodeValutaId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_KODE_VALUTA + " WHERE "
                    + PstContentDataKodeValuta.fieldNames[PstContentDataKodeValuta.FLD_VALUTA_OID] + " = " + entContentDataKodeValutaId;
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
            String sql = "SELECT COUNT(" + PstContentDataKodeValuta.fieldNames[PstContentDataKodeValuta.FLD_VALUTA_OID] + ") FROM " + TBL_CONTENT_DATA_KODE_VALUTA;
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
                    ContentDataKodeValuta entContentDataKodeValuta = (ContentDataKodeValuta) list.get(ls);
                    if (oid == entContentDataKodeValuta.getOID()) {
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
