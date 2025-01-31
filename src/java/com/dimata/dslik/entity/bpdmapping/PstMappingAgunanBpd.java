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
public class PstMappingAgunanBpd extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_MAPPING_AGUNAN_BPD = "dslik_agunan";
    public static final int FLD_MAPPING_AGUNAN_BPD_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_KODE_REGISTER_AGUNAN = 2;
    public static final int FLD_KODE_JENIS_AGUNAN = 3;
    public static final int FLD_NAMA_PEMILIK_AGUNAN = 4;
    public static final int FLD_BUKTI_KEPEMILIKAN = 5;
    public static final int FLD_ALAMAT_AGUNAN = 6;
    public static final int FLD_KODE_KAB_LOKASI_AGUNAN = 7;
    public static final int FLD_NILAI_AGUNAN_NJOP = 8;
    public static final int FLD_NILAI_AGUNAN_LJK = 9;
    
    public static final int FLD_NO_REKENING=10;
    public static final int FLD_CIF=11;
    public static final int FLD_JENIS_PENGIKATAN=12;
    public static final int FLD_TANGGAL_PENILAIAN_LJK=13;
    public static final int FLD_STATUS_PARIPASU=14;
    public static final int FLD_KODE_KANTOR_CABANG=15;
    public static final int FLD_PERIODE_ID=16;
    public static final int FLD_PROSENTASE_PARIPASU=17;
    public static final int FLD_STATUS_KREDIT_JOIN=18;
    public static final int FLD_KODE_JENIS_SEGMENT_FASILITAS=19;
    
    public static final int FLD_KODE_STATUS_AGUNAN=20;
    public static final int FLD_KODE_LEMBAGA_PEMERINGKAT=21;
    public static final int FLD_TGL_PENILAIAN_INDEPENDENT=22;
    public static final int FLD_DI_ASURANSIKAN=23;
    public static final int FLD_TGL_PENGIKAT=24;
    
    public static String[] fieldNames = {
        "AGUNAN_OID",
        "FLAG_DETAIL",
        "KODE_REGISTER_AGUNAN",
        "KODE_JENIS_AGUNAN",
        "NAMA_PEMILIK_AGUNAN",
        "BUKTI_KEPEMILIKAN",
        "ALAMAT_AGUNAN",
        "KODE_KAB_LOKASI_AGUNAN",
        "NILAI_AGUNAN_NJOP",
        "NILAI_AGUNAN_LJK",
        
        "NO_REKENING",
        "CIF",
        "KODE_JENIS_PENGIKATAN",
        "TGL_PENILAIAN_LJK",
        "STATUS_PARIPASU",
        "KODE_KANTOR_CABANG",
        "PERIODE_ID",
        "PROSENTASE_PARIPASU",
        "STATUS_KREDIT_JOIN",
        "KODE_JENIS_SEGMENT_FASILITAS",
        
        "KODE_STATUS_AGUNAN",
        "KODE_LEMBAGA_PEMERINGKAT",
        "TGL_PENILAIAN_PENILAI_INDEP",
        "DIASURANSIKAN",
        "TGL_PENGIKATAN"
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
        TYPE_FLOAT,
        TYPE_FLOAT,
        
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_LONG,
        TYPE_INT,
        TYPE_STRING,
        TYPE_STRING,
        
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE
    
    };

    public PstMappingAgunanBpd() {
    }

    public PstMappingAgunanBpd(int i) throws DBException {
        super(new PstMappingAgunanBpd());
    }

    public PstMappingAgunanBpd(String sOid) throws DBException {
        super(new PstMappingAgunanBpd(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstMappingAgunanBpd(long lOid) throws DBException {
        super(new PstMappingAgunanBpd(0));
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
        return TBL_MAPPING_AGUNAN_BPD;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstMappingAgunanBpd().getClass().getName();
    }

    public static MappingAgunanBpd fetchExc(long oid) throws DBException {
        try {
            MappingAgunanBpd entMappingAgunanBpd = new MappingAgunanBpd();
            PstMappingAgunanBpd pstMappingAgunanBpd = new PstMappingAgunanBpd(oid);
            entMappingAgunanBpd.setOID(oid);
            entMappingAgunanBpd.setFlagDetail(pstMappingAgunanBpd.getString(FLD_FLAG_DETAIL));
            entMappingAgunanBpd.setKodeRegisterAgunan(pstMappingAgunanBpd.getString(FLD_KODE_REGISTER_AGUNAN));
            entMappingAgunanBpd.setKodeJenisAgunan(pstMappingAgunanBpd.getString(FLD_KODE_JENIS_AGUNAN));
            entMappingAgunanBpd.setNamaPemilikAgunan(pstMappingAgunanBpd.getString(FLD_NAMA_PEMILIK_AGUNAN));
            entMappingAgunanBpd.setBuktiKepemilikan(pstMappingAgunanBpd.getString(FLD_BUKTI_KEPEMILIKAN));
            entMappingAgunanBpd.setAlamatAgunan(pstMappingAgunanBpd.getString(FLD_ALAMAT_AGUNAN));
            entMappingAgunanBpd.setKodeKabLokasiAgunan(pstMappingAgunanBpd.getString(FLD_KODE_KAB_LOKASI_AGUNAN));
            entMappingAgunanBpd.setNilaiAgunanNjop(pstMappingAgunanBpd.getdouble(FLD_NILAI_AGUNAN_NJOP));
            entMappingAgunanBpd.setNilaiAgunanLjk(pstMappingAgunanBpd.getdouble(FLD_NILAI_AGUNAN_LJK));
            
            entMappingAgunanBpd.setNoRekening(pstMappingAgunanBpd.getString(FLD_NO_REKENING));
            entMappingAgunanBpd.setCif(pstMappingAgunanBpd.getString(FLD_CIF));
            entMappingAgunanBpd.setJenisIkatan(pstMappingAgunanBpd.getString(FLD_JENIS_PENGIKATAN));
            entMappingAgunanBpd.setTanggalPenilaian(pstMappingAgunanBpd.getDate(FLD_TANGGAL_PENILAIAN_LJK));
            entMappingAgunanBpd.setStatusParipasu(pstMappingAgunanBpd.getString(FLD_STATUS_PARIPASU));
            entMappingAgunanBpd.setKodeKantorCabang(pstMappingAgunanBpd.getString(FLD_KODE_KANTOR_CABANG));
            entMappingAgunanBpd.setPeriodeId(pstMappingAgunanBpd.getlong(FLD_PERIODE_ID));
            
            entMappingAgunanBpd.setProsentaseParipasu(pstMappingAgunanBpd.getInt(FLD_PROSENTASE_PARIPASU));
            entMappingAgunanBpd.setStatusKreditJoinAccount(pstMappingAgunanBpd.getString(FLD_STATUS_KREDIT_JOIN));
            entMappingAgunanBpd.setKodeJenisSegementFasilitas(pstMappingAgunanBpd.getString(FLD_KODE_JENIS_SEGMENT_FASILITAS));
            
            //baru
            entMappingAgunanBpd.setKodeStatusAgunan(pstMappingAgunanBpd.getString(FLD_KODE_STATUS_AGUNAN));
            entMappingAgunanBpd.setKodeLembagaPemeringkat(pstMappingAgunanBpd.getString(FLD_KODE_LEMBAGA_PEMERINGKAT));
            entMappingAgunanBpd.setTglPenilaianIndependent(pstMappingAgunanBpd.getDate(FLD_TGL_PENILAIAN_INDEPENDENT));
            
            entMappingAgunanBpd.setDiAsuransikan(pstMappingAgunanBpd.getString(FLD_DI_ASURANSIKAN));
            entMappingAgunanBpd.setTglPengikat(pstMappingAgunanBpd.getDate(FLD_TGL_PENGIKAT));
            
            return entMappingAgunanBpd;
            
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingAgunanBpd(0), DBException.UNKNOWN);
        }
    }
    
    public static MappingAgunanBpd fetchExc(long oid, MappingAgunanBpd entMappingAgunanBpd ) throws DBException {
        try {
            //MappingAgunanBpd entMappingAgunanBpd = new MappingAgunanBpd();
            PstMappingAgunanBpd pstMappingAgunanBpd = new PstMappingAgunanBpd(oid);
            entMappingAgunanBpd.setOID(oid);
            entMappingAgunanBpd.setFlagDetail(pstMappingAgunanBpd.getString(FLD_FLAG_DETAIL));
            entMappingAgunanBpd.setKodeRegisterAgunan(pstMappingAgunanBpd.getString(FLD_KODE_REGISTER_AGUNAN));
            entMappingAgunanBpd.setKodeJenisAgunan(pstMappingAgunanBpd.getString(FLD_KODE_JENIS_AGUNAN));
            entMappingAgunanBpd.setNamaPemilikAgunan(pstMappingAgunanBpd.getString(FLD_NAMA_PEMILIK_AGUNAN));
            entMappingAgunanBpd.setBuktiKepemilikan(pstMappingAgunanBpd.getString(FLD_BUKTI_KEPEMILIKAN));
            entMappingAgunanBpd.setAlamatAgunan(pstMappingAgunanBpd.getString(FLD_ALAMAT_AGUNAN));
            entMappingAgunanBpd.setKodeKabLokasiAgunan(pstMappingAgunanBpd.getString(FLD_KODE_KAB_LOKASI_AGUNAN));
            entMappingAgunanBpd.setNilaiAgunanNjop(pstMappingAgunanBpd.getdouble(FLD_NILAI_AGUNAN_NJOP));
            entMappingAgunanBpd.setNilaiAgunanLjk(pstMappingAgunanBpd.getdouble(FLD_NILAI_AGUNAN_LJK));
            
            entMappingAgunanBpd.setNoRekening(pstMappingAgunanBpd.getString(FLD_NO_REKENING));
            entMappingAgunanBpd.setCif(pstMappingAgunanBpd.getString(FLD_CIF));
            entMappingAgunanBpd.setJenisIkatan(pstMappingAgunanBpd.getString(FLD_JENIS_PENGIKATAN));
            entMappingAgunanBpd.setTanggalPenilaian(pstMappingAgunanBpd.getDate(FLD_TANGGAL_PENILAIAN_LJK));
            entMappingAgunanBpd.setStatusParipasu(pstMappingAgunanBpd.getString(FLD_STATUS_PARIPASU));
            entMappingAgunanBpd.setKodeKantorCabang(pstMappingAgunanBpd.getString(FLD_KODE_KANTOR_CABANG));
            entMappingAgunanBpd.setPeriodeId(pstMappingAgunanBpd.getlong(FLD_PERIODE_ID));
            
            entMappingAgunanBpd.setProsentaseParipasu(pstMappingAgunanBpd.getInt(FLD_PROSENTASE_PARIPASU));
            entMappingAgunanBpd.setStatusKreditJoinAccount(pstMappingAgunanBpd.getString(FLD_STATUS_KREDIT_JOIN));
            entMappingAgunanBpd.setKodeJenisSegementFasilitas(pstMappingAgunanBpd.getString(FLD_KODE_JENIS_SEGMENT_FASILITAS));
            
            //baru
            entMappingAgunanBpd.setKodeStatusAgunan(pstMappingAgunanBpd.getString(FLD_KODE_STATUS_AGUNAN));
            entMappingAgunanBpd.setKodeLembagaPemeringkat(pstMappingAgunanBpd.getString(FLD_KODE_LEMBAGA_PEMERINGKAT));
            entMappingAgunanBpd.setTglPenilaianIndependent(pstMappingAgunanBpd.getDate(FLD_TGL_PENILAIAN_INDEPENDENT));
            
            entMappingAgunanBpd.setDiAsuransikan(pstMappingAgunanBpd.getString(FLD_DI_ASURANSIKAN));
            entMappingAgunanBpd.setTglPengikat(pstMappingAgunanBpd.getDate(FLD_TGL_PENGIKAT));
            
            return entMappingAgunanBpd;
            
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingAgunanBpd(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        MappingAgunanBpd entMappingAgunanBpd = fetchExc(entity.getOID());
        entity = (Entity) entMappingAgunanBpd;
        return entMappingAgunanBpd.getOID();
    }

    public static synchronized long updateExc(MappingAgunanBpd entMappingAgunanBpd) throws DBException {
        try {
            if (entMappingAgunanBpd.getOID() != 0) {
                PstMappingAgunanBpd pstMappingAgunanBpd = new PstMappingAgunanBpd(entMappingAgunanBpd.getOID());
                pstMappingAgunanBpd.setString(FLD_FLAG_DETAIL, entMappingAgunanBpd.getFlagDetail());
                pstMappingAgunanBpd.setString(FLD_KODE_REGISTER_AGUNAN, entMappingAgunanBpd.getKodeRegisterAgunan());
                pstMappingAgunanBpd.setString(FLD_KODE_JENIS_AGUNAN, entMappingAgunanBpd.getKodeJenisAgunan());
                pstMappingAgunanBpd.setString(FLD_NAMA_PEMILIK_AGUNAN, entMappingAgunanBpd.getNamaPemilikAgunan());
                pstMappingAgunanBpd.setString(FLD_BUKTI_KEPEMILIKAN, entMappingAgunanBpd.getBuktiKepemilikan());
                pstMappingAgunanBpd.setString(FLD_ALAMAT_AGUNAN, entMappingAgunanBpd.getAlamatAgunan());
                pstMappingAgunanBpd.setString(FLD_KODE_KAB_LOKASI_AGUNAN, entMappingAgunanBpd.getKodeKabLokasiAgunan());
                pstMappingAgunanBpd.setDouble(FLD_NILAI_AGUNAN_NJOP, entMappingAgunanBpd.getNilaiAgunanNjop());
                pstMappingAgunanBpd.setDouble(FLD_NILAI_AGUNAN_LJK, entMappingAgunanBpd.getNilaiAgunanLjk());
                
                pstMappingAgunanBpd.setString(FLD_NO_REKENING, entMappingAgunanBpd.getNoRekening());
                pstMappingAgunanBpd.setString(FLD_CIF, entMappingAgunanBpd.getCif());
                pstMappingAgunanBpd.setString(FLD_JENIS_PENGIKATAN, entMappingAgunanBpd.getJenisIkatan());
                pstMappingAgunanBpd.setDate(FLD_TANGGAL_PENILAIAN_LJK, entMappingAgunanBpd.getTanggalPenilaian());
                pstMappingAgunanBpd.setString(FLD_STATUS_PARIPASU, entMappingAgunanBpd.getStatusParipasu());
                pstMappingAgunanBpd.setString(FLD_KODE_KANTOR_CABANG, entMappingAgunanBpd.getKodeKantorCabang());
                pstMappingAgunanBpd.setLong(FLD_PERIODE_ID, entMappingAgunanBpd.getPeriodeId());
                
                pstMappingAgunanBpd.setInt(FLD_PROSENTASE_PARIPASU, entMappingAgunanBpd.getProsentaseParipasu());
                pstMappingAgunanBpd.setString(FLD_STATUS_KREDIT_JOIN, entMappingAgunanBpd.getStatusKreditJoinAccount());
                pstMappingAgunanBpd.setString(FLD_KODE_JENIS_SEGMENT_FASILITAS, entMappingAgunanBpd.getKodeJenisSegementFasilitas());
                
                
                pstMappingAgunanBpd.setString(FLD_KODE_STATUS_AGUNAN,  entMappingAgunanBpd.getKodeStatusAgunan());
                pstMappingAgunanBpd.setString(FLD_KODE_LEMBAGA_PEMERINGKAT,  entMappingAgunanBpd.getKodeLembagaPemeringkat());
                pstMappingAgunanBpd.setDate(FLD_TGL_PENILAIAN_INDEPENDENT, entMappingAgunanBpd.getTglPenilaianIndependent());
                
                pstMappingAgunanBpd.setString(FLD_DI_ASURANSIKAN,  entMappingAgunanBpd.getDiAsuransikan());
                pstMappingAgunanBpd.setDate(FLD_TGL_PENGIKAT,  entMappingAgunanBpd.getTglPengikat());
                pstMappingAgunanBpd.update();
                return entMappingAgunanBpd.getOID();
                
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingAgunanBpd(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((MappingAgunanBpd) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMappingAgunanBpd pstMappingAgunanBpd = new PstMappingAgunanBpd(oid);
            pstMappingAgunanBpd.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingAgunanBpd(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MappingAgunanBpd entMappingAgunanBpd) throws DBException {
        try {
            PstMappingAgunanBpd pstMappingAgunanBpd = new PstMappingAgunanBpd(0);
            pstMappingAgunanBpd.setString(FLD_FLAG_DETAIL, entMappingAgunanBpd.getFlagDetail());
            pstMappingAgunanBpd.setString(FLD_KODE_REGISTER_AGUNAN, entMappingAgunanBpd.getKodeRegisterAgunan());
            pstMappingAgunanBpd.setString(FLD_KODE_JENIS_AGUNAN, entMappingAgunanBpd.getKodeJenisAgunan());
            pstMappingAgunanBpd.setString(FLD_NAMA_PEMILIK_AGUNAN, entMappingAgunanBpd.getNamaPemilikAgunan());
            pstMappingAgunanBpd.setString(FLD_BUKTI_KEPEMILIKAN, entMappingAgunanBpd.getBuktiKepemilikan());
            pstMappingAgunanBpd.setString(FLD_ALAMAT_AGUNAN, entMappingAgunanBpd.getAlamatAgunan());
            pstMappingAgunanBpd.setString(FLD_KODE_KAB_LOKASI_AGUNAN, entMappingAgunanBpd.getKodeKabLokasiAgunan());
            pstMappingAgunanBpd.setDouble(FLD_NILAI_AGUNAN_NJOP, entMappingAgunanBpd.getNilaiAgunanNjop());
            pstMappingAgunanBpd.setDouble(FLD_NILAI_AGUNAN_LJK, entMappingAgunanBpd.getNilaiAgunanLjk());
            
            pstMappingAgunanBpd.setString(FLD_NO_REKENING, entMappingAgunanBpd.getNoRekening());
            pstMappingAgunanBpd.setString(FLD_CIF, entMappingAgunanBpd.getCif());
            pstMappingAgunanBpd.setString(FLD_JENIS_PENGIKATAN, entMappingAgunanBpd.getJenisIkatan());
            pstMappingAgunanBpd.setDate(FLD_TANGGAL_PENILAIAN_LJK, entMappingAgunanBpd.getTanggalPenilaian());
            pstMappingAgunanBpd.setString(FLD_STATUS_PARIPASU, entMappingAgunanBpd.getStatusParipasu());
            pstMappingAgunanBpd.setString(FLD_KODE_KANTOR_CABANG, entMappingAgunanBpd.getKodeKantorCabang());
            pstMappingAgunanBpd.setLong(FLD_PERIODE_ID, entMappingAgunanBpd.getPeriodeId());
                
            pstMappingAgunanBpd.setInt(FLD_PROSENTASE_PARIPASU,entMappingAgunanBpd.getProsentaseParipasu());
            pstMappingAgunanBpd.setString(FLD_STATUS_KREDIT_JOIN, entMappingAgunanBpd.getStatusKreditJoinAccount());
            pstMappingAgunanBpd.setString(FLD_KODE_JENIS_SEGMENT_FASILITAS, entMappingAgunanBpd.getKodeJenisSegementFasilitas());
            
            
            pstMappingAgunanBpd.setString(FLD_KODE_STATUS_AGUNAN,  entMappingAgunanBpd.getKodeStatusAgunan());
            pstMappingAgunanBpd.setString(FLD_KODE_LEMBAGA_PEMERINGKAT,  entMappingAgunanBpd.getKodeLembagaPemeringkat());
            pstMappingAgunanBpd.setDate(FLD_TGL_PENILAIAN_INDEPENDENT, entMappingAgunanBpd.getTglPenilaianIndependent());
            
            pstMappingAgunanBpd.setString(FLD_DI_ASURANSIKAN,  entMappingAgunanBpd.getDiAsuransikan());
            pstMappingAgunanBpd.setDate(FLD_TGL_PENGIKAT, entMappingAgunanBpd.getTglPengikat());
            pstMappingAgunanBpd.insert();
            entMappingAgunanBpd.setOID(pstMappingAgunanBpd.getlong(FLD_MAPPING_AGUNAN_BPD_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingAgunanBpd(0), DBException.UNKNOWN);
        }
        return entMappingAgunanBpd.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((MappingAgunanBpd) entity);
    }

    public static void resultToObject(ResultSet rs, MappingAgunanBpd entMappingAgunanBpd) {
        try {
            entMappingAgunanBpd.setOID(rs.getLong(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_MAPPING_AGUNAN_BPD_OID]));
            entMappingAgunanBpd.setFlagDetail(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_FLAG_DETAIL]));
            entMappingAgunanBpd.setKodeRegisterAgunan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_KODE_REGISTER_AGUNAN]));
            entMappingAgunanBpd.setKodeJenisAgunan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_KODE_JENIS_AGUNAN]));
            entMappingAgunanBpd.setNamaPemilikAgunan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_NAMA_PEMILIK_AGUNAN]));
            entMappingAgunanBpd.setBuktiKepemilikan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_BUKTI_KEPEMILIKAN]));
            entMappingAgunanBpd.setAlamatAgunan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_ALAMAT_AGUNAN]));
            entMappingAgunanBpd.setKodeKabLokasiAgunan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_KODE_KAB_LOKASI_AGUNAN]));
            entMappingAgunanBpd.setNilaiAgunanNjop(rs.getDouble(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_NILAI_AGUNAN_NJOP]));
            entMappingAgunanBpd.setNilaiAgunanLjk(rs.getDouble(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_NILAI_AGUNAN_LJK]));
            
            entMappingAgunanBpd.setNoRekening(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_NO_REKENING]));
            entMappingAgunanBpd.setCif(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_CIF]));
            entMappingAgunanBpd.setJenisIkatan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_JENIS_PENGIKATAN]));
            entMappingAgunanBpd.setTanggalPenilaian(rs.getDate(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_TANGGAL_PENILAIAN_LJK]));
            entMappingAgunanBpd.setStatusParipasu(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_STATUS_PARIPASU]));
            entMappingAgunanBpd.setKodeKantorCabang(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_KODE_KANTOR_CABANG]));
            entMappingAgunanBpd.setPeriodeId(rs.getLong(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_PERIODE_ID]));
            
            entMappingAgunanBpd.setProsentaseParipasu(rs.getInt(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_PROSENTASE_PARIPASU]));
            entMappingAgunanBpd.setStatusKreditJoinAccount(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_STATUS_KREDIT_JOIN]));
            entMappingAgunanBpd.setKodeJenisSegementFasilitas(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_KODE_JENIS_SEGMENT_FASILITAS]));
            
            entMappingAgunanBpd.setKodeStatusAgunan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_KODE_STATUS_AGUNAN]));
            entMappingAgunanBpd.setKodeLembagaPemeringkat(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_KODE_LEMBAGA_PEMERINGKAT]));
            entMappingAgunanBpd.setTglPenilaianIndependent(rs.getDate(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_TGL_PENILAIAN_INDEPENDENT]));
            entMappingAgunanBpd.setDiAsuransikan(rs.getString(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_DI_ASURANSIKAN]));
            entMappingAgunanBpd.setTglPengikat(rs.getDate(PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_TGL_PENGIKAT]));
            
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
            String sql = "SELECT * FROM " + TBL_MAPPING_AGUNAN_BPD;
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
                MappingAgunanBpd entMappingAgunanBpd = new MappingAgunanBpd();
                resultToObject(rs, entMappingAgunanBpd);
                lists.add(entMappingAgunanBpd);
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

    public static boolean checkOID(long entMappingAgunanBpdId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_AGUNAN_BPD + " WHERE "
                    + PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_MAPPING_AGUNAN_BPD_OID] + " = " + entMappingAgunanBpdId;
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
            String sql = "SELECT COUNT(" + PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_MAPPING_AGUNAN_BPD_OID] + ") FROM " + TBL_MAPPING_AGUNAN_BPD;
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
                    MappingAgunanBpd entMappingAgunanBpd = (MappingAgunanBpd) list.get(ls);
                    if (oid == entMappingAgunanBpd.getOID()) {
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

    
    public static long checkDataAgunan(long periodeId, String cif, String noRekening) {
        DBResultSet dbrs = null;
        long result = 0;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_AGUNAN_BPD + " WHERE "
                    + PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_PERIODE_ID] + " = '" + periodeId+"'"
                    + " AND "+PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_KODE_REGISTER_AGUNAN]+"='"+cif+"'"
                    + " AND "+PstMappingAgunanBpd.fieldNames[PstMappingAgunanBpd.FLD_NO_REKENING]+"='"+noRekening+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getLong(fieldNames[FLD_MAPPING_AGUNAN_BPD_OID]);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
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
    
     public static int deleteSegmentAnggunanPerPeriode(long periodeId, String cif) {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "DELETE FROM " + TBL_MAPPING_AGUNAN_BPD
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
