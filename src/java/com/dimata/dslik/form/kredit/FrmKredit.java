/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.kredit;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.kredit.*;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.util.Formater;
import javax.servlet.http.HttpServletRequest;

public class FrmKredit extends FRMHandler implements I_FRMInterface, I_FRMType {

    private Kredit entKredit;
    public static final String FRM_NAME_KREDIT = "FRM_NAME_KREDIT";
    public static final int FRM_FLD_KREDIT_OID = 0;
    public static final int FRM_FLD_FLAG_DETAIL = 1;
    public static final int FRM_FLD_NO_REKENING = 2;
    public static final int FRM_FLD_CIF = 3;
    public static final int FRM_FLD_KODE_SIFAT = 4;
    public static final int FRM_FLD_KODE_JENIS_KREDIT = 5;
    public static final int FRM_FLD_KODE_AKAD = 6;
    public static final int FRM_FLD_NO_AKAD_AWAL = 7;
    public static final int FRM_FLD_TGL_AKAD_AWAL = 8;
    public static final int FRM_FLD_NO_AKAD_AKHIR = 9;
    public static final int FRM_FLD_TGL_AKAD_AKHIR = 10;
    public static final int FRM_FLD_BARU_PERPANJANGAN = 11;
    public static final int FRM_FLD_TGL_AWAL = 12;
    public static final int FRM_FLD_TGL_MULAI = 13;
    public static final int FRM_FLD_TGL_TEMPO = 14;
    public static final int FRM_FLD_KODE_KAT_DBITUR = 15;
    public static final int FRM_FLD_KODE_JENIS_PENGGUNAAN = 16;
    public static final int FRM_FLD_KODE_ORIENTASI_PENGGUNAAN = 17;
    public static final int FRM_FLD_KODE_SEKTOR_EKONOMI = 18;
    public static final int FRM_FLD_KODE_KAB = 19;
    public static final int FRM_FLD_NILAI = 20;
    public static final int FRM_FLD_KODE_VALUTA = 21;
    public static final int FRM_FLD_PROSENTASE_BUNGA = 22;
    public static final int FRM_FLD_JENIS_BUNGA = 23;
    public static final int FRM_FLD_KREDIT_PEMERINTAH = 24;
    public static final int FRM_FLD_TAKEOVER = 25;
    public static final int FRM_FLD_SUMBER_DANA = 26;
    public static final int FRM_FLD_PLAFON_AWAL = 27;
    public static final int FRM_FLD_PLAFON = 28;
    public static final int FRM_FLD_REALISASI = 29;
    public static final int FRM_FLD_DENDA = 30;
    public static final int FRM_FLD_BAKI_DEBET = 31;
    public static final int FRM_FLD_NILAI_UANG_ASAL = 32;
    public static final int FRM_FLD_KODE_KOLEKTIBILITAS = 33;
    public static final int FRM_FLD_TGL_MACET = 34;
    public static final int FRM_FLD_KODE_SEBAB_MACET = 35;
    public static final int FRM_FLD_TUNGGAKAN_POKOK = 36;
    public static final int FRM_FLD_TUNGGAKAN_BUNGA = 37;
    public static final int FRM_FLD_JML_HARI_TUNGGAKAN = 38;
    public static final int FRM_FLD_FREKUENSI_TUNGGAKAN = 39;
    public static final int FRM_FLD_FREKUENSI_RESTRUKTURISASI = 40;
    public static final int FRM_FLD_TGL_RESTRUKTURISASI_AWAL = 41;
    public static final int FRM_FLD_TGL_RESTRUKTURISASI_AKHIR = 42;
    public static final int FRM_FLD_KODE_CARA = 43;
    public static final int FRM_FLD_KODE_KONDISI = 44;
    public static final int FRM_FLD_TGL_KONDISI = 45;
    public static final int FRM_FLD_KETERANGAN = 46;
    public static final int FRM_FLD_KODE_KANTOR_CABANG = 47;
    public static final int FRM_FLD_OPERASI_DATA = 48;
    public static final int FRM_FLD_OPEN_DATE = 49;
    public static final int FRM_FLD_STATUS_DATA = 50;
    public static final int FRM_FIELD_PERIODE_ID = 51;
    public static final int FRM_FIELD_STATUS_PERUBAHAN_DATA = 52;
    
    
    public static String[] fieldNames = {
        "FRM_FLD_KREDIT_OID",
        "FRM_FLD_FLAG_DETAIL",
        "FRM_FLD_NO_REKENING",
        "FRM_FLD_CIF",
        "FRM_FLD_KODE_SIFAT",
        "FRM_FLD_KODE_JENIS_KREDIT",
        "FRM_FLD_KODE_AKAD",
        "FRM_FLD_NO_AKAD_AWAL",
        "FRM_FLD_TGL_AKAD_AWAL",
        "FRM_FLD_NO_AKAD_AKHIR",
        "FRM_FLD_TGL_AKAD_AKHIR",
        "FRM_FLD_BARU_PERPANJANGAN",
        "FRM_FLD_TGL_AWAL",
        "FRM_FLD_TGL_MULAI",
        "FRM_FLD_TGL_TEMPO",
        "FRM_FLD_KODE_KAT_DBITUR",
        "FRM_FLD_KODE_JENIS_PENGGUNAAN",
        "FRM_FLD_KODE_ORIENTASI_PENGGUNAAN",
        "FRM_FLD_KODE_SEKTOR_EKONOMI",
        "FRM_FLD_KODE_KAB",
        "FRM_FLD_NILAI",
        "FRM_FLD_KODE_VALUTA",
        "FRM_FLD_PROSENTASE_BUNGA",
        "FRM_FLD_JENIS_BUNGA",
        "FRM_FLD_KREDIT_PEMERINTAH",
        "FRM_FLD_TAKEOVER",
        "FRM_FLD_SUMBER_DANA",
        "FRM_FLD_PLAFON_AWAL",
        "FRM_FLD_PLAFON",
        "FRM_FLD_REALISASI",
        "FRM_FLD_DENDA",
        "FRM_FLD_BAKI_DEBET",
        "FRM_FLD_NILAI_UANG_ASAL",
        "FRM_FLD_KODE_KOLEKTIBILITAS",
        "FRM_FLD_TGL_MACET",
        "FRM_FLD_KODE_SEBAB_MACET",
        "FRM_FLD_TUNGGAKAN_POKOK",
        "FRM_FLD_TUNGGAKAN_BUNGA",
        "FRM_FLD_JML_HARI_TUNGGAKAN",
        "FRM_FLD_FREKUENSI_TUNGGAKAN",
        "FRM_FLD_FREKUENSI_RESTRUKTURISASI",
        "FRM_FLD_TGL_RESTRUKTURISASI_AWAL",
        "FRM_FLD_TGL_RESTRUKTURISASI_AKHIR",
        "FRM_FLD_KODE_CARA",
        "FRM_FLD_KODE_KONDISI",
        "FRM_FLD_TGL_KONDISI",
        "FRM_FLD_KETERANGAN",
        "FRM_FLD_KODE_KANTOR_CABANG",
        "FRM_FLD_OPERASI_DATA",
        "FRM_FLD_OPEN_DATE",
        "FRM_FLD_STATUS_DATA",
        "FRM_FIELD_PERIODE_ID",
        "FRM_FIELD_STATUS_PERUBAHAN_DATA"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_INT,
        TYPE_INT,
        TYPE_INT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_LONG,
        TYPE_INT
    };
    
    public static String[] fieldQuestion = {
        /*0*/"",
        /*1*/"",
        /*2*/"- Kolom ini diisi dengan nomor rekening fasilitas."
            + "&#010;- Nomor rekening harus unik (1 (satu) nomor rekening untuk setiap 1 (satu) fasilitas)."
            + "&#010;- Nomor rekening tidak boleh berubah selama fasilitas tersebut tercatat di dalam SLIK."
            + "&#010;- Nomor rekening yang telah digunakan oleh satu fasilitas tidak boleh digunakan untuk fasilitas lainnya (no reuse/no recycle)."
            + "&#010;- Apabila nomor rekening mengandung karakter selain alfabet dan angka, maka karakter tersebut tidak perlu disertakan.",
        /*3*/"- Kolom ini diisi dengan nomor CIF debitur"
            + "&#010;- Setiap nomor CIF harus unik untuk setiap debitur (1 (satu) nomor CIF untuk setiap 1 (satu) debitur)."
            + "&#010;- Nomor CIF debitur tidak boleh berubah selama debitur tersebut tercatat di dalam SLIK."
            + "&#010;- Nomor CIF yang telah digunakan oleh satu debitur tidak boleh digunakan untuk debitur lainnya (no reuse/no recycle)",
        /*4*/"- Kolom ini diisi dengan 1 (satu) digit Kode Sifat Kredit sesuai dengan referensi ",
        /*5*/"- Kolom ini diisi dengan 2 (dua) digit Kode Jenis  Kredit/Pembiayaan sesuai dengan referensi",
        /*6*/"- Kolom ini diisi dengan 2 (dua) digit Kode Skim/Akad Kredit sesuai dengan referensi",
        /*7*/"- Kolom ini diisi dengan  nomor akad awal (akad pertama)fasilitas kredit/pembiayaan"
            + "&#010;- Kolom ini wajib diisi jika jenis kredit/pembiayaan masuk dalam kategori kredit/pembiayaan dengan  perjanjian  kecuali,  untuk  fasilitas  kartu  kredit  dapat dikosongkan"
            + "&#010;- Untuk pengisian kategori kredit/pembiayaan tanpa perjanjian maka kolom ini dikosongkan",
        /*8*/"- Kolom ini diisi dengan tanggal akad awal (akad pertama) fasilitas kredit/pembiayaan"
            + "&#010;- Kolom ini wajib diisi jika jenis kredit/pembiayaan masuk dalam kategori kredit/pembiayaan dengan perjanjian  kecuali,  untuk  fasilitas  kartu  kredit  dapat dikosongkan"
            + "&#010;- Untuk  pengisian kategori kredit/pembiayaan tanpa perjanjian maka kolom ini dikosongkan",
	/*9*/"- Kolom ini diisi dengan nomor akad akhir (akad terbaru) fasilitas kredit/pembiayaan"
            + "&#010;- Kolom ini wajib diisi jika jenis kredit/pembiayaan masuk dalam kategori kredit/pembiayaan  dengan  perjanjian, kecuali untuk  fasilitas  kartu  kredit  dapat dikosongkan"
            + "&#010;- Untuk pengisian kategori kredit/pembiayaan tanpa perjanjian maka kolom ini dikosongkan"
            + "&#010;- Apabila  tidak  ada  perubahan/adendum terhadap perjanjian kredit/pembiayaan sejak perjanjian/akad pertama kali maka kolom ini diisi sama dengan kolom nomor akad awal",
        /*10*/"- Kolom ini diisi dengan tanggal akad akhir (akad terbaru) fasilitas kredit sebagaimana tercatat dalam sistem internal pelapor"
            + "&#010;- Kolom ini wajib diisi jika jenis kredit/pembiayaan masuk dalam kategori kredit/pembiayaan  dengan  perjanjian  kecuali,  untuk  fasilitas  kartu  kredit  dapat dikosongkan"
            + "&#010;- Untuk pengisian kategori kredit/pembiayaan tanpa  perjanjian  maka  kolom  ini dikosongkan"
            + "&#010;- Apabila tidak ada  perubahan/adendum terhadap  perjanjian  kredit/pembiayaan sejak  perjanjian/akad  pertama  kali  maka  kolom  ini  diisi  sama  dengan  kolom tanggal akad awal",
	/*11*/"- Kolom ini diisi dengan frekuensi perpanjangan terhadap fasilitas kredit/pembiayaan yang dilaporkan"
            + "&#010;- Untuk fasilitas baru kolom ini diisi dengan 0"
            + "&#010;- Untuk  fasilitas perpanjangan kolom ini diisi dengan frekuensi perpanjangan yang telah dilakukan"
            + "&#010;- Kolom ini bersifat mandatory (wajib diisi)",
        /*12*/"- Kolom ini diisi dengan tanggal mulai berlakunya fasilitas kredit/pembiayaan pertama kali"
            + "&#010;- Jika jenis  kredit/pembiayaan masuk dalam kategori kredit/pembiayaan  dengan perjanjian,diisi berdasarkan    tanggal    mulai   berlakunya    kredit/pembiayaan berdasarkan akad kredit/pembiayaan yang pertama (akad awal)"
            + "&#010;- Kolom ini bersifat mandatory (wajib diisi)",
	/*13*/"- Untuk kredit baru (bukan perpanjangan), kolom ini diisi sama dengan tanggal awal kredit"
            + "&#010;- Untuk kredit dengan perpanjangan, kolom ini diisi dengan tanggal mulai berlakunya fasilitas kredit/pembiayaan perpanjangan yang terakhir",
	/*14*/"- Kolom ini diisi dengan tanggal jatuh tempo  atau berakhirnya jangka waktu kredit/pembiayaan"
            + "&#010;- Untuk kredit dengan perpanjangan, kolom ini  diisi  dengan  tanggal  jatuh  tempo fasilitas kredit/pembiayaan",
        /*15*/"- Kolom ini diisi dengan 2 (dua) digit Kode Kategori  Debitur  sesuai  dengan  referensi",
        /*16*/"- Kolom ini diisi dengan 1 (satu) digit Kode Jenis Penggunaan sesuai dengan referensi",
        /*17*/"- Kolom ini diisi dengan 1 (satu) digit Kode Orientasi Penggunaan sesuai dengan referensi",
        /*18*/"- Kolom ini diisi dengan 6  (enam) digit kode sektor  ekonomi  bidang  usaha  yang dibiayai sebagaimana tercantum pada Referensi Kode Sektor Ekonomi",
        /*19*/"- Kolom ini diisi kode  Kabupaten/Kota lokasi proyek/penggunaan fasilitas kredit/pembiayaan sebagaimana tercantum pada Referensi Kode Kabupaten / Kota (DATI II)"
            + "&#010;- Kolom ini tidak dapat diisi dengan kode sandi DATI II yang berstatus  tidak  aktif atau tingkat provinsi"
            + "&#010;- Kolom ini dapat diisi DATI 2  sesuai dengan  alamat debitur  apabila  jenis  kredit adalah Kartu Kredit dan Giro Bersaldo Debet",
        /*20*/"- Kolom ini diisi dengan nominal nilai proyek yang akan dibiayai dalam satuan penuh mata uang Rupiah"
            + "&#010;- Kolom ini wajib  diisi jika jenis penggunaan adalah Kredit/Pembiayaan  Modal Kerja atau Kredit/Pembiayaan Investasi"
            + "&#010;- Untuk  Kredit/Pembiayaan  Modal  Kerja  yang  tidak  terkait  dengan  pelaksanaan proyek tertentu, kolom ini diisi dengan kebutuhan modal kerja debitur",
        /*21*/"- Kolom  ini  diisi  dengan 4 (empat) digit kode  valuta fasilitas  kredit/pembiayaan sebagaimana tercantum pada Referensi Kode Valuta",
        /*22*/"- Kolom ini diisi dengan persentase suku bunga/imbalan fasilitas kredit/pembiayaan",
        /*23*/"- Kolom ini diisi dengan 1 (satu) digit Kode Jenis Suku Bunga/Imbalan sesuai dengan referensi",
        /*24*/"- Kolom ini diisi sesuai dengan referensi",
        /*25*/"- Kolom  ini  diisi dengan Kode LJK  dimana  fasilitas  kredit/pembiayaan takeover berasal"
            + "&#010;- Kolom ini wajib diisi apabila fasilitas  kredit/pembiayaan   merupakan  fasilitas takeover dari LJK lain"
            + "&#010;- Jika fasilitas  kredit/pembiayaan  bukan merupakan fasilitas takeover maka  kolom ini dikosongkan",
        /*26*/"- Kolom ini diisi dengan Kode LJK atau Kode Pihak Ketiga Bukan Bank yang menjadi sumber dana fasilitas kredit/pembiayaan"
            + "&#010;- Jika sumber dana tidak berasal dari lembaga lain maka kolom ini diisi dengan kode LJK pelapor",
        /*27*/"- Kolom ini diisi dengan nominal plafon  awal dari  fasilitas  kredit/pembiayaan  dalam satuan penuh mata uang Rupiah"
            + "&#010;- Untuk kredit/pembiayaan dengan  plafon menurun, kolom ini diisi dengan nominal plafon awal dan tidak mengikuti penurunan plafon"
            + "&#010;- Untuk  beberapa fasilitas kredit/pembiayaan  yang  plafon  awalnya  tergabung  dalam satu plafon induk, kolom ini diisi dengan nominal plafon induk",
        /*28*/"- Kolom ini diisi dengan nominal plafon efektif dari kredit/pembiayaan dalam satuan penuh mata uang Rupiah"
            + "&#010;- Untuk kredit/pembiayaan dengan plafon menurun, kolom ini diisi dengan nominal plafon mengikuti penurunan plafon"
            + "&#010;- Untuk  beberapa fasilitas kredit/pembiayaan  yang  plafonnya  tergabung  dalam  satu plafon induk, kolom ini diisi dengan nominal plafon efektif per fasilitas",
        /*29*/"- Kolom ini diisi dengan nominal ealisasi/pencairan kredit/pembiayaan yang dilakukan  pada  bulan  data  yang  dilaporkan  dengan  satuan  penuh  mata  uang Rupiah"
            + "&#010;- Jika pada bulan data  yang  dilaporkan  tidak  terjadi  realisasi  kredit/pembiayaan maka kolom ini diisi dengan “0” (nol)",
        /*30*/"- Kolom ini diisi dengan nominal denda yang dikenakan  terhadap  debitur dalam satuan penuh mata uang Rupiah",
        /*31*/"- Kolom ini diisi dengan nominal baki debet dari kredit/pembiayaan  dalam  satuan penuh mata uang Rupiah."
            + "&#010;- Tunggakan bunga dan denda tidak termasuk dalam kolom ini",
        /*32*/"- Kolom  ini diisi dengan nominal baki debet  dari  kredit/pembiayaan  dalam  satuan penuh mata uang asal sesuai dengan perjanjian kredit/pembiayaan"
            + "&#010;- Tunggakan pokok,tunggakan bunga, dan denda tidak termasuk dalam kolom ini"
            + "&#010;- Kolom  ini wajib diisi  untuk  kredit/pembiayaan dengan  mata  uang  selain  Rupiah dan wajib dikosongkan jika fasilitas kredit/pembiayaan dalam mata uang Rupiah",
        /*33*/"- Kolom  ini diisi dengan  1  (satu) digit Kode  Kolektibilitas  sesuai  dengan  referensi"
            + "&#010;- Aturan penentuan kolektibilitas  kredit/pembiayaan mengacu  kepada  ketentuan mengenai   kolektibilitas   yang   berlaku   pada   masing-masing   jenis   LJK   pelapor dengan",
	/*34*/"- Kolom  ini  diisi dengan tanggal  pada  saat  fasilitas  kredit/pembiayaan  dinyatakan macet"
            + "&#010;- Kolom ini wajib diisi apabila fasilitas kredit/pembiayaan yang dilaporkan dinyatakan macet. Sebaliknya,   kolom   ini   wajib   dikosongkan   apabila   fasilitas kredit/pembiayaan yang dilaporkan tidak macet ",
        /*35*/"- Kolom ini diisi dengan 2 (dua) digit Kode Sebab Macet sesuai dengan referensi"
            + "&#010;- Kolom ini wajib diisi apabila fasilitas kredit/pembiayaan yang dilaporkan dinyatakan   macet. Sebaliknya,   kolom   ini   wajib   dikosongkan   apabila   fasilitas kredit/pembiayaan yang dilaporkan tidak macet",
        /*36*/"- Kolom ini diisi dengan nominal tunggakan pokok dari kredit/pembiayaan  dalam satuan penuh mata uang Rupiah"
            + "&#010;- Apabila tidak ada tunggakan pokok maka kolom ini diisi dengan 0",
        /*37*/"- Kolom  ini  diisi dengan nominal tunggakan bunga/margin dari  kredit/pembiayaan dalam satuan penuh mata uang Rupiah"
            + "&#010;- Apabila tidak ada tunggakan bunga maka kolom ini diisi dengan 0",
        /*38*/"- Kolom ini diisi dengan jumlah hari tunggakan fasilitas  kredit/pembiayaan  yang dilaporkan (tunggakan pokok dan/atau bunga)"
            + "&#010;- Apabila tidak ada tunggakan maka kolom ini diisi dengan 0 (nol)",
        /*39*/"- Kolom ini diisi dengan frekuensi tunggakan fasilitas kredit/pembiayaan yang terjadi (tunggakan  pokok  dan/atau  bunga)  sejak  terakhir  kali melakukan  pelunasan tunggakan"
            + "&#010;- Apabila tidak ada tunggakan maka kolom ini diisi dengan 0 (nol)",
        /*40*/"- Kolom ini diisi dengan frekuensi restrukturisasi sejak tanggal awal kredit/pembiayaan"
            + "&#010;- Apabila tidak pernah terjadi restrukturisasi maka kolom ini diisi dengan 0 (nol)",
        /*41*/"- Kolom ini diisi dengan tanggal pada saat fasilitas kredit/pembiayaan direstrukturisasi pertama kali"
            + "&#010;- Kolom  ini  wajib  diisi  apabila fasilitas  kredit/pembiayaan  yang  dilaporkan  pernah direstrukturisasi. Sebaliknya, kolom ini wajib dikosongkan apabila fasilitas kredit/pembiayaan yang dilaporkan tidak pernah direstrukturisasi",
        /*42*/"- Kolom ini diisi dengan tanggal pada saat fasilitas kredit/pembiayaan direstrukturisasi terakhir kali"
            + "&#010;- Kolom  ini  wajib  diisi apabila  fasilitas  kredit/pembiayaan  yang  dilaporkan  pernah direstrukturisasi. Sebaliknya, kolom ini wajib dikosongkan apabila fasilitas kredit/pembiayaan yang dilaporkan tidak pernah direstrukturisasi"
            + "&#010;- Apabila fasilitas kredit/pembiayaan baru 1 (satu) kali direstrukturisasi, maka kolom tanggal restrukturisasi akhir diisi sama dengan tanggal restrukturisasi awal",
        /*43*/"- Kolom ini diisi dengan 2 (dua) digit Kode  Cara  Restrukturisasi sesuai dengan referensi"
            + "&#010;- Kolom ini  wajib  diisi  apabila  fasilitas  kredit/pembiayaan  yang  dilaporkan  pernah direstrukturisasi.Sebaliknya, kolom ini wajib dikosongkan apabila fasilitas kredit/pembiayaan yang dilaporkan tidak pernah direstrukturisasi",
        /*44*/"- Kolom ini diisi dengan 2 (dua) digit Kode  Kondisi sesuai  dengan  referensi",
        /*45*/"- Kolom ini diisi dengan tanggal pada saat fasilitas  kredit/pembiayaan  dinyatakan dalam kondisi sebagaimana dilaporkan dalam kolom Kode Kondisi"
            + "&#010;- Kolom ini wajib diisi apabila kolom Kode Kondisi berisi selain 00. Sebaliknya, kolom ini wajib dikosongkan apabila kolom Kode Kondisi berisi 00",
        /*46*/"- Kolom ini diisi apabila ada keterangan tambahan/keterangan lainnya yang diperlukan terkait fasilitas kredit/pembiayaan yang dilaporkan"
            + "&#010;- Untuk kredit program pemerintah diisi dengan singkatan  nama  program  diawali karakter # (Antara lain : #KUR,#KKPE,#FLPP,#KPENRB,#KUPS, dan lain-lain)",
        /*47*/"- Kolom ini berisi Kode Kantor Cabang dimana fasilitas kredit/pembiayaan dicatat/diregister",
        /*48*/"- Kolom ini dengan 1 (satu) digit kode operasi data sesuai dengan referensi",
        /*49*/"",
        /*50*/"",
        /*51*/"",
        /*52*/""
    };
     public static String[] fieldError = {
        "",
        "",
        "Kolom ini harus diisi dengan nomor rekening",
        "Kolom ini harus diisi dengan nomor CIF debitur.",
        "Pilih salah satu referensi kode sifat kredit",
        "Pilih salah satu referensi kode jenis kredit",
        "Pilih salah satu referensi kode skim/akad pembiayaan",
        "Kolom ini harus diisi dengan nomor Akad Awal ",
        "Kolom ini harus diisi dengan tanggal Akad Awal (Cth: 20111208)",
        "Kolom ini harus diisi dengan nomor akad akhir (akad terbaru)",
        "Kolom ini harus diisi dengan tanggal akad akhir (akad terbaru)",
        "Pilih salah satu referensi",
        "Kolom ini harus diisi sesuai tanggal mulai berlakunya fasilitas pertama kali (Cth: 20111208)",
        "Kolom ini harus diisi sesuai tanggal kredit baru atau kredit perpanjangan (Cth: 20111208)",
        "Kolom ini harus diisi dengan tanggal jatuh tempo(Cth: 20301207)",
        "Pilih salah satu referensi",
        "Pilih salah satu referensi",
        "Pilih salah satu referensi",
        "Pilih salah satu referensi kode sektor ekonomi",
        "Pilih salah satu referensi kode kabupaten Dati II",
        "Kolom ini harus diisi dengan nominal nilai proyek dalam rupiah (Cth: 140000000000)",
        "Pilih salah satu kode valuta",
        "Kolom ini harus diisi presentase suku bunga (Cth: 5,5)",
        "Pilih salah satu referensi jenis suku bunga",
        "Pilih salah satu referensi sandi kredit program pemerintah",
        "Kolom ini harus diisi dengan Kode LJK berasal (Cth: 002)",
        "Kolom ini harus diisi dengan Kode LJK atau Kode Pihak Ketiga Bukan Bank",
        "Kolom ini harus diisi dengan nominal plafon  awal dalam rupiah (Cth: 10000000000)",
        "Kolom ini harus diisi dengan nominal plafon efektif (Cth: 2000000000)",
        "Kolom ini harus diisi dengan nominal realisasi/pencairan dalam rupiah (Cth: 5000000000)",
        "Kolom ini harus diisi dengan nominal denda dalam rupiah (Cth: 100000)",
        "Kolom ini harus diisi dengan nominal baki debet dalam rupiah (Cth: 2000000000)",
        "Kolom ini harus diisi dengan nominal baki debet dalam mata uang asal",
        "Pilih salah satu referensi kode kolektibilitas",
        "Kode kolektibilitas macet, kolom ini harus diisi dengan tanggal pada saat fasilitas dinyatakan macet (Cth: 20141203)",
        "Kode kolektibilitas macet, pilih salah satu referensi kode sebab macet",
        "Kolom ini harus diisi dengan nominal tunggakan pokok dalam rupiah (Cth: 50000000)",
        "Kolom ini harus diisi dengan nominal tunggakan bunga/margin (Cth: 50000000) ",
        "Kolom ini harus diisi dengan jumlah hari tunggakan (Cth: 115)",
        "Kolom ini harus diisi dengan frekuensi tunggakan (Cth: 3)",
        "Kolom ini harus diisi dengan frekuensi restrukturisasi (Cth: 3)",
        "Kolom ini harus diisi dengan tanggal pada saat fasilitas pertama kali (Cth: 20141203)",
        "Kolom ini harus diisi dengan tanggal pada saat fasilitas terakhir kali(Cth: 20141203)",
        "Pilih salah satu referensi kode cara restrukturisasi atau dikosongkan",
        "Pilih salah satu referensi kode kondisi",
        "Kolom ini harus diisi dengan tanggal kondisi dinyatakan (Cth: 20150801) atau dikosongkan",
        "Kolom ini harus diisi dengan format '#' diawal keterangan (Cth: #KUR)",
        "Pilih salah satu referensi kode kantor cabang",
        "Pilih salah satu referensi kode operasi",
        "",
        "",
        "",
        ""
    };
    
    public FrmKredit() {
    }

    public FrmKredit(Kredit entKredit) {
        this.entKredit = entKredit;
    }

    public FrmKredit(HttpServletRequest request, Kredit entKredit) {
        super(new FrmKredit(entKredit), request);
        this.entKredit = entKredit;
    }

    public String getFormName() {
        return FRM_NAME_KREDIT;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public Kredit getEntityObject() {
        return entKredit;
    }

    public void requestEntityObject(Kredit entKredit) {
        try {
            this.requestParam();
            //entKredit.setKredit(getLong(FRM_FIELD_IRREVOCABLE_LC));
            entKredit.setFlagDetail(getString(FRM_FLD_FLAG_DETAIL));
            entKredit.setNoRekening(getString(FRM_FLD_NO_REKENING));
            entKredit.setCif(getString(FRM_FLD_CIF));
            entKredit.setKodeSifat(getString(FRM_FLD_KODE_SIFAT));
            entKredit.setKodeJenisKredit(getString(FRM_FLD_KODE_JENIS_KREDIT));
            entKredit.setKodeAkad(getString(FRM_FLD_KODE_AKAD));
            entKredit.setNoAkadAwal(getString(FRM_FLD_NO_AKAD_AWAL));
            String tglAkadAwal = getString(FRM_FLD_TGL_AKAD_AWAL);
            entKredit.setTglAkadAwal(Formater.formatDate(tglAkadAwal, "yyyy-MM-dd"));
            entKredit.setNoAkadAkhir(getString(FRM_FLD_NO_AKAD_AKHIR));
            String tglAkadAkhir = getString(FRM_FLD_TGL_AKAD_AKHIR);
            entKredit.setTglAkadAkhir(Formater.formatDate(tglAkadAkhir, "yyyy-MM-dd"));
            entKredit.setBaruPerpanjangan(getInt(FRM_FLD_BARU_PERPANJANGAN));
            String tglAwal = getString(FRM_FLD_TGL_AWAL);
            entKredit.setTglAwal(Formater.formatDate(tglAwal, "yyyy-MM-dd"));
            String tglMulai = getString(FRM_FLD_TGL_MULAI);
            entKredit.setTglMulai(Formater.formatDate(tglMulai, "yyyy-MM-dd"));
            String tglTempo = getString(FRM_FLD_TGL_TEMPO);
            entKredit.setTglTempo(Formater.formatDate(tglTempo, "yyyy-MM-dd"));
            entKredit.setKodeKatDbitur(getString(FRM_FLD_KODE_KAT_DBITUR));
            entKredit.setKodeJenisPenggunaan(getString(FRM_FLD_KODE_JENIS_PENGGUNAAN));
            entKredit.setKodeOrientasiPenggunaan(getString(FRM_FLD_KODE_ORIENTASI_PENGGUNAAN));
            entKredit.setKodeSektorEkonomi(getString(FRM_FLD_KODE_SEKTOR_EKONOMI));
            entKredit.setKodeKab(getString(FRM_FLD_KODE_KAB));
            entKredit.setNilai(getDouble(FRM_FLD_NILAI));
            entKredit.setKodeValuta(getString(FRM_FLD_KODE_VALUTA));
            entKredit.setProsentaseBunga(getInt(FRM_FLD_PROSENTASE_BUNGA));
            entKredit.setJenisBunga(getString(FRM_FLD_JENIS_BUNGA));
            entKredit.setKreditPemerintah(getString(FRM_FLD_KREDIT_PEMERINTAH));
            entKredit.setTakeover(getString(FRM_FLD_TAKEOVER));
            entKredit.setSumberDana(getString(FRM_FLD_SUMBER_DANA));
            entKredit.setPlafonAwal(getDouble(FRM_FLD_PLAFON_AWAL));
            entKredit.setPlafon(getDouble(FRM_FLD_PLAFON));
            entKredit.setRealisasi(getDouble(FRM_FLD_REALISASI));
            entKredit.setDenda(getDouble(FRM_FLD_DENDA));
            entKredit.setBakiDebet(getDouble(FRM_FLD_BAKI_DEBET));
            entKredit.setNilaiUangAsal(getDouble(FRM_FLD_NILAI_UANG_ASAL));
            entKredit.setKodeKolektibilitas(getString(FRM_FLD_KODE_KOLEKTIBILITAS));
            String tglMacet = getString(FRM_FLD_TGL_MACET);
            entKredit.setTglMacet(Formater.formatDate(tglMacet, "yyyy-MM-dd"));
            entKredit.setKodeSebabMacet(getString(FRM_FLD_KODE_SEBAB_MACET));
            entKredit.setTunggakanPokok(getDouble(FRM_FLD_TUNGGAKAN_POKOK));
            entKredit.setTunggakanBunga(getDouble(FRM_FLD_TUNGGAKAN_BUNGA));
            entKredit.setJmlHariTunggakan(getInt(FRM_FLD_JML_HARI_TUNGGAKAN));
            entKredit.setFrekuensiTunggakan(getInt(FRM_FLD_FREKUENSI_TUNGGAKAN));
            entKredit.setFrekuensiRestrukturisasi(getInt(FRM_FLD_FREKUENSI_RESTRUKTURISASI));
            String tglRestrukturisasiAwal = getString(FRM_FLD_TGL_RESTRUKTURISASI_AWAL);
            entKredit.setTglRestrukturisasiAwal(Formater.formatDate(tglRestrukturisasiAwal, "yyyy-MM-dd"));
            String tglRestrukturisasiAkhir = getString(FRM_FLD_TGL_RESTRUKTURISASI_AKHIR);
            entKredit.setTglRestrukturisasiAkhir(Formater.formatDate(tglRestrukturisasiAkhir, "yyyy-MM-dd"));
            
            entKredit.setKodeCara(getString(FRM_FLD_KODE_CARA));
            entKredit.setKodeKondisi(getString(FRM_FLD_KODE_KONDISI));
            String tglKondisi = getString(FRM_FLD_TGL_KONDISI);
            entKredit.setTglKondisi(Formater.formatDate(tglKondisi, "yyyy-MM-dd"));
            entKredit.setKeterangan(getString(FRM_FLD_KETERANGAN));
            entKredit.setKodeKantorCabang(getString(FRM_FLD_KODE_KANTOR_CABANG));
            entKredit.setOperasiData(getString(FRM_FLD_OPERASI_DATA));
            //entKredit.setOpenDate(getDate(FRM_FLD_OPEN_DATE));
            String openDate = getString(FRM_FLD_OPEN_DATE);
            entKredit.setOpenDate(Formater.formatDate(openDate, "yyyy-MM-dd"));
            entKredit.setStatusData(getInt(FRM_FLD_STATUS_DATA));
            entKredit.setPeriodeId(getLong(FRM_FIELD_PERIODE_ID));
            entKredit.setStatusPerubahanData(getInt(FRM_FIELD_STATUS_PERUBAHAN_DATA));
            
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
