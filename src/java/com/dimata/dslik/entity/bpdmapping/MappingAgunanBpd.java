/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.bpdmapping;

import com.dimata.qdep.entity.Entity;
import java.util.Date;

/**
 *
 * @author Dewa
 */
public class MappingAgunanBpd extends Entity {

    private String flagDetail = "";
    private String kodeRegisterAgunan = "";
    private String kodeJenisAgunan = "";
    private String namaPemilikAgunan = "";
    private String buktiKepemilikan = "";
    private String alamatAgunan = "";
    private String kodeKabLokasiAgunan = "";
    private double nilaiAgunanNjop = 0;
    private double nilaiAgunanLjk = 0;

    private String cif = "";
    private String noRekening = "";
    private String jenisIkatan = "";
    private Date tanggalPenilaian = null;
    private String statusParipasu = "";
    private String kodeKantorCabang = "";
    private long periodeId = 0;

    private int prosentaseParipasu = 0;

    private String kodeStatusAgunan = "";
    private String kodeLembagaPemeringkat = "";
    private Date tglPenilaianIndependent = null;
    private String diAsuransikan = "";

    private String statusKreditJoinAccount = "";
    private String kodeJenisSegementFasilitas = "";

    //new 20160111
    private String agunanDiAsuransikan = "";
    private Date tglPengikat = null;

    private double nilaiAgunanPenilaianIndependent = 0.0;

    private int statusDataPelaporan = 0;

    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
    }

    public String getKodeRegisterAgunan() {
        return kodeRegisterAgunan;
    }

    public void setKodeRegisterAgunan(String kodeRegisterAgunan) {
        this.kodeRegisterAgunan = kodeRegisterAgunan;
    }

    public String getKodeJenisAgunan() {
        return kodeJenisAgunan;
    }

    public void setKodeJenisAgunan(String kodeJenisAgunan) {
        this.kodeJenisAgunan = kodeJenisAgunan;
    }

    public String getNamaPemilikAgunan() {
        return namaPemilikAgunan;
    }

    public void setNamaPemilikAgunan(String namaPemilikAgunan) {
        this.namaPemilikAgunan = namaPemilikAgunan;
    }

    public String getBuktiKepemilikan() {
        return buktiKepemilikan;
    }

    public void setBuktiKepemilikan(String buktiKepemilikan) {
        this.buktiKepemilikan = buktiKepemilikan;
    }

    public String getAlamatAgunan() {
        return alamatAgunan;
    }

    public void setAlamatAgunan(String alamatAgunan) {
        this.alamatAgunan = alamatAgunan;
    }

    public String getKodeKabLokasiAgunan() {
        return kodeKabLokasiAgunan;
    }

    public void setKodeKabLokasiAgunan(String kodeKabLokasiAgunan) {
        this.kodeKabLokasiAgunan = kodeKabLokasiAgunan;
    }

    public double getNilaiAgunanNjop() {
        return nilaiAgunanNjop;
    }

    public void setNilaiAgunanNjop(double nilaiAgunanNjop) {
        this.nilaiAgunanNjop = nilaiAgunanNjop;
    }

    public double getNilaiAgunanLjk() {
        return nilaiAgunanLjk;
    }

    public void setNilaiAgunanLjk(double nilaiAgunanLjk) {
        this.nilaiAgunanLjk = nilaiAgunanLjk;
    }

    /**
     * @return the cif
     */
    public String getCif() {
        return cif;
    }

    /**
     * @param cif the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * @return the noRekening
     */
    public String getNoRekening() {
        return noRekening;
    }

    /**
     * @param noRekening the noRekening to set
     */
    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    /**
     * @return the jenisIkatan
     */
    public String getJenisIkatan() {
        return jenisIkatan;
    }

    /**
     * @param jenisIkatan the jenisIkatan to set
     */
    public void setJenisIkatan(String jenisIkatan) {
        this.jenisIkatan = jenisIkatan;
    }

    /**
     * @return the tanggalPenilaian
     */
    public Date getTanggalPenilaian() {
        return tanggalPenilaian;
    }

    /**
     * @param tanggalPenilaian the tanggalPenilaian to set
     */
    public void setTanggalPenilaian(Date tanggalPenilaian) {
        this.tanggalPenilaian = tanggalPenilaian;
    }

    /**
     * @return the kodeKantorCabang
     */
    public String getKodeKantorCabang() {
        return kodeKantorCabang;
    }

    /**
     * @param kodeKantorCabang the kodeKantorCabang to set
     */
    public void setKodeKantorCabang(String kodeKantorCabang) {
        this.kodeKantorCabang = kodeKantorCabang;
    }

    /**
     * @return the periodeId
     */
    public long getPeriodeId() {
        return periodeId;
    }

    /**
     * @param periodeId the periodeId to set
     */
    public void setPeriodeId(long periodeId) {
        this.periodeId = periodeId;
    }

    /**
     * @return the statusParipasu
     */
    public String getStatusParipasu() {
        return statusParipasu;
    }

    /**
     * @param statusParipasu the statusParipasu to set
     */
    public void setStatusParipasu(String statusParipasu) {
        this.statusParipasu = statusParipasu;
    }

    /**
     * @return the prosentaseParipasu
     */
    public int getProsentaseParipasu() {
        return prosentaseParipasu;
    }

    /**
     * @param prosentaseParipasu the prosentaseParipasu to set
     */
    public void setProsentaseParipasu(int prosentaseParipasu) {
        this.prosentaseParipasu = prosentaseParipasu;
    }

    /**
     * @return the kodeStatusAgunan
     */
    public String getKodeStatusAgunan() {
        return kodeStatusAgunan;
    }

    /**
     * @param kodeStatusAgunan the kodeStatusAgunan to set
     */
    public void setKodeStatusAgunan(String kodeStatusAgunan) {
        this.kodeStatusAgunan = kodeStatusAgunan;
    }

    /**
     * @return the kodeLembagaPemeringkat
     */
    public String getKodeLembagaPemeringkat() {
        return kodeLembagaPemeringkat;
    }

    /**
     * @param kodeLembagaPemeringkat the kodeLembagaPemeringkat to set
     */
    public void setKodeLembagaPemeringkat(String kodeLembagaPemeringkat) {
        this.kodeLembagaPemeringkat = kodeLembagaPemeringkat;
    }

    /**
     * @return the tglPenilaianIndependent
     */
    public Date getTglPenilaianIndependent() {
        return tglPenilaianIndependent;
    }

    /**
     * @param tglPenilaianIndependent the tglPenilaianIndependent to set
     */
    public void setTglPenilaianIndependent(Date tglPenilaianIndependent) {
        this.tglPenilaianIndependent = tglPenilaianIndependent;
    }

    /**
     * @return the diAsuransikan
     */
    public String getDiAsuransikan() {
        return diAsuransikan;
    }

    /**
     * @param diAsuransikan the diAsuransikan to set
     */
    public void setDiAsuransikan(String diAsuransikan) {
        this.diAsuransikan = diAsuransikan;
    }

    /**
     * @return the statusKreditJoinAccount
     */
    public String getStatusKreditJoinAccount() {
        return statusKreditJoinAccount;
    }

    /**
     * @param statusKreditJoinAccount the statusKreditJoinAccount to set
     */
    public void setStatusKreditJoinAccount(String statusKreditJoinAccount) {
        this.statusKreditJoinAccount = statusKreditJoinAccount;
    }

    /**
     * @return the kodeJenisSegementFasilitas
     */
    public String getKodeJenisSegementFasilitas() {
        return kodeJenisSegementFasilitas;
    }

    /**
     * @param kodeJenisSegementFasilitas the kodeJenisSegementFasilitas to set
     */
    public void setKodeJenisSegementFasilitas(String kodeJenisSegementFasilitas) {
        this.kodeJenisSegementFasilitas = kodeJenisSegementFasilitas;
    }

    /**
     * @return the nilaiAgunanPenilaianIndependent
     */
    public double getNilaiAgunanPenilaianIndependent() {
        return nilaiAgunanPenilaianIndependent;
    }

    /**
     * @param nilaiAgunanPenilaianIndependent the
     * nilaiAgunanPenilaianIndependent to set
     */
    public void setNilaiAgunanPenilaianIndependent(double nilaiAgunanPenilaianIndependent) {
        this.nilaiAgunanPenilaianIndependent = nilaiAgunanPenilaianIndependent;
    }

    /**
     * @return the agunanDiAsuransikan
     */
    public String getAgunanDiAsuransikan() {
        return agunanDiAsuransikan;
    }

    /**
     * @param agunanDiAsuransikan the agunanDiAsuransikan to set
     */
    public void setAgunanDiAsuransikan(String agunanDiAsuransikan) {
        this.agunanDiAsuransikan = agunanDiAsuransikan;
    }

    /**
     * @return the tglPengikat
     */
    public Date getTglPengikat() {
        return tglPengikat;
    }

    /**
     * @param tglPengikat the tglPengikat to set
     */
    public void setTglPengikat(Date tglPengikat) {
        this.tglPengikat = tglPengikat;
    }

    /**
     * @return the statusDataPelaporan
     */
    public int getStatusDataPelaporan() {
        return statusDataPelaporan;
    }

    /**
     * @param statusDataPelaporan the statusDataPelaporan to set
     */
    public void setStatusDataPelaporan(int statusDataPelaporan) {
        this.statusDataPelaporan = statusDataPelaporan;
    }

    public String getLogDetail(Entity prevDoc, Entity currentDoc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String history = "";
        MappingAgunanBpd prevMat = (MappingAgunanBpd) prevDoc;
        MappingAgunanBpd currMat = (MappingAgunanBpd) currentDoc;
        try {
            //0
            if (prevMat == null || !prevMat.getFlagDetail().equals(currMat.getFlagDetail())) {
                if (prevMat == null) {
                    history = history + " Flag Detail : " + currMat.getFlagDetail() + "/n";
                } else {
                    history = history + " Flag Detail diubah dari " + prevMat.getFlagDetail() + " menjadi " + currMat.getFlagDetail() + "/n";
                }
            }

            //1
            if (prevMat == null || !prevMat.getKodeRegisterAgunan().equals(currMat.getKodeRegisterAgunan())) {
                if (prevMat == null) {
                    history = history + " Kode Register Agunan : " + currMat.getKodeRegisterAgunan() + "/n";
                } else {
                    history = history + " Kode Register Agunan diubah dari " + prevMat.getKodeRegisterAgunan() + " menjadi " + currMat.getKodeRegisterAgunan() + "/n";
                }
            }

            //2
            if (prevMat == null || !prevMat.getNoRekening().equals(currMat.getNoRekening())) {
                if (prevMat == null) {
                    history = history + " No. Rekening : " + currMat.getNoRekening() + "/n";
                } else {
                    history = history + " No. Rekening diubah dari " + prevMat.getNoRekening() + " menjadi " + currMat.getNoRekening() + "/n";
                }
            }

            //3
            if (prevMat == null || !prevMat.getCif().equals(currMat.getCif())) {
                if (prevMat == null) {
                    history = history + " CIF : " + currMat.getCif() + "/n";
                } else {
                    history = history + " CIF duibah dari dari " + prevMat.getCif() + " menjadi " + currMat.getCif() + "/n";
                }
            }

            //4
            if (prevMat == null || !prevMat.getKodeStatusAgunan().equals(currMat.getKodeStatusAgunan())) {
                if (prevMat == null) {
                    history = history + " Kode Status Agunan : " + currMat.getKodeStatusAgunan() + "/n";
                } else {
                    history = history + " Kode Status Agunan diubah dari " + prevMat.getKodeStatusAgunan() + " menjadi " + currMat.getKodeStatusAgunan() + "/n";
                }
            }

            //5
            if (prevMat == null || !prevMat.getKodeJenisAgunan().equals(currMat.getKodeJenisAgunan())) {
                if (prevMat == null) {
                    history = history + " Kode Jenis Agunan : " + currMat.getKodeStatusAgunan() + "/n";
                } else {
                    history = history + " Kode Jenis Agunan diubah dari " + prevMat.getKodeJenisAgunan() + " menjadi " + currMat.getKodeJenisAgunan() + "/n";
                }
            }

            //6
//            if (prevMat == null || !prevMat.getp.equals(currMat.getPeringkatAgunan())) {
//                if (prevMat == null) {
//                    history = history + " Peringkat Agunan : " + currMat.getPeringkatAgunan() + "/n";
//                } else {
//                    history = history + " Peringkat Agunan diubah dari " + prevMat.getPeringkatAgunan() + " menjadi " + currMat.getPeringkatAgunan() + "/n";
//                }
//            }

            //7
            if (prevMat == null || !prevMat.getKodeLembagaPemeringkat().equals(currMat.getKodeLembagaPemeringkat())) {
                if (prevMat == null) {
                    history = history + " Kode Lembaga Pemeringkat : " + currMat.getKodeLembagaPemeringkat() + "/n";
                } else {
                    history = history + " Kode Lembaga Pemeringkat diubah dari " + prevMat.getKodeLembagaPemeringkat() + " menjadi " + currMat.getKodeLembagaPemeringkat() + "/n";
                }
            }

            //8
//            if (prevMat == null || !prevMat.get.equals(currMat.getKodeJenisPengikatan())) {
//                if (prevMat == null) {
//                    history = history + " Kode Jenis Pengikatan : " + currMat.getKodeJenisPengikatan() + "/n";
//                } else {
//                    history = history + " Kode Jenis Pengikatan diubah dari " + prevMat.getKodeJenisPengikatan() + " menjadi " + currMat.getKodeJenisPengikatan() + "/n";
//                }
//            }

            //8
            if (prevMat == null || !prevMat.getTglPengikat().equals(currMat.getTglPengikat())) {
                if (prevMat == null) {
                    history = history + " Tanggal Pengikatan : " + currMat.getTglPengikat() + "/n";
                } else {
                    history = history + " Tanggal Pengikatan diubah dari " + prevMat.getTglPengikat() + " menjadi " + currMat.getTglPengikat() + "/n";

                }
            }

            //9
            if (prevMat == null || !prevMat.getNamaPemilikAgunan().equals(currMat.getNamaPemilikAgunan())) {
                if (prevMat == null) {
                    history = history + " Nama Pemilik Agunan : " + currMat.getNamaPemilikAgunan() + "/n";
                } else {
                    history = history + " Nama Pemilik Agunan diubah dari " + prevMat.getNamaPemilikAgunan() + " menjadi " + currMat.getNamaPemilikAgunan()+ "/n";
                }
            }

            //10
            if (prevMat == null || !prevMat.getBuktiKepemilikan().equals(currMat.getBuktiKepemilikan())) {
                if (prevMat == null) {
                    history = history + " Bukti Kepemilikan : " + currMat.getBuktiKepemilikan() + "/n";
                } else {
                    history = history + " Bukti Kepemilikan diubah dari " + prevMat.getBuktiKepemilikan() + " menjadi " + currMat.getBuktiKepemilikan() + "/n";
                }
            }

            //11
            if (prevMat == null || !prevMat.getAlamatAgunan().equals(currMat.getAlamatAgunan())) {
                if (prevMat == null) {
                    history = history + " Alamat Agunan : " + currMat.getAlamatAgunan() + "/n";
                } else {
                    history = history + " Alamat Agunan diubah dari " + prevMat.getAlamatAgunan() + " menjadi " + currMat.getAlamatAgunan() + "/n";
                }
            }

            //12
            if (prevMat == null || !prevMat.getKodeKabLokasiAgunan().equals(currMat.getKodeKabLokasiAgunan())) {
                if (prevMat == null) {
                    history = history + " Kode Kab/Kota (DATI 2) Lokasi Agunan : " + currMat.getKodeKabLokasiAgunan() + "/n";
                } else {
                    history = history + " Kode Kab/Kota (DATI 2) Lokasi Agunan diubah dari " + prevMat.getKodeKabLokasiAgunan() + " menjadi " + currMat.getKodeKabLokasiAgunan() + "/n";
                }
            }

            //13
            if (prevMat == null || (prevMat.getNilaiAgunanNjop() != currMat.getNilaiAgunanNjop())) {
                if (prevMat == null) {
                    history = history + " Nilai Agunan (NJOP) : " + currMat.getNilaiAgunanNjop() + "/n";
                } else {
                    history = history + " Nilai Agunan (NJOP) diubah dari " + prevMat.getNilaiAgunanNjop() + " menjadi " + currMat.getNilaiAgunanNjop() + "/n";
                }
            }

            //14
            if (prevMat == null || prevMat.getNilaiAgunanLjk() != currMat.getNilaiAgunanLjk()) {
                if (prevMat == null) {
                    history = history + " Nilai Agunan Menurut LJK : " + currMat.getNilaiAgunanLjk() + "/n";
                } else {
                    history = history + " Nilai Agunan Menurut LJK diubah dari " + prevMat.getNilaiAgunanLjk() + " menjadi " + currMat.getNilaiAgunanLjk() + "/n";
                }
            }

            //15
            if (prevMat == null || !prevMat.getTanggalPenilaian().equals(currMat.getTanggalPenilaian())) {
                if (prevMat == null) {
                    history = history + " Tanggal LJK diubah : " + currMat.getTanggalPenilaian() + "/n";
                } else {
                    history = history + " Tanggal LJK diubah dari " + prevMat.getTanggalPenilaian() + " menjadi " + currMat.getTanggalPenilaian() + "/n";
                }
            }

            //16
            if (prevMat == null || prevMat.getNilaiAgunanPenilaianIndependent() != currMat.getNilaiAgunanPenilaianIndependent()) {
                if (prevMat == null) {
                    history = history + " Nilai Agunan Penilai Independen : " + currMat.getNilaiAgunanPenilaianIndependent() + "/n";
                } else {
                    history = history + " Nilai Agunan Penilai Independen diubah dari " + prevMat.getNilaiAgunanPenilaianIndependent() + " menjadi " + currMat.getNilaiAgunanPenilaianIndependent() + "/n";
                }
            }

            //17
//            if (prevMat == null || !prevMat.getTglPengikat().equals(currMat.getNamaPenilaiIndep())) {
//                if (prevMat == null) {
//                    history = history + " Nama Penilai Independen : " + currMat.getNamaPenilaiIndep() + "/n";
//                } else {
//                    history = history + " Nama Penilai Independen diubah dari " + prevMat.getNamaPenilaiIndep() + " menjadi " + currMat.getNamaPenilaiIndep() + "/n";
//                }
//            }

            //18
            if (prevMat == null || !prevMat.getTglPenilaianIndependent().equals(currMat.getTglPenilaianIndependent())) {
                if (prevMat == null) {
                    history = history + " Tanggal Penilaian Penilai Independen : " + currMat.getTglPenilaianIndependent() + "/n";
                } else {
                    history = history + " Tanggal Penilaian Penilai Independen diubah dari " + prevMat.getTglPenilaianIndependent() + " menjadi " + currMat.getTglPenilaianIndependent() + "/n";
                }
            }

            //19
            if (prevMat == null || !prevMat.getStatusParipasu().equals(currMat.getStatusParipasu())) {
                if (prevMat == null) {
                    history = history + " Status Paripasu : " + currMat.getStatusParipasu() + "/n";
                } else {
                    history = history + " Status Paripasu diubah dari " + prevMat.getStatusParipasu() + " menjadi " + currMat.getStatusParipasu() + "/n";
                }
            }

            //20
            if (prevMat == null || !prevMat.getStatusKreditJoinAccount().equals(currMat.getStatusKreditJoinAccount())) {
                if (prevMat == null) {
                    history = history + " Status Kredit Join : " + currMat.getStatusKreditJoinAccount() + "/n";
                } else {
                    history = history + " Status Kredit Join diubah dari " + prevMat.getStatusKreditJoinAccount() + " menjadi " + currMat.getStatusKreditJoinAccount() + "/n";
                }
            }

            //21
            if (prevMat == null || !prevMat.getDiAsuransikan().equals(currMat.getDiAsuransikan())) {
                if (prevMat == null) {
                    history = history + " Diasuransikan : " + currMat.getDiAsuransikan() + "/n";
                } else {
                    history = history + " Diasuransikan diubah dari " + prevMat.getDiAsuransikan() + " menjadi " + currMat.getDiAsuransikan() + "/n";
                }
            }


        } catch (Exception e) {
            System.out.println("" + e.toString() + "");
            history="Eror";
        }

        //lanjutkan
        return history;
    }

}
