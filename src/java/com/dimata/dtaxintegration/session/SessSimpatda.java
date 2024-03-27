/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session;

/**
 *
 * @author dimata005
 */
/**
 *
 * @author dimata005
 */
/* java package */
import com.dimata.dtaxintegration.entity.inquery.Bphtb;
import com.dimata.dtaxintegration.entity.inquery.Pbb;
import com.dimata.dtaxintegration.entity.inquery.Retribusi;
import com.dimata.dtaxintegration.entity.inquery.Simpatda;
import com.dimata.dtaxintegration.entity.loghistory.PstLogHistoryTransaksi;
import com.dimata.dtaxintegration.entity.payment.PstPaymentBphtb;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbb;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPhr;
import com.dimata.dtaxintegration.entity.payment.PstPaymentRetribusi;
import java.sql.*;

/* package qdep */
import com.dimata.qdep.db.*;
import com.dimata.util.Formater;
import com.dimata.webclient.AppSetting;
import java.util.Vector;

public class SessSimpatda {
    
    //add by 2017-132
    public static Vector getListSimpatda(String where) {
        return getListSimpatda(where,"");
    }
    
    public static Vector getListSimpatda(String where, String order) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = "";
        try {

            sql = "SELECT ID, replace(NAMA, '''', '') AS NAMA, JUMLAH, INSTANSI, MASA_PAJAK, TAHUN_PAJAK, ALAMAT, POKOK, DENDA, ID_KEY, NO_SPTPD,VOLUME,CONCAT(TAHUN_PAJAK,MASA_PAJAK) AS IDX FROM VIEW_SIMPATDA ";

            if (!where.equals("")) {
                sql = sql + where;
            }
            
            if(!order.equals("")){
                sql = sql + " order by "+order;
            }

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            
            while (rs.next()) {

                Simpatda simpatda = new Simpatda();

                simpatda.setId(rs.getString("ID"));
                simpatda.setNpwpd(rs.getString("ID"));
                simpatda.setNamaSimpatda(rs.getString("NAMA"));
                simpatda.setJumlahPajakSimpatda(rs.getString("JUMLAH"));
                simpatda.setInstansi(rs.getString("INSTANSI"));
                simpatda.setAlamat(rs.getString("ALAMAT"));
                simpatda.setBulanSimpatda(rs.getString("MASA_PAJAK"));
                simpatda.setTahunSimpatda(rs.getString("TAHUN_PAJAK"));
                simpatda.setPokok(rs.getString("POKOK"));
                simpatda.setDenda(rs.getString("DENDA"));
                simpatda.setNoSspdSimpatda(rs.getString("NO_SPTPD"));
                simpatda.setKeterangan(rs.getString("ID_KEY"));
                simpatda.setVolumeKet(rs.getString("VOLUME"));
                
                result.add(simpatda);
            }
            
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static Vector getListSimpatdaAuto(String where) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = "";
        try {

            sql = "SELECT ID, replace(NAMA, '''', '') AS NAMA, JUMLAH, INSTANSI, MASA_PAJAK, TAHUN_PAJAK, ALAMAT, POKOK, DENDA, ID_KEY, NO_SPTPD, VOLUME  FROM VIEW_SIMPATDA_COMPARE ";

            if (!where.equals("")) {
                sql = sql + where;
            }

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {

                Simpatda simpatda = new Simpatda();

                try {
                    simpatda.setId(rs.getString("ID"));
                    simpatda.setNpwpd(rs.getString("ID"));
                    simpatda.setNamaSimpatda(rs.getString("NAMA"));
                    simpatda.setJumlahPajakSimpatda(rs.getString("JUMLAH"));
                    simpatda.setInstansi(rs.getString("INSTANSI"));
                    simpatda.setAlamat(rs.getString("ALAMAT"));
                    simpatda.setBulanSimpatda(rs.getString("MASA_PAJAK"));
                    simpatda.setTahunSimpatda(rs.getString("TAHUN_PAJAK"));
                    simpatda.setPokok(rs.getString("POKOK"));
                    simpatda.setDenda(rs.getString("DENDA"));
                    simpatda.setNoSspdSimpatda(rs.getString("NO_SPTPD"));
                    simpatda.setKeterangan(rs.getString("ID_KEY"));
                    simpatda.setVolumeKet(rs.getString("VOLUME"));
                    result.add(simpatda);
                } catch (Exception ex) {

                }

            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static Vector getListSimpatdaThread(String where) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = ""; 
        try {

            sql = "SELECT ID, replace(NAMA, '''', '') AS NAMA, JUMLAH, INSTANSI, MASA_PAJAK, TAHUN_PAJAK, ALAMAT, POKOK, DENDA, ID_KEY, NO_SPTPD, VOLUME,CONCAT(TAHUN_PAJAK,MASA_PAJAK) AS IDX FROM VIEW_SIMPATDA ";

            if (!where.equals("")) {
                sql = sql + where;
            }
            
            sql = sql +" order by IDX ASC";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {

                Simpatda simpatda = new Simpatda();
                if (!DTaxManagerPhr.running) {
                    return new Vector();
                }
                simpatda.setId(rs.getString("ID"));
                simpatda.setNpwpd(rs.getString("ID"));
                simpatda.setNamaSimpatda(rs.getString("NAMA"));
                simpatda.setJumlahPajakSimpatda(rs.getString("JUMLAH"));
                simpatda.setInstansi(rs.getString("INSTANSI"));
                simpatda.setAlamat(rs.getString("ALAMAT"));
                simpatda.setBulanSimpatda(rs.getString("MASA_PAJAK"));
                simpatda.setTahunSimpatda(rs.getString("TAHUN_PAJAK"));
                simpatda.setPokok(rs.getString("POKOK"));
                simpatda.setDenda(rs.getString("DENDA"));
                simpatda.setNoSspdSimpatda(rs.getString("NO_SPTPD"));
                simpatda.setKeterangan(rs.getString("ID_KEY"));
                simpatda.setVolumeKet(rs.getString("VOLUME"));
                
                result.add(simpatda);
                DTaxManagerPhr.countQuery = DTaxManagerPhr.countQuery + 1;
            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static Vector getListPBB(String where) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = "";
        try {

            sql = "SELECT * FROM VIEW_PBB";

            if (!where.equals("")) {
                sql = sql + where;
            }

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {

                Pbb pbb = new Pbb();

                pbb.setId(rs.getString("NOP"));
                pbb.setNpwpd(rs.getString("NPWPD"));//3//sNoId
                pbb.setNama(rs.getString("NAMA"));//4//sNama
                pbb.setInstansi(rs.getString("INSTANSI"));
                pbb.setJumlahTagihan(rs.getString("JUMLAH_TAGIHAN"));//5 jum_tagihan
                pbb.setAlamat(rs.getString("ALAMAT_WP"));//sKet_2//Alamat
                pbb.setLetakObjectPajak(rs.getString("LETAK"));//Letak Objek Pajak
                pbb.setTahun(rs.getString("TAHUN"));//tahun//10
                pbb.setTglJatuhTempo(rs.getString("JATUH_TEMPO"));//11//jatuh tempo

                String sLuasBumiSppt = rs.getString("LUAS_BUMI_SPPT");
                double luasBumiSppt = Double.valueOf(sLuasBumiSppt);
                pbb.setLuasBumi(Formater.formatNumber(luasBumiSppt, "#,###,##0"));//12//luas bangunan

                String sLuasBgnSppt = rs.getString("LUAS_BNG_SPPT");
                double luasBgnSppt = Double.valueOf(sLuasBgnSppt);
                pbb.setLuasBangunan(Formater.formatNumber(luasBgnSppt, "#,###,##0"));//13

                String sNjopBumi = rs.getString("NJOP_BUMI_SPPT");
                double NjopBumi = Double.valueOf(sNjopBumi);
                pbb.setnJOPBumi(Formater.formatNumber(NjopBumi, "#,###,##0"));//14

                String sNjopBgn = rs.getString("NJOP_BNG_SPPT");
                double NjopBgn = Double.valueOf(sNjopBgn);
                pbb.setnJOPBangunan(Formater.formatNumber(NjopBgn, "#,###,##0"));

                String snJOPTKP = rs.getString("NJOPTKP_SPPT");
                double nJOPTKP = Double.valueOf(snJOPTKP);
                pbb.setnJOPTKP(Formater.formatNumber(nJOPTKP, "#,###,##0"));//NJOPTKP_SPPT

                pbb.setDenda(rs.getString("DENDA"));
                pbb.setPokok(rs.getString("POKOK"));
                if (AppSetting.USERNAME_PBB.equals("PBB_BANGLI")) {
                    pbb.setNjkpSppt(rs.getDouble("NJKP_SPPT"));
                    pbb.setTarifSppt(rs.getDouble("TARIF_SPPT"));
                } else {
                    pbb.setFormula("(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda");
                }

                pbb.setTerbilang("");

                result.add(pbb);
            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static Vector getListPBBIprotax(String where) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = "";
        try {

            sql = "SELECT * FROM VIEW_PBB";

            if (!where.equals("")) {
                sql = sql + where;
            }

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {

                Pbb pbb = new Pbb();

                pbb.setId(rs.getString("NOP"));
                pbb.setNpwpd(rs.getString("NPWPD"));//3//sNoId
                pbb.setNama(rs.getString("NAMA"));//4//sNama
                pbb.setInstansi(rs.getString("INSTANSI"));
                pbb.setJumlahTagihan(rs.getString("JUMLAH_TAGIHAN"));//5 jum_tagihan
                pbb.setAlamat(rs.getString("ALAMAT_WP"));//sKet_2//Alamat
                pbb.setLetakObjectPajak(rs.getString("LETAK"));//Letak Objek Pajak
                pbb.setTahun(rs.getString("TAHUN"));//tahun//10
                pbb.setTglJatuhTempo(rs.getString("JATUH_TEMPO"));//11//jatuh tempo

                String sLuasBumiSppt = rs.getString("LUAS_BUMI_SPPT");
                double luasBumiSppt = Double.valueOf(sLuasBumiSppt);
                pbb.setLuasBumi(Formater.formatNumber(luasBumiSppt, "#,###,##0"));//12//luas bangunan
                pbb.setDluasBumi(luasBumiSppt);

                String sLuasBgnSppt = rs.getString("LUAS_BNG_SPPT");
                double luasBgnSppt = Double.valueOf(sLuasBgnSppt);
                pbb.setLuasBangunan(Formater.formatNumber(luasBgnSppt, "#,###,##0"));//13
                pbb.setDluasBangunan(luasBgnSppt);

                String sNjopBumi = rs.getString("NJOP_BUMI_SPPT");
                double NjopBumi = Double.valueOf(sNjopBumi);
                pbb.setnJOPBumi(Formater.formatNumber(NjopBumi, "#,###,##0"));//14
                pbb.setDnJOPBumi(NjopBumi);

                String sNjopBgn = rs.getString("NJOP_BNG_SPPT");
                double NjopBgn = Double.valueOf(sNjopBgn);
                pbb.setnJOPBangunan(Formater.formatNumber(NjopBgn, "#,###,##0"));
                pbb.setDnJOPBangunan(NjopBgn);

                String snJOPTKP = rs.getString("NJOPTKP_SPPT");
                double nJOPTKP = Double.valueOf(snJOPTKP);
                pbb.setnJOPTKP(Formater.formatNumber(nJOPTKP, "#,###,##0"));//NJOPTKP_SPPT
                pbb.setDnJOPTKP(nJOPTKP);

                pbb.setDenda(rs.getString("DENDA"));
                pbb.setPokok(rs.getString("POKOK"));
                if (AppSetting.USERNAME_PBB.equals("PBB_BANGLI")) {
                    pbb.setNjkpSppt(rs.getDouble("NJKP_SPPT"));
                    pbb.setTarifSppt(rs.getDouble("TARIF_SPPT"));
                } else {
                    pbb.setFormula("(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda");
                }

                pbb.setTerbilang("");

                result.add(pbb);
            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static Vector getListPBBThread(String where) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = "";
        try {

            sql = "SELECT * FROM VIEW_PBB";

            if (!where.equals("")) {
                sql = sql + where;
            }

            dbrs = DBHandler.execQueryResult(sql);

            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {

                Pbb pbb = new Pbb();

                if (!DTaxManagerPbb.running) {
                    return new Vector();
                }

                pbb.setId(rs.getString("NOP"));
                pbb.setNpwpd(rs.getString("NPWPD"));//3//sNoId
                pbb.setNama(rs.getString("NAMA"));//4//sNama
                pbb.setInstansi(rs.getString("INSTANSI"));
                pbb.setJumlahTagihan(rs.getString("JUMLAH_TAGIHAN"));//5 jum_tagihan
                pbb.setAlamat(rs.getString("ALAMAT_WP"));//sKet_2//Alamat
                pbb.setLetakObjectPajak(rs.getString("LETAK"));//Letak Objek Pajak
                pbb.setTahun(rs.getString("TAHUN"));//tahun//10
                pbb.setTglJatuhTempo(rs.getString("JATUH_TEMPO"));//11//jatuh tempo
                pbb.setLuasBumi(rs.getString("LUAS_BUMI_SPPT"));//12//luas bangunan
                pbb.setLuasBangunan(rs.getString("LUAS_BNG_SPPT"));//13
                pbb.setnJOPBumi(rs.getString("NJOP_BUMI_SPPT"));//14
                pbb.setnJOPBangunan(rs.getString("NJOP_BNG_SPPT"));
                pbb.setnJOPTKP(rs.getString("NJOPTKP_SPPT"));//NJOPTKP_SPPT
                pbb.setDenda(rs.getString("DENDA"));
                pbb.setPokok(rs.getString("POKOK"));
                pbb.setFormula("(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda");

                pbb.setTerbilang("");

                result.add(pbb);

                DTaxManagerPbb.countQuery = DTaxManagerPbb.countQuery + 1;
            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static Vector getListPBBThreadIProtax(String where) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = "";
        try {

            sql = "SELECT * FROM VIEW_PBB";

            if (!where.equals("")) {
                sql = sql + where;
            }

            dbrs = DBHandler.execQueryResult(sql);

            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {

                Pbb pbb = new Pbb();

                if (!DTaxManagerPbb.running) {
                    return new Vector();
                }

                pbb.setId(rs.getString("NOP"));
                pbb.setNpwpd(rs.getString("NPWPD"));//3//sNoId
                pbb.setNama(rs.getString("NAMA"));//4//sNama
                pbb.setInstansi(rs.getString("INSTANSI"));
                pbb.setJumlahTagihan(rs.getString("JUMLAH_TAGIHAN"));//5 jum_tagihan
                pbb.setAlamat(rs.getString("ALAMAT_WP"));//sKet_2//Alamat
                pbb.setLetakObjectPajak(rs.getString("LETAK"));//Letak Objek Pajak
                pbb.setTahun(rs.getString("TAHUN"));//tahun//10
                pbb.setTglJatuhTempo(rs.getString("JATUH_TEMPO"));//11//jatuh tempo

//                pbb.setLuasBumi(rs.getString("LUAS_BUMI_SPPT"));//12//luas bangunan
//                pbb.setLuasBangunan(rs.getString("LUAS_BNG_SPPT"));//13
//                pbb.setnJOPBumi(rs.getString("NJOP_BUMI_SPPT"));//14
//                pbb.setnJOPBangunan(rs.getString("NJOP_BNG_SPPT"));
//                pbb.setnJOPTKP(rs.getString("NJOPTKP_SPPT"));//NJOPTKP_SPPT
                String sLuasBumiSppt = rs.getString("LUAS_BUMI_SPPT");
                double luasBumiSppt = Double.valueOf(sLuasBumiSppt);
                pbb.setLuasBumi(Formater.formatNumber(luasBumiSppt, "#,###,##0"));//12//luas bangunan

                String sLuasBgnSppt = rs.getString("LUAS_BNG_SPPT");
                double luasBgnSppt = Double.valueOf(sLuasBgnSppt);
                pbb.setLuasBangunan(Formater.formatNumber(luasBgnSppt, "#,###,##0"));//13

                String sNjopBumi = rs.getString("NJOP_BUMI_SPPT");
                double NjopBumi = Double.valueOf(sNjopBumi);
                pbb.setnJOPBumi(Formater.formatNumber(NjopBumi, "#,###,##0"));//14

                String sNjopBgn = rs.getString("NJOP_BNG_SPPT");
                double NjopBgn = Double.valueOf(sNjopBgn);
                pbb.setnJOPBangunan(Formater.formatNumber(NjopBgn, "#,###,##0"));

                String snJOPTKP = rs.getString("NJOPTKP_SPPT");
                double nJOPTKP = Double.valueOf(snJOPTKP);
                pbb.setnJOPTKP(Formater.formatNumber(nJOPTKP, "#,###,##0"));//NJOPTKP_SPPT

                pbb.setDenda(rs.getString("DENDA"));
                pbb.setPokok(rs.getString("POKOK"));
                pbb.setFormula("(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda");
                pbb.setTerbilang("");

                //proses perhitungan jumlah tagihan dan denda
                double totPambayaran = SessPbbIprotax.PerhitunganPbbYangHarusDibayar(pbb.getId(), pbb.getTahun(), pbb.getJumlahTagihan());
                /*count denda adm sppt*/
                double denda = SessPbbIprotax.PerhitunganDenda(pbb.getId(), pbb.getTahun(), pbb.getTglJatuhTempo(), pbb.getJumlahTagihan(),totPambayaran);
                /*total yang harus dibayarkan*/
                double ygHarusDibayar = totPambayaran + denda;
                try {
                    pbb.setJumlahTagihan(String.valueOf(ygHarusDibayar));
                } catch (Exception ex) {
                }
                try {
                    // pbb.setDenda(String.valueOf(denda));
                    pbb.setDenda(Formater.formatNumber(denda, "#,###,##0"));
                } catch (Exception ex) {
                }

                pbb.setFormula("(" + pbb.getnJOPBumi() + " + " + pbb.getnJOPBangunan() + " - " + pbb.getnJOPTKP() + ") X " + (pbb.getTarifSppt() * (pbb.getNjkpSppt() / 100)) + " % + " + pbb.getDenda());

                result.add(pbb);

                DTaxManagerPbb.countQuery = DTaxManagerPbb.countQuery + 1;
            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static int countPBB(String where) {
        int checkHistory = 0;
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT COUNT(NAMA) FROM VIEW_PBB";

            if (!where.equals("")) {
                sql = sql + where;
            }

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = rs.getInt(1);
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

    public static void deleteExc(String whereClause) throws DBException {
        DBResultSet dbrs = null;
        int iResult = 0;
        try {
            String sql = "DELETE FROM " + PstLogHistoryTransaksi.TBL_LOGHISTORYTRANSAKSI;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            try {
                iResult = DBHandler.execUpdate(sql);
            } catch (DBException e) {
                e.printStackTrace();
            } finally {
                DBResultSet.close(dbrs);
            }

            //dbrs = DBHandler.execQueryResult(sql);
            //ResultSet rs = dbrs.getResultSet();
            //rs.close();
        } catch (Exception e) {
            System.out.println("Err: delete item " + e.toString());
        }
    }

    public static int countPHR(String where) {
        int checkHistory = 0;
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT COUNT(ID) FROM VIEW_SIMPATDA";

            if (!where.equals("")) {
                sql = sql + where;
            }

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = rs.getInt(1);
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

    public static Vector getListBphtb(String where) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = "";
        try {

            sql = "SELECT * FROM VIEW_BPHTB";
            if (!where.equals("")) {
                sql = sql + where;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {

                Bphtb bphtb = new Bphtb();
                bphtb.setId(rs.getString("ID"));
                bphtb.setNama(rs.getString("NAMA"));
                bphtb.setJumlahTagihan(rs.getString("JUMLAH"));
                bphtb.setNop(rs.getString("NOP"));
                bphtb.setInstansi(rs.getString("INSTANSI"));
                bphtb.setPokok(rs.getString("POKOK"));
                bphtb.setDenda(rs.getString("DENDA"));
                bphtb.setLetakObjectPajak(rs.getString("ALAMAT"));
                result.add(bphtb);
            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static Vector getListAutoBphtb(String where) {

        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        String whereClause = "";
        try {

            sql = "SELECT * FROM VIEW_BPHTB_COMPARE";

            if (!where.equals("")) {
                sql = sql + where;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {

                Bphtb bphtb = new Bphtb();
                bphtb.setId(rs.getString("ID"));
                bphtb.setNama(rs.getString("NAMA"));
                bphtb.setJumlahTagihan(rs.getString("JUMLAH"));
                bphtb.setNop(rs.getString("NOP"));
                bphtb.setInstansi(rs.getString("INSTANSI"));
                bphtb.setPokok(rs.getString("POKOK"));
                bphtb.setDenda(rs.getString("DENDA"));
                bphtb.setLetakObjectPajak(rs.getString("ALAMAT"));
                result.add(bphtb);
            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static Vector getListRetribusi(String where) {
        Vector result = new Vector(1, 1);
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT * FROM VIEW_RETRIBUSI";
            if (!where.equals("")) {
                sql = sql + where;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {

                Retribusi retribusi = new Retribusi();
                retribusi.setNoRekening(rs.getString("ID"));
                retribusi.setNama(rs.getString("NAMA"));
                retribusi.setJumlahTagihan(rs.getString("JUMLAH"));
                retribusi.setTanggalPenerimaan(rs.getString("TGL_RETRIBUSI"));
                retribusi.setInstansi(rs.getString("INSTANSI"));
                retribusi.setTahun(rs.getString("TAHUN"));
                retribusi.setBulan(rs.getString("BULAN"));
                retribusi.setTanggal(rs.getString("TANGGAL"));
                retribusi.setPokok(rs.getString("POKOK"));
                retribusi.setDenda(rs.getString("DENDA"));
                retribusi.setIdKey(rs.getString("ID_KEY"));

                result.add(retribusi);
            }
            rs.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static boolean check(String npwd, String tahun, String tagihan, String bulan, String instansi) {
        boolean checkHistory = false;
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT * FROM " + PstLogHistoryTransaksi.TBL_LOGHISTORYTRANSAKSI
                    + " WHERE "
                    + "" + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_NPWD] + "='" + npwd + "'"
                    + " AND " + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_INSTANSI] + "='" + instansi + "'"
                    + " AND " + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_JUMLAHPAJAK] + "!=" + tagihan;

            if (tahun.equals("")) {
                sql = sql + " AND " + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TAHUN] + "='" + tahun + "'";
            }

            if (bulan.equals("")) {
                sql = sql + " AND " + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_BULAN] + "='" + bulan + "'";
            }

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = true;
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

    public synchronized static boolean checkHistoryRetribusi(String npwd, String tahun, String bulan, String tanggal) {
        boolean checkHistory = false;
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT * FROM " + PstLogHistoryTransaksi.TBL_LOGHISTORYTRANSAKSI
                    + " WHERE "
                    + "" + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_NPWD] + "='" + npwd + "'"
                    + " AND "+PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TGL_RETRIBUSI]
                    +" BETWEEN TO_DATE ('"+tanggal+"','YYYY-MM-DD HH24:MI:SS')"
                    +" AND TO_DATE ('"+tanggal+"','YYYY-MM-DD HH24:MI:SS')";

            if (tahun.equals("")) {
                sql = sql + " AND " + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TAHUN] + "='" + tahun + "'";
            }

            if (bulan.equals("")) {
                sql = sql + " AND " + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_BULAN] + "='" + bulan + "'";
            }

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = true;
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

    public static boolean checkPaymentPhr(String idPayment) {
        boolean checkHistory = true;
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT * FROM " + PstPaymentPhr.TBL_PAYMENTPHR
                    + " WHERE "
                    + PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_IDPAYMENT] + "='" + idPayment + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = false;
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

    public static String checkKeyId(String Npwpd, String tahun, String masaPajak, double tagihan) {
        String checkHistory = "AAAaO4AAFAACmClAAJ";
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT ID_KEY FROM VIEW_SIMPATDA"
                    + " WHERE ID='" + Npwpd + "' AND MASA_PAJAK='" + masaPajak + "' AND TAHUN_PAJAK='" + tahun + "' AND JUMLAH='" + tagihan + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = rs.getString(1);
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

    public static String checkKeyIdBphtb(String Npwpd, String tahun, String masaPajak, double tagihan) {
        String checkHistory = "";
        DBResultSet dbrs = null;
        String sql = "1";
        try {

            sql = "SELECT ID_KEY FROM VIEW_BPHTB"
                    + " WHERE ID='" + Npwpd + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = rs.getString(1);
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

    public static String checkKeyIdPbb(String Npwpd, String tahun, String masaPajak, double tagihan) {
        String checkHistory = "1";
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT ID_KEY FROM VIEW_PBB"
                    + " WHERE NOP='" + Npwpd + "' AND TAHUN='" + tahun + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = rs.getString(1);
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

    public static synchronized String checkKeyIdRetribusi(String Npwpd, String tahun, String bulan, String tanggal) {
        String checkHistory = "AAAaO4AAFAACmClAAJ";
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT ID_KEY FROM LOG_HISTORY_TRANSAKSI"
                    + " WHERE ID='" + Npwpd + "' AND TAHUN='" + tahun + "' AND BULAN='" + bulan + "' AND TANGGAL='" + tanggal + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = rs.getString(1);
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

    public static String updateStatusRaversal(String Npwpd, String tahun, String masaPajak, double tagihan) {
        String checkHistory = "";
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = " UPDATE PEMBAYARAN_SIMPATDA_BANK SET STATUS_REVERSAL='1'"
                    + " WHERE NPWPD='" + Npwpd + "' AND MASA_PAJAK='" + masaPajak + "' AND TAHUN_PAJAK='" + tahun + "' AND JUMLAH='" + tagihan + "'";

//            dbrs = DBHandler.execQueryResult(sql);
//            ResultSet rs = dbrs.getResultSet();
//            
//            while (rs.next()) {
//                checkHistory=rs.getString(1);
//            }
//            
//            rs.close();
//            return checkHistory;
            try {

                DBHandler.execUpdate(sql);

            } catch (Exception e) {

                //oidNew = 0;
            }
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }

        return checkHistory;
    }
    
    public static String updateStatusRaversalPhrAll(String Npwpd, String tahun, String masaPajak, double tagihan) {
        String checkHistory = "";
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = " UPDATE PEMBAYARAN_SIMPATDA_BANK_ALL SET STATUS_REVERSAL='1'"
                    + " WHERE NPWPD='" + Npwpd + "' AND MASA_PAJAK='" + masaPajak + "' AND TAHUN_PAJAK='" + tahun + "' AND JUMLAH='" + tagihan + "'";
            try {

                DBHandler.execUpdate(sql);

            } catch (Exception e) {

            }
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }

        return checkHistory;
    }

    public static String updateStatusRaversalBphtb(String Npwpd, String tahun, String masaPajak, double tagihan) {
        String checkHistory = "";
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = " UPDATE PEMBAYARAN_BPHTB_BANK SET STATUS='1'"
                    + " WHERE NO_TIB='" + Npwpd + "'";

            try {

                DBHandler.execUpdate(sql);

            } catch (Exception e) {

            }
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }

        return checkHistory;
    }

    public static String updateStatusRaversalPbb(String Npwpd, String tahun, String masaPajak, double tagihan) {
        String checkHistory = "";
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = " UPDATE PEMBAYARAN_SPPT_BANK SET STATUS='1'"
                    + " WHERE NOP='" + Npwpd + "' AND THN_PAJAK_SPPT='" + tahun + "'";

            try {

                DBHandler.execUpdate(sql);

            } catch (Exception e) {

            }
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }

        return checkHistory;
    }

    public static String updateStatusRaversalRetribusi(String Npwpd, String tahun, String masaPajak, double tagihan) {
        String checkHistory = "";
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = " UPDATE PEMBAYARAN_RETRIBUSI_BANK SET STATUS_REVERSAL='1'"
                    + " WHERE NPWRD='" + Npwpd + "' AND TRUNC(TGL_SSPD)= TO_DATE ('" + tahun + "', 'YYYY-MM-DD HH24:MI:SS') AND JUMLAH='"+tagihan+"'";

            try {

                DBHandler.execUpdate(sql);

            } catch (Exception e) {

            }
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
        }

        return checkHistory;
    }

    public static boolean checkPaymentPBB(String idPayment) {
        boolean checkHistory = true;
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT * FROM " + PstPaymentPbb.TBL_PAYMENTPBB
                    + " WHERE "
                    + PstPaymentPbb.fieldNames[PstPaymentPbb.FLD_ID_PAYMENT_BANK] + "='" + idPayment + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = false;
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

    public static boolean checkPaymentBphtb(String idPayment) {
        boolean checkHistory = true;
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT * FROM " + PstPaymentBphtb.TBL_PAYMENTBPHTB
                    + " WHERE "
                    + PstPaymentBphtb.fieldNames[PstPaymentBphtb.FLD_ID_PAYMENT_BANK] + "='" + idPayment + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = false;
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

    public static synchronized boolean checkPaymentRetribusi(String idPayment) {
        boolean checkHistory = true;
        DBResultSet dbrs = null;
        String sql = "";
        try {

            sql = "SELECT * FROM " + PstPaymentRetribusi.TBL_PAYMENTRETRIBUSI
                    + " WHERE "
                    + PstPaymentRetribusi.fieldNames[PstPaymentRetribusi.FLD_IDPAYMENTBANK] + "='" + idPayment + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                checkHistory = false;
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

}
