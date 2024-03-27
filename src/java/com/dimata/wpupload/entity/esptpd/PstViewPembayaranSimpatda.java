

package com.dimata.wpupload.entity.esptpd;

import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.qdep.db.I_DBInterface;
import com.dimata.qdep.db.I_DBType;
import com.dimata.qdep.entity.Entity;
import com.dimata.qdep.entity.I_PersintentExc;
import com.dimata.util.lang.I_Language;
import com.dimata.webclient.AppSetting;
import com.dimata.wpupload.entity.wpuser.PstAppUserWP;
import java.sql.ResultSet;
import java.util.Vector;

public class PstViewPembayaranSimpatda 
extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language{
    public static final String TBL_VIEW_PEMBAYARAN_SIMPATDA = "VIEW_PEMBAYARAN_SIMPATDA";
    public static final int FLD_KODE_SUBLOKASI = 0;
    public static final int FLD_SERVICE_TAX = 1;
    public static final int FLD_VROWID = 2;
    public static final int FLD_NO_SPTPD = 3;
    public static final int FLD_TIPE_DAFTAR= 4; 
    public static final int FLD_TGL_SSPD = 5; 
    public static final int FLD_NPWPD= 6; 
    public static final int FLD_KODE_PAJAK = 7; 
    public static final int FLD_NO_REKENING = 8; 
    public static final int FLD_MASA_PAJAK = 9; 
    public static final int FLD_TAHUN_PAJAK = 10; 
    public static final int FLD_JENIS_SETORAN = 11;
    public static final int FLD_NO_BUKTI_SETORAN = 12;
    public static final int FLD_TOTAL_OMZET = 13;
    public static final int FLD_TARIF = 14;
    public static final int FLD_TOTAL_PAJAK = 15;
    public static final int FLD_KETERANGAN = 16;
    public static final int FLD_TGL_REKAM = 17;
    public static final int FLD_ID_REKAM = 18;
    public static final int FLD_TGL_UBAH = 19;
    public static final int FLD_ID_UBAH= 20;
    public static final int FLD_SETORAN = 21;
    public static final int FLD_STATUS_DATA = 22;
    public static final int FLD_NAMA_PENYETOR= 23;
    
    public static String[] fieldNames = {
        "KODE_SUBLOKASI",
        "SERVICE_TAX",
        "VROWID",
        "NO_SPTPD",
        "TIPE_DAFTAR",
        "TGL_SSPD",
        "NPWPD",
        "KODE_PAJAK",
        "NO_REKENING",
        "MASA_PAJAK",
        "TAHUN_PAJAK",
        "JENIS_SETORAN",
        "NO_BUKTI_SETORAN",
        "TOTAL_OMZET",
        "TARIF",
        "TOTAL_PAJAK",
        "KETERANGAN",
        "TGL_REKAM",
        "ID_REKAM",
        "TGL_UBAH",
        "ID_UBAH",
        "SETORAN",
        "STATUS_DATA",
        "NAMA_PENYETOR",
        
        
    };
    
    public static int[] fieldTypes = {
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING, 
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
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
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        
     };
    
    public PstViewPembayaranSimpatda() {}

    public PstViewPembayaranSimpatda(int i) throws DBException {
       super(new PstViewPembayaranSimpatda());
    }

    public PstViewPembayaranSimpatda(String sOid) throws DBException {
        super(new PstViewPembayaranSimpatda(0));
        if(!locate(sOid))
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        else
            return;
    }

    public PstViewPembayaranSimpatda(long lOid) throws DBException {
        super(new PstViewPembayaranSimpatda(0));
        String sOid = "0";
        try {
            sOid = String.valueOf(lOid);
        }catch(Exception e) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        if(!locate(sOid))
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        else
            return;
    }

    @Override
    public int getFieldSize() {
        return fieldNames.length;
    }

    @Override
    public String getTableName() {
        return TBL_VIEW_PEMBAYARAN_SIMPATDA;
    }

    @Override
    public String[] getFieldNames() {
        return fieldNames;
    }

    @Override
    public int[] getFieldTypes() {
        return fieldTypes;
    }
    
    public static void resultToObjectJoin(ResultSet rs, ViewPembayaranSimpatda entViewPembayaranSimpatda) {
    try {
        entViewPembayaranSimpatda.setKodeSubLokasi(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_KODE_SUBLOKASI]));
        entViewPembayaranSimpatda.setServiceTax(rs.getDouble(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_SERVICE_TAX]));
        entViewPembayaranSimpatda.setVrowid(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_VROWID]));
        entViewPembayaranSimpatda.setNoSptpd(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_NO_SPTPD]));
        entViewPembayaranSimpatda.setTipeDaftar(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TIPE_DAFTAR]));
        entViewPembayaranSimpatda.setTglSspd(rs.getDate(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TGL_SSPD]));
        entViewPembayaranSimpatda.setNpwpd(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_NPWPD]));
        entViewPembayaranSimpatda.setKodePajak(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_KODE_PAJAK]));
        entViewPembayaranSimpatda.setNoRekening(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_NO_REKENING]));
        entViewPembayaranSimpatda.setMasaPajak(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_MASA_PAJAK]));
        entViewPembayaranSimpatda.setTahunPajak(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TAHUN_PAJAK]));
        entViewPembayaranSimpatda.setJenisSetoran(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_JENIS_SETORAN]));
        entViewPembayaranSimpatda.setNoBuktiSetoran(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_NO_BUKTI_SETORAN]));
        entViewPembayaranSimpatda.setTotalOmzet(rs.getDouble(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TOTAL_OMZET]));
        entViewPembayaranSimpatda.setTarif(rs.getDouble(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TARIF]));
        entViewPembayaranSimpatda.setTotalPajak(rs.getDouble(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TOTAL_PAJAK]));
        entViewPembayaranSimpatda.setKeterangan(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_KETERANGAN]));
        entViewPembayaranSimpatda.setTglRekam(rs.getDate(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TGL_REKAM]));
        entViewPembayaranSimpatda.setIdRekam(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_ID_REKAM]));
        entViewPembayaranSimpatda.setTglUbah(rs.getDate(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_TGL_UBAH]));
        entViewPembayaranSimpatda.setIdUbah(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_ID_UBAH]));
        entViewPembayaranSimpatda.setSetoran(rs.getDouble(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_SETORAN]));
        entViewPembayaranSimpatda.setStatusData(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_STATUS_DATA]));
        entViewPembayaranSimpatda.setNamaPenyetor(rs.getString(PstViewPembayaranSimpatda.fieldNames[PstViewPembayaranSimpatda.FLD_NAMA_PENYETOR]));
        
   } catch (Exception e) {
   
   }
   }
    
   public static Vector listJoin(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql ="SELECT " +
                        " "+TBL_VIEW_PEMBAYARAN_SIMPATDA+".* " +
                        " FROM " +
                        " "+TBL_VIEW_PEMBAYARAN_SIMPATDA+" " +
                        " INNER JOIN "+PstAppUserWP.TBL_APPUSERWP+" ON "+TBL_VIEW_PEMBAYARAN_SIMPATDA+"."+fieldNames[FLD_NPWPD]+" = "+PstAppUserWP.TBL_APPUSERWP+"."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD]+"";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            
            //20150930 disable sementara sampai nemu cara limit di oracle
            if(AppSetting.SQL_VERSION==AppSetting.DBSVR_ORACLE){
                 sql = sql + "";
            }else{
                if (limitStart == 0 && recordToGet == 0) {
                    sql = sql + "";
                } else {
                    sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                }
            }
            
            
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                ViewPembayaranSimpatda ViewPembayaranSimpatda = new ViewPembayaranSimpatda();
                resultToObjectJoin(rs, ViewPembayaranSimpatda);
                lists.add(ViewPembayaranSimpatda);
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

    @Override
    public String getPersistentName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long fetchExc(Entity ent) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long updateExc(Entity ent) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long deleteExc(Entity ent) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insertExc(Entity ent) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}




