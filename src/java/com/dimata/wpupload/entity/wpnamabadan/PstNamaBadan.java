/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.entity.wpnamabadan;

import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.qdep.db.I_DBInterface;
import com.dimata.qdep.db.I_DBType;
import com.dimata.qdep.entity.Entity;
import com.dimata.qdep.entity.I_PersintentExc;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import com.dimata.webclient.AppSetting;
import com.dimata.wpupload.entity.esptpd.PstESPTPD;
import com.dimata.wpupload.entity.wpuser.PstAppUserWP;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Ardiadi
 */
public class PstNamaBadan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_NAMABADAN = AppSetting.NAMA_VIEW_BADAN;
    public static final int FLD_NPWPD = 0;
    public static final int FLD_NAMA = 1;
    public static final int FLD_ALAMAT = 2;
    public static final int FLD_RT = 3;
    public static final int FLD_RW = 4;
    public static final int FLD_KELURAHAN = 5;
    public static final int FLD_KECAMATAN = 6;
    public static final int FLD_KABUPATEN = 7;
    public static final int FLD_NAMA_BADAN = 8;
    public static final int FLD_JENIS_USAHA = 9;
    public static final int FLD_ALAMAT_USAHA = 10;
    public static final int FLD_KODE_JENIS_USAHA = 11;
    public static final int FLD_KODE_JENIS_WP = 12;
    public static final int FLD_SUBJENIS_WP = 13;
    public static final int FLD_KD_LOKASI = 14;
    public static final int FLD_TARIF = 15;
    public static final int FLD_NO_TLP = 16;

    public static String[] fieldNames = {
        "NPWPD",
        "NAMA",
        "ALAMAT",
        "RT",
        "RW",
        "KELURAHAN",
        "KECAMATAN",
        "KABUPATEN",
        "NAMA_BADAN",
        "JENIS_USAHA",
        "ALAMAT_USAHA",
        "KODE_JENIS_USAHA",
        "KODE_JENIS_WP",
        "SUBJENIS_WP",
        "KD_LOKASI",
        "TARIF",
        "NO_TELPON"
    };

    public static int[] fieldTypes = {
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_STRING
    };

    public PstNamaBadan() {
    }

    public PstNamaBadan(int i) throws DBException {
        super(new PstNamaBadan());
    }

    public PstNamaBadan(String sOid) throws DBException {
        super(new PstNamaBadan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstNamaBadan(long lOid) throws DBException {
        super(new PstNamaBadan(0));
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
        return TBL_NAMABADAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstNamaBadan().getClass().getName();
    }

    public static NamaBadan fetchExc(long oid) throws DBException {
        try {
            NamaBadan entNamaBadan = new NamaBadan();
            PstNamaBadan pstNamaBadan = new PstNamaBadan(oid);
            entNamaBadan.setNbNPWPD(pstNamaBadan.getString(FLD_NPWPD));
            entNamaBadan.setNbNama(pstNamaBadan.getString(FLD_NAMA));
            entNamaBadan.setNbAlamat(pstNamaBadan.getString(FLD_ALAMAT));
            entNamaBadan.setNbRT(pstNamaBadan.getString(FLD_RT));
            entNamaBadan.setNbRW(pstNamaBadan.getString(FLD_RW));
            entNamaBadan.setNbKelurahan(pstNamaBadan.getString(FLD_KELURAHAN));
            entNamaBadan.setNbKecamatan(pstNamaBadan.getString(FLD_KECAMATAN));
            entNamaBadan.setNbKabupaten(pstNamaBadan.getString(FLD_KABUPATEN));
            entNamaBadan.setNbNamaBadan(pstNamaBadan.getString(FLD_NAMA_BADAN));
            entNamaBadan.setNbJenisUsaha(pstNamaBadan.getString(FLD_JENIS_USAHA));
            entNamaBadan.setNbAlamatUsaha(pstNamaBadan.getString(FLD_ALAMAT_USAHA));
            entNamaBadan.setNbKDJenisUsaha(pstNamaBadan.getString(FLD_KODE_JENIS_USAHA));
            entNamaBadan.setNbKDJenisWP(pstNamaBadan.getString(FLD_KODE_JENIS_WP));
            entNamaBadan.setNbSubjenisWP(pstNamaBadan.getString(FLD_SUBJENIS_WP));
            entNamaBadan.setNbKDLokasi(pstNamaBadan.getString(FLD_KD_LOKASI));
            entNamaBadan.setNbTarif(pstNamaBadan.getdouble(FLD_TARIF));
            entNamaBadan.setnNoTelepon(pstNamaBadan.getString(FLD_NO_TLP));
            return entNamaBadan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstNamaBadan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        NamaBadan entNamaBadan = fetchExc(entity.getOID());
        entity = (Entity) entNamaBadan;
        return entNamaBadan.getOID();
    }

    public static synchronized long updateExc(NamaBadan entNamaBadan) throws DBException {
        try {
            if (entNamaBadan.getOID() != 0) {
                PstNamaBadan pstNamaBadan = new PstNamaBadan(entNamaBadan.getOID());
                pstNamaBadan.setString(FLD_NPWPD, entNamaBadan.getNbNPWPD());
                pstNamaBadan.setString(FLD_NAMA, entNamaBadan.getNbNama());
                pstNamaBadan.setString(FLD_ALAMAT, entNamaBadan.getNbAlamat());
                pstNamaBadan.setString(FLD_RT, entNamaBadan.getNbRT());
                pstNamaBadan.setString(FLD_RW, entNamaBadan.getNbRW());
                pstNamaBadan.setString(FLD_KELURAHAN, entNamaBadan.getNbKelurahan());
                pstNamaBadan.setString(FLD_KECAMATAN, entNamaBadan.getNbKecamatan());
                pstNamaBadan.setString(FLD_KABUPATEN, entNamaBadan.getNbKabupaten());
                pstNamaBadan.setString(FLD_NAMA_BADAN, entNamaBadan.getNbNamaBadan());
                pstNamaBadan.setString(FLD_JENIS_USAHA, entNamaBadan.getNbJenisUsaha());
                pstNamaBadan.setString(FLD_ALAMAT_USAHA, entNamaBadan.getNbAlamatUsaha());
                pstNamaBadan.setString(FLD_KODE_JENIS_USAHA, entNamaBadan.getNbKDJenisUsaha());
                pstNamaBadan.setString(FLD_KODE_JENIS_WP, entNamaBadan.getNbKDJenisWP());
                pstNamaBadan.setString(FLD_SUBJENIS_WP, entNamaBadan.getNbSubjenisWP());
                pstNamaBadan.setString(FLD_KD_LOKASI, entNamaBadan.getNbKDLokasi());
                pstNamaBadan.setDouble(FLD_TARIF, entNamaBadan.getNbTarif());
                pstNamaBadan.setString(FLD_NO_TLP, entNamaBadan.getnNoTelepon());
                pstNamaBadan.update();
                return entNamaBadan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstNamaBadan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((NamaBadan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstNamaBadan pstNamaBadan = new PstNamaBadan(oid);
            pstNamaBadan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstNamaBadan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(NamaBadan entNamaBadan) throws DBException {
        try {
            PstNamaBadan pstNamaBadan = new PstNamaBadan(0);
            pstNamaBadan.setString(FLD_NAMA, entNamaBadan.getNbNama());
            pstNamaBadan.setString(FLD_ALAMAT, entNamaBadan.getNbAlamat());
            pstNamaBadan.setString(FLD_RT, entNamaBadan.getNbRT());
            pstNamaBadan.setString(FLD_RW, entNamaBadan.getNbRW());
            pstNamaBadan.setString(FLD_KELURAHAN, entNamaBadan.getNbKelurahan());
            pstNamaBadan.setString(FLD_KECAMATAN, entNamaBadan.getNbKecamatan());
            pstNamaBadan.setString(FLD_KABUPATEN, entNamaBadan.getNbKabupaten());
            pstNamaBadan.setString(FLD_NAMA_BADAN, entNamaBadan.getNbNamaBadan());
            pstNamaBadan.setString(FLD_JENIS_USAHA, entNamaBadan.getNbJenisUsaha());
            pstNamaBadan.setString(FLD_ALAMAT_USAHA, entNamaBadan.getNbAlamatUsaha());
            pstNamaBadan.setString(FLD_KODE_JENIS_USAHA, entNamaBadan.getNbKDJenisUsaha());
            pstNamaBadan.setString(FLD_KODE_JENIS_WP, entNamaBadan.getNbKDJenisWP());
            pstNamaBadan.setString(FLD_SUBJENIS_WP, entNamaBadan.getNbSubjenisWP());
            pstNamaBadan.setString(FLD_KD_LOKASI, entNamaBadan.getNbKDLokasi());
            pstNamaBadan.setDouble(FLD_TARIF, entNamaBadan.getNbTarif());
            pstNamaBadan.setString(FLD_NO_TLP, entNamaBadan.getnNoTelepon());
            pstNamaBadan.insert();
            entNamaBadan.setOID(pstNamaBadan.getLong(FLD_NPWPD));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstNamaBadan(0), DBException.UNKNOWN);
        }
        return entNamaBadan.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((NamaBadan) entity);
    }

    public static void resultToObject(ResultSet rs, NamaBadan entNamaBadan) {
        try {
            entNamaBadan.setNbNPWPD(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NPWPD]));
            entNamaBadan.setNbNama(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NAMA]));
            entNamaBadan.setNbAlamat(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_ALAMAT]));
            entNamaBadan.setNbRT(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_RT]));
            entNamaBadan.setNbRW(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_RW]));
            entNamaBadan.setNbKelurahan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KELURAHAN]));
            entNamaBadan.setNbKecamatan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KECAMATAN]));
            entNamaBadan.setNbKabupaten(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KABUPATEN]));
            entNamaBadan.setNbNamaBadan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NAMA_BADAN]));
            entNamaBadan.setNbJenisUsaha(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_JENIS_USAHA]));
            entNamaBadan.setNbAlamatUsaha(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_ALAMAT_USAHA]));
            entNamaBadan.setNbKDJenisUsaha(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KODE_JENIS_USAHA]));
            entNamaBadan.setNbKDJenisWP(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KODE_JENIS_WP]));
            entNamaBadan.setNbSubjenisWP(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_SUBJENIS_WP]));
            entNamaBadan.setNbKDLokasi(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KD_LOKASI]));
            entNamaBadan.setNbTarif(rs.getDouble(PstNamaBadan.fieldNames[PstNamaBadan.FLD_TARIF]));
            entNamaBadan.setnNoTelepon(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NO_TLP]));
        } catch (Exception e) {
        }
    }

    public static void resultToObjectJoin(ResultSet rs, NamaBadan entNamaBadan) {
        try {
            entNamaBadan.setNbNPWPD(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NPWPD]));
            entNamaBadan.setNbNama(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NAMA]));
            entNamaBadan.setNbAlamat(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_ALAMAT]));
            entNamaBadan.setNbKelurahan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KELURAHAN]));
            entNamaBadan.setNbKecamatan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KECAMATAN]));
            entNamaBadan.setNbKabupaten(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KABUPATEN]));
            entNamaBadan.setNbNamaBadan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NAMA_BADAN]));
            entNamaBadan.setNbTarif(rs.getDouble(PstNamaBadan.fieldNames[PstNamaBadan.FLD_TARIF]));
            entNamaBadan.setNbKDJenisUsaha(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KODE_JENIS_USAHA]));
            entNamaBadan.setNbJenisUsaha(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_JENIS_USAHA]));
            entNamaBadan.setNbKDJenisWP(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KODE_JENIS_WP]));
            entNamaBadan.setNbSubjenisWP(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_SUBJENIS_WP]));
            entNamaBadan.setNbUserId(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]));
            entNamaBadan.setNbNip(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_NIP]));
            entNamaBadan.setnUserName(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_NAMA]));
            entNamaBadan.setNoSubRekening(rs.getString("NO_SUBREKENING"));
            entNamaBadan.setNamaJenisWP(rs.getString("JENIS_WP"));
            entNamaBadan.setKdLokasi(rs.getString("KODE_LOKASI"));

        } catch (Exception e) {
        }
    }

    public static void resultToObjectJoinSpt(ResultSet rs, NamaBadan entNamaBadan) {
        try {
            entNamaBadan.setNbNPWPD(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NPWPD]));
            entNamaBadan.setNbNama(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NAMA]));
            entNamaBadan.setNbAlamat(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_ALAMAT]));
            entNamaBadan.setNbKelurahan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KELURAHAN]));
            entNamaBadan.setNbKecamatan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KECAMATAN]));
            entNamaBadan.setNbKabupaten(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KABUPATEN]));
            entNamaBadan.setNbNamaBadan(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NAMA_BADAN]));
            entNamaBadan.setNbTarif(rs.getDouble(PstNamaBadan.fieldNames[PstNamaBadan.FLD_TARIF]));
            entNamaBadan.setNbKDJenisUsaha(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KODE_JENIS_USAHA]));
            entNamaBadan.setNbJenisUsaha(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_JENIS_USAHA]));
            entNamaBadan.setNbKDJenisWP(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_KODE_JENIS_WP]));
            entNamaBadan.setNbSubjenisWP(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_SUBJENIS_WP]));
            entNamaBadan.setNbUserId(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]));
            entNamaBadan.setNbNip(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_NIP]));
            entNamaBadan.setnUserName(rs.getString(PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_NAMA]));
            entNamaBadan.setnNoTelepon(rs.getString(PstNamaBadan.fieldNames[PstNamaBadan.FLD_NO_TLP]));
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
            String sql = "SELECT * FROM " + TBL_NAMABADAN;
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
                NamaBadan entNamaBadan = new NamaBadan();
                resultToObject(rs, entNamaBadan);
                lists.add(entNamaBadan);
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
            String sql = "SELECT namaBadan." + fieldNames[FLD_NPWPD] + ", "
                    + "namaBadan." + fieldNames[FLD_NAMA] + ", "
                    + "namaBadan." + fieldNames[FLD_NAMA_BADAN] + ", "
                    + "namaBadan." + fieldNames[FLD_ALAMAT] + ", "
                    + "namaBadan." + fieldNames[FLD_KELURAHAN] + ", "
                    + "namaBadan." + fieldNames[FLD_KECAMATAN] + ", "
                    + "namaBadan." + fieldNames[FLD_KABUPATEN] + ","
                    + "namaBadan." + fieldNames[FLD_JENIS_USAHA] + ", "
                    + "namaBadan." + fieldNames[FLD_SUBJENIS_WP] + ", "
                    + "namaBadan." + fieldNames[FLD_KODE_JENIS_USAHA] + ", "
                    + "namaBadan." + fieldNames[FLD_KODE_JENIS_WP] + ", "
                    + "namaBadan." + fieldNames[FLD_TARIF] + ", "
                    + "viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID] + ", "
                    + "viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NIP] + ", "
                    + "viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_NAMA] + ", "
                    + "namaBadan.NO_SUBREKENING, "
                    + "namaBadan.JENIS_WP, "
                    + "namaBadan.KODE_LOKASI "
                    + "FROM " + PstAppUserWP.TBL_APPUSERWP + " viewUser "
                    + "INNER JOIN " + TBL_NAMABADAN + " namaBadan "
                    + "ON viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD] + "=namaBadan." + fieldNames[FLD_NPWPD];
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
                NamaBadan entNamaBadan = new NamaBadan();
                resultToObjectJoin(rs, entNamaBadan);
                lists.add(entNamaBadan);
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

    public static Vector listJoinSpt(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT namaBadan." + fieldNames[FLD_NPWPD] + ", "
                    + "namaBadan." + fieldNames[FLD_NAMA] + ", "
                    + "namaBadan." + fieldNames[FLD_NAMA_BADAN] + ", "
                    + "namaBadan." + fieldNames[FLD_ALAMAT] + ", "
                    + "namaBadan." + fieldNames[FLD_KELURAHAN] + ", "
                    + "namaBadan." + fieldNames[FLD_KECAMATAN] + ", "
                    + "namaBadan." + fieldNames[FLD_KABUPATEN] + ","
                    + "namaBadan." + fieldNames[FLD_JENIS_USAHA] + ", "
                    + "namaBadan." + fieldNames[FLD_SUBJENIS_WP] + ", "
                    + "namaBadan." + fieldNames[FLD_KODE_JENIS_USAHA] + ", "
                    + "namaBadan." + fieldNames[FLD_KODE_JENIS_WP] + ", "
                    + "namaBadan." + fieldNames[FLD_TARIF] + ", "
                    + "namaBadan." + fieldNames[FLD_NO_TLP] + ", "
                    + "viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID] + ", "
                    + "viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NIP] + ", "
                    + "viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_NAMA] + " "
                    + "FROM " + PstAppUserWP.TBL_APPUSERWP + " viewUser "
                    + "INNER JOIN " + TBL_NAMABADAN + " namaBadan "
                    + "ON viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD] + "=namaBadan." + fieldNames[FLD_NPWPD];
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
                NamaBadan entNamaBadan = new NamaBadan();
                resultToObjectJoinSpt(rs, entNamaBadan);
                lists.add(entNamaBadan);
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

    public static boolean checkOID(long entNamaBadanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_NAMABADAN + " WHERE "
                    + PstNamaBadan.fieldNames[PstNamaBadan.FLD_NPWPD] + " = " + entNamaBadanId;
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
            String sql = "SELECT COUNT(" + PstNamaBadan.fieldNames[PstNamaBadan.FLD_NPWPD] + ") FROM " + TBL_NAMABADAN;
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
                    NamaBadan entNamaBadan = (NamaBadan) list.get(ls);
                    if (oid == entNamaBadan.getOID()) {
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
        } else if (start == (vectSize - recordToGet)) {
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
        return cmd;
    }
}
