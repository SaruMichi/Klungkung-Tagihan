/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.dslik.entity.debitur.Debitur;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.form.kredit.*;
import com.dimata.dslik.session.proses.ProsesTransferDataBank;
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
public class AjaxKredit extends HttpServlet {

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
        this.cif= FRMQueryString.requestString(request, "FRM_FIELD_CIF");
        
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
        String cifx = FRMQueryString.requestString(request, "cif");
        String norekening = FRMQueryString.requestString(request, "idrekening");
        //proses download data per cif
        //hhohoho lakukan prosesnya
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
        if(!norekening.equals("")){
            debitur = prosesTransferDataBank.actionTransferDataKredit(""+norekening,periodeId, 1, 1);
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
        }else if (this.dataFor.equals("showlistkredit")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull") || this.dataFor.equals("loadsummaryotherkredit")){
            this.htmlReturn = getSummary(request);
        }else if(this.dataFor.equals("showotherform")){
            this.htmlReturn = otherForm(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	 if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = saveKredit(request);
        }
    }
        
    public String saveKredit(HttpServletRequest request) {
        //history
        String returnData = "";
        CtrlKredit ctrlKredit = new CtrlKredit(request);
        oid = FRMQueryString.requestLong(request, "FRM_FIELD_OID_KREDIT");
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        int iError = ctrlKredit.action(iCommand, oid,userId,userName);
        returnData = ctrlKredit.getMessage();
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
                PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID],
                PstKredit.fieldNames[PstKredit.FLD_CIF],
                PstKredit.fieldNames[PstKredit.FLD_NO_REKENING],
                PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("listkreditperlengkapandata") || this.dataFor.equals("listkreditperlengkapandatahome")){
            String[] cols = { 
                ""+PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_CIF]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_KODE_SIFAT]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_KODE_AKAD]+""
               
            };
            jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }else if(this.dataFor.equals("showlistkredit")){
	    String[] cols = { 
                PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID],
                PstKredit.fieldNames[PstKredit.FLD_CIF],
                PstKredit.fieldNames[PstKredit.FLD_NO_REKENING],
                PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("showform2")){
            String[] cols = { 
                ""+PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_CIF]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_KODE_SIFAT]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+"",
                ""+PstKredit.fieldNames[PstKredit.FLD_KODE_AKAD]+""
               
            };
            jSONObject = listDataTables(request, response, cols, "listkreditperlengkapandata", this.jSONObject);
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
        String norekening = FRMQueryString.requestString(request, "norekening");
        String kodejeniskredit = FRMQueryString.requestString(request, "kodejeniskredit");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        String alias = "";
        
        if(dataFor.equals("listdatasearch") || dataFor.equals("showlistkredit")){
            alias = "kredit.";
        }
        
        String whereClause = "";
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND "+alias+""+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                if(!kodeCabang.equals("")){
                    whereClause += " "+alias+""+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                }
                whereClause += " "+alias+""+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"' ";
            }
        }
        
        if(dataFor.equals("listdatasearch")){
	    if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(norekening.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+"='"+norekening+"'";
                }else{
                    whereClause += " kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+"='"+norekening+"'";
                }
            }

            if(kodejeniskredit.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+"='"+kodejeniskredit+"'";
                }else{
                    whereClause += " kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+"='"+kodejeniskredit+"'";
                }
            }
            
	    if(whereClause.length() > 0){
		whereClause += " AND (kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if (dataFor.equals("listkreditperlengkapandata") || dataFor.equals("listkreditperlengkapandatahome")){
            whereClause = " 1=1";
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause += " "
                + " AND kredit."+ PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cifParam+"'"
                //+ " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'"    
                + " AND ("
                    + " kredit."+PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]+" like '%"+this.searchTerm+"%'"
                    + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"                   
                    + " OR skredit."+PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]+" like '%"+ this.searchTerm+"%'"
                    + " OR apembiayaan."+PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_NAMA_AKAD_PEMBIAYAAN]+" like '%"+this.searchTerm+"%'"
                    + " OR jkredit."+PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]+" like '%"+this.searchTerm+"%'" 
                + ")"; 
        }else if(dataFor.equals("showlistkredit")){
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{
                    whereClause += "  kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL"; 
                }else{
                    whereClause += "  kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL"; 
                }
            }
             
	    if(whereClause.length() > 0){
		whereClause += " AND (kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+" LIKE '%"+this.searchTerm+"%')";
	    }
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstKredit.getCountJoin(whereClause);
	}else if (dataFor.equals("listkreditperlengkapandata") || dataFor.equals("listkreditperlengkapandatahome")){
            total = PstKredit.getCountJoin(whereClause);
        }else if(dataFor.equals("showlistkredit")){
            total = PstKredit.getCountJoin(whereClause);
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
    
    public JSONObject getData(int total, HttpServletRequest request, String dataforz, long periodeId){
        
        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        Kredit kredit = new Kredit();

	String cif = FRMQueryString.requestString(request, "cif");
        String norekening = FRMQueryString.requestString(request, "norekening");
        String kodejeniskredit = FRMQueryString.requestString(request, "kodejeniskredit");
	String whereClause = "";
        String order ="";
        String appRoot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        String alias = "";
        
        
        
        if(dataforz.equals("listdatasearch") || dataforz.equals("showlistkredit")){
            alias = "kredit.";
        }
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND "+alias+""+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                if(!kodeCabang.equals("")){
                    whereClause += " "+alias+""+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                }
                whereClause += " "+alias+""+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"' ";
            }
        }
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(dataforz.equals("listdatasearch")){
                if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(norekening.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+"='"+norekening+"'";
                    }else{
                        whereClause += " kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+"='"+norekening+"'";
                    }
                }

                if(kodejeniskredit.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+"='"+kodejeniskredit+"'";
                    }else{
                        whereClause += " kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+"='"+kodejeniskredit+"'";
                    }
                }
		if(whereClause.length() > 0){
                    whereClause += " AND (kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+" LIKE '%"+this.searchTerm+"%')";
		}
            }else if (dataforz.equals("listkreditperlengkapandata") || dataforz.equals("listkreditperlengkapandatahome")){
                whereClause = " 1=1";
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause += " "
                    + " AND kredit."+ PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cifParam+"'"
                    //+ " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'"    
                    + " AND ("
                        + " kredit."+PstKredit.fieldNames[PstKredit.FLD_KREDIT_OID]+" like '%"+this.searchTerm+"%'"
                        + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"                   
                        + " OR skredit."+PstContentDataSifatKredit.fieldNames[PstContentDataSifatKredit.FLD_SIFAT_KREDIT]+" like '%"+ this.searchTerm+"%'"
                        + " OR apembiayaan."+PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_NAMA_AKAD_PEMBIAYAAN]+" like '%"+this.searchTerm+"%'"
                        + " OR jkredit."+PstContentDataJenisKredit.fieldNames[PstContentDataJenisKredit.FLD_NAMA_JENIS_KREDIT]+" like '%"+this.searchTerm+"%'" 
                    + ")"; 
            }else if(dataforz.equals("showlistkredit")){
                
//                if(whereClause.length() > 0){
//                    if(whereClause.length() > 0){
//                        whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]+"='"+status+"'";
//                    }else{
//                        whereClause += " kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]+"='"+status+"'";
//                    }
//                }else{
//                    if(whereClause.length() > 0){
//                        whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]+"='"+status+"'";
//                    }else{
//                        whereClause += " kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]+"='"+status+"'";
//                    }
//                }
                if(status.equals("0")){
                        if(whereClause.length() > 0){
                            whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                        }else{
                            whereClause += "  kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                        }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL"; 
                    }else{
                        whereClause += "  kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL"; 
                    }
                }

                if(whereClause.length() > 0){
                    whereClause += " AND (kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (kredit."+PstKredit.fieldNames[PstKredit.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + " OR kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]+" LIKE '%"+this.searchTerm+"%')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(dataforz.equals("listdatasearch")){
	    listData = PstKredit.listJoin(start, amount, whereClause, "");
	}else if (dataforz.equals("listkreditperlengkapandata") || dataforz.equals("listkreditperlengkapandatahome")){
            listData = PstKredit.listJoin(start, colOrder, whereClause, "");
        }else if(dataforz.equals("showlistkredit")){
	    listData = PstKredit.listJoin(start, amount, whereClause, "");
	}
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
            String buttonUpdate = "";
	    if(dataforz.equals("listdatasearch")){
		kredit = (Kredit) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+kredit.getCif());
                ja.put(""+(kredit.getNoRekening()== null ? "-" : kredit.getNoRekening()));
                ja.put(""+(kredit.getKodeJenisKredit()== null ? "-" : kredit.getKodeJenisKredit()));
                
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+kredit.getDebiturOid()+"&type="+kredit.getDebiturType()+"&cif="+kredit.getCif()+"&activetab=AjaxKredit&otheroid="+kredit.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if (dataforz.equals("listkreditperlengkapandata")){
                
                Kredit entKredit = new Kredit();
                entKredit = (Kredit) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(entKredit.getNoRekening()));
                ja.put(""+(entKredit.getCif()));
                ja.put(""+(entKredit.getNamaSifatKredit()));
                ja.put(""+(entKredit.getNamaJenisKredit()));
                ja.put(""+(entKredit.getNamaAKad()));
                buttonUpdate += "<a href='"+appRoot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+entKredit.getDebiturOid()+"&type="+entKredit.getDebiturType()+"&cif="+entKredit.getCif()+"&activetab=AjaxKredit&otheroid="+entKredit.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                
                int count  = PstLogSysHistory.getCount(PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+entKredit.getOID()+"' "
                        + "AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" LIKE '%Transfer%'");
                if(count == 0){
                    buttonUpdate+="<div class='btn btn-default'><input type='checkbox' class='deletelist' value='"+entKredit.getOID()+"'></div>";
                }
                ja.put( buttonUpdate);
                array.put(ja);
            }else if(dataforz.equals("showlistkredit")){
		kredit = (Kredit) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+kredit.getCif());
                ja.put(""+(kredit.getNoRekening()== null ? "-" : kredit.getNoRekening()));
                ja.put(""+(kredit.getKodeJenisKredit()== null ? "-" : kredit.getKodeJenisKredit()));
                buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+kredit.getDebiturOid()+"&type="+kredit.getDebiturType()+"&cif="+kredit.getCif()+"&activetab=AjaxKredit&otheroid="+kredit.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if (dataforz.equals("listkreditperlengkapandatahome")){
                Kredit entKredit = new Kredit();
                entKredit = (Kredit) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(entKredit.getNoRekening()));
                ja.put(""+(entKredit.getCif()));
                ja.put(""+(entKredit.getNamaSifatKredit()));
                ja.put(""+(entKredit.getNamaJenisKredit()));
                ja.put(""+(entKredit.getNamaAKad()));
                buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+entKredit.getDebiturOid()+"&type="+entKredit.getDebiturType()+"&cif="+entKredit.getCif()+"&activetab=AjaxKredit&otheroid="+entKredit.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                ja.put(""
                    + buttonUpdate
                    + "");
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
        
        /*Kredit kredit = new Kredit();
        Date dNow = new Date();
        String tglAkadAwal = "";
        String tglAkadAkhir= "";
        String tglAwalKredit = "";
        String tglMulai = "";
        String tglJatuhTempo = "";
        String tglMacet = "";
        String tglRestrukturisasiAwal = "";
        String tglRestrukturisasiAkhir = "";
        String tglKondisi = "";
        String openDate = "";
        if(oid != 0){
             try {
                kredit = PstKredit.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            tglAkadAwal = Formater.formatDate(kredit.getTglAkadAwal(), "yyyy-MM-dd");
            tglAkadAkhir = Formater.formatDate(kredit.getTglAkadAkhir(),"yyyy-MM-dd");
            tglAwalKredit = Formater.formatDate(kredit.getTglAwal(), "yyyy-MM-dd");
            tglMulai = Formater.formatDate(kredit.getTglMulai(), "yyyy-MM-dd");
            tglJatuhTempo = Formater.formatDate(kredit.getTglTempo(), "yyyy-MM-dd");
            tglMacet = Formater.formatDate(kredit.getTglMacet(), "yyy-MM-dd");
            tglRestrukturisasiAwal = Formater.formatDate(kredit.getTglRestrukturisasiAwal(), "yyyy-MM-dd");
            tglRestrukturisasiAkhir = Formater.formatDate(kredit.getTglRestrukturisasiAkhir(), "yyyy-MM-dd");
            tglKondisi = Formater.formatDate(kredit.getTglKondisi(), "yyyy-MM-dd");
            openDate = Formater.formatDate(kredit.getOpenDate(), "yyyy-MM-dd");
        }
        else {
            tglAkadAwal = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglAkadAkhir = Formater.formatDate(dNow,"yyyy-MM-dd");
            tglAwalKredit = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglMulai = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglJatuhTempo = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglMacet = Formater.formatDate(dNow, "yyy-MM-dd");
            tglRestrukturisasiAwal = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglRestrukturisasiAkhir = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglKondisi = Formater.formatDate(dNow, "yyyy-MM-dd");
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
        }
        
        //tampilkan listKredit berdasarkan cif
        Vector listKredit = new Vector();
        if(!cif.equals("")){
                String where = PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
                listKredit = PstKredit.list(0,0, ""+where, "");
        }*/
        
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
            String where = PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
            countData = PstKredit.getCount(where);
        }
        if (cif.length()==0){
            returnData = ""  
            +"<div class='row'>"
                +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Kredit</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                    //+drawListKredit(iCommand, listKredit)
                                + "<div id='tableListKreditElement'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"
                                                + "<td>No Rekening</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Kode Sifat Kredit</td>"
                                                + "<td>Nama Jenis Kredit</td>"
                                                + "<td>Kode Skim/Akad Pembiayaan</td>"
                                                + "<td>Aksi</td>"
                                            + "</tr>"
                                        + "</thead>"
                                    + "</table>"
                                + "</div>"
                            +"</div>";
                            if (needAdd==0){
                                returnData += ""
                                +"<div class='box-header'>";
                                    if(privAdd){
                                        returnData +="<button type='button' class='btn btn-primary btnaddgeneralkredit' data-oid='0' data-for ='showform2' data-target='AjaxKredit' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data Kredit</button>";
                                    }
                                    
                                    if(privDelete){
                                        returnData +=" <button type='button' class='btn btn-danger' id='delete' data-for='deleteitem' data-target='AjaxKredit' data-command='"+Command.DELETEALL+"'><i class='fa fa-trash'></i> Hapus Data Kredit</button>";
                                    }
                                    
                                returnData +=""
                                +"</div>";
                            }

                        returnData+=""
                        +"</div>"
                +"</div>"
            +"</div>"   
            + "<div id='datakreditform'></div>"  ;       
        }else{
            if (countData>0){
                returnData = ""  
                +"<div class='row'>"
                    +"<div class='col-md-12'>"
                            +"<div class='box'>"
                                +"<div class='box-header'>"
                                    +"<h3 class='box-title'>List Kredit</h3>"
                                +"</div>"
                                +"<div class=\"box-body\">"
                                        //+drawListKredit(iCommand, listKredit)
                                    + "<div id='tableListKreditElement'>"
                                        + "<table class='table table-bordered table-striped'>"
                                            + "<thead>"
                                                + "<tr>"
                                                    + "<td>No</td>"
                                                    + "<td>No Rekening</td>"
                                                    + "<td>CIF</td>"
                                                    + "<td>Kode Sifat Kredit</td>"
                                                    + "<td>Nama Jenis Kredit</td>"
                                                    + "<td>Kode Skim/Akad Pembiayaan</td>"
                                                    + "<td>Aksi</td>"
                                                + "</tr>"
                                            + "</thead>"
                                        + "</table>"
                                    + "</div>"
                                +"</div>";
                                if (needAdd==0){
                                    returnData += ""
                                    +"<div class='box-header'>"
                                        +"<button type='button' class='btn btn-primary btnaddgeneralkredit' data-oid='0' data-for ='showform2' data-target='AjaxKredit' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data Kredit</button>"
                                    +"</div>";
                                }

                            returnData+=""
                            +"</div>"
                    +"</div>"
                +"</div>";
                
            }
        }
	
	
	return returnData;
    }
    
    public String showFormDetail (HttpServletRequest request){
        String returnData="";
        
        return returnData;
    }
    
    public String showForm2(HttpServletRequest request){
        Kredit kredit = new Kredit();
        kredit.setCif(this.cif);
        Date dNow = new Date();
        String tglAkadAwal = "";
        String tglAkadAkhir= "";
        String tglAwalKredit = "";
        String tglMulai = "";
        String tglJatuhTempo = "";
        String tglMacet = "";
        String tglRestrukturisasiAwal = "";
        String tglRestrukturisasiAkhir = "";
        String tglKondisi = "";
        String openDate = "";
        long oidPeriodeWrite = 0;
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        kredit.setKodeKantorCabang(kodeCabang);
        
        String kodesebabmacet="";
        String tglsebabmacet="";
        
        if(oid != 0){
            try {
                kredit = PstKredit.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            
            if(kredit.getKodeKolektibilitas()!=null){
                if(kredit.getKodeKolektibilitas().equals("5")){
                        kodesebabmacet="data-required='required'";
                        tglsebabmacet="data-required='required'";
                }
            }
            
            if (kredit.getTglAkadAwal()==null){
                tglAkadAwal = "";
            }else{
                tglAkadAwal = Formater.formatDate(kredit.getTglAkadAwal(), "yyyy-MM-dd");
            }
            
            if (kredit.getTglAkadAkhir()==null){
                tglAkadAkhir = "";
            }else{
                tglAkadAkhir = Formater.formatDate(kredit.getTglAkadAkhir(),"yyyy-MM-dd");
            }
            
            if (kredit.getTglAwal()==null){
                tglAwalKredit = "";
            }else{
                tglAwalKredit = Formater.formatDate(kredit.getTglAwal(), "yyyy-MM-dd");
            }
            
            if (kredit.getTglMulai()==null){
                tglMulai = "";
            }else{
                tglMulai = Formater.formatDate(kredit.getTglMulai(), "yyyy-MM-dd");
            }
            
            if (kredit.getTglTempo()==null){
                tglJatuhTempo = "";
            }else{
                tglJatuhTempo = Formater.formatDate(kredit.getTglTempo(), "yyyy-MM-dd");
            }
            
            if (kredit.getTglMacet()==null){
                tglMacet = "";
            }else{
                tglMacet = Formater.formatDate(kredit.getTglMacet(), "yyy-MM-dd");
            }
            
            if (kredit.getTglRestrukturisasiAwal()==null){
                tglRestrukturisasiAwal = "";
            }else{
                tglRestrukturisasiAwal = Formater.formatDate(kredit.getTglRestrukturisasiAwal(), "yyyy-MM-dd");
            }
            
            if (kredit.getTglRestrukturisasiAkhir()==null){
                tglRestrukturisasiAkhir = "";
            }else{
                
                tglRestrukturisasiAkhir = Formater.formatDate(kredit.getTglRestrukturisasiAkhir(), "yyyy-MM-dd");
            }
            
            if (kredit.getTglKondisi()==null){
                tglKondisi = "";
            }else{
                tglKondisi = Formater.formatDate(kredit.getTglKondisi(), "yyyy-MM-dd");
            }
            
            if (kredit.getOpenDate()==null){
                openDate = "";
            }else{
                openDate = Formater.formatDate(kredit.getOpenDate(), "yyyy-MM-dd");
            }
            
            oidPeriodeWrite = kredit.getPeriodeId();
        }
        else {
            tglAkadAwal = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglAkadAkhir = Formater.formatDate(dNow,"yyyy-MM-dd");
            tglAwalKredit = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglMulai = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglJatuhTempo = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglMacet = Formater.formatDate(dNow, "yyy-MM-dd");
            tglRestrukturisasiAwal = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglRestrukturisasiAkhir = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglKondisi = Formater.formatDate(dNow, "yyyy-MM-dd");
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
            Vector listPeriode = PstPeriode.list(0, 1, " "+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            Periode entPeriode = (Periode) listPeriode.get(0);
            oidPeriodeWrite = entPeriode.getOID();
        }
        
        //tampilkan listKredit berdasarkan cif
        Vector listKredit = new Vector();
        if(!cif.equals("")){
                String where = PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
                listKredit = PstKredit.list(0,0, ""+where, "");
        }
        
	String returnData = "<input type='hidden' value='"+oidPeriodeWrite+"' name ='FRM_FIELD_PERIODE_ID' id='oidPeriode'>"  
              
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>View Kredit</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_NO_REKENING]+"'></i> "
                                + "No. Rekening <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='hidden' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FIELD_STATUS_PERUBAHAN_DATA] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FIELD_STATUS_PERUBAHAN_DATA] +"' value='0' >"
                            + "<input type='text' class='form-control nospecial' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NO_REKENING] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NO_REKENING] +"' value='"+kredit.getNoRekening()+"' data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_NO_REKENING]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_CIF]+"'></i> "
                            + "CIF <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control nospecial' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_CIF] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_CIF] +"' value='"+kredit.getCif()+"' data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_CIF]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_SIFAT]+"'></i> "
                            + "Kode Sifat Kredit <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
			    Vector vCdSifatKredit =  PstContentDataSifatKredit.listWithoutOid(0,0,"","");
                            Vector valueCdSifatKredit = new Vector();
                            Vector keyCdSifatKredit = new Vector();
                            valueCdSifatKredit.add("");
                            keyCdSifatKredit.add("-- Pilih --");
                            for (int i = 0; i < vCdSifatKredit.size(); i++) {
                                ContentDataSifatKredit KdSifatKredit = (ContentDataSifatKredit) vCdSifatKredit.get(i);
                                valueCdSifatKredit.add(String.valueOf(KdSifatKredit.getKodeOjk()));
                                keyCdSifatKredit.add(KdSifatKredit.getKodeOjk()+"-"+KdSifatKredit.getSifatKredit());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_SIFAT], null, ""+kredit.getKodeSifat(), valueCdSifatKredit, keyCdSifatKredit, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_SIFAT]+"'", "form-control");
                            returnData += "</div>"
                            //end comboBox	
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_JENIS_KREDIT]+"'></i> "
                            + "Kode Jenis Kredit <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";			    
                                //start comboBox
                                Vector vCdJenisKredit =  PstContentDataJenisKredit.listWithoutOid(0,0,"","");
                                Vector valueCdJenisKredit = new Vector();
                                Vector keyCdJenisKredit = new Vector();
                                valueCdJenisKredit.add("");
                                keyCdJenisKredit.add("-- Pilih --");
                                for (int i = 0; i < vCdJenisKredit.size(); i++) {
                                    ContentDataJenisKredit KdJenisKredit = (ContentDataJenisKredit) vCdJenisKredit.get(i);
                                    valueCdJenisKredit.add(String.valueOf(KdJenisKredit.getKodeOjk()));
                                    keyCdJenisKredit.add(KdJenisKredit.getKodeOjk()+"-"+KdJenisKredit.getNamaJenisKredit());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_JENIS_KREDIT], null, ""+kredit.getKodeJenisKredit(), valueCdJenisKredit, keyCdJenisKredit, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_JENIS_KREDIT]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox	
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_AKAD]+"'></i> "
                            + "Kode Skim/Akad Pembiayaan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			       //start comboBox
                                Vector vCdSkim =  PstContentDataSkimAkadPembiayaan.listWithoutOid(0,0,"","");
                                Vector valueCdSkim = new Vector();
                                Vector keyCdSkim = new Vector();
                                valueCdSkim.add("");
                                keyCdSkim.add("-- Pilih --");
                                for (int i = 0; i < vCdSkim.size(); i++) {
                                    ContentDataSkimAkadPembiayaan KdSkim = (ContentDataSkimAkadPembiayaan) vCdSkim.get(i);
                                    valueCdSkim.add(String.valueOf(KdSkim.getKodeOjk()));
                                    keyCdSkim.add(KdSkim.getKodeOjk()+"-"+KdSkim.getNamaAkadPembiayaan());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_AKAD], null, ""+kredit.getKodeAkad(), valueCdSkim, keyCdSkim, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_AKAD]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox	
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_NO_AKAD_AWAL]+"'></i> "
                            + "No. Akad Awal <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NO_AKAD_AWAL] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NO_AKAD_AWAL] +"' value='"+kredit.getNoAkadAwal()+"' data-number='true' data-alpha='true' data-special='( ) _ - ' . /' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_NO_AKAD_AWAL]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_AKAD_AWAL]+"'></i> "
                                + "Tanggal Akad Awal <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_AKAD_AWAL] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_AKAD_AWAL] +"' value='"+tglAkadAwal+"' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_AKAD_AWAL]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_NO_AKAD_AKHIR]+"'></i> "
                            + "No. Akad Akhir <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NO_AKAD_AKHIR] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NO_AKAD_AKHIR] +"' value='"+kredit.getNoAkadAkhir()+"' data-number='true' data-alpha='true' data-special='( ) _ - ' . /'  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_NO_AKAD_AKHIR]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_AKAD_AKHIR]+"'></i> "
                            + "Tanggal Akad Akhir <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_AKAD_AKHIR] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_AKAD_AKHIR] +"' value='"+tglAkadAkhir+"' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_AKAD_AKHIR]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_BARU_PERPANJANGAN]+"'></i> "
                            + "Baru Perpanjangan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //+ "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_BARU_PERPANJANGAN] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_BARU_PERPANJANGAN] +"' value='"+kredit.getBaruPerpanjangan()+"' data-required='required'>"
//                                
//                                Vector valueBaruPerpanjang = new Vector();
//                                Vector keyCdBaruPerpanjang = new Vector();
//                                valueBaruPerpanjang.add("");
//                                keyCdBaruPerpanjang.add("-- Pilih --");
//                                valueBaruPerpanjang.add("0");
//                                keyCdBaruPerpanjang.add("Fasilitas kredit/pembiayaan adalah fasilitas baru");
//                                valueBaruPerpanjang.add("1");
//                                keyCdBaruPerpanjang.add("Fasilitas kredit/pembiayaan adalah fasilitas yang telah diperpanjang 1 (satu) kali");
//                                valueBaruPerpanjang.add("2");
//                                keyCdBaruPerpanjang.add("Fasilitas kredit/pembiayaan adalah fasilitas yang telah diperpanjang 2 (2) kali");
//                                //returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_BARU_PERPANJANGAN], null, ""+kredit.getBaruPerpanjangan(), valueBaruPerpanjang, keyCdBaruPerpanjang, "data-required='required' onchange=\"javascript:changeFunc();\" ", "form-control");
                                returnData += "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_BARU_PERPANJANGAN] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_BARU_PERPANJANGAN] +"' value='"+kredit.getBaruPerpanjangan()+"' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_BARU_PERPANJANGAN]+"'>";
                                returnData += "</div>"
			//+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_AWAL]+"'></i> "
                                + "Tanggal Awal Kredit <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_AWAL] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_AWAL] +"' value='"+tglAwalKredit+"' data-required='required' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_AWAL]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_MULAI]+"'></i> "
                            + "Tanggal Mulai <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_MULAI] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_MULAI] +"' value='"+tglMulai+"' data-required='required' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_MULAI]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_TEMPO]+"'></i> "
                                + "Tanggal Jatuh Tempo <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_TEMPO] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_TEMPO] +"' value='"+tglJatuhTempo+"' data-required='required' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_TEMPO]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_KAT_DBITUR]+"'></i> "
                            + "Kode Kategori Debitur <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			      //start comboBox
                                Vector vCdKatDeb =  PstContentDataKategoriDebitur.listWithoutOid(0,0,"","");
                                Vector valueCdKatDeb = new Vector();
                                Vector keyCdKatDeb = new Vector();
                                valueCdKatDeb.add("");
                                keyCdKatDeb.add("-- Pilih --");
                                for (int i = 0; i < vCdKatDeb.size(); i++) {
                                    ContentDataKategoriDebitur KdKatDeb = (ContentDataKategoriDebitur) vCdKatDeb.get(i);
                                    valueCdKatDeb.add(String.valueOf(KdKatDeb.getKodeOjk()));
                                    keyCdKatDeb.add(KdKatDeb.getKodeOjk()+"-"+KdKatDeb.getNamaKategoriDebitur());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_KAT_DBITUR], null, ""+kredit.getKodeKatDbitur(), valueCdKatDeb, keyCdKatDeb, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_KAT_DBITUR]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox	
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_JENIS_PENGGUNAAN]+"'></i> "
                            + "Kode Jenis Penggunaan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			        //start comboBox
                                Vector vCdJenisPenggunaan =  PstContentDataJenisPenggunaan.listWithoutOid(0,0,"","");
                                Vector valueCdJenisPenggunaan = new Vector();
                                Vector keyCdJenisPenggunaan = new Vector();
                                valueCdJenisPenggunaan.add("");
                                keyCdJenisPenggunaan.add("-- Pilih --");
                                for (int i = 0; i < vCdJenisPenggunaan.size(); i++) {
                                    ContentDataJenisPenggunaan KdJenisPenggunaan = (ContentDataJenisPenggunaan) vCdJenisPenggunaan.get(i);
                                    valueCdJenisPenggunaan.add(String.valueOf(KdJenisPenggunaan.getKodeOjk()));
                                    keyCdJenisPenggunaan.add(KdJenisPenggunaan.getKodeOjk()+"-"+KdJenisPenggunaan.getJenisPenggunaan());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_JENIS_PENGGUNAAN], null, ""+kredit.getKodeJenisPenggunaan(), valueCdJenisPenggunaan, keyCdJenisPenggunaan, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_JENIS_PENGGUNAAN]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox	
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_ORIENTASI_PENGGUNAAN]+"'></i> "
                                + "Kode Orientasi Penggunaan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdOrientasi =  PstContentDataOrientasiPenggunaan.listWithoutOid(0,0,"","");
                                Vector valueCdOrientasi = new Vector();
                                Vector keyCdOrientasi = new Vector();
                                valueCdOrientasi.add("");
                                keyCdOrientasi.add("-- Pilih --");
                                for (int i = 0; i < vCdOrientasi.size(); i++) {
                                    ContentDataOrientasiPenggunaan KdOrientasi = (ContentDataOrientasiPenggunaan) vCdOrientasi.get(i);
                                    valueCdOrientasi.add(String.valueOf(KdOrientasi.getKodeOjk()));
                                    keyCdOrientasi.add(KdOrientasi.getKodeOjk()+""+KdOrientasi.getNamaOrientasiPenggunaan());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_ORIENTASI_PENGGUNAAN], null, ""+kredit.getKodeOrientasiPenggunaan(), valueCdOrientasi, keyCdOrientasi, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_ORIENTASI_PENGGUNAAN]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_SEKTOR_EKONOMI]+"'></i> "
                            + "Kode Sektor Ekonomi <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdSektor =  PstContentDataSektorEkonomi.listWithoutOid(0,0,"","");
                                Vector valueCdSektor = new Vector();
                                Vector keyCdSektor = new Vector();
                                valueCdSektor.add("");
                                keyCdSektor.add("-- Pilih --");
                                for (int i = 0; i < vCdSektor.size(); i++) {
                                    ContentDataSektorEkonomi KdSektorEkonomi = (ContentDataSektorEkonomi) vCdSektor.get(i);
                                    valueCdSektor.add(String.valueOf(KdSektorEkonomi.getKodeOjk()));
                                    keyCdSektor.add(KdSektorEkonomi.getKodeOjk()+"-"+KdSektorEkonomi.getNamaSektorEkonomi());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_SEKTOR_EKONOMI], null, ""+kredit.getKodeSektorEkonomi(), valueCdSektor, keyCdSektor, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_SEKTOR_EKONOMI]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
                    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_KAB]+"'></i> "
                            + "Kode Kab/Kota (DATI 2) Lokasi Proyek/Penggunaan Kredit <i style='color:red'>m*</i></label>"
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

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_KAB], null, ""+kredit.getKodeKab(), valueCdKab, keyCdKab, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_KAB]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_NILAI]+"'></i> "
                            + "Nilai Proyek <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NILAI] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NILAI] +"' value='"+Formater.formatNumber(kredit.getNilai(),"###,###,##0")+"' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_NILAI]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>Kode Valuta <i style='color:red'>(m*</i></label>"
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
                                    keyCdValuta.add(KdValuta.getKodeOjk()+"-"+KdValuta.getNamaValuta());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_VALUTA], null, ""+kredit.getKodeValuta(), valueCdValuta, keyCdValuta, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_VALUTA]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_PROSENTASE_BUNGA]+"'></i> "
                            + "Prosentase Suku Bunga/Imbalan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_PROSENTASE_BUNGA] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_PROSENTASE_BUNGA] +"' value='"+kredit.getProsentaseBunga()+"' data-required='required' data-number='true' data-alpha='false' data-special=','  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_PROSENTASE_BUNGA]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_JENIS_BUNGA]+"'></i> "
                                + "Jenis Suku Bunga/Imbalan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			    Vector listSukuBunga = PstContentDataSukuBunga.listWithoutOid(0, 0, "", "");
                            Vector sukuBungaKey = new Vector(1,1);
                            Vector sukuBungaVal = new Vector(1,1);
                            if(listSukuBunga.size() > 0){
                                for(int i = 0; i < listSukuBunga.size(); i++){
                                    ContentDataSukuBunga contentDataSukuBunga = (ContentDataSukuBunga) listSukuBunga.get(i);
                                    sukuBungaKey.add(""+contentDataSukuBunga.getKodeOjk());
                                    sukuBungaVal.add(contentDataSukuBunga.getKodeOjk()+"-"+contentDataSukuBunga.getNamaSukuBunga());
                                }
                            }
                                
                            returnData+=""
			    + ControlCombo.draw(FrmKredit.fieldNames[FrmKredit.FRM_FLD_JENIS_BUNGA], "-- Pilih --", ""+kredit.getJenisBunga(), sukuBungaKey, sukuBungaVal, "data-required='required'  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_JENIS_BUNGA]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KREDIT_PEMERINTAH]+"'></i> "
                            + "Kredit Program Pemerintah<i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //+ "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_KREDIT_PEMERINTAH] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_KREDIT_PEMERINTAH] +"' value='"+kredit.getKreditPemerintah()+"' data-required='required'>"
                                Vector vProgramPemerintah =  PstContentDataKodeProgramPemerintah.listtanpaoid(0,0,"","");
                                Vector valueCdProgramPemerintah = new Vector();
                                Vector keyCdProgramPemerintah = new Vector();
                                valueCdProgramPemerintah.add("");
                                keyCdProgramPemerintah.add("-- Pilih --");
                                for (int i = 0; i < vProgramPemerintah.size(); i++) {
                                    ContentDataKodeProgramPemerintah contentDataKodeProgramPemerintah = (ContentDataKodeProgramPemerintah) vProgramPemerintah.get(i);
                                    valueCdProgramPemerintah.add(String.valueOf(contentDataKodeProgramPemerintah.getKodeOjk()));
                                    keyCdProgramPemerintah.add(contentDataKodeProgramPemerintah.getKodeOjk()+"-"+contentDataKodeProgramPemerintah.getNamaProgramPemerintah());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KREDIT_PEMERINTAH], null, ""+kredit.getKreditPemerintah(), valueCdProgramPemerintah, keyCdProgramPemerintah, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KREDIT_PEMERINTAH]+"'", "form-control");
                                returnData += "</div>"
			//+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TAKEOVER]+"'></i> "
                            + "Takeover Dari <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>";
			        //start comboBox
                                Vector vCdTakeOver =  PstContentDataTakeover.list(0,0,"","");
                                Vector valueCdTakeOver = new Vector();
                                Vector keyCdTakeOver = new Vector();
                                valueCdTakeOver.add("");
                                keyCdTakeOver.add("-- Pilih --");
                                for (int i = 0; i < vCdTakeOver.size(); i++) {
                                    ContentDataTakeover KdTakeover = (ContentDataTakeover) vCdTakeOver.get(i);
                                    valueCdTakeOver.add(String.valueOf(KdTakeover.getKodeOjk()));
                                    keyCdTakeOver.add(KdTakeover.getKodeOjk()+"-"+KdTakeover.getNamaTakeover());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_TAKEOVER], null, ""+kredit.getTakeover(), valueCdTakeOver, keyCdTakeOver, "onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TAKEOVER]+"'", "form-control");
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
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_SUMBER_DANA]+"'></i> "
                            + "Sumber Dana <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                                        
                               Vector vSumberDana =  PstContentDataSumberDanaKredit.list(0,0,"","");
                                Vector valueCdSumberDana = new Vector();
                                Vector keyCdSumberDana = new Vector();
                                valueCdTakeOver.add("");
                                keyCdTakeOver.add("-- Pilih --");
                                for (int i = 0; i < vSumberDana.size(); i++) {
                                    ContentDataSumberDanaKredit contentDataSumberDanaKredit = (ContentDataSumberDanaKredit) vSumberDana.get(i);
                                    valueCdSumberDana.add(String.valueOf(contentDataSumberDanaKredit.getKodeOjk()));
                                    keyCdSumberDana.add(contentDataSumberDanaKredit.getKodeOjk()+"-"+contentDataSumberDanaKredit.getSumberDanaKredit());
                                }         
                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_SUMBER_DANA], null, ""+kredit.getSumberDana(), valueCdSumberDana, keyCdSumberDana, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_SUMBER_DANA]+"'", "form-control");
                                returnData += "</div>"       
			    //+ "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_SUMBER_DANA] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_SUMBER_DANA] +"' value='"+kredit.getSumberDana()+"' data-required='required'>"
			//+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_PLAFON_AWAL]+"'></i> "
                            + "Plafon Awal <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_PLAFON_AWAL] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_PLAFON_AWAL] +"' value='"+ Formater.formatNumber(kredit.getPlafonAwal(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_PLAFON_AWAL]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_PLAFON]+"'></i> "
                                + "Plafon <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_PLAFON] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_PLAFON] +"' value='"+Formater.formatNumber(kredit.getPlafon(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_PLAFON]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_REALISASI]+"'></i> "
                            + "Realisasi/Pencairan Bulan Berjalan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_REALISASI] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_REALISASI] +"' value='"+Formater.formatNumber(kredit.getRealisasi(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_REALISASI]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_DENDA]+"'></i> "
                            + "Denda <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_DENDA] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_DENDA] +"' value='"+Formater.formatNumber(kredit.getDenda(),"###,##,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_DENDA]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_BAKI_DEBET]+"'></i> "
                            + "Baki Debet <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_BAKI_DEBET] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_BAKI_DEBET] +"' value='"+Formater.formatNumber(kredit.getBakiDebet(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_BAKI_DEBET]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_NILAI_UANG_ASAL]+"'></i> "
                            + "Nilai Dalam Mata Uang Asal <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NILAI_UANG_ASAL] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_NILAI_UANG_ASAL] +"' value='"+Formater.formatNumber(kredit.getNilaiUangAsal(),"###,###,##0")+"' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_NILAI_UANG_ASAL]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_KOLEKTIBILITAS]+"'></i> "
                            + "Kode Kolektibilitas <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdKolektibilitas =  PstContentDataKolektibilitas.list(0,0,"","");
                                Vector valueCdKolektibilitas = new Vector();
                                Vector keyCdKolektibilitas = new Vector();
                                valueCdKolektibilitas.add("");
                                keyCdKolektibilitas.add("-- Pilih --");
                                for (int i = 0; i < vCdKolektibilitas.size(); i++) {
                                    ContentDataKolektibilitas KdKolektibilitas = (ContentDataKolektibilitas) vCdKolektibilitas.get(i);
                                    valueCdKolektibilitas.add(String.valueOf(KdKolektibilitas.getKodeOjk()));
                                    keyCdKolektibilitas.add(KdKolektibilitas.getKodeOjk()+"-"+KdKolektibilitas.getNamaKolektibilitas());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_KOLEKTIBILITAS], null, ""+kredit.getKodeKolektibilitas(), valueCdKolektibilitas, keyCdKolektibilitas, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_KOLEKTIBILITAS]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_MACET]+"'></i> "
                            + "Tanggal Macet <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_MACET] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_MACET] +"' value='"+tglMacet+"' "+tglsebabmacet+" data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_MACET]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_SEBAB_MACET]+"'></i> "
                            + "Kode Sebab Macet <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdMacet =  PstContentDataSebabMacet.list(0,0,"","");
                                Vector valueCdMacet = new Vector();
                                Vector keyCdMacet = new Vector();
                                valueCdMacet.add("");
                                keyCdMacet.add("-- Pilih --");
                                for (int i = 0; i < vCdMacet.size(); i++) {
                                    ContentDataSebabMacet KdMacet = (ContentDataSebabMacet) vCdMacet.get(i);
                                    valueCdMacet.add(String.valueOf(KdMacet.getKodeOjk()));
                                    keyCdMacet.add(KdMacet.getKodeOjk()+"-"+KdMacet.getSebabMacet());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_SEBAB_MACET], null, ""+kredit.getKodeSebabMacet(), valueCdMacet, keyCdMacet, " onchange=\"javascript:changeFunc();\"  "+kodesebabmacet+" data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_SEBAB_MACET]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TUNGGAKAN_POKOK]+"'></i> "
                            + "Tunggakan Pokok <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TUNGGAKAN_POKOK] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TUNGGAKAN_POKOK] +"' value='"+Formater.formatNumber(kredit.getTunggakanPokok(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special=''  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TUNGGAKAN_POKOK]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TUNGGAKAN_BUNGA]+"'></i> "
                            + "Tunggakan Bunga <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TUNGGAKAN_BUNGA] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TUNGGAKAN_BUNGA] +"' value='"+Formater.formatNumber(kredit.getTunggakanBunga(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special=''  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TUNGGAKAN_BUNGA]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_JML_HARI_TUNGGAKAN]+"'></i> "
                            + "Jumlah Hari Tunggakan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_JML_HARI_TUNGGAKAN] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_JML_HARI_TUNGGAKAN] +"' value='"+kredit.getJmlHariTunggakan()+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_JML_HARI_TUNGGAKAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_FREKUENSI_TUNGGAKAN]+"'></i> "
                            + "Frekuensi Tunggakan <i style='color:red'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_FREKUENSI_TUNGGAKAN] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_FREKUENSI_TUNGGAKAN] +"' value='"+kredit.getFrekuensiTunggakan()+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_FREKUENSI_TUNGGAKAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_FREKUENSI_RESTRUKTURISASI]+"'></i> "
                            + "Frekuensi Restrukturisasi <i style='color:red'>(m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_FREKUENSI_RESTRUKTURISASI] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_FREKUENSI_RESTRUKTURISASI] +"' value='"+kredit.getFrekuensiRestrukturisasi()+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_FREKUENSI_TUNGGAKAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_RESTRUKTURISASI_AWAL]+"'></i> "
                            + "Tanggal Restrukturisasi Awal <i style='color:red'>(c**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_RESTRUKTURISASI_AWAL] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_RESTRUKTURISASI_AWAL] +"' value='"+tglRestrukturisasiAwal+"'  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_RESTRUKTURISASI_AWAL]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_RESTRUKTURISASI_AKHIR]+"'></i> "
                            + "Tanggal Restrukturisasi Akhir <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_RESTRUKTURISASI_AKHIR] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_RESTRUKTURISASI_AKHIR] +"' value='"+tglRestrukturisasiAkhir+"' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_RESTRUKTURISASI_AKHIR]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_CARA]+"'></i> "
                            + "Kode Cara Restrukturisasi <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdCara =  PstContentDataCaraRestrukturisasi.list(0,0,"","");
                                Vector valueCdCara = new Vector();
                                Vector keyCdCara = new Vector();
                                valueCdCara.add("");
                                keyCdCara.add("-- Pilih --");
                                for (int i = 0; i < vCdCara.size(); i++) {
                                    ContentDataCaraRestrukturisasi KdCara = (ContentDataCaraRestrukturisasi) vCdCara.get(i);
                                    valueCdCara.add(String.valueOf(KdCara.getKodeOjk()));
                                    keyCdCara.add(KdCara.getKodeOjk()+"-"+KdCara.getCaraRestrukturisasi());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_CARA], null, ""+kredit.getKodeCara(), valueCdCara, keyCdCara, " onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_CARA]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_KONDISI]+"'></i> "
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
                                    keyCdKondisi.add(KdKondisi.getKodeOjk()+"-"+KdKondisi.getNamaKondisi());
                                }

                                returnData += ControlCombo.draw(""+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_KONDISI], null, ""+kredit.getKodeKondisi(), valueCdKondisi, keyCdKondisi, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_KONDISI]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_TGL_KONDISI]+"'></i> "
                            + "Tanggal Kondisi <i style='color:red'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_KONDISI] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_TGL_KONDISI] +"' value='"+tglKondisi+"' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_TGL_KONDISI]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KETERANGAN]+"'></i> "
                            + "Keterangan <i style='color:red'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_KETERANGAN] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_KETERANGAN] +"' value='"+kredit.getKeterangan()+"' data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KETERANGAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmKredit.fieldQuestion[FrmKredit.FRM_FLD_KODE_KANTOR_CABANG]+"'></i> "
                            + "Kode Kantor Cabang <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
			    Vector vCdKantorCbg =  PstContentDataKantorCabang.list(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+kredit.getKodeKantorCabang()+"'","");
                            ContentDataKantorCabang kdKantorCbg = new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKantorCbg.size(); i++) {
                                kdKantorCbg = (ContentDataKantorCabang) vCdKantorCbg.get(i);
                                
                            }
                          
                            returnData += "<input type='hidden' name='"+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_KANTOR_CABANG]+"' value='"+kredit.getKodeKantorCabang()+"'>"
                                    + "<input type='text' name='DISPLAY_"+FrmKredit.fieldNames[FrmKredit.FRM_FLD_KODE_KANTOR_CABANG]+"' class='form-control' value='"+kdKantorCbg.getKodeOjk()+" "+kdKantorCbg.getNamaKantorCabang()+"' readonly='readonly' data-required='required'  data-type='text' data-error-message='"+FrmKredit.fieldError[FrmKredit.FRM_FLD_KODE_KANTOR_CABANG]+"'>";
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
//		    + "<div class='form-group'>"
//			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'>*</font></label>"
//			+ "<div class='col-sm-8'>"
//			    + "<input type='text' class='form-control' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_OPERASI_DATA] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_OPERASI_DATA] +"' value='"+kredit.getOperasiData()+"' data-required='required'>"
//			+ "</div>"
//		    + "</div>"
                    + "<div class='form-group hidden'>"
			+ "<label class='control-label col-sm-4'>Open Date <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			     + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_OPEN_DATE] +"' id='"+ FrmKredit.fieldNames[FrmKredit.FRM_FLD_OPEN_DATE] +"' value='"+openDate+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
                    + "<div class='form-group hidden'>"
			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'>*</font></label>"
			+ "<div class='col-sm-8'>";
			    Vector statusDataKey = new Vector(1,1);
                            Vector statusDataVal = new Vector(1,1);
                            for(int i = 0; i < PstDebitur.operasiData.length; i++){
                                statusDataKey.add(""+PstDebitur.operasiDataKey[i]);
                                statusDataVal.add(""+PstDebitur.operasiData[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmKredit.fieldNames[FrmKredit.FRM_FLD_STATUS_DATA], "-- Pilih --", ""+kredit.getStatusData(), statusDataKey, statusDataVal, "", "form-control")
                            + "<input type='hidden' name='"+FrmKredit.fieldNames[FrmKredit.FRM_FIELD_STATUS_PERUBAHAN_DATA]+"' value='0'>"
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
                            
        return returnData;
    }
    
    public String drawListKredit(int iCommand, Vector objectClass) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("No Rekening", "200px");//2
        ctrlist.addHeader("CIF", "200px");//10
        ctrlist.addHeader("Kode Sifat Kredit", "200px");//10
        ctrlist.addHeader("Nama Jenis Kredit", "200px");//10
        ctrlist.addHeader("Kode Skim/Akad Pembiayaan", "200px");//10
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
            Kredit kredit = new Kredit();
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+kredit.getNoRekening());//2
            rowx.add(""+kredit.getCif());//3
            rowx.add(""+kredit.getKodeSifat());//4
            rowx.add(""+kredit.getKodeJenisKredit());//5
            rowx.add(""+kredit.getKodeAkad());//6       
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
                    + "<label>Kode Jenis Kredit</label>"
                    + "<input type='text' name='kodejeniskredit' class='form-control' id='kodejeniskredit'>"
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
                            + "<th>Kode Jenis Kredit</th>"
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
                        + "<th>Kode Jenis Kredit</th>"
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
            whereClause = "kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='0'";
        }else if(this.dataFor.equals("loadsummarynull")){
            whereClause = "kredit."+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
        }else{
            whereClause = "l."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if(userGroup != 4){
            if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
                if(!kodeCabang.equals("")){
                    whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'  ";
                }
                whereClause += " AND kredit."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                if(!kodeCabang.equals("")){
                    whereClause += " AND l."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' ";
                }
            }
        }
        int summary = 0;
        if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            summary = PstKredit.getCountJoin(whereClause);
        }else{
            summary = SessionProsesPerlengkapan.getCountJoin(whereClause);
        }
        returnData +=""+summary;
        return returnData;
    }
    
    public String otherForm(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        
        String whereClause = "l."+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periodeId+"'";
        if(userGroup != 4){
            if(!kodeCabang.equals("")){
                whereClause += " AND l."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        Vector listKredit = SessionProsesPerlengkapan.listOtherKredit(0, 0, whereClause, "l."+PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
        returnData+=""
        + "<span class='pull-right'><input type='checkbox' class='checkall' value='0'> Check All</span>"
        + "<table class='table table-bordered table-strped'>"
            + "<thead>"
                + "<tr>"
                    + "<th>No.</th>"
                    + "<th>CIF</th>"
                    + "<th>No Rekening</th>"
                    + "<th>Kode Jenis Kredit</th>"
                    + "<th>Aksi</th>"
                + "</tr>"
            + "</thead>"
            + "<tbody>";
                for(int i = 0; i < listKredit.size(); i++){
                    Kredit kredit = (Kredit) listKredit.get(i);
                    returnData+=""
                    + "<tr>"
                        + "<td>"+(i+1)+"</td>"
                        + "<td>"+kredit.getCif()+"</td>"
                        + "<td>"+kredit.getNoRekening()+"</td>"
                        + "<td>"+kredit.getKodeJenisKredit()+"</td>"
                        + "<td><input type='checkbox' name='delete' value='"+kredit.getOID()+"' class='deletelist'></td>"
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
            CtrlKredit ctrlKredit = new CtrlKredit(request);
            ctrlKredit.action(Command.DELETE, oid, 0, "");
            String msg = ctrlKredit.getMessage();
            returnData = "<i class='fa fa-info'></i> "+msg;
        }
        
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        CtrlKredit ctrlKredit = new CtrlKredit(request);
        ctrlKredit.action(this.iCommand, this.oid, oidDelete);
        String msg = "<i class='fa fa-info'></i> "+ctrlKredit.getMessage();
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
