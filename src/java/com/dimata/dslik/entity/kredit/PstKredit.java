/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.kredit;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.contentdata.PstContentDataJenisKredit;
import com.dimata.dslik.entity.contentdata.PstContentDataSifatKredit;
import com.dimata.dslik.entity.contentdata.PstContentDataSkimAkadPembiayaan;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import java.sql.*;
import java.util.Vector;

public class PstKredit extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    private String sqlQueryHistory = "";
    
    public static final String TBL_KREDIT = "dslik_kredit";
    public static final int FLD_KREDIT_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_REKENING = 2;
    public static final int FLD_CIF = 3;
    public static final int FLD_KODE_SIFAT = 4;
    public static final int FLD_KODE_JENIS_KREDIT = 5;
    public static final int FLD_KODE_AKAD = 6;
    public static final int FLD_NO_AKAD_AWAL = 7;
    public static final int FLD_TGL_AKAD_AWAL = 8;
    public static final int FLD_NO_AKAD_AKHIR = 9;
    public static final int FLD_TGL_AKAD_AKHIR = 10;
    public static final int FLD_BARU_PERPANJANGAN = 11;
    public static final int FLD_TGL_AWAL = 12;
    public static final int FLD_TGL_MULAI = 13;
    public static final int FLD_TGL_TEMPO = 14;
    public static final int FLD_KODE_KAT_DBITUR = 15;
    public static final int FLD_KODE_JENIS_PENGGUNAAN = 16;
    public static final int FLD_KODE_ORIENTASI_PENGGUNAAN = 17;
    public static final int FLD_KODE_SEKTOR_EKONOMI = 18;
    public static final int FLD_KODE_KAB = 19;
    public static final int FLD_NILAI = 20;
    public static final int FLD_KODE_VALUTA = 21;
    public static final int FLD_PROSENTASE_BUNGA = 22;
    public static final int FLD_JENIS_BUNGA = 23;
    public static final int FLD_KREDIT_PEMERINTAH = 24;
    public static final int FLD_TAKEOVER = 25;
    public static final int FLD_SUMBER_DANA = 26;
    public static final int FLD_PLAFON_AWAL = 27;
    public static final int FLD_PLAFON = 28;
    public static final int FLD_REALISASI = 29;
    public static final int FLD_DENDA = 30;
    public static final int FLD_BAKI_DEBET = 31;
    public static final int FLD_NILAI_UANG_ASAL = 32;
    public static final int FLD_KODE_KOLEKTIBILITAS = 33;
    public static final int FLD_TGL_MACET = 34;
    public static final int FLD_KODE_SEBAB_MACET = 35;
    public static final int FLD_TUNGGAKAN_POKOK = 36;
    public static final int FLD_TUNGGAKAN_BUNGA = 37;
    public static final int FLD_JML_HARI_TUNGGAKAN = 38;
    public static final int FLD_FREKUENSI_TUNGGAKAN = 39;
    public static final int FLD_FREKUENSI_RESTRUKTURISASI = 40;
    public static final int FLD_TGL_RESTRUKTURISASI_AWAL = 41;
    public static final int FLD_TGL_RESTRUKTURISASI_AKHIR = 42;
    public static final int FLD_KODE_CARA = 43;
    public static final int FLD_KODE_KONDISI = 44;
    public static final int FLD_TGL_KONDISI = 45;
    public static final int FLD_KETERANGAN = 46;
    public static final int FLD_KODE_KANTOR_CABANG = 47;
    public static final int FLD_OPERASI_DATA = 48;
    public static final int FLD_OPEN_DATE = 49;
    public static final int FLD_STATUS_DATA = 50;
    public static final int FLD_PERIODE_ID = 51;
    public static final int FLD_STATUS_PERUBAHAN_DATA=52;
    
    
    public static String[] fieldNames = {
        "KREDIT_OID",
        "FLAG_DETAIL",
        "NO_REKENING",
        "CIF",
        "KODE_SIFAT",
        "KODE_JENIS_KREDIT",
        "KODE_AKAD",
        "NO_AKAD_AWAL",
        "TGL_AKAD_AWAL",
        "NO_AKAD_AKHIR",
        "TGL_AKAD_AKHIR",
        "BARU_PERPANJANGAN",
        "TGL_AWAL",
        "TGL_MULAI",
        "TGL_TEMPO",
        "KODE_KAT_DEBITUR",
        "KODE_JENIS_PENGGUNAAN",
        "KODE_ORIENTASI_PENGGUNAAN",
        "KODE_SEKTOR_EKONOMI",
        "KODE_KAB",
        "NILAI",
        "KODE_VALUTA",
        "PROSENTASE_BUNGA",
        "JENIS_BUNGA",
        "KREDIT_PEMERINTAH",
        "TAKEOVER",
        "SUMBER_DANA",
        "PLAFON_AWAL",
        "PLAFON",
        "REALISASI",
        "DENDA",
        "BAKI_DEBET",
        "NILAI_UANG_ASAL",
        "KODE_KOLEKTIBILITAS",
        "TGL_MACET",
        "KODE_SEBAB_MACET",
        "TUNGGAKAN_POKOK",
        "TUNGGAKAN_BUNGA",
        "JML_HARI_TUNGGAKAN",
        "FREKUENSI_TUNGGAKAN",
        "FREKUENSI_RESTRUKTURISASI",
        "TGL_RESTRUKTURISASI_AWAL",
        "TGL_RESTRUKTURISASI_AKHIR",
        "KODE_CARA",
        "KODE_KONDISI",
        "TGL_KONDISI",
        "KETERANGAN",
        "KODE_KANTOR_CABANG",
        "OPERASI_DATA",
        "OPEN_DATE",
        "STATUS_OPERASI_DATA",
        "PERIODE_ID",
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
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_INT,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_DATE,
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
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_INT,
        TYPE_INT,
        TYPE_INT,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_INT,
        TYPE_LONG,
        TYPE_INT
    };
    
    public PstKredit() {
    }

    public PstKredit(int i) throws DBException {
        super(new PstKredit());
    }

    public PstKredit(String sOid) throws DBException {
        super(new PstKredit(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstKredit(long lOid) throws DBException {
        super(new PstKredit(0));
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
        return TBL_KREDIT;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstKredit().getClass().getName();
    }

    public static Kredit fetchExc(long oid) throws DBException {
        try {
            Kredit entKredit = new Kredit();
            PstKredit pstKredit = new PstKredit(oid);
            entKredit.setOID(oid);
            entKredit.setFlagDetail(pstKredit.getString(FLD_FLAG_DETAIL));
            entKredit.setNoRekening(pstKredit.getString(FLD_NO_REKENING));
            entKredit.setCif(pstKredit.getString(FLD_CIF));
            entKredit.setKodeSifat(pstKredit.getString(FLD_KODE_SIFAT));
            entKredit.setKodeJenisKredit(pstKredit.getString(FLD_KODE_JENIS_KREDIT));
            entKredit.setKodeAkad(pstKredit.getString(FLD_KODE_AKAD));
            entKredit.setNoAkadAwal(pstKredit.getString(FLD_NO_AKAD_AWAL));
            entKredit.setTglAkadAwal(pstKredit.getDate(FLD_TGL_AKAD_AWAL));
            entKredit.setNoAkadAkhir(pstKredit.getString(FLD_NO_AKAD_AKHIR));
            entKredit.setTglAkadAkhir(pstKredit.getDate(FLD_TGL_AKAD_AKHIR));
            entKredit.setBaruPerpanjangan(pstKredit.getInt(FLD_BARU_PERPANJANGAN));
            entKredit.setTglAwal(pstKredit.getDate(FLD_TGL_AWAL));
            entKredit.setTglMulai(pstKredit.getDate(FLD_TGL_MULAI));
            entKredit.setTglTempo(pstKredit.getDate(FLD_TGL_TEMPO));
            entKredit.setKodeKatDbitur(pstKredit.getString(FLD_KODE_KAT_DBITUR));
            entKredit.setKodeJenisPenggunaan(pstKredit.getString(FLD_KODE_JENIS_PENGGUNAAN));
            entKredit.setKodeOrientasiPenggunaan(pstKredit.getString(FLD_KODE_ORIENTASI_PENGGUNAAN));
            entKredit.setKodeSektorEkonomi(pstKredit.getString(FLD_KODE_SEKTOR_EKONOMI));
            entKredit.setKodeKab(pstKredit.getString(FLD_KODE_KAB));
            entKredit.setNilai(pstKredit.getdouble(FLD_NILAI));
            entKredit.setKodeValuta(pstKredit.getString(FLD_KODE_VALUTA));
            entKredit.setProsentaseBunga(pstKredit.getInt(FLD_PROSENTASE_BUNGA));
            entKredit.setJenisBunga(pstKredit.getString(FLD_JENIS_BUNGA));
            entKredit.setKreditPemerintah(pstKredit.getString(FLD_KREDIT_PEMERINTAH));
            entKredit.setTakeover(pstKredit.getString(FLD_TAKEOVER));
            entKredit.setSumberDana(pstKredit.getString(FLD_SUMBER_DANA));
            entKredit.setPlafonAwal(pstKredit.getdouble(FLD_PLAFON_AWAL));
            entKredit.setPlafon(pstKredit.getdouble(FLD_PLAFON));
            entKredit.setRealisasi(pstKredit.getdouble(FLD_REALISASI));
            entKredit.setDenda(pstKredit.getdouble(FLD_DENDA));
            entKredit.setBakiDebet(pstKredit.getdouble(FLD_BAKI_DEBET));
            entKredit.setNilaiUangAsal(pstKredit.getdouble(FLD_NILAI_UANG_ASAL));
            entKredit.setKodeKolektibilitas(pstKredit.getString(FLD_KODE_KOLEKTIBILITAS));
            entKredit.setTglMacet(pstKredit.getDate(FLD_TGL_MACET));
            entKredit.setKodeSebabMacet(pstKredit.getString(FLD_KODE_SEBAB_MACET));
            entKredit.setTunggakanPokok(pstKredit.getdouble(FLD_TUNGGAKAN_POKOK));
            entKredit.setTunggakanBunga(pstKredit.getdouble(FLD_TUNGGAKAN_BUNGA));
            entKredit.setJmlHariTunggakan(pstKredit.getInt(FLD_JML_HARI_TUNGGAKAN));
            entKredit.setFrekuensiTunggakan(pstKredit.getInt(FLD_FREKUENSI_TUNGGAKAN));
            entKredit.setFrekuensiRestrukturisasi(pstKredit.getInt(FLD_FREKUENSI_RESTRUKTURISASI));
            entKredit.setTglRestrukturisasiAwal(pstKredit.getDate(FLD_TGL_RESTRUKTURISASI_AWAL));
            entKredit.setTglRestrukturisasiAkhir(pstKredit.getDate(FLD_TGL_RESTRUKTURISASI_AKHIR));
            entKredit.setKodeCara(pstKredit.getString(FLD_KODE_CARA));
            entKredit.setKodeKondisi(pstKredit.getString(FLD_KODE_KONDISI));
            entKredit.setTglKondisi(pstKredit.getDate(FLD_TGL_KONDISI));
            entKredit.setKeterangan(pstKredit.getString(FLD_KETERANGAN));
            entKredit.setKodeKantorCabang(pstKredit.getString(FLD_KODE_KANTOR_CABANG));
            entKredit.setOperasiData(pstKredit.getString(FLD_OPERASI_DATA));
            entKredit.setOpenDate(pstKredit.getDate(FLD_OPEN_DATE));
            entKredit.setStatusData(pstKredit.getInt(FLD_STATUS_DATA));
            entKredit.setPeriodeId(pstKredit.getlong(FLD_PERIODE_ID));
            entKredit.setStatusPerubahanData(pstKredit.getInt(FLD_STATUS_PERUBAHAN_DATA));
            
            return entKredit;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKredit(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        Kredit entKredit = fetchExc(entity.getOID());
        entity = (Entity) entKredit;
        return entKredit.getOID();
    }

    public static synchronized long updateExc(Kredit entKredit) throws DBException {
        try {
            if (entKredit.getOID() != 0) {
                PstKredit pstKredit = new PstKredit(entKredit.getOID());
                pstKredit.setString(FLD_FLAG_DETAIL, entKredit.getFlagDetail());
                pstKredit.setString(FLD_NO_REKENING, entKredit.getNoRekening());
                pstKredit.setString(FLD_CIF, entKredit.getCif());
                pstKredit.setString(FLD_KODE_SIFAT, entKredit.getKodeSifat());
                pstKredit.setString(FLD_KODE_JENIS_KREDIT, entKredit.getKodeJenisKredit());
                pstKredit.setString(FLD_KODE_AKAD, entKredit.getKodeAkad());
                pstKredit.setString(FLD_NO_AKAD_AWAL, entKredit.getNoAkadAwal());
                pstKredit.setDate(FLD_TGL_AKAD_AWAL, entKredit.getTglAkadAwal());
                pstKredit.setString(FLD_NO_AKAD_AKHIR, entKredit.getNoAkadAkhir());
                pstKredit.setDate(FLD_TGL_AKAD_AKHIR, entKredit.getTglAkadAkhir());
                pstKredit.setInt(FLD_BARU_PERPANJANGAN, entKredit.getBaruPerpanjangan());
                pstKredit.setDate(FLD_TGL_AWAL, entKredit.getTglAwal());
                pstKredit.setDate(FLD_TGL_MULAI, entKredit.getTglMulai());
                pstKredit.setDate(FLD_TGL_TEMPO, entKredit.getTglTempo());
                pstKredit.setString(FLD_KODE_KAT_DBITUR, entKredit.getKodeKatDbitur());
                pstKredit.setString(FLD_KODE_JENIS_PENGGUNAAN, entKredit.getKodeJenisPenggunaan());
                pstKredit.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entKredit.getKodeOrientasiPenggunaan());
                pstKredit.setString(FLD_KODE_SEKTOR_EKONOMI, entKredit.getKodeSektorEkonomi());
                pstKredit.setString(FLD_KODE_KAB, entKredit.getKodeKab());
                pstKredit.setDouble(FLD_NILAI, entKredit.getNilai());
                pstKredit.setString(FLD_KODE_VALUTA, entKredit.getKodeValuta());
                pstKredit.setInt(FLD_PROSENTASE_BUNGA, entKredit.getProsentaseBunga());
                pstKredit.setString(FLD_JENIS_BUNGA, entKredit.getJenisBunga());
                pstKredit.setString(FLD_KREDIT_PEMERINTAH, entKredit.getKreditPemerintah());
                pstKredit.setString(FLD_TAKEOVER, entKredit.getTakeover());
                pstKredit.setString(FLD_SUMBER_DANA, entKredit.getSumberDana());
                pstKredit.setDouble(FLD_PLAFON_AWAL, entKredit.getPlafonAwal());
                pstKredit.setDouble(FLD_PLAFON, entKredit.getPlafon());
                pstKredit.setDouble(FLD_REALISASI, entKredit.getRealisasi());
                pstKredit.setDouble(FLD_DENDA, entKredit.getDenda());
                pstKredit.setDouble(FLD_BAKI_DEBET, entKredit.getBakiDebet());
                pstKredit.setDouble(FLD_NILAI_UANG_ASAL, entKredit.getNilaiUangAsal());
                pstKredit.setString(FLD_KODE_KOLEKTIBILITAS, entKredit.getKodeKolektibilitas());
                pstKredit.setDate(FLD_TGL_MACET, entKredit.getTglMacet());
                pstKredit.setString(FLD_KODE_SEBAB_MACET, entKredit.getKodeSebabMacet());
                pstKredit.setDouble(FLD_TUNGGAKAN_POKOK, entKredit.getTunggakanPokok());
                pstKredit.setDouble(FLD_TUNGGAKAN_BUNGA, entKredit.getTunggakanBunga());
                pstKredit.setInt(FLD_JML_HARI_TUNGGAKAN, entKredit.getJmlHariTunggakan());
                pstKredit.setInt(FLD_FREKUENSI_TUNGGAKAN, entKredit.getFrekuensiTunggakan());
                pstKredit.setInt(FLD_FREKUENSI_RESTRUKTURISASI, entKredit.getFrekuensiRestrukturisasi());
                pstKredit.setDate(FLD_TGL_RESTRUKTURISASI_AWAL, entKredit.getTglRestrukturisasiAwal());
                pstKredit.setDate(FLD_TGL_RESTRUKTURISASI_AKHIR, entKredit.getTglRestrukturisasiAkhir());
                pstKredit.setString(FLD_KODE_CARA, entKredit.getKodeCara());
                pstKredit.setString(FLD_KODE_KONDISI, entKredit.getKodeKondisi());
                pstKredit.setDate(FLD_TGL_KONDISI, entKredit.getTglKondisi());
                pstKredit.setString(FLD_KETERANGAN, entKredit.getKeterangan());
                pstKredit.setString(FLD_KODE_KANTOR_CABANG, entKredit.getKodeKantorCabang());
                pstKredit.setString(FLD_OPERASI_DATA, entKredit.getOperasiData());
                pstKredit.setDate(FLD_OPEN_DATE, entKredit.getOpenDate());
                pstKredit.setInt(FLD_STATUS_DATA, entKredit.getStatusData());
                pstKredit.setLong(FLD_PERIODE_ID, entKredit.getPeriodeId());
                
                pstKredit.setInt(FLD_STATUS_PERUBAHAN_DATA,  entKredit.getStatusPerubahanData());
                
                pstKredit.update();
                
                pstKredit.setSqlQueryHistory("");
                pstKredit.setSqlQueryHistory(pstKredit.getInsertSQL());
                
                return entKredit.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKredit(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized Kredit updateExcObj(Kredit entKredit) throws DBException {
        try {
            if (entKredit.getOID() != 0) {
                PstKredit pstKredit = new PstKredit(entKredit.getOID());
                pstKredit.setString(FLD_FLAG_DETAIL, entKredit.getFlagDetail());
                pstKredit.setString(FLD_NO_REKENING, entKredit.getNoRekening());
                pstKredit.setString(FLD_CIF, entKredit.getCif());
                pstKredit.setString(FLD_KODE_SIFAT, entKredit.getKodeSifat());
                pstKredit.setString(FLD_KODE_JENIS_KREDIT, entKredit.getKodeJenisKredit());
                pstKredit.setString(FLD_KODE_AKAD, entKredit.getKodeAkad());
                pstKredit.setString(FLD_NO_AKAD_AWAL, entKredit.getNoAkadAwal());
                pstKredit.setDate(FLD_TGL_AKAD_AWAL, entKredit.getTglAkadAwal());
                pstKredit.setString(FLD_NO_AKAD_AKHIR, entKredit.getNoAkadAkhir());
                pstKredit.setDate(FLD_TGL_AKAD_AKHIR, entKredit.getTglAkadAkhir());
                pstKredit.setInt(FLD_BARU_PERPANJANGAN, entKredit.getBaruPerpanjangan());
                pstKredit.setDate(FLD_TGL_AWAL, entKredit.getTglAwal());
                pstKredit.setDate(FLD_TGL_MULAI, entKredit.getTglMulai());
                pstKredit.setDate(FLD_TGL_TEMPO, entKredit.getTglTempo());
                pstKredit.setString(FLD_KODE_KAT_DBITUR, entKredit.getKodeKatDbitur());
                pstKredit.setString(FLD_KODE_JENIS_PENGGUNAAN, entKredit.getKodeJenisPenggunaan());
                pstKredit.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entKredit.getKodeOrientasiPenggunaan());
                pstKredit.setString(FLD_KODE_SEKTOR_EKONOMI, entKredit.getKodeSektorEkonomi());
                pstKredit.setString(FLD_KODE_KAB, entKredit.getKodeKab());
                pstKredit.setDouble(FLD_NILAI, entKredit.getNilai());
                pstKredit.setString(FLD_KODE_VALUTA, entKredit.getKodeValuta());
                pstKredit.setInt(FLD_PROSENTASE_BUNGA, entKredit.getProsentaseBunga());
                pstKredit.setString(FLD_JENIS_BUNGA, entKredit.getJenisBunga());
                pstKredit.setString(FLD_KREDIT_PEMERINTAH, entKredit.getKreditPemerintah());
                pstKredit.setString(FLD_TAKEOVER, entKredit.getTakeover());
                pstKredit.setString(FLD_SUMBER_DANA, entKredit.getSumberDana());
                pstKredit.setDouble(FLD_PLAFON_AWAL, entKredit.getPlafonAwal());
                pstKredit.setDouble(FLD_PLAFON, entKredit.getPlafon());
                pstKredit.setDouble(FLD_REALISASI, entKredit.getRealisasi());
                pstKredit.setDouble(FLD_DENDA, entKredit.getDenda());
                pstKredit.setDouble(FLD_BAKI_DEBET, entKredit.getBakiDebet());
                pstKredit.setDouble(FLD_NILAI_UANG_ASAL, entKredit.getNilaiUangAsal());
                pstKredit.setString(FLD_KODE_KOLEKTIBILITAS, entKredit.getKodeKolektibilitas());
                pstKredit.setDate(FLD_TGL_MACET, entKredit.getTglMacet());
                pstKredit.setString(FLD_KODE_SEBAB_MACET, entKredit.getKodeSebabMacet());
                pstKredit.setDouble(FLD_TUNGGAKAN_POKOK, entKredit.getTunggakanPokok());
                pstKredit.setDouble(FLD_TUNGGAKAN_BUNGA, entKredit.getTunggakanBunga());
                pstKredit.setInt(FLD_JML_HARI_TUNGGAKAN, entKredit.getJmlHariTunggakan());
                pstKredit.setInt(FLD_FREKUENSI_TUNGGAKAN, entKredit.getFrekuensiTunggakan());
                pstKredit.setInt(FLD_FREKUENSI_RESTRUKTURISASI, entKredit.getFrekuensiRestrukturisasi());
                pstKredit.setDate(FLD_TGL_RESTRUKTURISASI_AWAL, entKredit.getTglRestrukturisasiAwal());
                pstKredit.setDate(FLD_TGL_RESTRUKTURISASI_AKHIR, entKredit.getTglRestrukturisasiAkhir());
                pstKredit.setString(FLD_KODE_CARA, entKredit.getKodeCara());
                pstKredit.setString(FLD_KODE_KONDISI, entKredit.getKodeKondisi());
                pstKredit.setDate(FLD_TGL_KONDISI, entKredit.getTglKondisi());
                pstKredit.setString(FLD_KETERANGAN, entKredit.getKeterangan());
                pstKredit.setString(FLD_KODE_KANTOR_CABANG, entKredit.getKodeKantorCabang());
                pstKredit.setString(FLD_OPERASI_DATA, entKredit.getOperasiData());
                pstKredit.setDate(FLD_OPEN_DATE, entKredit.getOpenDate());
                pstKredit.setInt(FLD_STATUS_DATA, entKredit.getStatusData());
                pstKredit.setLong(FLD_PERIODE_ID, entKredit.getPeriodeId());
                
                pstKredit.setInt(FLD_STATUS_PERUBAHAN_DATA,  entKredit.getStatusPerubahanData());
                
                pstKredit.update();
                
                pstKredit.setSqlQueryHistory("");
                pstKredit.setSqlQueryHistory(pstKredit.getInsertSQL());
                
                entKredit.setSqlHistory(pstKredit.getInsertSQL());
                
                return entKredit;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKredit(0), DBException.UNKNOWN);
        }
        return entKredit;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((Kredit) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstKredit pstKredit = new PstKredit(oid);
            pstKredit.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKredit(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(Kredit entKredit) throws DBException {
        try {
            PstKredit pstKredit = new PstKredit(0);
            pstKredit.setString(FLD_FLAG_DETAIL, entKredit.getFlagDetail());
            pstKredit.setString(FLD_NO_REKENING, entKredit.getNoRekening());
            pstKredit.setString(FLD_CIF, entKredit.getCif());
            pstKredit.setString(FLD_KODE_SIFAT, entKredit.getKodeSifat());
            pstKredit.setString(FLD_KODE_JENIS_KREDIT, entKredit.getKodeJenisKredit());
            pstKredit.setString(FLD_KODE_AKAD, entKredit.getKodeAkad());
            pstKredit.setString(FLD_NO_AKAD_AWAL, entKredit.getNoAkadAwal());
            pstKredit.setDate(FLD_TGL_AKAD_AWAL, entKredit.getTglAkadAwal());
            pstKredit.setString(FLD_NO_AKAD_AKHIR, entKredit.getNoAkadAkhir());
            pstKredit.setDate(FLD_TGL_AKAD_AKHIR, entKredit.getTglAkadAkhir());
            pstKredit.setInt(FLD_BARU_PERPANJANGAN, entKredit.getBaruPerpanjangan());
            pstKredit.setDate(FLD_TGL_AWAL, entKredit.getTglAwal());
            pstKredit.setDate(FLD_TGL_MULAI, entKredit.getTglMulai());
            pstKredit.setDate(FLD_TGL_TEMPO, entKredit.getTglTempo());
            pstKredit.setString(FLD_KODE_KAT_DBITUR, entKredit.getKodeKatDbitur());
            pstKredit.setString(FLD_KODE_JENIS_PENGGUNAAN, entKredit.getKodeJenisPenggunaan());
            pstKredit.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entKredit.getKodeOrientasiPenggunaan());
            pstKredit.setString(FLD_KODE_SEKTOR_EKONOMI, entKredit.getKodeSektorEkonomi());
            pstKredit.setString(FLD_KODE_KAB, entKredit.getKodeKab());
            pstKredit.setDouble(FLD_NILAI, entKredit.getNilai());
            pstKredit.setString(FLD_KODE_VALUTA, entKredit.getKodeValuta());
            pstKredit.setInt(FLD_PROSENTASE_BUNGA, entKredit.getProsentaseBunga());
            pstKredit.setString(FLD_JENIS_BUNGA, entKredit.getJenisBunga());
            pstKredit.setString(FLD_KREDIT_PEMERINTAH, entKredit.getKreditPemerintah());
            pstKredit.setString(FLD_TAKEOVER, entKredit.getTakeover());
            pstKredit.setString(FLD_SUMBER_DANA, entKredit.getSumberDana());
            pstKredit.setDouble(FLD_PLAFON_AWAL, entKredit.getPlafonAwal());
            pstKredit.setDouble(FLD_PLAFON, entKredit.getPlafon());
            pstKredit.setDouble(FLD_REALISASI, entKredit.getRealisasi());
            pstKredit.setDouble(FLD_DENDA, entKredit.getDenda());
            pstKredit.setDouble(FLD_BAKI_DEBET, entKredit.getBakiDebet());
            pstKredit.setDouble(FLD_NILAI_UANG_ASAL, entKredit.getNilaiUangAsal());
            pstKredit.setString(FLD_KODE_KOLEKTIBILITAS, entKredit.getKodeKolektibilitas());
            pstKredit.setDate(FLD_TGL_MACET, entKredit.getTglMacet());
            pstKredit.setString(FLD_KODE_SEBAB_MACET, entKredit.getKodeSebabMacet());
            pstKredit.setDouble(FLD_TUNGGAKAN_POKOK, entKredit.getTunggakanPokok());
            pstKredit.setDouble(FLD_TUNGGAKAN_BUNGA, entKredit.getTunggakanBunga());
            pstKredit.setInt(FLD_JML_HARI_TUNGGAKAN, entKredit.getJmlHariTunggakan());
            pstKredit.setInt(FLD_FREKUENSI_TUNGGAKAN, entKredit.getFrekuensiTunggakan());
            pstKredit.setInt(FLD_FREKUENSI_RESTRUKTURISASI, entKredit.getFrekuensiRestrukturisasi());
            pstKredit.setDate(FLD_TGL_RESTRUKTURISASI_AWAL, entKredit.getTglRestrukturisasiAwal());
            pstKredit.setDate(FLD_TGL_RESTRUKTURISASI_AKHIR, entKredit.getTglRestrukturisasiAkhir());
            pstKredit.setString(FLD_KODE_CARA, entKredit.getKodeCara());
            pstKredit.setString(FLD_KODE_KONDISI, entKredit.getKodeKondisi());
            pstKredit.setDate(FLD_TGL_KONDISI, entKredit.getTglKondisi());
            pstKredit.setString(FLD_KETERANGAN, entKredit.getKeterangan());
            pstKredit.setString(FLD_KODE_KANTOR_CABANG, entKredit.getKodeKantorCabang());
            pstKredit.setString(FLD_OPERASI_DATA, entKredit.getOperasiData());
            pstKredit.setDate(FLD_OPEN_DATE, entKredit.getOpenDate());
            pstKredit.setInt(FLD_STATUS_DATA, entKredit.getStatusData());
            pstKredit.setLong(FLD_PERIODE_ID, entKredit.getPeriodeId());
            
            pstKredit.setInt(FLD_STATUS_PERUBAHAN_DATA,  entKredit.getStatusPerubahanData());
            pstKredit.insert();
            
            pstKredit.setSqlQueryHistory("");
            pstKredit.setSqlQueryHistory(pstKredit.getInsertSQL());
                
            entKredit.setOID(pstKredit.getlong(FLD_KREDIT_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKredit(0), DBException.UNKNOWN);
        }
        return entKredit.getOID();
    }
    
    public static synchronized Kredit insertExcObj(Kredit entKredit) throws DBException {
        try {
            PstKredit pstKredit = new PstKredit(0);
            pstKredit.setString(FLD_FLAG_DETAIL, entKredit.getFlagDetail());
            pstKredit.setString(FLD_NO_REKENING, entKredit.getNoRekening());
            pstKredit.setString(FLD_CIF, entKredit.getCif());
            pstKredit.setString(FLD_KODE_SIFAT, entKredit.getKodeSifat());
            pstKredit.setString(FLD_KODE_JENIS_KREDIT, entKredit.getKodeJenisKredit());
            pstKredit.setString(FLD_KODE_AKAD, entKredit.getKodeAkad());
            pstKredit.setString(FLD_NO_AKAD_AWAL, entKredit.getNoAkadAwal());
            pstKredit.setDate(FLD_TGL_AKAD_AWAL, entKredit.getTglAkadAwal());
            pstKredit.setString(FLD_NO_AKAD_AKHIR, entKredit.getNoAkadAkhir());
            pstKredit.setDate(FLD_TGL_AKAD_AKHIR, entKredit.getTglAkadAkhir());
            pstKredit.setInt(FLD_BARU_PERPANJANGAN, entKredit.getBaruPerpanjangan());
            pstKredit.setDate(FLD_TGL_AWAL, entKredit.getTglAwal());
            pstKredit.setDate(FLD_TGL_MULAI, entKredit.getTglMulai());
            pstKredit.setDate(FLD_TGL_TEMPO, entKredit.getTglTempo());
            pstKredit.setString(FLD_KODE_KAT_DBITUR, entKredit.getKodeKatDbitur());
            pstKredit.setString(FLD_KODE_JENIS_PENGGUNAAN, entKredit.getKodeJenisPenggunaan());
            pstKredit.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entKredit.getKodeOrientasiPenggunaan());
            pstKredit.setString(FLD_KODE_SEKTOR_EKONOMI, entKredit.getKodeSektorEkonomi());
            pstKredit.setString(FLD_KODE_KAB, entKredit.getKodeKab());
            pstKredit.setDouble(FLD_NILAI, entKredit.getNilai());
            pstKredit.setString(FLD_KODE_VALUTA, entKredit.getKodeValuta());
            pstKredit.setInt(FLD_PROSENTASE_BUNGA, entKredit.getProsentaseBunga());
            pstKredit.setString(FLD_JENIS_BUNGA, entKredit.getJenisBunga());
            pstKredit.setString(FLD_KREDIT_PEMERINTAH, entKredit.getKreditPemerintah());
            pstKredit.setString(FLD_TAKEOVER, entKredit.getTakeover());
            pstKredit.setString(FLD_SUMBER_DANA, entKredit.getSumberDana());
            pstKredit.setDouble(FLD_PLAFON_AWAL, entKredit.getPlafonAwal());
            pstKredit.setDouble(FLD_PLAFON, entKredit.getPlafon());
            pstKredit.setDouble(FLD_REALISASI, entKredit.getRealisasi());
            pstKredit.setDouble(FLD_DENDA, entKredit.getDenda());
            pstKredit.setDouble(FLD_BAKI_DEBET, entKredit.getBakiDebet());
            pstKredit.setDouble(FLD_NILAI_UANG_ASAL, entKredit.getNilaiUangAsal());
            pstKredit.setString(FLD_KODE_KOLEKTIBILITAS, entKredit.getKodeKolektibilitas());
            pstKredit.setDate(FLD_TGL_MACET, entKredit.getTglMacet());
            pstKredit.setString(FLD_KODE_SEBAB_MACET, entKredit.getKodeSebabMacet());
            pstKredit.setDouble(FLD_TUNGGAKAN_POKOK, entKredit.getTunggakanPokok());
            pstKredit.setDouble(FLD_TUNGGAKAN_BUNGA, entKredit.getTunggakanBunga());
            pstKredit.setInt(FLD_JML_HARI_TUNGGAKAN, entKredit.getJmlHariTunggakan());
            pstKredit.setInt(FLD_FREKUENSI_TUNGGAKAN, entKredit.getFrekuensiTunggakan());
            pstKredit.setInt(FLD_FREKUENSI_RESTRUKTURISASI, entKredit.getFrekuensiRestrukturisasi());
            pstKredit.setDate(FLD_TGL_RESTRUKTURISASI_AWAL, entKredit.getTglRestrukturisasiAwal());
            pstKredit.setDate(FLD_TGL_RESTRUKTURISASI_AKHIR, entKredit.getTglRestrukturisasiAkhir());
            pstKredit.setString(FLD_KODE_CARA, entKredit.getKodeCara());
            pstKredit.setString(FLD_KODE_KONDISI, entKredit.getKodeKondisi());
            pstKredit.setDate(FLD_TGL_KONDISI, entKredit.getTglKondisi());
            pstKredit.setString(FLD_KETERANGAN, entKredit.getKeterangan());
            pstKredit.setString(FLD_KODE_KANTOR_CABANG, entKredit.getKodeKantorCabang());
            pstKredit.setString(FLD_OPERASI_DATA, entKredit.getOperasiData());
            pstKredit.setDate(FLD_OPEN_DATE, entKredit.getOpenDate());
            pstKredit.setInt(FLD_STATUS_DATA, entKredit.getStatusData());
            pstKredit.setLong(FLD_PERIODE_ID, entKredit.getPeriodeId());
            
            pstKredit.setInt(FLD_STATUS_PERUBAHAN_DATA,  entKredit.getStatusPerubahanData());
            
            pstKredit.insert();
            
            pstKredit.setSqlQueryHistory("");
            pstKredit.setSqlQueryHistory(pstKredit.getInsertSQL());
            entKredit.setSqlHistory(pstKredit.getInsertSQL());
                
            entKredit.setOID(pstKredit.getlong(FLD_KREDIT_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKredit(0), DBException.UNKNOWN);
        }
        return entKredit;
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((Kredit) entity);
    }

    public static void resultToObject(ResultSet rs, Kredit entKredit) {
        try {
            entKredit.setOID(rs.getLong(PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]));
            entKredit.setFlagDetail(rs.getString(PstKredit.fieldNames[PstKredit.FLD_FLAG_DETAIL]));
            entKredit.setNoRekening(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]));
            entKredit.setCif(rs.getString(PstKredit.fieldNames[PstKredit.FLD_CIF]));
            entKredit.setKodeSifat(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SIFAT]));
            entKredit.setKodeJenisKredit(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]));
            entKredit.setKodeAkad(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_AKAD]));
            entKredit.setNoAkadAwal(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AWAL]));
            entKredit.setTglAkadAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AWAL]));
            entKredit.setNoAkadAkhir(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AKHIR]));
            entKredit.setTglAkadAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AKHIR]));
            entKredit.setBaruPerpanjangan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_BARU_PERPANJANGAN]));
            entKredit.setTglAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]));
            entKredit.setTglMulai(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MULAI]));
            entKredit.setTglTempo(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_TEMPO]));
            entKredit.setKodeKatDbitur(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAT_DBITUR]));
            entKredit.setKodeJenisPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_PENGGUNAAN]));
            entKredit.setKodeOrientasiPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_ORIENTASI_PENGGUNAAN]));
            entKredit.setKodeSektorEkonomi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEKTOR_EKONOMI]));
            entKredit.setKodeKab(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAB]));
            entKredit.setNilai(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI]));
            entKredit.setKodeValuta(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_VALUTA]));
            entKredit.setProsentaseBunga(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_PROSENTASE_BUNGA]));
            entKredit.setJenisBunga(rs.getString(PstKredit.fieldNames[PstKredit.FLD_JENIS_BUNGA]));
            entKredit.setKreditPemerintah(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KREDIT_PEMERINTAH]));
            entKredit.setTakeover(rs.getString(PstKredit.fieldNames[PstKredit.FLD_TAKEOVER]));
            entKredit.setSumberDana(rs.getString(PstKredit.fieldNames[PstKredit.FLD_SUMBER_DANA]));
            entKredit.setPlafonAwal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON_AWAL]));
            entKredit.setPlafon(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON]));
            entKredit.setRealisasi(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_REALISASI]));
            entKredit.setDenda(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_DENDA]));
            entKredit.setBakiDebet(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_BAKI_DEBET]));
            entKredit.setNilaiUangAsal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI_UANG_ASAL]));
            entKredit.setKodeKolektibilitas(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KOLEKTIBILITAS]));
            entKredit.setTglMacet(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MACET]));
            entKredit.setKodeSebabMacet(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEBAB_MACET]));
            entKredit.setTunggakanPokok(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_TUNGGAKAN_POKOK]));
            entKredit.setTunggakanBunga(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_TUNGGAKAN_BUNGA]));
            entKredit.setJmlHariTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_JML_HARI_TUNGGAKAN]));
            entKredit.setFrekuensiTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_TUNGGAKAN]));
            entKredit.setFrekuensiRestrukturisasi(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_RESTRUKTURISASI]));
            entKredit.setTglRestrukturisasiAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AWAL]));
            entKredit.setTglRestrukturisasiAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AKHIR]));
            entKredit.setKodeCara(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_CARA]));
            entKredit.setKodeKondisi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]));
            entKredit.setTglKondisi(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_KONDISI]));
            entKredit.setKeterangan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KETERANGAN]));
            entKredit.setKodeKantorCabang(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]));
            entKredit.setOperasiData(rs.getString(PstKredit.fieldNames[PstKredit.FLD_OPERASI_DATA]));
            entKredit.setOpenDate(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_OPEN_DATE]));
            entKredit.setStatusData(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]));
            entKredit.setPeriodeId(rs.getLong(PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]));
            entKredit.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));

        } catch (Exception e) {
        }
    }
    
    
    public static void resultToObjectParentCabang(ResultSet rs, Kredit entKredit) {
        try {
            entKredit.setOID(rs.getLong(PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]));
            entKredit.setFlagDetail(rs.getString(PstKredit.fieldNames[PstKredit.FLD_FLAG_DETAIL]));
            entKredit.setNoRekening(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]));
            entKredit.setCif(rs.getString(PstKredit.fieldNames[PstKredit.FLD_CIF]));
            entKredit.setKodeSifat(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SIFAT]));
            entKredit.setKodeJenisKredit(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]));
            entKredit.setKodeAkad(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_AKAD]));
            entKredit.setNoAkadAwal(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AWAL]));
            entKredit.setTglAkadAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AWAL]));
            entKredit.setNoAkadAkhir(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AKHIR]));
            entKredit.setTglAkadAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AKHIR]));
            entKredit.setBaruPerpanjangan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_BARU_PERPANJANGAN]));
            entKredit.setTglAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]));
            entKredit.setTglMulai(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MULAI]));
            entKredit.setTglTempo(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_TEMPO]));
            entKredit.setKodeKatDbitur(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAT_DBITUR]));
            entKredit.setKodeJenisPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_PENGGUNAAN]));
            entKredit.setKodeOrientasiPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_ORIENTASI_PENGGUNAAN]));
            entKredit.setKodeSektorEkonomi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEKTOR_EKONOMI]));
            entKredit.setKodeKab(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAB]));
            entKredit.setNilai(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI]));
            entKredit.setKodeValuta(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_VALUTA]));
            entKredit.setProsentaseBunga(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_PROSENTASE_BUNGA]));
            entKredit.setJenisBunga(rs.getString(PstKredit.fieldNames[PstKredit.FLD_JENIS_BUNGA]));
            entKredit.setKreditPemerintah(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KREDIT_PEMERINTAH]));
            entKredit.setTakeover(rs.getString(PstKredit.fieldNames[PstKredit.FLD_TAKEOVER]));
            entKredit.setSumberDana(rs.getString(PstKredit.fieldNames[PstKredit.FLD_SUMBER_DANA]));
            entKredit.setPlafonAwal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON_AWAL]));
            entKredit.setPlafon(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON]));
            entKredit.setRealisasi(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_REALISASI]));
            entKredit.setDenda(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_DENDA]));
            entKredit.setBakiDebet(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_BAKI_DEBET]));
            entKredit.setNilaiUangAsal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI_UANG_ASAL]));
            entKredit.setKodeKolektibilitas(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KOLEKTIBILITAS]));
            entKredit.setTglMacet(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MACET]));
            entKredit.setKodeSebabMacet(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEBAB_MACET]));
            entKredit.setTunggakanPokok(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_TUNGGAKAN_POKOK]));
            entKredit.setTunggakanBunga(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_TUNGGAKAN_BUNGA]));
            entKredit.setJmlHariTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_JML_HARI_TUNGGAKAN]));
            entKredit.setFrekuensiTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_TUNGGAKAN]));
            entKredit.setFrekuensiRestrukturisasi(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_RESTRUKTURISASI]));
            entKredit.setTglRestrukturisasiAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AWAL]));
            entKredit.setTglRestrukturisasiAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AKHIR]));
            entKredit.setKodeCara(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_CARA]));
            entKredit.setKodeKondisi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]));
            entKredit.setTglKondisi(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_KONDISI]));
            entKredit.setKeterangan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KETERANGAN]));
            entKredit.setKodeKantorCabang(rs.getString("PARENT_CODE"));
            entKredit.setOperasiData(rs.getString(PstKredit.fieldNames[PstKredit.FLD_OPERASI_DATA]));
            entKredit.setOpenDate(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_OPEN_DATE]));
            entKredit.setStatusData(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]));
            entKredit.setPeriodeId(rs.getLong(PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]));
            entKredit.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));

        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoinReport(ResultSet rs, Kredit entKredit) {
        try {
            entKredit.setOID(rs.getLong(PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]));
            entKredit.setFlagDetail(rs.getString(PstKredit.fieldNames[PstKredit.FLD_FLAG_DETAIL]));
            entKredit.setNoRekening(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]));
            entKredit.setCif(rs.getString(PstKredit.fieldNames[PstKredit.FLD_CIF]));
            entKredit.setKodeSifat(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SIFAT]));
            entKredit.setKodeJenisKredit(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]));
            entKredit.setKodeAkad(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_AKAD]));
            entKredit.setNoAkadAwal(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AWAL]));
            entKredit.setTglAkadAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AWAL]));
            entKredit.setNoAkadAkhir(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AKHIR]));
            entKredit.setTglAkadAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AKHIR]));
            entKredit.setBaruPerpanjangan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_BARU_PERPANJANGAN]));
            entKredit.setTglAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]));
            entKredit.setTglMulai(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MULAI]));
            entKredit.setTglTempo(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_TEMPO]));
            entKredit.setKodeKatDbitur(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAT_DBITUR]));
            entKredit.setKodeJenisPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_PENGGUNAAN]));
            entKredit.setKodeOrientasiPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_ORIENTASI_PENGGUNAAN]));
            entKredit.setKodeSektorEkonomi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEKTOR_EKONOMI]));
            entKredit.setKodeKab(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAB]));
            entKredit.setNilai(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI]));
            entKredit.setKodeValuta(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_VALUTA]));
            entKredit.setProsentaseBunga(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_PROSENTASE_BUNGA]));
            entKredit.setJenisBunga(rs.getString(PstKredit.fieldNames[PstKredit.FLD_JENIS_BUNGA]));
            entKredit.setKreditPemerintah(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KREDIT_PEMERINTAH]));
            entKredit.setTakeover(rs.getString(PstKredit.fieldNames[PstKredit.FLD_TAKEOVER]));
            entKredit.setSumberDana(rs.getString(PstKredit.fieldNames[PstKredit.FLD_SUMBER_DANA]));
            entKredit.setPlafonAwal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON_AWAL]));
            entKredit.setPlafon(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON]));
            entKredit.setRealisasi(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_REALISASI]));
            entKredit.setDenda(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_DENDA]));
            entKredit.setBakiDebet(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_BAKI_DEBET]));
            entKredit.setNilaiUangAsal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI_UANG_ASAL]));
            entKredit.setKodeKolektibilitas(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KOLEKTIBILITAS]));
            entKredit.setTglMacet(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MACET]));
            entKredit.setKodeSebabMacet(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEBAB_MACET]));
            entKredit.setTunggakanPokok(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_TUNGGAKAN_POKOK]));
            entKredit.setTunggakanBunga(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_TUNGGAKAN_BUNGA]));
            entKredit.setJmlHariTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_JML_HARI_TUNGGAKAN]));
            entKredit.setFrekuensiTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_TUNGGAKAN]));
            entKredit.setFrekuensiRestrukturisasi(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_RESTRUKTURISASI]));
            entKredit.setTglRestrukturisasiAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AWAL]));
            entKredit.setTglRestrukturisasiAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AKHIR]));
            entKredit.setKodeCara(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_CARA]));
            entKredit.setKodeKondisi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]));
            entKredit.setTglKondisi(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_KONDISI]));
            entKredit.setKeterangan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KETERANGAN]));
            entKredit.setKodeKantorCabang(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]));
            entKredit.setOperasiData(rs.getString(PstKredit.fieldNames[PstKredit.FLD_OPERASI_DATA]));
            entKredit.setOpenDate(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_OPEN_DATE]));
            entKredit.setStatusData(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]));
            entKredit.setPeriodeId(rs.getLong(PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]));
            entKredit.setNamaSingkat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_LENGKAP]));
            entKredit.setAlamat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_ALAMAT]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoin(ResultSet rs, Kredit entKredit) {
        try {
            entKredit.setOID(rs.getLong(PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]));
            entKredit.setFlagDetail(rs.getString(PstKredit.fieldNames[PstKredit.FLD_FLAG_DETAIL]));
            entKredit.setNoRekening(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]));
            entKredit.setCif(rs.getString(PstKredit.fieldNames[PstKredit.FLD_CIF]));
            entKredit.setKodeSifat(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SIFAT]));
            entKredit.setKodeJenisKredit(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]));
            entKredit.setKodeAkad(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_AKAD]));
            entKredit.setNoAkadAwal(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AWAL]));
            entKredit.setTglAkadAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AWAL]));
            entKredit.setNoAkadAkhir(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AKHIR]));
            entKredit.setTglAkadAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AKHIR]));
            entKredit.setBaruPerpanjangan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_BARU_PERPANJANGAN]));
            entKredit.setTglAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]));
            entKredit.setTglMulai(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MULAI]));
            entKredit.setTglTempo(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_TEMPO]));
            entKredit.setKodeKatDbitur(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAT_DBITUR]));
            entKredit.setKodeJenisPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_PENGGUNAAN]));
            entKredit.setKodeOrientasiPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_ORIENTASI_PENGGUNAAN]));
            entKredit.setKodeSektorEkonomi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEKTOR_EKONOMI]));
            entKredit.setKodeKab(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAB]));
            entKredit.setNilai(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI]));
            entKredit.setKodeValuta(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_VALUTA]));
            entKredit.setProsentaseBunga(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_PROSENTASE_BUNGA]));
            entKredit.setJenisBunga(rs.getString(PstKredit.fieldNames[PstKredit.FLD_JENIS_BUNGA]));
            entKredit.setKreditPemerintah(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KREDIT_PEMERINTAH]));
            entKredit.setTakeover(rs.getString(PstKredit.fieldNames[PstKredit.FLD_TAKEOVER]));
            entKredit.setSumberDana(rs.getString(PstKredit.fieldNames[PstKredit.FLD_SUMBER_DANA]));
            entKredit.setPlafonAwal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON_AWAL]));
            entKredit.setPlafon(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON]));
            entKredit.setRealisasi(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_REALISASI]));
            entKredit.setDenda(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_DENDA]));
            entKredit.setBakiDebet(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_BAKI_DEBET]));
            entKredit.setNilaiUangAsal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI_UANG_ASAL]));
            entKredit.setKodeKolektibilitas(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KOLEKTIBILITAS]));
            entKredit.setTglMacet(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MACET]));
            entKredit.setKodeSebabMacet(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEBAB_MACET]));
            entKredit.setTunggakanPokok(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_TUNGGAKAN_POKOK]));
            entKredit.setTunggakanBunga(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_TUNGGAKAN_BUNGA]));
            entKredit.setJmlHariTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_JML_HARI_TUNGGAKAN]));
            entKredit.setFrekuensiTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_TUNGGAKAN]));
            entKredit.setFrekuensiRestrukturisasi(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_RESTRUKTURISASI]));
            entKredit.setTglRestrukturisasiAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AWAL]));
            entKredit.setTglRestrukturisasiAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AKHIR]));
            entKredit.setKodeCara(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_CARA]));
            entKredit.setKodeKondisi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KONDISI]));
            entKredit.setTglKondisi(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_KONDISI]));
            entKredit.setKeterangan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KETERANGAN]));
            entKredit.setKodeKantorCabang(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]));
            entKredit.setOperasiData(rs.getString(PstKredit.fieldNames[PstKredit.FLD_OPERASI_DATA]));
            entKredit.setOpenDate(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_OPEN_DATE]));
            entKredit.setStatusData(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]));
            entKredit.setNamaSifatKredit(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]));
            entKredit.setNamaJenisKredit(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]));
            entKredit.setNamaAKad(rs.getString(PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_NAMA_AKAD_PEMBIAYAAN]));
            entKredit.setDebiturOid(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]));
            entKredit.setDebiturType(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
            entKredit.setPeriodeId(rs.getLong(PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]));
        } catch (Exception e) {
        }
    }

    public static Vector listAll() {
        return list(0, 500, "", "");
    }
    
    /*
    Masih salah, gak bisa di linux
    */
    public static String formatCredit(String format, String value){
//        String formated = "";
//        if(value.length() > format.length()){
//            for(int i = 0; i < (value.length()-format.length());i++){
//                format +="#";
//            }
//        }
//        String[] split = value.split("");
//        String[] splitFormat = format.split("#");
//        for(int i = 0; i < split.length; i++){
//            if(splitFormat[i].equals("")){
//                formated +=""+split[i];
//            }else{
//                formated +=""+splitFormat[i]+""+split[i];
//            }
//        }
        return value;
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "+ 
                        "TRIM(KREDIT_OID) AS KREDIT_OID," +
                        "TRIM(FLAG_DETAIL) AS FLAG_DETAIL," +
                        "TRIM(NO_REKENING) AS NO_REKENING," +
                        "TRIM(CIF) AS CIF," +
                        "TRIM(KODE_SIFAT) AS KODE_SIFAT," +
                        "TRIM(KODE_JENIS_KREDIT) AS KODE_JENIS_KREDIT," +
                        "TRIM(KODE_AKAD) AS KODE_AKAD," +
                        "TRIM(NO_AKAD_AWAL) AS NO_AKAD_AWAL," +
                        "TRIM(TGL_AKAD_AWAL) AS TGL_AKAD_AWAL," +
                        "TRIM(NO_AKAD_AKHIR) AS NO_AKAD_AKHIR," +
                        "TRIM(TGL_AKAD_AKHIR) AS TGL_AKAD_AKHIR," +
                        "TRIM(BARU_PERPANJANGAN) AS BARU_PERPANJANGAN," +
                        "TRIM(TGL_AWAL) AS TGL_AWAL," +
                        "TRIM(TGL_MULAI) AS TGL_MULAI," +
                        "TRIM(TGL_TEMPO) AS TGL_TEMPO," +
                        "TRIM(KODE_KAT_DEBITUR) AS KODE_KAT_DEBITUR," +
                        "TRIM(KODE_JENIS_PENGGUNAAN) AS KODE_JENIS_PENGGUNAAN," +
                        "TRIM(KODE_ORIENTASI_PENGGUNAAN) AS KODE_ORIENTASI_PENGGUNAAN," +
                        "TRIM(KODE_SEKTOR_EKONOMI) AS KODE_SEKTOR_EKONOMI," +
                        "TRIM(KODE_KAB) AS KODE_KAB," +
                        "TRIM(NILAI) AS NILAI," +
                        "TRIM(KODE_VALUTA) AS KODE_VALUTA," +
                        "TRIM(PROSENTASE_BUNGA) AS PROSENTASE_BUNGA," +
                        "TRIM(JENIS_BUNGA) AS JENIS_BUNGA," +
                        "TRIM(KREDIT_PEMERINTAH) AS KREDIT_PEMERINTAH," +
                        "TRIM(TAKEOVER) AS TAKEOVER," +
                        "TRIM(SUMBER_DANA) AS SUMBER_DANA," +
                        "TRIM(PLAFON_AWAL) AS PLAFON_AWAL," +
                        "TRIM(PLAFON) AS PLAFON," +
                        "TRIM(REALISASI) AS REALISASI," +
                        "TRIM(DENDA) AS DENDA," +
                        "TRIM(BAKI_DEBET) AS BAKI_DEBET," +
                        "TRIM(NILAI_UANG_ASAL) AS NILAI_UANG_ASAL," +
                        "TRIM(KODE_KOLEKTIBILITAS) AS KODE_KOLEKTIBILITAS," +
                        "TRIM(TGL_MACET) AS TGL_MACET," +
                        "TRIM(KODE_SEBAB_MACET) AS KODE_SEBAB_MACET," +
                        "TRIM(TUNGGAKAN_POKOK) AS TUNGGAKAN_POKOK," +
                        "TRIM(TUNGGAKAN_BUNGA) AS TUNGGAKAN_BUNGA," +
                        "TRIM(JML_HARI_TUNGGAKAN) AS JML_HARI_TUNGGAKAN," +
                        "TRIM(FREKUENSI_TUNGGAKAN) AS FREKUENSI_TUNGGAKAN," +
                        "TRIM(FREKUENSI_RESTRUKTURISASI) AS FREKUENSI_RESTRUKTURISASI," +
                        "TRIM(TGL_RESTRUKTURISASI_AWAL) AS TGL_RESTRUKTURISASI_AWAL," +
                        "TRIM(TGL_RESTRUKTURISASI_AKHIR) AS TGL_RESTRUKTURISASI_AKHIR," +
                        "TRIM(KODE_CARA) AS KODE_CARA," +
                        "TRIM(KODE_KONDISI) AS KODE_KONDISI," +
                        "TRIM(TGL_KONDISI) AS TGL_KONDISI," +
                        "TRIM(KETERANGAN) AS KETERANGAN," +
                        "TRIM(KODE_KANTOR_CABANG) AS KODE_KANTOR_CABANG," +
                        "TRIM(OPERASI_DATA) AS OPERASI_DATA," +
                        "TRIM(OPEN_DATE) AS OPEN_DATE," +
                        "TRIM(STATUS_DATA) AS STATUS_DATA," +
                        "TRIM(PERIODE_ID) AS PERIODE_ID,"
                    + "STATUS_OPERASI_DATA"
                    + " FROM " + TBL_KREDIT;
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
                Kredit entKredit = new Kredit();
                resultToObject(rs, entKredit);
                lists.add(entKredit);
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
    
    public static Vector listParentCabang(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "+ 
                        "TRIM(KREDIT_OID) AS KREDIT_OID," +
                        "TRIM(FLAG_DETAIL) AS FLAG_DETAIL," +
                        "TRIM(NO_REKENING) AS NO_REKENING," +
                        "TRIM(CIF) AS CIF," +
                        "TRIM(KODE_SIFAT) AS KODE_SIFAT," +
                        "TRIM(KODE_JENIS_KREDIT) AS KODE_JENIS_KREDIT," +
                        "TRIM(KODE_AKAD) AS KODE_AKAD," +
                        "TRIM(NO_AKAD_AWAL) AS NO_AKAD_AWAL," +
                        "TRIM(TGL_AKAD_AWAL) AS TGL_AKAD_AWAL," +
                        "TRIM(NO_AKAD_AKHIR) AS NO_AKAD_AKHIR," +
                        "TRIM(TGL_AKAD_AKHIR) AS TGL_AKAD_AKHIR," +
                        "TRIM(BARU_PERPANJANGAN) AS BARU_PERPANJANGAN," +
                        "TRIM(TGL_AWAL) AS TGL_AWAL," +
                        "TRIM(TGL_MULAI) AS TGL_MULAI," +
                        "TRIM(TGL_TEMPO) AS TGL_TEMPO," +
                        "TRIM(KODE_KAT_DEBITUR) AS KODE_KAT_DEBITUR," +
                        "TRIM(KODE_JENIS_PENGGUNAAN) AS KODE_JENIS_PENGGUNAAN," +
                        "TRIM(KODE_ORIENTASI_PENGGUNAAN) AS KODE_ORIENTASI_PENGGUNAAN," +
                        "TRIM(KODE_SEKTOR_EKONOMI) AS KODE_SEKTOR_EKONOMI," +
                        "TRIM(KODE_KAB) AS KODE_KAB," +
                        "TRIM(NILAI) AS NILAI," +
                        "TRIM(KODE_VALUTA) AS KODE_VALUTA," +
                        "TRIM(PROSENTASE_BUNGA) AS PROSENTASE_BUNGA," +
                        "TRIM(JENIS_BUNGA) AS JENIS_BUNGA," +
                        "TRIM(KREDIT_PEMERINTAH) AS KREDIT_PEMERINTAH," +
                        "TRIM(TAKEOVER) AS TAKEOVER," +
                        "TRIM(SUMBER_DANA) AS SUMBER_DANA," +
                        "TRIM(PLAFON_AWAL) AS PLAFON_AWAL," +
                        "TRIM(PLAFON) AS PLAFON," +
                        "TRIM(REALISASI) AS REALISASI," +
                        "TRIM(DENDA) AS DENDA," +
                        "TRIM(BAKI_DEBET) AS BAKI_DEBET," +
                        "TRIM(NILAI_UANG_ASAL) AS NILAI_UANG_ASAL," +
                        "TRIM(KODE_KOLEKTIBILITAS) AS KODE_KOLEKTIBILITAS," +
                        "TRIM(TGL_MACET) AS TGL_MACET," +
                        "TRIM(KODE_SEBAB_MACET) AS KODE_SEBAB_MACET," +
                        "TRIM(TUNGGAKAN_POKOK) AS TUNGGAKAN_POKOK," +
                        "TRIM(TUNGGAKAN_BUNGA) AS TUNGGAKAN_BUNGA," +
                        "TRIM(JML_HARI_TUNGGAKAN) AS JML_HARI_TUNGGAKAN," +
                        "TRIM(FREKUENSI_TUNGGAKAN) AS FREKUENSI_TUNGGAKAN," +
                        "TRIM(FREKUENSI_RESTRUKTURISASI) AS FREKUENSI_RESTRUKTURISASI," +
                        "TRIM(TGL_RESTRUKTURISASI_AWAL) AS TGL_RESTRUKTURISASI_AWAL," +
                        "TRIM(TGL_RESTRUKTURISASI_AKHIR) AS TGL_RESTRUKTURISASI_AKHIR," +
                        "TRIM(KODE_CARA) AS KODE_CARA," +
                        "TRIM(KODE_KONDISI) AS KODE_KONDISI," +
                        "TRIM(TGL_KONDISI) AS TGL_KONDISI," +
                        "TRIM(KETERANGAN) AS KETERANGAN," +
                        "TRIM(KODE_KANTOR_CABANG) AS KODE_KANTOR_CABANG," +
                        "TRIM(OPERASI_DATA) AS OPERASI_DATA," +
                        "TRIM(OPEN_DATE) AS OPEN_DATE," +
                        "TRIM(PERIODE_ID) AS PERIODE_ID,"
                        + "TRIM(STATUS_DATA) AS STATUS_DATA, "
                        + "TRIM(PARENT_CODE) AS PARENT_CODE, STATUS_OPERASI_DATA "  
                        + "FROM " + TBL_KREDIT+" "
                        + "INNER JOIN dslik_cabang_bank ON "+TBL_KREDIT+"."+fieldNames[FLD_KODE_KANTOR_CABANG]+"=dslik_cabang_bank.KODE_CABANG";
//                        + "STATUS_OPERASI_DATA"
//                        + " FROM " + TBL_KREDIT;
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
                Kredit entKredit = new Kredit();
                resultToObjectParentCabang(rs, entKredit);
                lists.add(entKredit);
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
    
    
    
    public static Vector listJoinReport(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT kredit.*, "
                    + "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_LENGKAP]+","
                    + "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_ALAMAT]+" "
                    + "FROM " + TBL_KREDIT+" AS kredit "
                    + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "ON kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]
                    + " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID];
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
                Kredit entKredit = new Kredit();
                resultToObjectJoinReport(rs, entKredit);
                lists.add(entKredit);
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
    
    
    public static Vector listJoinSummaryReport(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT " +
                        " kredit.KODE_KONDISI, COUNT(kredit.NO_REKENING) AS TOT, "
                      + "SUM(kredit.PLAFON) AS PLAFON, "
                      + "SUM(kredit.BAKI_DEBET) AS BAKI, "
                      + "SUM(kredit.TUNGGAKAN_POKOK) AS TUNGGAKAN_POKOK " +
                        " FROM dslik_kredit AS kredit " +
                        " INNER JOIN dslik_debitur AS debitur " +
                        " ON kredit.CIF = debitur.CIF " +
                        " AND kredit.PERIODE_ID = debitur.PERIODE_ID ";
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
                Kredit entKredit = new Kredit();
                //resultToObjectJoinReport(rs, entKredit);
                entKredit.setKodeKondisi(rs.getString("KODE_KONDISI"));
                entKredit.setCountRekening(rs.getInt("TOT"));
                entKredit.setSumPlafon(rs.getDouble("PLAFON"));
                entKredit.setSumBakiDebet(rs.getDouble("BAKI"));
                entKredit.setSumTunggakanPokok(rs.getDouble("TUNGGAKAN_POKOK"));
                lists.add(entKredit);
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
    
    
    public static Vector listJoinSummaryReportLBU(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT " +
                        " kredit.KODE_KONDISI, COUNT(kredit.NO_REKENING) AS TOT, "
                      + "SUM(kredit.PLAFON) AS PLAFON, "
                      + "SUM(kredit.BAKI_DEBET) AS BAKI, "
                      + "SUM(kredit.TUNGGAKAN_POKOK) AS TUNGGAKAN_POKOK " +
                        " FROM dslik_kredit AS kredit " +
                        " INNER JOIN dslik_debitur AS debitur " +
                        " ON kredit.CIF = debitur.CIF " +
                        " AND kredit.PERIODE_ID = debitur.PERIODE_ID ";
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
                Kredit entKredit = new Kredit();
                //resultToObjectJoinReport(rs, entKredit);
                entKredit.setKodeKondisi(rs.getString("KODE_KONDISI"));
                entKredit.setCountRekening(rs.getInt("TOT"));
                entKredit.setSumPlafon(rs.getDouble("PLAFON"));
                entKredit.setSumBakiDebet(rs.getDouble("BAKI"));
                entKredit.setSumTunggakanPokok(rs.getDouble("TUNGGAKAN_POKOK"));
                lists.add(entKredit);
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
    
    
    public static Vector listJoinSummaryReportKolektibility(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT " +
                        " kredit.KODE_KOLEKTIBILITAS, COUNT(kredit.NO_REKENING) AS TOT, "
                      + "SUM(kredit.PLAFON) AS PLAFON, "
                      + "SUM(kredit.BAKI_DEBET) AS BAKI, "
                      + "SUM(kredit.TUNGGAKAN_POKOK) AS TUNGGAKAN_POKOK " +
                        " FROM dslik_kredit AS kredit " +
                        " INNER JOIN dslik_debitur AS debitur " +
                        " ON kredit.CIF = debitur.CIF " +
                        " AND kredit.PERIODE_ID = debitur.PERIODE_ID ";
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
                Kredit entKredit = new Kredit();
                //resultToObjectJoinReport(rs, entKredit);
                entKredit.setKodeKondisi(rs.getString("KODE_KOLEKTIBILITAS"));
                entKredit.setCountRekening(rs.getInt("TOT"));
                entKredit.setSumPlafon(rs.getDouble("PLAFON"));
                entKredit.setSumBakiDebet(rs.getDouble("BAKI"));
                entKredit.setSumTunggakanPokok(rs.getDouble("TUNGGAKAN_POKOK"));
                lists.add(entKredit);
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
    
    public static Vector listDistinct(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT TRIM(SUBSTRING(NO_REKENING,6,2)) AS NO_REKENING FROM " + TBL_KREDIT;
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
                Kredit entKredit = new Kredit();
                entKredit.setNoRekening(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]));
                lists.add(entKredit);
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
            String sql = ""
                + " SELECT DISTINCT kredit.*,"
                + " skredit."+PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]+", "
                + " apembiayaan."+PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_NAMA_AKAD_PEMBIAYAAN]+","
                + " jkredit."+PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]+","
                + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]+","
                + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+" "
                + " FROM "+TBL_KREDIT+" AS kredit "
                + " LEFT JOIN "+PstContentDataSifatKredit.TBL_CONTENT_DATA_SIFAT_KREDIT+" AS skredit "
                + " ON kredit."+fieldNames[FLD_KODE_SIFAT]+" = skredit."+PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataJenisKredit.TBL_CONTENT_DATA_JENIS_KREDIT+" AS jkredit "
                + " ON kredit."+fieldNames[FLD_KODE_JENIS_KREDIT]+" = jkredit."+PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataSkimAkadPembiayaan.TBL_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN+" apembiayaan "
                + " ON kredit."+fieldNames[FLD_KODE_AKAD]+" = apembiayaan."+PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_KODE_OJK]+" "
                + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + " ON kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + " INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + " ON kredit."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                + " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID];
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
                Kredit entKredit = new Kredit();
                resultToObjectJoin(rs, entKredit);
                lists.add(entKredit);
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

    public static boolean checkOID(long entKreditId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_KREDIT + " WHERE "
                    + PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID] + " = " + entKreditId;
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

    public static int getCountJoin(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = ""
                + "SELECT DISTINCT COUNT(DISTINCT kredit." + PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID] + ") "
                + " FROM "+TBL_KREDIT+" AS kredit "
                + " LEFT JOIN "+PstContentDataSifatKredit.TBL_CONTENT_DATA_SIFAT_KREDIT+" AS skredit "
                + " ON kredit."+fieldNames[FLD_KODE_SIFAT]+" = skredit."+PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataJenisKredit.TBL_CONTENT_DATA_JENIS_KREDIT+" AS jkredit "
                + " ON kredit."+fieldNames[FLD_KODE_JENIS_KREDIT]+" = jkredit."+PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataSkimAkadPembiayaan.TBL_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN+" apembiayaan "
                + " ON kredit."+fieldNames[FLD_KODE_AKAD]+" = apembiayaan."+PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_KODE_OJK]+""
                + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + " ON kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + " INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + " ON kredit."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                + " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID];
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
    
    public static int getCount(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT COUNT(" + PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID] + ") FROM " + TBL_KREDIT;
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
    
    public static long updateStatusData(long oidsegment) throws DBException {
        if(oidsegment==0){
            return 0;
        }
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "UPDATE "+TBL_KREDIT+" SET STATUS_DATA=0 WHERE "+fieldNames[FLD_KREDIT_OID]+"='"+oidsegment+"'";
        try {
            iResult = DBHandler.execUpdate(stSql);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            DBResultSet.close(dbrs);
        }
        return iResult;
    }
    
    
    public static long updateStatusOperasiData(long oidDebitur, int statusOperasiData) throws DBException {
        if(oidDebitur==0){
            return 0;
        }
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "UPDATE "+TBL_KREDIT+" SET STATUS_OPERASI_DATA='"+statusOperasiData+"' WHERE "+PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]+"='"+oidDebitur+"'";
        try {
            iResult = DBHandler.execUpdate(stSql);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            DBResultSet.close(dbrs);
        }
        return iResult;
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
                    Kredit entKredit = (Kredit) list.get(ls);
                    if (oid == entKredit.getOID()) {
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
    
    
    public static long moveDataAgunanHapus(long periodeId, String noRekening) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql ="insert into dslik_agunan_hapus (NO_REKENING, PERIODE_ID) values('"+noRekening+"','"+periodeId+"')";
                int result = execUpdate(sql);
                hasil = periodeId;
            }
        }
        catch(Exception e) {
        }
        return hasil;
    }
}
