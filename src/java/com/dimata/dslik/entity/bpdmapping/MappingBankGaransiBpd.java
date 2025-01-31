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
public class MappingBankGaransiBpd extends Entity {
    
    private String cif="";
    private String flagDetail = "";
    private String noRekening = "";
    private String kodeJenisGaransi = "";
    private String kodeTujuanGaransi = "";
    private Date tglDiterbitkan = null;
    private Date tglJatuhTempo = null;
    private String noAkadAwal = "";
    private Date tglAkadAwal = null;
    private String noAkadAkhir = "";
    private Date tglAkadAkhir = null;
    private String namaYgDijamin = "";
    
    private double plafon = 0;
    private double nominal = 0;
    private double setoranJaminan = 0;
    private String kodeKolektibilitas = "";
    private Date tglWanPrestasi = null;
    private String kodeKondisi = "";
    private Date tglKondisi = null;
    private String keterangan = "";
    private String kodeKantorCabang = "";
    private long periodeId=0;
    
    private String kodeValuta="";
    private Date tglAkhir=null;
    
    private int statusDataPelaporan=0;
    
    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getKodeJenisGaransi() {
        return kodeJenisGaransi;
    }

    public void setKodeJenisGaransi(String kodeJenisGaransi) {
        this.kodeJenisGaransi = kodeJenisGaransi;
    }

    public String getKodeTujuanGaransi() {
        return kodeTujuanGaransi;
    }

    public void setKodeTujuanGaransi(String kodeTujuanGaransi) {
        this.kodeTujuanGaransi = kodeTujuanGaransi;
    }

    public Date getTglDiterbitkan() {
        return tglDiterbitkan;
    }

    public void setTglDiterbitkan(Date tglDiterbitkan) {
        this.tglDiterbitkan = tglDiterbitkan;
    }

    public Date getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(Date tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

    public String getNoAkadAwal() {
        return noAkadAwal;
    }

    public void setNoAkadAwal(String noAkadAwal) {
        this.noAkadAwal = noAkadAwal;
    }

    public Date getTglAkadAwal() {
        return tglAkadAwal;
    }

    public void setTglAkadAwal(Date tglAkadAwal) {
        this.tglAkadAwal = tglAkadAwal;
    }

    public String getNoAkadAkhir() {
        return noAkadAkhir;
    }

    public void setNoAkadAkhir(String noAkadAkhir) {
        this.noAkadAkhir = noAkadAkhir;
    }

    public Date getTglAkadAkhir() {
        return tglAkadAkhir;
    }

    public void setTglAkadAkhir(Date tglAkadAkhir) {
        this.tglAkadAkhir = tglAkadAkhir;
    }

    public String getNamaYgDijamin() {
        return namaYgDijamin;
    }

    public void setNamaYgDijamin(String namaYgDijamin) {
        this.namaYgDijamin = namaYgDijamin;
    }


    public double getPlafon() {
        return plafon;
    }

    public void setPlafon(double plafon) {
        this.plafon = plafon;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public double getSetoranJaminan() {
        return setoranJaminan;
    }

    public void setSetoranJaminan(double setoranJaminan) {
        this.setoranJaminan = setoranJaminan;
    }

    public String getKodeKolektibilitas() {
        return kodeKolektibilitas;
    }

    public void setKodeKolektibilitas(String kodeKolektibilitas) {
        this.kodeKolektibilitas = kodeKolektibilitas;
    }


    public String getKodeKantorCabang() {
        return kodeKantorCabang;
    }

    public void setKodeKantorCabang(String kodeKantorCabang) {
        this.kodeKantorCabang = kodeKantorCabang;
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
     * @return the kodeValuta
     */
    public String getKodeValuta() {
        return kodeValuta;
    }

    /**
     * @param kodeValuta the kodeValuta to set
     */
    public void setKodeValuta(String kodeValuta) {
        this.kodeValuta = kodeValuta;
    }

    /**
     * @return the tglAkhir
     */
    public Date getTglAkhir() {
        return tglAkhir;
    }

    /**
     * @param tglAkhir the tglAkhir to set
     */
    public void setTglAkhir(Date tglAkhir) {
        this.tglAkhir = tglAkhir;
    }

    /**
     * @return the kodeKondisi
     */
    public String getKodeKondisi() {
        return kodeKondisi;
    }

    /**
     * @param kodeKondisi the kodeKondisi to set
     */
    public void setKodeKondisi(String kodeKondisi) {
        this.kodeKondisi = kodeKondisi;
    }

    /**
     * @return the tglWanPrestasi
     */
    public Date getTglWanPrestasi() {
        return tglWanPrestasi;
    }

    /**
     * @param tglWanPrestasi the tglWanPrestasi to set
     */
    public void setTglWanPrestasi(Date tglWanPrestasi) {
        this.tglWanPrestasi = tglWanPrestasi;
    }

    /**
     * @return the tglKondisi
     */
    public Date getTglKondisi() {
        return tglKondisi;
    }

    /**
     * @param tglKondisi the tglKondisi to set
     */
    public void setTglKondisi(Date tglKondisi) {
        this.tglKondisi = tglKondisi;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
       String history="";
        MappingBankGaransiBpd prevMat = (MappingBankGaransiBpd)prevDoc;
        MappingBankGaransiBpd currMat = (MappingBankGaransiBpd)currentDoc;
        try {
            //0
            if(prevMat == null || !prevMat.getFlagDetail().equals(currMat.getFlagDetail())){
                if (prevMat== null){
                    history=history+" Flag Detail : "+currMat.getFlagDetail()+"/n";
                }else{
                    history=history+" Flag Detail diubah dari "+prevMat.getFlagDetail()+" menjadi "+currMat.getFlagDetail()+"/n";
                }
            }
            
            //1
            if (prevMat == null || !prevMat.getNoRekening().equals(currMat.getNoRekening())) {
                    if (prevMat == null) {
                        history = history + " Nomor Rekening : " + currMat.getNoRekening() + "/n";
                    } else {
                        history = history + " Nomor Rekening diubah dari " + prevMat.getNoRekening() + " menjadi " + currMat.getNoRekening() + "/n";
                    }
            }
            
            //2
            if (prevMat == null || !prevMat.getCif().equals(currMat.getCif())) {
                if (prevMat == null) {
                    history = history + " Nomor Rekening : " + currMat.getNoRekening() + "/n";
                } else {
                    history = history + " CIF diubah dari " + prevMat.getCif()+ " menjadi " + currMat.getCif()+ "/n";
                }
            }
            
            //3
            if (prevMat == null ||!prevMat.getKodeJenisGaransi().equals(currMat.getKodeJenisGaransi())) {
                if (prevMat == null) {
                    history = history + " Kode Jenis Garansi : " + currMat.getKodeJenisGaransi() + "/n";
                } else {
                    history = history + " Kode Jenis Garansi diubah dari " + prevMat.getKodeJenisGaransi()+ " menjadi " + currMat.getKodeJenisGaransi()+ "/n";
                }
            }
            
            //4
            if (prevMat == null || !prevMat.getKodeTujuanGaransi().equals(currMat.getKodeTujuanGaransi())) {
                if (prevMat == null) {
                    history = history + " Kode Tujuan Garansi : " + currMat.getKodeTujuanGaransi() + "/n";
                } else {
                    history = history + " Kode Tujuan Garansi diubah dari " + prevMat.getKodeTujuanGaransi()+ " menjadi " + currMat.getKodeTujuanGaransi()+ "/n";
                }
            }
            
            //5
            if (prevMat == null || !prevMat.getTglDiterbitkan().equals(currMat.getTglDiterbitkan())) {
                if (prevMat == null) {
                    history = history + " Tanggal diterbitkan : " + currMat.getTglDiterbitkan() + "/n";
                } else {
                    history = history + " Tanggal diterbitkan diubah dari " + prevMat.getTglDiterbitkan()+ " menjadi " + currMat.getTglDiterbitkan()+ "/n";
                }
            }
            
            //6
            if (prevMat == null || !prevMat.getTglJatuhTempo().equals(currMat.getTglJatuhTempo())) {
                if (prevMat == null) {
                    history = history + " Tanggal Jatuh Tempo : " + currMat.getTglJatuhTempo() + "/n";
                } else {
                    history = history + " Tanggal Jatuh Tempo diubah dari " + prevMat.getTglJatuhTempo()+ " menjadi " + currMat.getTglJatuhTempo()+ "/n";
                }
            }
            
            //7
            if (prevMat == null || !prevMat.getNoAkadAwal().equals(currMat.getNoAkadAwal())) {
                if (prevMat == null) {
                    history = history + " Nomor akad awal : " + currMat.getNoAkadAwal() + "/n";
                } else {
                    history = history + " Nomor akad awal diubah dari " + prevMat.getNoAkadAwal()+ " menjadi " + currMat.getNoAkadAwal()+ "/n";
                }
            }
            
            //8
            if (prevMat == null || !prevMat.getTglAkadAwal().equals(currMat.getTglAkadAwal())) {
                if (prevMat == null) {
                    history = history + " Tanggal akad awal : " + currMat.getTglAkadAwal() + "/n";
                } else {
                    history = history + " Tanggal akad awal diubah dari " + prevMat.getTglAkadAwal()+ " menjadi " + currMat.getTglAkadAwal()+ "/n";
                }               
            }
            
            //9
            if (prevMat == null || !prevMat.getNoAkadAkhir().equals(currMat.getNoAkadAkhir())) {
                if (prevMat == null) {
                    history = history + " Nomor akad akhir : " + currMat.getNoAkadAkhir() + "/n";
                } else {
                    history = history + " Nomor akad akhir diubah dari " + prevMat.getNoAkadAkhir()+ " menjadi " + currMat.getNoAkadAkhir()+ "/n";
                }
            }
            
            //10
            if (prevMat == null || !prevMat.getTglAkadAkhir().equals(currMat.getTglAkadAkhir())) {
                if (prevMat == null) {
                    history = history + " Tanggal akad akhir : " + currMat.getTglAkadAkhir() + "/n";
                } else {
                    history = history + " Tanggal akad akhir diubah dari " + prevMat.getTglAkadAkhir()+ " menjadi " + currMat.getTglAkadAkhir()+ "/n";
                }
            }
            
            //11
            if (prevMat == null || !prevMat.getNamaYgDijamin().equals(currMat.getNamaYgDijamin())) {
                if (prevMat == null) {
                    history = history + " Nama yang dijamin : " + currMat.getNamaYgDijamin() + "/n";
                } else {
                    history = history + " Nama yang dijamin diubah dari " + prevMat.getNamaYgDijamin()+ " menjadi " + currMat.getNamaYgDijamin()+ "/n";
                }
            }
            
            //12
            if (prevMat == null || !prevMat.getKodeValuta().equals(currMat.getKodeValuta())) {
                if (prevMat == null) {
                    history = history + " Kode valuta : " + currMat.getKodeValuta() + "/n";
                } else {
                    history = history + " Kode valuta diubah dari " + prevMat.getKodeValuta()+ " menjadi " + currMat.getKodeValuta()+ "/n";
                }
            }
            
            //13
            if (prevMat == null || prevMat.getPlafon()!= currMat.getPlafon()) {
                if (prevMat == null) {
                    history = history + " Plafon : " + currMat.getPlafon() + "/n";
                } else {
                    history = history + " Plafon diubah dari " + prevMat.getPlafon()+ " menjadi " + currMat.getPlafon()+ "/n"; 
                }
            }
            
            //14
            if (prevMat == null || prevMat.getNominal()!= currMat.getNominal()) {
                if (prevMat == null) {
                    history = history + " Nominal : " + currMat.getNominal() + "/n";
                } else {
                    history = history + " Nominal diubah dari " + prevMat.getNominal()+ " menjadi " + currMat.getNominal()+ "/n";
                }
            }
            
            //15
            if (prevMat == null || prevMat.getSetoranJaminan()!= currMat.getSetoranJaminan()) {
                if (prevMat == null) {
                    history = history + " Setoran jaminan : " + currMat.getSetoranJaminan() + "/n";
                } else {
                     history = history + " Setoran jaminan diubah dari " + prevMat.getSetoranJaminan()+ " menjadi " + currMat.getSetoranJaminan()+ "/n";
                }
            }
            
            //16
            if (prevMat == null || !prevMat.getKodeKolektibilitas().equals(currMat.getKodeKolektibilitas())) {
                if (prevMat == null) {
                    history = history + " Kode kolektibilitas : " + currMat.getKodeKolektibilitas() + "/n";
                } else {
                    history = history + " Kode kolektibilitas diubah dari " + prevMat.getKodeKolektibilitas()+ " menjadi " + currMat.getKodeKolektibilitas()+ "/n";
                }
            }
            
            //17
            if(prevMat.getTglWanPrestasi()!=null){
            if (prevMat == null || !prevMat.getTglWanPrestasi().equals(currMat.getTglWanPrestasi())) {
                if (prevMat == null) {
                    history = history + " Tanggal wan prestasi : " + currMat.getTglWanPrestasi() + "/n";
                } else {
                    history = history + " Tanggal wan prestasi diubah dari " + prevMat.getTglWanPrestasi()+ " menjadi " + currMat.getTglWanPrestasi()+ "/n";
                }
            }
            }
            
            //18
            if (prevMat == null || !prevMat.getKodeKondisi().equals(currMat.getKodeKondisi())) {
                if (prevMat == null) {
                    history = history + " Kode kondisi : " + currMat.getKodeKondisi() + "/n";
                } else {
                    history = history + " Kode kondisi diubah dari " + prevMat.getKodeKondisi()+ " menjadi " + currMat.getKodeKondisi()+ "/n";
                }
            }
            
            //19
            if (prevMat == null || !prevMat.getTglKondisi().equals(currMat.getTglKondisi())) {
                if (prevMat == null) {
                    history = history + " Tanggal kondisi : " + currMat.getTglKondisi() + "/n";
                } else {
                    history = history + " Tanggal kondisi diubah dari " + prevMat.getTglKondisi()+ " menjadi " + currMat.getTglKondisi()+ "/n";
                }
            }
            
            //20
            if (prevMat == null || !prevMat.getKeterangan().equals(currMat.getKeterangan())) {
                if (prevMat == null) {
                    history = history + " Keterangan : " + currMat.getKeterangan() + "/n";
                } else {
                    history = history + " Keterangan diubah dari " + prevMat.getKeterangan()+ " menjadi " + currMat.getKeterangan()+ "/n";
                }
            }
            
            //21
            if (prevMat == null || !prevMat.getKodeKantorCabang().equals(currMat.getKodeKantorCabang())) {
                if (prevMat == null) {
                    history = history + " Kode kantor : " + currMat.getKodeKantorCabang() + "/n";
                } else {
                    history = history + " Kode kantor cabang diubah dari " + prevMat.getKodeKantorCabang()+ " menjadi " + currMat.getKodeKantorCabang()+ "/n";
                }
            }
            
            
        } catch (Exception e) {
            System.out.println(""+e.toString()+"");
            history="Eror";
        }
        
        return history;
    }
    
}
