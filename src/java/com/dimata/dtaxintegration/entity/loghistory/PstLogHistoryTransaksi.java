package com.dimata.dtaxintegration.entity.loghistory;
/**
 *
 * @author dimata005
 */

/* package java */
import java.sql.*;

/* package qdep */
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstLogHistoryTransaksi extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_LOGHISTORYTRANSAKSI = "LOG_HISTORY_TRANSAKSI";
    public static final int FLD_LOGHISTORYID = 0;
    public static final int FLD_INSTANSI = 1;
    public static final int FLD_NPWD = 2;
    public static final int FLD_NAMA = 3;
    public static final int FLD_JUMLAHPAJAK = 4;
    public static final int FLD_DENDA = 5;
    public static final int FLD_TAHUN = 6;
    public static final int FLD_BULAN = 7;
    public static final int FLD_TIB = 8;
    public static final int FLD_TANGGAL = 9;
    public static final int FLD_TGL_RETRIBUSI=10;
    public static final int FLD_ID_KEY=11;
    
    public static final int FLD_LUAS_BUMI=12;
    public static final int FLD_LUAS_BANGUNAN=13;
    public static final int FLD_NJOP_BUMI=14;
    public static final int FLD_NJOP_BANGUNAN=15;
    public static final int FLD_NJOP_PTKP=16;
    public static final int FLD_ALAMAT=17;
    public static final int FLD_LETAK_OBJEK_PAJAK=18;
    public static final int FLD_POKOK=19;
    
    public static String[] fieldNames = {
        "LOG_HISTORY_ID",
        "INSTANSI",
        "ID",
        "NAMA",
        "JUMLAH_PAJAK",
        "DENDA",
        "TAHUN",
        "BULAN",
        "TIB",
        "TANGGAL",
        "TGL_RETRIBUSI",
        "ID_KEY",
        
        "LUAS_BUMI",
        "LUAS_BANGUNAN",
        "NJOP_BUMI",
        "NJOP_BANGUNAN",
        "NJOP_PTKP",
        "ALAMAT",
        "LETAK_OBJECT_PAJAK",
        "POKOK"
        
    };
    
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT
        
    };

    public PstLogHistoryTransaksi() {
    }

    public PstLogHistoryTransaksi(int i) throws DBException {
        super(new PstLogHistoryTransaksi());
    }

    public PstLogHistoryTransaksi(String sOid) throws DBException {
        super(new PstLogHistoryTransaksi(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstLogHistoryTransaksi(long lOid) throws DBException {
        super(new PstLogHistoryTransaksi(0));
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
        return TBL_LOGHISTORYTRANSAKSI;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstLogHistoryTransaksi().getClass().getName();
    }

    public static LogHistoryTransaksi fetchExc(long oid) throws DBException {
        try {
            LogHistoryTransaksi entLogHistoryTransaksi = new LogHistoryTransaksi();
            PstLogHistoryTransaksi pstLogHistoryTransaksi = new PstLogHistoryTransaksi(oid);
            entLogHistoryTransaksi.setOID(oid);
            entLogHistoryTransaksi.setInstansi(pstLogHistoryTransaksi.getString(FLD_INSTANSI));
            entLogHistoryTransaksi.setNpwd(pstLogHistoryTransaksi.getString(FLD_NPWD));
            entLogHistoryTransaksi.setNama(pstLogHistoryTransaksi.getString(FLD_NAMA));
            entLogHistoryTransaksi.setJumlahPajak(pstLogHistoryTransaksi.getdouble(FLD_JUMLAHPAJAK));
            entLogHistoryTransaksi.setDenda(pstLogHistoryTransaksi.getdouble(FLD_DENDA));
            entLogHistoryTransaksi.setTahun(pstLogHistoryTransaksi.getString(FLD_TAHUN));
            entLogHistoryTransaksi.setBulan(pstLogHistoryTransaksi.getString(FLD_BULAN));
            entLogHistoryTransaksi.setTib(pstLogHistoryTransaksi.getlong(FLD_TIB));
            entLogHistoryTransaksi.setTanggalRetribusi(pstLogHistoryTransaksi.getDate(FLD_TGL_RETRIBUSI));
            entLogHistoryTransaksi.setTanggal(pstLogHistoryTransaksi.getString(FLD_TANGGAL));
            entLogHistoryTransaksi.setIdKey(pstLogHistoryTransaksi.getString(FLD_ID_KEY));
            
            entLogHistoryTransaksi.setLetakObjeckPajak(pstLogHistoryTransaksi.getString(FLD_LETAK_OBJEK_PAJAK));
            entLogHistoryTransaksi.setAlamat(pstLogHistoryTransaksi.getString(FLD_ALAMAT)); 
            entLogHistoryTransaksi.setLuasBumi(pstLogHistoryTransaksi.getdouble(FLD_LUAS_BUMI));
            entLogHistoryTransaksi.setLuasBangunan(pstLogHistoryTransaksi.getdouble(FLD_LUAS_BANGUNAN));
            entLogHistoryTransaksi.setnJOPBangunan(pstLogHistoryTransaksi.getdouble(FLD_NJOP_BANGUNAN));
            entLogHistoryTransaksi.setnJOPBumi(pstLogHistoryTransaksi.getdouble(FLD_NJOP_BUMI));
            entLogHistoryTransaksi.setnJOPTKP(pstLogHistoryTransaksi.getdouble(FLD_NJOP_PTKP));
            entLogHistoryTransaksi.setPokok(pstLogHistoryTransaksi.getdouble(FLD_POKOK));
            
            return entLogHistoryTransaksi;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLogHistoryTransaksi(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        LogHistoryTransaksi entLogHistoryTransaksi = fetchExc(entity.getOID());
        entity = (Entity) entLogHistoryTransaksi;
        return entLogHistoryTransaksi.getOID();
    }

    public static synchronized long updateExc(LogHistoryTransaksi entLogHistoryTransaksi) throws DBException {
        try {
            if (entLogHistoryTransaksi.getOID() != 0) {
                
                PstLogHistoryTransaksi pstLogHistoryTransaksi = new PstLogHistoryTransaksi(entLogHistoryTransaksi.getOID());
                pstLogHistoryTransaksi.setString(FLD_INSTANSI, entLogHistoryTransaksi.getInstansi());
                pstLogHistoryTransaksi.setString(FLD_NPWD, entLogHistoryTransaksi.getNpwd());
                pstLogHistoryTransaksi.setString(FLD_NAMA, entLogHistoryTransaksi.getNama());
                pstLogHistoryTransaksi.setDouble(FLD_JUMLAHPAJAK, entLogHistoryTransaksi.getJumlahPajak());
                pstLogHistoryTransaksi.setDouble(FLD_DENDA, entLogHistoryTransaksi.getDenda());
                pstLogHistoryTransaksi.setString(FLD_TAHUN, entLogHistoryTransaksi.getTahun());
                pstLogHistoryTransaksi.setString(FLD_BULAN, entLogHistoryTransaksi.getBulan());
                pstLogHistoryTransaksi.setLong(FLD_TIB, entLogHistoryTransaksi.getTib());
                pstLogHistoryTransaksi.setDate(FLD_TGL_RETRIBUSI, entLogHistoryTransaksi.getTanggalRetribusi());
                pstLogHistoryTransaksi.setString(FLD_TANGGAL, entLogHistoryTransaksi.getTanggal());
                pstLogHistoryTransaksi.setString(FLD_ID_KEY, entLogHistoryTransaksi.getIdKey());
                
                pstLogHistoryTransaksi.setString(FLD_ALAMAT, entLogHistoryTransaksi.getAlamat());
                pstLogHistoryTransaksi.setString(FLD_LETAK_OBJEK_PAJAK, entLogHistoryTransaksi.getLetakObjeckPajak());
                pstLogHistoryTransaksi.setDouble(FLD_LUAS_BUMI, entLogHistoryTransaksi.getLuasBumi());
                pstLogHistoryTransaksi.setDouble(FLD_LUAS_BANGUNAN, entLogHistoryTransaksi.getLuasBangunan());
                pstLogHistoryTransaksi.setDouble(FLD_NJOP_BANGUNAN, entLogHistoryTransaksi.getnJOPBangunan());
                pstLogHistoryTransaksi.setDouble(FLD_NJOP_BUMI, entLogHistoryTransaksi.getnJOPBumi());
                pstLogHistoryTransaksi.setDouble(FLD_NJOP_PTKP, entLogHistoryTransaksi.getnJOPTKP());
                pstLogHistoryTransaksi.setDouble(FLD_POKOK, entLogHistoryTransaksi.getPokok());
                
                pstLogHistoryTransaksi.update();
                
                return entLogHistoryTransaksi.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLogHistoryTransaksi(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((LogHistoryTransaksi) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstLogHistoryTransaksi pstLogHistoryTransaksi = new PstLogHistoryTransaksi(oid);
            pstLogHistoryTransaksi.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLogHistoryTransaksi(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(LogHistoryTransaksi entLogHistoryTransaksi) throws DBException {
        try {
            
            PstLogHistoryTransaksi pstLogHistoryTransaksi = new PstLogHistoryTransaksi(0);
            pstLogHistoryTransaksi.setString(FLD_INSTANSI, entLogHistoryTransaksi.getInstansi());
            pstLogHistoryTransaksi.setString(FLD_NPWD, entLogHistoryTransaksi.getNpwd());
            pstLogHistoryTransaksi.setString(FLD_NAMA, entLogHistoryTransaksi.getNama());
            pstLogHistoryTransaksi.setDouble(FLD_JUMLAHPAJAK, entLogHistoryTransaksi.getJumlahPajak());
            pstLogHistoryTransaksi.setDouble(FLD_DENDA, entLogHistoryTransaksi.getDenda());
            pstLogHistoryTransaksi.setString(FLD_TAHUN, entLogHistoryTransaksi.getTahun());
            pstLogHistoryTransaksi.setString(FLD_BULAN, entLogHistoryTransaksi.getBulan());
            pstLogHistoryTransaksi.setLong(FLD_TIB, entLogHistoryTransaksi.getTib());
            pstLogHistoryTransaksi.setDate(FLD_TGL_RETRIBUSI, entLogHistoryTransaksi.getTanggalRetribusi());
            pstLogHistoryTransaksi.setString(FLD_TANGGAL, entLogHistoryTransaksi.getTanggal());
            pstLogHistoryTransaksi.setString(FLD_ID_KEY, entLogHistoryTransaksi.getIdKey());
            
            pstLogHistoryTransaksi.setString(FLD_ALAMAT, entLogHistoryTransaksi.getAlamat());
            pstLogHistoryTransaksi.setString(FLD_LETAK_OBJEK_PAJAK, entLogHistoryTransaksi.getLetakObjeckPajak());
            pstLogHistoryTransaksi.setDouble(FLD_LUAS_BUMI, entLogHistoryTransaksi.getLuasBumi());
            pstLogHistoryTransaksi.setDouble(FLD_LUAS_BANGUNAN, entLogHistoryTransaksi.getLuasBangunan());
            pstLogHistoryTransaksi.setDouble(FLD_NJOP_BANGUNAN, entLogHistoryTransaksi.getnJOPBangunan());
            pstLogHistoryTransaksi.setDouble(FLD_NJOP_BUMI, entLogHistoryTransaksi.getnJOPBumi());
            pstLogHistoryTransaksi.setDouble(FLD_NJOP_PTKP, entLogHistoryTransaksi.getnJOPTKP());
            pstLogHistoryTransaksi.setDouble(FLD_POKOK, entLogHistoryTransaksi.getPokok());
            
            pstLogHistoryTransaksi.insert();
            entLogHistoryTransaksi.setOID(pstLogHistoryTransaksi.getlong(FLD_LOGHISTORYID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstLogHistoryTransaksi(0), DBException.UNKNOWN);
        }
        return entLogHistoryTransaksi.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((LogHistoryTransaksi) entity);
    }

    public static void resultToObject(ResultSet rs, LogHistoryTransaksi entLogHistoryTransaksi) {
        
        try {
            
            entLogHistoryTransaksi.setOID(rs.getLong(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_LOGHISTORYID]));
            entLogHistoryTransaksi.setInstansi(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_INSTANSI]));
            entLogHistoryTransaksi.setNpwd(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_NPWD]));
            entLogHistoryTransaksi.setNama(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_NAMA]));
            entLogHistoryTransaksi.setJumlahPajak(rs.getDouble(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_JUMLAHPAJAK]));
            entLogHistoryTransaksi.setDenda(rs.getDouble(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_DENDA]));
            entLogHistoryTransaksi.setTahun(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TAHUN]));
            entLogHistoryTransaksi.setBulan(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_BULAN]));
            entLogHistoryTransaksi.setTib(rs.getLong(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TIB]));
            entLogHistoryTransaksi.setTanggalRetribusi(rs.getDate(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TGL_RETRIBUSI]));
            entLogHistoryTransaksi.setTanggal(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TANGGAL]));
            entLogHistoryTransaksi.setIdKey(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_ID_KEY]));
            
            entLogHistoryTransaksi.setAlamat(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_ALAMAT]));
            entLogHistoryTransaksi.setLetakObjeckPajak(rs.getString(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_LETAK_OBJEK_PAJAK]));
            entLogHistoryTransaksi.setLuasBumi(rs.getDouble(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_LUAS_BUMI]));
            entLogHistoryTransaksi.setLuasBangunan(rs.getDouble(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_LUAS_BANGUNAN]));
            entLogHistoryTransaksi.setnJOPBangunan(rs.getDouble(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_NJOP_BANGUNAN]));
            entLogHistoryTransaksi.setnJOPBumi(rs.getDouble(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_NJOP_BUMI]));
            entLogHistoryTransaksi.setnJOPTKP(rs.getDouble(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_NJOP_PTKP]));
            entLogHistoryTransaksi.setPokok(rs.getDouble(PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_POKOK]));
            
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
            String sql = "SELECT * FROM " + TBL_LOGHISTORYTRANSAKSI;
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
                LogHistoryTransaksi entLogHistoryTransaksi = new LogHistoryTransaksi();
                resultToObject(rs, entLogHistoryTransaksi);
                lists.add(entLogHistoryTransaksi);
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

    public static boolean checkOID(long entLogHistoryTransaksiId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_LOGHISTORYTRANSAKSI + " WHERE "
                    + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_LOGHISTORYID] + " = " + entLogHistoryTransaksiId;
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
            String sql = "SELECT COUNT(" + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_LOGHISTORYID] + ") FROM " + TBL_LOGHISTORYTRANSAKSI;
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
                    LogHistoryTransaksi entLogHistoryTransaksi = (LogHistoryTransaksi) list.get(ls);
                    if (oid == entLogHistoryTransaksi.getOID()) {
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
    
    
    public static int deleteloghistoryperid(String id) {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql ="";
        if(!id.equals("")){
            stSql = "DELETE FROM " + TBL_LOGHISTORYTRANSAKSI
                    + " WHERE " + fieldNames[FLD_NPWD] + " = '" + id+"'";

            try {
                iResult = DBHandler.execUpdate(stSql);
            } catch (DBException e) {
                e.printStackTrace();
            } finally {
                DBResultSet.close(dbrs);
}
        }
        return iResult;
    }
    
    public static int deleteloghistoryperidmasapajak(String id, String tahun, String masa) {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql ="";
        if(!id.equals("")){
            stSql = "DELETE FROM " + TBL_LOGHISTORYTRANSAKSI
                    + " WHERE " + fieldNames[FLD_NPWD] + " = '" + id+"'"
                    + " AND " + fieldNames[FLD_TAHUN] + " = '" + tahun+"'"
                    + " AND " + fieldNames[FLD_BULAN] + " = '" + masa+"'";

            try {
                iResult = DBHandler.execUpdate(stSql);
            } catch (DBException e) {
                e.printStackTrace();
            } finally {
                DBResultSet.close(dbrs);
            }
        }
        return iResult;
    }
    
    
}
