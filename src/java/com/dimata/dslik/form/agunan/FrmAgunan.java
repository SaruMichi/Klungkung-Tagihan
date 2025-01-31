/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.agunan;

import com.dimata.dslik.entity.agunan.Agunan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.util.Formater;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmAgunan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private Agunan entAgunan;
    public static final String FRM_NAME_AGUNAN = "FRM_NAME_AGUNAN";
    public static final int FRM_FIELD_AGUNAN_OID = 0;
    public static final int FRM_FIELD_FLAG_DETAIL = 1;
    public static final int FRM_FIELD_KODE_REGISTER_AGUNAN = 2;
    public static final int FRM_FIELD_NO_REKENING = 3;
    public static final int FRM_FIELD_CIF = 4;
    public static final int FRM_FIELD_KODE_STATUS_AGUNAN = 5;
    public static final int FRM_FIELD_KODE_JENIS_AGUNAN = 6;
    public static final int FRM_FIELD_PERINGKAT_AGUNAN = 7;
    public static final int FRM_FIELD_KODE_LEMBAGA_PEMERINGKAT = 8;
    public static final int FRM_FIELD_KODE_JENIS_PENGIKATAN = 9;
    public static final int FRM_FIELD_TGL_PENGIKATAN = 10;
    public static final int FRM_FIELD_NAMA_PEMILIK_AGUNAN = 11;
    public static final int FRM_FIELD_BUKTI_KEPEMILIKAN = 12;
    public static final int FRM_FIELD_ALAMAT_AGUNAN = 13;
    public static final int FRM_FIELD_KODE_KAB_LOKASI_AGUNAN = 14;
    public static final int FRM_FIELD_NILAI_AGUNAN_NJOP = 15;
    public static final int FRM_FIELD_NILAI_AGUNAN_LJK = 16;
    public static final int FRM_FIELD_TGL_PENILAIAN_LJK = 17;
    public static final int FRM_FIELD_NILAI_AGU_PENILAI_INDEP = 18;
    public static final int FRM_FIELD_NAMA_PENILAI_INDEP = 19;
    public static final int FRM_FIELD_TGL_PENILAIAN_PENILAI_INDEP = 20;
    public static final int FRM_FIELD_STATUS_PARIPASU = 21;
    public static final int FRM_FIELD_STATUS_KREDIT_JOIN = 22;
    public static final int FRM_FIELD_DIASURANSIKAN = 23;
    public static final int FRM_FIELD_KETERANGAN = 24;
    public static final int FRM_FIELD_KODE_KANTOR_CABANG = 25;
    public static final int FRM_FIELD_OPERASI_DATA = 26;
    public static final int FRM_FIELD_OPEN_DATE = 27;
    public static final int FRM_FIELD_STATUS_DATA = 28;
    public static final int FRM_FIELD_PERIODE_ID = 29;
    public static final int FRM_FIELD_PROSENTASE_PARIPASU = 30;
    public static final int FRM_FIELD_STATUS_DATA_PERUBAHAN = 31;
    public static final int FRM_FIELD_KODE_JENIS_SEGMENT_FASILITAS = 32;
    
    public static String[] fieldNames = {
        "FRM_FIELD_AGUNAN_OID",
        "FRM_FIELD_FLAG_DETAIL",
        "FRM_FIELD_KODE_REGISTER_AGUNAN",
        "FRM_FIELD_NO_REKENING",
        "FRM_FIELD_CIF",
        "FRM_FIELD_KODE_STATUS_AGUNAN",
        "FRM_FIELD_KODE_JENIS_AGUNAN",
        "FRM_FIELD_PERINGKAT_AGUNAN",
        "FRM_FIELD_KODE_LEMBAGA_PEMERINGKAT",
        "FRM_FIELD_KODE_JENIS_PENGIKATAN",
        "FRM_FIELD_TGL_PENGIKATAN",
        "FRM_FIELD_NAMA_PEMILIK_AGUNAN",
        "FRM_FIELD_BUKTI_KEPEMILIKAN",
        "FRM_FIELD_ALAMAT_AGUNAN",
        "FRM_FIELD_KODE_KABLOKASI_AGUNAN",
        "FRM_FIELD_NILAI_AGUNAN_NJOP",
        "FRM_FIELD_NILAI_AGUNAN_LJK",
        "FRM_FIELD_TGL_PENILAIAN_LJK",
        "FRM_FIELD_NILAI_AGU_PENILAI_INDEP",
        "FRM_FIELD_NAMA_PENILAI_INDEP",
        "FRM_FIELD_TGL_PENILAIAN_PENILAI_INDEP",
        "FRM_FIELD_STATUS_PARIPASU",
        "FRM_FIELD_STATUS_KREDIT_JOIN",
        "FRM_FIELD_DIASURANSIKAN",
        "FRM_FIELD_KETERANGAN",
        "FRM_FIELD_KODE_KANTOR_CABANG",
        "FRM_FIELD_OPERASI_DATA",
        "FRM_FIELD_OPEN_DATE",
        "FRM_FIELD_STATUS_DATA",
        "FRM_FIELD_PERIODE_ID",
        "FRM_FIELD_PROSENTASE_PARIPASU",
        "FRM_FIELD_STATUS_DATA_PERUBAHAN",
        "FRM_FIELD_KODE_JENIS_SEGMENT_FASILITAS"
    };

    public static int[] fieldTypes = {
        TYPE_LONG ,
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
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_FLOAT,
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
        TYPE_FLOAT,
        TYPE_INT,
        TYPE_STRING
    };
    
    public static String[] fieldQuestion = {
        "",
        "",
        "- Kolom ini diisi dengan Kode Register/Nomor Agunan."
            + "&#010;- Kode Register/Nomor Agunan harus unik (1 (satu) kode register/nomor agunan digunakan untuk 1 (satu) agunan)."
            + "&#010;- Kode Register/Nomor Agunan yang telah digunakan oleh satu agunan tidak boleh digunakan untuk agunan lainnya (no reuse/no recycle)."
            + "&#010;- Kode register/nomor agunan yang telah dilaporkan tidak boleh berubah (konsisten)."
            + "&#010;- Apabila kode register agunan mengandung karakter selain alfabet dan angka, maka karakter tersebut tidak perlu disertakan.",
        "- Kolom ini diisi dengan nomor rekening fasilitas yang dijamin dengan agunan yang dilaporkan."
            + "&#010;- Nomor yang diisi pada kolom ini harus dilaporkan pula pada segmen data fasilitas yang terkait."
            + "&#010;- Apabila nomor rekening mengandung karakter selain alfabet dan angka, maka karakter tersebut tidak perlu disertakan.",
        "- Kolom ini diisi dengan nomor CIF debitur penerima fasilitas yang dijamin dengan agunan yang dilaporkan."
            + "&#010;- Nomor CIF debitur yang diisi pada kolom ini harus dilaporkan pula pada segmen debitur (perorangan atau badan usaha)."
            + "&#010;- Apabila nomor CIF mengandung karakter selain alfabet dan angka, maka karakter tersebut tidak perlu disertakan.",
        "- Status agunan diisi dengan informasi mengenai status ketersediaan wujud fisik agunan/jaminan."
            + "-&#010; Kolom ini diisi dengan kode status agunan dengan referensi.",
        "- Kolom ini diisi dengan Kode Jenis Agunan dengan referensi ",
        "- Kolom ini diisi dengan peringkat agunan."
            + "&#010;- Kolom ini hanya diisi apabila jenis agunan berupa surat berharga dan status agunan adalah 1-Tersedia.",
        "- Kolom ini diisi dengan kode lembaga pemeringkat dengan referensi."
            + "&#010;- Kolom ini hanya diisi apabila jenis agunan berupa surat berharga dan status agunan adalah 1-Tersedia",
        "- Kolom ini diisi jenis pengikatan yang dilakukan oleh Pelapor atas agunan/jaminan yang diserahkan debitur. "
            + "&#010;- Kolom ini diisi dengan Kode Jenis Pengikatan sesuai dengan referensi",
        "- Kolom ini diisi dengan tanggal agunan/jaminan diikat."
            + "&#010;- Kolom ini wajib diisi apabila status agunan adalah “1-Tersedia”.",
        "- Kolom ini diisi dengan nama pemilik agunan/jaminan sesuai dengan yang tercantum dalam dokumen kepemilikan.",
        "- Kolom ini diisi dengan informasi mengenai bukti kepemilikan agunan/jaminan.",
        "- Kolom ini diisi dengan Kolom ini diisi alamat lengkap agunan disertai dengan informasi kelurahan dan kecamatan."
            + "&#010;- Untuk agunan berupa barang tidak bergerak seperti tanah dan bangunan, alamat yang dilaporkan adalah alamat barang agunan yang tertera pada dokumen."
            + "&#010;- Untuk barang bergerak seperti kendaraan bermotor dan/atau persediaan barang, alamat yang dilaporkan adalah lokasi penyimpanan agunan."
            + "&#010;- Untuk agunan dalam bentuk surat berharga, alamat yang dilaporkan adalah alamat bank penyimpan dokumen surat berharga.",
        "- Kolom ini diisi kode Kabupaten/Kota sesuai dengan alamat agunan sebagaimana tercantum pada Referensi Kode Kabupaten/Kota (DATI II)."
            + "&#010;- Kolom ini tidak dapat diisi dengan kode sandi Dati II yang berstatus tidak aktif atau tingkat Provinsi.",
        "- Kolom ini diisi dengan nilai nominal berdasarkan Nilai Jual Obyek Pajak (NJOP) atau nilai wajar atas barang yang dijadikan agunan."
            + "&#010;- Untuk agunan berupa tanah dan bangunan diisi dengan nilai NJOP atas barang yang dijadikan agunan."
            + "&#010;- Untuk agunan selain tanah dan bangunan, kolom ini diisi dengan nilai wajar atas barang yang dijadikan agunan."
            + "&#010;- Kolom ini diisi dalam satuan penuh mata uang Rupiah."
            + "&#010;- Kolom ini wajib diisi apabila status agunan adalah 1-Tersedia.",
        "- Kolom ini diisi dengan nilai agunan berdasarkan penilaian pelapor atas barang yang dijadikan agunan berupa Barang Bergerak, Barang Tak Bergerak, Persediaan Barang, dan Lainnya."
            + "&#010;- Untuk jaminan berupa cash collateral, dan surat berharga atau garansi Bank atau Pemerintah/Lembaga Penjamin Kredit/Prime Bank, kolom ini diisi dengan nilai nominalnya."
            + "&#010;- Untuk Kredit Pemilikan Rumah (KPR) dan Kredit Kendaraan Bermotor (KKB), maka kolom nilai agunan ini harus diisi dengan"
            + "&#010;i. nilai pasar saat dilakukan pengikatan untuk KPR dan,"
            + "&#010;ii. hasil penilaian bank saat dilakukan pengikatan untuk KKB."
            + "&#010;Dalam hal terjadi penilaian ulang terhadap agunan tersebut, maka kolom Nilai Agunan ini diisi dengan hasil penilaian terbaru."
            + "&#010;- Nilai agunan yang dilaporkan pada formulir ini adalah nilai agunan dalam satuan penuh mata uang Rupiah."
            + "&#010;- Kolom ini wajib diisi apabila status agunan adalah 1-Tersedia.",
        "- Kolom ini berisi tanggal penilaian agunan yang dilakukan oleh Pelapor."
            + "&#010;- Kolom ini wajib diisi apabila status agunan adalah 1-Tersedia.",
        "- Kolom ini diisi dengan nilai agunan berdasarkan penilaian oleh penilai independen dalam satuan penuh mata uang Rupiah."
            + "&#010;- Jaminan berupa cash collateral dan surat berharga atau garansi bank atau Pemerintah/Lembaga Penjamin Kredit/Prime Bank, diisi nilai nominalnya.",
        "- Kolom ini berisi nama lembaga penilai independen."
            + "&#010;- Kolom ini bersifat wajib diisi jika kolom Nilai Agunan Penilai Independen diisi.",
        "- Kolom ini berisi tanggal penilaian agunan yang dilakukan oleh penilai independen.",
        "- Kolom ini diisi dengan Y jika agunan paripasu dan diisi T jika agunan bukan paripasu.",
        "- Kolom ini diisi dengan “Y” jika agunan merupakan agunan fasilitas kredit joint account dan diisi “T” jika agunan tidak diasuransikan.",
        "- Kolom ini diisi dengan “Y” jika agunan diasuransikan dan diisi “T” jika agunan tidak diasuransikan.",
        "- Kolom ini diisi apabila ada keterangan tambahan/keterangan lainnya yang diperlukan terkait agunan yang dilaporkan.",
        "- Kolom ini berisi Kode Kantor Cabang dimana agunan dicatat/diregister.",
        "",
        "",
        "",
        "",
        "- Kolom ini berisi besar persentase nilai agunan yang diserahkan debitur kepada pelapor untuk menjamin fasilitas yang diberikan."
            + "&#010;- Kolom ini wajib diisi apabila kolom Status Paripasu adalah Y.",
        "",
        "- Kolom ini diisi dengankode jenis segmen fasilitas yang diperoleh debitur."
    };
    
    public static String[] fieldError = {
        "",
        "",
        "Kolom ini harus diisi dengan kode register/nomor agunan.",
        "Kolom ini harus diisi dengan nomor rekening fasilitas yang dijamin dengan agunan.",
        "Kolom ini harus diisi dengan CIF debitur penerima fasilitas yang dijamin dengan agunan yang dilaporkan.",
        "Pilih salah satu kode status agunan.",
        "Pilih salah satu kode jenis agunan.",
        "Kolom ini harus diisi dengan peringkat agunan.",
        "Pilih salah satu kode lembaga pemeringkat.",
        "Pilih salah satu kode jenis pengikatan yang dilakukan oleh pelapor atas agunan/jaminan yang diserahkan debitur.",
        "Kolom ini harus diisi dengan tanggal agunan/jaminan diikat.",
        "Kolom ini harus diisi dengan nama pemilik agunan/jaminan sesuai dengan yang tercantum dalam dokumen kepemilikan.",
        "Kolom ini harus diisi dengan informasi mengenai kepemilikan agunan/jaminan.",
        "Kolom ini harus diisi dengan alamat lengkap agunan disertai dengan informasi kelurahan dan kecamatan.",
        "Pilih salah satu kode Kabupaten/Kota sesuai dengan alamat agunan.",
        "Kode Status Agunan tersedia, Kolom ini harus diisi dengan nilai nominal berdasarkan Nilai Jual Obyek Pajak (NJOP) atau nilai wajar atas barang yang dijadikan agunan.",
        "Kode Status Agunan tersedia, Kolom ini harus diisi dengan nilai agunan berdasarkan penilaian pelapor atas barang yang dijadikan agunan.",
        "Kolom ini harus diisi dengan tanggal penilaian agunan yang dilakukan oleh pelapor.",
        "Kolom ini harus diisi dengan nilai agunan berdasarkan penilaian oleh penilai independen dalam satuan penuh mata uang Rupiah.",
        "Kolom ini harus diisi dengan nama lembaga penilai independen.",
        "Kolom ini harus diisi dengan tanggal penilaian agunan yang dilakukan oleh penilai pelapor.",
        "Pilih salah satu status paripasu agunan.",
        "Pilih salah satu status kredit join agunan.",
        "Pilih salah satu status diasuransikan.",
        "Kolom ini harus diisi dengan keterangan tambahan/keterangan lainnya.",
        "Pilih salah satu kode kantor cabang dimana agunan dicatat/dilapor.",
        "",
        "",
        "",
        "",
        "Kolom ini harus diisi dengan vesar persentase nilai agunan yang diserahkan debitur kepada pelapor.",
        "",
        "Pilih salah satu kode jenis segmen fasilitas yang diperoleh debitur."
    };

    public FrmAgunan() {
    }

    public FrmAgunan(Agunan entAgunan) {
        this.entAgunan = entAgunan;
    }

    public FrmAgunan(HttpServletRequest request, Agunan entAgunan) {
        super(new FrmAgunan(entAgunan), request);
        this.entAgunan = entAgunan;
    }

    public String getFormName() {
        return FRM_NAME_AGUNAN;
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

    public Agunan getEntityObject() {
        return entAgunan;
    }

    public void requestEntityObject(Agunan entAgunan) {
        try {
            this.requestParam();
//            entAgunan.setAgunanOid(getLong(FRM_FIELD_AGUNANOID));
            entAgunan.setFlagDetail(getString(FRM_FIELD_FLAG_DETAIL));
            entAgunan.setKodeRegisterAgunan(getString(FRM_FIELD_KODE_REGISTER_AGUNAN));
            entAgunan.setNoRekening(getString(FRM_FIELD_NO_REKENING));
            entAgunan.setCif(getString(FRM_FIELD_CIF));
            entAgunan.setKodeStatusAgunan(getString(FRM_FIELD_KODE_STATUS_AGUNAN));
            entAgunan.setKodeJenisAgunan(getString(FRM_FIELD_KODE_JENIS_AGUNAN));
            entAgunan.setPeringkatAgunan(getString(FRM_FIELD_PERINGKAT_AGUNAN));
            entAgunan.setKodeLembagaPemeringkat(getString(FRM_FIELD_KODE_LEMBAGA_PEMERINGKAT));
            entAgunan.setKodeJenisPengikatan(getString(FRM_FIELD_KODE_JENIS_PENGIKATAN));
            String tglPengikatan = getString(FRM_FIELD_TGL_PENGIKATAN);
            entAgunan.setTglPengikatan(Formater.formatDate(tglPengikatan, "yyyy-MM-dd"));
            entAgunan.setNamaPemilikAgunan(getString(FRM_FIELD_NAMA_PEMILIK_AGUNAN));
            entAgunan.setBuktiKepemilikan(getString(FRM_FIELD_BUKTI_KEPEMILIKAN));
            entAgunan.setAlamatAgunan(getString(FRM_FIELD_ALAMAT_AGUNAN));
            entAgunan.setKodeKabLokasiAgunan(getString(FRM_FIELD_KODE_KAB_LOKASI_AGUNAN));
            entAgunan.setNilaiAgunanNjop(getFloat(FRM_FIELD_NILAI_AGUNAN_NJOP));
            entAgunan.setNilaiAgunanLjk(getFloat(FRM_FIELD_NILAI_AGUNAN_LJK));
            String tglPenilaianLjk = getString(FRM_FIELD_TGL_PENILAIAN_LJK);
            entAgunan.setTglPenilaianLjk(Formater.formatDate(tglPenilaianLjk, "yyyy-MM-dd"));
            entAgunan.setNilaiAguPenilaiIndep(getFloat(FRM_FIELD_NILAI_AGU_PENILAI_INDEP));
            entAgunan.setNamaPenilaiIndep(getString(FRM_FIELD_TGL_PENILAIAN_PENILAI_INDEP));
            String tglPenilaianPenilaiIndep= getString(FRM_FIELD_TGL_PENILAIAN_PENILAI_INDEP);
            entAgunan.setTglPenilaianPenilaiIndep(Formater.formatDate(tglPenilaianPenilaiIndep, "yyyy-MM-dd"));
            entAgunan.setStatusParipasu(getString(FRM_FIELD_STATUS_PARIPASU));
            entAgunan.setStatusKreditJoin(getString(FRM_FIELD_STATUS_KREDIT_JOIN));
            entAgunan.setDiasuransikan(getString(FRM_FIELD_DIASURANSIKAN));
            entAgunan.setKeterangan(getString(FRM_FIELD_KETERANGAN));
            entAgunan.setKodeKantorCabang(getString(FRM_FIELD_KODE_KANTOR_CABANG));
            entAgunan.setOperasiData(getString(FRM_FIELD_OPERASI_DATA));
            String openDate= getString(FRM_FIELD_OPEN_DATE);
            entAgunan.setOpenDate(Formater.formatDate(openDate, "yyyy-MM-dd"));
            entAgunan.setStatusData(getInt(FRM_FIELD_STATUS_DATA));
            entAgunan.setPeriodeId(getLong(FRM_FIELD_PERIODE_ID));
            entAgunan.setProsentaseParipasu(getDouble(FRM_FIELD_PROSENTASE_PARIPASU));
            entAgunan.setStatusDataPerubahan(getInt(FRM_FIELD_STATUS_DATA_PERUBAHAN));
            entAgunan.setKodeJenisSegementFasilitas(getString(FRM_FIELD_KODE_JENIS_SEGMENT_FASILITAS));
            
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
