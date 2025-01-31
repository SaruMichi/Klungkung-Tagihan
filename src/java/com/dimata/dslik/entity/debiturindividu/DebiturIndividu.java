/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.debiturindividu;

/**
 *
 * @author m20n9
 */
import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.qdep.entity.Entity;
import com.dimata.util.Formater;
import java.util.Date;

public class DebiturIndividu extends Entity implements I_LogHistory{

    private String flagDetail = "";
    private String cif = "";
    private String jenisIdentitas = "";
    private String nik = "";
    private String namaIdentitas = "";
    private String namaLengkap = "";
    private String kodeStatusGelar = "";
    private String jekel = "";
    private String tempatLahir = "";
    private Date tglLahir = null;
    private String npwp = "";
    private String alamat = "";
    private String kelurahan = "";
    private String kecamatan = "";
    private String kodeKab = "";
    private String kodePos = "";
    private String telepon = "";
    private String nomorHp = "";
    private String email = "";
    private String kodeDomisili = "";
    private String kodePekerjaan = "";
    private String tempatBekerja = "";
    private String kodeUsahaTempatBekerja = "";
    private String alamatTempatBekerja = "";
    private double penghasilanKotor = 0;
    private String kodePenghasilan = "";
    private int jmlTanggungan = 0;
    private String kodeHub = "";
    private String kodeGol = "";
    private String status = "";
    private String nikPasangan = "";
    private String namaPasangan = "";
    private Date tglLahirPasangan = null;
    private String perjanjianPisahHarga = "";
    private String melanggarBmpk = "";
    private String melampauiBmpk = "";
    private String namaIbuKandung = "";
    private String kodeKantorCabang = "";
    private String operasiData = "";
    private String historySql = "";
    private long periodeId = 0;
    private String kodeJenisNsb = "";
    private int statusOperasiData = 0;
    private int statusData = 0;
    
    private String noRekening = "";
    private Date tglAkadAkhir = null;
    
    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        if (flagDetail==null){
            this.flagDetail ="";
        }else{
            this.flagDetail = flagDetail;
        }
        
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        if (cif==null){
            this.cif = "";
        }else{
            this.cif = cif;
        }
        
    }

    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    public void setJenisIdentitas(String jenisIdentitas) {
        if (jenisIdentitas==null){
            this.jenisIdentitas ="";
        }else{
            this.jenisIdentitas = jenisIdentitas;
        }
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        if (nik==null){
            this.nik = "";
        }else{
            this.nik = nik;
        }
        
    }

    public String getNamaIdentitas() {
        return namaIdentitas;
    }

    public void setNamaIdentitas(String namaIdentitas) {
        if (namaIdentitas==null){
            this.namaIdentitas ="";
        }else{
            this.namaIdentitas = namaIdentitas;
        }
        
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        if (namaLengkap==null){
            this.namaLengkap = "";
        }else{
            this.namaLengkap = namaLengkap;
        }
        
    }

    public String getKodeStatusGelar() {
        return kodeStatusGelar;
    }

    public void setKodeStatusGelar(String kodeStatusGelar) {
        if (kodeStatusGelar==null){
            this.kodeStatusGelar = "";
        }else{
            this.kodeStatusGelar = kodeStatusGelar;
        }
        
    }

    public String getJekel() {
        return jekel;
    }

    public void setJekel(String jekel) {
        if (jekel==null){
            this.jekel = "";
        }else{
            this.jekel = jekel;
        }
        
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        if (tempatLahir==null){
            this.tempatLahir = "";
        }else{
            this.tempatLahir = tempatLahir;
        }
        
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        if (npwp==null){
            this.npwp = "";
        }else{
            this.npwp = npwp;
        }
        
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        if (alamat==null){
            this.alamat = "";
        }else{
            this.alamat = alamat;
        }
        
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        if (kelurahan==null){
            this.kelurahan = "";
        }else{
            this.kelurahan = kelurahan;
        }
        
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        if (kecamatan==null){
            this.kecamatan = "";
        }else{
            this.kecamatan = kecamatan;
        }
        
    }

    public String getKodeKab() {
        return kodeKab;
    }

    public void setKodeKab(String kodeKab) {
        if (kodeKab==null){
            this.kodeKab = "";
        }else{
            this.kodeKab = kodeKab;
        }
        
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        if (kodePos==null){
            this.kodePos = "";
        }else{
            this.kodePos = kodePos;
        }
        
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        if (telepon==null){
            this.telepon = "";
        }else{
            this.telepon = telepon;
        }
        
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        if (nomorHp==null){
            this.nomorHp = "";
        }else{
            this.nomorHp = nomorHp;
        }
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email==null){
            this.email = "";
        }else{
            this.email = email;
        }
        
    }

    public String getKodeDomisili() {
        return kodeDomisili;
    }

    public void setKodeDomisili(String kodeDomisili) {
        if (kodeDomisili==null){
            this.kodeDomisili = "";
        }else{
            this.kodeDomisili = kodeDomisili;
        }
        
    }

    public String getKodePekerjaan() {
        return kodePekerjaan;
    }

    public void setKodePekerjaan(String kodePekerjaan) {
        if (kodePekerjaan==null){
            this.kodePekerjaan = "";
        }else{
            this.kodePekerjaan = kodePekerjaan;
        }
        
    }

    public String getTempatBekerja() {
        return tempatBekerja;
    }

    public void setTempatBekerja(String tempatBekerja) {
        if (tempatBekerja==null){
            this.tempatBekerja = "";
        }else{
            this.tempatBekerja = tempatBekerja;
        }
        
    }

    public String getKodeUsahaTempatBekerja() {
        return kodeUsahaTempatBekerja;
    }

    public void setKodeUsahaTempatBekerja(String kodeUsahaTempatBekerja) {
        if (kodeUsahaTempatBekerja==null){
            this.kodeUsahaTempatBekerja = "";
        }else{
            this.kodeUsahaTempatBekerja = kodeUsahaTempatBekerja;
        }
        
    }

    public double getPenghasilanKotor() {
        return penghasilanKotor;
    }

    public void setPenghasilanKotor(double penghasilanKotor) {
        this.penghasilanKotor = penghasilanKotor;
    }

    public String getKodePenghasilan() {
        return kodePenghasilan;
    }

    public void setKodePenghasilan(String kodePenghasilan) {
        if (kodePenghasilan==null){
            this.kodePenghasilan = "";
        }else{
            this.kodePenghasilan = kodePenghasilan;
        }
        
    }

    public int getJmlTanggungan() {
        return jmlTanggungan;
    }

    public void setJmlTanggungan(int jmlTanggungan) {
        this.jmlTanggungan = jmlTanggungan;
    }

    public String getKodeHub() {
        return kodeHub;
    }

    public void setKodeHub(String kodeHub) {
        if (kodeHub==null){
            this.kodeHub = "";
        }else{
            this.kodeHub = kodeHub;
        }
        
    }

    public String getKodeGol() {
        return kodeGol;
    }

    public void setKodeGol(String kodeGol) {
        if (kodeGol==null){
            this.kodeGol = "";
        }else{
            this.kodeGol = kodeGol;
        }
        
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status==""){
            this.status = "";
        }else{
            this.status = status;
        }
        
    }

    public String getNikPasangan() {
        return nikPasangan;
    }

    public void setNikPasangan(String nikPasangan) {
        if (nikPasangan==null){
            this.nikPasangan = "";
        }else{
            this.nikPasangan = nikPasangan;
        }
        
    }

    public String getNamaPasangan() {
        return namaPasangan;
    }

    public void setNamaPasangan(String namaPasangan) {
        if (namaPasangan==null){
            this.namaPasangan = "";
        }else{
            this.namaPasangan = namaPasangan;
        }
        
    }

    public Date getTglLahirPasangan() {
        return tglLahirPasangan;
    }

    public void setTglLahirPasangan(Date tglLahirPasangan) {
        this.tglLahirPasangan = tglLahirPasangan;
    }

    public String getPerjanjianPisahHarga() {
        return perjanjianPisahHarga;
    }

    public void setPerjanjianPisahHarga(String perjanjianPisahHarga) {
        if (perjanjianPisahHarga==null){
            this.perjanjianPisahHarga = "";
        }else{
            this.perjanjianPisahHarga = perjanjianPisahHarga;
        }
        
    }

    public String getMelanggarBmpk() {
        return melanggarBmpk;
    }

    public void setMelanggarBmpk(String melanggarBmpk) {
        if (melanggarBmpk==null){
            this.melanggarBmpk = "";
        }else{
            this.melanggarBmpk = melanggarBmpk;
        }
        
    }

    public String getMelampauiBmpk() {
        return melampauiBmpk;
    }

    public void setMelampauiBmpk(String melampauiBmpk) {
        if (melampauiBmpk==null){
            this.melampauiBmpk = "";
        }else{
            this.melampauiBmpk = melampauiBmpk;
        }
        
    }

    public String getNamaIbuKandung() {
        return namaIbuKandung;
    }

    public void setNamaIbuKandung(String namaIbuKandung) {
        if (namaIbuKandung==null){
            this.namaIbuKandung = "";
        }else{
            this.namaIbuKandung = namaIbuKandung;
        }
        
    }

    public String getKodeKantorCabang() {
        return kodeKantorCabang;
    }

    public void setKodeKantorCabang(String kodeKantorCabang) {
        if (kodeKantorCabang==null){
            this.kodeKantorCabang = "";
        }else{
            this.kodeKantorCabang = kodeKantorCabang;
        }
        
    }

    public String getOperasiData() {
        return operasiData;
    }

    public void setOperasiData(String operasiData) {
        if (operasiData==null){
            this.operasiData = "";
        }else{
            this.operasiData = operasiData;
        }
        
    }

    /**
     * @return the alamatTempatBekerja
     */
    public String getAlamatTempatBekerja() {
        return alamatTempatBekerja;
    }

    /**
     * @param alamatTempatBekerja the alamatTempatBekerja to set
     */
    public void setAlamatTempatBekerja(String alamatTempatBekerja) {
        if (alamatTempatBekerja==null){
            this.alamatTempatBekerja = "";
        }else{
            this.alamatTempatBekerja = alamatTempatBekerja;
        }
        
    }
    
    
    @Override
    public String getLogDetail(Entity prevDoc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String history="";
        DebiturIndividu prevMat = (DebiturIndividu)prevDoc;
        try {
            //0
            if(prevMat == null || !prevMat.getFlagDetail().equals(this.getFlagDetail())){
                if (prevMat== null){
                    history=history+" Flag Detail : "+this.getFlagDetail()+"<br>";
                }else{
                    history=history+" Flag Detail diubah dari "+prevMat.getFlagDetail()+" menjadi "+this.getFlagDetail()+"<br>";
                }
            }
              
            //1
            if( prevMat == null || !prevMat.getCif().equals(this.getCif())){
                if (prevMat == null){
                    history=history+" CIF : "+this.getCif()+"<br>";
                }else{
                    history=history+" CIF diubah dari "+prevMat.getCif()+" menjadi "+this.getCif()+"<br>";
                }
            }     
            
            //2
            if( prevMat == null || !prevMat.getJenisIdentitas().equals(this.getJenisIdentitas())){
                if (prevMat == null){
                    history=history+" Jenis Identitas : "+this.getJenisIdentitas()+"<br>";
                }else{
                    history=history+" Jenis Identitas diubah dari "+prevMat.getJenisIdentitas()+" menjadi "+this.getJenisIdentitas()+"<br>";
                }
            }
            
            //3
            if( prevMat==null || !prevMat.getNik().equals(this.getNik())){
                if (prevMat == null){
                    history=history+" NIK : "+this.getNik()+"<br>";
                }else{
                    history=history+" NIK diubah dari "+prevMat.getNik()+" menjadi "+this.getNik()+"<br>";
                }
            }
            
            //4
            if( prevMat==null || !prevMat.getNamaIdentitas().equals(this.getNamaIdentitas())){
                if (prevMat == null){
                    history=history+" Nama Sesuai Identitas : "+this.getNamaIdentitas()+"<br>";
                }else{
                    history=history+" Nama Sesuai Identitas diubah dari "+prevMat.getNamaIdentitas()+" menjadi "+this.getNamaIdentitas()+"<br>";
                }
            }
            
            //5
            if( prevMat==null || !prevMat.getNamaLengkap().equals(this.getNamaLengkap())){
                if (prevMat == null){
                    history=history+" Nama Lengkap : "+this.getNamaLengkap()+"<br>";
                }else{
                    history=history+" Nama Lengkap diubah dari "+prevMat.getNamaLengkap()+" menjadi "+this.getNamaLengkap()+"<br>";
                }
            }
            
            //6
            if( prevMat==null || !prevMat.getStatus().equals(this.getStatus())){
                if (prevMat == null){
                    history=history+" Kode Status/Gelar Debitur : "+this.getStatus()+"<br>";
                }else{
                    history=history+" Kode Status/Gelar Debitur diubah dari "+prevMat.getStatus()+" menjadi "+this.getStatus()+"<br>";
                }
            }
            
            //7
            if( prevMat==null || !prevMat.getJekel().equals(this.getJekel())){
                if (prevMat == null){
                    history=history+" Jenis Kelamin : "+this.getJekel()+"<br>";
                }else{
                    history=history+" Jenis Kelamin diubah dari "+prevMat.getJekel()+" menjadi "+this.getJekel()+"<br>";
                }
            }
            
            //8
            if( prevMat==null || !prevMat.getTempatLahir().equals(this.getTempatLahir())){
                if (prevMat == null){
                    history=history+" Tempat lahir KTP : "+this.getTempatLahir()+"<br>";
                }else{
                    history=history+" Tempat lahir KTP diubah dari "+prevMat.getTempatLahir()+" menjadi "+this.getTempatLahir()+"<br>";
                }
            }
            
            //9
            if( prevMat==null || !prevMat.getTglLahir().equals(this.getTglLahir())){
                if (prevMat == null){
                    history=history+" Tanggal Lahir : "+this.getTglLahir()+"<br>";
                }else{
                    history=history+" Tanggal Lahir diubah dari "+prevMat.getTglLahir()+" menjadi "+this.getTglLahir()+"<br>";
                }
            }
            
            //10
            if( prevMat==null || !prevMat.getNpwp().equals(this.getNpwp())){
                if (prevMat == null){
                    history=history+" NPWP : "+this.getNpwp()+"<br>";
                }else{
                    history=history+" NPWP diubah dari "+prevMat.getNpwp()+" menjadi "+this.getNpwp()+"<br>";
                }
            }
            
            //11
            if( prevMat==null || !prevMat.getAlamat().equals(this.getAlamat())){
                if (prevMat == null){
                    history=history+" Alamat : "+this.getAlamat()+"<br>";
                }else{
                    history=history+" Alamat diubah dari "+prevMat.getAlamat()+" menjadi "+this.getAlamat()+"<br>";
                }
            }
            
            //12
            if( prevMat==null || !prevMat.getKelurahan().equals(this.getKelurahan())){
                if (prevMat == null){
                    history=history+" Kelurahan : "+this.getKelurahan()+"<br>";
                }else{
                    history=history+" Kelurahan diubah dari "+prevMat.getKelurahan()+" menjadi "+this.getKelurahan()+"<br>";
                }
            }
            
            //13
            if( prevMat==null || !prevMat.getKecamatan().equals(this.getKecamatan())){
                if (prevMat == null){
                    history=history+" Kecamatan : "+this.getKecamatan()+"<br>";
                }else{
                    history=history+" Kecamatan diubah dari "+prevMat.getKecamatan()+" menjadi "+this.getKecamatan()+"<br>";
                }
            }
            
            //14
            if( prevMat==null || !prevMat.getKodeKab().equals(this.getKodeKab())){
                if (prevMat == null){
                    history=history+" Kode Kab/Kota (Dati ii) : "+this.getKodeKab()+"<br>";
                }else{
                    history=history+" Kode Kab/Kota (Dati ii) diubah dari "+prevMat.getKodeKab()+" menjadi "+this.getKodeKab()+"<br>";
                }
            }
            
            //15
            if( prevMat==null || !prevMat.getKodePos().equals(this.getKodePos())){
                if (prevMat == null){
                    history=history+" Kode Pos : "+this.getKodePos()+"<br>";
                }else{
                    history=history+" Kode Pos diubah dari "+prevMat.getKodePos()+" menjadi "+this.getKodePos()+"<br>";
                }
            }
            
            //16
            if( prevMat==null || !prevMat.getTelepon().equals(this.getTelepon())){
                if (prevMat == null){
                    history=history+" Telepon : "+this.getTelepon()+"<br>";
                }else{
                    history=history+" Telepon diubah dari "+prevMat.getTelepon()+" menjadi "+this.getTelepon()+"<br>";
                }
            }
            
            //17
            if( prevMat==null || !prevMat.getNomorHp().equals(this.getNomorHp())){
                if (prevMat == null){
                    history=history+" Nomor Telepon Genggam : "+this.getNomorHp()+"<br>";
                }else{
                    history=history+" Nomor Telepon Genggam diubah dari "+prevMat.getNomorHp()+" menjadi "+this.getNomorHp()+"<br>";
                }
            }
            
            //18
            if( prevMat==null || !prevMat.getEmail().equals(this.getEmail())){
                if (prevMat == null){
                    history=history+" Email : "+this.getEmail()+"<br>";
                }else{
                    history=history+" Email diubah dari "+prevMat.getEmail()+" menjadi "+this.getEmail()+"<br>";
                }
            }
            
            //19
            if( prevMat==null || !prevMat.getKodeDomisili().equals(this.getKodeDomisili())){
                if (prevMat == null){
                    history=history+" Kode Negara Domisili : "+this.getKodeDomisili()+"<br>";
                }else{
                    history=history+" Kode Negara Domisili dari "+prevMat.getKodeDomisili()+" menjadi "+this.getKodeDomisili()+"<br>";
                }
            }
            
            //20
            if( prevMat==null || !prevMat.getKodePekerjaan().equals(this.getKodePekerjaan())){
                if (prevMat == null){
                    history=history+" Kode Pekerjaan : "+this.getKodePekerjaan()+"<br>";
                }else{
                    history=history+" Kode Pekerjaan diubah dari "+prevMat.getKodePekerjaan()+" menjadi "+this.getKodePekerjaan()+"<br>";
                }
            }
            
            //21
            if( prevMat==null || !prevMat.getTempatBekerja().equals(this.getTempatBekerja())){
                if (prevMat == null){
                    history=history+" Tempat Bekerja : "+this.getTempatBekerja()+"<br>";
                }else{
                    history=history+" Tempat Bekerja diubah dari "+prevMat.getTempatBekerja()+" menjadi "+this.getTempatBekerja()+"<br>";
                }
            }
            
            //22
            if( prevMat==null || !prevMat.getKodeUsahaTempatBekerja().equals(this.getKodeUsahaTempatBekerja())){
                if (prevMat == null){
                    history=history+" Kode Bidang Usaha Tempat Bekerja Debitur : "+this.getKodeUsahaTempatBekerja()+"<br>";
                }else{
                    history=history+" Kode Bidang Usaha Tempat Bekerja Debitur diubah dari "+prevMat.getKodeUsahaTempatBekerja()+" menjadi "+this.getKodeUsahaTempatBekerja()+"<br>";
                }
            }
            
            //23
            if( prevMat==null || !prevMat.getAlamatTempatBekerja().equals(this.getAlamatTempatBekerja())){
                if (prevMat == null){
                    history=history+" Alamat Tempat Bekerja Debitur : "+this.getAlamatTempatBekerja()+"<br>";
                }else{
                    history=history+" Alamat Tempat Bekerja Debitur diubah dari "+prevMat.getAlamatTempatBekerja()+" menjadi "+this.getAlamatTempatBekerja()+"<br>";
                }
            }
            
            //24
            if( prevMat==null || prevMat.getPenghasilanKotor() != this.getPenghasilanKotor()){
                if (prevMat == null){
                    history=history+" Penghasilan Kotor Per Tahun : "+Formater.formatNumber(this.getPenghasilanKotor(),"#,###")+"<br>";
                }else{
                    history=history+" Penghasilan Kotor Per Tahun diubah dari "+Formater.formatNumber(prevMat.getPenghasilanKotor(),"#,###")+" menjadi "+Formater.formatNumber(this.getPenghasilanKotor(),"#,###")+"<br>";
                }
            }  
            
            //25
            if( prevMat==null || !prevMat.getKodePenghasilan().equals(this.getKodePenghasilan())){
                if (prevMat == null){
                    history=history+" Kode Sumber Penghasilan : "+this.getKodePenghasilan()+"<br>";
                }else{
                    history=history+" Kode Sumber Penghasilan diubah dari "+prevMat.getKodePenghasilan()+" menjadi "+this.getKodePenghasilan()+"<br>";
                }
            }
            
            //26
            if( prevMat==null || prevMat.getJmlTanggungan() != this.getJmlTanggungan()){
                if (prevMat == null){
                    history=history+" Jumlah Tanggungan : "+this.getJmlTanggungan()+"<br>";
                }else{
                    history=history+" Jumlah Tanggungan diubah dari "+prevMat.getJmlTanggungan()+" menjadi "+this.getJmlTanggungan()+"<br>";
                }
            }
            
            //27
            if( prevMat==null || !prevMat.getKodeHub().equals(this.getKodeHub())){
                if (prevMat == null){
                    history=history+" Kode Hubungan : "+this.getKodeHub()+"<br>";
                }else{
                    history=history+" Kode Hubungan diubah dari "+prevMat.getKodeHub()+" menjadi "+this.getKodeHub()+"<br>";
                }
            }
            
            //28
            if( prevMat==null || !prevMat.getKodeGol().equals(this.getKodeGol())){
                if (prevMat == null){
                    history=history+" Kode Golongan Debitur : "+this.getKodeGol()+"<br>";
                }else{
                    history=history+" Kode Golongan Debitur diubah dari "+prevMat.getKodeGol()+" menjadi "+this.getKodeGol()+"<br>";
                }
            }
            
            //29
            if( prevMat==null || !prevMat.getStatus().equals(this.getStatus())){
                if (prevMat == null){
                    history=history+" Status Perkawinan Debitur : "+this.getStatus()+"<br>";
                }else{
                    history=history+" Status Perkawinan Debitur diubah dari "+prevMat.getStatus()+" menjadi "+this.getStatus()+"<br>";
                }
            }
            
            //30
            if( prevMat==null || !prevMat.getNikPasangan().equals(this.getNikPasangan())){
                if (prevMat == null){
                    history=history+" NIK atau Passport Pasangan : "+this.getNikPasangan()+"<br>";
                }else{
                    history=history+" NIK atau Passport Pasangan diubah dari "+prevMat.getNikPasangan()+" menjadi "+this.getNikPasangan()+"<br>";
                }
            }
            
            //31
            if( prevMat==null || !prevMat.getNamaPasangan().equals(this.getNamaPasangan())){
                if (prevMat == null){
                    history=history+" Nama Pasangan : "+this.getNamaPasangan()+"<br>";
                }else{
                    history=history+" Nama Pasangan diubah dari "+prevMat.getNamaPasangan()+" menjadi "+this.getNamaPasangan()+"<br>";
                }
            }
            
            //32
            if( prevMat==null || !prevMat.getTglLahirPasangan().equals(this.getTglLahirPasangan())){
                if (prevMat == null){
                    history=history+" Tanggal Lahir Pasangan : "+this.getTglLahirPasangan()+"<br>";
                }else{
                    history=history+" Tanggal Lahir Pasangan diubah dari "+prevMat.getTglLahirPasangan()+" menjadi "+this.getTglLahirPasangan()+"<br>";
                }
            }
            
            //33
            if( prevMat==null || !prevMat.getNamaIbuKandung().equals(this.getNamaIbuKandung())){
                if (prevMat == null){
                    history=history+" Nama Ibu Kandung  : "+this.getNamaIbuKandung()+"<br>";
                }else{
                    history=history+" Nama Ibu Kandung diubah dari "+prevMat.getNamaIbuKandung()+" menjadi "+this.getNamaIbuKandung()+"<br>";
                }
            }
            
            //34
            if( prevMat==null || !prevMat.getPerjanjianPisahHarga().equals(this.getPerjanjianPisahHarga())){
                if (prevMat == null){
                    history=history+" Perjanjian  Pisah Harga  : "+this.getPerjanjianPisahHarga()+"<br>";
                }else{
                    history=history+" Perjanjian  Pisah Harga diubah dari "+prevMat.getPerjanjianPisahHarga()+" menjadi "+this.getPerjanjianPisahHarga()+"<br>";
                }
            }
            
            //35
            if( prevMat==null || !prevMat.getMelanggarBmpk().equals(this.getMelanggarBmpk())){
                if (prevMat == null){
                    history=history+" Melanggar BMPK/BMPD : "+this.getMelanggarBmpk()+"<br>";
                }else{
                    history=history+" Melanggar BMPK/BMPD diubah dari "+prevMat.getMelanggarBmpk()+" menjadi "+this.getMelanggarBmpk()+"<br>";
                }
            }
            
            //36
            if( prevMat==null || !prevMat.getMelampauiBmpk().equals(this.getMelampauiBmpk())){
                if (prevMat == null){
                    history=history+" Melampaui BMPK/BMPD : "+this.getMelampauiBmpk()+"<br>";
                }else{
                    history=history+" Melampaui BMPK/BMPD diubah dari "+prevMat.getMelampauiBmpk()+" menjadi "+this.getMelampauiBmpk()+"<br>";
                }
            }
            
            //37
            if( prevMat==null || !prevMat.getKodeKantorCabang().equals(this.getKodeKantorCabang())){
                if (prevMat == null){
                    history=history+" Kode Kantor Cabang : "+this.getKodeKantorCabang()+"<br>";
                }else{
                    history=history+" Kode Kantor Cabang diubah dari "+prevMat.getKodeKantorCabang()+" menjadi "+this.getKodeKantorCabang()+"<br>";
                }
            }
            
        } catch (Exception e) {
            System.out.println(""+e.toString()+"");
        }
        
        //lanjutkan
        
        return history;
    }

    /**
     * @return the historySql
     */
    public String getHistorySql() {
        return historySql;
    }

    /**
     * @param historySql the historySql to set
     */
    public void setHistorySql(String historySql) {
        if (historySql==null){
            this.historySql = "";
        }else{
            this.historySql = historySql;
        }
        
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
     * @return the kodeJenisNsb
     */
    public String getKodeJenisNsb() {
        return kodeJenisNsb;
    }

    /**
     * @param kodeJenisNsb the kodeJenisNsb to set
     */
    public void setKodeJenisNsb(String kodeJenisNsb) {
        this.kodeJenisNsb = kodeJenisNsb;
    }

    /**
     * @return the statusOperasiData
     */
    public int getStatusOperasiData() {
        return statusOperasiData;
    }

    /**
     * @param statusOperasiData the statusOperasiData to set
     */
    public void setStatusOperasiData(int statusOperasiData) {
        this.statusOperasiData = statusOperasiData;
    }

    /**
     * @return the statusData
     */
    public int getStatusData() {
        return statusData;
    }

    /**
     * @param statusData the statusData to set
     */
    public void setStatusData(int statusData) {
        this.statusData = statusData;
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
     * @return the tglAkadAkhir
     */
    public Date getTglAkadAkhir() {
        return tglAkadAkhir;
    }

    /**
     * @param tglAkadAkhir the tglAkadAkhir to set
     */
    public void setTglAkadAkhir(Date tglAkadAkhir) {
        this.tglAkadAkhir = tglAkadAkhir;
    }
}