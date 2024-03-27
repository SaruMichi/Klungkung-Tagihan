/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.entity.esptpd;

import com.dimata.dtaxintegration.entity.loghistory.PstLogHistoryTransaksi;
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
import com.dimata.wpupload.entity.wpuser.PstAppUserWP;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 *
 * @author Ardiadi
 */
public class PstESPTPD extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static String TBL_ESPTPD = AppSetting.NAMA_VIEW_ESPTPD;//"view_e_sptpd";
    public static final int FLD_NPWPD = 0;
    public static final int FLD_TGL_INPUT = 1;
    public static final int FLD_MASA_PAJAK = 2;
    public static final int FLD_TAHUN_PAJAK = 3;
    public static final int FLD_TGL_REKAM = 4;
    public static final int FLD_NIP_REKAM = 5;
    public static final int FLD_ID_REKAM = 6;
    public static final int FLD_TGL_UBAH = 7;
    public static final int FLD_NIP_UBAH = 8;
    public static final int FLD_ID_UBAH = 9;
    public static final int FLD_JUMLAH_OMZET = 10;
    public static final int FLD_TARIF = 11;
    public static final int FLD_JUMLAH_PAJAK = 12;
    public static final int FLD_SERVICE_TAX = 13;
    public static final int FLD_DENDA = 14;
    public static final int FLD_PENGURANGAN = 15;
    public static final int FLD_HARUS_DIBAYAR = 16;
    public static final int FLD_KETERANGAN = 17;
    public static final int FLD_KODE_PAJAK = 18;
    public static final int FLD_NO_REKENING = 19;
    public static final int FLD_NO_SUBREKENING = 20;
    public static final int FLD_KODE_LOKASI = 21;
    public static final int FLD_NO_SPTPD = 22;
    public static final int FLD_NO_SSPD = 23;

    public static String[] fieldNames = {
        "NPWPD",
        "TGL_INPUT",
        "MASA_PAJAK",
        "TAHUN_PAJAK",
        "TGL_REKAM",
        "NIP_REKAM",
        "ID_REKAM",
        "TGL_UBAH",
        "NIP_UBAH",
        "ID_UBAH",
        "JUMLAH_OMZET",
        "TARIF",
        "JUMLAH_PAJAK",
        "SERVICE_TAX",
        "DENDA",
        "PENGURANGAN",
        "HARUS_DIBAYAR",
        "KETERANGAN",
        "KODE_PAJAK",
        "NO_REKENING",
        "NO_SUBREKENING",
        "KODE_LOKASI",
        "NO_SPTPD",
        "NO_SSPD"
    };

    public static int[] fieldTypes = {
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstESPTPD() {
    }

    public PstESPTPD(int i) throws DBException {
        super(new PstESPTPD());
    }

    public PstESPTPD(String sOid) throws DBException {
        super(new PstESPTPD(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstESPTPD(long lOid) throws DBException {
        super(new PstESPTPD(0));
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
        return TBL_ESPTPD;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstESPTPD().getClass().getName();
    }

    public static ESPTPD fetchExc(long oid) throws DBException {
        try {
            ESPTPD entESPTPD = new ESPTPD();
            PstESPTPD pstESPTPD = new PstESPTPD(oid);
            entESPTPD.setENPWPD(pstESPTPD.getString(FLD_NPWPD));
            entESPTPD.setETglInput(pstESPTPD.getDate(FLD_TGL_INPUT));
            entESPTPD.setEMasaPajak(pstESPTPD.getString(FLD_MASA_PAJAK));
            entESPTPD.setETahunPajak(pstESPTPD.getString(FLD_TAHUN_PAJAK));
            entESPTPD.setETglRekam(pstESPTPD.getDate(FLD_TGL_REKAM));
            entESPTPD.setENIPRekam(pstESPTPD.getString(FLD_NIP_REKAM));
            entESPTPD.setEIdRekam(pstESPTPD.getString(FLD_ID_REKAM));
            entESPTPD.setETglUbah(pstESPTPD.getDate(FLD_TGL_UBAH));
            entESPTPD.setENIPUbah(pstESPTPD.getString(FLD_NIP_UBAH));
            entESPTPD.setEIDUbah(pstESPTPD.getString(FLD_ID_UBAH));
            entESPTPD.setEJumlahOmzet(pstESPTPD.getString(FLD_JUMLAH_OMZET));
            entESPTPD.setETarif(pstESPTPD.getdouble(FLD_TARIF));
            entESPTPD.setEJumlahPajak(pstESPTPD.getdouble(FLD_JUMLAH_PAJAK));
            entESPTPD.setEServiceTax(pstESPTPD.getdouble(FLD_SERVICE_TAX));
            entESPTPD.setEDenda(pstESPTPD.getdouble(FLD_DENDA));
            entESPTPD.setEPengurangan(pstESPTPD.getdouble(FLD_PENGURANGAN));
            entESPTPD.setEHarusBayar(pstESPTPD.getdouble(FLD_HARUS_DIBAYAR));
            entESPTPD.setEKeterangan(pstESPTPD.getString(FLD_KETERANGAN));
            entESPTPD.setEKodePajak(pstESPTPD.getString(FLD_KODE_PAJAK));
            entESPTPD.setENoRekening(pstESPTPD.getString(FLD_NO_REKENING));
            entESPTPD.setENoSubrekening(pstESPTPD.getString(FLD_NO_SUBREKENING));
            entESPTPD.setEKodeLokasi(pstESPTPD.getString(FLD_KODE_LOKASI));
            entESPTPD.setENoSPTPD(pstESPTPD.getString(FLD_NO_SPTPD));
            entESPTPD.setENoSSPD(pstESPTPD.getString(FLD_NO_SSPD));
            return entESPTPD;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstESPTPD(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ESPTPD entESPTPD = fetchExc(entity.getOID());
        entity = (Entity) entESPTPD;
        return entESPTPD.getOID();
    }

    public static synchronized long updateExc(ESPTPD entESPTPD) throws DBException {
        try {
            if (entESPTPD.getOID() != 0) {
                PstESPTPD pstESPTPD = new PstESPTPD(entESPTPD.getOID());
                //pstESPTPD.setDate(FLD_TGL_INPUT, entESPTPD.getETglInput());
                //pstESPTPD.setString(FLD_MASA_PAJAK, entESPTPD.getEMasaPajak());
                //pstESPTPD.setString(FLD_TAHUN_PAJAK, entESPTPD.getETahunPajak());
                //pstESPTPD.setDate(FLD_TGL_REKAM, entESPTPD.getETglRekam());
                //pstESPTPD.setString(FLD_NIP_REKAM, entESPTPD.getENIPRekam());
                //pstESPTPD.setString(FLD_ID_REKAM, entESPTPD.getEIdRekam());
                pstESPTPD.setDate(FLD_TGL_UBAH, entESPTPD.getETglUbah());
                pstESPTPD.setString(FLD_NIP_UBAH, entESPTPD.getENIPUbah());
                pstESPTPD.setString(FLD_ID_UBAH, entESPTPD.getEIDUbah());
                pstESPTPD.setString(FLD_JUMLAH_OMZET, entESPTPD.getEJumlahOmzet());
                pstESPTPD.setDouble(FLD_TARIF, entESPTPD.getETarif());
                pstESPTPD.setDouble(FLD_JUMLAH_PAJAK, entESPTPD.getEJumlahPajak());
                pstESPTPD.setDouble(FLD_SERVICE_TAX, entESPTPD.getEServiceTax());
                pstESPTPD.setDouble(FLD_DENDA, entESPTPD.getEDenda());
                pstESPTPD.setDouble(FLD_PENGURANGAN, entESPTPD.getEPengurangan());
                pstESPTPD.setDouble(FLD_HARUS_DIBAYAR, entESPTPD.getEHarusBayar());
                pstESPTPD.setString(FLD_KETERANGAN, entESPTPD.getEKeterangan());
                pstESPTPD.setString(FLD_KODE_PAJAK, entESPTPD.getEKodePajak());
                pstESPTPD.setString(FLD_NO_REKENING, entESPTPD.getENoRekening());
                pstESPTPD.setString(FLD_NO_SUBREKENING, entESPTPD.getENoSubrekening());
                pstESPTPD.setString(FLD_KODE_LOKASI, entESPTPD.getEKodeLokasi());
                pstESPTPD.setString(FLD_NO_SPTPD, entESPTPD.getENoSPTPD());
                pstESPTPD.setString(FLD_NO_SSPD, entESPTPD.getENoSSPD());
                pstESPTPD.update();
                return entESPTPD.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstESPTPD(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public static synchronized long updateExcCustom(ESPTPD entESPTPD) throws DBException {
        try {
            if (entESPTPD.getENPWPD().length() != 0) {
                Connection connection = null;
                Statement statement = null;

                connection = getConnection();
                statement = getStatement(connection);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String SqlUpdate = ""
                        + "UPDATE " + TBL_ESPTPD + " SET "
                        + fieldNames[FLD_TGL_UBAH] + " = TO_DATE('" + simpledateformat.format(entESPTPD.getETglUbah()) + "', 'YYYY-MM-DD HH24:MI:SS'), "
                        + fieldNames[FLD_NIP_UBAH] + " = '" + entESPTPD.getENIPUbah() + "', "
                        + fieldNames[FLD_ID_UBAH] + " = '" + entESPTPD.getEIDUbah() + "', "
                        + fieldNames[FLD_JUMLAH_OMZET] + " = '" + entESPTPD.getEJumlahOmzet() + "', "
                        + fieldNames[FLD_TARIF] + " = '" + entESPTPD.getETarif() + "', "
                        + fieldNames[FLD_JUMLAH_PAJAK] + " = '" + entESPTPD.getEJumlahPajak() + "', "
                        + fieldNames[FLD_SERVICE_TAX] + " = '" + entESPTPD.getEServiceTax() + "', "
                        + fieldNames[FLD_HARUS_DIBAYAR] + " = '" + entESPTPD.getEHarusBayar() + "', "
                        + fieldNames[FLD_KETERANGAN] + " = '" + entESPTPD.getEKeterangan() + "', "
                        + fieldNames[FLD_KODE_PAJAK] + " = '" + entESPTPD.getEKodePajak() + "', "
                        + fieldNames[FLD_NO_REKENING] + " = '" + entESPTPD.getENoRekening() + "', "
                        + fieldNames[FLD_NO_SUBREKENING] + "= '" + entESPTPD.getENoSubrekening() + "', "
                        + fieldNames[FLD_KODE_LOKASI] + "= '" + entESPTPD.getEKodeLokasi() + "' "
                        + "WHERE " + fieldNames[FLD_NPWPD] + "= '" + entESPTPD.getENPWPD() + "' "
                        + "AND " + fieldNames[FLD_MASA_PAJAK] + "= '" + entESPTPD.getEMasaPajak() + "' "
                        + "AND " + fieldNames[FLD_TAHUN_PAJAK] + "= '" + entESPTPD.getETahunPajak() + "'";
                statement.executeUpdate(SqlUpdate);
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstESPTPD(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ESPTPD) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstESPTPD pstESPTPD = new PstESPTPD(oid);
            pstESPTPD.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstESPTPD(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ESPTPD entESPTPD) throws DBException {
        try {
            PstESPTPD pstESPTPD = new PstESPTPD(0);
            pstESPTPD.setString(FLD_NPWPD, entESPTPD.getENPWPD());
            pstESPTPD.setDate(FLD_TGL_INPUT, entESPTPD.getETglInput());
            pstESPTPD.setString(FLD_MASA_PAJAK, entESPTPD.getEMasaPajak());
            pstESPTPD.setString(FLD_TAHUN_PAJAK, entESPTPD.getETahunPajak());
            pstESPTPD.setDate(FLD_TGL_REKAM, entESPTPD.getETglRekam());
            pstESPTPD.setString(FLD_NIP_REKAM, entESPTPD.getENIPRekam());
            pstESPTPD.setString(FLD_ID_REKAM, entESPTPD.getEIdRekam());
            pstESPTPD.setDate(FLD_TGL_UBAH, entESPTPD.getETglUbah());
            pstESPTPD.setString(FLD_NIP_UBAH, entESPTPD.getENIPUbah());
            pstESPTPD.setString(FLD_ID_UBAH, entESPTPD.getEIDUbah());
            pstESPTPD.setString(FLD_JUMLAH_OMZET, entESPTPD.getEJumlahOmzet());
            pstESPTPD.setDouble(FLD_TARIF, entESPTPD.getETarif());
            pstESPTPD.setDouble(FLD_JUMLAH_PAJAK, entESPTPD.getEJumlahPajak());
            pstESPTPD.setDouble(FLD_SERVICE_TAX, entESPTPD.getEServiceTax());
            pstESPTPD.setDouble(FLD_DENDA, entESPTPD.getEDenda());
            pstESPTPD.setDouble(FLD_PENGURANGAN, entESPTPD.getEPengurangan());
            pstESPTPD.setDouble(FLD_HARUS_DIBAYAR, entESPTPD.getEHarusBayar());
            pstESPTPD.setString(FLD_KETERANGAN, entESPTPD.getEKeterangan());
            pstESPTPD.setString(FLD_KODE_PAJAK, entESPTPD.getEKodePajak());
            pstESPTPD.setString(FLD_NO_REKENING, entESPTPD.getENoRekening());
            pstESPTPD.setString(FLD_NO_SUBREKENING, entESPTPD.getENoSubrekening());
            pstESPTPD.setString(FLD_KODE_LOKASI, entESPTPD.getEKodeLokasi());
            pstESPTPD.setString(FLD_NO_SPTPD, entESPTPD.getENoSPTPD());
            pstESPTPD.setString(FLD_NO_SSPD, entESPTPD.getENoSSPD());
            pstESPTPD.TBL_ESPTPD = "simpatda.E_SPTPD";
            pstESPTPD.insert();
            entESPTPD.setOID(123);
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstESPTPD(0), DBException.UNKNOWN);
        }
        return entESPTPD.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ESPTPD) entity);
    }

    public static void resultToObject(ResultSet rs, ESPTPD entESPTPD) {
        try {
            entESPTPD.setENPWPD(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NPWPD]));
            entESPTPD.setETglInput(rs.getDate(PstESPTPD.fieldNames[PstESPTPD.FLD_TGL_INPUT]));
            entESPTPD.setEMasaPajak(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_MASA_PAJAK]));
            entESPTPD.setETahunPajak(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_TAHUN_PAJAK]));
            entESPTPD.setETglRekam(rs.getDate(PstESPTPD.fieldNames[PstESPTPD.FLD_TGL_REKAM]));
            entESPTPD.setENIPRekam(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NIP_REKAM]));
            entESPTPD.setEIdRekam(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_ID_REKAM]));
            entESPTPD.setETglUbah(rs.getDate(PstESPTPD.fieldNames[PstESPTPD.FLD_TGL_UBAH]));
            entESPTPD.setENIPUbah(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NIP_UBAH]));
            entESPTPD.setEIDUbah(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_ID_UBAH]));
            entESPTPD.setEJumlahOmzet(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_JUMLAH_OMZET]));
            entESPTPD.setETarif(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_TARIF]));
            entESPTPD.setEJumlahPajak(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_JUMLAH_PAJAK]));
            entESPTPD.setEServiceTax(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_SERVICE_TAX]));
            entESPTPD.setEDenda(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_DENDA]));
            entESPTPD.setEPengurangan(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_PENGURANGAN]));
            entESPTPD.setEHarusBayar(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_HARUS_DIBAYAR]));
            entESPTPD.setEKeterangan(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_KETERANGAN]));
            entESPTPD.setEKodePajak(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_KODE_PAJAK]));
            entESPTPD.setENoRekening(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NO_REKENING]));
            entESPTPD.setENoSubrekening(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NO_SUBREKENING]));
            entESPTPD.setEKodeLokasi(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_KODE_LOKASI]));
            entESPTPD.setENoSPTPD(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NO_SPTPD]));
            entESPTPD.setENoSSPD(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NO_SSPD]));
        } catch (Exception e) {
        }
    }

    public static void resultToObjectJoin(ResultSet rs, ESPTPD entESPTPD) {
        try {

            entESPTPD.setEMasaPajak(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_MASA_PAJAK]));
            entESPTPD.setETahunPajak(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_TAHUN_PAJAK]));
            entESPTPD.setETglRekam(rs.getDate(PstESPTPD.fieldNames[PstESPTPD.FLD_TGL_REKAM]));
            entESPTPD.setEIdRekam(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_ID_REKAM]));
            entESPTPD.setEJumlahOmzet(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_JUMLAH_OMZET]));
            entESPTPD.setETarif(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_TARIF]));
            entESPTPD.setEJumlahPajak(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_JUMLAH_PAJAK]));
            entESPTPD.setEServiceTax(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_SERVICE_TAX]));
            entESPTPD.setEHarusBayar(rs.getDouble(PstESPTPD.fieldNames[PstESPTPD.FLD_HARUS_DIBAYAR]));
            entESPTPD.setENoRekening(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NO_REKENING]));
            entESPTPD.setEKodeLokasi(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_KODE_LOKASI]));
            entESPTPD.setENoSPTPD(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NO_SPTPD]));
            entESPTPD.setEKeterangan(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_KETERANGAN]));
            entESPTPD.setENPWPD(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NPWPD]));
            entESPTPD.setENIPRekam(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NIP_REKAM]));
            entESPTPD.setEIdRekam(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_ID_REKAM]));
            entESPTPD.setEKodePajak(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_KODE_PAJAK]));
            entESPTPD.setENoSubrekening(rs.getString(PstESPTPD.fieldNames[PstESPTPD.FLD_NO_SUBREKENING]));
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
            String sql = "SELECT * FROM " + TBL_ESPTPD;
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
                ESPTPD entESPTPD = new ESPTPD();
                resultToObject(rs, entESPTPD);
                lists.add(entESPTPD);
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
            String sql = "SELECT sptpd." + fieldNames[FLD_TGL_REKAM] + ","
                    + "sptpd." + fieldNames[FLD_ID_REKAM] + ","
                    + "sptpd." + fieldNames[FLD_NO_SPTPD] + ","
                    + "sptpd." + fieldNames[FLD_MASA_PAJAK] + ", "
                    + "sptpd." + fieldNames[FLD_TAHUN_PAJAK] + ","
                    + "sptpd." + fieldNames[FLD_JUMLAH_OMZET] + ", "
                    + "sptpd." + fieldNames[FLD_TARIF] + ","
                    + "sptpd." + fieldNames[FLD_JUMLAH_PAJAK] + ","
                    + "sptpd." + fieldNames[FLD_SERVICE_TAX] + ","
                    + "sptpd." + fieldNames[FLD_HARUS_DIBAYAR] + ","
                    + "sptpd." + fieldNames[FLD_NO_REKENING] + ","
                    + "sptpd." + fieldNames[FLD_KODE_LOKASI] + ","
                    + "sptpd." + fieldNames[FLD_KETERANGAN] + ","
                    + "sptpd." + fieldNames[FLD_NPWPD] + ", "
                    + "sptpd." + fieldNames[FLD_NIP_REKAM] + ", "
                    + "sptpd." + fieldNames[FLD_KODE_PAJAK] + ", "
                    + "sptpd." + fieldNames[FLD_KODE_LOKASI] + ","
                    + "sptpd." + fieldNames[FLD_NO_SUBREKENING] + " "
                    + "FROM " + PstAppUserWP.TBL_APPUSERWP + " viewUser "
                    + "INNER JOIN " + TBL_ESPTPD + " sptpd "
                    + "ON viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD] + "=sptpd." + fieldNames[FLD_NPWPD];
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
                ESPTPD entESPTPD = new ESPTPD();
                resultToObjectJoin(rs, entESPTPD);
                lists.add(entESPTPD);
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

    public static int checkExistData(int limitStart, int recordToGet, String whereClause, String order) {
        int countExistData = 0;
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT COUNT(*) AS JUMLAH_DATA FROM view_all_simpatda ";// + TBL_ESPTPD;
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
                countExistData = rs.getInt("JUMLAH_DATA");
            }
            rs.close();
            return countExistData;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return countExistData;
    }

    public static boolean checkOID(long entESPTPDId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_ESPTPD + " WHERE "
                    + PstESPTPD.fieldNames[PstESPTPD.FLD_NPWPD] + " = " + entESPTPDId;
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
            String sql = "SELECT COUNT(" + PstESPTPD.fieldNames[PstESPTPD.FLD_NPWPD] + ") FROM " + TBL_ESPTPD;
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
    //select * from log_history_transaksi where id='10000091001201' and tahun='2017' and bulan='01';
     public static boolean getCountHistory(String id, String tahun, String bulan) {
        DBResultSet dbrs = null;
        boolean count = false;
        try {
            String sql = "SELECT (ID) FROM " + PstLogHistoryTransaksi.TBL_LOGHISTORYTRANSAKSI;
            sql = sql + " WHERE ID='"+id+"' AND TAHUN='"+tahun+"' AND BULAN='"+bulan+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
               return count = true;
            }
            rs.close();
            return count;
        } catch (Exception e) {
            return count;
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
                    ESPTPD entESPTPD = (ESPTPD) list.get(ls);
                    if (oid == entESPTPD.getOID()) {
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
    
     public static long updateDataEsptpd(String tahun, String masaPajak, String npwpd, String namaFile) throws DBException {
        long hasil = 0;
        try {
            if(!tahun.equals("") && !masaPajak.equals("") && !npwpd.equals("") && !namaFile.equals("")){
                String sql ="UPDATE "+TBL_ESPTPD+" SET FILE_DOKUMEN_UPLOAD='"+namaFile+"' WHERE NPWPD='"+npwpd+"' AND MASA_PAJAK='"+masaPajak+"' AND TAHUN_PAJAK='"+tahun+"'";
                int result = execUpdate(sql);
            }
        }
        catch(Exception e) {
        }
        return hasil;
    }
     
    public static boolean checkESPTDEXIST(String entESPTPDId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_ESPTPD + " WHERE "+entESPTPDId;
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
}
