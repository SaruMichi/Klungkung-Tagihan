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
import com.dimata.dslik.entity.penjamin.Penjamin;
import com.dimata.dslik.entity.penjamin.PstPenjamin;
import com.dimata.dslik.form.contentdata.*;
import com.dimata.dslik.form.penjamin.*;
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
public class AjaxPenjamin extends HttpServlet {//DATATABLES
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
    private boolean privvDelete = false;
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
        this.privvDelete = FRMQueryString.requestBoolean(request, "privdelete");
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
        }else if (this.dataFor.equals("showlistpenjamin")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            this.htmlReturn = getSummary(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	 if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = savePenjamin(request);
        }
    }
    
    public String savePenjamin(HttpServletRequest request) {
        //history
        String returnData = "";
        CtrlPenjamin ctrlPenjamin = new CtrlPenjamin(request);
        oid = FRMQueryString.requestLong(request, "FRM_FIELD_PENJAMIN_OID");
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        ctrlPenjamin.action(iCommand, oid,userId,userName);
        returnData = ctrlPenjamin.getMessage();
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
                PstPenjamin.fieldNames[PstPenjamin.FLD_PENJAMIN_OID],
                PstPenjamin.fieldNames[PstPenjamin.FLD_CIF],
                PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN],
                PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
            String[] cols = { 
                PstPenjamin.fieldNames[PstPenjamin.FLD_PENJAMIN_OID],
                PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN],
                PstPenjamin.fieldNames[PstPenjamin.FLD_NO_REKENING],
                PstPenjamin.fieldNames[PstPenjamin.FLD_CIF],
                PstPenjamin.fieldNames[PstPenjamin.FLD_JENIS_IDENTITAS],
                PstPenjamin.fieldNames[PstPenjamin.FLD_NAMA_IDENTITAS],
                PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_GOL_PENJAMIN],
                PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]
            };
            jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }if(this.dataFor.equals("showlistpenjamin")){
	    String[] cols = { 
                PstPenjamin.fieldNames[PstPenjamin.FLD_PENJAMIN_OID],
                PstPenjamin.fieldNames[PstPenjamin.FLD_CIF],
                PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN],
                PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]
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
        String noidentitas = FRMQueryString.requestString(request, "noidentitas");
        String alamat = FRMQueryString.requestString(request, "alamat");
        String status = FRMQueryString.requestString(request, "status");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        String alias = "";
        
        if(dataFor.equals("listdatasearch") || dataFor.equals("showlistpenjamin")){
            alias = "penjamin.";
        }
        
        String whereClause = "";
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND "+alias+""+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " "+alias+""+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        
        if(dataFor.equals("listdatasearch")){
	    if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(noidentitas.length() > 0){
                if(whereClause.length() > 0){
                    whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+"='"+noidentitas+"'";
                }else{
                    whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+"='"+noidentitas+"'";
                }
            }

            if(alamat.length() > 0){
                if(whereClause.length() > 0){
                    whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+alamat+"%'";
                }else{
                    whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+alamat+"%'";
                }
            }
            
	    if(whereClause.length() > 0){
		whereClause += " AND (penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if (dataFor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
            whereClause = "1=1";
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause += ""
                + " AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cifParam+"'"
                + " AND (" 
                    + " "+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_JENIS_IDENTITAS]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_NAMA_IDENTITAS]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_GOL_PENJAMIN]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" like '%"+this.searchTerm+"%'"
                + ")";
        }else if(dataFor.equals("showlistpenjamin")){
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{   
                    whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }else{
                    whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }
            }
            
	    if(whereClause.length() > 0){
		whereClause += " AND (penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
	    }
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstPenjamin.getCountJoin(whereClause);
	}else if (dataFor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
            total = PstPenjamin.getCount(whereClause);
        }else if(dataFor.equals("showlistpenjamin")){
            total = PstPenjamin.getCountJoin(whereClause);
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
        Penjamin penjamin = new Penjamin();
	
	String cif = FRMQueryString.requestString(request, "cif");
        String noidentitas = FRMQueryString.requestString(request, "noidentitas");
        String alamat = FRMQueryString.requestString(request, "alamat");
        String whereClause = "";
        String order ="";
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        String alias = "";
        
        if(dataFor.equals("listdatasearch") || dataFor.equals("showlistpenjamin")){
            alias = "penjamin.";
        }
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND "+alias+""+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " "+alias+""+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(datafor.equals("listdatasearch")){
                if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(noidentitas.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+"='"+noidentitas+"'";
                    }else{
                        whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+"='"+noidentitas+"'";
                    }
                }

                if(alamat.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+alamat+"%'";
                    }else{
                        whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+alamat+"%'";
                    }
                }
		if(whereClause.length() > 0){
                    whereClause += " AND (penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if (dataFor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
                whereClause = "1=1";
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause += ""
                    + " AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cifParam+"'"
                    + " AND (" 
                        + " "+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_JENIS_IDENTITAS]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_NAMA_IDENTITAS]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_GOL_PENJAMIN]+" like '%"+this.searchTerm+"%'"
                        + " OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" like '%"+this.searchTerm+"%'"
                    + ")";
            }else if(status.equals("0")){
                if (dataFor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
                    if(whereClause.length() > 0){
                        whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause +=" "+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                }
            }else{
                if (dataFor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
                    if(whereClause.length() > 0){
                        whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause +=""+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause +=" AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause +=" penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                }
                
            }
            
	    if(whereClause.length() > 0){
                if (dataFor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
                    whereClause += " AND ("+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " AND (penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
                }
		
	    }else{
                if (dataFor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
                    whereClause += " ("+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                    + "OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                    + "OR "+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                    + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" LIKE '%"+this.searchTerm+"%' "
                    + "OR penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_ALAMAT_PENJAMIN]+" LIKE '%"+this.searchTerm+"%')";
                }
		
	    }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(datafor.equals("listdatasearch")){
	    listData = PstPenjamin.listJoin(start, amount, whereClause, order);
	}else if (datafor.equals("listdatapenjamin") || this.dataFor.equals("listdatapenjaminhome")){
            listData = PstPenjamin.list(start, amount, whereClause, order);
        }else if(datafor.equals("showlistpenjamin")){
	    listData = PstPenjamin.listJoin(start, amount, whereClause, order);
	}
       
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
            String buttonUpdate = "";
	    if(datafor.equals("listdatasearch")){
		penjamin = (Penjamin) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+penjamin.getCif());
                ja.put(""+(penjamin.getNoIdPenjamin()== null ? "-" : penjamin.getNoIdPenjamin()));
                ja.put(""+(penjamin.getAlamatPenjamin()== null ? "-" : penjamin.getAlamatPenjamin()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+penjamin.getDebiturOid()+"&type="+penjamin.getDebiturType()+"&cif="+penjamin.getCif()+"&activetab=AjaxPenjamin&otheroid="+penjamin.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if (datafor.equals("listdatapenjamin")){
                penjamin = (Penjamin) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+(penjamin.getNoIdPenjamin()));
                ja.put(""+(penjamin.getNoRekening()));
                ja.put(""+(penjamin.getCif()));
                ja.put(""+(penjamin.getJenisIdentitas()));
                ja.put(""+(penjamin.getNamaIdentitas()));
                ja.put(""+(penjamin.getKodeGolPenjamin()));
                ja.put(""+(penjamin.getAlamatPenjamin()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+penjamin.getDebiturOid()+"&type="+penjamin.getDebiturType()+"&cif="+penjamin.getCif()+"&activetab=AjaxPenjamin&otheroid="+penjamin.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                
                int count  = PstLogSysHistory.getCount(PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+penjamin.getOID()+"' "
                        + "AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" LIKE '%Transfer%'");
                
                if(count == 0){
                    buttonUpdate += "<div class='btn btn-default'><input type='checkbox' class='deletelist' value='"+penjamin.getOID()+"'></div>";
                }
                ja.put(buttonUpdate);
                
                array.put(ja);
            }else if (datafor.equals("listdatapenjaminhome")){
                penjamin = (Penjamin) listData.get(i); 
                long debiturOid = 0;
                int debiturType = 0;
                if(penjamin.getCif().length() > 0){
                    Vector listDebitur = PstDebitur.list(0, 0, ""+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+penjamin.getCif()+"'", "");
                    if(listDebitur.size() > 0){
                        Debitur debitur = (Debitur) listDebitur.get(0);
                        debiturOid = debitur.getOID();
                        debiturType = debitur.getKodeJenisNsb();
                    }
                }
                
		ja.put(""+(this.start+i+1));
                ja.put(""+(penjamin.getNoIdPenjamin()));
                ja.put(""+(penjamin.getNoRekening()));
                ja.put(""+(penjamin.getCif()));
                ja.put(""+(penjamin.getJenisIdentitas()));
                ja.put(""+(penjamin.getNamaIdentitas()));
                ja.put(""+(penjamin.getKodeGolPenjamin()));
                ja.put(""+(penjamin.getAlamatPenjamin()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+penjamin.getDebiturOid()+"&type="+penjamin.getDebiturType()+"&cif="+penjamin.getCif()+"&activetab=AjaxPenjamin&otheroid="+penjamin.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                ja.put(buttonUpdate);
                
                array.put(ja);
            }else if(datafor.equals("showlistpenjamin")){
		penjamin = (Penjamin) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+penjamin.getCif());
                ja.put(""+(penjamin.getNoIdPenjamin()== null ? "-" : penjamin.getNoIdPenjamin()));
                ja.put(""+(penjamin.getAlamatPenjamin()== null ? "-" : penjamin.getAlamatPenjamin()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+penjamin.getDebiturOid()+"&type="+penjamin.getDebiturType()+"&cif="+penjamin.getCif()+"&activetab=AjaxPenjamin&otheroid="+penjamin.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
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
    
    
    public String showForm2(HttpServletRequest request){
        
        Penjamin penjamin = new Penjamin();
        penjamin.setCif(this.cif);
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        penjamin.setKodeKantorCabang(kodeCabang);
        Date dNow = new Date();
        String openDate = "";
        long oidPeriodeWrite = 0;
        if(oid != 0){
             try {
                penjamin = PstPenjamin.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            if (penjamin.getOpenDate()==null){
                openDate = "";
            }else{
                openDate = Formater.formatDate(penjamin.getOpenDate(), "yyyy-MM-dd");
            }
            
            oidPeriodeWrite = penjamin.getPeriodeId();
            if(oidPeriodeWrite==0){
                Vector listPeriode = PstPeriode.list(0, 1, " "+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
                Periode entPeriode = (Periode) listPeriode.get(0);
                oidPeriodeWrite = entPeriode.getOID();
            }
        }
        else {
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
            Vector listPeriode = PstPeriode.list(0, 1, " "+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            Periode entPeriode = (Periode) listPeriode.get(0);
            oidPeriodeWrite = entPeriode.getOID();
        }
        
        //tampilkan listPenjamin berdasarkan cif
        Vector listPenjamin = new Vector();
        if(!cif.equals("")){
                String where = PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"'";
                listPenjamin = PstPenjamin.list(0,0, ""+where, "");
        }
        
	String returnData = ""
        + "<input type='hidden' value='"+oidPeriodeWrite+"' name ='FRM_FIELD_PERIODE_ID' id='oidPeriode'>"
                
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>View Penjamin</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_NO_ID_PENJAMIN]+"'></i> "
                            + "No Identitas Penjamin <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_NO_ID_PENJAMIN] +"' id='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_NO_ID_PENJAMIN] +"' value='"+penjamin.getNoIdPenjamin()+"' data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_NO_ID_PENJAMIN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_NO_REKENING]+"'></i> "
                            + "No. Rekening <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector kreditKey = new Vector(1,1);
                            Vector kreditVal = new Vector(1,1);
                            Vector listKredit = PstKredit.list(0, 0, ""+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+penjamin.getCif()+"' "
                                    + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+penjamin.getKodeKantorCabang()+"'"
                                    + " AND "+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+oidPeriodeWrite+"'", "");
                            
                            if(listKredit.size() > 0){
                                for(int i = 0; i < listKredit.size(); i++){
                                    Kredit kredit = (Kredit) listKredit.get(i);
                                    kreditKey.add(""+kredit.getNoRekening());
                                    kreditVal.add(""+kredit.getNoRekening());
                                }
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_NO_REKENING], "-- Pilih --", penjamin.getNoRekening(), kreditKey, kreditVal, "data-required='required' data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_NO_REKENING]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_CIF]+"'></i> "
                            + "CIF <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_CIF] +"' id='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_CIF] +"' value='"+penjamin.getCif()+"' data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_CIF]+"'>"
			+ "</div>"
		    + "</div>"
                    + "<div class='form-group'>"
                        + "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_KODE_JENIS_SEGMEN_FASILITAS]+"'></i> "
                            + "Kode Jenis Segmen Fasilitas  <i style='color:red'>m</i></label>"
                        + "<div class='col-sm-8'>";
                            
                            Vector listJenisFasilitas = PstContentDataJenisFasilitas.listWithoutOid(0, 0, "", "");
                            Vector jenisFasilitasKey = new Vector(1,1);
                            Vector jenisFasilitasVal = new Vector(1,1);
                            if(listJenisFasilitas.size() > 0){
                                for(int i = 0; i < listJenisFasilitas.size(); i++){
                                    ContentDataJenisFasilitas contentDataJenisFasilitas = (ContentDataJenisFasilitas) listJenisFasilitas.get(i);
                                    jenisFasilitasKey.add(""+contentDataJenisFasilitas.getKodeOjk());
                                    jenisFasilitasVal.add(""+contentDataJenisFasilitas.getJenisFasilitas());
                                }
                            }
                            returnData+=""
                            + ControlCombo.draw(FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_KODE_JENIS_SEGMEN_FASILITAS], "-- Pilih --", ""+penjamin.getKodeJenisSegmentFasilitas(), jenisFasilitasKey, jenisFasilitasVal, "data-required='required' data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_KODE_JENIS_SEGMEN_FASILITAS]+"'", "form-control")
                        + "</div>"
                    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_JENIS_IDENTITAS]+"'></i> "
                            + "Jenis Identitas  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector listJenisIdentitas = PstContentDataJenisIdentitas.listWithoutOid(0, 0, "", "");
                            Vector jenisIdentitasKey = new Vector(1,1);
                            Vector jenisIdentitasVal = new Vector(1,1);
                            if(listJenisIdentitas.size() > 0){
                                for(int i = 0; i < listJenisIdentitas.size(); i++){
                                    ContentDataJenisIdentitas contentDataJenisIdentitas = (ContentDataJenisIdentitas) listJenisIdentitas.get(i);
                                    jenisIdentitasKey.add(""+contentDataJenisIdentitas.getKodeOjk());
                                    jenisIdentitasVal.add(""+contentDataJenisIdentitas.getNamaIdentitas());
                                }
                            }
                            returnData += ""
			    + ControlCombo.draw(FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_JENIS_IDENTITAS], "-- Pilih --", ""+penjamin.getJenisIdentitas(), jenisIdentitasKey, jenisIdentitasVal, "data-required='required' data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_JENIS_IDENTITAS]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_NAMA_IDENTITAS]+"'></i> "
                            + "Nama Sesuai Identitas/Passport  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_NAMA_IDENTITAS] +"' id='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_NAMA_IDENTITAS] +"' value='"+penjamin.getNamaIdentitas()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" &',.()-\" data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_NAMA_IDENTITAS]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_NAMA_LENGKAP]+"'></i> "
                            + "Nama Lengkap <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_NAMA_LENGKAP] +"' id='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_NAMA_LENGKAP] +"' value='"+penjamin.getNamaLengkap()+"' data-number='true' data-alpha='true' data-special=\" &',.()-\" data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_NAMA_LENGKAP]+"'>"
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>&nbsp;</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_KODE_GOL_PENJAMIN]+"'></i> "
                            + "Kode Golongan Penjamin   <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector listGolonganPenjamin = PstContentDataGolonganDebitur.listWithoutOid(0, 0, "", "");
                            Vector golonganPenjaminKey = new Vector(1,1);
                            Vector golonganPenjaminVal = new Vector(1,1);
                            if(listGolonganPenjamin.size() > 0){
                                for(int i = 0; i < listGolonganPenjamin.size(); i++){
                                    ContentDataGolonganDebitur contentDataGolonganDebitur = (ContentDataGolonganDebitur) listGolonganPenjamin.get(i);
                                    golonganPenjaminKey.add(""+contentDataGolonganDebitur.getKodeOjk());
                                    golonganPenjaminVal.add(""+contentDataGolonganDebitur.getGolonganDebitur());
                                }
                            }
                            returnData +=""
			    + ControlCombo.draw(FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_KODE_GOL_PENJAMIN], "-- Pilih --", ""+penjamin.getKodeGolPenjamin(), golonganPenjaminKey, golonganPenjaminVal, "data-required='required' data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_KODE_GOL_PENJAMIN]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_ALAMAT_PENJAMIN]+"'></i> "
                            + "Alamat Penjamin  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_ALAMAT_PENJAMIN] +"' id='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_ALAMAT_PENJAMIN] +"' value='"+penjamin.getAlamatPenjamin()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" ./&,()'-\" data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_ALAMAT_PENJAMIN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_PROSENTASE_DIJAMIN]+"'></i> "
                            + "Prosentase Penjamin <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_PROSENTASE_DIJAMIN] +"' id='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_PROSENTASE_DIJAMIN] +"' value='"+penjamin.getProsentaseDijamin()+"' data-number='true' data-alpha='false' data-special=',' data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_PROSENTASE_DIJAMIN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_KETERANGAN]+"'></i> "
                            + "Keterangan <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_KETERANGAN] +"' id='"+ FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_KETERANGAN] +"' value='"+penjamin.getKeterangan()+"' data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_KETERANGAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPenjamin.fieldQuestion[FrmPenjamin.FRM_FIELD_KODE_KANTOR_CABANG]+"'></i> "
                            + "Kode Kantor Cabang  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			   //start comboBox
			    Vector vCdKantorCbg =  PstContentDataKantorCabang.listWithoutOid(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+penjamin.getKodeKantorCabang()+"'","");
                             ContentDataKantorCabang kdKantorCbg = new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKantorCbg.size(); i++) {
                                kdKantorCbg = (ContentDataKantorCabang) vCdKantorCbg.get(i);
                            }
                          
                            returnData += "<input type='hidden' name='"+FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+"'>"
                                    + "<input type='text' name='DISPLAY_"+FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+" "+kdKantorCbg.getNamaKantorCabang()+"' data-required='required' readonly='readonly' class='form-control'  data-type='text' data-error-message='"+FrmPenjamin.fieldError[FrmPenjamin.FRM_FIELD_KODE_KANTOR_CABANG]+"'>";
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group hidden'>"
                        + "<input type='hidden' name='"+FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_STATUS_PERUBAHAN_DATA]+"' value='0'>"
			+ "<label class='control-label col-sm-4'>Operasi Data <font color='red'>*</font></label>"
			+ "<div class='col-sm-8'>";
                            Vector operasiDataKey = new Vector(1,1);
                            Vector operasiDataVal = new Vector(1,1);
                            for(int i = 0; i < PstPenjamin.operasiDataKey.length; i++){
                                operasiDataKey.add(""+PstPenjamin.operasiDataKey[i]);
                                operasiDataVal.add(""+PstPenjamin.operasiDataValue[i]);
                            }
                            returnData += ""
			    + ControlCombo.draw(FrmPenjamin.fieldNames[FrmPenjamin.FRM_FIELD_OPERASI_DATA], "-- Pilih --", ""+penjamin.getOperasiData(), operasiDataKey, operasiDataVal, "", "form-control")
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
	
	
	return returnData;
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
            String where = PstPenjamin.fieldNames[PstPenjamin.FLD_CIF]+"='"+cif+"'";
            countData = PstPenjamin.getCount(where);
        }
	
        if (cif.length()==0){
            returnData = ""
            +"<div class='row'>"
                +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Penjamin</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                + "<div id='tableListPenjamin'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"
                                                + "<td>No Identitas Penjamin</td>"
                                                + "<td>No Rekening</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Jenis Identitas</td>"
                                                + "<td>Nama Pemilik Agunan</td>"
                                                + "<td>Kode Golongan Penjamin</td>"
                                                + "<td>Alamat Penjamin</td>"
                                                + "<td>Aksi</td>"
                                            + "</tr>"
                                        + "</thead>"
                                    + "</table>"
                                + "</div>"
                                    //+drawListPenjamin(iCommand, listPenjamin)
                            +"</div>"
                             +"<div class='box-header'>";
                                if(privAdd){
                                    returnData += "<button type='button' class='btn btn-primary btnaddgeneralpenjamin' data-for ='showform2' data-target='AjaxPenjamin' data-command='"+Command.NONE+"' data-oid='0'><i class='fa fa-plus'></i> Tambah Data Penjamin</button>";
                                }
                                if(privvDelete){
                                    returnData += " <button type='button' class='btn btn-danger' id='delete' data-for='deleteitem' data-target='AjaxPenjamin' data-command='"+Command.DELETEALL+"'><i class='fa fa-trash'></i> Hapus Data Penjamin</button>";
                                }
                                
                            returnData+=""
                            +"</div>"
                        +"</div>"
                +"</div>"
            +"</div>"
            + "<div id='penjaminform'></div>";   
        }else{
            if (countData>0){
                returnData = ""
                +"<div class='row'>"
                    +"<div class='col-md-12'>"
                            +"<div class='box'>"
                                +"<div class='box-header'>"
                                    +"<h3 class='box-title'>List Penjamin</h3>"
                                +"</div>"
                                +"<div class=\"box-body\">"
                                    + "<div id='tableListPenjamin'>"
                                        + "<table class='table table-bordered table-striped'>"
                                            + "<thead>"
                                                + "<tr>"
                                                    + "<td>No</td>"
                                                    + "<td>No Identitas Penjamin</td>"
                                                    + "<td>No Rekening</td>"
                                                    + "<td>CIF</td>"
                                                    + "<td>Jenis Identitas</td>"
                                                    + "<td>Nama Pemilik Agunan</td>"
                                                    + "<td>Kode Golongan Penjamin</td>"
                                                    + "<td>Alamat Penjamin</td>"
                                                    + "<td>Aksi</td>"
                                                + "</tr>"
                                            + "</thead>"
                                        + "</table>"
                                    + "</div>"
                                        //+drawListPenjamin(iCommand, listPenjamin)
                                +"</div>"
                            +"</div>"
                    +"</div>"
                +"</div>";
                
            }
        }
	return returnData;
    }
    
    public String drawListPenjamin(int iCommand, Vector objectClass) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("No Identitas Penjamin", "200px");//2
        ctrlist.addHeader("No Rekening", "200px");//2
        ctrlist.addHeader("CIF", "200px");//10
        ctrlist.addHeader("Jenis Identitas", "200px");//10
        ctrlist.addHeader("Nama Pemilik Agunan", "200px");//10
        ctrlist.addHeader("Kode Golongan Penjamin", "200px");//10
        ctrlist.addHeader("Alamat Penjamin", "200px");//10
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
            Agunan agunan = new Agunan();
            Penjamin penjamin = (Penjamin) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+penjamin.getNoIdPenjamin());//2
            rowx.add(""+penjamin.getNoRekening());//3
            rowx.add(""+penjamin.getCif());//4
            rowx.add(""+penjamin.getJenisIdentitas());//5
            rowx.add(""+agunan.getNamaPemilikAgunan());//6
            rowx.add(""+penjamin.getKodeGolPenjamin());//7
            rowx.add(""+penjamin.getAlamatPenjamin());//8
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
                    + "<label>No Identitas Penjamin</label>"
                    + "<input type='text' name='noidentitas' class='form-control' id='noidentitas'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Alamat Penjamin</label>"
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
                            + "<th>No Identitas Penjamin</th>"
                            + "<th>Alamat Penjamin</th>"
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
                        + "<th>No Identitas Penjamin</th>"
                        + "<th>Alamat Penjamin</th>"
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
            whereClause = "penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='0'";
        }else{
            whereClause = "penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
        }
        
        if(userGroup != 4){
            whereClause += " AND penjamin."+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
        }
        
        int summary = PstPenjamin.getCountJoin(whereClause);
        returnData +=""+summary;
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        CtrlPenjamin ctrlPenjamin = new CtrlPenjamin(request);
        ctrlPenjamin.action(this.iCommand, this.oid, oidDelete);
        String msg = "<i class='fa fa-info'></i> "+ctrlPenjamin.getMessage();
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
