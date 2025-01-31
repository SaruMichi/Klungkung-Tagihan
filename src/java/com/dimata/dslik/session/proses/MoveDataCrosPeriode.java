/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import com.dimata.dslik.entity.summaryfasilitas.SummaryFasilitas;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import static com.dimata.qdep.db.DBHandler.execUpdate;
import com.dimata.qdep.db.DBResultSet;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author dimata005
 */
public class MoveDataCrosPeriode {
    public static long moveAgunan(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "INSERT INTO dslik_agunan (AGUNAN_OID, FLAG_DETAIL, KODE_REGISTER_AGUNAN, NO_REKENING, CIF, KODE_STATUS_AGUNAN, KODE_JENIS_AGUNAN, PERINGKAT_AGUNAN, KODE_LEMBAGA_PEMERINGKAT, KODE_JENIS_PENGIKATAN, TGL_PENGIKATAN, NAMA_PEMILIK_AGUNAN, BUKTI_KEPEMILIKAN, ALAMAT_AGUNAN, KODE_KAB_LOKASI_AGUNAN, NILAI_AGUNAN_NJOP, NILAI_AGUNAN_LJK, TGL_PENILAIAN_LJK, NILAI_AGU_PENILAI_INDEP, NAMA_PENILAI_INDEP, TGL_PENILAIAN_PENILAI_INDEP, STATUS_PARIPASU, PROSENTASE_PARIPASU, STATUS_KREDIT_JOIN, DIASURANSIKAN, KETERANGAN, KODE_KANTOR_CABANG, OPERASI_DATA, OPEN_DATE, STATUS_DATA, PERIODE_ID, STATUS_OPERASI_DATA)"+
                " SELECT"+
                  " AGUNAN_OID + 111111111111111 AS AGUNAN_OID,"+
                  " FLAG_DETAIL,"+
                  " KODE_REGISTER_AGUNAN,"+
                  " NO_REKENING,"+
                  " CIF,"+
                  " KODE_STATUS_AGUNAN,"+
                  " KODE_JENIS_AGUNAN,"+
                  " PERINGKAT_AGUNAN,"+
                  " KODE_LEMBAGA_PEMERINGKAT,"+
                  " KODE_JENIS_PENGIKATAN,"+
                  " TGL_PENGIKATAN,"+
                  " NAMA_PEMILIK_AGUNAN,"+
                  " BUKTI_KEPEMILIKAN,"+
                  " ALAMAT_AGUNAN,"+
                  " KODE_KAB_LOKASI_AGUNAN,"+
                  " NILAI_AGUNAN_NJOP,"+
                  " NILAI_AGUNAN_LJK,"+
                  " TGL_PENILAIAN_LJK,"+
                  " NILAI_AGU_PENILAI_INDEP,"+
                  " NAMA_PENILAI_INDEP,"+
                  " TGL_PENILAIAN_PENILAI_INDEP,"+
                  " STATUS_PARIPASU,"+
                  " PROSENTASE_PARIPASU,"+
                  " STATUS_KREDIT_JOIN,"+
                  " DIASURANSIKAN,"+
                  " KETERANGAN,"+
                  " KODE_KANTOR_CABANG,"+
                  " OPERASI_DATA,"+
                  " OPEN_DATE,"+
                  " STATUS_DATA,"+
                  "'"+periodeId+"' AS PERIODE_ID,"+
                  " 1 AS STATUS_OPERASI_DATA"+
                " FROM dslik_agunan "+
                " WHERE PERIODE_ID = '"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    public static long moveBankGaransi(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_bank_garansi (BANK_GARANSI_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_GARANSI, KODE_TUJUAN_GARANSI, TGL_DITERBITKAN, TGL_JATUH_TEMPO, NO_AKAD_AWAL, TGL_AKAD_AWAL, NO_AKAD_AKHIR, TGL_AKAD_AKHIR, NAMA_YG_DIJAMIN, KODE_VALUTA, PLAFON, NOMINAL, SETORAN_JAMINAN, KODE_KOLEKTIBILITAS, TGL_WAN_PRESTASI, KODE_KONDISI, TGL_KONDISI, KETERANGAN, KODE_KANTOR_CABANG, OPERASI_DATA, OPEN_DATE, STATUS_DATA, PERIODE_ID, STATUS_OPERASI_DATA) "+
                "SELECT 	 "+
                      " BANK_GARANSI_OID + 111111111111111 AS BANK_GARANSI_OID, "+
                      " FLAG_DETAIL, "+
                      " NO_REKENING, "+
                      " CIF, "+
                      " KODE_JENIS_GARANSI, "+
                      " KODE_TUJUAN_GARANSI, "+
                      " TGL_DITERBITKAN, "+
                      " TGL_JATUH_TEMPO, "+
                      " NO_AKAD_AWAL, "+
                      " TGL_AKAD_AWAL, "+
                      " NO_AKAD_AKHIR, "+
                      " TGL_AKAD_AKHIR, "+
                      " NAMA_YG_DIJAMIN, "+
                      " KODE_VALUTA, "+
                      " PLAFON, "+
                      " NOMINAL, "+
                      " SETORAN_JAMINAN, "+
                      " KODE_KOLEKTIBILITAS, "+
                      " TGL_WAN_PRESTASI, "+
                      " KODE_KONDISI, "+
                      " TGL_KONDISI, "+
                      " KETERANGAN, "+
                      " KODE_KANTOR_CABANG, "+
                      " OPERASI_DATA, "+
                      " OPEN_DATE, "+
                      " STATUS_DATA, "+
                      "'"+periodeId+"' AS PERIODE_ID, "+
                      " 1 AS STATUS_OPERASI_DATA "+
                      " FROM dslik_bank_garansi "+
                " WHERE PERIODE_ID = '"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
    public static long moveDebitur(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_debitur (DEBITUR_OID, KODE_JENIS_NSB, FLAG_DETAIL, CIF, JENIS_IDENTITAS, NIK, NAMA_IDENTITAS, NAMA_LENGKAP, KODE_STATUS_GELAR, JEKEL, TEMPAT_LAHIR, TANGGAL_LAHIR, NPWP, ALAMAT, KELURAHAN, KECAMATAN, KODE_KAB, KODE_POS, TELEPON, NOMOR_HP, EMAIL, KODE_DOMISILI, KODE_PEKERJAAN, TEMPAT_BEKERJA, KODE_USAHA_TEMPAT_BEKERJA, ALAMAT_TEMPAT_BEKERJA, PENGHASILAN_KOTOR, KODE_PENGHASILAN, JML_TANGGUNGAN, KODE_HUB, KODE_GOL, STATUS, NIK_PASANGAN, NAMA_PASANGAN, TGL_LAHIR_PASANGAN, PERJANJIAN_PISAH_HARGA, MELANGGAR_BMPK, MELAMPAUI_BMPK, NAMA_IBU_KANDUNG, KODE_KANTOR_CABANG, NO_IDENTITAS, NAMA_BADAN_USAHA, KODE_JENIS_USAHA, TEMPAT_PENDIRIAN, NO_AKTE, TGL_AKTE_PENDIRIAN, NO_AKTE_PERUBAHAN, TGL_AKTE_PERUBAHAN, KODE_BIDANG_USAHA, KODE_HUB_LJK, GO_PUBLIC, PERINGKAT, LEMBAGA_PEMERINGKAT, TGL_PEMERINGKAT, NAMA_GROUP, OPERASI_DATA, OPEN_DATE, STATUS_DATA, PERIODE_ID, STATUS_OPERASI_DATA) "+
                " SELECT "+ 	
                      " DEBITUR_OID + 111111111111111 AS DEBITUR_OID, "+
                      " KODE_JENIS_NSB, "+
                      " FLAG_DETAIL, "+
                      " CIF, "+
                      " JENIS_IDENTITAS, "+
                      " NIK, "+
                      " NAMA_IDENTITAS, "+
                      " NAMA_LENGKAP, "+
                      " KODE_STATUS_GELAR, "+
                      " JEKEL, "+
                      " TEMPAT_LAHIR, "+
                      " TANGGAL_LAHIR, "+
                      " NPWP, "+
                      " ALAMAT, "+
                      " KELURAHAN, "+
                      " KECAMATAN, "+
                      " KODE_KAB, "+
                      " KODE_POS, "+
                      " TELEPON, "+
                      " NOMOR_HP, "+
                      " EMAIL, "+
                      " KODE_DOMISILI, "+
                      " KODE_PEKERJAAN, "+
                      " TEMPAT_BEKERJA, "+
                      " KODE_USAHA_TEMPAT_BEKERJA, "+
                      " ALAMAT_TEMPAT_BEKERJA, "+
                      " PENGHASILAN_KOTOR, "+
                      " KODE_PENGHASILAN, "+
                      " JML_TANGGUNGAN, "+
                      " KODE_HUB, "+
                      " KODE_GOL, "+
                      " STATUS, "+
                      " NIK_PASANGAN, "+
                      " NAMA_PASANGAN, "+
                      " TGL_LAHIR_PASANGAN, "+
                      " PERJANJIAN_PISAH_HARGA, "+
                      " MELANGGAR_BMPK, "+
                      " MELAMPAUI_BMPK, "+
                      " NAMA_IBU_KANDUNG, "+
                      " KODE_KANTOR_CABANG, "+
                      " NO_IDENTITAS, "+
                      " NAMA_BADAN_USAHA, "+
                      " KODE_JENIS_USAHA, "+
                      " TEMPAT_PENDIRIAN, "+
                      " NO_AKTE, "+
                      " TGL_AKTE_PENDIRIAN, "+
                      " NO_AKTE_PERUBAHAN, "+
                      " TGL_AKTE_PERUBAHAN, "+
                      " KODE_BIDANG_USAHA, "+
                      " KODE_HUB_LJK, "+
                      " GO_PUBLIC, "+
                      " PERINGKAT, "+
                      " LEMBAGA_PEMERINGKAT, "+
                      " TGL_PEMERINGKAT, "+
                      " NAMA_GROUP, "+
                      " OPERASI_DATA, "+
                      " OPEN_DATE, "+
                      " STATUS_DATA, "+
                      "'"+periodeId+"' AS PERIODE_ID, "+
                      " 1 AS STATUS_OPERASI_DATA "+
                " FROM dslik_debitur "+
                " WHERE PERIODE_ID = '"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
 
    
    public static long moveFasilitasLain(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                " insert into dslik_fasilitas_lain ( "+
     " FASILITAS_LAIN_OID, "+
     " FLAG_DETAIL, "+
     " NO_REKENING, "+
     " CIF, "+
     " KODE_JENIS_FASILITAS, "+
     " SUMBER_DANA, "+
     " TGL_MULAI, "+
     " TGL_JATUH_TEMPO, "+
     " SUKU_BUNGA, "+
     " KODE_VALUTA, "+
     " NOMINAL_JML_KEWAJIBAN, "+
     " NILAI_MATA_UANG_ASAL, "+
     " KODE_KOLEKTIBILITAS, "+
     " TGL_MACET, "+
     " KODE_SEBAB_MACET, "+
     " TUNGGAKAN, "+
     " JML_HARI_TUNGGAKAN, "+
     " KODE_KONDISI, "+
     " TGL_KONDISI, "+
     " KETERANGAN, "+
     " KODE_KANTOR_CABANG, "+
     " OPERASI_DATA, "+
     " OPEN_DATE, "+
     " STATUS_DATA, "+
     " PERIODE_ID, "+
     " STATUS_OPERASI_DATA ) "+
                        " SELECT  "+
     " FASILITAS_LAIN_OID + 9999999999 AS FASILITAS_LAIN_OID, "+
     " FLAG_DETAIL, "+
     " NO_REKENING, "+
     " CIF, "+
     " KODE_JENIS_FASILITAS, "+
     " SUMBER_DANA, "+
     " TGL_MULAI, "+
     " TGL_JATUH_TEMPO, "+
     " SUKU_BUNGA, "+
     " KODE_VALUTA, "+
     " NOMINAL_JML_KEWAJIBAN, "+
     " NILAI_MATA_UANG_ASAL, "+
     " KODE_KOLEKTIBILITAS, "+
     " TGL_MACET, "+
     " KODE_SEBAB_MACET, "+
     " TUNGGAKAN, "+
     " JML_HARI_TUNGGAKAN, "+
     " KODE_KONDISI, "+
     " TGL_KONDISI, "+
     " KETERANGAN, "+
     " KODE_KANTOR_CABANG, "+
     " OPERASI_DATA, "+
     " OPEN_DATE, "+
     " STATUS_DATA, "+
     "'"+periodeId+"' AS PERIODE_ID, "+
     " 1 AS STATUS_OPERASI_DATA "+
                        " FROM dslik_fasilitas_lain "+
                  " WHERE PERIODE_ID = '"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
    public static long moveKredit(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_kredit (KREDIT_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_SIFAT, KODE_JENIS_KREDIT, KODE_AKAD, NO_AKAD_AWAL, TGL_AKAD_AWAL, NO_AKAD_AKHIR, TGL_AKAD_AKHIR, BARU_PERPANJANGAN, TGL_AWAL, TGL_MULAI, TGL_TEMPO, KODE_KAT_DEBITUR, KODE_JENIS_PENGGUNAAN, KODE_ORIENTASI_PENGGUNAAN, KODE_SEKTOR_EKONOMI, KODE_KAB, NILAI, KODE_VALUTA, PROSENTASE_BUNGA, JENIS_BUNGA, KREDIT_PEMERINTAH, TAKEOVER, SUMBER_DANA, PLAFON_AWAL, PLAFON, REALISASI, DENDA, BAKI_DEBET, NILAI_UANG_ASAL, KODE_KOLEKTIBILITAS, TGL_MACET, KODE_SEBAB_MACET, TUNGGAKAN_POKOK, TUNGGAKAN_BUNGA, JML_HARI_TUNGGAKAN, FREKUENSI_TUNGGAKAN, FREKUENSI_RESTRUKTURISASI, TGL_RESTRUKTURISASI_AWAL, TGL_RESTRUKTURISASI_AKHIR, KODE_CARA, KODE_KONDISI, TGL_KONDISI, KETERANGAN, KODE_KANTOR_CABANG, OPERASI_DATA, OPEN_DATE, STATUS_DATA, PERIODE_ID, APPLID, STATUS_OPERASI_DATA) "+
                "SELECT "+ 	
                      " KREDIT_OID + 111111111111111 AS KREDIT_OID, "+
                      " FLAG_DETAIL, "+
                      " NO_REKENING, "+
                      " CIF, "+
                      " KODE_SIFAT, "+
                      " KODE_JENIS_KREDIT, "+
                      " KODE_AKAD, "+
                      " NO_AKAD_AWAL, "+
                      " TGL_AKAD_AWAL, "+
                      " NO_AKAD_AKHIR, "+
                      " TGL_AKAD_AKHIR, "+
                      " BARU_PERPANJANGAN, "+
                      " TGL_AWAL, "+
                      " TGL_MULAI, "+
                      " TGL_TEMPO, "+
                      " KODE_KAT_DEBITUR, "+
                      " KODE_JENIS_PENGGUNAAN, "+
                      " KODE_ORIENTASI_PENGGUNAAN, "+
                      " KODE_SEKTOR_EKONOMI, "+
                      " KODE_KAB, "+
                      " NILAI, "+
                      " KODE_VALUTA, "+
                      " PROSENTASE_BUNGA, "+
                      " JENIS_BUNGA, "+
                      " KREDIT_PEMERINTAH, "+
                      " TAKEOVER, "+
                      " SUMBER_DANA, "+
                      " PLAFON_AWAL, "+
                      " PLAFON, "+
                      " REALISASI, "+
                      " DENDA, "+
                      " BAKI_DEBET, "+
                      " NILAI_UANG_ASAL, "+
                      " KODE_KOLEKTIBILITAS, "+
                      " TGL_MACET, "+
                      " KODE_SEBAB_MACET, "+
                      " TUNGGAKAN_POKOK, "+
                      " TUNGGAKAN_BUNGA, "+
                      " JML_HARI_TUNGGAKAN, "+
                      " FREKUENSI_TUNGGAKAN, "+
                      " FREKUENSI_RESTRUKTURISASI, "+
                      " TGL_RESTRUKTURISASI_AWAL, "+
                      " TGL_RESTRUKTURISASI_AKHIR, "+
                      " KODE_CARA, "+
                      " KODE_KONDISI, "+
                      " TGL_KONDISI, "+
                      " KETERANGAN, "+
                      " KODE_KANTOR_CABANG, "+
                      " OPERASI_DATA, "+
                      " OPEN_DATE, "+
                      " STATUS_DATA, "+
                      "'"+periodeId+"' AS PERIODE_ID, "+
                      " APPLID, "+
                      " 1 AS STATUS_OPERASI_DATA  "+
                " FROM dslik_kredit "+
                " WHERE PERIODE_ID = '"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
    public static long movePengurusPemilik(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_pengurus_atau_pemilik (PENGURUS_OID, FLAG_DETAIL, NO_IDENTITAS, CIF, JENIS_IDENTITAS, NAMA_PENGURUS, JENIS_KELAMIN, ALAMAT, KELURAHAN, KECAMATAN, KODE_KABUPATEN, KODE_JABATAN, PANGSA_KEPEMILIKAN, STATUS_PENGURUS, KODE_KANTOR_CABANG, OPERASI_DATA, OPEN_DATE, STATUS_DATA, PERIODE_ID, STATUS_OPERASI_DATA) "+
                " SELECT "+	
                      " PENGURUS_OID + 111111111111111 AS PENGURUS_OID, "+
                      " FLAG_DETAIL, "+
                      " NO_IDENTITAS, "+
                      " CIF, "+
                      " JENIS_IDENTITAS, "+
                      " NAMA_PENGURUS, "+
                      " JENIS_KELAMIN, "+
                      " ALAMAT, "+
                      " KELURAHAN, "+
                      " KECAMATAN, "+
                      " KODE_KABUPATEN, "+
                      " KODE_JABATAN, "+
                      " PANGSA_KEPEMILIKAN, "+
                      " STATUS_PENGURUS, "+
                      " KODE_KANTOR_CABANG, "+
                      " OPERASI_DATA, "+
                      " OPEN_DATE, "+
                      " STATUS_DATA, "+
                      "'"+periodeId+"' AS PERIODE_ID, "+
                      " 1 AS STATUS_OPERASI_DATA "+
                " FROM dslik_pengurus_atau_pemilik "+
                " WHERE PERIODE_ID = '"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    public static long moveIrrevocable(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_irrevocable_lc "+
                "("+
                      "IRREVOCABLE_LC, "+
                      "FLAG_DETAIL, "+
                      "NO_REKENING, "+
                      "CIF, "+
                      "KODE_JENIS, "+
                      "KODE_TUJUAN, "+
                      "TGL_KELUAR, "+
                      "TGL_JATUH_TEMPO, "+
                      "NO_AKAD_AWAL, "+
                      "TGL_AKAD_AWAL, "+
                      "NO_AKAD_AKHIR, "+
                      "TGL_AKAD_AKHIR, "+
                      "BANK_COUNTERPARTY, "+
                      "KODE_VALUTA, "+
                      "PLAFON, "+
                      "NOMINAL, "+
                      "SETORAN_JAMINAN, "+
                      "KODE_KOLEKTIBILITAS, "+
                      "TGL_WAN_PRESTASI, "+
                      "KODE_KONDISI, "+
                      "TGL_KONDISI, "+
                      "KETERANGAN, "+
                      "KODE_KANTOR_CABANG, "+
                      "OPERASI_DATA, "+
                      "OPEN_DATE, "+
                      "STATUS_DATA, "+
                      "PERIODE_ID,"+ 
                      "STATUS_OPERASI_DATA) "+
                "SELECT  "+
                      
                      "IRREVOCABLE_LC + 111111111111111 AS IRREVOCABLE_LC, "+  
                      "FLAG_DETAIL, "+
                      "NO_REKENING, "+
                      "CIF, "+
                      "KODE_JENIS, "+
                      "KODE_TUJUAN, "+
                      "TGL_KELUAR, "+
                      "TGL_JATUH_TEMPO, "+
                      "NO_AKAD_AWAL, "+
                      "TGL_AKAD_AWAL, "+
                      "NO_AKAD_AKHIR, "+
                      "TGL_AKAD_AKHIR, "+
                      "BANK_COUNTERPARTY, "+
                      "KODE_VALUTA, "+
                      "PLAFON, "+
                      "NOMINAL, "+
                      "SETORAN_JAMINAN, "+
                      "KODE_KOLEKTIBILITAS, "+
                      "TGL_WAN_PRESTASI, "+
                      "KODE_KONDISI, "+
                      "TGL_KONDISI, "+
                      "KETERANGAN, "+
                      "KODE_KANTOR_CABANG, "+
                      "OPERASI_DATA, "+
                      "OPEN_DATE, "+
                      "STATUS_DATA, "+
                      "'"+periodeId+"' AS PERIODE_ID, "+ 
                      "1 AS STATUS_OPERASI_DATA "+ 
                      "FROM dslik_irrevocable_lc  WHERE PERIODE_ID = '"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
    public static long moveKreditJoin(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                " insert into dslik_kredit_join_account "+
                " ( "+
                      " KREDIT_JOIN_ACNT_OID, "+
                      " FLAG_DETAIL, "+
                      " NO_REKENING, "+
                      " CIF, "+
                      " SEQUENCE_DEB_JOIN, "+
                      " KODE_SIFAT, "+
                      " KODE_JENIS, "+
                      " KODE_AKAD, "+
                      " NO_AKAD_AWAL, "+
                      " TGL_AKAD_AWAL, "+
                      " NO_AKAD_AKHIR, "+
                      " TGL_AKAD_AKHIR, "+
                      " BARU_PERPANJANGAN, "+
                      " TGL_AWAL_KREDIT, "+
                      " TGL_MULAI, "+
                      " TGL_JATUH_TEMPO, "+
                      " KODE_KAT_DEB, "+
                      " KODE_JENIS_PENGGUNAAN, "+
                      " KODE_ORIENTASI_PENGGUNAAN, "+
                      " KODE_SEKTOR_EKONOMI, "+
                      " KODE_KAB, "+
                      " NILAI_PROYEK, "+
                      " KODE_VALUTA, "+
                      " PROSENTASE_BUNGA, "+
                      " JENIS_BUNGA, "+
                      " KREDIT_PROGRAM_PEMERINTAH, "+
                      " TAKEOVER, "+
                      " SUMBER_DANA, "+
                      " PLAFON_AWAL, "+
                      " PLAFON, "+
                      " REALISASI, "+
                      " DENDA, "+
                      " BAKI_DEBET, "+
                      " NILAI_UANG_ASAL, "+
                      " KODE_KOLEKTIBILITAS, "+
                      " TGL_MACET, "+
                      " KODE_SEBAB_MACET, "+
                      " TUNGGAKAN_POKOK, "+
                      " TUNGGAKAN_BUNGA, "+
                      " JML_HARI_TUNGGAKAN, "+
                      " FREKUENSI_TUNGGAKAN, "+
                      " FREKUENSI_RESTRUKTURISASI, "+
                      " TGL_RESTRUKTURISASI_AWAL, "+
                      " TGL_RESTRUKTURISASI_AKHIR, "+
                      " KODE_KONDISI, "+
                      " KODE_CARA, "+
                      " TGL_KONDISI, "+
                      " KETERANGAN, "+
                      " KODE_KANTOR_CABANG, "+
                      " OPERASI_DATA, "+
                      " OPEN_DATE, "+
                      " STATUS_DATA, "+
                      " PERIODE_ID, "+
                      " STATUS_OPERASI_DATA "+
              " ) "+
              " SELECT "+
                      " KREDIT_JOIN_ACNT_OID + 111111111111111 AS KREDIT_JOIN_ACNT_OID, "+
                      " FLAG_DETAIL, "+
                      " NO_REKENING, "+
                      " CIF, "+
                      " SEQUENCE_DEB_JOIN, "+
                      " KODE_SIFAT, "+
                      " KODE_JENIS, "+
                      " KODE_AKAD, "+
                      " NO_AKAD_AWAL, "+
                      " TGL_AKAD_AWAL, "+
                      " NO_AKAD_AKHIR, "+
                      " TGL_AKAD_AKHIR, "+
                      " BARU_PERPANJANGAN, "+
                      " TGL_AWAL_KREDIT, "+
                      " TGL_MULAI, "+
                      " TGL_JATUH_TEMPO, "+
                      " KODE_KAT_DEB, "+
                      " KODE_JENIS_PENGGUNAAN, "+
                      " KODE_ORIENTASI_PENGGUNAAN, "+
                      " KODE_SEKTOR_EKONOMI, "+
                      " KODE_KAB, "+
                      " NILAI_PROYEK, "+
                      " KODE_VALUTA, "+
                      " PROSENTASE_BUNGA, "+
                      " JENIS_BUNGA, "+
                      " KREDIT_PROGRAM_PEMERINTAH, "+
                      " TAKEOVER, "+
                      " SUMBER_DANA, "+
                      " PLAFON_AWAL, "+
                      " PLAFON, "+
                      " REALISASI, "+
                      " DENDA, "+
                      " BAKI_DEBET, "+
                      " NILAI_UANG_ASAL, "+
                      " KODE_KOLEKTIBILITAS, "+
                      " TGL_MACET, "+
                      " KODE_SEBAB_MACET, "+
                      " TUNGGAKAN_POKOK, "+
                      " TUNGGAKAN_BUNGA, "+
                      " JML_HARI_TUNGGAKAN, "+
                      " FREKUENSI_TUNGGAKAN, "+
                      " FREKUENSI_RESTRUKTURISASI, "+
                      " TGL_RESTRUKTURISASI_AWAL, "+
                      " TGL_RESTRUKTURISASI_AKHIR, "+
                      " KODE_KONDISI, "+
                      " KODE_CARA, "+
                      " TGL_KONDISI, "+
                      " KETERANGAN, "+
                      " KODE_KANTOR_CABANG, "+
                      " OPERASI_DATA, "+
                      " OPEN_DATE, "+
                      " STATUS_DATA, "+
                      "'"+periodeId+"' AS PERIODE_ID, "+
                      " 1 AS STATUS_OPERASI_DATA  "+
              " FROM  "+
              " dslik_kredit_join_account WHERE PERIODE_ID='"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
    public static long moveLapKeuangan(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_laporan_keuangan_debitur "+
                "( "+
                        " LAP_KEU_DEB_OID, "+
                        " FLAG_DETAIL, "+
                        " CIF, "+
                        " ASET, "+
                        " ASET_LANCAR, "+
                        " KAS_DAN_SETARA_KAS, "+
                        " PIUTANG_USAHA_AST_LNCR, "+
                        " INVESTASI_AST_LNCR, "+
                        " ASET_LANCAR_LAIN, "+
                        " ASET_TIDAK_LANCAR, "+
                        " PIUTANG_USAHA_AST_TDK_LNCR, "+
                        " INVESTASI_AST_TDK_LNCR, "+
                        " ASET_TDK_LNCR_LAIN, "+
                        " LIABILITAS, "+
                        " LIABILITAS_JNK_PNDK, "+
                        " PINJAMAN_JNK_PNDK, "+
                        " UTANG_USAHA_LIA_JNK_PNDK, "+
                        " LIABILITAS_JNK_PNDK_LAIN, "+
                        " LIABILITAS_JNK_PNJG, "+
                        " PINJAMAN_JNK_PNJG, "+
                        " UTANG_USAHA_LIA_JNK_PNJG, "+
                        " LIABILITAS_JNK_PNJG_LAIN, "+
                        " EKUITAS, "+
                        " PENDAPATAN_USAHA_OPR, "+
                        " BEBAN_POKOK_PEND, "+
                        " LABA_RUGI_BRUTO, "+
                        " PEND_LAIN_NON_OPR, "+
                        " BEBAN_LAIN_NON_OPR, "+
                        " LABA_RUGI_SBLM_PAJAK, "+
                        " LABA_RUGI_PERIODE, "+
                        " KODE_KANTOR_CABANG, "+
                        " OPERASI_DATA, "+
                        " OPEN_DATE, "+
                        " STATUS_DATA, "+
                        " PERIODE_ID, "+
                        " STATUS_OPERASI_DATA "+
                " ) "+
                " SELECT "+
                        " LAP_KEU_DEB_OID + 111111111111111 AS LAP_KEU_DEB_OID, "+
                        " FLAG_DETAIL, "+
                        " CIF, "+
                        " ASET, "+
                        " ASET_LANCAR, "+
                        " KAS_DAN_SETARA_KAS, "+
                        " PIUTANG_USAHA_AST_LNCR, "+
                        " INVESTASI_AST_LNCR, "+
                        " ASET_LANCAR_LAIN, "+
                        " ASET_TIDAK_LANCAR, "+
                        " PIUTANG_USAHA_AST_TDK_LNCR, "+
                        " INVESTASI_AST_TDK_LNCR, "+
                        " ASET_TDK_LNCR_LAIN, "+
                        " LIABILITAS, "+
                        " LIABILITAS_JNK_PNDK, "+
                        " PINJAMAN_JNK_PNDK, "+
                        " UTANG_USAHA_LIA_JNK_PNDK, "+
                        " LIABILITAS_JNK_PNDK_LAIN, "+
                        " LIABILITAS_JNK_PNJG, "+
                        " PINJAMAN_JNK_PNJG, "+
                        " UTANG_USAHA_LIA_JNK_PNJG, "+
                        " LIABILITAS_JNK_PNJG_LAIN, "+
                        " EKUITAS, "+
                        " PENDAPATAN_USAHA_OPR, "+
                        " BEBAN_POKOK_PEND, "+
                        " LABA_RUGI_BRUTO, "+
                        " PEND_LAIN_NON_OPR, "+
                        " BEBAN_LAIN_NON_OPR, "+
                        " LABA_RUGI_SBLM_PAJAK, "+
                        " LABA_RUGI_PERIODE, "+
                        " KODE_KANTOR_CABANG, "+
                        " OPERASI_DATA, "+
                        " OPEN_DATE, "+
                        " STATUS_DATA, "+
                        "'"+periodeId+"' AS PERIODE_ID, "+
                        " 1 AS STATUS_OPERASI_DATA "+
                " FROM dslik_laporan_keuangan_debitur WHERE PERIODE_ID='"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
    public static long movePenjamin(long prevPeriode, long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_penjamin "+
                "( "+
                        "PENJAMIN_OID, "+
                        "FLAG_DETAIL, "+
                        "NO_ID_PENJAMIN, "+
                        "NO_REKENING, "+
                        "CIF, "+
                        "JENIS_IDENTITAS, "+
                        "NAMA_IDENTITAS, "+
                        "NAMA_LENGKAP, "+
                        "KODE_GOL_PENJAMIN, "+
                        "ALAMAT_PENJAMIN, "+
                        "PROSENTASE_DIJAMIN, "+
                        "KETERANGAN, "+
                        "KODE_KANTOR_CABANG, "+
                        "OPERASI_DATA, "+
                        "OPEN_DATE, "+
                        "STATUS_DATA, "+
                        "PERIODE_ID, "+
                        "STATUS_OPERASI_DATA "+
                ") "+
                "SELECT  "+
                        "PENJAMIN_OID + 111111111111111 AS PENJAMIN_OID, "+
                        "FLAG_DETAIL, "+
                        "NO_ID_PENJAMIN, "+
                        "NO_REKENING, "+
                        "CIF, "+
                        "JENIS_IDENTITAS, "+
                        "NAMA_IDENTITAS, "+
                        "NAMA_LENGKAP, "+
                        "KODE_GOL_PENJAMIN, "+
                        "ALAMAT_PENJAMIN, "+
                        "PROSENTASE_DIJAMIN, "+
                        "KETERANGAN, "+
                        "KODE_KANTOR_CABANG, "+
                        "OPERASI_DATA, "+
                        "OPEN_DATE, "+
                        "STATUS_DATA, "+
                        "'"+periodeId+"' AS PERIODE_ID, "+
                        "1 AS STATUS_OPERASI_DATA "+
                "FROM dslik_penjamin WHERE PERIODE_ID='"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
    public static long moveDataSummaryFasilitasKredit(long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                        + "SELECT DISTINCT  "+ 
                        " KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " +
                        " 'D' AS FLAG_DETAIL,  " +
                        " TRIM(dslik_kredit.NO_REKENING) AS NO_REKENING,  " +
                        " TRIM(dslik_kredit.CIF) AS CIF,  " +
                        " 'F01' AS KODE_JENIS_FASILITAS,  " +
                        " dslik_kredit.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_1,  " +
                        " dslik_kredit.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_1, " +
                        " 0 AS KODE_KOLEKTIBLITAS_2, " +
                        " 0 AS JML_HARI_TUNGGAKAN_2, " +
                        " 0 AS KODE_KOLEKTIBILITAS_3, " +
                        " 0 AS JML_HARI_TUNGGAKAN_3, " +
                        " 0 AS KODE_KOLEKTIBILITAS_4, " +
                        " 0 AS JML_HARI_TUNGGAKAN_4, " +
                        " 0 AS KODE_KOLEKTIBILITAS_5, " +
                        " 0 AS JML_HARI_TUNGGAKAN_5, " +
                        " 0 AS KODE_KOLEKTIBILITAS_6, " +
                        " 0 AS JML_HARI_TUNGGAKAN_6, " +
                        " 0 AS KODE_KOLEKTIBILITAS_7, " +
                        " 0 AS JML_HARI_TUNGGAKAN_7, " +
                        " 0 AS KODE_KOLEKTIBILITAS_8, " +
                        " 0 AS JML_HARI_TUNGGAKAN_8, " +
                        " 0 AS KODE_KOLEKTIBILITAS_9, " +
                        " 0 AS JML_HARI_TUNGGAKAN_9, " +
                        " 0 AS KODE_KOLEKTIBILITAS_10, " +
                        " 0 AS JML_HARI_TUNGGAKAN_10, " +
                        " 0 AS KODE_KOLEKTIBILITAS_11, " +
                        " 0 AS JML_HARI_TUNGGAKAN_11, " +
                        " 0 AS KODE_KOLEKTIBILITAS_12, " +
                        " 0 AS JML_HARI_TUNGGAKAN_12 " +
                        " FROM dslik_kredit INNER JOIN  " +
                        " dslik_debitur ON dslik_kredit.CIF=dslik_debitur.CIF " +
                        " WHERE dslik_kredit.PERIODE_ID='"+periodeId+"'";
//                      " PENGURUS_OID + 111111111111111 AS PENGURUS_OID, "+
//                      " FLAG_DETAIL, "+
//                      " NO_IDENTITAS, "+
//                      " CIF, "+
//                      " JENIS_IDENTITAS, "+
//                      " NAMA_PENGURUS, "+
//                      " JENIS_KELAMIN, "+
//                      " ALAMAT, "+
//                      " KELURAHAN, "+
//                      " KECAMATAN, "+
//                      " KODE_KABUPATEN, "+
//                      " KODE_JABATAN, "+
//                      " PANGSA_KEPEMILIKAN, "+
//                      " STATUS_PENGURUS, "+
//                      " KODE_KANTOR_CABANG, "+
//                      " OPERASI_DATA, "+
//                      " OPEN_DATE, "+
//                      " STATUS_DATA, "+
//                      "'"+periodeId+"' AS PERIODE_ID, "+
//                      " 1 AS STATUS_OPERASI_DATA "+
//                " FROM dslik_pengurus_atau_pemilik "+
//                " WHERE PERIODE_ID = '"+prevPeriode+"'";
                
                int result = execUpdate(sql);
                
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    public static long moveDataSummaryBankGaransi(long periodeId) throws DBException {
        long hasil = 0;
        try {
            if(periodeId!=0){
                String sql =""+
                "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                        + "SELECT DISTINCT  " +
                        " BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                        + "'D' AS FLAG_DETAIL,  " +
                        " TRIM(dslik_bank_garansi.NO_REKENING) AS NO_REKENING,  " +
                        " TRIM(dslik_bank_garansi.CIF) AS CIF,  " +
                        " 'F05' AS KODE_JENIS_FASILITAS,  " +
                        " dslik_bank_garansi.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_1,  " +
                        " 0 AS JML_HARI_TUNGGAKAN_1, " +
                        " 0 AS KODE_KOLEKTIBLITAS_2, " +
                        " 0 AS JML_HARI_TUNGGAKAN_2, " +
                        " 0 AS KODE_KOLEKTIBILITAS_3, " +
                        " 0 AS JML_HARI_TUNGGAKAN_3, " +
                        " 0 AS KODE_KOLEKTIBILITAS_4, " +
                        " 0 AS JML_HARI_TUNGGAKAN_4, " +
                        " 0 AS KODE_KOLEKTIBILITAS_5, " +
                        " 0 AS JML_HARI_TUNGGAKAN_5, " +
                        " 0 AS KODE_KOLEKTIBILITAS_6, " +
                        " 0 AS JML_HARI_TUNGGAKAN_6, " +
                        " 0 AS KODE_KOLEKTIBILITAS_7, " +
                        " 0 AS JML_HARI_TUNGGAKAN_7, " +
                        " 0 AS KODE_KOLEKTIBILITAS_8, " +
                        " 0 AS JML_HARI_TUNGGAKAN_8, " +
                        " 0 AS KODE_KOLEKTIBILITAS_9, " +
                        " 0 AS JML_HARI_TUNGGAKAN_9, " +
                        " 0 AS KODE_KOLEKTIBILITAS_10, " +
                        " 0 AS JML_HARI_TUNGGAKAN_10, " +
                        " 0 AS KODE_KOLEKTIBILITAS_11, " +
                        " 0 AS JML_HARI_TUNGGAKAN_11, " +
                        " 0 AS KODE_KOLEKTIBILITAS_12, " +
                        " 0 AS JML_HARI_TUNGGAKAN_12 " +
                        " FROM dslik_bank_garansi INNER JOIN  " +
                        " dslik_debitur ON dslik_bank_garansi.CIF=dslik_debitur.CIF " +
                        " WHERE dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                
                int result = execUpdate(sql);
                
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
     public static long moveDataSummaryKreditPerPeriode(long periodeId, int urutan_ke, int totalData, int prosesData) throws DBException {
        long hasil = 0;
       // DBResultSet dbrs = null;
        int resultProses=0;
        try {
            if(periodeId!=0){
//                String sql =" SELECT 'D' AS FLAG_DETAIL, " +
//                            " TRIM(dslik_kredit.NO_REKENING) AS NO_REKENING, " +
//                            " TRIM(dslik_kredit.CIF) AS CIF, " +
//                            " 'F01' AS KODE_JENIS_FASILITAS, " +
//                            " dslik_kredit.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS, " +
//                            " dslik_kredit.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN "
//                          + " FROM dslik_kredit WHERE dslik_kredit.PERIODE_ID='"+periodeId+"'";
//                
//                int result = execUpdate(sql);
//                dbrs = DBHandler.execQueryResult(sql);
//                ResultSet rs = dbrs.getResultSet();
//                int resultProses=0;
//                int urutanProses=0;
//                while (rs.next()) {
//                    //cek apakah data cif sudah ada atau belum
//                    String cif = rs.getString("CIF");
//                    String noRekening = rs.getString("NO_REKENING");
//                    String kodeKolektibilitas=rs.getString("KODE_KOLEKTIBILITAS");
//                    int jmlHariTunggakan = rs.getInt("JML_HARI_TUNGGAKAN");
//                    String kodeJenisFasilitas="F01";
//                    boolean cekCifNoRekening =  PstSummaryFasilitas.checkOID(cif,noRekening,kodeJenisFasilitas);
//                    urutanProses=urutanProses+1;
//                    ManagerDilimitedTextSummary.setStatusSummaryPelaporanProsesTransferData("Proses Transfer Data "+urutanProses+" / "+totalData);
                    if(prosesData==0){ //update
                        //update
                        switch (urutan_ke) {
                             case 2:
                                  try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBLITAS_2='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_2='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBLITAS_2=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_2=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                  break;
                             case 3:
                                 try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBLITAS_3='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_3='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_3=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_3=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 4:
                                 try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_4='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_4='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_4=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_4=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 5:
                                 try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_5='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_5='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_5=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_5=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 6:
                                  try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_6='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_6='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_6=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_6=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 7:
                                 try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_7='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_7='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_7=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_7=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 8:
                                 try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_8='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_8='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_8=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_8=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 9:
                                 try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_9='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_9='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_9=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_9=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 10:
                                  try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_10='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_10='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_10=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_10=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 11:
                                 try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_11='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_11='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_11=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_11=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 12:
                                 try{
                                      //String sqlUpdateDua = " UPDATE dslik_summary_fasilitas SET KODE_KOLEKTIBILITAS_12='"+kodeKolektibilitas+"', JML_HARI_TUNGGAKAN_12='"+jmlHariTunggakan+"' WHERE CIF='"+cif+"' AND NO_REKENING='"+noRekening+"' AND KODE_JENIS_FASILITAS='F01' ";
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas " +
                                                            "INNER JOIN " +
                                                            "dslik_kredit " +
                                                            "ON dslik_kredit.CIF=dslik_summary_fasilitas.CIF " +
                                                            "AND dslik_kredit.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_12=dslik_kredit.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_12=dslik_kredit.JML_HARI_TUNGGAKAN " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F01' AND dslik_kredit.PERIODE_ID='"+periodeId+"' ";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;    
                             default :
                                 break;
                        }
                    }else{
                        //insert
                        SummaryFasilitas entSummaryFasilitas = new SummaryFasilitas();
                        switch (urutan_ke) {
                             case 2:
                                  try{
                                        String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBLITAS_2, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                  break;
                             case 3:
                                 try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_3, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 4:
                                 try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_4, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 5:
                                 try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_5, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 6:
                                  try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_6, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 7:
                                 try{
                                   String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_7, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 8:
                                 try{
                                    String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_8, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 9:
                                 try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_9, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 10:
                                  try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_10, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 11:
                                 try{
                                     String sqlUpdateDua =""+
                                                          "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_11, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;
                             case 12:
                                 try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.KREDIT_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F01' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_12, " +
                                                            " c.JML_HARI_TUNGGAKAN AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_kredit AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F01'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                      System.out.print("");
                                  }
                                 break;    
                             default :
                                 break;
                        }
                    }
//                }
//                rs.close();
            }
        }catch (Exception e) {
            System.out.println(e);
        } finally {
           // DBResultSet.close(dbrs);
        }
        return hasil;
    }
     
     public static int countMoveDataSummaryKreditPerPeriode(long periodeId, int urutan_ke) throws DBException {
        int hasil = 0;
        DBResultSet dbrs = null;
        try {
            if(periodeId!=0){
                String sql =" SELECT " +
                            " COUNT(dslik_kredit.NO_REKENING) AS TOTAL " +
                            " FROM dslik_kredit WHERE dslik_kredit.PERIODE_ID='"+periodeId+"'";
                
                //int result = execUpdate(sql);
                dbrs = DBHandler.execQueryResult(sql);
                ResultSet rs = dbrs.getResultSet();
                //int resultProses=0;
                while (rs.next()) {
                    hasil = rs.getInt("TOTAL");
                }
                rs.close();
            }
        }catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return hasil;
    }
     
    public static long moveDataSummaryBankGaransiPerPeriode(long periodeId, int urutan_ke, int totalBankGaransi, int prosesData) throws DBException {
        long hasil = 0;
    //    DBResultSet dbrs = null;
        int resultProses=0;
        try {
            if(periodeId!=0){
//                String sql =" SELECT 'D' AS FLAG_DETAIL, " +
//                            " TRIM(dslik_bank_garansi.NO_REKENING) AS NO_REKENING, " +
//                            " TRIM(dslik_bank_garansi.CIF) AS CIF, " +
//                            " 'F05' AS KODE_JENIS_FASILITAS, " +
//                            " dslik_bank_garansi.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS, " +
//                            " 0 AS JML_HARI_TUNGGAKAN "
//                          + " FROM dslik_bank_garansi WHERE dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
//                
//                int result = execUpdate(sql);
//                dbrs = DBHandler.execQueryResult(sql);
//                ResultSet rs = dbrs.getResultSet();
//                int resultProses=0;
//                int urutanProses=0;
//                if(prosesData==0){ //update
                    //cek apakah data cif sudah ada atau belum
//                    String cif = rs.getString("CIF");
//                    String noRekening = rs.getString("NO_REKENING");
//                    String kodeKolektibilitas=rs.getString("KODE_KOLEKTIBILITAS");
//                    int jmlHariTunggakan = rs.getInt("JML_HARI_TUNGGAKAN");
//                    String kodeJenisFasilitas="F05";
//                    boolean cekCifNoRekening =  PstSummaryFasilitas.checkOID(cif,noRekening,kodeJenisFasilitas);
//                    urutanProses=urutanProses+1;
//                    ManagerDilimitedTextSummary.setStatusSummaryPelaporanProsesTransferData("Proses Transfer Data "+urutanProses+" / "+totalBankGaransi);
                    if(prosesData==0){
                        //update
                        switch (urutan_ke) {
                             case 2:
                                  try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBLITAS_2=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_2=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                  break;
                             case 3:
                                 try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_3=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_3=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 4:
                                 try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_4=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_4=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 5:
                                 try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_5=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_5=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 6:
                                  try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_6=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_6=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 7:
                                 try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_7=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_7=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 8:
                                 try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_8=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_8=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 9:
                                 try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_9=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_9=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 10:
                                  try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_10=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_10=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 11:
                                 try{
                                      String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_11=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_11=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 12:
                                 try{
                                     String sqlUpdateDua = "UPDATE dslik_summary_fasilitas  " +
                                                            "INNER JOIN  " +
                                                            "dslik_bank_garansi " +
                                                            "ON dslik_bank_garansi.CIF=dslik_summary_fasilitas.CIF  " +
                                                            "AND dslik_bank_garansi.NO_REKENING=dslik_summary_fasilitas.NO_REKENING " +
                                                            "SET  " +
                                                            "dslik_summary_fasilitas.KODE_KOLEKTIBILITAS_12=dslik_bank_garansi.KODE_KOLEKTIBILITAS, " +
                                                            "dslik_summary_fasilitas.JML_HARI_TUNGGAKAN_12=0 " +
                                                            "WHERE dslik_summary_fasilitas.KODE_JENIS_FASILITAS='F05' AND dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                                      resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;    
                             default :
                                 break;
                        }
                    }else{
                        //insert
                        SummaryFasilitas entSummaryFasilitas = new SummaryFasilitas();
                        switch (urutan_ke) {
                             case 2:
                                  try{
                                     String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                  break;
                             case 3:
                                 try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 4:
                                 try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 5:
                                 try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 6:
                                  try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 7:
                                 try{
                                     String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 8:
                                 try{
                                     String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 9:
                                 try{
                                     String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 10:
                                  try{
                                      String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 11:
                                 try{
                                     String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;
                             case 12:
                                 try{
                                     String sqlUpdateDua =""+
                                                    "insert into dslik_summary_fasilitas (SUMMARY_FASILITAS_OID, FLAG_DETAIL, NO_REKENING, CIF, KODE_JENIS_FASILITAS , KODE_KOLEKTIBILITAS_1, JML_HARI_TUNGGAKAN_1, KODE_KOLEKTIBLITAS_2, JML_HARI_TUNGGAKAN_2, KODE_KOLEKTIBILITAS_3, JML_HARI_TUNGGAKAN_3, KODE_KOLEKTIBILITAS_4, JML_HARI_TUNGGAKAN_4, KODE_KOLEKTIBILITAS_5, JML_HARI_TUNGGAKAN_5, KODE_KOLEKTIBILITAS_6, JML_HARI_TUNGGAKAN_6, KODE_KOLEKTIBILITAS_7, JML_HARI_TUNGGAKAN_7, KODE_KOLEKTIBILITAS_8, JML_HARI_TUNGGAKAN_8, KODE_KOLEKTIBILITAS_9, JML_HARI_TUNGGAKAN_9, KODE_KOLEKTIBILITAS_10, JML_HARI_TUNGGAKAN_10, KODE_KOLEKTIBILITAS_11, JML_HARI_TUNGGAKAN_11, KODE_KOLEKTIBILITAS_12, JML_HARI_TUNGGAKAN_12) "
                                                            + "SELECT DISTINCT  " +
                                                            " c.BANK_GARANSI_OID + 111111111111111 AS SUMMARY_FASILITAS_OID, " 
                                                            + "'D' AS FLAG_DETAIL,  " +
                                                            " TRIM(c.NO_REKENING) AS NO_REKENING,  " +
                                                            " TRIM(c.CIF) AS CIF,  " +
                                                            " 'F05' AS KODE_JENIS_FASILITAS,  " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_1,  " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_1, " +
                                                            " 0 AS KODE_KOLEKTIBLITAS_2, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_2, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_3, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_3, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_4, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_4, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_5, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_5, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_6, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_6, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_7, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_7, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_8, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_8, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_9, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_9, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_10, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_10, " +
                                                            " 0 AS KODE_KOLEKTIBILITAS_11, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_11, " +
                                                            " c.KODE_KOLEKTIBILITAS AS KODE_KOLEKTIBILITAS_12, " +
                                                            " 0 AS JML_HARI_TUNGGAKAN_12 " +
                                                            " FROM dslik_bank_garansi AS c WHERE NOT EXISTS (" +
                                                            " SELECT 1" +
                                                            " FROM dslik_summary_fasilitas AS p" +
                                                            " WHERE p.CIF = c.CIF" +
                                                            " AND p.NO_REKENING = c.NO_REKENING AND p.KODE_JENIS_FASILITAS='F05'" +
                                                            ") AND c.PERIODE_ID='"+periodeId+"'";
                                        resultProses = execUpdate(sqlUpdateDua);
                                  } catch(Exception es){
                                  }
                                 break;    
                             default :
                                 break;
                        }
                    }
//                }
            }
        }catch (Exception e) {
            System.out.println(e);
        } finally {
           // DBResultSet.close(dbrs);
        }
        return hasil;
    }
    
    
    public static int countMoveDataSummaryBankGaransiPerPeriode(long periodeId, int urutan_ke) throws DBException {
        int hasil = 0;
        DBResultSet dbrs = null;
        try {
            if(periodeId!=0){
                String sql =" SELECT " +
                            " COUNT(dslik_bank_garansi.NO_REKENING) AS TOTAL " 
                          + " FROM dslik_bank_garansi WHERE dslik_bank_garansi.PERIODE_ID='"+periodeId+"'";
                //int result = execUpdate(sql);
                dbrs = DBHandler.execQueryResult(sql);
                ResultSet rs = dbrs.getResultSet();
                int resultProses=0;
                while (rs.next()) {
                   hasil=rs.getInt("TOTAL");
                }
                rs.close();
            }
        }catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return hasil;
    }
     
    public static long deleteFasilitasSummary() throws DBException {
        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = "DELETE FROM dslik_summary_fasilitas";
        try {
            iResult = DBHandler.execUpdate(stSql);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            DBResultSet.close(dbrs);
        }
        return iResult;
    }
    
    
    public static Date tanggalAwalKredit(long periodeId, String noRekening, String cif) throws DBException {
        Date hasil = null;
        DBResultSet dbrs = null;
        try {
            if(periodeId!=0){
                String sql =" SELECT " +
                            " * " 
                          + " FROM dslik_kredit WHERE dslik_kredit.PERIODE_ID='"+periodeId+"'"
                          + " AND dslik_kredit.NO_REKENING='"+noRekening+"'"
                          + " AND dslik_kredit.CIF='"+cif+"'";
                //sql=sql+" AND NO_REKENING='0110403000397'";
                dbrs = DBHandler.execQueryResult(sql);
                ResultSet rs = dbrs.getResultSet();
                while (rs.next()) {
                   hasil=rs.getDate("TGL_AWAL");
                }
                rs.close();
            }
        }catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return hasil;
    }
    
    public static Date tanggalAwalBankGaransi(long periodeId, String noRekening, String cif) throws DBException {
        Date hasil = null;
        DBResultSet dbrs = null;
        try {
            if(periodeId!=0){
                String sql =" SELECT " +
                            " * " 
                          + " FROM dslik_bank_garansi WHERE dslik_bank_garansi.PERIODE_ID='"+periodeId+"'"
                          + " AND dslik_bank_garansi.NO_REKENING='"+noRekening+"'"
                          + " AND dslik_bank_garansi.CIF='"+cif+"'";
                //sql=sql+" AND NO_REKENING='0110403000397'";
                dbrs = DBHandler.execQueryResult(sql);
                ResultSet rs = dbrs.getResultSet();
                while (rs.next()) {
                   hasil=rs.getDate("TGL_DITERBITKAN");
                }
                rs.close();
            }
        }catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return hasil;
    }
    
}
