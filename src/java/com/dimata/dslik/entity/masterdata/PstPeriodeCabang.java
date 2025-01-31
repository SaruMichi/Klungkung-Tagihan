/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.masterdata;

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
public class PstPeriodeCabang extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_PERIODE_CABANG = "dslik_periode_cabang";
    public static final int FLD_PERIODE_CABANG_ID = 0;
    public static final int FLD_PERIODE_ID = 1;
    public static final int FLD_CABANG_ID = 2;
    public static final int FLD_KETERANGAN = 3;
    public static final int FLD_STATUS = 4;
    public static final int FLD_USER_ID = 5;
    public static final int FLD_TANGGAL_CREATE = 6;
    public static final int FLD_TANGGAL_POSTING = 7;

    public static String[] fieldNames = {
        "PERIODE_CABANG_ID",
        "PERIODE_ID",
        "CABANG_ID",
        "KETERANGAN",
        "STATUS",
        "USER_ID",
        "TANGGAL_CREATE",
        "TANGGAL_POSTING"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_LONG,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_INT,
        TYPE_LONG,
        TYPE_DATE,
        TYPE_DATE
    };

    public PstPeriodeCabang() {
    }

    public PstPeriodeCabang(int i) throws DBException {
        super(new PstPeriodeCabang());
    }

    public PstPeriodeCabang(String sOid) throws DBException {
        super(new PstPeriodeCabang(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstPeriodeCabang(long lOid) throws DBException {
        super(new PstPeriodeCabang(0));
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
        return TBL_PERIODE_CABANG;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstPeriodeCabang().getClass().getName();
    }

    public static PeriodeCabang fetchExc(long oid) throws DBException {
        try {
            PeriodeCabang entPeriodeCabang = new PeriodeCabang();
            PstPeriodeCabang pstPeriodeCabang = new PstPeriodeCabang(oid);
            entPeriodeCabang.setOID(oid);
            entPeriodeCabang.setPeriodeId(pstPeriodeCabang.getlong(FLD_PERIODE_ID));
            entPeriodeCabang.setCabangId(pstPeriodeCabang.getlong(FLD_CABANG_ID));
            entPeriodeCabang.setKeterangan(pstPeriodeCabang.getString(FLD_KETERANGAN));
            entPeriodeCabang.setStatus(pstPeriodeCabang.getInt(FLD_STATUS));
            entPeriodeCabang.setUserId(pstPeriodeCabang.getlong(FLD_USER_ID));
            entPeriodeCabang.setTanggalCreate(pstPeriodeCabang.getDate(FLD_TANGGAL_CREATE));
            entPeriodeCabang.setTanggalPosting(pstPeriodeCabang.getDate(FLD_TANGGAL_POSTING));
            return entPeriodeCabang;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPeriodeCabang(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        PeriodeCabang entPeriodeCabang = fetchExc(entity.getOID());
        entity = (Entity) entPeriodeCabang;
        return entPeriodeCabang.getOID();
    }

    public static synchronized long updateExc(PeriodeCabang entPeriodeCabang) throws DBException {
        try {
            if (entPeriodeCabang.getOID() != 0) {
                PstPeriodeCabang pstPeriodeCabang = new PstPeriodeCabang(entPeriodeCabang.getOID());
                pstPeriodeCabang.setLong(FLD_PERIODE_ID, entPeriodeCabang.getPeriodeId());
                pstPeriodeCabang.setLong(FLD_CABANG_ID, entPeriodeCabang.getCabangId());
                pstPeriodeCabang.setString(FLD_KETERANGAN, entPeriodeCabang.getKeterangan());
                pstPeriodeCabang.setInt(FLD_STATUS, entPeriodeCabang.getStatus());
                pstPeriodeCabang.setLong(FLD_USER_ID, entPeriodeCabang.getUserId());
                pstPeriodeCabang.setDate(FLD_TANGGAL_CREATE, entPeriodeCabang.getTanggalCreate());
                pstPeriodeCabang.setDate(FLD_TANGGAL_POSTING, entPeriodeCabang.getTanggalPosting());
                pstPeriodeCabang.update();
                return entPeriodeCabang.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPeriodeCabang(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((PeriodeCabang) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstPeriodeCabang pstPeriodeCabang = new PstPeriodeCabang(oid);
            pstPeriodeCabang.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPeriodeCabang(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(PeriodeCabang entPeriodeCabang) throws DBException {
        try {
            PstPeriodeCabang pstPeriodeCabang = new PstPeriodeCabang(0);
            pstPeriodeCabang.setLong(FLD_PERIODE_ID, entPeriodeCabang.getPeriodeId());
            pstPeriodeCabang.setLong(FLD_CABANG_ID, entPeriodeCabang.getCabangId());
            pstPeriodeCabang.setString(FLD_KETERANGAN, entPeriodeCabang.getKeterangan());
            pstPeriodeCabang.setInt(FLD_STATUS, entPeriodeCabang.getStatus());
            pstPeriodeCabang.setLong(FLD_USER_ID, entPeriodeCabang.getUserId());
            pstPeriodeCabang.setDate(FLD_TANGGAL_CREATE, entPeriodeCabang.getTanggalCreate());
            pstPeriodeCabang.setDate(FLD_TANGGAL_POSTING, entPeriodeCabang.getTanggalPosting());
            pstPeriodeCabang.insert();
            entPeriodeCabang.setOID(pstPeriodeCabang.getlong(FLD_PERIODE_CABANG_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPeriodeCabang(0), DBException.UNKNOWN);
        }
        return entPeriodeCabang.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((PeriodeCabang) entity);
    }

    public static void resultToObject(ResultSet rs, PeriodeCabang entPeriodeCabang) {
        try {
            entPeriodeCabang.setOID(rs.getLong(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_CABANG_ID]));
            entPeriodeCabang.setPeriodeId(rs.getLong(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_ID]));
            entPeriodeCabang.setCabangId(rs.getLong(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_CABANG_ID]));
            entPeriodeCabang.setKeterangan(rs.getString(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_KETERANGAN]));
            entPeriodeCabang.setStatus(rs.getInt(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_STATUS]));
            entPeriodeCabang.setUserId(rs.getLong(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_USER_ID]));
            entPeriodeCabang.setTanggalCreate(rs.getDate(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_TANGGAL_CREATE]));
            entPeriodeCabang.setTanggalPosting(rs.getDate(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_TANGGAL_POSTING]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoin(ResultSet rs, PeriodeCabang entPeriodeCabang) {
        try {
            entPeriodeCabang.setOID(rs.getLong(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_CABANG_ID]));
            entPeriodeCabang.setNamaPeriode(rs.getString(PstPeriode.fieldNames[PstPeriode.FLD_NAMA]));
            entPeriodeCabang.setNamaCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_NAMA_CABANG]));
            entPeriodeCabang.setKeterangan(rs.getString(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_KETERANGAN]));
            entPeriodeCabang.setStatus(rs.getInt(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_STATUS]));
            entPeriodeCabang.setUserId(rs.getLong(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_USER_ID]));
            entPeriodeCabang.setTanggalCreate(rs.getDate(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_TANGGAL_CREATE]));
            entPeriodeCabang.setTanggalPosting(rs.getDate(PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_TANGGAL_POSTING]));
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
            String sql = "SELECT * FROM " + TBL_PERIODE_CABANG;
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
                PeriodeCabang entPeriodeCabang = new PeriodeCabang();
                resultToObject(rs, entPeriodeCabang);
                lists.add(entPeriodeCabang);
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
    
    public static Vector listJoin(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT dp." + PstPeriode.fieldNames[PstPeriode.FLD_NAMA] 
                    + ", dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_CABANG_ID] 
                    + ", dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_NAMA_CABANG] 
                    + ", dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_KETERANGAN] 
                    + ", dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_STATUS] 
                    + ", dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_USER_ID]
                    + ", dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_TANGGAL_CREATE]
                    + ", dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_TANGGAL_POSTING]
                    + " FROM " + PstPeriodeCabang.TBL_PERIODE_CABANG + " AS dpc "
                    + " JOIN " + PstCabangBank.TBL_CABANG_BANK + " AS dcb "
                    + " ON dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_CABANG_ID] 
                    + " = dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_CABANG_ID]
                    + " JOIN " + PstPeriode.TBL_PERIODE +" AS dp "
                    + " ON dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_ID] 
                    + " = dp." + PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID];
            
            String sql2 = "SELECT dp.nama, dcb.NAMA_CABANG, dpc.KETERANGAN, dpc.STATUS, "
                    + "dpc.USER_ID, dpc.TANGGAL_CREATE, dpc.TANGGAL_POSTING "
                    + "FROM dslik_periode_cabang AS dpc "
                    + "JOIN dslik_cabang_bank AS dcb "
                    + "ON dpc.CABANG_ID = dcb.CABANG_ID "
                    + "JOIN dslik_periode AS dp "
                    + "ON dpc.PERIODE_ID = dp.periode_id";
                    
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
                PeriodeCabang entPeriodeCabang = new PeriodeCabang();
                resultToObjectJoin(rs, entPeriodeCabang);
                lists.add(entPeriodeCabang);
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

    public static boolean checkOID(long entPeriodeCabangId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_PERIODE_CABANG + " WHERE "
                    + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_CABANG_ID] + " = " + entPeriodeCabangId;
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
            String sql = "SELECT COUNT(" + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_CABANG_ID] + ") FROM " + TBL_PERIODE_CABANG;
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
    
    
    public static int getCountJoin(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT COUNT(dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_CABANG_ID]+")" 
                    + " FROM " + PstPeriodeCabang.TBL_PERIODE_CABANG + " AS dpc "
                    + " JOIN " + PstCabangBank.TBL_CABANG_BANK + " AS dcb "
                    + " ON dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_CABANG_ID] 
                    + " = dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_CABANG_ID]
                    + " JOIN " + PstPeriode.TBL_PERIODE +" AS dp "
                    + " ON dpc." + PstPeriodeCabang.fieldNames[PstPeriodeCabang.FLD_PERIODE_ID] 
                    + " = dp." + PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID];
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
                    PeriodeCabang entPeriodeCabang = (PeriodeCabang) list.get(ls);
                    if (oid == entPeriodeCabang.getOID()) {
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
