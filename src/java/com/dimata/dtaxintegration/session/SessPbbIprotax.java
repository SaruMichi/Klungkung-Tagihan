/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session;

import com.dimata.dtaxintegration.entity.payment.PaymentPbbIprotax;
import com.dimata.dtaxintegration.entity.payment.PaymentPbbRaversalIprotax;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbbRaversalIprotax;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import static com.dimata.qdep.db.DBHandler.execUpdate;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.util.Formater;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author dimata005
 */
public class SessPbbIprotax {

    public static double getSumPembayaran(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT SUM(JML_PBB_YG_DIBAYAR - JML_DENDA_SPPT) AS JML FROM VIEW_PEMBAYARAN_PBB ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            double count = 0.0;
            while (rs.next()) {
                count = rs.getDouble(1);
            }
            rs.close();
            return count;
        } catch (Exception e) {
            return 0;
        } finally {
            DBResultSet.close(dbrs);
        }
    }

    public static double getSumDendaAdms(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT SUM(JML_POKOK_DENDA - JML_PENGURANGAN_DENDA) AS JML FROM VIEW_DENDA_ADM_SPPT ";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            double count = 0.0;
            while (rs.next()) {
                count = rs.getDouble(1);
            }
            rs.close();
            return count;
        } catch (Exception e) {
            return 0;
        } finally {
            DBResultSet.close(dbrs);
        }
    }

    public static int CalculateMonthsDiff(Date startDate, Date endDate) {
        int monthsBetween = 0;
        if(false){
            Calendar start = Calendar.getInstance();
            start.setTime(startDate);

            Calendar end = Calendar.getInstance();
            end.setTime(endDate);

            int dateDiff = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);
            if (dateDiff < 0) {
                int borrrow = end.getActualMaximum(Calendar.DAY_OF_MONTH);
                dateDiff = (end.get(Calendar.DAY_OF_MONTH) + borrrow) - start.get(Calendar.DAY_OF_MONTH);
                monthsBetween--;
                if (dateDiff > 0) {
                    monthsBetween++;
                }
            } else {
                monthsBetween++;
            }
            monthsBetween += end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
            monthsBetween += (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;

            monthsBetween=monthsBetween-1;

            if(monthsBetween>=24){
                return 24;
            }
        }else if(false){
            String start = Formater.formatDate(startDate, "dd/MM/yyyy");//"01/01/2012";
            String end = Formater.formatDate(endDate, "dd/MM/yyyy");//"31/07/2014";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate from = LocalDate.parse(start, formatter);
            LocalDate to = LocalDate.parse(end, formatter);
            System.out.println(from.until(to, ChronoUnit.MONTHS));
            monthsBetween = (int) from.until(to, ChronoUnit.MONTHS);
            if(monthsBetween>=24){
                return 24;
            }
        }else{
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(startDate);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate);
            int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);    
            if(diffMonth>=24){
                return 24;
            }
        }
        
        
        return monthsBetween;
    }
    
    
    
    public static double PerhitunganDenda(String nop, String tahunPajak, String tglJatuhTempo, String jmlTagihan, double totPambayaran) {
        double ygHarusDibayar = 0.0;
        ygHarusDibayar = totPambayaran;//PerhitunganPbbYangHarusDibayar(nop, tahunPajak, jmlTagihan);
        
        double denda=0.0;
        double dendaAdm = getSumDendaAdms("NOP='"+nop+"' AND THN_PAJAK_PERMOHONAN='"+tahunPajak+"'");
        if(dendaAdm!=0){
            denda=denda+dendaAdm;
        }else{
            //cari jumlah bulan telat dari jatuh tempo dan hari pengiriman
             Date tanggalPembayaran =  new Date();
             SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
             Date date = new Date();
             try {
                 date = formatter.parse(tglJatuhTempo);
             } catch (ParseException e) {
                e.printStackTrace();
             }
             int bulan_telat = SessPbbIprotax.CalculateMonthsDiff(date, tanggalPembayaran);
             if(bulan_telat<0){
                 bulan_telat=0;
             }
             double bunga_denda = (2.0/100.0);
             denda=bulan_telat*bunga_denda*ygHarusDibayar;
        }
        denda = Math.ceil(denda);
        return denda ;
    }
    
    
    public static double PerhitunganPbbYangHarusDibayar(String nop, String tahunPajak, String jmlTagihan) {
        double totPambayaran = SessPbbIprotax.getSumPembayaran("NOP='"+nop+"' AND THN_PAJAK_SPPT='"+tahunPajak+"'");
        double pembayaran = Double.valueOf(jmlTagihan);
        double ygHarusDibayar = pembayaran - totPambayaran;
        return ygHarusDibayar;
    }
    
    public static int UpdateDataTagihan(String kdprovinsi, String kddati, String kecamatan, String kelurahan, String kdBlock, String noUrut, String kdjnsop, String thnPajk, String statusPembayaran) {
        int resultProses=1;
        try{
            String sqlUpdateDua = " UPDATE SPPT SET STATUS_PEMBAYARAN_SPPT='"+statusPembayaran+"' WHERE "+ 
                                  " KD_PROPINSI='"+kdprovinsi+"' AND KD_DATI2='"+kddati+"' AND KD_KECAMATAN='"+kecamatan+"' " +
                                  " AND KD_KELURAHAN='"+kelurahan+"'" +
                                  " AND KD_BLOK='"+kdBlock+"'" +
                                  " AND NO_URUT='"+noUrut+"'" +
                                  " AND KD_JNS_OP='"+kdjnsop+"'" +
                                  " AND THN_PAJAK_SPPT='"+thnPajk+"'";
            resultProses = execUpdate(sqlUpdateDua);
        } catch(Exception es){
            System.out.print("");
        }
        return resultProses;
    }
    
    
    public static int DeleteDataPembayaran(String idPaymentBank, String kdprovinsi, String kddati, String kecamatan, String kelurahan, String kdBlock, String noUrut, String kdjnsop, String thnPajk, String statusPembayaran) {

        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = " DELETE FROM PEMBAYARAN_SPPT WHERE "+
                                    "NO_TRANSAKSI_BYR_SPPT_BANK='"+idPaymentBank+"'";
//                                  " KD_PROPINSI='"+kdprovinsi+"' "+ 
//                                  " AND KD_DATI2='"+kddati+"' "+ 
//                                  " AND KD_KECAMATAN='"+kecamatan+"' " +
//                                  " AND KD_KELURAHAN='"+kelurahan+"'" +
//                                  " AND KD_BLOK='"+kdBlock+"'" +
//                                  " AND NO_URUT='"+noUrut+"'" +
//                                  " AND KD_JNS_OP='"+kdjnsop+"'" +
//                                  " AND THN_PAJAK_SPPT='"+thnPajk+"'";
        try {
            iResult = DBHandler.execUpdate(stSql);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            DBResultSet.close(dbrs);
        }
        return iResult;
    }
    
    
    public static boolean checkPaymentPBBRaversalIprotax(String idPayment){
        boolean checkHistory=true;
        DBResultSet dbrs = null;
        String sql = "";
        try {
            sql = "SELECT * FROM VIEW_PEMBAYARAN_PBB_RAVERSAL "+
                  " WHERE NO_TRANSAKSI_BYR_BANK='"+idPayment+"'";
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
    
    
    
    public static boolean insertPaymentPbbRaversalIprotax(PaymentPbbIprotax paymentPbb){
        boolean checkHistory=true;
        String sql = "";
        try {
            PaymentPbbRaversalIprotax entPaymentPbbRaversalIprotax =  new PaymentPbbRaversalIprotax();
            
            entPaymentPbbRaversalIprotax.setKdProvinsi(paymentPbb.getKdProvinsi());
            entPaymentPbbRaversalIprotax.setKdDati2(paymentPbb.getKdDati2());
            entPaymentPbbRaversalIprotax.setKdKecamatan(paymentPbb.getKdKecamatan());
            entPaymentPbbRaversalIprotax.setKdKelurahan(paymentPbb.getKdKelurahan());
            entPaymentPbbRaversalIprotax.setKdBlock(paymentPbb.getKdBlock());
            entPaymentPbbRaversalIprotax.setNoUrut(paymentPbb.getNoUrut());
            entPaymentPbbRaversalIprotax.setNoJnsOp(paymentPbb.getNoJnsOp());
            entPaymentPbbRaversalIprotax.setThnPajakSppt(paymentPbb.getThnPajakSppt());
            
            entPaymentPbbRaversalIprotax.setJnsKetetapan("0");
            entPaymentPbbRaversalIprotax.setNoKetetapan("0");
            entPaymentPbbRaversalIprotax.setTglKetetapan(new Date());
            entPaymentPbbRaversalIprotax.setJmlDendaAdm(paymentPbb.getJmlDendaSppt());
            entPaymentPbbRaversalIprotax.setJmlPbbYgDibayar(paymentPbb.getJmlPbbYgDibayar());
            entPaymentPbbRaversalIprotax.setNamaPenyetor(paymentPbb.getNamaPenyetor());
            entPaymentPbbRaversalIprotax.setKetRaversalByr("");
            entPaymentPbbRaversalIprotax.setKdSumberData("9");
            entPaymentPbbRaversalIprotax.setTglPembayaran(paymentPbb.getTglPembayaranSppt());
            entPaymentPbbRaversalIprotax.setKdBankTunggal(paymentPbb.getKdBankTunggal());
            entPaymentPbbRaversalIprotax.setKdBankPersepsi(paymentPbb.getKdBankPersepsi());
            entPaymentPbbRaversalIprotax.setKdTp(paymentPbb.getKdTp());
            entPaymentPbbRaversalIprotax.setNoTransaksiByr(paymentPbb.getNoTransaksiByrSppt());
            entPaymentPbbRaversalIprotax.setNoTransaksiByrKolektif(paymentPbb.getNoTransaksiByrSpptKolektif());
            entPaymentPbbRaversalIprotax.setNoTransaksiByrBank(paymentPbb.getNoTransaksiByrSpptBank());
            entPaymentPbbRaversalIprotax.setUserBankRekam(paymentPbb.getUserBankRekam());
            entPaymentPbbRaversalIprotax.setTglRaversalByr(paymentPbb.getTglPembayaranSppt());
            entPaymentPbbRaversalIprotax.setUserBankRaversal(paymentPbb.getUserBankRekam());

            long oid = PstPaymentPbbRaversalIprotax.insertExc(entPaymentPbbRaversalIprotax);
            
        } catch (Exception e) {
            System.out.println("Exc in getListAP >>> " + e.toString());
        } finally {
        }
        
        return checkHistory;
    }
    
    
}
