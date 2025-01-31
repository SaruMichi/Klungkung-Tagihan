/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.kreditjoinaccount;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.contentdata.PstContentDataJenisKredit;
import com.dimata.dslik.entity.contentdata.PstContentDataSifatKredit;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstKreditJoinAccount extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    private String sqlQueryHistory = "";

    public static final String TBL_KREDIT_JOIN_ACCOUNT = "dslik_kredit_join_account";
    public static final int FLD_KREDIT_JOIN_ACNT_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_REKENING = 2;
    public static final int FLD_CIF = 3;
    public static final int FLD_SQUENCE_DEB_JOIN = 4;
    public static final int FLD_KODE_SIFAT = 5;
    public static final int FLD_KODE_JENIS = 6;
    public static final int FLD_KODE_AKAD = 7;
    public static final int FLD_NO_AKAD_AWAL = 8;
    public static final int FLD_TGL_AKAD_AWAL = 9;
    public static final int FLD_NO_AKAD_AKHIR = 10;
    public static final int FLD_TGL_AKAD_AKHIR = 11;
    public static final int FLD_BARU_PERPANJANGAN = 12;
    public static final int FLD_TGL_AWAL_KREDIT = 13;
    public static final int FLD_TGL_MULAI = 14;
    public static final int FLD_TGL_JATUH_TEMPO = 15;
    public static final int FLD_KODE_KAT_DEB = 16;
    public static final int FLD_KODE_JENIS_PENGGUNAAN = 17;
    public static final int FLD_KODE_ORIENTASI_PENGGUNAAN = 18;
    public static final int FLD_KODE_SEKTOR_EKONOMI = 19;
    public static final int FLD_KODE_KAB = 20;
    public static final int FLD_NILAI_PROYEK = 21;
    public static final int FLD_KODE_VALUTA = 22;
    public static final int FLD_PROSENTASE_BUNGA = 23;
    public static final int FLD_JENIS_BUNGA = 24;
    public static final int FLD_KREDIT_PROGRAM_PEMERINTAH = 25;
    public static final int FLD_TAKEOVER = 26;
    public static final int FLD_SUMBER_DANA = 27;
    public static final int FLD_PLAFON_AWAL = 28;
    public static final int FLD_PLAFON = 29;
    public static final int FLD_REALISASI = 30;
    public static final int FLD_DENDA = 31;
    public static final int FLD_BAKI_DEBET = 32;
    public static final int FLD_NILAI_UANG_ASAL = 33;
    public static final int FLD_KODE_KOLEKTIBILITAS = 34;
    public static final int FLD_TGL_MACET = 35;
    public static final int FLD_KODE_SEBAB_MACET = 36;
    public static final int FLD_TUNGGAKAN_POKOK = 37;
    public static final int FLD_TUNGGAKAN_BUNGA = 38;
    public static final int FLD_JML_HARI_TUNGGAKAN = 39;
    public static final int FLD_FREKUENSI_TUNGGAKAN = 40;
    public static final int FLD_FREKUENSI_RESTRUKTURISASI = 41;
    public static final int FLD_TGL_RESTRUKTURISASI_AWAL = 42;
    public static final int FLD_TGL_RESTRUKTIRISASI_AKHIR = 43;
    public static final int FLD_KODE_KONDISI = 44;
    public static final int FLD_KODE_CARA = 45;
    public static final int FLD_TGL_KONDISI = 46;
    public static final int FLD_KETERANGAN = 47;
    public static final int FLD_KODE_KANTOR_CABANG = 48;
    public static final int FLD_OPERASI_DATA = 49;
    public static final int FLD_OPEN_DATE = 50;
    public static final int FLD_STATUS_DATA = 51;
    public static final int FLD_PERIODE_ID = 52;
    public static final int FLD_STATUS_PERUBAHAN_DATA = 53;
    
    public static String[] fieldNames = {
        "KREDIT_JOIN_ACNT_OID",
        "FLAG_DETAIL",
        "NO_REKENING",
        "CIF",
        "SEQUENCE_DEB_JOIN",
        "KODE_SIFAT",
        "KODE_JENIS",
        "KODE_AKAD",
        "NO_AKAD_AWAL",
        "TGL_AKAD_AWAL",
        "NO_AKAD_AKHIR",
        "TGL_AKAD_AKHIR",
        "BARU_PERPANJANGAN",
        "TGL_AWAL_KREDIT",
        "TGL_MULAI",
        "TGL_JATUH_TEMPO",
        "KODE_KAT_DEB",
        "KODE_JENIS_PENGGUNAAN",
        "KODE_ORIENTASI_PENGGUNAAN",
        "KODE_SEKTOR_EKONOMI",
        "KODE_KAB",
        "NILAI_PROYEK",
        "KODE_VALUTA",
        "PROSENTASE_BUNGA",
        "JENIS_BUNGA",
        "KREDIT_PROGRAM_PEMERINTAH",
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
        "KODE_KONDISI",
        "KODE_CARA",
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
        TYPE_INT,
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

    public PstKreditJoinAccount() {
    }

    public PstKreditJoinAccount(int i) throws DBException {
        super(new PstKreditJoinAccount());
    }

    public PstKreditJoinAccount(String sOid) throws DBException {
        super(new PstKreditJoinAccount(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstKreditJoinAccount(long lOid) throws DBException {
        super(new PstKreditJoinAccount(0));
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
        return TBL_KREDIT_JOIN_ACCOUNT;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstKreditJoinAccount().getClass().getName();
    }

    public static KreditJoinAccount fetchExc(long oid) throws DBException {
        try {
            KreditJoinAccount entKreditJoinAccount = new KreditJoinAccount();
            PstKreditJoinAccount pstKreditJoinAccount = new PstKreditJoinAccount(oid);
            entKreditJoinAccount.setOID(oid);
            entKreditJoinAccount.setFlagDetail(pstKreditJoinAccount.getString(FLD_FLAG_DETAIL));
            entKreditJoinAccount.setNoRekening(pstKreditJoinAccount.getString(FLD_NO_REKENING));
            entKreditJoinAccount.setCif(pstKreditJoinAccount.getString(FLD_CIF));
            entKreditJoinAccount.setSquenceDebJoin(pstKreditJoinAccount.getInt(FLD_SQUENCE_DEB_JOIN));
            entKreditJoinAccount.setKodeSifat(pstKreditJoinAccount.getString(FLD_KODE_SIFAT));
            entKreditJoinAccount.setKodeJenis(pstKreditJoinAccount.getString(FLD_KODE_JENIS));
            entKreditJoinAccount.setKodeAkad(pstKreditJoinAccount.getString(FLD_KODE_AKAD));
            entKreditJoinAccount.setNoAkadAwal(pstKreditJoinAccount.getString(FLD_NO_AKAD_AWAL));
            entKreditJoinAccount.setTglAkadAwal(pstKreditJoinAccount.getDate(FLD_TGL_AKAD_AWAL));
            entKreditJoinAccount.setNoAkadAkhir(pstKreditJoinAccount.getString(FLD_NO_AKAD_AKHIR));
            entKreditJoinAccount.setTglAkadAkhir(pstKreditJoinAccount.getDate(FLD_TGL_AKAD_AKHIR));
            entKreditJoinAccount.setBaruPerpanjangan(pstKreditJoinAccount.getInt(FLD_BARU_PERPANJANGAN));
            entKreditJoinAccount.setTglAwalKredit(pstKreditJoinAccount.getDate(FLD_TGL_AWAL_KREDIT));
            entKreditJoinAccount.setTglMulai(pstKreditJoinAccount.getDate(FLD_TGL_MULAI));
            entKreditJoinAccount.setTglJatuhTempo(pstKreditJoinAccount.getDate(FLD_TGL_JATUH_TEMPO));
            entKreditJoinAccount.setKodeKatDeb(pstKreditJoinAccount.getString(FLD_KODE_KAT_DEB));
            entKreditJoinAccount.setKodeJenisPenggunaan(pstKreditJoinAccount.getString(FLD_KODE_JENIS_PENGGUNAAN));
            entKreditJoinAccount.setKodeOrientasiPenggunaan(pstKreditJoinAccount.getString(FLD_KODE_ORIENTASI_PENGGUNAAN));
            entKreditJoinAccount.setKodeSektorEkonomi(pstKreditJoinAccount.getString(FLD_KODE_SEKTOR_EKONOMI));
            entKreditJoinAccount.setKodeKab(pstKreditJoinAccount.getString(FLD_KODE_KAB));
            entKreditJoinAccount.setNilaiProyek(pstKreditJoinAccount.getdouble(FLD_NILAI_PROYEK));
            entKreditJoinAccount.setKodeValuta(pstKreditJoinAccount.getString(FLD_KODE_VALUTA));
            entKreditJoinAccount.setProsentaseBunga(pstKreditJoinAccount.getInt(FLD_PROSENTASE_BUNGA));
            entKreditJoinAccount.setJenisBunga(pstKreditJoinAccount.getString(FLD_JENIS_BUNGA));
            entKreditJoinAccount.setKreditPrgPemerintah(pstKreditJoinAccount.getString(FLD_KREDIT_PROGRAM_PEMERINTAH));
            entKreditJoinAccount.setTakeover(pstKreditJoinAccount.getString(FLD_TAKEOVER));
            entKreditJoinAccount.setSumberDana(pstKreditJoinAccount.getString(FLD_SUMBER_DANA));
            entKreditJoinAccount.setPlafonAwal(pstKreditJoinAccount.getdouble(FLD_PLAFON_AWAL));
            entKreditJoinAccount.setPlafon(pstKreditJoinAccount.getdouble(FLD_PLAFON));
            entKreditJoinAccount.setRealisasi(pstKreditJoinAccount.getdouble(FLD_REALISASI));
            entKreditJoinAccount.setDenda(pstKreditJoinAccount.getdouble(FLD_DENDA));
            entKreditJoinAccount.setBakiDebet(pstKreditJoinAccount.getdouble(FLD_BAKI_DEBET));
            entKreditJoinAccount.setNilaiUangAsal(pstKreditJoinAccount.getdouble(FLD_NILAI_UANG_ASAL));
            entKreditJoinAccount.setKodeKolektibilitas(pstKreditJoinAccount.getString(FLD_KODE_KOLEKTIBILITAS));
            entKreditJoinAccount.setTglMacet(pstKreditJoinAccount.getDate(FLD_TGL_MACET));
            entKreditJoinAccount.setKodeSebabMacet(pstKreditJoinAccount.getString(FLD_KODE_SEBAB_MACET));
            entKreditJoinAccount.setTunggakanPokok(pstKreditJoinAccount.getdouble(FLD_TUNGGAKAN_POKOK));
            entKreditJoinAccount.setTunggakanBunga(pstKreditJoinAccount.getdouble(FLD_TUNGGAKAN_BUNGA));
            entKreditJoinAccount.setJmlHariTunggakan(pstKreditJoinAccount.getInt(FLD_JML_HARI_TUNGGAKAN));
            entKreditJoinAccount.setFrekuensiTunggakan(pstKreditJoinAccount.getInt(FLD_FREKUENSI_TUNGGAKAN));
            entKreditJoinAccount.setFrekuensiRestrukturisasi(pstKreditJoinAccount.getInt(FLD_FREKUENSI_RESTRUKTURISASI));
            entKreditJoinAccount.setTglRestrukturisasiAwal(pstKreditJoinAccount.getDate(FLD_TGL_RESTRUKTURISASI_AWAL));
            entKreditJoinAccount.setTglRestruktirisasiAkhir(pstKreditJoinAccount.getDate(FLD_TGL_RESTRUKTIRISASI_AKHIR));
            entKreditJoinAccount.setKodeKondisi(pstKreditJoinAccount.getString(FLD_KODE_KONDISI));
            entKreditJoinAccount.setKodeCara(pstKreditJoinAccount.getString(FLD_KODE_CARA));
            entKreditJoinAccount.setTglKondisi(pstKreditJoinAccount.getDate(FLD_TGL_KONDISI));
            entKreditJoinAccount.setKeterangan(pstKreditJoinAccount.getString(FLD_KETERANGAN));
            entKreditJoinAccount.setKodeKantorCabang(pstKreditJoinAccount.getString(FLD_KODE_KANTOR_CABANG));
            entKreditJoinAccount.setOperasiData(pstKreditJoinAccount.getString(FLD_OPERASI_DATA));
            entKreditJoinAccount.setOpenDate(pstKreditJoinAccount.getDate(FLD_OPEN_DATE));
            entKreditJoinAccount.setStatusData(pstKreditJoinAccount.getInt(FLD_STATUS_DATA));
            entKreditJoinAccount.setPeriodeId(pstKreditJoinAccount.getlong(FLD_PERIODE_ID));
            entKreditJoinAccount.setStatusPerubahanData(pstKreditJoinAccount.getInt(FLD_STATUS_PERUBAHAN_DATA));
            
            return entKreditJoinAccount;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKreditJoinAccount(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        KreditJoinAccount entKreditJoinAccount = fetchExc(entity.getOID());
        entity = (Entity) entKreditJoinAccount;
        return entKreditJoinAccount.getOID();
    }

    public static synchronized long updateExc(KreditJoinAccount entKreditJoinAccount) throws DBException {
        try {
            if (entKreditJoinAccount.getOID() != 0) {
                PstKreditJoinAccount pstKreditJoinAccount = new PstKreditJoinAccount(entKreditJoinAccount.getOID());
                pstKreditJoinAccount.setString(FLD_FLAG_DETAIL, entKreditJoinAccount.getFlagDetail());
                pstKreditJoinAccount.setString(FLD_NO_REKENING, entKreditJoinAccount.getNoRekening());
                pstKreditJoinAccount.setString(FLD_CIF, entKreditJoinAccount.getCif());
                pstKreditJoinAccount.setInt(FLD_SQUENCE_DEB_JOIN, entKreditJoinAccount.getSquenceDebJoin());
                pstKreditJoinAccount.setString(FLD_KODE_SIFAT, entKreditJoinAccount.getKodeSifat());
                pstKreditJoinAccount.setString(FLD_KODE_JENIS, entKreditJoinAccount.getKodeJenis());
                pstKreditJoinAccount.setString(FLD_KODE_AKAD, entKreditJoinAccount.getKodeAkad());
                pstKreditJoinAccount.setString(FLD_NO_AKAD_AWAL, entKreditJoinAccount.getNoAkadAwal());
                pstKreditJoinAccount.setDate(FLD_TGL_AKAD_AWAL, entKreditJoinAccount.getTglAkadAwal());
                pstKreditJoinAccount.setString(FLD_NO_AKAD_AKHIR, entKreditJoinAccount.getNoAkadAkhir());
                pstKreditJoinAccount.setDate(FLD_TGL_AKAD_AKHIR, entKreditJoinAccount.getTglAkadAkhir());
                pstKreditJoinAccount.setInt(FLD_BARU_PERPANJANGAN, entKreditJoinAccount.getBaruPerpanjangan());
                pstKreditJoinAccount.setDate(FLD_TGL_AWAL_KREDIT, entKreditJoinAccount.getTglAwalKredit());
                pstKreditJoinAccount.setDate(FLD_TGL_MULAI, entKreditJoinAccount.getTglMulai());
                pstKreditJoinAccount.setDate(FLD_TGL_JATUH_TEMPO, entKreditJoinAccount.getTglJatuhTempo());
                pstKreditJoinAccount.setString(FLD_KODE_KAT_DEB, entKreditJoinAccount.getKodeKatDeb());
                pstKreditJoinAccount.setString(FLD_KODE_JENIS_PENGGUNAAN, entKreditJoinAccount.getKodeJenisPenggunaan());
                pstKreditJoinAccount.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entKreditJoinAccount.getKodeOrientasiPenggunaan());
                pstKreditJoinAccount.setString(FLD_KODE_SEKTOR_EKONOMI, entKreditJoinAccount.getKodeSektorEkonomi());
                pstKreditJoinAccount.setString(FLD_KODE_KAB, entKreditJoinAccount.getKodeKab());
                pstKreditJoinAccount.setDouble(FLD_NILAI_PROYEK, entKreditJoinAccount.getNilaiProyek());
                pstKreditJoinAccount.setString(FLD_KODE_VALUTA, entKreditJoinAccount.getKodeValuta());
                pstKreditJoinAccount.setInt(FLD_PROSENTASE_BUNGA, entKreditJoinAccount.getProsentaseBunga());
                pstKreditJoinAccount.setString(FLD_JENIS_BUNGA, entKreditJoinAccount.getJenisBunga());
                pstKreditJoinAccount.setString(FLD_KREDIT_PROGRAM_PEMERINTAH, entKreditJoinAccount.getKreditPrgPemerintah());
                pstKreditJoinAccount.setString(FLD_TAKEOVER, entKreditJoinAccount.getTakeover());
                pstKreditJoinAccount.setString(FLD_SUMBER_DANA, entKreditJoinAccount.getSumberDana());
                pstKreditJoinAccount.setDouble(FLD_PLAFON_AWAL, entKreditJoinAccount.getPlafonAwal());
                pstKreditJoinAccount.setDouble(FLD_PLAFON, entKreditJoinAccount.getPlafon());
                pstKreditJoinAccount.setDouble(FLD_REALISASI, entKreditJoinAccount.getRealisasi());
                pstKreditJoinAccount.setDouble(FLD_DENDA, entKreditJoinAccount.getDenda());
                pstKreditJoinAccount.setDouble(FLD_BAKI_DEBET, entKreditJoinAccount.getBakiDebet());
                pstKreditJoinAccount.setDouble(FLD_NILAI_UANG_ASAL, entKreditJoinAccount.getNilaiUangAsal());
                pstKreditJoinAccount.setString(FLD_KODE_KOLEKTIBILITAS, entKreditJoinAccount.getKodeKolektibilitas());
                pstKreditJoinAccount.setDate(FLD_TGL_MACET, entKreditJoinAccount.getTglMacet());
                pstKreditJoinAccount.setString(FLD_KODE_SEBAB_MACET, entKreditJoinAccount.getKodeSebabMacet());
                pstKreditJoinAccount.setDouble(FLD_TUNGGAKAN_POKOK, entKreditJoinAccount.getTunggakanPokok());
                pstKreditJoinAccount.setDouble(FLD_TUNGGAKAN_BUNGA, entKreditJoinAccount.getTunggakanBunga());
                pstKreditJoinAccount.setInt(FLD_JML_HARI_TUNGGAKAN, entKreditJoinAccount.getJmlHariTunggakan());
                pstKreditJoinAccount.setInt(FLD_FREKUENSI_TUNGGAKAN, entKreditJoinAccount.getFrekuensiTunggakan());
                pstKreditJoinAccount.setInt(FLD_FREKUENSI_RESTRUKTURISASI, entKreditJoinAccount.getFrekuensiRestrukturisasi());
                pstKreditJoinAccount.setDate(FLD_TGL_RESTRUKTURISASI_AWAL, entKreditJoinAccount.getTglRestrukturisasiAwal());
                pstKreditJoinAccount.setDate(FLD_TGL_RESTRUKTIRISASI_AKHIR, entKreditJoinAccount.getTglRestruktirisasiAkhir());
                pstKreditJoinAccount.setString(FLD_KODE_KONDISI, entKreditJoinAccount.getKodeKondisi());
                pstKreditJoinAccount.setString(FLD_KODE_CARA, entKreditJoinAccount.getKodeCara());
                pstKreditJoinAccount.setDate(FLD_TGL_KONDISI, entKreditJoinAccount.getTglKondisi());
                pstKreditJoinAccount.setString(FLD_KETERANGAN, entKreditJoinAccount.getKeterangan());
                pstKreditJoinAccount.setString(FLD_KODE_KANTOR_CABANG, entKreditJoinAccount.getKodeKantorCabang());
                pstKreditJoinAccount.setString(FLD_OPERASI_DATA, entKreditJoinAccount.getOperasiData());
                pstKreditJoinAccount.setDate(FLD_OPEN_DATE, entKreditJoinAccount.getOpenDate());
                pstKreditJoinAccount.setInt(FLD_STATUS_DATA, entKreditJoinAccount.getStatusData());
                pstKreditJoinAccount.setLong(FLD_PERIODE_ID, entKreditJoinAccount.getPeriodeId());
                pstKreditJoinAccount.setInt(FLD_STATUS_PERUBAHAN_DATA, entKreditJoinAccount.getStatusPerubahanData());
                
                pstKreditJoinAccount.update();
                
                pstKreditJoinAccount.setSqlQueryHistory("");
                pstKreditJoinAccount.setSqlQueryHistory(pstKreditJoinAccount.getUpdateSQL());
                
                return entKreditJoinAccount.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKreditJoinAccount(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized KreditJoinAccount updateExcObj(KreditJoinAccount entKreditJoinAccount) throws DBException {
        try {
            if (entKreditJoinAccount.getOID() != 0) {
                PstKreditJoinAccount pstKreditJoinAccount = new PstKreditJoinAccount(entKreditJoinAccount.getOID());
                pstKreditJoinAccount.setString(FLD_FLAG_DETAIL, entKreditJoinAccount.getFlagDetail());
                pstKreditJoinAccount.setString(FLD_NO_REKENING, entKreditJoinAccount.getNoRekening());
                pstKreditJoinAccount.setString(FLD_CIF, entKreditJoinAccount.getCif());
                pstKreditJoinAccount.setInt(FLD_SQUENCE_DEB_JOIN, entKreditJoinAccount.getSquenceDebJoin());
                pstKreditJoinAccount.setString(FLD_KODE_SIFAT, entKreditJoinAccount.getKodeSifat());
                pstKreditJoinAccount.setString(FLD_KODE_JENIS, entKreditJoinAccount.getKodeJenis());
                pstKreditJoinAccount.setString(FLD_KODE_AKAD, entKreditJoinAccount.getKodeAkad());
                pstKreditJoinAccount.setString(FLD_NO_AKAD_AWAL, entKreditJoinAccount.getNoAkadAwal());
                pstKreditJoinAccount.setDate(FLD_TGL_AKAD_AWAL, entKreditJoinAccount.getTglAkadAwal());
                pstKreditJoinAccount.setString(FLD_NO_AKAD_AKHIR, entKreditJoinAccount.getNoAkadAkhir());
                pstKreditJoinAccount.setDate(FLD_TGL_AKAD_AKHIR, entKreditJoinAccount.getTglAkadAkhir());
                pstKreditJoinAccount.setInt(FLD_BARU_PERPANJANGAN, entKreditJoinAccount.getBaruPerpanjangan());
                pstKreditJoinAccount.setDate(FLD_TGL_AWAL_KREDIT, entKreditJoinAccount.getTglAwalKredit());
                pstKreditJoinAccount.setDate(FLD_TGL_MULAI, entKreditJoinAccount.getTglMulai());
                pstKreditJoinAccount.setDate(FLD_TGL_JATUH_TEMPO, entKreditJoinAccount.getTglJatuhTempo());
                pstKreditJoinAccount.setString(FLD_KODE_KAT_DEB, entKreditJoinAccount.getKodeKatDeb());
                pstKreditJoinAccount.setString(FLD_KODE_JENIS_PENGGUNAAN, entKreditJoinAccount.getKodeJenisPenggunaan());
                pstKreditJoinAccount.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entKreditJoinAccount.getKodeOrientasiPenggunaan());
                pstKreditJoinAccount.setString(FLD_KODE_SEKTOR_EKONOMI, entKreditJoinAccount.getKodeSektorEkonomi());
                pstKreditJoinAccount.setString(FLD_KODE_KAB, entKreditJoinAccount.getKodeKab());
                pstKreditJoinAccount.setDouble(FLD_NILAI_PROYEK, entKreditJoinAccount.getNilaiProyek());
                pstKreditJoinAccount.setString(FLD_KODE_VALUTA, entKreditJoinAccount.getKodeValuta());
                pstKreditJoinAccount.setInt(FLD_PROSENTASE_BUNGA, entKreditJoinAccount.getProsentaseBunga());
                pstKreditJoinAccount.setString(FLD_JENIS_BUNGA, entKreditJoinAccount.getJenisBunga());
                pstKreditJoinAccount.setString(FLD_KREDIT_PROGRAM_PEMERINTAH, entKreditJoinAccount.getKreditPrgPemerintah());
                pstKreditJoinAccount.setString(FLD_TAKEOVER, entKreditJoinAccount.getTakeover());
                pstKreditJoinAccount.setString(FLD_SUMBER_DANA, entKreditJoinAccount.getSumberDana());
                pstKreditJoinAccount.setDouble(FLD_PLAFON_AWAL, entKreditJoinAccount.getPlafonAwal());
                pstKreditJoinAccount.setDouble(FLD_PLAFON, entKreditJoinAccount.getPlafon());
                pstKreditJoinAccount.setDouble(FLD_REALISASI, entKreditJoinAccount.getRealisasi());
                pstKreditJoinAccount.setDouble(FLD_DENDA, entKreditJoinAccount.getDenda());
                pstKreditJoinAccount.setDouble(FLD_BAKI_DEBET, entKreditJoinAccount.getBakiDebet());
                pstKreditJoinAccount.setDouble(FLD_NILAI_UANG_ASAL, entKreditJoinAccount.getNilaiUangAsal());
                pstKreditJoinAccount.setString(FLD_KODE_KOLEKTIBILITAS, entKreditJoinAccount.getKodeKolektibilitas());
                pstKreditJoinAccount.setDate(FLD_TGL_MACET, entKreditJoinAccount.getTglMacet());
                pstKreditJoinAccount.setString(FLD_KODE_SEBAB_MACET, entKreditJoinAccount.getKodeSebabMacet());
                pstKreditJoinAccount.setDouble(FLD_TUNGGAKAN_POKOK, entKreditJoinAccount.getTunggakanPokok());
                pstKreditJoinAccount.setDouble(FLD_TUNGGAKAN_BUNGA, entKreditJoinAccount.getTunggakanBunga());
                pstKreditJoinAccount.setInt(FLD_JML_HARI_TUNGGAKAN, entKreditJoinAccount.getJmlHariTunggakan());
                pstKreditJoinAccount.setInt(FLD_FREKUENSI_TUNGGAKAN, entKreditJoinAccount.getFrekuensiTunggakan());
                pstKreditJoinAccount.setInt(FLD_FREKUENSI_RESTRUKTURISASI, entKreditJoinAccount.getFrekuensiRestrukturisasi());
                pstKreditJoinAccount.setDate(FLD_TGL_RESTRUKTURISASI_AWAL, entKreditJoinAccount.getTglRestrukturisasiAwal());
                pstKreditJoinAccount.setDate(FLD_TGL_RESTRUKTIRISASI_AKHIR, entKreditJoinAccount.getTglRestruktirisasiAkhir());
                pstKreditJoinAccount.setString(FLD_KODE_KONDISI, entKreditJoinAccount.getKodeKondisi());
                pstKreditJoinAccount.setString(FLD_KODE_CARA, entKreditJoinAccount.getKodeCara());
                pstKreditJoinAccount.setDate(FLD_TGL_KONDISI, entKreditJoinAccount.getTglKondisi());
                pstKreditJoinAccount.setString(FLD_KETERANGAN, entKreditJoinAccount.getKeterangan());
                pstKreditJoinAccount.setString(FLD_KODE_KANTOR_CABANG, entKreditJoinAccount.getKodeKantorCabang());
                pstKreditJoinAccount.setString(FLD_OPERASI_DATA, entKreditJoinAccount.getOperasiData());
                pstKreditJoinAccount.setDate(FLD_OPEN_DATE, entKreditJoinAccount.getOpenDate());
                pstKreditJoinAccount.setInt(FLD_STATUS_DATA, entKreditJoinAccount.getStatusData());
                pstKreditJoinAccount.setLong(FLD_PERIODE_ID, entKreditJoinAccount.getPeriodeId());
                pstKreditJoinAccount.setInt(FLD_STATUS_PERUBAHAN_DATA, entKreditJoinAccount.getStatusPerubahanData());
                pstKreditJoinAccount.update();
                entKreditJoinAccount.setSqlHIstory(pstKreditJoinAccount.getUpdateSQL());
                pstKreditJoinAccount.setSqlQueryHistory("");
                pstKreditJoinAccount.setSqlQueryHistory(pstKreditJoinAccount.getUpdateSQL());
                
                return entKreditJoinAccount;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKreditJoinAccount(0), DBException.UNKNOWN);
        }
        return entKreditJoinAccount;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((KreditJoinAccount) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstKreditJoinAccount pstKreditJoinAccount = new PstKreditJoinAccount(oid);
            pstKreditJoinAccount.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKreditJoinAccount(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(KreditJoinAccount entKreditJoinAccount) throws DBException {
        try {
            PstKreditJoinAccount pstKreditJoinAccount = new PstKreditJoinAccount(0);
            pstKreditJoinAccount.setString(FLD_FLAG_DETAIL, entKreditJoinAccount.getFlagDetail());
            pstKreditJoinAccount.setString(FLD_NO_REKENING, entKreditJoinAccount.getNoRekening());
            pstKreditJoinAccount.setString(FLD_CIF, entKreditJoinAccount.getCif());
            pstKreditJoinAccount.setInt(FLD_SQUENCE_DEB_JOIN, entKreditJoinAccount.getSquenceDebJoin());
            pstKreditJoinAccount.setString(FLD_KODE_SIFAT, entKreditJoinAccount.getKodeSifat());
            pstKreditJoinAccount.setString(FLD_KODE_JENIS, entKreditJoinAccount.getKodeJenis());
            pstKreditJoinAccount.setString(FLD_KODE_AKAD, entKreditJoinAccount.getKodeAkad());
            pstKreditJoinAccount.setString(FLD_NO_AKAD_AWAL, entKreditJoinAccount.getNoAkadAwal());
            pstKreditJoinAccount.setDate(FLD_TGL_AKAD_AWAL, entKreditJoinAccount.getTglAkadAwal());
            pstKreditJoinAccount.setString(FLD_NO_AKAD_AKHIR, entKreditJoinAccount.getNoAkadAkhir());
            pstKreditJoinAccount.setDate(FLD_TGL_AKAD_AKHIR, entKreditJoinAccount.getTglAkadAkhir());
            pstKreditJoinAccount.setInt(FLD_BARU_PERPANJANGAN, entKreditJoinAccount.getBaruPerpanjangan());
            pstKreditJoinAccount.setDate(FLD_TGL_AWAL_KREDIT, entKreditJoinAccount.getTglAwalKredit());
            pstKreditJoinAccount.setDate(FLD_TGL_MULAI, entKreditJoinAccount.getTglMulai());
            pstKreditJoinAccount.setDate(FLD_TGL_JATUH_TEMPO, entKreditJoinAccount.getTglJatuhTempo());
            pstKreditJoinAccount.setString(FLD_KODE_KAT_DEB, entKreditJoinAccount.getKodeKatDeb());
            pstKreditJoinAccount.setString(FLD_KODE_JENIS_PENGGUNAAN, entKreditJoinAccount.getKodeJenisPenggunaan());
            pstKreditJoinAccount.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entKreditJoinAccount.getKodeOrientasiPenggunaan());
            pstKreditJoinAccount.setString(FLD_KODE_SEKTOR_EKONOMI, entKreditJoinAccount.getKodeSektorEkonomi());
            pstKreditJoinAccount.setString(FLD_KODE_KAB, entKreditJoinAccount.getKodeKab());
            pstKreditJoinAccount.setDouble(FLD_NILAI_PROYEK, entKreditJoinAccount.getNilaiProyek());
            pstKreditJoinAccount.setString(FLD_KODE_VALUTA, entKreditJoinAccount.getKodeValuta());
            pstKreditJoinAccount.setInt(FLD_PROSENTASE_BUNGA, entKreditJoinAccount.getProsentaseBunga());
            pstKreditJoinAccount.setString(FLD_JENIS_BUNGA, entKreditJoinAccount.getJenisBunga());
            pstKreditJoinAccount.setString(FLD_KREDIT_PROGRAM_PEMERINTAH, entKreditJoinAccount.getKreditPrgPemerintah());
            pstKreditJoinAccount.setString(FLD_TAKEOVER, entKreditJoinAccount.getTakeover());
            pstKreditJoinAccount.setString(FLD_SUMBER_DANA, entKreditJoinAccount.getSumberDana());
            pstKreditJoinAccount.setDouble(FLD_PLAFON_AWAL, entKreditJoinAccount.getPlafonAwal());
            pstKreditJoinAccount.setDouble(FLD_PLAFON, entKreditJoinAccount.getPlafon());
            pstKreditJoinAccount.setDouble(FLD_REALISASI, entKreditJoinAccount.getRealisasi());
            pstKreditJoinAccount.setDouble(FLD_DENDA, entKreditJoinAccount.getDenda());
            pstKreditJoinAccount.setDouble(FLD_BAKI_DEBET, entKreditJoinAccount.getBakiDebet());
            pstKreditJoinAccount.setDouble(FLD_NILAI_UANG_ASAL, entKreditJoinAccount.getNilaiUangAsal());
            pstKreditJoinAccount.setString(FLD_KODE_KOLEKTIBILITAS, entKreditJoinAccount.getKodeKolektibilitas());
            pstKreditJoinAccount.setDate(FLD_TGL_MACET, entKreditJoinAccount.getTglMacet());
            pstKreditJoinAccount.setString(FLD_KODE_SEBAB_MACET, entKreditJoinAccount.getKodeSebabMacet());
            pstKreditJoinAccount.setDouble(FLD_TUNGGAKAN_POKOK, entKreditJoinAccount.getTunggakanPokok());
            pstKreditJoinAccount.setDouble(FLD_TUNGGAKAN_BUNGA, entKreditJoinAccount.getTunggakanBunga());
            pstKreditJoinAccount.setInt(FLD_JML_HARI_TUNGGAKAN, entKreditJoinAccount.getJmlHariTunggakan());
            pstKreditJoinAccount.setInt(FLD_FREKUENSI_TUNGGAKAN, entKreditJoinAccount.getFrekuensiTunggakan());
            pstKreditJoinAccount.setInt(FLD_FREKUENSI_RESTRUKTURISASI, entKreditJoinAccount.getFrekuensiRestrukturisasi());
            pstKreditJoinAccount.setDate(FLD_TGL_RESTRUKTURISASI_AWAL, entKreditJoinAccount.getTglRestrukturisasiAwal());
            pstKreditJoinAccount.setDate(FLD_TGL_RESTRUKTIRISASI_AKHIR, entKreditJoinAccount.getTglRestruktirisasiAkhir());
            pstKreditJoinAccount.setString(FLD_KODE_KONDISI, entKreditJoinAccount.getKodeKondisi());
            pstKreditJoinAccount.setString(FLD_KODE_CARA, entKreditJoinAccount.getKodeCara());
            pstKreditJoinAccount.setDate(FLD_TGL_KONDISI, entKreditJoinAccount.getTglKondisi());
            pstKreditJoinAccount.setString(FLD_KETERANGAN, entKreditJoinAccount.getKeterangan());
            pstKreditJoinAccount.setString(FLD_KODE_KANTOR_CABANG, entKreditJoinAccount.getKodeKantorCabang());
            pstKreditJoinAccount.setString(FLD_OPERASI_DATA, entKreditJoinAccount.getOperasiData());
            pstKreditJoinAccount.setDate(FLD_OPEN_DATE, entKreditJoinAccount.getOpenDate());
            pstKreditJoinAccount.setInt(FLD_STATUS_DATA, entKreditJoinAccount.getStatusData());
            pstKreditJoinAccount.setLong(FLD_PERIODE_ID, entKreditJoinAccount.getPeriodeId());
            pstKreditJoinAccount.setInt(FLD_STATUS_PERUBAHAN_DATA, entKreditJoinAccount.getStatusPerubahanData());
            pstKreditJoinAccount.insert();
            
            pstKreditJoinAccount.setSqlQueryHistory("");
            pstKreditJoinAccount.setSqlQueryHistory(pstKreditJoinAccount.getInsertSQL());
                
            entKreditJoinAccount.setOID(pstKreditJoinAccount.getLong(FLD_KREDIT_JOIN_ACNT_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKreditJoinAccount(0), DBException.UNKNOWN);
        }
        return entKreditJoinAccount.getOID();
    }
    
    public static synchronized KreditJoinAccount insertExcObj(KreditJoinAccount entKreditJoinAccount) throws DBException {
        try {
            PstKreditJoinAccount pstKreditJoinAccount = new PstKreditJoinAccount(0);
            pstKreditJoinAccount.setString(FLD_FLAG_DETAIL, entKreditJoinAccount.getFlagDetail());
            pstKreditJoinAccount.setString(FLD_NO_REKENING, entKreditJoinAccount.getNoRekening());
            pstKreditJoinAccount.setString(FLD_CIF, entKreditJoinAccount.getCif());
            pstKreditJoinAccount.setInt(FLD_SQUENCE_DEB_JOIN, entKreditJoinAccount.getSquenceDebJoin());
            pstKreditJoinAccount.setString(FLD_KODE_SIFAT, entKreditJoinAccount.getKodeSifat());
            pstKreditJoinAccount.setString(FLD_KODE_JENIS, entKreditJoinAccount.getKodeJenis());
            pstKreditJoinAccount.setString(FLD_KODE_AKAD, entKreditJoinAccount.getKodeAkad());
            pstKreditJoinAccount.setString(FLD_NO_AKAD_AWAL, entKreditJoinAccount.getNoAkadAwal());
            pstKreditJoinAccount.setDate(FLD_TGL_AKAD_AWAL, entKreditJoinAccount.getTglAkadAwal());
            pstKreditJoinAccount.setString(FLD_NO_AKAD_AKHIR, entKreditJoinAccount.getNoAkadAkhir());
            pstKreditJoinAccount.setDate(FLD_TGL_AKAD_AKHIR, entKreditJoinAccount.getTglAkadAkhir());
            pstKreditJoinAccount.setInt(FLD_BARU_PERPANJANGAN, entKreditJoinAccount.getBaruPerpanjangan());
            pstKreditJoinAccount.setDate(FLD_TGL_AWAL_KREDIT, entKreditJoinAccount.getTglAwalKredit());
            pstKreditJoinAccount.setDate(FLD_TGL_MULAI, entKreditJoinAccount.getTglMulai());
            pstKreditJoinAccount.setDate(FLD_TGL_JATUH_TEMPO, entKreditJoinAccount.getTglJatuhTempo());
            pstKreditJoinAccount.setString(FLD_KODE_KAT_DEB, entKreditJoinAccount.getKodeKatDeb());
            pstKreditJoinAccount.setString(FLD_KODE_JENIS_PENGGUNAAN, entKreditJoinAccount.getKodeJenisPenggunaan());
            pstKreditJoinAccount.setString(FLD_KODE_ORIENTASI_PENGGUNAAN, entKreditJoinAccount.getKodeOrientasiPenggunaan());
            pstKreditJoinAccount.setString(FLD_KODE_SEKTOR_EKONOMI, entKreditJoinAccount.getKodeSektorEkonomi());
            pstKreditJoinAccount.setString(FLD_KODE_KAB, entKreditJoinAccount.getKodeKab());
            pstKreditJoinAccount.setDouble(FLD_NILAI_PROYEK, entKreditJoinAccount.getNilaiProyek());
            pstKreditJoinAccount.setString(FLD_KODE_VALUTA, entKreditJoinAccount.getKodeValuta());
            pstKreditJoinAccount.setInt(FLD_PROSENTASE_BUNGA, entKreditJoinAccount.getProsentaseBunga());
            pstKreditJoinAccount.setString(FLD_JENIS_BUNGA, entKreditJoinAccount.getJenisBunga());
            pstKreditJoinAccount.setString(FLD_KREDIT_PROGRAM_PEMERINTAH, entKreditJoinAccount.getKreditPrgPemerintah());
            pstKreditJoinAccount.setString(FLD_TAKEOVER, entKreditJoinAccount.getTakeover());
            pstKreditJoinAccount.setString(FLD_SUMBER_DANA, entKreditJoinAccount.getSumberDana());
            pstKreditJoinAccount.setDouble(FLD_PLAFON_AWAL, entKreditJoinAccount.getPlafonAwal());
            pstKreditJoinAccount.setDouble(FLD_PLAFON, entKreditJoinAccount.getPlafon());
            pstKreditJoinAccount.setDouble(FLD_REALISASI, entKreditJoinAccount.getRealisasi());
            pstKreditJoinAccount.setDouble(FLD_DENDA, entKreditJoinAccount.getDenda());
            pstKreditJoinAccount.setDouble(FLD_BAKI_DEBET, entKreditJoinAccount.getBakiDebet());
            pstKreditJoinAccount.setDouble(FLD_NILAI_UANG_ASAL, entKreditJoinAccount.getNilaiUangAsal());
            pstKreditJoinAccount.setString(FLD_KODE_KOLEKTIBILITAS, entKreditJoinAccount.getKodeKolektibilitas());
            pstKreditJoinAccount.setDate(FLD_TGL_MACET, entKreditJoinAccount.getTglMacet());
            pstKreditJoinAccount.setString(FLD_KODE_SEBAB_MACET, entKreditJoinAccount.getKodeSebabMacet());
            pstKreditJoinAccount.setDouble(FLD_TUNGGAKAN_POKOK, entKreditJoinAccount.getTunggakanPokok());
            pstKreditJoinAccount.setDouble(FLD_TUNGGAKAN_BUNGA, entKreditJoinAccount.getTunggakanBunga());
            pstKreditJoinAccount.setInt(FLD_JML_HARI_TUNGGAKAN, entKreditJoinAccount.getJmlHariTunggakan());
            pstKreditJoinAccount.setInt(FLD_FREKUENSI_TUNGGAKAN, entKreditJoinAccount.getFrekuensiTunggakan());
            pstKreditJoinAccount.setInt(FLD_FREKUENSI_RESTRUKTURISASI, entKreditJoinAccount.getFrekuensiRestrukturisasi());
            pstKreditJoinAccount.setDate(FLD_TGL_RESTRUKTURISASI_AWAL, entKreditJoinAccount.getTglRestrukturisasiAwal());
            pstKreditJoinAccount.setDate(FLD_TGL_RESTRUKTIRISASI_AKHIR, entKreditJoinAccount.getTglRestruktirisasiAkhir());
            pstKreditJoinAccount.setString(FLD_KODE_KONDISI, entKreditJoinAccount.getKodeKondisi());
            pstKreditJoinAccount.setString(FLD_KODE_CARA, entKreditJoinAccount.getKodeCara());
            pstKreditJoinAccount.setDate(FLD_TGL_KONDISI, entKreditJoinAccount.getTglKondisi());
            pstKreditJoinAccount.setString(FLD_KETERANGAN, entKreditJoinAccount.getKeterangan());
            pstKreditJoinAccount.setString(FLD_KODE_KANTOR_CABANG, entKreditJoinAccount.getKodeKantorCabang());
            pstKreditJoinAccount.setString(FLD_OPERASI_DATA, entKreditJoinAccount.getOperasiData());
            pstKreditJoinAccount.setDate(FLD_OPEN_DATE, entKreditJoinAccount.getOpenDate());
            pstKreditJoinAccount.setInt(FLD_STATUS_DATA, entKreditJoinAccount.getStatusData());
            pstKreditJoinAccount.setLong(FLD_PERIODE_ID, entKreditJoinAccount.getPeriodeId());
            pstKreditJoinAccount.setInt(FLD_STATUS_PERUBAHAN_DATA, entKreditJoinAccount.getStatusPerubahanData());
            pstKreditJoinAccount.insert();
            
            pstKreditJoinAccount.setSqlQueryHistory("");
            pstKreditJoinAccount.setSqlQueryHistory(pstKreditJoinAccount.getInsertSQL());
            
            entKreditJoinAccount.setSqlHIstory(pstKreditJoinAccount.getInsertSQL());
            entKreditJoinAccount.setOID(pstKreditJoinAccount.getlong(FLD_KREDIT_JOIN_ACNT_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstKreditJoinAccount(0), DBException.UNKNOWN);
        }
        return entKreditJoinAccount;
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((KreditJoinAccount) entity);
    }

    public static void resultToObject(ResultSet rs, KreditJoinAccount entKreditJoinAccount) {
        try {
            entKreditJoinAccount.setOID(rs.getLong(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KREDIT_JOIN_ACNT_OID]));
            entKreditJoinAccount.setFlagDetail(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_FLAG_DETAIL]));
            entKreditJoinAccount.setNoRekening(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_REKENING]));
            entKreditJoinAccount.setCif(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_CIF]));
            entKreditJoinAccount.setSquenceDebJoin(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_SQUENCE_DEB_JOIN]));
            entKreditJoinAccount.setKodeSifat(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_SIFAT]));
            entKreditJoinAccount.setKodeJenis(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_JENIS]));
            entKreditJoinAccount.setKodeAkad(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_AKAD]));
            entKreditJoinAccount.setNoAkadAwal(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_AKAD_AWAL]));
            entKreditJoinAccount.setTglAkadAwal(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_AKAD_AWAL]));
            entKreditJoinAccount.setNoAkadAkhir(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_AKAD_AKHIR]));
            entKreditJoinAccount.setTglAkadAkhir(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_AKAD_AKHIR]));
            entKreditJoinAccount.setBaruPerpanjangan(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_BARU_PERPANJANGAN]));
            entKreditJoinAccount.setTglAwalKredit(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_AWAL_KREDIT]));
            entKreditJoinAccount.setTglMulai(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_MULAI]));
            entKreditJoinAccount.setTglJatuhTempo(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_JATUH_TEMPO]));
            entKreditJoinAccount.setKodeKatDeb(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KAT_DEB]));
            entKreditJoinAccount.setKodeJenisPenggunaan(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_JENIS_PENGGUNAAN]));
            entKreditJoinAccount.setKodeOrientasiPenggunaan(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_ORIENTASI_PENGGUNAAN]));
            entKreditJoinAccount.setKodeSektorEkonomi(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_SEKTOR_EKONOMI]));
            entKreditJoinAccount.setKodeKab(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KAB]));
            entKreditJoinAccount.setNilaiProyek(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NILAI_PROYEK]));
            entKreditJoinAccount.setKodeValuta(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_VALUTA]));
            entKreditJoinAccount.setProsentaseBunga(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PROSENTASE_BUNGA]));
            entKreditJoinAccount.setJenisBunga(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_JENIS_BUNGA]));
            entKreditJoinAccount.setKreditPrgPemerintah(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KREDIT_PROGRAM_PEMERINTAH]));
            entKreditJoinAccount.setTakeover(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TAKEOVER]));
            entKreditJoinAccount.setSumberDana(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_SUMBER_DANA]));
            entKreditJoinAccount.setPlafonAwal(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PLAFON_AWAL]));
            entKreditJoinAccount.setPlafon(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PLAFON]));
            entKreditJoinAccount.setRealisasi(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_REALISASI]));
            entKreditJoinAccount.setDenda(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_DENDA]));
            entKreditJoinAccount.setBakiDebet(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_BAKI_DEBET]));
            entKreditJoinAccount.setNilaiUangAsal(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NILAI_UANG_ASAL]));
            entKreditJoinAccount.setKodeKolektibilitas(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KOLEKTIBILITAS]));
            entKreditJoinAccount.setTglMacet(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_MACET]));
            entKreditJoinAccount.setKodeSebabMacet(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_SEBAB_MACET]));
            entKreditJoinAccount.setTunggakanPokok(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TUNGGAKAN_POKOK]));
            entKreditJoinAccount.setTunggakanBunga(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TUNGGAKAN_BUNGA]));
            entKreditJoinAccount.setJmlHariTunggakan(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_JML_HARI_TUNGGAKAN]));
            entKreditJoinAccount.setFrekuensiTunggakan(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_FREKUENSI_TUNGGAKAN]));
            entKreditJoinAccount.setFrekuensiRestrukturisasi(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_FREKUENSI_RESTRUKTURISASI]));
            entKreditJoinAccount.setTglRestrukturisasiAwal(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_RESTRUKTURISASI_AWAL]));
            entKreditJoinAccount.setTglRestruktirisasiAkhir(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_RESTRUKTIRISASI_AKHIR]));
            entKreditJoinAccount.setKodeKondisi(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KONDISI]));
            entKreditJoinAccount.setKodeCara(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_CARA]));
            entKreditJoinAccount.setTglKondisi(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_KONDISI]));
            entKreditJoinAccount.setKeterangan(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KETERANGAN]));
            entKreditJoinAccount.setKodeKantorCabang(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KANTOR_CABANG]));
            entKreditJoinAccount.setOperasiData(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_OPERASI_DATA]));
            entKreditJoinAccount.setOpenDate(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_OPEN_DATE]));
            entKreditJoinAccount.setStatusData(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_STATUS_DATA]));
            entKreditJoinAccount.setPeriodeId(rs.getLong(PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]));
            entKreditJoinAccount.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));

        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoin(ResultSet rs, KreditJoinAccount entKreditJoinAccount) {
        try {
            entKreditJoinAccount.setOID(rs.getLong(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KREDIT_JOIN_ACNT_OID]));
            entKreditJoinAccount.setFlagDetail(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_FLAG_DETAIL]));
            entKreditJoinAccount.setNoRekening(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_REKENING]));
            entKreditJoinAccount.setCif(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_CIF]));
            entKreditJoinAccount.setSquenceDebJoin(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_SQUENCE_DEB_JOIN]));
            entKreditJoinAccount.setKodeSifat(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_SIFAT]));
            entKreditJoinAccount.setKodeJenis(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_JENIS]));
            entKreditJoinAccount.setKodeAkad(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_AKAD]));
            entKreditJoinAccount.setNoAkadAwal(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_AKAD_AWAL]));
            entKreditJoinAccount.setTglAkadAwal(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_AKAD_AWAL]));
            entKreditJoinAccount.setNoAkadAkhir(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_AKAD_AKHIR]));
            entKreditJoinAccount.setTglAkadAkhir(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_AKAD_AKHIR]));
            entKreditJoinAccount.setBaruPerpanjangan(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_BARU_PERPANJANGAN]));
            entKreditJoinAccount.setTglAwalKredit(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_AWAL_KREDIT]));
            entKreditJoinAccount.setTglMulai(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_MULAI]));
            entKreditJoinAccount.setTglJatuhTempo(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_JATUH_TEMPO]));
            entKreditJoinAccount.setKodeKatDeb(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KAT_DEB]));
            entKreditJoinAccount.setKodeJenisPenggunaan(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_JENIS_PENGGUNAAN]));
            entKreditJoinAccount.setKodeOrientasiPenggunaan(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_ORIENTASI_PENGGUNAAN]));
            entKreditJoinAccount.setKodeSektorEkonomi(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_SEKTOR_EKONOMI]));
            entKreditJoinAccount.setKodeKab(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KAB]));
            entKreditJoinAccount.setNilaiProyek(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NILAI_PROYEK]));
            entKreditJoinAccount.setKodeValuta(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_VALUTA]));
            entKreditJoinAccount.setProsentaseBunga(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PROSENTASE_BUNGA]));
            entKreditJoinAccount.setJenisBunga(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_JENIS_BUNGA]));
            entKreditJoinAccount.setKreditPrgPemerintah(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KREDIT_PROGRAM_PEMERINTAH]));
            entKreditJoinAccount.setTakeover(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TAKEOVER]));
            entKreditJoinAccount.setSumberDana(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_SUMBER_DANA]));
            entKreditJoinAccount.setPlafonAwal(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PLAFON_AWAL]));
            entKreditJoinAccount.setPlafon(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PLAFON]));
            entKreditJoinAccount.setRealisasi(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_REALISASI]));
            entKreditJoinAccount.setDenda(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_DENDA]));
            entKreditJoinAccount.setBakiDebet(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_BAKI_DEBET]));
            entKreditJoinAccount.setNilaiUangAsal(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NILAI_UANG_ASAL]));
            entKreditJoinAccount.setKodeKolektibilitas(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KOLEKTIBILITAS]));
            entKreditJoinAccount.setTglMacet(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_MACET]));
            entKreditJoinAccount.setKodeSebabMacet(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_SEBAB_MACET]));
            entKreditJoinAccount.setTunggakanPokok(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TUNGGAKAN_POKOK]));
            entKreditJoinAccount.setTunggakanBunga(rs.getDouble(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TUNGGAKAN_BUNGA]));
            entKreditJoinAccount.setJmlHariTunggakan(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_JML_HARI_TUNGGAKAN]));
            entKreditJoinAccount.setFrekuensiTunggakan(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_FREKUENSI_TUNGGAKAN]));
            entKreditJoinAccount.setFrekuensiRestrukturisasi(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_FREKUENSI_RESTRUKTURISASI]));
            entKreditJoinAccount.setTglRestrukturisasiAwal(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_RESTRUKTURISASI_AWAL]));
            entKreditJoinAccount.setTglRestruktirisasiAkhir(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_RESTRUKTIRISASI_AKHIR]));
            entKreditJoinAccount.setKodeKondisi(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KONDISI]));
            entKreditJoinAccount.setKodeCara(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_CARA]));
            entKreditJoinAccount.setTglKondisi(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_TGL_KONDISI]));
            entKreditJoinAccount.setKeterangan(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KETERANGAN]));
            entKreditJoinAccount.setKodeKantorCabang(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KANTOR_CABANG]));
            entKreditJoinAccount.setOperasiData(rs.getString(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_OPERASI_DATA]));
            entKreditJoinAccount.setOpenDate(rs.getDate(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_OPEN_DATE]));
            entKreditJoinAccount.setStatusData(rs.getInt(PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_STATUS_DATA]));
            entKreditJoinAccount.setSifatKredit(rs.getString(PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]));
            entKreditJoinAccount.setJenisKredit(rs.getString(PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]));
            entKreditJoinAccount.setPeriodeId(rs.getLong(PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]));
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
            String sql = "SELECT DISTINCT * FROM " + TBL_KREDIT_JOIN_ACCOUNT;
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
                KreditJoinAccount entKreditJoinAccount = new KreditJoinAccount();
                resultToObject(rs, entKreditJoinAccount);
                lists.add(entKreditJoinAccount);
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
                + " SELECT DISTINCT kj.*,"
                + " sk."+PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]+", "
                + " jk."+PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]+","
                + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]+", "
                + " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+" "
                + " FROM "+TBL_KREDIT_JOIN_ACCOUNT+" kj "
                + " LEFT JOIN "+PstContentDataSifatKredit.TBL_CONTENT_DATA_SIFAT_KREDIT+" sk "
                + " ON kj."+fieldNames[FLD_KODE_SIFAT]+" = sk."+PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataJenisKredit.TBL_CONTENT_DATA_JENIS_KREDIT+" jk "
                + " ON kj."+fieldNames[FLD_KODE_JENIS]+" = jk."+PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_KODE_OJK]+" "
                + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + " ON kj."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + " INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + " ON kj."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
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
                KreditJoinAccount entKreditJoinAccount = new KreditJoinAccount();
                resultToObjectJoin(rs, entKreditJoinAccount);
                lists.add(entKreditJoinAccount);
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

    public static boolean checkOID(long entKreditJoinAccountId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_KREDIT_JOIN_ACCOUNT + " WHERE "
                    + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KREDIT_JOIN_ACNT_OID] + " = " + entKreditJoinAccountId;
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
            String sql = "SELECT DISTINCT COUNT(" + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KREDIT_JOIN_ACNT_OID] + ") FROM " + TBL_KREDIT_JOIN_ACCOUNT;
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
    
    public static int getCountJoin(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = ""
                + " SELECT DISTINCT COUNT(kj." + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KREDIT_JOIN_ACNT_OID] + ") "
                + " FROM "+TBL_KREDIT_JOIN_ACCOUNT+" kj "
                + " LEFT JOIN "+PstContentDataSifatKredit.TBL_CONTENT_DATA_SIFAT_KREDIT+" sk "
                + " ON kj."+fieldNames[FLD_KODE_SIFAT]+" = sk."+PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataJenisKredit.TBL_CONTENT_DATA_JENIS_KREDIT+" jk "
                + " ON kj."+fieldNames[FLD_KODE_JENIS]+" = jk."+PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_KODE_OJK]+""
                + " INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + " ON kj."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + " AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + " INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + " ON kj."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
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

    public static int findLimitStart(long oid, int recordToGet, String whereClause, String orderClause) {
        int size = getCount(whereClause);
        int start = 0;
        boolean found = false;
        for (int i = 0; (i < size) && !found; i = i + recordToGet) {
            Vector list = list(i, recordToGet, whereClause, orderClause);
            start = i;
            if (list.size() > 0) {
                for (int ls = 0; ls < list.size(); ls++) {
                    KreditJoinAccount entKreditJoinAccount = (KreditJoinAccount) list.get(ls);
                    if (oid == entKreditJoinAccount.getOID()) {
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
