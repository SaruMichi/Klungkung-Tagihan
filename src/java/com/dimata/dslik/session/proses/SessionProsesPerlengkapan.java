/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import com.dimata.dslik.entity.agunan.Agunan;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.bankgaransi.BankGaransi;
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.entity.debitur.Debitur;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.pengurusataupemilik.PengurusAtauPemilik;
import com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author dimata005
 */
public class SessionProsesPerlengkapan {
    public static int getCountJoin(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql ="SELECT  DISTINCT COUNT(DISTINCT l.NO_REKENING) FROM dslik_kredit l LEFT JOIN dslik_debitur r " +
                        "ON r.CIF = l.CIF " +
                        "WHERE r.CIF IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
    
    public static int getCountJoinForDebitur(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql ="SELECT COUNT(DISTINCT l.CIF) FROM dslik_debitur  l "
                    + "LEFT JOIN dslik_kredit r " +
                        "ON r.CIF = l.CIF " + 
                        "LEFT JOIN dslik_bank_garansi a " +
                        "ON l.CIF=a.CIF "+
                        "WHERE r.CIF IS NULL "
                        + "AND a.CIF IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
    
    public static int getCountJoinBankGaransi(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql ="SELECT  DISTINCT COUNT(DISTINCT l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+") FROM "+PstBankGaransi.TBL_BANK_GARANSI+" l "
                    + "LEFT JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" r " +
                        "ON r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" = l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" " +
                        "WHERE r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
    
    public static int getCountJoinAgunan(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql ="SELECT  DISTINCT COUNT(l."+PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING]+") FROM "+PstAgunan.TBL_AGUNAN+" l "
                    + "LEFT JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" r " +
                        "ON r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" = l."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" " +
                        "WHERE r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
    
    public static int getCountJoinPengurusPemilik(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql ="SELECT  DISTINCT COUNT(DISTINCT l."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+") FROM "+PstPengurusAtauPemilik.TBL_PENGURUS_ATAU_PEMILIK+" l "
                    + "LEFT JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" r " +
                        "ON r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" = l."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" " +
                        "WHERE r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
    
    public static Vector listOtherKredit(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT  DISTINCT l.* FROM "+PstKredit.TBL_KREDIT+" l "
                    + "LEFT JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" r " +
                        "ON r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" = l."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" " +
                        "WHERE r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
                Kredit entKredit = new Kredit();
                PstKredit.resultToObject(rs, entKredit);
                lists.add(entKredit);
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
    
    public static Vector listOtherDebitur(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT  DISTINCT l.* FROM "+PstDebitur.TBL_DEBITUR_INDIVIDU+" l "
                    + "LEFT JOIN "+PstKredit.TBL_KREDIT+" r " +
                        "ON r."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" = l."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" "
                        + "LEFT JOIN " + PstBankGaransi.TBL_BANK_GARANSI+" a "
                        + "ON l."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"=a."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" "+
                        "WHERE r."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" IS NULL "
                    + "AND a."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
                Debitur entDebitur = new Debitur();
                PstDebitur.resultToObject(rs, entDebitur);
                lists.add(entDebitur);
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
    
    public static Vector listOtherBankGaransi(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT l.* FROM "+PstBankGaransi.TBL_BANK_GARANSI+" l "
                    + "LEFT JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" r " +
                        "ON r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" = l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" " +
                        "WHERE r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
                BankGaransi entBankGaransi = new BankGaransi();
                PstBankGaransi.resultToObject(rs, entBankGaransi);
                lists.add(entBankGaransi);
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
    
    public static Vector listOtherAgunan(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT  l.* FROM "+PstAgunan.TBL_AGUNAN+" l "
                    + "LEFT JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" r " +
                        "ON r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" = l."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" " +
                        "WHERE r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
                PstAgunan.resultToObject(rs, entAgunan);
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
    
    public static Vector listOtherPengurusPemilik(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT l.* FROM "+PstPengurusAtauPemilik.TBL_PENGURUS_ATAU_PEMILIK+" l "
                    + "LEFT JOIN "+PstDebitur.TBL_DEBITUR_INDIVIDU+" r " +
                        "ON r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" = l."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" " +
                        "WHERE r."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" IS NULL ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
                PengurusAtauPemilik entPengurusAtauPemilik = new PengurusAtauPemilik();
                PstPengurusAtauPemilik.resultToObject(rs, entPengurusAtauPemilik);
                lists.add(entPengurusAtauPemilik);
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
}
