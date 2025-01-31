/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.suratberharga.PstSuratBerharga;
import com.dimata.dslik.entity.suratberharga.SuratBerharga;
import com.dimata.dslik.form.contentdata.*;
import com.dimata.dslik.form.suratberharga.*;
import com.dimata.gui.jsp.ControlCombo;
import com.dimata.gui.jsp.ControlList;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AjaxSuratBerharga extends HttpServlet {

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
            
    //history
    private long userId = 0;
    private String userName = "";

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
		
	    default : commandNone(request);
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
    
    public void commandNone(HttpServletRequest request){
	if(this.dataFor.equals("showform")){
	    this.htmlReturn = showForm(request);
	}else if(this.dataFor.equals("searchform")){
            this.htmlReturn = searchForm(request);
        }else if (this.dataFor.equals("showform2")){
            this.htmlReturn = showForm2(request);
        }else if (this.dataFor.equals("showlistsuratberharga")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            this.htmlReturn = getSummary(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = saveSuratBerharga(request);
        }
    }
    
    public String saveSuratBerharga(HttpServletRequest request) {
        //history
        String returnData = "";
        oid = FRMQueryString.requestLong(request, "FRM_FIELD_SURAT_BERHARGA_OID");
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        CtrlSuratBerharg ctrlSuratBerharg = new CtrlSuratBerharg(request);
        ctrlSuratBerharg.action(iCommand, oid,userId,userName);
        returnData = ctrlSuratBerharg.getMessage();
        return returnData;
    }
    
    public void commandDeleteAll(HttpServletRequest request){
	if(this.dataFor.equals("deleterateplan")){
	    
	}else if(this.dataFor.equals("deleteitem")){
            this.htmlReturn = deleteItem(request);
        }
    }
    
    
    
    public void commandList(HttpServletRequest request, HttpServletResponse response){
	if(this.dataFor.equals("listdatasearch")){
	    String[] cols = { 
                PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID],
                PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF],
                PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING],
                PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("listdatasuratberharga") || this.dataFor.equals("listdatasuratberhargahome")){
            String[] cols = { 
                "sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID]+"",
                "sb"+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+"",
                "sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF],
                "jsb."+PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA],
                "sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SOVEREIGN_RATE],
                "sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_LISTING],
            };
            jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }else if(this.dataFor.equals("showlistsuratberharga")){
	    String[] cols = { 
                PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID],
                PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF],
                PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING],
                PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
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
        
	
        String cif = FRMQueryString.requestString(request, "cif");
        String noRekening = FRMQueryString.requestString(request, "norekening");
        String kodejenissuratberharga = FRMQueryString.requestString(request, "kodejenissuratberharga");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS");
        String whereClause = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        if(dataFor.equals("listdatasearch")){
            if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(noRekening.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+"='"+noRekening+"'";
                }else{
                    whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+"='"+noRekening+"'";
                }
            }

            if(kodejenissuratberharga.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+"='"+kodejenissuratberharga+"'";
                }else{
                    whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+"='"+kodejenissuratberharga+"'";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if (dataFor.equals("listdatasuratberharga") || this.dataFor.equals("listdatasuratberhargahome")){
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause = "1=1";
            whereClause +=""
                + " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cifParam+"'"
                + " AND ("
                   + " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID]+" like '%"+this.searchTerm+"%'"
                   + " OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                   + " OR jsb."+PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA] + " like '%"+this.searchTerm+"%'"
                   + " OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SOVEREIGN_RATE]+" like '%"+this.searchTerm+"%'"
                   + " OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_LISTING] + " like '%"+this.searchTerm+"%'"
                + ")";
        }else if(dataFor.equals("showlistsuratberharga")){
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{
                    whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }else{
                    whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+" LIKE '%"+this.searchTerm+"%')";
	    }
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstSuratBerharga.getCountJoin(whereClause);
	}else if (dataFor.equals("listdatasuratberharga") || this.dataFor.equals("listdatasuratberhargahome")){
            total = PstSuratBerharga.getCountJoin(whereClause);
        }else if (dataFor.equals("showlistsuratberharga")){
            total = PstSuratBerharga.getCountJoin(whereClause);
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
        SuratBerharga suratBerharga = new SuratBerharga();
	
	String cif = FRMQueryString.requestString(request, "cif");
        String noRekening = FRMQueryString.requestString(request, "norekening");
        String kodejenissuratberharga = FRMQueryString.requestString(request, "kodejenissuratberharga");
        String whereClause = "";
        String order ="";
        String appRoot = FRMQueryString.requestString(request, "FRM_FIELD_APP_ROOT");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
            if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(noRekening.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+"='"+noRekening+"'";
                }else{
                    whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+"='"+noRekening+"'";
                }
            }

            if(kodejenissuratberharga.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+"='"+kodejenissuratberharga+"'";
                }else{
                    whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+"='"+kodejenissuratberharga+"'";
                }
            }
	     if(datafor.equals("listdatasearch")){
		if(whereClause.length() > 0){
                    whereClause += " AND (sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if (dataFor.equals("listdatasuratberharga") || this.dataFor.equals("listdatasuratberhargahome")){
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause = "1=1";
                whereClause +=""
                    + " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cifParam+"'"
                    + " AND ("
                       + " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SURAT_BERHARGA_OID]+" like '%"+this.searchTerm+"%'"
                       + " OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                       + " OR jsb."+PstContentDataJenisSuratBerharga.fieldNames[PstContentDataJenisSuratBerharga.FLD_JENIS_SURAT_BERHARGA] + " like '%"+this.searchTerm+"%'"
                       + " OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_SOVEREIGN_RATE]+" like '%"+this.searchTerm+"%'"
                       + " OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_LISTING] + " like '%"+this.searchTerm+"%'"
                    + ")";
            }else if(dataFor.equals("showlistsuratberharga")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause += " sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                }
                if(whereClause.length() > 0){
                    whereClause += " AND (sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_JENIS]+" LIKE '%"+this.searchTerm+"%')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(datafor.equals("listperlengkapandata")){
	    listData = PstSuratBerharga.listJoin(this.start, this.amount,whereClause,order);
	}else if (datafor.equals("listdatasuratberharga") || this.dataFor.equals("listdatasuratberhargahome")){
            listData = PstSuratBerharga.listJoin(this.start, this.amount, whereClause, order);
        }else if (datafor.equals("showlistsuratberharga")){
            listData = PstSuratBerharga.listJoin(this.start, this.amount, whereClause, order);
        }
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
            String buttonUpdate = "";
	    if(datafor.equals("listdatasearch")){
		suratBerharga = (SuratBerharga) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+suratBerharga.getCif());
                ja.put(""+suratBerharga.getNoRekening());
                ja.put(""+suratBerharga.getKodeJenis());
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+suratBerharga.getDebiturOid()+"&type="+suratBerharga.getDebiturType()+"&cif="+suratBerharga.getCif()+"&activetab=AjaxSuratBerharga&otheroid="+suratBerharga.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if (datafor.equals("listdatasuratberharga")){
                suratBerharga = (SuratBerharga) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+(suratBerharga.getNoRekening()));
                ja.put(""+(suratBerharga.getCif()));
                ja.put(""+(suratBerharga.getJenisSuratBerharga()));
                ja.put(""+(suratBerharga.getSovereignRate()));
                ja.put(""+(suratBerharga.getListing()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+suratBerharga.getDebiturOid()+"&type="+suratBerharga.getDebiturType()+"&cif="+suratBerharga.getCif()+"&activetab=AjaxSuratBerharga&otheroid="+suratBerharga.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                
                int count  = PstLogSysHistory.getCount(PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+suratBerharga.getOID()+"' "
                        + "AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" LIKE '%Transfer%'");
                
                if(count == 0){
                    buttonUpdate += "<div class='btn btn-default'><input type='checkbox' class='deletelist' value='"+suratBerharga.getOID()+"'></div>";
                }
                ja.put(buttonUpdate);
                
                array.put(ja);
            }else if (datafor.equals("listdatasuratberhargahome")){
                suratBerharga = (SuratBerharga) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+(suratBerharga.getNoRekening()));
                ja.put(""+(suratBerharga.getCif()));
                ja.put(""+(suratBerharga.getJenisSuratBerharga()));
                ja.put(""+(suratBerharga.getSovereignRate()));
                ja.put(""+(suratBerharga.getListing()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+suratBerharga.getDebiturOid()+"&type="+suratBerharga.getDebiturType()+"&cif="+suratBerharga.getCif()+"&activetab=AjaxSuratBerharga&otheroid="+suratBerharga.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                ja.put(buttonUpdate);
                
                array.put(ja);
            }else if(datafor.equals("showlistsuratberharga")){
		suratBerharga = (SuratBerharga) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+suratBerharga.getCif());
                ja.put(""+suratBerharga.getNoRekening());
                ja.put(""+suratBerharga.getKodeJenis());
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+suratBerharga.getDebiturOid()+"&type="+suratBerharga.getDebiturType()+"&cif="+suratBerharga.getCif()+"&activetab=AjaxSuratBerharga&otheroid="+suratBerharga.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
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
        
        if (cif.length()>0){
            String where = PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cif+"'";
            countData = PstSuratBerharga.getCount(where);
        }
        
        if (cif.length()==0){
            returnData = ""
            +"<div class='row'>"
                +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Surat Berharga</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                //+drawListSuratBerharga(iCommand, listSuratBerharga)
                                + "<div id='tableListSuratBerharga'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"
                                                + "<td>No Rekening</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Kode Jenis Surat Berharga</td>"
                                                + "<td>Kode Severeign Rate</td>"
                                                + "<td>Listing</td>"
                                                + "<td>Aksi</td>"
                                            + "</tr>"
                                        + "</thead>"
                                    + "</table>"
                                + "</div>"
                            +"</div>"
                             +"<div class='box-header'>";
            
                                if(privAdd){
                                    returnData +="<button type='button' class='btn btn-primary btnaddgeneralsuratberharga' data-oid='0' data-type='1' data-for ='showform2' data-target='AjaxSuratBerharga' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data Surat Berharga</button>" ;
                                }
                                
                                if(privDelete){
                                    returnData +=" <button type='button' class='btn btn-danger' id='delete' data-for='deleteitem' data-target='AjaxSuratBerharga' data-command='"+Command.DELETEALL+"'><i class='fa fa-trash'></i> Hapus Data Surat Berharga</button>";
                                }
                                
                                returnData+=""
                            +"</div>"
                        +"</div>"
                +"</div>"
            +"</div>"          
            + "<div id='suratberhargaform'></div>";       
        }else{
            if (countData>0){
                returnData = ""
                +"<div class='row'>"
                    +"<div class='col-md-12'>"
                            +"<div class='box'>"
                                +"<div class='box-header'>"
                                    +"<h3 class='box-title'>List Surat Berharga</h3>"
                                +"</div>"
                                +"<div class=\"box-body\">"
                                    //+drawListSuratBerharga(iCommand, listSuratBerharga)
                                    + "<div id='tableListSuratBerharga'>"
                                        + "<table class='table table-bordered table-striped'>"
                                            + "<thead>"
                                                + "<tr>"
                                                    + "<td>No</td>"
                                                    + "<td>No Rekening</td>"
                                                    + "<td>CIF</td>"
                                                    + "<td>Kode Jenis Surat Berharga</td>"
                                                    + "<td>Kode Severeign Rate</td>"
                                                    + "<td>Listing</td>"
                                                    + "<td>Aksi</td>"
                                                + "</tr>"
                                            + "</thead>"
                                        + "</table>"
                                    + "</div>"
                                +"</div>"
                                 +"<div class='box-header'>"
                                    +"<button type='button' class='btn btn-primary btnaddgeneralsuratberharga' data-oid='0' data-type='1' data-for ='showform2' data-target='AjaxSuratBerharga' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data Surat Berharga</button>"
                                +"</div>"
                            +"</div>"
                    +"</div>"
                +"</div>" ;         
                 
            }
        }
	return returnData;
    }
    
    public String showForm2(HttpServletRequest request){
        
        SuratBerharga suratBerharga = new SuratBerharga();
        suratBerharga.setCif(this.cif);
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        suratBerharga.setKodeKantorCabang(kodeCabang);
        Date dNow = new Date();
        String tglTerbit = "";
        String tglBeli = "";
        String tglJatuhTempo = "";
        String tglMacet = "";
        String tglKondisi = "";
        String openDate = "";
        long oidPeriodeWrite = 0;
        if (oid != 0) {
            try {
                suratBerharga = PstSuratBerharga.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            if (suratBerharga.getTglTerbit()==null){
                tglTerbit = "";
            }else{
                tglTerbit = Formater.formatDate(suratBerharga.getTglTerbit(), "yyyy-MM-dd");
            }
            if(suratBerharga.getTglBeli()==null){
                tglBeli = "";
            }else{
                tglBeli = Formater.formatDate(suratBerharga.getTglBeli(), "yyyy-MM-dd");
            }
            if (suratBerharga.getTglJatuhTempo()==null){
                tglJatuhTempo = "";
            }else{
                tglJatuhTempo = Formater.formatDate(suratBerharga.getTglJatuhTempo(), "yyyy-MM-dd");
            }
            if (suratBerharga.getTglMacet()==null){
                tglMacet = "";
            }else{
                tglMacet = Formater.formatDate(suratBerharga.getTglMacet(), "yyyy-MM-dd");
            }
            if (suratBerharga.getTglKondisi()==null){
                tglKondisi = "";
            }else{
                tglKondisi = Formater.formatDate(suratBerharga.getTglKondisi(), "yyyy-MM-dd");
            }
            
            if (suratBerharga.getOpenDate()==null){
                openDate = "";
            }else{
                openDate = Formater.formatDate(suratBerharga.getOpenDate(), "yyyy-MM-dd");
            }
            
            oidPeriodeWrite = suratBerharga.getPeriodeId();
        }
        else {
            tglTerbit = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglBeli = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglJatuhTempo = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglMacet = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglKondisi = Formater.formatDate(dNow, "yyyy-MM-dd");
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
            Vector listPeriode = PstPeriode.list(0, 1, " "+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            Periode entPeriode = (Periode) listPeriode.get(0);
            oidPeriodeWrite = entPeriode.getOID();
        }
        
        //tampilkan listIrrevocableLc berdasarkan cif
        Vector listSuratBerharga = new Vector();
        if(!cif.equals("")){
                String where = PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_CIF]+"='"+cif+"'";
                listSuratBerharga = PstSuratBerharga.list(0,0, ""+where, "");
        }
        
	String returnData = ""
        + "<input type='hidden' value='"+oidPeriodeWrite+"' name ='FRM_FIELD_PERIODE_ID' id='oidPeriode'>"
                  
                
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>View Surat Berharga</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                             + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_NO_REKENING]+"'></i> "
                            + "No. Rekening <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
                            + "<input type='hidden' class='form-control' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_STATUS_PERUBAHAN_DATA] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_STATUS_PERUBAHAN_DATA] +"' value='0'>";
                            
                            Vector kreditKey = new Vector(1,1);
                            Vector kreditVal = new Vector(1,1);
                            Vector listKredit = PstKredit.list(0, 0, ""+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+suratBerharga.getCif()+"' "
                                    + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+suratBerharga.getKodeKantorCabang()+"'", "");
                            
                            if(listKredit.size() > 0){
                                for(int i = 0; i < listKredit.size(); i++){
                                    Kredit kredit = (Kredit) listKredit.get(i);
                                    kreditKey.add(""+kredit.getNoRekening());
                                    kreditVal.add(""+kredit.getNoRekening());
                                }
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_NO_REKENING], "-- Pilih --", suratBerharga.getNoRekening(), kreditKey, kreditVal, "data-required='required' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_NO_REKENING]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_CIF]+"'></i> "
                            + "CIF <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_CIF] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_CIF] +"' value='"+suratBerharga.getCif()+"' data-required='required' data-number='true' data-alpha='true' data-special=\"\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_CIF]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_KODE_JENIS]+"'></i> "
                                + "Kode Jenis Surat Berharga <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdBerharga =  PstContentDataJenisSuratBerharga.listWithoutOid(0,0,"","");
                                Vector valueCdBerharga = new Vector();
                                Vector keyCdBerharga  = new Vector();
                                valueCdBerharga .add("");
                                keyCdBerharga .add("-- Pilih --");
                                for (int i = 0; i < vCdBerharga .size(); i++) {
                                    ContentDataJenisSuratBerharga KdBerharga  = (ContentDataJenisSuratBerharga) vCdBerharga .get(i);
                                    valueCdBerharga .add(String.valueOf(KdBerharga .getKodeOjk()));
                                    keyCdBerharga .add(""+KdBerharga .getJenisSuratBerharga());
                                }

                                returnData += ControlCombo.draw(""+FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KODE_JENIS], null, ""+suratBerharga.getKodeJenis(), valueCdBerharga , keyCdBerharga, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_KODE_JENIS]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_SOVEREIGN_RATE]+"'></i> "
                                + "Sovereign Rate <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_SOVEREIGN_RATE] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_SOVEREIGN_RATE] +"' value='"+suratBerharga.getSovereignRate()+"' data-number='true' data-alpha='true' data-special=\"+()-\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_SOVEREIGN_RATE]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_LISTING]+"'></i> "
                            + "Listing <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector listingKey = new Vector(1,1);
                            Vector listingVal = new Vector(1,1);
                            for(int i = 0; i < PstSuratBerharga.listingKey.length; i++){
                                listingKey.add(""+PstSuratBerharga.listingKey[i]);
                                listingVal.add(""+PstSuratBerharga.listingValue[i]);
                            }
                            returnData += ""
			    + ControlCombo.draw(FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_LISTING], "-- Pilih --", ""+suratBerharga.getListing(), listingKey, listingVal, "data-required='required' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_LISTING]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_PERINGKAT]+"'></i> "
                            + "Peringkat Surat Berharga <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_PERINGKAT] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_PERINGKAT] +"' value='"+suratBerharga.getPeringkat()+"' data-number='true' data-alpha='true' data-special=\"./+&@()'-\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_PERINGKAT]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_KODE_TUJUAN]+"'></i> "
                            + "Kode Tujuan Kepemilikan <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector tujuanKepemilikanKey = new Vector(1,1);
                            Vector tujuanKepemilikanVal = new Vector(1,1);
                            for(int i = 0; i < PstSuratBerharga.tujuanKepemilikanKey.length; i++){
                                tujuanKepemilikanKey.add(""+PstSuratBerharga.tujuanKepemilikanKey[i]);
                                tujuanKepemilikanVal.add(""+PstSuratBerharga.tujuanKepemilikanVal[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KODE_TUJUAN], "-- Pilih --", ""+suratBerharga.getKodeTujuan(), tujuanKepemilikanKey, tujuanKepemilikanVal, "data-required='required' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_KODE_TUJUAN]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_TGL_DEBIT]+"'></i> "
                            + "Tanggal Terbit <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_DEBIT] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_DEBIT] +"' value='"+tglTerbit+"' data-required='required' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_TGL_DEBIT]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_TGL_BELI]+"'></i> "
                            + "Tanngal Beli <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_BELI] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_BELI] +"' value='"+tglBeli+"' data-required='required' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_TGL_BELI]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_TGL_JATUH_TEMPO]+"'></i> "
                            + "Tanngal Jatuh Tempo <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_JATUH_TEMPO] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_JATUH_TEMPO] +"' value='"+tglJatuhTempo+"' data-required='required' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_TGL_JATUH_TEMPO]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_KODE_VALUTA]+"'></i> "
                            + "Kode Valuta <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdValuta =  PstContentDataKodeValuta.listWithoutOid(0,0,"","");
                                Vector valueCdValuta = new Vector();
                                Vector keyCdValuta = new Vector();
                                valueCdValuta.add("");
                                keyCdValuta.add("-- Pilih --");
                                for (int i = 0; i < vCdValuta.size(); i++) {
                                    ContentDataKodeValuta KdValuta = (ContentDataKodeValuta) vCdValuta.get(i);
                                    valueCdValuta.add(String.valueOf(KdValuta.getKodeOjk()));
                                    keyCdValuta.add(""+KdValuta.getNamaValuta());
                                }

                                returnData += ControlCombo.draw(""+FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KODE_VALUTA], null, ""+suratBerharga.getKodeValuta(), valueCdValuta, keyCdValuta, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_KODE_VALUTA]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_NOMINAL]+"'></i> "
                            + "Nominal <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_NOMINAL]+"' value='"+Formater.formatNumber(suratBerharga.getNominal(),"###,###,##0")+"' class='form-control numeric' data-required='required' data-number='true' data-alpha='false' data-special=\"\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_NOMINAL]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_NILAI_UANG_ASAL]+"'></i> "
                            + "Nilai Dalam Mata Uang Asal   <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_NILAI_UANG_ASAL] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_NILAI_UANG_ASAL] +"' value='"+Formater.formatNumber(suratBerharga.getNilaiUangAsal(),"###,###,##0")+"' data-number='true' data-alpha='false' data-special=\"\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_NILAI_UANG_ASAL]+"'>"
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>&nbsp;</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_NILAI_PASAR]+"'></i> "
                            + "Nilai Pasar  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_NILAI_PASAR] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_NILAI_PASAR] +"' value='"+Formater.formatNumber(suratBerharga.getNilaiPasar(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special=\"\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_NILAI_PASAR]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_NILAI_PEROLEHAN]+"'></i> "
                            + "Nilai Perolehan  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_NILAI_PEROLEHAN] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_NILAI_PEROLEHAN] +"' value='"+Formater.formatNumber(suratBerharga.getNilaiPerolehan(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special=\"\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_NILAI_PEROLEHAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_SUKU_BUNGA]+"'></i> "
                            + "Suku Bunga/Imbalan  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_SUKU_BUNGA] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_SUKU_BUNGA] +"' value='"+suratBerharga.getSukuBunga()+"' data-required='required' data-number='true' data-alpha='false' data-special=\",\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_SUKU_BUNGA]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_TUNGGAKAN]+"'></i> "
                            + "Tunggakan <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TUNGGAKAN] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TUNGGAKAN] +"'value='"+ Formater.formatNumber(suratBerharga.getTunggakan(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special=\"\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_TUNGGAKAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_JML_HARI_TUNGGAKAN]+"'></i> "
                            + "Jumlah Hari Tunggakan <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_JML_HARI_TUNGGAKAN] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_JML_HARI_TUNGGAKAN] +"' value='"+suratBerharga.getJmlHariTunggakan()+"' data-required='required' data-number='true' data-alpha='false' data-special=\"\" data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_JML_HARI_TUNGGAKAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_KODE_KOLEKTIBILITAS]+"'></i> "
                            + "Kode Kolektibilitas <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector listKodeKolektibilitas = PstContentDataKolektibilitas.listWitoutOid(0, 0, "", "");
                            Vector kolektibilitasKey = new Vector(1,1);
                            Vector kolektibilitasVal = new Vector(1,1);
                            
                            if(listKodeKolektibilitas.size() > 0){
                                for(int i = 0; i < listKodeKolektibilitas.size(); i++){
                                    ContentDataKolektibilitas contentDataKolektibilitas = (ContentDataKolektibilitas) listKodeKolektibilitas.get(i);
                                    kolektibilitasKey.add(""+contentDataKolektibilitas.getKodeOjk());
                                    kolektibilitasVal.add(""+contentDataKolektibilitas.getNamaKolektibilitas());
                                }
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KODE_KOLEKTIBILITAS], "-- Pilih --", ""+suratBerharga.getKodeKolektibilitas(), kolektibilitasKey, kolektibilitasVal, "data-required='required'  data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_KODE_KOLEKTIBILITAS]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_TGL_MACET]+"'></i> "
                            + "Tanggal Macet <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_MACET] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_MACET] +"' value='"+tglMacet+"' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_TGL_MACET]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_KODE_SEBAB_MACET]+"'></i> "
                            + "Kode Sebab Macet <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector listSebabMacet = PstContentDataSebabMacet.listWithoutOid(0, 0, "", "");
                            Vector sebabMacetKey = new Vector(1,1);
                            Vector sebabMacetVal = new Vector(1,1);
                            if(listSebabMacet.size() > 0){
                                for(int i = 0; i < listSebabMacet.size(); i++){
                                    ContentDataSebabMacet contentDataSebabMacet = (ContentDataSebabMacet) listSebabMacet.get(i);
                                    sebabMacetKey.add(""+contentDataSebabMacet.getKodeOjk());
                                    sebabMacetVal.add(""+contentDataSebabMacet.getSebabMacet());
                                }
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KODE_SEBAB_MACET], "-- Pilih --", ""+suratBerharga.getKodeSebabMacet(), sebabMacetKey, sebabMacetVal, " data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_KODE_SEBAB_MACET]+"'","form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_KODE_KONDISI]+"'></i> "
                            + "Kode Kondisi  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			   //start comboBox
                                Vector vCdKondisi =  PstContentDataKondisi.listTanpaOid(0,0,"","");
                                Vector valueCdKondisi = new Vector();
                                Vector keyCdKondisi = new Vector();
                                valueCdKondisi.add("");
                                keyCdKondisi.add("-- Pilih --");
                                for (int i = 0; i < vCdKondisi.size(); i++) {
                                    ContentDataKondisi KdKondisi = (ContentDataKondisi) vCdKondisi.get(i);
                                    valueCdKondisi.add(String.valueOf(KdKondisi.getKodeOjk()));
                                    keyCdKondisi.add(""+KdKondisi.getNamaKondisi());
                                }

                                returnData += ControlCombo.draw(""+FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KODE_KONDISI], null, ""+suratBerharga.getKodeKondisi(), valueCdKondisi, keyCdKondisi, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_KODE_KONDISI]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_TGL_KONDISI]+"'></i> "            
                            + "Tanggal Kondisi <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_KONDISI] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_TGL_KONDISI] +"' value='"+tglKondisi+"' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_TGL_KONDISI]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_KETERANGAN]+"'></i> "
                            + "Keterangan <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KETERANGAN] +"' id='"+ FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KETERANGAN] +"' value='"+suratBerharga.getKeterangan()+"' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_KETERANGAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmSuratBerharga.fieldQuestion[FrmSuratBerharga.FRM_FIELD_KODE_KANTOR_CABANG]+"'></i> "
                            + "Kode Kantor Cabang   <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			   //start comboBox
			    Vector vCdKantorCbg =  PstContentDataKantorCabang.listWithoutOid(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+suratBerharga.getKodeKantorCabang()+"'","");
                            ContentDataKantorCabang kdKantorCbg = new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKantorCbg.size(); i++) {
                                kdKantorCbg = (ContentDataKantorCabang) vCdKantorCbg.get(i);
                                
                            }
                          
                            returnData += "<input type='hidden' name='"+FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+suratBerharga.getKodeKantorCabang()+"'>"
                                    + "<input type='text' name='DISPLAY_"+FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+" "+kdKantorCbg.getNamaKantorCabang()+"' data-required='required' readonly='readonly' class='form-control' data-type='text' data-error-message='"+FrmSuratBerharga.fieldError[FrmSuratBerharga.FRM_FIELD_KODE_KANTOR_CABANG]+"'>";
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		     + "<div class='form-group hidden'>"
                        + "<input type='hidden' name='"+FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_STATUS_PERUBAHAN_DATA]+"' value='0'>"
			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'>*</font></label>"
			+ "<div class='col-sm-8'>";
                            Vector operasiDataKey = new Vector(1,1);
                            Vector operasiDataVal = new Vector(1,1);
                            for(int i = 0; i < PstDebitur.operasiData.length; i++){
                                operasiDataKey.add(""+PstDebitur.operasiDataKey[i]);
                                operasiDataVal.add(""+PstDebitur.operasiData[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmSuratBerharga.fieldNames[FrmSuratBerharga.FRM_FIELD_OPERASI_DATA], "-- Pilih --", ""+suratBerharga.getOperasiData(), operasiDataKey, operasiDataVal, "", "form-control")
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
	
	
	return returnData;
    }
    
    public String drawListSuratBerharga(int iCommand, Vector objectClass) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("No Rekening", "200px");//2
        ctrlist.addHeader("CIF", "200px");//10
        ctrlist.addHeader("Kode Jenis Surat Berharga", "200px");//10
        ctrlist.addHeader("Kode Sovereign Rate", "200px");//10
        ctrlist.addHeader("Listing", "200px");//10
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
            SuratBerharga suratBerharga = (SuratBerharga) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+suratBerharga.getNoRekening());//2
            rowx.add(""+suratBerharga.getCif());//3
            rowx.add(""+suratBerharga.getKodeJenis());//4
            rowx.add(""+suratBerharga.getSovereignRate());//4
            rowx.add(""+suratBerharga.getListing());//4
            rowx.add("<button type='button' class='btn btn-primary btnaddgeneral' data-oid='1' data-command='1'><i class='fa fa-plus'></i>View Data</button>");//4
            
            lstData.add(rowx);
        }

        return ctrlist.drawBootstrapStrip();
    }
    
    public String searchForm(HttpServletRequest request){
        String returnData = ""
        + "<div class='row'>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>CIF</label>"
                    + "<input type='text' name='cif' class='form-control' id='cif'>"
                    + "<input type='hidden' name='type' id='type' value='2'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>No Rekening</label>"
                    + "<input type='text' name='norekening' class='form-control' id='norekening'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Kode Jenis Surat Berharga</label>"
                    + "<input type='text' name='kodejenissuratberharga' class='form-control' id='kodejenissuratberharga'>"
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
                            + "<th>No Rekening</th>"
                            + "<th>Kode Jenis Surat Berharga</th>"
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
                        + "<th>No Rekening</th>"
                        + "<th>Kode Jenis Surat Berharga</th>"
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
        String whereClause = "";
        if(this.dataFor.equals("loadsummary")){
            whereClause = "sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+"='0'";
        }else{
            whereClause = "sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
        }
        
        if(userGroup != 4){
            whereClause += " AND sb."+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
        }
        
        int summary = PstSuratBerharga.getCountJoin(whereClause);
        returnData +=""+summary;
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        CtrlSuratBerharg ctrlSuratBerharg = new CtrlSuratBerharg(request);
        ctrlSuratBerharg.action(this.iCommand, this.oid, oidDelete);
        String msg = "<i class='fa fa-info'></i> "+ctrlSuratBerharg.getMessage();
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
}
