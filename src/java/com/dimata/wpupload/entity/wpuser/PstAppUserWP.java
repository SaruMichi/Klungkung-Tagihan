/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.entity.wpuser;

import com.dimata.qdep.db.DBResultSet;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.I_DBInterface;
import com.dimata.qdep.db.I_DBType;
import com.dimata.qdep.entity.Entity;
import com.dimata.qdep.entity.I_PersintentExc;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Ardiadi
 */
public class PstAppUserWP extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_APPUSERWP = "VIEW_USER";
    public static final int FLD_USER_ID = 0;
    public static final int FLD_USER_NAMA = 1;
    public static final int FLD_PASSWORD = 2;
    public static final int FLD_CREATE_DATE = 3;
    public static final int FLD_LAST_LOGIN = 4;
    public static final int FLD_NIP = 5;
    public static final int FLD_KD_JABATAN = 6;
    public static final int FLD_NPWPD = 7;
    public static final int FLD_KD_JENIS_USAHA = 8;
    public static final int FLD_KD_JENIS_WP = 9;
    public static final int FLD_KD_SUBJENIS_WP = 10;
    public static final int FLD_KD_LOKASI = 11;
    public static final int FLD_KD_SUBLOKASI = 12;
    public static final int FLD_NO_TELP = 13;
    public static String[] fieldNames = {
        "USERID",
        "NAMA_BADAN",
        "PASSWORD",
        "CREATE_DATE",
        "LAST_LOGIN",
        "NIP",
        "KD_JABATAN",
        "NPWPD",
        "KD_JENIS_USAHA",
        "KD_JENIS_WP",
        "KD_SUBJENIS_WP",
        "KD_LOKASI",
        "KD_SUBLOKASI",
        "NO_TELP"
    };
    public static int[] fieldTypes = {
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstAppUserWP() {
    }

    public PstAppUserWP(int i) throws DBException {
        super(new PstAppUserWP());
    }

    public PstAppUserWP(String sOid) throws DBException {
        super(new PstAppUserWP(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstAppUserWP(long lOid) throws DBException {
        super(new PstAppUserWP(0));
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
        return TBL_APPUSERWP;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstAppUserWP().getClass().getName();
    }

    public static AppUserWP fetchExc(long oid) throws DBException {
        try {
            AppUserWP entAppUserWP = new AppUserWP();
            PstAppUserWP pstAppUserWP = new PstAppUserWP(oid);
            entAppUserWP.setOID(oid);
            entAppUserWP.setWpUserNama(pstAppUserWP.getString(FLD_USER_NAMA));
            entAppUserWP.setWpUserPassword(pstAppUserWP.getString(FLD_PASSWORD));
            entAppUserWP.setWpUserCreateDate(pstAppUserWP.getDate(FLD_CREATE_DATE));
            entAppUserWP.setWpUserLastLogin(pstAppUserWP.getDate(FLD_LAST_LOGIN));
            entAppUserWP.setWpUserNIP(pstAppUserWP.getString(FLD_NIP));
            entAppUserWP.setWpUserKDJabatan(pstAppUserWP.getString(FLD_KD_JABATAN));
            entAppUserWP.setWpUserNPWPD(pstAppUserWP.getString(FLD_NPWPD));
            entAppUserWP.setWpUserKDJenisUsaha(pstAppUserWP.getString(FLD_KD_JENIS_USAHA));
            entAppUserWP.setWpUserKDJenisWP(pstAppUserWP.getString(FLD_KD_JENIS_WP));
            entAppUserWP.setWpUserKDSubjenisWP(pstAppUserWP.getString(FLD_KD_SUBJENIS_WP));
            entAppUserWP.setWpUserLokasi(pstAppUserWP.getString(FLD_KD_LOKASI));
            entAppUserWP.setWpUserSublokasi(pstAppUserWP.getString(FLD_KD_SUBLOKASI));
            entAppUserWP.setWpUserNoTelp(pstAppUserWP.getString(FLD_NO_TELP));
            return entAppUserWP;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAppUserWP(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        AppUserWP entAppUserWP = fetchExc(entity.getOID());
        entity = (Entity) entAppUserWP;
        return entAppUserWP.getOID();
    }

    public static synchronized long updateExc(AppUserWP entAppUserWP) throws DBException {
        try {
            if (entAppUserWP.getOID() != 0) {
                PstAppUserWP pstAppUserWP = new PstAppUserWP(entAppUserWP.getOID());
                pstAppUserWP.setString(FLD_USER_NAMA, entAppUserWP.getWpUserNama());
                pstAppUserWP.setString(FLD_PASSWORD, entAppUserWP.getWpUserPassword());
                pstAppUserWP.setDate(FLD_CREATE_DATE, entAppUserWP.getWpUserCreateDate());
                pstAppUserWP.setDate(FLD_LAST_LOGIN, entAppUserWP.getWpUserLastLogin());
                pstAppUserWP.setString(FLD_NIP, entAppUserWP.getWpUserNIP());
                pstAppUserWP.setString(FLD_KD_JABATAN, entAppUserWP.getWpUserKDJabatan());
                pstAppUserWP.setString(FLD_NPWPD, entAppUserWP.getWpUserNPWPD());
                pstAppUserWP.setString(FLD_KD_JENIS_USAHA, entAppUserWP.getWpUserKDJenisUsaha());
                pstAppUserWP.setString(FLD_KD_JENIS_WP, entAppUserWP.getWpUserKDJenisWP());
                pstAppUserWP.setString(FLD_KD_SUBJENIS_WP, entAppUserWP.getWpUserKDSubjenisWP());
                pstAppUserWP.setString(FLD_KD_LOKASI, entAppUserWP.getWpUserLokasi());
                pstAppUserWP.setString(FLD_KD_SUBLOKASI, entAppUserWP.getWpUserSublokasi());
                pstAppUserWP.setString(FLD_NO_TELP, entAppUserWP.getWpUserNoTelp());
                pstAppUserWP.update();
                return entAppUserWP.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAppUserWP(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((AppUserWP) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstAppUserWP pstAppUserWP = new PstAppUserWP(oid);
            pstAppUserWP.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAppUserWP(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(AppUserWP entAppUserWP) throws DBException {
        try {
            PstAppUserWP pstAppUserWP = new PstAppUserWP(0);
            pstAppUserWP.setString(FLD_USER_NAMA, entAppUserWP.getWpUserNama());
            pstAppUserWP.setString(FLD_PASSWORD, entAppUserWP.getWpUserPassword());
            pstAppUserWP.setDate(FLD_CREATE_DATE, entAppUserWP.getWpUserCreateDate());
            pstAppUserWP.setDate(FLD_LAST_LOGIN, entAppUserWP.getWpUserLastLogin());
            pstAppUserWP.setString(FLD_NIP, entAppUserWP.getWpUserNIP());
            pstAppUserWP.setString(FLD_KD_JABATAN, entAppUserWP.getWpUserKDJabatan());
            pstAppUserWP.setString(FLD_NPWPD, entAppUserWP.getWpUserNPWPD());
            pstAppUserWP.setString(FLD_KD_JENIS_USAHA, entAppUserWP.getWpUserKDJenisUsaha());
            pstAppUserWP.setString(FLD_KD_JENIS_WP, entAppUserWP.getWpUserKDJenisWP());
            pstAppUserWP.setString(FLD_KD_SUBJENIS_WP, entAppUserWP.getWpUserKDSubjenisWP());
            pstAppUserWP.setString(FLD_KD_LOKASI, entAppUserWP.getWpUserLokasi());
            pstAppUserWP.setString(FLD_KD_SUBLOKASI, entAppUserWP.getWpUserSublokasi());
            pstAppUserWP.setString(FLD_NO_TELP, entAppUserWP.getWpUserNoTelp());
            pstAppUserWP.insert();
            entAppUserWP.setOID(pstAppUserWP.getLong(FLD_USER_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAppUserWP(0), DBException.UNKNOWN);
        }
        return entAppUserWP.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((AppUserWP) entity);
    }

    public static void resultToObject(ResultSet rs, AppUserWP entAppUserWP) {
        try {
            entAppUserWP.setWpUserId(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]));
            entAppUserWP.setWpUserNama(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_NAMA]));
            entAppUserWP.setWpUserPassword(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_PASSWORD]));
            entAppUserWP.setWpUserCreateDate(rs.getDate(PstAppUserWP.fieldNames[PstAppUserWP.FLD_CREATE_DATE]));
            entAppUserWP.setWpUserLastLogin(rs.getDate(PstAppUserWP.fieldNames[PstAppUserWP.FLD_LAST_LOGIN]));
            entAppUserWP.setWpUserNIP(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_NIP]));
            entAppUserWP.setWpUserKDJabatan(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_KD_JABATAN]));
            entAppUserWP.setWpUserNPWPD(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD]));
            entAppUserWP.setWpUserKDJenisUsaha(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_KD_JENIS_USAHA]));
            entAppUserWP.setWpUserKDJenisWP(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_KD_JENIS_WP]));
            entAppUserWP.setWpUserKDSubjenisWP(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_KD_SUBJENIS_WP]));
            entAppUserWP.setWpUserLokasi(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_KD_LOKASI]));
            entAppUserWP.setWpUserSublokasi(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_KD_SUBLOKASI]));
            entAppUserWP.setWpUserNoTelp(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_NO_TELP]));
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
            String sql = "SELECT * FROM " + TBL_APPUSERWP;
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
                AppUserWP entAppUserWP = new AppUserWP();
                resultToObject(rs, entAppUserWP);
                lists.add(entAppUserWP);
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

    public static AppUserWP getByLoginIDAndPassword(String loginID, String password, String noTelp) {
        if ((loginID == null) || (loginID.length() < 1) || (password == null) || (password.length() < 1)) {
            return null;
        }

        try {

            String whereClause = " " + fieldNames[FLD_USER_ID] + "='" + loginID.trim() + "' AND "
                    + fieldNames[FLD_PASSWORD] + "='" + password.trim() + "'";

            Vector appUsers = list(0, 0, whereClause, "");

            if ((appUsers == null) || (appUsers.size() != 1)) {
                return new AppUserWP();
            }

            return (AppUserWP) appUsers.get(0);

        } catch (Exception e) {
            System.out.println("getByLoginIDAndPassword " + e);
            return null;
        }
    }

    public static boolean checkOID(long entAppUserWPId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_APPUSERWP + " WHERE "
                    + PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID] + " = " + entAppUserWPId;
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
            String sql = "SELECT COUNT(" + PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID] + ") FROM " + TBL_APPUSERWP;
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
                    AppUserWP entAppUserWP = (AppUserWP) list.get(ls);
                    if (oid == entAppUserWP.getOID()) {
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
    
     public static int updateNewPassword(String userId, String currentPass, String newPass) throws DBException {
        DBResultSet dbrs = null;
        int result=1;
        try {
            String sql = "UPDATE " + TBL_APPUSERWP + " SET " +
                    fieldNames[FLD_PASSWORD] + " = '" + newPass +"'"+
                    " WHERE " +fieldNames[FLD_USER_ID] +" = '" + userId+"'"+
                    " AND "+fieldNames[FLD_PASSWORD]+" = '"+currentPass+"'";
            int Result = DBHandler.execUpdate(sql);
            
            //cek apakah password usudah berganti
            String whereNewPass = fieldNames[FLD_USER_ID] +" = '" + userId+"' AND "+fieldNames[FLD_PASSWORD]+" = '"+newPass+"'";
            result = getCount(whereNewPass);
            
        } catch (Exception e) {
            result = 0;
        } finally {
            DBResultSet.close(dbrs);
        }
        return result;
    }
    
}
