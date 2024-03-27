package com.dimata.wpupload.entity.paymentchanel;


import com.dimata.dtaxintegration.entity.inquery.InqueryProses;
import com.dimata.dtaxintegration.entity.inquery.Simpatda;
import com.dimata.dtaxintegration.entity.logsApi.PstLogApi;
import com.dimata.dtaxintegration.entity.tagihan.Tagihan;
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.services.WebServices;
import com.dimata.util.Command;
import com.dimata.webclient.AppSetting;
import com.dimata.webclient.Inquery;
import com.dimata.wpupload.entity.logspaymentwp.LogPaymentWp;
import com.dimata.wpupload.entity.logspaymentwp.PstLogPaymentWp;
import com.dimata.wpupload.entity.paymentchanel.PaymentChanel;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Vector;
import org.apache.commons.codec.digest.DigestUtils; 
import org.json.JSONObject;

public class PstPaymentChanel extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_PAYMENT_CHANEL = "payment_chanel";
    public static final int FLD_PAYMENT_ID = 0;
    public static final int FLD_PAYMENT_NAME = 1;
    public static final int FLD_PAYMENT_CODE = 2;
    public static final int FLD_IDX = 3;
    public static final int FLD_CODE = 4;
    public static final int FLD_QRVALUE = 5;
    public static final int FLD_BILL_NUMBER = 6;

    public static String[] fieldNames = {
        "PAYMENT_ID",
        "PAYMENT_NAME",
        "PAYMENT_CODE",
        "IDX",
        "CODE",
        "QRVALUE",
        "BILLNUMBER"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID, 
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_LONG
    };
    
    public static final int QRIS = 1;
    public static final int VA = 2;
    
    public static final int USE_LOG = 1;
    public static final int UNUSE_LOG = 2;

    public PstPaymentChanel() {
    }

    public PstPaymentChanel(int i) throws DBException {
        super(new PstPaymentChanel());
    }

    public PstPaymentChanel(String sOid) throws DBException {
        super(new PstPaymentChanel(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstPaymentChanel(long lOid) throws DBException {
        super(new PstPaymentChanel(0));
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
        return TBL_PAYMENT_CHANEL;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstPaymentChanel().getClass().getName();
    }

    public static PaymentChanel fetchExc(long oid) throws DBException {
        try {
            PaymentChanel entPaymentChanel = new PaymentChanel();
            PstPaymentChanel pstPaymentChanel = new PstPaymentChanel(oid);
            entPaymentChanel.setOID(oid);
            entPaymentChanel.setPaymentName(pstPaymentChanel.getString(FLD_PAYMENT_NAME));
            entPaymentChanel.setPaymentCode(pstPaymentChanel.getString(FLD_PAYMENT_CODE));
            entPaymentChanel.setIdx(pstPaymentChanel.getInt(FLD_IDX));
            return entPaymentChanel;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentChanel(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        PaymentChanel entPaymentChanel = fetchExc(entity.getOID());
        entity = (Entity) entPaymentChanel;
        return entPaymentChanel.getOID();
    }

    public static synchronized long updateExc(PaymentChanel entPaymentChanel) throws DBException {
        try {
            if (entPaymentChanel.getOID() != 0) {
                PstPaymentChanel pstPaymentChanel = new PstPaymentChanel(entPaymentChanel.getOID());
                pstPaymentChanel.setString(FLD_PAYMENT_NAME, entPaymentChanel.getPaymentName());
                pstPaymentChanel.setString(FLD_PAYMENT_CODE, entPaymentChanel.getPaymentCode());
                pstPaymentChanel.setInt(FLD_IDX, entPaymentChanel.getIdx());
                pstPaymentChanel.update();
                return entPaymentChanel.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentChanel(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((PaymentChanel) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstPaymentChanel pstPaymentChanel = new PstPaymentChanel(oid);
            pstPaymentChanel.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentChanel(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(PaymentChanel entPaymentChanel) throws DBException {
        try {
            PstPaymentChanel pstPaymentChanel = new PstPaymentChanel(0);
            pstPaymentChanel.setString(FLD_PAYMENT_NAME, entPaymentChanel.getPaymentName());
            pstPaymentChanel.setString(FLD_PAYMENT_CODE, entPaymentChanel.getPaymentCode());
            pstPaymentChanel.setInt(FLD_IDX, entPaymentChanel.getIdx());
            pstPaymentChanel.setString(FLD_QRVALUE, entPaymentChanel.getQrvalue());
            pstPaymentChanel.setLong(FLD_BILL_NUMBER, entPaymentChanel.getBillnumber());
            pstPaymentChanel.insert();
            entPaymentChanel.setOID(pstPaymentChanel.getLong(FLD_PAYMENT_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentChanel(0), DBException.UNKNOWN);
        }
        return entPaymentChanel.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((PaymentChanel) entity);
    }

    public static void resultToObject(ResultSet rs, PaymentChanel entPaymentChanel) {
        try {
            entPaymentChanel.setOID(rs.getLong(PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_ID]));
            entPaymentChanel.setPaymentName(rs.getString(PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_NAME]));
            entPaymentChanel.setPaymentCode(rs.getString(PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_CODE]));
            entPaymentChanel.setIdx(rs.getInt(PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_IDX]));
            entPaymentChanel.setCode(rs.getString(PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_CODE]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Vector listAll() {
        return list(0, 500, "", "");
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + TBL_PAYMENT_CHANEL;
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
                PaymentChanel entPaymentChanel = new PaymentChanel();
                resultToObject(rs, entPaymentChanel);
                lists.add(entPaymentChanel);
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

    public static boolean checkOID(long entPaymentChanelId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_PAYMENT_CHANEL + " WHERE "
                    + PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_ID] + " = " + entPaymentChanelId;
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
            String sql = "SELECT COUNT(" + PstPaymentChanel.fieldNames[PstPaymentChanel.FLD_PAYMENT_ID] + ") FROM " + TBL_PAYMENT_CHANEL;
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
                    PaymentChanel entPaymentChanel = (PaymentChanel) list.get(ls);
                    if (oid == entPaymentChanel.getOID()) {
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
    
    public static JSONObject generateQris(long kodeBayar, Simpatda simpatda, String ip){
        String qrisValue = "";
        JSONObject objResponse = new JSONObject();
        PstLogApi pstLog = new PstLogApi();
                
        long contributionId = kodeBayar;
        String apiName = "generateQrisPost";
        String modul = "Pembayaran"; 
        long res = 0;
                
        try {
            /*webservice bpd*/
            Inquery inquery = new Inquery();
            InqueryProses inqueryProses = new InqueryProses();
            inqueryProses.setsUser(AppSetting.USERNAME_PHR);
            inqueryProses.setsPassword(AppSetting.PWD_PHR);
            inqueryProses.setsInstansi(AppSetting.INSTANSI_PHR);
            inqueryProses.setsNoId(""+contributionId);
            
            Tagihan tagihanGenerate = new Tagihan();
            Vector listReportDetail = inquery.InqueryPHR(inqueryProses);
            if(listReportDetail.size()>0){
                for (int i = 0; i < listReportDetail.size(); i++) {
                    Tagihan tagihan = (Tagihan) listReportDetail.get(i);
                    if(simpatda.getTahunSimpatda().equals(tagihan.getTahun()) 
                            && simpatda.getBulanSimpatda().equals(tagihan.getBulan())){
                        tagihanGenerate = (Tagihan) listReportDetail.get(i);
                        break;
                    }
                }
            }
    
            String signature = AppSetting.MPAN+AppSetting.TERMINAL+AppSetting.PRODUK+contributionId+AppSetting.KEY;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(signature.getBytes(StandardCharsets.UTF_8));
            String hash = Utility.toHexString(bytes);
            hash = hash.replace(" ", "");
            
            String data = "{"
                    + "\"merchantPan\" : \""+AppSetting.MPAN+"\","
                    + "\"terminalUser\" :\""+AppSetting.TERMINAL+"\","
                    + "\"productCode\" :\""+AppSetting.PRODUK+"\","
                    + "\"hashcodeKey\" : \""+hash+"\"," 
                    + "\"billNumber\" : \""+contributionId+"\","
                    + "\"recordId\" : \""+tagihanGenerate.getId()+"\""
                    + "}";
            
            //log request   
            res = pstLog.setLogApiBpd(apiName, "Berhasil", modul, "00", data, "", 0, ip);
            objResponse = WebServices.postAPI(data, AppSetting.IP_BANK_BPD_QRIS+"generateQrisPost");
            String msg = ""+(objResponse.isNull("objResponse") ?  "Berhasil" : objResponse.getString("objResponse"));
            pstLog.setLogApiBpd(apiName, msg, modul, "00", "", objResponse.toString(), res, ip);
            
            System.out.println("Insert Log Sukses"); 
        } catch (Exception exc){
            System.out.println("Error Add Log"+exc);
            pstLog.setLogApiBpd(apiName, exc.getMessage(), modul, "00", "", "", res,ip);
        } 
        return objResponse;
    }
    
    public static JSONObject chekPayQris(long kodeBayar, int log, String ip){
        String qrisValue = "";
        JSONObject objResponse = new JSONObject();
        PstLogApi pstLog = new PstLogApi();
                
        long contributionId = kodeBayar;
        try {
            /*webservice bpd*/ 
            Inquery inquery = new Inquery();
            InqueryProses inqueryProses = new InqueryProses();
            inqueryProses.setsUser(AppSetting.USERNAME_PHR);
            inqueryProses.setsPassword(AppSetting.PWD_PHR);
            inqueryProses.setsInstansi(AppSetting.INSTANSI_PHR);
            inqueryProses.setsNoId(""+contributionId);
            
            String wrClause = " "+PstLogPaymentWp.fieldNames[PstLogPaymentWp.FLD_BILLNUMBER]+" = "+kodeBayar;
            LogPaymentWp logpay = new LogPaymentWp();
            Vector getPay = PstLogPaymentWp.list(0, 0, wrClause, "");
            if(getPay.size()>0){
                logpay = (LogPaymentWp) getPay.get(0);
                
                String signature = AppSetting.MPAN+AppSetting.TERMINAL+logpay.getQrvalue()+AppSetting.KEY;
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] bytes = digest.digest(signature.getBytes(StandardCharsets.UTF_8));
                String hash = Utility.toHexString(bytes); 
                hash = hash.replace(" ", "");
                
                
                String data = "{"
                    + "\"merchantPan\" : \""+AppSetting.MPAN+"\","
                    + "\"terminalUser\" :\""+AppSetting.TERMINAL+"\","
                    + "\"qrValue\" :\""+logpay.getQrvalue()+"\","
                    + "\"hashcodeKey\" : \""+hash+"\"" 
                    + "}";
                //log request 
                String apiName = "getTrxByQrString";
                String modul = "Pembayaran";
                if(log == USE_LOG){
                    long res = pstLog.setLogApiBpd(apiName, "Berhasil", modul, "00", data, "", 0, ip);
                    objResponse = WebServices.postAPI(data, AppSetting.IP_BANK_BPD_QRIS+""+apiName);
                    pstLog.setLogApiBpd(apiName, "", modul, "00", "", objResponse.toString(), res, ip);
                }else{
                    objResponse = WebServices.postAPI(data, AppSetting.IP_BANK_BPD_QRIS+""+apiName);
                }
            }
             
            System.out.println("Insert Log Sukses");
        } catch (Exception exc){
            System.out.println("Error Add Log"+exc);
        }
        return objResponse;
    }
}
