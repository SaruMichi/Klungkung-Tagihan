/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.debiturindividu;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.masterdata.PstBank;
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstDebiturIndividu extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    private String sqlQueryHistory = "";

    public static final String TBL_DEBITUR_INDIVIDU = "dslik_debitur";
    public static final int FLD_DEBITUR_INDIVIDU_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_CIF = 2;
    public static final int FLD_JENIS_IDENTITAS = 3;
    public static final int FLD_NIK = 4;
    public static final int FLD_NAMA_IDENTITAS = 5;
    public static final int FLD_NAMA_LENGKAP = 6;
    public static final int FLD_KODE_STATUS_GELAR = 7;
    public static final int FLD_JEKEL = 8;
    public static final int FLD_TEMPAT_LAHIR = 9;
    public static final int FLD_TGL_LAHIR = 10;
    public static final int FLD_NPWP = 11;
    public static final int FLD_ALAMAT = 12;
    public static final int FLD_KELURAHAN = 13;
    public static final int FLD_KECAMATAN = 14;
    public static final int FLD_KODE_KAB = 15;
    public static final int FLD_KODE_POS = 16;
    public static final int FLD_TELEPON = 17;
    public static final int FLD_NOMOR_HP = 18;
    public static final int FLD_EMAIL = 19;
    public static final int FLD_KODE_DOMISILI = 20;
    public static final int FLD_KODE_PEKERJAAN = 21;
    public static final int FLD_TEMPAT_BEKERJA = 22;
    public static final int FLD_KODE_USAHA_TEMPAT_BEKERJA = 23;
    public static final int FLD_ALAMAT_TEMPAT_BEKERJA = 24;
    public static final int FLD_PENGHASILAN_KOTOR = 25;
    public static final int FLD_KODE_PENGHASILAN = 26;
    public static final int FLD_JML_TANGGUNGAN = 27;
    public static final int FLD_KODE_HUB = 28;
    public static final int FLD_KODE_GOL = 29;
    public static final int FLD_STATUS = 30;
    public static final int FLD_NIK_PASANGAN = 31;
    public static final int FLD_NAMA_PASANGAN = 32;
    public static final int FLD_TGL_LAHIR_PASANGAN = 33;
    public static final int FLD_PERJANJIAN_PISAH_HARGA = 34;
    public static final int FLD_MELANGGAR_BMPK = 35;
    public static final int FLD_MELAMPAUI_BMPK = 36;
    public static final int FLD_NAMA_IBU_KANDUNG = 37;
    public static final int FLD_KODE_KANTOR_CABANG = 38;
    public static final int FLD_OPERASI_DATA = 39;
    public static final int FLD_PERIODE_ID = 40;
    public static final int FLD_KODE_JENIS_NSB = 41;
    public static final int FLD_STATUS_PERUBAHAN_DATA = 42;
    public static final int FLD_STATUS_DATA = 43;
    public static String[] fieldNames = {
        "DEBITUR_OID",
        "FLAG_DETAIL",
        "CIF",
        "JENIS_IDENTITAS",
        "NIK",
        "NAMA_IDENTITAS",
        "NAMA_LENGKAP",
        "KODE_STATUS_GELAR",
        "JEKEL",
        "TEMPAT_LAHIR",
        "TANGGAL_LAHIR",
        "NPWP",
        "ALAMAT",
        "KELURAHAN",
        "KECAMATAN",
        "KODE_KAB",
        "KODE_POS",
        "TELEPON",
        "NOMOR_HP",
        "EMAIL",
        "KODE_DOMISILI",
        "KODE_PEKERJAAN",
        "TEMPAT_BEKERJA",
        "KODE_USAHA_TEMPAT_BEKERJA",
        "ALAMAT_TEMPAT_BEKERJA",
        "PENGHASILAN_KOTOR",
        "KODE_PENGHASILAN",
        "JML_TANGGUNGAN",
        "KODE_HUB",
        "KODE_GOL",
        "STATUS",
        "NIK_PASANGAN",
        "NAMA_PASANGAN",
        "TGL_LAHIR_PASANGAN",
        "PERJANJIAN_PISAH_HARGA",
        "MELANGGAR_BMPK",
        "MELAMPAUI_BMPK",
        "NAMA_IBU_KANDUNG",
        "KODE_KANTOR_CABANG",
        "OPERASI_DATA",
        "PERIODE_ID",
        "KODE_JENIS_NSB",
        "STATUS_DATA",
        "STATUS_OPERASI_DATA"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
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
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_INT,
        TYPE_INT
    };

    public PstDebiturIndividu() {
    }

    public PstDebiturIndividu(int i) throws DBException {
        super(new PstDebiturIndividu());
    }

    public PstDebiturIndividu(String sOid) throws DBException {
        super(new PstDebiturIndividu(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstDebiturIndividu(long lOid) throws DBException {
        super(new PstDebiturIndividu(0));
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
        return TBL_DEBITUR_INDIVIDU;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstDebiturIndividu().getClass().getName();
    }

    public static DebiturIndividu fetchExc(long oid) throws DBException {
        try {
            DebiturIndividu entDebiturIndividu = new DebiturIndividu();
            PstDebiturIndividu pstDebiturIndividu = new PstDebiturIndividu(oid);
            entDebiturIndividu.setOID(oid);
            entDebiturIndividu.setFlagDetail(pstDebiturIndividu.getString(FLD_FLAG_DETAIL));
            entDebiturIndividu.setCif(pstDebiturIndividu.getString(FLD_CIF));
            entDebiturIndividu.setJenisIdentitas(pstDebiturIndividu.getString(FLD_JENIS_IDENTITAS));
            entDebiturIndividu.setNik(pstDebiturIndividu.getString(FLD_NIK));
            entDebiturIndividu.setNamaIdentitas(pstDebiturIndividu.getString(FLD_NAMA_IDENTITAS));
            entDebiturIndividu.setNamaLengkap(pstDebiturIndividu.getString(FLD_NAMA_LENGKAP));
            entDebiturIndividu.setKodeStatusGelar(pstDebiturIndividu.getString(FLD_KODE_STATUS_GELAR));
            entDebiturIndividu.setJekel(pstDebiturIndividu.getString(FLD_JEKEL));
            entDebiturIndividu.setTempatLahir(pstDebiturIndividu.getString(FLD_TEMPAT_LAHIR));
            entDebiturIndividu.setTglLahir(pstDebiturIndividu.getDate(FLD_TGL_LAHIR));
            entDebiturIndividu.setNpwp(pstDebiturIndividu.getString(FLD_NPWP));
            entDebiturIndividu.setAlamat(pstDebiturIndividu.getString(FLD_ALAMAT));
            entDebiturIndividu.setKelurahan(pstDebiturIndividu.getString(FLD_KELURAHAN));
            entDebiturIndividu.setKecamatan(pstDebiturIndividu.getString(FLD_KECAMATAN));
            entDebiturIndividu.setKodeKab(pstDebiturIndividu.getString(FLD_KODE_KAB));
            entDebiturIndividu.setKodePos(pstDebiturIndividu.getString(FLD_KODE_POS));
            entDebiturIndividu.setTelepon(pstDebiturIndividu.getString(FLD_TELEPON));
            entDebiturIndividu.setNomorHp(pstDebiturIndividu.getString(FLD_NOMOR_HP));
            entDebiturIndividu.setEmail(pstDebiturIndividu.getString(FLD_EMAIL));
            entDebiturIndividu.setKodeDomisili(pstDebiturIndividu.getString(FLD_KODE_DOMISILI));
            entDebiturIndividu.setKodePekerjaan(pstDebiturIndividu.getString(FLD_KODE_PEKERJAAN));
            entDebiturIndividu.setTempatBekerja(pstDebiturIndividu.getString(FLD_TEMPAT_BEKERJA));
            entDebiturIndividu.setKodeUsahaTempatBekerja(pstDebiturIndividu.getString(FLD_KODE_USAHA_TEMPAT_BEKERJA));
            entDebiturIndividu.setAlamatTempatBekerja(pstDebiturIndividu.getString(FLD_ALAMAT_TEMPAT_BEKERJA));
            entDebiturIndividu.setPenghasilanKotor(pstDebiturIndividu.getdouble(FLD_PENGHASILAN_KOTOR));
            entDebiturIndividu.setKodePenghasilan(pstDebiturIndividu.getString(FLD_KODE_PENGHASILAN));
            entDebiturIndividu.setJmlTanggungan(pstDebiturIndividu.getInt(FLD_JML_TANGGUNGAN));
            entDebiturIndividu.setKodeHub(pstDebiturIndividu.getString(FLD_KODE_HUB));
            entDebiturIndividu.setKodeGol(pstDebiturIndividu.getString(FLD_KODE_GOL));
            entDebiturIndividu.setStatus(pstDebiturIndividu.getString(FLD_STATUS));
            entDebiturIndividu.setNikPasangan(pstDebiturIndividu.getString(FLD_NIK_PASANGAN));
            entDebiturIndividu.setNamaPasangan(pstDebiturIndividu.getString(FLD_NAMA_PASANGAN));
            entDebiturIndividu.setTglLahirPasangan(pstDebiturIndividu.getDate(FLD_TGL_LAHIR_PASANGAN));
            entDebiturIndividu.setPerjanjianPisahHarga(pstDebiturIndividu.getString(FLD_PERJANJIAN_PISAH_HARGA));
            entDebiturIndividu.setMelanggarBmpk(pstDebiturIndividu.getString(FLD_MELANGGAR_BMPK));
            entDebiturIndividu.setMelampauiBmpk(pstDebiturIndividu.getString(FLD_MELAMPAUI_BMPK));
            entDebiturIndividu.setNamaIbuKandung(pstDebiturIndividu.getString(FLD_NAMA_IBU_KANDUNG));
            entDebiturIndividu.setKodeKantorCabang(pstDebiturIndividu.getString(FLD_KODE_KANTOR_CABANG));
            entDebiturIndividu.setOperasiData(pstDebiturIndividu.getString(FLD_OPERASI_DATA));
            entDebiturIndividu.setPeriodeId(pstDebiturIndividu.getlong(FLD_PERIODE_ID));
            entDebiturIndividu.setKodeJenisNsb(pstDebiturIndividu.getString(FLD_KODE_JENIS_NSB));
            entDebiturIndividu.setStatusData(pstDebiturIndividu.getInt(FLD_STATUS_PERUBAHAN_DATA));
            entDebiturIndividu.setStatusOperasiData(pstDebiturIndividu.getInt( FLD_STATUS_DATA));
            
            return entDebiturIndividu;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebiturIndividu(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        DebiturIndividu entDebiturIndividu = fetchExc(entity.getOID());
        entity = (Entity) entDebiturIndividu;
        return entDebiturIndividu.getOID();
    }

    public static synchronized long updateExc(DebiturIndividu entDebiturIndividu) throws DBException {
        try {
            if (entDebiturIndividu.getOID() != 0) {
                PstDebiturIndividu pstDebiturIndividu = new PstDebiturIndividu(entDebiturIndividu.getOID());
                pstDebiturIndividu.setString(FLD_FLAG_DETAIL, entDebiturIndividu.getFlagDetail());
                pstDebiturIndividu.setString(FLD_CIF, entDebiturIndividu.getCif());
                pstDebiturIndividu.setString(FLD_JENIS_IDENTITAS, entDebiturIndividu.getJenisIdentitas());
                pstDebiturIndividu.setString(FLD_NIK, entDebiturIndividu.getNik());
                pstDebiturIndividu.setString(FLD_NAMA_IDENTITAS, entDebiturIndividu.getNamaIdentitas());
                pstDebiturIndividu.setString(FLD_NAMA_LENGKAP, entDebiturIndividu.getNamaLengkap());
                pstDebiturIndividu.setString(FLD_KODE_STATUS_GELAR, entDebiturIndividu.getKodeStatusGelar());
                pstDebiturIndividu.setString(FLD_JEKEL, entDebiturIndividu.getJekel());
                pstDebiturIndividu.setString(FLD_TEMPAT_LAHIR, entDebiturIndividu.getTempatLahir());
                pstDebiturIndividu.setDate(FLD_TGL_LAHIR, entDebiturIndividu.getTglLahir());
                pstDebiturIndividu.setString(FLD_NPWP, entDebiturIndividu.getNpwp());
                pstDebiturIndividu.setString(FLD_ALAMAT, entDebiturIndividu.getAlamat());
                pstDebiturIndividu.setString(FLD_KELURAHAN, entDebiturIndividu.getKelurahan());
                pstDebiturIndividu.setString(FLD_KECAMATAN, entDebiturIndividu.getKecamatan());
                pstDebiturIndividu.setString(FLD_KODE_KAB, entDebiturIndividu.getKodeKab());
                pstDebiturIndividu.setString(FLD_KODE_POS, entDebiturIndividu.getKodePos());
                pstDebiturIndividu.setString(FLD_TELEPON, entDebiturIndividu.getTelepon());
                pstDebiturIndividu.setString(FLD_NOMOR_HP, entDebiturIndividu.getNomorHp());
                pstDebiturIndividu.setString(FLD_EMAIL, entDebiturIndividu.getEmail());
                pstDebiturIndividu.setString(FLD_KODE_DOMISILI, entDebiturIndividu.getKodeDomisili());
                pstDebiturIndividu.setString(FLD_KODE_PEKERJAAN, entDebiturIndividu.getKodePekerjaan());
                pstDebiturIndividu.setString(FLD_TEMPAT_BEKERJA, entDebiturIndividu.getTempatBekerja());
                pstDebiturIndividu.setString(FLD_KODE_USAHA_TEMPAT_BEKERJA, entDebiturIndividu.getKodeUsahaTempatBekerja());
                pstDebiturIndividu.setString(FLD_ALAMAT_TEMPAT_BEKERJA, entDebiturIndividu.getAlamatTempatBekerja());
                pstDebiturIndividu.setDouble(FLD_PENGHASILAN_KOTOR, entDebiturIndividu.getPenghasilanKotor());
                pstDebiturIndividu.setString(FLD_KODE_PENGHASILAN, entDebiturIndividu.getKodePenghasilan());
                pstDebiturIndividu.setInt(FLD_JML_TANGGUNGAN, entDebiturIndividu.getJmlTanggungan());
                pstDebiturIndividu.setString(FLD_KODE_HUB, entDebiturIndividu.getKodeHub());
                pstDebiturIndividu.setString(FLD_KODE_GOL, entDebiturIndividu.getKodeGol());
                pstDebiturIndividu.setString(FLD_STATUS, entDebiturIndividu.getStatus());
                pstDebiturIndividu.setString(FLD_NIK_PASANGAN, entDebiturIndividu.getNikPasangan());
                pstDebiturIndividu.setString(FLD_NAMA_PASANGAN, entDebiturIndividu.getNamaPasangan());
                pstDebiturIndividu.setDate(FLD_TGL_LAHIR_PASANGAN, entDebiturIndividu.getTglLahirPasangan());
                pstDebiturIndividu.setString(FLD_PERJANJIAN_PISAH_HARGA, entDebiturIndividu.getPerjanjianPisahHarga());
                pstDebiturIndividu.setString(FLD_MELANGGAR_BMPK, entDebiturIndividu.getMelanggarBmpk());
                pstDebiturIndividu.setString(FLD_MELAMPAUI_BMPK, entDebiturIndividu.getMelampauiBmpk());
                pstDebiturIndividu.setString(FLD_NAMA_IBU_KANDUNG, entDebiturIndividu.getNamaIbuKandung());
                pstDebiturIndividu.setString(FLD_KODE_KANTOR_CABANG, entDebiturIndividu.getKodeKantorCabang());
                pstDebiturIndividu.setString(FLD_OPERASI_DATA, entDebiturIndividu.getOperasiData());
                pstDebiturIndividu.setLong(FLD_PERIODE_ID, entDebiturIndividu.getPeriodeId());
                pstDebiturIndividu.setString(FLD_KODE_JENIS_NSB, entDebiturIndividu.getKodeJenisNsb());
                pstDebiturIndividu.setInt(FLD_STATUS_PERUBAHAN_DATA, entDebiturIndividu.getStatusData());
                pstDebiturIndividu.setInt(FLD_STATUS_DATA,entDebiturIndividu.getStatusOperasiData());
                
                pstDebiturIndividu.update();
                
                pstDebiturIndividu.setSqlQueryHistory("");
                pstDebiturIndividu.setSqlQueryHistory(pstDebiturIndividu.getInsertSQL());
                
                return entDebiturIndividu.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebiturIndividu(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized DebiturIndividu updateExcObj(DebiturIndividu entDebiturIndividu) throws DBException {
        try {
            if (entDebiturIndividu.getOID() != 0) {
                PstDebiturIndividu pstDebiturIndividu = new PstDebiturIndividu(entDebiturIndividu.getOID());
                pstDebiturIndividu.setString(FLD_FLAG_DETAIL, entDebiturIndividu.getFlagDetail());
                pstDebiturIndividu.setString(FLD_CIF, entDebiturIndividu.getCif());
                pstDebiturIndividu.setString(FLD_JENIS_IDENTITAS, entDebiturIndividu.getJenisIdentitas());
                pstDebiturIndividu.setString(FLD_NIK, entDebiturIndividu.getNik());
                pstDebiturIndividu.setString(FLD_NAMA_IDENTITAS, entDebiturIndividu.getNamaIdentitas());
                pstDebiturIndividu.setString(FLD_NAMA_LENGKAP, entDebiturIndividu.getNamaLengkap());
                pstDebiturIndividu.setString(FLD_KODE_STATUS_GELAR, entDebiturIndividu.getKodeStatusGelar());
                pstDebiturIndividu.setString(FLD_JEKEL, entDebiturIndividu.getJekel());
                pstDebiturIndividu.setString(FLD_TEMPAT_LAHIR, entDebiturIndividu.getTempatLahir());
                pstDebiturIndividu.setDate(FLD_TGL_LAHIR, entDebiturIndividu.getTglLahir());
                pstDebiturIndividu.setString(FLD_NPWP, entDebiturIndividu.getNpwp());
                pstDebiturIndividu.setString(FLD_ALAMAT, entDebiturIndividu.getAlamat());
                pstDebiturIndividu.setString(FLD_KELURAHAN, entDebiturIndividu.getKelurahan());
                pstDebiturIndividu.setString(FLD_KECAMATAN, entDebiturIndividu.getKecamatan());
                pstDebiturIndividu.setString(FLD_KODE_KAB, entDebiturIndividu.getKodeKab());
                pstDebiturIndividu.setString(FLD_KODE_POS, entDebiturIndividu.getKodePos());
                pstDebiturIndividu.setString(FLD_TELEPON, entDebiturIndividu.getTelepon());
                pstDebiturIndividu.setString(FLD_NOMOR_HP, entDebiturIndividu.getNomorHp());
                pstDebiturIndividu.setString(FLD_EMAIL, entDebiturIndividu.getEmail());
                pstDebiturIndividu.setString(FLD_KODE_DOMISILI, entDebiturIndividu.getKodeDomisili());
                pstDebiturIndividu.setString(FLD_KODE_PEKERJAAN, entDebiturIndividu.getKodePekerjaan());
                pstDebiturIndividu.setString(FLD_TEMPAT_BEKERJA, entDebiturIndividu.getTempatBekerja());
                pstDebiturIndividu.setString(FLD_KODE_USAHA_TEMPAT_BEKERJA, entDebiturIndividu.getKodeUsahaTempatBekerja());
                pstDebiturIndividu.setString(FLD_ALAMAT_TEMPAT_BEKERJA, entDebiturIndividu.getAlamatTempatBekerja());
                pstDebiturIndividu.setDouble(FLD_PENGHASILAN_KOTOR, entDebiturIndividu.getPenghasilanKotor());
                pstDebiturIndividu.setString(FLD_KODE_PENGHASILAN, entDebiturIndividu.getKodePenghasilan());
                pstDebiturIndividu.setInt(FLD_JML_TANGGUNGAN, entDebiturIndividu.getJmlTanggungan());
                pstDebiturIndividu.setString(FLD_KODE_HUB, entDebiturIndividu.getKodeHub());
                pstDebiturIndividu.setString(FLD_KODE_GOL, entDebiturIndividu.getKodeGol());
                pstDebiturIndividu.setString(FLD_STATUS, entDebiturIndividu.getStatus());
                pstDebiturIndividu.setString(FLD_NIK_PASANGAN, entDebiturIndividu.getNikPasangan());
                pstDebiturIndividu.setString(FLD_NAMA_PASANGAN, entDebiturIndividu.getNamaPasangan());
                pstDebiturIndividu.setDate(FLD_TGL_LAHIR_PASANGAN, entDebiturIndividu.getTglLahirPasangan());
                pstDebiturIndividu.setString(FLD_PERJANJIAN_PISAH_HARGA, entDebiturIndividu.getPerjanjianPisahHarga());
                pstDebiturIndividu.setString(FLD_MELANGGAR_BMPK, entDebiturIndividu.getMelanggarBmpk());
                pstDebiturIndividu.setString(FLD_MELAMPAUI_BMPK, entDebiturIndividu.getMelampauiBmpk());
                pstDebiturIndividu.setString(FLD_NAMA_IBU_KANDUNG, entDebiturIndividu.getNamaIbuKandung());
                pstDebiturIndividu.setString(FLD_KODE_KANTOR_CABANG, entDebiturIndividu.getKodeKantorCabang());
                pstDebiturIndividu.setString(FLD_OPERASI_DATA, entDebiturIndividu.getOperasiData());
                pstDebiturIndividu.setLong(FLD_PERIODE_ID, entDebiturIndividu.getPeriodeId());
                pstDebiturIndividu.setString(FLD_KODE_JENIS_NSB, entDebiturIndividu.getKodeJenisNsb());
                pstDebiturIndividu.setInt(FLD_STATUS_PERUBAHAN_DATA, entDebiturIndividu.getStatusData());
                pstDebiturIndividu.setInt(FLD_STATUS_DATA,entDebiturIndividu.getStatusOperasiData());
                pstDebiturIndividu.update();
                
                entDebiturIndividu.setHistorySql(pstDebiturIndividu.getInsertSQL());
                return entDebiturIndividu;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebiturIndividu(0), DBException.UNKNOWN);
        }
        return entDebiturIndividu;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((DebiturIndividu) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstDebiturIndividu pstDebiturIndividu = new PstDebiturIndividu(oid);
            pstDebiturIndividu.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebiturIndividu(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(DebiturIndividu entDebiturIndividu) throws DBException {
        try {
            PstDebiturIndividu pstDebiturIndividu = new PstDebiturIndividu(0);
            pstDebiturIndividu.setString(FLD_FLAG_DETAIL, entDebiturIndividu.getFlagDetail());
            pstDebiturIndividu.setString(FLD_CIF, entDebiturIndividu.getCif());
            pstDebiturIndividu.setString(FLD_JENIS_IDENTITAS, entDebiturIndividu.getJenisIdentitas());
            pstDebiturIndividu.setString(FLD_NIK, entDebiturIndividu.getNik());
            pstDebiturIndividu.setString(FLD_NAMA_IDENTITAS, entDebiturIndividu.getNamaIdentitas());
            pstDebiturIndividu.setString(FLD_NAMA_LENGKAP, entDebiturIndividu.getNamaLengkap());
            pstDebiturIndividu.setString(FLD_KODE_STATUS_GELAR, entDebiturIndividu.getKodeStatusGelar());
            pstDebiturIndividu.setString(FLD_JEKEL, entDebiturIndividu.getJekel());
            pstDebiturIndividu.setString(FLD_TEMPAT_LAHIR, entDebiturIndividu.getTempatLahir());
            pstDebiturIndividu.setDate(FLD_TGL_LAHIR, entDebiturIndividu.getTglLahir());
            pstDebiturIndividu.setString(FLD_NPWP, entDebiturIndividu.getNpwp());
            pstDebiturIndividu.setString(FLD_ALAMAT, entDebiturIndividu.getAlamat());
            pstDebiturIndividu.setString(FLD_KELURAHAN, entDebiturIndividu.getKelurahan());
            pstDebiturIndividu.setString(FLD_KECAMATAN, entDebiturIndividu.getKecamatan());
            pstDebiturIndividu.setString(FLD_KODE_KAB, entDebiturIndividu.getKodeKab());
            pstDebiturIndividu.setString(FLD_KODE_POS, entDebiturIndividu.getKodePos());
            pstDebiturIndividu.setString(FLD_TELEPON, entDebiturIndividu.getTelepon());
            pstDebiturIndividu.setString(FLD_NOMOR_HP, entDebiturIndividu.getNomorHp());
            pstDebiturIndividu.setString(FLD_EMAIL, entDebiturIndividu.getEmail());
            pstDebiturIndividu.setString(FLD_KODE_DOMISILI, entDebiturIndividu.getKodeDomisili());
            pstDebiturIndividu.setString(FLD_KODE_PEKERJAAN, entDebiturIndividu.getKodePekerjaan());
            pstDebiturIndividu.setString(FLD_TEMPAT_BEKERJA, entDebiturIndividu.getTempatBekerja());
            pstDebiturIndividu.setString(FLD_KODE_USAHA_TEMPAT_BEKERJA, entDebiturIndividu.getKodeUsahaTempatBekerja());
            pstDebiturIndividu.setString(FLD_ALAMAT_TEMPAT_BEKERJA, entDebiturIndividu.getAlamatTempatBekerja());
            pstDebiturIndividu.setDouble(FLD_PENGHASILAN_KOTOR, entDebiturIndividu.getPenghasilanKotor());
            pstDebiturIndividu.setString(FLD_KODE_PENGHASILAN, entDebiturIndividu.getKodePenghasilan());
            pstDebiturIndividu.setInt(FLD_JML_TANGGUNGAN, entDebiturIndividu.getJmlTanggungan());
            pstDebiturIndividu.setString(FLD_KODE_HUB, entDebiturIndividu.getKodeHub());
            pstDebiturIndividu.setString(FLD_KODE_GOL, entDebiturIndividu.getKodeGol());
            pstDebiturIndividu.setString(FLD_STATUS, entDebiturIndividu.getStatus());
            pstDebiturIndividu.setString(FLD_NIK_PASANGAN, entDebiturIndividu.getNikPasangan());
            pstDebiturIndividu.setString(FLD_NAMA_PASANGAN, entDebiturIndividu.getNamaPasangan());
            pstDebiturIndividu.setDate(FLD_TGL_LAHIR_PASANGAN, entDebiturIndividu.getTglLahirPasangan());
            pstDebiturIndividu.setString(FLD_PERJANJIAN_PISAH_HARGA, entDebiturIndividu.getPerjanjianPisahHarga());
            pstDebiturIndividu.setString(FLD_MELANGGAR_BMPK, entDebiturIndividu.getMelanggarBmpk());
            pstDebiturIndividu.setString(FLD_MELAMPAUI_BMPK, entDebiturIndividu.getMelampauiBmpk());
            pstDebiturIndividu.setString(FLD_NAMA_IBU_KANDUNG, entDebiturIndividu.getNamaIbuKandung());
            pstDebiturIndividu.setString(FLD_KODE_KANTOR_CABANG, entDebiturIndividu.getKodeKantorCabang());
            pstDebiturIndividu.setString(FLD_OPERASI_DATA, entDebiturIndividu.getOperasiData());
            pstDebiturIndividu.setLong(FLD_PERIODE_ID, entDebiturIndividu.getPeriodeId());
            pstDebiturIndividu.setString(FLD_KODE_JENIS_NSB, entDebiturIndividu.getKodeJenisNsb());
            pstDebiturIndividu.setInt(FLD_STATUS_PERUBAHAN_DATA, entDebiturIndividu.getStatusData());
            pstDebiturIndividu.setInt(FLD_STATUS_DATA,entDebiturIndividu.getStatusOperasiData());
            pstDebiturIndividu.insert();
            
            pstDebiturIndividu.setSqlQueryHistory("");
            pstDebiturIndividu.setSqlQueryHistory(pstDebiturIndividu.getInsertSQL());
                
            entDebiturIndividu.setOID(pstDebiturIndividu.getlong(FLD_DEBITUR_INDIVIDU_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebiturIndividu(0), DBException.UNKNOWN);
        }
        return entDebiturIndividu.getOID();
    }
    
    public static synchronized DebiturIndividu insertExcObj(DebiturIndividu entDebiturIndividu) throws DBException {
        try {
            PstDebiturIndividu pstDebiturIndividu = new PstDebiturIndividu(0);
            pstDebiturIndividu.setString(FLD_FLAG_DETAIL, entDebiturIndividu.getFlagDetail());
            pstDebiturIndividu.setString(FLD_CIF, entDebiturIndividu.getCif());
            pstDebiturIndividu.setString(FLD_JENIS_IDENTITAS, entDebiturIndividu.getJenisIdentitas());
            pstDebiturIndividu.setString(FLD_NIK, entDebiturIndividu.getNik());
            pstDebiturIndividu.setString(FLD_NAMA_IDENTITAS, entDebiturIndividu.getNamaIdentitas());
            pstDebiturIndividu.setString(FLD_NAMA_LENGKAP, entDebiturIndividu.getNamaLengkap());
            pstDebiturIndividu.setString(FLD_KODE_STATUS_GELAR, entDebiturIndividu.getKodeStatusGelar());
            pstDebiturIndividu.setString(FLD_JEKEL, entDebiturIndividu.getJekel());
            pstDebiturIndividu.setString(FLD_TEMPAT_LAHIR, entDebiturIndividu.getTempatLahir());
            pstDebiturIndividu.setDate(FLD_TGL_LAHIR, entDebiturIndividu.getTglLahir());
            pstDebiturIndividu.setString(FLD_NPWP, entDebiturIndividu.getNpwp());
            pstDebiturIndividu.setString(FLD_ALAMAT, entDebiturIndividu.getAlamat());
            pstDebiturIndividu.setString(FLD_KELURAHAN, entDebiturIndividu.getKelurahan());
            pstDebiturIndividu.setString(FLD_KECAMATAN, entDebiturIndividu.getKecamatan());
            pstDebiturIndividu.setString(FLD_KODE_KAB, entDebiturIndividu.getKodeKab());
            pstDebiturIndividu.setString(FLD_KODE_POS, entDebiturIndividu.getKodePos());
            pstDebiturIndividu.setString(FLD_TELEPON, entDebiturIndividu.getTelepon());
            pstDebiturIndividu.setString(FLD_NOMOR_HP, entDebiturIndividu.getNomorHp());
            pstDebiturIndividu.setString(FLD_EMAIL, entDebiturIndividu.getEmail());
            pstDebiturIndividu.setString(FLD_KODE_DOMISILI, entDebiturIndividu.getKodeDomisili());
            pstDebiturIndividu.setString(FLD_KODE_PEKERJAAN, entDebiturIndividu.getKodePekerjaan());
            pstDebiturIndividu.setString(FLD_TEMPAT_BEKERJA, entDebiturIndividu.getTempatBekerja());
            pstDebiturIndividu.setString(FLD_KODE_USAHA_TEMPAT_BEKERJA, entDebiturIndividu.getKodeUsahaTempatBekerja());
            pstDebiturIndividu.setString(FLD_ALAMAT_TEMPAT_BEKERJA, entDebiturIndividu.getAlamatTempatBekerja());
            pstDebiturIndividu.setDouble(FLD_PENGHASILAN_KOTOR, entDebiturIndividu.getPenghasilanKotor());
            pstDebiturIndividu.setString(FLD_KODE_PENGHASILAN, entDebiturIndividu.getKodePenghasilan());
            pstDebiturIndividu.setInt(FLD_JML_TANGGUNGAN, entDebiturIndividu.getJmlTanggungan());
            pstDebiturIndividu.setString(FLD_KODE_HUB, entDebiturIndividu.getKodeHub());
            pstDebiturIndividu.setString(FLD_KODE_GOL, entDebiturIndividu.getKodeGol());
            pstDebiturIndividu.setString(FLD_STATUS, entDebiturIndividu.getStatus());
            pstDebiturIndividu.setString(FLD_NIK_PASANGAN, entDebiturIndividu.getNikPasangan());
            pstDebiturIndividu.setString(FLD_NAMA_PASANGAN, entDebiturIndividu.getNamaPasangan());
            pstDebiturIndividu.setDate(FLD_TGL_LAHIR_PASANGAN, entDebiturIndividu.getTglLahirPasangan());
            pstDebiturIndividu.setString(FLD_PERJANJIAN_PISAH_HARGA, entDebiturIndividu.getPerjanjianPisahHarga());
            pstDebiturIndividu.setString(FLD_MELANGGAR_BMPK, entDebiturIndividu.getMelanggarBmpk());
            pstDebiturIndividu.setString(FLD_MELAMPAUI_BMPK, entDebiturIndividu.getMelampauiBmpk());
            pstDebiturIndividu.setString(FLD_NAMA_IBU_KANDUNG, entDebiturIndividu.getNamaIbuKandung());
            pstDebiturIndividu.setString(FLD_KODE_KANTOR_CABANG, entDebiturIndividu.getKodeKantorCabang());
            pstDebiturIndividu.setString(FLD_OPERASI_DATA, entDebiturIndividu.getOperasiData());
            pstDebiturIndividu.setLong(FLD_PERIODE_ID, entDebiturIndividu.getPeriodeId());
            pstDebiturIndividu.setString(FLD_KODE_JENIS_NSB, entDebiturIndividu.getKodeJenisNsb());
            pstDebiturIndividu.setInt(FLD_STATUS_PERUBAHAN_DATA, entDebiturIndividu.getStatusData());
            pstDebiturIndividu.setInt(FLD_STATUS_DATA,entDebiturIndividu.getStatusOperasiData());
            pstDebiturIndividu.insert();
            
            pstDebiturIndividu.setSqlQueryHistory("");
            pstDebiturIndividu.setSqlQueryHistory(pstDebiturIndividu.getInsertSQL());
            entDebiturIndividu.setHistorySql(pstDebiturIndividu.getInsertSQL());
                
            entDebiturIndividu.setOID(pstDebiturIndividu.getlong(FLD_DEBITUR_INDIVIDU_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebiturIndividu(0), DBException.UNKNOWN);
        }
        return entDebiturIndividu;
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((DebiturIndividu) entity);
    }

    public static void resultToObject(ResultSet rs, DebiturIndividu entDebiturIndividu) {
        try {
            entDebiturIndividu.setOID(rs.getLong(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_DEBITUR_INDIVIDU_OID]));
            entDebiturIndividu.setFlagDetail(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_FLAG_DETAIL]));
            entDebiturIndividu.setCif(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]));
            entDebiturIndividu.setJenisIdentitas(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JENIS_IDENTITAS]));
            entDebiturIndividu.setNik(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NIK]));
            entDebiturIndividu.setNamaIdentitas(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_IDENTITAS]));
            entDebiturIndividu.setNamaLengkap(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_LENGKAP]));
            entDebiturIndividu.setKodeStatusGelar(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_STATUS_GELAR]));
            entDebiturIndividu.setJekel(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JEKEL]));
            entDebiturIndividu.setTempatLahir(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TEMPAT_LAHIR]));
            entDebiturIndividu.setTglLahir(rs.getDate(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TGL_LAHIR]));
            entDebiturIndividu.setNpwp(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NPWP]));
            entDebiturIndividu.setAlamat(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_ALAMAT]));
            entDebiturIndividu.setKelurahan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KELURAHAN]));
            entDebiturIndividu.setKecamatan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KECAMATAN]));
            entDebiturIndividu.setKodeKab(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_KAB]));
            entDebiturIndividu.setKodePos(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_POS]));
            entDebiturIndividu.setTelepon(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TELEPON]));
            entDebiturIndividu.setNomorHp(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NOMOR_HP]));
            entDebiturIndividu.setEmail(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_EMAIL]));
            entDebiturIndividu.setKodeDomisili(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_DOMISILI]));
            entDebiturIndividu.setKodePekerjaan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_PEKERJAAN]));
            entDebiturIndividu.setTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TEMPAT_BEKERJA]));
            entDebiturIndividu.setKodeUsahaTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_USAHA_TEMPAT_BEKERJA]));
            entDebiturIndividu.setAlamatTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_ALAMAT_TEMPAT_BEKERJA]));
            entDebiturIndividu.setPenghasilanKotor(rs.getDouble(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PENGHASILAN_KOTOR]));
            entDebiturIndividu.setKodePenghasilan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_PENGHASILAN]));
            entDebiturIndividu.setJmlTanggungan(rs.getInt(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JML_TANGGUNGAN]));
            entDebiturIndividu.setKodeHub(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_HUB]));
            entDebiturIndividu.setKodeGol(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_GOL]));
            entDebiturIndividu.setStatus(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_STATUS]));
            entDebiturIndividu.setNikPasangan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NIK_PASANGAN]));
            entDebiturIndividu.setNamaPasangan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_PASANGAN]));
            entDebiturIndividu.setTglLahirPasangan(rs.getDate(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TGL_LAHIR_PASANGAN]));
            entDebiturIndividu.setPerjanjianPisahHarga(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERJANJIAN_PISAH_HARGA]));
            entDebiturIndividu.setMelanggarBmpk(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_MELANGGAR_BMPK]));
            entDebiturIndividu.setMelampauiBmpk(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_MELAMPAUI_BMPK]));
            entDebiturIndividu.setNamaIbuKandung(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_IBU_KANDUNG]));
            entDebiturIndividu.setKodeKantorCabang(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_KANTOR_CABANG]));
            entDebiturIndividu.setOperasiData(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_OPERASI_DATA]));
            entDebiturIndividu.setPeriodeId(rs.getLong(fieldNames[FLD_PERIODE_ID]));
            entDebiturIndividu.setKodeJenisNsb(rs.getString(fieldNames[FLD_KODE_JENIS_NSB]));
            entDebiturIndividu.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));
            entDebiturIndividu.setStatusData(rs.getInt(fieldNames[FLD_STATUS_PERUBAHAN_DATA]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectDoubleNik(ResultSet rs, DebiturIndividu entDebiturIndividu) {
        try {
            entDebiturIndividu.setNoRekening(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]));
            entDebiturIndividu.setCif(rs.getString(fieldNames[FLD_CIF]));
            entDebiturIndividu.setNik(rs.getString(fieldNames[FLD_NIK]));
            entDebiturIndividu.setNamaIdentitas(rs.getString(fieldNames[FLD_NAMA_IDENTITAS]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectParentCabang(ResultSet rs, DebiturIndividu entDebiturIndividu) {
        try {
            entDebiturIndividu.setOID(rs.getLong(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_DEBITUR_INDIVIDU_OID]));
            entDebiturIndividu.setFlagDetail(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_FLAG_DETAIL]));
            entDebiturIndividu.setCif(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]));
            entDebiturIndividu.setJenisIdentitas(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JENIS_IDENTITAS]));
            entDebiturIndividu.setNik(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NIK]));
            entDebiturIndividu.setNamaIdentitas(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_IDENTITAS]));
            entDebiturIndividu.setNamaLengkap(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_LENGKAP]));
            entDebiturIndividu.setKodeStatusGelar(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_STATUS_GELAR]));
            entDebiturIndividu.setJekel(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JEKEL]));
            entDebiturIndividu.setTempatLahir(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TEMPAT_LAHIR]));
            entDebiturIndividu.setTglLahir(rs.getDate(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TGL_LAHIR]));
            entDebiturIndividu.setNpwp(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NPWP]));
            entDebiturIndividu.setAlamat(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_ALAMAT]));
            entDebiturIndividu.setKelurahan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KELURAHAN]));
            entDebiturIndividu.setKecamatan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KECAMATAN]));
            entDebiturIndividu.setKodeKab(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_KAB]));
            entDebiturIndividu.setKodePos(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_POS]));
            entDebiturIndividu.setTelepon(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TELEPON]));
            entDebiturIndividu.setNomorHp(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NOMOR_HP]));
            entDebiturIndividu.setEmail(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_EMAIL]));
            entDebiturIndividu.setKodeDomisili(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_DOMISILI]));
            entDebiturIndividu.setKodePekerjaan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_PEKERJAAN]));
            entDebiturIndividu.setTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TEMPAT_BEKERJA]));
            entDebiturIndividu.setKodeUsahaTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_USAHA_TEMPAT_BEKERJA]));
            entDebiturIndividu.setAlamatTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_ALAMAT_TEMPAT_BEKERJA]));
            entDebiturIndividu.setPenghasilanKotor(rs.getDouble(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PENGHASILAN_KOTOR]));
            entDebiturIndividu.setKodePenghasilan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_PENGHASILAN]));
            entDebiturIndividu.setJmlTanggungan(rs.getInt(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JML_TANGGUNGAN]));
            entDebiturIndividu.setKodeHub(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_HUB]));
            entDebiturIndividu.setKodeGol(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_GOL]));
            entDebiturIndividu.setStatus(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_STATUS]));
            entDebiturIndividu.setNikPasangan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NIK_PASANGAN]));
            entDebiturIndividu.setNamaPasangan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_PASANGAN]));
            entDebiturIndividu.setTglLahirPasangan(rs.getDate(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TGL_LAHIR_PASANGAN]));
            entDebiturIndividu.setPerjanjianPisahHarga(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERJANJIAN_PISAH_HARGA]));
            entDebiturIndividu.setMelanggarBmpk(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_MELANGGAR_BMPK]));
            entDebiturIndividu.setMelampauiBmpk(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_MELAMPAUI_BMPK]));
            entDebiturIndividu.setNamaIbuKandung(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_IBU_KANDUNG]));
            entDebiturIndividu.setKodeKantorCabang(rs.getString("PARENT_CODE"));
            entDebiturIndividu.setOperasiData(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_OPERASI_DATA]));
            entDebiturIndividu.setPeriodeId(rs.getLong(fieldNames[FLD_PERIODE_ID]));
            entDebiturIndividu.setKodeJenisNsb(rs.getString(fieldNames[FLD_KODE_JENIS_NSB]));
            entDebiturIndividu.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));
            entDebiturIndividu.setStatusData(rs.getInt(fieldNames[FLD_STATUS_PERUBAHAN_DATA]));
        } catch (Exception e) {
        }
    }
    
    
    public static void resultToObjectJoinReport(ResultSet rs, DebiturIndividu entDebiturIndividu) {
        try {
            entDebiturIndividu.setOID(rs.getLong(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_DEBITUR_INDIVIDU_OID]));
            entDebiturIndividu.setFlagDetail(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_FLAG_DETAIL]));
            entDebiturIndividu.setCif(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]));
            entDebiturIndividu.setJenisIdentitas(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JENIS_IDENTITAS]));
            entDebiturIndividu.setNik(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NIK]));
            entDebiturIndividu.setNamaIdentitas(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_IDENTITAS]));
            entDebiturIndividu.setNamaLengkap(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_LENGKAP]));
            entDebiturIndividu.setKodeStatusGelar(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_STATUS_GELAR]));
            entDebiturIndividu.setJekel(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JEKEL]));
            entDebiturIndividu.setTempatLahir(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TEMPAT_LAHIR]));
            entDebiturIndividu.setTglLahir(rs.getDate(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TGL_LAHIR]));
            entDebiturIndividu.setNpwp(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NPWP]));
            entDebiturIndividu.setAlamat(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_ALAMAT]));
            entDebiturIndividu.setKelurahan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KELURAHAN]));
            entDebiturIndividu.setKecamatan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KECAMATAN]));
            entDebiturIndividu.setKodeKab(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_KAB]));
            entDebiturIndividu.setKodePos(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_POS]));
            entDebiturIndividu.setTelepon(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TELEPON]));
            entDebiturIndividu.setNomorHp(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NOMOR_HP]));
            entDebiturIndividu.setEmail(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_EMAIL]));
            entDebiturIndividu.setKodeDomisili(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_DOMISILI]));
            entDebiturIndividu.setKodePekerjaan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_PEKERJAAN]));
            entDebiturIndividu.setTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TEMPAT_BEKERJA]));
            entDebiturIndividu.setKodeUsahaTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_USAHA_TEMPAT_BEKERJA]));
            entDebiturIndividu.setAlamatTempatBekerja(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_ALAMAT_TEMPAT_BEKERJA]));
            entDebiturIndividu.setPenghasilanKotor(rs.getDouble(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PENGHASILAN_KOTOR]));
            entDebiturIndividu.setKodePenghasilan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_PENGHASILAN]));
            entDebiturIndividu.setJmlTanggungan(rs.getInt(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_JML_TANGGUNGAN]));
            entDebiturIndividu.setKodeHub(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_HUB]));
            entDebiturIndividu.setKodeGol(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_GOL]));
            entDebiturIndividu.setStatus(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_STATUS]));
            entDebiturIndividu.setNikPasangan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NIK_PASANGAN]));
            entDebiturIndividu.setNamaPasangan(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_PASANGAN]));
            entDebiturIndividu.setTglLahirPasangan(rs.getDate(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_TGL_LAHIR_PASANGAN]));
            entDebiturIndividu.setPerjanjianPisahHarga(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERJANJIAN_PISAH_HARGA]));
            entDebiturIndividu.setMelanggarBmpk(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_MELANGGAR_BMPK]));
            entDebiturIndividu.setMelampauiBmpk(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_MELAMPAUI_BMPK]));
            entDebiturIndividu.setNamaIbuKandung(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_NAMA_IBU_KANDUNG]));
            entDebiturIndividu.setKodeKantorCabang(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_KANTOR_CABANG]));
            entDebiturIndividu.setOperasiData(rs.getString(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_OPERASI_DATA]));
            entDebiturIndividu.setPeriodeId(rs.getLong(fieldNames[FLD_PERIODE_ID]));
            entDebiturIndividu.setKodeJenisNsb(rs.getString(fieldNames[FLD_KODE_JENIS_NSB]));
            entDebiturIndividu.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));
            entDebiturIndividu.setStatusData(rs.getInt(fieldNames[FLD_STATUS_PERUBAHAN_DATA]));
            entDebiturIndividu.setNoRekening(rs.getString("NO_REKENING"));
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
            String sql = "SELECT DISTINCT TRIM(FLAG_DETAIL)," +
                        "TRIM(DEBITUR_OID) AS DEBITUR_OID, " +
                        "TRIM(FLAG_DETAIL) AS FLAG_DETAIL, " +
                        "TRIM(CIF) AS CIF, " +
                        "TRIM(JENIS_IDENTITAS) AS JENIS_IDENTITAS, " +
                        "TRIM(NIK) AS NIK, " +
                        "TRIM(NAMA_IDENTITAS) AS NAMA_IDENTITAS, " +
                        "TRIM(NAMA_LENGKAP) AS NAMA_LENGKAP, " +
                        "TRIM(KODE_STATUS_GELAR) AS KODE_STATUS_GELAR, " +
                        "TRIM(JEKEL) AS JEKEL, " +
                        "TRIM(TEMPAT_LAHIR) AS TEMPAT_LAHIR, " +
                        "TRIM(TANGGAL_LAHIR) AS TANGGAL_LAHIR, " +
                        "TRIM(NPWP) AS NPWP, " +
                        "TRIM(ALAMAT) AS ALAMAT, " +
                        "TRIM(KELURAHAN) AS KELURAHAN, " +
                        "TRIM(KECAMATAN) AS KECAMATAN, " +
                        "TRIM(KODE_KAB) AS KODE_KAB, " +
                        "TRIM(KODE_POS) AS KODE_POS, " +
                        "TRIM(TELEPON) AS TELEPON, " +
                        "TRIM(NOMOR_HP) AS NOMOR_HP, " +
                        "TRIM(EMAIL) AS EMAIL, " +
                        "TRIM(KODE_DOMISILI) AS KODE_DOMISILI, " +
                        "TRIM(KODE_PEKERJAAN) AS KODE_PEKERJAAN, " +
                        "TRIM(TEMPAT_BEKERJA) AS TEMPAT_BEKERJA, " +
                        "TRIM(KODE_USAHA_TEMPAT_BEKERJA) AS KODE_USAHA_TEMPAT_BEKERJA, " +
                        "TRIM(ALAMAT_TEMPAT_BEKERJA) AS ALAMAT_TEMPAT_BEKERJA, " +
                        "TRIM(PENGHASILAN_KOTOR) AS PENGHASILAN_KOTOR, " +
                        "TRIM(KODE_PENGHASILAN) AS KODE_PENGHASILAN, " +
                        "TRIM(JML_TANGGUNGAN) AS JML_TANGGUNGAN, " +
                        "TRIM(KODE_HUB) AS KODE_HUB, " +
                        "TRIM(KODE_GOL) AS KODE_GOL, " +
                        "TRIM(STATUS) AS STATUS, " +
                        "TRIM(NIK_PASANGAN) AS NIK_PASANGAN, " +
                        "TRIM(NAMA_PASANGAN) AS NAMA_PASANGAN, " +
                        "TRIM(TGL_LAHIR_PASANGAN) AS TGL_LAHIR_PASANGAN, " +
                        "TRIM(PERJANJIAN_PISAH_HARGA) AS PERJANJIAN_PISAH_HARGA, " +
                        "TRIM(MELANGGAR_BMPK) AS MELANGGAR_BMPK, " +
                        "TRIM(MELAMPAUI_BMPK) AS MELAMPAUI_BMPK, " +
                        "TRIM(NAMA_IBU_KANDUNG) AS NAMA_IBU_KANDUNG, " +
                        "TRIM(KODE_KANTOR_CABANG) AS KODE_KANTOR_CABANG, " +
                        "TRIM(OPERASI_DATA) AS OPERASI_DATA, " +
                        "TRIM(PERIODE_ID) AS PERIODE_ID, " +
                        "TRIM(KODE_JENIS_NSB) AS KODE_JENIS_NSB, "
                      + "TRIM(STATUS_OPERASI_DATA) AS STATUS_OPERASI_DATA ,"
                      + "TRIM(STATUS_DATA) AS STATUS_DATA "
                      + "FROM " + TBL_DEBITUR_INDIVIDU;
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
                DebiturIndividu entDebiturIndividu = new DebiturIndividu();
                resultToObject(rs, entDebiturIndividu);
                lists.add(entDebiturIndividu);
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
    
    public static String getCif(int kodeJenisNasabah, long periodeId){
        String cif = "";
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT "+ fieldNames[FLD_DEBITUR_INDIVIDU_OID]+", "
                    + fieldNames[FLD_NIK]+", "
                    + fieldNames[FLD_NAMA_IDENTITAS]+", "
                    + fieldNames[FLD_CIF]+", "
                    + "COUNT(*) c "
                    + "FROM "+TBL_DEBITUR_INDIVIDU+" WHERE "+fieldNames[FLD_PERIODE_ID]+"='"+periodeId+"' "
                    + "AND "+fieldNames[FLD_KODE_JENIS_NSB]+"='"+kodeJenisNasabah+"' "
                    + "GROUP BY "+fieldNames[FLD_NIK]+" "
                    + "HAVING c > 1";
            
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                if(cif.length() > 0){
                    cif+=",'"+rs.getString(fieldNames[FLD_NIK])+"'";
                }else{
                    cif+="'"+rs.getString(fieldNames[FLD_NIK])+"'";
                }
            }
            rs.close();
            return cif;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return cif;
    }
    
    public static Vector listDoubleNik(int limitStart, int recordToGet, String kodeCabang, long periodeId) {
        String cif = getCif(1, periodeId);
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT dup."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+", "
                    + "debitur."+fieldNames[FLD_CIF]+", "
                    + "debitur."+fieldNames[FLD_NIK]+","
                    + "debitur."+fieldNames[FLD_NAMA_IDENTITAS]+" "
                    + "FROM "+TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "LEFT JOIN ("
                        + "SELECT DISTINCT "+PstKredit.fieldNames[PstKredit.FLD_CIF]+","
                        + PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+","
                        + PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+" "
                        + "FROM ("
                            + "SELECT DISTINCT "+PstKredit.fieldNames[PstKredit.FLD_CIF]+","
                            + PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+","
                            + PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]+","
                            + PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+" "
                            + "FROM "+PstKredit.TBL_KREDIT+" "
                            + "WHERE "+PstKredit.TBL_KREDIT+"."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"' "
                            + "UNION "
                            + "SELECT DISTINCT "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+","
                            + PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+","
                            + PstBankGaransi.fieldNames[PstBankGaransi.FLD_TGL_DITERBITKAN]+" AS "+PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]+","
                            + PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+" "
                            + "FROM "+PstBankGaransi.TBL_BANK_GARANSI+" "
                            + "WHERE "+PstBankGaransi.TBL_BANK_GARANSI+"."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'"
                    + ") AS CUP"
                    + " ORDER BY CUP."+PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]+" DESC"
                + ") AS dup "
                + "ON debitur."+fieldNames[FLD_CIF]+" = dup."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" "
                + "WHERE debitur."+fieldNames[FLD_PERIODE_ID]+"='"+periodeId+"' "
                + "AND debitur."+fieldNames[FLD_KODE_JENIS_NSB]+"='1'";
                
                if(cif.length() > 0){
                    sql+=" AND debitur."+fieldNames[FLD_NIK]+" IN ("+cif+")";
                }
                
                if (kodeCabang != null && kodeCabang.length() > 0) {
                    sql = sql + " AND dup." +fieldNames[FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'" ;
                }
                
            sql+=" ORDER BY debitur."+fieldNames[FLD_NIK]+" ASC";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                DebiturIndividu entDebiturIndividu = new DebiturIndividu();
                resultToObjectDoubleNik(rs, entDebiturIndividu);
                lists.add(entDebiturIndividu);
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
    
    public static String listDoubleNik2(int limitStart, int recordToGet, String kodeCabang, long periodeId) {
        String cif2 = getCif(1, periodeId);
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        String cif="";
        try {
            String sql = "SELECT DISTINCT dup."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+", "
                    + "debitur."+fieldNames[FLD_CIF]+", "
                    + "debitur."+fieldNames[FLD_NIK]+","
                    + "debitur."+fieldNames[FLD_NAMA_IDENTITAS]+" "
                    + "FROM "+TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "INNER JOIN ("
                        + "SELECT DISTINCT "+PstKredit.fieldNames[PstKredit.FLD_CIF]+","
                        + PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+","
                        + PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+" "
                        + "FROM ("
                            + "SELECT DISTINCT "+PstKredit.fieldNames[PstKredit.FLD_CIF]+","
                            + PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+","
                            + PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]+","
                            + PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+" "
                            + "FROM "+PstKredit.TBL_KREDIT+" "
                            + "WHERE "+PstKredit.TBL_KREDIT+"."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"' "
                            + "UNION "
                            + "SELECT DISTINCT "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+","
                            + PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+","
                            + PstBankGaransi.fieldNames[PstBankGaransi.FLD_TGL_DITERBITKAN]+" AS "+PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]+","
                            + PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+" "
                            + "FROM "+PstBankGaransi.TBL_BANK_GARANSI+" "
                            + "WHERE "+PstBankGaransi.TBL_BANK_GARANSI+"."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'"
                    + ") AS CUP"
                    + " ORDER BY CUP."+PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]+" DESC"
                + ") AS dup "
                + "ON debitur."+fieldNames[FLD_CIF]+" = dup."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" "
                + "WHERE debitur."+fieldNames[FLD_PERIODE_ID]+"='"+periodeId+"' "
                + "AND debitur."+fieldNames[FLD_KODE_JENIS_NSB]+"='1'";
                
                if(cif2.length() > 0){
                    sql+=" AND debitur."+fieldNames[FLD_CIF]+" IN ("+cif2+")";
                }
                
            if (kodeCabang != null && kodeCabang.length() > 0) {
                sql = sql + " AND debitur." +fieldNames[FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'" ;
            }
            
            sql+=" ORDER BY debitur."+fieldNames[FLD_NIK]+" ASC";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                if(cif.length() > 0){
                    cif+=",'"+rs.getString(fieldNames[FLD_NIK])+"'";
                }else{
                    cif+="'"+rs.getString(fieldNames[FLD_NIK])+"'";
                }
            }
            rs.close();
            return cif;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return cif;
    }
    
    
    
    public static Vector listJoinParentCabang(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT TRIM(FLAG_DETAIL)," +
                        "TRIM(DEBITUR_OID) AS DEBITUR_OID, " +
                        "TRIM(FLAG_DETAIL) AS FLAG_DETAIL, " +
                        "TRIM(CIF) AS CIF, " +
                        "TRIM(JENIS_IDENTITAS) AS JENIS_IDENTITAS, " +
                        "TRIM(NIK) AS NIK, " +
                        "TRIM(NAMA_IDENTITAS) AS NAMA_IDENTITAS, " +
                        "TRIM(NAMA_LENGKAP) AS NAMA_LENGKAP, " +
                        "TRIM(KODE_STATUS_GELAR) AS KODE_STATUS_GELAR, " +
                        "TRIM(JEKEL) AS JEKEL, " +
                        "TRIM(TEMPAT_LAHIR) AS TEMPAT_LAHIR, " +
                        "TRIM(TANGGAL_LAHIR) AS TANGGAL_LAHIR, " +
                        "TRIM(NPWP) AS NPWP, " +
                        "TRIM(ALAMAT) AS ALAMAT, " +
                        "TRIM(KELURAHAN) AS KELURAHAN, " +
                        "TRIM(KECAMATAN) AS KECAMATAN, " +
                        "TRIM(KODE_KAB) AS KODE_KAB, " +
                        "TRIM(KODE_POS) AS KODE_POS, " +
                        "TRIM(TELEPON) AS TELEPON, " +
                        "TRIM(NOMOR_HP) AS NOMOR_HP, " +
                        "TRIM(EMAIL) AS EMAIL, " +
                        "TRIM(KODE_DOMISILI) AS KODE_DOMISILI, " +
                        "TRIM(KODE_PEKERJAAN) AS KODE_PEKERJAAN, " +
                        "TRIM(TEMPAT_BEKERJA) AS TEMPAT_BEKERJA, " +
                        "TRIM(KODE_USAHA_TEMPAT_BEKERJA) AS KODE_USAHA_TEMPAT_BEKERJA, " +
                        "TRIM(ALAMAT_TEMPAT_BEKERJA) AS ALAMAT_TEMPAT_BEKERJA, " +
                        "TRIM(PENGHASILAN_KOTOR) AS PENGHASILAN_KOTOR, " +
                        "TRIM(KODE_PENGHASILAN) AS KODE_PENGHASILAN, " +
                        "TRIM(JML_TANGGUNGAN) AS JML_TANGGUNGAN, " +
                        "TRIM(KODE_HUB) AS KODE_HUB, " +
                        "TRIM(KODE_GOL) AS KODE_GOL, " +
                        "TRIM(STATUS) AS STATUS, " +
                        "TRIM(NIK_PASANGAN) AS NIK_PASANGAN, " +
                        "TRIM(NAMA_PASANGAN) AS NAMA_PASANGAN, " +
                        "TRIM(TGL_LAHIR_PASANGAN) AS TGL_LAHIR_PASANGAN, " +
                        "TRIM(PERJANJIAN_PISAH_HARGA) AS PERJANJIAN_PISAH_HARGA, " +
                        "TRIM(MELANGGAR_BMPK) AS MELANGGAR_BMPK, " +
                        "TRIM(MELAMPAUI_BMPK) AS MELAMPAUI_BMPK, " +
                        "TRIM(NAMA_IBU_KANDUNG) AS NAMA_IBU_KANDUNG, " +
                        "TRIM(KODE_KANTOR_CABANG) AS KODE_KANTOR_CABANG, " +
                        "TRIM(OPERASI_DATA) AS OPERASI_DATA, " +
                        "TRIM(PERIODE_ID) AS PERIODE_ID, " +
                        "TRIM(KODE_JENIS_NSB) AS KODE_JENIS_NSB, "
                      + "TRIM(STATUS_OPERASI_DATA) AS STATUS_OPERASI_DATA ,"
                      + "TRIM(STATUS_DATA) AS STATUS_DATA, "
                      + "TRIM(PARENT_CODE) AS PARENT_CODE "  
                      + "FROM " + TBL_DEBITUR_INDIVIDU+" "
                      + "INNER JOIN dslik_cabang_bank ON dslik_debitur.KODE_KANTOR_CABANG=dslik_cabang_bank.KODE_CABANG";
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
                DebiturIndividu entDebiturIndividu = new DebiturIndividu();
                resultToObjectParentCabang(rs, entDebiturIndividu);
                lists.add(entDebiturIndividu);
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
    
    public static Vector listJoinReport(int limitStart, int recordToGet, String whereClause, String order, long periodeId, String kodecabang) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT TRIM(dslik_debitur.FLAG_DETAIL)," +
                        "TRIM(dslik_debitur.DEBITUR_OID) AS DEBITUR_OID, " +
                        "TRIM(dslik_debitur.FLAG_DETAIL) AS FLAG_DETAIL, " +
                        "TRIM(dslik_debitur.CIF) AS CIF, " +
                        "TRIM(dup.NO_REKENING) AS NO_REKENING, " +
                        "TRIM(dslik_debitur.JENIS_IDENTITAS) AS JENIS_IDENTITAS, " +
                        "TRIM(dslik_debitur.NIK) AS NIK, " +
                        "TRIM(dslik_debitur.NAMA_IDENTITAS) AS NAMA_IDENTITAS, " +
                        "TRIM(dslik_debitur.NAMA_LENGKAP) AS NAMA_LENGKAP, " +
                        "TRIM(dslik_debitur.KODE_STATUS_GELAR) AS KODE_STATUS_GELAR, " +
                        "TRIM(dslik_debitur.JEKEL) AS JEKEL, " +
                        "TRIM(dslik_debitur.TEMPAT_LAHIR) AS TEMPAT_LAHIR, " +
                        "TRIM(dslik_debitur.TANGGAL_LAHIR) AS TANGGAL_LAHIR, " +
                        "TRIM(dslik_debitur.NPWP) AS NPWP, " +
                        "TRIM(dslik_debitur.ALAMAT) AS ALAMAT, " +
                        "TRIM(dslik_debitur.KELURAHAN) AS KELURAHAN, " +
                        "TRIM(dslik_debitur.KECAMATAN) AS KECAMATAN, " +
                        "TRIM(dslik_debitur.KODE_KAB) AS KODE_KAB, " +
                        "TRIM(dslik_debitur.KODE_POS) AS KODE_POS, " +
                        "TRIM(dslik_debitur.TELEPON) AS TELEPON, " +
                        "TRIM(dslik_debitur.NOMOR_HP) AS NOMOR_HP, " +
                        "TRIM(dslik_debitur.EMAIL) AS EMAIL, " +
                        "TRIM(dslik_debitur.KODE_DOMISILI) AS KODE_DOMISILI, " +
                        "TRIM(dslik_debitur.KODE_PEKERJAAN) AS KODE_PEKERJAAN, " +
                        "TRIM(dslik_debitur.TEMPAT_BEKERJA) AS TEMPAT_BEKERJA, " +
                        "TRIM(dslik_debitur.KODE_USAHA_TEMPAT_BEKERJA) AS KODE_USAHA_TEMPAT_BEKERJA, " +
                        "TRIM(dslik_debitur.ALAMAT_TEMPAT_BEKERJA) AS ALAMAT_TEMPAT_BEKERJA, " +
                        "TRIM(dslik_debitur.PENGHASILAN_KOTOR) AS PENGHASILAN_KOTOR, " +
                        "TRIM(dslik_debitur.KODE_PENGHASILAN) AS KODE_PENGHASILAN, " +
                        "TRIM(dslik_debitur.JML_TANGGUNGAN) AS JML_TANGGUNGAN, " +
                        "TRIM(dslik_debitur.KODE_HUB) AS KODE_HUB, " +
                        "TRIM(dslik_debitur.KODE_GOL) AS KODE_GOL, " +
                        "TRIM(dslik_debitur.STATUS) AS STATUS, " +
                        "TRIM(dslik_debitur.NIK_PASANGAN) AS NIK_PASANGAN, " +
                        "TRIM(dslik_debitur.NAMA_PASANGAN) AS NAMA_PASANGAN, " +
                        "TRIM(dslik_debitur.TGL_LAHIR_PASANGAN) AS TGL_LAHIR_PASANGAN, " +
                        "TRIM(dslik_debitur.PERJANJIAN_PISAH_HARGA) AS PERJANJIAN_PISAH_HARGA, " +
                        "TRIM(dslik_debitur.MELANGGAR_BMPK) AS MELANGGAR_BMPK, " +
                        "TRIM(dslik_debitur.MELAMPAUI_BMPK) AS MELAMPAUI_BMPK, " +
                        "TRIM(dslik_debitur.NAMA_IBU_KANDUNG) AS NAMA_IBU_KANDUNG, " +
                        "TRIM(dslik_debitur.KODE_KANTOR_CABANG) AS KODE_KANTOR_CABANG, " +
                        "TRIM(dslik_debitur.OPERASI_DATA) AS OPERASI_DATA, " +
                        "TRIM(dslik_debitur.PERIODE_ID) AS PERIODE_ID, " +
                        "TRIM(dslik_debitur.KODE_JENIS_NSB) AS KODE_JENIS_NSB, "
                      + "TRIM(dslik_debitur.STATUS_OPERASI_DATA) AS STATUS_OPERASI_DATA ,"
                      + "TRIM(dslik_debitur.STATUS_DATA) AS STATUS_DATA "
                      + "FROM " + TBL_DEBITUR_INDIVIDU
//                      + " INNER JOIN " +
//                        "(" +
//                        "SELECT DISTINCT CIF, NO_REKENING, TGL_AWAL, KODE_KANTOR_CABANG FROM dslik_kredit WHERE dslik_kredit.PERIODE_ID = '"+periodeId+"' AND dslik_kredit.KODE_KANTOR_CABANG='"+kodecabang+"'"+
//                        "GROUP BY CIF ORDER BY dslik_kredit.TGL_AWAL DESC" +
//                        ") AS dup " +
                    + " INNER JOIN " +
                        "(" +
                            "SELECT DISTINCT CIF,NO_REKENING FROM (" +
                            " SELECT DISTINCT" +
                            " CIF," +
                            " NO_REKENING," +
                            " TGL_AWAL," +
                            " KODE_KANTOR_CABANG" +
                            " FROM dslik_kredit" +
                            " WHERE dslik_kredit.PERIODE_ID = '"+periodeId+"' ";
                            if(!kodecabang.equals("")){
                                sql = sql + " AND dslik_kredit.KODE_KANTOR_CABANG='"+kodecabang+"'";
                            }
                sql = sql + " GROUP BY CIF" +
                            " UNION " +
                            " SELECT DISTINCT" +
                            " CIF," +
                            " NO_REKENING," +
                            " TGL_DITERBITKAN AS TGL_AWAL," +
                            " KODE_KANTOR_CABANG" +
                            " FROM dslik_bank_garansi" +
                            " WHERE dslik_bank_garansi.PERIODE_ID = '"+periodeId+"' ";
                if(!kodecabang.equals("")){
                                sql = sql + " AND dslik_bank_garansi.KODE_KANTOR_CABANG='"+kodecabang+"'";
                            }
                    sql = sql +" GROUP BY CIF ) AS CUP " +
                            " ORDER BY CUP.TGL_AWAL DESC "+
                        ") AS dup " +
                        "ON dslik_debitur.CIF=dup.CIF";
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
            int urut=0;
            String cif1 = "";
            String cif2 = "";
            while (rs.next()) {
                DebiturIndividu entDebiturIndividu = new DebiturIndividu();
                resultToObjectJoinReport(rs, entDebiturIndividu);
                if(urut==0){
                    lists.add(entDebiturIndividu);
                    cif1 = entDebiturIndividu.getCif();
                }else{
                    cif2 = entDebiturIndividu.getCif();
                    if(!cif1.equals(cif2)){
                        lists.add(entDebiturIndividu);
                        cif1=entDebiturIndividu.getCif();
                    }
                }
                urut=urut+1;
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

    public static boolean checkOID(long entDebiturIndividuId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_DEBITUR_INDIVIDU + " WHERE "
                    + PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_DEBITUR_INDIVIDU_OID] + " = " + entDebiturIndividuId;
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
            String sql = "SELECT COUNT(" + PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_DEBITUR_INDIVIDU_OID] + ") FROM " + TBL_DEBITUR_INDIVIDU;
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
                    DebiturIndividu entDebiturIndividu = (DebiturIndividu) list.get(ls);
                    if (oid == entDebiturIndividu.getOID()) {
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

    /**
     * @return the sqlQueryHistory
     */
    public String getSqlQueryHistory() {
        return sqlQueryHistory;
    }

    /**
     * @param sqlQueryHistory the sqlQueryHistory to set
     */
    public void setSqlQueryHistory(String sqlQueryHistory) {
        this.sqlQueryHistory = sqlQueryHistory;
    }
    
    
}
