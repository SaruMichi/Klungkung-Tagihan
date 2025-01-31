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

public class PstPaymentPbbHistory extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_PAYMENTPBB = "PEMBAYARAN_SPPT_BANK_HISTORY";
    public static final int FLD_IDPAYMENT = 0;
    public static final int FLD_KDPROPINSI = 1;
    public static final int FLD_KDDATI2 = 2;
    public static final int FLD_KDKECAMATA = 3;
    public static final int FLD_KDKELURAHAN = 4;
    public static final int FLD_KDBLOK = 5;
    public static final int FLD_NOURUT = 6;
    public static final int FLD_KDJNSOP = 7;
    public static final int FLD_THNPAJAKSPPT = 8;
    public static final int FLD_PEMBAYARANSPPTKE = 9;
    public static final int FLD_KDKANWILBANK = 10;
    public static final int FLD_KDKPPBBBANK = 11;
    public static final int FLD_KDBANKTUNGGAL = 12;
    public static final int FLD_KDBANKPERSEPSI = 13;
    public static final int FLD_KDTP = 14;
    public static final int FLD_DENDASPPT = 15;
    public static final int FLD_JMLSPPTYGDIBAYAR = 16;
    public static final int FLD_TGLPEMBAYARANSPPT = 17;
    public static final int FLD_TGLREKAMBYRSPPT = 18;
    public static final int FLD_NIPREKAMBYRSPPT = 19;
    public static final int FLD_ID_PAYMENT_BANK = 20;
    public static final int FLD_NOP=21;
    public static final int FLD_STATUS=22;
    public static final int FLD_ID_KEY=23;
    
   public static String[] fieldNames = {
        "ID_SPPT",
        "KD_PROPINSI",
        "KD_DATI2",
        "KD_KECAMATAN",
        "KD_KELURAHAN",
        "KD_BLOK",
        "NO_URUT",
        "KD_JNS_OP",
        "THN_PAJAK_SPPT",
        "PEMBAYARAN_SPPT_KE",
        "KD_KANWIL_BANK",
        "KD_KPPBB_BANK",
        "KD_BANK_TUNGGAL",
        "KD_BANK_PERSEPSI",
        "KD_TP",
        "DENDA_SPPT",
        "JML_SPPT_YG_DIBAYAR",
        "TGL_PEMBAYARAN_SPPT",
        "TGL_REKAM_BYR_SPPT",
        "NIP_REKAM_BYR_SPPT",
        "ID_PAYMENT_BANK",
        "NOP",
        "STATUS",
        "ID_KEY"
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
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_LONG,
        TYPE_LONG,
        TYPE_INT,
        TYPE_STRING
    };

    public PstPaymentPbbHistory() {
    }

    public PstPaymentPbbHistory(int i) throws DBException {
        super(new PstPaymentPbbHistory());
    }

    public PstPaymentPbbHistory(String sOid) throws DBException {
        super(new PstPaymentPbbHistory(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstPaymentPbbHistory(long lOid) throws DBException {
        super(new PstPaymentPbbHistory(0));
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
        return TBL_PAYMENTPBB;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstPaymentPbbHistory().getClass().getName();
    }

    public static PaymentPbb fetchExc(long oid) throws DBException {
        try {
            PaymentPbb entPaymentPbb = new PaymentPbb();
            PstPaymentPbbHistory pstPaymentPbb = new PstPaymentPbbHistory(oid);
            entPaymentPbb.setOID(oid);
            entPaymentPbb.setKdPropinsi(pstPaymentPbb.getString(FLD_KDPROPINSI));
            entPaymentPbb.setKdDati2(pstPaymentPbb.getString(FLD_KDDATI2));
            entPaymentPbb.setKdKecamata(pstPaymentPbb.getString(FLD_KDKECAMATA));
            entPaymentPbb.setKdKelurahan(pstPaymentPbb.getString(FLD_KDKELURAHAN));
            entPaymentPbb.setKdBlok(pstPaymentPbb.getString(FLD_KDBLOK));
            entPaymentPbb.setNoUrut(pstPaymentPbb.getString(FLD_NOURUT));
            entPaymentPbb.setKdJnsOp(pstPaymentPbb.getString(FLD_KDJNSOP));
            entPaymentPbb.setThnPajakSppt(pstPaymentPbb.getString(FLD_THNPAJAKSPPT));
            entPaymentPbb.setPembayaranSpptKe(pstPaymentPbb.getdouble(FLD_PEMBAYARANSPPTKE));
            entPaymentPbb.setKdKanwilBank(pstPaymentPbb.getString(FLD_KDKANWILBANK));
            entPaymentPbb.setKdKppbbBank(pstPaymentPbb.getString(FLD_KDKPPBBBANK));
            entPaymentPbb.setKdBankTunggal(pstPaymentPbb.getString(FLD_KDBANKTUNGGAL));
            entPaymentPbb.setKdBankPersepsi(pstPaymentPbb.getString(FLD_KDBANKPERSEPSI));
            entPaymentPbb.setKdTp(pstPaymentPbb.getString(FLD_KDTP));
            entPaymentPbb.setDendaSppt(pstPaymentPbb.getdouble(FLD_DENDASPPT));
            entPaymentPbb.setJmlSpptYgDibayar(pstPaymentPbb.getdouble(FLD_JMLSPPTYGDIBAYAR));
            entPaymentPbb.setTglPembayaranSppt(pstPaymentPbb.getDate(FLD_TGLPEMBAYARANSPPT));
            entPaymentPbb.setTglRekamByrSppt(pstPaymentPbb.getDate(FLD_TGLREKAMBYRSPPT));
            entPaymentPbb.setNipRekamByrSppt(pstPaymentPbb.getString(FLD_NIPREKAMBYRSPPT));
            entPaymentPbb.setIdPaymentBank(pstPaymentPbb.getlong(FLD_ID_PAYMENT_BANK));
            entPaymentPbb.setNop(pstPaymentPbb.getlong(FLD_NOP));
            entPaymentPbb.setStatus(pstPaymentPbb.getInt(FLD_STATUS));
            entPaymentPbb.setIdKey(pstPaymentPbb.getString(FLD_ID_KEY));
            
            return entPaymentPbb;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbHistory(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        PaymentPbb entPaymentPbb = fetchExc(entity.getOID());
        entity = (Entity) entPaymentPbb;
        return entPaymentPbb.getOID();
    }

    public static synchronized long updateExc(PaymentPbb entPaymentPbb) throws DBException {
        try {
            if (entPaymentPbb.getOID() != 0) {
                PstPaymentPbbHistory pstPaymentPbb = new PstPaymentPbbHistory(entPaymentPbb.getOID());
                pstPaymentPbb.setString(FLD_KDPROPINSI, entPaymentPbb.getKdPropinsi());
                pstPaymentPbb.setString(FLD_KDDATI2, entPaymentPbb.getKdDati2());
                pstPaymentPbb.setString(FLD_KDKECAMATA, entPaymentPbb.getKdKecamata());
                pstPaymentPbb.setString(FLD_KDKELURAHAN, entPaymentPbb.getKdKelurahan());
                pstPaymentPbb.setString(FLD_KDBLOK, entPaymentPbb.getKdBlok());
                pstPaymentPbb.setString(FLD_NOURUT, entPaymentPbb.getNoUrut());
                pstPaymentPbb.setString(FLD_KDJNSOP, entPaymentPbb.getKdJnsOp());
                pstPaymentPbb.setString(FLD_THNPAJAKSPPT, entPaymentPbb.getThnPajakSppt());
                pstPaymentPbb.setDouble(FLD_PEMBAYARANSPPTKE, entPaymentPbb.getPembayaranSpptKe());
                pstPaymentPbb.setString(FLD_KDKANWILBANK, entPaymentPbb.getKdKanwilBank());
                pstPaymentPbb.setString(FLD_KDKPPBBBANK, entPaymentPbb.getKdKppbbBank());
                pstPaymentPbb.setString(FLD_KDBANKTUNGGAL, entPaymentPbb.getKdBankTunggal());
                pstPaymentPbb.setString(FLD_KDBANKPERSEPSI, entPaymentPbb.getKdBankPersepsi());
                pstPaymentPbb.setString(FLD_KDTP, entPaymentPbb.getKdTp());
                pstPaymentPbb.setDouble(FLD_DENDASPPT, entPaymentPbb.getDendaSppt());
                pstPaymentPbb.setDouble(FLD_JMLSPPTYGDIBAYAR, entPaymentPbb.getJmlSpptYgDibayar());
                pstPaymentPbb.setDate(FLD_TGLPEMBAYARANSPPT, entPaymentPbb.getTglPembayaranSppt());
                pstPaymentPbb.setDate(FLD_TGLREKAMBYRSPPT, entPaymentPbb.getTglRekamByrSppt());
                pstPaymentPbb.setString(FLD_NIPREKAMBYRSPPT, entPaymentPbb.getNipRekamByrSppt());
                pstPaymentPbb.setLong(FLD_ID_PAYMENT_BANK, entPaymentPbb.getIdPaymentBank());
                pstPaymentPbb.setLong(FLD_NOP, entPaymentPbb.getNop());
                pstPaymentPbb.setInt(FLD_STATUS, entPaymentPbb.getStatus());
                pstPaymentPbb.setString(FLD_ID_KEY, entPaymentPbb.getIdKey());
                
                pstPaymentPbb.update();
                return entPaymentPbb.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbHistory(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((PaymentPbb) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstPaymentPbbHistory pstPaymentPbb = new PstPaymentPbbHistory(oid);
            pstPaymentPbb.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbHistory(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(PaymentPbb entPaymentPbb) throws DBException {
        try {
            PstPaymentPbbHistory pstPaymentPbb = new PstPaymentPbbHistory(0);
            pstPaymentPbb.setString(FLD_KDPROPINSI, entPaymentPbb.getKdPropinsi());
            pstPaymentPbb.setString(FLD_KDDATI2, entPaymentPbb.getKdDati2());
            pstPaymentPbb.setString(FLD_KDKECAMATA, entPaymentPbb.getKdKecamata());
            pstPaymentPbb.setString(FLD_KDKELURAHAN, entPaymentPbb.getKdKelurahan());
            pstPaymentPbb.setString(FLD_KDBLOK, entPaymentPbb.getKdBlok());
            pstPaymentPbb.setString(FLD_NOURUT, entPaymentPbb.getNoUrut());
            pstPaymentPbb.setString(FLD_KDJNSOP, entPaymentPbb.getKdJnsOp());
            pstPaymentPbb.setString(FLD_THNPAJAKSPPT, entPaymentPbb.getThnPajakSppt());
            pstPaymentPbb.setDouble(FLD_PEMBAYARANSPPTKE, entPaymentPbb.getPembayaranSpptKe());
            pstPaymentPbb.setString(FLD_KDKANWILBANK, entPaymentPbb.getKdKanwilBank());
            pstPaymentPbb.setString(FLD_KDKPPBBBANK, entPaymentPbb.getKdKppbbBank());
            pstPaymentPbb.setString(FLD_KDBANKTUNGGAL, entPaymentPbb.getKdBankTunggal());
            pstPaymentPbb.setString(FLD_KDBANKPERSEPSI, entPaymentPbb.getKdBankPersepsi());
            pstPaymentPbb.setString(FLD_KDTP, entPaymentPbb.getKdTp());
            pstPaymentPbb.setDouble(FLD_DENDASPPT, entPaymentPbb.getDendaSppt());
            pstPaymentPbb.setDouble(FLD_JMLSPPTYGDIBAYAR, entPaymentPbb.getJmlSpptYgDibayar());
            pstPaymentPbb.setDate(FLD_TGLPEMBAYARANSPPT, entPaymentPbb.getTglPembayaranSppt());
            pstPaymentPbb.setDate(FLD_TGLREKAMBYRSPPT, entPaymentPbb.getTglRekamByrSppt());
            pstPaymentPbb.setString(FLD_NIPREKAMBYRSPPT, entPaymentPbb.getNipRekamByrSppt());
            pstPaymentPbb.setLong(FLD_ID_PAYMENT_BANK, entPaymentPbb.getIdPaymentBank());
            pstPaymentPbb.setLong(FLD_NOP, entPaymentPbb.getNop());
            pstPaymentPbb.setInt(FLD_STATUS, entPaymentPbb.getStatus());
            pstPaymentPbb.setString(FLD_ID_KEY, entPaymentPbb.getIdKey());
            
            pstPaymentPbb.insert();
            entPaymentPbb.setOID(pstPaymentPbb.getlong(FLD_IDPAYMENT));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbHistory(0), DBException.UNKNOWN);
        }
        return entPaymentPbb.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((PaymentPbb) entity);
    }

    public static void resultToObject(ResultSet rs, PaymentPbb entPaymentPbb) {
        try {
            entPaymentPbb.setOID(rs.getLong(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_IDPAYMENT]));
            entPaymentPbb.setKdPropinsi(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDPROPINSI]));
            entPaymentPbb.setKdDati2(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDDATI2]));
            entPaymentPbb.setKdKecamata(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDKECAMATA]));
            entPaymentPbb.setKdKelurahan(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDKELURAHAN]));
            entPaymentPbb.setKdBlok(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDBLOK]));
            entPaymentPbb.setNoUrut(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_NOURUT]));
            entPaymentPbb.setKdJnsOp(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDJNSOP]));
            entPaymentPbb.setThnPajakSppt(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_THNPAJAKSPPT]));
            entPaymentPbb.setPembayaranSpptKe(rs.getDouble(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_PEMBAYARANSPPTKE]));
            entPaymentPbb.setKdKanwilBank(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDKANWILBANK]));
            entPaymentPbb.setKdKppbbBank(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDKPPBBBANK]));
            entPaymentPbb.setKdBankTunggal(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDBANKTUNGGAL]));
            entPaymentPbb.setKdBankPersepsi(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDBANKPERSEPSI]));
            entPaymentPbb.setKdTp(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_KDTP]));
            entPaymentPbb.setDendaSppt(rs.getDouble(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_DENDASPPT]));
            entPaymentPbb.setJmlSpptYgDibayar(rs.getDouble(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_JMLSPPTYGDIBAYAR]));
            entPaymentPbb.setTglPembayaranSppt(rs.getDate(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_TGLPEMBAYARANSPPT]));
            entPaymentPbb.setTglRekamByrSppt(rs.getDate(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_TGLREKAMBYRSPPT]));
            entPaymentPbb.setNipRekamByrSppt(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_NIPREKAMBYRSPPT]));
            entPaymentPbb.setIdPaymentBank(rs.getLong(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_ID_PAYMENT_BANK]));
            entPaymentPbb.setNop(rs.getLong(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_NOP]));
            entPaymentPbb.setStatus(rs.getInt(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_STATUS]));
            entPaymentPbb.setIdKey(rs.getString(PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_ID_KEY]));
            
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
            String sql = "SELECT * FROM " + TBL_PAYMENTPBB;
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
                PaymentPbb entPaymentPbb = new PaymentPbb();
                resultToObject(rs, entPaymentPbb);
                lists.add(entPaymentPbb);
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

    public static boolean checkOID(long entPaymentPbbId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_PAYMENTPBB + " WHERE "
                    + PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_IDPAYMENT] + " = " + entPaymentPbbId;
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
            String sql = "SELECT COUNT(" + PstPaymentPbbHistory.fieldNames[PstPaymentPbbHistory.FLD_IDPAYMENT] + ") FROM " + TBL_PAYMENTPBB;
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
                    PaymentPbb entPaymentPbb = (PaymentPbb) list.get(ls);
                    if (oid == entPaymentPbb.getOID()) {
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

