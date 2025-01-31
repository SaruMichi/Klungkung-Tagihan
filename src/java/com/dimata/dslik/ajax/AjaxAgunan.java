/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.agunan.Agunan;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.dslik.entity.debitur.Debitur;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.form.agunan.*;
import com.dimata.dslik.form.contentdata.*;
import com.dimata.dslik.session.proses.ProsesTransferDataBank;
import com.dimata.dslik.session.proses.SessionProsesPerlengkapan;
import com.dimata.gui.jsp.ControlCombo;
import com.dimata.gui.jsp.ControlList;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
public class AjaxAgunan extends HttpServlet {


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
    
    //BOOLEAN
    private boolean privAdd = false;
    private boolean privUpdate = false;
    private boolean privDelete = false;
    private boolean privView = false;
    //INT
    private int iCommand = 0;
    private int iErrCode = 0;
    
    private long userId=0;
    private String userName="";

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
        this.cif = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
        
        //BOOLEAN
        this.privAdd = FRMQueryString.requestBoolean(request, "privadd");
        this.privUpdate = FRMQueryString.requestBoolean(request, "privupdate");
        this.privDelete = FRMQueryString.requestBoolean(request, "privdelete");
	this.privView = FRMQueryString.requestBoolean(request, "privview");
	//INT
	this.iCommand = FRMQueryString.requestCommand(request);
	this.iErrCode = 0;
	
	//OBJECT
	this.jSONObject = new JSONObject();
	
	switch(this.iCommand){
	    case Command.SAVE :
                //history
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
	    this.jSONObject.put("FRM_FIELD_RETURN_OID", this.oidReturn);
	    this.jSONObject.put("FRM_FIELD_DATE_START", this.dateStart);
	    this.jSONObject.put("FRM_FIELD_DATE_END", this.dateEnd);
	}catch(JSONException jSONException){
	    jSONException.printStackTrace();
	}
	
	response.getWriter().print(this.jSONObject);
        
    }
    
    public String downloadItem(HttpServletRequest request){
        String returnData = "";
        String cif = FRMQueryString.requestString(request, "cif");
        String kodeagunan = FRMQueryString.requestString(request, "kodeagunan");
        String norekening = FRMQueryString.requestString(request, "idrekening");
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
        if(!kodeagunan.equals("") && !norekening.equals("")){
            debitur = prosesTransferDataBank.actionTransferDataAgunan(""+kodeagunan, periodeId,1,1,norekening);
        }
       // String xxx = "Sukses";
        this.htmlReturn =""+debitur;
        return returnData;
    }
    
    public void commandNone(HttpServletRequest request){
	if(this.dataFor.equals("showform")){
	    this.htmlReturn = showForm(request);
	}else if(this.dataFor.equals("searchform")){
            this.htmlReturn = searchForm(request);
        }else if (this.dataFor.equals("showform2")){
            this.htmlReturn = showForm2(request);
        }else if(this.dataFor.equals("showlistagunan")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull") || this.dataFor.equals("loadsummaryotheragunan")){
            this.htmlReturn = getSummary(request);
        }else if(this.dataFor.equals("showotherform")){
            this.htmlReturn = otherForm(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	 if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = saveAgunan(request);
        }
    }
    
    
    public String saveAgunan(HttpServletRequest request) {
        //history
        String returnData = "";
        oid = FRMQueryString.requestLong(request, "FRM_FIELD_AGUNAN_OID");
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        CtrlAgunan ctrlAgunan = new CtrlAgunan(request);
        ctrlAgunan.action(iCommand, oid,userId,userName);
        returnData = ctrlAgunan.getMessage();
        return returnData;
    }
    
    
    public void commandDeleteAll(HttpServletRequest request){
	if(this.dataFor.equals("clearlist")){
	    this.htmlReturn = clearList(request);
	}else if(this.dataFor.equals("deleteitem")){
            this.htmlReturn = deleteItem(request);
        }
    }
    
    
    
    public void commandList(HttpServletRequest request, HttpServletResponse response){
	if(this.dataFor.equals("listdatasearch")){
	    String[] cols = { 
                PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID],
                PstAgunan.fieldNames[PstAgunan.FLD_CIF],
                PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN],
                PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("listdataagunan") || this.dataFor.equals("listdataagunanhome")){
            String[] cols = { 
                PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID],
                PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN],
                PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING],
                PstAgunan.fieldNames[PstAgunan.FLD_CIF],
                PstAgunan.fieldNames[PstAgunan.FLD_KODE_STATUS_AGUNAN],
                PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN],
                PstAgunan.fieldNames[PstAgunan.FLD_BUKTI_KEPEMILIKAN],
                PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]
            };
            jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }else if(this.dataFor.equals("showlistagunan")){
	    String[] cols = { 
                PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID],
                PstAgunan.fieldNames[PstAgunan.FLD_CIF],
                PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN],
                PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("showform2")){
            String[] cols = { 
                PstAgunan.fieldNames[PstAgunan.FLD_AGUNAN_OID],
                PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN],
                PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING],
                PstAgunan.fieldNames[PstAgunan.FLD_CIF],
                PstAgunan.fieldNames[PstAgunan.FLD_KODE_STATUS_AGUNAN],
                PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN],
                PstAgunan.fieldNames[PstAgunan.FLD_BUKTI_KEPEMILIKAN],
                PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]
            };
            jSONObject = listDataTables(request, response, cols, "listdataagunan", this.jSONObject);
        }
    }
    
    public void commandDelete(HttpServletRequest request){
	if(this.dataFor.equals("deleterule")){
	    //deleteRules(request);
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
                 if(!kodeCabang.equals("")){
                    whereClause += " AND "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                 }
                  whereClause += alias+""+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                 if(!kodeCabang.equals("")){
                    whereClause += " "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                 }
                 whereClause += alias+""+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }
        }else{
            whereClause += " "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if(dataFor.equals("listdatasearch")){
	    if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+"='"+cif+"'";
                }else{
                    whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+"='"+cif+"'";
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
		whereClause += " AND (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if (dataFor.equals("listdataagunan") || this.dataFor.equals("listdataagunanhome")){
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause += " AND 1=1";
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
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+"='"+status+"'";
                }else{
                    whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+" IS NULL"; 
                }else{
                    whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+" IS NULL"; 
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
	}else if (dataFor.equals("listdataagunan") || this.dataFor.equals("listdataagunanhome")){
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
	
        approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
	String cif = FRMQueryString.requestString(request, "cif");
        String nama = FRMQueryString.requestString(request, "nama");
        String alamat = FRMQueryString.requestString(request, "alamat");
        String whereClause = "";
        String order ="";
	
        String appRoot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
        long periodeId = FRMQueryString.requestLong(request, "periode");
        Periode periode = new Periode();
        try{
            Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            if(listPeriode != null){
                periode = (Periode) listPeriode.get(0);
                periodeId=periode.getOID();
            }
        }catch(Exception ex){

        }
        
        
        String alias = "";
        if(datafor.equals("listdatasearch") || datafor.equals("showlistagunan") || datafor.equals("listdataagunan")  || datafor.equals("listdataagunanhome") ){
            alias = "agunan.";
        }
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                 if(!kodeCabang.equals("")){
                    whereClause += " AND "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                 }
                 whereClause += " "+" "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                 if(!kodeCabang.equals("")){
                    whereClause += " "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                 }
                 whereClause +=alias+""+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }
        }else{
            whereClause += " "+alias+""+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(datafor.equals("listdatasearch")){
		if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+"='"+cif+"'";
                    }else{
                        whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+"='"+cif+"'";
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
                    whereClause += " AND (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if (datafor.equals("listdataagunan") || this.dataFor.equals("listdataagunanhome")){
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause += " AND 1=1";
                whereClause += ""
                    + " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cifParam+"'"
                    //+ " AND "+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'"
                    + " AND ("
                        + " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" like '%"+this.searchTerm+"%'"
                        + " OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                        + " OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" like '%"+this.searchTerm+"%'"
                        + " OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_STATUS_AGUNAN]+" like '%"+this.searchTerm+"%'"
                        + " OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_NAMA_PEMILIK_AGUNAN]+" like '%"+this.searchTerm+"%'"
                        + " OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_BUKTI_KEPEMILIKAN]+" like '%"+this.searchTerm+"%'"
                        + " OR agunan."+PstAgunan.fieldNames[PstAgunan.FLD_ALAMAT_AGUNAN]+" like '%"+this.searchTerm+"%'"
                    + ")";
            }else if(dataFor.equals("showlistagunan")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+"='"+status+"'";  
                    }else{
                        whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+"='"+status+"'";  
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+" IS NULL";
                    }else{
                        whereClause += " agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+" IS NULL";
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
	    listData = PstAgunan.listJoin(start, amount, whereClause, "");
	}else if (datafor.equals("listdataagunan") || this.dataFor.equals("listdataagunanhome")){
            listData = PstAgunan.listJoin(start, amount, whereClause, "");
        }else if (datafor.equals("showlistagunan")){
            listData = PstAgunan.listJoin(start, amount, whereClause, "");
        }
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
            String buttonUpdate = "";
	    if(datafor.equals("listdatasearch")){
		agunan = (Agunan) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+agunan.getCif());
                ja.put(""+(agunan.getNamaPemilikAgunan()== null ? "-" : agunan.getNamaPemilikAgunan()));
                ja.put(""+(agunan.getAlamatAgunan()== null ? "-" : agunan.getAlamatAgunan()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+agunan.getDebiturOid()+"&type="+agunan.getDebiturType()+"&cif="+agunan.getCif()+"&activetab=AjaxAgunan&otheroid="+agunan.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
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
                if(privView){
                    buttonUpdate += "<a href='"+appRoot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+agunan.getDebiturOid()+"&type="+agunan.getDebiturType()+"&cif="+agunan.getCif()+"&activetab=AjaxAgunan&otheroid="+agunan.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                
                int count  = PstLogSysHistory.getCount(PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+agunan.getOID()+"' "
                        + "AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" LIKE '%Transfer%'");
                
                if(count == 0){
                    buttonUpdate += "<div class='btn btn-default'><input type='checkbox' class='deletelist' value='"+agunan.getOID()+"'></div>";
                }
                ja.put( buttonUpdate);
                array.put(ja);
            }else if (datafor.equals("listdataagunanhome")){
                agunan = (Agunan) listData.get(i);
                
                //get oid debitur
                long debiturOid = 0;
                int debiturType = 0;
//                if(agunan.getCif().length() > 0){
//                    Vector listDebitur = PstDebitur.list(0, 0, ""+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+agunan.getCif()+"'", "");
//                    if(listDebitur.size() > 0){
//                        Debitur debitur = (Debitur) listDebitur.get(0);
//                        debiturOid = debitur.getOID();
//                        debiturType = debitur.getKodeJenisNsb();
//                    }
//                }
                ja.put(""+(this.start+i+1));
                ja.put(""+(agunan.getKodeRegisterAgunan()));
                ja.put(""+(agunan.getNoRekening()));
                ja.put(""+(agunan.getCif()));
                ja.put(""+(agunan.getKodeStatusAgunan()));
                ja.put(""+(agunan.getNamaPemilikAgunan()));
                ja.put(""+(agunan.getBuktiKepemilikan()));
                ja.put(""+(agunan.getAlamatAgunan()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+agunan.getDebiturOid()+"&type="+agunan.getDebiturType()+"&cif="+agunan.getCif()+"&activetab=AjaxAgunan&otheroid="+agunan.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                ja.put(buttonUpdate);
                array.put(ja);
            }else if(datafor.equals("showlistagunan")){
		agunan = (Agunan) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+agunan.getCif());
                ja.put(""+(agunan.getNamaPemilikAgunan()== null ? "-" : agunan.getNamaPemilikAgunan()));
                ja.put(""+(agunan.getAlamatAgunan()== null ? "-" : agunan.getAlamatAgunan()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+agunan.getDebiturOid()+"&type="+agunan.getDebiturType()+"&cif="+agunan.getCif()+"&activetab=AjaxAgunan&otheroid="+agunan.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
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
        
        int needAdd = 0;
        String cif = "";
        int countData = 0;
        String returnData="";
        
        try {
            needAdd = FRMQueryString.requestInt(request, "FRM_FIELD_NEED_ADD");
        } catch (Exception e) {
            needAdd = 0;
        }
        
        try {
            cif = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
        } catch (Exception e) {
            cif = "";
        }
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        
        long periodeId = FRMQueryString.requestLong(request, "periode");
        Periode periode = new Periode();
        try{
            Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            if(listPeriode != null){
                periode = (Periode) listPeriode.get(0);
                periodeId=periode.getOID();
            }
        }catch(Exception ex){

        }
        
        if (cif.length()>0){
            String where = PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cif+"' AND "+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
            if(!kodeCabang.equals("")){
                where = where+ " AND "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
            countData = PstAgunan.getCount(where);
        }
        
        if (cif.length()==0){
            returnData = ""        
            +"<div class='row'>"
                +"<div class='col-md-12'>"
                    +"<div class='box'>"
                        +"<div class='box-header'>"
                            +"<h3 class='box-title'>List Agunan</h3>"
                        +"</div>"
                        +"<div class=\"box-body\">"
                              //+drawListAgunan(iCommand, listAgunan)
                              + "<div id='tableListAgunan'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"
                                                + "<td>Kode Register Agunan</td>"
                                                + "<td>No Rekening</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Kode Status Agunan</td>"
                                                + "<td>Nama Pemilik Agunan</td>"
                                                + "<td>Bukti Kepemilikan</td>"
                                                + "<td>Alamat Agunan</td>"                                            
                                                + "<td>Aksi</td>"
                                            + "</tr>"                              
                                        + "</thead>"             
                                    + "</table>"
                                + "</div>"
                        +"</div>"
                         +"<div class='box-header'>";
                            if(privAdd){
                                returnData +="<button type='button' class='btn btn-primary btnaddgeneralagunan' data-oid='0' data-type='1' data-for ='showform2' data-target='AjaxAgunan' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data Agunan</button>";
                            }
                            
                            if(privDelete){
                                returnData +=" <button type='button' class='btn btn-danger' id='delete' data-for='deleteitem' data-target='AjaxAgunan' data-command='"+Command.DELETEALL+"'><i class='fa fa-trash'></i> Hapus Data Agunan</button>";
                            }
                            
                            returnData +=""
                        +"</div>"
                    +"</div>"
                +"</div>"
            +"</div>"
            + "<div id='agunanform'></div>"  ; 
        }else{
            if (countData>0){
                returnData = ""        
                +"<div class='row'>"
                    +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Agunan</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                  //+drawListAgunan(iCommand, listAgunan)
                                  + "<div id='tableListAgunan'>"
                                        + "<table class='table table-bordered table-striped'>"
                                            + "<thead>"
                                                + "<tr>"
                                                    + "<td>No</td>"
                                                    + "<td>Kode Register Agunan</td>"
                                                    + "<td>No Rekening</td>"
                                                    + "<td>CIF</td>"
                                                    + "<td>Kode Status Agunan</td>"
                                                    + "<td>Nama Pemilik Agunan</td>"
                                                    + "<td>Bukti Kepemilikan</td>"
                                                    + "<td>Alamat Agunan</td>"                                            
                                                    + "<td>Aksi</td>"
                                                + "</tr>"                              
                                            + "</thead>"             
                                        + "</table>"
                                    + "</div>"
                            +"</div>"
                             
                        +"</div>"
                    +"</div>"
                +"</div>";
                
            }
        }
	
	
	
	return returnData;
    }
    
    public String showForm2(HttpServletRequest request){
        Date dNow = new Date();
        Agunan agunan = new Agunan();
        agunan.setCif(cif);
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        agunan.setKodeKantorCabang(kodeCabang);
        String tglPengikatan = "";
        String tglPenilaianLjk = "";
        String tglPenilaianPenilaiIndep = "";
        String openDate = "";
        long oidPeriodeWrite = 0;
        
        String mandatoryNilaiLJK="";
        String mandatoryNilaiNJOP="";
        
        if (oid != 0) {
            try {
                agunan = PstAgunan.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            if (agunan.getTglPengikatan()==null){
                tglPengikatan = "";
            }else{
                tglPengikatan = Formater.formatDate(agunan.getTglPengikatan(), "yyyy-MM-dd");
            }
            
            if (agunan.getTglPenilaianLjk()==null){
                tglPenilaianLjk = "";
            }else{
                tglPenilaianLjk = Formater.formatDate(agunan.getTglPenilaianLjk(), "yyyy-MM-dd");
            }
            if (agunan.getTglPenilaianPenilaiIndep()==null){
                tglPenilaianPenilaiIndep = "";
            }else{
                tglPenilaianPenilaiIndep = Formater.formatDate(agunan.getTglPenilaianPenilaiIndep(), "yyyy-MM-dd");
            }
            if (agunan.getOpenDate()==null){
                openDate = "";
            }else{
                openDate = Formater.formatDate(agunan.getOpenDate(), "yyyy-MM-dd");
            }
            oidPeriodeWrite = agunan.getPeriodeId();
            
            //cek untuk mandatory optional
            if(agunan.getKodeStatusAgunan().equals("1")){
                if (agunan.getNilaiAgunanLjk()<=0){
                    mandatoryNilaiLJK="data-required='required'";
                }
                if (agunan.getNilaiAgunanNjop()<=0){
                    mandatoryNilaiNJOP="data-required='required'";
                }
            }
            
        }
        else {
            tglPengikatan = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglPenilaianLjk = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglPenilaianPenilaiIndep = Formater.formatDate(dNow, "yyyy-MM-dd");
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
            Vector listPeriode = PstPeriode.list(0, 1, " "+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            Periode entPeriode = (Periode) listPeriode.get(0);
            oidPeriodeWrite = entPeriode.getOID();
        }
        long periodeId = FRMQueryString.requestLong(request, "periode");
        Periode periode = new Periode();
        try{
            Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            if(listPeriode != null){
                periode = (Periode) listPeriode.get(0);
                periodeId=periode.getOID();
            }
        }catch(Exception ex){

        }
        //tampilkan listAgunan berdasarkan cif
        Vector listAgunan = new Vector();
        if(!cif.equals("")){
                String where = PstAgunan.fieldNames[PstAgunan.FLD_CIF]+"='"+cif+"'";
                listAgunan = PstAgunan.list(0,0, ""+where, "");
        }
      
	String returnData = ""
        + "<input type='hidden' value='"+oidPeriodeWrite+"' name ='FRM_FIELD_PERIODE_ID' id='oidPeriode'>"        
     
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>Data Agunan</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KODE_REGISTER_AGUNAN]+"'></i> "
                            + "Kode Register Agunan<i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
                            + "<input type='hidden' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_DATA_PERUBAHAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_DATA_PERUBAHAN] +"' value='0'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_REGISTER_AGUNAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_REGISTER_AGUNAN] +"' value='"+agunan.getKodeRegisterAgunan()+"' data-required='required' data-number='true' data-alpha='true' data-special='' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KODE_REGISTER_AGUNAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_NO_REKENING]+"'></i> "
                            + "No Rekening <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector kreditKey = new Vector(1,1);
                            Vector kreditVal = new Vector(1,1);
                            if(agunan.getOID()==0){
                                Vector listKredit = PstKredit.list(0, 0, ""+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+agunan.getCif()+"' "
                                        + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+agunan.getKodeKantorCabang()+"' "
                                        + "AND "+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId +"'", "");
                                if(listKredit.size() > 0){
                                    for(int i = 0; i < listKredit.size(); i++){
                                        Kredit kredit = (Kredit) listKredit.get(i);
                                        kreditKey.add(""+kredit.getNoRekening());
                                        kreditVal.add(""+kredit.getNoRekening());
                                    }
                                }
                            }else{
                                kreditKey.add(""+agunan.getNoRekening());
                                kreditVal.add(""+agunan.getNoRekening());
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NO_REKENING],null, agunan.getNoRekening(), kreditKey, kreditVal, " data-required='required' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_NO_REKENING]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_CIF]+"'></i> "
                            + "CIF <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_CIF] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_CIF] +"' value='"+agunan.getCif()+"' data-required='required' data-number='true' data-alpha='true' data-special=''  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_CIF]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
                
                    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KODE_JENIS_SEGMENT_FASILITAS]+"'></i> "
                            + "Kode Jenis Segmen Fasilitas <i style='color:red'>(m</i></label>"
			+ "<div class='col-sm-8'>";
			    //+ "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_JENIS_SEGMENT_FASILITAS] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_JENIS_SEGMENT_FASILITAS] +"' value='"+agunan.getKodeJenisSegementFasilitas()+"' data-required='required'>"
                            //+ "</div>"
                            //start comboBox
                            Vector valueKdJenisSegmentFasilitas = new Vector();
                            Vector keyKdJenisSegmentFasilitas = new Vector();
                            valueKdJenisSegmentFasilitas.add("");
                            keyKdJenisSegmentFasilitas.add("-- Pilih --");
                            valueKdJenisSegmentFasilitas.add("F01");
                            keyKdJenisSegmentFasilitas.add("Kredit");
                            valueKdJenisSegmentFasilitas.add("F02");
                            keyKdJenisSegmentFasilitas.add("Kredit Join");
                            valueKdJenisSegmentFasilitas.add("F03");
                            keyKdJenisSegmentFasilitas.add("Surat Berharga");
                            valueKdJenisSegmentFasilitas.add("F04");
                            keyKdJenisSegmentFasilitas.add("Irrevocable LC");
                            valueKdJenisSegmentFasilitas.add("F05");
                            keyKdJenisSegmentFasilitas.add("Bank Garansi");
                            valueKdJenisSegmentFasilitas.add("F06");
                            keyKdJenisSegmentFasilitas.add("Fasilitas Lainnya");
                            returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_JENIS_SEGMENT_FASILITAS], null, ""+agunan.getKodeJenisSegementFasilitas(), valueKdJenisSegmentFasilitas, keyKdJenisSegmentFasilitas, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KODE_JENIS_SEGMENT_FASILITAS]+"' data-type='text'", "form-control");
                            returnData += "</div>"
		    + "</div>"
                
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KODE_STATUS_AGUNAN]+"'></i> "
                            + "Kode Status Agunan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                             //start comboBox
			    Vector vCdKodeStatusAgunan =  PstContentDataStatusAgunan.listTanpaOid(0,0,"","");
                            Vector valueCdKodeStatusAgunan = new Vector();
                            Vector keyCdKodeStatusAgunan = new Vector();
                            valueCdKodeStatusAgunan.add("");
                            keyCdKodeStatusAgunan.add("-- Pilih --");
                            for (int i = 0; i < vCdKodeStatusAgunan.size(); i++) {
                                ContentDataStatusAgunan agn = (ContentDataStatusAgunan) vCdKodeStatusAgunan.get(i);
                                valueCdKodeStatusAgunan.add(String.valueOf(agn.getKodeOjk()));
                                keyCdKodeStatusAgunan.add(agn.getKodeOjk()+" - "+agn.getNamaStatusAgunan());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_STATUS_AGUNAN], null, ""+agunan.getKodeStatusAgunan(), valueCdKodeStatusAgunan, keyCdKodeStatusAgunan, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KODE_STATUS_AGUNAN]+"' data-type='text'", "form-control");
                            returnData += "</div>"        
			    //+ "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_STATUS_AGUNAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_STATUS_AGUNAN] +"' value='"+agunan.getKodeStatusAgunan()+"' data-required='required'>"
			//+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KODE_JENIS_AGUNAN]+"'></i> "
                            + "Kode Jenis Agunan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                            //start comboBox
			    Vector vCdJenisAgunan =  PstContentDataJenisAgunan.listTanpaOid(0,0,"","");
                            Vector valueCdJenisAgunan = new Vector();
                            Vector keyCdJenisAgunan = new Vector();
                            valueCdJenisAgunan.add("");
                            keyCdJenisAgunan.add("-- Pilih --");
                            for (int i = 0; i < vCdJenisAgunan.size(); i++) {
                                ContentDataJenisAgunan agn = (ContentDataJenisAgunan) vCdJenisAgunan.get(i);
                                valueCdJenisAgunan.add(String.valueOf(agn.getKodeOjk()));
                                keyCdJenisAgunan.add(agn.getKodeOjk()+"-"+agn.getJenisAgunan());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_JENIS_AGUNAN], null, ""+agunan.getKodeJenisAgunan(), valueCdJenisAgunan, keyCdJenisAgunan, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KODE_JENIS_AGUNAN]+"' data-type='text'", "form-control");
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_PERINGKAT_AGUNAN]+"'></i> "
                            + "Peringkat Agunan <i style='color:red'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_PERINGKAT_AGUNAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_PERINGKAT_AGUNAN] +"' value='"+agunan.getPeringkatAgunan()+"' data-required='required' data-number='true' data-alpha='true' data-special='_/()+-' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_PERINGKAT_AGUNAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KODE_LEMBAGA_PEMERINGKAT]+"'></i> "
                            + "Kode Lembaga Pemeringkat <i style='color:red'>(o*</i></label>"
			+ "<div class='col-sm-8'>";
                            //start comboBox
			    Vector vCdLembaga =  PstContentDataLembagaPemeringkat.list(0,0,"","");
                            Vector valueCdLembaga = new Vector();
                            Vector keyCdLembaga = new Vector();
                            valueCdLembaga.add("");
                            keyCdLembaga.add("-- Pilih --");
                            for (int i = 0; i < vCdLembaga.size(); i++) {
                                ContentDataLembagaPemeringkat lmbg = (ContentDataLembagaPemeringkat) vCdLembaga.get(i);
                                valueCdLembaga.add(String.valueOf(lmbg.getKodeOjk()));
                                keyCdLembaga.add(lmbg.getKodeOjk()+"-"+lmbg.getNamaLembagaPemeringkat());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_LEMBAGA_PEMERINGKAT], null, ""+agunan.getKodeLembagaPemeringkat(), valueCdLembaga, keyCdLembaga, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KODE_LEMBAGA_PEMERINGKAT]+"' data-type='text'", "form-control");
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KODE_JENIS_PENGIKATAN]+"'></i> "
                            + "Kode Jenis Pengikatan <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>";
			   //start comboBox
			    Vector vCdJenisPengikatan =  PstContentDataJenisPengikatan.listTanpaOid(0,0,"","");
                            Vector valueCdJenisPengikatan = new Vector();
                            Vector keyCdJenisPengikatan = new Vector();
                            valueCdJenisPengikatan.add("");
                            keyCdJenisPengikatan.add("-- Pilih --");
                            for (int i = 0; i < vCdJenisPengikatan.size(); i++) {
                                ContentDataJenisPengikatan KdJenisPengikatan = (ContentDataJenisPengikatan) vCdJenisPengikatan.get(i);
                                valueCdJenisPengikatan.add(String.valueOf(KdJenisPengikatan.getKodeOjk()));
                                keyCdJenisPengikatan.add(KdJenisPengikatan.getKodeOjk()+"-"+KdJenisPengikatan.getNamaJenisPengikatan());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_JENIS_PENGIKATAN], null, ""+agunan.getKodeJenisPengikatan(), valueCdJenisPengikatan, keyCdJenisPengikatan, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KODE_JENIS_PENGIKATAN]+"' data-type='text'", "form-control");
                            returnData += "</div>"
                            //end comboBox			
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_TGL_PENGIKATAN]+"'></i> "
                            + "Tanggal Pengikatan <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_TGL_PENGIKATAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_TGL_PENGIKATAN] +"' value='"+tglPengikatan+"' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_TGL_PENGIKATAN]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_NAMA_PEMILIK_AGUNAN]+"'></i> "
                            + "Nama Pemilik Agunan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NAMA_PEMILIK_AGUNAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NAMA_PEMILIK_AGUNAN] +"' value='"+agunan.getNamaPemilikAgunan()+"' data-required='required' data-number='true' data-alpha='true' data-special='& - ' ,.()' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_NAMA_PEMILIK_AGUNAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_BUKTI_KEPEMILIKAN]+"'></i> "
                            + "Bukti Kepemilikan <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_BUKTI_KEPEMILIKAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_BUKTI_KEPEMILIKAN] +"' value='"+agunan.getBuktiKepemilikan()+"' data-required='required' data-number='true' data-alpha='true' data-special='/ - . ,' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_BUKTI_KEPEMILIKAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_ALAMAT_AGUNAN]+"'></i> "
                            + "Alamat Agunan <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_ALAMAT_AGUNAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_ALAMAT_AGUNAN] +"' value='"+agunan.getAlamatAgunan()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" ./&,()'-\" data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_ALAMAT_AGUNAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KODE_KAB_LOKASI_AGUNAN]+"'></i> "
                            + "Kode Kab/Kota (DATI 2) Lokasi Agunan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
			    Vector vCdKab =  PstContentDataKabupatenKota.list(0,0,"","");
                            Vector valueCdKab = new Vector();
                            Vector keyCdKab = new Vector();
                            valueCdKab.add("");
                            keyCdKab.add("-- Pilih --");
                            for (int i = 0; i < vCdKab.size(); i++) {
                                ContentDataKabupatenKota kdKab = (ContentDataKabupatenKota) vCdKab.get(i);
                                valueCdKab.add(String.valueOf(kdKab.getKodeOjk()));
                                keyCdKab.add(kdKab.getKodeOjk()+"-"+kdKab.getNamaKabupatenKota());
                            }
                            returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_KAB_LOKASI_AGUNAN], null, ""+agunan.getKodeKabLokasiAgunan(), valueCdKab, keyCdKab, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KODE_KAB_LOKASI_AGUNAN]+"' data-type='text'", "form-control");
                            returnData += "</div>"
                            //end comboBox			
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>&nbsp;</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_NJOP]+"'></i> "
                            + "Nilai Agunan (NJOP) <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>";
                            if(mandatoryNilaiNJOP.equals("")){
                                returnData += "<input type='text' class='form-control numeric' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_NJOP] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_NJOP] +"' value='"+Formater.formatNumber(agunan.getNilaiAgunanNjop(),"###,###,##0")+"' "+mandatoryNilaiNJOP+" data-number='true' data-alpha='false' data-special='' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_NJOP]+"' data-type='text'>";
                            }else{
                                returnData += "<input type='text' class='form-control numeric' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_NJOP] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_NJOP] +"' value='' "+mandatoryNilaiNJOP+" data-number='true' data-alpha='false' data-special='' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_NJOP]+"' data-type='text'>";
                            }
			returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_LJK]+"'></i> "
                            + "Nilai Agunan Menurut LJK <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>";
                            if(mandatoryNilaiLJK.equals("")){
                                returnData += "<input type='text' class='form-control numeric' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_LJK] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_LJK] +"' value='"+Formater.formatNumber(agunan.getNilaiAgunanLjk(),"###,###,##0")+"' "+mandatoryNilaiLJK+" data-number='true' data-alpha='false' data-special='' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_LJK]+"' data-type='text'>";
                            }else{
                                returnData += "<input type='text' class='form-control numeric' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_LJK] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_LJK] +"' value='' "+mandatoryNilaiLJK+" data-number='true' data-alpha='false' data-special='' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_NILAI_AGUNAN_LJK]+"' data-type='text'>";
                            }
			returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_TGL_PENILAIAN_LJK]+"'></i> "
                                + "Tanggal Penilaian LJK <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_TGL_PENILAIAN_LJK] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_TGL_PENILAIAN_LJK] +"' value='"+tglPenilaianLjk+"' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_TGL_PENILAIAN_LJK]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_NILAI_AGU_PENILAI_INDEP]+"'></i> "
                            + "Nilai Agunan Penilai Independen <i style='color:red'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGU_PENILAI_INDEP] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NILAI_AGU_PENILAI_INDEP] +"' value='"+Formater.formatNumber(agunan.getNilaiAguPenilaiIndep(),"###,###,##0")+"' data-number='true' data-alpha='false' data-special='' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_NILAI_AGU_PENILAI_INDEP]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_NAMA_PENILAI_INDEP]+"'></i> "
                            + "Nama Penilai Independen <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NAMA_PENILAI_INDEP] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_NAMA_PENILAI_INDEP] +"' value='"+agunan.getNamaPenilaiIndep()+"' data-number='true' data-alpha='true' data-special='& - ' ,.()'  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_NAMA_PENILAI_INDEP]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_TGL_PENILAIAN_PENILAI_INDEP]+"'></i> "
                            + "Tanggal Penilaian Penilai Independen <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_TGL_PENILAIAN_PENILAI_INDEP] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_TGL_PENILAIAN_PENILAI_INDEP] +"' value='"+tglPenilaianPenilaiIndep+"'data-number='true' data-alpha='true' data-special=\"&',.()-\" data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_TGL_PENILAIAN_PENILAI_INDEP]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_STATUS_PARIPASU]+"'></i> "
                            + "Status Paripasu <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //+ "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_PARIPASU] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_PARIPASU] +"' value='"+agunan.getStatusParipasu()+"' data-required='required'>"
                                    Vector valueStatusParipasu = new Vector();
                                    Vector keyStatusParipasu = new Vector();
                                    valueStatusParipasu.add("");
                                    keyStatusParipasu.add("--Pilih--");
                                    valueStatusParipasu.add("Y");
                                    keyStatusParipasu.add("Y");
                                    valueStatusParipasu.add("T");
                                    keyStatusParipasu.add("T");
                                    returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_PARIPASU], null, ""+agunan.getStatusParipasu(), valueStatusParipasu, keyStatusParipasu, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_STATUS_PARIPASU]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
                    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_PROSENTASE_PARIPASU]+"'></i> "
                            + "Prosentase Paripasu <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_PROSENTASE_PARIPASU] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_PROSENTASE_PARIPASU] +"' value='"+agunan.getProsentaseParipasu()+"' data-number='true' data-alpha='false' data-special=',' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_PROSENTASE_PARIPASU]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_STATUS_KREDIT_JOIN]+"'></i> "
                                + "Status Kredit Join <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			   // + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_KREDIT_JOIN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_KREDIT_JOIN] +"' value='"+agunan.getStatusKreditJoin()+"' data-required='required'>"
                                    Vector valueStatusKreditJoin = new Vector();
                                    Vector keyStatusKreditJoin = new Vector();
                                    valueStatusKreditJoin.add("");
                                    keyStatusKreditJoin.add("--Pilih--");
                                    valueStatusKreditJoin.add("Y");
                                    keyStatusKreditJoin.add("Y");
                                    valueStatusKreditJoin.add("T");
                                    keyStatusKreditJoin.add("T");
                                    returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_KREDIT_JOIN], null, ""+agunan.getStatusKreditJoin(), valueStatusKreditJoin, keyStatusKreditJoin, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_STATUS_KREDIT_JOIN]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_DIASURANSIKAN]+"'></i> "
                            + "Diasuransikan  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //+ "<input type='text' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_DIASURANSIKAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_DIASURANSIKAN] +"' value='"+agunan.getDiasuransikan()+"' class='form-control'>"
                                    Vector valueAsuransi = new Vector();
                                    Vector keyAsuransi = new Vector();
                                    valueAsuransi.add("");
                                    keyAsuransi.add("--Pilih--");
                                    valueAsuransi.add("Y");
                                    keyAsuransi.add("Y");
                                    valueAsuransi.add("T");
                                    keyAsuransi.add("T");
                                    returnData += ControlCombo.draw(""+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_DIASURANSIKAN], null, ""+agunan.getDiasuransikan(), valueAsuransi, keyAsuransi, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_DIASURANSIKAN]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KETERANGAN]+"'></i> "
                                + "Keterangan <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KETERANGAN] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KETERANGAN] +"' value='"+agunan.getKeterangan()+"' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KETERANGAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmAgunan.fieldQuestion[FrmAgunan.FRM_FIELD_KODE_KANTOR_CABANG]+"'></i> "
                                + "Kode Kantor Cabang <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			   //start comboBox
			    Vector vCdKantorCbg =  PstContentDataKantorCabang.list(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+agunan.getKodeKantorCabang()+"'","");
                            ContentDataKantorCabang kdKantorCbg =  new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKantorCbg.size(); i++) {
                                kdKantorCbg = (ContentDataKantorCabang) vCdKantorCbg.get(i);
                            }
                          
                            returnData += "<input type='hidden' name='"+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+"'>"
                                    + "<input type='text' name='DISPLAY_"+FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+" "+kdKantorCbg.getNamaKantorCabang()+"' data-required='required' readonly='readonly' class='form-control' data-error-message='"+FrmAgunan.fieldError[FrmAgunan.FRM_FIELD_KODE_KANTOR_CABANG]+"' data-type='text'>";
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
//		    + "<div class='form-group'>"
//			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'>*</font></label>"
//			+ "<div class='col-sm-8'>"
//			    + "<input type='text' class='form-control' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_OPERASI_DATA] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_OPERASI_DATA] +"' value='"+agunan.getOperasiData()+"' data-required='required'>"
//			+ "</div>"
//		    + "</div>"
//                    + "<div class='form-group'>"
//			+ "<label class='control-label col-sm-4'>Open Date<font color='red'></font></label>"
//			+ "<div class='col-sm-8'>"
//			     + "<div class='input-group'>"
//				+ "<input type='text' class='form-control datepicker' name='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_OPEN_DATE] +"' id='"+ FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_OPEN_DATE] +"' value='"+openDate+"'>"
//				+ "<div class='input-group-addon'>"
//				    + "<i class='fa fa-calendar'></i>"
//				+ "</div>"
//			    + "</div>"
//			+ "</div>"
//		    + "</div>"
                    + "<div class='form-group hidden'>"
			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'></font></label>"
			+ "<div class='col-sm-8'>";
                            
                            Vector statusDataKey = new Vector(1,1);
                            Vector statusDataVal = new Vector(1,1);
                            for(int i = 0; i < PstAgunan.statusDatas.length; i++){
                                statusDataKey.add(""+i);
                                statusDataVal.add(""+PstAgunan.statusDatas[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmAgunan.fieldNames[FrmAgunan.FRM_FIELD_STATUS_DATA], "-- Pilih --", ""+agunan.getStatusData(), statusDataKey, statusDataVal, "", "form-control")
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
	
	
	return returnData;
    }
    
    public String drawListAgunan(int iCommand, Vector objectClass) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("Kode Register Agunan", "200px");//2
        ctrlist.addHeader("No Rekening", "200px");//2
        ctrlist.addHeader("CIF", "200px");//10
        ctrlist.addHeader("Kode Status Agunan", "200px");//10
        ctrlist.addHeader("Nama Pemilik Agunan", "200px");//10
        ctrlist.addHeader("Bukti Kepemilikan", "200px");//10
        ctrlist.addHeader("Alamat Agunan", "200px");//10
        ctrlist.addHeader("Aksi", "200px");//10
        
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        
        for (int i = 0; i < objectClass.size(); i++) {
            Agunan agunan = (Agunan) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+agunan.getKodeRegisterAgunan());//2
            rowx.add(""+agunan.getNoRekening());//3
            rowx.add(""+agunan.getCif());//4
            rowx.add(""+agunan.getKodeStatusAgunan());//4
            rowx.add(""+agunan.getNamaPemilikAgunan());//4
            rowx.add(""+agunan.getBuktiKepemilikan());//4
            rowx.add(""+agunan.getAlamatAgunan());//4
            rowx.add("<button type='button' class='btn btn-primary btnaddgeneral' data-oid='1' data-command='1'><i class='fa fa-plus'></i>Edit Data</button>");//4
            
            lstData.add(rowx);
        }

        return ctrlist.drawBootstrapStrip();
    }
    
    public String searchForm(HttpServletRequest request){
        String returnData = ""
        + "<div class='row'>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Kode Register Agunan</label>"
                    + "<input type='text' name='cif' class='form-control' id='cif'>"
                    + "<input type='hidden' name='type' id='type' value='2'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Nama Pemilik Agunan</label>"
                    + "<input type='text' name='nama' class='form-control' id='nama'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Alamat Agunan</label>"
                    + "<input type='text' name='alamat' class='form-control' id='alamat'>"
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
                            + "<th>Nama Pemilik Agunan</th>"
                            + "<th>Alamat Agunan</th>"
                            + "<th>Aksi</th>"
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
                        + "<th>Nama Pemilik Agunan</th>"
                        + "<th>Alamat Agunan</th>"
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
            whereClause = "agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+"='0'";
        }else if(this.dataFor.equals("loadsummarynull")){
            whereClause = "agunan."+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+" IS NULL";
        }else{
            whereClause = "l."+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if(userGroup != 4){
            if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
                if(!kodeCabang.equals("")){
                    whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' ";
                }
                whereClause += " AND agunan."+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                if(!kodeCabang.equals("")){
                    whereClause += " AND l."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
                }
            }
        }
        
        int summary = 0;
        if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            summary = PstAgunan.getCountJoin(whereClause);
        }else{
            summary = SessionProsesPerlengkapan.getCountJoinAgunan(whereClause);
        }
        returnData +=""+summary;
        return returnData;
    }
    
    public String otherForm(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        
        String whereClause = "l."+PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periodeId+"'";
        if(userGroup != 4){
            if(!kodeCabang.equals("")){
                whereClause += " AND l."+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        Vector listData = SessionProsesPerlengkapan.listOtherAgunan(0, 0, whereClause, "l."+PstAgunan.fieldNames[PstAgunan.FLD_CIF]+" ASC");
        returnData+=""
        + "<span class='pull-right'><input type='checkbox' class='checkall' value='0'> Check All</span>"
        + "<table class='table table-bordered table-strped'>"
            + "<thead>"
                + "<tr>"
                    + "<th>No.</th>"
                    + "<th>CIF</th>"
                    + "<th>Nama Pemilik Agunan</th>"
                    + "<th>Alamat Agunan</th>"
                    + "<th>Aksi</th>"
                + "</tr>"
            + "</thead>"
            + "<tbody>";
                for(int i = 0; i < listData.size(); i++){
                    Agunan data = (Agunan) listData.get(i);
                    returnData+=""
                    + "<tr>"
                        + "<td>"+(i+1)+"</td>"
                        + "<td>"+data.getCif()+"</td>"
                        + "<td>"+data.getNamaPemilikAgunan()+"</td>"
                        + "<td>"+data.getAlamatAgunan()+"</td>"
                        + "<td><input type='checkbox' name='delete' value='"+data.getOID()+"' class='deletelist'></td>"
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
            CtrlAgunan ctrlAgunan = new CtrlAgunan(request);
            ctrlAgunan.action(Command.DELETE, oid, 0, "");
            String msg = ctrlAgunan.getMessage();
            returnData = "<i class='fa fa-info'></i> "+msg;
        }
        
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        CtrlAgunan ctrlAgunan = new CtrlAgunan(request);
        ctrlAgunan.action(this.iCommand, this.oid, oidDelete);
        String msg = "<i class='fa fa-info'></i> "+ctrlAgunan.getMessage();
        returnData = msg;
        return returnData;
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
    
    
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//        FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        if(supportsGzip(httpServletRequest)) {
//            //CompressionResponseWrapper responseWrapper = new CompressionResponseWrapper(httpServletResponse);
//            filterChain.doFilter(servletRequest, responseWrapper);
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//    
//    private boolean supportsGzip(HttpServletRequest httpServletRequest) {
//        boolean accepts=false;
//        String encoding = httpServletRequest.getHeader("Accept-Encoding");
//        if (null != encoding && encoding.indexOf("gzip")>-1) {
//            accepts = true;
//        }
//        return accepts;
//    }

}
