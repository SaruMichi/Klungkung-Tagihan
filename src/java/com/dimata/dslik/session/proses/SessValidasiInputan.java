/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dimata005
 */
public class SessValidasiInputan {

    static String statusProsesValidasi = "";
    static String statusProsesValidasiAgunan = "";
    static String statusProsesValidasiBankGaransi = "";
    static String statusProsesValidasiKredit = "";
    // Pattern regex RFC2822 (aturan alamat email yang valid)
    private static final Pattern rfc2822 = Pattern
            .compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
    private static final Pattern onlycharacterspace = Pattern
            .compile("[a-zA-Z\\\\s\\'\\\"]+");

    private static final String[] specialChar = {"+or+", "+and+", "<", ">", "'", "/*", "\"", "--", ";", "\n", "\r", ".", "\\", "/", "%", "²", "[", "]", "&", ":", "`", "=", "+", "|", "_", "*", "-"};
    private static final String[] specialCharReplace = {" or ", " and ", "&lt;", "&gt;", "`", "", "", "", "", "", "", "", "", "", "", "2", "", "", " dan ", "", "", "", "", "", "", "", ""};

    private static final String[] specialCharx = {"+or+", "+and+", "<", ">", "'", "/*", "\"", "--", ";", "\n", "\r", "\\", "(", ")", "/", ":", ",", "-", "_", "[", "]", "=", "*"};
    private static final String[] specialCharReplacex = {" or ", " and ", "&lt;", "&gt;", "", "", "", "", "", "", "", "", "", "", "-", "", "", "", "", "", "", "", ""};

    private static final String[] specialCharIdentitas = {"+or+", "+and+", "<", ">", "'", "/*", "\"", "--", ";", "\n", "\r", ".", "\\", "/", ",", "&", "=", "[", "]", ":", "+", "~", "_", ";", "*"};
    private static final String[] specialCharReplaceIdentitas = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

    //"=","[","]",":","+","~","_",";"
    //"","","","","","","",""
    public static final int FLD_DATA_NIK = 0;
    public static final int FLD_DATA_TELP = 1;
    public static final int FLD_DATA_EMAIL = 2;
    public static final int FLD_DATA_MELANGGAR_BMPK = 3;
    public static final int FLD_DATA_MELAMPAU_BMPK = 4;
    public static final int FLD_DATA_KODE_GELAR = 5;
    public static final int FLD_DATA_KODE_BIDANG_USAHA = 6;
    public static final int FLD_DATA_KODE_TEMPAT_BEKERJA = 7;
    public static final int FLD_DATA_KODE_KELURAHAN = 8;
    public static final int FLD_DATA_KODE_KECAMATAN = 9;
    public static final int FLD_DATA_KODE_PEKERJAAN = 10;
    public static final int FLD_DATA_TEMPAT_LAHIR_KTP = 11;
    public static final int FLD_DATA_KODE_POS = 12;
    public static final int FLD_DATA_KODE_KAB_KOTA = 13;
    public static final int FLD_DATA_TEMPAT_BEKERJA = 14;
    public static final int FLD_DATA_KELURAHAN = 15;
    public static final int FLD_DATA_KECAMATAN = 16;

    public static final int FLD_AGUNAN_OWNER = 17;
    public static final int FLD_AGUNAN_BUKTI_KEPEMILIKAN = 18;
    public static final int FLD_AGUNAN_ALAMAT = 19;

    public static final int FLD_DB_NAMA = 20;
    public static final int FLD_DB_NAMA_LENGKAP = 21;
    public static final int FLD_DB_NAMA_BADAN_USAHA = 22;
    public static final int FLD_DB_NAMA_IBU_KANDUNG = 23;
    public static final int FLD_DB_HP = 24;

    public static final int FLD_DB_ALAMAT_TEMPAT_BEKERJA = 24;
    public static final int FLD_DB_NPWP = 25;
    public static final int FLD_DB_ALAMAT = 26;

    // Method untuk memeriksa alamat email
    public static String checkAlamatEmail(String alamatEmail) {
        String hasil = null;
        try {
            if (alamatEmail != null || !alamatEmail.equals("")) {
                if (rfc2822.matcher(alamatEmail).matches()) {
                    hasil = alamatEmail;
                } else {
                    hasil = "";
                    statusProsesValidasi = statusProsesValidasi + "<br> Format Email Salah : " + alamatEmail;
                }
            } else {
                hasil = "";
                //statusProsesValidasi = statusProsesValidasi + "<br> Format Email Salah : "+alamatEmail;
            }
        } catch (Exception ex) {
            hasil = "";
        }
        return hasil;
    }

    public static String checkTelephone(String telp) {
        String hasil = null;
        try {
            telp = telp.replaceAll("[^0-9]", "");//checkKarakter(telp,1, FLD_DB_HP);
            if (telp != null && !telp.equals("")) {
                if (telp.matches("[0-9]+")) {
                    hasil = telp;
                } else {
                    hasil = "0";
                    statusProsesValidasi = statusProsesValidasi + "<br> Format Telephone Salah";
                }
            } else {
                hasil = "0";
                statusProsesValidasi = statusProsesValidasi + "<br> Format Telephone Salah";
            }

        } catch (Exception ex) {
            hasil = "0";
            statusProsesValidasi = statusProsesValidasi + "<br> Format Telephone Salah";
        }
        return hasil;
    }

    public static String checkHandphone(String telp, int typeNsb) {
        String hasil = null;
        try {
            telp = telp.replaceAll("[^0-9]", "");//checkKarakter(telp,1, FLD_DB_HP);
            if (telp != null && !telp.equals("")) {
                if (telp.matches("[0-9]+")) {
                    hasil = telp;
                } else {
                    hasil = "";
                    if (typeNsb == 1) {
                        statusProsesValidasi = statusProsesValidasi + "<br> Format No Handphone Salah";
                    }
                }
            } else {
                hasil = "";
                if (typeNsb == 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> Format No Handphone Salah";
                }
            }

        } catch (Exception ex) {
            hasil = "";
            if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Format No Handphone Salah";
            }
        }
        return hasil;
    }

    public static String checkMelanggarBmpk(String melanggarBmpk) {
        String hasil = null;
        try {
            if (melanggarBmpk != null && !melanggarBmpk.equals("") && !melanggarBmpk.equals(" ") && !melanggarBmpk.equals(".")) {
                hasil = melanggarBmpk;
                if (!hasil.equals("Y") && !hasil.equals("T")) {
                    hasil = "T";
                    statusProsesValidasi = statusProsesValidasi + "<br> Melanggar BMPK, Default T, perbaiki di core ";
                }
            } else {
                hasil = "T";
                statusProsesValidasi = statusProsesValidasi + "<br> Melanggar BMPK, Default T, perbaiki di core ";
            }

        } catch (Exception ex) {
            hasil = "T";
            statusProsesValidasi = statusProsesValidasi + "<br> Melanggar BMPK, Default T, perbaiki di core";
        }
        return hasil;
    }

    public static String checkMelampauBmpk(String melampauBmpk) {
        String hasil = null;
        try {
            if (melampauBmpk != null && !melampauBmpk.equals("") && !melampauBmpk.equals(" ") && !melampauBmpk.equals(".")) {
                hasil = melampauBmpk;
                if (!hasil.equals("Y") && !hasil.equals("T")) {
                    hasil = "T";
                    statusProsesValidasi = statusProsesValidasi + "<br> Melanggar BMPK, Default T, perbaiki di core ";
                }
            } else {
                hasil = "T";
                statusProsesValidasi = statusProsesValidasi + "<br> Melampaui BMPK, default T, perbaiki di core";
            }

        } catch (Exception ex) {
            hasil = "T";
            statusProsesValidasi = statusProsesValidasi + "<br> Melampaui BMPK, default T, perbaiki di core";
        }
        return hasil;
    }

    public static String checkStatusGelarDebitur(String StatusGelarDebitur, int typeNsb) {
        String hasil = null;
        try {
            if (StatusGelarDebitur != null && !StatusGelarDebitur.equals("")) {
                hasil = StatusGelarDebitur;
            } else {
                hasil = "99";
                if (typeNsb == 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> Status Gelar Kosong / Mapping Tidak Lengkap / Mapping Data ke Lainnya";
                }
            }
        } catch (Exception ex) {
            hasil = "99";
            //statusProsesValidasi = statusProsesValidasi + "<br> Status Gelar Kosong / Mapping Tidak Lengkap, Data di arahkan langsung ke mapping kode gelar lainnya";
            if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Status Gelar Kosong / Mapping Tidak Lengkap  / Mapping Data ke Lainnya";
            }
        }
        return hasil;
    }

    public static String checkBidangUsaha(String bidangUsaha, int typeNsb) {
        String hasil = null;
        try {
            if (bidangUsaha != null && !bidangUsaha.equals("") && !bidangUsaha.equals(" ")) {
                hasil = bidangUsaha;
            } else {
                hasil = "";
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br>Bidang Usaha, Wajib diisi";
                }

            }
        } catch (Exception ex) {
            hasil = "";
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br>Bidang Usaha, Wajib diisi";
            }
        }
        return hasil;
    }

    public static String checkBidangUsahaIndividu(String bidangUsaha, int typeNsb) {
        String hasil = null;
        try {
            if (bidangUsaha != null && !bidangUsaha.equals("") && !bidangUsaha.equals(" ")) {
                hasil = bidangUsaha;
            } else {
                hasil = "";
                if (typeNsb == 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br>Bidang Usaha, Wajib diisi";
                }

            }
        } catch (Exception ex) {
            hasil = "";
            if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br>Bidang Usaha, Wajib diisi";
            }
        }
        return hasil;
    }

    public static String checkBentukBadanUsaha(String data, int typeNsb) {
        String hasil = "";
        try {
            if (data != null && !data.equals("") && !data.equals(" ")) {
                hasil = data;
            } else {
                hasil = "";
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br>Data Bentuk Badan Usaha, Wajib di perbaiki di core";
                }
            }
        } catch (Exception ex) {
            hasil = "";
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br>Data Bentuk Badan Usaha, Wajib di perbaiki di core";
            }
        }
        return hasil;
    }

    public static String checkGolonganDebitur(String bidangUsaha, String cif) {
        String hasil = null;
        try {
            if (bidangUsaha != null && !bidangUsaha.equals("") && !bidangUsaha.equals(" ")) {
                hasil = bidangUsaha;
            } else {
                hasil = "";
                statusProsesValidasi = statusProsesValidasi + "<br>Data Golongan Debitur, Wajib diisi";
            }

            //cek apakah ada data pengurus ?
            boolean xxx = PstPengurusAtauPemilik.checkCIF(cif);
            if (xxx) {
                statusProsesValidasi = statusProsesValidasi + "<br>Data Pengurus atau Pemilik Tidak Ada, Wajib diisi";
            }
        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasi = statusProsesValidasi + "<br> Data Golongan Debitur, Wajib diisi";
        }
        return hasil;
    }

    public static String checkKodePekerjaan(String kodepekerjaan, int typeNsb) {
        String hasil = null;
        try {
            if (kodepekerjaan != null && !kodepekerjaan.equals("") && !kodepekerjaan.equals(" ")) {
                hasil = kodepekerjaan;
            } else {
                hasil = "";
                if (typeNsb == 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br>Kode Pekerjaan, Wajib di perbaiki di core ";
                }

            }

        } catch (Exception ex) {
            hasil = "";
            if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br>Kode Pekerjaan, Wajib di perbaiki di core ";
            }

        }
        return hasil;
    }

    public static String checkKodePos(String kodepos) {
        String hasil = null;
        try {
            if (kodepos != null && !kodepos.equals("") && !kodepos.equals(" ")) {
                hasil = kodepos.replace("\\", " ");//; entDebitur.getAlamat().replace("\\", " "));
                hasil = hasil.replace(" ", "");//; entDebitur.getAlamat().replace("\\", " "));
                if (hasil.length() < 5) {
                    hasil="";
                    statusProsesValidasi = statusProsesValidasi + "<br>KODE POS, panjang minimum 5, Wajib di perbaiki di core";
                }
            } else {
                hasil = "";
                statusProsesValidasi = statusProsesValidasi + "<br> Kode pos, Wajib di perbaiki di core";
            }

        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasi = statusProsesValidasi + "<br> Kode pos, Wajib di perbaiki di core";
        }
        return hasil;
    }

    public static String checkKodeDati(String kodedati) {
        String hasil = null;
        try {
            if (kodedati != null && !kodedati.equals("") && !kodedati.equals(" ")) {
                hasil = kodedati;
            } else {
                hasil = "";
                statusProsesValidasi = statusProsesValidasi + "<br> Kode Kab/Kota , Wajib di perbaiki di core";
            }

        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasi = statusProsesValidasi + "<br> Kode Kab/Kota, Wajib di perbaiki di core";
        }
        return hasil;
    }

    public static String checkIdentitasUsaha(String identitasUsaha, int typeNsb) {
        String hasil = "";
        try {
            if (identitasUsaha != null && !identitasUsaha.equals("") && !identitasUsaha.equals(" ")) {
                hasil = identitasUsaha;
            } else {
                hasil = "";
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> Kode Identitas Usaha, Wajib diisi";
                }

            }

        } catch (Exception ex) {
            hasil = "";
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Kode Identitas Usaha, Wajib diisi";
            }
        }
        return hasil;
    }

    public static String checkTempatPendirianUsaha(String data, int typeNsb) {
        String hasil = "";

        try {
            if (data != null && !data.equals("") && !data.equals(" ")) {
                hasil = data;
            } else {
                hasil = "";
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> Data Tempat Pendirian, Wajib diisi";
                }
            }

        } catch (Exception ex) {
            hasil = "";
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Data Tempat Pendirian, Wajib diisi";
            }
        }
        return hasil;
    }

    public static String checkNoAktePendirian(String data, int typeNsb) {
        String hasil = "";
        try {
            if (data != null && !data.equals("") && !data.equals(" ")) {
                hasil = data;
            } else {
                hasil = "";
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> No akte pendirian, Wajib diisi";
                }
            }

        } catch (Exception ex) {
            hasil = "";
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> No akte pendirian, Wajib diisi";
            }
        }
        return hasil;
    }

    public static Date checkTglAktePendirian(Date data, int typeNsb) {
        Date hasil = null;
        try {
            if (data != null) {
                hasil = data;
            } else {
                hasil = null;
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> Tanggal akte pendirian , Wajib di perbaiki di core";
                }

            }

        } catch (Exception ex) {
            hasil = null;
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Tanggal akte pendirian , Wajib di perbaiki di core";
            }
        }
        return hasil;
    }

    public static String checkNoAktePerubahan(String data, int typeNsb) {
        String hasil = null;
        try {
            if (data != null && !data.equals("") && !data.equals(" ")) {
                hasil = data.replaceAll(":", "");
            } else {
                hasil = "";
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> No akte Perubahan Akhir, Wajib di perbaiki di core";
                }
            }
        } catch (Exception ex) {
            hasil = "";
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> No akte Perubahan Akhir, Wajib di perbaiki di core";
            }
        }
        return hasil;
    }

    public static Date checkTglAktePerubahan(Date data, int typeNsb) {
        Date hasil = null;
        try {
            if (data != null) {
                hasil = data;
            } else {
                hasil = null;
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> Tanggal akte Perubahan Akhir, Wajib di perbaiki di core";
                }

            }

        } catch (Exception ex) {
            hasil = null;
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Tanggal akte Perubahan Akhir, Wajib di perbaiki di core";
            }
        }
        return hasil;
    }

    public static String checkLembagaPemeringkat(String data, int typeNsb) {
        String hasil = null;
        try {
            if (data != null && !data.equals("") && !data.equals(" ")) {
                hasil = data;
            } else {
                hasil = "";
                if (typeNsb != 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> Lembaga Pemeringkat, Wajib diisi";
                }

            }
        } catch (Exception ex) {
            hasil = "";
            if (typeNsb != 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Lembaga Pemeringkat, Wajib diisi";
            }
        }
        return hasil;
    }

//     public static String checkBidangUsaha(String data) {
//        String hasil = null;
//        try{
//            if (data!=null && !data.equals("")) {
//                hasil = data;
//            } else {
//                hasil = "";
//                statusProsesValidasi = statusProsesValidasi + "<br> No akte Perubahan Akhir Harus di Isi";
//            }
//                
//        }catch(Exception ex){
//            hasil="";
//            statusProsesValidasi = statusProsesValidasi + "<br> No akte Perubahan Akhir Harus di Isi";
//        }   
//        return hasil;
//    }    
    public static String replaceKarakterGaring(String data) {
        String hasil = null;
        try {
            if (data != null) {
                hasil = data.replace("/", "-");
            } else {
                hasil = "";
            }
        } catch (Exception ex) {
            hasil = "";
        }

        return hasil;
    }

    public static String validasiNik(String data, int typeNsb) {
        String hasil = null;
        try {
            //statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterIdentitas(data, typeNsb, FLD_DATA_NIK);
            } else {
                hasil = "";
                if (typeNsb == 1) {
                    statusProsesValidasi = statusProsesValidasi + "<br> Data NIK / No Identitas, Wajib di ISI. Penulisan dengan angka";
                }
            }
        } catch (Exception ex) {
            hasil = "";
            if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Data NIK / No Identitas, Wajib di ISI. Penulisan dengan angka";
            }
        }

        return hasil;
    }

    public static String validasiTempatBekerja(String data, int typeNsb) {
        String hasil = null;
        try {
            // statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, data, FLD_DATA_TEMPAT_BEKERJA);
            } else if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Data tempat bekerja, Wajib diisi";
            }
        } catch (Exception ex) {
            hasil = "";
            if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br> Data tempat bekerja, Wajib diisi";
            }
        }

        return hasil;
    }

    public static String validasiAlamatTempatBekerja(String data, int typeNsb) {
        String hasil = null;
        try {
            // statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakter(data, typeNsb, FLD_DB_ALAMAT_TEMPAT_BEKERJA);
            } else {
                hasil = "";
            }
        } catch (Exception ex) {
            hasil = "";
        }

        return hasil;
    }

    public static String validasiNpwp(String data, int typeNsb) {
        String hasil = null;
        try {
            // statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, "", FLD_DB_NPWP, typeNsb);
            } else {
                hasil = "";
            }
        } catch (Exception ex) {
            hasil = "";
        }

        return hasil;
    }

    public static String validasiKelurahan(String data) {
        String hasil = null;
        try {
            //statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, data, FLD_DATA_KELURAHAN);
            } else {
                statusProsesValidasi = statusProsesValidasi + "<br> Data kelurahan, Wajib di perbaiki di core";
            }
        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasi = statusProsesValidasi + "<br> Data kelurahan, Wajib di perbaiki di core";
        }

        return hasil;
    }

    public static String validasiKecamatan(String data) {
        String hasil = null;
        try {
            //statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, data, FLD_DATA_KECAMATAN);
            } else {
                statusProsesValidasi = statusProsesValidasi + "<br>Data kecamatan , Wajib di perbaiki di core";
            }
        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasi = statusProsesValidasi + "<br> Data kecamatan , Wajib di perbaiki di core";
        }

        return hasil;
    }

    public static String validasiAlamat(String data) {
        String hasil = null;
        try {
            //statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, data, FLD_DB_ALAMAT);
            } else {
                statusProsesValidasi = statusProsesValidasi + "<br>Data Alamat , Wajib di perbaiki di core";
            }
        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasi = statusProsesValidasi + "<br> Data Alamat , Wajib di perbaiki di core";
        }

        return hasil;
    }

    public static String validasiTempatLagir(String data, int typeNsb) {
        String hasil = null;
        try {
            // statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, data, FLD_DATA_TEMPAT_LAHIR_KTP);
            } else if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br>Data tempat lahir sesuai KTP, Wajib di perbaiki di core";
            }
        } catch (Exception ex) {
            hasil = "";
            //statusProsesValidasi = statusProsesValidasi + "<br> Data tempat lahir sesuai KTP tidak boleh kosong";
            if (typeNsb == 1) {
                statusProsesValidasi = statusProsesValidasi + "<br>Data tempat lahir sesuai KTP, Wajib di perbaiki di core";
            }
        }

        return hasil;
    }

    /**
     * @return the statusProsesValidasi
     */
    public static String getStatusProsesValidasi() {
        return statusProsesValidasi;
    }

    public static String getStatusProsesValidasiAgunan() {
        return statusProsesValidasiAgunan;
    }

    public static String getStatusProsesValidasiBankGaransi() {
        return statusProsesValidasiBankGaransi;
    }

    public static String getStatusProsesValidasiKredit() {
        return statusProsesValidasiKredit;
    }

    /**
     * @param aStatusProsesValidasi the statusProsesValidasi to set
     */
    public static void setStatusProsesValidasi(String aStatusProsesValidasi) {
        statusProsesValidasi = aStatusProsesValidasi;
    }

    public static String checkKarakterOnlyString(String value, String prevValue, int typeData) {
        return checkKarakterOnlyString(value, prevValue, typeData, 0);
    }

    public static String checkKarakterOnlyString(String value, String prevValue, int typeData, int typeNsb) {
        if (value != null && value.length() > 0) {
            String specChar = "";
            int index = -1;
            boolean stop = false;
            boolean isValidasi = false;
            for (int i = 0; i < specialChar.length; i++) {
                specChar = specialChar[i];
                stop = false;
                while (!stop) {
                    index = value.indexOf(specChar);
                    if (index > -1) {
                        value = replaceString(index, value, specChar, specialCharReplace[i]);
                        isValidasi = true;
                    } else {
                        stop = true;
                    }
                }
            }
            if (isValidasi) {//jika ada validasi, buatkan catatannya
                switch (typeData) {
                    case FLD_DATA_NIK: //untuk NIK
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan NIK tanpa huruf karakter";
                        break;
                    case FLD_DATA_TEMPAT_BEKERJA:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan tempat bekerja tanpa huruf karakter";
                        break;
                    case FLD_DATA_KELURAHAN:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data kelurahan tanpa huruf karakter";
                        break;
                    case FLD_DATA_KECAMATAN:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data kecamatan tanpa huruf karakter";
                        break;
                    case FLD_DATA_TEMPAT_LAHIR_KTP:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data tempat lagir tanpa huruf karakter";
                        break;
                    case FLD_AGUNAN_OWNER:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data Nama Pemilik Agunan";
                        break;
                    case FLD_DB_NPWP:
                        if (typeNsb == 1) {
                            statusProsesValidasi = statusProsesValidasi + "<br> Perubahan NPWP tanda karakter . ";
                        }
                    case FLD_DB_ALAMAT:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data alamat tanpa huruf karakter";
                        break;
                    default:
                        break;
                }

            }
        }

        return value;
    }

    public static String checkKarakter(String value, int prevValue, int typeData) {
        if (value != null && value.length() > 0) {
            String specChar = "";
            int index = -1;
            boolean stop = false;
            boolean isValidasi = false;
            for (int i = 0; i < specialCharx.length; i++) {
                specChar = specialCharx[i];
                stop = false;
                while (!stop) {
                    index = value.indexOf(specChar);
                    if (index > -1) {
                        value = replaceString(index, value, specChar, specialCharReplacex[i]);
                        isValidasi = true;
                    } else {
                        stop = true;
                    }
                }
            }
            if (isValidasi) {//jika ada validasi, buatkan catatannya
                switch (typeData) {
                    case FLD_DB_NAMA: //untuk NIK
                        if (prevValue == 1) {
                            statusProsesValidasi = statusProsesValidasi + "<br> Perubahan Nama Sesuai Identitas tanpa huruf karakter";
                        }
                        break;
                    case FLD_DB_NAMA_LENGKAP:
                        if (prevValue == 1) {
                            statusProsesValidasi = statusProsesValidasi + "<br> Perubahan Nama lengkap tanpa huruf karakter";
                        }
                        break;
                    case FLD_DB_NAMA_BADAN_USAHA:
                        if (prevValue != 1) {
                            statusProsesValidasi = statusProsesValidasi + "<br> Perubahan Nama Badan Usaha tanpa huruf karakter";
                        }
                        break;
                    case FLD_DB_NAMA_IBU_KANDUNG:
                        if (prevValue == 1) {
                            statusProsesValidasi = statusProsesValidasi + "<br> Perubahan Nama gadis ibu kandung tanpa huruf karakter";
                        }
                        break;
                    case FLD_DB_ALAMAT_TEMPAT_BEKERJA:
                        if (prevValue == 1) {
                            statusProsesValidasi = statusProsesValidasi + "<br> Perubahan Alamat Tempat Bekerja tanpa huruf karakter";
                        }
                    default:
                        break;
                }

            }
        }

        return value;
    }

    public static String checkKarakterIdentitas(String value, int prevValue, int typeData) {
        if (value != null && value.length() > 0) {
            String specChar = "";
            int index = -1;
            boolean stop = false;
            boolean isValidasi = false;
            for (int i = 0; i < specialCharIdentitas.length; i++) {
                specChar = specialCharIdentitas[i];
                stop = false;
                while (!stop) {
                    index = value.indexOf(specChar);
                    if (index > -1) {
                        value = replaceString(index, value, specChar, specialCharReplaceIdentitas[i]);
                        isValidasi = true;
                    } else {
                        stop = true;
                    }
                }
            }
            if (isValidasi) {//jika ada validasi, buatkan catatannya
                switch (typeData) {
                    case FLD_DATA_NIK: //untuk NIK
                        if (prevValue == 1) {
                            statusProsesValidasi = statusProsesValidasi + "<br> Perubahan NIK tanpa huruf karakter";
                        }
                        break;
                    case FLD_DATA_TEMPAT_BEKERJA:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan tempat bekerja tanpa huruf karakter";
                        break;
                    case FLD_DATA_KELURAHAN:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data kelurahan tanpa huruf karakter";
                        break;
                    case FLD_DATA_KECAMATAN:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data kecamatan tanpa huruf karakter";
                        break;
                    case FLD_DATA_TEMPAT_LAHIR_KTP:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data tempat lagir tanpa huruf karakter";
                        break;
                    case FLD_AGUNAN_OWNER:
                        statusProsesValidasi = statusProsesValidasi + "<br> Perubahan data Nama Pemilik Agunan";
                        break;
                    default:
                        break;
                }

            }
        }

        return value;
    }

    public static String replaceString(int index, String value, String specChar, String charReplace) {
        if (index == 0) {
            value = charReplace + value.substring(index + specChar.length(), value.length());
        } else {
            String st1 = "";
            String st2 = "";
            st1 = value.substring(0, index);
            st2 = value.substring(index + specChar.length(), value.length());
            value = st1 + charReplace + st2;
        }
        return value;
    }

    public static String validasiOwnerAgunan(String data) {
        String hasil = null;
        try {
            //statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, data, FLD_AGUNAN_OWNER);
            } else {
                hasil = "";
                statusProsesValidasiAgunan = statusProsesValidasiAgunan + "<br> Nama Pemilik, Wajib Di isi";
            }
        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasiAgunan = statusProsesValidasiAgunan + "<br> Nama Pemilik, Wajib Di isi";
        }

        return hasil;
    }

    public static String validasiBuktiKepemilikan(String data) {
        String hasil = null;
        try {
            //statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, data, FLD_AGUNAN_BUKTI_KEPEMILIKAN);
            } else {
                hasil = "";
                statusProsesValidasiAgunan = statusProsesValidasiAgunan + "<br> Bukti Kepemilikan, Wajib Di isi";
            }
        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasiAgunan = statusProsesValidasiAgunan + "<br> Bukti Kepemilikan, Wajib Di isi";
        }

        return hasil;
    }

    public static String validasiAgunanAlamat(String data) {
        String hasil = null;
        try {
            //statusProsesValidasi="";
            if (data != null && !data.equals(" ") && !data.equals("")) {
                hasil = checkKarakterOnlyString(data, data, FLD_AGUNAN_ALAMAT);
            } else {
                hasil = "";
                statusProsesValidasiAgunan = statusProsesValidasiAgunan + "<br> Alamat Agunan, Wajib Di isi";
            }
        } catch (Exception ex) {
            hasil = "";
            statusProsesValidasiAgunan = statusProsesValidasiAgunan + "<br> Alamat Agunan, Wajib Di isi";
        }

        return hasil;
    }

    public static boolean wajibDiisi(String dataValidasi) {
        boolean hasil = false;
        try {
            /// String regex = ".*\\bWajib\\b.*";
//            if(dataValidasi.matches(regex)) {
//                hasil = false;
//            } else {
//                hasil = true;
//            }
            int intIndex = dataValidasi.indexOf("Wajib");
            if (intIndex == - 1) {
                hasil = true;
            } else {
                hasil = false;
            }

        } catch (Exception ex) {
            hasil = false;
        }

        return hasil;
    }

    public static boolean validateLetters(String txt) {
        boolean result = true;
        for (int x = 0; x < txt.length(); x++) {
            char ch = txt.charAt(x);
            if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == ' ')) {
                return false;
            }
        }
        return true;
    }
}
