/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.bankgaransi.BankGaransi;
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.entity.contentdata.ContentDataBidangUsaha;
import com.dimata.dslik.entity.contentdata.ContentDataGolonganDebitur;
import com.dimata.dslik.entity.contentdata.ContentDataHubDgnPelapor;
import com.dimata.dslik.entity.contentdata.ContentDataJenisIdentitas;
import com.dimata.dslik.entity.contentdata.ContentDataKabupatenKota;
import com.dimata.dslik.entity.contentdata.ContentDataKantorCabang;
import com.dimata.dslik.entity.contentdata.ContentDataKodeNegaraDomisili;
import com.dimata.dslik.entity.contentdata.ContentDataKodePekerjaan;
import com.dimata.dslik.entity.contentdata.ContentDataStatusPendidikan;
import com.dimata.dslik.entity.contentdata.ContentDataStatusPerkawinanDebitur;
import com.dimata.dslik.entity.contentdata.ContentDataSumberPenghasilan;
import com.dimata.dslik.entity.contentdata.PstContentDataBidangUsaha;
import com.dimata.dslik.entity.contentdata.PstContentDataGolonganDebitur;
import com.dimata.dslik.entity.contentdata.PstContentDataHubDgnPelapor;
import com.dimata.dslik.entity.contentdata.PstContentDataJenisIdentitas;
import com.dimata.dslik.entity.contentdata.PstContentDataKabupatenKota;
import com.dimata.dslik.entity.contentdata.PstContentDataKantorCabang;
import com.dimata.dslik.entity.contentdata.PstContentDataKodeNegaraDomisili;
import com.dimata.dslik.entity.contentdata.PstContentDataKodePekerjaan;
import com.dimata.dslik.entity.contentdata.PstContentDataStatusPendidikan;
import com.dimata.dslik.entity.contentdata.PstContentDataStatusPerkawinanDebitur;
import com.dimata.dslik.entity.contentdata.PstContentDataSumberPenghasilan;
import com.dimata.dslik.entity.debitur.Debitur;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.debiturindividu.*;
import com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain;
import com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.kreditjoinaccount.PstKreditJoinAccount;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.penjamin.PstPenjamin;
import com.dimata.dslik.entity.suratberharga.PstSuratBerharga;
import com.dimata.dslik.form.debiturindividu.*;
import com.dimata.dslik.session.proses.ProsesTransferDataBank;
import com.dimata.dslik.session.proses.SessionProsesPerlengkapan;
import com.dimata.gui.jsp.ControlCombo;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ardiadi
 */
public class AjaxDebiturIndividu extends HttpServlet {

    ///DATATABLES
    private String searchTerm;
    private String colName;
    private int colOrder;
    private String dir;
    private int start;
    private int amount;
    
    //OBJECT
    private JSONObject jSONObject = new JSONObject();
    private JSONArray jSONArray = new JSONArray();
    
    //LONG
    private long oid = 0;
    private long oidReturn = 0;
    
    //STRING
    private String dataFor = "";
    private String oidDelete = "";
    private String approot = "";
    private String htmlReturn = "";
    private String dateStart = "";
    private String dateEnd = "";
    private String cif = "";
    private String cifReturn = "";
    
    private boolean privAdd = false;
    private boolean privUpdate = false;
    private boolean privDelete = false;
    private boolean privView = false;
    
    //INT
    private int iCommand = 0;
    private int iErrCode = 0;
    
    private long userId=0;
    private String userName="";
    
    
    public final static String[] statusPendidikan = {
	"Tanpa Gelar",
	"Diploma 1",
	"Diploma 2",
	"Diploma 3",
	"S-1",
	"S-2",
	"S-3",
	"Lainnya"
    };
    
    public final static String[] kodeKab = {
	"Kota Salatiga",
	"Kab. Mojokerto",
	"Luar Wilayah Indonesia"
    };
    
    public final static String[] kodeNegara = {
	"Indonesia",
	"Malaysia",
	"Korea Selatan"
    };
    
    public final static String[] kodePekerjaan = {
	"Accounting/Finance Officer",
	"Customer service",
	"Engineering",
	"Eksekutif",
	"Administrasi umum",
	"Teknologi Informasi",
	"Konsultan/Analis",
	"Marketing",
	"Pengajar (Guru, Dosen)",
	"Militer",
	"Pensiunan",
	"Pelajar/Mahasiswa",
	"Wiraswasta",
	"Polisi",
	"Petani",
	"Nelayan",
	"Peternak",
	"Dokter",
	"Tenaga Medis (Perawat, Bidan, dsb)",
	"Hukum (Pengacara, Notaris)",
	"Perhotelan & Restoran (Koki, Bartender, dsb)",
	"Peneliti",
	"Desainer",
	"Arsitek",
	"Pekerja Seni (Artis, Musisi, Pelukis, dsb)",
	"Pengamanan",
	"Pialang/Broker",
	"Distributor",
	"Transportasi Udara (Pilot, Pramugari)",
	"Transportasi Laut (Nahkoda, ABK)",
	"Transportasi Darat (Masinis, Sopir, Kondektur)",
	"Buruh (Buruh Pabrik, Buruh Bangunan, Buruh Tani)",
	"Pertukangan & Pengrajin (Tukang Kayu, Pengrajin Kulit, dll)",
	"Ibu Rumah Tangga",
	"Pekerja Informal (Asisten Rumah Tangga, Asongan, dll)",
	"Pejabat Negara/Penyelenggara Negara",
	"Pegawai Pemerintahan/Lembaga Negara (selain Pejabat/Penyelenggara Negara)",
	"Lain-lain"
    };
    
    public static final String[] kodeBidangUsaha = {
	"Sektor Perkebunan Tembakau",
	"Sektor Pertanian Padi",
	"Sektor Jasa Perikanan"
    };
    
    public static final String[] kodeSumberPenghasilan = {
	"Gaji",
	"Usaha",
	"Lainnya"
    };
    
    public static final String[] jenisIdentitas = {
	"KTP",
	"Paspor"
    };
    
    public static final String[] golonganDebitur = {
	"Pengendali dan atau keluarga pengendali Pelapor",
	"Perusahan/badan dimana Pelapor bertindak sebagai pengendali (subsidiary)"
    };
    
    public static final String[] kodeKantorCabang = {
	"Fasilitas tercatat pada kantor cabang dengan kode cabang 002 ",
	"Fasilitas pada contoh nomor 1 pindah/mutasi dari cabang 002 ke cabang 023 "
    };

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        //LONG
	this.oid = FRMQueryString.requestLong(request, "FRM_FIELD_OID");
	this.oidReturn=0;
	
	//STRING
	this.dataFor = FRMQueryString.requestString(request, "FRM_FIELD_DATA_FOR");
	this.oidDelete = FRMQueryString.requestString(request, "FRM_FIELD_OID_DELETE");
	this.approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
	this.htmlReturn = "";
        this.cifReturn = "";
	
        //BOOLEAN
        this.privAdd = FRMQueryString.requestBoolean(request, "privadd");
        this.privDelete = FRMQueryString.requestBoolean(request, "privdelete");
        this.privUpdate = FRMQueryString.requestBoolean(request, "privupdate");
        this.privView = FRMQueryString.requestBoolean(request, "privview");
        
	//INT
	this.iCommand = FRMQueryString.requestCommand(request);
	this.iErrCode = 0;
	
	//OBJECT
	this.jSONObject = new JSONObject();
	
	switch(this.iCommand){
	    case Command.SAVE :
		commandSave(request);
	    break;
		
	    case Command.LIST :
		commandList(request, response);
	    break;
		
	    case Command.DELETEALL : 
		commandDeleteAll(request);
	    break;
	    
	    case Command.DELETE : 
		commandDelete(request);
	    break;
            
            case Command.DOWNLOADDATA:
                downloadItem(request);
	    break;
            
	    default : commandNone(request);
            break;
	}
	try{
	    
	    this.jSONObject.put("FRM_FIELD_HTML", this.htmlReturn);
	    this.jSONObject.put("FRM_FIELD_RETURN_OID", String.valueOf(this.oidReturn));
	    this.jSONObject.put("FRM_FIELD_DATE_START", this.dateStart);
	    this.jSONObject.put("FRM_FIELD_DATE_END", this.dateEnd);
            this.jSONObject.put("FRM_FIELD_CIF_RETURN",this.cifReturn);
	}catch(JSONException jSONException){
	    jSONException.printStackTrace();
	}
	
	response.getWriter().print(this.jSONObject);
        
    }
    
    public void commandNone(HttpServletRequest request){
	if(this.dataFor.equals("showform")){
	    this.htmlReturn = showForm(request);
	}else if(this.dataFor.equals("searchform")){
            this.htmlReturn = searchForm(request);
        }else if(this.dataFor.equals("showlistindividu")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull") || this.dataFor.equals("loadsummaryotherdebitur")){
            this.htmlReturn = getSummary(request);
        }else if(this.dataFor.equals("showotherform")){
            this.htmlReturn = otherForm(request);
        }else if(this.dataFor.equals("loadniksama")){
            this.htmlReturn = getNikSama(request);
        }else if(this.dataFor.equals("showniksamaform")){
            this.htmlReturn = nikSamaForm(request);
        }else if(this.dataFor.equals("searchformglobal")){
            this.htmlReturn = searchFormGlobal(request);
        }    
    }
    
   public void commandSave(HttpServletRequest request){
	 if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = saveDebIndv(request);
        }
    }
    
    
    public String saveDebIndv(HttpServletRequest request) {
        //history
        String returnData = "";
        CtrlDebiturIndividu ctrlDebiturIndividu = new CtrlDebiturIndividu(request);
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        ctrlDebiturIndividu.action(iCommand, oid, userId, userName);
        DebiturIndividu entDebiturIndividu = ctrlDebiturIndividu.getDebiturIndividu();
        returnData = ctrlDebiturIndividu.getMessage();
        this.oidReturn = entDebiturIndividu.getOID();
        this.cifReturn = entDebiturIndividu.getCif();
        return returnData;
    }
    
    public void commandDeleteAll(HttpServletRequest request){
	if(this.dataFor.equals("clearlist")){
	    this.htmlReturn = clearList(request);
	}
    }
    
    
    
    public void commandList(HttpServletRequest request, HttpServletResponse response){
	if(this.dataFor.equals("listdatasearch")){
	    String[] cols = { 
                PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID],
                PstDebitur.fieldNames[PstDebitur.FLD_CIF],
                PstDebitur.fieldNames[PstDebitur.FLD_NIK],
                PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if(this.dataFor.equals("showlistindividu")){
	    String[] cols = { 
                PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID],
                PstDebitur.fieldNames[PstDebitur.FLD_CIF],
                PstDebitur.fieldNames[PstDebitur.FLD_NIK],
                PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if(this.dataFor.equals("listdatasearchglobal")){
	    String[] cols = { 
                PstDebitur.fieldNames[PstDebitur.FLD_DEBITUR_OID],
                PstDebitur.fieldNames[PstDebitur.FLD_CIF],
                PstDebitur.fieldNames[PstDebitur.FLD_NIK],
                PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS],
                " REKENING "
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}
    }
    
    public void commandDelete(HttpServletRequest request){
	if(this.dataFor.equals("deleteitem")){
	    this.htmlReturn = deleteItem(request);
	}
    }
    
    public JSONObject listDataTables (HttpServletRequest request, HttpServletResponse response, String[] cols, String dataFor, JSONObject result){
        this.searchTerm = FRMQueryString.requestString(request, "sSearch");
        int amount = 10;
        int start = 0;
        int col = 0;
        String dir = "asc";
        String sStart = request.getParameter("iDisplayStart");
        String sAmount = request.getParameter("iDisplayLength");
        String sCol = request.getParameter("iSortCol_0");
        String sdir = request.getParameter("sSortDir_0");
        
        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0) {
                start = 0;
            }
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10) {
                amount = 10;
            }
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0 )
                col = 0;
        }
        if (sdir != null) {
            if (!sdir.equals("asc"))
            dir = "desc";
        }
        
	String cif = FRMQueryString.requestString(request, "cif");
        String npwp = FRMQueryString.requestString(request, "npwp");
        String nama = FRMQueryString.requestString(request, "nama");
        int type = FRMQueryString.requestInt(request, "type");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        String whereClause = "";
        
        Periode periode = new Periode();
        long periodeId=0;
        try{
            Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            if(listPeriode != null){
                periode = (Periode) listPeriode.get(0);
                periodeId=periode.getOID();
            }
        }catch(Exception ex){

        }
        
        if(userGroup!=4){
            whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }else{
            whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if(dataFor.equals("listdatasearch")){
            if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(npwp.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NIK]+"='"+npwp+"'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NIK]+"='"+npwp+"'";
                }
            }

            if(nama.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]+" LIKE '%"+nama+"%'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]+" LIKE '%"+nama+"%'";
                }
            }

            if(type == 1){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                }
            }
            if (whereClause.length() > 0) {
                whereClause += " AND (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
            } else {
                whereClause += " (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
            }
	}else if(dataFor.equals("showlistindividu")){
            if(status.equals("0")){
                if (whereClause.length() > 0) {
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }
                
            }
            
            if (whereClause.length() > 0) {
                whereClause += " AND (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
            } else {
                whereClause += " (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
            }
            
            whereClause+=" AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='1'";
            
        }else if(dataFor.equals("listdatasearchglobal")){
            if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(npwp.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NIK]+"='"+npwp+"'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NIK]+"='"+npwp+"'";
                }
            }

            if(nama.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]+" LIKE '%"+nama+"%'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]+" LIKE '%"+nama+"%'";
                }
            }

            if(type == 1){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                }else{
                    whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                }
            }
            if (whereClause.length() > 0) {
                whereClause += " AND (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
            } else {
                whereClause += " (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                    + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
            }
	}
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstDebitur.getCountJoin(whereClause,""+periodeId,kodeCabang);
            
	}else if(dataFor.equals("showlistindividu")){
	    total = PstDebitur.getCountJoin(whereClause,""+periodeId,kodeCabang);
            
	}else if(dataFor.equals("listdatasearchglobal")){
            if(!cif.equals("") || !npwp.equals("") || !nama.equals("")){
                total = PstDebitur.getCountGlobal(whereClause,""+periodeId,kodeCabang);
            }else{
                total=0;
            }
        }
        
        
        this.amount = amount;
       
        this.colName = colName;
        this.dir = dir;
        this.start = start;
        this.colOrder = col;
        
        try {
            result = getData(total, request, dataFor);
        } catch(Exception ex){
            System.out.println(ex);
        }
       
       return result;
    }
    
    public JSONObject getData(int total, HttpServletRequest request, String datafor){
        
        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        Debitur debitur = new Debitur();
	
        String cif = FRMQueryString.requestString(request, "cif");
        String npwp = FRMQueryString.requestString(request, "npwp");
        String nama = FRMQueryString.requestString(request, "nama");
        int type = FRMQueryString.requestInt(request, "type");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
        String whereClause = "";
        String order ="";
        
        Periode periode = new Periode();
        long periodeId=0;
        try{
            Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            if(listPeriode != null){
                periode = (Periode) listPeriode.get(0);
                periodeId=periode.getOID();
            }
        }catch(Exception ex){
        }
        
        if(userGroup!=4){
            whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }else{
            whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
            if(dataFor.equals("listdatasearch")){
	    	if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(npwp.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NIK]+"='"+npwp+"'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NIK]+"='"+npwp+"'";
                    }
                }

                if(nama.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]+" LIKE '%"+nama+"%'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]+" LIKE '%"+nama+"%'";
                    }
                }

                if(type == 1){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                    }
                }
                if (whereClause.length() > 0) {
                    whereClause += " AND (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
                } else {
                    whereClause += " (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
                }
            }else if(datafor.equals("showlistindividu")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                    
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                    
                }
                
                if (whereClause.length() > 0) {
                    whereClause += " AND (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
                } else {
                    whereClause += " (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
                }
                whereClause +=" AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='1'";
                
            }else if(dataFor.equals("listdatasearchglobal")){
	    	if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(npwp.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NIK]+"='"+npwp+"'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NIK]+"='"+npwp+"'";
                    }
                }

                if(nama.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]+" LIKE '%"+nama+"%'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]+" LIKE '%"+nama+"%'";
                    }
                }

                if(type == 1){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                    }else{
                        whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                    }
                }
                if (whereClause.length() > 0) {
                    whereClause += " AND (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
                } else {
                    whereClause += " (debitur." + PstDebitur.fieldNames[PstDebitur.FLD_CIF] + " LIKE '%" + searchTerm + "%' "
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NIK] + " LIKE '%" + searchTerm + "%'"
                        + " OR debitur." + PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector();
        if(datafor.equals("listdatasearchglobal")){
            if(!cif.equals("") || !npwp.equals("") || !nama.equals("")){
                 listData = PstDebitur.list(start, amount, whereClause, order,""+periodeId,kodeCabang);
            }else{
                 listData = new Vector();
            }
        }else{
            listData = PstDebitur.listJoin(start, amount, whereClause, order,""+periodeId,kodeCabang);
        }
        
        for (int i =0 ; i<listData.size();i++){
	    JSONArray ja = new JSONArray();
	    if(datafor.equals("listdatasearch")){
                debitur = (Debitur) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+debitur.getCif());
                ja.put(""+(debitur.getNik() == null ? "-" : debitur.getNik()));
                ja.put(""+(debitur.getNamaIdentitas() == null ? "-" : debitur.getNamaIdentitas()));
                String buttonUpdate = "";
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+debitur.getOID()+"&type="+debitur.getKodeJenisNsb()+"&cif="+debitur.getCif()+"&activetab=AjaxDebiturIndividu&otheroid="+debitur.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>"; 
                }
		ja.put(buttonUpdate);
		array.put(ja);
	    }else if(datafor.equals("showlistindividu")){
                debitur = (Debitur) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+debitur.getCif());
                ja.put(""+(debitur.getNik() == null ? "-" : debitur.getNik()));
                ja.put(""+(debitur.getNamaIdentitas() == null ? "-" : debitur.getNamaIdentitas()));
                String buttonUpdate = "";
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+debitur.getOID()+"&type="+debitur.getKodeJenisNsb()+"&cif="+debitur.getCif()+"&activetab=AjaxDebiturIndividu&otheroid="+debitur.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		array.put(ja);
                
	    }else if(datafor.equals("listdatasearchglobal")){
                
                debitur = (Debitur) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+debitur.getCif());
                ja.put(""+(debitur.getNik() == null ? "-" : debitur.getNik()));
                ja.put(""+(debitur.getNamaIdentitas() == null ? "-" : debitur.getNamaIdentitas()));
                
                //cek rekening masing-masing
                Vector vNoRekening  = PstKredit.list(0, 0, PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+debitur.getCif()+"' AND "+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'", ""+PstKredit.fieldNames[PstKredit.FLD_TGL_MULAI]+" ASC ");
                String noRekening="";
                if(vNoRekening.size()>0){
                     for (int v = 0; v < vNoRekening.size(); v++) {
                         Kredit entKredit =  (Kredit) vNoRekening.get(v);
                         noRekening = noRekening+" "+entKredit.getNoRekening();
                     }
                }
                
                Vector vBankGaransi  = PstBankGaransi.list(0, 0, PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+debitur.getCif()+"' AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'", ""+PstBankGaransi.fieldNames[PstBankGaransi.FLD_TGL_DITERBITKAN]+" ASC ");
                if(vBankGaransi.size()>0){
                     for (int v = 0; v < vBankGaransi.size(); v++) {
                         BankGaransi bankGaransi =  (BankGaransi) vBankGaransi.get(v);
                         noRekening = noRekening+" "+bankGaransi.getNoRekening();
                     }
                }
                
                if(noRekening.equals("")){
                    if(privView){
                        noRekening += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+debitur.getOID()+"&type="+debitur.getKodeJenisNsb()+"&cif="+debitur.getCif()+"&activetab=AjaxDebiturIndividu&otheroid="+debitur.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>"; 
                    }
                }
                
                ja.put(""+noRekening);
		array.put(ja);
	    }
        }
        
        totalAfterFilter = total;
        
        try {
            result.put("iTotalRecords", total);
            result.put("iTotalDisplayRecords", totalAfterFilter);
            result.put("aaData", array);
        } catch (Exception e) {

        }
        
        return result;
    }
    
    
    public String showForm(HttpServletRequest request){
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        Date dNow = new Date();        
        DebiturIndividu di = new DebiturIndividu();
        di.setKodeKantorCabang(kodeCabang);
        String tglLahir = "";
        String tglLahirPasangan = "";
        if(oid != 0){
             try {
                di = PstDebiturIndividu.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            try {
                if (di.getTglLahir()==null){
                    tglLahir ="";
                }else{
                    tglLahir = Formater.formatDate(di.getTglLahir(), "yyyy-MM-dd"); 
                }
            } catch (Exception e) {
               tglLahir ="";
            }
            try {
                if (di.getTglLahirPasangan()==null){
                    tglLahirPasangan ="";
                }else{
                    tglLahirPasangan = Formater.formatDate(di.getTglLahirPasangan(), "yyyy-MM-dd");
                }
               
            } catch (Exception e) {
               tglLahirPasangan ="";
            }
            
            
            //proses untuk mengecek data yang o, mandatory jika ada triger tertentu
            
            
        }     
        else {
            tglLahir = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglLahirPasangan = Formater.formatDate(dNow, "yyyy-MM-dd");
        }
        
        //tampilkan listKredit berdasarkan cif
        Vector listDi = new Vector();
        if(!cif.equals("")){
                String where = PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]+"='"+cif+"'";
                listDi = PstDebiturIndividu.list(0,0, ""+where, "");
        }
        
        
        Vector listJenisIdentitas = PstContentDataJenisIdentitas.listWithoutOid(0, 0, "", "");
        Vector jenisIdentitasKey = new Vector(1,1);
        Vector jenisIdentitasVal = new Vector(1,1);
        jenisIdentitasKey.add("");
        jenisIdentitasVal.add("-- Pilih --");
        if(listJenisIdentitas.size() > 0){
            for(int i = 0; i < listJenisIdentitas.size(); i++){
                ContentDataJenisIdentitas contentDataJenisIdentitas = (ContentDataJenisIdentitas) listJenisIdentitas.get(i);
                
                jenisIdentitasKey.add(""+contentDataJenisIdentitas.getKodeOjk());
                jenisIdentitasVal.add(""+contentDataJenisIdentitas.getNamaIdentitas());
            }
        }
        
        Vector listKodeHubungan = PstContentDataHubDgnPelapor.listWithoutOid(0, 0, "", "");
        Vector kodeHubunganKey = new Vector(1,1);
        Vector kodeHubunganVal = new Vector(1,1);
        if(listKodeHubungan.size() > 0){
            for(int i = 0; i < listKodeHubungan.size(); i++){
                ContentDataHubDgnPelapor contentDataHubDgnPelapor = (ContentDataHubDgnPelapor) listKodeHubungan.get(i);
                kodeHubunganKey.add(""+contentDataHubDgnPelapor.getKodeOjk());
                kodeHubunganVal.add(""+contentDataHubDgnPelapor.getHubunganDgnPelapor());
            }
        }
       
        
	String returnData = ""
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>Basic Information</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_CIF]+"'></i> "
                        + "CIF <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_CIF] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_CIF] +"' class='form-control' value='"+di.getCif()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_CIF]+"' data-type='text' data-number='true' data-alpha='true' data-special=''>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_JENIS_IDENTITAS]+"'></i> "
                        + "Jenis Identitas <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + ControlCombo.draw(FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_JENIS_IDENTITAS], null, ""+di.getJenisIdentitas(), jenisIdentitasKey, jenisIdentitasVal, "data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_JENIS_IDENTITAS]+"' data-type='text' ", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_NIK]+"'></i> "
                        + " Nomor Identitas <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NIK] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NIK] +"' class='form-control' value='"+di.getNik()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_NIK]+"' data-type='text' data-number='true' data-alpha='true' data-special=''>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_NAMA_IDENTITAS]+"'></i> "
                        + "Nama Sesuai Identitas <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NAMA_IDENTITAS] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NAMA_IDENTITAS] +"' class='form-control' value='"+di.getNamaIdentitas()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_NAMA_IDENTITAS]+"' data-type='text' data-number='false' data-alpha='true' data-special=\"-.' \">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_NAMA_LENGKAP]+"'></i> "
                        + "Nama Lengkap <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NAMA_LENGKAP] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NAMA_LENGKAP] +"' value='"+di.getNamaLengkap()+"' class='form-control' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_NAMA_LENGKAP]+"' data-type='text' data-number='false' data-alpha='true' data-special=\"-.' \">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_STATUS_GELAR]+"'></i> "
                        + "Kode Status/Gelar Debitur <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
                                Vector vCdPendidikan =  PstContentDataStatusPendidikan.listWitoutOid(0,0,"","");
                                Vector valueCdPendidikan = new Vector();
                                Vector keyCdPendidikan = new Vector();
                                valueCdPendidikan.add("");
                                keyCdPendidikan.add("-- Pilih --");
                                for (int i = 0; i < vCdPendidikan.size(); i++) {
                                    ContentDataStatusPendidikan KdPendidikan = (ContentDataStatusPendidikan) vCdPendidikan.get(i);
                                    valueCdPendidikan.add(String.valueOf(KdPendidikan.getKodeOjk()));
                                    keyCdPendidikan.add(KdPendidikan.getKodeOjk()+"-"+KdPendidikan.getStatusPendidikan());
                                }
                                String selectPria= "";
                                String selectWanita="";
                                if (di.getJekel().equals("L")){
                                    selectPria = "selected";
                                    selectWanita = "";
                                }else if (di.getJekel().equals("P")){
                                    selectPria = "";
                                    selectWanita = "selected";
                                }else{
                                    selectPria = "";
                                    selectWanita = "";
                                }
                                
                                returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_STATUS_GELAR], null, ""+di.getKodeStatusGelar(), valueCdPendidikan, keyCdPendidikan, "data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_STATUS_GELAR]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_JEKEL]+"'></i> "                
                        + "Jenis Kelamin <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<select name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_JEKEL] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_JEKEL] +"' class='form-control'  data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_JEKEL]+"' data-type='text'>"
				+ "<option value=''> -- Pilih --</option>"
				+ "<option "+selectPria+" value='L'> Pria/Laki-laki </option>"
				+ "<option "+selectWanita+" value='P'> Wanita/Perempuan </option>"
			    + "</select>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_TEMPAT_LAHIR]+"'></i> "                                
                        + "Tempat Lahir KTP <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TEMPAT_LAHIR] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TEMPAT_LAHIR] +"' class='form-control' value='"+di.getTempatLahir()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_TEMPAT_LAHIR]+"' data-type='text' data-number='true' data-alpha='true' data-special=' '>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_TGL_LAHIR]+"'></i> "                                                
                        + "Tanggal Lahir <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TGL_LAHIR] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TGL_LAHIR] +"' class='form-control datepicker' value='"+tglLahir+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_TGL_LAHIR]+"' data-type='text' >"
				+ "<div class='input-group-addon'> " 
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_NPWP]+"'></i> "                                                                
                        + "NPWP <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NPWP] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NPWP] +"' value='"+di.getNpwp()+"' data-required='' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_NPWP]+"' data-type='text' class='form-control nospecial' data-number='true' data-alpha='false' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_ALAMAT]+"'></i> "                                                                                
                        + "Alamat <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_ALAMAT] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_ALAMAT] +"'class='form-control' value='"+di.getAlamat()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_ALAMAT]+"' data-type='text' data-number='true' data-alpha='true' data-special=\"&,()-/.' \">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KELURAHAN]+"'></i> "                                                                                                
                        + "Kelurahan <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KELURAHAN] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KELURAHAN] +"' class='form-control' value='"+di.getKelurahan()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KELURAHAN]+"' data-type='text' data-number='true' data-alpha='true' data-special=\"-.'/ \">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KECAMATAN]+"'></i> "                                                                                                                
                        + "Kecamatan <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KECAMATAN] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KECAMATAN] +"' class='form-control' value='"+di.getKecamatan()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KECAMATAN]+"' data-type='text' data-number='true' data-alpha='true' data-special=\"-.'/ \">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_KAB]+"'></i> "                                                                                                                                
                        + "Kode Kab/Kota (Dati ii) <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			   //start comboBox
                                Vector vCdKab =  PstContentDataKabupatenKota.listWithoutOid(0,0,"","");
                                Vector valueCdKab = new Vector();
                                Vector keyCdKab = new Vector();
                                valueCdKab.add("");
                                keyCdKab.add("-- Pilih --");
                                for (int i = 0; i < vCdKab.size(); i++) {
                                    ContentDataKabupatenKota KdKab = (ContentDataKabupatenKota) vCdKab.get(i);
                                    valueCdKab.add(String.valueOf(KdKab.getKodeOjk()));
                                    keyCdKab.add(KdKab.getKodeOjk()+"-"+KdKab.getNamaKabupatenKota());
                                }

                                returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_KAB], null, ""+di.getKodeKab(), valueCdKab, keyCdKab, "data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_KAB]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_POS]+"'></i> "                                                                                                                                                
                        + "Kode Pos <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_POS] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_POS] +"' class='form-control' value='"+di.getKodePos()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_POS]+"' data-type='text' data-number='true' data-alpha='false' data-special=''>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_TELEPON]+"'></i> "                                                                                                                                                                
                        + "Telepon <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TELEPON] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TELEPON] +"' class='form-control numericonly' value='"+di.getTelepon()+"' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_TELEPON]+"' data-type='text' data-number='true' data-alpha='false' data-special=''>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_NOMOR_HP]+"'></i> "                                                                                                                                                                                
                        + "Nomor Telepon Genggam <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NOMOR_HP] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NOMOR_HP] +"' class='form-control numericonly' value='"+di.getNomorHp()+"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_NOMOR_HP]+"' data-type='text' data-number='true' data-alpha='false' data-special=''>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_EMAIL]+"'></i> "                                                                                                                                                                                                
                        + "Alamat Email <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_EMAIL] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_EMAIL] +"' class='form-control' value='"+di.getEmail()+"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_EMAIL]+"' data-type='email' data-number='true' data-alpha='true' data-special='@._-'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_DOMISILI]+"'></i> "                                                                                                                                                                                                                
                        + "Kode Negara Domisili <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			      //start comboBox
                                Vector vCdNegara =  PstContentDataKodeNegaraDomisili.listWithoutOid(0,0,"","");
                                Vector valueCdNegara = new Vector();
                                Vector keyCdNegara = new Vector();
                                valueCdNegara.add("");
                                keyCdNegara.add("-- Pilih --");
                                for (int i = 0; i < vCdNegara.size(); i++) {
                                    ContentDataKodeNegaraDomisili KdNegara = (ContentDataKodeNegaraDomisili) vCdNegara.get(i);
                                    valueCdNegara.add(String.valueOf(KdNegara.getKodeOjk()));
                                    keyCdNegara.add(KdNegara.getKodeOjk()+"-"+KdNegara.getNamaNegara());
                                }

                                returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_DOMISILI], null, ""+di.getKodeDomisili(), valueCdNegara, keyCdNegara, "data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_DOMISILI]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		+ "<h4>Job Information</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_PEKERJAAN]+"'></i> "                                                                                                                                                                                                                                
                        + "Kode Pekerjaan <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdPekerjaan =  PstContentDataKodePekerjaan.listWitoutOid(0,0,"","");
                                Vector valueCdPekerjaan = new Vector();
                                Vector keyCdPekerjaan = new Vector();
                                valueCdPekerjaan.add("");
                                keyCdPekerjaan.add("-- Pilih --");
                                for (int i = 0; i < vCdPekerjaan.size(); i++) {
                                    ContentDataKodePekerjaan KdPekerjaan = (ContentDataKodePekerjaan) vCdPekerjaan.get(i);
                                    valueCdPekerjaan.add(String.valueOf(KdPekerjaan.getKodeOjk()));
                                    keyCdPekerjaan.add(KdPekerjaan.getKodeOjk()+"-"+KdPekerjaan.getNamaPekerjaan());
                                }

                                returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_PEKERJAAN], null, ""+di.getKodePekerjaan(), valueCdPekerjaan, keyCdPekerjaan, "data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_PEKERJAAN]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_TEMPAT_BEKERJA]+"'></i> "                                                                                                                                                                                                                                                
                        + "Tempat Bekerja <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' value='"+di.getTempatBekerja()+"' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TEMPAT_BEKERJA] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TEMPAT_BEKERJA] +"' class='form-control' data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_TEMPAT_BEKERJA]+"' data-type='text' data-number='true' data-alpha='true' data-special=\"&-',.() \">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_USAHA_TEMPAT_BEKERJA]+"'></i> "                                                                                                                                                                                                                                                                
                        + "Kode Bidang Usaha Tempat Bekerja Debitur <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdBidang =  PstContentDataBidangUsaha.listWithoutOid(0,0,"","");
                                Vector valueCdBidang = new Vector();
                                Vector keyCdBidang = new Vector();
                                valueCdBidang.add("");
                                keyCdBidang.add("-- Pilih --");
                                for (int i = 0; i < vCdBidang.size(); i++) {
                                    ContentDataBidangUsaha KdBidang = (ContentDataBidangUsaha) vCdBidang.get(i);
                                    valueCdBidang.add(String.valueOf(KdBidang.getKodeOjk()));
                                    keyCdBidang.add(KdBidang.getKodeOjk()+"-"+KdBidang.getNamaBidangUsaha());
                                }

                                returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_USAHA_TEMPAT_BEKERJA], null, ""+di.getKodeUsahaTempatBekerja(), valueCdBidang, keyCdBidang, "data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_USAHA_TEMPAT_BEKERJA]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_ALAMAT_TEMPAT_BEKERJA]+"'></i> "                                                                                                                                                                                                                                                                                
                        + "Alamat Tempat Bekerja <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_ALAMAT_TEMPAT_BEKERJA] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_ALAMAT_TEMPAT_BEKERJA] +"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_ALAMAT_TEMPAT_BEKERJA]+"' data-type='text' class='form-control' value='"+di.getAlamatTempatBekerja()+"' data-number='true' data-alpha='true' data-special=\"&-',.()/ \">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_PENGHASILAN_KOTOR]+"'></i> "                                                                                                                                                                                                                                                                                
                                        + "Penghasilan Kotor Per Tahun <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_PENGHASILAN_KOTOR] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_PENGHASILAN_KOTOR] +"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_PENGHASILAN_KOTOR]+"' data-type='text' class='form-control numericonly' value='"+di.getPenghasilanKotor()+"' data-number='true' data-alpha='false' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_PENGHASILAN]+"'></i> " 
                        + "Kode Sumber Penghasilan <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
                                Vector vCdSumber =  PstContentDataSumberPenghasilan.listWithoutOid(0,0,"","");
                                Vector valueCdSumber = new Vector();
                                Vector keyCdSumber = new Vector();
                                valueCdSumber.add("");
                                keyCdSumber.add("-- Pilih --");
                                for (int i = 0; i < vCdSumber.size(); i++) {
                                    ContentDataSumberPenghasilan KdSumber = (ContentDataSumberPenghasilan) vCdSumber.get(i);
                                    valueCdSumber.add(String.valueOf(KdSumber.getKodeOjk()));
                                    keyCdSumber.add(KdSumber.getKodeOjk()+"-"+KdSumber.getSumberPenghasilan());
                                }

                                returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_PENGHASILAN], null, ""+di.getKodePenghasilan(), valueCdSumber, keyCdSumber, " data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_PENGHASILAN]+"' data-type='text' data-required='required' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		+ "</div>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>Family Information</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_JML_TANGGUNGAN]+"'></i> " 
                                        + "Jumlah Tanggungan <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_JML_TANGGUNGAN] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_JML_TANGGUNGAN] +"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_JML_TANGGUNGAN]+"' data-type='text' class='form-control numericonly' value='"+di.getJmlTanggungan()+"' data-number='true' data-alpha='false' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_HUB]+"'></i> " 
                                        + "Kode Hubungan dengan Pelapor<i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + ControlCombo.draw(FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_HUB], "-- Pilih --", ""+di.getKodeHub(), kodeHubunganKey, kodeHubunganVal, " data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_HUB]+"' data-type='text' data-required='required'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_KODE_GOL]+"'></i> " 
                                        + "Kode Golongan Debitur <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
                                Vector vCdGolDeb =  PstContentDataGolonganDebitur.listWithoutOid(0,0,"","");
                                Vector valueCdGolDeb = new Vector();
                                Vector keyCdGolDeb = new Vector();
                                valueCdGolDeb.add("");
                                keyCdGolDeb.add("-- Pilih --");
                                for (int i = 0; i < vCdGolDeb.size(); i++) {
                                    ContentDataGolonganDebitur KdGolDeb = (ContentDataGolonganDebitur) vCdGolDeb.get(i);
                                    valueCdGolDeb.add(String.valueOf(KdGolDeb.getKodeOjk()));
                                    keyCdGolDeb.add(KdGolDeb.getKodeOjk()+"-"+KdGolDeb.getGolonganDebitur());
                                }

                                returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_GOL], null, ""+di.getKodeGol(), valueCdGolDeb, keyCdGolDeb, "data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_KODE_GOL]+"' data-type='text' data-required='required' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_STATUS]+"'></i> " 
                                        + "Status Perkawinan Debitur <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector listStatusKawin = PstContentDataStatusPerkawinanDebitur.listWithoutOid(0, 0, "", "");
                            Vector statusKawinKey = new Vector(1,1);
                            Vector statusKawinVal = new Vector(1,1);
                            statusKawinKey.add("");
                            statusKawinVal.add("-- Pilih --");
                            if(listStatusKawin.size() > 0){
                                for(int i = 0; i < listStatusKawin.size(); i++){
                                    ContentDataStatusPerkawinanDebitur contentDataStatusPerkawinanDebitur = (ContentDataStatusPerkawinanDebitur) listStatusKawin.get(i);
                                    statusKawinKey.add(""+contentDataStatusPerkawinanDebitur.getKodeOjk());
                                    statusKawinVal.add(contentDataStatusPerkawinanDebitur.getKodeOjk()+"-"+contentDataStatusPerkawinanDebitur.getStatusPerkawinan());
                                }
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_STATUS], null, ""+di.getStatus(),statusKawinKey, statusKawinVal," data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_STATUS]+"' data-type='text' data-required='required' ","form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                    + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_NIK_PASANGAN]+"'></i> " 
                                    + "Nomor Identitas Pasangan <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NIK_PASANGAN] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NIK_PASANGAN] +"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_NIK_PASANGAN]+"' data-type='text' class='form-control nospecial' data-number='true' data-alpha='true' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                    + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_NAMA_PASANGAN]+"'></i> " 
                                    + "Nama Pasangan <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NAMA_PASANGAN] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NAMA_PASANGAN] +"' value='"+di.getNamaPasangan()+"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_NAMA_PASANGAN]+"' data-type='text' class='form-control' data-number='false' data-alpha='true' data-special=\" -.'\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                    + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_TGLLAHIR_PASANGAN]+"'></i> " 
                                    + "Tanggal Lahir Pasangan <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TGLLAHIR_PASANGAN] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_TGLLAHIR_PASANGAN] +"' value='"+tglLahirPasangan+"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_TGLLAHIR_PASANGAN]+"' data-type='text' class='form-control datepicker'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                    + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_NAMA_IBU_KANDUNG]+"'></i> " 
                                    + "Nama Gadis Ibu Kandung <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NAMA_IBU_KANDUNG] +"' id='"+ FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_NAMA_IBU_KANDUNG] +"' value='"+di.getNamaIbuKandung()+"' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_NAMA_IBU_KANDUNG]+"' data-type='text' class='form-control' data-required='required' data-number='false' data-alpha='true' data-special=\" -.'\">"
			+ "</div>"
		    + "</div>"
		+ "</div>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>Other Information</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                    + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_PERJANJIAN_PISAH_HARGA]+"'></i> " 
                                    + "Perjanjian Pisah Harga <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>";
                            
                            Vector pisahHargaKey = new Vector(1,1);
                            Vector pisahHargaVal = new Vector(1,1);
                            for(int i = 0; i < PstDebitur.pisahHargaKey.length; i++){
                                pisahHargaKey.add(""+PstDebitur.pisahHargaKey[i]);
                                pisahHargaVal.add(PstDebitur.pisahHargaKey[i]+"-"+PstDebitur.pisahHargaVal[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_PERJANJIAN_PISAH_HARGA], "-- Pilih --", ""+di.getPerjanjianPisahHarga(), pisahHargaKey, pisahHargaVal, "data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_PERJANJIAN_PISAH_HARGA]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                    + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_MELANGGAR_BMPK]+"'></i> " 
                                    + "Melanggar BMPK/BMPD <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + ControlCombo.draw(FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_MELANGGAR_BMPK], "-- Pilih --", ""+di.getMelanggarBmpk(), pisahHargaKey, pisahHargaVal, "data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_MELANGGAR_BMPK]+"' data-type='text' ", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                    + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturIndividu.fieldQuestion[FrmDebiturIndividu.FRM_FIELD_MELAMPAUI_BMPK]+"'></i> " 
                                    + "Melampaui BMPK/BMPD <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + ControlCombo.draw(FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_MELAMPAUI_BMPK], "-- Pilih --", ""+di.getMelampauiBmpk(), pisahHargaKey, pisahHargaVal, "data-required='required' data-error-message='"+FrmDebiturIndividu.fieldError[FrmDebiturIndividu.FRM_FIELD_MELAMPAUI_BMPK]+"' data-type='text' ", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>Kode Kantor Cabang <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
			    Vector vCdKantorCbg =  PstContentDataKantorCabang.list(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+di.getKodeKantorCabang()+"'","");
                            ContentDataKantorCabang kdKantorCbg = new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKantorCbg.size(); i++) {
                                kdKantorCbg = (ContentDataKantorCabang) vCdKantorCbg.get(i);
                            }
                          
                            //returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_KANTOR_CABANG], null, ""+di.getKodeKantorCabang(), valueCdKantorCbg, keyCdKantorCbg, "data-required='required' onchange=\"javascript:changeFunc();\" readonly='readonly'", "form-control");
                            returnData += ""
                            + "<input type='text' name='DISPLAY_"+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_KANTOR_CABANG]+"' class='form-control'  data-required='required' readonly='readonly' value='"+kdKantorCbg.getKodeOjk()+" "+kdKantorCbg.getNamaKantorCabang()+"'>"
                            + "<input type='hidden' name='"+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+"'></div>"
                             + "<input type='hidden' name='"+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_KODE_JENIS_NSB]+"' value='"+di.getKodeJenisNsb()+"'></div>"
		    + "</div>"
                    + "<div class='form-group hidden'>"
			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'>*</font></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
                            Vector operasiDataKey = new Vector();
                            Vector operasiDataVal = new Vector();
                            operasiDataVal.add("-- Pilih --");
                            operasiDataKey.add("");
                            for (int i = 0; i < PstDebitur.operasiData.length; i++) {
                                operasiDataKey.add(""+PstDebitur.operasiDataKey[i]);
                                operasiDataVal.add(""+PstDebitur.operasiData[i]);
                            }
                          
                            returnData += ControlCombo.draw(""+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_OPERASI_DATA], null, ""+di.getStatusOperasiData(), operasiDataKey, operasiDataVal, "data-required='required' ", "form-control");
                            returnData += "</div>"
		    + "</div>"
                    + "<div class='form-group' style='display:none;'>"
			+ "<label class='control-label col-sm-4'>Status Data</label>"
			+ "<div class='col-sm-8'>"
                            + "<input type='hidden' name='"+FrmDebiturIndividu.fieldNames[FrmDebiturIndividu.FRM_FIELD_STATUS_DATA]+"' value='0'>";
                            returnData += "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
	
	
	return returnData;
    }
    
    public String searchForm(HttpServletRequest request){
        String returnData = ""
        + "<div class='row'>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>CIF</label>"
                    + "<input type='hidden' name='type' id='type' value='1'>"
                    + "<input type='text' name='cif' class='form-control' id='cif'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>NIK</label>"
                    + "<input type='text' name='npwp' class='form-control' id='npwp'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Nama</label>"
                    + "<input type='text' name='nama' class='form-control' id='nama'>"
                + "</div>"
            + "</div>"
             + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>&nbsp;</label>"
                    + "<button name='search' type='button' class='btn btn-primary form-control' id='btnsearch'><i class='fa fa-search'></i> Cari</button>"
                + "</div>"
            + "</div>"
        + "</div>"
        + "<hr>"
        + "<div class='row'>"
            + "<div class='col-sm-12' id='searchElement'>"
                + "<table class='table table-striped table-bordered'>"
                    + "<thead>"
                        + "<tr>"
                            + "<th>No.</th>"
                            + "<th>CIF</th>"
                            + "<th>NIK</th>"
                            + "<th>Nama</th>"
                            + "<th>Aksi</th>"
                        + "</tr>"
                    + "</thead>"
                + "</table>"
            + "</div>"
        + "</div>";
        
        return returnData;
    }
    
    
    public String searchFormGlobal(HttpServletRequest request){
        String returnData = ""
        + "<div class='row'>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>CIF</label>"
                    + "<input type='hidden' name='type' id='type' value='1'>"
                    + "<input type='text' name='cif' class='form-control' id='cif'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>NIK</label>"
                    + "<input type='text' name='npwp' class='form-control' id='npwp'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Nama</label>"
                    + "<input type='text' name='nama' class='form-control' id='nama'>"
                + "</div>"
            + "</div>"
             + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>&nbsp;</label>"
                    + "<button name='search' type='button' class='btn btn-primary form-control' id='btnsearch'><i class='fa fa-search'></i> Cari</button>"
                + "</div>"
            + "</div>"
        + "</div>"
        + "<hr>"
        + "<div class='row'>"
            + "<div class='col-sm-12' id='searchElement'>"
                + "<table class='table table-striped table-bordered'>"
                    + "<thead>"
                        + "<tr>"
                            + "<th>No.</th>"
                            + "<th>CIF</th>"
                            + "<th>NIK</th>"
                            + "<th>Nama</th>"
                            + "<th>No Rekening</th>"
                        + "</tr>"
                    + "</thead>"
                + "</table>"
            + "</div>"
        + "</div>";
        
        return returnData;
    }
    
    public String showListForm(HttpServletRequest request){
        String returnData = "";
        returnData+=""
        + "<div id='showListElement'>"
            + "<table class='table table-bordered table-striped'>"
                + "<thead>"
                    + "<tr>"
                        + "<th>No.</th>"
                        + "<th>CIF</th>"
                        + "<th>NIK</th>"
                        + "<th>Nama</th>"
                        + "<th>Aksi</th>"
                    + "</tr>"
                + "</thead>"
            + "</table>"
        + "</div>";
        return returnData;
    }
    
    public String getSummary(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        String whereClause = "";
        if(this.dataFor.equals("loadsummary")){
            whereClause = "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+"='0' AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='1'";
        }else if(this.dataFor.equals("loadsummarynull")){
            whereClause = "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"='1'";
        }else{
            whereClause = "l."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if(userGroup != 4){
            if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
                whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                whereClause += " AND l."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }else{
            if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
                whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                whereClause += " AND l."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        
        int summary = 0;
        if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            summary = PstDebitur.getCountJoin(whereClause,""+periodeId, kodeCabang);
        }else{
            summary = SessionProsesPerlengkapan.getCountJoinForDebitur(whereClause);
        }
        
        returnData +=""+summary;
        return returnData;
    }
    
    public String getNikSama(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        Vector listSama = PstDebiturIndividu.listDoubleNik(0, 0, kodeCabang, periodeId);
        
        returnData +=""+listSama.size();
        return returnData;
    }
    
    public String otherForm(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        
        String whereClause = "l."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
        if(userGroup != 4){
            whereClause += " AND l."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
        }
        Vector listData = SessionProsesPerlengkapan.listOtherDebitur(0, 0, whereClause, "l."+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+" ASC");
        returnData+=""
        + "<span class='pull-right'><input type='checkbox' class='checkall' value='0'> Check All</span>"
        + "<table class='table table-bordered table-strped'>"
            + "<thead>"
                + "<tr>"
                    + "<th>No.</th>"
                    + "<th>CIF</th>"
                    + "<th>NIK</th>"
                    + "<th>Nama</th>"
                    + "<th>Aksi</th>"
                + "</tr>"
            + "</thead>"
            + "<tbody>";
                for(int i = 0; i < listData.size(); i++){
                    Debitur data = (Debitur) listData.get(i);
                    returnData+=""
                    + "<tr>"
                        + "<td>"+(i+1)+"</td>"
                        + "<td>"+data.getCif()+"</td>"
                        + "<td>"+data.getNpwp()+"</td>"
                        + "<td>"+data.getNamaLengkap()+"</td>"
                        + "<td><input type='checkbox' name='delete' value='"+data.getOID()+"' class='deletelist'></td>"
                    + "</tr>";
                }
                returnData+= 
                ""
            + "</tbody>"    
        + "</table>";
        return returnData;
    }
    
    public String nikSamaForm(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        Vector listData = PstDebiturIndividu.listDoubleNik(0, 0, kodeCabang, periodeId);
        returnData+=""
        + "<table class='table table-bordered table-strped'>"
            + "<thead>"
                + "<tr>"
                    + "<th>No.</th>"
                    + "<th>No Rekening</th>"
                    + "<th>CIF</th>"
                    + "<th>NIK</th>"
                    + "<th>Nama</th>"
                    + "<th>Aksi</th>"
                + "</tr>"
            + "</thead>"
            + "<tbody>";
                for(int i = 0; i < listData.size(); i++){
                    DebiturIndividu data = (DebiturIndividu) listData.get(i);
                    DebiturIndividu debiturIndividu = new DebiturIndividu();
                    Vector listOid = PstDebiturIndividu.list(0, 1, ""+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]+"='"+data.getCif()+"' AND "+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERIODE_ID]+"='"+periodeId+"'", "");
                    if(listOid.size() > 0){
                        debiturIndividu = (DebiturIndividu) listOid.get(0);
                    }
                    returnData+=""
                    + "<tr>"
                        + "<td>"+(i+1)+"</td>"
                        + "<td>"+data.getNoRekening()+"</td>"    
                        + "<td>"+data.getCif()+"</td>"
                        + "<td>"+data.getNik()+"</td>"
                        + "<td>"+data.getNamaIdentitas()+"</td>"
                        + "<td><a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command="+Command.VIEW+"&oid="+debiturIndividu.getOID()+"&type="+debiturIndividu.getKodeJenisNsb()+"&cif="+data.getCif()+"&activetab=AjaxDebiturIndividu&otheroid="+debiturIndividu.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a></td>"
                    + "</tr>";
                }
                returnData+= 
                ""
            + "</tbody>"    
        + "</table>";
        return returnData;
    }
    
    public String clearList(HttpServletRequest request){
        String[] oidDelete = FRMQueryString.requestStringValues(request, "delete");
        String returnData = "";
        for(int i = 0; i < oidDelete.length; i++){
            long oid = 0;
            try{
                oid = Long.parseLong(oidDelete[i]);
            }catch(Exception ex){
                
            }
            CtrlDebiturIndividu ctrlDebiturIndividu = new CtrlDebiturIndividu(request);
            ctrlDebiturIndividu.action(Command.DELETE, oid, 0, "");
            String msg = ctrlDebiturIndividu.getMessage();
            returnData = "<i class='fa fa-info'></i> "+msg;
        }
        
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        String cif = FRMQueryString.requestString(request, "cif");
        returnData += "<i class='fa fa-info'></i> ";
        //CEK SEMUA KOMPONEN
        int total = 0;
        Periode periode = new Periode();
        long periodeId=0;
            try{
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
                if(listPeriode != null){
                    periode = (Periode) listPeriode.get(0);
                    periodeId=periode.getOID();
                }
            }catch(Exception ex){

            }
            
        total += PstKredit.getCount(""+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"' AND "+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'");
        total += PstBankGaransi.getCount(""+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"' AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'");
        total += PstAgunan.getCount(""+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cif+"' AND "+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'");
        total += PstPenjamin.getCount(""+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"' AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_PERIODE_ID]+"='"+periodeId+"'");
        total += PstSuratBerharga.getCount(""+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cif+"' AND "+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID]+"='"+periodeId+"'");
        total += PstKreditJoinAccount.getCount(""+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_CIF]+"='"+cif+"' AND "+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PERIODE_ID]+"='"+periodeId+"'");
        total += PstIrrevocableLc.getCount(""+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cif+"' AND "+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID]+"='"+periodeId+"'");
        total += PstFasilitasLain.getCount(""+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+"='"+cif+"' AND "+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID]+"='"+periodeId+"'");
        //total += PstPenjamin.getCount(""+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"' AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_PERIODE_ID]+"='"+periodeId+"'");
        
        if(total > 0){
            returnData  +=" Data tidak dapat dihapus";
            this.oidReturn = 0;
        }else{
            DebiturIndividu debiturBdnUsaha = new DebiturIndividu();
            Vector listData = PstDebiturIndividu.list(0, 0, ""+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]+"='"+cif+"' AND "+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERIODE_ID]+"='"+periodeId+"'", "");
            if(listData.size() > 0){
                debiturBdnUsaha = (DebiturIndividu) listData.get(0);
            }
            CtrlDebiturIndividu ctrlDebiturBdnUsaha = new CtrlDebiturIndividu(request);
            if(debiturBdnUsaha.getOID()!=0){
                ctrlDebiturBdnUsaha.action(iCommand, debiturBdnUsaha.getOID(), 0, "");
            }
            returnData +=""+ctrlDebiturBdnUsaha.getMessage();
            this.oidReturn = 1;
        }
        
        
        return returnData;
    }
    
    public String downloadItem(HttpServletRequest request){
        String returnData = "";
        String cif = FRMQueryString.requestString(request, "cif");
        
        //proses download data per cif
        //hhohoho lakukan prosesnya
        Periode periode = new Periode();
        long periodeId=0;
        try{
            Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            if(listPeriode != null){
                periode = (Periode) listPeriode.get(0);
                periodeId=periode.getOID();
            }
        }catch(Exception ex){

        }
        //proses download data per cif
        //hhohoho lakukan prosesnya
        ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
        String debitur = "";
        if(!cif.equals("")){
            debitur = prosesTransferDataBank.actionTransferDataDebitur(""+cif,periodeId, 1, 1);
        }
        
       // String xxx = "Sukses";
        this.htmlReturn =""+debitur;
        return debitur;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>
}
