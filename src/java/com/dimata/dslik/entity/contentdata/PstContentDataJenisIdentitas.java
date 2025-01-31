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

public class PstContentDataJenisIdentitas extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_JENIS_IDENTITAS = "dslik_cd_jenis_identitas";
    public static final int FLD_JENIS_IDENTITAS_OID = 0;
    public static final int FLD_NAMA_IDENTITAS = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "JENIS_IDENTITAS_OID",
        "NAMA_IDENTITAS",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataJenisIdentitas() {
    }

    public PstContentDataJenisIdentitas(int i) throws DBException {
        super(new PstContentDataJenisIdentitas());
    }

    public PstContentDataJenisIdentitas(String sOid) throws DBException {
        super(new PstContentDataJenisIdentitas(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataJenisIdentitas(long lOid) throws DBException {
        super(new PstContentDataJenisIdentitas(0));
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
        return TBL_CONTENT_DATA_JENIS_IDENTITAS;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataJenisIdentitas().getClass().getName();
    }

    public static ContentDataJenisIdentitas fetchExc(long oid) throws DBException {
        try {
            ContentDataJenisIdentitas entContentDataJenisIdentitas = new ContentDataJenisIdentitas();
            PstContentDataJenisIdentitas pstContentDataJenisIdentitas = new PstContentDataJenisIdentitas(oid);
            entContentDataJenisIdentitas.setOID(oid);
            entContentDataJenisIdentitas.setNamaIdentitas(pstContentDataJenisIdentitas.getString(FLD_NAMA_IDENTITAS));
            entContentDataJenisIdentitas.setKodeCoreBanking(pstContentDataJenisIdentitas.getString(FLD_KODE_CORE_BANKING));
            entContentDataJenisIdentitas.setKodeOjk(pstContentDataJenisIdentitas.getString(FLD_KODE_OJK));
            return entContentDataJenisIdentitas;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisIdentitas(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataJenisIdentitas entContentDataJenisIdentitas = fetchExc(entity.getOID());
        entity = (Entity) entContentDataJenisIdentitas;
        return entContentDataJenisIdentitas.getOID();
    }

    public static synchronized long updateExc(ContentDataJenisIdentitas entContentDataJenisIdentitas) throws DBException {
        try {
            if (entContentDataJenisIdentitas.getOID() != 0) {
                PstContentDataJenisIdentitas pstContentDataJenisIdentitas = new PstContentDataJenisIdentitas(entContentDataJenisIdentitas.getOID());
                pstContentDataJenisIdentitas.setString(FLD_NAMA_IDENTITAS, entContentDataJenisIdentitas.getNamaIdentitas());
                pstContentDataJenisIdentitas.setString(FLD_KODE_CORE_BANKING, entContentDataJenisIdentitas.getKodeCoreBanking());
                pstContentDataJenisIdentitas.setString(FLD_KODE_OJK, entContentDataJenisIdentitas.getKodeOjk());
                pstContentDataJenisIdentitas.update();
                return entContentDataJenisIdentitas.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisIdentitas(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataJenisIdentitas) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataJenisIdentitas pstContentDataJenisIdentitas = new PstContentDataJenisIdentitas(oid);
            pstContentDataJenisIdentitas.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisIdentitas(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataJenisIdentitas entContentDataJenisIdentitas) throws DBException {
        try {
            PstContentDataJenisIdentitas pstContentDataJenisIdentitas = new PstContentDataJenisIdentitas(0);
            pstContentDataJenisIdentitas.setString(FLD_NAMA_IDENTITAS, entContentDataJenisIdentitas.getNamaIdentitas());
            pstContentDataJenisIdentitas.setString(FLD_KODE_CORE_BANKING, entContentDataJenisIdentitas.getKodeCoreBanking());
            pstContentDataJenisIdentitas.setString(FLD_KODE_OJK, entContentDataJenisIdentitas.getKodeOjk());
            pstContentDataJenisIdentitas.insert();
            entContentDataJenisIdentitas.setOID(pstContentDataJenisIdentitas.getlong(FLD_JENIS_IDENTITAS_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisIdentitas(0), DBException.UNKNOWN);
        }
        return entContentDataJenisIdentitas.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataJenisIdentitas) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataJenisIdentitas entContentDataJenisIdentitas) {
        try {
            entContentDataJenisIdentitas.setOID(rs.getLong(PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_JENIS_IDENTITAS_OID]));
            entContentDataJenisIdentitas.setNamaIdentitas(rs.getString(PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_NAMA_IDENTITAS]));
            entContentDataJenisIdentitas.setKodeCoreBanking(rs.getString(PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_KODE_CORE_BANKING]));
            entContentDataJenisIdentitas.setKodeOjk(rs.getString(PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataJenisIdentitas entContentDataJenisIdentitas) {
        try {
            entContentDataJenisIdentitas.setNamaIdentitas(rs.getString(PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_NAMA_IDENTITAS]));
            entContentDataJenisIdentitas.setKodeOjk(rs.getString(PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_IDENTITAS;
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
                ContentDataJenisIdentitas entContentDataJenisIdentitas = new ContentDataJenisIdentitas();
                resultToObject(rs, entContentDataJenisIdentitas);
                lists.add(entContentDataJenisIdentitas);
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
            String sql = "SELECT DISTINCT "+fieldNames[FLD_NAMA_IDENTITAS]+","
                    + fieldNames[FLD_KODE_OJK]+" "
                    + "FROM " + TBL_CONTENT_DATA_JENIS_IDENTITAS;
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
                ContentDataJenisIdentitas entContentDataJenisIdentitas = new ContentDataJenisIdentitas();
                resultToObjectWithoutOid(rs, entContentDataJenisIdentitas);
                lists.add(entContentDataJenisIdentitas);
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

    public static boolean checkOID(long entContentDataJenisIdentitasId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_IDENTITAS + " WHERE "
                    + PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_JENIS_IDENTITAS_OID] + " = " + entContentDataJenisIdentitasId;
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
            String sql = "SELECT COUNT(" + PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_JENIS_IDENTITAS_OID] + ") FROM " + TBL_CONTENT_DATA_JENIS_IDENTITAS;
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
                    ContentDataJenisIdentitas entContentDataJenisIdentitas = (ContentDataJenisIdentitas) list.get(ls);
                    if (oid == entContentDataJenisIdentitas.getOID()) {
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
