/*
 * AppObjInfo.java
 *
 * Created on April 3, 2002, 4:09 PM
 * Modified on November 27, 2002, 10:14 AM by karya
 */

package com.dimata.dslik.entity.admin;

import java.util.*;

/**
 *
 * @author  ktanjana
 * @version
 * @Purpose Describe application object as binary coded (integer)
 * @CodeMapping
 * bit 0 - 7   : command CMD
 * bit 8 - 15  : page , menu or other objects  OBJ
 * bit 16 - 23 :  level 2 sub-application G2
 * bit 24 - 31 :  level 1 sub-application G1
 */
public class AppObjInfo {
    
    /** Creates new AppObjInfo */
    public AppObjInfo() {
    }
    
    // filter code (contain of 32 bit)
    public static final int FILTER_CODE_G1= 0xFF000000;
    public static final int FILTER_CODE_G2= 0x00FF0000;
    public static final int FILTER_CODE_OBJ=0x0000FF00;
    public static final int FILTER_CODE_CMD=0x000000FF;
    
    public static final int SHIFT_CODE_G1= 24;   // untuk shift sampai 24 bit ke kiri dari belakang, sehingga kita mendapatkan 8 bit pertama/0xFF (G1)
    public static final int SHIFT_CODE_G2= 16;   // untuk shift sampai 16 bit ke kiri dari belakang, sehingga kita mendapatkan 8 bit pertama/0x00FF (G2)
    public static final int SHIFT_CODE_OBJ=8;    // untuk shift sampai 8 bit ke kiri dari belakang, sehingga kita mendapatkan 8 bit pertama/0x0000FF (OBJ)
    //public static final int SHIFT_CODE_CMD=0;
    
    
    // OBJECT COMMAND
    public static final int COMMAND_VIEW    = 0;
    public static final int COMMAND_ADD     = 1;
    public static final int COMMAND_UPDATE  = 2;
    public static final int COMMAND_DELETE  = 3;
    public static final int COMMAND_PRINT   = 4;
    public static final int COMMAND_SUBMIT  = 5;
    public static final int COMMAND_START   = 6;
    public static final int COMMAND_STOP    = 7;
    public static final int COMMAND_APPROVE = 8;
    public static final int COMMAND_VIEW_VALUE = 9; //update opie-eyek 20131201
    public static final int COMMAND_VIEW_STOCK = 10; //update opie-eyek 20131201
    public static final int COMMAND_FINAL = 11;
    public static final int COMMAND_UPLOAD = 12;
    public static final int COMMAND_DOWNLOAD = 13;
    
    /** untuk membatasi informasi yang ditampilkan */
   // public static final int SHOW_ALL_INFO   = 8;
    
    public static final String[] strCommand = {
        "View", "Add New", "Update", "Delete", "Print", "Submit","Start", "Stop", "Approve","View Price","View Stock","Final","Upload","Download" //update opie-eyek 20131201
    };
    
    // *** Application Structure ****** //
    public static final int G1_LOGIN = 0;
    /**/public static final int G2_LOGIN = 0;
    /******/public static final int OBJ_LOGIN_LOGIN = 0;
    /******/public static final int OBJ_LOGIN_LOGOUT = 1;
    
     public static final int G1_ADMIN = 1;
    /**/public static final int G2_ADMIN_USER = 0;
    /******/public static final int OBJ_ADMIN_USER_PRIVILEGE = 0;
    /******/public static final int OBJ_ADMIN_USER_GROUP = 1;
    /******/public static final int OBJ_ADMIN_USER_USER = 2;
    
    /**/public static final int G2_ADMIN_SYSTEM = 1;
    /******/public static final int OBJ_ADMIN_SYSTEM_BACK_UP = 0;
    /******/public static final int OBJ_ADMIN_SYSTEM_APP_SET = 1;
    /******/public static final int OBJ_ADMIN_SYSTEM_TRANSFER_DATA = 2;
    /******/public static final int OBJ_ADMIN_SYSTEM_RESTORE_DATA = 3;
    /******/public static final int OBJ_ADMIN_SYSTEM_CLOSING_PERIOD = 4;
    
    public static final int G1_HOME = 2;
    /**/public static final int G2_HOME = 0;
    /******/public static final int OBJ_HOME = 0;
    
    public static final int G1_PERIODE_DATA = 3;
    /**/public static final int G2_PERIODE_DATA = 0;
    /******/public static final int OBJ_PERIODE_DATA = 0;
    
    public static final int G1_PROSES_TRANSFER_DATA = 4;
    /**/public static final int G2_TRANSFER_DEBITUR_DATA = 0;
    /******/public static final int OBJ_TRANSFER_DEBITUR_DATA = 0;
    
    /**/public static final int G2_TRANSFER_KREDIT_DATA = 1;
    /******/public static final int OBJ_TRANSFER_KREDIT_DATA = 0;
    
    /**/public static final int G2_TRANSFER_BANK_GARANSI_DATA = 2;
    /******/public static final int OBJ_TRANSFER_BANK_GARANSI_DATA = 0;
    
    /**/public static final int G2_TRANSFER_AGUNAN_DATA = 3;
    /******/public static final int OBJ_TRANSFER_AGUNAN_DATA = 0;
    
    /**/public static final int G2_TRANSFER_PENGURUS_PEMILIK_DATA = 4;
    /******/public static final int OBJ_TRANSFER_PENGURUS_PEMILIK_DATA = 0;
    
    public static final int G1_PERLENGKAPAN_DATA = 5;
    /**/public static final int G2_PROSES_PERLENGKAPAN_DATA = 0;
    /******/public static final int OBJ_PROSES_PERLENGKAPAN_DATA = 0;
    
    public static final int G1_APPROVE_DATA = 6;
    /**/public static final int G2_APPROVE_DATA_CABANG = 0;
    /******/public static final int OBJ_APPROVE_DATA_CABANG = 0;
    
    public static final int G1_PROSES_DELIMITED_TEXT = 7;
    /**/public static final int G2_BULANAN = 0;
    /******/public static final int OBJ_BULANAN = 0;
    /**/public static final int G2_INITIAL = 1;
    /******/public static final int OBJ_INITIAL = 0;
    
    public static final int G1_MODUL_LAPORAN = 8;
    /**/public static final int G2_PER_SEGMENT_SUMMARY = 0;
    /******/public static final int OBJ_PER_SEGMENT_SUMMARY = 0;
    
    /**/public static final int G2_PER_SEGMENT_DETAIL = 1;
    /******/public static final int OBJ_PER_SEGMENT_DETAIL = 0;
    
    /**/public static final int G2_PERUBAHAN_DATA_SEGMENT = 2;
    /******/public static final int OBJ_PERUBAHAN_DATA_SEGMENT = 0;
    
    /**/public static final int G2_NOMINATIF_AGUNAN = 3;
    /******/public static final int OBJ_NOMINATIF_AGUNAN = 0;
    
    /**/public static final int G2_NOMINATIF_BANK_GARANSI = 4;
    /******/public static final int OBJ_NOMINATIF_BANK_GARANSI = 0;
    
    /**/public static final int G2_NOMINATIF_KREDIT_HAPUS_BUKU = 5;
    /******/public static final int OBJ_NOMINATIF_KREDIT_HAPUS_BUKU = 0;
    
    /**/public static final int G2_KREDIT_PER_KOLEKTIBILITAS= 6;
    /******/public static final int OBJ_KREDIT_PER_KOLEKTIBILITAS = 0;
    
    /**/public static final int G2_PINJAMAN_TUTUP_PER_BULAN = 7;
    /******/public static final int OBJ_PINJAMAN_TUTUP_PER_BULAN = 0;
    
    public static final int G1_MODUL_PEMELIHARAAN = 9;
    /**/public static final int G2_HISTORY_PERUBAHAN_DATA = 0;
    /******/public static final int OBJ_HISTORY_PERUBAHAN_DATA = 0;
    /**/public static final int G2_LAPORAN_HISTORY_PERUBAHAN_DATA = 1;
    /******/public static final int OBJ_LAPORAN_HISTORY_PERUBAHAN_DATA = 0;
    
    public static final int G1_MODUL_MASTER_DATA = 10;
    /**/public static final int G2_PROFILE_BANK = 0;
    /******/public static final int OBJ_BANK = 0;
    /******/public static final int OBJ_CABANG_BANK = 1;
    /**/public static final int G2_MAPPING_CONTENT_DATA = 1;
    /******/public static final int OBJ_BENTUK_BADAN_USAHA = 0;
    /******/public static final int OBJ_BIDANG_USAHA = 1;
    /******/public static final int OBJ_CARA_RESTRUKTURISASI = 2;
    /******/public static final int OBJ_GOLONGAN_DEBITUR = 3;
    /******/public static final int OBJ_HUBUNGAN_DENGAN_PELAPOR = 4;
    /******/public static final int OBJ_JABATAN = 5;
    /******/public static final int OBJ_JENIS_AGUNAN = 6;
    /******/public static final int OBJ_JENIS_FASILITAS = 7;
    /******/public static final int OBJ_JENIS_GARANSI = 8;
    /******/public static final int OBJ_JENIS_IDENTITAS = 9;
    /******/public static final int OBJ_JENIS_KREDIT = 10;
    /******/public static final int OBJ_JENIS_PEMERINGKAT = 11;
    /******/public static final int OBJ_JENIS_PENGGUNAAN = 12;
    /******/public static final int OBJ_JENIS_PENGIKATAN = 13;
    /******/public static final int OBJ_JENIS_SURAT_BERHARGA = 14;
    /******/public static final int OBJ_KABUPATEN_KOTA = 15;
    /******/public static final int OBJ_KATEGORI_DEBITUR= 16;
    /******/public static final int OBJ_KODE_NEGARA_DOMISILI = 17;
    /******/public static final int OBJ_KODE_PEKERJAAN = 18;
    /******/public static final int OBJ_KODE_VALUTA = 19;
    /******/public static final int OBJ_KOLEKTIBILITAS= 20;
    /******/public static final int OBJ_KONDISI = 21;
    /******/public static final int OBJ_LEMBAGA_PEMERINGKAT = 22;
    /******/public static final int OBJ_NOMOR_IDENTITAS = 23;
    /******/public static final int OBJ_ORIENTASI_PENGGUNAAN = 24;
    /******/public static final int OBJ_SEBAB_MACET = 25;
    /******/public static final int OBJ_SEKTOR_EKONOMI = 26;
    /******/public static final int OBJ_SIFAT_KREDIT = 27;
    /******/public static final int OBJ_SKIM_AKAD_PEMBIAYAAN = 28;
    /******/public static final int OBJ_STATUS_AGUNAN = 29;
    /******/public static final int OBJ_STATUS_PENDIDIKAN = 30;
    /******/public static final int OBJ_STATUS_PERKAWINAN_DEBITUR = 31;
    /******/public static final int OBJ_SUKU_BUNGA = 32;
    /******/public static final int OBJ_SUMBER_PENGHASILAN = 33;
    /******/public static final int OBJ_TAKEOVER = 34;
    /******/public static final int OBJ_TUJUAN_GARANSI = 35;
    /******/public static final int OBJ_TUJUAN_LC = 36;
    /******/public static final int OBJ_DSLIK_DEBITUR = 37;
    /******/public static final int OBJ_DSLIK_FASILITAS_LAIN = 38;
    /******/public static final int OBJ_DSLIK_IRREVICABLE_LC = 39;
    
    public static final int G1_CONFIGURASI = 11;
    /**/public static final int G2_CONFIGURASI_DATABASES = 0;
    /******/public static final int OBJ_CONFIGURASI_DATABASES = 0;
    
    public static final int G1_REPLIKASI = 12;
    /**/public static final int G2_REPLIKASI = 0;
    /******/public static final int OBJ_REPLIKASI = 0;
    
    
    public static final String[] titleG1 = {
        "Login Access",
        "Admin",
        "Home",
        "Periode Data",
        "Proses Transfer Data",
        "Perlengkapan Data",
        "Approve Data",
        "Proses Delimited Text",
        "Modul Laporan",
        "Modul Pemeliharaan",
        "Modul Mastet Data",
        "Configurasi",
        "Replikasi"
    };
    
    
    public static final String[][] titleG2 = {
        /* login */ {"Login Access"},
        /* admin */ {"User Management", "System Management"},
        /* Home */ {"Home"},
        /* Periode Data */{"Periode Data",},
        /* Proses Transfer Data */ {"Transfer Debitur Data", "Transfer Kredit Data","Transfer Bank Garansi Data", "Transfer Agunan Data", "Transfer Pengurus/Pemilik Data"},
        /* Perlengkapan Data */ {"Proses Perlengkapan Data"},
        /* Approve Data */ {"Approve Data Cabang"},
        /* Proses Delimited Text */ {"Bulanan", "Initial"},
        /* Modul Pelaporan */ {"Per Segmen Summary", "Per Segmen Detail", "Perubahan Data Segmen", "Nominatif Agunan", "Nominatif Bank Garansi", "Nominatif Kredit Hapus Buku", "Nominatif Kredit Per Kolektibilitas", "Nominatif Pinjaman Tutup Per Bulan"},
        /* Modul Pemeliharaan */ {"History Perubahan Data", "Laporan History Perubahan Data"},
        /* Modul Master Data */ {"Profile Bank", "Mapping Content Data"},
        /* Configurasi */ {"Configurasi Databases"},
                           {"Replikasi"}
    };
    
    
    public static final String[][][] objectTitles = {
        // Login
        {   // Login
            {"Login Page", "Logout page"}
        },
        // Admin
        {   // User
            {"Pivilege", "Group", "User"},
            // System
            {"Data Backup", "Application Setting", "Transfer Data", "Restore Data", "Closing Period"}
        },
        //HOME
        {   // HOME
            {"Home"}
        },
        //Periode Data
        {   // Periode Data
            {"Periode Data"}
        },
        //Proses Transfer Data
        {   // Transfer Debitur Data
            {"Transfer Debitur Data"},
            {"Transfer Kredit Data"},
            {"Transfer Bank Garansi Data"},
            {"Transfer Agunan Data"},
            {"Transfer Pengurus/Pemilik Data"}
        },
        //Perlengkapan Data
        {   // Proses Perlengkapan Data
            {"Proses Perlengkapan Data"}
        },
        //Approve Data
        {   // Approve Data Cabang
            {"Approve Data Cabang"}
        },
        //Proses Delimited Text
        {   // Bulanan
            {"Bulanan"},
            // Initial
            {"Initial"}
        },
        //Modul Laporan
        {   // Per Segmen Summary
            {"Per Segmen Summary"},
            //Per Segmen Detail
            {"Per Segmen Detail"},
            //Perubahan Data Segmen
            {"Perubahan Data Segmen"},
            //Nominatif Agunan
            {"Nominatif Agunan"},
            //Nominatif Bank Garansi
            {"Nominatif Bank Garansi"},
            //Nominatif Kredit Hapus Buku
            {"Nominatif Kredit Hapus Buku"},
            //Nominatif Kredit Per Kolektibilitas
            {"Nominatif Kredit Per Kolektibilitas"},
            //Nominatif Pinjaman Tutup Per Bulan
            {"Nominatif Pinjaman Tutup Per Bulan"}
        },
        //Modul Pemeliharaan
        {
            //History Perubahan Data
            {"History Perubahan Data"},
            //Laporan History Perubahan Data
            {"Laporan History Perubahan Data"}
        },
        //Modul Master Data
        {
            //Profile bank
            {"Bank", "Cabang Bank"},
            //Mapping Content Data
            {
                "Bentuk Badan Usaha", "Bidang Usaha", "Cara Restrukturisasi", "Golongan Debitur",
                "Hubungan Dengan Pelapor", "Jabatan", "Jenis Agunan", "Jenis Fasilitas", "Jenis Garansi",
                "Jenis Identitas", "Jenis Kredit", "Jenis Pemeringkat", "Jenis Penggunaan", "Jenis Pengikatan", 
                "Jenis Surat Berharga", "Kabupaten Kota", "Kategori Debitur", "Kode Negara Domisili",
                "Kode Pekerjaan", "Kode Valuta", "Kolektibilitas", "Kondisi", "Lembaga Pemeringkat", "Nomor Identitas", "Orientasi Penggunaan",
                "Sebab Macet", "Sektor Ekonomi", "Sifat Kredit", "Skim Akad Pembiayaan","Status Agunan", "Status Pendidikan", "Status Perkawinan Debitur",
                "Suku Bunga", "Sumber Penghasilan", "Takeover", "Tujuan Garansi", "Tujuan LC", "Dslik Debitur", "Dslik Fasilitas Lain", "Dslik Irrevocable LC"
            }
        },
        //Configurasi
        {
            //Configurasi Databases
            {"Configurasi Databases"}
        },
        {
            //Repplikasi
            {"Replikasi"}
        }    
    };
    
    
    public static final int[][][][] objectCommands = {
        // Login
        {   // Login
            {
                //Login Page
                {COMMAND_VIEW, COMMAND_SUBMIT} ,
                //"Logout page"
                {COMMAND_VIEW, COMMAND_SUBMIT}
            }
        },
        
        // Admin
        {   // user
            {
                //"Pivilege"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DELETE, COMMAND_UPDATE},
                //"Group"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DELETE, COMMAND_UPDATE},
                //"User"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DELETE, COMMAND_UPDATE}
            },
            // system
            {
                //"Data Backup"
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP},
                //"Application Setting"
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                // Transfer Data
                {COMMAND_VIEW, COMMAND_START},
                // Restore Data
                {COMMAND_VIEW, COMMAND_START},
                // Closing Period
                {COMMAND_VIEW, COMMAND_START}
            }
        },
        // HOME
        {   
            { //"HOME"
               
                {COMMAND_VIEW, COMMAND_DELETE, COMMAND_UPDATE}
            }
        },
        
        {   // Periode Data
            {
                //Periode Data
                {COMMAND_VIEW, COMMAND_UPDATE, COMMAND_ADD, COMMAND_DELETE, COMMAND_DOWNLOAD}
            }
        },
        // Proses Transfer Data
        {   //Transfer Debitur Data
            {
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP}
            },
            {  
                //Transfer Kredit Data
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP}
            },
            {
                //Transfer Bank Garansi Data
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP}
            },
            {
                //Transfer Agunan data
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP}
            },
            {
                //Transfer Pengurus/Pemilik Data
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP}
            }
        },
        //PERLENGKAPAN DATA
        {
            {
                {COMMAND_VIEW, COMMAND_UPDATE, COMMAND_ADD, COMMAND_DELETE, COMMAND_DOWNLOAD}
            }
            
        },
        {   // Approve Data
            {
                //Approve Data
                {COMMAND_VIEW, COMMAND_APPROVE}
            }
        },
        {   // Delimited Text
            {
                //Bulanan
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP}
            },
            {// Initial
                {COMMAND_VIEW, COMMAND_START, COMMAND_STOP}
            }
        },
        {//Modul Pelaporan
            {//Per Segmen Summary
                {COMMAND_VIEW, COMMAND_PRINT}
            },
            {//Per Segmen Detail
                {COMMAND_VIEW, COMMAND_PRINT}
            },
            {//Perubahan data segmen
                {COMMAND_VIEW, COMMAND_PRINT}
            },
            {//Nominatif Agunan
                {COMMAND_VIEW, COMMAND_PRINT}
            },
            {//Nominatif Bank Garansi
                {COMMAND_VIEW, COMMAND_PRINT}
            },
            {//Nominatif Kredit Hapus Buku
                {COMMAND_VIEW, COMMAND_PRINT}
            },
            {//Nominatif kredit per kolektibilitas
                {COMMAND_VIEW, COMMAND_PRINT}
            },
            {//Nominatif pinjaman tutup per bulan
                {COMMAND_VIEW, COMMAND_PRINT}
            },
        },
        {//Modul Pemeliharaan
            {//History Perubahan Data
                {COMMAND_VIEW, COMMAND_PRINT}
            },
            {
                {COMMAND_VIEW, COMMAND_PRINT}
            }
        },
        {//Modul Master Data
            {//Profile Bank
                //Bank
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE},
                //Cabang Bank
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE}
            },
            {//Mapping Content Data
                //Bentuk Badan usaha
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Bidang Usaha
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Cara Restrukturisasi
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Golongan Debitur
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Hubungan Dengan Pelapor
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jabatan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Agunan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Fasilitas
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Garansi
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Identitas
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Kredit
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Pemeringkat
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Penggunaan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Pengikatan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Jenis Surat Berharga
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Kabupaten Kota
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Kategori Debitur
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Kode Negara Domisili
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Kode Pekerjaan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Kode Valuta
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Kolektibilitas
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Kondisi
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Lembaga Pemeringkat
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Nomor Identitas
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Orientasi Penggunaan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Sebab Macet
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Sektor Ekonomi
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Sifat Kredit
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Skim Akad Pembiayaan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Status Agunan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //status pendidikan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //status perkawinan debitur
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //suku bunga
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //Sumber penghasilan
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //takeover
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //tujuan garansi
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //tujuan lc
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //dslik debitur
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //dslik fasilitas lain
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE},
                //dslik irrevocable lc
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_DOWNLOAD, COMMAND_UPDATE, COMMAND_DELETE}
                
            }
        },
        {//Configurasi
            {//configurasi Databse
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            }
        },
        {//Repplikasi
            {//Repplikasi
                {COMMAND_VIEW, COMMAND_ADD, COMMAND_UPDATE, COMMAND_DELETE}
            }
        }
    };
    
    
    public static String getStrCommand(int command){
        if((command<0) || (command > strCommand.length) ){
            System.out.println(" ERR: getStrCommand - commmand out of range");
            return "";
        }
        return strCommand[command];
        
    }
    
    public static boolean existObject(int g1, int g2, int objIdx){
        if( (g1<0) || (g1> titleG1.length)){
            System.out.println(" ERR: composeCode g1 out of range");
            return false;
        }
        
        if((g2<0) || (g2 > (titleG2[g1]).length))  {
            System.out.println(" ERR: composeCode g2 out of range");
            return false;
        }
        
        if((objIdx<0) || (objIdx> (objectTitles[g1][g2]).length)){
            System.out.println(" ERR: composeCode objIdx out of range");
            return false;
        }
        
        return true;
    }
    
    public static int composeCode(int g1, int g2, int objIdx, int command) {
        if(!existObject(g1,g2, objIdx))
            return -1;
        
        if((command<0) || (command > strCommand.length)){
            System.out.println(" ERR: composeCode commmand out of range");
            return -1;
        }
        
        if(!privExistCommand(g1,g2, objIdx, command)){
            System.out.println(" ERR: composeCode commmand out not exist on object "+
            getTitleGroup1(g1)+"-"+getTitleGroup2(g2)+"-"+getTitleObject(objIdx));
            return -1;
        }
        
        return (g1 << SHIFT_CODE_G1) + (g2 << SHIFT_CODE_G2) + (objIdx << SHIFT_CODE_OBJ ) + command;
    }
    
    public static int composeCode(int objCode, int command) {
        if((command<0) || (command > strCommand.length) ){
            System.out.println(" ERR: composeCode commmand out of range");
            return -1;
        }
        //System.out.println("objCode + command ="+objCode + command);
        return objCode + command;
    }
    
    public static int composeObjCode(int g1, int g2, int objIdx) {
        if(!existObject(g1,g2, objIdx))
            return -1;
        
        return (g1 << SHIFT_CODE_G1) + (g2 << SHIFT_CODE_G2) + (objIdx << SHIFT_CODE_OBJ);
    }
    
   public static int composeObjCode(int g1, int g2) {
        if ((g1 < 0) || (g1 > titleG1.length)) {
            System.out.println(" ERR: composeCode g1 out of range");
            return -1;
        }

        if ((g2 < 0) || (g2 > (titleG2[g1]).length)) {
            System.out.println(" ERR: composeCode g2 out of range");
            return -1;
        }

        return (g1 << SHIFT_CODE_G1) + (g2 << SHIFT_CODE_G2) ;
    }
    
      public static int composeObjCode(int g1) {
            if ((g1 < 0) || (g1 > titleG1.length)) {
                System.out.println(" ERR: composeCode g1 out of range");
                return -1;
            }

            return (g1 << SHIFT_CODE_G1) ;
        }    
    
    private static boolean privExistCommand(int g1, int g2, int objIdx, int command){
        for(int i=0; i< objectCommands[g1][g2][objIdx].length;i++){
            if(objectCommands[g1][g2][objIdx][i]==command)
                return true;
        }
        return false;
    }
    
    public static boolean existCommand(int g1, int g2, int objIdx, int command){
        if(!existObject(g1,g2, objIdx))
            return false;
        
        return privExistCommand(g1,g2, objIdx, command);
    }
    
    public static int getG1G2ObjIdx(int code){
        return (code & (FILTER_CODE_G1 + FILTER_CODE_G2 + FILTER_CODE_OBJ));
    }
    
    public static int getCommand(int code){
        return (code & FILTER_CODE_CMD);
    }
    
    public static int getIdxGroup1(int code){
        int g1 = (code & FILTER_CODE_G1) >> SHIFT_CODE_G1;
        if( (g1<0) || (g1> titleG1.length)){
            System.out.println(" ERR: getIdxGroup1 g1 on code out of range");
            return -1;
        }
        return g1;
    }
    
    public static String getTitleGroup1(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return "";
        
        return titleG1[g1];
    }
    
    public static int getIdxGroup2(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return -1;
        
        int g2 = (code & FILTER_CODE_G2) >> SHIFT_CODE_G2;
        if( (g2<0) || (g2> titleG2[g1].length)){
            System.out.println(" ERR: getIdxGroup2 g2 on code out of range");
            return -1;
        }
        return g2;
    }
    
    public static String getTitleGroup2(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return "";
        
        int g2 = getIdxGroup2(code);
        if(g2<0)
            return "";
        
        return titleG2[g1][g2];
    }
    
    public static int getIdxObject(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return -1;
        
        int g2 = getIdxGroup2(code);
        if(g2<0)
            return -1;
        
        int oidx = (code & FILTER_CODE_OBJ) >> SHIFT_CODE_OBJ;
        if( (oidx<0) || (oidx> objectTitles[g1][g2].length)){
            System.out.println(" ERR: getIdxObject, oidx on code out of range");
            return -1;
        }
        return oidx;
    }
    
    public static String getTitleObject(int code){
        int g1 = getIdxGroup1(code);
        if(g1<0)
            return "";
        
        int g2 = getIdxGroup2(code);
        if(g2<0)
            return "";
        
        int oidx = getIdxObject(code);
        if(oidx<0)
            return "";
        
        return objectTitles[g1][g2][oidx];
    }
    
    /*
     * parse privobj code into title/string of g1, g2, objidx and command
     * return Vector of String: 0=g1, 1=g, 2=objIdx, 3=command, 4=Integer error code (0=false, -1=falses),
     *
     */
    public static Vector parseStringCode(int code){
        Vector titleCodes= new Vector(4,1);
        titleCodes.add(new String(""));
        titleCodes.add(new String(""));
        titleCodes.add(new String(""));
        titleCodes.add(new String(""));
        titleCodes.add(new Integer(0));
        
        int g1 = getIdxGroup1(code);
        if(g1<0){
            titleCodes.set(0, "Invalid G1 Idx");
            titleCodes.set(4, new Integer(-1));
            return titleCodes;
        }
        titleCodes.set(0, titleG1[g1]);
        
        int g2 = getIdxGroup2(code);
        if(g2<0){
            titleCodes.set(1, "Invalid G2 Idx");
            titleCodes.set(4, new Integer(-1));
            return titleCodes;
        }
        titleCodes.set(1, titleG2[g1][g2]);
        
        int oidx = getIdxObject(code);
        if(oidx<0){
            titleCodes.set(2, "Invalid Obj. Idx");
            titleCodes.set(4, new Integer(-1));
            return titleCodes;
        }
        titleCodes.set(2, objectTitles[g1][g2][oidx]);
        
        int cmd = getCommand(code);
        if(cmd<0){
            titleCodes.set(3, "Invalid Command");
            titleCodes.set(4, new Integer(-1));
            return titleCodes;
        }
        titleCodes.set(3, strCommand[cmd]);
        
        return titleCodes;
    }
    
}
