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
public class PstMappingKreditBpd extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_MAPPING_KREDIT_BPD = "dslik_kredit";
    public static final int FLD_MAPPING_KREDIT_BPD_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_REKENING = 2;
    public static final int FLD_KODE_SIFAT = 3;
    public static final int FLD_NO_AKAD_AWAL = 4;
    public static final int FLD_TGL_AKAD_AWAL = 5;
    public static final int FLD_NO_AKAD_AKHIR = 6;
    public static final int FLD_TGL_AKAD_AKHIR = 7;
    public static final int FLD_BARU_PERPANJANGAN = 8;
    public static final int FLD_TGL_AWAL = 9;
    public static final int FLD_TGL_MULAI = 10;

    public static final int FLD_TGL_TEMPO = 11;
    public static final int FLD_KODE_JENIS_PENGGUNAAN = 12;
    public static final int FLD_KODE_ORIENTASI_PENGGUNAAN = 13;
    public static final int FLD_KODE_KAB = 14;
    public static final int FLD_NILAI = 15;
    public static final int FLD_KODE_VALUTA = 16;
    public static final int FLD_PROSENTASE_BUNGA = 17;
    public static final int FLD_JENIS_BUNGA = 18;
    public static final int FLD_PLAFON_AWAL = 19;
    public static final int FLD_REALISASI = 20;

    public static final int FLD_DENDA = 21;
    public static final int FLD_BAKI_DEBET = 22;
    public static final int FLD_KODE_KOLEKTIBILITAS = 23;
    public static final int FLD_TGL_MACET = 24;
    public static final int FLD_TUNGGAKAN_POKOK = 25;
    public static final int FLD_KODE_KONDISI = 26;
    public static final int FLD_TGL_KONDISI = 27;
    public static final int FLD_KODE_KANTOR_CABANG = 28;
    public static final int FLD_CIF = 29;
    public static final int FLD_KODE_KATEGORI_DEBITUR = 30;

    public static final int FLD_SUMBER_DANA = 31;
    public static final int FLD_TUNGGAKAN_BUNGA = 32;
    public static final int FLD_JUMLAH_HARI_TUNGGAKAN = 33;
    public static final int FLD_FREKUENSI_TUNGGAKAN = 34;
    public static final int FLD_FREKUENSI_RESTRUKTURISASI = 35;
    public static final int FLD_TGL_RESTRUKTURISASI_AWAL = 36;
    public static final int FLD_TGL_RESTRUKTURISASI_AKHIR = 37;
    public static final int FLD_PERIODE_ID = 38;
    public static final int FLD_APPLID=39;
    public static final int FLD_KREDIT_PROGRAM_PEMERINTAH=40;
    public static final int FLD_KODE_SKIM_AKAD_PEMBIAYAAN=41;
    public static final int FLD_PLAFON=42;
    public static final int FLD_JENIS_KREDIT=43;
    public static final int FLD_KODE_SEKTOR_EKONOMI=44;
    public static final int FLD_KODE_SEBAB_MACET=45;
    
    public static String[] fieldNames = {
        "KREDIT_OID",
        "FLAG_DETAIL",
        "NO_REKENING",
        "KODE_SIFAT",
        "NO_AKAD_AWAL",
        "TGL_AKAD_AWAL",
        "NO_AKAD_AKHIR",
        "TGL_AKAD_AKHIR",
        "BARU_PERPANJANGAN",
        "TGL_AWAL",
        "TGL_MULAI",
        "TGL_TEMPO",//10

        "KODE_JENIS_PENGGUNAAN",
        "KODE_ORIENTASI_PENGGUNAAN",
        "KODE_KAB",
        "NILAI",
        "KODE_VALUTA",
        "PROSENTASE_BUNGA",
        "JENIS_BUNGA",
        "PLAFON_AWAL",
        "REALISASI",
        "DENDA",//10

        "BAKI_DEBET",
        "KODE_KOLEKTIBILITAS",
        "TGL_MACET",
        "TUNGGAKAN_POKOK",
        "KODE_KONDISI",
        "TGL_KONDISI",
        "KODE_KANTOR_CABANG",
        "CIF",
        "KODE_KAT_DEBITUR",//10

        "SUMBER_DANA",
        "TUNGGAKAN_BUNGA",
        "JML_HARI_TUNGGAKAN",
        "FREKUENSI_TUNGGAKAN",
        "FREKUENSI_RESTRUKTURISASI",
        "TGL_RESTRUKTURISASI_AWAL",
        "TGL_RESTRUKTURISASI_AKHIR",
        "PERIODE_ID",
        "APPLID",
        "KREDIT_PEMERINTAH",
        "KODE_AKAD",
        "PLAFON",
        "KODE_JENIS_KREDIT",
        "KODE_SEKTOR_EKONOMI",
        "KODE_SEBAB_MACET"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_INT,
        TYPE_DATE,
        TYPE_DATE,//10

        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,//10

        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,//10

        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING

    };

    public PstMappingKreditBpd() {
    }

    public PstMappingKreditBpd(int i) throws DBException {
        super(new PstMappingKreditBpd());
    }

    public PstMappingKreditBpd(String sOid) throws DBException {
        super(new PstMappingKreditBpd(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstMappingKreditBpd(long lOid) throws DBException {
        super(new PstMappingKreditBpd(0));
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
        return TBL_MAPPING_KREDIT_BPD;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstMappingKreditBpd().getClass().getName();
    }

    public static MappingKreditBpd fetchExc(long oid) throws DBException {
        try {
            MappingKreditBpd entMappingKreditBpd = new MappingKreditBpd();
            PstMappingKreditBpd pstMappingKreditBpd = new PstMappingKreditBpd(oid);
            entMappingKreditBpd.setOID(oid);
            entMappingKreditBpd.setFlagDetail(pstMappingKreditBpd.getString(FLD_FLAG_DETAIL));
            entMappingKreditBpd.setNoRekening(pstMappingKreditBpd.getString(FLD_NO_REKENING));
            entMappingKreditBpd.setKodeSifat(pstMappingKreditBpd.getString(FLD_KODE_SIFAT));
            entMappingKreditBpd.setNoAkadAwal(pstMappingKreditBpd.getString(FLD_NO_AKAD_AWAL));
            entMappingKreditBpd.setTglAkadAwal(pstMappingKreditBpd.getDate(FLD_TGL_AKAD_AWAL));
            entMappingKreditBpd.setNoAkadAkhir(pstMappingKreditBpd.getString(FLD_NO_AKAD_AKHIR));
            entMappingKreditBpd.setTglAkadAkhir(pstMappingKreditBpd.getDate(FLD_TGL_AKAD_AKHIR));
            entMappingKreditBpd.setBaruPerpanjangan(pstMappingKreditBpd.getInt(FLD_BARU_PERPANJANGAN));
            entMappingKreditBpd.setTglAwal(pstMappingKreditBpd.getDate(FLD_TGL_AWAL));
            entMappingKreditBpd.setTglMulai(pstMappingKreditBpd.getDate(FLD_TGL_MULAI));
            entMappingKreditBpd.setTglTempo(pstMappingKreditBpd.getDate(FLD_TGL_TEMPO));
            entMappingKreditBpd.setKodeJenisPenggunaan(pstMappingKreditBpd.getString(FLD_KODE_JENIS_PENGGUNAAN));
            entMappingKreditBpd.setKodeOrientasiPenggunaan(pstMappingKreditBpd.getString(FLD_KODE_ORIENTASI_PENGGUNAAN));
            entMappingKreditBpd.setKodeKab(pstMappingKreditBpd.getString(FLD_KODE_KAB));
            entMappingKreditBpd.setNilai(pstMappingKreditBpd.getdouble(FLD_NILAI));
            entMappingKreditBpd.setKodeValuta(pstMappingKreditBpd.getString(FLD_KODE_VALUTA));
            entMappingKreditBpd.setProsentaseBunga(pstMappingKreditBpd.getInt(FLD_PROSENTASE_BUNGA));
            entMappingKreditBpd.setJenisBunga(pstMappingKreditBpd.getString(FLD_JENIS_BUNGA));
            entMappingKreditBpd.setPlafonAwal(pstMappingKreditBpd.getdouble(FLD_PLAFON_AWAL));
            entMappingKreditBpd.setRealisasi(pstMappingKreditBpd.getdouble(FLD_REALISASI));
            entMappingKreditBpd.setDenda(pstMappingKreditBpd.getdouble(FLD_DENDA));
            entMappingKreditBpd.setBakiDebet(pstMappingKreditBpd.getdouble(FLD_BAKI_DEBET));
            entMappingKreditBpd.setKodeKolektibilitas(pstMappingKreditBpd.getString(FLD_KODE_KOLEKTIBILITAS));
            entMappingKreditBpd.setTglMacet(pstMappingKreditBpd.getDate(FLD_TGL_MACET));
            entMappingKreditBpd.setTunggakanPokok(pstMappingKreditBpd.getdouble(FLD_TUNGGAKAN_POKOK));
            entMappingKreditBpd.setKodeKondisi(pstMappingKreditBpd.getString(FLD_KODE_KONDISI));
            entMappingKreditBpd.setTglKondisi(pstMappingKreditBpd.getDate(FLD_TGL_KONDISI));
            entMappingKreditBpd.setKodeKantorCabang(pstMappingKreditBpd.getString(FLD_KODE_KANTOR_CABANG));
            entMappingKreditBpd.setCif(pstMappingKreditBpd.getString(FLD_CIF));

            entMappingKreditBpd.setKodeKategoriDebitur(pstMappingKreditBpd.getString(FLD_KODE_KATEGORI_DEBITUR));
            entMappingKreditBpd.setSumberDana(pstMappingKreditBpd.getString(FLD_SUMBER_DANA));
            entMappingKreditBpd.setTunggakanBunga(pstMappingKreditBpd.getdouble(FLD_TUNGGAKAN_BUNGA));
            entMappingKreditBpd.setJumlahHariTunggakan(pstMappingKreditBpd.getdouble(FLD_JUMLAH_HARI_TUNGGAKAN));
            entMappingKreditBpd.setFrekuensiTunggakan(pstMappingKreditBpd.getdouble(FLD_FREKUENSI_TUNGGAKAN));
            entMappingKreditBpd.setFrekuensiRestrukturisasi(pstMappingKreditBpd.getdouble(FLD_FREKUENSI_RESTRUKTURISASI));
            entMappingKreditBpd.setTanggalRestrukturisasiAwal(pstMappingKreditBpd.getDate(FLD_TGL_RESTRUKTURISASI_AWAL));
            entMappingKreditBpd.setTanggalRestrukturisasiAkhir(pstMappingKreditBpd.getDate(FLD_TGL_RESTRUKTURISASI_AKHIR));
            entMappingKreditBpd.setPeriodeId(pstMappingKreditBpd.getlong(FLD_PERIODE_ID));
            entMappingKreditBpd.setApplid(pstMappingKreditBpd.getString(FLD_APPLID));
            entMappingKreditBpd.setKodeKreditProgramPemerintah(pstMappingKreditBpd.getString(FLD_KREDIT_PROGRAM_PEMERINTAH));
            entMappingKreditBpd.setKodeSkimAkadPembiayaan(pstMappingKreditBpd.getString(FLD_KODE_SKIM_AKAD_PEMBIAYAAN));
            entMappingKreditBpd.setPlafon(pstMappingKreditBpd.getdouble(FLD_PLAFON));
            
            entMappingKreditBpd.setKodeJenisKredit(pstMappingKreditBpd.getString(FLD_JENIS_KREDIT));
            entMappingKreditBpd.setKodeSektorEkonomi(pstMappingKreditBpd.getString(FLD_KODE_SEKTOR_EKONOMI));
            entMappingKreditBpd.setKodeSebabMacet(pstMappingKreditBpd.getString(FLD_KODE_SEBAB_MACET));
            
            return entMappingKreditBpd;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingKreditBpd(0), DBException.UNKNOWN);
        }
    }
    
    public static MappingKreditBpd fetchExc(long oid, MappingKreditBpd entMappingKreditBpd) throws DBException {
        try {
            //MappingKreditBpd entMappingKreditBpd = new MappingKreditBpd();
            PstMappingKreditBpd pstMappingKreditBpd = new PstMappingKreditBpd(oid);
            entMappingKreditBpd.setOID(oid);
            entMappingKreditBpd.setFlagDetail(pstMappingKreditBpd.getString(FLD_FLAG_DETAIL));
            entMappingKreditBpd.setNoRekening(pstMappingKreditBpd.getString(FLD_NO_REKENING));
            entMappingKreditBpd.setKodeSifat(pstMappingKreditBpd.getString(FLD_KODE_SIFAT));
            entMappingKreditBpd.setNoAkadAwal(pstMappingKreditBpd.getString(FLD_NO_AKAD_AWAL));
            entMappingKreditBpd.setTglAkadAwal(pstMappingKreditBpd.getDate(FLD_TGL_AKAD_AWAL));
            entMappingKreditBpd.setNoAkadAkhir(pstMappingKreditBpd.getString(FLD_NO_AKAD_AKHIR));
            entMappingKreditBpd.setTglAkadAkhir(pstMappingKreditBpd.getDate(FLD_TGL_AKAD_AKHIR));
            entMappingKreditBpd.setBaruPerpanjangan(pstMappingKreditBpd.getInt(FLD_BARU_PERPANJANGAN));
            entMappingKreditBpd.setTglAwal(pstMappingKreditBpd.getDate(FLD_TGL_AWAL));
            entMappingKreditBpd.setTglMulai(pstMappingKreditBpd.getDate(FLD_TGL_MULAI));
            entMappingKreditBpd.setTglTempo(pstMappingKreditBpd.getDate(FLD_TGL_TEMPO));
            entMappingKreditBpd.setKodeJenisPenggunaan(pstMappingKreditBpd.getString(FLD_KODE_JENIS_PENGGUNAAN));
            entMappingKreditBpd.setKodeOrientasiPenggunaan(pstMappingKreditBpd.getString(FLD_KODE_ORIENTASI_PENGGUNAAN));
            entMappingKreditBpd.setKodeKab(pstMappingKreditBpd.getString(FLD_KODE_KAB));
            entMappingKreditBpd.setNilai(pstMappingKreditBpd.getdouble(FLD_NILAI));
            entMappingKreditBpd.setKodeValuta(pstMappingKreditBpd.getString(FLD_KODE_VALUTA));
            entMappingKreditBpd.setProsentaseBunga(pstMappingKreditBpd.getInt(FLD_PROSENTASE_BUNGA));
            entMappingKreditBpd.setJenisBunga(pstMappingKreditBpd.getString(FLD_JENIS_BUNGA));
            entMappingKreditBpd.setPlafonAwal(pstMappingKreditBpd.getdouble(FLD_PLAFON_AWAL));
            entMappingKreditBpd.setRealisasi(pstMappingKreditBpd.getdouble(FLD_REALISASI));
            entMappingKreditBpd.setDenda(pstMappingKreditBpd.getdouble(FLD_DENDA));
            entMappingKreditBpd.setBakiDebet(pstMappingKreditBpd.getdouble(FLD_BAKI_DEBET));
            entMappingKreditBpd.setKodeKolektibilitas(pstMappingKreditBpd.getString(FLD_KODE_KOLEKTIBILITAS));
            entMappingKreditBpd.setTglMacet(pstMappingKreditBpd.getDate(FLD_TGL_MACET));
            entMappingKreditBpd.setTunggakanPokok(pstMappingKreditBpd.getdouble(FLD_TUNGGAKAN_POKOK));
            entMappingKreditBpd.setKodeKondisi(pstMappingKreditBpd.getString(FLD_KODE_KONDISI));
            entMappingKreditBpd.setTglKondisi(pstMappingKreditBpd.getDate(FLD_TGL_KONDISI));
            entMappingKreditBpd.setKodeKantorCabang(pstMappingKreditBpd.getString(FLD_KODE_KANTOR_CABANG));
            entMappingKreditBpd.setCif(pstMappingKreditBpd.getString(FLD_CIF));

            entMappingKreditBpd.setKodeKategoriDebitur(pstMappingKreditBpd.getString(FLD_KODE_KATEGORI_DEBITUR));
            entMappingKreditBpd.setSumberDana(pstMappingKreditBpd.getString(FLD_SUMBER_DANA));
            entMappingKreditBpd.setTunggakanBunga(pstMappingKreditBpd.getdouble(FLD_TUNGGAKAN_BUNGA));
            entMappingKreditBpd.setJumlahHariTunggakan(pstMappingKreditBpd.getdouble(FLD_JUMLAH_HARI_TUNGGAKAN));
            entMappingKreditBpd.setFrekuensiTunggakan(pstMappingKreditBpd.getdouble(FLD_FREKUENSI_TUNGGAKAN));
            entMappingKreditBpd.setFrekuensiRestrukturisasi(pstMappingKreditBpd.getdouble(FLD_FREKUENSI_RESTRUKTURISASI));
            entMappingKreditBpd.setTanggalRestrukturisasiAwal(pstMappingKreditBpd.getDate(FLD_TGL_RESTRUKTURISASI_AWAL));
            entMappingKreditBpd.setTanggalRestrukturisasiAkhir(pstMappingKreditBpd.getDate(FLD_TGL_RESTRUKTURISASI_AKHIR));
            entMappingKreditBpd.setPeriodeId(pstMappingKreditBpd.getlong(FLD_PERIODE_ID));
            entMappingKreditBpd.setApplid(pstMappingKreditBpd.getString(FLD_APPLID));
            entMappingKreditBpd.setKodeKreditProgramPemerintah(pstMappingKreditBpd.getString(FLD_KREDIT_PROGRAM_PEMERINTAH));
            entMappingKreditBpd.setKodeSkimAkadPembiayaan(pstMappingKreditBpd.getString(FLD_KODE_SKIM_AKAD_PEMBIAYAAN));
            entMappingKreditBpd.setPlafon(pstMappingKreditBpd.getdouble(FLD_PLAFON));
            
            entMappingKreditBpd.setKodeJenisKredit(pstMappingKreditBpd.getString(FLD_JENIS_KREDIT));
            entMappingKreditBpd.setKodeSektorEkonomi(pstMappingKreditBpd.getString(FLD_KODE_SEKTOR_EKONOMI));
            entMappingKreditBpd.setKodeSebabMacet(pstMappingKreditBpd.getString(FLD_KODE_SEBAB_MACET));
            
            return entMappingKreditBpd;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingKreditBpd(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        MappingKreditBpd entMappingKreditBpd = fetchExc(entity.getOID());
        entity = (Entity) entMappingKreditBpd;
        return entMappingKreditBpd.getOID();
    }

    public static synchronized long updateExc(MappingKreditBpd entMappingKreditBpd) throws DBException {
        try {
            if (entMappingKreditBpd.getOID() != 0) {
                PstMappingKreditBpd pstMappingKreditBpd = new PstMappingKreditBpd(entMappingKreditBpd.getOID());
                pstMappingKreditBpd.setString(FLD_FLAG_DETAIL, entMappingKreditBpd.getFlagDetail());
                pstMappingKreditBpd.setString(FLD_NO_REKENING, entMappingKreditBpd.getNoRekening());
                pstMappingKreditBpd.setString(FLD_KODE_SIFAT, entMappingKreditBpd.getKodeSifat());
                pstMappingKreditBpd.setString(FLD_NO_AKAD_AWAL, entMappingKreditBpd.getNoAkadAwal());
                pstMappingKreditBpd.setDate(FLD_TGL_AKAD_AWAL, entMappingKreditBpd.getTglAkadAwal());
                pstMappingKreditBpd.setString(FLD_NO_AKAD_AKHIR, entMappingKreditBpd.getNoAkadAkhir());
                pstMappingKreditBpd.setDate(FLD_TGL_AKAD_AKHIR, entMappingKreditBpd.getTglAkadAkhir());
                pstMappingKreditBpd.setInt(FLD_BARU_PERPANJANGAN, entMappingKreditBpd.getBaruPerpanjangan());
                pstMappingKreditBpd.setDate(FLD_TGL_AWAL, entMappingKreditBpd.getTglAwal());
                pstMappingKreditBpd.setDate(FLD_TGL_MULAI, entMappingKreditBpd.getTglMulai());
                pstMappingKreditBpd.setDate(FLD_TGL_TEMPO, entMappingKreditBpd.getTglTempo());
                pstMappingKreditBpd.setString(FLD_KODE_JENIS_PENGGUNAAN, entMappingKreditBpd.getKodeJenisPenggunaan());
                pstMappingKreditBpd.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entMappingKreditBpd.getKodeOrientasiPenggunaan());
                pstMappingKreditBpd.setString(FLD_KODE_KAB, entMappingKreditBpd.getKodeKab());
                pstMappingKreditBpd.setDouble(FLD_NILAI, entMappingKreditBpd.getNilai());
                pstMappingKreditBpd.setString(FLD_KODE_VALUTA, entMappingKreditBpd.getKodeValuta());
                pstMappingKreditBpd.setInt(FLD_PROSENTASE_BUNGA, entMappingKreditBpd.getProsentaseBunga());
                pstMappingKreditBpd.setString(FLD_JENIS_BUNGA, entMappingKreditBpd.getJenisBunga());
                pstMappingKreditBpd.setDouble(FLD_PLAFON_AWAL, entMappingKreditBpd.getPlafonAwal());
                pstMappingKreditBpd.setDouble(FLD_REALISASI, entMappingKreditBpd.getRealisasi());
                pstMappingKreditBpd.setDouble(FLD_DENDA, entMappingKreditBpd.getDenda());
                pstMappingKreditBpd.setDouble(FLD_BAKI_DEBET, entMappingKreditBpd.getBakiDebet());
                pstMappingKreditBpd.setString(FLD_KODE_KOLEKTIBILITAS, entMappingKreditBpd.getKodeKolektibilitas());
                pstMappingKreditBpd.setDate(FLD_TGL_MACET, entMappingKreditBpd.getTglMacet());
                pstMappingKreditBpd.setDouble(FLD_TUNGGAKAN_POKOK, entMappingKreditBpd.getTunggakanPokok());
                pstMappingKreditBpd.setString(FLD_KODE_KONDISI, entMappingKreditBpd.getKodeKondisi());
                pstMappingKreditBpd.setDate(FLD_TGL_KONDISI, entMappingKreditBpd.getTglKondisi());
                pstMappingKreditBpd.setString(FLD_KODE_KANTOR_CABANG, entMappingKreditBpd.getKodeKantorCabang());
                pstMappingKreditBpd.setString(FLD_CIF, entMappingKreditBpd.getCif());

                pstMappingKreditBpd.setString(FLD_KODE_KATEGORI_DEBITUR, entMappingKreditBpd.getKodeKategoriDebitur());
                pstMappingKreditBpd.setString(FLD_SUMBER_DANA, entMappingKreditBpd.getSumberDana());
                pstMappingKreditBpd.setDouble(FLD_TUNGGAKAN_BUNGA, entMappingKreditBpd.getTunggakanBunga());
                pstMappingKreditBpd.setDouble(FLD_JUMLAH_HARI_TUNGGAKAN, entMappingKreditBpd.getJumlahHariTunggakan());
                pstMappingKreditBpd.setDouble(FLD_FREKUENSI_TUNGGAKAN, entMappingKreditBpd.getFrekuensiTunggakan());
                pstMappingKreditBpd.setDouble(FLD_FREKUENSI_RESTRUKTURISASI, entMappingKreditBpd.getFrekuensiRestrukturisasi());
                pstMappingKreditBpd.setLong(FLD_PERIODE_ID, entMappingKreditBpd.getPeriodeId());
                pstMappingKreditBpd.setString(FLD_APPLID, entMappingKreditBpd.getApplid());
                pstMappingKreditBpd.setString(FLD_KREDIT_PROGRAM_PEMERINTAH,entMappingKreditBpd.getKodeKreditProgramPemerintah());
                pstMappingKreditBpd.setString(FLD_KODE_SKIM_AKAD_PEMBIAYAAN, entMappingKreditBpd.getKodeSkimAkadPembiayaan());
                pstMappingKreditBpd.setDouble(FLD_PLAFON, entMappingKreditBpd.getPlafon());
                pstMappingKreditBpd.setString(FLD_JENIS_KREDIT,  entMappingKreditBpd.getKodeJenisKredit());
                pstMappingKreditBpd.setString(FLD_KODE_SEKTOR_EKONOMI,entMappingKreditBpd.getKodeSektorEkonomi());
                pstMappingKreditBpd.setString(FLD_KODE_SEBAB_MACET,  entMappingKreditBpd.getKodeSebabMacet());
                pstMappingKreditBpd.update();
                return entMappingKreditBpd.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingKreditBpd(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((MappingKreditBpd) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMappingKreditBpd pstMappingKreditBpd = new PstMappingKreditBpd(oid);
            pstMappingKreditBpd.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingKreditBpd(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MappingKreditBpd entMappingKreditBpd) throws DBException {
        try {
            PstMappingKreditBpd pstMappingKreditBpd = new PstMappingKreditBpd(0);
            pstMappingKreditBpd.setString(FLD_FLAG_DETAIL, entMappingKreditBpd.getFlagDetail());
            pstMappingKreditBpd.setString(FLD_NO_REKENING, entMappingKreditBpd.getNoRekening());
            pstMappingKreditBpd.setString(FLD_KODE_SIFAT, entMappingKreditBpd.getKodeSifat());
            pstMappingKreditBpd.setString(FLD_NO_AKAD_AWAL, entMappingKreditBpd.getNoAkadAwal());
            pstMappingKreditBpd.setDate(FLD_TGL_AKAD_AWAL, entMappingKreditBpd.getTglAkadAwal());
            pstMappingKreditBpd.setString(FLD_NO_AKAD_AKHIR, entMappingKreditBpd.getNoAkadAkhir());
            pstMappingKreditBpd.setDate(FLD_TGL_AKAD_AKHIR, entMappingKreditBpd.getTglAkadAkhir());
            pstMappingKreditBpd.setInt(FLD_BARU_PERPANJANGAN, entMappingKreditBpd.getBaruPerpanjangan());
            pstMappingKreditBpd.setDate(FLD_TGL_AWAL, entMappingKreditBpd.getTglAwal());
            pstMappingKreditBpd.setDate(FLD_TGL_MULAI, entMappingKreditBpd.getTglMulai());
            pstMappingKreditBpd.setDate(FLD_TGL_TEMPO, entMappingKreditBpd.getTglTempo());
            pstMappingKreditBpd.setString(FLD_KODE_JENIS_PENGGUNAAN, entMappingKreditBpd.getKodeJenisPenggunaan());
            pstMappingKreditBpd.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entMappingKreditBpd.getKodeOrientasiPenggunaan());
            pstMappingKreditBpd.setString(FLD_KODE_KAB, entMappingKreditBpd.getKodeKab());
            pstMappingKreditBpd.setDouble(FLD_NILAI, entMappingKreditBpd.getNilai());
            pstMappingKreditBpd.setString(FLD_KODE_VALUTA, entMappingKreditBpd.getKodeValuta());
            pstMappingKreditBpd.setInt(FLD_PROSENTASE_BUNGA, entMappingKreditBpd.getProsentaseBunga());
            pstMappingKreditBpd.setString(FLD_JENIS_BUNGA, entMappingKreditBpd.getJenisBunga());
            pstMappingKreditBpd.setDouble(FLD_PLAFON_AWAL, entMappingKreditBpd.getPlafonAwal());
            pstMappingKreditBpd.setDouble(FLD_REALISASI, entMappingKreditBpd.getRealisasi());
            pstMappingKreditBpd.setDouble(FLD_DENDA, entMappingKreditBpd.getDenda());
            pstMappingKreditBpd.setDouble(FLD_BAKI_DEBET, entMappingKreditBpd.getBakiDebet());
            pstMappingKreditBpd.setString(FLD_KODE_KOLEKTIBILITAS, entMappingKreditBpd.getKodeKolektibilitas());
            pstMappingKreditBpd.setDate(FLD_TGL_MACET, entMappingKreditBpd.getTglMacet());
            pstMappingKreditBpd.setDouble(FLD_TUNGGAKAN_POKOK, entMappingKreditBpd.getTunggakanPokok());
            pstMappingKreditBpd.setString(FLD_KODE_KONDISI, entMappingKreditBpd.getKodeKondisi());
            pstMappingKreditBpd.setDate(FLD_TGL_KONDISI, entMappingKreditBpd.getTglKondisi());
            pstMappingKreditBpd.setString(FLD_KODE_KANTOR_CABANG, entMappingKreditBpd.getKodeKantorCabang());
            pstMappingKreditBpd.setString(FLD_CIF, entMappingKreditBpd.getCif());

            pstMappingKreditBpd.setString(FLD_KODE_KATEGORI_DEBITUR, entMappingKreditBpd.getKodeKategoriDebitur());
            pstMappingKreditBpd.setString(FLD_SUMBER_DANA, entMappingKreditBpd.getSumberDana());
            pstMappingKreditBpd.setDouble(FLD_TUNGGAKAN_BUNGA, entMappingKreditBpd.getTunggakanBunga());
            pstMappingKreditBpd.setDouble(FLD_JUMLAH_HARI_TUNGGAKAN, entMappingKreditBpd.getJumlahHariTunggakan());
            pstMappingKreditBpd.setDouble(FLD_FREKUENSI_TUNGGAKAN, entMappingKreditBpd.getFrekuensiTunggakan());
            pstMappingKreditBpd.setDouble(FLD_FREKUENSI_RESTRUKTURISASI, entMappingKreditBpd.getFrekuensiRestrukturisasi());
            pstMappingKreditBpd.setLong(FLD_PERIODE_ID, entMappingKreditBpd.getPeriodeId());
            pstMappingKreditBpd.setString(FLD_APPLID, entMappingKreditBpd.getApplid());
            pstMappingKreditBpd.setString(FLD_KREDIT_PROGRAM_PEMERINTAH, entMappingKreditBpd.getKodeKreditProgramPemerintah());
            pstMappingKreditBpd.setString(FLD_KODE_SKIM_AKAD_PEMBIAYAAN,  entMappingKreditBpd.getKodeSkimAkadPembiayaan());
            pstMappingKreditBpd.setDouble(FLD_PLAFON, entMappingKreditBpd.getPlafon());
            pstMappingKreditBpd.setString(FLD_JENIS_KREDIT,  entMappingKreditBpd.getKodeJenisKredit());
            pstMappingKreditBpd.setString(FLD_KODE_SEKTOR_EKONOMI,entMappingKreditBpd.getKodeSektorEkonomi());
            pstMappingKreditBpd.setString(FLD_KODE_SEBAB_MACET,  entMappingKreditBpd.getKodeSebabMacet());
            pstMappingKreditBpd.insert();
            entMappingKreditBpd.setOID(pstMappingKreditBpd.getlong(FLD_MAPPING_KREDIT_BPD_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingKreditBpd(0), DBException.UNKNOWN);
        }
        return entMappingKreditBpd.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((MappingKreditBpd) entity);
    }

    public static void resultToObject(ResultSet rs, MappingKreditBpd entMappingKreditBpd) {
        try {
            entMappingKreditBpd.setOID(rs.getLong(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_MAPPING_KREDIT_BPD_OID]));
            entMappingKreditBpd.setFlagDetail(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_FLAG_DETAIL]));
            entMappingKreditBpd.setNoRekening(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_NO_REKENING]));
            entMappingKreditBpd.setKodeSifat(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_SIFAT]));
            entMappingKreditBpd.setNoAkadAwal(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_NO_AKAD_AWAL]));
            entMappingKreditBpd.setTglAkadAwal(rs.getDate(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_TGL_AKAD_AWAL]));
            entMappingKreditBpd.setNoAkadAkhir(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_NO_AKAD_AKHIR]));
            entMappingKreditBpd.setTglAkadAkhir(rs.getDate(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_TGL_AKAD_AKHIR]));
            entMappingKreditBpd.setBaruPerpanjangan(rs.getInt(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_BARU_PERPANJANGAN]));
            entMappingKreditBpd.setTglAwal(rs.getDate(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_TGL_AWAL]));
            entMappingKreditBpd.setTglMulai(rs.getDate(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_TGL_MULAI]));
            entMappingKreditBpd.setTglTempo(rs.getDate(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_TGL_TEMPO]));
            entMappingKreditBpd.setKodeJenisPenggunaan(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_JENIS_PENGGUNAAN]));
            entMappingKreditBpd.setKodeOrientasiPenggunaan(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_ORIENTASI_PENGGUNAAN]));
            entMappingKreditBpd.setKodeKab(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_KAB]));
            entMappingKreditBpd.setNilai(rs.getDouble(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_NILAI]));
            entMappingKreditBpd.setKodeValuta(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_VALUTA]));
            entMappingKreditBpd.setProsentaseBunga(rs.getInt(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_PROSENTASE_BUNGA]));
            entMappingKreditBpd.setJenisBunga(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_JENIS_BUNGA]));
            entMappingKreditBpd.setPlafonAwal(rs.getDouble(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_PLAFON_AWAL]));
            entMappingKreditBpd.setRealisasi(rs.getDouble(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_REALISASI]));
            entMappingKreditBpd.setDenda(rs.getDouble(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_DENDA]));
            entMappingKreditBpd.setBakiDebet(rs.getDouble(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_BAKI_DEBET]));
            entMappingKreditBpd.setKodeKolektibilitas(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_KOLEKTIBILITAS]));
            entMappingKreditBpd.setTglMacet(rs.getDate(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_TGL_MACET]));
            entMappingKreditBpd.setTunggakanPokok(rs.getDouble(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_TUNGGAKAN_POKOK]));
            entMappingKreditBpd.setKodeKondisi(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_KONDISI]));
            entMappingKreditBpd.setTglKondisi(rs.getDate(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_TGL_KONDISI]));
            entMappingKreditBpd.setKodeKantorCabang(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_KANTOR_CABANG]));
            entMappingKreditBpd.setCif(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_CIF]));

            entMappingKreditBpd.setKodeKategoriDebitur(rs.getString(PstMappingKreditBpd.fieldNames[FLD_KODE_KATEGORI_DEBITUR]));
            entMappingKreditBpd.setSumberDana(rs.getString(PstMappingKreditBpd.fieldNames[FLD_SUMBER_DANA]));
            entMappingKreditBpd.setTunggakanBunga(rs.getDouble(PstMappingKreditBpd.fieldNames[FLD_TUNGGAKAN_BUNGA]));
            entMappingKreditBpd.setJumlahHariTunggakan(rs.getDouble(PstMappingKreditBpd.fieldNames[FLD_JUMLAH_HARI_TUNGGAKAN]));
            entMappingKreditBpd.setFrekuensiTunggakan(rs.getDouble(PstMappingKreditBpd.fieldNames[FLD_FREKUENSI_TUNGGAKAN]));
            entMappingKreditBpd.setFrekuensiRestrukturisasi(rs.getDouble(PstMappingKreditBpd.fieldNames[FLD_FREKUENSI_RESTRUKTURISASI]));
            entMappingKreditBpd.setTanggalRestrukturisasiAwal(rs.getDate(PstMappingKreditBpd.fieldNames[FLD_TGL_RESTRUKTURISASI_AWAL]));
            entMappingKreditBpd.setTanggalRestrukturisasiAkhir(rs.getDate(PstMappingKreditBpd.fieldNames[FLD_TGL_RESTRUKTURISASI_AKHIR]));
            entMappingKreditBpd.setPeriodeId(rs.getLong(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_PERIODE_ID]));
            entMappingKreditBpd.setApplid(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_APPLID]));
            entMappingKreditBpd.setKodeKreditProgramPemerintah(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KREDIT_PROGRAM_PEMERINTAH]));
            entMappingKreditBpd.setKodeSkimAkadPembiayaan(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_SKIM_AKAD_PEMBIAYAAN]));
            entMappingKreditBpd.setPlafon(rs.getDouble(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_PLAFON]));
            
            entMappingKreditBpd.setKodeJenisKredit(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_JENIS_KREDIT]));
            entMappingKreditBpd.setKodeSektorEkonomi(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_SEKTOR_EKONOMI]));
            entMappingKreditBpd.setKodeSebabMacet(rs.getString(PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_KODE_SEBAB_MACET]));
            
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
            String sql = "SELECT * FROM " + TBL_MAPPING_KREDIT_BPD;
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
                MappingKreditBpd entMappingKreditBpd = new MappingKreditBpd();
                resultToObject(rs, entMappingKreditBpd);
                lists.add(entMappingKreditBpd);
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

    public static boolean checkOID(long entMappingKreditBpdId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_KREDIT_BPD + " WHERE "
                    + PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_MAPPING_KREDIT_BPD_OID] + " = " + entMappingKreditBpdId;
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
            String sql = "SELECT COUNT(" + PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_MAPPING_KREDIT_BPD_OID] + ") FROM " + TBL_MAPPING_KREDIT_BPD;
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
                    MappingKreditBpd entMappingKreditBpd = (MappingKreditBpd) list.get(ls);
                    if (oid == entMappingKreditBpd.getOID()) {
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

    public static int deleteSegmentKreditPerPeriode(long periodeId, String cif) {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "DELETE FROM " + TBL_MAPPING_KREDIT_BPD
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
    
    
    public static long checkKredit(long periodeId, String cif) {
        DBResultSet dbrs = null;
        long result = 0;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_KREDIT_BPD+ " WHERE "
                    + PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_PERIODE_ID] + " = '" + periodeId+"'"
                    + " AND "+PstMappingKreditBpd.fieldNames[PstMappingKreditBpd.FLD_NO_REKENING]+"='"+cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getLong(fieldNames[FLD_MAPPING_KREDIT_BPD_OID]);
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
