/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.irrevocablelc.IrrevocableLc;
import com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.form.contentdata.*;
import com.dimata.dslik.form.irrevocablelc.*;
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
public class AjaxIrrevocable extends HttpServlet {

    //DATATABLES
    private String searchTerm="";
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
    private boolean privDelete = false;
    private boolean privUpdate = false;
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
	//INT
	this.iCommand = FRMQueryString.requestCommand(request);
	this.iErrCode = 0;
        
        //BOOLEAN
        this.privAdd = FRMQueryString.requestBoolean(request, "privadd");
        this.privUpdate = FRMQueryString.requestBoolean(request, "privupdate");
        this.privDelete = FRMQueryString.requestBoolean(request, "privdelete");
	this.privView = FRMQueryString.requestBoolean(request, "privview");
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
        }else if (this.dataFor.equals("showlistirrevocable")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            this.htmlReturn = getSummary(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = saveIrrevocableLc(request);
        }
    }
    
    public String saveIrrevocableLc(HttpServletRequest request) {
        //history
        String returnData = "";
        oid = FRMQueryString.requestLong(request, "FRM_FIELD_IRREVOCABLE_OID");
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        CtrlIrrevocableLc ctrlIrrevocableLc = new CtrlIrrevocableLc(request);
        ctrlIrrevocableLc.action(iCommand, oid,userId,userName);
        returnData = ctrlIrrevocableLc.getMessage();
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
                PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_IRREVOCABLE_LC_OID],
                PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF],
                PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING],
                PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("listdatairrevocable") || this.dataFor.equals("listdatairrevocablehome")){
            String[] cols = { 
                "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_IRREVOCABLE_LC_OID]+"",
                "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+"",
                "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"",
                "jlc."+PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_NAMA_LC]+"",
                "tlc."+PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]+"",
                "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_AKAD_AWAL]+""   
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }else if(this.dataFor.equals("showlistirrevocable")){
            String[] cols = { 
                "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_IRREVOCABLE_LC_OID]+"",
                "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+"",
                "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"",
                "jlc."+PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_NAMA_LC]+"",
                "tlc."+PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]+"",
                "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_AKAD_AWAL]+""   
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
        String kodeJenisLc = FRMQueryString.requestString(request, "kodejenislc");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        String whereClause = "";
        
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        if(dataFor.equals("listdatasearch")){
	    if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(noRekening.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+"='"+noRekening+"'";
                }else{
                    whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+"='"+noRekening+"'";
                }
            }

            if(kodeJenisLc.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+"='"+kodeJenisLc+"'";
                }else{
                    whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+"='"+kodeJenisLc+"'";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+" LIKE '"+this.searchTerm+"' "
                        + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" LIKE '"+this.searchTerm+"' "
                        + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+" LIKE '"+this.searchTerm+"')";
	    }else{
		whereClause += " (ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+" LIKE '"+this.searchTerm+"' "
                        + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" LIKE '"+this.searchTerm+"' "
                        + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+" LIKE '"+this.searchTerm+"')";
	    }
	}else if (dataFor.equals("listdatairrevocable") || this.dataFor.equals("listdatairrevocablehome")){
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause = " 1=1";
            whereClause += " "
                + " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cifParam+"'"
                + " AND ("
                    + " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                    + " OR jlc."+PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_NAMA_LC]+" like '%"+this.searchTerm+"%'"
                    + " OR tlc."+PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]+" like '%"+this.searchTerm+"%'"
                    + " OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_AKAD_AWAL]+" like '%"+this.searchTerm+"%'"
                + " )";
        }else if(dataFor.equals("showlistirrevocable")){
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{
                    whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }else{
                    whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+" LIKE '"+this.searchTerm+"' "
                        + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" LIKE '"+this.searchTerm+"' "
                        + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+" LIKE '"+this.searchTerm+"')";
	    }else{
		whereClause += " (ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+" LIKE '"+this.searchTerm+"' "
                        + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" LIKE '"+this.searchTerm+"' "
                        + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+" LIKE '"+this.searchTerm+"')";
	    }
        } 
        
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstIrrevocableLc.getCountJoin(whereClause);
	}else if (dataFor.equals("listdatairrevocable") || this.dataFor.equals("listdatairrevocablehome")){
            total = PstIrrevocableLc.getCountJoin(whereClause);
        }else if(dataFor.equals("showlistirrevocable")){
            total = PstIrrevocableLc.getCountJoin(whereClause);
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
        IrrevocableLc irrevocableLc = new IrrevocableLc();
	
        approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
	String cif = FRMQueryString.requestString(request, "cif");
        String noRekening = FRMQueryString.requestString(request, "norekening");
        String kodeJenisLc = FRMQueryString.requestString(request, "kodejenislc");
        String order ="";
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
        String whereClause = "";
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(dataFor.equals("listdatasearch")){
	    	if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(noRekening.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+"='"+noRekening+"'";
                    }else{
                        whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+"='"+noRekening+"'";
                    }
                }

                if(kodeJenisLc.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+"='"+kodeJenisLc+"'";
                    }else{
                        whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+"='"+kodeJenisLc+"'";
                    }
                }
                if(whereClause.length() > 0){
                    whereClause += " AND (ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+" LIKE '"+this.searchTerm+"' "
                            + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" LIKE '"+this.searchTerm+"' "
                            + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+" LIKE '"+this.searchTerm+"')";
                }else{
                    whereClause += " (ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+" LIKE '"+this.searchTerm+"' "
                            + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" LIKE '"+this.searchTerm+"' "
                            + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+" LIKE '"+this.searchTerm+"')";
                }
            }else if (dataFor.equals("listdatairrevocable") || this.dataFor.equals("listdatairrevocablehome")){
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause = " 1=1";
                whereClause += " "
                    + " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cifParam+"'"
                    + " AND ("
                        + " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                        + " OR jlc."+PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_NAMA_LC]+" like '%"+this.searchTerm+"%'"
                        + " OR tlc."+PstContentDataTujuanLc.fieldNames[PstContentDataTujuanLc.FLD_NAMA_TUJUAN_LC]+" like '%"+this.searchTerm+"%'"
                        + " OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_AKAD_AWAL]+" like '%"+this.searchTerm+"%'"
                    + " )";
            }else if(dataFor.equals("showlistirrevocable")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause += " ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                }
                if(whereClause.length() > 0){
                    whereClause += " AND (ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+" LIKE '"+this.searchTerm+"' "
                            + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" LIKE '"+this.searchTerm+"' "
                            + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+" LIKE '"+this.searchTerm+"')";
                }else{
                    whereClause += " (ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+" LIKE '"+this.searchTerm+"' "
                            + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" LIKE '"+this.searchTerm+"' "
                            + "OR ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_JENIS]+" LIKE '"+this.searchTerm+"')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(datafor.equals("listdatasearch")){
	    listData = PstIrrevocableLc.listJoin(start, amount, whereClause, order);
	}else if (datafor.equals("listdatairrevocable") || this.dataFor.equals("listdatairrevocablehome")){
            listData = PstIrrevocableLc.listJoin(start, colOrder, whereClause, order);
        }else if (datafor.equals("showlistirrevocable")){
            listData = PstIrrevocableLc.listJoin(start, colOrder, whereClause, order);
        }
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
            
            String buttonUpdate = "";
	    JSONArray ja = new JSONArray();
	    if(datafor.equals("listdatasearch")){
		irrevocableLc = (IrrevocableLc) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+irrevocableLc.getCif());
                ja.put(""+irrevocableLc.getNoRekening());
                ja.put(""+irrevocableLc.getKodeJenis());
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+irrevocableLc.getDebiturOid()+"&type="+irrevocableLc.getDebiturType()+"&cif="+irrevocableLc.getCif()+"&activetab=AjaxIrrevocable&otheroid="+irrevocableLc.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if (datafor.equals("listdatairrevocable")){
                irrevocableLc = (IrrevocableLc) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(irrevocableLc.getNoRekening()));
                ja.put(""+(irrevocableLc.getCif()));
                ja.put(""+(irrevocableLc.getNamaJenisLc()));
                ja.put(""+(irrevocableLc.getNamaTujuanLc()));
                ja.put(""+(irrevocableLc.getNoAkadAwal()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+irrevocableLc.getDebiturOid()+"&type="+irrevocableLc.getDebiturType()+"&cif="+irrevocableLc.getCif()+"&activetab=AjaxIrrevocable&otheroid="+irrevocableLc.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                
                int count  = PstLogSysHistory.getCount(PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+irrevocableLc.getOID()+"' "
                        + "AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" LIKE '%Transfer%'");
                
                if(count == 0){
                    buttonUpdate += "<div class='btn btn-default'><input type='checkbox' class='deletelist' value='"+irrevocableLc.getOID()+"'></div>";
                }
                ja.put(buttonUpdate);
                array.put(ja);
            }else if (datafor.equals("listdatairrevocablehome")){
                irrevocableLc = (IrrevocableLc) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(irrevocableLc.getNoRekening()));
                ja.put(""+(irrevocableLc.getCif()));
                ja.put(""+(irrevocableLc.getNamaJenisLc()));
                ja.put(""+(irrevocableLc.getNamaTujuanLc()));
                ja.put(""+(irrevocableLc.getNoAkadAwal()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+irrevocableLc.getDebiturOid()+"&type="+irrevocableLc.getDebiturType()+"&cif="+irrevocableLc.getCif()+"&activetab=AjaxIrrevocable&otheroid="+irrevocableLc.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                ja.put(buttonUpdate);
                array.put(ja);
            }else if(datafor.equals("showlistirrevocable")){
		irrevocableLc = (IrrevocableLc) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+irrevocableLc.getCif());
                ja.put(""+irrevocableLc.getNoRekening());
                ja.put(""+irrevocableLc.getKodeJenis());
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+irrevocableLc.getDebiturOid()+"&type="+irrevocableLc.getDebiturType()+"&cif="+irrevocableLc.getCif()+"&activetab=AjaxIrrevocable&otheroid="+irrevocableLc.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
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
            String where = PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cif+"'";
            countData = PstIrrevocableLc.getCount(where);
        }
	
        if (cif.length()==0){
            returnData = ""
            +"<div class='row'>"
                +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Irrevocable L/C</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                //+drawListIrrevocableLc(iCommand, listIrrevocableLc)
                                + "<div id='tableIrrevocable'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"                                           
                                                + "<td>No Rekening</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Kode Jenis Lc</td>"
                                                + "<td>Kode Tujuan Lc</td>"
                                                + "<td>No Akad Awal</td>"                                         
                                                + "<td>Aksi</td>"
                                            + "</tr>"
                                        + "</thead>"
                                    + "</table>"
                                + "</div>"
                            +"</div>"
                             +"<div class='box-header'>";
            
                                if(privAdd){
                                    returnData+="<button type='button' class='btn btn-primary btnaddgenerairrevocable' data-oid='0' data-type='0' data-for ='showform2' data-target='AjaxIrrevocable' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data  Irrevocable L/C</button>";
                                }
                                if(privDelete){
                                    returnData+=" <button type='button' class='btn btn-danger' id='delete' data-for='deleteitem' data-target='AjaxIrrevocable' data-command='"+Command.DELETEALL+"'><i class='fa fa-trash'></i> Hapus Data Irrevocable L/C</button>";
                                }
                                
                                returnData+=""
                            +"</div>"
                        +"</div>"
                +"</div>"
            +"</div>"
            + "<div id='irrevocableform'></div>";        
        }else{
            if (countData>0){
                returnData = ""
                +"<div class='row'>"
                    +"<div class='col-md-12'>"
                            +"<div class='box'>"
                                +"<div class='box-header'>"
                                    +"<h3 class='box-title'>List Irrevocable L/C</h3>"
                                +"</div>"
                                +"<div class=\"box-body\">"
                                    //+drawListIrrevocableLc(iCommand, listIrrevocableLc)
                                    + "<div id='tableIrrevocable'>"
                                        + "<table class='table table-bordered table-striped'>"
                                            + "<thead>"
                                                + "<tr>"
                                                    + "<td>No</td>"                                           
                                                    + "<td>No Rekening</td>"
                                                    + "<td>CIF</td>"
                                                    + "<td>Kode Jenis Lc</td>"
                                                    + "<td>Kode Tujuan Lc</td>"
                                                    + "<td>No Akad Awal</td>"                                         
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
        
        IrrevocableLc irrevocableLc = new IrrevocableLc();
        irrevocableLc.setCif(this.cif);
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        irrevocableLc.setKodeKantorCabang(kodeCabang);
        Date dNow = new Date();
        String tglKeluar = "";
        String tglTempo = "";
        String tglAkadAwal = "";
        String tglAkadAkhir = "";
        String tglWanPrestasi = "";
        String tglKondisi = "";
        String openDate = "";
        long oidPeriodeWrite = 0;
        if (oid != 0) {
            try {
                irrevocableLc = PstIrrevocableLc.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            
            if (irrevocableLc.getTglKeluar()==null){
                tglKeluar = "";
            }else{
                tglKeluar = Formater.formatDate(irrevocableLc.getTglKeluar(), "yyyy-MM-dd");
            }
            if (irrevocableLc.getTglJatuhTempo()==null){
                tglTempo = "";
            }else{
                tglTempo = Formater.formatDate(irrevocableLc.getTglJatuhTempo(), "yyyy-MM-dd");
            }
            if (irrevocableLc.getTglAkadAwal()==null){
                tglAkadAwal = "";
            }else{
                tglAkadAwal = Formater.formatDate(irrevocableLc.getTglAkadAwal(), "yyyy-MM-dd");
            }
            
            if (irrevocableLc.getTglAkadAkhir()==null){
                tglAkadAkhir = "";
            }else{
                tglAkadAkhir = Formater.formatDate(irrevocableLc.getTglAkadAkhir(), "yyyy-MM-dd");
            }
            if (irrevocableLc.getTglWanPrestasi()==null){
                tglWanPrestasi = "";
            }else{
                tglWanPrestasi = Formater.formatDate(irrevocableLc.getTglWanPrestasi(), "yyyy-MM-dd");
            }
            if (irrevocableLc.getTglKondisi()==null){
                tglKondisi = "";
            }else{
                tglKondisi = Formater.formatDate(irrevocableLc.getTglKondisi(), "yyyy-MM-dd");
            }
            
            if (irrevocableLc.getOpenDate()==null){
                openDate = "";
            }else{
                openDate = Formater.formatDate(irrevocableLc.getOpenDate(), "yyyy-MM-dd");
            }
            
            oidPeriodeWrite = irrevocableLc.getPeriodeId();
        }
        else {
            tglKeluar = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglTempo = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglAkadAwal = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglAkadAkhir = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglWanPrestasi = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglKondisi = Formater.formatDate(dNow, "yyyy-MM-dd");
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
            Vector listPeriode = PstPeriode.list(0, 1, " "+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            Periode entPeriode = (Periode) listPeriode.get(0);
            oidPeriodeWrite = entPeriode.getOID();
        }
        
        //tampilkan listIrrevocableLc berdasarkan cif
        Vector listIrrevocableLc = new Vector();
        if(!cif.equals("")){
                String where = PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_CIF]+"='"+cif+"'";
                listIrrevocableLc = PstIrrevocableLc.list(0,0, ""+where, "");
        }
        
	String returnData = ""
        + "<input type='hidden' value='"+oidPeriodeWrite+"' name ='FRM_FIELD_PERIODE_ID' id='oidPeriode'>"
              
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>View  Irrevocable L/C</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_NO_REKENING]+"'></i> "
                        + "No. Rekening <i style='color:red;'>m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector kreditKey = new Vector(1,1);
                            Vector kreditVal = new Vector(1,1);
                            Vector listKredit = PstKredit.list(0, 0, ""+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+irrevocableLc.getCif()+"' "
                                    + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+irrevocableLc.getKodeKantorCabang()+"'", "");
                            
                            if(listKredit.size() > 0){
                                for(int i = 0; i < listKredit.size(); i++){
                                    Kredit kredit = (Kredit) listKredit.get(i);
                                    kreditKey.add(""+kredit.getNoRekening());
                                    kreditVal.add(""+kredit.getNoRekening());
                                }
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_NO_REKENING], "-- Pilih --", irrevocableLc.getNoRekening(), kreditKey, kreditVal, "data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_NO_REKENING]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_CIF]+"'></i> "
			+ "CIF <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_CIF] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_CIF] +"' value='"+irrevocableLc.getCif()+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_CIF]+"' data-type='text' data-number='true' data-alpha='true' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_KODE_JENIS]+"'></i> "
			+ "Kode Jenis LC <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>";
			    //start comboBox
			    Vector vCdJenisLc =  PstContentDataJenisLc.listWithoutOid(0,0,"","");
                            Vector valueCdJenisLc = new Vector();
                            Vector keyCdJenisLc = new Vector();
                            valueCdJenisLc.add("");
                            keyCdJenisLc.add("-- Pilih --");
                            for (int i = 0; i < vCdJenisLc.size(); i++) {
                                ContentDataJenisLc KdJenisLc = (ContentDataJenisLc) vCdJenisLc.get(i);
                                valueCdJenisLc.add(String.valueOf(KdJenisLc.getKodeOjk()));
                                keyCdJenisLc.add(""+KdJenisLc.getNamaLc());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KODE_JENIS], null, ""+irrevocableLc.getKodeJenis(), valueCdJenisLc, keyCdJenisLc, "data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_KODE_JENIS]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_KODE_TUJUAN]+"'></i> "
			+ "Kode Tujuan LC <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>";
                           //start comboBox
			    Vector vCdTujuanLc =  PstContentDataTujuanLc.listWitoutOid(0,0,"","");
                            Vector valueCdTujuanLc = new Vector();
                            Vector keyCdTujuanLc = new Vector();
                            valueCdTujuanLc.add("");
                            keyCdTujuanLc.add("-- Pilih --");
                            for (int i = 0; i < vCdTujuanLc.size(); i++) {
                                ContentDataTujuanLc KdTujuanLc = (ContentDataTujuanLc) vCdTujuanLc.get(i);
                                valueCdTujuanLc.add(String.valueOf(KdTujuanLc.getKodeOjk()));
                                keyCdTujuanLc.add(""+KdTujuanLc.getNamaTujuanLc());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KODE_TUJUAN], null, ""+irrevocableLc.getKodeTujuan(), valueCdTujuanLc, keyCdTujuanLc, "data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_KODE_TUJUAN]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_TGL_KELUAR]+"'></i> "
			+ "Tanggal Keluar <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_KELUAR] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_KELUAR] +"' value='"+tglKeluar+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_TGL_KELUAR]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_TGL_JATUH_TEMPO]+"'></i> "
			+ "Tanggal Jatuh Tempo <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_JATUH_TEMPO] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_JATUH_TEMPO] +"' value='"+tglTempo+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_TGL_JATUH_TEMPO]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_NO_AKAD_AWAL]+"'></i> "
			+ "No. Akad Awal <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_NO_AKAD_AWAL] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_NO_AKAD_AWAL] +"' value='"+irrevocableLc.getNoAkadAwal()+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_NO_AKAD_AWAL]+"' data-type='text' data-number='true' data-alpha='true' data-special=\" _/()'.-\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_TGL_AKAD_AWAL]+"'></i> "
			+ "Tanggal Akad Awal <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_AKAD_AWAL] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_AKAD_AWAL] +"' value='"+tglAkadAwal+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_TGL_AKAD_AWAL]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_NO_AKAD_AKHR]+"'></i> "
			+ "No. Akad Akhir <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_NO_AKAD_AKHR] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_NO_AKAD_AKHR] +"' value='"+irrevocableLc.getNoAkadAkhr()+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_NO_AKAD_AKHR]+"' data-type='text' data-number='true' data-alpha='true' data-special=\" _/()'.-\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_TGL_AKAD_AKHIR]+"'></i> "
			+ "Tanggal Akad Akhir <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_AKAD_AKHIR] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_AKAD_AKHIR] +"' value='"+tglAkadAkhir+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_TGL_AKAD_AKHIR]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_BANK_COUNTERPARTY]+"'></i> "
			+ "Bank Counterparty <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_BANK_COUNTERPARTY] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_BANK_COUNTERPARTY] +"' value='"+irrevocableLc.getBankCounterparty()+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_BANK_COUNTERPARTY]+"' data-type='text' data-number='true' data-alpha='true' data-special=\" _/()'.-\">"
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>&nbsp;</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_KODE_VALUTA]+"'></i> "
			+ "Kode Valuta <i style='color:red;'>m*</i></label>"
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

                                returnData += ControlCombo.draw(""+FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KODE_VALUTA], null, ""+irrevocableLc.getKodeValuta(), valueCdValuta, keyCdValuta, "data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_KODE_VALUTA]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_PLAFON]+"'></i> "
			+ "Plafon <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_PLAFON] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_PLAFON] +"' value='"+Formater.formatNumber(irrevocableLc.getPlafon(),"###,###,##0")+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_PLAFON]+"' data-type='text' data-number='true' data-alpha='false' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_NOMINAL]+"'></i> "
			+ "Nominal (IDR) <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_NOMINAL] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_NOMINAL] +"' value='"+Formater.formatNumber( irrevocableLc.getNominal(),"###,###,##0")+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_NOMINAL]+"' data-type='text' data-number='true' data-alpha='false' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_SETORAN_JAMINAN]+"'></i> "
			+ "Setoran Jaminan <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_SETORAN_JAMINAN] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_SETORAN_JAMINAN] +"' value='"+Formater.formatNumber(irrevocableLc.getSetoranJaminan(),"###,###,##0")+"' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_SETORAN_JAMINAN]+"' data-type='text' data-number='true' data-alpha='false' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_KODE_KOLEKTIBILITAS]+"'></i> "
			+ "Kode Kolektibilitas <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>";
			    //start comboBox
                                Vector vCdKolektibilitas =  PstContentDataKolektibilitas.listWitoutOid(0,0,"","");
                                Vector valueCdKolektibilitas = new Vector();
                                Vector keyCdKolektibilitas = new Vector();
                                valueCdKolektibilitas.add("");
                                keyCdKolektibilitas.add("-- Pilih --");
                                for (int i = 0; i < vCdKolektibilitas.size(); i++) {
                                    ContentDataKolektibilitas KdKolektibilitas = (ContentDataKolektibilitas) vCdKolektibilitas.get(i);
                                    valueCdKolektibilitas.add(String.valueOf(KdKolektibilitas.getKodeOjk()));
                                    keyCdKolektibilitas.add(""+KdKolektibilitas.getNamaKolektibilitas());
                                }

                                returnData += ControlCombo.draw(""+FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KODE_KOLEKTIBILITAS], null, ""+irrevocableLc.getKodeKolektibilitas(), valueCdKolektibilitas, keyCdKolektibilitas, "data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_KODE_KOLEKTIBILITAS]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_TGL_WAN_PRESTASI]+"'></i> "
                        + "Tanggal Wan Prestasi <i style='color:red;'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_WAN_PRESTASI] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_WAN_PRESTASI] +"' value='"+tglWanPrestasi+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_KODE_KONDISI]+"'></i> "
			+ "Kode Kondisi <i style='color:red;'>m*</i></label>"
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

                                returnData += ControlCombo.draw(""+FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KODE_KONDISI], null, ""+irrevocableLc.getKodeKondisi(), valueCdKondisi, keyCdKondisi, "data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_KODE_KONDISI]+"' data-type='text' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_TGL_KONDISI]+"'></i> "
                        + "Tanggal Kondisi <i style='color:red;'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_KONDISI] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_TGL_KONDISI] +"' value='"+tglKondisi+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                         + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_KETERANGAN]+"'></i> "
			+ "Keterangan <i style='color:red;'>o*</i></label>"
                        + "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KETERANGAN] +"' id='"+ FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KETERANGAN] +"' value='"+irrevocableLc.getKeterangan()+"'>"
			+ "</div>"
		    + "</div>"
		   + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                        + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmIrrevocableLc.fieldQuestion[FrmIrrevocableLc.FRM_FIELD_KODE_KANTOR_CABANG]+"'></i> "
			+ "Kode Kantor Cabang <i style='color:red;'>m*</i></label>"
                        + "<div class='col-sm-8'>";
			    //start comboBox
			    Vector vCdKantorCbg =  PstContentDataKantorCabang.listWithoutOid(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+irrevocableLc.getKodeKantorCabang()+"'","");
                            ContentDataKantorCabang kdKantorCbg =  new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKantorCbg.size(); i++) {
                                kdKantorCbg = (ContentDataKantorCabang) vCdKantorCbg.get(i);
                            }
                          
                            returnData += "<input type='hidden' name='"+FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+"'>"
                                    + "<input type='text' name='DISPLAY_"+FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+" "+kdKantorCbg.getNamaKantorCabang()+"' class='form-control' readonly='readonly' data-required='required' data-error-message='"+FrmIrrevocableLc.fieldError[FrmIrrevocableLc.FRM_FIELD_KODE_KANTOR_CABANG]+"' data-type='text'>";
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group hidden'>"
                        + "<input type='hidden' name='"+FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_STATUS_PERUBAHAN_DATA]+"' value='0'>"
			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'>*</font></label>"
			+ "<div class='col-sm-8'>";
                            Vector operasiDataKey = new Vector(1,1);
                            Vector operasiDataVal = new Vector(1,1);
                            for(int i = 0; i < PstDebitur.operasiData.length; i++){
                                operasiDataKey.add(""+PstDebitur.operasiDataKey[i]);
                                operasiDataVal.add(""+PstDebitur.operasiData[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmIrrevocableLc.fieldNames[FrmIrrevocableLc.FRM_FIELD_OPERASI_DATA], "-- Pilih --", ""+irrevocableLc.getOperasiData(), operasiDataKey, operasiDataVal, "", "form-control")
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
	
	
	return returnData;
    }
    
    public String drawListIrrevocableLc(int iCommand, Vector objectClass) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("No Rekening", "200px");//2
        ctrlist.addHeader("CIF", "200px");//10
        ctrlist.addHeader("Kode Jenis Lc", "200px");//10
        ctrlist.addHeader("Kode Tujuan Lc", "200px");//10
        ctrlist.addHeader("No. Akad Awal", "200px");//10
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
            IrrevocableLc irrevocableLc = (IrrevocableLc) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+irrevocableLc.getNoRekening());//2
            rowx.add(""+irrevocableLc.getCif());//3
            rowx.add(""+irrevocableLc.getKodeJenis());//4
            rowx.add(""+irrevocableLc.getKodeTujuan());//4
            rowx.add(""+irrevocableLc.getNoAkadAwal());//4
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
                    + "<label>Kode Jenis LC</label>"
                    + "<input type='text' name='kodejenislc' class='form-control' id='kodejenislc'>"
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
                            + "<th>Kode Jenis LC</th>"
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
                        + "<th>Kode Jenis LC</th>"
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
            whereClause = "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_DATA]+"='0'";
        }else{
            whereClause = "ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_DATA]+" IS NULL";
        }
        
        if(userGroup != 4){
            whereClause += " AND ilc."+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
        }
        
        int summary = PstIrrevocableLc.getCountJoin(whereClause);
        returnData +=""+summary;
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        CtrlIrrevocableLc ctrlIrrevocableLc = new CtrlIrrevocableLc(request);
        ctrlIrrevocableLc.action(this.iCommand, this.oid, oidDelete);
        String msg = "<i class='fa fa-info'></i> "+ctrlIrrevocableLc.getMessage();
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
