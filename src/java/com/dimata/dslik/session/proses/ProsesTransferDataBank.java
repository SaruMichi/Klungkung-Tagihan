/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import com.dimata.common.entity.logger.LogSysHistory;
import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.admin.AppUser;
import com.dimata.dslik.entity.admin.PstAppUser;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.entity.bpdmapping.MappingAgunanBpd;
import com.dimata.dslik.entity.bpdmapping.MappingBankGaransiBpd;
import com.dimata.dslik.entity.bpdmapping.MappingDebiturBpd;
import com.dimata.dslik.entity.bpdmapping.MappingKreditBpd;
import com.dimata.dslik.entity.bpdmapping.MappingPengurusAtauPemilikBpd;
import com.dimata.dslik.entity.bpdmapping.PstMappingAgunanBpd;
import com.dimata.dslik.entity.bpdmapping.PstMappingBankGaransiBpd;
import com.dimata.dslik.entity.bpdmapping.PstMappingDebiturBpd;
import com.dimata.dslik.entity.bpdmapping.PstMappingKreditBpd;
import com.dimata.dslik.entity.bpdmapping.PstMappingPengurusAtauPemilikBpd;
import com.dimata.dslik.entity.debiturbdnusaha.DebiturBdnUsaha;
import com.dimata.dslik.entity.debiturbdnusaha.PstDebiturBdnUsaha;
import com.dimata.dslik.entity.debiturindividu.DebiturIndividu;
import com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.masterdata.Bank;
import com.dimata.dslik.entity.masterdata.CabangBank;
import com.dimata.dslik.entity.masterdata.OutletConnection;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstBank;
import com.dimata.dslik.entity.masterdata.PstCabangBank;
import com.dimata.dslik.entity.masterdata.PstConnection;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.form.admin.FrmAppUser;
import com.dimata.dslik.session.admin.SessAppUser;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author dimata005
 */
public class ProsesTransferDataBank {

//    private OutletConnection conOb=null;
//    private Connection dbConn = null;
//    
//    private void createConn()
//        throws SQLException
//    {
//        try
//        {
//            //Class.forName(this.conOb.getDbdriver()).newInstance();
//            
//            Vector outletConn = PstConnection.list(0, 0, ""+PstConnection.fieldNames[PstConnection.FLD_TYPE_CONNECTION]+"=0", "");
//            if(outletConn.size()>=1){
//                    //conOb = new OutletConnection();
//                    conOb = (OutletConnection) outletConn.get(0);
//            }
//            Class.forName(this.conOb.getDbdriver()).newInstance();
//            dbConn = DriverManager.getConnection(this.conOb.getDburl(), this.conOb.getDbuser(), this.conOb.getDbpasswd());
//        }
//        catch(Exception e) {
//            ManagerTransferData.setStatusProses("SLIK Gagal Koneksi ke Corebanking<br>");
//            ManagerTransferDataDebitur.setStatusProses("SLIK Gagal Koneksi ke Corebanking<br>");
//            ManagerTransferDataKredit.setStatusProses("SLIK Gagal Koneksi ke Corebanking<br>");
//            ManagerTransferDataAgunan.setStatusProses("SLIK Gagal Koneksi ke Corebanking<br>");
//            ManagerTransferDataBankGaransi.setStatusProses("SLIK Gagal Koneksi ke Corebanking<br>");
//            ManagerTransferDataPengurusPemilik.setStatusProses("SLIK Gagal Koneksi ke Corebanking<br>");
//        }
//    }
    public OutletConnection getConfigurasiConnection() {
        OutletConnection outletConnection = new OutletConnection();
        try {
            Vector outletConn = PstConnection.list(0, 0, "" + PstConnection.fieldNames[PstConnection.FLD_TYPE_CONNECTION] + "=0", "");
            if (outletConn.size() >= 1) {
                outletConnection = (OutletConnection) outletConn.get(0);
            }
        } catch (Exception ex) {
        }

        return outletConnection;
    }
    
    public Bank getBank() {
        Bank bank = new Bank();
        try {
            Vector vBank = PstBank.list(0, 0, "", "");
            if (vBank.size() >= 1) {
                bank = (Bank) vBank.get(0);
            }
        } catch (Exception ex) {
        }

        return bank;
    }

    public String actionTransferDataDebitur() {
        return actionTransferDataDebitur("", 0, 0,0);
    }

    public String actionTransferDataDebitur(String cif, long periodeId, int counter, int typeGetData) {
        String action = "";
        if (ManagerTransferDataDebitur.running==false && typeGetData==0) {
            return action;
        }
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            OutletConnection outletConnection = getConfigurasiConnection();
            String dbMasterBackup="";
            if(typeGetData==1){
                dbMasterBackup = outletConnection.getDbDatabase();
            }else{
                dbMasterBackup = outletConnection.getDbDatabaseBackup();
            }
            //createConn(); 
            String sql = "SELECT  DISTINCT "
                    //+ "a.NO_NSB, "
                    + dbMasterBackup+".NASABAH.NO_NSB, " 
                    + dbMasterBackup+".NASABAH.KD_IDENTITAS, "
                    + dbMasterBackup+".NASABAH.NO_IDENTITAS, "
                    //+ "a.NAMA_NSB, "
                    + dbMasterBackup+".NASABAH.NAMA_NSB, "
                    + dbMasterBackup+".NASABAH.NAMA_SINGKAT, "
                    + dbMasterBackup+".SID.STS_GELAR, "
                    + dbMasterBackup+".NASABAH.JNS_KELAMIN, "
                    + dbMasterBackup+".NASABAH.TEMPAT_LAHIR, "
                    + dbMasterBackup+".NASABAH.TGL_LAHIR, "
                    + dbMasterBackup+".NASABAH.NPWP, "
                    //+ "a.ALAMAT, "
                    + dbMasterBackup+".NASABAH.ALAMAT, "
                    + dbMasterBackup+".NASABAH.KELURAHAN, "
                    + dbMasterBackup+".NASABAH.KD_KECAMATAN, "
                    + dbMasterBackup+".NASABAH.KD_DATI_II, "
                    + dbMasterBackup+".NASABAH.KD_POS, "
                    + dbMasterBackup+".NASABAH.TELEPON, "
                    + dbMasterBackup+".NASABAH.NO_HP, "
                    + dbMasterBackup+".NASABAH.EMAIL, "
                    + dbMasterBackup+".NASABAH.KD_NEGARA, "
                    + dbMasterBackup+".NASABAH.PEKERJAAN, "
                    + dbMasterBackup+".NASABAH.TEMPAT_KERJA, "
                    + dbMasterBackup+".NASABAH.BIDANG_KERJA, "
                    + dbMasterBackup+".NASABAH.ALAMAT_KERJA, "
                    + dbMasterBackup+".NASABAH.HASIL_KOTOR, "
                    + dbMasterBackup+".NASABAH.JML_TANGGUNGAN, "
                    + dbMasterBackup+".NASABAH.HUB_DGN_NSB, "
                    + dbMasterBackup+".NASABAH.STS_KAWIN, "
                    + dbMasterBackup+".SID.LANGGAR_BMK, "
                    + dbMasterBackup+".SID.LAMPAU_BMK, "
                    + dbMasterBackup+".NASABAH.NAMA_IBU, "
                    + dbMasterBackup+".SID.NM_PERUSAHAAN, "
                    + dbMasterBackup+".NASABAH.JENIS_USAHA, "
                    + dbMasterBackup+".SID.TEMPAT_AKTE, "
                    + dbMasterBackup+".SID.NO_AKTE_AWAL, "
                    + dbMasterBackup+".SID.TGL_AKTE_AWAL, "
                    + dbMasterBackup+".SID.NO_AKTE_AKHIR, "
                    + dbMasterBackup+".SID.TGL_AKTE_AKHIR, "
                    + dbMasterBackup+".SID.BIDANG_USAHA, "
                    + dbMasterBackup+".SID.HUB_DGN_BANK, " //hub_dgn_bank
                    + dbMasterBackup+".SID.GO_PUBLIC, "
                    + dbMasterBackup+".SID.GOL_DEB, "
                    + dbMasterBackup+".SID.RATING_USH_DEB, "
                    + dbMasterBackup+".SID.LEMBAGA_PRINGKAT, "
                    + dbMasterBackup+".SID.GROUP_DEB, "
                    + dbMasterBackup+".SID.PEKERJAAN_DEB, "
                    //+ "a.KD_CAB, "
                    + dbMasterBackup+".NASABAH.KD_CAB, "
                    + dbMasterBackup+".NASABAH.KD_JNS_NSB, "
                    + dbMasterBackup+".NASABAH.SUMBER_DANA "
                    + "FROM "+dbMasterBackup+".NASABAH  "
                    + "LEFT JOIN "+dbMasterBackup+".SID   "
                    + "ON "+dbMasterBackup+".NASABAH.NO_NSB = "+dbMasterBackup+".SID.NO_NSB AND "+dbMasterBackup+".SID.KD_CAB = "+dbMasterBackup+".NASABAH.KD_CAB "
                    
                    + " INNER JOIN ( SELECT CIFMSTID  FROM "
                    + "( SELECT "+dbMasterBackup+".LNACCAT.CIFMSTID FROM "+dbMasterBackup+".LNACCAT "
                    //opie-eyek
                    + ","+dbMasterBackup+".SYSTEM_HOST H ";
            
                    if(typeGetData==0){
                        sql=sql+ "WHERE ( ("+dbMasterBackup+".LNACCAT.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCAT.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCAT.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCAT.PAIDDATE) = YEAR(H.OPEN_DATE) ) )";
                    }
                    
                    sql=sql+ " UNION "
                    + " SELECT "+dbMasterBackup+".LNACCATT.CIFMSTID FROM "+dbMasterBackup+".LNACCATT "
                    //opie-eyek
                    + ","+dbMasterBackup+".SYSTEM_HOST H ";
                    if(typeGetData==0){
                        sql=sql+ "WHERE ( ("+dbMasterBackup+".LNACCATT.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCATT.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCATT.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCATT.PAIDDATE) = YEAR(H.OPEN_DATE) ) )";
                    }
                    sql=sql+ " UNION "
                    + " SELECT "+dbMasterBackup+".LNACCTA.CIFMSTID FROM "+dbMasterBackup+".LNACCTA "
                    //opie-eyek
                    + ","+dbMasterBackup+".SYSTEM_HOST H ";
                    
                    if(typeGetData==0){
                        sql=sql + " WHERE ( ("+dbMasterBackup+".LNACCTA.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCTA.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCTA.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCTA.PAIDDATE) = YEAR(H.OPEN_DATE) ) )";
                    }
                    
                    sql=sql+ " UNION "
                   
                    + " SELECT "+dbMasterBackup+".BGARANSI_MASTER.NO_NSB AS CIFMSTID "
                    + "FROM "+dbMasterBackup+".BGARANSI_MASTER, " +
                    " "+dbMasterBackup+".TBL_VALUTA, " +
                    " "+dbMasterBackup+".TBL_JENIS_BG " +
                    "WHERE ("+dbMasterBackup+".BGARANSI_MASTER.KD_VALUTA = "+dbMasterBackup+".TBL_VALUTA.KD_VALUTA) " +
                    "AND ("+dbMasterBackup+".BGARANSI_MASTER.KD_JENIS = "+dbMasterBackup+".TBL_JENIS_BG.KD_JENIS) " +
                    "AND ( " +
                    "( " +
                    "MONTH("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR) = (SELECT " +
                    "  MONTH(OPEN_DATE) " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    "AND YEAR("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR) = (SELECT " +
                    "  YEAR(OPEN_DATE) " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    ") " +
                    "OR ( " +
                    "("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR > (SELECT " +
                    "  OPEN_DATE " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    ") " ;
                    if(typeGetData==0){
                        sql=sql+ "AND ("+dbMasterBackup+".BGARANSI_MASTER.KD_STATUS NOT IN ('9')) ";
                    }
                    sql=sql+") " +
                    ") "
                    + ") AS TAB ) "
                    + " AS BAR ON BAR.CIFMSTID="+dbMasterBackup+".NASABAH.NO_NSB";

            if (!cif.equals("")) {
                sql = sql + " WHERE "+dbMasterBackup+".NASABAH.NO_NSB='" + cif + "'";
            }
            
            // sql = sql + " WHERE DBLMON.NASABAH.NO_NSB IN ('13572',\n" + Transfer Data 90618996 00001500
            //sql=sql+" WHERE BAR.CIFMSTID IN ('00213048','00334851','90647437','90829614','99167062','99167062','00414473','99173616')";
            //sql=sql+" WHERE BAR.CIFMSTID ='00001500'";
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int no = 0;
            int noInsert=0;
            int noUpdate=0;
            String cifHistory="";
            int data=0;
            while (rs.next()) {
                //sent=false;
                data=1;
                try {
                    no = no + 1;
                    if (ManagerTransferDataDebitur.running==false && typeGetData==0) {
                        return action;
                    }
                    MappingDebiturBpd entDebitur = new MappingDebiturBpd();
                    MappingDebiturBpd currEntDebitur = new MappingDebiturBpd();
                    MappingDebiturBpd prevEntDebitur = new MappingDebiturBpd();
                    String entCif = rs.getString("no_nsb"); //entMappingKreditBpd.getCif().replaceAll("\\s","");
                    entDebitur.setCif(entCif.replace("\\s", ""));
                    cifHistory = entDebitur.getCif();
                    boolean checkDebitur = true;//PstMappingDebiturBpd.checkDebitur(periodeId, cif);
                    if (checkDebitur) {
                        //entDebitur.setCif(rs.getString("no_nsb"));
                        //buatkan perbedaan antara kode golongan debitur dengan kode golongan badan usaha
                        //dibuatkan perbedaan saja
                        try{
                            entDebitur.setKodeJenisNsb(rs.getInt("kd_jns_nsb"));
                        }catch(Exception es){
                            entDebitur.setKodeJenisNsb(0);
                        }
                        
                        //cek previuse data di database
                        if(0==0){
                            requestPrevEntityObject(entDebitur, periodeId, entCif, prevEntDebitur);
                        }
                        
                        entDebitur.setKodeKantorCabang(rs.getString("kd_cab").replace("\\s",""));
                        //cek di SID table
                        requestEntityObjectSID(entDebitur, conn, st, rs,dbMasterBackup);
                        
                        entDebitur.setFlagDetail("D");
                        entDebitur.setJenisIdentitas(SessGetContentDataOjk.getContentDataJenisIdentitas(rs.getString("kd_identitas")));
                        SessValidasiInputan.statusProsesValidasi="";
                        entDebitur.setNik(SessValidasiInputan.validasiNik(rs.getString("no_identitas"), entDebitur.getKodeJenisNsb()));
                        entDebitur.setNamaIdentitas(SessValidasiInputan.checkKarakter(rs.getString("nama_nsb"),entDebitur.getKodeJenisNsb(), SessValidasiInputan.FLD_DB_NAMA));
                        entDebitur.setNamaLengkap(SessValidasiInputan.checkKarakter(rs.getString("nama_nsb"),entDebitur.getKodeJenisNsb(), SessValidasiInputan.FLD_DB_NAMA_LENGKAP));
                        
                        if(!entDebitur.getNamaLengkap().equals("")){
                            boolean validLetter =  SessValidasiInputan.validateLetters(entDebitur.getNamaLengkap());
                            if(!validLetter){
                                entDebitur.setNamaLengkap("");
                                SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> NAMA LENGKAP, format data harus huruf, spasi dan karakter -'., Wajib di perbaiki di core";
                            }
                        }
                        
                        if(!entDebitur.getNamaIdentitas().equals("")){
                            boolean validLetter =  SessValidasiInputan.validateLetters(entDebitur.getNamaIdentitas());
                            if(!validLetter){
                                entDebitur.setNamaIdentitas("");
                                SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> NAMA SESUAI IDENTITAS, format data harus huruf, spasi dan karakter -'., Wajib di perbaiki di core";
                            }
                        }
                        
                        //status gelar di debitur
                        entDebitur.setKodeStatusGelar(SessValidasiInputan.checkStatusGelarDebitur(SessGetContentDataOjk.getContentDataStatusPendidikan(entDebitur.getKodeStatusGelar()), entDebitur.getKodeJenisNsb()));
                        
                        String jnsKelamin=rs.getString("jns_kelamin");
                        if(jnsKelamin.equals("1")){
                            entDebitur.setJekel("L");
                        } else if(jnsKelamin.equals("2")){
                            entDebitur.setJekel("P");
                        }else{
                            if(jnsKelamin.equals("0")){
                                entDebitur.setJekel("B");
                            }
                        }
                        
                        entDebitur.setTempatLahir(SessValidasiInputan.validasiTempatLagir(rs.getString("tempat_lahir"),entDebitur.getKodeJenisNsb()));
                        
                        entDebitur.setTanggalLahir(rs.getDate("tgl_lahir"));
                        if(entDebitur.getTanggalLahir()==null){
                            if(entDebitur.getKodeJenisNsb()==1){
                                SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> Tanggal Lahir Kosong, Wajib di perbaiki di core";
                            }
                        }else{
                            Date newDataeLahir= new Date();
                            newDataeLahir.setYear(newDataeLahir.getYear()-1);
                            if(entDebitur.getTanggalLahir().after(newDataeLahir)){
                                if(entDebitur.getKodeJenisNsb()==1){
                                    SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> Tanggal lahir melebihi tahun ini, Wajib diperbaiki di core";
                                }
                            }
                        }
                        
                        entDebitur.setNpwp(SessValidasiInputan.validasiNpwp(rs.getString("npwp"),entDebitur.getKodeJenisNsb()));
                        
                        entDebitur.setAlamat(rs.getString("alamat"));
                        try{
                            entDebitur.setAlamat(SessValidasiInputan.validasiAlamat(entDebitur.getAlamat()));//entDebitur.getAlamat().replace("\\", " "));
                        }catch(Exception ex){
                            entDebitur.setAlamat(entDebitur.getAlamat());
                        }
                        entDebitur.setKelurahan(rs.getString("kelurahan"));
                        try{
                            entDebitur.setKelurahan(SessValidasiInputan.validasiKelurahan(entDebitur.getKelurahan()));
                            
                        }catch(Exception ex){
                            entDebitur.setKelurahan(entDebitur.getKelurahan());
                        }
                        entDebitur.setKecamatan(SessValidasiInputan.validasiKecamatan(rs.getString("kd_kecamatan")));
                        
                        entDebitur.setKodeKab(SessValidasiInputan.checkKodeDati(SessGetContentDataOjk.getContentDataKabKota(rs.getString("kd_dati_ii"))));
                        
                        entDebitur.setKodePos(SessValidasiInputan.checkKodePos(rs.getString("kd_pos")));
                        
                        String noTlp="";
                        try{
                            if(rs.getString("telepon")==null || rs.getString("telepon").equals("")){
                                  noTlp="0";
                            }else{
                                noTlp=rs.getString("telepon");
                                noTlp = noTlp.replaceAll("[^0-9]", "");
                                if(noTlp.equals("")){
                                    noTlp="0";
                                }
                            }
                            if(noTlp.length()>15){
                                noTlp="";
                                SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> TELEPON, panjang maksimum 15, Wajib di diperbaiki di core";
                            }
                        }catch(Exception ex){
                        }
                        
                        entDebitur.setTelepon(SessValidasiInputan.checkTelephone(noTlp));
                        
                        try{
                            entDebitur.setNomorHp(SessValidasiInputan.checkHandphone(rs.getString("no_hp"),entDebitur.getKodeJenisNsb()));
                        }catch(Exception ex){
                            //entDebitur.setNomorHp(nomorHp);
                        }
                        
                        entDebitur.setEmail(SessValidasiInputan.checkAlamatEmail(rs.getString("email")));
                        
                        entDebitur.setKodeDomisili(SessGetContentDataOjk.getContentDataNegaraDomisili(rs.getString("kd_negara")));
                        if(entDebitur.getKodeDomisili()==null || entDebitur.getKodeDomisili().equals("")){
                            SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> Kode Negara Domisili Kosong, Wajib di perbaiki di core";
                        }
                        String kodePekerjaan="";
                        try{
                            if(entDebitur.getKodePekerjaan()==null || entDebitur.getKodePekerjaan().equals("") ){
                                kodePekerjaan=rs.getString("pekerjaan");
                            }else{
                                kodePekerjaan=entDebitur.getKodePekerjaan();
                            }
                        }catch(Exception ex){}
                        entDebitur.setKodePekerjaan(SessValidasiInputan.checkKodePekerjaan(SessGetContentDataOjk.getContentDataKodePekerjaan(kodePekerjaan),entDebitur.getKodeJenisNsb()));
                        
                        entDebitur.setTempatBekerja(rs.getString("tempat_kerja"));
                        try{
                            if(entDebitur.getTempatBekerja()==null || entDebitur.getTempatBekerja().equals("")){
                                entDebitur.setTempatBekerja("NA");
                            }
                            entDebitur.setTempatBekerja(SessValidasiInputan.validasiTempatBekerja(entDebitur.getTempatBekerja(),entDebitur.getKodeJenisNsb()));
                        }catch(Exception ex){
                            entDebitur.setTempatBekerja(entDebitur.getTempatBekerja());
                        }
                        
                        entDebitur.setAlamatTempatBekerja(rs.getString("alamat_kerja"));
                        try{
                            entDebitur.setAlamatTempatBekerja(SessValidasiInputan.validasiAlamatTempatBekerja(entDebitur.getAlamatTempatBekerja(),entDebitur.getKodeJenisNsb()));
                        }catch(Exception ex){
                            entDebitur.setAlamatTempatBekerja("");
                        }
                        
                        try {
                            entDebitur.setPenghasilanKotor(rs.getDouble("hasil_kotor"));
                        } catch (Exception es) {
                            entDebitur.setPenghasilanKotor(0);
                        }

                        entDebitur.setJmlTanggungan(rs.getInt("jml_tanggungan"));
                        try{
                            //entDebitur.setKodeHub(entDebitur.getKodeHub().replace("\\", " "));
                            entDebitur.setKodeHub(SessGetContentDataOjk.getContentDataHubunganDenganPelapor(entDebitur.getKodeHub()));
                        }catch(Exception ex){
                            entDebitur.setKodeHub(entDebitur.getKodeHub());
                        }
                        
                        if(entDebitur.getKodeJenisNsb()==1){
                            if(entDebitur.getKodeHub()==null || entDebitur.getKodeHub().equals("")){
                                SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> Kode Hubungan, Wajib di perbaiki di core";
                            }
                        }
                        
                        entDebitur.setKodeUsahaTempatBekerja(SessValidasiInputan.checkBidangUsahaIndividu(SessGetContentDataOjk.getContentDataKodeBidangUsaha(entDebitur.getKodeUsahaTempatBekerja()),entDebitur.getKodeJenisNsb()));// bidang_kerja update, salah maapping
                        
                        if(entDebitur.getKodeJenisNsb()==1){//individu, langsung set menjadi penduduk
                            entDebitur.setKodeGol("9000");
                        }else{
                            entDebitur.setKodeGol(SessValidasiInputan.checkGolonganDebitur(SessGetContentDataOjk.getContentDataKodeGolDeb(entDebitur.getKodeGol()), entDebitur.getCif()));
                        }
                        
                        entDebitur.setStatus(rs.getString("sts_kawin"));
                        
                        try{
                            if(entDebitur.getStatus()==null || entDebitur.getStatus().equals("")){
                                if(entDebitur.getJmlTanggungan()>0){
                                    entDebitur.setStatus("1");
                                }else{
                                    entDebitur.setStatus("2");
                                }
                            }
                        }catch(Exception es){
                        
                        }
                        
                        entDebitur.setKodePenghasilan(SessGetContentDataOjk.getContentKodePenghasilan(rs.getString("sumber_dana")));
                        if(entDebitur.getKodeJenisNsb()==1){
                            if(entDebitur.getKodePenghasilan()==null || entDebitur.getKodePenghasilan().equals("")){
                                entDebitur.setKodePenghasilan("3");
                                //SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> Kode Sumber Penghasilan, Wajib di perbaiki di core jika memiliki fasilitas penyediaan dana baru atau perpanjangan sejak 1 Juni 2016.";
                            }
                        }
                        
                        entDebitur.setMelanggarBmpk(SessValidasiInputan.checkMelanggarBmpk(entDebitur.getMelampuiBmpk()));
                        
                        entDebitur.setMelampuiBmpk(SessValidasiInputan.checkMelampauBmpk(entDebitur.getMelampuiBmpk()));
                        
                        entDebitur.setNamaIbuKandung(SessValidasiInputan.checkKarakter(rs.getString("nama_ibu"),entDebitur.getKodeJenisNsb(), SessValidasiInputan.FLD_DB_NAMA_IBU_KANDUNG));
                        if(entDebitur.getNamaIbuKandung()==null || entDebitur.getNamaIbuKandung().equals("")){
                            if(entDebitur.getKodeJenisNsb()==1){
                                SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> Nama Gadis Ibu Kandung Kosong, Wajib di perbaiki di core";
                            }
                        }else{
                            if(entDebitur.getKodeJenisNsb()==1){
                                boolean validLetter =  SessValidasiInputan.validateLetters(entDebitur.getNamaIbuKandung());
                                if(!validLetter){
                                    SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> Nama Gadis Ibu Kandung mengandung number, Wajib di perbaiki di core";
                                }
                            }
                        }
                        
                        entDebitur.setNamaBadanUsaha(SessValidasiInputan.checkKarakter(rs.getString("nama_singkat"),entDebitur.getKodeJenisNsb(), SessValidasiInputan.FLD_DB_NAMA_BADAN_USAHA));
                        
                        //ini di cek dari sini data untuk badan usaha
                        entDebitur.setNoIdentitas(SessValidasiInputan.checkIdentitasUsaha(rs.getString("npwp"),entDebitur.getKodeJenisNsb()));
                        
                        //masalah di debitu badanusaha
                        entDebitur.setKodeJenisUsaha(SessValidasiInputan.checkBentukBadanUsaha(SessGetContentDataOjk.getContentDataBentukBdnUsh(entDebitur.getKodeJenisUsaha()),entDebitur.getKodeJenisNsb()));//(rs.getString("jenis_usaha"));
                        
                        entDebitur.setTempatPendirian(SessValidasiInputan.checkTempatPendirianUsaha(entDebitur.getTempatPendirian(),entDebitur.getKodeJenisNsb()));
                        entDebitur.setNoAkte(SessValidasiInputan.checkNoAktePendirian(entDebitur.getNoAkte(),entDebitur.getKodeJenisNsb()));
                        entDebitur.setTglAktePendirian(SessValidasiInputan.checkTglAktePendirian(entDebitur.getTglAktePendirian(),entDebitur.getKodeJenisNsb()));
                        entDebitur.setNoAktePerubahan(SessValidasiInputan.checkNoAktePerubahan(entDebitur.getNoAktePerubahan(),entDebitur.getKodeJenisNsb()));
                        entDebitur.setTglAktePerubahan(SessValidasiInputan.checkTglAktePerubahan(entDebitur.getTglAktePerubahan(),entDebitur.getKodeJenisNsb()));
                        
                        entDebitur.setKodeBidangUsaha(SessValidasiInputan.checkBidangUsaha(SessGetContentDataOjk.getContentDataKodeBidangUsaha(entDebitur.getKodeBidangUsaha()),entDebitur.getKodeJenisNsb()));
                        
                        entDebitur.setKodeHubLjk(SessGetContentDataOjk.getContentDataHubunganDenganPelapor(entDebitur.getKodeHubLjk()));
                        if(entDebitur.getKodeJenisNsb()!=1){
                            if(entDebitur.getKodeHubLjk().equals("")){
                                    SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br> KODE HUBUNGAN DENGAN LJK, Wajib di perbaiki di core";
                            }
                        }
                        
                        entDebitur.setGoPublic(entDebitur.getGoPublic());
                        try{
                            if(entDebitur.getGoPublic()==null ||  entDebitur.getGoPublic().equals("") ||  entDebitur.getGoPublic().equals(" ")){
                                entDebitur.setGoPublic("T");
                            }
                        }catch( Exception es){
                        
                        }
                        
                        entDebitur.setPeringkat(entDebitur.getPeringkat());
                        if(entDebitur.getPeringkat()!=null && !entDebitur.getPeringkat().equals("")){
                            entDebitur.setLembagaPemeringkat(SessValidasiInputan.checkLembagaPemeringkat(SessGetContentDataOjk.getContentDataKodeLembagaPemeringkat(entDebitur.getLembagaPemeringkat()),entDebitur.getKodeJenisNsb()));
                        }else{
                            entDebitur.setLembagaPemeringkat("");
                        }
                        
                        entDebitur.setNamaGroup(entDebitur.getNamaGroup());
                        
                        try{
                            
                            if(entDebitur.getKodeJenisNsb()!=1){
                                if(entDebitur.getTglAktePerubahan()!=null || entDebitur.getTglAktePendirian()!=null){
                                    if(entDebitur.getTglAktePerubahan().compareTo(entDebitur.getTglAktePendirian())==0){
                                        if(entDebitur.getNoAkte()!=null || entDebitur.getNoAktePerubahan()!=null){
                                            if(!entDebitur.getNoAkte().trim().equals(entDebitur.getNoAktePerubahan().trim())){
                                                    SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br>NOMOR AKTE PERUBAHAN TERAKHIR, NOMOR AKTE PENDIRIAN harus sama dengan NOMOR AKTE PERUBAHAN TERAKHIR, karena TANGGAL AKTE PENDIRIAN = TGL AKTE PERUBAHAN TERAKHIR, Wajib di perbaiki di core";
                                            }
                                        }
                                    }
                                }
                            }
                            
                            if(entDebitur.getKodeJenisNsb()!=1){
                                if(entDebitur.getTglAktePerubahan()!=null || entDebitur.getTglAktePendirian()!=null){
                                    if(entDebitur.getTglAktePendirian().after(entDebitur.getTglAktePerubahan())){
                                        SessValidasiInputan.statusProsesValidasi = SessValidasiInputan.statusProsesValidasi+"<br>TANGGAL AKTE PENDIRIAN, TANGGAL AKTE PENDIRIAN harus lebih kecil/sama dengan TGL AKTE PERUBAHAN TERAKHIR, Wajib di perbaiki di core";
                                    }
                                }
                            }
                            
                        }catch(Exception ex){
                        
                        }
                        
                        
                        Date newDate = new Date();
                        entDebitur.setOpenDate(newDate);
                        entDebitur.setPeriodeId(periodeId);
                        
                        try{
                            long oidDebitur = PstMappingDebiturBpd.checkDebitur(periodeId, entCif);
                            if(oidDebitur!=0){
                                entDebitur.setOID(oidDebitur);
                                long insertDebitur = PstMappingDebiturBpd.updateExc(entDebitur);
                                currEntDebitur = PstMappingDebiturBpd.fetchExc(oidDebitur);
                                noUpdate=noUpdate+1;
                                if(!SessValidasiInputan.getStatusProsesValidasi().equals("")){
                                    insertHistory(0, "Transfer Data Update", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasi, "Debitur",periodeId);
                                    if(SessValidasiInputan.wajibDiisi(SessValidasiInputan.getStatusProsesValidasi())){
                                        long updateData = PstDebiturBdnUsaha.updateStatusData(insertDebitur);
                                    }
                                }else{
                                    //data lengkap
                                    insertHistory(0, "Transfer Data Update", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasi, "Debitur",periodeId);
                                    long updateData = PstDebiturBdnUsaha.updateStatusData(insertDebitur);
                                }
                                
                                //buatkan status apakah data itu create /update /not change
                                if(entDebitur.getStatusDataPelaporan()==0){//insert
                                    long updateData = PstDebiturBdnUsaha.updateStatusOperasiData(insertDebitur,0);
                                }else{
                                    //di cek apakah ada perubahan data di bulan sebelumnya?
                                    String perubahanData = entDebitur.getLogDetail(prevEntDebitur, currEntDebitur);
                                    if(perubahanData.equals("")){
                                        long updateData = PstDebiturBdnUsaha.updateStatusOperasiData(insertDebitur,2);
                                    }else{
                                        long updateData = PstDebiturBdnUsaha.updateStatusOperasiData(insertDebitur,1);
                                    }
                                }
                                action="Transfer Data Sudah Berhasil di Update";
                                
                            }else{
                                if(typeGetData==0){
                                    long insertDebitur = PstMappingDebiturBpd.insertExc(entDebitur);
                                    currEntDebitur = PstMappingDebiturBpd.fetchExc(insertDebitur);
                                    noInsert=noInsert+1;
                                    if(!SessValidasiInputan.getStatusProsesValidasi().equals("")){
                                        insertHistory(0, "Transfer Data ", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasi, "Debitur",periodeId);
                                        if(SessValidasiInputan.wajibDiisi(SessValidasiInputan.getStatusProsesValidasi())){
                                            long updateData = PstDebiturBdnUsaha.updateStatusData(insertDebitur);
                                        }
                                    }else{
                                        //data lengkap/tdk bermasalah
                                        insertHistory(0, "Transfer Data ", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasi, "Debitur",periodeId);
                                        long updateData = PstDebiturBdnUsaha.updateStatusData(insertDebitur);
                                    }

                                    //buatkan status apakah data itu create /update /not change
                                    if(entDebitur.getStatusDataPelaporan()==0){//insert
                                        long updateData = PstDebiturBdnUsaha.updateStatusOperasiData(insertDebitur,0);
                                    }else{
                                        //di cek apakah ada perubahan data di bulan sebelumnya?
                                        String perubahanData = entDebitur.getLogDetail(prevEntDebitur, currEntDebitur);
                                        if(perubahanData.equals("")){
                                            long updateData = PstDebiturBdnUsaha.updateStatusOperasiData(insertDebitur,2);
                                        }else{
                                            long updateData = PstDebiturBdnUsaha.updateStatusOperasiData(insertDebitur,1);
                                        }
                                    }
                                    action="Transfer Data Sudah Berhasil di Insert";
                                }    
                            }
                            
                            ManagerTransferData.setStatusDebitur("Transfer Data Debitur : " + no + "/" + counter + "<br>");
                            ManagerTransferDataDebitur.setStatusDebitur(""
                                    + "Transfer Data Debitur Update: " + noUpdate + "/" + counter + "<br>"
                                    + "Transfer Data Debitur Baru: " + noInsert + "/" + counter + "<br>"
                                    + "Transfer Data Debitur : " + no + "/" + counter + "<br>");
                            
                        }catch(Exception ex){
                            insertHistory(0, "", no, periodeId, "Debitur", entCif,periodeId);
                            action="Transfer Data Gagal Debitur ";
                        }
                    }
                } catch (Exception es) {
                    System.out.print("Eror loh urutan " + no);
                    insertHistory(0, "", no, periodeId, "Transfer Data Gagal Debitur", cifHistory,periodeId);
                    action="Transfer Data Gagal Debitur ";
                }
            }
            if(data==0){
                   action="Data tidak ditemukan, Update data gagal";
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            action="Koneksi database bermasalah ";
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }

        return action;
    }

    public int countTransferDataDebitur(String cif, long periodeId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //createConn(); 
            OutletConnection outletConnection = getConfigurasiConnection();
            String dbMasterBackup="";
            dbMasterBackup = outletConnection.getDbDatabaseBackup();
            String sql = "SELECT COUNT(DISTINCT "
                    + ""+dbMasterBackup+".NASABAH.NO_NSB) AS TOTAL "
                    + "FROM "+dbMasterBackup+".NASABAH  "
                    + "LEFT JOIN "+dbMasterBackup+".SID   "
                    + " ON "+dbMasterBackup+".NASABAH.NO_NSB = "+dbMasterBackup+".SID.NO_NSB AND "+dbMasterBackup+".SID.KD_CAB = "+dbMasterBackup+".NASABAH.KD_CAB "
                    + " INNER JOIN ( SELECT CIFMSTID  FROM "
                    + "( SELECT "+dbMasterBackup+".LNACCAT.CIFMSTID FROM "+dbMasterBackup+".LNACCAT "
                    //opie-eyek
                    + ", "+dbMasterBackup+".SYSTEM_HOST H "
                        + "WHERE ( ("+dbMasterBackup+".LNACCAT.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCAT.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCAT.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCAT.PAIDDATE) = YEAR(H.OPEN_DATE) ) )"
                    
                    + " UNION "
                    + " SELECT "+dbMasterBackup+".LNACCATT.CIFMSTID FROM "+dbMasterBackup+".LNACCATT "
                    //opie-eyek
                    + ","+dbMasterBackup+".SYSTEM_HOST H "
                        + "WHERE ( ("+dbMasterBackup+".LNACCATT.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCATT.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCATT.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCATT.PAIDDATE) = YEAR(H.OPEN_DATE) ) )"
                    
                    + " UNION "
                    + " SELECT "+dbMasterBackup+".LNACCTA.CIFMSTID FROM "+dbMasterBackup+".LNACCTA "
                    //opie-eyek
                    + ","+dbMasterBackup+".SYSTEM_HOST H "
                        + "WHERE ( ("+dbMasterBackup+".LNACCTA.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCTA.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCTA.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCTA.PAIDDATE) = YEAR(H.OPEN_DATE) ) )"
                    
                    + " UNION "
                    //+ " SELECT "+dbMasterBackup+".BGARANSI_MASTER.NO_NSB AS CIFMSTID FROM "+dbMasterBackup+".BGARANSI_MASTER "
                    //opie-eyek
                    //+ ","+dbMasterBackup+".SYSTEM_HOST D"
                    //+ " WHERE (("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR > D.OPEN_DATE) OR (MONTH("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR)=MONTH(D.OPEN_DATE) AND YEAR("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR)=YEAR(D.OPEN_DATE))) AND "+dbMasterBackup+".BGARANSI_MASTER.KD_STATUS <> '9'"
                    + " SELECT "+dbMasterBackup+".BGARANSI_MASTER.NO_NSB AS CIFMSTID "
                    + "FROM "+dbMasterBackup+".BGARANSI_MASTER, " +
                    " "+dbMasterBackup+".TBL_VALUTA, " +
                    " "+dbMasterBackup+".TBL_JENIS_BG " +
                    "WHERE ("+dbMasterBackup+".BGARANSI_MASTER.KD_VALUTA = "+dbMasterBackup+".TBL_VALUTA.KD_VALUTA) " +
                    "AND ("+dbMasterBackup+".BGARANSI_MASTER.KD_JENIS = "+dbMasterBackup+".TBL_JENIS_BG.KD_JENIS) " +
                    "AND ( " +
                    "( " +
                    "MONTH("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR) = (SELECT " +
                    "  MONTH(OPEN_DATE) " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    "AND YEAR("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR) = (SELECT " +
                    "  YEAR(OPEN_DATE) " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    ") " +
                    "OR ( " +
                    "("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR > (SELECT " +
                    "  OPEN_DATE " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    ") " +
                    "AND ("+dbMasterBackup+".BGARANSI_MASTER.KD_STATUS NOT IN ('9')) " +
                    ") " +
                    ") "
                    
                    + ") AS TAB ) "
                    
                    + " AS BAR ON BAR.CIFMSTID="+dbMasterBackup+".NASABAH.NO_NSB";

            if (!cif.equals("")) {
                sql = sql + "WHERE "+dbMasterBackup+".NASABAH.NO_NSB='" + cif + "'";
            }
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int no = 0;
            while (rs.next()) {
                //sent=false;
                try {
                    count = rs.getInt("TOTAL");
                } catch (Exception es) {
                    System.out.print("Eror loh urutan " + no);
                }
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }

        return count;
    }

    public int countTransferDataKredit(String cif, long periodeId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //createConn(); 
            //masalah di sid_kreditnya, kalau di inner join gak ketemu dia
            OutletConnection outletConnection = getConfigurasiConnection();
            
            String dbMasterBackup="";
            dbMasterBackup = outletConnection.getDbDatabaseBackup();
            String sql = ""
                    + "SELECT COUNT(DISTINCT "
                    + "CONCAT(BRANCHID,ACCNBR)) AS TOTAL "
                    + "FROM "
                    + "(  "
                    + "SELECT CIFMSTID , ACCNBR, CONCAT(BRANCHID, ACCNBR) AS NO_REK, LNCCHRID, CHRTBSL2,CONTRCNO, CONTRTDT, PKAKHIR, PKAKHDT, ADNDMSEQ,  "
                    + "TGL_AWAL_KREDIT,TGL_MULAI,LNDUEDT,DCATBSL2,USAGEID, ORIENTID, SIDDT2ID, PRJAMSID, CCYID, LNINTRST,INTRTYPE,SRCID,PLAFOND, KUMULATIF_REALISASI, "
                    + "PENALTY,ENDBAL,COLID,TGL_MACET,TUNGGPKK, TUNGGBNG, BASETGDY, COUNTBNG, RESTDATE, KONDISI, TGL_KONDISI, BRANCHID "
                    + "FROM (  "
                    + "SELECT "+dbMasterBackup+".LNACCAT.ACCNBR, "+dbMasterBackup+".LNACCAT.CIFMSTID, "+dbMasterBackup+".LNACCAT.LNCCHRID, "+dbMasterBackup+".LNACCAT.CHRTBSL2,  "+dbMasterBackup+".LNACCAT.CONTRCNO, "+dbMasterBackup+".LNACCAT.CONTRTDT, "+dbMasterBackup+".LNACCAT.PKAKHIR, "+dbMasterBackup+".LNACCAT.PKAKHDT, "+dbMasterBackup+".LNACCAT.ADNDMSEQ,  "
                    + "(CASE WHEN "+dbMasterBackup+".LNACCAT.CONTRTDT > "+dbMasterBackup+".LNACCAT.ACCOPNDT THEN "+dbMasterBackup+".LNACCAT.CONTRTDT ELSE "+dbMasterBackup+".LNACCAT.ACCOPNDT END) AS TGL_AWAL_KREDIT, "
                    + "(CASE WHEN "+dbMasterBackup+".LNACCAT.CONTRTDT > "+dbMasterBackup+".LNACCAT.LNSTRDT THEN "+dbMasterBackup+".LNACCAT.CONTRTDT ELSE "+dbMasterBackup+".LNACCAT.LNSTRDT END) AS TGL_MULAI, "
                    + ""+dbMasterBackup+".LNACCAT.LNDUEDT, "+dbMasterBackup+".LNACCAT.DCATBSL2, "+dbMasterBackup+".LNACCAT.USAGEID, "+dbMasterBackup+".LNACCAT.ORIENTID,  "+dbMasterBackup+".LNACCAT.SIDDT2ID, "
                    + ""+dbMasterBackup+".LNACCAT.PRJAMSID, "+dbMasterBackup+".LNACCAT.CCYID, "+dbMasterBackup+".LNACCAT.LNINTRST, "+dbMasterBackup+".LNACCAT.INTRTYPE, "+dbMasterBackup+".LNACCAT.SRCID, "+dbMasterBackup+".LNACCAT.PLAFOND, "
                    + "(CASE WHEN "+dbMasterBackup+".LNACCAT.ENDBAL < "+dbMasterBackup+".LNACCAT.BOMBAL THEN 0 ELSE "+dbMasterBackup+".LNACCAT.ENDBAL - "+dbMasterBackup+".LNACCAT.BOMBAL END) AS KUMULATIF_REALISASI, "
                    + ""+dbMasterBackup+".LNACCAT.PENALTY, "+dbMasterBackup+".LNACCAT.ENDBAL, "+dbMasterBackup+".LNACCAT.COLID, "
                    + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCAT.COLID)) = '5' THEN "+dbMasterBackup+".LNACCAT.COLDATE END AS TGL_MACET, "
                    + ""+dbMasterBackup+".LNACCAT.TUNGGPKK,  "+dbMasterBackup+".LNACCAT.TUNGGBNG, "+dbMasterBackup+".LNACCAT.BASETGDY, "+dbMasterBackup+".LNACCAT.COUNTBNG, "+dbMasterBackup+".LNACCAT.RESTDATE, "
                    + "CASE RTRIM(CHAR("+dbMasterBackup+".LNACCAT.ACCSTS)) WHEN '7' THEN '03' WHEN '9' THEN '02' END AS KONDISI, "
                    + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCAT.ACCSTS)) = '9' THEN "+dbMasterBackup+".LNACCAT.PAIDDATE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCAT.ACCSTS)) = '7' THEN "+dbMasterBackup+".LNACCAT.HPSBKDT END AS TGL_KONDISI, "
                    + ""+dbMasterBackup+".LNACCAT.BRANCHID "
                    + "FROM "+dbMasterBackup+".LNACCAT  "
                    
                     //update opie-eyek
                    + ","+dbMasterBackup+".SYSTEM_HOST H "
                    + "WHERE (("+dbMasterBackup+".LNACCAT.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCAT.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCAT.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCAT.PAIDDATE) = YEAR(H.OPEN_DATE)))"
                    + "UNION  "
                    + "SELECT "+dbMasterBackup+".LNACCATT.ACCNBR, "+dbMasterBackup+".LNACCATT.CIFMSTID, "+dbMasterBackup+".LNACCATT.LNCCHRID, "+dbMasterBackup+".LNACCATT.CHRTBSL2, "+dbMasterBackup+".LNACCATT.CONTRCNO, "+dbMasterBackup+".LNACCATT.CONTRTDT, "+dbMasterBackup+".LNACCATT.PKAKHIR, "+dbMasterBackup+".LNACCATT.PKAKHDT, "+dbMasterBackup+".LNACCATT.ADNDMSEQ,  "
                    + "(CASE WHEN "+dbMasterBackup+".LNACCATT.CONTRTDT > "+dbMasterBackup+".LNACCATT.ACCOPNDT THEN "+dbMasterBackup+".LNACCATT.CONTRTDT ELSE "+dbMasterBackup+".LNACCATT.ACCOPNDT END) AS TGL_AWAL_KREDIT, "
                    + "(CASE WHEN "+dbMasterBackup+".LNACCATT.CONTRTDT > "+dbMasterBackup+".LNACCATT.LNSTRDT THEN "+dbMasterBackup+".LNACCATT.CONTRTDT ELSE "+dbMasterBackup+".LNACCATT.LNSTRDT END) AS TGL_MULAI, "
                    + ""+dbMasterBackup+".LNACCATT.LNDUEDT, "+dbMasterBackup+".LNACCATT.DCATBSL2, "+dbMasterBackup+".LNACCATT.USAGEID, "+dbMasterBackup+".LNACCATT.ORIENTID,  "+dbMasterBackup+".LNACCATT.SIDDT2ID, "
                    + ""+dbMasterBackup+".LNACCATT.PRJAMSID, "+dbMasterBackup+".LNACCATT.CCYID, "+dbMasterBackup+".LNACCATT.LNINTRST, "+dbMasterBackup+".LNACCATT.INTRTYPE, "+dbMasterBackup+".LNACCATT.SRCID, "+dbMasterBackup+".LNACCATT.PLAFOND, "
                    + "CASE WHEN "+dbMasterBackup+".LNACCATT.ENDBAL < "+dbMasterBackup+".LNACCATT.BOMBAL THEN 0 ELSE "+dbMasterBackup+".LNACCATT.ENDBAL - "+dbMasterBackup+".LNACCATT.BOMBAL END AS KUMULATIF_REALISASI, "
                    + ""+dbMasterBackup+".LNACCATT.PENALTY, "+dbMasterBackup+".LNACCATT.ENDBAL, "+dbMasterBackup+".LNACCATT.COLID, "
                    + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCATT.COLID)) = '5' THEN "+dbMasterBackup+".LNACCATT.COLDATE END AS TGL_MACET, "
                    + ""+dbMasterBackup+".LNACCATT.TUNGGPKK,  "+dbMasterBackup+".LNACCATT.TUNGGBNG, "+dbMasterBackup+".LNACCATT.BASETGDY, "+dbMasterBackup+".LNACCATT.COUNTBNG, "+dbMasterBackup+".LNACCATT.RESTDATE, "
                    + "CASE RTRIM(CHAR("+dbMasterBackup+".LNACCATT.ACCSTS)) WHEN '7' THEN '03' WHEN '9' THEN '02' END AS KONDISI, "
                    + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCATT.ACCSTS)) = '9' THEN "+dbMasterBackup+".LNACCATT.PAIDDATE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCATT.ACCSTS)) = '7' THEN "+dbMasterBackup+".LNACCATT.HPSBKDT END AS TGL_KONDISI, "
                    + ""+dbMasterBackup+".LNACCATT.BRANCHID "
                    + "FROM "+dbMasterBackup+".LNACCATT  "
                    //update opie-eyek
                    + ","+dbMasterBackup+".SYSTEM_HOST H "
                    + "WHERE (("+dbMasterBackup+".LNACCATT.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCATT.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCATT.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCATT.PAIDDATE) = YEAR(H.OPEN_DATE)))"
                    + "UNION  "
                    + "SELECT "+dbMasterBackup+".LNACCTA.ACCNBR, "+dbMasterBackup+".LNACCTA.CIFMSTID, "+dbMasterBackup+".LNACCTA.LNCCHRID, "+dbMasterBackup+".LNACCTA.CHRTBSL2, "+dbMasterBackup+".LNACCTA.CONTRCNO,"+dbMasterBackup+".LNACCTA.CONTRTDT, "+dbMasterBackup+".LNACCTA.PKAKHIR, "+dbMasterBackup+".LNACCTA.PKAKHDT, "+dbMasterBackup+".LNACCTA.ADNDMSEQ,  "
                    + "(CASE WHEN "+dbMasterBackup+".LNACCTA.CONTRTDT > "+dbMasterBackup+".LNACCTA.ACCOPNDT THEN "+dbMasterBackup+".LNACCTA.CONTRTDT ELSE "+dbMasterBackup+".LNACCTA.ACCOPNDT END) AS TGL_AWAL_KREDIT, "
                    + "(CASE WHEN "+dbMasterBackup+".LNACCTA.CONTRTDT > "+dbMasterBackup+".LNACCTA.LNSTRDT THEN "+dbMasterBackup+".LNACCTA.CONTRTDT ELSE "+dbMasterBackup+".LNACCTA.LNSTRDT END) AS TGL_MULAI, "
                    + ""+dbMasterBackup+".LNACCTA.LNDUEDT, "+dbMasterBackup+".LNACCTA.DCATBSL2, "+dbMasterBackup+".LNACCTA.USAGEID, "+dbMasterBackup+".LNACCTA.ORIENTID, "+dbMasterBackup+".LNACCTA.SIDDT2ID, "
                    + ""+dbMasterBackup+".LNACCTA.PRJAMSID, "+dbMasterBackup+".LNACCTA.CCYID, "+dbMasterBackup+".LNACCTA.LNINTRST, "+dbMasterBackup+".LNACCTA.INTRTYPE, "+dbMasterBackup+".LNACCTA.SRCID, "+dbMasterBackup+".LNACCTA.PLAFOND, "
                    + "CASE WHEN "+dbMasterBackup+".LNACCTA.ENDBAL < "+dbMasterBackup+".LNACCTA.BOMBAL THEN 0 ELSE "+dbMasterBackup+".LNACCTA.ENDBAL - "+dbMasterBackup+".LNACCTA.BOMBAL END AS KUMULATIF_REALISASI, "
                    + ""+dbMasterBackup+".LNACCTA.PENALTY, "+dbMasterBackup+".LNACCTA.ENDBAL,"+dbMasterBackup+".LNACCTA.COLID, "
                    + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCTA.COLID)) = '5' THEN "+dbMasterBackup+".LNACCTA.COLDATE END AS TGL_MACET, "
                    + ""+dbMasterBackup+".LNACCTA.TUNGGPKK, "+dbMasterBackup+".LNACCTA.TUNGGBNG, "+dbMasterBackup+".LNACCTA.BASETGDY, "+dbMasterBackup+".LNACCTA.COUNTBNG,  "+dbMasterBackup+".LNACCTA.RESTDATE, "
                    + "CASE RTRIM(CHAR("+dbMasterBackup+".LNACCTA.ACCSTS)) WHEN '7' THEN '03' WHEN '9' THEN '02' END AS KONDISI, "
                    + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCTA.ACCSTS)) = '9' THEN "+dbMasterBackup+".LNACCTA.PAIDDATE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCTA.ACCSTS)) = '7' THEN "+dbMasterBackup+".LNACCTA.HPSBKDT END AS TGL_KONDISI, "
                    + ""+dbMasterBackup+".LNACCTA.BRNCHID AS BRANCHID "
                    + "FROM "+dbMasterBackup+".LNACCTA  "
                    //update opie-eyek
                    + ","+dbMasterBackup+".SYSTEM_HOST H "
                    + "WHERE (("+dbMasterBackup+".LNACCTA.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCTA.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCTA.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCTA.PAIDDATE) = YEAR(H.OPEN_DATE)))"
                    + ") AS TAB ) AS BAR  "
                    + "LEFT JOIN  "
                    + dbMasterBackup+".SID_KREDIT "
                    + "ON BAR.NO_REK = CONCAT("+dbMasterBackup+".SID_KREDIT.ID_KTR_CABANG,"+dbMasterBackup+".SID_KREDIT.NO_REKENING) ";

            if (!cif.equals("")) {
                sql = sql + " WHERE BAR.CIFMSTID='" + cif + "'";
            }
            //sql=sql+" FETCH FIRST 5 ROWS ONLY";

           // OutletConnection outletConnection = getConfigurasiConnection();
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int no = 0;
            while (rs.next()) {
                //sent=false;
                try {
                    count = rs.getInt("TOTAL");
                } catch (Exception es) {
                    System.out.print("Eror loh urutan " + no);
                }
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }

        return count;
    }

    public int countTransferDataBankGaransi(String cif, long periodeId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //createConn(); 
             OutletConnection outletConnection = getConfigurasiConnection();
             String dbMasterBackup="";
             dbMasterBackup = outletConnection.getDbDatabaseBackup();
            String sql = " SELECT COUNT(DISTINCT "
                    + "CONCAT("+dbMasterBackup+".BGARANSI_MASTER.KD_CAB, "+dbMasterBackup+".BGARANSI_MASTER.NO_REK)) AS TOTAL "
//                    + "FROM DBLMON.BGARANSI_MASTER "
//                    + ","+dbMasterBackup+".SYSTEM_HOST D "
//                    + " WHERE (("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR > D.OPEN_DATE) OR (MONTH("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR)=MONTH(D.OPEN_DATE) AND YEAR("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR)=YEAR(D.OPEN_DATE))) AND "+dbMasterBackup+".BGARANSI_MASTER.KD_STATUS <> '9'"
//                    + "";
                      + "FROM "+dbMasterBackup+".BGARANSI_MASTER, " +
                        " "+dbMasterBackup+".TBL_VALUTA, " +
                        " "+dbMasterBackup+".TBL_JENIS_BG " +
                        "WHERE ("+dbMasterBackup+".BGARANSI_MASTER.KD_VALUTA = "+dbMasterBackup+".TBL_VALUTA.KD_VALUTA) " +
                        "AND ("+dbMasterBackup+".BGARANSI_MASTER.KD_JENIS = "+dbMasterBackup+".TBL_JENIS_BG.KD_JENIS) " +
                        "AND ( " +
                        "( " +
                        "MONTH("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR) = (SELECT " +
                        "  MONTH(OPEN_DATE) " +
                        "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                        "AND YEAR("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR) = (SELECT " +
                        "  YEAR(OPEN_DATE) " +
                        "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                        ") " +
                        "OR ( " +
                        "("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR > (SELECT " +
                        "  OPEN_DATE " +
                        "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                        ") " +
                        "AND ("+dbMasterBackup+".BGARANSI_MASTER.KD_STATUS NOT IN ('9')) " +
                        ") " +
                        ") ";

            if (!cif.equals("")) {
                sql = sql + " WHERE NO_NSB='" + cif + "'";
            }
            // sql=sql+" FETCH FIRST 5 ROWS ONLY";

           
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int no = 0;
            while (rs.next()) {
                //sent=false;
                try {
                    count = rs.getInt("TOTAL");
                } catch (Exception es) {
                    System.out.print("Eror loh urutan " + no);
                }
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }

        return count;
    }

    public int countTransferDataAgunan(String cif, long periodeId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //createConn(); 
            OutletConnection outletConnection = getConfigurasiConnection();
            String dbMasterBackup="";
            dbMasterBackup = outletConnection.getDbDatabaseBackup();
            String sql = ""+
                        "SELECT COUNT(TAB.AGUNANID) AS TOTAL FROM ( " +
                        "SELECT DISTINCT C.AGUNANID, A.CIFMSTID, A.ACCNBR,  C.BRANCHID, C.JNSAGNID, C.IKATANID,  C.OWNER, C.CERTIFCT, C.OWNRADDR, C.LOCDT2, C.NJOPVAL, " +
                        "C.BANKVAL, C.APRDATE, C.PARIPASU, C.BRANCHID,  C.JNSAGLBU, C.RATORG, C.APRDATE " +
                        "FROM "+dbMasterBackup+".LNACCTA A, "+dbMasterBackup+".DTLAGNAN B, "+dbMasterBackup+".AGUNAN C " +
                        "WHERE ( A.BRNCHID = B.BRANCHID ) AND   ( A.ACCNBR  = B.ACCNBR ) AND  ( B.BRANCHID = C.BRANCHID ) AND   ( B.AGUNANID  = C.AGUNANID ) " +
                        "AND ( A.ACCSTS NOT IN ('0','7','9'))" +
                        "UNION " +
                        "SELECT DISTINCT C.AGUNANID, A.CIFMSTID, A.ACCNBR,  C.BRANCHID, C.JNSAGNID, C.IKATANID,  C.OWNER, C.CERTIFCT, " +
                        "C.OWNRADDR, C.LOCDT2, C.NJOPVAL,  C.BANKVAL, C.APRDATE, C.PARIPASU, C.BRANCHID,  C.JNSAGLBU, C.RATORG, C.APRDATE " +
                        "FROM "+dbMasterBackup+".LNACCATT A, "+dbMasterBackup+".DTLAGNAN B, "+dbMasterBackup+".AGUNAN C WHERE ( A.BRANCHID = B.BRANCHID ) AND  ( A.ACCNBR  = B.ACCNBR ) AND  ( B.BRANCHID = C.BRANCHID )" +
                        "AND   ( B.AGUNANID  = C.AGUNANID ) AND ( A.ACCSTS NOT IN ('0','7','9')) " +
                        "UNION " +
                        "SELECT DISTINCT C.AGUNANID, A.CIFMSTID, A.ACCNBR,  C.BRANCHID, C.JNSAGNID, C.IKATANID,  C.OWNER, C.CERTIFCT, C.OWNRADDR, C.LOCDT2, C.NJOPVAL,  " +
                        "C.BANKVAL, C.APRDATE, C.PARIPASU, C.BRANCHID,   C.JNSAGLBU, C.RATORG, C.APRDATE FROM "+dbMasterBackup+".LNACCAT A, "+dbMasterBackup+".DTLAGNAN B, "+dbMasterBackup+".AGUNAN C " +
                        "WHERE ( A.BRANCHID = B.BRANCHID ) AND   ( A.ACCNBR  = B.ACCNBR ) AND  ( B.BRANCHID = C.BRANCHID ) AND   ( B.AGUNANID  = C.AGUNANID ) " +
                        "AND  ( A.ACCSTS NOT IN ('0','7','9')) " +
                        " ) AS TAB";
            if (!cif.equals("")) {
                sql = sql + " WHERE CIFMSTID='" + cif + "'";
            }
            // sql = sql + "FETCH FIRST 5 ROWS ONLY";

            
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int no = 0;
            while (rs.next()) {
                //sent=false;
                try {
                    count = rs.getInt("TOTAL");
                } catch (Exception es) {
                    System.out.print("Eror loh urutan " + no);
                }
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }

        return count;
    }

    public int countTransferDataPengurusAtauPemilik(String cif, long periodeId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //createConn(); 
            OutletConnection outletConnection = getConfigurasiConnection();
            String dbMasterBackup="";
            dbMasterBackup = outletConnection.getDbDatabaseBackup();
            String sql = ""
                    + "SELECT COUNT(DISTINCT "
                    + ""+dbMasterBackup+".PENGURUS.NO_NSB) AS TOTAL "
                    + "FROM "+dbMasterBackup+".PENGURUS "
                    + "INNER JOIN "+dbMasterBackup+".NASABAH "
                    + "ON "+dbMasterBackup+".PENGURUS.NO_NSB = "+dbMasterBackup+".NASABAH.NO_NSB ";

            if (!cif.equals("")) {
                sql = sql + " WHERE NO_NSB='" + cif + "'";
            }
            //sql = sql + "FETCH FIRST 5 ROWS ONLY";
            
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int no = 0;
            while (rs.next()) {
                //sent=false;
                try {
                    count = rs.getInt("TOTAL");
                } catch (Exception es) {
                    System.out.print("Eror loh urutan " + no);
                }
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }

        return count;
    }

    public String actionTransferDataKredit() {
        return actionTransferDataKredit("", 0, 0,0);
    }

    public String actionTransferDataKredit(String cif, long periodeId, int counter, int typeGetData) {
        String action = "";
        if(ManagerTransferDataKredit.running==false && typeGetData==0){
            return action;
        }
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        if (periodeId != 0) {
            try {
                //createConn(); 
                OutletConnection outletConnection = getConfigurasiConnection();
                String dbMasterBackup="";
                if(typeGetData==1){
                    dbMasterBackup = outletConnection.getDbDatabase();
                }else{
                    dbMasterBackup = outletConnection.getDbDatabaseBackup();
                }
                /*carikan querynya*/
                //masalah di sid_kreditnya, kalau di inner join gak ketemu dia? carikan masalahnya
                String sql = ""
                        + "SELECT DISTINCT "
                        + "CONCAT(BRANCHID, ACCNBR) AS NO_REKENING, "
                        + "BAR.CIFMSTID, "
                        + "BAR.LNCCHRID, "
                        + "BAR.CHRTBSL2, "
                        + "BAR.CONTRCNO, "
                        + "BAR.CONTRTDT, "
                        + "BAR.PKAKHIR, "
                        + "BAR.PKAKHDT, "
                        + "BAR.ADNDMSEQ, "
                        + "BAR.TGL_AWAL_KREDIT, "
                        + "BAR.TGL_MULAI, "
                        + "BAR.LNDUEDT, "
                        + "BAR.DCATBSL2, "
                        + "BAR.USAGEID, "
                        + "BAR.ORIENTID, "
                        + "BAR.SIDDT2ID, "
                        + "BAR.PRJAMSID, "
                        + "BAR.CCYID, "
                        + "BAR.LNINTRST, "
                        + "BAR.INTRTYPE, "
                        + "BAR.SRCID, "
                        + "BAR.PLAFOND, "
                        + "BAR.KUMULATIF_REALISASI, "
                        + "BAR.PENALTY, "
                        + "BAR.ENDBAL, "
                        + "BAR.COLID, "
                        + "BAR.TGL_MACET, "
                        + "BAR.TUNGGPKK, "
                        + "BAR.TUNGGBNG, "
                        + "BAR.INTRTGDY, "
                        + "BAR.BASETGDY, "
                        + "BAR.COUNTBNG, "
                        + ""+dbMasterBackup+".SID_KREDIT.RESTRUK_KE,  "
                        + ""+dbMasterBackup+".SID_KREDIT.RESTRUK_AWAL, "
                        + "BAR.RESTDATE, "
                        + "BAR.KONDISI, "
                        + "BAR.TGL_KONDISI, "
                        + "BAR.BRANCHID,"
                        + "BAR.APPLID, "
                        + "BAR.PRODID, "
                        + "BAR.ECOSECID,"
                        + "BAR.ECOSESCSID, "
                        + ""+dbMasterBackup+".SID_KREDIT.SEBAB_MACET, "
                        + "BAR.LOK2ID "
                        + "FROM "
//                        +dbMasterBackup+".SID_KREDIT "
//                        + "RIGHT JOIN  "
                        + "(  "
                        + "SELECT CIFMSTID , ACCNBR, CONCAT(BRANCHID, ACCNBR) AS NO_REK, LNCCHRID, CHRTBSL2,CONTRCNO, CONTRTDT, PKAKHIR, PKAKHDT, ADNDMSEQ,  "
                        + "TGL_AWAL_KREDIT,TGL_MULAI,LNDUEDT,DCATBSL2,USAGEID, ORIENTID, SIDDT2ID, PRJAMSID, CCYID, LNINTRST,INTRTYPE,SRCID,PLAFOND, KUMULATIF_REALISASI, "
                        + "PENALTY,ENDBAL,COLID,TGL_MACET,TUNGGPKK, TUNGGBNG, BASETGDY, INTRTGDY, COUNTBNG, RESTDATE, KONDISI, TGL_KONDISI, BRANCHID, APPLID,PRODID, ECOSECID, ECOSESCSID, LOK2ID "
                        + "FROM (  "
                        + "SELECT "+dbMasterBackup+".LNACCAT.ACCNBR, "+dbMasterBackup+".LNACCAT.CIFMSTID, "+dbMasterBackup+".LNACCAT.LNCCHRID, "+dbMasterBackup+".LNACCAT.CHRTBSL2,  "+dbMasterBackup+".LNACCAT.CONTRCNO, "+dbMasterBackup+".LNACCAT.CONTRTDT, "+dbMasterBackup+".LNACCAT.PKAKHIR, "+dbMasterBackup+".LNACCAT.PKAKHDT, "+dbMasterBackup+".LNACCAT.ADNDMSEQ,  "
                        + "(CASE WHEN "+dbMasterBackup+".LNACCAT.CONTRTDT > "+dbMasterBackup+".LNACCAT.ACCOPNDT THEN "+dbMasterBackup+".LNACCAT.CONTRTDT ELSE "+dbMasterBackup+".LNACCAT.ACCOPNDT END) AS TGL_AWAL_KREDIT, "
                        + "(CASE WHEN "+dbMasterBackup+".LNACCAT.CONTRTDT > "+dbMasterBackup+".LNACCAT.LNSTRDT THEN "+dbMasterBackup+".LNACCAT.CONTRTDT ELSE "+dbMasterBackup+".LNACCAT.LNSTRDT END) AS TGL_MULAI, "
                        + ""+dbMasterBackup+".LNACCAT.LNDUEDT, "+dbMasterBackup+".LNACCAT.DCATBSL2, "+dbMasterBackup+".LNACCAT.USAGEID, "+dbMasterBackup+".LNACCAT.ORIENTID,  "+dbMasterBackup+".LNACCAT.SIDDT2ID, "
                        + ""+dbMasterBackup+".LNACCAT.PRJAMSID, "+dbMasterBackup+".LNACCAT.CCYID, "+dbMasterBackup+".LNACCAT.LNINTRST, "+dbMasterBackup+".LNACCAT.INTRTYPE, "+dbMasterBackup+".LNACCAT.SRCID, "+dbMasterBackup+".LNACCAT.PLAFOND, "
                        + "(CASE WHEN "+dbMasterBackup+".LNACCAT.ENDBAL < "+dbMasterBackup+".LNACCAT.BOMBAL THEN 0 ELSE "+dbMasterBackup+".LNACCAT.ENDBAL - "+dbMasterBackup+".LNACCAT.BOMBAL END) AS KUMULATIF_REALISASI, "
                        + ""+dbMasterBackup+".LNACCAT.PENALTY, "+dbMasterBackup+".LNACCAT.ENDBAL, "+dbMasterBackup+".LNACCAT.COLID, "
                        + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCAT.COLID)) = '5' THEN "+dbMasterBackup+".LNACCAT.COLDATE END AS TGL_MACET, "
                        + ""+dbMasterBackup+".LNACCAT.TUNGGPKK,  "+dbMasterBackup+".LNACCAT.TUNGGBNG, "+dbMasterBackup+".LNACCAT.BASETGDY, "+dbMasterBackup+".LNACCAT.INTRTGDY, "+dbMasterBackup+".LNACCAT.COUNTBNG, "+dbMasterBackup+".LNACCAT.RESTDATE, "
                        + "CASE RTRIM(CHAR("+dbMasterBackup+".LNACCAT.ACCSTS)) WHEN '7' THEN '03' WHEN '9' THEN '02' END AS KONDISI, "
                        + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCAT.ACCSTS)) = '9' THEN "+dbMasterBackup+".LNACCAT.PAIDDATE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCAT.ACCSTS)) = '7' THEN "+dbMasterBackup+".LNACCAT.HPSBKDT END AS TGL_KONDISI, "
                        + ""+dbMasterBackup+".LNACCAT.BRANCHID, "
                        + ""+dbMasterBackup+".LNACCAT.APPLID, "+dbMasterBackup+".LNACCAT.PRODID , "+dbMasterBackup+".LNACCAT.ECOSECID, '' AS ECOSESCSID, "+dbMasterBackup+".LNACCAT.LOK2ID "
                        + "FROM "+dbMasterBackup+".LNACCAT "
                        //update opie-eyek
                        + ","+dbMasterBackup+".SYSTEM_HOST H ";
                        if(typeGetData==0){
                            sql=sql+ " WHERE (("+dbMasterBackup+".LNACCAT.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCAT.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCAT.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCAT.PAIDDATE) = YEAR(H.OPEN_DATE)))";
                        }
                        sql=sql+ " UNION  "
                        + "SELECT "+dbMasterBackup+".LNACCATT.ACCNBR, "+dbMasterBackup+".LNACCATT.CIFMSTID, "+dbMasterBackup+".LNACCATT.LNCCHRID, "+dbMasterBackup+".LNACCATT.CHRTBSL2, "+dbMasterBackup+".LNACCATT.CONTRCNO, "+dbMasterBackup+".LNACCATT.CONTRTDT, "+dbMasterBackup+".LNACCATT.PKAKHIR, "+dbMasterBackup+".LNACCATT.PKAKHDT, "+dbMasterBackup+".LNACCATT.ADNDMSEQ,  "
                        + "(CASE WHEN "+dbMasterBackup+".LNACCATT.CONTRTDT > "+dbMasterBackup+".LNACCATT.ACCOPNDT THEN "+dbMasterBackup+".LNACCATT.CONTRTDT ELSE "+dbMasterBackup+".LNACCATT.ACCOPNDT END) AS TGL_AWAL_KREDIT, "
                        + "(CASE WHEN "+dbMasterBackup+".LNACCATT.CONTRTDT > "+dbMasterBackup+".LNACCATT.LNSTRDT THEN "+dbMasterBackup+".LNACCATT.CONTRTDT ELSE "+dbMasterBackup+".LNACCATT.LNSTRDT END) AS TGL_MULAI, "
                        + ""+dbMasterBackup+".LNACCATT.LNDUEDT, "+dbMasterBackup+".LNACCATT.DCATBSL2, "+dbMasterBackup+".LNACCATT.USAGEID, "+dbMasterBackup+".LNACCATT.ORIENTID,  "+dbMasterBackup+".LNACCATT.SIDDT2ID, "
                        + ""+dbMasterBackup+".LNACCATT.PRJAMSID, "+dbMasterBackup+".LNACCATT.CCYID, "+dbMasterBackup+".LNACCATT.LNINTRST, "+dbMasterBackup+".LNACCATT.INTRTYPE, "+dbMasterBackup+".LNACCATT.SRCID, "+dbMasterBackup+".LNACCATT.PLAFOND, "
                        + "CASE WHEN "+dbMasterBackup+".LNACCATT.ENDBAL < "+dbMasterBackup+".LNACCATT.BOMBAL THEN 0 ELSE "+dbMasterBackup+".LNACCATT.ENDBAL - "+dbMasterBackup+".LNACCATT.BOMBAL END AS KUMULATIF_REALISASI, "
                        + ""+dbMasterBackup+".LNACCATT.PENALTY, "+dbMasterBackup+".LNACCATT.ENDBAL, "+dbMasterBackup+".LNACCATT.COLID, "
                        + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCATT.COLID)) = '5' THEN "+dbMasterBackup+".LNACCATT.COLDATE END AS TGL_MACET, "
                        + ""+dbMasterBackup+".LNACCATT.TUNGGPKK,  "+dbMasterBackup+".LNACCATT.TUNGGBNG, "+dbMasterBackup+".LNACCATT.BASETGDY, "+dbMasterBackup+".LNACCATT.INTRTGDY, "+dbMasterBackup+".LNACCATT.COUNTBNG, "+dbMasterBackup+".LNACCATT.RESTDATE, "
                        + "CASE RTRIM(CHAR("+dbMasterBackup+".LNACCATT.ACCSTS)) WHEN '7' THEN '03' WHEN '9' THEN '02' END AS KONDISI, "
                        + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCATT.ACCSTS)) = '9' THEN "+dbMasterBackup+".LNACCATT.PAIDDATE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCATT.ACCSTS)) = '7' THEN "+dbMasterBackup+".LNACCATT.HPSBKDT END AS TGL_KONDISI, "
                        + ""+dbMasterBackup+".LNACCATT.BRANCHID, "
                        + ""+dbMasterBackup+".LNACCATT.APPLID, "+dbMasterBackup+".LNACCATT.PRODID , "+dbMasterBackup+".LNACCATT.ECOSECID, '' AS ECOSESCSID, "+dbMasterBackup+".LNACCATT.LOK2ID  "
                        + "FROM "+dbMasterBackup+".LNACCATT  "
                        //update opie-eyek
                        + ","+dbMasterBackup+".SYSTEM_HOST H ";
                        if(typeGetData==0){
                            sql=sql+ "WHERE (("+dbMasterBackup+".LNACCATT.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCATT.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCATT.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCATT.PAIDDATE) = YEAR(H.OPEN_DATE)))";
                        }
                        sql=sql+ " UNION  "
                        + "SELECT "+dbMasterBackup+".LNACCTA.ACCNBR, "+dbMasterBackup+".LNACCTA.CIFMSTID, "+dbMasterBackup+".LNACCTA.LNCCHRID, "+dbMasterBackup+".LNACCTA.CHRTBSL2, "+dbMasterBackup+".LNACCTA.CONTRCNO,"+dbMasterBackup+".LNACCTA.CONTRTDT, "+dbMasterBackup+".LNACCTA.PKAKHIR, "+dbMasterBackup+".LNACCTA.PKAKHDT, "+dbMasterBackup+".LNACCTA.ADNDMSEQ,  "
                        + "(CASE WHEN "+dbMasterBackup+".LNACCTA.CONTRTDT > "+dbMasterBackup+".LNACCTA.ACCOPNDT THEN "+dbMasterBackup+".LNACCTA.CONTRTDT ELSE "+dbMasterBackup+".LNACCTA.ACCOPNDT END) AS TGL_AWAL_KREDIT, "
                        + "(CASE WHEN "+dbMasterBackup+".LNACCTA.CONTRTDT > "+dbMasterBackup+".LNACCTA.LNSTRDT THEN "+dbMasterBackup+".LNACCTA.CONTRTDT ELSE "+dbMasterBackup+".LNACCTA.LNSTRDT END) AS TGL_MULAI, "
                        + ""+dbMasterBackup+".LNACCTA.LNDUEDT, "+dbMasterBackup+".LNACCTA.DCATBSL2, "+dbMasterBackup+".LNACCTA.USAGEID, "+dbMasterBackup+".LNACCTA.ORIENTID, "+dbMasterBackup+".LNACCTA.SIDDT2ID, "
                        + ""+dbMasterBackup+".LNACCTA.PRJAMSID, "+dbMasterBackup+".LNACCTA.CCYID, "+dbMasterBackup+".LNACCTA.LNINTRST, "+dbMasterBackup+".LNACCTA.INTRTYPE, "+dbMasterBackup+".LNACCTA.SRCID, "+dbMasterBackup+".LNACCTA.PLAFOND, "
                        + "CASE WHEN "+dbMasterBackup+".LNACCTA.ENDBAL < "+dbMasterBackup+".LNACCTA.BOMBAL THEN 0 ELSE "+dbMasterBackup+".LNACCTA.ENDBAL - "+dbMasterBackup+".LNACCTA.BOMBAL END AS KUMULATIF_REALISASI, "
                        + ""+dbMasterBackup+".LNACCTA.PENALTY, "+dbMasterBackup+".LNACCTA.ENDBAL,"+dbMasterBackup+".LNACCTA.COLID, "
                        + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCTA.COLID)) = '5' THEN "+dbMasterBackup+".LNACCTA.COLDATE END AS TGL_MACET, "
                        + ""+dbMasterBackup+".LNACCTA.TUNGGPKK, "+dbMasterBackup+".LNACCTA.TUNGGBNG, "+dbMasterBackup+".LNACCTA.BASETGDY, "+dbMasterBackup+".LNACCTA.INTRTGDY, "+dbMasterBackup+".LNACCTA.COUNTBNG,  "+dbMasterBackup+".LNACCTA.RESTDATE, "
                        + "CASE RTRIM(CHAR("+dbMasterBackup+".LNACCTA.ACCSTS)) WHEN '7' THEN '03' WHEN '9' THEN '02' END AS KONDISI, "
                        + "CASE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCTA.ACCSTS)) = '9' THEN "+dbMasterBackup+".LNACCTA.PAIDDATE WHEN RTRIM(CHAR("+dbMasterBackup+".LNACCTA.ACCSTS)) = '7' THEN "+dbMasterBackup+".LNACCTA.HPSBKDT END AS TGL_KONDISI, "
                        + ""+dbMasterBackup+".LNACCTA.BRNCHID AS BRANCHID, "
                        + ""+dbMasterBackup+".LNACCTA.APPLID, "+dbMasterBackup+".LNACCTA.PRODID , "+dbMasterBackup+".LNACCTA.ECOSECID, "+dbMasterBackup+".LNACCTA.ECOSESCSID, "+dbMasterBackup+".LNACCTA.LOK2ID "
                        + "FROM "+dbMasterBackup+".LNACCTA  "
                        //update opie-eyek
                        + ","+dbMasterBackup+".SYSTEM_HOST H ";
                        if(typeGetData==0){
                            sql=sql+ "WHERE (("+dbMasterBackup+".LNACCTA.ACCSTS NOT IN (0,9) ) OR ("+dbMasterBackup+".LNACCTA.ACCSTS = 9 AND MONTH("+dbMasterBackup+".LNACCTA.PAIDDATE) = MONTH(H.OPEN_DATE) AND YEAR("+dbMasterBackup+".LNACCTA.PAIDDATE) = YEAR(H.OPEN_DATE)))";
                        }
                        sql=sql+ ") AS TAB ) AS BAR  "
                        + "LEFT JOIN  "
                        + dbMasterBackup+".SID_KREDIT "
                        + "ON BAR.NO_REK = CONCAT("+dbMasterBackup+".SID_KREDIT.ID_KTR_CABANG,"+dbMasterBackup+".SID_KREDIT.NO_REKENING) ";

                if (!cif.equals("")) {
                    sql = sql + " WHERE CONCAT("+dbMasterBackup+".SID_KREDIT.ID_KTR_CABANG,"+dbMasterBackup+".SID_KREDIT.NO_REKENING)='" + cif + "'";
                }
                
                Class.forName(outletConnection.getDbdriver()).newInstance();
                conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                int no = 0;
                int noInsert=0;
                int noUpdate=0;
                int data=0;
                while (rs.next()) {
                    //sent=false;
                    data=1;
                    try {
                        
                        if(ManagerTransferDataKredit.running==false && typeGetData==0){
                            return action;
                        }
                        
                        MappingKreditBpd entMappingKreditBpd = new MappingKreditBpd();
                        MappingKreditBpd currEntMappingKreditBpd = new MappingKreditBpd();
                        MappingKreditBpd prevEntMappingKreditBpd = new MappingKreditBpd();
                        String entRekening = rs.getString("NO_REKENING");
                        entMappingKreditBpd.setNoRekening(entRekening.replace("\\s", ""));
                        SessValidasiInputan.statusProsesValidasiKredit="";
                        if (true) {
                            String entCif = rs.getString("CIFMSTID"); //entMappingKreditBpd.getCif().replaceAll("\\s","");
                            entMappingKreditBpd.setCif(entCif.replace("\\s", ""));
                            
                            
                            if(0==0){
                                requestPrevEntityObjectKredit(entMappingKreditBpd, periodeId, entRekening, prevEntMappingKreditBpd);
                            }
                            
                            entMappingKreditBpd.setKodeSifat(SessGetContentDataOjk.getContentDataKodeSifatKredit(rs.getString("LNCCHRID")));
                            
                            if(entMappingKreditBpd.getKodeSifat()==null || entMappingKreditBpd.getKodeSifat().equals("")){
                                SessValidasiInputan.statusProsesValidasiKredit= SessValidasiInputan.statusProsesValidasiKredit+"<br> Kode Sifat Kosong, Wajib di perbaiki di core";
                            }
                            
                            entMappingKreditBpd.setKodeJenisKredit(SessGetContentDataOjk.getContentDataKodeJenisKredit(rs.getString("CHRTBSL2"),entMappingKreditBpd.getNoRekening()));
                            if(entMappingKreditBpd.getKodeJenisKredit()==null || entMappingKreditBpd.getKodeJenisKredit().equals("")){
                                SessValidasiInputan.statusProsesValidasiKredit= SessValidasiInputan.statusProsesValidasiKredit+"<br> Kode Jenis Kredit Kosong, Wajib di perbaiki di core";
                            }
                            entMappingKreditBpd.setKodeSkimAkadPembiayaan("99");
                            entMappingKreditBpd.setNoAkadAwal(rs.getString("CONTRCNO"));
                            entMappingKreditBpd.setTglAkadAwal(rs.getDate("CONTRTDT"));
                            entMappingKreditBpd.setFlagDetail("D");
                            entMappingKreditBpd.setNoAkadAkhir(rs.getString("PKAKHIR"));
                            entMappingKreditBpd.setTglAkadAkhir(rs.getDate("PKAKHDT"));
                            
                            entMappingKreditBpd.setBaruPerpanjangan(rs.getInt("ADNDMSEQ"));
                            entMappingKreditBpd.setTglAwal(rs.getDate("TGL_AWAL_KREDIT"));
                            entMappingKreditBpd.setTglMulai(rs.getDate("TGL_MULAI"));
                            entMappingKreditBpd.setTglTempo(rs.getDate("LNDUEDT"));

                            entMappingKreditBpd.setKodeJenisPenggunaan(SessGetContentDataOjk.getContentDataJenisPenggunaan(rs.getString("USAGEID")));
                            
                            entMappingKreditBpd.setKodeOrientasiPenggunaan(SessGetContentDataOjk.getContentDataOrientasiPenggunaan(rs.getString("ORIENTID")));
                            
                            entMappingKreditBpd.setKodeKab(SessGetContentDataOjk.getContentDataKabKota(rs.getString("SIDDT2ID")));
                            if(entMappingKreditBpd.getKodeKab()==null || entMappingKreditBpd.getKodeKab().equals("")){
                                entMappingKreditBpd.setKodeKab(SessGetContentDataOjk.getContentDataKabKota(rs.getString("LOK2ID"))); 
                                if(entMappingKreditBpd.getKodeKab()==null || entMappingKreditBpd.getKodeKab().equals("")){
                                    SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>Kode Kab/Kota Kosong, Wajib di perbaiki di core";
                                }
                            }
                            entMappingKreditBpd.setKodeValuta(SessGetContentDataOjk.getContentDataKodeValuta(rs.getString("CCYID")));
                            if(entMappingKreditBpd.getKodeValuta()==null || entMappingKreditBpd.getKodeValuta().equals("")){
                                SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>Kode Valuta Kosong, Wajib di perbaiki di core";
                            }
                            
                            entMappingKreditBpd.setProsentaseBunga(rs.getInt("LNINTRST"));
                            entMappingKreditBpd.setJenisBunga(SessGetContentDataOjk.getContentSukuBunga(rs.getString("INTRTYPE")));
                            
                            entMappingKreditBpd.setPlafonAwal(rs.getDouble("PLAFOND"));
                            entMappingKreditBpd.setPlafon(rs.getDouble("PLAFOND"));
                            entMappingKreditBpd.setNilai(rs.getDouble("PRJAMSID"));
                            try{
                                if(entMappingKreditBpd.getNilai() <= 0){
                                    entMappingKreditBpd.setNilai(entMappingKreditBpd.getPlafonAwal());//jika nilai proyek 0, setting nilainya sama dengan plafond
                                }
                            }catch(Exception ex){
                                entMappingKreditBpd.setNilai(entMappingKreditBpd.getPlafonAwal());
                            }
                            
                            
                            entMappingKreditBpd.setRealisasi(rs.getDouble("KUMULATIF_REALISASI"));
                            entMappingKreditBpd.setDenda(rs.getDouble("PENALTY"));
                            entMappingKreditBpd.setBakiDebet(rs.getDouble("ENDBAL"));
                            if(entMappingKreditBpd.getBakiDebet()<0){
                                entMappingKreditBpd.setBakiDebet(0);
                            }
                            
                            entMappingKreditBpd.setKodeKolektibilitas(SessGetContentDataOjk.getContentDataKodeKolektibilitas(rs.getString("COLID")));
                            entMappingKreditBpd.setTglMacet(rs.getDate("TGL_MACET"));
                            entMappingKreditBpd.setTunggakanPokok(rs.getDouble("TUNGGPKK"));
                            
                            entMappingKreditBpd.setKodeKondisi(SessGetContentDataOjk.getContentDataKodeKondisi(rs.getString("KONDISI")));
                            if(entMappingKreditBpd.getKodeKondisi()==null){
                                 entMappingKreditBpd.setKodeKondisi("00");//aktive
                            }
                            entMappingKreditBpd.setTglKondisi(rs.getDate("TGL_KONDISI"));
                            entMappingKreditBpd.setKodeKantorCabang(rs.getString("BRANCHID"));

                            entMappingKreditBpd.setKodeKategoriDebitur(SessGetContentDataOjk.getContentDataKategoriDeb(rs.getString("DCATBSL2")));
                            
                            entMappingKreditBpd.setSumberDana(SessGetContentDataOjk.getContentDataKodeSumberDanaKredit(rs.getString("SRCID")));
                            
                            entMappingKreditBpd.setTunggakanBunga(rs.getDouble("TUNGGBNG"));
                            
                            double jmlHariTunggakan=0;
                            try{
                                if(rs.getDouble("BASETGDY") > rs.getDouble("INTRTGDY")){
                                    jmlHariTunggakan = rs.getDouble("BASETGDY");
                                }else{
                                    jmlHariTunggakan = rs.getDouble("INTRTGDY");
                                }
                            }catch(Exception ex){
                            }
                           
                            entMappingKreditBpd.setJumlahHariTunggakan(jmlHariTunggakan);
                            if(entMappingKreditBpd.getJumlahHariTunggakan()<0){
                                SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"Jumlah hari tunggakan minus, Wajib di perbaiki di core";
                            }
                            entMappingKreditBpd.setFrekuensiTunggakan(rs.getDouble("COUNTBNG"));
                            entMappingKreditBpd.setFrekuensiRestrukturisasi(rs.getDouble("RESTRUK_KE"));
                            entMappingKreditBpd.setTanggalRestrukturisasiAwal(rs.getDate("RESTRUK_AWAL"));
                            entMappingKreditBpd.setTanggalRestrukturisasiAkhir(rs.getDate("RESTDATE"));
                            entMappingKreditBpd.setPeriodeId(periodeId);
                            entMappingKreditBpd.setApplid(rs.getString("APPLID"));
                            
                            entMappingKreditBpd.setKodeKreditProgramPemerintah(SessGetContentDataOjk.getContentDataKodeProgramPemerintah(rs.getString("PRODID")));
                            
                            entMappingKreditBpd.setKodeSektorEkonomi(SessGetContentDataOjk.getContentDataSektorEkonomi(rs.getString("ECOSECID")));
                            if(entMappingKreditBpd.getKodeSektorEkonomi()==null || entMappingKreditBpd.getKodeSektorEkonomi().equals("")){
                                entMappingKreditBpd.setKodeSektorEkonomi(SessGetContentDataOjk.getContentDataSektorEkonomi(rs.getString("ECOSESCSID")));
                                if(entMappingKreditBpd.getKodeSektorEkonomi()==null || entMappingKreditBpd.getKodeSektorEkonomi().equals("")){
                                    SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>Kode Sektor Ekonomi Kosong, Wajib di perbaiki di core";
                                }
                            }
                            String kodeSebabMacet=rs.getString("SEBAB_MACET");
                            if(kodeSebabMacet==null){
                                //ambil data sebelumnya
                                kodeSebabMacet=entMappingKreditBpd.getKodeSebabMacet();
                            }else{
                                if(kodeSebabMacet.equals("")){
                                    kodeSebabMacet=entMappingKreditBpd.getKodeSebabMacet();
                                }
                            }
                            
                            entMappingKreditBpd.setKodeSebabMacet(kodeSebabMacet);
                            if(entMappingKreditBpd.getKodeKolektibilitas().equals("5")){
                                if(entMappingKreditBpd.getKodeSebabMacet()==null ||  entMappingKreditBpd.getKodeSebabMacet().equals("")){
                                    SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>Kode Kolektibilitas Macet, Kode Sebab Macet, Wajib di perbaiki di aplikasi SLIK";
                                }
                            }
                            
                            if(entMappingKreditBpd.getJumlahHariTunggakan()==0){
                                if(entMappingKreditBpd.getTunggakanPokok()>0 || entMappingKreditBpd.getTunggakanBunga()>0){
                                    SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>Jumlah hari tunggakan Wajib > 0, karena Tunggakan Pokok > 0 atau Tunggakan Bunga >0, Wajib di perbaiki di aplikasi SLIK";
                                }
                            }
                            
                            try{
                                if(entMappingKreditBpd.getTunggakanPokok()==0 && entMappingKreditBpd.getTunggakanBunga()==0){
                                    if(entMappingKreditBpd.getJumlahHariTunggakan()!=0){
                                        SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>jumlah hari tunggakan harus=0, karena tunggakan pokok=0 dan tunggakan bunga=0, Wajib di perbaiki di aplikasi SLIK";
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            try{
                                if(entMappingKreditBpd.getKodeSifat().equals("1")){
                                    if(entMappingKreditBpd.getFrekuensiTunggakan()==0){
                                        SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>Frekuensi restrukturisasi harus > 0, Sifat Kredit Adalah Kredit yang direstrukturisasi, Wajib diperbaiki di SID";
                                    }
                                    if(entMappingKreditBpd.getTanggalRestrukturisasiAwal() == null){
                                        SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>Tanggal Frekuensi restrukturisasi harus di cantumkan, Sifat Kredit Adalah Kredit yang direstrukturisasi, Wajib diperbaiki di SID";
                                    }
                                    if(entMappingKreditBpd.getTanggalRestrukturisasiAkhir() == null){
                                        SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>Tanggal Frekuensi restrukturisasi harus di cantumkan, Sifat Kredit Adalah Kredit yang direstrukturisasi, Wajib diperbaiki di SID";
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            //no akad awal dan no akad akhir sama
                            try{
                                if(entMappingKreditBpd.getNoAkadAwal()!=null || entMappingKreditBpd.getNoAkadAkhir()!=null){
                                    if(entMappingKreditBpd.getNoAkadAwal().trim().equals(entMappingKreditBpd.getNoAkadAkhir().trim())){
                                        //cek apakah tanggalnya sama? 
                                        if(entMappingKreditBpd.getTglAwal()!=null || entMappingKreditBpd.getTglMulai()!=null){
                                            if(entMappingKreditBpd.getTglAwal().compareTo(entMappingKreditBpd.getTglMulai())!=0){
                                                SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>tanggal awal kredit harus sama dengan tanggal mulai, karena nomor akad awal sama dengan nomor akad akhir, Wajib diperbaiki di core";
                                                SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>tanggal awal kredit harus lebih kecil/sama dengan TANGGAL MULAI, Wajib diperbaiki di core";
                                            }
                                        }
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            
                            try{
                                if(entMappingKreditBpd.getTglAkadAwal()!=null || entMappingKreditBpd.getTglAkadAkhir()!=null){
                                    if(entMappingKreditBpd.getTglAkadAwal().compareTo(entMappingKreditBpd.getTglAkadAkhir())==0){
                                        if(entMappingKreditBpd.getNoAkadAwal()!=null || entMappingKreditBpd.getNoAkadAkhir()!=null){
                                            if(entMappingKreditBpd.getNoAkadAwal().trim().equals(entMappingKreditBpd.getNoAkadAkhir().trim())){
                                            }else{
                                                SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>NOMOR AKAD AWAL harus sama dengan NOMOR AKAD AKHIR, karena tanggal akad awal sama dengan tanggal akad akhir, Wajib diperbaiki di core";
                                            }
                                        }else{
                                            SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>NOMOR AKAD AWAL harus sama dengan NOMOR AKAD AKHIR, karena tanggal akad awal sama dengan tanggal akad akhir, Wajib diperbaiki di core";
                                        }
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            
                            try{
                                if(entMappingKreditBpd.getKodeKondisi().equals("02")){
                                    if(!entMappingKreditBpd.getKodeKolektibilitas().equals("1")){
                                        SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br> KOLEKTIBILITAS, nilai field KODE KOLEKTIBILITAS harus sama dengan 1, karena kode kondisi '02' dengan kategori kondisi 'DEBITUR TIDAK MEMILIKI KEWAJIBAN', Wajib diperbaiki di core";
                                    }
                                    if(entMappingKreditBpd.getDenda()>0){
                                        SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br> DENDA harus sama dengan 0, karena kode kondisi '02' dengan kategori kondisi 'DEBITUR TIDAK MEMILIKI KEWAJIBAN', Wajib diperbaiki di core";
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            try{
                                if(entMappingKreditBpd.getTunggakanPokok()==0 && entMappingKreditBpd.getTunggakanBunga()==0){
                                    if(entMappingKreditBpd.getJumlahHariTunggakan()>0){
                                        SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br> JUMLAH HARI TUNGGAKAN, JUMLAH HARI TUNGGAKAN harus = 0, karena TUNGGAKAN POKOK=0 dan TUNGGAKAN BUNGA=0, Wajib diperbaiki di core";
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            
                            try{
                                if(entMappingKreditBpd.getNoAkadAwal()!=null || entMappingKreditBpd.getNoAkadAkhir()!=null){
                                    if(entMappingKreditBpd.getNoAkadAwal().trim().equals(entMappingKreditBpd.getNoAkadAkhir().trim())){
                                        //cek apakah tanggalnya sama? 
                                        if(entMappingKreditBpd.getTglAkadAkhir()!=null || entMappingKreditBpd.getTglAkadAwal()!=null){
                                            if(entMappingKreditBpd.getTglAkadAkhir().compareTo(entMappingKreditBpd.getTglAkadAwal())!=0){
                                                SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>NOMOR AKAD AWAL tidak boleh sama dengan NOMOR AKAD AKHIR, karena tanggal akad awal tidak sama dengan tanggal akad akhir, Wajib diperbaiki di core";
                                            }
                                        }
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            try{
                                if(entMappingKreditBpd.getKodeJenisKredit()!=null){
                                    if(entMappingKreditBpd.getKodeJenisKredit().equals("80") || entMappingKreditBpd.getKodeJenisKredit().equals("85") || entMappingKreditBpd.getKodeJenisKredit().equals("99")){
                                        if(entMappingKreditBpd.getTglTempo()!=null){
                                            SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>TANGGAL JATUH TEMPO, harus Kosong. KODE JENIS KREDIT kategori sama dengan 'Tanpa PK', Wajib diperbaiki di core";
                                        }
                                    }
                                }
                                
                                if(entMappingKreditBpd.getKodeJenisKredit()!=null){
                                    if(entMappingKreditBpd.getKodeJenisKredit().equals("05") || entMappingKreditBpd.getKodeJenisKredit().equals("10") || entMappingKreditBpd.getKodeJenisKredit().equals("20") || entMappingKreditBpd.getKodeJenisKredit().equals("26")|| entMappingKreditBpd.getKodeJenisKredit().equals("27") || entMappingKreditBpd.getKodeJenisKredit().equals("45")){
                                        if(entMappingKreditBpd.getNoAkadAwal()==null){
                                            SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>NOMOR AKAD AWAL, wajib diisi. KODE JENIS KREDIT kategori sama dengan 'Dengan PK', Wajib diperbaiki di core";
                                        }
                                        if(entMappingKreditBpd.getNoAkadAkhir()==null){
                                            SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>NOMOR AKAD AKHIR, wajib diisi. KODE JENIS KREDIT kategori sama dengan 'Dengan PK', Wajib diperbaiki di core";
                                        }
                                        if(entMappingKreditBpd.getTglMulai()==null){
                                            SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>TANGGAL MULAI, wajib diisi. TANGGAL MULAI kategori sama dengan 'Dengan PK', Wajib diperbaiki di core";
                                        }
                                        if(entMappingKreditBpd.getTglAwal()==null){
                                            SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>TANGGAL AWAL, wajib diisi. TANGGAL AWAL kategori sama dengan 'Dengan PK', Wajib diperbaiki di core";
                                        }
                                        if(entMappingKreditBpd.getTglAkadAkhir()==null){
                                            SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>TANGGAL AKAD AKHIR, wajib diisi. TANGGAL AKAD AKHIR kategori sama dengan 'Dengan PK', Wajib diperbaiki di core";
                                        }
                                        if(entMappingKreditBpd.getTglAkadAwal()==null){
                                            SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>TANGGAL AKAD AWAL, wajib diisi. TANGGAL AKAD AWAL kategori sama dengan 'Dengan PK', Wajib diperbaiki di core";
                                        }
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            try{
                                if(entMappingKreditBpd.getBaruPerpanjangan()>0){
                                    if(entMappingKreditBpd.getTglAkadAwal()!=null){
                                        if(entMappingKreditBpd.getTglMulai()!=null){
                                            if(entMappingKreditBpd.getTglAkadAwal().after(entMappingKreditBpd.getTglMulai())){
                                                SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>TANGGAL AKAD AWAL, TANGGAL AKAD AWAL harus lebih kecil TANGGAL MULAI karena BARU / PERPANJANGAN lebih besar dari 0, TANGGAL AKAD AWAL harus lebih kecil/sama dengan TANGGAL MULAI KREDIT, Wajib diperbaiki di core";
                                            }
                                            if(entMappingKreditBpd.getTglAkadAwal().compareTo(entMappingKreditBpd.getTglMulai())==0){
                                                SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>TANGGAL AKAD AWAL, TANGGAL AKAD AWAL harus lebih kecil TANGGAL MULAI karena BARU / PERPANJANGAN lebih besar dari 0, TANGGAL AKAD AWAL harus lebih kecil/sama dengan TANGGAL MULAI KREDIT, Wajib diperbaiki di core";
                                            }
                                        }
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            try{
                                if(entMappingKreditBpd.getKodeSifat()!=null){
                                    if(entMappingKreditBpd.getKodeSifat().equals("2")){
                                        SessValidasiInputan.statusProsesValidasiKredit=SessValidasiInputan.statusProsesValidasiKredit+"<br>TAKEOVER DARI, wajib diisi. KODE SIFAT KREDIT sama dengan 2, Wajib diperbaiki di core";
                                    }
                                }
                            }catch(Exception ex){
                            }
                            
                            long checkKredit = PstMappingKreditBpd.checkKredit(periodeId, entMappingKreditBpd.getNoRekening());
                            if(checkKredit!=0) {
                                entMappingKreditBpd.setOID(checkKredit);
                                currEntMappingKreditBpd = PstMappingKreditBpd.fetchExc(checkKredit);
                                
                                entMappingKreditBpd.setPlafon(currEntMappingKreditBpd.getPlafon());
                                entMappingKreditBpd.setPlafonAwal(currEntMappingKreditBpd.getPlafonAwal());
                                entMappingKreditBpd.setBakiDebet(currEntMappingKreditBpd.getBakiDebet());
                                entMappingKreditBpd.setDenda(currEntMappingKreditBpd.getDenda());
                                entMappingKreditBpd.setNilai(currEntMappingKreditBpd.getNilai());
                                entMappingKreditBpd.setRealisasi(currEntMappingKreditBpd.getRealisasi());
                                entMappingKreditBpd.setTunggakanBunga(currEntMappingKreditBpd.getTunggakanBunga());
                                entMappingKreditBpd.setTunggakanPokok(currEntMappingKreditBpd.getTunggakanPokok());
                                
                                long insertDebitur = PstMappingKreditBpd.updateExc(entMappingKreditBpd);
                                noUpdate=noUpdate+1;
                                if(!SessValidasiInputan.getStatusProsesValidasiKredit().equals("")){
                                    insertHistory(0, "Transfer Kredit Data Update", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiKredit, "Kredit",periodeId);
                                    if(SessValidasiInputan.wajibDiisi(SessValidasiInputan.getStatusProsesValidasiKredit())){
                                        long updateData = PstKredit.updateStatusData(insertDebitur);
                                    }
                                }else{
                                    insertHistory(0, "Transfer Kredit Data Update", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiKredit, "Kredit",periodeId);
                                    long updateData = PstKredit.updateStatusData(insertDebitur);
                                }
                                
                                //buatkan status apakah data itu create /update /not change
                                if(entMappingKreditBpd.getStatusDataPelaporan()==0){//insert
                                    long updateData = PstKredit.updateStatusOperasiData(insertDebitur,0);
                                }else{
                                    //di cek apakah ada perubahan data di bulan sebelumnya?
                                    String perubahanData = entMappingKreditBpd.getLogDetail(prevEntMappingKreditBpd, currEntMappingKreditBpd);
                                    if(perubahanData.equals("")){
                                        long updateData = PstKredit.updateStatusOperasiData(insertDebitur,1);
                                    }else{
                                        long updateData = PstKredit.updateStatusOperasiData(insertDebitur,1);
                                    }
                                }
                                
                                action="Transfer Data Sudah Berhasil di Update";
                                
                            }else{
                                if(typeGetData==0){
                                    long insertDebitur = PstMappingKreditBpd.insertExc(entMappingKreditBpd);
                                    noInsert=noInsert+1;
                                    currEntMappingKreditBpd = PstMappingKreditBpd.fetchExc(insertDebitur);
                                    if(!SessValidasiInputan.getStatusProsesValidasiKredit().equals("")){
                                        insertHistory(0, "Transfer Kredit Data", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiKredit, "Kredit",periodeId);
                                        if(SessValidasiInputan.wajibDiisi(SessValidasiInputan.getStatusProsesValidasiKredit())){
                                            long updateData = PstKredit.updateStatusData(insertDebitur);
                                        }
                                    }else{
                                        insertHistory(0, "Transfer Kredit Data", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiKredit, "Kredit",periodeId);
                                        long updateData = PstKredit.updateStatusData(insertDebitur);
                                    }

                                    //buatkan status apakah data itu create /update /not change
                                    if(entMappingKreditBpd.getStatusDataPelaporan()==0){//insert
                                        long updateData = PstKredit.updateStatusOperasiData(insertDebitur,0);
                                    }else{
                                        //di cek apakah ada perubahan data di bulan sebelumnya?
                                        String perubahanData = entMappingKreditBpd.getLogDetail(prevEntMappingKreditBpd, currEntMappingKreditBpd);
                                        if(perubahanData.equals("")){
                                            long updateData = PstKredit.updateStatusOperasiData(insertDebitur,1);
                                        }else{
                                            long updateData = PstKredit.updateStatusOperasiData(insertDebitur,1);
                                        }
                                    }
                                    action="Transfer Data Sudah Berhasil di Insert";
                                }    
                            }
                            
                            no = no + 1;
                            ManagerTransferData.setStatusKredit("Transfer Data Kredit : " + no + "/" + counter + "<br>");
                            ManagerTransferDataKredit.setStatusKredit(""
                                    + "Transfer Data Kredit Update : " + noUpdate + "/" + counter + "<br>"
                                    + "Transfer Data Kredit Baru: " + noInsert + "/" + counter + "<br>"
                                    + "Transfer Data Kredit : " + no + "/" + counter + "<br>");
                        }

                    } catch (Exception es) {
                        System.out.print("Eror loh");
                        action="Transfer Data Gagal Debitur ";
                    }
                }
                if(data==0){
                    action="Data tidak ditemukan, Update data gagal";
                }
                rs.close();
                st.close();
                conn.close();

            } catch (Exception e) {
                action="Koneksi database bermasalah ";
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        /* ignored */
                    }
                }
            }
        }
        return action;
    }

    public String actionTransferDataBankGaransi() {
        return actionTransferDataBankGaransi("", 0, 0,0);
    }

    public String actionTransferDataBankGaransi(String cif, long periodeId, int counter, int typeGetData) {
        String action = "";
        if(ManagerTransferDataBankGaransi.running==false && typeGetData==0){
            return action;
        }
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //createConn();
            OutletConnection outletConnection = getConfigurasiConnection();
            String dbMasterBackup="";
            if(typeGetData==1){
                dbMasterBackup = outletConnection.getDbDatabase();
            }else{
                dbMasterBackup = outletConnection.getDbDatabaseBackup();
            }
            /*carikan querynya*/
            String sql = " SELECT GM.* FROM "
                    + "("
                    + "SELECT DISTINCT "
                    + "CONCAT("+dbMasterBackup+".BGARANSI_MASTER.KD_CAB, "+dbMasterBackup+".BGARANSI_MASTER.NO_REK) AS NO_REK, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.NO_NSB, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.KD_JENIS, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.KD_TUJUAN, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.TGL_BUKA_REK, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.NO_GARANSI, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.TGL_BUKA_REK, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.NO_GARANSI, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.TGL_BUKA_REK, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.NAMA_SINGKAT, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.NILAI_GARANSI, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.NILAI_GARANSI, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.NILAI_KONTRA, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.KOLEKTIBILITY, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.KD_CAB, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.KD_VALUTA, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.KD_STATUS, "
                    + ""+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR "
                    + "FROM "+dbMasterBackup+".BGARANSI_MASTER, " +
                    " "+dbMasterBackup+".TBL_VALUTA, " +
                    " "+dbMasterBackup+".TBL_JENIS_BG " +
                    "WHERE ("+dbMasterBackup+".BGARANSI_MASTER.KD_VALUTA = "+dbMasterBackup+".TBL_VALUTA.KD_VALUTA) " +
                    "AND ("+dbMasterBackup+".BGARANSI_MASTER.KD_JENIS = "+dbMasterBackup+".TBL_JENIS_BG.KD_JENIS) " +
                    "AND ( " +
                    "( " +
                    "MONTH("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR) = (SELECT " +
                    "  MONTH(OPEN_DATE) " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    "AND YEAR("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR) = (SELECT " +
                    "  YEAR(OPEN_DATE) " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    ") " +
                    "OR ( " +
                    "("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR > (SELECT " +
                    "  OPEN_DATE " +
                    "FROM "+dbMasterBackup+".SYSTEM_HOST) " +
                    ") " +
                    "AND ("+dbMasterBackup+".BGARANSI_MASTER.KD_STATUS NOT IN ('9')) " +
                    ") " +
                    ") ) AS GM ";
//                    + "FROM "+dbMasterBackup+".BGARANSI_MASTER "
//                    //opie-eyek
//                    + ","+dbMasterBackup+".SYSTEM_HOST D "
//                    + " WHERE (("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR > D.OPEN_DATE) OR (MONTH("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR)=MONTH(D.OPEN_DATE) AND YEAR("+dbMasterBackup+".BGARANSI_MASTER.TGL_AKHIR)=YEAR(D.OPEN_DATE))) AND "+dbMasterBackup+".BGARANSI_MASTER.KD_STATUS <> '9'"
//                    + ") AS GM "
//                    + " INNER JOIN "+dbMasterBackup+".NASABAH ON "+dbMasterBackup+".NASABAH.NO_NSB=GM.NO_NSB"
//                    + "";

            if (!cif.equals("")) {
                sql = sql + " WHERE GM.NO_REK='" + cif + "'";
            }
            // sql=sql+" FETCH FIRST 5 ROWS ONLY";

            
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            int no = 0;
            int noInsert=0;
            int noUpdate=0;
            int data=0;
            while (rs.next()) {
                data=1;
                try {
                    if(ManagerTransferDataBankGaransi.running==false && typeGetData==0){
                        return action;
                    }
                    MappingBankGaransiBpd entMappingBankGaransiBpd = new MappingBankGaransiBpd();
                    MappingBankGaransiBpd currEntMappingBankGaransiBpd = new MappingBankGaransiBpd();
                    MappingBankGaransiBpd prevEntMappingBankGaransiBpd = new MappingBankGaransiBpd();
                    SessValidasiInputan.statusProsesValidasiBankGaransi="";
                    entMappingBankGaransiBpd.setNoRekening(rs.getString("NO_REK"));
                    entMappingBankGaransiBpd.setFlagDetail("D");
                    
                    if(0==0){
                        requestPrevEntityObjectBankGaransi(entMappingBankGaransiBpd, periodeId, entMappingBankGaransiBpd.getNoRekening(), prevEntMappingBankGaransiBpd);
                    }
                    
                    //entMappingBankGaransiBpd.setCif(rs.getString("NO_NSB"));
                    String entCif = rs.getString("NO_NSB"); //entMappingKreditBpd.getCif().replaceAll("\\s","");
                    entMappingBankGaransiBpd.setCif(entCif.replace("\\s", ""));
                    entMappingBankGaransiBpd.setKodeJenisGaransi(SessGetContentDataOjk.getContentDataKodeJenisGaransi(rs.getString("KD_JENIS")));
                    if(entMappingBankGaransiBpd.getKodeJenisGaransi()==null || entMappingBankGaransiBpd.getKodeJenisGaransi().equals("")){
                        SessValidasiInputan.statusProsesValidasiBankGaransi=SessValidasiInputan.statusProsesValidasiBankGaransi+"<br> Kode Jenis Kosong, Wajib di perbaiki di core";
                    }
                    entMappingBankGaransiBpd.setKodeTujuanGaransi(SessGetContentDataOjk.getContentDataKodeTujuanGaransi(rs.getString("KD_TUJUAN")));
                    if(entMappingBankGaransiBpd.getKodeTujuanGaransi()==null || entMappingBankGaransiBpd.getKodeTujuanGaransi().equals("")){
                        SessValidasiInputan.statusProsesValidasiBankGaransi=SessValidasiInputan.statusProsesValidasiBankGaransi+"<br> Kode Tujuan Garansi, Wajib di perbaiki di core";
                    }
                    entMappingBankGaransiBpd.setTglDiterbitkan(rs.getDate("TGL_BUKA_REK"));
                    entMappingBankGaransiBpd.setTglJatuhTempo(rs.getDate("TGL_AKHIR"));
                    entMappingBankGaransiBpd.setNoAkadAwal(rs.getString("NO_GARANSI"));
                    entMappingBankGaransiBpd.setTglAkadAwal(rs.getDate("TGL_BUKA_REK"));
                    entMappingBankGaransiBpd.setNoAkadAkhir(rs.getString("NO_GARANSI"));
                    entMappingBankGaransiBpd.setTglAkadAkhir(rs.getDate("TGL_BUKA_REK"));
                    entMappingBankGaransiBpd.setNamaYgDijamin(rs.getString("NAMA_SINGKAT"));
                    entMappingBankGaransiBpd.setPlafon(rs.getDouble("NILAI_GARANSI"));
                    entMappingBankGaransiBpd.setNominal(rs.getDouble("NILAI_GARANSI"));
                    entMappingBankGaransiBpd.setSetoranJaminan(rs.getDouble("NILAI_KONTRA"));
                    entMappingBankGaransiBpd.setKodeKolektibilitas(SessGetContentDataOjk.getContentDataKodeKolektibilitas(rs.getString("KOLEKTIBILITY")));
                    if(entMappingBankGaransiBpd.getKodeKolektibilitas()==null || entMappingBankGaransiBpd.getKodeKolektibilitas().equals("")){
                        SessValidasiInputan.statusProsesValidasiBankGaransi=SessValidasiInputan.statusProsesValidasiBankGaransi+"<br> Kode Kolektibility, Wajib di perbaiki di core";
                    }
                    entMappingBankGaransiBpd.setKodeKantorCabang(rs.getString("KD_CAB"));
                    entMappingBankGaransiBpd.setPeriodeId(periodeId);
                    
                    
                    entMappingBankGaransiBpd.setKodeKondisi(SessGetContentDataOjk.getContentDataKodeKondisiBankGaransi(rs.getString("KD_STATUS")));
                    if(entMappingBankGaransiBpd.getKodeKondisi().equals("")){
                        SessValidasiInputan.statusProsesValidasiBankGaransi = SessValidasiInputan.statusProsesValidasiBankGaransi+"<br>Kode Kondisi, Wajib di perbaiki di core";
                    }
                    entMappingBankGaransiBpd.setKodeValuta(SessGetContentDataOjk.getContentDataKodeValutaAngka(rs.getString("KD_VALUTA")));
                    if(entMappingBankGaransiBpd.getKodeValuta().equals("")){
                        SessValidasiInputan.statusProsesValidasiBankGaransi =  SessValidasiInputan.statusProsesValidasiBankGaransi+"<br>Kode Valuta, Wajib di perbaiki di core";
                    }
                    entMappingBankGaransiBpd.setTglAkhir(rs.getDate("TGL_AKHIR"));
                    entMappingBankGaransiBpd.setTglKondisi(rs.getDate("TGL_AKHIR"));
                    if(!entMappingBankGaransiBpd.getKodeKondisi().equals("00")){
                        if(entMappingBankGaransiBpd.getTglKondisi()==null){
                            SessValidasiInputan.statusProsesValidasiBankGaransi =  SessValidasiInputan.statusProsesValidasiBankGaransi+"<br>Kode Kondisi Tidak Sama Dengan 00, Tanggal Kondisi, Wajib di perbaiki di core";
                        }
                    }
                    
                    long bankGaransiOid = PstMappingBankGaransiBpd.checkBankGaransi(periodeId, entMappingBankGaransiBpd.getNoRekening());
                    if(bankGaransiOid!=0){
                        entMappingBankGaransiBpd.setOID(bankGaransiOid);
                        long insertDebitur = PstMappingBankGaransiBpd.updateExc(entMappingBankGaransiBpd);
                        currEntMappingBankGaransiBpd = PstMappingBankGaransiBpd.fetchExc(bankGaransiOid);
                        noUpdate=noUpdate+1;
                        //ManagerTransferData.setStatusBankGaransi("Transfer Data Bank Garansi Update : " + noUpdate + "/" + counter + "<br>");
                        //ManagerTransferDataBankGaransi.setStatusBankGaransi("Transfer Data Bank Garansi Update : " + noUpdate + "/" + counter + "<br>");
                        if(!SessValidasiInputan.getStatusProsesValidasiBankGaransi().equals("")){
                            insertHistory(0, "Transfer Data Bank Garansi Update", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiBankGaransi, "Bank Garansi",periodeId);
                            if(SessValidasiInputan.wajibDiisi(SessValidasiInputan.getStatusProsesValidasiBankGaransi())){
                                long updateData = PstDebiturBdnUsaha.updateStatusData(insertDebitur);
                            }
                        }else{
                            insertHistory(0, "Transfer Data Bank Garansi Update", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiBankGaransi, "Bank Garansi",periodeId);
                            long updateData = PstBankGaransi.updateStatusData(insertDebitur);
                        }
                        
                        //buatkan status apakah data itu create /update /not change
                        if(entMappingBankGaransiBpd.getStatusDataPelaporan()==0){//insert
                            long updateData = PstBankGaransi.updateStatusOperasiData(insertDebitur,0);
                        }else{
                            //di cek apakah ada perubahan data di bulan sebelumnya?
                            String perubahanData = entMappingBankGaransiBpd.getLogDetail(prevEntMappingBankGaransiBpd, currEntMappingBankGaransiBpd);
                            if(perubahanData.equals("")){
                                long updateData = PstBankGaransi.updateStatusOperasiData(insertDebitur,2);
                            }else{
                                long updateData = PstBankGaransi.updateStatusOperasiData(insertDebitur,1);
                            }
                        }
                        
                        action="Transfer Data Sudah Berhasil di Update";
 
                    }else{
                        if(typeGetData==0){
                            long insertDebitur = PstMappingBankGaransiBpd.insertExc(entMappingBankGaransiBpd);
                            currEntMappingBankGaransiBpd = PstMappingBankGaransiBpd.fetchExc(insertDebitur);
                            noInsert=noInsert+1;
                            if(!SessValidasiInputan.getStatusProsesValidasiBankGaransi().equals("")){
                                insertHistory(0, "Transfer Data Bank Garansi", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiBankGaransi, "Bank Garansi",periodeId);
                                if(SessValidasiInputan.wajibDiisi(SessValidasiInputan.getStatusProsesValidasiBankGaransi())){
                                    long updateData = PstDebiturBdnUsaha.updateStatusData(insertDebitur);
                                }
                            }else{
                                insertHistory(0, "Transfer Data Bank Garansi", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiBankGaransi, "Bank Garansi",periodeId);
                                long updateData = PstBankGaransi.updateStatusData(insertDebitur);
                            }

                            //buatkan status apakah data itu create /update /not change
                            if(entMappingBankGaransiBpd.getStatusDataPelaporan()==0){//insert
                                long updateData = PstBankGaransi.updateStatusOperasiData(insertDebitur,0);
                            }else{
                                //di cek apakah ada perubahan data di bulan sebelumnya?
                                String perubahanData = entMappingBankGaransiBpd.getLogDetail(prevEntMappingBankGaransiBpd, currEntMappingBankGaransiBpd);
                                if(perubahanData.equals("")){
                                    long updateData = PstBankGaransi.updateStatusOperasiData(insertDebitur,2);
                                }else{
                                    long updateData = PstBankGaransi.updateStatusOperasiData(insertDebitur,1);
                                }
                            }
                            action="Transfer Data Sudah Berhasil di Insert";
                        }
                    }
                    
                    no = no + 1;
                    ManagerTransferData.setStatusBankGaransi("Transfer Data Bank Garansi : " + no + "/" + counter + "<br>");
                    ManagerTransferDataBankGaransi.setStatusBankGaransi(""
                            + "Transfer Data Bank Garansi Update : " + noUpdate + "/" + counter + "<br>"
                            + "Transfer Data Bank Garansi Baru : " + noInsert + "/" + counter + "<br>"
                            + "Transfer Data Bank Garansi : " + no + "/" + counter + "<br>");

                } catch (Exception es) {
                    //System.out.print("Eror loh");
                    action="Transfer Data Gagal Bank Garansi ";
                }
            }
            
            if(data==0){
                action="Data tidak ditemukan, Update data gagal";
            }
            
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
             action="Koneksi database bermasalah ";
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        return action;
    }

    public String actionTransferDataAgunan() {
        return actionTransferDataAgunan("", 0, 0,0,"");
    }

    public String actionTransferDataAgunan(String cif, long periodeId, int counter, int typeGetData, String noRekeningData) {
        String action = "";
        if(ManagerTransferDataAgunan.running==false && typeGetData==0){
            return action;
        }
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //createConn();
            OutletConnection outletConnection = getConfigurasiConnection();
            String dbMasterBackup="";
            if(typeGetData==1){
                dbMasterBackup = outletConnection.getDbDatabase();
            }else{
                dbMasterBackup = outletConnection.getDbDatabaseBackup();
            }
            String sql = "SELECT AGN.* FROM ( "+
                        " SELECT DISTINCT C.AGUNANID, A.CIFMSTID, A.ACCNBR,  C.BRANCHID, C.JNSAGNID, C.IKATANID, " +
                        " C.OWNER, C.CERTIFCT, C.OWNRADDR, C.LOCDT2, C.NJOPVAL, " +
                        " C.BANKVAL, C.APRDATE, C.PARIPASU, C.BRANCHID, " +
                        " C.JNSAGLBU, C.RATORG, C.APRDATE, CONCAT(C.BRANCHID,A.ACCNBR) AS NO_REKENING " +
                        ", (CASE WHEN (B.NILAI<>0) THEN B.NILAI ELSE (C.BANKVAL*B.PARIPASU/100) END) NILAITAKSASI "+
                        " , C.STARTDT "+
                        " FROM "+dbMasterBackup+".LNACCTA A, "+dbMasterBackup+".DTLAGNAN B, "+dbMasterBackup+".AGUNAN C " +
                        " WHERE ( A.BRNCHID = B.BRANCHID ) AND  " +
                        " ( A.ACCNBR  = B.ACCNBR ) AND " +
                        " ( B.BRANCHID = C.BRANCHID ) AND  " +
                        " ( B.AGUNANID  = C.AGUNANID ) AND " +
                        "( A.ACCSTS NOT IN ('0','7','9')) " +
                        "UNION " +
                        "SELECT DISTINCT C.AGUNANID, A.CIFMSTID, A.ACCNBR,  C.BRANCHID, C.JNSAGNID, C.IKATANID, " +
                        " C.OWNER, C.CERTIFCT, C.OWNRADDR, C.LOCDT2, C.NJOPVAL, " +
                        " C.BANKVAL, C.APRDATE, C.PARIPASU, C.BRANCHID, " +
                        " C.JNSAGLBU, C.RATORG, C.APRDATE, CONCAT(C.BRANCHID,A.ACCNBR) AS NO_REKENING " +
                        ", (CASE WHEN (B.NILAI<>0) THEN B.NILAI ELSE (C.BANKVAL*B.PARIPASU/100) END) NILAITAKSASI "+
                        " , C.STARTDT "+
                        "FROM "+dbMasterBackup+".LNACCATT A, "+dbMasterBackup+".DTLAGNAN B, "+dbMasterBackup+".AGUNAN C " +
                        "WHERE ( A.BRANCHID = B.BRANCHID ) AND  " +
                        "( A.ACCNBR  = B.ACCNBR ) AND " +
                        " ( B.BRANCHID = C.BRANCHID ) AND  " +
                        " ( B.AGUNANID  = C.AGUNANID ) AND " +
                        "( A.ACCSTS NOT IN ('0','7','9'))  " +
                        " " +
                        "UNION " +
                        "SELECT DISTINCT C.AGUNANID, A.CIFMSTID, A.ACCNBR,  C.BRANCHID, C.JNSAGNID, C.IKATANID, " +
                        " C.OWNER, C.CERTIFCT, C.OWNRADDR, C.LOCDT2, C.NJOPVAL, " +
                        " C.BANKVAL, C.APRDATE, C.PARIPASU, C.BRANCHID, " +
                        "  C.JNSAGLBU, C.RATORG, C.APRDATE, CONCAT(C.BRANCHID,A.ACCNBR) AS NO_REKENING " +
                        ", (CASE WHEN (B.NILAI<>0) THEN B.NILAI ELSE (C.BANKVAL*B.PARIPASU/100) END) NILAITAKSASI "+
                        " , C.STARTDT "+
                        "FROM "+dbMasterBackup+".LNACCAT A, "+dbMasterBackup+".DTLAGNAN B, "+dbMasterBackup+".AGUNAN C " +
                        "WHERE ( A.BRANCHID = B.BRANCHID ) AND  " +
                        " ( A.ACCNBR  = B.ACCNBR ) AND " +
                        " ( B.BRANCHID = C.BRANCHID ) AND  " +
                        " ( B.AGUNANID  = C.AGUNANID ) AND " +
                        " ( A.ACCSTS NOT IN ('0','7','9')) ) AS AGN ";

            if (!cif.equals("")) {
                sql = sql + " WHERE AGN.AGUNANID='" + cif + "' AND AGN.NO_REKENING='"+noRekeningData+"'";
            }
//            sql = sql + "WHERE DBLMON.AGUNAN.AGUNANID='0053000777'";
            
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int no = 0;
            int noInsert=0;
            int noUpdate=0;
            String noIdAgunan="";
            int data=0;
            while (rs.next()) {
                data=1;
                try {
                    if(ManagerTransferDataAgunan.running==false && typeGetData==0){
                        return action;
                    }
                    MappingAgunanBpd entMappingAgunanBpd = new MappingAgunanBpd();
                    MappingAgunanBpd prevEntMappingAgunanBpd = new MappingAgunanBpd();
                    MappingAgunanBpd currEntMappingAgunanBpd = new MappingAgunanBpd();
                    entMappingAgunanBpd.setKodeRegisterAgunan(rs.getString("AGUNANID"));
                    String cifx = entMappingAgunanBpd.getKodeRegisterAgunan().replaceAll("\\s", "");
                    noIdAgunan=cifx;
                    entMappingAgunanBpd.setFlagDetail("D");
                    if (true) {
                        
                        String noRekening="";
                        noRekening=""+rs.getString("BRANCHID")+""+rs.getString("ACCNBR");
                        entMappingAgunanBpd.setNoRekening(noRekening);
                        
                        if(0==0){
                            requestPrevEntityObjectAgunan(entMappingAgunanBpd, periodeId, noRekening, cifx, prevEntMappingAgunanBpd);
                        }
                        
                        SessValidasiInputan.statusProsesValidasiAgunan="";
                        entMappingAgunanBpd.setKodeStatusAgunan(SessGetContentDataOjk.getContentDataStatusAgunan(rs.getString("JNSAGLBU")));
                        if(entMappingAgunanBpd.getKodeStatusAgunan()==null || entMappingAgunanBpd.getKodeStatusAgunan().equals("")){
                            SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Kode Status Agunan Tidak Boleh Kosong, Wajib di perbaiki di core";
                        }
                        entMappingAgunanBpd.setKodeJenisAgunan(SessGetContentDataOjk.getContentDataKodeJenisAgunan(rs.getString("JNSAGNID")));
                        if(entMappingAgunanBpd.getKodeJenisAgunan().equals("")||entMappingAgunanBpd.getKodeJenisAgunan().equals(" ")){
                            SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Kode Jenis Agunan, Wajib di perbaiki di core";
                        }
                        entMappingAgunanBpd.setNamaPemilikAgunan(SessValidasiInputan.validasiOwnerAgunan(rs.getString("OWNER")));
                        entMappingAgunanBpd.setBuktiKepemilikan(SessValidasiInputan.validasiBuktiKepemilikan(rs.getString("CERTIFCT")));
                        
                       // String alamatAgunan = rs.getString("OWNRADDR");
                        try{
                            entMappingAgunanBpd.setAlamatAgunan(SessValidasiInputan.validasiAgunanAlamat(rs.getString("OWNRADDR")));
                        }catch(Exception es){
                            entMappingAgunanBpd.setAlamatAgunan("");
                        }
                        
                        entMappingAgunanBpd.setKodeKabLokasiAgunan(SessGetContentDataOjk.getContentDataKabKota(rs.getString("LOCDT2")));
                        if(entMappingAgunanBpd.getKodeKabLokasiAgunan().equals("")){
                            SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Kode Kab/Kota (Dati 2), Wajib di perbaiki di core";
                        }
                        entMappingAgunanBpd.setNilaiAgunanNjop(rs.getDouble("NJOPVAL"));
                        
                        if(entMappingAgunanBpd.getKodeStatusAgunan().equals("1")){
                            if (entMappingAgunanBpd.getNilaiAgunanNjop()==0){
                                SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Kode Status Tersedia, Nilai Agunan (NJOP) harus lebih besar daripada 0, Wajib di perbaiki di core";
                            }
                        }
                        
                        //entMappingAgunanBpd.setNilaiAgunanLjk(rs.getDouble("NILAITAKSASI"));
                        //update opie-eyek 20170228 sesuai hasil sosialisasi
                        entMappingAgunanBpd.setNilaiAgunanLjk(rs.getDouble("BANKVAL"));
                        if(entMappingAgunanBpd.getKodeStatusAgunan().equals("1")){
                            if (entMappingAgunanBpd.getNilaiAgunanLjk()==0){
                                SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Kode Status Tersedia, Nilai Agunan Menurut LJK, Wajib di perbaiki di core";
                            }
                            if (entMappingAgunanBpd.getNilaiAgunanLjk()<0){
                                SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Nilai agunan menurut LJK, format data harus angka tidak boleh negatif, Wajib di rubah";
                            }
                        }
                        
                        
                        String entCif = rs.getString("CIFMSTID"); //entMappingKreditBpd.getCif().replaceAll("\\s","");
                        entMappingAgunanBpd.setCif(entCif.replace("\\s", ""));
                        entMappingAgunanBpd.setJenisIkatan(SessGetContentDataOjk.getContentDataKodeJenisPengikatan(rs.getString("IKATANID")));
                        
                        if(entMappingAgunanBpd.getKodeStatusAgunan().equals("1")){
                            if (entMappingAgunanBpd.getJenisIkatan().equals("")){
                                SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Kode Status Indent, Kode Jenis Pengikatan, Wajib di perbaiki di core";
                                SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Kode Status Indent, Tanggal Pengikatan, Wajib di perbaiki di core";
                            }
                        }
                        
                        
                        entMappingAgunanBpd.setTanggalPenilaian(rs.getDate("APRDATE"));
                        if(entMappingAgunanBpd.getKodeStatusAgunan().equals("1")){
                            if(entMappingAgunanBpd.getTanggalPenilaian()==null){
                                SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Status Agunan Tersedia, Tanggal Penilaian LJK, Wajib di perbaiki di core";
                            }
                        }
                        
                        int porsentaseParipasu = rs.getInt("PARIPASU");
                        if(porsentaseParipasu==100){
                            entMappingAgunanBpd.setStatusParipasu("T");
                            entMappingAgunanBpd.setProsentaseParipasu(porsentaseParipasu);
                        }else{
                            if(porsentaseParipasu==0){
                                entMappingAgunanBpd.setStatusParipasu("T");
                                entMappingAgunanBpd.setProsentaseParipasu(porsentaseParipasu);
                            }else{
                                entMappingAgunanBpd.setStatusParipasu("Y");
                                entMappingAgunanBpd.setProsentaseParipasu(porsentaseParipasu);
                            }
                        }
                        
                        if(entMappingAgunanBpd.getProsentaseParipasu()<0){
                            SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br>Prosentase paripasu format data harus angka dan koma tidak boleh negatif, Wajib di rubah";
                        }
                        
                        entMappingAgunanBpd.setKodeKantorCabang(rs.getString("BRANCHID"));
                        entMappingAgunanBpd.setPeriodeId(periodeId);
                        
                        if(entMappingAgunanBpd.getKodeStatusAgunan().equals("1")){
                            entMappingAgunanBpd.setTglPengikat(rs.getDate("STARTDT"));
                            if(entMappingAgunanBpd.getTglPengikat()==null){
                                SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Status Agunan Tersedia, Tanggal Pengikatan Wajib di perbaiki di core";
                            }
                        }
                        
                        entMappingAgunanBpd.setKodeLembagaPemeringkat(SessGetContentDataOjk.getContentDataKodeLembagaPemeringkat(rs.getString("RATORG")));
                        entMappingAgunanBpd.setTglPenilaianIndependent(rs.getDate("APRDATE"));
                        
                        if(entMappingAgunanBpd.getNilaiAgunanPenilaianIndependent()!=0){
                            if(entMappingAgunanBpd.getTglPenilaianIndependent()==null){
                                SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Tanggal Penilaian Independent, Wajib di perbaiki di core";
                            }
                        }
                        
                        entMappingAgunanBpd.setStatusKreditJoinAccount("T");
                        entMappingAgunanBpd.setKodeJenisSegementFasilitas("F01");
                        entMappingAgunanBpd.setDiAsuransikan("T");
                        SessValidasiInputan.statusProsesValidasiAgunan = SessValidasiInputan.statusProsesValidasiAgunan+"<br> Pilihan diasuransikan memang tidak ditemukan, default T";
                        
                        long checkDataAgunan = PstMappingAgunanBpd.checkDataAgunan(periodeId, cifx, noRekening);
                        if(checkDataAgunan!=0){
                            entMappingAgunanBpd.setOID(checkDataAgunan);
                            long insertDebitur = PstMappingAgunanBpd.updateExc(entMappingAgunanBpd);
                            currEntMappingAgunanBpd = PstMappingAgunanBpd.fetchExc(checkDataAgunan);
                            noUpdate=noUpdate+1;
                             if(!SessValidasiInputan.getStatusProsesValidasiAgunan().equals("")){
                                insertHistory(0, "Transfer Data Agunan Update", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiAgunan, "Agunan",periodeId);
                                
                                if(SessValidasiInputan.wajibDiisi(SessValidasiInputan.getStatusProsesValidasiAgunan())){
                                    long updateData = PstAgunan.updateStatusData(insertDebitur);
                                }
                            }else{
                                 insertHistory(0, "Transfer Data Agunan Update", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiAgunan, "Agunan",periodeId);
                                 long updateData = PstAgunan.updateStatusData(insertDebitur);
                            }
                             
                            //buatkan status apakah data itu create /update /not change
                            if(entMappingAgunanBpd.getStatusDataPelaporan()==0){//insert
                                long updateData = PstAgunan.updateStatusOperasiData(insertDebitur,0);
                            }else{
                                //di cek apakah ada perubahan data di bulan sebelumnya?
                                String perubahanData = entMappingAgunanBpd.getLogDetail(prevEntMappingAgunanBpd, currEntMappingAgunanBpd);
                                if(perubahanData.equals("")){
                                    long updateData = PstAgunan.updateStatusOperasiData(insertDebitur,2);
                                }else{
                                    long updateData = PstAgunan.updateStatusOperasiData(insertDebitur,1);
                                }
                            }
                             
                            action="Transfer Data Sudah Berhasil di Update";
 
                        }else{
                            if(typeGetData==0){
                                long insertDebitur = PstMappingAgunanBpd.insertExc(entMappingAgunanBpd);
                                currEntMappingAgunanBpd = PstMappingAgunanBpd.fetchExc(insertDebitur);
                                noInsert=noInsert+1;
                                if(!SessValidasiInputan.getStatusProsesValidasiAgunan().equals("")){
                                    insertHistory(0, "Transfer Data Agunan ", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiAgunan, "Agunan",periodeId);
                                    if(SessValidasiInputan.wajibDiisi(SessValidasiInputan.getStatusProsesValidasiAgunan())){
                                        long updateData = PstAgunan.updateStatusData(insertDebitur);
                                    }
                                }else{
                                    insertHistory(0, "Transfer Data Agunan ", 4, insertDebitur,""+SessValidasiInputan.statusProsesValidasiAgunan, "Agunan",periodeId);
                                    long updateData = PstAgunan.updateStatusData(insertDebitur);
                                }

                                //buatkan status apakah data itu create /update /not change
                                if(entMappingAgunanBpd.getStatusDataPelaporan()==0){//insert
                                    long updateData = PstAgunan.updateStatusOperasiData(insertDebitur,0);
                                }else{
                                    //di cek apakah ada perubahan data di bulan sebelumnya?
                                    String perubahanData = entMappingAgunanBpd.getLogDetail(prevEntMappingAgunanBpd, currEntMappingAgunanBpd);
                                    if(perubahanData.equals("")){
                                        long updateData = PstAgunan.updateStatusOperasiData(insertDebitur,2);
                                    }else{
                                        long updateData = PstAgunan.updateStatusOperasiData(insertDebitur,1);
                                    }
                                } 
                                action="Transfer Data Sudah Berhasil di Insert";
                            }    
                        }
                        
                        no = no + 1;
                        ManagerTransferData.setStatusAgunan("Transfer Data Agunan : " + no + "/" + counter + "<br>");
                        ManagerTransferDataAgunan.setStatusAgunan(""
                                + "Transfer Data Agunan Update : " + noUpdate + "/" + counter + "<br>"
                                + "Transfer Data Agunan Baru : " + noInsert + "/" + counter + "<br>"
                                + "Transfer Data Agunan : " + no + "/" + counter + "<br>");
                    }
                } catch (Exception es) {
                    System.out.print("Eror loh : " + noIdAgunan);
                    insertHistory(0, "Transfer Data Agunan Bermasalah", 4, 0,"Data Agunan Tidak Masuk : "+noIdAgunan, "Agunan",periodeId);
                    action="Transfer Data Gagal Debitur ";
                }
            }
            
            if(data==0){
                action="Data tidak ditemukan, Update data gagal";
            }
            
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            action="Koneksi database bermasalah ";
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        return action;
    }

    public String actionTransferDataPengurusAtauPemilik() {
        return actionTransferDataPengurusAtauPemilik("", 0, 0);
    }

    public String actionTransferDataPengurusAtauPemilik(String cif, long periodeId, int counter) {
        String action = "";
        if(ManagerTransferDataPengurusPemilik.running==false){
            return action;
        }
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //createConn();
            /*carikan querynya*/
            String sql = ""
                    + "SELECT DISTINCT "
                    + "DBLMON.NASABAH.NO_IDENTITAS, "
                    + "DBLMON.PENGURUS.NO_NSB, "
                    + "DBLMON.NASABAH.KD_IDENTITAS, "
                    + "DBLMON.NASABAH.NAMA_NSB, "
                    + "DBLMON.NASABAH.JNS_KELAMIN, "
                    + "DBLMON.NASABAH.ALAMAT, "
                    + "DBLMON.NASABAH.KELURAHAN, "
                    + "DBLMON.NASABAH.KD_KECAMATAN, "
                    + "DBLMON.NASABAH.KD_DATI_II, "
                    + "DBLMON.PENGURUS.PANGSA, "
                    + "DBLMON.NASABAH.KD_CAB "
                    + "FROM DBLMON.PENGURUS "
                    + "INNER JOIN DBLMON.NASABAH "
                    + "ON DBLMON.PENGURUS.NO_NSB = DBLMON.NASABAH.NO_NSB ";

            if (!cif.equals("")) {
                sql = sql + " WHERE NO_NSB='" + cif + "'";
            }
            //sql = sql + "FETCH FIRST 5 ROWS ONLY";

            OutletConnection outletConnection = getConfigurasiConnection();
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            int no = 0;
            int noInsert=0;
            int noUpdate=0;
            while (rs.next()) {
                try {
                    MappingPengurusAtauPemilikBpd entMappingPengurusAtauPemilikBpd = new MappingPengurusAtauPemilikBpd();

                    entMappingPengurusAtauPemilikBpd.setFlagDetail("D");
                    entMappingPengurusAtauPemilikBpd.setNomorIdentitas(rs.getString("NO_IDENTITAS"));
                    String entCif = rs.getString("NO_NSB");
                    entMappingPengurusAtauPemilikBpd.setCif(entCif.replace("\\s", ""));
                    entMappingPengurusAtauPemilikBpd.setJenisIdentitas(SessGetContentDataOjk.getContentDataJenisIdentitas(rs.getString("KD_IDENTITAS")));
                    entMappingPengurusAtauPemilikBpd.setNamaPengurus(rs.getString("NAMA_NSB"));
                    
                    try{
                        if(rs.getString("JNS_KELAMIN").equals("1")){
                            entMappingPengurusAtauPemilikBpd.setJenisKelamin("P");
                        }else{
                            entMappingPengurusAtauPemilikBpd.setJenisKelamin("W");
                        }
                    }catch(Exception ex){
                        entMappingPengurusAtauPemilikBpd.setJenisKelamin("P");
                    }
                    
                    entMappingPengurusAtauPemilikBpd.setAlamat(rs.getString("ALAMAT"));
                    entMappingPengurusAtauPemilikBpd.setKelurahan(rs.getString("KELURAHAN"));
                    entMappingPengurusAtauPemilikBpd.setKecamatan(rs.getString("KD_KECAMATAN"));
                    entMappingPengurusAtauPemilikBpd.setKdKabupaten(SessGetContentDataOjk.getContentDataKabKota(rs.getString("KD_DATI_II")));
                    
                    entMappingPengurusAtauPemilikBpd.setPangsa(rs.getString("PANGSA"));
                    
                    entMappingPengurusAtauPemilikBpd.setKdCabang(rs.getString("KD_CAB"));
                    entMappingPengurusAtauPemilikBpd.setPeriodeId(periodeId);
                    
                    long oidPengurus = PstMappingPengurusAtauPemilikBpd.checkDataPengurus(periodeId, entCif);
                    if(oidPengurus!=0){
                        entMappingPengurusAtauPemilikBpd.setOID(oidPengurus);
                        long insertDebitur = PstMappingPengurusAtauPemilikBpd.updateExc(entMappingPengurusAtauPemilikBpd);
                        noUpdate=noUpdate+1;
                        ManagerTransferData.setStatusPengurusAtauPemilik("Transfer Data Pengurus/Pemilik : " + noUpdate + "/" + counter + "<br>");
                        ManagerTransferDataPengurusPemilik.setStatusPengurusAtauPemilik("Transfer Data Pengurus/Pemilik : " + noUpdate + "/" + counter + "<br>");
                    }else{
                        long insertDebitur = PstMappingPengurusAtauPemilikBpd.insertExc(entMappingPengurusAtauPemilikBpd);
                        noInsert=noInsert+1;
                        ManagerTransferData.setStatusPengurusAtauPemilik("Transfer Data Pengurus/Pemilik : " + noInsert + "/" + counter + "<br>");
                        ManagerTransferDataPengurusPemilik.setStatusPengurusAtauPemilik("Transfer Data Pengurus/Pemilik : " + noInsert + "/" + counter + "<br>");
                    }
                    no = no + 1;
                    ManagerTransferData.setStatusPengurusAtauPemilik("Transfer Data Pengurus/Pemilik : " + no + "/" + counter + "<br>");
                    ManagerTransferDataPengurusPemilik.setStatusPengurusAtauPemilik("Transfer Data Pengurus/Pemilik : " + no + "/" + counter + "<br>");

                } catch (Exception es) {
                    System.out.print("Eror loh");
                }
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        return action;
    }

    public long cekOidPeriode(String cif, int type) {
        long periodeId = 0;
        Vector vperiodeSize = PstPeriode.list(0, 0,"", "");
        Vector vperiode = PstPeriode.list(0, 0, PstPeriode.fieldNames[PstPeriode.FLD_POSTED] + "='0'", "");
        Periode periode = new Periode();
            if (vperiode.size() > 0) {
                periode = (Periode) vperiode.get(0);
                periodeId = periode.getOID();
                //delete history
                
                //delete semua transaksi pada periode jika transaksi masih 1 bulan
                if(vperiodeSize.size()==1){
                    if (periode.getOID() != 0) {
                        if (type == 0) {
                            if (cif.equals("")) {
                                long xxxx = PstLogSysHistory.deleteHistorySegment(periode.getOID(),"");
                                long ooo = PstMappingKreditBpd.deleteSegmentKreditPerPeriode(periode.getOID(), "");
                                long x = PstMappingAgunanBpd.deleteSegmentAnggunanPerPeriode(periode.getOID(), "");
                                long y = PstMappingBankGaransiBpd.deleteSegmentBankGaransiPerPeriode(periode.getOID(), "");
                                long z = PstMappingDebiturBpd.deleteSegmentDebiturPerPeriode(periode.getOID(), "");
                                long q = PstMappingPengurusAtauPemilikBpd.deleteSegmentPengurusPemilikPerPeriode(periode.getOID(), "");
                            } else {
                                long ooo = PstMappingKreditBpd.deleteSegmentKreditPerPeriode(periode.getOID(), cif);
                                long x = PstMappingAgunanBpd.deleteSegmentAnggunanPerPeriode(periode.getOID(), cif);
                                long y = PstMappingBankGaransiBpd.deleteSegmentBankGaransiPerPeriode(periode.getOID(), cif);
                                long z = PstMappingDebiturBpd.deleteSegmentDebiturPerPeriode(periode.getOID(), cif);
                                long q = PstMappingPengurusAtauPemilikBpd.deleteSegmentPengurusPemilikPerPeriode(periode.getOID(), cif);
                            }
                        } else if (type == 1) { //debitur
                            if (cif.equals("")) {
                                long z = PstMappingDebiturBpd.deleteSegmentDebiturPerPeriode(periode.getOID(), "");
                            } else {
                                long z = PstMappingDebiturBpd.deleteSegmentDebiturPerPeriode(periode.getOID(), cif);
                            }
                        } else if (type == 2) { //kredit
                            if (cif.equals("")) {
                                long ooo = PstMappingKreditBpd.deleteSegmentKreditPerPeriode(periode.getOID(), "");
                            } else {
                                long ooo = PstMappingKreditBpd.deleteSegmentKreditPerPeriode(periode.getOID(), cif);
                            }
                        } else if (type == 3) { //agunan
                            if (cif.equals("")) {
                                long x = PstMappingAgunanBpd.deleteSegmentAnggunanPerPeriode(periode.getOID(), "");
                            } else {
                                long x = PstMappingAgunanBpd.deleteSegmentAnggunanPerPeriode(periode.getOID(), cif);
                            }
                        } else if (type == 4) { //bank garansi
                            if (cif.equals("")) {
                                long y = PstMappingBankGaransiBpd.deleteSegmentBankGaransiPerPeriode(periode.getOID(), "");
                            } else {
                                long y = PstMappingBankGaransiBpd.deleteSegmentBankGaransiPerPeriode(periode.getOID(), cif);
                            }
                        } else if (type == 5) { //pengurus
                            if (cif.equals("")) {
                                long q = PstMappingPengurusAtauPemilikBpd.deleteSegmentPengurusPemilikPerPeriode(periode.getOID(), "");
                            } else {
                                long q = PstMappingPengurusAtauPemilikBpd.deleteSegmentPengurusPemilikPerPeriode(periode.getOID(), cif);
                            }
                        }
                    }
                }else{
                    if (periode.getOID() != 0) {
                        if (type == 0) {
                        } else if (type == 1) { //debitur
                            long xxxx = PstLogSysHistory.deleteHistorySegment(periode.getOID(),"Debitur.jsp");
                        } else if (type == 2) { //kredit
                            long xxxx = PstLogSysHistory.deleteHistorySegment(periode.getOID(),"Kredit.jsp");
                        } else if (type == 3) { //agunan
                            long xxxx = PstLogSysHistory.deleteHistorySegment(periode.getOID(),"Agunan.jsp");
                        } else if (type == 4) { //bank garansi
                            long xxxx = PstLogSysHistory.deleteHistorySegment(periode.getOID(),"Bank Garansi.jsp");
                        } else if (type == 5) { //pengurus
                        }
                    }
                
                }
            }
        return periodeId;
    }

    public Date getHostPeriodeDate() {
        Date action = new Date();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //createConn();
            /*carikan querynya*/
            OutletConnection outletConnection = getConfigurasiConnection();
            String sql = ""
                    + "SELECT "+outletConnection.getDbDatabaseBackup()+".SYSTEM_HOST.OPEN_DATE FROM "+outletConnection.getDbDatabaseBackup()+".SYSTEM_HOST";
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                try {
                    action = rs.getDate("OPEN_DATE");
                } catch (Exception es) {
                    System.out.print("Eror loh");
                }
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        return action;
    }
    
    
    
     public int getDataCabangBank() {
        int action =0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //createConn();
            /*carikan querynya*/
            String sql = ""
                    + "SELECT * FROM TBL_CABANG ORDER BY KD_CAB ASC";
            OutletConnection outletConnection = getConfigurasiConnection();
            
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            Bank bank = getBank();
            int nourut=0;
            while (rs.next()) {
                try {
                    //cek eksitensi cabang
                    CabangBank cabangBank = new CabangBank();
                    cabangBank.setKodeCabang(rs.getString("KD_CAB"));
                    cabangBank.setNamaCabang(rs.getString("URAIAN"));
                    cabangBank.setBankId(bank.getOID());
                    cabangBank.setNamaBank(""+bank.getNamaBank());
                    cabangBank.setAlamatCabang(rs.getString("ALAMAT"));
                    cabangBank.setNamaKota(rs.getString("NAMA_KOTA"));
                    nourut=nourut+1;
                    cabangBank.setNoUrut(nourut);
                    try{
                        long checkKreditId = PstCabangBank.checkCabangBank(cabangBank.getKodeCabang());
                        if(checkKreditId!=0){
                                cabangBank.setOID(checkKreditId);
                                long xxx = PstCabangBank.updateExc(cabangBank);
                        }else{
                                long xxx = PstCabangBank.insertExc(cabangBank);
                        }
                    }catch(Exception ex){
                    }
                } catch (Exception es) {
                    System.out.print("Eror loh");
                }
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        return action;
    }
     
     
    public String getDataUserBank(String kd_user) {
        String action ="";
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //createConn();
            /*carikan querynya*/
            OutletConnection outletConnection = getConfigurasiConnection();
            String sql = ""
                    + "SELECT TRIM("+outletConnection.getDbDatabase()+".USER_LIST.NAMA_USER) AS NAMA_USER, TRIM("+outletConnection.getDbDatabase()+".USER_LIST.KD_CAB) AS KD_CAB, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.PASSWORD) AS PASSWORD, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.KD_USER) AS KD_USER, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.WWN_GROUP_1) AS WWN_GROUP_1, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.WWN_GROUP_2) AS WWN_GROUP_2, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.WWN_GROUP_3) AS WWN_GROUP_3 "
                    + " FROM "+outletConnection.getDbDatabase()+".USER_LIST ";
            
            if(!kd_user.equals("")){
                sql=sql+ " WHERE "+outletConnection.getDbDatabase()+".USER_LIST.KD_USER='"+kd_user+"'";
                sql=sql+" AND (WWN_GROUP_1='01' OR WWN_GROUP_2='01' OR WWN_GROUP_3='01' OR " +
                        " WWN_GROUP_1='03' OR WWN_GROUP_2='03' OR WWN_GROUP_3='03' OR " +
                        " WWN_GROUP_1='05' OR WWN_GROUP_2='05' OR WWN_GROUP_3='05' OR " +
                        " WWN_GROUP_1='27' OR WWN_GROUP_2='27' OR WWN_GROUP_3='27')";
            }else{
                sql=sql+ " WHERE 1=1 ";
                sql=sql+" AND (WWN_GROUP_1='01' OR WWN_GROUP_2='01' OR WWN_GROUP_3='01' OR " +
                        " WWN_GROUP_1='03' OR WWN_GROUP_2='03' OR WWN_GROUP_3='03' OR " +
                        " WWN_GROUP_1='05' OR WWN_GROUP_2='05' OR WWN_GROUP_3='05' OR " +
                        " WWN_GROUP_1='27' OR WWN_GROUP_2='27' OR WWN_GROUP_3='27')";
            }
            
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            Bank bank = getBank();
            while (rs.next()) {
                try {
                    AppUser appUser = new AppUser();
                    appUser.setFullName(rs.getString("NAMA_USER"));
                    appUser.setKdCabangBank(rs.getString("KD_CAB"));
                    appUser.setPassword(rs.getString("PASSWORD"));
                    appUser.setLoginId(rs.getString("KD_USER"));
                    appUser.setUserGroupNew(0);
                    String wwn_group_1 = rs.getString("WWN_GROUP_1");
                    if(wwn_group_1==null){
                        wwn_group_1="";
                    }
                    String wwn_group_2 = rs.getString("WWN_GROUP_2");
                    if(wwn_group_2==null){
                        wwn_group_2="";
                    }
                    String wwn_group_3 = rs.getString("WWN_GROUP_2");
                    if(wwn_group_3==null){
                        wwn_group_3="";
                    }
                    action = appUser.getPassword();
                    try{
                        long checkKreditId = PstAppUser.checkUser(appUser.getLoginId());
                        if(checkKreditId!=0){
                                Vector userGroup = new Vector();
                                if(wwn_group_1.equals("01") || wwn_group_2.equals("01") || wwn_group_3.equals("01")){
                                     userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564022521");
                                } else if(wwn_group_1.equals("03") || wwn_group_2.equals("03") || wwn_group_3.equals("03")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564022521");
                                } else if(wwn_group_1.equals("05") || wwn_group_2.equals("05") || wwn_group_3.equals("05")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404642283570401");
                                }else if(wwn_group_1.equals("27") || wwn_group_2.equals("27") || wwn_group_3.equals("27")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564075121");
                                    appUser.setUserGroupNew(0);
                                    appUser.setKdCabangBank("");
                                }
                                appUser.setOID(checkKreditId);
                                long xxx = PstAppUser.update(appUser);
                                if (SessAppUser.setUserGroup(checkKreditId, userGroup)){
                                }else {
                                }
                        }else{
                                Vector userGroup = new Vector();
                                if(wwn_group_1.equals("01") || wwn_group_2.equals("01") || wwn_group_3.equals("01")){
                                     userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564022521");
                                } else if(wwn_group_1.equals("03") || wwn_group_2.equals("03") || wwn_group_3.equals("03")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564022521");
                                } else if(wwn_group_1.equals("05") || wwn_group_2.equals("05") || wwn_group_3.equals("05")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404642283570401");
                                }else if(wwn_group_1.equals("27") || wwn_group_2.equals("27") || wwn_group_3.equals("27")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564075121");
                                    appUser.setUserGroupNew(0);
                                    appUser.setKdCabangBank("");
                                }
                                appUser.setOID(checkKreditId);
                                long xxx = PstAppUser.insert(appUser);
                                if (SessAppUser.setUserGroup(checkKreditId, userGroup)){
                                }else {
                                }
                        }
                    }catch(Exception ex){
                    }
                } catch (Exception es) {
                    System.out.print("Eror loh");
                }
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        return action;
    } 
    
    
    public String getDataPasswordUserBank(String kd_user) {
        String action ="";
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //createConn();
            /*carikan querynya*/
            OutletConnection outletConnection = getConfigurasiConnection();
            String sql = ""
                    + "SELECT TRIM("+outletConnection.getDbDatabase()+".USER_LIST.NAMA_USER) AS NAMA_USER, TRIM("+outletConnection.getDbDatabase()+".USER_LIST.KD_CAB) AS KD_CAB, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.PASSWORD) AS PASSWORD, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.KD_USER) AS KD_USER, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.WWN_GROUP_1) AS WWN_GROUP_1, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.WWN_GROUP_2) AS WWN_GROUP_2, "
                    + " TRIM("+outletConnection.getDbDatabase()+".USER_LIST.WWN_GROUP_3) AS WWN_GROUP_3 "
                    + " FROM "+outletConnection.getDbDatabase()+".USER_LIST ";
            
            if(!kd_user.equals("")){
                sql=sql+ " WHERE "+outletConnection.getDbDatabase()+".USER_LIST.KD_USER='"+kd_user+"'";
                sql=sql+" AND ( "+ 
                        " WWN_GROUP_1='01' OR WWN_GROUP_2='01' OR WWN_GROUP_3='01' OR " +
                        " WWN_GROUP_1='03' OR WWN_GROUP_2='03' OR WWN_GROUP_3='03' OR " +
                        " WWN_GROUP_1='05' OR WWN_GROUP_2='05' OR WWN_GROUP_3='05' OR " +
                        " WWN_GROUP_1='27' OR WWN_GROUP_2='27' OR WWN_GROUP_3='27' OR " +
                        " WWN_GROUP_1='98' OR WWN_GROUP_2='98' OR WWN_GROUP_3='98' " +
                        ")";
            }else{
                sql=sql+ " WHERE 1=1 ";
                sql=sql+" AND ( "+ 
                        " WWN_GROUP_1='01' OR WWN_GROUP_2='01' OR WWN_GROUP_3='01' OR " +
                        " WWN_GROUP_1='03' OR WWN_GROUP_2='03' OR WWN_GROUP_3='03' OR " +
                        " WWN_GROUP_1='05' OR WWN_GROUP_2='05' OR WWN_GROUP_3='05' OR " +
                        " WWN_GROUP_1='27' OR WWN_GROUP_2='27' OR WWN_GROUP_3='27' OR " +
                        " WWN_GROUP_1='98' OR WWN_GROUP_2='98' OR WWN_GROUP_3='98' " +
                        " )";
            }
            
            Class.forName(outletConnection.getDbdriver()).newInstance();
            conn = DriverManager.getConnection(outletConnection.getDburl(), outletConnection.getDbuser(), outletConnection.getDbpasswd());
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            Bank bank = getBank();
            boolean wewenang=false;
            while (rs.next()) {
                try {
                    AppUser appUser = new AppUser();
                    appUser.setFullName(rs.getString("NAMA_USER"));
                    appUser.setKdCabangBank(rs.getString("KD_CAB"));
                    appUser.setPassword(rs.getString("PASSWORD"));
                    appUser.setLoginId(rs.getString("KD_USER"));
                    appUser.setUserGroupNew(0);
                    String wwn_group_1 = rs.getString("WWN_GROUP_1");
                    if(wwn_group_1==null){
                        wwn_group_1="";
                    }
                    String wwn_group_2 = rs.getString("WWN_GROUP_2");
                    if(wwn_group_2==null){
                        wwn_group_2="";
                    }
                    String wwn_group_3 = rs.getString("WWN_GROUP_3");
                    if(wwn_group_3==null){
                        wwn_group_3="";
                    }
                    action = appUser.getPassword();
                    try{
                        long checkKreditId = PstAppUser.checkUser(appUser.getLoginId());
                        if(checkKreditId!=0){
                                Vector userGroup = new Vector();
                                if(wwn_group_1.equals("98") || wwn_group_2.equals("98") || wwn_group_3.equals("98")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404647643339319");
                                    wewenang=true;
                                }else if(wwn_group_1.equals("01") || wwn_group_2.equals("01") || wwn_group_3.equals("01")){
                                     userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564022521");
                                     wewenang=true;
                                } else if(wwn_group_1.equals("03") || wwn_group_2.equals("03") || wwn_group_3.equals("03")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564022521");
                                    wewenang=true;
                                } else if(wwn_group_1.equals("05") || wwn_group_2.equals("05") || wwn_group_3.equals("05")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404642283570401");
                                    wewenang=true;
                                }else if(wwn_group_1.equals("27") || wwn_group_2.equals("27") || wwn_group_3.equals("27")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564075121");
                                    appUser.setUserGroupNew(0);
                                    appUser.setKdCabangBank("");
                                    wewenang=true;
                                }
                                
                                appUser.setOID(checkKreditId);
                                long xxx = PstAppUser.update(appUser);
                                if (SessAppUser.setUserGroup(checkKreditId, userGroup)){
                                }else {
                                }
                        }else{
                                Vector userGroup = new Vector();
                                if(wwn_group_1.equals("98") || wwn_group_2.equals("98") || wwn_group_3.equals("98")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404647643339319");
                                    wewenang=true;
                                }else if(wwn_group_1.equals("01") || wwn_group_2.equals("01") || wwn_group_3.equals("01")){
                                     userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564022521");
                                     wewenang=true;
                                } else if(wwn_group_1.equals("03") || wwn_group_2.equals("03") || wwn_group_3.equals("03")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564022521");
                                    wewenang=true;
                                } else if(wwn_group_1.equals("05") || wwn_group_2.equals("05") || wwn_group_3.equals("05")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404642283570401");
                                    wewenang=true;
                                }else if(wwn_group_1.equals("27") || wwn_group_2.equals("27") || wwn_group_3.equals("27")){
                                    userGroup = FrmAppUser.getOtomatisUserGroup(checkKreditId,"504404644564075121");
                                    appUser.setUserGroupNew(0);
                                    appUser.setKdCabangBank("");
                                    wewenang=true;
                                }
                                appUser.setOID(checkKreditId);
                                long xxx = PstAppUser.insert(appUser);
                                if (SessAppUser.setUserGroup(checkKreditId, userGroup)){
                                }else {
                                }
                        }
                    }catch(Exception ex){
                    }
                } catch (Exception es) {
                    action="hakakses";
                }
            }
            
            if(action.equals("")){
                action="hakakses";
            }
            if(wewenang==false){
                action="hakakses";
            }
            
            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            action="databaseeror";
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
        }
        return action;
    } 
    
    public void insertHistory(long userID, String nameUser, int cmd, long oid, String cif, String segment, long PeriodeId) {
        try {
            Date dateLog = new  Date();
            LogSysHistory logSysHistory = new LogSysHistory();
            logSysHistory.setLogUserId(userID);
            logSysHistory.setLogLoginName(nameUser);
            logSysHistory.setLogApplication("DSlik");
            logSysHistory.setLogOpenUrl(segment+".jsp");
            logSysHistory.setLogUpdateDate(dateLog);
            logSysHistory.setLogDocumentType("Transfer Data "+segment);
            logSysHistory.setLogUserAction(Command.commandString[cmd]);
            logSysHistory.setLogDocumentNumber(""+PeriodeId);
            logSysHistory.setLogDocumentId(oid);
            try {
                logSysHistory.setLogDetail(""+cif);
            } catch (Exception e) {
                logSysHistory.setLogDetail("-");
            }
            
            logSysHistory.setQueryRoleBack("");
            
            long oid2 = PstLogSysHistory.insertLog(logSysHistory);
            
        } catch (Exception e) {

        }
    }
    
    
    public void requestEntityObjectSID(MappingDebiturBpd entDebitur, Connection connx, PreparedStatement stx, ResultSet rsx, String dbMasterBackup) {
        //Connection connxx = connx;
        PreparedStatement stxx = stx;
        ResultSet rsxx = rsx;
        try {
            String sql = "SELECT "+dbMasterBackup+".SID.LANGGAR_BMK, "+dbMasterBackup+".SID.LAMPAU_BMK, "+dbMasterBackup+".SID.NM_PERUSAHAAN, "+dbMasterBackup+".SID.TEMPAT_AKTE, "
                       + ""+dbMasterBackup+".SID.NO_AKTE_AWAL, "+dbMasterBackup+".SID.TGL_AKTE_AWAL, "+dbMasterBackup+".SID.NO_AKTE_AKHIR, "+dbMasterBackup+".SID.TGL_AKTE_AKHIR, "
                       + ""+dbMasterBackup+".SID.BIDANG_USAHA, "+dbMasterBackup+".SID.HUB_DGN_BANK, "+dbMasterBackup+".SID.GO_PUBLIC, "+dbMasterBackup+".SID.GOL_DEB, "+dbMasterBackup+".SID.RATING_USH_DEB, "
                       + ""+dbMasterBackup+".SID.LEMBAGA_PRINGKAT, "+dbMasterBackup+".SID.GROUP_DEB "
                       + ","+dbMasterBackup+".SID.PEKERJAAN_DEB "
                       + ","+dbMasterBackup+".SID.STS_GELAR "
                       + "FROM "+dbMasterBackup+".SID WHERE "+dbMasterBackup+".SID.NO_NSB='"+entDebitur.getCif()+"'";
            stxx = connx.prepareStatement(sql);
            rsxx = stxx.executeQuery();
            while (rsxx.next()) {
                
                if(rsxx.getString("langgar_bmk")!=null){
                    entDebitur.setMelanggarBmpk(rsxx.getString("langgar_bmk"));
                }
                if(rsxx.getString("lampau_bmk")!=null){
                    entDebitur.setMelampuiBmpk(rsxx.getString("lampau_bmk"));
                }
                
                try{
                    if(rsxx.getString("tempat_akte")!=null && !rsxx.getString("tempat_akte").equals("")){
                        entDebitur.setTempatPendirian(rsxx.getString("tempat_akte"));
                    }
                }catch(Exception ex){}
                
                try{
                    if(rsxx.getString("no_akte_awal")!=null && !rsxx.getString("no_akte_awal").equals("")){
                        entDebitur.setNoAkte(rsxx.getString("no_akte_awal"));
                    }
                }catch(Exception ex){}
                
                if(rsxx.getDate("tgl_akte_awal")!=null){
                    entDebitur.setTglAktePendirian(rsxx.getDate("tgl_akte_awal"));
                }
                
                try{
                    if(rsxx.getString("no_akte_akhir")!=null && !rsxx.getString("no_akte_akhir").equals("")){
                        entDebitur.setNoAktePerubahan(rsxx.getString("no_akte_akhir"));
                    }
                } catch(Exception ex){}
               
                
                if(rsxx.getDate("tgl_akte_akhir")!=null){
                    entDebitur.setTglAktePerubahan(rsxx.getDate("tgl_akte_akhir"));
                }
                
                if(rsxx.getString("bidang_usaha")!=null){
                    entDebitur.setKodeBidangUsaha(rsxx.getString("bidang_usaha"));
                    entDebitur.setKodeUsahaTempatBekerja(rsxx.getString("bidang_usaha"));
                }
                
                if(rsxx.getString("hub_dgn_bank")!=null){
                    entDebitur.setKodeHubLjk(rsxx.getString("hub_dgn_bank"));
                }
                
                if(rsxx.getString("go_public")!=null){
                    entDebitur.setGoPublic(rsxx.getString("go_public"));
                }
                
                if(rsxx.getString("gol_deb")!=null){
                    entDebitur.setKodeGol(rsxx.getString("gol_deb"));
                }
                
                if(rsxx.getString("rating_ush_deb")!=null){
                    entDebitur.setPeringkat(rsxx.getString("rating_ush_deb"));
                }
                
                if(rsxx.getString("lembaga_pringkat")!=null){
                    entDebitur.setLembagaPemeringkat(rsxx.getString("lembaga_pringkat"));
                }
                
                if(rsxx.getString("group_deb")!=null){
                    entDebitur.setNamaGroup(rsxx.getString("group_deb"));
                }
                
                if(rsxx.getString("hub_dgn_bank")!=null){
                    entDebitur.setKodeHub(rsxx.getString("hub_dgn_bank"));
                }
                
                if(rsxx.getString("pekerjaan_deb")!=null){
                    entDebitur.setKodePekerjaan(rsxx.getString("pekerjaan_deb"));
                }
                
                if(rsxx.getString("sts_gelar")!=null){
                    entDebitur.setKodeStatusGelar(rsxx.getString("sts_gelar"));
                    entDebitur.setKodeJenisUsaha(rsxx.getString("sts_gelar"));
                }
                
            }
        }catch (Exception e) {
        } finally {
            if (rsxx != null) {
                try {
                    rsxx.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
            if (stxx != null) {
                try {
                    stxx.close();
                } catch (SQLException e) {
                    /* ignored */
                }
            }
//            if (connxx != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    /* ignored */
//                }
//            }
        }
            
    }
    
    
    public void requestPrevEntityObject(MappingDebiturBpd entDebitur, long periodeIdx, String cif, MappingDebiturBpd prevEntDebitur) {
        try {
            Periode periode = new Periode();
            long prevPeriodeId=0;
            long oidDebitur=0;
            try{
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"='"+periodeIdx+"'", "");
                if(listPeriode != null){
                    periode = (Periode) listPeriode.get(0);
                    periode.getTglAwal().setMonth(periode.getTglAwal().getMonth()-1);
                    
                    Vector listPeriodex = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]+" BETWEEN '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd") +"' AND '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd")+"'", "");
                    if(listPeriodex != null){
                        if(listPeriodex.size()>0){
                            periode = (Periode) listPeriodex.get(0);
                            prevPeriodeId = periode.getOID();
                            oidDebitur = PstMappingDebiturBpd.checkDebitur(prevPeriodeId, cif);
                        }
                     }
                }
                 // entDebitur  = PstMappingDebiturBpd.fetchExc(oidDebitur);
                  ///individu
                  if(oidDebitur!=0){
                        if(entDebitur.getKodeJenisNsb()==1){
                            DebiturIndividu debiturIndividu  =  PstDebiturIndividu.fetchExc(oidDebitur);
                            PstMappingDebiturBpd.fetchExc(oidDebitur, prevEntDebitur);
                            entDebitur.setNamaPasangan(debiturIndividu.getNamaPasangan());
                            entDebitur.setNikPassportPasangan(debiturIndividu.getNikPasangan());
                            entDebitur.setTglLahirPasangan(debiturIndividu.getTglLahirPasangan());
                            entDebitur.setPerjanjianPisahHarta(debiturIndividu.getPerjanjianPisahHarga());
                            entDebitur.setStatusDataPelaporan(1);
                        }else{
                            //badan usaha
                            DebiturBdnUsaha debiturBdnUsaha  =  PstDebiturBdnUsaha.fetchExc(oidDebitur);
                            PstMappingDebiturBpd.fetchExc(oidDebitur, prevEntDebitur);
                            entDebitur.setTanggalPemeringkat(debiturBdnUsaha.getTglPemeringkat());
                            entDebitur.setStatusDataPelaporan(1);
                        }
                  }else{
                      entDebitur.setStatusDataPelaporan(0);
                  }
                  
            }catch(Exception ex){

            }
        }catch (Exception e) {
        } finally {
        }
            
    }
    
    public void requestPrevEntityObjectKredit(MappingKreditBpd entKredit, long periodeIdx, String noRekening, MappingKreditBpd prevEntKredit) {
        try {
            Periode periode = new Periode();
            long prevPeriodeId=0;
            long oidKredit=0;
            try{
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"='"+periodeIdx+"'", "");
                if(listPeriode != null){
                    periode = (Periode) listPeriode.get(0);
                    periode.getTglAwal().setMonth(periode.getTglAwal().getMonth()-1);
                    
                    Vector listPeriodex = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]+" BETWEEN '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd") +"' AND '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd")+"'", "");
                    if(listPeriodex != null){
                        if(listPeriodex.size()>0){
                            periode = (Periode) listPeriodex.get(0);
                            prevPeriodeId = periode.getOID();
                            oidKredit = PstMappingKreditBpd.checkKredit(prevPeriodeId, noRekening);
                        }
                     }
                }
                 // entDebitur  = PstMappingDebiturBpd.fetchExc(oidDebitur);
                  ///individu
                  if(oidKredit!=0){
                        PstMappingKreditBpd.fetchExc(oidKredit,prevEntKredit);
                        entKredit.setStatusDataPelaporan(1);
                  }else{
                      entKredit.setStatusDataPelaporan(0);
                  }
                  
            }catch(Exception ex){

            }
        }catch (Exception e) {
        } finally {
        }
            
    }
    
    
    public void requestPrevEntityObjectBankGaransi(MappingBankGaransiBpd entBankGaransi, long periodeIdx, String noRekening, MappingBankGaransiBpd prevEntBankGaransi) {
        try {
            Periode periode = new Periode();
            long prevPeriodeId=0;
            long oidKredit=0;
            try{
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"='"+periodeIdx+"'", "");
                if(listPeriode != null){
                    periode = (Periode) listPeriode.get(0);
                    periode.getTglAwal().setMonth(periode.getTglAwal().getMonth()-1);
                    
                    Vector listPeriodex = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]+" BETWEEN '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd") +"' AND '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd")+"'", "");
                    if(listPeriodex != null){
                        if(listPeriodex.size()>0){
                            periode = (Periode) listPeriodex.get(0);
                            prevPeriodeId = periode.getOID();
                            oidKredit = PstMappingBankGaransiBpd.checkBankGaransi(prevPeriodeId, noRekening);
                        }
                     }
                }
                  // entDebitur  = PstMappingDebiturBpd.fetchExc(oidDebitur);
                  if(oidKredit!=0){
                        PstMappingBankGaransiBpd.fetchExc(oidKredit, prevEntBankGaransi);
                        entBankGaransi.setStatusDataPelaporan(1);
                  }else{
                      entBankGaransi.setStatusDataPelaporan(0);
                  }
                  
            }catch(Exception ex){

            }
        }catch (Exception e) {
        } finally {
        }
            
    }
    
    
    public void requestPrevEntityObjectAgunan(MappingAgunanBpd entAgunan, long periodeIdx, String noRekening, String kdRegister, MappingAgunanBpd prevEntAgunan) {
        try {
            Periode periode = new Periode();
            long prevPeriodeId=0;
            long oidKredit=0;
            try{
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"='"+periodeIdx+"'", "");
                if(listPeriode != null){
                    periode = (Periode) listPeriode.get(0);
                    periode.getTglAwal().setMonth(periode.getTglAwal().getMonth()-1);
                    
                    Vector listPeriodex = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]+" BETWEEN '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd") +"' AND '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd")+"'", "");
                    if(listPeriodex != null){
                        if(listPeriodex.size()>0){
                            periode = (Periode) listPeriodex.get(0);
                            prevPeriodeId = periode.getOID();
                            oidKredit = PstMappingAgunanBpd.checkDataAgunan(prevPeriodeId, kdRegister, noRekening);
                        }
                     }
                }
                  // entDebitur  = PstMappingDebiturBpd.fetchExc(oidDebitur);
                  ///individu
                  if(oidKredit!=0){
                        PstMappingAgunanBpd.fetchExc(oidKredit, prevEntAgunan);
                        entAgunan.setStatusDataPelaporan(1);
                  }else{
                      entAgunan.setStatusDataPelaporan(0);
                  }
                  
            }catch(Exception ex){

            }
        }catch (Exception e) {
        } finally {
        }
            
    }
    
}
