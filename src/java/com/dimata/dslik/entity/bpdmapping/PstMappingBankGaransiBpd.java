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
public class PstMappingBankGaransiBpd extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_MAPPING_BANK_GARANSI_BPD = "dslik_bank_garansi";
    public static final int FLD_MAPPING_BANK_GARANSI_BPD_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_REKENING = 2;
    public static final int FLD_KODE_JENIS_GARANSI = 3;
    public static final int FLD_KODE_TUJUAN_GARANSI = 4;
    public static final int FLD_TGL_DITERBITKAN = 5;
    public static final int FLD_TGL_JATUH_TEMPO = 6;
    public static final int FLD_NO_AKAD_AWAL = 7;
    public static final int FLD_TGL_AKAD_AWAL = 8;
    public static final int FLD_NO_AKAD_AKHIR = 9;
    public static final int FLD_TGL_AKAD_AKHIR = 10;
    public static final int FLD_NAMA_YG_DIJAMIN = 11;
    public static final int FLD_PLAFON = 12;
    public static final int FLD_NOMINAL = 13;
    public static final int FLD_SETORAN_JAMINAN = 14;
    public static final int FLD_KODE_KOLEKTIBILITAS = 15;
    
    public static final int FLD_TGL_WAN_PRESTASI = 16;
    public static final int FLD_KODE_KONDISI = 17;
    public static final int FLD_TGL_KONDISI = 18;
    public static final int FLD_KETERANGAN = 19;
    
    public static final int FLD_KODE_KANTOR_CABANG = 20;
    public static final int FLD_CIF = 21;
    public static final int FLD_PERIODE_ID=22;
    public static final int FLD_KODE_VALUTA=23;
    
    public static String[] fieldNames = {
        "BANK_GARANSI_OID",
        "FLAG_DETAIL",
        "NO_REKENING",
        "KODE_JENIS_GARANSI",
        "KODE_TUJUAN_GARANSI",
        "TGL_DITERBITKAN",
        "TGL_JATUH_TEMPO",
        "NO_AKAD_AWAL",
        "TGL_AKAD_AWAL",
        "NO_AKAD_AKHIR",
        "TGL_AKAD_AKHIR",
        "NAMA_YG_DIJAMIN",
        "PLAFON",
        "NOMINAL",
        "SETORAN_JAMINAN",
        "KODE_KOLEKTIBILITAS",
        
        "TGL_WAN_PRESTASI",
        "KODE_KONDISI",
        "TGL_KONDISI",
        "KETERANGAN",
        
        "KODE_KANTOR_CABANG",
        "CIF",
        "PERIODE_ID",
        "KODE_VALUTA"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        
        TYPE_STRING,
        TYPE_STRING,
        TYPE_LONG,
        TYPE_STRING
    };

    public PstMappingBankGaransiBpd() {
    }

    public PstMappingBankGaransiBpd(int i) throws DBException {
        super(new PstMappingBankGaransiBpd());
    }

    public PstMappingBankGaransiBpd(String sOid) throws DBException {
        super(new PstMappingBankGaransiBpd(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstMappingBankGaransiBpd(long lOid) throws DBException {
        super(new PstMappingBankGaransiBpd(0));
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
        return TBL_MAPPING_BANK_GARANSI_BPD;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstMappingBankGaransiBpd().getClass().getName();
    }

    public static MappingBankGaransiBpd fetchExc(long oid) throws DBException {
        try {
            MappingBankGaransiBpd entMappingBankGaransiBpd = new MappingBankGaransiBpd();
            PstMappingBankGaransiBpd pstMappingBankGaransiBpd = new PstMappingBankGaransiBpd(oid);
            entMappingBankGaransiBpd.setOID(oid);
            entMappingBankGaransiBpd.setFlagDetail(pstMappingBankGaransiBpd.getString(FLD_FLAG_DETAIL));
            entMappingBankGaransiBpd.setNoRekening(pstMappingBankGaransiBpd.getString(FLD_NO_REKENING));
            entMappingBankGaransiBpd.setKodeJenisGaransi(pstMappingBankGaransiBpd.getString(FLD_KODE_JENIS_GARANSI));
            entMappingBankGaransiBpd.setKodeTujuanGaransi(pstMappingBankGaransiBpd.getString(FLD_KODE_TUJUAN_GARANSI));
            entMappingBankGaransiBpd.setTglDiterbitkan(pstMappingBankGaransiBpd.getDate(FLD_TGL_DITERBITKAN));
            entMappingBankGaransiBpd.setTglJatuhTempo(pstMappingBankGaransiBpd.getDate(FLD_TGL_JATUH_TEMPO));
            entMappingBankGaransiBpd.setNoAkadAwal(pstMappingBankGaransiBpd.getString(FLD_NO_AKAD_AWAL));
            entMappingBankGaransiBpd.setTglAkadAwal(pstMappingBankGaransiBpd.getDate(FLD_TGL_AKAD_AWAL));
            entMappingBankGaransiBpd.setNoAkadAkhir(pstMappingBankGaransiBpd.getString(FLD_NO_AKAD_AKHIR));
            entMappingBankGaransiBpd.setTglAkadAkhir(pstMappingBankGaransiBpd.getDate(FLD_TGL_AKAD_AKHIR));
            entMappingBankGaransiBpd.setNamaYgDijamin(pstMappingBankGaransiBpd.getString(FLD_NAMA_YG_DIJAMIN));
            entMappingBankGaransiBpd.setPlafon(pstMappingBankGaransiBpd.getdouble(FLD_PLAFON));
            entMappingBankGaransiBpd.setNominal(pstMappingBankGaransiBpd.getdouble(FLD_NOMINAL));
            entMappingBankGaransiBpd.setSetoranJaminan(pstMappingBankGaransiBpd.getdouble(FLD_SETORAN_JAMINAN));
            entMappingBankGaransiBpd.setKodeKolektibilitas(pstMappingBankGaransiBpd.getString(FLD_KODE_KOLEKTIBILITAS));
            
            entMappingBankGaransiBpd.setTglWanPrestasi(pstMappingBankGaransiBpd.getDate(FLD_TGL_WAN_PRESTASI));
            entMappingBankGaransiBpd.setKodeKondisi(pstMappingBankGaransiBpd.getString(FLD_KODE_KONDISI));
            entMappingBankGaransiBpd.setTglKondisi(pstMappingBankGaransiBpd.getDate(FLD_TGL_KONDISI));
            entMappingBankGaransiBpd.setKeterangan(pstMappingBankGaransiBpd.getString(FLD_KETERANGAN));
            
            entMappingBankGaransiBpd.setKodeKantorCabang(pstMappingBankGaransiBpd.getString(FLD_KODE_KANTOR_CABANG));
            entMappingBankGaransiBpd.setCif(pstMappingBankGaransiBpd.getString(FLD_CIF));
            entMappingBankGaransiBpd.setPeriodeId(pstMappingBankGaransiBpd.getlong(FLD_PERIODE_ID));
            
            entMappingBankGaransiBpd.setKodeValuta(pstMappingBankGaransiBpd.getString(FLD_KODE_VALUTA));
            
            return entMappingBankGaransiBpd;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingBankGaransiBpd(0), DBException.UNKNOWN);
        }
    }
    
    public static MappingBankGaransiBpd fetchExc(long oid, MappingBankGaransiBpd entMappingBankGaransiBpd) throws DBException {
        try {
            //MappingBankGaransiBpd entMappingBankGaransiBpd = new MappingBankGaransiBpd();
            PstMappingBankGaransiBpd pstMappingBankGaransiBpd = new PstMappingBankGaransiBpd(oid);
            entMappingBankGaransiBpd.setOID(oid);
            entMappingBankGaransiBpd.setFlagDetail(pstMappingBankGaransiBpd.getString(FLD_FLAG_DETAIL));
            entMappingBankGaransiBpd.setNoRekening(pstMappingBankGaransiBpd.getString(FLD_NO_REKENING));
            entMappingBankGaransiBpd.setKodeJenisGaransi(pstMappingBankGaransiBpd.getString(FLD_KODE_JENIS_GARANSI));
            entMappingBankGaransiBpd.setKodeTujuanGaransi(pstMappingBankGaransiBpd.getString(FLD_KODE_TUJUAN_GARANSI));
            entMappingBankGaransiBpd.setTglDiterbitkan(pstMappingBankGaransiBpd.getDate(FLD_TGL_DITERBITKAN));
            entMappingBankGaransiBpd.setTglJatuhTempo(pstMappingBankGaransiBpd.getDate(FLD_TGL_JATUH_TEMPO));
            entMappingBankGaransiBpd.setNoAkadAwal(pstMappingBankGaransiBpd.getString(FLD_NO_AKAD_AWAL));
            entMappingBankGaransiBpd.setTglAkadAwal(pstMappingBankGaransiBpd.getDate(FLD_TGL_AKAD_AWAL));
            entMappingBankGaransiBpd.setNoAkadAkhir(pstMappingBankGaransiBpd.getString(FLD_NO_AKAD_AKHIR));
            entMappingBankGaransiBpd.setTglAkadAkhir(pstMappingBankGaransiBpd.getDate(FLD_TGL_AKAD_AKHIR));
            entMappingBankGaransiBpd.setNamaYgDijamin(pstMappingBankGaransiBpd.getString(FLD_NAMA_YG_DIJAMIN));
            entMappingBankGaransiBpd.setPlafon(pstMappingBankGaransiBpd.getdouble(FLD_PLAFON));
            entMappingBankGaransiBpd.setNominal(pstMappingBankGaransiBpd.getdouble(FLD_NOMINAL));
            entMappingBankGaransiBpd.setSetoranJaminan(pstMappingBankGaransiBpd.getdouble(FLD_SETORAN_JAMINAN));
            entMappingBankGaransiBpd.setKodeKolektibilitas(pstMappingBankGaransiBpd.getString(FLD_KODE_KOLEKTIBILITAS));
            
            entMappingBankGaransiBpd.setTglWanPrestasi(pstMappingBankGaransiBpd.getDate(FLD_TGL_WAN_PRESTASI));
            entMappingBankGaransiBpd.setKodeKondisi(pstMappingBankGaransiBpd.getString(FLD_KODE_KONDISI));
            entMappingBankGaransiBpd.setTglKondisi(pstMappingBankGaransiBpd.getDate(FLD_TGL_KONDISI));
            entMappingBankGaransiBpd.setKeterangan(pstMappingBankGaransiBpd.getString(FLD_KETERANGAN));
            
            entMappingBankGaransiBpd.setKodeKantorCabang(pstMappingBankGaransiBpd.getString(FLD_KODE_KANTOR_CABANG));
            entMappingBankGaransiBpd.setCif(pstMappingBankGaransiBpd.getString(FLD_CIF));
            entMappingBankGaransiBpd.setPeriodeId(pstMappingBankGaransiBpd.getlong(FLD_PERIODE_ID));
            
            entMappingBankGaransiBpd.setKodeValuta(pstMappingBankGaransiBpd.getString(FLD_KODE_VALUTA));
            
            return entMappingBankGaransiBpd;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingBankGaransiBpd(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        MappingBankGaransiBpd entMappingBankGaransiBpd = fetchExc(entity.getOID());
        entity = (Entity) entMappingBankGaransiBpd;
        return entMappingBankGaransiBpd.getOID();
    }

    public static synchronized long updateExc(MappingBankGaransiBpd entMappingBankGaransiBpd) throws DBException {
        try {
            if (entMappingBankGaransiBpd.getOID() != 0) {
                PstMappingBankGaransiBpd pstMappingBankGaransiBpd = new PstMappingBankGaransiBpd(entMappingBankGaransiBpd.getOID());
                pstMappingBankGaransiBpd.setString(FLD_FLAG_DETAIL, entMappingBankGaransiBpd.getFlagDetail());
                pstMappingBankGaransiBpd.setString(FLD_NO_REKENING, entMappingBankGaransiBpd.getNoRekening());
                pstMappingBankGaransiBpd.setString(FLD_KODE_JENIS_GARANSI, entMappingBankGaransiBpd.getKodeJenisGaransi());
                pstMappingBankGaransiBpd.setString(FLD_KODE_TUJUAN_GARANSI, entMappingBankGaransiBpd.getKodeTujuanGaransi());
                pstMappingBankGaransiBpd.setDate(FLD_TGL_DITERBITKAN, entMappingBankGaransiBpd.getTglDiterbitkan());
                pstMappingBankGaransiBpd.setDate(FLD_TGL_JATUH_TEMPO, entMappingBankGaransiBpd.getTglJatuhTempo());
                pstMappingBankGaransiBpd.setString(FLD_NO_AKAD_AWAL, entMappingBankGaransiBpd.getNoAkadAwal());
                pstMappingBankGaransiBpd.setDate(FLD_TGL_AKAD_AWAL, entMappingBankGaransiBpd.getTglAkadAwal());
                pstMappingBankGaransiBpd.setString(FLD_NO_AKAD_AKHIR, entMappingBankGaransiBpd.getNoAkadAkhir());
                pstMappingBankGaransiBpd.setDate(FLD_TGL_AKAD_AKHIR, entMappingBankGaransiBpd.getTglAkadAkhir());
                pstMappingBankGaransiBpd.setString(FLD_NAMA_YG_DIJAMIN, entMappingBankGaransiBpd.getNamaYgDijamin());
                pstMappingBankGaransiBpd.setDouble(FLD_PLAFON, entMappingBankGaransiBpd.getPlafon());
                pstMappingBankGaransiBpd.setDouble(FLD_NOMINAL, entMappingBankGaransiBpd.getNominal());
                pstMappingBankGaransiBpd.setDouble(FLD_SETORAN_JAMINAN, entMappingBankGaransiBpd.getSetoranJaminan());
                pstMappingBankGaransiBpd.setString(FLD_KODE_KOLEKTIBILITAS, entMappingBankGaransiBpd.getKodeKolektibilitas());
                pstMappingBankGaransiBpd.setDate(FLD_TGL_WAN_PRESTASI, entMappingBankGaransiBpd.getTglWanPrestasi());
                pstMappingBankGaransiBpd.setString(FLD_KODE_KONDISI, entMappingBankGaransiBpd.getKodeKondisi());
                pstMappingBankGaransiBpd.setDate(FLD_TGL_KONDISI, entMappingBankGaransiBpd.getTglKondisi());
                pstMappingBankGaransiBpd.setString(FLD_KETERANGAN, entMappingBankGaransiBpd.getKeterangan());
                pstMappingBankGaransiBpd.setString(FLD_KODE_KANTOR_CABANG, entMappingBankGaransiBpd.getKodeKantorCabang());
                pstMappingBankGaransiBpd.setString(FLD_CIF,  entMappingBankGaransiBpd.getCif());
                pstMappingBankGaransiBpd.setLong(FLD_PERIODE_ID, entMappingBankGaransiBpd.getPeriodeId());
                
                pstMappingBankGaransiBpd.setString(FLD_KODE_VALUTA, entMappingBankGaransiBpd.getKodeValuta());
                
                pstMappingBankGaransiBpd.update();
                return entMappingBankGaransiBpd.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingBankGaransiBpd(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((MappingBankGaransiBpd) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMappingBankGaransiBpd pstMappingBankGaransiBpd = new PstMappingBankGaransiBpd(oid);
            pstMappingBankGaransiBpd.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingBankGaransiBpd(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MappingBankGaransiBpd entMappingBankGaransiBpd) throws DBException {
        try {
            PstMappingBankGaransiBpd pstMappingBankGaransiBpd = new PstMappingBankGaransiBpd(0);
            pstMappingBankGaransiBpd.setString(FLD_FLAG_DETAIL, entMappingBankGaransiBpd.getFlagDetail());
            pstMappingBankGaransiBpd.setString(FLD_NO_REKENING, entMappingBankGaransiBpd.getNoRekening());
            pstMappingBankGaransiBpd.setString(FLD_KODE_JENIS_GARANSI, entMappingBankGaransiBpd.getKodeJenisGaransi());
            pstMappingBankGaransiBpd.setString(FLD_KODE_TUJUAN_GARANSI, entMappingBankGaransiBpd.getKodeTujuanGaransi());
            pstMappingBankGaransiBpd.setDate(FLD_TGL_DITERBITKAN, entMappingBankGaransiBpd.getTglDiterbitkan());
            pstMappingBankGaransiBpd.setDate(FLD_TGL_JATUH_TEMPO, entMappingBankGaransiBpd.getTglJatuhTempo());
            pstMappingBankGaransiBpd.setString(FLD_NO_AKAD_AWAL, entMappingBankGaransiBpd.getNoAkadAwal());
            pstMappingBankGaransiBpd.setDate(FLD_TGL_AKAD_AWAL, entMappingBankGaransiBpd.getTglAkadAwal());
            pstMappingBankGaransiBpd.setString(FLD_NO_AKAD_AKHIR, entMappingBankGaransiBpd.getNoAkadAkhir());
            pstMappingBankGaransiBpd.setDate(FLD_TGL_AKAD_AKHIR, entMappingBankGaransiBpd.getTglAkadAkhir());
            pstMappingBankGaransiBpd.setString(FLD_NAMA_YG_DIJAMIN, entMappingBankGaransiBpd.getNamaYgDijamin());
            pstMappingBankGaransiBpd.setDouble(FLD_PLAFON, entMappingBankGaransiBpd.getPlafon());
            pstMappingBankGaransiBpd.setDouble(FLD_NOMINAL, entMappingBankGaransiBpd.getNominal());
            pstMappingBankGaransiBpd.setDouble(FLD_SETORAN_JAMINAN, entMappingBankGaransiBpd.getSetoranJaminan());
            pstMappingBankGaransiBpd.setString(FLD_KODE_KOLEKTIBILITAS, entMappingBankGaransiBpd.getKodeKolektibilitas());
            pstMappingBankGaransiBpd.setDate(FLD_TGL_WAN_PRESTASI, entMappingBankGaransiBpd.getTglWanPrestasi());
            pstMappingBankGaransiBpd.setString(FLD_KODE_KONDISI, entMappingBankGaransiBpd.getKodeKondisi());
            pstMappingBankGaransiBpd.setDate(FLD_TGL_KONDISI, entMappingBankGaransiBpd.getTglKondisi());
            pstMappingBankGaransiBpd.setString(FLD_KETERANGAN, entMappingBankGaransiBpd.getKeterangan());
            pstMappingBankGaransiBpd.setString(FLD_KODE_KANTOR_CABANG, entMappingBankGaransiBpd.getKodeKantorCabang());
            pstMappingBankGaransiBpd.setString(FLD_CIF, entMappingBankGaransiBpd.getCif());
            pstMappingBankGaransiBpd.setLong(FLD_PERIODE_ID, entMappingBankGaransiBpd.getPeriodeId());
            pstMappingBankGaransiBpd.setString(FLD_KODE_VALUTA, entMappingBankGaransiBpd.getKodeValuta());
            
            pstMappingBankGaransiBpd.insert();
            entMappingBankGaransiBpd.setOID(pstMappingBankGaransiBpd.getlong(FLD_MAPPING_BANK_GARANSI_BPD_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMappingBankGaransiBpd(0), DBException.UNKNOWN);
        }
        return entMappingBankGaransiBpd.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((MappingBankGaransiBpd) entity);
    }

    public static void resultToObject(ResultSet rs, MappingBankGaransiBpd entMappingBankGaransiBpd) {
        try {
            entMappingBankGaransiBpd.setOID(rs.getLong(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_MAPPING_BANK_GARANSI_BPD_OID]));
            entMappingBankGaransiBpd.setFlagDetail(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_FLAG_DETAIL]));
            entMappingBankGaransiBpd.setNoRekening(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_NO_REKENING]));
            entMappingBankGaransiBpd.setKodeJenisGaransi(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_KODE_JENIS_GARANSI]));
            entMappingBankGaransiBpd.setKodeTujuanGaransi(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_KODE_TUJUAN_GARANSI]));
            entMappingBankGaransiBpd.setTglDiterbitkan(rs.getDate(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_TGL_DITERBITKAN]));
            entMappingBankGaransiBpd.setTglJatuhTempo(rs.getDate(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_TGL_JATUH_TEMPO]));
            entMappingBankGaransiBpd.setNoAkadAwal(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_NO_AKAD_AWAL]));
            entMappingBankGaransiBpd.setTglAkadAwal(rs.getDate(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_TGL_AKAD_AWAL]));
            entMappingBankGaransiBpd.setNoAkadAkhir(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_NO_AKAD_AKHIR]));
            entMappingBankGaransiBpd.setTglAkadAkhir(rs.getDate(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_TGL_AKAD_AKHIR]));
            entMappingBankGaransiBpd.setNamaYgDijamin(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_NAMA_YG_DIJAMIN]));
            entMappingBankGaransiBpd.setPlafon(rs.getDouble(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_PLAFON]));
            entMappingBankGaransiBpd.setNominal(rs.getDouble(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_NOMINAL]));
            entMappingBankGaransiBpd.setSetoranJaminan(rs.getDouble(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_SETORAN_JAMINAN]));
            entMappingBankGaransiBpd.setKodeKolektibilitas(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_KODE_KOLEKTIBILITAS]));
            entMappingBankGaransiBpd.setTglWanPrestasi(rs.getDate(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_TGL_WAN_PRESTASI]));
            entMappingBankGaransiBpd.setKodeKondisi(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_KODE_KONDISI]));
            entMappingBankGaransiBpd.setTglKondisi(rs.getDate(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_TGL_KONDISI]));
            entMappingBankGaransiBpd.setKeterangan(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_KETERANGAN]));
            entMappingBankGaransiBpd.setKodeKantorCabang(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_KODE_KANTOR_CABANG]));
            entMappingBankGaransiBpd.setCif(rs.getString(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_CIF]));
            entMappingBankGaransiBpd.setPeriodeId( rs.getLong(PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_PERIODE_ID]));
            
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
            String sql = "SELECT * FROM " + TBL_MAPPING_BANK_GARANSI_BPD;
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
                MappingBankGaransiBpd entMappingBankGaransiBpd = new MappingBankGaransiBpd();
                resultToObject(rs, entMappingBankGaransiBpd);
                lists.add(entMappingBankGaransiBpd);
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

    public static boolean checkOID(long entMappingBankGaransiBpdId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_BANK_GARANSI_BPD + " WHERE "
                    + PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_MAPPING_BANK_GARANSI_BPD_OID] + " = " + entMappingBankGaransiBpdId;
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
            String sql = "SELECT COUNT(" + PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_MAPPING_BANK_GARANSI_BPD_OID] + ") FROM " + TBL_MAPPING_BANK_GARANSI_BPD;
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
                    MappingBankGaransiBpd entMappingBankGaransiBpd = (MappingBankGaransiBpd) list.get(ls);
                    if (oid == entMappingBankGaransiBpd.getOID()) {
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
    
     public static int deleteSegmentBankGaransiPerPeriode(long periodeId, String cif) {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "DELETE FROM " + TBL_MAPPING_BANK_GARANSI_BPD
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
    
     
      public static long checkBankGaransi(long periodeId, String cif) {
        DBResultSet dbrs = null;
        long result = 0;
        try {
            String sql = "SELECT * FROM " + TBL_MAPPING_BANK_GARANSI_BPD  + " WHERE "
                    + PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_PERIODE_ID] + " = '" + periodeId+"'"
                    + " AND "+PstMappingBankGaransiBpd.fieldNames[PstMappingBankGaransiBpd.FLD_NO_REKENING]+"='"+cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getLong(""+fieldNames[FLD_MAPPING_BANK_GARANSI_BPD_OID]);
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
