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

public class PstContentDataJabatan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_JABATAN = "dslik_cd_jabatan";
    public static final int FLD_JABATAN_OID = 0;
    public static final int FLD_NAMA_JABATAN = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "JABATAN_OID",
        "NAMA_JABATAN",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataJabatan() {
    }

    public PstContentDataJabatan(int i) throws DBException {
        super(new PstContentDataJabatan());
    }

    public PstContentDataJabatan(String sOid) throws DBException {
        super(new PstContentDataJabatan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataJabatan(long lOid) throws DBException {
        super(new PstContentDataJabatan(0));
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
        return TBL_CONTENT_DATA_JABATAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataJabatan().getClass().getName();
    }

    public static ContentDataJabatan fetchExc(long oid) throws DBException {
        try {
            ContentDataJabatan entContentDataJabatan = new ContentDataJabatan();
            PstContentDataJabatan pstContentDataJabatan = new PstContentDataJabatan(oid);
            entContentDataJabatan.setOID(oid);
            entContentDataJabatan.setNamaJabatan(pstContentDataJabatan.getString(FLD_NAMA_JABATAN));
            entContentDataJabatan.setKodeCoreBanking(pstContentDataJabatan.getString(FLD_KODE_CORE_BANKING));
            entContentDataJabatan.setKodeOjk(pstContentDataJabatan.getString(FLD_KODE_OJK));
            return entContentDataJabatan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJabatan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataJabatan entContentDataJabatan = fetchExc(entity.getOID());
        entity = (Entity) entContentDataJabatan;
        return entContentDataJabatan.getOID();
    }

    public static synchronized long updateExc(ContentDataJabatan entContentDataJabatan) throws DBException {
        try {
            if (entContentDataJabatan.getOID() != 0) {
                PstContentDataJabatan pstContentDataJabatan = new PstContentDataJabatan(entContentDataJabatan.getOID());
                pstContentDataJabatan.setString(FLD_NAMA_JABATAN, entContentDataJabatan.getNamaJabatan());
                pstContentDataJabatan.setString(FLD_KODE_CORE_BANKING, entContentDataJabatan.getKodeCoreBanking());
                pstContentDataJabatan.setString(FLD_KODE_OJK, entContentDataJabatan.getKodeOjk());
                pstContentDataJabatan.update();
                return entContentDataJabatan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJabatan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataJabatan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataJabatan pstContentDataJabatan = new PstContentDataJabatan(oid);
            pstContentDataJabatan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJabatan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataJabatan entContentDataJabatan) throws DBException {
        try {
            PstContentDataJabatan pstContentDataJabatan = new PstContentDataJabatan(0);
            pstContentDataJabatan.setString(FLD_NAMA_JABATAN, entContentDataJabatan.getNamaJabatan());
            pstContentDataJabatan.setString(FLD_KODE_CORE_BANKING, entContentDataJabatan.getKodeCoreBanking());
            pstContentDataJabatan.setString(FLD_KODE_OJK, entContentDataJabatan.getKodeOjk());
            pstContentDataJabatan.insert();
            entContentDataJabatan.setOID(pstContentDataJabatan.getlong(FLD_JABATAN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJabatan(0), DBException.UNKNOWN);
        }
        return entContentDataJabatan.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataJabatan) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataJabatan entContentDataJabatan) {
        try {
            entContentDataJabatan.setOID(rs.getLong(PstContentDataJabatan.fieldNames[PstContentDataJabatan.FLD_JABATAN_OID]));
            entContentDataJabatan.setNamaJabatan(rs.getString(PstContentDataJabatan.fieldNames[PstContentDataJabatan.FLD_NAMA_JABATAN]));
            entContentDataJabatan.setKodeCoreBanking(rs.getString(PstContentDataJabatan.fieldNames[PstContentDataJabatan.FLD_KODE_CORE_BANKING]));
            entContentDataJabatan.setKodeOjk(rs.getString(PstContentDataJabatan.fieldNames[PstContentDataJabatan.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JABATAN;
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
                ContentDataJabatan entContentDataJabatan = new ContentDataJabatan();
                resultToObject(rs, entContentDataJabatan);
                lists.add(entContentDataJabatan);
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

    public static boolean checkOID(long entContentDataJabatanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JABATAN + " WHERE "
                    + PstContentDataJabatan.fieldNames[PstContentDataJabatan.FLD_JABATAN_OID] + " = " + entContentDataJabatanId;
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
            String sql = "SELECT COUNT(" + PstContentDataJabatan.fieldNames[PstContentDataJabatan.FLD_JABATAN_OID] + ") FROM " + TBL_CONTENT_DATA_JABATAN;
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
                    ContentDataJabatan entContentDataJabatan = (ContentDataJabatan) list.get(ls);
                    if (oid == entContentDataJabatan.getOID()) {
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
