/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.entity.compareesptpd;

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
import com.dimata.wpupload.entity.compareesptpd.CompareEsptpd;
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
public class PstCompareEsptpd extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    public static final String TBL_ALL_ESPTPD = ""+AppSetting.NAMA_VIEW_ESPTPD;
    public static final String TBL_VIEW_ALL_SIMPATDA = "VIEW_ALL_SIMPATDA";
    public static final String TBL_ESPTPD = "VIEW_COMPARE_ESPTPD";
    public static final String TBL_ESPTPD_NEW = "VIEW_COMPARE_ESPTPD_2";
    public static final String TBL_ESPTPD_DENDA = "VIEW_COMPARE_ESPTPD_DENDA";
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
     public static final int FLD_FILE_DOCUMENT_UPLOAD = 24;

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
        "NO_SSPD",
        "FILE_DOKUMEN_UPLOAD"
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
        TYPE_STRING,
        TYPE_STRING
    };

    public PstCompareEsptpd() {
    }

    public PstCompareEsptpd(int i) throws DBException {
        super(new PstCompareEsptpd());
    }

    public PstCompareEsptpd(String sOid) throws DBException {
        super(new PstCompareEsptpd(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstCompareEsptpd(long lOid) throws DBException {
        super(new PstCompareEsptpd(0));
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
        return new PstCompareEsptpd().getClass().getName();
    }

    public static CompareEsptpd fetchExc(long oid) throws DBException {
        try {
            CompareEsptpd entCompareEsptpd = new CompareEsptpd();
            PstCompareEsptpd pstCompareEsptpd = new PstCompareEsptpd(oid);
            entCompareEsptpd.setCompareENpwpd(pstCompareEsptpd.getString(FLD_NPWPD));
            entCompareEsptpd.setCompareETglInput(pstCompareEsptpd.getDate(FLD_TGL_INPUT));
            entCompareEsptpd.setCompareEMasaPajak(pstCompareEsptpd.getString(FLD_MASA_PAJAK));
            entCompareEsptpd.setCompareETahunPajak(pstCompareEsptpd.getString(FLD_TAHUN_PAJAK));
            entCompareEsptpd.setCompareETglRekam(pstCompareEsptpd.getDate(FLD_TGL_REKAM));
            entCompareEsptpd.setCompareENIPRekam(pstCompareEsptpd.getString(FLD_NIP_REKAM));
            entCompareEsptpd.setCompareEIdRekam(pstCompareEsptpd.getString(FLD_ID_REKAM));
            entCompareEsptpd.setCompareETglUbah(pstCompareEsptpd.getDate(FLD_TGL_UBAH));
            entCompareEsptpd.setCompareENIPUbah(pstCompareEsptpd.getString(FLD_NIP_UBAH));
            entCompareEsptpd.setCompareEIDUbah(pstCompareEsptpd.getString(FLD_ID_UBAH));
            entCompareEsptpd.setCompareEJumlahOmzet(pstCompareEsptpd.getString(FLD_JUMLAH_OMZET));
            entCompareEsptpd.setCompareETarif(pstCompareEsptpd.getdouble(FLD_TARIF));
            entCompareEsptpd.setCompareEJumlahPajak(pstCompareEsptpd.getdouble(FLD_JUMLAH_PAJAK));
            entCompareEsptpd.setCompareEServiceTax(pstCompareEsptpd.getdouble(FLD_SERVICE_TAX));
            entCompareEsptpd.setCompareEDenda(pstCompareEsptpd.getdouble(FLD_DENDA));
            entCompareEsptpd.setCompareEPengurangan(pstCompareEsptpd.getdouble(FLD_PENGURANGAN));
            entCompareEsptpd.setCompareEHarusBayar(pstCompareEsptpd.getdouble(FLD_HARUS_DIBAYAR));
            entCompareEsptpd.setCompareEKeterangan(pstCompareEsptpd.getString(FLD_KETERANGAN));
            entCompareEsptpd.setCompareEKodePajak(pstCompareEsptpd.getString(FLD_KODE_PAJAK));
            entCompareEsptpd.setCompareENoRekening(pstCompareEsptpd.getString(FLD_NO_REKENING));
            entCompareEsptpd.setCompareENoSubrekening(pstCompareEsptpd.getString(FLD_NO_SUBREKENING));
            entCompareEsptpd.setCompareEKodeLokasi(pstCompareEsptpd.getString(FLD_KODE_LOKASI));
            entCompareEsptpd.setCompareENoSPTPD(pstCompareEsptpd.getString(FLD_NO_SPTPD));
            entCompareEsptpd.setCompareENoSSPD(pstCompareEsptpd.getString(FLD_NO_SSPD));
            return entCompareEsptpd;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCompareEsptpd(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        CompareEsptpd entCompareEsptpd = fetchExc(entity.getOID());
        entity = (Entity) entCompareEsptpd;
        return entCompareEsptpd.getOID();
    }

    public static synchronized long updateExc(CompareEsptpd entCompareEsptpd) throws DBException {
        try {
            if (entCompareEsptpd.getOID() != 0) {
                PstCompareEsptpd pstCompareEsptpd = new PstCompareEsptpd(entCompareEsptpd.getOID());
            //pstESPTPD.setDate(FLD_TGL_INPUT, entESPTPD.getETglInput());
                //pstESPTPD.setString(FLD_MASA_PAJAK, entESPTPD.getEMasaPajak());
                //pstESPTPD.setString(FLD_TAHUN_PAJAK, entESPTPD.getETahunPajak());
                //pstESPTPD.setDate(FLD_TGL_REKAM, entESPTPD.getETglRekam());
                //pstESPTPD.setString(FLD_NIP_REKAM, entESPTPD.getENIPRekam());
                //pstESPTPD.setString(FLD_ID_REKAM, entESPTPD.getEIdRekam());
                pstCompareEsptpd.setDate(FLD_TGL_UBAH, entCompareEsptpd.getCompareETglUbah());
                pstCompareEsptpd.setString(FLD_NIP_UBAH, entCompareEsptpd.getCompareENIPUbah());
                pstCompareEsptpd.setString(FLD_ID_UBAH, entCompareEsptpd.getCompareEIDUbah());
                pstCompareEsptpd.setString(FLD_JUMLAH_OMZET, entCompareEsptpd.getCompareEJumlahOmzet());
                pstCompareEsptpd.setDouble(FLD_TARIF, entCompareEsptpd.getCompareETarif());
                pstCompareEsptpd.setDouble(FLD_JUMLAH_PAJAK, entCompareEsptpd.getCompareEJumlahPajak());
                pstCompareEsptpd.setDouble(FLD_SERVICE_TAX, entCompareEsptpd.getCompareEServiceTax());
                pstCompareEsptpd.setDouble(FLD_DENDA, entCompareEsptpd.getCompareEDenda());
                pstCompareEsptpd.setDouble(FLD_PENGURANGAN, entCompareEsptpd.getCompareEPengurangan());
                pstCompareEsptpd.setDouble(FLD_HARUS_DIBAYAR, entCompareEsptpd.getCompareEHarusBayar());
                pstCompareEsptpd.setString(FLD_KETERANGAN, entCompareEsptpd.getCompareEKeterangan());
                pstCompareEsptpd.setString(FLD_KODE_PAJAK, entCompareEsptpd.getCompareEKodePajak());
                pstCompareEsptpd.setString(FLD_NO_REKENING, entCompareEsptpd.getCompareENoRekening());
                pstCompareEsptpd.setString(FLD_NO_SUBREKENING, entCompareEsptpd.getCompareENoSubrekening());
                pstCompareEsptpd.setString(FLD_KODE_LOKASI, entCompareEsptpd.getCompareEKodeLokasi());
                pstCompareEsptpd.setString(FLD_NO_SPTPD, entCompareEsptpd.getCompareENoSPTPD());
                pstCompareEsptpd.setString(FLD_NO_SSPD, entCompareEsptpd.getCompareENoSSPD());
                pstCompareEsptpd.update();
                return entCompareEsptpd.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCompareEsptpd(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public static synchronized long updateExcCustom(CompareEsptpd entCompareEsptpd) throws DBException {
        try {
            if (entCompareEsptpd.getCompareENpwpd().length() != 0) {
                Connection connection = null;
                Statement statement = null;

                connection = getConnection();
                statement = getStatement(connection);
                SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String SqlUpdate = ""
                        + "UPDATE " + TBL_ESPTPD + " SET "
                        + fieldNames[FLD_TGL_UBAH] + " = DATE_FORMAT('" + simpledateformat.format(entCompareEsptpd.getCompareETglUbah()) + "', '%Y-%m-%d %T'), "
                        + fieldNames[FLD_NIP_UBAH] + " = '" + entCompareEsptpd.getCompareENIPUbah() + "', "
                        + fieldNames[FLD_ID_UBAH] + " = '" + entCompareEsptpd.getCompareEIDUbah() + "', "
                        + fieldNames[FLD_JUMLAH_OMZET] + " = '" + entCompareEsptpd.getCompareEJumlahOmzet() + "', "
                        + fieldNames[FLD_TARIF] + " = '" + entCompareEsptpd.getCompareETarif() + "', "
                        + fieldNames[FLD_JUMLAH_PAJAK] + " = '" + entCompareEsptpd.getCompareEJumlahPajak() + "', "
                        + fieldNames[FLD_SERVICE_TAX] + " = '" + entCompareEsptpd.getCompareEServiceTax() + "', "
                        + fieldNames[FLD_HARUS_DIBAYAR] + " = '" + entCompareEsptpd.getCompareEHarusBayar() + "', "
                        + fieldNames[FLD_KETERANGAN] + " = '" + entCompareEsptpd.getCompareEKeterangan() + "', "
                        + fieldNames[FLD_KODE_PAJAK] + " = '" + entCompareEsptpd.getCompareEKodePajak() + "', "
                        + fieldNames[FLD_NO_REKENING] + " = '" + entCompareEsptpd.getCompareENoRekening() + "', "
                        + fieldNames[FLD_NO_SUBREKENING] + "= '" + entCompareEsptpd.getCompareENoSubrekening() + "', "
                        + fieldNames[FLD_KODE_LOKASI] + "= '" + entCompareEsptpd.getCompareEKodeLokasi() + "' "
                        + "WHERE " + fieldNames[FLD_NPWPD] + "= '" + entCompareEsptpd.getCompareENpwpd() + "' "
                        + "AND " + fieldNames[FLD_MASA_PAJAK] + "= '" + entCompareEsptpd.getCompareEMasaPajak() + "' "
                        + "AND " + fieldNames[FLD_TAHUN_PAJAK] + "= '" + entCompareEsptpd.getCompareETahunPajak() + "'";
                statement.executeUpdate(SqlUpdate);
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCompareEsptpd(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((CompareEsptpd) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstCompareEsptpd pstCompareEsptpd = new PstCompareEsptpd(oid);
            pstCompareEsptpd.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCompareEsptpd(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(CompareEsptpd entCompareEsptpd) throws DBException {
        try {
            PstCompareEsptpd pstCompareEsptpd = new PstCompareEsptpd(0);
            pstCompareEsptpd.setString(FLD_NPWPD, entCompareEsptpd.getCompareENpwpd());
            pstCompareEsptpd.setDate(FLD_TGL_INPUT, entCompareEsptpd.getCompareETglInput());
            pstCompareEsptpd.setString(FLD_MASA_PAJAK, entCompareEsptpd.getCompareEMasaPajak());
            pstCompareEsptpd.setString(FLD_TAHUN_PAJAK, entCompareEsptpd.getCompareETahunPajak());
            pstCompareEsptpd.setDate(FLD_TGL_REKAM, entCompareEsptpd.getCompareETglRekam());
            pstCompareEsptpd.setString(FLD_NIP_REKAM, entCompareEsptpd.getCompareENIPRekam());
            pstCompareEsptpd.setString(FLD_ID_REKAM, entCompareEsptpd.getCompareEIdRekam());
            pstCompareEsptpd.setDate(FLD_TGL_UBAH, entCompareEsptpd.getCompareETglUbah());
            pstCompareEsptpd.setString(FLD_NIP_UBAH, entCompareEsptpd.getCompareENIPUbah());
            pstCompareEsptpd.setString(FLD_ID_UBAH, entCompareEsptpd.getCompareEIDUbah());
            pstCompareEsptpd.setString(FLD_JUMLAH_OMZET, entCompareEsptpd.getCompareEJumlahOmzet());
            pstCompareEsptpd.setDouble(FLD_TARIF, entCompareEsptpd.getCompareETarif());
            pstCompareEsptpd.setDouble(FLD_JUMLAH_PAJAK, entCompareEsptpd.getCompareEJumlahPajak());
            pstCompareEsptpd.setDouble(FLD_SERVICE_TAX, entCompareEsptpd.getCompareEServiceTax());
            pstCompareEsptpd.setDouble(FLD_DENDA, entCompareEsptpd.getCompareEDenda());
            pstCompareEsptpd.setDouble(FLD_PENGURANGAN, entCompareEsptpd.getCompareEPengurangan());
            pstCompareEsptpd.setDouble(FLD_HARUS_DIBAYAR, entCompareEsptpd.getCompareEHarusBayar());
            pstCompareEsptpd.setString(FLD_KETERANGAN, entCompareEsptpd.getCompareEKeterangan());
            pstCompareEsptpd.setString(FLD_KODE_PAJAK, entCompareEsptpd.getCompareEKodePajak());
            pstCompareEsptpd.setString(FLD_NO_REKENING, entCompareEsptpd.getCompareENoRekening());
            pstCompareEsptpd.setString(FLD_NO_SUBREKENING, entCompareEsptpd.getCompareENoSubrekening());
            pstCompareEsptpd.setString(FLD_KODE_LOKASI, entCompareEsptpd.getCompareEKodeLokasi());
            pstCompareEsptpd.setString(FLD_NO_SPTPD, entCompareEsptpd.getCompareENoSPTPD());
            pstCompareEsptpd.setString(FLD_NO_SSPD, entCompareEsptpd.getCompareENoSSPD());
            pstCompareEsptpd.insert();
            entCompareEsptpd.setOID(pstCompareEsptpd.getLong(FLD_NPWPD));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCompareEsptpd(0), DBException.UNKNOWN);
        }
        return entCompareEsptpd.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((CompareEsptpd) entity);
    }

    public static void resultToObject(ResultSet rs, CompareEsptpd entCompareEsptpd) {
        try {
            entCompareEsptpd.setCompareENpwpd(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NPWPD]));
            entCompareEsptpd.setCompareETglInput(rs.getDate(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TGL_INPUT]));
            entCompareEsptpd.setCompareEMasaPajak(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_MASA_PAJAK]));
            entCompareEsptpd.setCompareETahunPajak(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TAHUN_PAJAK]));
            entCompareEsptpd.setCompareETglRekam(rs.getDate(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TGL_REKAM]));
            entCompareEsptpd.setCompareENIPRekam(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NIP_REKAM]));
            entCompareEsptpd.setCompareEIdRekam(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_ID_REKAM]));
            entCompareEsptpd.setCompareETglUbah(rs.getDate(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TGL_UBAH]));
            entCompareEsptpd.setCompareENIPUbah(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NIP_UBAH]));
            entCompareEsptpd.setCompareEIDUbah(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_ID_UBAH]));
            entCompareEsptpd.setCompareEJumlahOmzet(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_JUMLAH_OMZET]));
            entCompareEsptpd.setCompareETarif(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TARIF]));
            entCompareEsptpd.setCompareEJumlahPajak(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_JUMLAH_PAJAK]));
            entCompareEsptpd.setCompareEServiceTax(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_SERVICE_TAX]));
            entCompareEsptpd.setCompareEDenda(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_DENDA]));
            entCompareEsptpd.setCompareEPengurangan(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_PENGURANGAN]));
            entCompareEsptpd.setCompareEHarusBayar(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_HARUS_DIBAYAR]));
            entCompareEsptpd.setCompareEKeterangan(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_KETERANGAN]));
            entCompareEsptpd.setCompareEKodePajak(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_KODE_PAJAK]));
            entCompareEsptpd.setCompareENoRekening(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NO_REKENING]));
            entCompareEsptpd.setCompareENoSubrekening(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NO_SUBREKENING]));
            entCompareEsptpd.setCompareEKodeLokasi(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_KODE_LOKASI]));
            entCompareEsptpd.setCompareENoSPTPD(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NO_SPTPD]));
            entCompareEsptpd.setCompareENoSSPD(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NO_SSPD]));
        } catch (Exception e) {
        }
    }

    public static void resultToObjectJoin(ResultSet rs, CompareEsptpd entCompareEsptpd) {
        
        try {

            entCompareEsptpd.setCompareEMasaPajak(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_MASA_PAJAK]));
            entCompareEsptpd.setCompareETahunPajak(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TAHUN_PAJAK]));
            entCompareEsptpd.setCompareETglRekam(rs.getDate(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TGL_REKAM]));
            entCompareEsptpd.setCompareEIdRekam(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_ID_REKAM]));
            entCompareEsptpd.setCompareEJumlahOmzet(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_JUMLAH_OMZET]));
            entCompareEsptpd.setCompareETarif(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TARIF]));
            entCompareEsptpd.setCompareEJumlahPajak(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_JUMLAH_PAJAK]));
            entCompareEsptpd.setCompareEServiceTax(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_SERVICE_TAX]));
            entCompareEsptpd.setCompareEHarusBayar(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_HARUS_DIBAYAR]));
            entCompareEsptpd.setCompareENoRekening(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NO_REKENING]));
            entCompareEsptpd.setCompareEKodeLokasi(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_KODE_LOKASI]));
            entCompareEsptpd.setCompareENoSPTPD(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NO_SPTPD]));
            entCompareEsptpd.setCompareEKeterangan(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_KETERANGAN]));
            entCompareEsptpd.setCompareENpwpd(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NPWPD]));
            entCompareEsptpd.setCompareENIPRekam(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NIP_REKAM]));
            entCompareEsptpd.setCompareEIdRekam(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_ID_REKAM]));
            entCompareEsptpd.setCompareEKodePajak(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_KODE_PAJAK]));
            entCompareEsptpd.setCompareENoSubrekening(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NO_SUBREKENING]));
            entCompareEsptpd.setCompareEDenda(rs.getDouble(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_DENDA]));
            entCompareEsptpd.setNamaDocumentUpload(rs.getString(PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_FILE_DOCUMENT_UPLOAD]));
            
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
                CompareEsptpd entCompareEsptpd = new CompareEsptpd();
                resultToObject(rs, entCompareEsptpd);
                lists.add(entCompareEsptpd);
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
                    + "sptpd." + fieldNames[FLD_NO_SUBREKENING] + ", "
                    + "sptpd." + fieldNames[FLD_DENDA] + ", "
                    + "sptpd." + fieldNames[FLD_FILE_DOCUMENT_UPLOAD] + " "
                    + "FROM " + PstAppUserWP.TBL_APPUSERWP + " viewUser "
                    + "INNER JOIN " + TBL_ESPTPD + " sptpd "
                    + "ON viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD] + "=sptpd." + fieldNames[FLD_NPWPD];
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            
            //20150930 opie-eyek
            if(AppSetting.SQL_VERSION==AppSetting.DBSVR_ORACLE){
                sql = sql + "";
            }else{
                if (limitStart == 0 && recordToGet == 0) {
                    sql = sql + "";
                } else {
                    sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                }
            }
            
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                CompareEsptpd entCompareEsptpd = new CompareEsptpd();
                resultToObjectJoin(rs, entCompareEsptpd);
                lists.add(entCompareEsptpd);
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
    
    public static Vector listJoinAll(int limitStart, int recordToGet, String whereClause, String order) {
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
                    + "(sptpd.HARUS_DIBAYAR - sptpd.DENDA) AS " + fieldNames[FLD_HARUS_DIBAYAR] + ","
                    + "sptpd." + fieldNames[FLD_NO_REKENING] + ","
                    + "sptpd." + fieldNames[FLD_KODE_LOKASI] + ","
                    + "sptpd." + fieldNames[FLD_KETERANGAN] + ","
                    + "sptpd." + fieldNames[FLD_NPWPD] + ", "
                    + "sptpd." + fieldNames[FLD_NIP_REKAM] + ", "
                    + "sptpd." + fieldNames[FLD_KODE_PAJAK] + ", "
                    + "sptpd." + fieldNames[FLD_KODE_LOKASI] + ","
                    + "sptpd." + fieldNames[FLD_NO_SUBREKENING] + ", "
                    + "sptpd." + fieldNames[FLD_DENDA] + " "
                    + "FROM " + PstAppUserWP.TBL_APPUSERWP + " viewUser "
                    + "INNER JOIN " + TBL_VIEW_ALL_SIMPATDA + " sptpd "
                    + "ON viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD] + "=sptpd." + fieldNames[FLD_NPWPD];
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            
            //20150930 opie-eyek
            if(AppSetting.SQL_VERSION==AppSetting.DBSVR_ORACLE){
                sql = sql + "";
            }else{
                if (limitStart == 0 && recordToGet == 0) {
                    sql = sql + "";
                } else {
                    sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                }
            }
            
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                CompareEsptpd entCompareEsptpd = new CompareEsptpd();
                resultToObjectJoin(rs, entCompareEsptpd);
                lists.add(entCompareEsptpd);
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
    
    
    public static Vector listJoindua(int limitStart, int recordToGet, String whereClause, String order) {
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
                    + "sptpd." + fieldNames[FLD_NO_SUBREKENING] + ", "
                    + "sptpd." + fieldNames[FLD_DENDA] + " "
                    + "FROM " + PstAppUserWP.TBL_APPUSERWP + " viewUser "
                    + "INNER JOIN " + TBL_ESPTPD_NEW + " sptpd "
                    + "ON viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD] + "=sptpd." + fieldNames[FLD_NPWPD];
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            
            //20150930 opie-eyek
            if(AppSetting.SQL_VERSION==AppSetting.DBSVR_ORACLE){
                sql = sql + "";
            }else{
                if (limitStart == 0 && recordToGet == 0) {
                    sql = sql + "";
                } else {
                    sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                }
            }
            
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                CompareEsptpd entCompareEsptpd = new CompareEsptpd();
                resultToObjectJoin(rs, entCompareEsptpd);
                lists.add(entCompareEsptpd);
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
    
    public static Vector listJoinDenda(int limitStart, int recordToGet, String whereClause, String order, Vector lists) {
       // Vector lists = new Vector();
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
                    + "sptpd." + fieldNames[FLD_NO_SUBREKENING] + ", "
                    + "sptpd." + fieldNames[FLD_DENDA] + " "
                    + "FROM " + PstAppUserWP.TBL_APPUSERWP + " viewUser "
                    + "INNER JOIN " + TBL_ESPTPD_DENDA + " sptpd "
                    + "ON viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD] + "=sptpd." + fieldNames[FLD_NPWPD];
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            
            //20150930 opie-eyek
            if(AppSetting.SQL_VERSION==AppSetting.DBSVR_ORACLE){
                sql = sql + "";
            }else{
                if (limitStart == 0 && recordToGet == 0) {
                    sql = sql + "";
                } else {
                    sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                }
            }
            
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                CompareEsptpd entCompareEsptpd = new CompareEsptpd();
                resultToObjectJoin(rs, entCompareEsptpd);
                lists.add(entCompareEsptpd);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return lists;
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

    public static boolean checkOID(long entCompareEsptpdId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_ESPTPD + " WHERE "
                    + PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NPWPD] + " = " + entCompareEsptpdId;
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
            String sql = "SELECT COUNT(" + PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NPWPD] + ") FROM " + TBL_ESPTPD;
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
                    CompareEsptpd entCompareEsptpd = (CompareEsptpd) list.get(ls);
                    if (oid == entCompareEsptpd.getOID()) {
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
    
    
    public static Vector listJoinESPTPDNOL(int limitStart, int recordToGet, String whereClause, String order, Vector lists) {
       // Vector lists = new Vector();
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
                    + "sptpd." + fieldNames[FLD_NO_SUBREKENING] + ", "
                    + "sptpd." + fieldNames[FLD_DENDA] + " "
                    + "FROM " + PstAppUserWP.TBL_APPUSERWP + " viewUser "
                    + "INNER JOIN " + TBL_ALL_ESPTPD + " sptpd "
                    + "ON viewUser." + PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD] + "=sptpd." + fieldNames[FLD_NPWPD];
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            
            //20150930 opie-eyek
            if(AppSetting.SQL_VERSION==AppSetting.DBSVR_ORACLE){
                sql = sql + "";
            }else{
                if (limitStart == 0 && recordToGet == 0) {
                    sql = sql + "";
                } else {
                    sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                }
            }
            
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                CompareEsptpd entCompareEsptpd = new CompareEsptpd();
                resultToObjectJoin(rs, entCompareEsptpd);
                lists.add(entCompareEsptpd);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return lists;
    }
    
}
