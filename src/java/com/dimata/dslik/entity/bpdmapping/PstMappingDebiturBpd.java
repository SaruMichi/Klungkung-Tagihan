/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.bpdmapping;

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
public class PstMappingDebiturBpd extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_MAPPING_DEBITUR_BPD = "dslik_debitur";
    public static final int FLD_MAPPING_DEBITUR_BPD_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_CIF = 2;
    public static final int FLD_JENIS_IDENTITAS = 3;
    public static final int FLD_NIK = 4;
    public static final int FLD_NAMA_IDENTITAS = 5;
    public static final int FLD_NAMA_LENGKAP = 6;
    public static final int FLD_KODE_STATUS_GELAR = 7;
    public static final int FLD_JEKEL = 8;
    public static final int FLD_TEMPAT_LAHIR = 9;
    public static final int FLD_TANGGAL_LAHIR = 10;
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
    public static final int FLD_JML_TANGGUNGAN = 26;
    public static final int FLD_KODE_HUB = 27;
    public static final int FLD_KODE_GOL = 28;
    public static final int FLD_STATUS = 29;
    public static final int FLD_MELANGGAR_BMPK = 30;
    public static final int FLD_MELAMPUI_BMPK = 31;
    public static final int FLD_NAMA_IBU_KANDUNG = 32;
    public static final int FLD_KODE_KANTOR_CABANG = 33;
    public static final int FLD_NAMA_BADAN_USAHA = 34;
    public static final int FLD_KODE_JENIS_USAHA = 35;
    public static final int FLD_TEMPAT_PENDIRIAN = 36;
    public static final int FLD_NO_AKTE = 37;
    public static final int FLD_TGL_AKTE_PENDIRIAN = 38;
    public static final int FLD_NO_AKTE_PERUBAHAN = 39;
    public static final int FLD_TGL_AKTE_PERUBAHAN = 40;
    public static final int FLD_KODE_BIDANG_USAHA = 41;
    public static final int FLD_KODE_HUB_LJK = 42;
    public static final int FLD_GO_PUBLIC = 43;
    public static final int FLD_PERINGKAT = 44;
    public static final int FLD_LEMBAGA_PEMERINGKAT = 45;
    public static final int FLD_NAMA_GROUP = 46;
    public static final int FLD_KODE_JENIS_NSB = 47;
    public static final int FLD_OPEN_DATE = 48;
    public static final int FLD_PERIODE_ID=49;
    public static final int FLD_NO_IDENTITAS=50;
    public static final int FLD_KODE_PENGHASILAN=51;
    
    //tidak ada pada core
    public static final int FLD_NIK_PASSPORT_PASANGAN=52;
    public static final int FLD_NAMA_PASANGAN=53;
    public static final int FLD_TGL_LAHIR_PASANGAN=54;
    public static final int FLD_PERJANJIAN_PISAH_HARTA=55;
    public static final int FLD_TGL_PEMERINGKAT=56;
    
    
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
        "JML_TANGGUNGAN",
        "KODE_HUB",
        "KODE_GOL",
        "STATUS",
        "MELANGGAR_BMPK",
        "MELAMPAUI_BMPK",
        "NAMA_IBU_KANDUNG",
        "KODE_KANTOR_CABANG",
        "NAMA_BADAN_USAHA",
        "KODE_JENIS_USAHA",
        "TEMPAT_PENDIRIAN",
        "NO_AKTE",
        "TGL_AKTE_PENDIRIAN",
        "NO_AKTE_PERUBAHAN",
        "TGL_AKTE_PERUBAHAN",
        "KODE_BIDANG_USAHA",
        "KODE_HUB_LJK",
        "GO_PUBLIC",
        "PERINGKAT",
        "LEMBAGA_PEMERINGKAT",
        "NAMA_GROUP",
        "KODE_JENIS_NSB",
        "OPEN_DATE",
        "PERIODE_ID",
        "NO_IDENTITAS",
        "KODE_PENGHASILAN",
        //tidak ada di core
        "NIK_PASANGAN",
        "NAMA_PASANGAN",
        "TGL_LAHIR_PASANGAN",
        "PERJANJIAN_PISAH_HARGA",
        "TGL_PEMERINGKAT"
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
        TYPE_INT,
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
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_DATE,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        //tidak ada di core
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE
    };

    public PstMappingDebiturBpd() {
    }

    public PstMappingDebiturBpd(int i) throws DBException {
        super(new PstMappingDebiturBpd());
    }

    public PstMappingDebiturBpd(String sOid) throws DBException {
        super(new PstMappingDebiturBpd(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstMappingDebiturBpd(long lOid) throws DBException {
        super(new PstMappingDebiturBpd(0));
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
        return TBL_MAPPING_DEBITUR_BPD;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstMappingDebiturBpd().getClass().getName();
    }

    public static MappingDebiturBpd fetchExc(long oid) throws DBException {
        try {
            MappingDebiturBpd entMappingDebiturBpd = new MappingDebiturBpd();
            PstMappingDebiturBpd pstMappingDebiturBpd = new PstMappingDebiturBpd(oid);
            entMappingDebiturBpd.setOID(oid);
            entMappingDebiturBpd.setFlagDetail(pstMappingDebiturBpd.getString(FLD_FLAG_DETAIL));
            entMappingDebiturBpd.setCif(pstMappingDebiturBpd.getString(FLD_CIF));
            entMappingDebiturBpd.setJenisIdentitas(pstMappingDebiturBpd.getString(FLD_JENIS_IDENTITAS));
            entMappingDebiturBpd.setNik(pstMappingDebiturBpd.getString(FLD_NIK));
            entMappingDebiturBpd.setNamaIdentitas(pstMappingDebiturBpd.getString(FLD_NAMA_IDENTITAS));
            entMappingDebiturBpd.setNamaLengkap(pstMappingDebiturBpd.getString(FLD_NAMA_LENGKAP));
            entMappingDebiturBpd.setKodeStatusGelar(pstMappingDebiturBpd.getString(FLD_KODE_STATUS_GELAR));
            entMappingDebiturBpd.setJekel(pstMappingDebiturBpd.getString(FLD_JEKEL));
            entMappingDebiturBpd.setTempatLahir(pstMappingDebiturBpd.getString(FLD_TEMPAT_LAHIR));
            entMappingDebiturBpd.setTanggalLahir(pstMappingDebiturBpd.getDate(FLD_TANGGAL_LAHIR));
            entMappingDebiturBpd.setNpwp(pstMappingDebiturBpd.getString(FLD_NPWP));
            entMappingDebiturBpd.setAlamat(pstMappingDebiturBpd.getString(FLD_ALAMAT));
            entMappingDebiturBpd.setKelurahan(pstMappingDebiturBpd.getString(FLD_KELURAHAN));
            entMappingDebiturBpd.setKecamatan(pstMappingDebiturBpd.getString(FLD_KECAMATAN));
            entMappingDebiturBpd.setKodeKab(pstMappingDebiturBpd.getString(FLD_KODE_KAB));
            entMappingDebiturBpd.setKodePos(pstMappingDebiturBpd.getString(FLD_KODE_POS));
            entMappingDebiturBpd.setTelepon(pstMappingDebiturBpd.getString(FLD_TELEPON));
            entMappingDebiturBpd.setNomorHp(pstMappingDebiturBpd.getString(FLD_NOMOR_HP));
            entMappingDebiturBpd.setEmail(pstMappingDebiturBpd.getString(FLD_EMAIL));
            entMappingDebiturBpd.setKodeDomisili(pstMappingDebiturBpd.getString(FLD_KODE_DOMISILI));
            entMappingDebiturBpd.setKodePekerjaan(pstMappingDebiturBpd.getString(FLD_KODE_PEKERJAAN));
            entMappingDebiturBpd.setTempatBekerja(pstMappingDebiturBpd.getString(FLD_TEMPAT_BEKERJA));
            entMappingDebiturBpd.setKodeUsahaTempatBekerja(pstMappingDebiturBpd.getString(FLD_KODE_USAHA_TEMPAT_BEKERJA));
            entMappingDebiturBpd.setAlamatTempatBekerja(pstMappingDebiturBpd.getString(FLD_ALAMAT_TEMPAT_BEKERJA));
            entMappingDebiturBpd.setPenghasilanKotor(pstMappingDebiturBpd.getdouble(FLD_PENGHASILAN_KOTOR));
            entMappingDebiturBpd.setJmlTanggungan(pstMappingDebiturBpd.getInt(FLD_JML_TANGGUNGAN));
            entMappingDebiturBpd.setKodeHub(pstMappingDebiturBpd.getString(FLD_KODE_HUB));
            entMappingDebiturBpd.setKodeGol(pstMappingDebiturBpd.getString(FLD_KODE_GOL));
            entMappingDebiturBpd.setStatus(pstMappingDebiturBpd.getString(FLD_STATUS));
            entMappingDebiturBpd.setMelanggarBmpk(pstMappingDebiturBpd.getString(FLD_MELANGGAR_BMPK));
            entMappingDebiturBpd.setMelampuiBmpk(pstMappingDebiturBpd.getString(FLD_MELAMPUI_BMPK));
            entMappingDebiturBpd.setNamaIbuKandung(pstMappingDebiturBpd.getString(FLD_NAMA_IBU_KANDUNG));
            entMappingDebiturBpd.setKodeKantorCabang(pstMappingDebiturBpd.getString(FLD_KODE_KANTOR_CABANG));
            entMappingDebiturBpd.setNamaBadanUsaha(pstMappingDebiturBpd.getString(FLD_NAMA_BADAN_USAHA));
            entMappingDebiturBpd.setKodeJenisUsaha(pstMappingDebiturBpd.getString(FLD_KODE_JENIS_USAHA));
            entMappingDebiturBpd.setTempatPendirian(pstMappingDebiturBpd.getString(FLD_TEMPAT_PENDIRIAN));
            entMappingDebiturBpd.setNoAkte(pstMappingDebiturBpd.getString(FLD_NO_AKTE));
            entMappingDebiturBpd.setTglAktePendirian(pstMappingDebiturBpd.getDate(FLD_TGL_AKTE_PENDIRIAN));
            entMappingDebiturBpd.setNoAktePerubahan(pstMappingDebiturBpd.getString(FLD_NO_AKTE_PERUBAHAN));
            entMappingDebiturBpd.setTglAktePerubahan(pstMappingDebiturBpd.getDate(FLD_TGL_AKTE_PERUBAHAN));
            entMappingDebiturBpd.setKodeBidangUsaha(pstMappingDebiturBpd.getString(FLD_KODE_BIDANG_USAHA));
            entMappingDebiturBpd.setKodeHubLjk(pstMappingDebiturBpd.getString(FLD_KODE_HUB_LJK));
            entMappingDebiturBpd.setGoPublic(pstMappingDebiturBpd.getString(FLD_GO_PUBLIC));
            entMappingDebiturBpd.setPeringkat(pstMappingDebiturBpd.getString(FLD_PERINGKAT));
            entMappingDebiturBpd.setLembagaPemeringkat(pstMappingDebiturBpd.getString(FLD_LEMBAGA_PEMERINGKAT));
            entMappingDebiturBpd.setNamaGroup(pstMappingDebiturBpd.getString(FLD_NAMA_GROUP));
            entMappingDebiturBpd.setKodeJenisNsb(pstMappingDebiturBpd.getInt(FLD_KODE_JENIS_NSB));
            entMappingDebiturBpd.setOpenDate(pstMappingDebiturBpd.getDate(FLD_OPEN_DATE));//16
            entMappingDebiturBpd.setPeriodeId(pstMappingDebiturBpd.getlong(FLD_PERIODE_ID));
            entMappingDebiturBpd.setNoIdentitas(pstMappingDebiturBpd.getString(FLD_NO_IDENTITAS));
            entMappingDebiturBpd.setKodePenghasilan(pstMappingDebiturBpd.getString(FLD_KODE_PENGHASILAN));
            
            entMappingDebiturBpd.setNikPassportPasangan(pstMappingDebiturBpd.getString(FLD_NIK_PASSPORT_PASANGAN));
            entMappingDebiturBpd.setNamaPasangan(pstMappingDebiturBpd.getString(FLD_NAMA_PASANGAN));
            entMappingDebiturBpd.setTglLahirPasangan(pstMappingDebiturBpd.getDate(FLD_TGL_LAHIR_PASANGAN));
            entMappingDebiturBpd.setPerjanjianPisahHarta(pstMappingDebiturBpd.getString(FLD_PERJANJIAN_PISAH_HARTA));
            entMappingDebiturBpd.setTanggalPemeringkat(pstMappingDebiturBpd.getDate(FLD_TGL_PEMERINGKAT));
            
            return entMappingDebiturBpd;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingDebiturBpd(0), DBException.UNKNOWN);
        }
    }
    
    
    public static MappingDebiturBpd fetchExc(long oid, MappingDebiturBpd entMappingDebiturBpd) throws DBException {
        try {
            //MappingDebiturBpd entMappingDebiturBpd = new MappingDebiturBpd();
            PstMappingDebiturBpd pstMappingDebiturBpd = new PstMappingDebiturBpd(oid);
            entMappingDebiturBpd.setOID(oid);
            entMappingDebiturBpd.setFlagDetail(pstMappingDebiturBpd.getString(FLD_FLAG_DETAIL));
            entMappingDebiturBpd.setCif(pstMappingDebiturBpd.getString(FLD_CIF));
            entMappingDebiturBpd.setJenisIdentitas(pstMappingDebiturBpd.getString(FLD_JENIS_IDENTITAS));
            entMappingDebiturBpd.setNik(pstMappingDebiturBpd.getString(FLD_NIK));
            entMappingDebiturBpd.setNamaIdentitas(pstMappingDebiturBpd.getString(FLD_NAMA_IDENTITAS));
            entMappingDebiturBpd.setNamaLengkap(pstMappingDebiturBpd.getString(FLD_NAMA_LENGKAP));
            entMappingDebiturBpd.setKodeStatusGelar(pstMappingDebiturBpd.getString(FLD_KODE_STATUS_GELAR));
            entMappingDebiturBpd.setJekel(pstMappingDebiturBpd.getString(FLD_JEKEL));
            entMappingDebiturBpd.setTempatLahir(pstMappingDebiturBpd.getString(FLD_TEMPAT_LAHIR));
            entMappingDebiturBpd.setTanggalLahir(pstMappingDebiturBpd.getDate(FLD_TANGGAL_LAHIR));
            entMappingDebiturBpd.setNpwp(pstMappingDebiturBpd.getString(FLD_NPWP));
            entMappingDebiturBpd.setAlamat(pstMappingDebiturBpd.getString(FLD_ALAMAT));
            entMappingDebiturBpd.setKelurahan(pstMappingDebiturBpd.getString(FLD_KELURAHAN));
            entMappingDebiturBpd.setKecamatan(pstMappingDebiturBpd.getString(FLD_KECAMATAN));
            entMappingDebiturBpd.setKodeKab(pstMappingDebiturBpd.getString(FLD_KODE_KAB));
            entMappingDebiturBpd.setKodePos(pstMappingDebiturBpd.getString(FLD_KODE_POS));
            entMappingDebiturBpd.setTelepon(pstMappingDebiturBpd.getString(FLD_TELEPON));
            entMappingDebiturBpd.setNomorHp(pstMappingDebiturBpd.getString(FLD_NOMOR_HP));
            entMappingDebiturBpd.setEmail(pstMappingDebiturBpd.getString(FLD_EMAIL));
            entMappingDebiturBpd.setKodeDomisili(pstMappingDebiturBpd.getString(FLD_KODE_DOMISILI));
            entMappingDebiturBpd.setKodePekerjaan(pstMappingDebiturBpd.getString(FLD_KODE_PEKERJAAN));
            entMappingDebiturBpd.setTempatBekerja(pstMappingDebiturBpd.getString(FLD_TEMPAT_BEKERJA));
            entMappingDebiturBpd.setKodeUsahaTempatBekerja(pstMappingDebiturBpd.getString(FLD_KODE_USAHA_TEMPAT_BEKERJA));
            entMappingDebiturBpd.setAlamatTempatBekerja(pstMappingDebiturBpd.getString(FLD_ALAMAT_TEMPAT_BEKERJA));
            entMappingDebiturBpd.setPenghasilanKotor(pstMappingDebiturBpd.getdouble(FLD_PENGHASILAN_KOTOR));
            entMappingDebiturBpd.setJmlTanggungan(pstMappingDebiturBpd.getInt(FLD_JML_TANGGUNGAN));
            entMappingDebiturBpd.setKodeHub(pstMappingDebiturBpd.getString(FLD_KODE_HUB));
            entMappingDebiturBpd.setKodeGol(pstMappingDebiturBpd.getString(FLD_KODE_GOL));
            entMappingDebiturBpd.setStatus(pstMappingDebiturBpd.getString(FLD_STATUS));
            entMappingDebiturBpd.setMelanggarBmpk(pstMappingDebiturBpd.getString(FLD_MELANGGAR_BMPK));
            entMappingDebiturBpd.setMelampuiBmpk(pstMappingDebiturBpd.getString(FLD_MELAMPUI_BMPK));
            entMappingDebiturBpd.setNamaIbuKandung(pstMappingDebiturBpd.getString(FLD_NAMA_IBU_KANDUNG));
            entMappingDebiturBpd.setKodeKantorCabang(pstMappingDebiturBpd.getString(FLD_KODE_KANTOR_CABANG));
            entMappingDebiturBpd.setNamaBadanUsaha(pstMappingDebiturBpd.getString(FLD_NAMA_BADAN_USAHA));
            entMappingDebiturBpd.setKodeJenisUsaha(pstMappingDebiturBpd.getString(FLD_KODE_JENIS_USAHA));
            entMappingDebiturBpd.setTempatPendirian(pstMappingDebiturBpd.getString(FLD_TEMPAT_PENDIRIAN));
            entMappingDebiturBpd.setNoAkte(pstMappingDebiturBpd.getString(FLD_NO_AKTE));
            entMappingDebiturBpd.setTglAktePendirian(pstMappingDebiturBpd.getDate(FLD_TGL_AKTE_PENDIRIAN));
            entMappingDebiturBpd.setNoAktePerubahan(pstMappingDebiturBpd.getString(FLD_NO_AKTE_PERUBAHAN));
            entMappingDebiturBpd.setTglAktePerubahan(pstMappingDebiturBpd.getDate(FLD_TGL_AKTE_PERUBAHAN));
            entMappingDebiturBpd.setKodeBidangUsaha(pstMappingDebiturBpd.getString(FLD_KODE_BIDANG_USAHA));
            entMappingDebiturBpd.setKodeHubLjk(pstMappingDebiturBpd.getString(FLD_KODE_HUB_LJK));
            entMappingDebiturBpd.setGoPublic(pstMappingDebiturBpd.getString(FLD_GO_PUBLIC));
            entMappingDebiturBpd.setPeringkat(pstMappingDebiturBpd.getString(FLD_PERINGKAT));
            entMappingDebiturBpd.setLembagaPemeringkat(pstMappingDebiturBpd.getString(FLD_LEMBAGA_PEMERINGKAT));
            entMappingDebiturBpd.setNamaGroup(pstMappingDebiturBpd.getString(FLD_NAMA_GROUP));
            entMappingDebiturBpd.setKodeJenisNsb(pstMappingDebiturBpd.getInt(FLD_KODE_JENIS_NSB));
            entMappingDebiturBpd.setOpenDate(pstMappingDebiturBpd.getDate(FLD_OPEN_DATE));//16
            entMappingDebiturBpd.setPeriodeId(pstMappingDebiturBpd.getlong(FLD_PERIODE_ID));
            entMappingDebiturBpd.setNoIdentitas(pstMappingDebiturBpd.getString(FLD_NO_IDENTITAS));
            entMappingDebiturBpd.setKodePenghasilan(pstMappingDebiturBpd.getString(FLD_KODE_PENGHASILAN));
            
            entMappingDebiturBpd.setNikPassportPasangan(pstMappingDebiturBpd.getString(FLD_NIK_PASSPORT_PASANGAN));
            entMappingDebiturBpd.setNamaPasangan(pstMappingDebiturBpd.getString(FLD_NAMA_PASANGAN));
            entMappingDebiturBpd.setTglLahirPasangan(pstMappingDebiturBpd.getDate(FLD_TGL_LAHIR_PASANGAN));
            entMappingDebiturBpd.setPerjanjianPisahHarta(pstMappingDebiturBpd.getString(FLD_PERJANJIAN_PISAH_HARTA));
            entMappingDebiturBpd.setTanggalPemeringkat(pstMappingDebiturBpd.getDate(FLD_TGL_PEMERINGKAT));
            
            return entMappingDebiturBpd;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingDebiturBpd(0), DBException.UNKNOWN);
        }
    }
    
    public long fetchExc(Entity entity) throws Exception {
        MappingDebiturBpd entMappingDebiturBpd = fetchExc(entity.getOID());
        entity = (Entity) entMappingDebiturBpd;
        return entMappingDebiturBpd.getOID();
    }

    public static synchronized long updateExc(MappingDebiturBpd entMappingDebiturBpd) throws DBException {
        try {
            if (entMappingDebiturBpd.getOID() != 0) {
                PstMappingDebiturBpd pstMappingDebiturBpd = new PstMappingDebiturBpd(entMappingDebiturBpd.getOID());
                pstMappingDebiturBpd.setString(FLD_FLAG_DETAIL, entMappingDebiturBpd.getFlagDetail());
                pstMappingDebiturBpd.setString(FLD_CIF, entMappingDebiturBpd.getCif());
                pstMappingDebiturBpd.setString(FLD_JENIS_IDENTITAS, entMappingDebiturBpd.getJenisIdentitas());
                pstMappingDebiturBpd.setString(FLD_NIK, entMappingDebiturBpd.getNik());
                pstMappingDebiturBpd.setString(FLD_NAMA_IDENTITAS, entMappingDebiturBpd.getNamaIdentitas());
                pstMappingDebiturBpd.setString(FLD_NAMA_LENGKAP, entMappingDebiturBpd.getNamaLengkap());
                pstMappingDebiturBpd.setString(FLD_KODE_STATUS_GELAR, entMappingDebiturBpd.getKodeStatusGelar());
                pstMappingDebiturBpd.setString(FLD_JEKEL, entMappingDebiturBpd.getJekel());
                pstMappingDebiturBpd.setString(FLD_TEMPAT_LAHIR, entMappingDebiturBpd.getTempatLahir());
                pstMappingDebiturBpd.setDate(FLD_TANGGAL_LAHIR, entMappingDebiturBpd.getTanggalLahir());
                pstMappingDebiturBpd.setString(FLD_NPWP, entMappingDebiturBpd.getNpwp());
                pstMappingDebiturBpd.setString(FLD_ALAMAT, entMappingDebiturBpd.getAlamat());
                pstMappingDebiturBpd.setString(FLD_KELURAHAN, entMappingDebiturBpd.getKelurahan());
                pstMappingDebiturBpd.setString(FLD_KECAMATAN, entMappingDebiturBpd.getKecamatan());
                pstMappingDebiturBpd.setString(FLD_KODE_KAB, entMappingDebiturBpd.getKodeKab());
                pstMappingDebiturBpd.setString(FLD_KODE_POS, entMappingDebiturBpd.getKodePos());
                pstMappingDebiturBpd.setString(FLD_TELEPON, entMappingDebiturBpd.getTelepon());
                pstMappingDebiturBpd.setString(FLD_NOMOR_HP, entMappingDebiturBpd.getNomorHp());
                pstMappingDebiturBpd.setString(FLD_EMAIL, entMappingDebiturBpd.getEmail());
                pstMappingDebiturBpd.setString(FLD_KODE_DOMISILI, entMappingDebiturBpd.getKodeDomisili());
                pstMappingDebiturBpd.setString(FLD_KODE_PEKERJAAN, entMappingDebiturBpd.getKodePekerjaan());
                pstMappingDebiturBpd.setString(FLD_TEMPAT_BEKERJA, entMappingDebiturBpd.getTempatBekerja());
                pstMappingDebiturBpd.setString(FLD_KODE_USAHA_TEMPAT_BEKERJA, entMappingDebiturBpd.getKodeUsahaTempatBekerja());
                pstMappingDebiturBpd.setString(FLD_ALAMAT_TEMPAT_BEKERJA, entMappingDebiturBpd.getAlamatTempatBekerja());
                pstMappingDebiturBpd.setDouble(FLD_PENGHASILAN_KOTOR, entMappingDebiturBpd.getPenghasilanKotor());
                pstMappingDebiturBpd.setInt(FLD_JML_TANGGUNGAN, entMappingDebiturBpd.getJmlTanggungan());
                pstMappingDebiturBpd.setString(FLD_KODE_HUB, entMappingDebiturBpd.getKodeHub());
                pstMappingDebiturBpd.setString(FLD_KODE_GOL, entMappingDebiturBpd.getKodeGol());
                pstMappingDebiturBpd.setString(FLD_STATUS, entMappingDebiturBpd.getStatus());
                pstMappingDebiturBpd.setString(FLD_MELANGGAR_BMPK, entMappingDebiturBpd.getMelanggarBmpk());
                pstMappingDebiturBpd.setString(FLD_MELAMPUI_BMPK, entMappingDebiturBpd.getMelampuiBmpk());
                pstMappingDebiturBpd.setString(FLD_NAMA_IBU_KANDUNG, entMappingDebiturBpd.getNamaIbuKandung());
                pstMappingDebiturBpd.setString(FLD_KODE_KANTOR_CABANG, entMappingDebiturBpd.getKodeKantorCabang());
                pstMappingDebiturBpd.setString(FLD_NAMA_BADAN_USAHA, entMappingDebiturBpd.getNamaBadanUsaha());
                pstMappingDebiturBpd.setString(FLD_KODE_JENIS_USAHA, entMappingDebiturBpd.getKodeJenisUsaha());
                pstMappingDebiturBpd.setString(FLD_TEMPAT_PENDIRIAN, entMappingDebiturBpd.getTempatPendirian());
                pstMappingDebiturBpd.setString(FLD_NO_AKTE, entMappingDebiturBpd.getNoAkte());
                pstMappingDebiturBpd.setDate(FLD_TGL_AKTE_PENDIRIAN, entMappingDebiturBpd.getTglAktePendirian());
                pstMappingDebiturBpd.setString(FLD_NO_AKTE_PERUBAHAN, entMappingDebiturBpd.getNoAktePerubahan());
                pstMappingDebiturBpd.setDate(FLD_TGL_AKTE_PERUBAHAN, entMappingDebiturBpd.getTglAktePerubahan());
                pstMappingDebiturBpd.setString(FLD_KODE_BIDANG_USAHA, entMappingDebiturBpd.getKodeBidangUsaha());
                pstMappingDebiturBpd.setString(FLD_KODE_HUB_LJK, entMappingDebiturBpd.getKodeHubLjk());
                pstMappingDebiturBpd.setString(FLD_GO_PUBLIC, entMappingDebiturBpd.getGoPublic());
                pstMappingDebiturBpd.setString(FLD_PERINGKAT, entMappingDebiturBpd.getPeringkat());
                pstMappingDebiturBpd.setString(FLD_LEMBAGA_PEMERINGKAT, entMappingDebiturBpd.getLembagaPemeringkat());
                pstMappingDebiturBpd.setString(FLD_NAMA_GROUP, entMappingDebiturBpd.getNamaGroup());
                pstMappingDebiturBpd.setInt(FLD_KODE_JENIS_NSB, entMappingDebiturBpd.getKodeJenisNsb());
                pstMappingDebiturBpd.setDate(FLD_OPEN_DATE,  entMappingDebiturBpd.getOpenDate());
                pstMappingDebiturBpd.setLong(FLD_PERIODE_ID, entMappingDebiturBpd.getPeriodeId());
                pstMappingDebiturBpd.setString(FLD_NO_IDENTITAS,entMappingDebiturBpd.getNoIdentitas());
                pstMappingDebiturBpd.setString( FLD_KODE_PENGHASILAN, entMappingDebiturBpd.getKodePenghasilan());
                
                pstMappingDebiturBpd.setString(FLD_NIK_PASSPORT_PASANGAN, entMappingDebiturBpd.getNikPassportPasangan());
                pstMappingDebiturBpd.setString(FLD_NAMA_PASANGAN, entMappingDebiturBpd.getNamaPasangan());
                pstMappingDebiturBpd.setDate(FLD_TGL_LAHIR_PASANGAN, entMappingDebiturBpd.getTglLahirPasangan());
                pstMappingDebiturBpd.setString(FLD_PERJANJIAN_PISAH_HARTA, entMappingDebiturBpd.getPerjanjianPisahHarta());
                pstMappingDebiturBpd.setDate(FLD_TGL_PEMERINGKAT, entMappingDebiturBpd.getTanggalPemeringkat());
                
                pstMappingDebiturBpd.update();
                return entMappingDebiturBpd.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingDebiturBpd(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((MappingDebiturBpd) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMappingDebiturBpd pstMappingDebiturBpd = new PstMappingDebiturBpd(oid);
            pstMappingDebiturBpd.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingDebiturBpd(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MappingDebiturBpd entMappingDebiturBpd) throws DBException {
        try {
            PstMappingDebiturBpd pstMappingDebiturBpd = new PstMappingDebiturBpd(0);
            pstMappingDebiturBpd.setString(FLD_FLAG_DETAIL, entMappingDebiturBpd.getFlagDetail());
            pstMappingDebiturBpd.setString(FLD_CIF, entMappingDebiturBpd.getCif());
            pstMappingDebiturBpd.setString(FLD_JENIS_IDENTITAS, entMappingDebiturBpd.getJenisIdentitas());
            pstMappingDebiturBpd.setString(FLD_NIK, entMappingDebiturBpd.getNik());
            pstMappingDebiturBpd.setString(FLD_NAMA_IDENTITAS, entMappingDebiturBpd.getNamaIdentitas());
            pstMappingDebiturBpd.setString(FLD_NAMA_LENGKAP, entMappingDebiturBpd.getNamaLengkap());
            pstMappingDebiturBpd.setString(FLD_KODE_STATUS_GELAR, entMappingDebiturBpd.getKodeStatusGelar());
            pstMappingDebiturBpd.setString(FLD_JEKEL, entMappingDebiturBpd.getJekel());
            pstMappingDebiturBpd.setString(FLD_TEMPAT_LAHIR, entMappingDebiturBpd.getTempatLahir());
            pstMappingDebiturBpd.setDate(FLD_TANGGAL_LAHIR, entMappingDebiturBpd.getTanggalLahir());
            pstMappingDebiturBpd.setString(FLD_NPWP, entMappingDebiturBpd.getNpwp());
            pstMappingDebiturBpd.setString(FLD_ALAMAT, entMappingDebiturBpd.getAlamat());
            pstMappingDebiturBpd.setString(FLD_KELURAHAN, entMappingDebiturBpd.getKelurahan());
            pstMappingDebiturBpd.setString(FLD_KECAMATAN, entMappingDebiturBpd.getKecamatan());
            pstMappingDebiturBpd.setString(FLD_KODE_KAB, entMappingDebiturBpd.getKodeKab());
            pstMappingDebiturBpd.setString(FLD_KODE_POS, entMappingDebiturBpd.getKodePos());
            pstMappingDebiturBpd.setString(FLD_TELEPON, entMappingDebiturBpd.getTelepon());
            pstMappingDebiturBpd.setString(FLD_NOMOR_HP, entMappingDebiturBpd.getNomorHp());
            pstMappingDebiturBpd.setString(FLD_EMAIL, entMappingDebiturBpd.getEmail());
            pstMappingDebiturBpd.setString(FLD_KODE_DOMISILI, entMappingDebiturBpd.getKodeDomisili());
            pstMappingDebiturBpd.setString(FLD_KODE_PEKERJAAN, entMappingDebiturBpd.getKodePekerjaan());
            pstMappingDebiturBpd.setString(FLD_TEMPAT_BEKERJA, entMappingDebiturBpd.getTempatBekerja());
            pstMappingDebiturBpd.setString(FLD_KODE_USAHA_TEMPAT_BEKERJA, entMappingDebiturBpd.getKodeUsahaTempatBekerja());
            pstMappingDebiturBpd.setString(FLD_ALAMAT_TEMPAT_BEKERJA, entMappingDebiturBpd.getAlamatTempatBekerja());
            pstMappingDebiturBpd.setDouble(FLD_PENGHASILAN_KOTOR, entMappingDebiturBpd.getPenghasilanKotor());
            pstMappingDebiturBpd.setInt(FLD_JML_TANGGUNGAN, entMappingDebiturBpd.getJmlTanggungan());
            pstMappingDebiturBpd.setString(FLD_KODE_HUB, entMappingDebiturBpd.getKodeHub());
            pstMappingDebiturBpd.setString(FLD_KODE_GOL, entMappingDebiturBpd.getKodeGol());
            pstMappingDebiturBpd.setString(FLD_STATUS, entMappingDebiturBpd.getStatus());
            pstMappingDebiturBpd.setString(FLD_MELANGGAR_BMPK, entMappingDebiturBpd.getMelanggarBmpk());
            pstMappingDebiturBpd.setString(FLD_MELAMPUI_BMPK, entMappingDebiturBpd.getMelampuiBmpk());
            pstMappingDebiturBpd.setString(FLD_NAMA_IBU_KANDUNG, entMappingDebiturBpd.getNamaIbuKandung());
            pstMappingDebiturBpd.setString(FLD_KODE_KANTOR_CABANG, entMappingDebiturBpd.getKodeKantorCabang());
            pstMappingDebiturBpd.setString(FLD_NAMA_BADAN_USAHA, entMappingDebiturBpd.getNamaBadanUsaha());
            pstMappingDebiturBpd.setString(FLD_KODE_JENIS_USAHA, entMappingDebiturBpd.getKodeJenisUsaha());
            pstMappingDebiturBpd.setString(FLD_TEMPAT_PENDIRIAN, entMappingDebiturBpd.getTempatPendirian());
            pstMappingDebiturBpd.setString(FLD_NO_AKTE, entMappingDebiturBpd.getNoAkte());
            pstMappingDebiturBpd.setDate(FLD_TGL_AKTE_PENDIRIAN, entMappingDebiturBpd.getTglAktePendirian());
            pstMappingDebiturBpd.setString(FLD_NO_AKTE_PERUBAHAN, entMappingDebiturBpd.getNoAktePerubahan());
            pstMappingDebiturBpd.setDate(FLD_TGL_AKTE_PERUBAHAN, entMappingDebiturBpd.getTglAktePerubahan());
            pstMappingDebiturBpd.setString(FLD_KODE_BIDANG_USAHA, entMappingDebiturBpd.getKodeBidangUsaha());
            pstMappingDebiturBpd.setString(FLD_KODE_HUB_LJK, entMappingDebiturBpd.getKodeHubLjk());
            pstMappingDebiturBpd.setString(FLD_GO_PUBLIC, entMappingDebiturBpd.getGoPublic());
            pstMappingDebiturBpd.setString(FLD_PERINGKAT, entMappingDebiturBpd.getPeringkat());
            pstMappingDebiturBpd.setString(FLD_LEMBAGA_PEMERINGKAT, entMappingDebiturBpd.getLembagaPemeringkat());
            pstMappingDebiturBpd.setString(FLD_NAMA_GROUP, entMappingDebiturBpd.getNamaGroup());
            pstMappingDebiturBpd.setInt(FLD_KODE_JENIS_NSB, entMappingDebiturBpd.getKodeJenisNsb());
            pstMappingDebiturBpd.setDate(FLD_OPEN_DATE,  entMappingDebiturBpd.getOpenDate());
            pstMappingDebiturBpd.setLong(FLD_PERIODE_ID, entMappingDebiturBpd.getPeriodeId());
            
            pstMappingDebiturBpd.setString(FLD_NO_IDENTITAS, entMappingDebiturBpd.getNoIdentitas());
            pstMappingDebiturBpd.setString(FLD_KODE_PENGHASILAN,  entMappingDebiturBpd.getKodePenghasilan());
            
            //tdk ada di core
            pstMappingDebiturBpd.setString(FLD_NIK_PASSPORT_PASANGAN, entMappingDebiturBpd.getNikPassportPasangan());
            pstMappingDebiturBpd.setString(FLD_NAMA_PASANGAN, entMappingDebiturBpd.getNamaPasangan());
            pstMappingDebiturBpd.setDate(FLD_TGL_LAHIR_PASANGAN, entMappingDebiturBpd.getTglLahirPasangan());
            pstMappingDebiturBpd.setString(FLD_PERJANJIAN_PISAH_HARTA, entMappingDebiturBpd.getPerjanjianPisahHarta());
            pstMappingDebiturBpd.setDate(FLD_TGL_PEMERINGKAT, entMappingDebiturBpd.getTanggalPemeringkat());

            
            
            pstMappingDebiturBpd.insert();
            entMappingDebiturBpd.setOID(pstMappingDebiturBpd.getlong(FLD_MAPPING_DEBITUR_BPD_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingDebiturBpd(0), DBException.UNKNOWN);
        }
        return entMappingDebiturBpd.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((MappingDebiturBpd) entity);
    }

    public static void resultToObject(ResultSet rs, MappingDebiturBpd entMappingDebiturBpd) {
        try {
            entMappingDebiturBpd.setOID(rs.getLong(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_MAPPING_DEBITUR_BPD_OID]));
            entMappingDebiturBpd.setFlagDetail(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_FLAG_DETAIL]));
            entMappingDebiturBpd.setCif(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_CIF]));
            entMappingDebiturBpd.setJenisIdentitas(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_JENIS_IDENTITAS]));
            entMappingDebiturBpd.setNik(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NIK]));
            entMappingDebiturBpd.setNamaIdentitas(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NAMA_IDENTITAS]));
            entMappingDebiturBpd.setNamaLengkap(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NAMA_LENGKAP]));
            entMappingDebiturBpd.setKodeStatusGelar(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_STATUS_GELAR]));
            entMappingDebiturBpd.setJekel(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_JEKEL]));
            entMappingDebiturBpd.setTempatLahir(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_TEMPAT_LAHIR]));
            entMappingDebiturBpd.setTanggalLahir(rs.getDate(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_TANGGAL_LAHIR]));
            entMappingDebiturBpd.setNpwp(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NPWP]));
            entMappingDebiturBpd.setAlamat(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_ALAMAT]));
            entMappingDebiturBpd.setKelurahan(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KELURAHAN]));
            entMappingDebiturBpd.setKecamatan(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KECAMATAN]));
            entMappingDebiturBpd.setKodeKab(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_KAB]));
            entMappingDebiturBpd.setKodePos(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_POS]));
            entMappingDebiturBpd.setTelepon(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_TELEPON]));
            entMappingDebiturBpd.setNomorHp(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NOMOR_HP]));
            entMappingDebiturBpd.setEmail(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_EMAIL]));
            entMappingDebiturBpd.setKodeDomisili(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_DOMISILI]));
            entMappingDebiturBpd.setKodePekerjaan(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_PEKERJAAN]));
            entMappingDebiturBpd.setTempatBekerja(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_TEMPAT_BEKERJA]));
            entMappingDebiturBpd.setKodeUsahaTempatBekerja(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_USAHA_TEMPAT_BEKERJA]));
            entMappingDebiturBpd.setAlamatTempatBekerja(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_ALAMAT_TEMPAT_BEKERJA]));
            entMappingDebiturBpd.setPenghasilanKotor(rs.getDouble(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_PENGHASILAN_KOTOR]));
            entMappingDebiturBpd.setJmlTanggungan(rs.getInt(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_JML_TANGGUNGAN]));
            entMappingDebiturBpd.setKodeHub(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_HUB]));
            entMappingDebiturBpd.setKodeGol(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_GOL]));
            entMappingDebiturBpd.setStatus(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_STATUS]));
            entMappingDebiturBpd.setMelanggarBmpk(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_MELANGGAR_BMPK]));
            entMappingDebiturBpd.setMelampuiBmpk(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_MELAMPUI_BMPK]));
            entMappingDebiturBpd.setNamaIbuKandung(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NAMA_IBU_KANDUNG]));
            entMappingDebiturBpd.setKodeKantorCabang(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_KANTOR_CABANG]));
            entMappingDebiturBpd.setNamaBadanUsaha(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NAMA_BADAN_USAHA]));
            entMappingDebiturBpd.setKodeJenisUsaha(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_JENIS_USAHA]));
            entMappingDebiturBpd.setTempatPendirian(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_TEMPAT_PENDIRIAN]));
            entMappingDebiturBpd.setNoAkte(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NO_AKTE]));
            entMappingDebiturBpd.setTglAktePendirian(rs.getDate(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_TGL_AKTE_PENDIRIAN]));
            entMappingDebiturBpd.setNoAktePerubahan(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NO_AKTE_PERUBAHAN]));
            entMappingDebiturBpd.setTglAktePerubahan(rs.getDate(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_TGL_AKTE_PERUBAHAN]));
            entMappingDebiturBpd.setKodeBidangUsaha(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_BIDANG_USAHA]));
            entMappingDebiturBpd.setKodeHubLjk(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_HUB_LJK]));
            entMappingDebiturBpd.setGoPublic(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_GO_PUBLIC]));
            entMappingDebiturBpd.setPeringkat(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_PERINGKAT]));
            entMappingDebiturBpd.setLembagaPemeringkat(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_LEMBAGA_PEMERINGKAT]));
            entMappingDebiturBpd.setNamaGroup(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NAMA_GROUP]));
            entMappingDebiturBpd.setKodeJenisNsb(rs.getInt(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_JENIS_NSB]));
            entMappingDebiturBpd.setPeriodeId(rs.getLong(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_PERIODE_ID]));
            entMappingDebiturBpd.setNoIdentitas(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_NO_IDENTITAS]));
            
            entMappingDebiturBpd.setKodePenghasilan(rs.getString(PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_KODE_PENGHASILAN]));
            
            entMappingDebiturBpd.setOpenDate(rs.getDate(PstMappingDebiturBpd.FLD_OPEN_DATE));
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
            String sql = "SELECT * FROM " + TBL_MAPPING_DEBITUR_BPD;
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
                MappingDebiturBpd entMappingDebiturBpd = new MappingDebiturBpd();
                resultToObject(rs, entMappingDebiturBpd);
                lists.add(entMappingDebiturBpd);
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

    public static boolean checkOID(long entMappingDebiturBpdId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_DEBITUR_BPD + " WHERE "
                    + PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_MAPPING_DEBITUR_BPD_OID] + " = " + entMappingDebiturBpdId;
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
    
    
    public static long checkDebitur(long periodeId, String cif) {
        DBResultSet dbrs = null;
        long result = 0;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_DEBITUR_BPD + " WHERE "
                    + PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_PERIODE_ID] + " = '" + periodeId+"'"
                    + " AND "+PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_CIF]+"='"+cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getLong(""+fieldNames[FLD_MAPPING_DEBITUR_BPD_OID]);
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
            String sql = "SELECT COUNT(" + PstMappingDebiturBpd.fieldNames[PstMappingDebiturBpd.FLD_MAPPING_DEBITUR_BPD_OID] + ") FROM " + TBL_MAPPING_DEBITUR_BPD;
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
                    MappingDebiturBpd entMappingDebiturBpd = (MappingDebiturBpd) list.get(ls);
                    if (oid == entMappingDebiturBpd.getOID()) {
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
    
     public static int deleteSegmentDebiturPerPeriode(long periodeId, String cif) {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "DELETE FROM " + TBL_MAPPING_DEBITUR_BPD
                + " WHERE " + fieldNames[FLD_PERIODE_ID] + " = " + periodeId;
             if(!cif.equals("")){
               stSql=stSql+" AND " + fieldNames[FLD_CIF]+"='"+cif+"'";
        }
        try {
            iResult = DBHandler.execUpdate(stSql);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            DBResultSet.close(dbrs);
        }
        return iResult;
    }
    
}
