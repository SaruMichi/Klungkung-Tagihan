/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.contentdata.ContentDataJabatan;
import com.dimata.dslik.entity.contentdata.ContentDataJenisIdentitas;
import com.dimata.dslik.entity.contentdata.ContentDataKabupatenKota;
import com.dimata.dslik.entity.contentdata.ContentDataKantorCabang;
import com.dimata.dslik.entity.contentdata.PstContentDataJabatan;
import com.dimata.dslik.entity.contentdata.PstContentDataJenisIdentitas;
import com.dimata.dslik.entity.contentdata.PstContentDataKabupatenKota;
import com.dimata.dslik.entity.contentdata.PstContentDataKantorCabang;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.pengurusataupemilik.PengurusAtauPemilik;
import com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik;
import com.dimata.dslik.form.pengurusataupemilik.CtrlPengurusAtauPemilik;
import com.dimata.dslik.form.pengurusataupemilik.FrmPengurusAtauPemilik;
import com.dimata.dslik.session.proses.SessionProsesPerlengkapan;
import com.dimata.gui.jsp.ControlCombo;
import com.dimata.gui.jsp.ControlList;
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
public class AjaxPengurusPemilik extends HttpServlet {

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
    
    //BOOLEAN
    private boolean privAdd = false;
    private boolean privUpdate = false;
    private boolean privDelete = false;
    private boolean privView = false;
    
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
        }else if(this.dataFor.equals("showlistpenguruspemilik")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull") || this.dataFor.equals("loadsummaryotherpenguruspemilik")){
            this.htmlReturn = getSummary(request);
        }else if(this.dataFor.equals("showotherform")){
            this.htmlReturn = otherForm(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = savePengurusPemilik(request);
        }
    }
    
    public String savePengurusPemilik(HttpServletRequest request){
        //history
        String returnData = "";
        CtrlPengurusAtauPemilik ctrlPengurusPemilik = new CtrlPengurusAtauPemilik(request);
        oid = FRMQueryString.requestLong(request, "FRM_FIELD_PENGURUS_OID");
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        ctrlPengurusPemilik.action(iCommand, oid,userId,userName);
        returnData = ctrlPengurusPemilik.getMessage();
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
                PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID],
                PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF],
                PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS],
                PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("listdatapengurus") || this.dataFor.equals("listdatapengurushome")){
            String[] cols = { 
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID]+"",
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]+"",
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"",
                "jenisidentitas."+PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_NAMA_IDENTITAS]+"",
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+"",
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+"",
                
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }else if(this.dataFor.equals("showlistpenguruspemilik")){
	    String[] cols = { 
                PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID],
                PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF],
                PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS],
                PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("showform2")){
            String[] cols = { 
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID]+"",
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]+"",
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"",
                "jenisidentitas."+PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_NAMA_IDENTITAS]+"",
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+"",
                "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+"",
                
            };
	    jSONObject = listDataTables(request, response, cols,"listdatapengurus", this.jSONObject);
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
        String whereClause = "";
        
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                if(!kodeCabang.equals("")){
                    whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                }
                whereClause += "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }
        }else{
            whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if(dataFor.equals("listdatasearch")){
	    if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(nama.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+"='"+nama+"'";
                }else{
                    whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+"='"+nama+"'";
                }
            }

            if(alamat.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+alamat+"%'";
                }else{
                    whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+alamat+"%'";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if (dataFor.equals("listdatapengurus") || this.dataFor.equals("listdatapengurushome")){
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause  = " "
                + " 1=1"
                + " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" = '"+cifParam+"'"
                + " AND ("
                    + " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]+" like '%"+this.searchTerm+"%'"
                    + " OR jenisidentitas."+PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_NAMA_IDENTITAS]+" like '%"+this.searchTerm+"%'"
                    + " OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" like '%"+this.searchTerm+"%'"
                    + " OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" like '%"+this.searchTerm+"%'"
                + ")";
            
        }else if(dataFor.equals("showlistpenguruspemilik")){
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{
                    whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }else{
                    whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+this.searchTerm+"%')";
	    }
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch") || dataFor.equals("listdatapengurus") || dataFor.equals("showlistpenguruspemilik")  || this.dataFor.equals("listdatapengurushome")){
	    total = PstPengurusAtauPemilik.getCountJoin(whereClause);
	}
        
        
        this.amount = amount;
       
        this.colName = colName;
        this.dir = dir;
        this.start = start;
        this.colOrder = col;
        
        try {
            result = getData(total, request, dataFor, periodeId);
        } catch(Exception ex){
            System.out.println(ex);
        }
       
       return result;
    }
    
    public JSONObject getData(int total, HttpServletRequest request, String dataforx, long periodeId){
        
        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        PengurusAtauPemilik pengurusAtauPemilik = new PengurusAtauPemilik();
	
	String whereClause = "";
        String order ="";
        String cif = FRMQueryString.requestString(request, "cif");
        String nama = FRMQueryString.requestString(request, "nama");
        String alamat = FRMQueryString.requestString(request, "alamat");
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
        
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId +"'";
            }else{
                //whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId +"'";
                if(!kodeCabang.equals("")){
                    whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                }
                whereClause += "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }
        }
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(dataforx.equals("listdatasearch")){
                if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(nama.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+nama+"%'";
                    }else{
                        whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+nama+"%'";
                    }
                }

                if(alamat.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+alamat+"%'";
                    }else{
                        whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+alamat+"%'";
                    }
                }
		if(whereClause.length() > 0){
                    whereClause += " AND (pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if (dataFor.equals("listdatapengurus") || this.dataFor.equals("listdatapengurushome")){
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause  = " "
                    + " 1=1"
                    + " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" = '"+cifParam+"'"
                    + " AND ("
                        + " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]+" like '%"+this.searchTerm+"%'"
                        + " OR jenisidentitas."+PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_NAMA_IDENTITAS]+" like '%"+this.searchTerm+"%'"
                        + " OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" like '%"+this.searchTerm+"%'"
                        + " OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" like '%"+this.searchTerm+"%'"
                    + ")";

            }else if(dataFor.equals("showlistpenguruspemilik")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                }
                if(whereClause.length() > 0){
                    whereClause += " AND (pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]+" LIKE '%"+this.searchTerm+"%')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(dataforx.equals("listdatasearch") || dataforx.equals("listdatapengurus") || dataforx.equals("showlistpenguruspemilik") || this.dataFor.equals("listdatapengurushome")){
            listData = PstPengurusAtauPemilik.listJoin(start, amount, whereClause, order);
        }
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
            String buttonUpdate = "";
	    if(dataforx.equals("listdatasearch")){
		pengurusAtauPemilik = (PengurusAtauPemilik) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+pengurusAtauPemilik.getCif());
                ja.put(""+(pengurusAtauPemilik.getNamaPengurus()== null ? "-" : pengurusAtauPemilik.getNamaPengurus()));
                ja.put(""+(pengurusAtauPemilik.getAlamat()== null ? "-" : pengurusAtauPemilik.getAlamat()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+pengurusAtauPemilik.getDebiturOid()+"&type="+pengurusAtauPemilik.getDebiturType()+"&cif="+pengurusAtauPemilik.getCif()+"&activetab=AjaxPengurusPemilik&otheroid="+pengurusAtauPemilik.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if (dataforx.equals("listdatapengurus")){
                pengurusAtauPemilik = (PengurusAtauPemilik) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(pengurusAtauPemilik.getNoIdentitas()));
                ja.put(""+(pengurusAtauPemilik.getCif()));
                ja.put(""+(pengurusAtauPemilik.getJenisIdentitas()));
                ja.put(""+(pengurusAtauPemilik.getNamaPengurus()));
                ja.put(""+(pengurusAtauPemilik.getAlamat()));
                
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+pengurusAtauPemilik.getDebiturOid()+"&type="+pengurusAtauPemilik.getDebiturType()+"&cif="+pengurusAtauPemilik.getCif()+"&activetab=AjaxPengurusPemilik&otheroid="+pengurusAtauPemilik.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                
                int count  = PstLogSysHistory.getCount(PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+pengurusAtauPemilik.getOID()+"' "
                        + "AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" LIKE '%Transfer%'");
                
                if(count == 0){
                    buttonUpdate += "<div class='btn btn-default'><input type='checkbox' class='deletelist' value='"+pengurusAtauPemilik.getOID()+"'></div>";
                }
                ja.put(buttonUpdate);
                array.put(ja);
            }else if (dataforx.equals("listdatapengurushome")){
                pengurusAtauPemilik = (PengurusAtauPemilik) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(pengurusAtauPemilik.getNoIdentitas()));
                ja.put(""+(pengurusAtauPemilik.getCif()));
                ja.put(""+(pengurusAtauPemilik.getJenisIdentitas()));
                ja.put(""+(pengurusAtauPemilik.getNamaPengurus()));
                ja.put(""+(pengurusAtauPemilik.getAlamat()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+pengurusAtauPemilik.getDebiturOid()+"&type="+pengurusAtauPemilik.getDebiturType()+"&cif="+pengurusAtauPemilik.getCif()+"&activetab=AjaxPengurusPemilik&otheroid="+pengurusAtauPemilik.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                ja.put(buttonUpdate);
                array.put(ja);
            }else if(dataforx.equals("showlistpenguruspemilik")){
		pengurusAtauPemilik = (PengurusAtauPemilik) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+pengurusAtauPemilik.getCif());
                ja.put(""+(pengurusAtauPemilik.getNamaPengurus()== null ? "-" : pengurusAtauPemilik.getNamaPengurus()));
                ja.put(""+(pengurusAtauPemilik.getAlamat()== null ? "-" : pengurusAtauPemilik.getAlamat()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+pengurusAtauPemilik.getDebiturOid()+"&type="+pengurusAtauPemilik.getDebiturType()+"&cif="+pengurusAtauPemilik.getCif()+"&activetab=AjaxPengurusPemilik&otheroid="+pengurusAtauPemilik.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
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
            String where = PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"='"+cif+"'";
            countData = PstPengurusAtauPemilik.getCount(where);
        }
        
        if (cif.length()==0){
            returnData = ""
            +"<div class='row'>"
                +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Pengurus/Pemilik</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                //+drawListPengurus(iCommand, listPengurus)
                                + "<div id='tablePengurusElement'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"
                                                + "<td>No Identitas</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Jenis Identitas</td>"
                                                + "<td>Nama Pengurus/Pemilik</td>"
                                                + "<td>Alamat</td>"
                                                + "<td>Aksi</td>"
                                            + "</tr>"
                                        + "</thead>"
                                    + "</table>"
                                + "</div>"
                            +"</div>"
                             +"<div class='box-header'>";
            
                                if(privAdd){
                                    returnData +="<button type='button' class='btn btn-primary btnaddgeneralpengurus' data-oid='0' data-type='0' data-for ='showform2' data-target='AjaxPengurusPemilik' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data Pengurus/Pemilik</button>";
                                }
                                
                                if(privDelete){
                                    returnData +=" <button type='button' class='btn btn-danger' id='delete' data-for='deleteitem' data-target='AjaxPengurusPemilik' data-command='"+Command.DELETEALL+"'><i class='fa fa-trash'></i> Hapus Data Pengurus/Pemilik</button>";
                                }
                                
                            returnData+=""
                            +"</div>"
                        +"</div>"
                +"</div>"
            +"</div>"            
            +"<div id='pengurusform'></div>";
        }else{
            if (countData>0){
                returnData = ""
                +"<div class='row'>"
                    +"<div class='col-md-12'>"
                            +"<div class='box'>"
                                +"<div class='box-header'>"
                                    +"<h3 class='box-title'>List Pengurus/Pemilik</h3>"
                                +"</div>"
                                +"<div class=\"box-body\">"
                                    //+drawListPengurus(iCommand, listPengurus)
                                    + "<div id='tablePengurusElement'>"
                                        + "<table class='table table-bordered table-striped'>"
                                            + "<thead>"
                                                + "<tr>"
                                                    + "<td>No</td>"
                                                    + "<td>No Identitas</td>"
                                                    + "<td>CIF</td>"
                                                    + "<td>Jenis Identitas</td>"
                                                    + "<td>Nama Pengurus/Pemilik</td>"
                                                    + "<td>Alamat</td>"
                                                    + "<td>Aksi</td>"
                                                + "</tr>"
                                            + "</thead>"
                                        + "</table>"
                                    + "</div>"
                                +"</div>"
                                 
                            +"</div>"
                    +"</div>"
                +"</div>" ;           
                
            }
        }
	
	
	return returnData;
    }
    
    public String showForm2(HttpServletRequest request){
        
        PengurusAtauPemilik pengurus = new PengurusAtauPemilik();
        pengurus.setCif(cif);
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        pengurus.setKodeKantorCabang(kodeCabang);
        Date dNow = new Date();
        String openDate = "";
        long oidPeriodeWrite = 0;
        if(oid!=0){
            try {
                pengurus = PstPengurusAtauPemilik.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            if (pengurus.getOpenDate()==null){
                openDate = "";
            }else{
                openDate = Formater.formatDate(pengurus.getOpenDate(), "yyyy-MM-dd");
            }
            
            oidPeriodeWrite = pengurus.getPeriodeId();
        }
        else {
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
            Vector listPeriode = PstPeriode.list(0, 1, " "+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            Periode entPeriode = (Periode) listPeriode.get(0);
            oidPeriodeWrite = entPeriode.getOID();
        }
        
        
	String returnData = ""
        + "<input type='hidden' value='"+oidPeriodeWrite+"' name ='FRM_FIELD_PERIODE_ID' id='oidPeriode'>"
       
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>View Data Pengurus/Pemilik</h4>"
		    
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_CIF]+"'></i> "
                            + "CIF <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
                            + "<input type='hidden' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_STATUS_PERUBAHAN_DATA] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_STATUS_PERUBAHAN_DATA] +"' value='0'>"
			    + "<input type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_CIF] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_CIF] +"' value='"+pengurus.getCif()+"' data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_CIF]+"'>"
			+ "</div>"
		    + "</div>"
                    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_NO_IDENTITAS]+"'></i> "
                            + "No Identitas <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                            //returnData += ControlCombo.draw(""+FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_NO_IDENTITAS], null, ""+pengurus.getNoIdentitas(), valueCdNoId, keyCdNoId, "data-required='required' onchange=\"javascript:changeFunc();\" ", "form-control");
                            returnData += "<input type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_NO_IDENTITAS] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_NO_IDENTITAS] +"' value='"+pengurus.getNoIdentitas()+"'  data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_NO_IDENTITAS]+"'>";
                            returnData += "</div>"
                                    
//			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_JENIS_IDENTITAS]+"'></i> "
                            + "Jenis Identitas <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
//			    + "<input type='text' class='form-control' name='jenisidentitas'>"
                            Vector vCdJenisId =  PstContentDataJenisIdentitas.listWithoutOid(0,0,"","");
                            Vector valueCdJenisId = new Vector();
                            Vector keyCdJenisId = new Vector();
                            valueCdJenisId.add("");
                            keyCdJenisId.add("-- Pilih --");
                            for (int i = 0; i < vCdJenisId.size(); i++) {
                                ContentDataJenisIdentitas jenisId = (ContentDataJenisIdentitas) vCdJenisId.get(i);
                                valueCdJenisId.add(String.valueOf(jenisId.getKodeOjk()));
                                keyCdJenisId.add(jenisId.getKodeOjk()+"-"+jenisId.getNamaIdentitas());
                            }
                            String selPria = "";
                            String selWanita="";
                            String selBadanUsaha="";
                            String selMasyarakat="";
                            if (pengurus.getJenisKelamin().equals("L")){
                                selPria = "selected";
                                selWanita ="";
                                selBadanUsaha="";
                                selMasyarakat="";
                            }else if (pengurus.getJenisKelamin().equals("P")){
                                selPria = "";
                                selWanita ="selected";
                                selBadanUsaha="";
                                selMasyarakat="";
                            }else if (pengurus.getJenisKelamin().equals("B")){
                                selPria = "";
                                selWanita ="";
                                selMasyarakat="";
                                selBadanUsaha="selected";
                            }else{
                                selPria = "";
                                selWanita ="";
                                selBadanUsaha="";
                                selMasyarakat="selected";
                            }
                            returnData += ControlCombo.draw(""+FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_JENIS_IDENTITAS], null, ""+pengurus.getJenisIdentitas(), valueCdJenisId, keyCdJenisId, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_JENIS_IDENTITAS]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_NAMA_PENGURUS]+"'></i> "
                            + "Nama Pengurus/Pemilik  <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_NAMA_PENGURUS] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_NAMA_PENGURUS] +"' value='"+pengurus.getNamaPengurus()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" &( )'.,-\" data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_NAMA_PENGURUS]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_JENIS_KELAMIN]+"'></i> "
                            + "Jenis Kelamin <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<select type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_JENIS_KELAMIN] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_JENIS_KELAMIN] +"' data-required='required' data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_JENIS_KELAMIN]+"'>"
				+ "<option value=''> -- Pilih -- </option>"
                                + "<option "+selPria+" value='L'>Pria</option>"
                                + "<option "+selWanita+" value='P'>Wanita</option>"
                                + "<option "+selBadanUsaha+" value='B'>Badan Usaha</option>"
                                + "<option "+selMasyarakat+" value='M'>Masyarakat</option>"    
			    + "</select>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_ALAMAT]+"'></i> "
                            + "Alamat   <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_ALAMAT] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_ALAMAT] +"' value='"+pengurus.getAlamat()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" ./'(),-\" data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_ALAMAT]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_KELURAHAN]+"'></i> "
                            + "Kelurahan  <i style='color:red;'>(m*</i> </label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_KELURAHAN] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_KELURAHAN] +"' value='"+pengurus.getKelurahan()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" ./'-\" data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_KELURAHAN]+"'>"
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>&nbsp;</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_KECAMATAN]+"'></i> "
                            + "Kecamatan  <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_KECAMATAN] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_KECAMATAN] +"' value='"+pengurus.getKecamatan()+"' data-required='required' data-required='required' data-number='true' data-alpha='true'  data-special=\" ./'-\" data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_KECAMATAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_KODE_KABUPATEN]+"'></i> "
                            + "Kode Sandi Kab/Kota (DATI II)  <i style='color:red;'>m*</i></label>"
			+ "<div class='col-sm-8'>";
//			    + "<select class='form-control' name='kodesandikab'>"
//				+ "<option value=''> -- Pilih -- </option>"
//			    + "</select>"
//			+ "</div>"
                            Vector vCdKab =  PstContentDataKabupatenKota.list(0,0,"","");
                            Vector valueCdKab = new Vector();
                            Vector keyCdKab = new Vector();
                            valueCdKab.add("");
                            keyCdKab.add("-- Pilih --");
                            for (int i = 0; i < vCdKab.size(); i++) {
                                ContentDataKabupatenKota kab = (ContentDataKabupatenKota) vCdKab.get(i);
                                valueCdKab.add(String.valueOf(kab.getKodeOjk()));
                                keyCdKab.add(kab.getKodeOjk()+"-"+kab.getNamaKabupatenKota());
                            }
                            returnData += ControlCombo.draw(""+FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_KODE_KABUPATEN], null, ""+pengurus.getKodeKabupaten(), valueCdKab, keyCdKab, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_KODE_KABUPATEN]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_KODE_JABATAN]+"'></i> "
                            + "Kode Jabatan  <i style='color:red;'>(m*</i> </label>"
			+ "<div class='col-sm-8'>";
//			    + "<select class='form-control' name='kodejabatan'>"
//				+ "<option value=''> -- Pilih -- </option>"
//			    + "</select>"
//			+ "</div>"
                            Vector vCdJabatan =  PstContentDataJabatan.list(0,0,"","");
                            Vector valueCdJabatan = new Vector();
                            Vector keyCdJabatan = new Vector();
                            valueCdJabatan.add("");
                            keyCdJabatan.add("-- Pilih --");
                            for (int i = 0; i < vCdJabatan.size(); i++) {
                                ContentDataJabatan jabatan = (ContentDataJabatan) vCdJabatan.get(i);
                                valueCdJabatan.add(String.valueOf(jabatan.getKodeOjk()));
                                keyCdJabatan.add(jabatan.getKodeOjk()+"-"+jabatan.getNamaJabatan());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_KODE_JABATAN], null, ""+pengurus.getKodeJabatan(), valueCdJabatan, keyCdJabatan, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_KODE_JABATAN]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_PANGSA_KEPEMILIKAN]+"'></i> "
                            + "Pangsa Kepemilikan  <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_PANGSA_KEPEMILIKAN] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_PANGSA_KEPEMILIKAN] +"' value='"+pengurus.getPangsaKepemilikan()+"' data-required='required' data-required='required' data-number='true' data-alpha='false' data-special=',' data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_PANGSA_KEPEMILIKAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_STATUS_PENGURUS]+"'></i> "
                            + "Status Pengurus/Pemilik  <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                            
                            Vector valueStatusPengurus = new Vector();
                            Vector keyStatusPengurus = new Vector();
                            valueStatusPengurus.add("");
                            keyStatusPengurus.add("-- Pilih --");
                            valueStatusPengurus.add("1");
                            keyStatusPengurus.add("Aktive");
                            valueStatusPengurus.add("2");
                            keyStatusPengurus.add("Telah Berakhir");
                            returnData += ControlCombo.draw(""+FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_STATUS_PENGURUS], null, ""+pengurus.getStatusPengurus(), valueStatusPengurus, keyStatusPengurus, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_STATUS_PENGURUS]+"'", "form-control");
                            returnData += "</div>"      
			    //+ "<input type='text' class='form-control' name='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_STATUS_PENGURUS] +"' id='"+ FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_STATUS_PENGURUS] +"' value='"+pengurus.getStatusPengurus()+"'>"
			//+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmPengurusAtauPemilik.fieldQuestion[FrmPengurusAtauPemilik.FRM_FIELD_KODE_KANTOR_CABANG]+"'></i> "
                            + "Kode Kantor Cabang <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                            Vector vCdKanCab =  PstContentDataKantorCabang.list(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+pengurus.getKodeKantorCabang()+"'","");
                            ContentDataKantorCabang kanCab = new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKanCab.size(); i++) {
                                kanCab = (ContentDataKantorCabang) vCdKanCab.get(i);
                            }
                            returnData += "<input type='hidden' name='"+FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kanCab.getKodeOjk()+"'>"
                                    + "<input type='text' name='DISPLAY_"+FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kanCab.getKodeOjk()+" "+kanCab.getNamaKantorCabang()+"' data-required='required' readonly='readonly' class='form-control' data-type='text' data-error-message='"+FrmPengurusAtauPemilik.fieldError[FrmPengurusAtauPemilik.FRM_FIELD_KODE_KANTOR_CABANG]+"'>";
                            returnData += "</div>"
		    + "</div>"
                    + "<div class='form-group hidden'>"
			+ "<label class='control-label col-sm-4'>Operasi Data <font color='red'>*</font></label>"
			+ "<div class='col-sm-8'>";
                            Vector statusDataKey = new Vector(1,1);
                            Vector statusDataVal = new Vector(1,1);
                            for(int i = 0; i < PstAgunan.statusDatas.length; i++){
                                statusDataKey.add(""+i);
                                statusDataVal.add(""+PstAgunan.statusDatas[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmPengurusAtauPemilik.fieldNames[FrmPengurusAtauPemilik.FRM_FIELD_STATUS_DATA], "-- Pilih --", ""+pengurus.getStatusData(), statusDataKey, statusDataVal, "", "form-control")
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
	
	
	return returnData;
    }
    
    public String drawListPengurus(int iCommand, Vector objectClass) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("No Identitas", "200px");//2
        ctrlist.addHeader("CIF", "200px");//10
        ctrlist.addHeader("Jenis Identitas", "200px");//10
        ctrlist.addHeader("Nama Pengurus/Pemilik", "200px");//10
        ctrlist.addHeader("Alamat", "200px");//10
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
            PengurusAtauPemilik pengurus = (PengurusAtauPemilik) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+pengurus.getNoIdentitas());//2
            rowx.add(""+pengurus.getCif());//4
            rowx.add(""+pengurus.getJenisIdentitas());//4
            rowx.add(""+pengurus.getNamaPengurus());//4
            rowx.add(""+pengurus.getAlamat());//4
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
                    + "<label>Nama Pengurus/Pemilik</label>"
                    + "<input type='text' name='nama' class='form-control' id='nama'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Alamat</label>"
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
                            + "<th>Nama Pengurus/Pemilik</th>"
                            + "<th>Alamat</th>"
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
                        + "<th>Nama Pengurus/Pemilik</th>"
                        + "<th>Alamat</th>"
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
            whereClause = "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+"='0'";
        }else if(this.dataFor.equals("loadsummarynull")){
            whereClause = "pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
        }else{
            whereClause = "l."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if(userGroup != 4){
            if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
                if(!kodeCabang.equals("")){
                whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
                }
            }else{
                if(!kodeCabang.equals("")){
                whereClause += " AND l."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
                }
            }
        }else{
            if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
               if(whereClause.length()>0){
                    whereClause += " AND pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
               }else{
                    whereClause += " pengurus."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
               }
            }
        }
        
        int summary = 0;
        if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            summary = PstPengurusAtauPemilik.getCountJoin(whereClause);
        }else{
            summary = SessionProsesPerlengkapan.getCountJoinPengurusPemilik(whereClause);
        }
        returnData +=""+summary;
        return returnData;
    }
    
    public String otherForm(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        
        String whereClause = "l."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periodeId+"'";
        if(userGroup != 4){
            if(!kodeCabang.equals("")){
                whereClause += " AND l."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        
        Vector listData = SessionProsesPerlengkapan.listOtherPengurusPemilik(0, 0, whereClause, "l."+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+" ASC");
        returnData+=""
        + "<span class='pull-right'><input type='checkbox' class='checkall' value='0'> Check All</span>"
        + "<table class='table table-bordered table-strped'>"
            + "<thead>"
                + "<tr>"
                    + "<th>No.</th>"
                    + "<th>CIF</th>"
                    + "<th>Nama Pengurus/Pemilik</th>"
                    + "<th>Alamat</th>"
                    + "<th>Aksi</th>"
                + "</tr>"
            + "</thead>"
            + "<tbody>";
                for(int i = 0; i < listData.size(); i++){
                    PengurusAtauPemilik data = (PengurusAtauPemilik) listData.get(i);
                    returnData+=""
                    + "<tr>"
                        + "<td>"+(i+1)+"</td>"
                        + "<td>"+data.getCif()+"</td>"
                        + "<td>"+data.getNamaPengurus()+"</td>"
                        + "<td>"+data.getAlamat()+"</td>"
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
            CtrlPengurusAtauPemilik ctrlPengurusAtauPemilik = new CtrlPengurusAtauPemilik(request);
            ctrlPengurusAtauPemilik.action(Command.DELETE, oid, 0, "");
            String msg = ctrlPengurusAtauPemilik.getMessage();
            returnData = "<i class='fa fa-info'></i> "+msg;
        }
        
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        CtrlPengurusAtauPemilik ctrlPengurusAtauPemilik = new CtrlPengurusAtauPemilik(request);
        ctrlPengurusAtauPemilik.action(this.iCommand, this.oid, oidDelete);
        String msg = "<i class='fa fa-info'></i> "+ctrlPengurusAtauPemilik.getMessage();
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
