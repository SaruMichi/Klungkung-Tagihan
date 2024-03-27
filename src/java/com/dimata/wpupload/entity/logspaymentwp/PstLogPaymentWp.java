package com.dimata.wpupload.entity.logspaymentwp;

import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstLogPaymentWp extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_LOG_PAYMENT_WP = "log_payment_wp";
    public static final int FLD_OID_LOG_PAY = 0;
    public static final int FLD_NPWPD = 1;
    public static final int FLD_MASA_PAJAK = 2;
    public static final int FLD_TAHUN_PAJAK = 3;
    public static final int FLD_TYPE_PAYMNET = 4;
    public static final int FLD_STATUS = 5;
    public static final int FLD_QRVALUE = 6;
    public static final int FLD_BILLNUMBER = 7;

    public static String[] fieldNames = {
        "OID_LOG_PAY",
        "NPWPD",
        "MASA_PAJAK",
        "TAHUN_PAJAK",
        "TYPE_PAYMENT",
        "STATUS",
        "QRVALUE",
        "BILLNUMBER"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_INT,
        TYPE_INT,
        TYPE_STRING,
        TYPE_LONG
    };
    
    public static final int CHOOES_METHOD_PAYMNET = 0;
    public static final int PENDING_PAYMNET = 1;

    public PstLogPaymentWp() {
    }

    public PstLogPaymentWp(int i) throws DBException {
        super(new PstLogPaymentWp());
    }

    public PstLogPaymentWp(String sOid) throws DBException {
        super(new PstLogPaymentWp(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstLogPaymentWp(long lOid) throws DBException {
        super(new PstLogPaymentWp(0));
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
        return TBL_LOG_PAYMENT_WP;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstLogPaymentWp().getClass().getName();
    }

    public static LogPaymentWp fetchExc(long oid) throws DBException {
        try {
            LogPaymentWp entLogPaymentWp = new LogPaymentWp();
            PstLogPaymentWp pstLogPaymentWp = new PstLogPaymentWp(oid);
            entLogPaymentWp.setOID(oid);
            entLogPaymentWp.setNpwpd(pstLogPaymentWp.getString(FLD_NPWPD));
            entLogPaymentWp.setMasaPajak(pstLogPaymentWp.getString(FLD_MASA_PAJAK));
            entLogPaymentWp.setTahunPajak(pstLogPaymentWp.getInt(FLD_TAHUN_PAJAK));
            entLogPaymentWp.setTypePaymnet(pstLogPaymentWp.getInt(FLD_TYPE_PAYMNET));
            entLogPaymentWp.setStatus(pstLogPaymentWp.getInt(FLD_STATUS));
            return entLogPaymentWp;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLogPaymentWp(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        LogPaymentWp entLogPaymentWp = fetchExc(entity.getOID());
        entity = (Entity) entLogPaymentWp;
        return entLogPaymentWp.getOID();
    }

    public static synchronized long updateExc(LogPaymentWp entLogPaymentWp) throws DBException {
        try {
            if (entLogPaymentWp.getOID() != 0) {
                PstLogPaymentWp pstLogPaymentWp = new PstLogPaymentWp(entLogPaymentWp.getOID());
                pstLogPaymentWp.setString(FLD_NPWPD, entLogPaymentWp.getNpwpd());
                pstLogPaymentWp.setString(FLD_MASA_PAJAK, entLogPaymentWp.getMasaPajak());
                pstLogPaymentWp.setInt(FLD_TAHUN_PAJAK, entLogPaymentWp.getTahunPajak());
                pstLogPaymentWp.setInt(FLD_TYPE_PAYMNET, entLogPaymentWp.getTypePaymnet());
                pstLogPaymentWp.setInt(FLD_STATUS, entLogPaymentWp.getStatus());
                pstLogPaymentWp.setLong(FLD_BILLNUMBER, entLogPaymentWp.getBillnumber());
                pstLogPaymentWp.setString(FLD_QRVALUE, entLogPaymentWp.getQrvalue());
                pstLogPaymentWp.update();
                return entLogPaymentWp.getOID(); 
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLogPaymentWp(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((LogPaymentWp) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstLogPaymentWp pstLogPaymentWp = new PstLogPaymentWp(oid);
            pstLogPaymentWp.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLogPaymentWp(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(LogPaymentWp entLogPaymentWp) throws DBException {
        try {
            PstLogPaymentWp pstLogPaymentWp = new PstLogPaymentWp(0);
            pstLogPaymentWp.setString(FLD_NPWPD, entLogPaymentWp.getNpwpd());
            pstLogPaymentWp.setString(FLD_MASA_PAJAK, entLogPaymentWp.getMasaPajak());
            pstLogPaymentWp.setInt(FLD_TAHUN_PAJAK, entLogPaymentWp.getTahunPajak());
            pstLogPaymentWp.setInt(FLD_TYPE_PAYMNET, entLogPaymentWp.getTypePaymnet());
            pstLogPaymentWp.setInt(FLD_STATUS, entLogPaymentWp.getStatus());
            pstLogPaymentWp.insert();
            entLogPaymentWp.setOID(pstLogPaymentWp.getlong(FLD_OID_LOG_PAY));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLogPaymentWp(0), DBException.UNKNOWN);
        }
        return entLogPaymentWp.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((LogPaymentWp) entity);
    }

    public static void resultToObject(ResultSet rs, LogPaymentWp entLogPaymentWp) {
        try {
            entLogPaymentWp.setOID(rs.getLong(PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_OID_LOG_PAY]));
            entLogPaymentWp.setNpwpd(rs.getString(PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_NPWPD]));
            entLogPaymentWp.setMasaPajak(rs.getString(PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_MASA_PAJAK]));
            entLogPaymentWp.setTahunPajak(rs.getInt(PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_TAHUN_PAJAK]));
            entLogPaymentWp.setTypePaymnet(rs.getInt(PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_TYPE_PAYMNET]));
            entLogPaymentWp.setStatus(rs.getInt(PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_STATUS]));
            entLogPaymentWp.setQrvalue(rs.getString(PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_QRVALUE]));
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
            String sql = "SELECT * FROM " + TBL_LOG_PAYMENT_WP;
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
                LogPaymentWp entLogPaymentWp = new LogPaymentWp();
                resultToObject(rs, entLogPaymentWp);
                lists.add(entLogPaymentWp);
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

    public static boolean checkOID(long entLogPaymentWpId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_LOG_PAYMENT_WP + " WHERE "
                    + PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_OID_LOG_PAY] + " = " + entLogPaymentWpId;
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
            String sql = "SELECT COUNT(" + PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_OID_LOG_PAY] + ") FROM " + TBL_LOG_PAYMENT_WP;
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
                    LogPaymentWp entLogPaymentWp = (LogPaymentWp) list.get(ls);
                    if (oid == entLogPaymentWp.getOID()) {
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
    
    public static boolean resetStatus(String Npwpd) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "UPDATE "
                    + ""+PstLogPaymentWp.TBL_LOG_PAYMENT_WP+" "
                    + "SET "
                    + ""+PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_TYPE_PAYMNET]+" = 0, "
                    + ""+PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_STATUS]+" = 0, "
                    + ""+PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_QRVALUE]+" = '' "
                    + "WHERE NPWPD = '"+Npwpd+"'";
            DBHandler.execUpdate(sql);
            result = true;
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }
    
    public static long updateQrValue(long billnumber, String qrval) {
        DBResultSet dbrs = null;
        try {
            String sql = "UPDATE "+TBL_LOG_PAYMENT_WP+" SET "+fieldNames[FLD_QRVALUE]+" = '"+qrval+"'"
                    + " WHERE "+fieldNames[FLD_BILLNUMBER]+" = '"+billnumber+"'";
            DBHandler.execUpdate(sql);
            return billnumber;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return 0;
    }
}
