/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.debitur;

import com.dimata.dslik.entity.masterdata.PstPeriode;
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

/**
 *
 * @author dimata005
 */
public class PstDebitur extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language{
    
    public static final String TBL_DEBITUR_INDIVIDU = "dslik_debitur";
    public static final int FLD_DEBITUR_OID = 0;
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
    
    public static final int FLD_NO_IDENTITAS = 40;
    public static final int FLD_NAMA_BADAN_USAHA = 41;
    public static final int FLD_KODE_JENIS_USAHA = 42;
    public static final int FLD_TEMPAT_PENDIRIAN = 43;
    public static final int FLD_NO_AKTE = 44;
    public static final int FLD_TGL_AKTE_PENDIRIAN = 45;
    public static final int FLD_NO_AKTE_PERUBAHAN = 46;
    public static final int FLD_TGL_AKTE_PERUBAHAN = 47;
    public static final int FLD_KODE_BIDANG_USAHA = 48;
    public static final int FLD_KODE_HUB_LJK = 49;
    public static final int FLD_GO_PUBLIC = 50;
    public static final int FLD_PERINGKAT = 51;
    public static final int FLD_LEMBAGA_PEMERINGKAT = 52;
    public static final int FLD_TGL_PEMERINGKAT = 53;
    public static final int FLD_NAMA_GROUP = 54;
    public static final int FLD_OPEN_DATE = 55;
    public static final int FLD_KODE_JENIS_NSB=56;
    public static final int FLD_PERIODE_ID=57;
    public static final int FLD_STATUS_DATA = 58;
    public static final int FLD_STATUS_PERUBAHAN_DATA = 59;
    
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
        "TANGGAL_LAHIR",//10
        "NPWP",
        "ALAMAT",
        "KELURAHAN",
        "KECAMATAN",
        "KODE_KAB",
        "KODE_POS",
        "TELEPON",
        "NOMOR_HP",
        "EMAIL",
        "KODE_DOMISILI",//20
        "KODE_PEKERJAAN",
        "TEMPAT_BEKERJA",
        "KODE_USAHA_TEMPAT_BEKERJA",
        "ALAMAT_TEMPAT_BEKERJA",
        "PENGHASILAN_KOTOR",
        "KODE_PENGHASILAN",
        "JML_TANGGUNGAN",
        "KODE_HUB",
        "KODE_GOL",
        "STATUS",//30
        "NIK_PASANGAN",
        "NAMA_PASANGAN",
        "TGL_LAHIR_PASANGAN",
        "PERJANJIAN_PISAH_HARGA",
        "MELANGGAR_BMPK",
        "MELAMPAUI_BMPK",
        "NAMA_IBU_KANDUNG",
        "KODE_KANTOR_CABANG",
        "OPERASI_DATA",
        
        "NO_IDENTITAS",//40
        "NAMA_BADAN_USAHA",
        "KODE_JENIS_USAHA",
        "TEMPAT_PENDIRIAN",
        "NO_AKTE",
        "TGL_AKTE_PENDIRIAN",
        "NO_AKTE_PERUBAHAN",
        "TGL_AKTE_PERUBAHAN",
        "KODE_BIDANG_USAHA",
        "KODE_HUB_LJK",
        "GO_PUBLIC",//50
        "PERINGKAT",
        "LEMBAGA_PEMERINGKAT",
        "TGL_PEMERINGKAT",
        "NAMA_GROUP",
        "OPEN_DATE",
        "KODE_JENIS_NSB",
        "PERIODE_ID",
        "STATUS_OPERASI_DATA",
        "STATUS_DATA"
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
        
        TYPE_STRING,//1
        TYPE_STRING,//2
        TYPE_STRING,//3
        TYPE_STRING,//4
        TYPE_STRING,//5
        TYPE_STRING,//6
        TYPE_STRING,//7
        TYPE_STRING,//8
        TYPE_STRING,//9
        TYPE_STRING,//10
        TYPE_STRING,//11
        TYPE_STRING,//12
        TYPE_STRING,//13
        TYPE_STRING,//14
        TYPE_STRING,//15
        TYPE_DATE,//16
        TYPE_INT,
        TYPE_LONG,
        TYPE_INT,
        TYPE_INT
    };

    public PstDebitur() {
    }
    
    public static String[] pisahHargaKey = {
      "Y",
      "T"
    };
    public static String[] pisahHargaVal = {
      "Ya",
      "Tidak"
    };
    
    public static String[] operasiData = {
        "Create",
        "Update",
        "Not Change"
    };
    
    public static String[] operasiDataKey = {
        "0",
        "1",
        "2"
    };
    
    public PstDebitur(int i) throws DBException {
        super(new PstDebitur());
    }

    public PstDebitur(String sOid) throws DBException {
        super(new PstDebitur(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstDebitur(long lOid) throws DBException {
        super(new PstDebitur(0));
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
        return new PstDebitur().getClass().getName();
    }

    public static Debitur fetchExc(long oid) throws DBException {
        try {
            Debitur entDebitur = new Debitur();
            PstDebitur pstDebitur = new PstDebitur(oid);
            entDebitur.setOID(oid);
            entDebitur.setFlagDetail(pstDebitur.getString(FLD_FLAG_DETAIL));
            entDebitur.setCif(pstDebitur.getString(FLD_CIF));
            entDebitur.setJenisIdentitas(pstDebitur.getString(FLD_JENIS_IDENTITAS));
            entDebitur.setNik(pstDebitur.getString(FLD_NIK));
            entDebitur.setNamaIdentitas(pstDebitur.getString(FLD_NAMA_IDENTITAS));
            entDebitur.setNamaLengkap(pstDebitur.getString(FLD_NAMA_LENGKAP));
            entDebitur.setKodeStatusGelar(pstDebitur.getString(FLD_KODE_STATUS_GELAR));
            entDebitur.setJekel(pstDebitur.getString(FLD_JEKEL));
            entDebitur.setTempatLahir(pstDebitur.getString(FLD_TEMPAT_LAHIR));
            entDebitur.setTglLahir(pstDebitur.getDate(FLD_TGL_LAHIR));
            entDebitur.setNpwp(pstDebitur.getString(FLD_NPWP));
            entDebitur.setAlamat(pstDebitur.getString(FLD_ALAMAT));
            entDebitur.setKelurahan(pstDebitur.getString(FLD_KELURAHAN));
            entDebitur.setKecamatan(pstDebitur.getString(FLD_KECAMATAN));
            entDebitur.setKodeKab(pstDebitur.getString(FLD_KODE_KAB));
            entDebitur.setKodePos(pstDebitur.getString(FLD_KODE_POS));
            entDebitur.setTelepon(pstDebitur.getString(FLD_TELEPON));
            entDebitur.setNomorHp(pstDebitur.getString(FLD_NOMOR_HP));
            entDebitur.setEmail(pstDebitur.getString(FLD_EMAIL));
            entDebitur.setKodeDomisili(pstDebitur.getString(FLD_KODE_DOMISILI));
            entDebitur.setKodePekerjaan(pstDebitur.getString(FLD_KODE_PEKERJAAN));
            entDebitur.setTempatBekerja(pstDebitur.getString(FLD_TEMPAT_BEKERJA));
            entDebitur.setKodeUsahaTempatBekerja(pstDebitur.getString(FLD_KODE_USAHA_TEMPAT_BEKERJA));
            entDebitur.setAlamatTempatBekerja(pstDebitur.getString(FLD_ALAMAT_TEMPAT_BEKERJA));
            entDebitur.setPenghasilanKotor(pstDebitur.getdouble(FLD_PENGHASILAN_KOTOR));
            entDebitur.setKodePenghasilan(pstDebitur.getString(FLD_KODE_PENGHASILAN));
            entDebitur.setJmlTanggungan(pstDebitur.getInt(FLD_JML_TANGGUNGAN));
            entDebitur.setKodeHub(pstDebitur.getString(FLD_KODE_HUB));
            entDebitur.setKodeGol(pstDebitur.getString(FLD_KODE_GOL));
            entDebitur.setStatus(pstDebitur.getString(FLD_STATUS));
            entDebitur.setNikPasangan(pstDebitur.getString(FLD_NIK_PASANGAN));
            entDebitur.setNamaPasangan(pstDebitur.getString(FLD_NAMA_PASANGAN));
            entDebitur.setTglLahirPasangan(pstDebitur.getDate(FLD_TGL_LAHIR_PASANGAN));
            entDebitur.setPerjanjianPisahHarga(pstDebitur.getString(FLD_PERJANJIAN_PISAH_HARGA));
            entDebitur.setMelanggarBmpk(pstDebitur.getString(FLD_MELANGGAR_BMPK));
            entDebitur.setMelampauiBmpk(pstDebitur.getString(FLD_MELAMPAUI_BMPK));
            entDebitur.setNamaIbuKandung(pstDebitur.getString(FLD_NAMA_IBU_KANDUNG));
            entDebitur.setKodeKantorCabang(pstDebitur.getString(FLD_KODE_KANTOR_CABANG));
            entDebitur.setOperasiData(pstDebitur.getString(FLD_OPERASI_DATA));
            
            entDebitur.setNoIdentitas(pstDebitur.getString(FLD_NO_IDENTITAS));//1
            entDebitur.setNamaBadanUsaha(pstDebitur.getString(FLD_NAMA_BADAN_USAHA));//2
            entDebitur.setKodeJenisUsaha(pstDebitur.getString(FLD_KODE_JENIS_USAHA));//3
            entDebitur.setTempatPendirian(pstDebitur.getString(FLD_TEMPAT_PENDIRIAN));//4
            entDebitur.setNoAkte(pstDebitur.getString(FLD_NO_AKTE));//5
            entDebitur.setTglAktePendirian(pstDebitur.getDate(FLD_TGL_AKTE_PENDIRIAN));//6
            entDebitur.setNoAktePerubahan(pstDebitur.getString(FLD_NO_AKTE_PERUBAHAN));//7
            entDebitur.setTglAktePerubahan(pstDebitur.getDate(FLD_TGL_AKTE_PERUBAHAN));//8
            entDebitur.setKodeBidangUsaha(pstDebitur.getString(FLD_KODE_BIDANG_USAHA));//9
            entDebitur.setKodeHubLjk(pstDebitur.getString(FLD_KODE_HUB_LJK));//10
            entDebitur.setGoPublic(pstDebitur.getString(FLD_GO_PUBLIC));//11
            entDebitur.setPeringkat(pstDebitur.getString(FLD_PERINGKAT));//12
            entDebitur.setLembagaPemeringkat(pstDebitur.getString(FLD_LEMBAGA_PEMERINGKAT));//13
            entDebitur.setTglPemeringkat(pstDebitur.getDate(FLD_TGL_PEMERINGKAT));//14
            entDebitur.setNamaGroup(pstDebitur.getString(FLD_NAMA_GROUP));//15
            entDebitur.setOpenDate(pstDebitur.getDate(FLD_OPEN_DATE));//16
            entDebitur.setKodeJenisNsb(pstDebitur.getInt(FLD_KODE_JENIS_NSB));//17
            entDebitur.setPeriodeId(pstDebitur.getlong(FLD_PERIODE_ID));
            entDebitur.setStatusPerubahanData(pstDebitur.getInt(FLD_STATUS_PERUBAHAN_DATA));
            
            return entDebitur;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebitur(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        Debitur entDebitur = fetchExc(entity.getOID());
        entity = (Entity) entDebitur;
        return entDebitur.getOID();
    }

    public static synchronized long updateExc(Debitur entDebitur) throws DBException {
        try {
            if (entDebitur.getOID() != 0) {
                PstDebitur pstDebitur = new PstDebitur(entDebitur.getOID());
                pstDebitur.setString(FLD_FLAG_DETAIL, entDebitur.getFlagDetail());
                pstDebitur.setString(FLD_CIF, entDebitur.getCif());
                pstDebitur.setString(FLD_JENIS_IDENTITAS, entDebitur.getJenisIdentitas());
                pstDebitur.setString(FLD_NIK, entDebitur.getNik());
                pstDebitur.setString(FLD_NAMA_IDENTITAS, entDebitur.getNamaIdentitas());
                pstDebitur.setString(FLD_NAMA_LENGKAP, entDebitur.getNamaLengkap());
                pstDebitur.setString(FLD_KODE_STATUS_GELAR, entDebitur.getKodeStatusGelar());
                pstDebitur.setString(FLD_JEKEL, entDebitur.getJekel());
                pstDebitur.setString(FLD_TEMPAT_LAHIR, entDebitur.getTempatLahir());
                pstDebitur.setDate(FLD_TGL_LAHIR, entDebitur.getTglLahir());
                pstDebitur.setString(FLD_NPWP, entDebitur.getNpwp());
                pstDebitur.setString(FLD_ALAMAT, entDebitur.getAlamat());
                pstDebitur.setString(FLD_KELURAHAN, entDebitur.getKelurahan());
                pstDebitur.setString(FLD_KECAMATAN, entDebitur.getKecamatan());
                pstDebitur.setString(FLD_KODE_KAB, entDebitur.getKodeKab());
                pstDebitur.setString(FLD_KODE_POS, entDebitur.getKodePos());
                pstDebitur.setString(FLD_TELEPON, entDebitur.getTelepon());
                pstDebitur.setString(FLD_NOMOR_HP, entDebitur.getNomorHp());
                pstDebitur.setString(FLD_EMAIL, entDebitur.getEmail());
                pstDebitur.setString(FLD_KODE_DOMISILI, entDebitur.getKodeDomisili());
                pstDebitur.setString(FLD_KODE_PEKERJAAN, entDebitur.getKodePekerjaan());
                pstDebitur.setString(FLD_TEMPAT_BEKERJA, entDebitur.getTempatBekerja());
                pstDebitur.setString(FLD_KODE_USAHA_TEMPAT_BEKERJA, entDebitur.getKodeUsahaTempatBekerja());
                pstDebitur.setString(FLD_ALAMAT_TEMPAT_BEKERJA, entDebitur.getAlamatTempatBekerja());
                pstDebitur.setDouble(FLD_PENGHASILAN_KOTOR, entDebitur.getPenghasilanKotor());
                pstDebitur.setString(FLD_KODE_PENGHASILAN, entDebitur.getKodePenghasilan());
                pstDebitur.setInt(FLD_JML_TANGGUNGAN, entDebitur.getJmlTanggungan());
                pstDebitur.setString(FLD_KODE_HUB, entDebitur.getKodeHub());
                pstDebitur.setString(FLD_KODE_GOL, entDebitur.getKodeGol());
                pstDebitur.setString(FLD_STATUS, entDebitur.getStatus());
                pstDebitur.setString(FLD_NIK_PASANGAN, entDebitur.getNikPasangan());
                pstDebitur.setString(FLD_NAMA_PASANGAN, entDebitur.getNamaPasangan());
                pstDebitur.setDate(FLD_TGL_LAHIR_PASANGAN, entDebitur.getTglLahirPasangan());
                pstDebitur.setString(FLD_PERJANJIAN_PISAH_HARGA, entDebitur.getPerjanjianPisahHarga());
                pstDebitur.setString(FLD_MELANGGAR_BMPK, entDebitur.getMelanggarBmpk());
                pstDebitur.setString(FLD_MELAMPAUI_BMPK, entDebitur.getMelampauiBmpk());
                pstDebitur.setString(FLD_NAMA_IBU_KANDUNG, entDebitur.getNamaIbuKandung());
                pstDebitur.setString(FLD_KODE_KANTOR_CABANG, entDebitur.getKodeKantorCabang());
                pstDebitur.setString(FLD_OPERASI_DATA, entDebitur.getOperasiData());
                
                /*badan usaha*/
                pstDebitur.setString(FLD_NO_IDENTITAS, entDebitur.getNoIdentitas());
                pstDebitur.setString(FLD_NAMA_BADAN_USAHA, entDebitur.getNamaBadanUsaha());
                pstDebitur.setString(FLD_KODE_JENIS_USAHA, entDebitur.getKodeJenisUsaha());
                pstDebitur.setString(FLD_TEMPAT_PENDIRIAN, entDebitur.getTempatPendirian());
                pstDebitur.setString(FLD_NO_AKTE,entDebitur.getNoAkte());
                pstDebitur.setDate(FLD_TGL_AKTE_PENDIRIAN, entDebitur.getTglAktePendirian());
                pstDebitur.setString(FLD_NO_AKTE_PERUBAHAN, entDebitur.getNoAktePerubahan());
                pstDebitur.setDate(FLD_TGL_AKTE_PERUBAHAN, entDebitur.getTglAktePerubahan());
                pstDebitur.setString(FLD_KODE_BIDANG_USAHA, entDebitur.getKodeBidangUsaha());
                pstDebitur.setString(FLD_KODE_HUB_LJK, entDebitur.getKodeHubLjk());
                pstDebitur.setString(FLD_GO_PUBLIC, entDebitur.getGoPublic());
                pstDebitur.setString(FLD_PERINGKAT, entDebitur.getPeringkat());
                pstDebitur.setString(FLD_LEMBAGA_PEMERINGKAT, entDebitur.getLembagaPemeringkat());
                pstDebitur.setDate(FLD_TGL_PEMERINGKAT, entDebitur.getTglPemeringkat());
                pstDebitur.setString(FLD_NAMA_GROUP, entDebitur.getNamaGroup());
                pstDebitur.setDate(FLD_OPEN_DATE,  entDebitur.getOpenDate());
                pstDebitur.setInt(FLD_KODE_JENIS_NSB, entDebitur.getKodeJenisNsb());
                pstDebitur.setLong(FLD_PERIODE_ID, entDebitur.getPeriodeId());
                pstDebitur.setInt( FLD_STATUS_PERUBAHAN_DATA,  entDebitur.getStatusPerubahanData());
                
                pstDebitur.update();
                return entDebitur.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebitur(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((Debitur) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstDebitur pstDebitur = new PstDebitur(oid);
            pstDebitur.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebitur(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(Debitur entDebitur) throws DBException {
        try {
            PstDebitur pstDebitur = new PstDebitur(0);
            pstDebitur.setString(FLD_FLAG_DETAIL, entDebitur.getFlagDetail());
            pstDebitur.setString(FLD_CIF, entDebitur.getCif());
            pstDebitur.setString(FLD_JENIS_IDENTITAS, entDebitur.getJenisIdentitas());
            pstDebitur.setString(FLD_NIK, entDebitur.getNik());
            pstDebitur.setString(FLD_NAMA_IDENTITAS, entDebitur.getNamaIdentitas());
            pstDebitur.setString(FLD_NAMA_LENGKAP, entDebitur.getNamaLengkap());
            pstDebitur.setString(FLD_KODE_STATUS_GELAR, entDebitur.getKodeStatusGelar());
            pstDebitur.setString(FLD_JEKEL, entDebitur.getJekel());
            pstDebitur.setString(FLD_TEMPAT_LAHIR, entDebitur.getTempatLahir());
            pstDebitur.setDate(FLD_TGL_LAHIR, entDebitur.getTglLahir());
            pstDebitur.setString(FLD_NPWP, entDebitur.getNpwp());
            pstDebitur.setString(FLD_ALAMAT, entDebitur.getAlamat());
            pstDebitur.setString(FLD_KELURAHAN, entDebitur.getKelurahan());
            pstDebitur.setString(FLD_KECAMATAN, entDebitur.getKecamatan());
            pstDebitur.setString(FLD_KODE_KAB, entDebitur.getKodeKab());
            pstDebitur.setString(FLD_KODE_POS, entDebitur.getKodePos());
            pstDebitur.setString(FLD_TELEPON, entDebitur.getTelepon());
            pstDebitur.setString(FLD_NOMOR_HP, entDebitur.getNomorHp());
            pstDebitur.setString(FLD_EMAIL, entDebitur.getEmail());
            pstDebitur.setString(FLD_KODE_DOMISILI, entDebitur.getKodeDomisili());
            pstDebitur.setString(FLD_KODE_PEKERJAAN, entDebitur.getKodePekerjaan());
            pstDebitur.setString(FLD_TEMPAT_BEKERJA, entDebitur.getTempatBekerja());
            pstDebitur.setString(FLD_KODE_USAHA_TEMPAT_BEKERJA, entDebitur.getKodeUsahaTempatBekerja());
            pstDebitur.setString(FLD_ALAMAT_TEMPAT_BEKERJA, entDebitur.getAlamatTempatBekerja());
            pstDebitur.setDouble(FLD_PENGHASILAN_KOTOR, entDebitur.getPenghasilanKotor());
            pstDebitur.setString(FLD_KODE_PENGHASILAN, entDebitur.getKodePenghasilan());
            pstDebitur.setInt(FLD_JML_TANGGUNGAN, entDebitur.getJmlTanggungan());
            pstDebitur.setString(FLD_KODE_HUB, entDebitur.getKodeHub());
            pstDebitur.setString(FLD_KODE_GOL, entDebitur.getKodeGol());
            pstDebitur.setString(FLD_STATUS, entDebitur.getStatus());
            pstDebitur.setString(FLD_NIK_PASANGAN, entDebitur.getNikPasangan());
            pstDebitur.setString(FLD_NAMA_PASANGAN, entDebitur.getNamaPasangan());
            pstDebitur.setDate(FLD_TGL_LAHIR_PASANGAN, entDebitur.getTglLahirPasangan());
            pstDebitur.setString(FLD_PERJANJIAN_PISAH_HARGA, entDebitur.getPerjanjianPisahHarga());
            pstDebitur.setString(FLD_MELANGGAR_BMPK, entDebitur.getMelanggarBmpk());
            pstDebitur.setString(FLD_MELAMPAUI_BMPK, entDebitur.getMelampauiBmpk());
            pstDebitur.setString(FLD_NAMA_IBU_KANDUNG, entDebitur.getNamaIbuKandung());
            pstDebitur.setString(FLD_KODE_KANTOR_CABANG, entDebitur.getKodeKantorCabang());
            pstDebitur.setString(FLD_OPERASI_DATA, entDebitur.getOperasiData());
            
            /*badan usaha*/
            pstDebitur.setString(FLD_NO_IDENTITAS, entDebitur.getNoIdentitas());
            pstDebitur.setString(FLD_NAMA_BADAN_USAHA, entDebitur.getNamaBadanUsaha());
            pstDebitur.setString(FLD_KODE_JENIS_USAHA, entDebitur.getKodeJenisUsaha());
            pstDebitur.setString(FLD_TEMPAT_PENDIRIAN, entDebitur.getTempatPendirian());
            pstDebitur.setString(FLD_NO_AKTE,entDebitur.getNoAkte());
            pstDebitur.setDate(FLD_TGL_AKTE_PENDIRIAN, entDebitur.getTglAktePendirian());
            pstDebitur.setString(FLD_NO_AKTE_PERUBAHAN, entDebitur.getNoAktePerubahan());
            pstDebitur.setDate(FLD_TGL_AKTE_PERUBAHAN, entDebitur.getTglAktePerubahan());
            pstDebitur.setString(FLD_KODE_BIDANG_USAHA, entDebitur.getKodeBidangUsaha());
            pstDebitur.setString(FLD_KODE_HUB_LJK, entDebitur.getKodeHubLjk());
            pstDebitur.setString(FLD_GO_PUBLIC, entDebitur.getGoPublic());
            pstDebitur.setString(FLD_PERINGKAT, entDebitur.getPeringkat());
            pstDebitur.setString(FLD_LEMBAGA_PEMERINGKAT, entDebitur.getLembagaPemeringkat());
            pstDebitur.setDate(FLD_TGL_PEMERINGKAT, entDebitur.getTglPemeringkat());
            pstDebitur.setString(FLD_NAMA_GROUP, entDebitur.getNamaGroup());
            pstDebitur.setDate(FLD_OPEN_DATE,  entDebitur.getOpenDate());
            pstDebitur.setInt(FLD_KODE_JENIS_NSB, entDebitur.getKodeJenisNsb());
            pstDebitur.setLong(FLD_PERIODE_ID, entDebitur.getPeriodeId());
            pstDebitur.setInt( FLD_STATUS_PERUBAHAN_DATA,  entDebitur.getStatusPerubahanData());
            pstDebitur.insert();
            entDebitur.setOID(pstDebitur.getLong(FLD_DEBITUR_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstDebitur(0), DBException.UNKNOWN);
        }
        return entDebitur.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((Debitur) entity);
    }

    public static void resultToObject(ResultSet rs, Debitur entDebitur) {
        try {
            entDebitur.setOID(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]));
            entDebitur.setFlagDetail(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_FLAG_DETAIL]));
            entDebitur.setCif(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_CIF]));
            entDebitur.setJenisIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_JENIS_IDENTITAS]));
            entDebitur.setNik(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NIK]));
            entDebitur.setNamaIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]));
            entDebitur.setNamaLengkap(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_LENGKAP]));
            entDebitur.setKodeStatusGelar(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_STATUS_GELAR]));
            entDebitur.setJekel(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_JEKEL]));
            entDebitur.setTempatLahir(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_LAHIR]));
            entDebitur.setTglLahir(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_LAHIR]));
            entDebitur.setNpwp(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NPWP]));
            entDebitur.setAlamat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_ALAMAT]));
            entDebitur.setKelurahan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KELURAHAN]));
            entDebitur.setKecamatan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KECAMATAN]));
            entDebitur.setKodeKab(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_KAB]));
            entDebitur.setKodePos(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_POS]));
            entDebitur.setTelepon(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TELEPON]));
            entDebitur.setNomorHp(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NOMOR_HP]));
            entDebitur.setEmail(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_EMAIL]));
            entDebitur.setKodeDomisili(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_DOMISILI]));
            entDebitur.setKodePekerjaan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_PEKERJAAN]));
            entDebitur.setTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_BEKERJA]));
            entDebitur.setKodeUsahaTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_USAHA_TEMPAT_BEKERJA]));
            entDebitur.setAlamatTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_ALAMAT_TEMPAT_BEKERJA]));
            entDebitur.setPenghasilanKotor(rs.getDouble(PstDebitur.fieldNames[PstDebitur.FLD_PENGHASILAN_KOTOR]));
            entDebitur.setKodePenghasilan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_PENGHASILAN]));
            entDebitur.setJmlTanggungan(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_JML_TANGGUNGAN]));
            entDebitur.setKodeHub(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_HUB]));
            entDebitur.setKodeGol(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_GOL]));
            entDebitur.setStatus(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_STATUS]));
            entDebitur.setNikPasangan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NIK_PASANGAN]));
            entDebitur.setNamaPasangan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_PASANGAN]));
            entDebitur.setTglLahirPasangan(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_LAHIR_PASANGAN]));
            entDebitur.setPerjanjianPisahHarga(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_PERJANJIAN_PISAH_HARGA]));
            entDebitur.setMelanggarBmpk(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_MELANGGAR_BMPK]));
            entDebitur.setMelampauiBmpk(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_MELAMPAUI_BMPK]));
            entDebitur.setNamaIbuKandung(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IBU_KANDUNG]));
            entDebitur.setKodeKantorCabang(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]));
            entDebitur.setOperasiData(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_OPERASI_DATA]));
            
            entDebitur.setNoIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_IDENTITAS]));
            entDebitur.setNamaBadanUsaha(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_BADAN_USAHA]));
            entDebitur.setKodeJenisUsaha(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_USAHA]));
            entDebitur.setTempatPendirian(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_PENDIRIAN]));
            entDebitur.setNoAkte(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_AKTE]));
            entDebitur.setTglAktePendirian(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_AKTE_PENDIRIAN]));
            entDebitur.setNoAktePerubahan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_AKTE_PERUBAHAN]));
            entDebitur.setTglAktePerubahan(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_AKTE_PERUBAHAN]));
            entDebitur.setKodeBidangUsaha(rs.getString(PstDebitur.fieldNames[FLD_KODE_BIDANG_USAHA]));
            entDebitur.setKodeHubLjk(rs.getString(PstDebitur.fieldNames[FLD_KODE_HUB_LJK]));
            entDebitur.setGoPublic(rs.getString(PstDebitur.fieldNames[FLD_GO_PUBLIC]));
            entDebitur.setPeringkat(rs.getString(PstDebitur.fieldNames[FLD_PERINGKAT]));
            entDebitur.setLembagaPemeringkat(rs.getString(PstDebitur.fieldNames[FLD_LEMBAGA_PEMERINGKAT]));
            entDebitur.setTglPemeringkat(rs.getDate(PstDebitur.fieldNames[FLD_TGL_PEMERINGKAT]));
            entDebitur.setNamaGroup(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_GROUP]));
            entDebitur.setOpenDate(rs.getDate(PstDebitur.fieldNames[FLD_OPEN_DATE]));
            entDebitur.setKodeJenisNsb(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
            entDebitur.setPeriodeId(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]));
            
        } catch (Exception e) {
        }
    }

    public static Vector listAll() {
        return list(0, 500, "", "");
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order, String periodeId, String cabang) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_DEBITUR_INDIVIDU+ " AS debitur "
                       + " WHERE PERIODE_ID='"+periodeId+"'";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
                Debitur entDebitur = new Debitur();
                resultToObject(rs, entDebitur);
                lists.add(entDebitur);
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
    
    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_DEBITUR_INDIVIDU;
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
                Debitur entDebitur = new Debitur();
                resultToObject(rs, entDebitur);
                lists.add(entDebitur);
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
            String sql = "SELECT DISTINCT debitur.* FROM " + TBL_DEBITUR_INDIVIDU +" AS debitur "
                    + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                    + "ON debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
               // sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                Debitur entDebitur = new Debitur();
                resultToObject(rs, entDebitur);
                lists.add(entDebitur);
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
    
    public static Vector listJoin(int limitStart, int recordToGet, String whereClause, String order, String xxx, String kodecabang) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT debitur.* FROM " + TBL_DEBITUR_INDIVIDU +" AS debitur "
//                    + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
//                    + "ON debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
//                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'";
                      + " INNER JOIN " +
                        "(" +
                            "SELECT DISTINCT CIF FROM (" +
                            " SELECT DISTINCT" +
                            " CIF," +
                            " NO_REKENING," +
                            " TGL_AWAL," +
                            " KODE_KANTOR_CABANG" +
                            " FROM dslik_kredit" +
                            " WHERE dslik_kredit.PERIODE_ID = '"+xxx+"' ";
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
                            " WHERE dslik_bank_garansi.PERIODE_ID = '"+xxx+"' ";
                if(!kodecabang.equals("")){
                                sql = sql + " AND dslik_bank_garansi.KODE_KANTOR_CABANG='"+kodecabang+"'";
                            }
                    sql = sql +" GROUP BY CIF ) AS CUP " +
                            " ORDER BY CUP.TGL_AWAL DESC "+
                             
                        ") AS dup " +
                        "ON debitur.CIF=dup.CIF";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
               // sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                Debitur entDebitur = new Debitur();
                resultToObject(rs, entDebitur);
                lists.add(entDebitur);
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

    public static boolean checkOID(long entDebiturId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_DEBITUR_INDIVIDU + " WHERE "
                    + PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID] + " = " + entDebiturId;
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
            String sql = "SELECT DISTINCT COUNT(" + PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID] + ") FROM " + TBL_DEBITUR_INDIVIDU;
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
    
    
    public static int getCountGlobal(String whereClause, String periodeId, String cabang) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT COUNT(" + PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID] + ") FROM " + TBL_DEBITUR_INDIVIDU +" AS debitur"+
                         " WHERE PERIODE_ID='"+periodeId+"'";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
            String sql = "SELECT DISTINCT COUNT(debitur." + PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID] + ") "
                    + "FROM " + TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                    + "ON debitur."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'";
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
    
    public static int getCountJoin(String whereClause, String periodeId, String kodecabang) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT COUNT(debitur." + PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID] + ") "
                    + "FROM " + TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + " INNER JOIN " +
                        "(" +
                            "SELECT DISTINCT CIF FROM (" +
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
                    "ON debitur.CIF=dup.CIF";
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
                    Debitur entDebitur = (Debitur) list.get(ls);
                    if (oid == entDebitur.getOID()) {
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
