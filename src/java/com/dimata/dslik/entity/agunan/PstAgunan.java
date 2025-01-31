/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.agunan;

import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.masterdata.PstPeriode;
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
public class PstAgunan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    
    private String sqlQueryHistory = "";
    
    public static final String TBL_AGUNAN = "dslik_agunan";
    public static final int FLD_AGUNAN_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_KODE_REGISTER_AGUNAN = 2;
    public static final int FLD_NO_REKENING = 3;
    public static final int FLD_CIF = 4;
    public static final int FLD_KODE_STATUS_AGUNAN = 5;
    public static final int FLD_KODE_JENIS_AGUNAN = 6;
    public static final int FLD_PERINGKAT_AGUNAN = 7;
    public static final int FLD_KODE_LEMBAGA_PEMERINGKAT = 8;
    public static final int FLD_KODE_JENIS_PENGIKATAN = 9;
    public static final int FLD_TGL_PENGIKATAN = 10;
    public static final int FLD_NAMA_PEMILIK_AGUNAN = 11;
    public static final int FLD_BUKTI_KEPEMILIKAN = 12;
    public static final int FLD_ALAMAT_AGUNAN = 13;
    public static final int FLD_KODE_KAB_LOKASI_AGUNAN = 14;
    public static final int FLD_NILAI_AGUNAN_NJOP = 15;
    public static final int FLD_NILAI_AGUNAN_LJK = 16;
    public static final int FLD_TGL_PENILAIAN_LJK = 17;
    public static final int FLD_NILAI_AGU_PENILAI_INDEP = 18;
    public static final int FLD_NAMA_PENILAI_INDEP = 19;
    public static final int FLD_TGL_PENILAIAN_PENILAI_INDEP = 20;
    public static final int FLD_STATUS_PARIPASU = 21;
    public static final int FLD_STATUS_KREDIT_JOIN = 22;
    public static final int FLD_DIASURANSIKAN = 23;
    public static final int FLD_KETERANGAN = 24;
    public static final int FLD_KODE_KANTOR_CABANG = 25;
    public static final int FLD_OPERASI_DATA = 26;
    public static final int FLD_OPEN_DATE = 27;
    public static final int FLD_STATUS_DATA = 28;   
    public static final int FLD_PERIODE_ID = 29;
    public static final int FLD_PROSENTASE_PARIPASU = 30;
    public static final int FLD_STATUS_DATA_PERUBAHAN = 31;
    public static final int FLD_KODE_JENIS_SEGMENT_FASILITAS = 32;
    
    public static String[] fieldNames = {
        "AGUNAN_OID",
        "FLAG_DETAIL",
        "KODE_REGISTER_AGUNAN",
        "NO_REKENING",
        "CIF",
        "KODE_STATUS_AGUNAN",
        "KODE_JENIS_AGUNAN",
        "PERINGKAT_AGUNAN",
        "KODE_LEMBAGA_PEMERINGKAT",
        "KODE_JENIS_PENGIKATAN",
        "TGL_PENGIKATAN",
        "NAMA_PEMILIK_AGUNAN",
        "BUKTI_KEPEMILIKAN",
        "ALAMAT_AGUNAN",
        "KODE_KAB_LOKASI_AGUNAN",
        "NILAI_AGUNAN_NJOP",
        "NILAI_AGUNAN_LJK",
        "TGL_PENILAIAN_LJK",
        "NILAI_AGU_PENILAI_INDEP",
        "NAMA_PENILAI_INDEP",
        "TGL_PENILAIAN_PENILAI_INDEP",
        "STATUS_PARIPASU",
        "STATUS_KREDIT_JOIN",
        "DIASURANSIKAN",
        "KETERANGAN",
        "KODE_KANTOR_CABANG",
        "OPERASI_DATA",
        "OPEN_DATE",
        "STATUS_OPERASI_DATA",
        "PERIODE_ID",
        "PROSENTASE_PARIPASU",
        "STATUS_DATA",
        "KODE_JENIS_SEGMENT_FASILITAS"
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
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_DATE,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,//21
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_INT,
        TYPE_LONG,
        TYPE_FLOAT,
        TYPE_INT,
        TYPE_STRING
    };
    
    public static final String STATUS_KREDIT_YANG_BELUM_LUNAS = "";
    public static final String STATUS_REKENING_TUTUP = "02";
    public static final String STATUS_HAPUS_BUKU = "03";
    
    
    public static String[] statusKeys = {
        "00",
        "02",
        "03",
        "10"    
    };
    
    public static String[] statusValues = {
        "Kredit Yang Belum Lunas",
        "Rekening Tutup",
        "Hapus Buku",
        "Koletibility"
    };
    
    public static String[] statusDatas = {
        "Create",
        "Update",
        "Delete"
    };

    public PstAgunan() {
    }

    public PstAgunan(int i) throws DBException {
        super(new PstAgunan());
    }

    public PstAgunan(String sOid) throws DBException {
        super(new PstAgunan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstAgunan(long lOid) throws DBException {
        super(new PstAgunan(0));
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
        return TBL_AGUNAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstAgunan().getClass().getName();
    }

    public static Agunan fetchExc(long oid) throws DBException {
        try {
            Agunan entAgunan = new Agunan();
            PstAgunan pstAgunan = new PstAgunan(oid);
            entAgunan.setOID(oid);
            entAgunan.setFlagDetail(pstAgunan.getString(FLD_FLAG_DETAIL));
            entAgunan.setKodeRegisterAgunan(pstAgunan.getString(FLD_KODE_REGISTER_AGUNAN));
            entAgunan.setNoRekening(pstAgunan.getString(FLD_NO_REKENING));
            entAgunan.setCif(pstAgunan.getString(FLD_CIF));
            entAgunan.setKodeStatusAgunan(pstAgunan.getString(FLD_KODE_STATUS_AGUNAN));
            entAgunan.setKodeJenisAgunan(pstAgunan.getString(FLD_KODE_JENIS_AGUNAN));
            entAgunan.setPeringkatAgunan(pstAgunan.getString(FLD_PERINGKAT_AGUNAN));
            entAgunan.setKodeLembagaPemeringkat(pstAgunan.getString(FLD_KODE_LEMBAGA_PEMERINGKAT));
            entAgunan.setKodeJenisPengikatan(pstAgunan.getString(FLD_KODE_JENIS_PENGIKATAN));
            entAgunan.setTglPengikatan(pstAgunan.getDate(FLD_TGL_PENGIKATAN));
            entAgunan.setNamaPemilikAgunan(pstAgunan.getString(FLD_NAMA_PEMILIK_AGUNAN));
            entAgunan.setBuktiKepemilikan(pstAgunan.getString(FLD_BUKTI_KEPEMILIKAN));
            entAgunan.setAlamatAgunan(pstAgunan.getString(FLD_ALAMAT_AGUNAN));
            entAgunan.setKodeKabLokasiAgunan(pstAgunan.getString(FLD_KODE_KAB_LOKASI_AGUNAN));
            entAgunan.setNilaiAgunanNjop(pstAgunan.getdouble(FLD_NILAI_AGUNAN_NJOP));
            entAgunan.setNilaiAgunanLjk(pstAgunan.getdouble(FLD_NILAI_AGUNAN_LJK));
            entAgunan.setTglPenilaianLjk(pstAgunan.getDate(FLD_TGL_PENILAIAN_LJK));
            entAgunan.setNilaiAguPenilaiIndep(pstAgunan.getdouble(FLD_NILAI_AGU_PENILAI_INDEP));
            entAgunan.setNamaPenilaiIndep(pstAgunan.getString(FLD_NAMA_PENILAI_INDEP));
            entAgunan.setTglPenilaianPenilaiIndep(pstAgunan.getDate(FLD_TGL_PENILAIAN_PENILAI_INDEP));
            entAgunan.setStatusParipasu(pstAgunan.getString(FLD_STATUS_PARIPASU));
            entAgunan.setStatusKreditJoin(pstAgunan.getString(FLD_STATUS_KREDIT_JOIN));
            entAgunan.setDiasuransikan(pstAgunan.getString(FLD_DIASURANSIKAN));
            entAgunan.setKeterangan(pstAgunan.getString(FLD_KETERANGAN));
            entAgunan.setKodeKantorCabang(pstAgunan.getString(FLD_KODE_KANTOR_CABANG));
            entAgunan.setOperasiData(pstAgunan.getString(FLD_OPERASI_DATA));
            entAgunan.setOpenDate(pstAgunan.getDate(FLD_OPEN_DATE));
            entAgunan.setPeriodeId(pstAgunan.getlong(FLD_PERIODE_ID));
            entAgunan.setProsentaseParipasu(pstAgunan.getdouble(FLD_PROSENTASE_PARIPASU));
            entAgunan.setStatusDataPerubahan(pstAgunan.getInt(FLD_STATUS_DATA_PERUBAHAN));
            entAgunan.setKodeJenisSegementFasilitas(pstAgunan.getString( FLD_KODE_JENIS_SEGMENT_FASILITAS));
            return entAgunan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAgunan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        Agunan entAgunan = fetchExc(entity.getOID());
        entity = (Entity) entAgunan;
        return entAgunan.getOID();
    }

    public static synchronized long updateExc(Agunan entAgunan) throws DBException {
        try {
            if (entAgunan.getOID() != 0) {
                PstAgunan pstAgunan = new PstAgunan(entAgunan.getOID());
                pstAgunan.setString(FLD_FLAG_DETAIL, entAgunan.getFlagDetail());
                pstAgunan.setString(FLD_KODE_REGISTER_AGUNAN, entAgunan.getKodeRegisterAgunan());
                pstAgunan.setString(FLD_NO_REKENING, entAgunan.getNoRekening());
                pstAgunan.setString(FLD_CIF, entAgunan.getCif());
                pstAgunan.setString(FLD_KODE_STATUS_AGUNAN, entAgunan.getKodeStatusAgunan());
                pstAgunan.setString(FLD_KODE_JENIS_AGUNAN, entAgunan.getKodeJenisAgunan());
                pstAgunan.setString(FLD_PERINGKAT_AGUNAN, entAgunan.getPeringkatAgunan());
                pstAgunan.setString(FLD_KODE_LEMBAGA_PEMERINGKAT, entAgunan.getKodeLembagaPemeringkat());
                pstAgunan.setString(FLD_KODE_JENIS_PENGIKATAN, entAgunan.getKodeJenisPengikatan());
                pstAgunan.setDate(FLD_TGL_PENGIKATAN, entAgunan.getTglPengikatan());
                pstAgunan.setString(FLD_NAMA_PEMILIK_AGUNAN, entAgunan.getNamaPemilikAgunan());
                pstAgunan.setString(FLD_BUKTI_KEPEMILIKAN, entAgunan.getBuktiKepemilikan());
                pstAgunan.setString(FLD_ALAMAT_AGUNAN, entAgunan.getAlamatAgunan());
                pstAgunan.setString(FLD_KODE_KAB_LOKASI_AGUNAN, entAgunan.getKodeKabLokasiAgunan());
                pstAgunan.setDouble(FLD_NILAI_AGUNAN_NJOP, entAgunan.getNilaiAgunanNjop());
                pstAgunan.setDouble(FLD_NILAI_AGUNAN_LJK, entAgunan.getNilaiAgunanLjk());
                pstAgunan.setDate(FLD_TGL_PENILAIAN_LJK, entAgunan.getTglPenilaianLjk());
                pstAgunan.setDouble(FLD_NILAI_AGU_PENILAI_INDEP, entAgunan.getNilaiAguPenilaiIndep());
                pstAgunan.setString(FLD_NAMA_PENILAI_INDEP, entAgunan.getNamaPenilaiIndep());
                pstAgunan.setDate(FLD_TGL_PENILAIAN_PENILAI_INDEP, entAgunan.getTglPenilaianPenilaiIndep());
                pstAgunan.setString(FLD_STATUS_PARIPASU, entAgunan.getStatusParipasu());
                pstAgunan.setString(FLD_STATUS_KREDIT_JOIN, entAgunan.getStatusKreditJoin());
                pstAgunan.setString(FLD_DIASURANSIKAN, entAgunan.getDiasuransikan());
                pstAgunan.setString(FLD_KETERANGAN, entAgunan.getKeterangan());
                pstAgunan.setString(FLD_KODE_KANTOR_CABANG, entAgunan.getKodeKantorCabang());
                pstAgunan.setString(FLD_OPERASI_DATA, entAgunan.getOperasiData());
                pstAgunan.setDate(FLD_OPEN_DATE, entAgunan.getOpenDate());
                pstAgunan.setInt(FLD_STATUS_DATA, entAgunan.getStatusData());
                pstAgunan.setLong(FLD_PERIODE_ID, entAgunan.getPeriodeId());
                pstAgunan.setDouble(FLD_PROSENTASE_PARIPASU, entAgunan.getProsentaseParipasu());
                
                pstAgunan.setInt( FLD_STATUS_DATA_PERUBAHAN,  entAgunan.getStatusDataPerubahan());
                pstAgunan.setString(FLD_KODE_JENIS_SEGMENT_FASILITAS, entAgunan.getKodeJenisSegementFasilitas());
                
                pstAgunan.update();
                
                pstAgunan.setSqlQueryHistory("");
                pstAgunan.setSqlQueryHistory(pstAgunan.getInsertSQL());
                                
                return entAgunan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAgunan(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized Agunan updateExcObj(Agunan entAgunan) throws DBException {
        try {
            if (entAgunan.getOID() != 0) {
                PstAgunan pstAgunan = new PstAgunan(entAgunan.getOID());
                pstAgunan.setString(FLD_FLAG_DETAIL, entAgunan.getFlagDetail());
                pstAgunan.setString(FLD_KODE_REGISTER_AGUNAN, entAgunan.getKodeRegisterAgunan());
                pstAgunan.setString(FLD_NO_REKENING, entAgunan.getNoRekening());
                pstAgunan.setString(FLD_CIF, entAgunan.getCif());
                pstAgunan.setString(FLD_KODE_STATUS_AGUNAN, entAgunan.getKodeStatusAgunan());
                pstAgunan.setString(FLD_KODE_JENIS_AGUNAN, entAgunan.getKodeJenisAgunan());
                pstAgunan.setString(FLD_PERINGKAT_AGUNAN, entAgunan.getPeringkatAgunan());
                pstAgunan.setString(FLD_KODE_LEMBAGA_PEMERINGKAT, entAgunan.getKodeLembagaPemeringkat());
                pstAgunan.setString(FLD_KODE_JENIS_PENGIKATAN, entAgunan.getKodeJenisPengikatan());
                pstAgunan.setDate(FLD_TGL_PENGIKATAN, entAgunan.getTglPengikatan());
                pstAgunan.setString(FLD_NAMA_PEMILIK_AGUNAN, entAgunan.getNamaPemilikAgunan());
                pstAgunan.setString(FLD_BUKTI_KEPEMILIKAN, entAgunan.getBuktiKepemilikan());
                pstAgunan.setString(FLD_ALAMAT_AGUNAN, entAgunan.getAlamatAgunan());
                pstAgunan.setString(FLD_KODE_KAB_LOKASI_AGUNAN, entAgunan.getKodeKabLokasiAgunan());
                pstAgunan.setDouble(FLD_NILAI_AGUNAN_NJOP, entAgunan.getNilaiAgunanNjop());
                pstAgunan.setDouble(FLD_NILAI_AGUNAN_LJK, entAgunan.getNilaiAgunanLjk());
                pstAgunan.setDate(FLD_TGL_PENILAIAN_LJK, entAgunan.getTglPenilaianLjk());
                pstAgunan.setDouble(FLD_NILAI_AGU_PENILAI_INDEP, entAgunan.getNilaiAguPenilaiIndep());
                pstAgunan.setString(FLD_NAMA_PENILAI_INDEP, entAgunan.getNamaPenilaiIndep());
                pstAgunan.setDate(FLD_TGL_PENILAIAN_PENILAI_INDEP, entAgunan.getTglPenilaianPenilaiIndep());
                pstAgunan.setString(FLD_STATUS_PARIPASU, entAgunan.getStatusParipasu());
                pstAgunan.setString(FLD_STATUS_KREDIT_JOIN, entAgunan.getStatusKreditJoin());
                pstAgunan.setString(FLD_DIASURANSIKAN, entAgunan.getDiasuransikan());
                pstAgunan.setString(FLD_KETERANGAN, entAgunan.getKeterangan());
                pstAgunan.setString(FLD_KODE_KANTOR_CABANG, entAgunan.getKodeKantorCabang());
                pstAgunan.setString(FLD_OPERASI_DATA, entAgunan.getOperasiData());
                pstAgunan.setDate(FLD_OPEN_DATE, entAgunan.getOpenDate());
                pstAgunan.setInt(FLD_STATUS_DATA, entAgunan.getStatusData());
                pstAgunan.setLong(FLD_PERIODE_ID, entAgunan.getPeriodeId());
                pstAgunan.setDouble(FLD_PROSENTASE_PARIPASU, entAgunan.getProsentaseParipasu());
                pstAgunan.setInt(FLD_STATUS_DATA_PERUBAHAN, entAgunan.getStatusDataPerubahan());
                pstAgunan.setString(FLD_KODE_JENIS_SEGMENT_FASILITAS, entAgunan.getKodeJenisSegementFasilitas());
                pstAgunan.update();
                
                pstAgunan.setSqlQueryHistory("");
                pstAgunan.setSqlQueryHistory(pstAgunan.getInsertSQL());
                entAgunan.setSqlHistory(pstAgunan.getInsertSQL());
                return entAgunan;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAgunan(0), DBException.UNKNOWN);
        }
        return entAgunan;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((Agunan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstAgunan pstAgunan = new PstAgunan(oid);
            pstAgunan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAgunan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(Agunan entAgunan) throws DBException {
        try {
            PstAgunan pstAgunan = new PstAgunan(0);
            pstAgunan.setString(FLD_FLAG_DETAIL, entAgunan.getFlagDetail());
            pstAgunan.setString(FLD_KODE_REGISTER_AGUNAN, entAgunan.getKodeRegisterAgunan());
            pstAgunan.setString(FLD_NO_REKENING, entAgunan.getNoRekening());
            pstAgunan.setString(FLD_CIF, entAgunan.getCif());
            pstAgunan.setString(FLD_KODE_STATUS_AGUNAN, entAgunan.getKodeStatusAgunan());
            pstAgunan.setString(FLD_KODE_JENIS_AGUNAN, entAgunan.getKodeJenisAgunan());
            pstAgunan.setString(FLD_PERINGKAT_AGUNAN, entAgunan.getPeringkatAgunan());
            pstAgunan.setString(FLD_KODE_LEMBAGA_PEMERINGKAT, entAgunan.getKodeLembagaPemeringkat());
            pstAgunan.setString(FLD_KODE_JENIS_PENGIKATAN, entAgunan.getKodeJenisPengikatan());
            pstAgunan.setDate(FLD_TGL_PENGIKATAN, entAgunan.getTglPengikatan());
            pstAgunan.setString(FLD_NAMA_PEMILIK_AGUNAN, entAgunan.getNamaPemilikAgunan());
            pstAgunan.setString(FLD_BUKTI_KEPEMILIKAN, entAgunan.getBuktiKepemilikan());
            pstAgunan.setString(FLD_ALAMAT_AGUNAN, entAgunan.getAlamatAgunan());
            pstAgunan.setString(FLD_KODE_KAB_LOKASI_AGUNAN, entAgunan.getKodeKabLokasiAgunan());
            pstAgunan.setDouble(FLD_NILAI_AGUNAN_NJOP, entAgunan.getNilaiAgunanNjop());
            pstAgunan.setDouble(FLD_NILAI_AGUNAN_LJK, entAgunan.getNilaiAgunanLjk());
            pstAgunan.setDate(FLD_TGL_PENILAIAN_LJK, entAgunan.getTglPenilaianLjk());
            pstAgunan.setDouble(FLD_NILAI_AGU_PENILAI_INDEP, entAgunan.getNilaiAguPenilaiIndep());
            pstAgunan.setString(FLD_NAMA_PENILAI_INDEP, entAgunan.getNamaPenilaiIndep());
            pstAgunan.setDate(FLD_TGL_PENILAIAN_PENILAI_INDEP, entAgunan.getTglPenilaianPenilaiIndep());
            pstAgunan.setString(FLD_STATUS_PARIPASU, entAgunan.getStatusParipasu());
            pstAgunan.setString(FLD_STATUS_KREDIT_JOIN, entAgunan.getStatusKreditJoin());
            pstAgunan.setString(FLD_DIASURANSIKAN, entAgunan.getDiasuransikan());
            pstAgunan.setString(FLD_KETERANGAN, entAgunan.getKeterangan());
            pstAgunan.setString(FLD_KODE_KANTOR_CABANG, entAgunan.getKodeKantorCabang());
            pstAgunan.setString(FLD_OPERASI_DATA, entAgunan.getOperasiData());
            pstAgunan.setDate(FLD_OPEN_DATE, entAgunan.getOpenDate());
            pstAgunan.setInt(FLD_STATUS_DATA, entAgunan.getStatusData());
            pstAgunan.setLong(FLD_PERIODE_ID, entAgunan.getPeriodeId());
            pstAgunan.setDouble(FLD_PROSENTASE_PARIPASU, entAgunan.getProsentaseParipasu());
            pstAgunan.setInt(FLD_STATUS_DATA_PERUBAHAN, entAgunan.getStatusDataPerubahan());
            pstAgunan.setString(FLD_KODE_JENIS_SEGMENT_FASILITAS, entAgunan.getKodeJenisSegementFasilitas());
            pstAgunan.insert();
            
            pstAgunan.setSqlQueryHistory("");
            pstAgunan.setSqlQueryHistory(pstAgunan.getInsertSQL());
            
            entAgunan.setOID(pstAgunan.getlong(FLD_AGUNAN_OID));
            
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAgunan(0), DBException.UNKNOWN);
        }
        return entAgunan.getOID();
    }
    
    public static synchronized Agunan insertExcObj(Agunan entAgunan) throws DBException {
        try {
            PstAgunan pstAgunan = new PstAgunan(0);
            pstAgunan.setString(FLD_FLAG_DETAIL, entAgunan.getFlagDetail());
            pstAgunan.setString(FLD_KODE_REGISTER_AGUNAN, entAgunan.getKodeRegisterAgunan());
            pstAgunan.setString(FLD_NO_REKENING, entAgunan.getNoRekening());
            pstAgunan.setString(FLD_CIF, entAgunan.getCif());
            pstAgunan.setString(FLD_KODE_STATUS_AGUNAN, entAgunan.getKodeStatusAgunan());
            pstAgunan.setString(FLD_KODE_JENIS_AGUNAN, entAgunan.getKodeJenisAgunan());
            pstAgunan.setString(FLD_PERINGKAT_AGUNAN, entAgunan.getPeringkatAgunan());
            pstAgunan.setString(FLD_KODE_LEMBAGA_PEMERINGKAT, entAgunan.getKodeLembagaPemeringkat());
            pstAgunan.setString(FLD_KODE_JENIS_PENGIKATAN, entAgunan.getKodeJenisPengikatan());
            pstAgunan.setDate(FLD_TGL_PENGIKATAN, entAgunan.getTglPengikatan());
            pstAgunan.setString(FLD_NAMA_PEMILIK_AGUNAN, entAgunan.getNamaPemilikAgunan());
            pstAgunan.setString(FLD_BUKTI_KEPEMILIKAN, entAgunan.getBuktiKepemilikan());
            pstAgunan.setString(FLD_ALAMAT_AGUNAN, entAgunan.getAlamatAgunan());
            pstAgunan.setString(FLD_KODE_KAB_LOKASI_AGUNAN, entAgunan.getKodeKabLokasiAgunan());
            pstAgunan.setDouble(FLD_NILAI_AGUNAN_NJOP, entAgunan.getNilaiAgunanNjop());
            pstAgunan.setDouble(FLD_NILAI_AGUNAN_LJK, entAgunan.getNilaiAgunanLjk());
            pstAgunan.setDate(FLD_TGL_PENILAIAN_LJK, entAgunan.getTglPenilaianLjk());
            pstAgunan.setDouble(FLD_NILAI_AGU_PENILAI_INDEP, entAgunan.getNilaiAguPenilaiIndep());
            pstAgunan.setString(FLD_NAMA_PENILAI_INDEP, entAgunan.getNamaPenilaiIndep());
            pstAgunan.setDate(FLD_TGL_PENILAIAN_PENILAI_INDEP, entAgunan.getTglPenilaianPenilaiIndep());
            pstAgunan.setString(FLD_STATUS_PARIPASU, entAgunan.getStatusParipasu());
            pstAgunan.setString(FLD_STATUS_KREDIT_JOIN, entAgunan.getStatusKreditJoin());
            pstAgunan.setString(FLD_DIASURANSIKAN, entAgunan.getDiasuransikan());
            pstAgunan.setString(FLD_KETERANGAN, entAgunan.getKeterangan());
            pstAgunan.setString(FLD_KODE_KANTOR_CABANG, entAgunan.getKodeKantorCabang());
            pstAgunan.setString(FLD_OPERASI_DATA, entAgunan.getOperasiData());
            pstAgunan.setDate(FLD_OPEN_DATE, entAgunan.getOpenDate());
            pstAgunan.setInt(FLD_STATUS_DATA, entAgunan.getStatusData());
            pstAgunan.setLong(FLD_PERIODE_ID, entAgunan.getPeriodeId());
            pstAgunan.setDouble(FLD_PROSENTASE_PARIPASU, entAgunan.getProsentaseParipasu());
            pstAgunan.setInt(FLD_STATUS_DATA_PERUBAHAN, entAgunan.getStatusDataPerubahan());
            pstAgunan.setString(FLD_KODE_JENIS_SEGMENT_FASILITAS, entAgunan.getKodeJenisSegementFasilitas());
            pstAgunan.insert();
            
            pstAgunan.setSqlQueryHistory("");
            pstAgunan.setSqlQueryHistory(pstAgunan.getInsertSQL());
            entAgunan.setSqlHistory(pstAgunan.getInsertSQL());
            entAgunan.setOID(pstAgunan.getlong(FLD_AGUNAN_OID));
            
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstAgunan(0), DBException.UNKNOWN);
        }
        return entAgunan;
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((Agunan) entity);
    }

    public static void resultToObject(ResultSet rs, Agunan entAgunan) {
        try {
            entAgunan.setOID(rs.getLong(PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID]));
            entAgunan.setFlagDetail(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_FLAG_DETAIL]));
            entAgunan.setKodeRegisterAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]));
            entAgunan.setNoRekening(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING]));
            entAgunan.setCif(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_CIF]));
            entAgunan.setKodeStatusAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_STATUS_AGUNAN]));
            entAgunan.setKodeJenisAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_JENIS_AGUNAN]));
            entAgunan.setPeringkatAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_PERINGKAT_AGUNAN]));
            entAgunan.setKodeLembagaPemeringkat(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_LEMBAGA_PEMERINGKAT]));
            entAgunan.setKodeJenisPengikatan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_JENIS_PENGIKATAN]));
            entAgunan.setTglPengikatan(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENGIKATAN]));
            entAgunan.setNamaPemilikAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]));
            entAgunan.setBuktiKepemilikan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_BUKTI_KEPEMILIKAN]));
            entAgunan.setAlamatAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]));
            entAgunan.setKodeKabLokasiAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_KAB_LOKASI_AGUNAN]));
            entAgunan.setNilaiAgunanNjop(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGUNAN_NJOP]));
            entAgunan.setNilaiAgunanLjk(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGUNAN_LJK]));
            entAgunan.setTglPenilaianLjk(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENILAIAN_LJK]));
            entAgunan.setNilaiAguPenilaiIndep(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGU_PENILAI_INDEP]));
            entAgunan.setNamaPenilaiIndep(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PENILAI_INDEP]));
            entAgunan.setTglPenilaianPenilaiIndep(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENILAIAN_PENILAI_INDEP]));
            entAgunan.setStatusParipasu(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_STATUS_PARIPASU]));
            entAgunan.setStatusKreditJoin(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_STATUS_KREDIT_JOIN]));
            entAgunan.setDiasuransikan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_DIASURANSIKAN]));
            entAgunan.setKeterangan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KETERANGAN]));
            entAgunan.setKodeKantorCabang(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]));
            entAgunan.setOperasiData(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_OPERASI_DATA]));
            entAgunan.setOpenDate(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_OPEN_DATE]));
            entAgunan.setStatusData(rs.getInt(FLD_STATUS_DATA));
            entAgunan.setPeriodeId(rs.getLong(fieldNames[FLD_PERIODE_ID]));
            entAgunan.setProsentaseParipasu(rs.getDouble(fieldNames[FLD_PROSENTASE_PARIPASU]));
            entAgunan.setKodeJenisSegementFasilitas(rs.getString(fieldNames[FLD_KODE_JENIS_SEGMENT_FASILITAS]));
            
            entAgunan.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));

            
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectParentCabang(ResultSet rs, Agunan entAgunan) {
        try {
            entAgunan.setOID(rs.getLong(PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID]));
            entAgunan.setFlagDetail(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_FLAG_DETAIL]));
            entAgunan.setKodeRegisterAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]));
            entAgunan.setNoRekening(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING]));
            entAgunan.setCif(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_CIF]));
            entAgunan.setKodeStatusAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_STATUS_AGUNAN]));
            entAgunan.setKodeJenisAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_JENIS_AGUNAN]));
            entAgunan.setPeringkatAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_PERINGKAT_AGUNAN]));
            entAgunan.setKodeLembagaPemeringkat(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_LEMBAGA_PEMERINGKAT]));
            entAgunan.setKodeJenisPengikatan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_JENIS_PENGIKATAN]));
            entAgunan.setTglPengikatan(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENGIKATAN]));
            entAgunan.setNamaPemilikAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]));
            entAgunan.setBuktiKepemilikan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_BUKTI_KEPEMILIKAN]));
            entAgunan.setAlamatAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]));
            entAgunan.setKodeKabLokasiAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_KAB_LOKASI_AGUNAN]));
            entAgunan.setNilaiAgunanNjop(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGUNAN_NJOP]));
            entAgunan.setNilaiAgunanLjk(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGUNAN_LJK]));
            entAgunan.setTglPenilaianLjk(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENILAIAN_LJK]));
            entAgunan.setNilaiAguPenilaiIndep(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGU_PENILAI_INDEP]));
            entAgunan.setNamaPenilaiIndep(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PENILAI_INDEP]));
            entAgunan.setTglPenilaianPenilaiIndep(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENILAIAN_PENILAI_INDEP]));
            entAgunan.setStatusParipasu(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_STATUS_PARIPASU]));
            entAgunan.setStatusKreditJoin(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_STATUS_KREDIT_JOIN]));
            entAgunan.setDiasuransikan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_DIASURANSIKAN]));
            entAgunan.setKeterangan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KETERANGAN]));
            entAgunan.setKodeKantorCabang(rs.getString("PARENT_CODE"));
            entAgunan.setOperasiData(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_OPERASI_DATA]));
            entAgunan.setOpenDate(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_OPEN_DATE]));
            entAgunan.setStatusData(rs.getInt(FLD_STATUS_DATA));
            entAgunan.setPeriodeId(rs.getLong(fieldNames[FLD_PERIODE_ID]));
            entAgunan.setProsentaseParipasu(rs.getDouble(fieldNames[FLD_PROSENTASE_PARIPASU]));
            entAgunan.setKodeJenisSegementFasilitas(rs.getString(fieldNames[FLD_KODE_JENIS_SEGMENT_FASILITAS]));
            
            entAgunan.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));

            
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectJoin(ResultSet rs, Agunan entAgunan) {
        try {
            entAgunan.setOID(rs.getLong(PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID]));
            entAgunan.setFlagDetail(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_FLAG_DETAIL]));
            entAgunan.setKodeRegisterAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]));
            entAgunan.setNoRekening(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING]));
            entAgunan.setCif(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_CIF]));
            entAgunan.setKodeStatusAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_STATUS_AGUNAN]));
            entAgunan.setKodeJenisAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_JENIS_AGUNAN]));
            entAgunan.setPeringkatAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_PERINGKAT_AGUNAN]));
            entAgunan.setKodeLembagaPemeringkat(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_LEMBAGA_PEMERINGKAT]));
            entAgunan.setKodeJenisPengikatan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_JENIS_PENGIKATAN]));
            entAgunan.setTglPengikatan(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENGIKATAN]));
            entAgunan.setNamaPemilikAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]));
            entAgunan.setBuktiKepemilikan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_BUKTI_KEPEMILIKAN]));
            entAgunan.setAlamatAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]));
            entAgunan.setKodeKabLokasiAgunan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_KAB_LOKASI_AGUNAN]));
            entAgunan.setNilaiAgunanNjop(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGUNAN_NJOP]));
            entAgunan.setNilaiAgunanLjk(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGUNAN_LJK]));
            entAgunan.setTglPenilaianLjk(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENILAIAN_LJK]));
            entAgunan.setNilaiAguPenilaiIndep(rs.getDouble(PstAgunan.fieldNames[PstAgunan.FLD_NILAI_AGU_PENILAI_INDEP]));
            entAgunan.setNamaPenilaiIndep(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PENILAI_INDEP]));
            entAgunan.setTglPenilaianPenilaiIndep(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_TGL_PENILAIAN_PENILAI_INDEP]));
            entAgunan.setStatusParipasu(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_STATUS_PARIPASU]));
            entAgunan.setStatusKreditJoin(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_STATUS_KREDIT_JOIN]));
            entAgunan.setDiasuransikan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_DIASURANSIKAN]));
            entAgunan.setKeterangan(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KETERANGAN]));
            entAgunan.setKodeKantorCabang(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]));
            entAgunan.setOperasiData(rs.getString(PstAgunan.fieldNames[PstAgunan.FLD_OPERASI_DATA]));
            entAgunan.setOpenDate(rs.getDate(PstAgunan.fieldNames[PstAgunan.FLD_OPEN_DATE]));
            entAgunan.setStatusData(rs.getInt(FLD_STATUS_DATA));
            entAgunan.setPeriodeId(rs.getLong(fieldNames[FLD_PERIODE_ID]));
            entAgunan.setProsentaseParipasu(rs.getDouble(fieldNames[FLD_PROSENTASE_PARIPASU]));
            entAgunan.setDebiturOid(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]));
            entAgunan.setDebiturType(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
            entAgunan.setKodeJenisSegementFasilitas(rs.getString(fieldNames[FLD_KODE_JENIS_SEGMENT_FASILITAS]));
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
            String sql = "SELECT DISTINCT "
                    + "TRIM(AGUNAN_OID) AS AGUNAN_OID," +
                        "TRIM(FLAG_DETAIL) AS FLAG_DETAIL," +
                        "TRIM(KODE_REGISTER_AGUNAN) AS KODE_REGISTER_AGUNAN," +
                        "TRIM(NO_REKENING) AS NO_REKENING," +
                        "TRIM(CIF) AS CIF," +
                        "TRIM(KODE_STATUS_AGUNAN) AS KODE_STATUS_AGUNAN," +
                        "TRIM(KODE_JENIS_AGUNAN) AS KODE_JENIS_AGUNAN," +
                        "TRIM(PERINGKAT_AGUNAN) AS PERINGKAT_AGUNAN," +
                        "TRIM(KODE_LEMBAGA_PEMERINGKAT) AS KODE_LEMBAGA_PEMERINGKAT," +
                        "TRIM(KODE_JENIS_PENGIKATAN) AS KODE_JENIS_PENGIKATAN," +
                        "TRIM(TGL_PENGIKATAN) AS TGL_PENGIKATAN," +
                        "TRIM(NAMA_PEMILIK_AGUNAN) AS NAMA_PEMILIK_AGUNAN," +
                        "TRIM(BUKTI_KEPEMILIKAN) AS BUKTI_KEPEMILIKAN," +
                        "TRIM(ALAMAT_AGUNAN) AS ALAMAT_AGUNAN," +
                        "TRIM(KODE_KAB_LOKASI_AGUNAN) AS KODE_KAB_LOKASI_AGUNAN," +
                        "TRIM(NILAI_AGUNAN_NJOP) AS NILAI_AGUNAN_NJOP," +
                        "TRIM(NILAI_AGUNAN_LJK) AS NILAI_AGUNAN_LJK," +
                        "TRIM(TGL_PENILAIAN_LJK) AS TGL_PENILAIAN_LJK," +
                        "TRIM(NILAI_AGU_PENILAI_INDEP) AS NILAI_AGU_PENILAI_INDEP," +
                        "TRIM(NAMA_PENILAI_INDEP) AS NAMA_PENILAI_INDEP," +
                        "TRIM(TGL_PENILAIAN_PENILAI_INDEP) AS TGL_PENILAIAN_PENILAI_INDEP," +
                        "TRIM(STATUS_PARIPASU) AS STATUS_PARIPASU," +
                        "TRIM(STATUS_KREDIT_JOIN) AS STATUS_KREDIT_JOIN," +
                        "TRIM(DIASURANSIKAN) AS DIASURANSIKAN," +
                        "TRIM(KETERANGAN) AS KETERANGAN," +
                        "TRIM(KODE_KANTOR_CABANG) AS KODE_KANTOR_CABANG," +
                        "TRIM(OPERASI_DATA) AS OPERASI_DATA," +
                        "TRIM(OPEN_DATE) AS OPEN_DATE," +
                        "TRIM(STATUS_DATA) AS STATUS_DATA," +
                        "TRIM(PERIODE_ID) AS PERIODE_ID," +
                        "TRIM(PROSENTASE_PARIPASU) AS PROSENTASE_PARIPASU, STATUS_OPERASI_DATA, KODE_JENIS_SEGMENT_FASILITAS"
                    + " FROM " + TBL_AGUNAN;
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
                Agunan entAgunan = new Agunan();
                resultToObject(rs, entAgunan);
                lists.add(entAgunan);
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
    
    
    public static Vector listJoinParentCabang(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "
                    + "TRIM(AGUNAN_OID) AS AGUNAN_OID," +
                        "TRIM(FLAG_DETAIL) AS FLAG_DETAIL," +
                        "TRIM(KODE_REGISTER_AGUNAN) AS KODE_REGISTER_AGUNAN," +
                        "TRIM(NO_REKENING) AS NO_REKENING," +
                        "TRIM(CIF) AS CIF," +
                        "TRIM(KODE_STATUS_AGUNAN) AS KODE_STATUS_AGUNAN," +
                        "TRIM(KODE_JENIS_AGUNAN) AS KODE_JENIS_AGUNAN," +
                        "TRIM(PERINGKAT_AGUNAN) AS PERINGKAT_AGUNAN," +
                        "TRIM(KODE_LEMBAGA_PEMERINGKAT) AS KODE_LEMBAGA_PEMERINGKAT," +
                        "TRIM(KODE_JENIS_PENGIKATAN) AS KODE_JENIS_PENGIKATAN," +
                        "TRIM(TGL_PENGIKATAN) AS TGL_PENGIKATAN," +
                        "TRIM(NAMA_PEMILIK_AGUNAN) AS NAMA_PEMILIK_AGUNAN," +
                        "TRIM(BUKTI_KEPEMILIKAN) AS BUKTI_KEPEMILIKAN," +
                        "TRIM(ALAMAT_AGUNAN) AS ALAMAT_AGUNAN," +
                        "TRIM(KODE_KAB_LOKASI_AGUNAN) AS KODE_KAB_LOKASI_AGUNAN," +
                        "TRIM(NILAI_AGUNAN_NJOP) AS NILAI_AGUNAN_NJOP," +
                        "TRIM(NILAI_AGUNAN_LJK) AS NILAI_AGUNAN_LJK," +
                        "TRIM(TGL_PENILAIAN_LJK) AS TGL_PENILAIAN_LJK," +
                        "TRIM(NILAI_AGU_PENILAI_INDEP) AS NILAI_AGU_PENILAI_INDEP," +
                        "TRIM(NAMA_PENILAI_INDEP) AS NAMA_PENILAI_INDEP," +
                        "TRIM(TGL_PENILAIAN_PENILAI_INDEP) AS TGL_PENILAIAN_PENILAI_INDEP," +
                        "TRIM(STATUS_PARIPASU) AS STATUS_PARIPASU," +
                        "TRIM(STATUS_KREDIT_JOIN) AS STATUS_KREDIT_JOIN," +
                        "TRIM(DIASURANSIKAN) AS DIASURANSIKAN," +
                        "TRIM(KETERANGAN) AS KETERANGAN," +
                        "TRIM(KODE_KANTOR_CABANG) AS KODE_KANTOR_CABANG," +
                        "TRIM(OPERASI_DATA) AS OPERASI_DATA," +
                        "TRIM(OPEN_DATE) AS OPEN_DATE," +
                        "TRIM(STATUS_DATA) AS STATUS_DATA," +
                        "TRIM(PERIODE_ID) AS PERIODE_ID," +
                        "TRIM(PROSENTASE_PARIPASU) AS PROSENTASE_PARIPASU, STATUS_OPERASI_DATA, KODE_JENIS_SEGMENT_FASILITAS,"
                      + "TRIM(PARENT_CODE) AS PARENT_CODE "  
                      + "FROM " + TBL_AGUNAN+" "
                      + "INNER JOIN dslik_cabang_bank ON "+TBL_AGUNAN+"."+fieldNames[FLD_KODE_KANTOR_CABANG]+"=dslik_cabang_bank.KODE_CABANG";
                    //+ " FROM " + TBL_AGUNAN;
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
                Agunan entAgunan = new Agunan();
                resultToObjectParentCabang(rs, entAgunan);
                lists.add(entAgunan);
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
    
    
    
    public static Vector listJoinParentCabangAgunanHapus(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "
                    + "TRIM(AGUNAN_OID) AS AGUNAN_OID," +
                        "TRIM(FLAG_DETAIL) AS FLAG_DETAIL," +
                        "TRIM(KODE_REGISTER_AGUNAN) AS KODE_REGISTER_AGUNAN," +
                        "TRIM(dslik_agunan.NO_REKENING) AS NO_REKENING," +
                        "TRIM(CIF) AS CIF," +
                        "TRIM(KODE_STATUS_AGUNAN) AS KODE_STATUS_AGUNAN," +
                        "TRIM(KODE_JENIS_AGUNAN) AS KODE_JENIS_AGUNAN," +
                        "TRIM(PERINGKAT_AGUNAN) AS PERINGKAT_AGUNAN," +
                        "TRIM(KODE_LEMBAGA_PEMERINGKAT) AS KODE_LEMBAGA_PEMERINGKAT," +
                        "TRIM(KODE_JENIS_PENGIKATAN) AS KODE_JENIS_PENGIKATAN," +
                        "TRIM(TGL_PENGIKATAN) AS TGL_PENGIKATAN," +
                        "TRIM(NAMA_PEMILIK_AGUNAN) AS NAMA_PEMILIK_AGUNAN," +
                        "TRIM(BUKTI_KEPEMILIKAN) AS BUKTI_KEPEMILIKAN," +
                        "TRIM(ALAMAT_AGUNAN) AS ALAMAT_AGUNAN," +
                        "TRIM(KODE_KAB_LOKASI_AGUNAN) AS KODE_KAB_LOKASI_AGUNAN," +
                        "TRIM(NILAI_AGUNAN_NJOP) AS NILAI_AGUNAN_NJOP," +
                        "TRIM(NILAI_AGUNAN_LJK) AS NILAI_AGUNAN_LJK," +
                        "TRIM(TGL_PENILAIAN_LJK) AS TGL_PENILAIAN_LJK," +
                        "TRIM(NILAI_AGU_PENILAI_INDEP) AS NILAI_AGU_PENILAI_INDEP," +
                        "TRIM(NAMA_PENILAI_INDEP) AS NAMA_PENILAI_INDEP," +
                        "TRIM(TGL_PENILAIAN_PENILAI_INDEP) AS TGL_PENILAIAN_PENILAI_INDEP," +
                        "TRIM(STATUS_PARIPASU) AS STATUS_PARIPASU," +
                        "TRIM(STATUS_KREDIT_JOIN) AS STATUS_KREDIT_JOIN," +
                        "TRIM(DIASURANSIKAN) AS DIASURANSIKAN," +
                        "TRIM(KETERANGAN) AS KETERANGAN," +
                        "TRIM(KODE_KANTOR_CABANG) AS KODE_KANTOR_CABANG," +
                        "TRIM(OPERASI_DATA) AS OPERASI_DATA," +
                        "TRIM(OPEN_DATE) AS OPEN_DATE," +
                        "TRIM(STATUS_DATA) AS STATUS_DATA," +
                        "TRIM(dslik_agunan.PERIODE_ID) AS PERIODE_ID," +
                        "TRIM(PROSENTASE_PARIPASU) AS PROSENTASE_PARIPASU, STATUS_OPERASI_DATA, KODE_JENIS_SEGMENT_FASILITAS,"
                      + "TRIM(PARENT_CODE) AS PARENT_CODE "  
                      + "FROM " + TBL_AGUNAN+" "
                      + "INNER JOIN dslik_agunan_hapus ON dslik_agunan.NO_REKENING=dslik_agunan_hapus.NO_REKENING "
                      + "INNER JOIN dslik_cabang_bank ON "+TBL_AGUNAN+"."+fieldNames[FLD_KODE_KANTOR_CABANG]+"=dslik_cabang_bank.KODE_CABANG";
                    //+ " FROM " + TBL_AGUNAN;
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
                Agunan entAgunan = new Agunan();
                resultToObjectParentCabang(rs, entAgunan);
                lists.add(entAgunan);
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
            String sql = "SELECT DISTINCT agunan.*,"
                    + "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]+","
                    + "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+" "
                    + "FROM "+TBL_AGUNAN+" AS agunan "
                    + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                    + "ON agunan."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID] +" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                    + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "ON agunan."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                    + "AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID];
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
                Agunan entAgunan = new Agunan();
                resultToObjectJoin(rs, entAgunan);
                lists.add(entAgunan);
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
    
    
     public static Vector listSummaryAgunan(String kodeCabang, long Periode) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
           String sql = "SELECT SUM(TAB.TOT_KODE) AS TOT_AGUNAN, SUM(TAB.TOT_NOREK) AS TOT_NOREK, SUM(TAB.TOTAL) AS TOTAL_NILAI_AGN, SUM(TAB.BAKI) AS BAKI_DEBET FROM ( " +
                        "SELECT  " +
                        "COUNT(dslik_agunan.KODE_REGISTER_AGUNAN) AS TOT_KODE,  " +
                        "COUNT(DISTINCT dslik_agunan.NO_REKENING) AS TOT_NOREK, " +
                        "SUM(dslik_agunan.NILAI_AGUNAN_LJK) AS TOTAL, " +
                        "0 AS BAKI " +
                        "FROM dslik_agunan " +
                        " INNER JOIN " +
                        "dslik_kredit  " +
                        "ON dslik_agunan.NO_REKENING = dslik_kredit.NO_REKENING AND dslik_agunan.PERIODE_ID=dslik_kredit.PERIODE_ID " +
                        "WHERE   " +
                        "dslik_agunan.KODE_KANTOR_CABANG = '"+kodeCabang+"' AND dslik_agunan.PERIODE_ID = '"+Periode+"' " +
                        "UNION ALL " +
                        "SELECT  " +
                        "0 AS TOT_KODE,  " +
                        "0 AS TOT_NOREK, " +
                        "0 AS TOTAL, " +
                        "SUM(dslik_kredit.BAKI_DEBET) AS BAKI  " +
                        "FROM dslik_kredit  " +
                        "INNER JOIN  " +
                        "(SELECT DISTINCT dslik_agunan.NO_REKENING FROM dslik_agunan WHERE dslik_agunan.KODE_KANTOR_CABANG = '"+kodeCabang+"' AND dslik_agunan.PERIODE_ID = '"+Periode+"')  " +
                        "AS dslik_agunan  " +
                        "ON dslik_kredit.NO_REKENING = dslik_agunan.NO_REKENING " +
                        "WHERE dslik_kredit.KODE_KANTOR_CABANG = '"+kodeCabang+"' AND dslik_kredit.PERIODE_ID = '"+Periode+"') " +
                        "AS TAB ";
           
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                Agunan entAgunan = new Agunan();
                //resultToObjectJoin(rs, entAgunan);
                entAgunan.setCountRegisterAgunan(rs.getInt("TOT_AGUNAN"));
                entAgunan.setSumBakiPerAgunan(rs.getDouble("BAKI_DEBET"));
                entAgunan.setSumRekeningAgunan(rs.getInt("TOT_NOREK"));
                entAgunan.setSumTotalAgunan(rs.getDouble("TOTAL_NILAI_AGN"));
                lists.add(entAgunan);
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
    

    public static boolean checkOID(long entAgunanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_AGUNAN + " WHERE "
                    + PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID] + " = " + entAgunanId;
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
    
    
    public static boolean checkParipasu(long periodeId, String kodeRegisterAgunan) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT COUNT(AGUNAN_OID) AS TOT FROM " + TBL_AGUNAN + " WHERE "
                    + PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID] + " = " + periodeId
                    + " AND "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+"='"+kodeRegisterAgunan+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                int agunan= rs.getInt("TOT");
                if(agunan>1){
                    result = true;
                }
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
            String sql = "SELECT DISTINCT COUNT(" + PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID] + ") FROM " + TBL_AGUNAN;
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
    
    public static int getCountHapus(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT COUNT(NO_REKENING) FROM dslik_agunan_hapus ";
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
            String sql = "SELECT DISTINCT COUNT(agunan." + PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID] + ") "
                    + "FROM "+TBL_AGUNAN+" AS agunan "
                    + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                    + "ON agunan."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID] +" "
                    + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                    + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                    + "ON agunan."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                    + "AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID];
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
                    Agunan entAgunan = (Agunan) list.get(ls);
                    if (oid == entAgunan.getOID()) {
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
    
    public static long updateStatusData(long oidsegment) throws DBException {
        if(oidsegment==0){
            return 0;
        }
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "UPDATE "+TBL_AGUNAN+" SET STATUS_DATA=0 WHERE "+fieldNames[FLD_AGUNAN_OID]+"='"+oidsegment+"'";
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
        String stSql = "UPDATE "+TBL_AGUNAN+" SET STATUS_OPERASI_DATA='"+statusOperasiData+"' WHERE "+PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID]+"='"+oidDebitur+"'";
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
