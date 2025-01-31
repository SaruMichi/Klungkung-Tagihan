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
public class PstCabangBank extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CABANG_BANK = "dslik_cabang_bank";
    public static final int FLD_CABANG_ID = 0;
    public static final int FLD_BANK_ID = 1;
    public static final int FLD_KODE_CABANG = 2;
    public static final int FLD_NAMA_CABANG = 3;
    public static final int FLD_ALAMAT_CABANG = 4;
    public static final int FLD_EMAIL_CABANG = 5;
    public static final int FLD_FAX_CABANG = 6;
    public static final int FLD_NO_URUT = 7;

    public static String[] fieldNames = {
        "CABANG_ID",
        "BANK_ID",
        "KODE_CABANG",
        "NAMA_CABANG",
        "ALAMAT_CABANG",
        "EMAIL_CABANG",
        "FAX_CABANG",
        "NO_URUT"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT
    };

    public PstCabangBank() {
    }

    public PstCabangBank(int i) throws DBException {
        super(new PstCabangBank());
    }

    public PstCabangBank(String sOid) throws DBException {
        super(new PstCabangBank(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstCabangBank(long lOid) throws DBException {
        super(new PstCabangBank(0));
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
        return TBL_CABANG_BANK;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstCabangBank().getClass().getName();
    }

    public static CabangBank fetchExc(long oid) throws DBException {
        try {
            CabangBank entCabangBank = new CabangBank();
            PstCabangBank pstCabangBank = new PstCabangBank(oid);
            entCabangBank.setOID(oid);
            entCabangBank.setBankId(pstCabangBank.getlong(FLD_BANK_ID));
            entCabangBank.setKodeCabang(pstCabangBank.getString(FLD_KODE_CABANG));
            entCabangBank.setNamaCabang(pstCabangBank.getString(FLD_NAMA_CABANG));
            entCabangBank.setAlamatCabang(pstCabangBank.getString(FLD_ALAMAT_CABANG));
            entCabangBank.setEmailCabang(pstCabangBank.getString(FLD_EMAIL_CABANG));
            entCabangBank.setFaxCabang(pstCabangBank.getString(FLD_FAX_CABANG));
            entCabangBank.setNoUrut(pstCabangBank.getInt(FLD_NO_URUT));
            
            return entCabangBank;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCabangBank(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        CabangBank entCabangBank = fetchExc(entity.getOID());
        entity = (Entity) entCabangBank;
        return entCabangBank.getOID();
    }

    public static synchronized long updateExc(CabangBank entCabangBank) throws DBException {
        try {
            if (entCabangBank.getOID() != 0) {
                PstCabangBank pstCabangBank = new PstCabangBank(entCabangBank.getOID());
                pstCabangBank.setLong(FLD_BANK_ID, entCabangBank.getBankId());
                pstCabangBank.setString(FLD_KODE_CABANG, entCabangBank.getKodeCabang());
                pstCabangBank.setString(FLD_NAMA_CABANG, entCabangBank.getNamaCabang());
                pstCabangBank.setString(FLD_ALAMAT_CABANG, entCabangBank.getAlamatCabang());
                pstCabangBank.setString(FLD_EMAIL_CABANG, entCabangBank.getEmailCabang());
                pstCabangBank.setString(FLD_FAX_CABANG, entCabangBank.getFaxCabang());
                pstCabangBank.setInt(FLD_NO_URUT, entCabangBank.getNoUrut());
                
                pstCabangBank.update();
                return entCabangBank.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCabangBank(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((CabangBank) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstCabangBank pstCabangBank = new PstCabangBank(oid);
            pstCabangBank.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCabangBank(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(CabangBank entCabangBank) throws DBException {
        try {
            PstCabangBank pstCabangBank = new PstCabangBank(0);
            pstCabangBank.setLong(FLD_BANK_ID, entCabangBank.getBankId());
            pstCabangBank.setString(FLD_KODE_CABANG, entCabangBank.getKodeCabang());
            pstCabangBank.setString(FLD_NAMA_CABANG, entCabangBank.getNamaCabang());
            pstCabangBank.setString(FLD_ALAMAT_CABANG, entCabangBank.getAlamatCabang());
            pstCabangBank.setString(FLD_EMAIL_CABANG, entCabangBank.getEmailCabang());
            pstCabangBank.setString(FLD_FAX_CABANG, entCabangBank.getFaxCabang());
            pstCabangBank.setInt(FLD_NO_URUT, entCabangBank.getNoUrut());
            
            pstCabangBank.insert();
            entCabangBank.setOID(pstCabangBank.getlong(FLD_CABANG_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCabangBank(0), DBException.UNKNOWN);
        }
        return entCabangBank.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((CabangBank) entity);
    }

    public static void resultToObject(ResultSet rs, CabangBank entCabangBank) {
        try {
            entCabangBank.setOID(rs.getLong(PstCabangBank.fieldNames[PstCabangBank.FLD_CABANG_ID]));
            entCabangBank.setBankId(rs.getLong(PstCabangBank.fieldNames[PstCabangBank.FLD_BANK_ID]));
            entCabangBank.setKodeCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]));
            entCabangBank.setNamaCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_NAMA_CABANG]));
            entCabangBank.setAlamatCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_ALAMAT_CABANG]));
            entCabangBank.setEmailCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_EMAIL_CABANG]));
            entCabangBank.setFaxCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_FAX_CABANG]));
            entCabangBank.setNoUrut(rs.getInt(PstCabangBank.fieldNames[PstCabangBank.FLD_NO_URUT]));
            entCabangBank.setKodeParentCabang(rs.getString("PARENT_CODE"));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoin(ResultSet rs, CabangBank entCabangBank) {
        try {
            entCabangBank.setOID(rs.getLong(PstCabangBank.fieldNames[PstCabangBank.FLD_CABANG_ID]));
            entCabangBank.setNamaBank(rs.getString(PstBank.fieldNames[PstBank.FLD_NAMA_BANK]));
            entCabangBank.setKodeCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]));
            entCabangBank.setNamaCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_NAMA_CABANG]));
            entCabangBank.setAlamatCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_ALAMAT_CABANG]));
            entCabangBank.setEmailCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_EMAIL_CABANG]));
            entCabangBank.setFaxCabang(rs.getString(PstCabangBank.fieldNames[PstCabangBank.FLD_FAX_CABANG]));
            entCabangBank.setNoUrut(rs.getInt(PstCabangBank.fieldNames[PstCabangBank.FLD_NO_URUT]));
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
            String sql = "SELECT * FROM " + TBL_CABANG_BANK;
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
                CabangBank entCabangBank = new CabangBank();
                resultToObject(rs, entCabangBank);
                lists.add(entCabangBank);
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
    
    
    public static Vector listParent(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM dslik_parent_cabang_bank";
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
                CabangBank entCabangBank = new CabangBank();
                entCabangBank.setNamaBank(rs.getString("NAMA_PARENT"));
                entCabangBank.setKodeCabang(rs.getString("KODE_PARENT"));
                entCabangBank.setNoUrut(rs.getInt("NO_URUT"));
                lists.add(entCabangBank);
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
            String sql = "SELECT db." + PstBank.fieldNames[PstBank.FLD_NAMA_BANK]
                    + ", dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_CABANG_ID]
                    + ", dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]
                    + ", dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_NAMA_CABANG]
                    + ", dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_ALAMAT_CABANG]
                    + ", dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_EMAIL_CABANG]
                    + ", dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_FAX_CABANG]
                    + " FROM " + PstCabangBank.TBL_CABANG_BANK + " AS dcb"
                    + " INNER JOIN " + PstBank.TBL_BANK + " AS db"
                    + " ON dcb." + PstCabangBank.fieldNames[PstCabangBank.FLD_BANK_ID]
                    + " = db." + PstBank.fieldNames[PstBank.FLD_BANK_ID];
            
           
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
                CabangBank entCabangBank = new CabangBank();
                resultToObjectJoin(rs, entCabangBank);
                lists.add(entCabangBank);
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

    public static boolean checkOID(long entCabangBankId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CABANG_BANK + " WHERE "
                    + PstCabangBank.fieldNames[PstCabangBank.FLD_CABANG_ID] + " = " + entCabangBankId;
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
            String sql = "SELECT COUNT(" + PstCabangBank.fieldNames[PstCabangBank.FLD_CABANG_ID] + ") FROM " + TBL_CABANG_BANK;
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
                    CabangBank entCabangBank = (CabangBank) list.get(ls);
                    if (oid == entCabangBank.getOID()) {
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
    
    public static long checkCabangBank(String kdCabang) {
        DBResultSet dbrs = null;
        long result = 0;
        try {
            String sql = "SELECT * FROM " + TBL_CABANG_BANK+ " WHERE "
                    + ""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+kdCabang+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getLong("CABANG_ID");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }
    
    
    
}
