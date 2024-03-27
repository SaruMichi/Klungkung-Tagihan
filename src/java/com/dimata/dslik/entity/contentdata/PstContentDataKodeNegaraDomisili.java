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
public class PstContentDataKodeNegaraDomisili extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_KODE_NEGARA_DOMISILI = "dslik_cd_kode_negara_domisili";
    public static final int FLD_NEGARA_DOMISILI_OID = 0;
    public static final int FLD_NAMA_NEGARA = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "NEGARA_DOMISILI_OID",
        "NAMA_NEGARA",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataKodeNegaraDomisili() {
    }

    public PstContentDataKodeNegaraDomisili(int i) throws DBException {
        super(new PstContentDataKodeNegaraDomisili());
    }

    public PstContentDataKodeNegaraDomisili(String sOid) throws DBException {
        super(new PstContentDataKodeNegaraDomisili(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataKodeNegaraDomisili(long lOid) throws DBException {
        super(new PstContentDataKodeNegaraDomisili(0));
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
        return TBL_CONTENT_DATA_KODE_NEGARA_DOMISILI;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataKodeNegaraDomisili().getClass().getName();
    }

    public static ContentDataKodeNegaraDomisili fetchExc(long oid) throws DBException {
        try {
            ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili = new ContentDataKodeNegaraDomisili();
            PstContentDataKodeNegaraDomisili pstContentDataKodeNegaraDomisili = new PstContentDataKodeNegaraDomisili(oid);
            entContentDataKodeNegaraDomisili.setOID(oid);
            entContentDataKodeNegaraDomisili.setNamaNegara(pstContentDataKodeNegaraDomisili.getString(FLD_NAMA_NEGARA));
            entContentDataKodeNegaraDomisili.setKodeCoreBanking(pstContentDataKodeNegaraDomisili.getString(FLD_KODE_CORE_BANKING));
            entContentDataKodeNegaraDomisili.setKodeOjk(pstContentDataKodeNegaraDomisili.getString(FLD_KODE_OJK));
            return entContentDataKodeNegaraDomisili;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKodeNegaraDomisili(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili = fetchExc(entity.getOID());
        entity = (Entity) entContentDataKodeNegaraDomisili;
        return entContentDataKodeNegaraDomisili.getOID();
    }

    public static synchronized long updateExc(ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili) throws DBException {
        try {
            if (entContentDataKodeNegaraDomisili.getOID() != 0) {
                PstContentDataKodeNegaraDomisili pstContentDataKodeNegaraDomisili = new PstContentDataKodeNegaraDomisili(entContentDataKodeNegaraDomisili.getOID());
                pstContentDataKodeNegaraDomisili.setString(FLD_NAMA_NEGARA, entContentDataKodeNegaraDomisili.getNamaNegara());
                pstContentDataKodeNegaraDomisili.setString(FLD_KODE_CORE_BANKING, entContentDataKodeNegaraDomisili.getKodeCoreBanking());
                pstContentDataKodeNegaraDomisili.setString(FLD_KODE_OJK, entContentDataKodeNegaraDomisili.getKodeOjk());
                pstContentDataKodeNegaraDomisili.update();
                return entContentDataKodeNegaraDomisili.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKodeNegaraDomisili(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataKodeNegaraDomisili) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataKodeNegaraDomisili pstContentDataKodeNegaraDomisili = new PstContentDataKodeNegaraDomisili(oid);
            pstContentDataKodeNegaraDomisili.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKodeNegaraDomisili(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili) throws DBException {
        try {
            PstContentDataKodeNegaraDomisili pstContentDataKodeNegaraDomisili = new PstContentDataKodeNegaraDomisili(0);
            pstContentDataKodeNegaraDomisili.setString(FLD_NAMA_NEGARA, entContentDataKodeNegaraDomisili.getNamaNegara());
            pstContentDataKodeNegaraDomisili.setString(FLD_KODE_CORE_BANKING, entContentDataKodeNegaraDomisili.getKodeCoreBanking());
            pstContentDataKodeNegaraDomisili.setString(FLD_KODE_OJK, entContentDataKodeNegaraDomisili.getKodeOjk());
            pstContentDataKodeNegaraDomisili.insert();
            entContentDataKodeNegaraDomisili.setOID(pstContentDataKodeNegaraDomisili.getlong(FLD_NEGARA_DOMISILI_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKodeNegaraDomisili(0), DBException.UNKNOWN);
        }
        return entContentDataKodeNegaraDomisili.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataKodeNegaraDomisili) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili) {
        try {
            entContentDataKodeNegaraDomisili.setOID(rs.getLong(PstContentDataKodeNegaraDomisili.fieldNames[PstContentDataKodeNegaraDomisili.FLD_NEGARA_DOMISILI_OID]));
            entContentDataKodeNegaraDomisili.setNamaNegara(rs.getString(PstContentDataKodeNegaraDomisili.fieldNames[PstContentDataKodeNegaraDomisili.FLD_NAMA_NEGARA]));
            entContentDataKodeNegaraDomisili.setKodeCoreBanking(rs.getString(PstContentDataKodeNegaraDomisili.fieldNames[PstContentDataKodeNegaraDomisili.FLD_KODE_CORE_BANKING]));
            entContentDataKodeNegaraDomisili.setKodeOjk(rs.getString(PstContentDataKodeNegaraDomisili.fieldNames[PstContentDataKodeNegaraDomisili.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili) {
        try {
            entContentDataKodeNegaraDomisili.setNamaNegara(rs.getString(PstContentDataKodeNegaraDomisili.fieldNames[PstContentDataKodeNegaraDomisili.FLD_NAMA_NEGARA]));
            entContentDataKodeNegaraDomisili.setKodeOjk(rs.getString(PstContentDataKodeNegaraDomisili.fieldNames[PstContentDataKodeNegaraDomisili.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_KODE_NEGARA_DOMISILI;
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
                ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili = new ContentDataKodeNegaraDomisili();
                resultToObject(rs, entContentDataKodeNegaraDomisili);
                lists.add(entContentDataKodeNegaraDomisili);
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
                    + fieldNames[FLD_NAMA_NEGARA]+" FROM " + TBL_CONTENT_DATA_KODE_NEGARA_DOMISILI;
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
                ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili = new ContentDataKodeNegaraDomisili();
                resultToObjectWithoutOid(rs, entContentDataKodeNegaraDomisili);
                lists.add(entContentDataKodeNegaraDomisili);
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

    public static boolean checkOID(long entContentDataKodeNegaraDomisiliId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_KODE_NEGARA_DOMISILI + " WHERE "
                    + PstContentDataKodeNegaraDomisili.fieldNames[PstContentDataKodeNegaraDomisili.FLD_NEGARA_DOMISILI_OID] + " = " + entContentDataKodeNegaraDomisiliId;
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
            String sql = "SELECT COUNT(" + PstContentDataKodeNegaraDomisili.fieldNames[PstContentDataKodeNegaraDomisili.FLD_NEGARA_DOMISILI_OID] + ") FROM " + TBL_CONTENT_DATA_KODE_NEGARA_DOMISILI;
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
                    ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili = (ContentDataKodeNegaraDomisili) list.get(ls);
                    if (oid == entContentDataKodeNegaraDomisili.getOID()) {
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
