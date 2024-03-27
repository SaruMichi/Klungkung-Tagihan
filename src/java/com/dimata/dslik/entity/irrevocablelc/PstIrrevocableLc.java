/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.irrevocablelc;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.contentdata.PstContentDataJenisLc;
import com.dimata.dslik.entity.contentdata.PstContentDataTujuanLc;
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstIrrevocableLc extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    private String sqlQueryHistory = "";

    public static final String TBL_IRREVOCABLE_LC = "dslik_irrevocable_lc";
    public static final int FLD_IRREVOCABLE_LC_OID = 0;
    public static final int FLD_FLAG_DETAIL = 1;
    public static final int FLD_NO_REKENING = 2;
    public static final int FLD_CIF = 3;
    public static final int FLD_KODE_JENIS = 4;
    public static final int FLD_KODE_TUJUAN = 5;
    public static final int FLD_TGL_KELUAR = 6;
    public static final int FLD_TGL_JATUH_TEMPO = 7;
    public static final int FLD_NO_AKAD_AWAL = 8;
    public static final int FLD_TGL_AKAD_AWAL = 9;
    public static final int FLD_NO_AKAD_AKHR = 10;
    public static final int FLD_TGL_AKAD_AKHIR = 11;
    public static final int FLD_BANK_COUNTERPARTY = 12;
    public static final int FLD_KODE_VALUTA = 13;
    public static final int FLD_PLAFON = 14;
    public static final int FLD_NOMINAL = 15;
    public static final int FLD_SETORAN_JAMINAN = 16;
    public static final int FLD_KODE_KOLEKTIBILITAS = 17;
    public static final int FLD_TGL_WAN_PRESTASI = 18;
    public static final int FLD_KODE_KONDISI = 19;
    public static final int FLD_TGL_KONDISI = 20;
    public static final int FLD_KETERANGAN = 21;
    public static final int FLD_KODE_KANTOR_CABANG = 22;
    public static final int FLD_OPERASI_DATA = 23;
    public static final int FLD_OPEN_DATE = 24;
    public static final int FLD_STATUS_DATA = 25;
    public static final int FLD_PERIODE_ID = 26;
    public static final int FLD_STATUS_PERUBAHAN_DATA = 27;
    public static String[] fieldNames = {
        "IRREVOCABLE_LC",
        "FLAG_DETAIL",
        "NO_REKENING",
        "CIF",
        "KODE_JENIS",
        "KODE_TUJUAN",
        "TGL_KELUAR",
        "TGL_JATUH_TEMPO",
        "NO_AKAD_AWAL",
        "TGL_AKAD_AWAL",
        "NO_AKAD_AKHIR",
        "TGL_AKAD_AKHIR",
        "BANK_COUNTERPARTY",
        "KODE_VALUTA",
        "PLAFON",
        "NOMINAL",
        "SETORAN_JAMINAN",
        "KODE_KOLEKTIBILITAS",
        "TGL_WAN_PRESTASI",
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
        TYPE_DATE,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
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
        TYPE_DATE,
        TYPE_INT,
        TYPE_LONG,
        TYPE_INT
    };

    public PstIrrevocableLc() {
    }

    public PstIrrevocableLc(int i) throws DBException {
        super(new PstIrrevocableLc());
    }

    public PstIrrevocableLc(String sOid) throws DBException {
        super(new PstIrrevocableLc(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstIrrevocableLc(long lOid) throws DBException {
        super(new PstIrrevocableLc(0));
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
        return TBL_IRREVOCABLE_LC;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstIrrevocableLc().getClass().getName();
    }

    public static IrrevocableLc fetchExc(long oid) throws DBException {
        try {
            IrrevocableLc entIrrevocableLc = new IrrevocableLc();
            PstIrrevocableLc pstIrrevocableLc = new PstIrrevocableLc(oid);
            entIrrevocableLc.setOID(oid);
            entIrrevocableLc.setFlagDetail(pstIrrevocableLc.getString(FLD_FLAG_DETAIL));
            entIrrevocableLc.setNoRekening(pstIrrevocableLc.getString(FLD_NO_REKENING));
            entIrrevocableLc.setCif(pstIrrevocableLc.getString(FLD_CIF));
            entIrrevocableLc.setKodeJenis(pstIrrevocableLc.getString(FLD_KODE_JENIS));
            entIrrevocableLc.setKodeTujuan(pstIrrevocableLc.getString(FLD_KODE_TUJUAN));
            entIrrevocableLc.setTglKeluar(pstIrrevocableLc.getDate(FLD_TGL_KELUAR));
            entIrrevocableLc.setTglJatuhTempo(pstIrrevocableLc.getDate(FLD_TGL_JATUH_TEMPO));
            entIrrevocableLc.setNoAkadAwal(pstIrrevocableLc.getString(FLD_NO_AKAD_AWAL));
            entIrrevocableLc.setTglAkadAwal(pstIrrevocableLc.getDate(FLD_TGL_AKAD_AWAL));
            entIrrevocableLc.setNoAkadAkhr(pstIrrevocableLc.getString(FLD_NO_AKAD_AKHR));
            entIrrevocableLc.setTglAkadAkhir(pstIrrevocableLc.getDate(FLD_TGL_AKAD_AKHIR));
            entIrrevocableLc.setBankCounterparty(pstIrrevocableLc.getString(FLD_BANK_COUNTERPARTY));
            entIrrevocableLc.setKodeValuta(pstIrrevocableLc.getString(FLD_KODE_VALUTA));
            entIrrevocableLc.setPlafon(pstIrrevocableLc.getdouble(FLD_PLAFON));
            entIrrevocableLc.setNominal(pstIrrevocableLc.getdouble(FLD_NOMINAL));
            entIrrevocableLc.setSetoranJaminan(pstIrrevocableLc.getdouble(FLD_SETORAN_JAMINAN));
            entIrrevocableLc.setKodeKolektibilitas(pstIrrevocableLc.getString(FLD_KODE_KOLEKTIBILITAS));
            entIrrevocableLc.setTglWanPrestasi(pstIrrevocableLc.getDate(FLD_TGL_WAN_PRESTASI));
            entIrrevocableLc.setKodeKondisi(pstIrrevocableLc.getString(FLD_KODE_KONDISI));
            entIrrevocableLc.setTglKondisi(pstIrrevocableLc.getDate(FLD_TGL_KONDISI));
            entIrrevocableLc.setKeterangan(pstIrrevocableLc.getString(FLD_KETERANGAN));
            entIrrevocableLc.setKodeKantorCabang(pstIrrevocableLc.getString(FLD_KODE_KANTOR_CABANG));
            entIrrevocableLc.setOperasiData(pstIrrevocableLc.getString(FLD_OPERASI_DATA));
            entIrrevocableLc.setOpenDate(pstIrrevocableLc.getDate(FLD_OPEN_DATE));
            entIrrevocableLc.setStatusData(pstIrrevocableLc.getInt(FLD_STATUS_DATA));
            entIrrevocableLc.setPeriodeId(pstIrrevocableLc.getlong(FLD_PERIODE_ID));
            entIrrevocableLc.setStatusPerubahanData(pstIrrevocableLc.getInt(FLD_STATUS_PERUBAHAN_DATA));
            return entIrrevocableLc;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstIrrevocableLc(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        IrrevocableLc entIrrevocableLc = fetchExc(entity.getOID());
        entity = (Entity) entIrrevocableLc;
        return entIrrevocableLc.getOID();
    }

    public static synchronized long updateExc(IrrevocableLc entIrrevocableLc) throws DBException {
        try {
            if (entIrrevocableLc.getOID() != 0) {
                PstIrrevocableLc pstIrrevocableLc = new PstIrrevocableLc(entIrrevocableLc.getOID());
                pstIrrevocableLc.setString(FLD_FLAG_DETAIL, entIrrevocableLc.getFlagDetail());
                pstIrrevocableLc.setString(FLD_NO_REKENING, entIrrevocableLc.getNoRekening());
                pstIrrevocableLc.setString(FLD_CIF, entIrrevocableLc.getCif());
                pstIrrevocableLc.setString(FLD_KODE_JENIS, entIrrevocableLc.getKodeJenis());
                pstIrrevocableLc.setString(FLD_KODE_TUJUAN, entIrrevocableLc.getKodeTujuan());
                pstIrrevocableLc.setDate(FLD_TGL_KELUAR, entIrrevocableLc.getTglKeluar());
                pstIrrevocableLc.setDate(FLD_TGL_JATUH_TEMPO, entIrrevocableLc.getTglJatuhTempo());
                pstIrrevocableLc.setString(FLD_NO_AKAD_AWAL, entIrrevocableLc.getNoAkadAwal());
                pstIrrevocableLc.setDate(FLD_TGL_AKAD_AWAL, entIrrevocableLc.getTglAkadAwal());
                pstIrrevocableLc.setString(FLD_NO_AKAD_AKHR, entIrrevocableLc.getNoAkadAkhr());
                pstIrrevocableLc.setDate(FLD_TGL_AKAD_AKHIR, entIrrevocableLc.getTglAkadAkhir());
                pstIrrevocableLc.setString(FLD_BANK_COUNTERPARTY, entIrrevocableLc.getBankCounterparty());
                pstIrrevocableLc.setString(FLD_KODE_VALUTA, entIrrevocableLc.getKodeValuta());
                pstIrrevocableLc.setDouble(FLD_PLAFON, entIrrevocableLc.getPlafon());
                pstIrrevocableLc.setDouble(FLD_NOMINAL, entIrrevocableLc.getNominal());
                pstIrrevocableLc.setDouble(FLD_SETORAN_JAMINAN, entIrrevocableLc.getSetoranJaminan());
                pstIrrevocableLc.setString(FLD_KODE_KOLEKTIBILITAS, entIrrevocableLc.getKodeKolektibilitas());
                pstIrrevocableLc.setDate(FLD_TGL_WAN_PRESTASI, entIrrevocableLc.getTglWanPrestasi());
                pstIrrevocableLc.setString(FLD_KODE_KONDISI, entIrrevocableLc.getKodeKondisi());
                pstIrrevocableLc.setDate(FLD_TGL_KONDISI, entIrrevocableLc.getTglKondisi());
                pstIrrevocableLc.setString(FLD_KETERANGAN, entIrrevocableLc.getKeterangan());
                pstIrrevocableLc.setString(FLD_KODE_KANTOR_CABANG, entIrrevocableLc.getKodeKantorCabang());
                pstIrrevocableLc.setString(FLD_OPERASI_DATA, entIrrevocableLc.getOperasiData());
                pstIrrevocableLc.setDate(FLD_OPEN_DATE, entIrrevocableLc.getOpenDate());
                pstIrrevocableLc.setInt(FLD_STATUS_DATA, entIrrevocableLc.getStatusData());
                pstIrrevocableLc.setLong(FLD_PERIODE_ID, entIrrevocableLc.getPeriodeId());
                pstIrrevocableLc.setInt(FLD_STATUS_PERUBAHAN_DATA, entIrrevocableLc.getStatusPerubahanData());
                pstIrrevocableLc.update();
                
                pstIrrevocableLc.setSqlQueryHistory("");
                pstIrrevocableLc.setSqlQueryHistory(pstIrrevocableLc.getUpdateSQL());
                
                return entIrrevocableLc.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstIrrevocableLc(0), DBException.UNKNOWN);
        }
        return 0;
    }
    
    public static synchronized IrrevocableLc updateExcObj(IrrevocableLc entIrrevocableLc) throws DBException {
        try {
            if (entIrrevocableLc.getOID() != 0) {
                PstIrrevocableLc pstIrrevocableLc = new PstIrrevocableLc(entIrrevocableLc.getOID());
                pstIrrevocableLc.setString(FLD_FLAG_DETAIL, entIrrevocableLc.getFlagDetail());
                pstIrrevocableLc.setString(FLD_NO_REKENING, entIrrevocableLc.getNoRekening());
                pstIrrevocableLc.setString(FLD_CIF, entIrrevocableLc.getCif());
                pstIrrevocableLc.setString(FLD_KODE_JENIS, entIrrevocableLc.getKodeJenis());
                pstIrrevocableLc.setString(FLD_KODE_TUJUAN, entIrrevocableLc.getKodeTujuan());
                pstIrrevocableLc.setDate(FLD_TGL_KELUAR, entIrrevocableLc.getTglKeluar());
                pstIrrevocableLc.setDate(FLD_TGL_JATUH_TEMPO, entIrrevocableLc.getTglJatuhTempo());
                pstIrrevocableLc.setString(FLD_NO_AKAD_AWAL, entIrrevocableLc.getNoAkadAwal());
                pstIrrevocableLc.setDate(FLD_TGL_AKAD_AWAL, entIrrevocableLc.getTglAkadAwal());
                pstIrrevocableLc.setString(FLD_NO_AKAD_AKHR, entIrrevocableLc.getNoAkadAkhr());
                pstIrrevocableLc.setDate(FLD_TGL_AKAD_AKHIR, entIrrevocableLc.getTglAkadAkhir());
                pstIrrevocableLc.setString(FLD_BANK_COUNTERPARTY, entIrrevocableLc.getBankCounterparty());
                pstIrrevocableLc.setString(FLD_KODE_VALUTA, entIrrevocableLc.getKodeValuta());
                pstIrrevocableLc.setDouble(FLD_PLAFON, entIrrevocableLc.getPlafon());
                pstIrrevocableLc.setDouble(FLD_NOMINAL, entIrrevocableLc.getNominal());
                pstIrrevocableLc.setDouble(FLD_SETORAN_JAMINAN, entIrrevocableLc.getSetoranJaminan());
                pstIrrevocableLc.setString(FLD_KODE_KOLEKTIBILITAS, entIrrevocableLc.getKodeKolektibilitas());
                pstIrrevocableLc.setDate(FLD_TGL_WAN_PRESTASI, entIrrevocableLc.getTglWanPrestasi());
                pstIrrevocableLc.setString(FLD_KODE_KONDISI, entIrrevocableLc.getKodeKondisi());
                pstIrrevocableLc.setDate(FLD_TGL_KONDISI, entIrrevocableLc.getTglKondisi());
                pstIrrevocableLc.setString(FLD_KETERANGAN, entIrrevocableLc.getKeterangan());
                pstIrrevocableLc.setString(FLD_KODE_KANTOR_CABANG, entIrrevocableLc.getKodeKantorCabang());
                pstIrrevocableLc.setString(FLD_OPERASI_DATA, entIrrevocableLc.getOperasiData());
                pstIrrevocableLc.setDate(FLD_OPEN_DATE, entIrrevocableLc.getOpenDate());
                pstIrrevocableLc.setInt(FLD_STATUS_DATA, entIrrevocableLc.getStatusData());
                pstIrrevocableLc.setLong(FLD_PERIODE_ID, entIrrevocableLc.getPeriodeId());
                pstIrrevocableLc.setInt(FLD_STATUS_PERUBAHAN_DATA, entIrrevocableLc.getStatusPerubahanData());
                pstIrrevocableLc.update();
                
                pstIrrevocableLc.setSqlQueryHistory("");
                pstIrrevocableLc.setSqlQueryHistory(pstIrrevocableLc.getUpdateSQL());
                entIrrevocableLc.setSqlHIstory(pstIrrevocableLc.getUpdateSQL());
                return entIrrevocableLc;
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstIrrevocableLc(0), DBException.UNKNOWN);
        }
        return entIrrevocableLc;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((IrrevocableLc) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstIrrevocableLc pstIrrevocableLc = new PstIrrevocableLc(oid);
            pstIrrevocableLc.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstIrrevocableLc(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(IrrevocableLc entIrrevocableLc) throws DBException {
        try {
            PstIrrevocableLc pstIrrevocableLc = new PstIrrevocableLc(0);
            pstIrrevocableLc.setString(FLD_FLAG_DETAIL, entIrrevocableLc.getFlagDetail());
            pstIrrevocableLc.setString(FLD_NO_REKENING, entIrrevocableLc.getNoRekening());
            pstIrrevocableLc.setString(FLD_CIF, entIrrevocableLc.getCif());
            pstIrrevocableLc.setString(FLD_KODE_JENIS, entIrrevocableLc.getKodeJenis());
            pstIrrevocableLc.setString(FLD_KODE_TUJUAN, entIrrevocableLc.getKodeTujuan());
            pstIrrevocableLc.setDate(FLD_TGL_KELUAR, entIrrevocableLc.getTglKeluar());
            pstIrrevocableLc.setDate(FLD_TGL_JATUH_TEMPO, entIrrevocableLc.getTglJatuhTempo());
            pstIrrevocableLc.setString(FLD_NO_AKAD_AWAL, entIrrevocableLc.getNoAkadAwal());
            pstIrrevocableLc.setDate(FLD_TGL_AKAD_AWAL, entIrrevocableLc.getTglAkadAwal());
            pstIrrevocableLc.setString(FLD_NO_AKAD_AKHR, entIrrevocableLc.getNoAkadAkhr());
            pstIrrevocableLc.setDate(FLD_TGL_AKAD_AKHIR, entIrrevocableLc.getTglAkadAkhir());
            pstIrrevocableLc.setString(FLD_BANK_COUNTERPARTY, entIrrevocableLc.getBankCounterparty());
            pstIrrevocableLc.setString(FLD_KODE_VALUTA, entIrrevocableLc.getKodeValuta());
            pstIrrevocableLc.setDouble(FLD_PLAFON, entIrrevocableLc.getPlafon());
            pstIrrevocableLc.setDouble(FLD_NOMINAL, entIrrevocableLc.getNominal());
            pstIrrevocableLc.setDouble(FLD_SETORAN_JAMINAN, entIrrevocableLc.getSetoranJaminan());
            pstIrrevocableLc.setString(FLD_KODE_KOLEKTIBILITAS, entIrrevocableLc.getKodeKolektibilitas());
            pstIrrevocableLc.setDate(FLD_TGL_WAN_PRESTASI, entIrrevocableLc.getTglWanPrestasi());
            pstIrrevocableLc.setString(FLD_KODE_KONDISI, entIrrevocableLc.getKodeKondisi());
            pstIrrevocableLc.setDate(FLD_TGL_KONDISI, entIrrevocableLc.getTglKondisi());
            pstIrrevocableLc.setString(FLD_KETERANGAN, entIrrevocableLc.getKeterangan());
            pstIrrevocableLc.setString(FLD_KODE_KANTOR_CABANG, entIrrevocableLc.getKodeKantorCabang());
            pstIrrevocableLc.setString(FLD_OPERASI_DATA, entIrrevocableLc.getOperasiData());
            pstIrrevocableLc.setDate(FLD_OPEN_DATE, entIrrevocableLc.getOpenDate());
            pstIrrevocableLc.setInt(FLD_STATUS_DATA, entIrrevocableLc.getStatusData());
            pstIrrevocableLc.setLong(FLD_PERIODE_ID, entIrrevocableLc.getPeriodeId());
            pstIrrevocableLc.setInt(FLD_STATUS_PERUBAHAN_DATA, entIrrevocableLc.getStatusPerubahanData());
            pstIrrevocableLc.insert();
            
            pstIrrevocableLc.setSqlQueryHistory("");
            pstIrrevocableLc.setSqlQueryHistory(pstIrrevocableLc.getInsertSQL());
                
            entIrrevocableLc.setOID(pstIrrevocableLc.getLong(FLD_IRREVOCABLE_LC_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstIrrevocableLc(0), DBException.UNKNOWN);
        }
        return entIrrevocableLc.getOID();
    }
    public static synchronized IrrevocableLc insertExcObj(IrrevocableLc entIrrevocableLc) throws DBException {
        try {
            PstIrrevocableLc pstIrrevocableLc = new PstIrrevocableLc(0);
            pstIrrevocableLc.setString(FLD_FLAG_DETAIL, entIrrevocableLc.getFlagDetail());
            pstIrrevocableLc.setString(FLD_NO_REKENING, entIrrevocableLc.getNoRekening());
            pstIrrevocableLc.setString(FLD_CIF, entIrrevocableLc.getCif());
            pstIrrevocableLc.setString(FLD_KODE_JENIS, entIrrevocableLc.getKodeJenis());
            pstIrrevocableLc.setString(FLD_KODE_TUJUAN, entIrrevocableLc.getKodeTujuan());
            pstIrrevocableLc.setDate(FLD_TGL_KELUAR, entIrrevocableLc.getTglKeluar());
            pstIrrevocableLc.setDate(FLD_TGL_JATUH_TEMPO, entIrrevocableLc.getTglJatuhTempo());
            pstIrrevocableLc.setString(FLD_NO_AKAD_AWAL, entIrrevocableLc.getNoAkadAwal());
            pstIrrevocableLc.setDate(FLD_TGL_AKAD_AWAL, entIrrevocableLc.getTglAkadAwal());
            pstIrrevocableLc.setString(FLD_NO_AKAD_AKHR, entIrrevocableLc.getNoAkadAkhr());
            pstIrrevocableLc.setDate(FLD_TGL_AKAD_AKHIR, entIrrevocableLc.getTglAkadAkhir());
            pstIrrevocableLc.setString(FLD_BANK_COUNTERPARTY, entIrrevocableLc.getBankCounterparty());
            pstIrrevocableLc.setString(FLD_KODE_VALUTA, entIrrevocableLc.getKodeValuta());
            pstIrrevocableLc.setDouble(FLD_PLAFON, entIrrevocableLc.getPlafon());
            pstIrrevocableLc.setDouble(FLD_NOMINAL, entIrrevocableLc.getNominal());
            pstIrrevocableLc.setDouble(FLD_SETORAN_JAMINAN, entIrrevocableLc.getSetoranJaminan());
            pstIrrevocableLc.setString(FLD_KODE_KOLEKTIBILITAS, entIrrevocableLc.getKodeKolektibilitas());
            pstIrrevocableLc.setDate(FLD_TGL_WAN_PRESTASI, entIrrevocableLc.getTglWanPrestasi());
            pstIrrevocableLc.setString(FLD_KODE_KONDISI, entIrrevocableLc.getKodeKondisi());
            pstIrrevocableLc.setDate(FLD_TGL_KONDISI, entIrrevocableLc.getTglKondisi());
            pstIrrevocableLc.setString(FLD_KETERANGAN, entIrrevocableLc.getKeterangan());
            pstIrrevocableLc.setString(FLD_KODE_KANTOR_CABANG, entIrrevocableLc.getKodeKantorCabang());
            pstIrrevocableLc.setString(FLD_OPERASI_DATA, entIrrevocableLc.getOperasiData());
            pstIrrevocableLc.setDate(FLD_OPEN_DATE, entIrrevocableLc.getOpenDate());
            pstIrrevocableLc.setInt(FLD_STATUS_DATA, entIrrevocableLc.getStatusData());
            pstIrrevocableLc.setLong(FLD_PERIODE_ID, entIrrevocableLc.getPeriodeId());
            pstIrrevocableLc.setInt(FLD_STATUS_PERUBAHAN_DATA, entIrrevocableLc.getStatusPerubahanData());
            pstIrrevocableLc.insert();
            
            pstIrrevocableLc.setSqlQueryHistory("");
            pstIrrevocableLc.setSqlQueryHistory(pstIrrevocableLc.getInsertSQL());
            entIrrevocableLc.setSqlHIstory(pstIrrevocableLc.getInsertSQL());
                
            entIrrevocableLc.setOID(pstIrrevocableLc.getlong(FLD_IRREVOCABLE_LC_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstIrrevocableLc(0), DBException.UNKNOWN);
        }
        return entIrrevocableLc;
    }
    public long insertExc(Entity entity) throws Exception {
        return insertExc((IrrevocableLc) entity);
    }

    public static void resultToObject(ResultSet rs, IrrevocableLc entIrrevocableLc) {
        try {
            entIrrevocableLc.setOID(rs.getLong(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_IRREVOCABLE_LC_OID]));
            entIrrevocableLc.setFlagDetail(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_FLAG_DETAIL]));
            entIrrevocableLc.setNoRekening(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]));
            entIrrevocableLc.setCif(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]));
            entIrrevocableLc.setKodeJenis(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]));
            entIrrevocableLc.setKodeTujuan(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_TUJUAN]));
            entIrrevocableLc.setTglKeluar(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_KELUAR]));
            entIrrevocableLc.setTglJatuhTempo(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_JATUH_TEMPO]));
            entIrrevocableLc.setNoAkadAwal(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_AKAD_AWAL]));
            entIrrevocableLc.setTglAkadAwal(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_AKAD_AWAL]));
            entIrrevocableLc.setNoAkadAkhr(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_AKAD_AKHR]));
            entIrrevocableLc.setTglAkadAkhir(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_AKAD_AKHIR]));
            entIrrevocableLc.setBankCounterparty(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_BANK_COUNTERPARTY]));
            entIrrevocableLc.setKodeValuta(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_VALUTA]));
            entIrrevocableLc.setPlafon(rs.getDouble(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PLAFON]));
            entIrrevocableLc.setNominal(rs.getDouble(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NOMINAL]));
            entIrrevocableLc.setSetoranJaminan(rs.getDouble(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_SETORAN_JAMINAN]));
            entIrrevocableLc.setKodeKolektibilitas(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KOLEKTIBILITAS]));
            entIrrevocableLc.setTglWanPrestasi(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_WAN_PRESTASI]));
            entIrrevocableLc.setKodeKondisi(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KONDISI]));
            entIrrevocableLc.setTglKondisi(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_KONDISI]));
            entIrrevocableLc.setKeterangan(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KETERANGAN]));
            entIrrevocableLc.setKodeKantorCabang(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]));
            entIrrevocableLc.setOperasiData(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_OPERASI_DATA]));
            entIrrevocableLc.setOpenDate(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_OPEN_DATE]));
            entIrrevocableLc.setStatusData(rs.getInt(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_DATA]));
            entIrrevocableLc.setPeriodeId(rs.getLong(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID]));
            entIrrevocableLc.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));
            entIrrevocableLc.setStatusPerubahanData(rs.getInt(fieldNames[FLD_STATUS_PERUBAHAN_DATA]));
        } catch (Exception e) {
        }
    }
      
    public static void resultToObjectJoin(ResultSet rs, IrrevocableLc entIrrevocableLc) {
        try {
            entIrrevocableLc.setOID(rs.getLong(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_IRREVOCABLE_LC_OID]));
            entIrrevocableLc.setFlagDetail(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_FLAG_DETAIL]));
            entIrrevocableLc.setNoRekening(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]));
            entIrrevocableLc.setCif(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]));
            entIrrevocableLc.setKodeJenis(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]));
            entIrrevocableLc.setKodeTujuan(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_TUJUAN]));
            entIrrevocableLc.setTglKeluar(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_KELUAR]));
            entIrrevocableLc.setTglJatuhTempo(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_JATUH_TEMPO]));
            entIrrevocableLc.setNoAkadAwal(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_AKAD_AWAL]));
            entIrrevocableLc.setTglAkadAwal(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_AKAD_AWAL]));
            entIrrevocableLc.setNoAkadAkhr(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_AKAD_AKHR]));
            entIrrevocableLc.setTglAkadAkhir(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_AKAD_AKHIR]));
            entIrrevocableLc.setBankCounterparty(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_BANK_COUNTERPARTY]));
            entIrrevocableLc.setKodeValuta(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_VALUTA]));
            entIrrevocableLc.setPlafon(rs.getDouble(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PLAFON]));
            entIrrevocableLc.setNominal(rs.getDouble(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NOMINAL]));
            entIrrevocableLc.setSetoranJaminan(rs.getDouble(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_SETORAN_JAMINAN]));
            entIrrevocableLc.setKodeKolektibilitas(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KOLEKTIBILITAS]));
            entIrrevocableLc.setTglWanPrestasi(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_WAN_PRESTASI]));
            entIrrevocableLc.setKodeKondisi(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KONDISI]));
            entIrrevocableLc.setTglKondisi(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_TGL_KONDISI]));
            entIrrevocableLc.setKeterangan(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KETERANGAN]));
            entIrrevocableLc.setKodeKantorCabang(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]));
            entIrrevocableLc.setOperasiData(rs.getString(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_OPERASI_DATA]));
            entIrrevocableLc.setOpenDate(rs.getDate(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_OPEN_DATE]));
            entIrrevocableLc.setStatusData(rs.getInt(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_DATA]));
            entIrrevocableLc.setNamaJenisLc(rs.getString(PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_NAMA_LC]));
            entIrrevocableLc.setNamaTujuanLc(rs.getString(PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]));
            entIrrevocableLc.setPeriodeId(rs.getLong(PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID]));
            entIrrevocableLc.setDebiturOid(rs.getLong(PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID]));
            entIrrevocableLc.setDebiturType(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
            entIrrevocableLc.setStatusPerubahanData(rs.getInt(fieldNames[FLD_STATUS_PERUBAHAN_DATA]));
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
            String sql = "SELECT DISTINCT * FROM " + TBL_IRREVOCABLE_LC;
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
                IrrevocableLc entIrrevocableLc = new IrrevocableLc();
                resultToObject(rs, entIrrevocableLc);
                lists.add(entIrrevocableLc);
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
                + " SELECT DISTINCT ilc.*, "
                + " jlc."+PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_NAMA_LC]+", "
                + " tlc."+PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]+" "
                + " FROM "+TBL_IRREVOCABLE_LC+" ilc "
                + " LEFT JOIN "+PstContentDataJenisLc.TBL_CONTENT_DATA_JENIS_LC+" jlc "
                + " ON ilc."+fieldNames[FLD_KODE_JENIS]+" = jlc."+PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataTujuanLc.TBL_CONTENT_DATA_TUJUAN_LC+" tlc "
                + " ON ilc."+fieldNames[FLD_KODE_TUJUAN]+" = tlc."+PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_KODE_OJK]+" "                
                + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + "ON ilc."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + "ON ilc."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID];
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
                IrrevocableLc entIrrevocableLc = new IrrevocableLc();
                resultToObjectJoin(rs, entIrrevocableLc);
                lists.add(entIrrevocableLc);
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

    public static boolean checkOID(long entIrrevocableLcId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT DISTINCT * FROM " + TBL_IRREVOCABLE_LC + " WHERE "
                    + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_IRREVOCABLE_LC_OID] + " = " + entIrrevocableLcId;
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
            String sql = ""
                + " SELECT DISTINCT COUNT(" + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_IRREVOCABLE_LC_OID] + ") "
                + " FROM " + TBL_IRREVOCABLE_LC;
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
                + " SELECT DISTINCT COUNT(ilc." + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_IRREVOCABLE_LC_OID] + ") "
                + " FROM "+TBL_IRREVOCABLE_LC+" ilc "
                + " LEFT JOIN "+PstContentDataJenisLc.TBL_CONTENT_DATA_JENIS_LC+" jlc "
                + " ON ilc."+fieldNames[FLD_KODE_JENIS]+" = jlc."+PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_KODE_OJK]+" "
                + " LEFT JOIN "+PstContentDataTujuanLc.TBL_CONTENT_DATA_TUJUAN_LC+" tlc "
                + " ON ilc."+fieldNames[FLD_KODE_TUJUAN]+" = tlc."+PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]+" "
                + "INNER JOIN "+PstPeriode.TBL_PERIODE+" AS periode "
                + "ON ilc."+fieldNames[FLD_PERIODE_ID]+"=periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+" "
                + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0' "
                + "INNER JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" AS debitur "
                + "ON ilc."+fieldNames[FLD_CIF]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                + "AND periode."+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"=debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID];
            
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
                    IrrevocableLc entIrrevocableLc = (IrrevocableLc) list.get(ls);
                    if (oid == entIrrevocableLc.getOID()) {
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
