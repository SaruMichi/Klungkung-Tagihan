/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.entity.payment;

/**
 *
 * @author dimata005
 */
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstPaymentPbbIprotax extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_PAYMENTPBBIPROTAX = "PEMBAYARAN_SPPT";
    public static final int FLD_KDPROVINSI = 0;
    public static final int FLD_KDDATI2 = 1;
    public static final int FLD_KDKECAMATAN = 2;
    public static final int FLD_KDKELURAHAN = 3;
    public static final int FLD_KDBLOCK = 4;
    public static final int FLD_NOURUT = 5;
    public static final int FLD_NOJNSOP = 6;
    public static final int FLD_THNPAJAKSPPT = 7;
    public static final int FLD_PEMBAYARANSPPTKE = 8;
    public static final int FLD_JMLDENDASPPT = 9;
    public static final int FLD_JMLPBBYGDIBAYAR = 10;
    public static final int FLD_URAIANBAYARSPPT = 11;
    public static final int FLD_KDSUMBERDATA = 12;
    public static final int FLD_TGLPEMBAYARANSPPT = 13;
    public static final int FLD_KDBANKTUNGGAL = 14;
    public static final int FLD_KDBANKPERSEPSI = 15;
    public static final int FLD_KDTP = 16;
    public static final int FLD_NOTRANSAKSIBYRSPPT = 17;
    public static final int FLD_NOTRANSAKSIBYRSPPTKOLEKTIF = 18;
    public static final int FLD_NOTRANSAKSIBYRSPPTBANK = 19;
    public static final int FLD_TGLREKAMBYRSPPT = 20;
    public static final int FLD_USERBANKREKAM = 21;
    public static final int FLD_DATAPEMBAYARAN = 22;

    public static String[] fieldNames = {
        "KD_PROPINSI",
        "KD_DATI2",
        "KD_KECAMATAN",
        "KD_KELURAHAN",
        "KD_BLOK",
        "NO_URUT",
        "KD_JNS_OP",
        "THN_PAJAK_SPPT",
        "PEMBAYARAN_SPPT_KE",
        "JML_DENDA_SPPT",
        "JML_PBB_YG_DIBAYAR",
        "URAIAN_BAYAR_SPPT",
        "KD_SUMBER_DATA",
        "TGL_PEMBAYARAN_SPPT",
        "KD_BANK_TUNGGAL",
        "KD_BANK_PERSEPSI",
        "KD_TP",
        "NO_TRANSAKSI_BYR_SPPT",
        "NO_TRANSAKSI_BYR_SPPT_KOLEKTIF",
        "NO_TRANSAKSI_BYR_SPPT_BANK",
        "TGL_REKAM_BYR_SPPT",
        "USER_BANK_REKAM",
        "DATA_PEMBAYARAN"
    };

    public static int[] fieldTypes = {
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstPaymentPbbIprotax() {
    }

    public PstPaymentPbbIprotax(int i) throws DBException {
        super(new PstPaymentPbbIprotax());
    }

    public PstPaymentPbbIprotax(String sOid) throws DBException {
        super(new PstPaymentPbbIprotax(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstPaymentPbbIprotax(long lOid) throws DBException {
        super(new PstPaymentPbbIprotax(0));
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
        return TBL_PAYMENTPBBIPROTAX;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstPaymentPbbIprotax().getClass().getName();
    }

    public static PaymentPbbIprotax fetchExc(long oid) throws DBException {
        try {
            PaymentPbbIprotax entPaymentPbbIprotax = new PaymentPbbIprotax();
            PstPaymentPbbIprotax pstPaymentPbbIprotax = new PstPaymentPbbIprotax(oid);
            entPaymentPbbIprotax.setOID(oid);
            entPaymentPbbIprotax.setKdDati2(pstPaymentPbbIprotax.getString(FLD_KDDATI2));
            entPaymentPbbIprotax.setKdKecamatan(pstPaymentPbbIprotax.getString(FLD_KDKECAMATAN));
            entPaymentPbbIprotax.setKdKelurahan(pstPaymentPbbIprotax.getString(FLD_KDKELURAHAN));
            entPaymentPbbIprotax.setKdBlock(pstPaymentPbbIprotax.getString(FLD_KDBLOCK));
            entPaymentPbbIprotax.setNoUrut(pstPaymentPbbIprotax.getString(FLD_NOURUT));
            entPaymentPbbIprotax.setNoJnsOp(pstPaymentPbbIprotax.getString(FLD_NOJNSOP));
            entPaymentPbbIprotax.setThnPajakSppt(pstPaymentPbbIprotax.getString(FLD_THNPAJAKSPPT));
            entPaymentPbbIprotax.setPembayaranSpptKe(pstPaymentPbbIprotax.getInt(FLD_PEMBAYARANSPPTKE));
            entPaymentPbbIprotax.setJmlDendaSppt(pstPaymentPbbIprotax.getdouble(FLD_JMLDENDASPPT));
            entPaymentPbbIprotax.setJmlPbbYgDibayar(pstPaymentPbbIprotax.getdouble(FLD_JMLPBBYGDIBAYAR));
            entPaymentPbbIprotax.setUraianBayarSppt(pstPaymentPbbIprotax.getString(FLD_URAIANBAYARSPPT));
            entPaymentPbbIprotax.setKdSumberData(pstPaymentPbbIprotax.getString(FLD_KDSUMBERDATA));
            entPaymentPbbIprotax.setTglPembayaranSppt(pstPaymentPbbIprotax.getDate(FLD_TGLPEMBAYARANSPPT));
            entPaymentPbbIprotax.setKdBankTunggal(pstPaymentPbbIprotax.getString(FLD_KDBANKTUNGGAL));
            entPaymentPbbIprotax.setKdBankPersepsi(pstPaymentPbbIprotax.getString(FLD_KDBANKPERSEPSI));
            entPaymentPbbIprotax.setKdTp(pstPaymentPbbIprotax.getString(FLD_KDTP));
            entPaymentPbbIprotax.setNoTransaksiByrSppt(pstPaymentPbbIprotax.getString(FLD_NOTRANSAKSIBYRSPPT));
            entPaymentPbbIprotax.setNoTransaksiByrSpptKolektif(pstPaymentPbbIprotax.getString(FLD_NOTRANSAKSIBYRSPPTKOLEKTIF));
            entPaymentPbbIprotax.setNoTransaksiByrSpptBank(pstPaymentPbbIprotax.getString(FLD_NOTRANSAKSIBYRSPPTBANK));
            entPaymentPbbIprotax.setTglRekamByrSppt(pstPaymentPbbIprotax.getDate(FLD_TGLREKAMBYRSPPT));
            entPaymentPbbIprotax.setUserBankRekam(pstPaymentPbbIprotax.getString(FLD_USERBANKREKAM));
            entPaymentPbbIprotax.setDataPembayaran(pstPaymentPbbIprotax.getString(FLD_DATAPEMBAYARAN));
            return entPaymentPbbIprotax;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbIprotax(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        PaymentPbbIprotax entPaymentPbbIprotax = fetchExc(entity.getOID());
        entity = (Entity) entPaymentPbbIprotax;
        return entPaymentPbbIprotax.getOID();
    }

    public static synchronized long updateExc(PaymentPbbIprotax entPaymentPbbIprotax) throws DBException {
        try {
            if (entPaymentPbbIprotax.getOID() != 0) {
                PstPaymentPbbIprotax pstPaymentPbbIprotax = new PstPaymentPbbIprotax(entPaymentPbbIprotax.getOID());
                pstPaymentPbbIprotax.setString(FLD_KDDATI2, entPaymentPbbIprotax.getKdDati2());
                pstPaymentPbbIprotax.setString(FLD_KDKECAMATAN, entPaymentPbbIprotax.getKdKecamatan());
                pstPaymentPbbIprotax.setString(FLD_KDKELURAHAN, entPaymentPbbIprotax.getKdKelurahan());
                pstPaymentPbbIprotax.setString(FLD_KDBLOCK, entPaymentPbbIprotax.getKdBlock());
                pstPaymentPbbIprotax.setString(FLD_NOURUT, entPaymentPbbIprotax.getNoUrut());
                pstPaymentPbbIprotax.setString(FLD_NOJNSOP, entPaymentPbbIprotax.getNoJnsOp());
                pstPaymentPbbIprotax.setString(FLD_THNPAJAKSPPT, entPaymentPbbIprotax.getThnPajakSppt());
                pstPaymentPbbIprotax.setInt(FLD_PEMBAYARANSPPTKE, entPaymentPbbIprotax.getPembayaranSpptKe());
                pstPaymentPbbIprotax.setDouble(FLD_JMLDENDASPPT, entPaymentPbbIprotax.getJmlDendaSppt());
                pstPaymentPbbIprotax.setDouble(FLD_JMLPBBYGDIBAYAR, entPaymentPbbIprotax.getJmlPbbYgDibayar());
                pstPaymentPbbIprotax.setString(FLD_URAIANBAYARSPPT, entPaymentPbbIprotax.getUraianBayarSppt());
                pstPaymentPbbIprotax.setString(FLD_KDSUMBERDATA, entPaymentPbbIprotax.getKdSumberData());
                pstPaymentPbbIprotax.setDate(FLD_TGLPEMBAYARANSPPT, entPaymentPbbIprotax.getTglPembayaranSppt());
                pstPaymentPbbIprotax.setString(FLD_KDBANKTUNGGAL, entPaymentPbbIprotax.getKdBankTunggal());
                pstPaymentPbbIprotax.setString(FLD_KDBANKPERSEPSI, entPaymentPbbIprotax.getKdBankPersepsi());
                pstPaymentPbbIprotax.setString(FLD_KDTP, entPaymentPbbIprotax.getKdTp());
                pstPaymentPbbIprotax.setString(FLD_NOTRANSAKSIBYRSPPT, entPaymentPbbIprotax.getNoTransaksiByrSppt());
                pstPaymentPbbIprotax.setString(FLD_NOTRANSAKSIBYRSPPTKOLEKTIF, entPaymentPbbIprotax.getNoTransaksiByrSpptKolektif());
                pstPaymentPbbIprotax.setString(FLD_NOTRANSAKSIBYRSPPTBANK, entPaymentPbbIprotax.getNoTransaksiByrSpptBank());
                pstPaymentPbbIprotax.setDate(FLD_TGLREKAMBYRSPPT, entPaymentPbbIprotax.getTglRekamByrSppt());
                pstPaymentPbbIprotax.setString(FLD_USERBANKREKAM, entPaymentPbbIprotax.getUserBankRekam());
                pstPaymentPbbIprotax.setString(FLD_DATAPEMBAYARAN, entPaymentPbbIprotax.getDataPembayaran());
                pstPaymentPbbIprotax.update();
                return entPaymentPbbIprotax.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbIprotax(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((PaymentPbbIprotax) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstPaymentPbbIprotax pstPaymentPbbIprotax = new PstPaymentPbbIprotax(oid);
            pstPaymentPbbIprotax.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbIprotax(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(PaymentPbbIprotax entPaymentPbbIprotax) throws DBException {
        try {
            PstPaymentPbbIprotax pstPaymentPbbIprotax = new PstPaymentPbbIprotax(0);
            pstPaymentPbbIprotax.setString(FLD_KDPROVINSI, entPaymentPbbIprotax.getKdProvinsi());
            pstPaymentPbbIprotax.setString(FLD_KDDATI2, entPaymentPbbIprotax.getKdDati2());
            pstPaymentPbbIprotax.setString(FLD_KDKECAMATAN, entPaymentPbbIprotax.getKdKecamatan());
            pstPaymentPbbIprotax.setString(FLD_KDKELURAHAN, entPaymentPbbIprotax.getKdKelurahan());
            pstPaymentPbbIprotax.setString(FLD_KDBLOCK, entPaymentPbbIprotax.getKdBlock());
            pstPaymentPbbIprotax.setString(FLD_NOURUT, entPaymentPbbIprotax.getNoUrut());
            pstPaymentPbbIprotax.setString(FLD_NOJNSOP, entPaymentPbbIprotax.getNoJnsOp());
            pstPaymentPbbIprotax.setString(FLD_THNPAJAKSPPT, entPaymentPbbIprotax.getThnPajakSppt());
            pstPaymentPbbIprotax.setInt(FLD_PEMBAYARANSPPTKE, entPaymentPbbIprotax.getPembayaranSpptKe());
            pstPaymentPbbIprotax.setDouble(FLD_JMLDENDASPPT, entPaymentPbbIprotax.getJmlDendaSppt());
            pstPaymentPbbIprotax.setDouble(FLD_JMLPBBYGDIBAYAR, entPaymentPbbIprotax.getJmlPbbYgDibayar());
            pstPaymentPbbIprotax.setString(FLD_URAIANBAYARSPPT, entPaymentPbbIprotax.getUraianBayarSppt());
            pstPaymentPbbIprotax.setString(FLD_KDSUMBERDATA, entPaymentPbbIprotax.getKdSumberData());
            pstPaymentPbbIprotax.setDate(FLD_TGLPEMBAYARANSPPT, entPaymentPbbIprotax.getTglPembayaranSppt());
            pstPaymentPbbIprotax.setString(FLD_KDBANKTUNGGAL, entPaymentPbbIprotax.getKdBankTunggal());
            pstPaymentPbbIprotax.setString(FLD_KDBANKPERSEPSI, entPaymentPbbIprotax.getKdBankPersepsi());
            pstPaymentPbbIprotax.setString(FLD_KDTP, entPaymentPbbIprotax.getKdTp());
            pstPaymentPbbIprotax.setString(FLD_NOTRANSAKSIBYRSPPT, entPaymentPbbIprotax.getNoTransaksiByrSppt());
            pstPaymentPbbIprotax.setString(FLD_NOTRANSAKSIBYRSPPTKOLEKTIF, entPaymentPbbIprotax.getNoTransaksiByrSpptKolektif());
            pstPaymentPbbIprotax.setString(FLD_NOTRANSAKSIBYRSPPTBANK, entPaymentPbbIprotax.getNoTransaksiByrSpptBank());
            pstPaymentPbbIprotax.setDate(FLD_TGLREKAMBYRSPPT, entPaymentPbbIprotax.getTglRekamByrSppt());
            pstPaymentPbbIprotax.setString(FLD_USERBANKREKAM, entPaymentPbbIprotax.getUserBankRekam());
            pstPaymentPbbIprotax.setString(FLD_DATAPEMBAYARAN, entPaymentPbbIprotax.getDataPembayaran());
            
            pstPaymentPbbIprotax.insert();
            entPaymentPbbIprotax.setOID(1);
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbIprotax(0), DBException.UNKNOWN);
        }
        return entPaymentPbbIprotax.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((PaymentPbbIprotax) entity);
    }

    public static void resultToObject(ResultSet rs, PaymentPbbIprotax entPaymentPbbIprotax) {
        try {
            
            //entPaymentPbbIprotax.setOID(rs.getLong(PstPaymentPbbRaversalIprotax.fieldNames[PstPaymentPbbRaversalIprotax.FLD_IDRAVERSAL]));
            entPaymentPbbIprotax.setKdProvinsi(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDPROVINSI]));
            entPaymentPbbIprotax.setKdDati2(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDDATI2]));
            entPaymentPbbIprotax.setKdKecamatan(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDKECAMATAN]));
            entPaymentPbbIprotax.setKdKelurahan(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDKELURAHAN]));
            entPaymentPbbIprotax.setKdBlock(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDBLOCK]));
            entPaymentPbbIprotax.setNoUrut(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_NOURUT]));
            entPaymentPbbIprotax.setNoJnsOp(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_NOJNSOP]));
            entPaymentPbbIprotax.setThnPajakSppt(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_THNPAJAKSPPT]));
            entPaymentPbbIprotax.setPembayaranSpptKe(rs.getInt(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_PEMBAYARANSPPTKE]));
            entPaymentPbbIprotax.setJmlDendaSppt(rs.getDouble(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_JMLDENDASPPT]));
            entPaymentPbbIprotax.setJmlPbbYgDibayar(rs.getDouble(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_JMLPBBYGDIBAYAR]));
            entPaymentPbbIprotax.setUraianBayarSppt(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_URAIANBAYARSPPT]));
            entPaymentPbbIprotax.setKdSumberData(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDSUMBERDATA]));
            entPaymentPbbIprotax.setTglPembayaranSppt(rs.getDate(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_TGLPEMBAYARANSPPT]));
            entPaymentPbbIprotax.setKdBankTunggal(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDBANKTUNGGAL]));
            entPaymentPbbIprotax.setKdBankPersepsi(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDBANKPERSEPSI]));
            entPaymentPbbIprotax.setKdTp(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDTP]));
            entPaymentPbbIprotax.setNoTransaksiByrSppt(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_NOTRANSAKSIBYRSPPT]));
            entPaymentPbbIprotax.setNoTransaksiByrSpptKolektif(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_NOTRANSAKSIBYRSPPTKOLEKTIF]));
            entPaymentPbbIprotax.setNoTransaksiByrSpptBank(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_NOTRANSAKSIBYRSPPTBANK]));
            entPaymentPbbIprotax.setTglRekamByrSppt(rs.getDate(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_TGLREKAMBYRSPPT]));
            entPaymentPbbIprotax.setUserBankRekam(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_USERBANKREKAM]));
            entPaymentPbbIprotax.setDataPembayaran(rs.getString(PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_DATAPEMBAYARAN]));
            
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
            String sql = "SELECT * FROM " + TBL_PAYMENTPBBIPROTAX;
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
                PaymentPbbIprotax entPaymentPbbIprotax = new PaymentPbbIprotax();
                resultToObject(rs, entPaymentPbbIprotax);
                lists.add(entPaymentPbbIprotax);
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

    public static boolean checkOID(long entPaymentPbbIprotaxId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_PAYMENTPBBIPROTAX + " WHERE "
                    + PstPaymentPbbIprotax.fieldNames[PstPaymentPbbIprotax.FLD_KDPROVINSI] + " = " + entPaymentPbbIprotaxId;
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
            String sql = "SELECT COUNT(NOP) AS JML FROM VIEW_PEMBAYARAN_PBB ";
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
    
    
    
    
    
    public static boolean checkPaymentPBBIprotax(String idPayment){
        boolean checkHistory=true;
        DBResultSet dbrs = null;
        String sql = "";
        try {
            
            sql = "SELECT * FROM VIEW_PEMBAYARAN_PBB "+
                  " WHERE NO_TRANSAKSI_BYR_SPPT_BANK='"+idPayment+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            
            while (rs.next()) {
                checkHistory=false;
            }
            
            rs.close();
            return checkHistory;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        
        return checkHistory;
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
                    PaymentPbbIprotax entPaymentPbbIprotax = (PaymentPbbIprotax) list.get(ls);
                    if (oid == entPaymentPbbIprotax.getOID()) {
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
}
