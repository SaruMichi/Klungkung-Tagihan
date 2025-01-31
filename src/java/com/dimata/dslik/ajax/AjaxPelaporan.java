/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.common.entity.logger.LogSysHistory;
import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.agunan.Agunan;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.bankgaransi.BankGaransi;
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.debiturbdnusaha.DebiturBdnUsaha;
import com.dimata.dslik.entity.debiturbdnusaha.PstDebiturBdnUsaha;
import com.dimata.dslik.entity.debiturindividu.DebiturIndividu;
import com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu;
import com.dimata.dslik.entity.fasilitaslain.FasilitasLain;
import com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain;
import com.dimata.dslik.entity.irrevocablelc.IrrevocableLc;
import com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.kreditjoinaccount.KreditJoinAccount;
import com.dimata.dslik.entity.kreditjoinaccount.PstKreditJoinAccount;
import com.dimata.dslik.entity.laporankeuangandebitur.LaporanKeuanganDebitur;
import com.dimata.dslik.entity.laporankeuangandebitur.PstLaporanKeuanganDebitur;
import com.dimata.dslik.entity.masterdata.CabangBank;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstCabangBank;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.pengurusataupemilik.PengurusAtauPemilik;
import com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik;
import com.dimata.dslik.entity.penjamin.Penjamin;
import com.dimata.dslik.entity.penjamin.PstPenjamin;
import com.dimata.dslik.entity.suratberharga.PstSuratBerharga;
import com.dimata.dslik.entity.suratberharga.SuratBerharga;
import com.dimata.gui.jsp.ControlList;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author QA
 */
public class AjaxPelaporan extends HttpServlet {

    //DATATABLES
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

    //INT
    private int iCommand = 0;
    private int iErrCode = 0;

    private long userId = 0;
    private String userName = "";

    public final static int SEGMEN_DEBITUR_INDIVIDU = 0;
    public final static int SEGMEN_DEBITUR_BADAN_USAHA = 1;
    public final static int SEGMEN_PENGURUS_PEMILIK = 2;
    public final static int SEGMEN_KREDIT = 3;
    public final static int SEGMEN_BANK_GARANSI = 4;
    public final static int SEGMEN_AGUNAN = 5;
    public final static int SEGMEN_PENJAMIN = 6;
    public final static int SEGMEN_LAPORAN_KEUANGAN_DEBITUR = 7;
    public final static int SEGMEN_SURAT_BERHARGA = 8;
    public final static int SEGMEN_KREDIT_JOIN = 9;
    public final static int SEGMEN_IRREVOCABLE_LC = 10;
    public final static int SEGMEN_FASILITAS_LAINNYA = 11;

    public final static String[] segmenNames = {
        "Debitur Individu",
        "Debitur Badan Usaha",
        "Pengurus/Pemilik",
        "Kredit",
        "Bank Garansi",
        "Agunan",
        "Penjamin",
        "Laporan Keuangan Debitur",
        "Surat Berharga",
        "Kredit Join",
        "Irrevocable L/C",
        "Fasilitas Lainnya"
    };

    public final static String[] segmenEror = {
        "Debitur Individu",
        "Debitur Badan Usaha",
        "Pengurus/Pemilik",
        "Kredit",
        "Bank Garansi",
        "Agunan",
        "Penjamin",
        "Laporan Keuangan Debitur",
        "Surat Berharga",
        "Kredit Join",
        "Irrevocable L/C",
        "Fasilitas Lainnya"
    };

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //LONG
        this.oid = FRMQueryString.requestLong(request, "FRM_FIELD_OID");
        this.oidReturn = 0;

        //STRING
        this.dataFor = FRMQueryString.requestString(request, "FRM_FIELD_DATA_FOR");
        this.oidDelete = FRMQueryString.requestString(request, "FRM_FIELD_OID_DELETE");
        this.approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        this.htmlReturn = "";

        //INT
        this.iCommand = FRMQueryString.requestCommand(request);
        this.iErrCode = 0;

        //OBJECT
        this.jSONObject = new JSONObject();

        switch (this.iCommand) {
            case Command.SAVE:
                //history
                commandSave(request);
                break;

            case Command.LIST:
                commandList(request, response);
                break;

            case Command.DELETEALL:
                commandDeleteAll(request);
                break;

            case Command.DELETE:
                commandDelete(request);
                break;

            case Command.SEARCH:
                commandSearch(request);
                break;

            default:
                commandNone(request);
        }
        try {

            this.jSONObject.put("FRM_FIELD_HTML", this.htmlReturn);
            this.jSONObject.put("FRM_FIELD_RETURN_OID", this.oidReturn);
            this.jSONObject.put("FRM_FIELD_DATE_START", this.dateStart);
            this.jSONObject.put("FRM_FIELD_DATE_END", this.dateEnd);
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }

        response.getWriter().print(this.jSONObject);

    }

    public void commandNone(HttpServletRequest request) {
        if (this.dataFor.equals("showform")) {
            this.htmlReturn = showForm(request);
        } else if (this.dataFor.equals("searchform")) {
            //this.htmlReturn = searchForm(request);
        } else if (this.dataFor.equals("showform2")) {
            //this.htmlReturn = showForm2(request);
        } else if (this.dataFor.equals("showlistagunan")) {
            //this.htmlReturn = showListForm(request);
        }
    }

    public void commandSave(HttpServletRequest request) {
        if (this.dataFor.equals("showform")) {
            //history
            this.htmlReturn = saveAgunan(request);
        }
    }

    public String saveAgunan(HttpServletRequest request) {
        //history
        String returnData = "";
        return returnData;
    }

    public void commandDeleteAll(HttpServletRequest request) {
        if (this.dataFor.equals("deleterateplan")) {

        }
    }

    public void commandSearch(HttpServletRequest request) {
        if (this.dataFor.equals("searchsummerysegmen")) {
            this.htmlReturn = searchSummarySegmen(request);
        } else if (this.dataFor.equals("searchpersegment")) {
            this.htmlReturn = searchPerSegment(request);
        }
    }

    public void commandList(HttpServletRequest request, HttpServletResponse response) {

    }

    public void commandDelete(HttpServletRequest request) {
        if (this.dataFor.equals("deleterule")) {
            //deleteRules(request);
        }
    }

    /*public JSONObject listDataTables (HttpServletRequest request, HttpServletResponse response, String[] cols, String dataFor, JSONObject result){
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
        String nama = FRMQueryString.requestString(request, "nama");
        String alamat = FRMQueryString.requestString(request, "alamat");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        String whereClause = "";
        String alias = "";
        if(dataFor.equals("listdatasearch") || dataFor.equals("showlistagunan")){
            alias = "agunan.";
        }
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        if(dataFor.equals("listdatasearch")){
	    if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(nama.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+nama+"%'";
                }else{
                    whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+nama+"%'";
                }
            }

            if(alamat.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+alamat+"%'";
                }else{
                    whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+alamat+"%'";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if (dataFor.equals("listdataagunan")){
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause = " 1=1";
            whereClause += ""
                + " AND "+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cifParam+"'"
                + " AND ("
                    + " "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_STATUS_AGUNAN]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_BUKTI_KEPEMILIKAN]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" like '%"+this.searchTerm+"%'"
                + ")";
        }else if(dataFor.equals("showlistagunan")){
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA]+"='"+status+"'";
                }else{
                    whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA]+" IS NULL"; 
                }else{
                    whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA]+" IS NULL"; 
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
	    }
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstAgunan.getCountJoin(whereClause);
	}else if (dataFor.equals("listdataagunan")){
            total = PstAgunan.getCount(whereClause);
        }else if(dataFor.equals("showlistagunan")){
            total = PstAgunan.getCountJoin(whereClause);
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
        Agunan agunan = new Agunan();
	
	String cif = FRMQueryString.requestString(request, "cif");
        String nama = FRMQueryString.requestString(request, "nama");
        String alamat = FRMQueryString.requestString(request, "alamat");
        String whereClause = "";
        String order ="";
	boolean privUpdate = FRMQueryString.requestBoolean(request, "privUpdate");
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        String alias = "";
        if(datafor.equals("listdatasearch") || datafor.equals("showlistagunan")){
            alias = "agunan.";
        }
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(datafor.equals("listdatasearch")){
		if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(nama.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+nama+"%'";
                    }else{
                        whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+nama+"%'";
                    }
                }

                if(alamat.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+alamat+"%'";
                    }else{
                        whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+alamat+"%'";
                    }
                }
		if(whereClause.length() > 0){
                    whereClause += " AND (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if (datafor.equals("listdataagunan")){
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause = " 1=1";
                whereClause += ""
                    + " AND "+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cifParam+"'"
                    + " AND ("
                        + " "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_STATUS_AGUNAN]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_BUKTI_KEPEMILIKAN]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" like '%"+this.searchTerm+"%'"
                    + ")";
            }else if(dataFor.equals("showlistagunan")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA]+"='"+status+"'";  
                    }else{
                        whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA]+"='"+status+"'";  
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA]+" IS NULL";
                    }else{
                        whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA]+" IS NULL";
                    }
                }
                if(whereClause.length() > 0){
                    whereClause += " AND (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(datafor.equals("listdatasearch")){
	    listData = PstAgunan.listJoin(start, amount, whereClause, order);
	}else if (datafor.equals("listdataagunan")){
            listData = PstAgunan.list(start, amount, whereClause, order);
        }else if (datafor.equals("showlistagunan")){
            listData = PstAgunan.listJoin(start, amount, whereClause, order);
        }
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
	    if(datafor.equals("listdatasearch")){
		agunan = (Agunan) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+agunan.getCif());
                ja.put(""+(agunan.getNamaPemilikAgunan()== null ? "-" : agunan.getNamaPemilikAgunan()));
                ja.put(""+(agunan.getAlamatAgunan()== null ? "-" : agunan.getAlamatAgunan()));
		ja.put("<button name='view' type='button' data-oid='"+agunan.getDebiturOid()+"' data-type='"+agunan.getDebiturType()+"' data-cif='"+agunan.getCif()+"' class='btn btn-warning edit'><i class='fa fa-pencil'></i> Edit</button>");
		
		array.put(ja);
	    }else if (datafor.equals("listdataagunan")){
                agunan = (Agunan) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(agunan.getKodeRegisterAgunan()));
                ja.put(""+(agunan.getNoRekening()));
                ja.put(""+(agunan.getCif()));
                ja.put(""+(agunan.getKodeStatusAgunan()));
                ja.put(""+(agunan.getNamaPemilikAgunan()));
                ja.put(""+(agunan.getBuktiKepemilikan()));
                ja.put(""+(agunan.getAlamatAgunan()));
                ja.put("<button name='view' type='button' data-oid='"+agunan.getOID()+"' data-type='"+agunan.getKodeJenisAgunan()+"' data-for ='showform2' data-target='AjaxAgunan' data-command='"+Command.NONE+"' class='btn btn-warning btnaddgeneralagunan'><i class='fa fa-pencil'></i> Edit</button>");
                array.put(ja);
            }else if(datafor.equals("showlistagunan")){
		agunan = (Agunan) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+agunan.getCif());
                ja.put(""+(agunan.getNamaPemilikAgunan()== null ? "-" : agunan.getNamaPemilikAgunan()));
                ja.put(""+(agunan.getAlamatAgunan()== null ? "-" : agunan.getAlamatAgunan()));
		ja.put("<button name='view' type='button' data-oid='"+agunan.getDebiturOid()+"' data-type='"+agunan.getDebiturType()+"' data-cif='"+agunan.getCif()+"' class='btn btn-warning edit'><i class='fa fa-pencil'></i> Edit</button>");
		
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
    }*/
    public String showForm(HttpServletRequest request) {

        String returnData = "";

        return returnData;
    }

    public String drawReportSummary(int iCommand, Vector objectClass, long periodeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");

        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("Cabang", "200px");//2
        for (int i = 0; i < segmenNames.length; i++) {
            ctrlist.addHeader(segmenNames[i], "200px");//2
        }

        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count = 0;
        Vector rowx = new Vector(1, 1);
        double total = 0.0;

        for (int i = 0; i < objectClass.size(); i++) {
            CabangBank cabangBank = (CabangBank) objectClass.get(i);
            count = count + 1;
            rowx = new Vector(1, 1);

            rowx.add("" + count);//1
            rowx.add("" + cabangBank.getNamaCabang());//2
            for (int iS = 0; iS < segmenNames.length; iS++) {
                int summary = getSummaryReport(iS, cabangBank.getKodeCabang(), periodeId);
                rowx.add("" + summary);
            }
            lstData.add(rowx);
        }

        return ctrlist.drawBootstrapStrip();
    }

    public String drawReportPerSegment(int iCommand, Vector objectClass, int segment) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");

        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("Cabang", "200px");//2
        ctrlist = draControlList(ctrlist, segment);

        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count = 0;
        Vector rowx = new Vector(1, 1);
        double total = 0.0;

        lstData = drawRowx(objectClass, segment, lstData);

        return ctrlist.drawBootstrapStrip();
    }

    public static ControlList draControlList(ControlList controlList, int segment) {
        switch (segment) {
            case SEGMEN_AGUNAN:
                controlList.addHeader("Kode Register Agunan");//2
                controlList.addHeader("No. Rekening");//3
                controlList.addHeader("CIF");//4
                controlList.addHeader("Kode Status Agunan");//5
                controlList.addHeader("Kode Jenis Agunan");//6
                controlList.addHeader("Peringkat Agunan");//7
                controlList.addHeader("Kode Lembaga Pemeringkat");//8
                controlList.addHeader("Kode Jenis Pengikatan");//9
                controlList.addHeader("Tgl. Pengikatan");//10
                controlList.addHeader("Nama Pemilik Agunan");//11
                controlList.addHeader("Bukti Kepemilikan");//12
                controlList.addHeader("Alamat Agunan");//13
                controlList.addHeader("Kode Kab. Lokasi Agunan");//14
                controlList.addHeader("Nilai Agunan NJOP");//15
                controlList.addHeader("Nilai Agunan LJK");//16
                controlList.addHeader("Tgl Penilaian LJK");//17
                controlList.addHeader("Nilai Agunan (Penilai Independen)");//18
                controlList.addHeader("Nama Penilai Independen");//19
                controlList.addHeader("Penilaian Penilai Independen");//20
                controlList.addHeader("Status Paripasu");//21
                controlList.addHeader("Prosentasi Paripasu");//22
                controlList.addHeader("Status Kredit Join");//23
                controlList.addHeader("Diasuransikan");//24
                controlList.addHeader("Keterangan");//25
                break;

            case SEGMEN_BANK_GARANSI:
                controlList.addHeader("No Rekening");//2
                controlList.addHeader("Cif");//3
                controlList.addHeader("Kode Jenis Garansi");//4
                controlList.addHeader("Kode Tujuan Garansi");//5
                controlList.addHeader("Tgl Diterbitkan");//6
                controlList.addHeader("Tgl Jatuh Tempo");//7
                controlList.addHeader("No Akad Awal");//8
                controlList.addHeader("Tgl Akad Awal");//9
                controlList.addHeader("No Akad Akhir");//10
                controlList.addHeader("Tgl Akad Akhir");//11
                controlList.addHeader("Nama Yang Dijamin");//12
                controlList.addHeader("Kode Valuta");//13
                controlList.addHeader("Plafon");//14
                controlList.addHeader("Nominal");//15
                controlList.addHeader("Setoran Jaminan");//16
                controlList.addHeader("Kode Kolektibilitas");//17
                controlList.addHeader("Tgl Wan Prestasi ");//18
                controlList.addHeader("Kode Kondisi");//19
                controlList.addHeader("Tgl Kondisi");//20
                controlList.addHeader("Keterangan");//21
                break;

            case SEGMEN_DEBITUR_BADAN_USAHA:
                controlList.addHeader("CIF");//2
                controlList.addHeader("No Rekening");//2
                controlList.addHeader("Tgl Akad Akhir");//2
                controlList.addHeader("No Identitas");//3
                controlList.addHeader("Nama");//4
                controlList.addHeader("Kode Bentuk Badan Usaha");//5
                controlList.addHeader("Tempat");//6
                controlList.addHeader("No Akte");//7
                controlList.addHeader("Tgl Akte Pendirian");//8
                controlList.addHeader("No Akte Perubahan");//9
                controlList.addHeader("Tgl Akte Perubahan");//10
                controlList.addHeader("Telepon");//11
                controlList.addHeader("Email");//12
                controlList.addHeader("Alamat");//13
                controlList.addHeader("Kelurahan");//14
                controlList.addHeader("Kecamatan");//15
                controlList.addHeader("Kode Kab");//16
                controlList.addHeader("Kode Pos");//17
                controlList.addHeader("Kode Negara");//18
                controlList.addHeader("Kode Bidang Usaha");//19
                controlList.addHeader("Kode Hub LJK");//20
                controlList.addHeader("Melanggar BMPK");//21
                controlList.addHeader("Melampaui BMPK");//22
                controlList.addHeader("Go Public");//23
                controlList.addHeader("Kode Golongan");//24
                controlList.addHeader("Peringkat");//25
                controlList.addHeader("Lembaga Pemeringkat");//26
                controlList.addHeader("Tgl Pemeringkat");//27
                controlList.addHeader("Nama Group");//28
                break;

            case SEGMEN_FASILITAS_LAINNYA:
                controlList.addHeader("No Rekening");//2
                controlList.addHeader("CIF");//3
                controlList.addHeader("Kode Jenis Fasilitas");//4
                controlList.addHeader("Sumber Dana");//5
                controlList.addHeader("Tgl Mulai");//6
                controlList.addHeader("Tgl Jatuh Tempo");//7
                controlList.addHeader("Suku Bunga");//8
                controlList.addHeader("Kode Valuta");//9
                controlList.addHeader("Nominal Jumlah Kewajiban");//10
                controlList.addHeader("Nilai Mata Uang Asal");//11
                controlList.addHeader("Kode Kolektibilitas");//12
                controlList.addHeader("Tgl Macet");//13
                controlList.addHeader("Kode Sebab Macet");//14
                controlList.addHeader("Tunggakan");//15
                controlList.addHeader("Jumlah Hari Tunggakan");//16
                controlList.addHeader("Kode Kondisi");//17
                controlList.addHeader("Tgl Kondisi");//18
                controlList.addHeader("Keterangan");//19
                break;

            case SEGMEN_IRREVOCABLE_LC:
                controlList.addHeader("No Rekening");//2
                controlList.addHeader("CIF");//3
                controlList.addHeader("Kode Jenis");//4
                controlList.addHeader("Kode Tujuan");//5
                controlList.addHeader("Tgl Keluar");//6
                controlList.addHeader("Tgl Jatuh Tempo");//7
                controlList.addHeader("No Akad Awal");//8
                controlList.addHeader("Tgl Akad Awal");//9
                controlList.addHeader("No Akad Akhir");//10
                controlList.addHeader("Tgl Akad Akhir");//11
                controlList.addHeader("Bank Counter Party");//12
                controlList.addHeader("Kode Valuta");//13
                controlList.addHeader("Plafon");//14
                controlList.addHeader("Nominal");//15
                controlList.addHeader("Setoran Jaminan");//16
                controlList.addHeader("Kode Kolektibilitas");//17
                controlList.addHeader("Tgl Wan Prestasi");//18
                controlList.addHeader("Kode Kondisi");//19
                controlList.addHeader("Tgl Kondisi");//20
                controlList.addHeader("Keterangan");//21
                break;

            case SEGMEN_KREDIT:
                controlList.addHeader("No Rekening");//2
                controlList.addHeader("CIF");//3
                controlList.addHeader("Kode Sifat");//4
                controlList.addHeader("Kode Jenis Kredit");//5
                controlList.addHeader("Kode Akad");//6
                controlList.addHeader("No Akad Awal");//7
                controlList.addHeader("Tgl Akad Awal");//8
                controlList.addHeader("No Akad Akhir");//9
                controlList.addHeader("Tgl Akad Akhir");//10
                controlList.addHeader("Baru Perpanjangan");//11
                controlList.addHeader("Tgl Awal Kredit");//12
                controlList.addHeader("Tgl Mulai");//13
                controlList.addHeader("Tgl Jatuh Tempo");//14
                controlList.addHeader("Kode Kategori Debitur");//15
                controlList.addHeader("Kode Jenis Penggunaan");//16
                controlList.addHeader("Kode Orientasi Penggunaan");//17
                controlList.addHeader("Kode Sektor Ekonomi");//18
                controlList.addHeader("Kode Kab/Kota");//19
                controlList.addHeader("Nilai");//20
                controlList.addHeader("Kode Valuta");//21
                controlList.addHeader("Prosentase Bunga");//22
                controlList.addHeader("Jenis Bunga");//23
                controlList.addHeader("Kredit Pemerintah");//24
                controlList.addHeader("Takeover");//25
                controlList.addHeader("Sumber Dana");//26
                controlList.addHeader("Plafon Awal");//27
                controlList.addHeader("Plafon");//28
                controlList.addHeader("Realisasi");//29
                controlList.addHeader("Denda");//30
                controlList.addHeader("Baki Debet");//31
                controlList.addHeader("Nilai Ulang Asal");//32
                controlList.addHeader("Kode Kolektibilitas");//33
                controlList.addHeader("Tgl Macet");//34
                controlList.addHeader("Kode Sebab macet");//35
                controlList.addHeader("Tunggakan Pokok");//36
                controlList.addHeader("Tunggakan Bunga");//37
                controlList.addHeader("Jumlah Hari Tunggakan");//38
                controlList.addHeader("Frekuensi Tunggakan");//39
                controlList.addHeader("Frekuensi Restrukturisasi");//40
                controlList.addHeader("Tgl Restrukturisasi Awal");//41
                controlList.addHeader("Tgl Restrukturisasi Akhir");//42
                controlList.addHeader("Kode Cara Restrukturisasi");//43
                controlList.addHeader("Kode Kondisi");//44
                controlList.addHeader("Tgl Kondisi");//45
                controlList.addHeader("Keterangan");//46
                break;

            case SEGMEN_KREDIT_JOIN:
                controlList.addHeader("No Rekening");//2
                controlList.addHeader("CIF");//3
                controlList.addHeader("Squence Debitur Anggota Join Accoun");//4
                controlList.addHeader("Kode Sifat");//5
                controlList.addHeader("Kode Jenis");//6
                controlList.addHeader("Kode Akad");//7
                controlList.addHeader("No Akad Awal");//8
                controlList.addHeader("Tgl Akad Awal");//9
                controlList.addHeader("No Akad Akhir");//10
                controlList.addHeader("Tgl Akad Akhir");//11
                controlList.addHeader("Baru Perpanjangan");//12
                controlList.addHeader("Tgl Awal Kredit");//13
                controlList.addHeader("Tgl Mulai");//14
                controlList.addHeader("Tgl Jatuh Tempo");//15
                controlList.addHeader("Kode Kategori Debitur");//16
                controlList.addHeader("Kode Jenis Penggunaan");//17
                controlList.addHeader("Kode Orientasi Penggunaan");//18
                controlList.addHeader("Kode Sektor Ekonomi");//19
                controlList.addHeader("Kode Kab");//20
                controlList.addHeader("Nilai Proyek");//21
                controlList.addHeader("Kode Valuta");//22
                controlList.addHeader("Prosentase Bunga");//23
                controlList.addHeader("Jenis Bunga");//24
                controlList.addHeader("Kredit Program Pemerintah");//25
                controlList.addHeader("Takeover");//26
                controlList.addHeader("Sumber Dana");//27
                controlList.addHeader("Palfon Awal");//28
                controlList.addHeader("Plafon");//29
                controlList.addHeader("Realisasi");//30
                controlList.addHeader("Denda");//31
                controlList.addHeader("Baki Debet");//32
                controlList.addHeader("Nilai Uang Asal");//33
                controlList.addHeader("Kode Kolektibilitas");//34
                controlList.addHeader("Tgl Macet");//35
                controlList.addHeader("Kode Sebab Macet");//36
                controlList.addHeader("Tunggakan Pokok");//37
                controlList.addHeader("Tunggakan Bunga");//38
                controlList.addHeader("Jumlah Hari Tunggakan");//39
                controlList.addHeader("Frekuensi Tunggakan");//40
                controlList.addHeader("Frekuensi Restrukturisasi");//41
                controlList.addHeader("Tgl Restrukturisasi Awal");//42
                controlList.addHeader("Tgl Restrukturisasi Akhir");//43
                controlList.addHeader("Kode Cara");//44
                controlList.addHeader("Kode Kondisi");//45
                controlList.addHeader("Tgl Kondisi");//46
                controlList.addHeader("Keterangan");//47
                break;

            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR:
                controlList.addHeader("CIF");//2
                controlList.addHeader("Aset");//3
                controlList.addHeader("Aset Lancar");//4
                controlList.addHeader("Kas dan Setara Kas");//5
                controlList.addHeader("Piutang Usaha (Aset Lancar)");//6
                controlList.addHeader("Investasi (Aset Lancar)");//7
                controlList.addHeader("Aset Lancar Lainnya");//8
                controlList.addHeader("Aset Tidak Lancar");//9
                controlList.addHeader("Piutang Usaha (Aset Tidak Lancar)");//10
                controlList.addHeader("Investasi (Aset Tidak Lancar)");//11
                controlList.addHeader("Aset Tidak Lancar Lainnya");//12
                controlList.addHeader("Liabilitas");//13
                controlList.addHeader("Liabilitas Jangka Pendek");//14
                controlList.addHeader("Pinjaman Jangka Pendek");//15
                controlList.addHeader("Utang Usaha (Liabilitas Jangka Pendek)");//16
                controlList.addHeader("Liabilitas Jangka Pendek Lainnya");//17
                controlList.addHeader("Liabilitas Jangka Panjang");//18
                controlList.addHeader("Pinjaman Jangka Panjang");//19
                controlList.addHeader("Utang Usaha (Liabilitas Jangka Panjang)");//20
                controlList.addHeader("Liabilitas Jangka Panjang Lainnya");//21
                controlList.addHeader("Ekuitas");//22
                controlList.addHeader("Pendapatan Usaha/Operasional");//23
                controlList.addHeader("Beban Pokok Pendapatan");//24
                controlList.addHeader("Laba/Rugi Bruto");//25
                controlList.addHeader("Pedapatan Lain-lain (Non-Operasional)");//26
                controlList.addHeader("Beban Lain-lain (Non-Operasional)");//27
                controlList.addHeader("Laba/Rugi Sebelum Pajak");//28
                controlList.addHeader("Laba/Rugi Periode");//29
                // controlList.addHeader("Kode Kantor Cabang");//30
                //controlList.addHeader("Operasi Data"); //31
                break;

            case SEGMEN_PENGURUS_PEMILIK:
                controlList.addHeader("No Identitas");//2
                controlList.addHeader("CIF");//3
                controlList.addHeader("Jenis Identitas");//4
                controlList.addHeader("Nama Pengurus");//5
                controlList.addHeader("Jenis Kelamin");//6
                controlList.addHeader("Alamat");//7
                controlList.addHeader("Kelurahan");//8
                controlList.addHeader("Kecamatan");//9
                controlList.addHeader("Kode Kabupaten");//10
                controlList.addHeader("Kode Jabatan");//11
                controlList.addHeader("Pangsa Kepemilikan");//12
                controlList.addHeader("Status Pengurus");//13
                //controlList.addHeader("Kode Kantor Cabang");//14
                //controlList.addHeader("Operasi Data");
                break;

            case SEGMEN_PENJAMIN:
                controlList.addHeader("No Id Penjamin");//2
                controlList.addHeader("No Rekening");//3
                controlList.addHeader("CIF");//4
                controlList.addHeader("Jenis Identitas");//5
                controlList.addHeader("Nama Identitas");//6
                controlList.addHeader("Nama Lengkap");//7
                controlList.addHeader("Kode Golongan Penjamin");//8
                controlList.addHeader("Alamat Penjamin");//9
                controlList.addHeader("Prosentasi Dijamin");//10
                controlList.addHeader("Keterangan");//11
                //controlList.addHeader("Kode Kantor Cabang");//12
                //controlList.addHeader("Operasid Data");
                break;

            case SEGMEN_SURAT_BERHARGA:
                controlList.addHeader("No Rekening");//2
                controlList.addHeader("CIF");//3
                controlList.addHeader("Kode Jenis");//4
                controlList.addHeader("Sovereign Rate");//5
                controlList.addHeader("Listing");//6
                controlList.addHeader("Peringkat");//7
                controlList.addHeader("Kode Tujuan");//8
                controlList.addHeader("Tgl Terbit");//9
                controlList.addHeader("Tgl Beli");//10
                controlList.addHeader("Tgl Jatuh Tempo");//11
                controlList.addHeader("Kode Valuta");//12
                controlList.addHeader("Nominal");//13
                controlList.addHeader("Nilai Uang Asal");//14
                controlList.addHeader("Nilai Pasar");//15
                controlList.addHeader("Nilai Perolehan");//16
                controlList.addHeader("Suku Bunga");//17
                controlList.addHeader("Tunggakan");//18
                controlList.addHeader("Jumlah Hari Tunggakan");//19
                controlList.addHeader("Kode Kolektibilitas");//20
                controlList.addHeader("Tgl Macet");//21
                controlList.addHeader("Kode Sebab Macet");//22
                controlList.addHeader("Kode Kondisi");//23
                controlList.addHeader("Tgl Kondisi");//24
                controlList.addHeader("Keterangan");//25
                //controlList.addHeader("Kode Kantor Cabang");//26
                //controlList.addHeader("Operasi Data");//2
                break;
            default:
                controlList.addHeader("CIF");//2
                controlList.addHeader("NO REKENING");//2
                controlList.addHeader("Tgl Akad Akhir");//2
                controlList.addHeader("Jenis Identitas");//3
                controlList.addHeader("NIK");//4
                controlList.addHeader("Nama Identitas");//5
                controlList.addHeader("Nama Lengkap");//6
                controlList.addHeader("Kode Status Gelar");//7
                controlList.addHeader("Jenis Kelamin");//8
                controlList.addHeader("Tempat Lahir");//9
                controlList.addHeader("Tgl Lahir");//10
                controlList.addHeader("NPWP");//11
                controlList.addHeader("Alamat");//12
                controlList.addHeader("Kelurahan");//13
                controlList.addHeader("Kecamatan");//14
                controlList.addHeader("Kode Kab");//15
                controlList.addHeader("Kode Pos");//16
                controlList.addHeader("Telepon");//17
                controlList.addHeader("Nomor HP");//18
                controlList.addHeader("Email");//19
                controlList.addHeader("Kode Domisili");//20
                controlList.addHeader("Kode Pekerjaan");//21
                controlList.addHeader("Tempat Bekerja");//22
                controlList.addHeader("Kode Usaha Tempat Bekerja");//23
                controlList.addHeader("Alamat Tempat Bekerja");//24
                controlList.addHeader("Penghasilan Kotor");//25
                controlList.addHeader("Kode Penghasilan");//26
                controlList.addHeader("Jumlah Tanggungan");//27
                controlList.addHeader("Kode Hub");//28
                controlList.addHeader("Kode Golongan");//29
                controlList.addHeader("Status");//30
                controlList.addHeader("NIK Pasangan");//31
                controlList.addHeader("Nama Pasangan");//32
                controlList.addHeader("Tanggal Lahir Pasangan");//33
                controlList.addHeader("Perjanjian Pisah Harga");//34
                controlList.addHeader("Melanggar BMPK");//35
                controlList.addHeader("Melampaui BMPK");//36
                controlList.addHeader("Nama Ibu Kandung");//37
                break;
        }
        return controlList;
    }
    
    public static Vector drawRowx(Vector listObj, int segment, Vector listData) {
        return drawRowx( listObj,  segment,  listData,0);
    }
    
    public static Vector drawRowx(Vector listObj, int segment, Vector listData, int type) {
        switch (segment) {
            case SEGMEN_AGUNAN:
                int noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    Agunan agunan = (Agunan) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//0
                    rowx.add("" + agunan.getKodeKantorCabang());//1
                    rowx.add("" + agunan.getKodeRegisterAgunan());//2
                    rowx.add("" + agunan.getNoRekening());//3
                    rowx.add("" + agunan.getCif());//4
                    rowx.add("" + agunan.getKodeStatusAgunan());//5
                    rowx.add("" + agunan.getKodeJenisAgunan());//6
                    rowx.add("" + agunan.getPeringkatAgunan());//7
                    rowx.add("" + agunan.getKodeLembagaPemeringkat());//8
                    rowx.add("" + agunan.getKodeJenisPengikatan());//9
                    String tglPengikatan = "";
                    if (agunan.getTglPengikatan() != null) {
                        try {
                            tglPengikatan = Formater.formatDate(agunan.getTglPengikatan(), "yyyy-MM-dd");
                        } catch (Exception ex) {
                            tglPengikatan = "";
                        }
                    }
                    rowx.add("" + tglPengikatan);//10
                    rowx.add("" + agunan.getNamaPemilikAgunan());//11
                    rowx.add("" + agunan.getBuktiKepemilikan());//12
                    rowx.add("" + agunan.getAlamatAgunan());//13
                    rowx.add("" + agunan.getKodeKabLokasiAgunan());//14
                    rowx.add("" + FRMHandler.userFormatStringDecimal(agunan.getNilaiAgunanNjop()));//15
                    rowx.add("" + FRMHandler.userFormatStringDecimal(agunan.getNilaiAgunanLjk()));//16
                    String tglPenilaian = "";
                    try {
                        tglPenilaian = Formater.formatDate(agunan.getTglPenilaianLjk(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglPenilaian = "";
                    }
                    rowx.add("" + tglPenilaian);//17
                    rowx.add("" + agunan.getNilaiAguPenilaiIndep());//18
                    rowx.add("" + agunan.getNamaPenilaiIndep());//19
                    String tglPenilaianPenilaiIndep = "";
                    try {
                        tglPenilaianPenilaiIndep = Formater.formatDate(agunan.getTglPenilaianPenilaiIndep(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglPenilaianPenilaiIndep = "";
                    }
                    rowx.add("" + tglPenilaianPenilaiIndep);//20
                    rowx.add("" + agunan.getStatusParipasu());//21
                    rowx.add("" + agunan.getProsentaseParipasu());//22
                    rowx.add("" + agunan.getStatusKreditJoin());//23
                    rowx.add("" + agunan.getDiasuransikan());//24
                    rowx.add("" + agunan.getKeterangan());//25
                    listData.add(rowx);

                    long oid = agunan.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='22' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }
                }

                break;

            case SEGMEN_BANK_GARANSI:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    noUrut = noUrut + 1;
                    BankGaransi bankGaransi = (BankGaransi) listObj.get(i);
                    rowx.add("" + noUrut);//2
                    rowx.add("" + bankGaransi.getKodeKantorCabang());//2
                    rowx.add("" + bankGaransi.getNoRekening());//2
                    rowx.add("" + bankGaransi.getCif());//3
                    rowx.add("" + bankGaransi.getKodeJenisGaransi());//4
                    rowx.add("" + bankGaransi.getKodeTujuanGaransi());//5

                    try {
                        rowx.add("" + bankGaransi.getTglDiterbitkan());//6
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    try {
                        rowx.add("" + bankGaransi.getTglJatuhTempo());//7
                    } catch (Exception es) {
                        rowx.add("");
                    }

                    rowx.add("" + bankGaransi.getNoAkadAwal());//8

                    try {
                        rowx.add("" + bankGaransi.getTglAkadAwal());//9
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + bankGaransi.getNoAkadAkhir());//10

                    try {
                        rowx.add("" + bankGaransi.getTglAkadAkhir());//11
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + bankGaransi.getNamaYgDijamin());//12
                    rowx.add("" + bankGaransi.getKodeValuta());//13
                    rowx.add("" + FRMHandler.userFormatStringDecimal(bankGaransi.getPlafon()));//14
                    rowx.add("" + FRMHandler.userFormatStringDecimal(bankGaransi.getNominal()));//15
                    rowx.add("" + bankGaransi.getSetoranJaminan());//16
                    rowx.add("" + bankGaransi.getKodeKolektibilitas());//17
                    try {
                        rowx.add("" + bankGaransi.getTglWanPrestasi());//18
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + bankGaransi.getKodeKondisi());//19
                    try {
                        rowx.add("" + bankGaransi.getTglKondisi());//20
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + bankGaransi.getKeterangan());//21
//                    rowx.add("" + bankGaransi.getKodeKantorCabang());//22
//                    rowx.add("" + bankGaransi.getOperasiData() + "");//23
                    listData.add(rowx);

                    long oid = bankGaransi.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='18' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }

                }
                break;

            case SEGMEN_DEBITUR_BADAN_USAHA:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    DebiturBdnUsaha debiturBdnUsaha = (DebiturBdnUsaha) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + debiturBdnUsaha.getKodeKtrCabang());//2
                    rowx.add("" + debiturBdnUsaha.getCif());//2
                    rowx.add("" + debiturBdnUsaha.getNoRekening());//2
                    rowx.add("" + Formater.formatDate(debiturBdnUsaha.getTglAkadAkhir(), "yyyy-MM-dd"));//2
                    rowx.add("" + debiturBdnUsaha.getNoIdentitas());//3
                    rowx.add("" + debiturBdnUsaha.getNama());//4
                    rowx.add("" + debiturBdnUsaha.getKodeJenis());//5
                    rowx.add("" + debiturBdnUsaha.getTempat());//6
                    rowx.add("" + debiturBdnUsaha.getNoAkte());//7

                    try {
                        rowx.add("" + debiturBdnUsaha.getTglAktePendirian());//8
                    } catch (Exception es) {
                        rowx.add("");
                    }

                    rowx.add("" + debiturBdnUsaha.getNoAktePerubahan());//9
                    try {
                        rowx.add("" + debiturBdnUsaha.getTglAktePerubahan());//10
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + debiturBdnUsaha.getTelepon());//11
                    rowx.add("" + debiturBdnUsaha.getEmail());//12
                    rowx.add("" + debiturBdnUsaha.getAlamat());//13
                    rowx.add("" + debiturBdnUsaha.getKelurahan());//14
                    rowx.add("" + debiturBdnUsaha.getKecamatan());//15
                    rowx.add("" + debiturBdnUsaha.getKodeKab());//16
                    rowx.add("" + debiturBdnUsaha.getKodePos());//17
                    rowx.add("" + debiturBdnUsaha.getKodeNegara());//18
                    rowx.add("" + debiturBdnUsaha.getKodeBidangUsaha());//19
                    rowx.add("" + debiturBdnUsaha.getKodeHubLjk());//20
                    rowx.add("" + debiturBdnUsaha.getMelanggarBmpk());//21
                    rowx.add("" + debiturBdnUsaha.getMelampauiBmpk());//22
                    rowx.add("" + debiturBdnUsaha.getGoPublic());//23
                    rowx.add("" + debiturBdnUsaha.getKodeGol());//24
                    rowx.add("" + debiturBdnUsaha.getPeringkat());//25
                    rowx.add("" + debiturBdnUsaha.getLembagaPemeringkat());//26

                    try {
                        rowx.add("" + debiturBdnUsaha.getTglPemeringkat());//27
                    } catch (Exception es) {
                    }

                    rowx.add("" + debiturBdnUsaha.getNamaGroup());//28
//                    rowx.add("" + debiturBdnUsaha.getKodeKtrCabang());//29
//                    rowx.add("" + debiturBdnUsaha.getOperasiData());//30
                    listData.add(rowx);

                    long oid = debiturBdnUsaha.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='25' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }

                }
                break;

            case SEGMEN_FASILITAS_LAINNYA:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    FasilitasLain fasilitasLain = (FasilitasLain) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + fasilitasLain.getKodeKantorCabang());//2
                    rowx.add("" + fasilitasLain.getNoRekening());//2
                    rowx.add("" + fasilitasLain.getCif());//3
                    rowx.add("" + fasilitasLain.getKodeJenisFasilitas());//4
                    rowx.add("" + fasilitasLain.getSumberDana());//5
                    String tglMulai = "";
                    try {
                        tglMulai = Formater.formatDate(fasilitasLain.getTglMulai(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglMulai = "";
                    }
                    rowx.add("" + tglMulai);//6
                    String tglJatuhTempo = "";
                    try {
                        tglJatuhTempo = Formater.formatDate(fasilitasLain.getTglJatuhTempo(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglJatuhTempo = "";
                    }
                    rowx.add("" + tglJatuhTempo);//7
                    rowx.add("" + fasilitasLain.getSukuBunga());//8
                    rowx.add("" + fasilitasLain.getKodeValuta());//9
                    rowx.add("" + fasilitasLain.getNominalJmlKewajiban());//10
                    rowx.add("" + fasilitasLain.getNilaiMataUangAsal());//11
                    rowx.add("" + fasilitasLain.getKodeKolektibilitas());//12
                    String tglMacet = "";
                    try {
                        tglMacet = Formater.formatDate(fasilitasLain.getTglMacet(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglMacet = "";
                    }
                    rowx.add("" + tglMacet);//13
                    rowx.add("" + fasilitasLain.getKodeSebabMacet());//14
                    rowx.add("" + FRMHandler.userFormatStringDecimal(fasilitasLain.getTunggakan()));//15
                    rowx.add("" + fasilitasLain.getJmlHariTunggakan());//16
                    rowx.add("" + fasilitasLain.getKodeKondisi());//17
                    String tglKondisi = "";
                    try {
                        tglKondisi = Formater.formatDate(fasilitasLain.getTglKondisi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKondisi = "";
                    }
                    rowx.add("" + tglKondisi);//18
                    rowx.add("" + fasilitasLain.getKeterangan());//19
//                    rowx.add("" + fasilitasLain.getKodeKantorCabang());//20
//                    rowx.add("" + fasilitasLain.getOperasiData() );//21
                    listData.add(rowx);

                    long oid = fasilitasLain.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='16' >" + logSysHistory.getLogDetail() + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }

                }
                break;

            case SEGMEN_IRREVOCABLE_LC:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    IrrevocableLc irrevocableLc = (IrrevocableLc) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + irrevocableLc.getKodeKantorCabang());//2
                    rowx.add("" + irrevocableLc.getNoRekening());//2
                    rowx.add("" + irrevocableLc.getCif());//3
                    rowx.add("" + irrevocableLc.getKodeJenis());//4
                    rowx.add("" + irrevocableLc.getKodeTujuan());//5
                    String tglKeluar = "";
                    try {
                        tglKeluar = Formater.formatDate(irrevocableLc.getTglKeluar(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKeluar = "";
                    }
                    rowx.add("" + tglKeluar);//6
                    String tglJatuhTempo = "";
                    try {
                        tglJatuhTempo = Formater.formatDate(irrevocableLc.getTglJatuhTempo(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglJatuhTempo = "";
                    }
                    rowx.add("" + tglJatuhTempo);//7
                    rowx.add("" + irrevocableLc.getNoAkadAwal());//8
                    String tglAkadAwal = "";
                    try {
                        tglAkadAwal = Formater.formatDate(irrevocableLc.getTglAkadAwal(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAkadAwal = "";
                    }
                    rowx.add("" + tglAkadAwal);//9
                    rowx.add("" + irrevocableLc.getNoAkadAkhr());//10
                    String tglAkadAkhir = "";
                    try {
                        tglAkadAkhir = Formater.formatDate(irrevocableLc.getTglAkadAkhir(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAkadAkhir = "";
                    }
                    rowx.add("" + tglAkadAkhir);//11
                    rowx.add("" + irrevocableLc.getBankCounterparty());//12
                    rowx.add("" + irrevocableLc.getKodeValuta());//13
                    rowx.add("" + irrevocableLc.getPlafon());//14
                    rowx.add("" + irrevocableLc.getNominal());//15
                    rowx.add("" + irrevocableLc.getSetoranJaminan());//16
                    rowx.add("" + irrevocableLc.getKodeKolektibilitas());//17
                    String tglWanPrestasi = "";
                    try {
                        tglWanPrestasi = Formater.formatDate(irrevocableLc.getTglWanPrestasi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglWanPrestasi = "";
                    }
                    rowx.add("" + tglWanPrestasi);//18
                    rowx.add("" + irrevocableLc.getKodeKondisi());//19
                    String tglKondisi = "";
                    try {
                        tglKondisi = Formater.formatDate(irrevocableLc.getTglKondisi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKondisi = "";
                    }
                    rowx.add("" + tglKondisi);//20
                    rowx.add("" + irrevocableLc.getKeterangan());//21
//                    rowx.add("" + irrevocableLc.getKodeKantorCabang());//22
//                    rowx.add("" + irrevocableLc.getOperasiData()+ "");
                    listData.add(rowx);

                    long oid = irrevocableLc.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='18' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }

                }
                break;

            case SEGMEN_KREDIT:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    Kredit kredit = (Kredit) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//0
                    rowx.add("" + kredit.getKodeKantorCabang());//1
                    rowx.add("" + kredit.getNoRekening());//2
                    rowx.add("" + kredit.getCif());//3
                    rowx.add("" + kredit.getKodeSifat());//4
                    rowx.add("" + kredit.getKodeJenisKredit());//5
                    rowx.add("" + kredit.getKodeAkad());//6
                    rowx.add("" + kredit.getNoAkadAwal());//7
                    String tglAkadAwal = "";
                    try {
                        tglAkadAwal = Formater.formatDate(kredit.getTglAkadAwal(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAkadAwal = "";
                    }
                    rowx.add("" + tglAkadAwal);//8
                    rowx.add("" + kredit.getNoAkadAkhir());//9
                    String tglAkadAkhir = "";
                    try {
                        tglAkadAkhir = Formater.formatDate(kredit.getTglAkadAkhir(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAkadAkhir = "";
                    }
                    rowx.add("" + tglAkadAkhir);//10
                    rowx.add("" + kredit.getBaruPerpanjangan());//11
                    String tglAwal = "";
                    try {
                        tglAwal = Formater.formatDate(kredit.getTglAwal(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAwal = "";
                    }
                    rowx.add("" + tglAwal);//12
                    String tglMulai = "";
                    try {
                        tglMulai = Formater.formatDate(kredit.getTglMulai(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglMulai = "";
                    }
                    rowx.add("" + tglMulai);//13
                    String tglTempo = "";
                    try {
                        tglTempo = Formater.formatDate(kredit.getTglTempo(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglTempo = "";
                    }
                    rowx.add("" + tglTempo);//14
                    rowx.add("" + kredit.getKodeKatDbitur());//15
                    rowx.add("" + kredit.getKodeJenisPenggunaan());//16
                    rowx.add("" + kredit.getKodeOrientasiPenggunaan());//17
                    rowx.add("" + kredit.getKodeSektorEkonomi());//18
                    rowx.add("" + kredit.getKodeKab());//19
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getNilai()));//20
                    rowx.add("" + kredit.getKodeValuta());//21
                    rowx.add("" + kredit.getProsentaseBunga());//22
                    rowx.add("" + kredit.getJenisBunga());//23
                    rowx.add("" + kredit.getKreditPemerintah());//24
                    rowx.add("" + kredit.getTakeover());//25
                    rowx.add("" + kredit.getSumberDana());//26
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getPlafonAwal()));//27
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getPlafon()));//28
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getRealisasi()));//29
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getDenda()));//30
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getBakiDebet()));//31
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getNilaiUangAsal()));//32
                    rowx.add("" + kredit.getKodeKolektibilitas());//33
                    String tglMacet = "";
                    try {
                        tglMacet = Formater.formatDate(kredit.getTglMacet(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglMacet = "";
                    }
                    rowx.add("" + tglMacet);//34
                    rowx.add("" + kredit.getKodeSebabMacet());//35
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getTunggakanPokok()));//36
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getTunggakanBunga()));//37
                    rowx.add("" + kredit.getJmlHariTunggakan());//38
                    rowx.add("" + kredit.getFrekuensiTunggakan());//39
                    rowx.add("" + kredit.getFrekuensiRestrukturisasi());//40
                    String tglRestrukturisasiAwal = "";
                    try {
                        tglRestrukturisasiAwal = Formater.formatDate(kredit.getTglRestrukturisasiAwal(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglRestrukturisasiAwal = "";
                    }
                    rowx.add("" + tglRestrukturisasiAwal);//41
                    String tglres = "";
                    try {
                        tglres = Formater.formatDate(kredit.getTglRestrukturisasiAkhir(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglres = "";
                    }
                    rowx.add("" + tglres);//42
                    rowx.add("" + kredit.getKodeCara());//43
                    rowx.add("" + kredit.getKodeKondisi());//44
                    String tglKondisi = "";
                    try {
                        tglKondisi = Formater.formatDate(kredit.getTglKondisi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKondisi = "";
                    }
                    rowx.add("" + tglKondisi);//45
                    rowx.add("" + kredit.getKeterangan());//46
//                    rowx.add("" + kredit.getKodeKantorCabang());//47
//                    rowx.add("" + kredit.getOperasiData() );//48
                    listData.add(rowx);

                    long oid = kredit.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='43' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }

                }
                break;

            case SEGMEN_KREDIT_JOIN:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    KreditJoinAccount kreditJoinAccount = (KreditJoinAccount) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + kreditJoinAccount.getKodeKantorCabang());//2
                    rowx.add("" + kreditJoinAccount.getNoRekening());//2
                    rowx.add("" + kreditJoinAccount.getCif());//3
                    rowx.add("" + kreditJoinAccount.getSquenceDebJoin());//4
                    rowx.add("" + kreditJoinAccount.getKodeSifat());//5
                    rowx.add("" + kreditJoinAccount.getKodeJenis());//6
                    rowx.add("" + kreditJoinAccount.getKodeAkad());//7
                    rowx.add("" + kreditJoinAccount.getNoAkadAwal());//8

                    try {
                        rowx.add("" + kreditJoinAccount.getTglAkadAwal());//9
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getNoAkadAkhir());//10
                    try {
                        rowx.add("" + kreditJoinAccount.getTglAkadAkhir());//11
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getBaruPerpanjangan());//12

                    try {
                        rowx.add("" + kreditJoinAccount.getTglAwalKredit());//13
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    try {
                        rowx.add("" + kreditJoinAccount.getTglMulai());//14
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    try {
                        rowx.add("" + kreditJoinAccount.getTglJatuhTempo());//15
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getKodeKatDeb());//16
                    rowx.add("" + kreditJoinAccount.getKodeJenisPenggunaan());//17
                    rowx.add("" + kreditJoinAccount.getKodeOrientasiPenggunaan());//18
                    rowx.add("" + kreditJoinAccount.getKodeSektorEkonomi());//19
                    rowx.add("" + kreditJoinAccount.getKodeKab());//20
                    rowx.add("" + kreditJoinAccount.getNilaiProyek());//21
                    rowx.add("" + kreditJoinAccount.getKodeValuta());//22
                    rowx.add("" + kreditJoinAccount.getProsentaseBunga());//23
                    rowx.add("" + kreditJoinAccount.getJenisBunga());//24
                    rowx.add("" + kreditJoinAccount.getKreditPrgPemerintah());//25
                    rowx.add("" + kreditJoinAccount.getTakeover());//26
                    rowx.add("" + kreditJoinAccount.getSumberDana());//27
                    rowx.add("" + kreditJoinAccount.getPlafonAwal());//28
                    rowx.add("" + kreditJoinAccount.getPlafon());//29
                    rowx.add("" + kreditJoinAccount.getRealisasi());//30
                    rowx.add("" + kreditJoinAccount.getDenda());//31
                    rowx.add("" + kreditJoinAccount.getBakiDebet());//32
                    rowx.add("" + kreditJoinAccount.getNilaiUangAsal());//33
                    rowx.add("" + kreditJoinAccount.getKodeKolektibilitas());//34
                    try {
                        rowx.add("" + kreditJoinAccount.getTglMacet());//35
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getKodeSebabMacet());//36
                    rowx.add("" + kreditJoinAccount.getTunggakanPokok());//37
                    rowx.add("" + kreditJoinAccount.getTunggakanBunga());//38
                    rowx.add("" + kreditJoinAccount.getJmlHariTunggakan());//39
                    rowx.add("" + kreditJoinAccount.getFrekuensiTunggakan());//40
                    rowx.add("" + kreditJoinAccount.getFrekuensiRestrukturisasi());//41
                    try {
                        rowx.add("" + kreditJoinAccount.getTglRestrukturisasiAwal());//42
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    try {
                        rowx.add("" + kreditJoinAccount.getTglRestruktirisasiAkhir());//43
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getKodeCara());//44
                    rowx.add("" + kreditJoinAccount.getKodeKondisi());//45
                    try {
                        rowx.add("" + kreditJoinAccount.getTglKondisi());//46
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getKeterangan());//47
//                    rowx.add("" + kreditJoinAccount.getKodeKantorCabang());//48
//                    rowx.add("" + kreditJoinAccount.getOperasiData() + "");//49
                    listData.add(rowx);

                    long oid = kreditJoinAccount.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='44' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }
                }
                break;

            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    noUrut = noUrut + 1;
                    LaporanKeuanganDebitur laporanKeuanganDebitur = (LaporanKeuanganDebitur) listObj.get(i);
                    rowx.add("" + noUrut);//2
                    rowx.add("" + laporanKeuanganDebitur.getKodeKantorCabang());//2
                    rowx.add("" + laporanKeuanganDebitur.getCif());//2
                    rowx.add("" + laporanKeuanganDebitur.getAset());//3
                    rowx.add("" + laporanKeuanganDebitur.getAsetLancar());//4
                    rowx.add("" + laporanKeuanganDebitur.getKasDanSetaraKas());//5
                    rowx.add("" + laporanKeuanganDebitur.getPiutangUsahaAstLncr());//6
                    rowx.add("" + laporanKeuanganDebitur.getInvestasiAstLncr());//7
                    rowx.add("" + laporanKeuanganDebitur.getAsetLancarLain());//8
                    rowx.add("" + laporanKeuanganDebitur.getAsetTidakLancar());//9
                    rowx.add("" + laporanKeuanganDebitur.getPiutangUsahaAstTdkLncr());//10
                    rowx.add("" + laporanKeuanganDebitur.getInvestasiAstTdkLncr());//11
                    rowx.add("" + laporanKeuanganDebitur.getAsetTdkLncrLain());//12
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitas());//13
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitasJnkPndk());//14
                    rowx.add("" + laporanKeuanganDebitur.getPinjamanJnkPndk());//15
                    rowx.add("" + laporanKeuanganDebitur.getUtangUsahaLiaJnkPndk());//16
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitasJnkPndkLain());//17
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitasJnkPnjg());//18
                    rowx.add("" + laporanKeuanganDebitur.getPinjamanJnkPnjg());//19
                    rowx.add("" + laporanKeuanganDebitur.getUtangUsahaLiaJnkPnjg());//20
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitasJnkPnjgLain());//21
                    rowx.add("" + laporanKeuanganDebitur.getEkuitas());//22
                    rowx.add("" + laporanKeuanganDebitur.getPendapatanUsahaOpr());//23
                    rowx.add("" + laporanKeuanganDebitur.getBebanPokokPend());//24
                    rowx.add("" + laporanKeuanganDebitur.getLabaRugiBruto());//25
                    rowx.add("" + laporanKeuanganDebitur.getPendLainNonOpr());//26
                    rowx.add("" + laporanKeuanganDebitur.getBebanLainNonOpr());//27
                    rowx.add("" + laporanKeuanganDebitur.getLabaRugiSblmPajak());//28
                    rowx.add("" + laporanKeuanganDebitur.getLabaRugiPeriode());//29
//                    rowx.add("" + laporanKeuanganDebitur.getKodeKantorCabang() );//30
//                    rowx.add("" + laporanKeuanganDebitur.getOperasiData() ); //31
                    listData.add(rowx);

                    long oid = laporanKeuanganDebitur.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='26' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }

                }
                break;

            case SEGMEN_PENGURUS_PEMILIK:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    PengurusAtauPemilik pengurusAtauPemilik = (PengurusAtauPemilik) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + pengurusAtauPemilik.getKodeKantorCabang());//2
                    rowx.add("" + pengurusAtauPemilik.getNoIdentitas());//2
                    rowx.add("" + pengurusAtauPemilik.getCif());//3
                    rowx.add("" + pengurusAtauPemilik.getJenisIdentitas());//4
                    rowx.add("" + pengurusAtauPemilik.getNamaPengurus());//5
                    rowx.add("" + pengurusAtauPemilik.getJenisKelamin());//6
                    rowx.add("" + pengurusAtauPemilik.getAlamat());//7
                    rowx.add("" + pengurusAtauPemilik.getKelurahan());//8
                    rowx.add("" + pengurusAtauPemilik.getKecamatan());//9
                    rowx.add("" + pengurusAtauPemilik.getKodeKabupaten());//10
                    rowx.add("" + pengurusAtauPemilik.getKodeJabatan());//11
                    rowx.add("" + pengurusAtauPemilik.getPangsaKepemilikan());//12
                    rowx.add("" + pengurusAtauPemilik.getStatusPengurus());//13
//                    rowx.add("" + pengurusAtauPemilik.getKodeKantorCabang());//14
//                    rowx.add("" + pengurusAtauPemilik.getOperasiData());
                    listData.add(rowx);

                    long oid = pengurusAtauPemilik.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='10' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }

                }
                break;

            case SEGMEN_PENJAMIN:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    Penjamin penjamin = (Penjamin) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + penjamin.getKodeKantorCabang());//2
                    rowx.add("" + penjamin.getNoIdPenjamin());//2
                    rowx.add("" + penjamin.getNoRekening());//3
                    rowx.add("" + penjamin.getCif());//4
                    rowx.add("" + penjamin.getJenisIdentitas());//5
                    rowx.add("" + penjamin.getNamaIdentitas());//6
                    rowx.add("" + penjamin.getNamaLengkap());//7
                    rowx.add("" + penjamin.getKodeGolPenjamin());//8
                    rowx.add("" + penjamin.getAlamatPenjamin());//9
                    rowx.add("" + penjamin.getProsentaseDijamin());//10
                    rowx.add("" + penjamin.getKeterangan());//11
//                    rowx.add("" + penjamin.getKodeKantorCabang());//12
//                    rowx.add("" + penjamin.getOperasiData());
                    listData.add(rowx);

                    long oid = penjamin.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='8' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }
                }
                break;

            case SEGMEN_SURAT_BERHARGA:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    SuratBerharga suratBerharga = (SuratBerharga) listObj.get(i);

                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + suratBerharga.getKodeKantorCabang());//2
                    rowx.add("" + suratBerharga.getNoRekening());//2
                    rowx.add("" + suratBerharga.getCif());//3
                    rowx.add("" + suratBerharga.getKodeJenis());//4
                    rowx.add("" + suratBerharga.getSovereignRate());//5
                    rowx.add("" + suratBerharga.getListing());//6
                    rowx.add("" + suratBerharga.getPeringkat());//7
                    rowx.add("" + suratBerharga.getKodeTujuan());//8
                    String tglTerbit = "";
                    try {
                        tglTerbit = Formater.formatDate(suratBerharga.getTglTerbit(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglTerbit = "";
                    }
                    rowx.add("" + tglTerbit);//9
                    String tglBeli = "";
                    try {
                        tglBeli = Formater.formatDate(suratBerharga.getTglBeli(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglBeli = "";
                    }
                    rowx.add("" + tglBeli);//10
                    String tglJatuhTempo = "";
                    try {
                        tglJatuhTempo = Formater.formatDate(suratBerharga.getTglJatuhTempo(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglJatuhTempo = "";
                    }
                    rowx.add("" + tglJatuhTempo);//11
                    rowx.add("" + suratBerharga.getKodeValuta());//12
                    rowx.add("" + suratBerharga.getNominal());//13
                    rowx.add("" + suratBerharga.getNilaiUangAsal());//14
                    rowx.add("" + suratBerharga.getNilaiPasar());//15
                    rowx.add("" + suratBerharga.getNilaiPerolehan());//16
                    rowx.add("" + suratBerharga.getSukuBunga());//17
                    rowx.add("" + suratBerharga.getTunggakan());//18
                    rowx.add("" + suratBerharga.getJmlHariTunggakan());//19
                    rowx.add("" + suratBerharga.getKodeKolektibilitas());//20
                    String tglMacet = "";
                    try {
                        tglMacet = Formater.formatDate(suratBerharga.getTglMacet(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglMacet = "";
                    }
                    rowx.add("" + tglMacet);//21
                    rowx.add("" + suratBerharga.getKodeSebabMacet());//22
                    rowx.add("" + suratBerharga.getKodeKondisi());//23
                    String tglKondisi = "";
                    try {
                        tglKondisi = Formater.formatDate(suratBerharga.getTglKondisi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKondisi = "";
                    }
                    rowx.add("" + tglKondisi);//24
                    rowx.add("" + suratBerharga.getKeterangan());//25
                    listData.add(rowx);
                    long oid = suratBerharga.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan : ");//0
                                rowx.add("<td colspan ='22' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }
                }
                break;
            default:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    DebiturIndividu debiturIndividu = (DebiturIndividu) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//0
                    rowx.add("" + debiturIndividu.getKodeKantorCabang());//1
                    rowx.add("" + debiturIndividu.getCif());//2
                    rowx.add("" + debiturIndividu.getNoRekening());//2
                    rowx.add("" + Formater.formatDate(debiturIndividu.getTglAkadAkhir(), "yyyy-MM-dd"));//2
                    rowx.add("" + debiturIndividu.getJenisIdentitas());//3
                    rowx.add("" + debiturIndividu.getNik());//4
                    rowx.add("" + debiturIndividu.getNamaIdentitas());//5
                    rowx.add("" + debiturIndividu.getNamaLengkap());//6
                    rowx.add("" + debiturIndividu.getKodeStatusGelar());//7
                    rowx.add("" + debiturIndividu.getJekel());//8
                    rowx.add("" + debiturIndividu.getTempatLahir());//9
                    if (debiturIndividu.getTglLahir() != null) {
                        rowx.add("" + Formater.formatDate(debiturIndividu.getTglLahir(), "yyyy-MM-dd"));//10
                    } else {
                        rowx.add("");//10
                    }
                    rowx.add("" + debiturIndividu.getNpwp());//11
                    rowx.add("" + debiturIndividu.getAlamat());//12
                    rowx.add("" + debiturIndividu.getKelurahan());//13
                    rowx.add("" + debiturIndividu.getKecamatan());//14
                    rowx.add("" + debiturIndividu.getKodeKab());//15
                    rowx.add("" + debiturIndividu.getKodePos());//16
                    rowx.add("" + debiturIndividu.getTelepon());//17
                    rowx.add("" + debiturIndividu.getNomorHp());//18
                    rowx.add("" + debiturIndividu.getEmail());//19
                    rowx.add("" + debiturIndividu.getKodeDomisili());//20
                    rowx.add("" + debiturIndividu.getKodePekerjaan());//21
                    rowx.add("" + debiturIndividu.getTempatBekerja());//22
                    rowx.add("" + debiturIndividu.getKodeUsahaTempatBekerja());//23
                    rowx.add("" + debiturIndividu.getAlamatTempatBekerja());//24
                    rowx.add("" + debiturIndividu.getPenghasilanKotor());//25
                    rowx.add("" + debiturIndividu.getKodePenghasilan());//26
                    rowx.add("" + debiturIndividu.getJmlTanggungan());//27
                    rowx.add("" + debiturIndividu.getKodeHub());//28
                    rowx.add("" + debiturIndividu.getKodeGol());//29
                    rowx.add("" + debiturIndividu.getStatus());//30
                    rowx.add("" + debiturIndividu.getNikPasangan());//31
                    rowx.add("" + debiturIndividu.getNamaPasangan());//32
                    try {
                        rowx.add("" + Formater.formatDate(debiturIndividu.getTglLahirPasangan(), "yyyy-MM-dd"));//33
                    } catch (Exception es) {
                        rowx.add("");//33
                    }
                    rowx.add("" + debiturIndividu.getPerjanjianPisahHarga());//34
                    rowx.add("" + debiturIndividu.getMelanggarBmpk());//35
                    rowx.add("" + debiturIndividu.getMelampauiBmpk());//36
                    rowx.add("" + debiturIndividu.getNamaIbuKandung());//37
//                    rowx.add("" + debiturIndividu.getKodeKantorCabang());//38
//                    rowx.add("" + debiturIndividu.getOperasiData());//39
                    listData.add(rowx);

                    long oid = debiturIndividu.getOID();
                    if (oid != 0) {
                        Vector logError = PstLogSysHistory.listPurchaseOrder(0, 1, "" + PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID] + "='" + oid + "'", "");
                        if (logError.size() > 0) {
                            try {
                                LogSysHistory logSysHistory = (LogSysHistory) logError.get(0);
                                rowx = new Vector(1, 1);
                                rowx.add("");//0
                                rowx.add("Catatan");//0
                                rowx.add("<td colspan ='34' >" + logSysHistory.getLogDetail().replace("<br>", "<br />") + " </td>");//1
                                listData.add(rowx);
                            } catch (Exception es) {
                            }
                        }
                    }
                }
                break;
        }

        return listData;
    }

    public static Vector drawRowxEror(Vector listObj, int segment, Vector listData) {
        switch (segment) {
            case SEGMEN_AGUNAN:
                int noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    Agunan agunan = (Agunan) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//0
                    rowx.add("" + agunan.getKodeKantorCabang());//1
                    rowx.add("" + agunan.getKodeRegisterAgunan());//2
                    rowx.add("" + agunan.getNoRekening());//3
                    rowx.add("" + agunan.getCif());//4
                    if (agunan.getKodeStatusAgunan().equals("")) {
                        rowx.add("WAJIB ISI");//5
                    } else {
                        rowx.add("" + agunan.getKodeStatusAgunan());//5
                    }
                    rowx.add("" + agunan.getKodeJenisAgunan());//6
                    rowx.add("" + agunan.getPeringkatAgunan());//7
                    rowx.add("" + agunan.getKodeLembagaPemeringkat());//8
                    rowx.add("" + agunan.getKodeJenisPengikatan());//9
                    String tglPengikatan = "";
                    if (agunan.getTglPengikatan() != null) {
                        try {
                            tglPengikatan = Formater.formatDate(agunan.getTglPengikatan(), "yyyy-MM-dd");
                        } catch (Exception ex) {
                            tglPengikatan = "";
                        }
                    }
                    rowx.add("" + tglPengikatan);//10
                    rowx.add("" + agunan.getNamaPemilikAgunan());//11

                    if (agunan.getBuktiKepemilikan().equals("")) {
                        rowx.add("WAJIB ISI");//12
                    } else {
                        rowx.add("" + agunan.getBuktiKepemilikan());//12
                    }
                    if (agunan.getAlamatAgunan().equals("")) {
                        rowx.add("WAJIB ISI");//12
                    } else {
                        rowx.add("" + agunan.getAlamatAgunan());//13
                    }

                    if (agunan.getKodeKabLokasiAgunan().equals("")) {
                        rowx.add("WAJIB ISI");//12
                    } else {
                        rowx.add("" + agunan.getKodeKabLokasiAgunan());//14
                    }

                    rowx.add("" + FRMHandler.userFormatStringDecimal(agunan.getNilaiAgunanNjop()));//15
                    rowx.add("" + FRMHandler.userFormatStringDecimal(agunan.getNilaiAgunanLjk()));//16
                    String tglPenilaian = "";
                    try {
                        tglPenilaian = Formater.formatDate(agunan.getTglPenilaianLjk(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglPenilaian = "";
                    }
                    if (agunan.getKodeStatusAgunan().equals("1") && tglPenilaian.equals("")) {
                        rowx.add("WAJIB ISI");//17
                    } else {
                        rowx.add("" + tglPenilaian);//17
                    }

                    rowx.add("" + agunan.getNilaiAguPenilaiIndep());//18
                    rowx.add("" + agunan.getNamaPenilaiIndep());//19
                    String tglPenilaianPenilaiIndep = "";
                    try {
                        tglPenilaianPenilaiIndep = Formater.formatDate(agunan.getTglPenilaianPenilaiIndep(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglPenilaianPenilaiIndep = "";
                    }
                    rowx.add("" + tglPenilaianPenilaiIndep);//20
                    rowx.add("" + agunan.getStatusParipasu());//21
                    rowx.add("" + agunan.getProsentaseParipasu());//22
                    rowx.add("" + agunan.getStatusKreditJoin());//23
                    rowx.add("" + agunan.getDiasuransikan());//24
                    rowx.add("" + agunan.getKeterangan());//25
                    listData.add(rowx);
                }

                break;

            case SEGMEN_BANK_GARANSI:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    noUrut = noUrut + 1;
                    BankGaransi bankGaransi = (BankGaransi) listObj.get(i);
                    rowx.add("" + noUrut);//2
                    rowx.add("" + bankGaransi.getKodeKantorCabang());//2
                    rowx.add("" + bankGaransi.getNoRekening());//2
                    rowx.add("" + bankGaransi.getCif());//3
                    rowx.add("" + bankGaransi.getKodeJenisGaransi());//4
                    rowx.add("" + bankGaransi.getKodeTujuanGaransi());//5

                    try {
                        rowx.add("" + bankGaransi.getTglDiterbitkan());//6
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    try {
                        rowx.add("" + bankGaransi.getTglJatuhTempo());//7
                    } catch (Exception es) {
                        rowx.add("");
                    }

                    rowx.add("" + bankGaransi.getNoAkadAwal());//8

                    try {
                        rowx.add("" + bankGaransi.getTglAkadAwal());//9
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + bankGaransi.getNoAkadAkhir());//10

                    try {
                        rowx.add("" + bankGaransi.getTglAkadAkhir());//11
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + bankGaransi.getNamaYgDijamin());//12
                    rowx.add("" + bankGaransi.getKodeValuta());//13
                    rowx.add("" + FRMHandler.userFormatStringDecimal(bankGaransi.getPlafon()));//14
                    rowx.add("" + FRMHandler.userFormatStringDecimal(bankGaransi.getNominal()));//15
                    rowx.add("" + bankGaransi.getSetoranJaminan());//16
                    rowx.add("" + bankGaransi.getKodeKolektibilitas());//17
                    try {
                        rowx.add("" + bankGaransi.getTglWanPrestasi());//18
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + bankGaransi.getKodeKondisi());//19
                    try {
                        if (!bankGaransi.getKodeKondisi().equals("0") && bankGaransi.getTglKondisi() == null) {
                            rowx.add("WAJIB ISI");
                        } else {
                            rowx.add("" + bankGaransi.getTglKondisi());//20
                        }

                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + bankGaransi.getKeterangan());//21
                    listData.add(rowx);

                }
                break;

            case SEGMEN_DEBITUR_BADAN_USAHA:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    DebiturBdnUsaha debiturBdnUsaha = (DebiturBdnUsaha) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + debiturBdnUsaha.getKodeKtrCabang());//2
                    rowx.add("" + debiturBdnUsaha.getCif());//2
                    rowx.add("" + debiturBdnUsaha.getNoRekening());//2

                    rowx.add("" + Formater.formatDate(debiturBdnUsaha.getTglAkadAkhir(), "yyyy-MM-dd"));//2

                    if (debiturBdnUsaha.getNoIdentitas().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getNoIdentitas());//3
                    }

                    if (debiturBdnUsaha.getNama().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getNama());//4
                    }

                    if (debiturBdnUsaha.getKodeJenis().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKodeJenis());//5
                    }
                    if (debiturBdnUsaha.getTempat().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getTempat());//6
                    }
                    if (debiturBdnUsaha.getNoAkte().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getNoAkte());//7
                    }
                    try {
                        rowx.add("" + debiturBdnUsaha.getTglAktePendirian());//8
                    } catch (Exception es) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    }

                    if (debiturBdnUsaha.getNoAktePerubahan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getNoAktePerubahan());//9
                    }
                    try {
                        if(debiturBdnUsaha.getTglAktePerubahan()!=null){
                            rowx.add("" + debiturBdnUsaha.getTglAktePerubahan());//10
                        }else{
                             rowx.add("WAJIB ISI");//9
                        }
                    } catch (Exception es) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    }
                    rowx.add("" + debiturBdnUsaha.getTelepon());//11
                    rowx.add("" + debiturBdnUsaha.getEmail());//12

                    if (debiturBdnUsaha.getAlamat().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getAlamat());//13
                    }
                    if (debiturBdnUsaha.getKelurahan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKelurahan());//14
                    }
                    if (debiturBdnUsaha.getKecamatan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKecamatan());//15
                    }
                    if (debiturBdnUsaha.getKodeKab().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKodeKab());//16
                    }
                    if (debiturBdnUsaha.getKodePos().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKodePos());//17
                    }
                    if (debiturBdnUsaha.getKodeNegara().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKodeNegara());//18
                    }
                    if (debiturBdnUsaha.getKodeBidangUsaha().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKodeBidangUsaha());//19
                    }
                    if (debiturBdnUsaha.getKodeHubLjk().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKodeHubLjk());//20
                    }

                    rowx.add("" + debiturBdnUsaha.getMelanggarBmpk());//21
                    rowx.add("" + debiturBdnUsaha.getMelampauiBmpk());//22
                    rowx.add("" + debiturBdnUsaha.getGoPublic());//23
                    if (debiturBdnUsaha.getKodeGol().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        //updateStatus = false;
                    } else {
                        rowx.add("" + debiturBdnUsaha.getKodeGol());//24
                    }
                    rowx.add("" + debiturBdnUsaha.getPeringkat());//25
                    rowx.add("" + debiturBdnUsaha.getLembagaPemeringkat());//26

                    try {
                        rowx.add("" + debiturBdnUsaha.getTglPemeringkat());//27
                    } catch (Exception es) {
                    }

                    rowx.add("" + debiturBdnUsaha.getNamaGroup());//28
                    listData.add(rowx);

                }
                break;

            case SEGMEN_FASILITAS_LAINNYA:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    FasilitasLain fasilitasLain = (FasilitasLain) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + fasilitasLain.getKodeKantorCabang());//2
                    rowx.add("" + fasilitasLain.getNoRekening());//2
                    rowx.add("" + fasilitasLain.getCif());//3
                    rowx.add("" + fasilitasLain.getKodeJenisFasilitas());//4
                    rowx.add("" + fasilitasLain.getSumberDana());//5
                    String tglMulai = "";
                    try {
                        tglMulai = Formater.formatDate(fasilitasLain.getTglMulai(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglMulai = "";
                    }
                    rowx.add("" + tglMulai);//6
                    String tglJatuhTempo = "";
                    try {
                        tglJatuhTempo = Formater.formatDate(fasilitasLain.getTglJatuhTempo(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglJatuhTempo = "";
                    }
                    rowx.add("" + tglJatuhTempo);//7
                    rowx.add("" + fasilitasLain.getSukuBunga());//8
                    rowx.add("" + fasilitasLain.getKodeValuta());//9
                    rowx.add("" + fasilitasLain.getNominalJmlKewajiban());//10
                    rowx.add("" + fasilitasLain.getNilaiMataUangAsal());//11
                    rowx.add("" + fasilitasLain.getKodeKolektibilitas());//12
                    String tglMacet = "";
                    try {
                        tglMacet = Formater.formatDate(fasilitasLain.getTglMacet(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglMacet = "";
                    }
                    rowx.add("" + tglMacet);//13
                    rowx.add("" + fasilitasLain.getKodeSebabMacet());//14
                    rowx.add("" + FRMHandler.userFormatStringDecimal(fasilitasLain.getTunggakan()));//15
                    rowx.add("" + fasilitasLain.getJmlHariTunggakan());//16
                    rowx.add("" + fasilitasLain.getKodeKondisi());//17
                    String tglKondisi = "";
                    try {
                        tglKondisi = Formater.formatDate(fasilitasLain.getTglKondisi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKondisi = "";
                    }
                    rowx.add("" + tglKondisi);//18
                    rowx.add("" + fasilitasLain.getKeterangan());//19
                    listData.add(rowx);

                }
                break;

            case SEGMEN_IRREVOCABLE_LC:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    IrrevocableLc irrevocableLc = (IrrevocableLc) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + irrevocableLc.getKodeKantorCabang());//2
                    rowx.add("" + irrevocableLc.getNoRekening());//2
                    rowx.add("" + irrevocableLc.getCif());//3
                    rowx.add("" + irrevocableLc.getKodeJenis());//4
                    rowx.add("" + irrevocableLc.getKodeTujuan());//5
                    String tglKeluar = "";
                    try {
                        tglKeluar = Formater.formatDate(irrevocableLc.getTglKeluar(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKeluar = "";
                    }
                    rowx.add("" + tglKeluar);//6
                    String tglJatuhTempo = "";
                    try {
                        tglJatuhTempo = Formater.formatDate(irrevocableLc.getTglJatuhTempo(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglJatuhTempo = "";
                    }
                    rowx.add("" + tglJatuhTempo);//7
                    rowx.add("" + irrevocableLc.getNoAkadAwal());//8
                    String tglAkadAwal = "";
                    try {
                        tglAkadAwal = Formater.formatDate(irrevocableLc.getTglAkadAwal(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAkadAwal = "";
                    }
                    rowx.add("" + tglAkadAwal);//9
                    rowx.add("" + irrevocableLc.getNoAkadAkhr());//10
                    String tglAkadAkhir = "";
                    try {
                        tglAkadAkhir = Formater.formatDate(irrevocableLc.getTglAkadAkhir(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAkadAkhir = "";
                    }
                    rowx.add("" + tglAkadAkhir);//11
                    rowx.add("" + irrevocableLc.getBankCounterparty());//12
                    rowx.add("" + irrevocableLc.getKodeValuta());//13
                    rowx.add("" + irrevocableLc.getPlafon());//14
                    rowx.add("" + irrevocableLc.getNominal());//15
                    rowx.add("" + irrevocableLc.getSetoranJaminan());//16
                    rowx.add("" + irrevocableLc.getKodeKolektibilitas());//17
                    String tglWanPrestasi = "";
                    try {
                        tglWanPrestasi = Formater.formatDate(irrevocableLc.getTglWanPrestasi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglWanPrestasi = "";
                    }
                    rowx.add("" + tglWanPrestasi);//18
                    rowx.add("" + irrevocableLc.getKodeKondisi());//19
                    String tglKondisi = "";
                    try {
                        tglKondisi = Formater.formatDate(irrevocableLc.getTglKondisi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKondisi = "";
                    }
                    rowx.add("" + tglKondisi);//20
                    rowx.add("" + irrevocableLc.getKeterangan());//21
                    listData.add(rowx);

                }
                break;

            case SEGMEN_KREDIT:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    Kredit kredit = (Kredit) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//0
                    rowx.add("" + kredit.getKodeKantorCabang());//1
                    rowx.add("" + kredit.getNoRekening());//2
                    rowx.add("" + kredit.getCif());//3
                    
                    if (kredit.getKodeSifat().equals("")) {
                        rowx.add("WAJIB ISI");//9
                    } else {
                        rowx.add("" + kredit.getKodeSifat());//4
                    }
                    if (kredit.getKodeJenisKredit().equals("")) {
                        rowx.add("WAJIB ISI");//9
                    } else {
                        rowx.add("" + kredit.getKodeJenisKredit());//5
                    }
                    
                    rowx.add("" + kredit.getKodeAkad());//6
                    rowx.add("" + kredit.getNoAkadAwal());//7
                    String tglAkadAwal = "";
                    try {
                        tglAkadAwal = Formater.formatDate(kredit.getTglAkadAwal(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAkadAwal = "";
                    }
                    rowx.add("" + tglAkadAwal);//8
                    rowx.add("" + kredit.getNoAkadAkhir());//9
                    String tglAkadAkhir = "";
                    try {
                        tglAkadAkhir = Formater.formatDate(kredit.getTglAkadAkhir(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglAkadAkhir = "";
                    }
                    rowx.add("" + tglAkadAkhir);//10
                    
                    rowx.add("" + kredit.getBaruPerpanjangan());//11
                    String tglAwal = "";
                    try {
                        if(kredit.getTglAwal()!=null){
                            tglAwal = Formater.formatDate(kredit.getTglAwal(), "yyyy-MM-dd");
                        }else{
                            tglAwal="";
                        }
                    } catch (Exception ex) {
                        tglAwal="";
                    }
                    rowx.add("" + tglAwal);//12
                    String tglMulai = "";
                    try {
                        if(kredit.getTglMulai()!=null){
                            tglMulai = Formater.formatDate(kredit.getTglMulai(), "yyyy-MM-dd");
                        }else{
                            tglMulai="WAJIB ISI";
                        }
                        
                    } catch (Exception ex) {
                        tglMulai="WAJIB ISI";
                    }
                    
                    rowx.add("" + tglMulai);//13
                    String tglTempo = "";
                    try {
                        //tglTempo = Formater.formatDate(kredit.getTglTempo(), "yyyy-MM-dd");
                        if(kredit.getTglTempo()!=null){
                            tglTempo = Formater.formatDate(kredit.getTglTempo(), "yyyy-MM-dd");
                        }else{
                            tglTempo="WAJIB ISI";
                        }
                    } catch (Exception ex) {
                        tglTempo="WAJIB ISI";
                    }
                    rowx.add("" + tglTempo);//14
                    
                    if (kredit.getKodeKatDbitur().equals("")) {
                        rowx.add("WAJIB ISI");//9
                    } else {
                        rowx.add("" + kredit.getKodeKatDbitur());//15
                    }
                    if (kredit.getKodeJenisPenggunaan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                    } else {
                        rowx.add("" + kredit.getKodeJenisPenggunaan());//16
                    }
                    if (kredit.getKodeOrientasiPenggunaan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                    } else {
                         rowx.add("" + kredit.getKodeOrientasiPenggunaan());//17
                    }
                    if (kredit.getKodeSektorEkonomi().equals("")) {
                        rowx.add("WAJIB ISI");//9
                    } else {
                         rowx.add("" + kredit.getKodeSektorEkonomi());//18
                    }
                    if (kredit.getKodeKab().equals("")) {
                        rowx.add("WAJIB ISI");//9
                    } else {
                         rowx.add("" + kredit.getKodeKab());//19
                    }
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getNilai()));//20
                    rowx.add("" + kredit.getKodeValuta());//21
                    rowx.add("" + kredit.getProsentaseBunga());//22
                    rowx.add("" + kredit.getJenisBunga());//23
                    rowx.add("" + kredit.getKreditPemerintah());//24
                    rowx.add("" + kredit.getTakeover());//25
                    rowx.add("" + kredit.getSumberDana());//26
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getPlafonAwal()));//27
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getPlafon()));//28
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getRealisasi()));//29
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getDenda()));//30
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getBakiDebet()));//31
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getNilaiUangAsal()));//32
                    rowx.add("" + kredit.getKodeKolektibilitas());//33
                    String tglMacet = "";
                    try {
                        if(kredit.getKodeKolektibilitas().equals("5")){
                            if(kredit.getTglMacet()!=null){
                                tglMacet = Formater.formatDate(kredit.getTglMacet(), "yyyy-MM-dd");
                            }else{
                                rowx.add("WAJIB ISI");//9
                            }
                        }else{
                            tglMacet = "";
                        }
                        
                    } catch (Exception ex) {
                        tglMacet = "";
                    }
                    rowx.add("" + tglMacet);//34
                    if(kredit.getKodeKolektibilitas().equals("5")){
                        if(kredit.getKodeSebabMacet().equals("")){
                             rowx.add("WAJIB ISI");//9
                        }else{
                             rowx.add("" + kredit.getKodeSebabMacet());//35
                        }
                    }else{
                        rowx.add("" + kredit.getKodeSebabMacet());//35
                    }
                    
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getTunggakanPokok()));//36
                    rowx.add("" + FRMHandler.userFormatStringDecimal(kredit.getTunggakanBunga()));//37
                    
                    if(kredit.getTunggakanBunga()>0 && kredit.getTunggakanPokok() > 0 && kredit.getJmlHariTunggakan()==0){
                        rowx.add("WAJIB ISI");//9
                    }else{
                        rowx.add("" + kredit.getJmlHariTunggakan());//38
                    }
                    
                    rowx.add("" + kredit.getFrekuensiTunggakan());//39
                    rowx.add("" + kredit.getFrekuensiRestrukturisasi());//40
                    String tglRestrukturisasiAwal = "";
                    try {
                        tglRestrukturisasiAwal = Formater.formatDate(kredit.getTglRestrukturisasiAwal(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglRestrukturisasiAwal = "";
                    }
                    rowx.add("" + tglRestrukturisasiAwal);//41
                    String tglres = "";
                    try {
                        tglres = Formater.formatDate(kredit.getTglRestrukturisasiAkhir(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglres = "";
                    }
                    rowx.add("" + tglres);//42
                    rowx.add("" + kredit.getKodeCara());//43
                    if(kredit.getKodeKondisi().equals("")){
                        rowx.add("WAJIB ISI");//9
                    }else{
                        rowx.add("" + kredit.getKodeKondisi());//44
                    }
                    String tglKondisi = "";
                    try {
                        tglKondisi = Formater.formatDate(kredit.getTglKondisi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKondisi = "";
                    }
                    rowx.add("" + tglKondisi);//45
                    rowx.add("" + kredit.getKeterangan());//46
                    listData.add(rowx);

                }
                break;

            case SEGMEN_KREDIT_JOIN:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    KreditJoinAccount kreditJoinAccount = (KreditJoinAccount) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + kreditJoinAccount.getKodeKantorCabang());//2
                    rowx.add("" + kreditJoinAccount.getNoRekening());//2
                    rowx.add("" + kreditJoinAccount.getCif());//3
                    rowx.add("" + kreditJoinAccount.getSquenceDebJoin());//4
                    rowx.add("" + kreditJoinAccount.getKodeSifat());//5
                    rowx.add("" + kreditJoinAccount.getKodeJenis());//6
                    rowx.add("" + kreditJoinAccount.getKodeAkad());//7
                    rowx.add("" + kreditJoinAccount.getNoAkadAwal());//8

                    try {
                        rowx.add("" + kreditJoinAccount.getTglAkadAwal());//9
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getNoAkadAkhir());//10
                    try {
                        rowx.add("" + kreditJoinAccount.getTglAkadAkhir());//11
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getBaruPerpanjangan());//12

                    try {
                        rowx.add("" + kreditJoinAccount.getTglAwalKredit());//13
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    try {
                        rowx.add("" + kreditJoinAccount.getTglMulai());//14
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    try {
                        rowx.add("" + kreditJoinAccount.getTglJatuhTempo());//15
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getKodeKatDeb());//16
                    rowx.add("" + kreditJoinAccount.getKodeJenisPenggunaan());//17
                    rowx.add("" + kreditJoinAccount.getKodeOrientasiPenggunaan());//18
                    rowx.add("" + kreditJoinAccount.getKodeSektorEkonomi());//19
                    rowx.add("" + kreditJoinAccount.getKodeKab());//20
                    rowx.add("" + kreditJoinAccount.getNilaiProyek());//21
                    rowx.add("" + kreditJoinAccount.getKodeValuta());//22
                    rowx.add("" + kreditJoinAccount.getProsentaseBunga());//23
                    rowx.add("" + kreditJoinAccount.getJenisBunga());//24
                    rowx.add("" + kreditJoinAccount.getKreditPrgPemerintah());//25
                    rowx.add("" + kreditJoinAccount.getTakeover());//26
                    rowx.add("" + kreditJoinAccount.getSumberDana());//27
                    rowx.add("" + kreditJoinAccount.getPlafonAwal());//28
                    rowx.add("" + kreditJoinAccount.getPlafon());//29
                    rowx.add("" + kreditJoinAccount.getRealisasi());//30
                    rowx.add("" + kreditJoinAccount.getDenda());//31
                    rowx.add("" + kreditJoinAccount.getBakiDebet());//32
                    rowx.add("" + kreditJoinAccount.getNilaiUangAsal());//33
                    rowx.add("" + kreditJoinAccount.getKodeKolektibilitas());//34
                    try {
                        rowx.add("" + kreditJoinAccount.getTglMacet());//35
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getKodeSebabMacet());//36
                    rowx.add("" + kreditJoinAccount.getTunggakanPokok());//37
                    rowx.add("" + kreditJoinAccount.getTunggakanBunga());//38
                    rowx.add("" + kreditJoinAccount.getJmlHariTunggakan());//39
                    rowx.add("" + kreditJoinAccount.getFrekuensiTunggakan());//40
                    rowx.add("" + kreditJoinAccount.getFrekuensiRestrukturisasi());//41
                    try {
                        rowx.add("" + kreditJoinAccount.getTglRestrukturisasiAwal());//42
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    try {
                        rowx.add("" + kreditJoinAccount.getTglRestruktirisasiAkhir());//43
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getKodeCara());//44
                    rowx.add("" + kreditJoinAccount.getKodeKondisi());//45
                    try {
                        rowx.add("" + kreditJoinAccount.getTglKondisi());//46
                    } catch (Exception es) {
                        rowx.add("");
                    }
                    rowx.add("" + kreditJoinAccount.getKeterangan());//47
                    listData.add(rowx);
                }
                break;

            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    noUrut = noUrut + 1;
                    LaporanKeuanganDebitur laporanKeuanganDebitur = (LaporanKeuanganDebitur) listObj.get(i);
                    rowx.add("" + noUrut);//2
                    rowx.add("" + laporanKeuanganDebitur.getKodeKantorCabang());//2
                    rowx.add("" + laporanKeuanganDebitur.getCif());//2
                    rowx.add("" + laporanKeuanganDebitur.getAset());//3
                    rowx.add("" + laporanKeuanganDebitur.getAsetLancar());//4
                    rowx.add("" + laporanKeuanganDebitur.getKasDanSetaraKas());//5
                    rowx.add("" + laporanKeuanganDebitur.getPiutangUsahaAstLncr());//6
                    rowx.add("" + laporanKeuanganDebitur.getInvestasiAstLncr());//7
                    rowx.add("" + laporanKeuanganDebitur.getAsetLancarLain());//8
                    rowx.add("" + laporanKeuanganDebitur.getAsetTidakLancar());//9
                    rowx.add("" + laporanKeuanganDebitur.getPiutangUsahaAstTdkLncr());//10
                    rowx.add("" + laporanKeuanganDebitur.getInvestasiAstTdkLncr());//11
                    rowx.add("" + laporanKeuanganDebitur.getAsetTdkLncrLain());//12
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitas());//13
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitasJnkPndk());//14
                    rowx.add("" + laporanKeuanganDebitur.getPinjamanJnkPndk());//15
                    rowx.add("" + laporanKeuanganDebitur.getUtangUsahaLiaJnkPndk());//16
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitasJnkPndkLain());//17
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitasJnkPnjg());//18
                    rowx.add("" + laporanKeuanganDebitur.getPinjamanJnkPnjg());//19
                    rowx.add("" + laporanKeuanganDebitur.getUtangUsahaLiaJnkPnjg());//20
                    rowx.add("" + laporanKeuanganDebitur.getLiabilitasJnkPnjgLain());//21
                    rowx.add("" + laporanKeuanganDebitur.getEkuitas());//22
                    rowx.add("" + laporanKeuanganDebitur.getPendapatanUsahaOpr());//23
                    rowx.add("" + laporanKeuanganDebitur.getBebanPokokPend());//24
                    rowx.add("" + laporanKeuanganDebitur.getLabaRugiBruto());//25
                    rowx.add("" + laporanKeuanganDebitur.getPendLainNonOpr());//26
                    rowx.add("" + laporanKeuanganDebitur.getBebanLainNonOpr());//27
                    rowx.add("" + laporanKeuanganDebitur.getLabaRugiSblmPajak());//28
                    rowx.add("" + laporanKeuanganDebitur.getLabaRugiPeriode());//29
                    listData.add(rowx);

                }
                break;

            case SEGMEN_PENGURUS_PEMILIK:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    PengurusAtauPemilik pengurusAtauPemilik = (PengurusAtauPemilik) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + pengurusAtauPemilik.getKodeKantorCabang());//2
                    
                    if(pengurusAtauPemilik.getNoIdentitas().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getNoIdentitas());//2
                    }
                    
                    rowx.add("" + pengurusAtauPemilik.getCif());//3
                    if(pengurusAtauPemilik.getJenisIdentitas().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getJenisIdentitas());//4
                    }
                    if(pengurusAtauPemilik.getNamaPengurus().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getNamaPengurus());//5
                    }
                    
                    if(pengurusAtauPemilik.getJenisKelamin().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getJenisKelamin());//6
                    }
                    if(pengurusAtauPemilik.getAlamat().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getAlamat());//7
                    }
                    if(pengurusAtauPemilik.getKelurahan().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getKelurahan());//8
                    }
                    if(pengurusAtauPemilik.getKecamatan().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getKecamatan());//9
                    }
                    if(pengurusAtauPemilik.getKodeKabupaten().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getKodeKabupaten());//10
                    }
                    if(pengurusAtauPemilik.getKodeJabatan().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getKodeJabatan());//11
                    }
                    
                    if(pengurusAtauPemilik.getPangsaKepemilikan().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getPangsaKepemilikan());//11
                    }
                    
                    if(pengurusAtauPemilik.getStatusPengurus().equals("")){
                        rowx.add("WAJIB ISI");//12
                    }else{
                        rowx.add("" + pengurusAtauPemilik.getStatusPengurus());//13
                    }
                    
                    listData.add(rowx);

                }
                break;

            case SEGMEN_PENJAMIN:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    Penjamin penjamin = (Penjamin) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + penjamin.getKodeKantorCabang());//2
                    rowx.add("" + penjamin.getNoIdPenjamin());//2
                    rowx.add("" + penjamin.getNoRekening());//3
                    rowx.add("" + penjamin.getCif());//4
                    rowx.add("" + penjamin.getJenisIdentitas());//5
                    rowx.add("" + penjamin.getNamaIdentitas());//6
                    rowx.add("" + penjamin.getNamaLengkap());//7
                    rowx.add("" + penjamin.getKodeGolPenjamin());//8
                    rowx.add("" + penjamin.getAlamatPenjamin());//9
                    rowx.add("" + penjamin.getProsentaseDijamin());//10
                    rowx.add("" + penjamin.getKeterangan());//11
                    listData.add(rowx);

                }
                break;

            case SEGMEN_SURAT_BERHARGA:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    Vector rowx = new Vector(1, 1);
                    SuratBerharga suratBerharga = (SuratBerharga) listObj.get(i);

                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//2
                    rowx.add("" + suratBerharga.getKodeKantorCabang());//2
                    rowx.add("" + suratBerharga.getNoRekening());//2
                    rowx.add("" + suratBerharga.getCif());//3
                    rowx.add("" + suratBerharga.getKodeJenis());//4
                    rowx.add("" + suratBerharga.getSovereignRate());//5
                    rowx.add("" + suratBerharga.getListing());//6
                    rowx.add("" + suratBerharga.getPeringkat());//7
                    rowx.add("" + suratBerharga.getKodeTujuan());//8
                    String tglTerbit = "";
                    try {
                        tglTerbit = Formater.formatDate(suratBerharga.getTglTerbit(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglTerbit = "";
                    }
                    rowx.add("" + tglTerbit);//9
                    String tglBeli = "";
                    try {
                        tglBeli = Formater.formatDate(suratBerharga.getTglBeli(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglBeli = "";
                    }
                    rowx.add("" + tglBeli);//10
                    String tglJatuhTempo = "";
                    try {
                        tglJatuhTempo = Formater.formatDate(suratBerharga.getTglJatuhTempo(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglJatuhTempo = "";
                    }
                    rowx.add("" + tglJatuhTempo);//11
                    rowx.add("" + suratBerharga.getKodeValuta());//12
                    rowx.add("" + suratBerharga.getNominal());//13
                    rowx.add("" + suratBerharga.getNilaiUangAsal());//14
                    rowx.add("" + suratBerharga.getNilaiPasar());//15
                    rowx.add("" + suratBerharga.getNilaiPerolehan());//16
                    rowx.add("" + suratBerharga.getSukuBunga());//17
                    rowx.add("" + suratBerharga.getTunggakan());//18
                    rowx.add("" + suratBerharga.getJmlHariTunggakan());//19
                    rowx.add("" + suratBerharga.getKodeKolektibilitas());//20
                    String tglMacet = "";
                    try {
                        tglMacet = Formater.formatDate(suratBerharga.getTglMacet(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglMacet = "";
                    }
                    rowx.add("" + tglMacet);//21
                    rowx.add("" + suratBerharga.getKodeSebabMacet());//22
                    rowx.add("" + suratBerharga.getKodeKondisi());//23
                    String tglKondisi = "";
                    try {
                        tglKondisi = Formater.formatDate(suratBerharga.getTglKondisi(), "yyyy-MM-dd");
                    } catch (Exception ex) {
                        tglKondisi = "";
                    }
                    rowx.add("" + tglKondisi);//24
                    rowx.add("" + suratBerharga.getKeterangan());//25
                    listData.add(rowx);
                }
                break;
            default:
                noUrut = 0;
                for (int i = 0; i < listObj.size(); i++) {
                    boolean updateStatus = true;
                    Vector rowx = new Vector(1, 1);
                    DebiturIndividu debiturIndividu = (DebiturIndividu) listObj.get(i);
                    noUrut = noUrut + 1;
                    rowx.add("" + noUrut);//0
                    rowx.add("" + debiturIndividu.getKodeKantorCabang());//1
                    rowx.add("" + debiturIndividu.getCif());//2
                    rowx.add("" + debiturIndividu.getNoRekening());//2
                    rowx.add("" + Formater.formatDate(debiturIndividu.getTglAkadAkhir(), "yyyy-MM-dd"));//2
                    if (debiturIndividu.getJenisIdentitas().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getJenisIdentitas());//3
                    }

                    if (debiturIndividu.getNik().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getNik());//4
                    }

                    if (debiturIndividu.getNamaIdentitas().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getNamaIdentitas());//5
                    }

                    if (debiturIndividu.getNamaLengkap().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getNamaLengkap());//6
                    }

                    if (debiturIndividu.getKodeStatusGelar().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodeStatusGelar());//7
                    }

                    rowx.add("" + debiturIndividu.getJekel());//8
                    if (debiturIndividu.getTempatLahir().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getTempatLahir());//9
                    }

                    if (debiturIndividu.getTglLahir() != null) {
                        rowx.add("" + Formater.formatDate(debiturIndividu.getTglLahir(), "yyyy-MM-dd"));//10
                    } else {
                        rowx.add("WAJIB ISI");//10
                        updateStatus = false;
                    }
                    rowx.add("" + debiturIndividu.getNpwp());//11

                    if (debiturIndividu.getAlamat().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getAlamat());//12
                    }

                    if (debiturIndividu.getKelurahan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKelurahan());//13
                    }

                    if (debiturIndividu.getKecamatan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKecamatan());//14
                    }

                    if (debiturIndividu.getKodeKab().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodeKab());//15
                    }

                    if (debiturIndividu.getKodePos().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodePos());//16
                    }

                    rowx.add("" + debiturIndividu.getTelepon());//17
                    rowx.add("" + debiturIndividu.getNomorHp());//18
                    rowx.add("" + debiturIndividu.getEmail());//19

                    if (debiturIndividu.getKodeDomisili().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodeDomisili());//20
                    }

                    if (debiturIndividu.getKodePekerjaan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodePekerjaan());//21
                    }

                    if (debiturIndividu.getTempatBekerja().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getTempatBekerja());//22
                    }

                    if (debiturIndividu.getKodeUsahaTempatBekerja().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodeUsahaTempatBekerja());//23
                    }

                    rowx.add("" + debiturIndividu.getAlamatTempatBekerja());//24
                    rowx.add("" + debiturIndividu.getPenghasilanKotor());//25

                    if (debiturIndividu.getKodePenghasilan().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodePenghasilan());//26
                    }

                    rowx.add("" + debiturIndividu.getJmlTanggungan());//27

                    if (debiturIndividu.getKodeHub().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodeHub());//28
                    }

                    if (debiturIndividu.getKodeGol().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getKodeGol());//29
                    }

                    rowx.add("" + debiturIndividu.getStatus());//30
                    rowx.add("" + debiturIndividu.getNikPasangan());//31
                    rowx.add("" + debiturIndividu.getNamaPasangan());//32
                    try {
                        rowx.add("" + Formater.formatDate(debiturIndividu.getTglLahirPasangan(), "yyyy-MM-dd"));//33
                    } catch (Exception es) {
                        rowx.add("");//33
                    }
                    rowx.add("" + debiturIndividu.getPerjanjianPisahHarga());//34
                    rowx.add("" + debiturIndividu.getMelanggarBmpk());//35
                    rowx.add("" + debiturIndividu.getMelampauiBmpk());//36

                    if (debiturIndividu.getNamaIbuKandung().equals("")) {
                        rowx.add("WAJIB ISI");//9
                        updateStatus = false;
                    } else {
                        rowx.add("" + debiturIndividu.getNamaIbuKandung());//37
                    }

                    if (updateStatus && debiturIndividu.getOID() != 0) {
                        try {
                            long updateData = PstDebiturBdnUsaha.updateStatusData(debiturIndividu.getOID());
                        } catch (DBException ex) {
                        }
                    }

                    listData.add(rowx);
                }
                break;
        }

        return listData;
    }

    public String searchSummarySegmen(HttpServletRequest request) {
        String returnData = "";
        String cabang = FRMQueryString.requestString(request, "cabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        String whereClause = "";
        if (cabang.length() > 0) {
            if (whereClause.length() > 0) {
                whereClause += " AND " + PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG] + "='" + cabang + "'";
            } else {
                whereClause += " " + PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG] + "='" + cabang + "'";
            }
        }
        Vector listCabang = PstCabangBank.list(0, 0, whereClause, "" + PstCabangBank.fieldNames[PstCabangBank.FLD_NAMA_CABANG] + " ASC");

        returnData = drawReportSummary(iCommand, listCabang, periodeId);

        //Get Periode
        return returnData;
    }

    public String searchPerSegment(HttpServletRequest request) {
        String returnData = "";
        String cabang = FRMQueryString.requestString(request, "cabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        String getSegment = FRMQueryString.requestString(request, "segment");
        String whereClause = "";
        if (cabang.length() > 0) {
            if (whereClause.length() > 0) {
                whereClause += " AND " + PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG] + "='" + cabang + "'";
            } else {
                whereClause += " " + PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG] + "='" + cabang + "'";
            }
        }

        if (getSegment.length() > 0) {
            int segment = 0;
            try {
                segment = Integer.parseInt(getSegment);
            } catch (Exception ex) {

            }
            returnData += ""
                    + "<div class='row'>"
                    + "<div class='col-md-12'>"
                    + "<div class='form-group'>"
                    + "<b>" + segmenNames[segment] + "</b><br>"
                    + getPerSegmentReport(periodeId, cabang, segment)
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "<div class='row'>"
                    + "<div class='col-md-12'>"
                    + "<div class='form-group'>"
                    + "&nbsp;"
                    + "</div>"
                    + "</div>"
                    + "</div>";
        } else {
            for (int i = 0; i < segmenNames.length; i++) {
                returnData += ""
                        + "<div class='row'>"
                        + "<div class='col-md-12'>"
                        + "<div class='form-group'>"
                        + "<b>" + segmenNames[i] + "</b><br>"
                        + getPerSegmentReport(periodeId, cabang, i)
                        + "</div>"
                        + "</div>"
                        + "</div>"
                        + "<div class='row'>"
                        + "<div class='col-md-12'>"
                        + "<div class='form-group'>"
                        + "&nbsp;"
                        + "</div>"
                        + "</div>"
                        + "</div>";
            }
        }

        //Get Periode
        return returnData;
    }

    public String getPerSegmentReport(long periode, String cabang, int segment) {
        String returnData = "";
        String whereClause = "";
        Vector listObj = new Vector(1, 1);
        switch (segment) {
            case SEGMEN_AGUNAN:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstAgunan.list(0, 0, PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, "" + PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN] + " ASC");
                break;

            case SEGMEN_BANK_GARANSI:

                if (cabang.length() > 0) {
                    whereClause += " AND " + PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstBankGaransi.list(0, 0, PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, "" + PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING] + " ASC");
                break;

            case SEGMEN_DEBITUR_BADAN_USAHA:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_KTR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstDebiturBdnUsaha.list(0, 0, PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_PERIODE_ID] + "='" + periode + "' "
                        + "AND " + PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB] + "!='1'"
                        + "" + whereClause, PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF] + " ASC");
                break;

            case SEGMEN_FASILITAS_LAINNYA:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstFasilitasLain.list(0, 0, "" + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING] + " ASC");
                break;

            case SEGMEN_IRREVOCABLE_LC:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstIrrevocableLc.list(0, 0, "" + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING] + " ASC");
                break;

            case SEGMEN_KREDIT:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstKredit.list(0, 0, "" + PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, PstKredit.fieldNames[PstKredit.FLD_NO_REKENING] + " ASC");
                break;

            case SEGMEN_KREDIT_JOIN:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstKreditJoinAccount.list(0, 0, "" + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_REKENING] + " ASC");
                break;

            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstLaporanKeuanganDebitur.list(0, 0, "" + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_CIF] + " ASC");
                break;

            case SEGMEN_PENGURUS_PEMILIK:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstPengurusAtauPemilik.list(0, 0, "" + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS] + " ASC");
                break;

            case SEGMEN_PENJAMIN:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstPenjamin.list(0, 0, "" + PstPenjamin.fieldNames[PstPenjamin.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN] + " ASC");
                break;

            case SEGMEN_SURAT_BERHARGA:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstSuratBerharga.list(0, 0, "" + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID] + "='" + periode + "'"
                        + "" + whereClause, PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING] + " ASC");
                break;
            default:
                if (cabang.length() > 0) {
                    whereClause += " AND " + PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' ";
                }
                listObj = PstDebiturIndividu.list(0, 0, "" + PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERIODE_ID] + "='" + periode + "' "
                        + "AND " + PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_JENIS_NSB] + "!='1'"
                        + "" + whereClause, PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF] + " ASC");
                break;
        }

        returnData += drawReportPerSegment(iCommand, listObj, segment);
        return returnData;
    }

    public int getSummaryReport(int segmen, String cabang, long periode) {
        int getSummary = 0;

        switch (segmen) {
            case SEGMEN_AGUNAN:
                getSummary = PstAgunan.getCount("" + PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_BANK_GARANSI:
                getSummary = PstBankGaransi.getCount("" + PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_DEBITUR_BADAN_USAHA:
                getSummary = PstDebitur.getCountJoin(""// + PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "" + PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID] + "='" + periode + "' "
                        + "AND " + PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB] + "!='1'",""+periode, cabang);
                break;

            case SEGMEN_FASILITAS_LAINNYA:
                getSummary = PstFasilitasLain.getCount("" + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_IRREVOCABLE_LC:
                getSummary = PstIrrevocableLc.getCount("" + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_KREDIT:
                getSummary = PstKredit.getCount("" + PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_KREDIT_JOIN:
                getSummary = PstKreditJoinAccount.getCount("" + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR:
                getSummary = PstLaporanKeuanganDebitur.getCount("" + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_PENGURUS_PEMILIK:
                getSummary = PstPengurusAtauPemilik.getCount("" + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_PENJAMIN:
                getSummary = PstPenjamin.getCount("" + PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstPenjamin.fieldNames[PstPenjamin.FLD_PERIODE_ID] + "='" + periode + "'");
                break;

            case SEGMEN_SURAT_BERHARGA:
                getSummary = PstSuratBerharga.getCount("" + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "AND " + PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID] + "='" + periode + "'");
                break;
            default:
//                getSummary = PstDebitur.getCount("" + PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
//                        + "AND " + PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID] + "='" + periode + "' "
//                        + "AND " + PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB] + "!='1'");
                getSummary = PstDebitur.getCountJoin(""// + PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG] + "='" + cabang + "' "
                        + "" + PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID] + "='" + periode + "' "
                        + "AND " + PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB] + "='1'",""+periode, cabang);
                break;
        }

        return getSummary;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
