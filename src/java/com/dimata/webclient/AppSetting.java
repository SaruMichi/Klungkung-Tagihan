/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.webclient;

/**
 *
 * @author dimata005
 */
public class AppSetting {
    
    public static final int DBSVR_MYSQL = 0;
    public static final int DBSVR_POSTGRESQL = 1;
    public static final int DBSVR_SYBASE = 2;
    public static final int DBSVR_ORACLE = 3;
    public static final int DBSVR_MSSQL = 4;
    
    public static int SQL_VERSION = DBSVR_ORACLE;
    
    public static final int DISPENDA_GIANYAR = 0;
    public static final int DISPENDA_KLUNGKUNG = 1;
    
    public static String NAMA_VIEW_BADAN ="VIEW_NAMA_BADAN";
    public static String NAMA_VIEW_ESPTPD ="E_SPTPD";
    public static String NAMA_KABUPATEN ="KABUPATEN KLUNGKUNG";
    public static String NAMA_BUPATI ="BUPATI KLUNGKUNG";
    
    public static int TYPE_KOP_WPONLINE = DISPENDA_KLUNGKUNG;
    public static int LAMPIRAN_PRINT = DISPENDA_KLUNGKUNG;
    
    public static String REPORT_INSTANSI ="PEMERINTAH KABUPATEN KLUNGKUNG";
    public static String REPORT_DINAS ="BADAN PENGELOLAAN KEUANGAN DAN PENDAPATAN DAERAH";
    public static String REPORT_INSTANSI_ALAMAT ="Jl. Untung Surapati No.2, Telp.No.(0361)21496-25590";
    
    public static final int APP_TYPE_GIANYAR=0;
    public static final int APP_IPROTAX=1;
    
    public static int TYPE_APP_BACKOFFICE=APP_TYPE_GIANYAR;
    
    
    /* development start *
    public static String IP_BANK_BPD ="http://192.168.201.78:88/index.asmx";
    
    public static String USERNAME_PBB ="PBB_GIANYAR";
    public static String PWD_PBB ="123456";
    public static String INSTANSI_PBB ="PBB_GIANYAR";
    public static String PBB_LOCATION_FILE ="E:\\Dimata\\File";
    public static String PBB_FILE_ZIP_NAME ="PBB_GIANYAR.zip";
    
    public static String USERNAME_PHR ="PHR_GIANYAR";
    public static String PWD_PHR ="123456";
    public static String INSTANSI_PHR ="PHR_GIANYAR";
    public static String PHR_LOCATION_FILE ="E:\\Dimata\\File";
    public static String PHR_FILE_ZIP_NAME ="PHR_GIANYAR.zip";
    
    public static String USERNAME_BPHTB ="BPHTB_GIANYAR";
    public static String PWD_BPHTB ="123456";
    public static String INSTANSI_BPHTB ="BPHTB_GIANYAR";
    
    public static String USERNAME_RETRIBUSI ="RETRIBUSI_GIANYAR";
    public static String PWD_RETRIBUSI ="123456";
    public static String INSTANSI_RETRIBUSI ="RETRIBUSI_GIANYAR";
    
    * development end*/
    
    /*produksi start 
     
    public static String IP_BANK_BPD ="http://192.168.202.166:99/index.asmx";
    
    public static String USERNAME_PBB ="PBB_GIANYAR";
    public static String PWD_PBB ="gyrpbb2014*";
    public static String INSTANSI_PBB ="PBB_GIANYAR";
    public static String PBB_LOCATION_FILE ="C:\\payment\\file";
    public static String PBB_FILE_ZIP_NAME ="PBB_GIANYAR.zip";
    
    public static String USERNAME_PHR ="PHR_GIANYAR";
    public static String PWD_PHR ="Gianyar0264825";
    public static String INSTANSI_PHR ="PHR_GIANYAR";
    public static String PHR_LOCATION_FILE ="C:\\payment\\file";
    public static String PHR_FILE_ZIP_NAME ="PHR_GIANYAR.zip";
    
    public static String USERNAME_BPHTB ="BPHTB_GIANYAR";
    public static String PWD_BPHTB ="Gianyar0264825";
    public static String INSTANSI_BPHTB ="BPHTB_GIANYAR";
    
    public static String USERNAME_RETRIBUSI ="RETRIBUSI_GIANYAR";
    public static String PWD_RETRIBUSI ="Gianyar0264825";
    public static String INSTANSI_RETRIBUSI ="RETRIBUSI_GIANYAR";
    
    produksi end*/
    
    
    ///***BANGLI***///
    /*---------------------------------------------------------------------------*/
    /* development start */
    /*public static String IP_BANK_BPD ="http://192.168.201.78:88/index.asmx";
    
    public static String USERNAME_PBB ="PBB_BANGLI";
    public static String PWD_PBB ="123456";
    public static String INSTANSI_PBB ="PBB_BANGLI";
    public static String PBB_LOCATION_FILE ="C:\\Dimata\\File";
    public static String PBB_FILE_ZIP_NAME ="PBB_BANGLI.zip";
    
    public static String USERNAME_PHR ="PHR_BANGLI";
    public static String PWD_PHR ="123456";
    public static String INSTANSI_PHR ="PHR_BANGLI";
    public static String PHR_LOCATION_FILE ="C:\\Dimata\\File";
    public static String PHR_FILE_ZIP_NAME ="PHR_BANGLI.zip";
    
    public static String USERNAME_BPHTB ="BPHTB_BANGLI";
    public static String PWD_BPHTB ="123456";
    public static String INSTANSI_BPHTB ="BPHTB_BANGLI";
    
    public static String USERNAME_RETRIBUSI ="RETRIBUSI_BANGLI";
    public static String PWD_RETRIBUSI ="123456";
    public static String INSTANSI_RETRIBUSI ="RETRIBUSI_BANGLI";*/
    
    /* development end*/
    
    
    
     ///***KLUNGKUNG***///
    /*---------------------------------------------------------------------------*/
    //DEV 
    
//    public static String IP_BANK_BPD ="http://192.168.201.98:89/index.asmx?wsdl";
//    public static String IP_BANK_BPD_QRIS = "http://36.75.213.124:7070/merchant-admin/rest/openapi/";
//    
//    public static String USERNAME_PHR ="DIMATA";
//    public static String PWD_PHR ="123456";
//    public static String INSTANSI_PHR ="PHR_KLUNGKUNG";
//    public static String PHR_LOCATION_FILE ="D:\\Dimata\\File";
//    public static String PHR_FILE_ZIP_NAME ="PHR_KLUNGKUNG.zip";
    
    //Qris
//    public static String MPAN = "9360012900000001558";
//    public static String TERMINAL = "A01";
//    public static String MERCHANT_NAME = "DEV PHR KLUNGKUNG";
//    public static String KEY = "pVwWwXNk";
//    public static String PRODUK = "PHR_KLUNGKUNG";
    
     /* produksi start */
    public static String IP_BANK_BPD ="https://dev.bpdbali.id:8443/ws_bpd_payment/interkoneksi/v1/ws_interkoneksi.php?wsdl";
    
    public static String USERNAME_PHR ="DIMATA";
    public static String PWD_PHR ="D1m4t@zxc123zxczxcBPDB4l1i!!!";
    public static String INSTANSI_PHR ="PAJAK_KLUNGKUNG";
    public static String PHR_LOCATION_FILE ="C:\\Dimata\\File";
    public static String PHR_FILE_ZIP_NAME ="PHR_KLUNGKUNG.zip";
    
    public static String USERNAME_ABT ="PHR_KLUNGKUNG";
    public static String PWD_ABT ="bpdB@li_phrklungkung2017";
    public static String INSTANSI_ABT ="ABT_KLUNGKUNG";
    public static String ABT_LOCATION_FILE ="C:\\Dimata\\File";
    public static String ABT_FILE_ZIP_NAME ="ABT_KLUNGKUNG.zip";
    
    public static String USERNAME_BPHTB ="BPHTB_KLUNGKUNG";
    public static String PWD_BPHTB ="123456";
    public static String INSTANSI_BPHTB ="BPHTB_KLUNGKUNG";
    
    public static String USERNAME_RETRIBUSI ="RETRIBUSI_KLUNGKUNG";
    public static String PWD_RETRIBUSI ="123456";
    public static String INSTANSI_RETRIBUSI ="RETRIBUSI_KLUNGKUNG";
    
    public static String USERNAME_PBB ="PBB_KLUNGKUNG";
    public static String PWD_PBB ="123456";
    public static String INSTANSI_PBB ="PBB_BANGLI";
    public static String PBB_LOCATION_FILE ="C:\\Dimata\\File";
    public static String PBB_FILE_ZIP_NAME ="PBB_KLUNGKUNG.zip";
    public static String WP_UPLOAD_LOCATION_FILE ="C:\\Dimata\\wpupload";
    
    //qris  
    public static String IP_BANK_BPD_QRIS = "https://portal.bpdbali.id/openapi/";
   
    public static String MPAN = "9360012900000232070";
    public static String TERMINAL = "A01";
    public static String MERCHANT_NAME = "PHR KLUNGKUNG";
    public static String KEY = "Lhel4PWo";
    public static String PRODUK = "PHR_KLUNGKUNG"; 
    
  /* produksi end*/ 
    
    
     /* development start 
    public static String IP_BANK_BPD ="http://192.168.201.78:89/index.asmx";
    
    public static String USERNAME_PHR ="dimata";
    public static String PWD_PHR ="123456";
    public static String INSTANSI_PHR ="PHR_KLUNGKUNG";
    public static String PHR_LOCATION_FILE ="C:\\Dimata\\File";
    public static String PHR_FILE_ZIP_NAME ="PHR_KLUNGKUNG.zip";
    public static String WP_UPLOAD_LOCATION_FILE ="C:\\Dimata\\wpupload";
    
    public static String USERNAME_ABT ="dimata";
    public static String PWD_ABT ="123456";
    public static String INSTANSI_ABT ="ABT_KLUNGKUNG";
    public static String ABT_LOCATION_FILE ="C:\\Dimata\\File";
    public static String ABT_FILE_ZIP_NAME ="ABT_KLUNGKUNG.zip";
    
    public static String USERNAME_BPHTB ="BPHTB_KLUNGKUNG";
    public static String PWD_BPHTB ="123456";
    public static String INSTANSI_BPHTB ="BPHTB_KLUNGKUNG";
    
    public static String USERNAME_RETRIBUSI ="RETRIBUSI_KLUNGKUNG";
    public static String PWD_RETRIBUSI ="123456";
    public static String INSTANSI_RETRIBUSI ="RETRIBUSI_KLUNGKUNG";
    
    public static String USERNAME_PBB ="PBB_KLUNGKUNG";
    public static String PWD_PBB ="123456";
    public static String INSTANSI_PBB ="PBB_BANGLI";
    public static String PBB_LOCATION_FILE ="C:\\Dimata\\File";
    public static String PBB_FILE_ZIP_NAME ="PBB_KLUNGKUNG.zip";
    development end*/
    
}
