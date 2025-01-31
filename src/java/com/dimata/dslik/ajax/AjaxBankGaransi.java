/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;
import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.bankgaransi.BankGaransi;
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.form.bankgaransi.*;
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
public class AjaxBankGaransi extends HttpServlet {

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
        
        //BOOLEAN
        privAdd = FRMQueryString.requestBoolean(request, "privadd");
        privUpdate = FRMQueryString.requestBoolean(request, "privupdate");
        privDelete = FRMQueryString.requestBoolean(request, "privdelete");
	this.privView = FRMQueryString.requestBoolean(request, "privview");
	//STRING
	this.dataFor = FRMQueryString.requestString(request, "FRM_FIELD_DATA_FOR");
	this.oidDelete = FRMQueryString.requestString(request, "FRM_FIELD_OID_DELETE");
	this.approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
	this.htmlReturn = "";
        this.cif = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
	
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
        String debitur ="";
        if(!norekening.equals("")){
             debitur = prosesTransferDataBank.actionTransferDataBankGaransi(""+norekening,periodeId, 1,1);
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
        }else if(this.dataFor.equals("showlistbankgaransi")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull") || this.dataFor.equals("loadsummaryotherbankgaransi")){
            this.htmlReturn = getSummary(request);
        }else if(this.dataFor.equals("showotherform")){
            this.htmlReturn = otherForm(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = saveBankGaransi(request);
        }
    }
    
    public String saveBankGaransi(HttpServletRequest request) {
        //history
        String returnData = "";
        oid = FRMQueryString.requestLong(request, "FRM_FIELD_OID_BANK_GARANSI");
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        CtrlBankGaransi ctrlBankGaransi = new CtrlBankGaransi(request);
        ctrlBankGaransi.action(iCommand, oid,userId,userName);
        returnData = ctrlBankGaransi.getMessage();
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
                PstBankGaransi.fieldNames[PstBankGaransi.FLD_BANK_GARANSI_OID],
                PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF],
                PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING],
                PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("listdatabankgaransi") || this.dataFor.equals("listdatabankgaransihome")){
            String[] cols = {
                "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_BANK_GARANSI_OID]+"",
                "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+"",
                "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"",
                "jg."+PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI]+"",
                "tg."+PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI]+"",
                "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_AKAD_AWAL]+""
            };
            jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }else if(this.dataFor.equals("showlistbankgaransi")){
	    String[] cols = { 
                PstBankGaransi.fieldNames[PstBankGaransi.FLD_BANK_GARANSI_OID],
                PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF],
                PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING],
                PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("showform2")){
            String[] cols = {
                "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_BANK_GARANSI_OID]+"",
                "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+"",
                "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"",
                "jg."+PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI]+"",
                "tg."+PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI]+"",
                "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_AKAD_AWAL]+""
            };
            jSONObject = listDataTables(request, response, cols, "listdatabankgaransi", this.jSONObject);
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
        String norekening = FRMQueryString.requestString(request, "norekening");
        String kodejenisgaransi = FRMQueryString.requestString(request, "kodejenisgaransi");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        String whereClause = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
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
        
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                if(!kodeCabang.equals("")){
                    whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                }
                whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"' ";
            }
        }
        
        if(dataFor.equals("listdatasearch")){
	    if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(norekening.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+"='"+norekening+"'";
                }else{
                    whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+"='"+norekening+"'";
                }
            }

            if(kodejenisgaransi.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+"='"+kodejenisgaransi+"'";
                }else{
                    whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+"='"+kodejenisgaransi+"'";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if (dataFor.equals("listdatabankgaransi") || this.dataFor.equals("listdatabankgaransihome")){
            whereClause = " 1=1";
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause += ""
                + " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cifParam+"'"
                + " AND ("
                    + " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                    + " OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_AKAD_AWAL]+" like '%"+this.searchTerm+"%'"
                    + " OR tg."+PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI]+" like '%"+this.searchTerm+"%'"
                    + " OR jg."+PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI]+" like '%"+this.searchTerm+"%'"
                + " )";            
        }else if(dataFor.equals("showlistbankgaransi")){
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{
                    whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }else{
                    whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }
            }
	    if(whereClause.length() > 0){
		whereClause += " AND (bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+" LIKE '%"+this.searchTerm+"%')";
	    }
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstBankGaransi.getCountJoin(whereClause);
	}else if (dataFor.equals("listdatabankgaransi") || this.dataFor.equals("listdatabankgaransihome")){
            total = PstBankGaransi.getCountJoin(whereClause);
        }else if(dataFor.equals("showlistbankgaransi")){
            total = PstBankGaransi.getCountJoin(whereClause);
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
    
    public JSONObject getData(int total, HttpServletRequest request, String dataforx){
        
        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        BankGaransi bankGaransi = new BankGaransi();
	
	String cif = FRMQueryString.requestString(request, "cif");
        String norekening = FRMQueryString.requestString(request, "norekening");
        String kodejenisgaransi = FRMQueryString.requestString(request, "kodejenisgaransi");
        String whereClause = "";
        String order ="";
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
        Periode periode = new Periode();
        try{
            Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            if(listPeriode != null){
                periode = (Periode) listPeriode.get(0);
                periodeId=periode.getOID();
            }
        }catch(Exception ex){

        }
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                if(!kodeCabang.equals("")){
                    whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' AND ";
                }
                whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }
        }
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(dataforx.equals("listdatasearch")){
		if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(norekening.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+"='"+norekening+"'";
                    }else{
                        whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+"='"+norekening+"'";
                    }
                }

                if(kodejenisgaransi.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+"='"+kodejenisgaransi+"'";
                    }else{
                        whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+"='"+kodejenisgaransi+"'";
                    }
                }
		if(whereClause.length() > 0){
                    whereClause += " AND (bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if (dataforx.equals("listdatabankgaransi") || this.dataFor.equals("listdatabankgaransihome")){
                whereClause = " 1=1";
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause += ""
                    + " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cifParam+"'"
                   // + " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'"
                    + " AND ("
                        + " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                        + " OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_AKAD_AWAL]+" like '%"+this.searchTerm+"%'"
                        + " OR tg."+PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI]+" like '%"+this.searchTerm+"%'"
                        + " OR jg."+PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI]+" like '%"+this.searchTerm+"%'"
                    + " )";   
            }else if(dataforx.equals("showlistbankgaransi")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause += " bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                }
                if(whereClause.length() > 0){
                    whereClause += " AND (bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_JENIS_GARANSI]+" LIKE '%"+this.searchTerm+"%')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(dataforx.equals("listdatasearch")){
	    listData = PstBankGaransi.listJoin(start, amount, whereClause, order);
	}else if (dataforx.equals("listdatabankgaransi") || this.dataFor.equals("listdatabankgaransihome")){
            listData = PstBankGaransi.listJoin(start, amount, whereClause, order);
        }else if(dataforx.equals("showlistbankgaransi")){
            listData = PstBankGaransi.listJoin(start, amount, whereClause, order);
        }
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
            String buttunUpdate = "";
	    if(dataforx.equals("listdatasearch")){
		bankGaransi = (BankGaransi) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+bankGaransi.getCif());
                ja.put(""+(bankGaransi.getNoRekening()== null ? "-" : bankGaransi.getNoRekening()));
                ja.put(""+(bankGaransi.getKodeJenisGaransi()== null ? "-" : bankGaransi.getKodeJenisGaransi()));
                
                if(privView){
                    buttunUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+bankGaransi.getDebiturOid()+"&type="+bankGaransi.getDebiturType()+"&cif="+bankGaransi.getCif()+"&activetab=AjaxBankGaransi&otheroid="+bankGaransi.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttunUpdate);
		
		array.put(ja);
	    }else if (dataforx.equals("listdatabankgaransi")){
                bankGaransi = (BankGaransi) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(bankGaransi.getNoRekening()));
                ja.put(""+(bankGaransi.getCif()));
                ja.put(""+(bankGaransi.getJenisGaransi()));
                ja.put(""+(bankGaransi.getTujuanGaransi()));
                ja.put(""+(bankGaransi.getNoAkadAwal()));
                
                if(privView){
                    buttunUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+bankGaransi.getDebiturOid()+"&type="+bankGaransi.getDebiturType()+"&cif="+bankGaransi.getCif()+"&activetab=AjaxBankGaransi&otheroid="+bankGaransi.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                
                int count  = PstLogSysHistory.getCount(PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+bankGaransi.getOID()+"' "
                        + "AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" LIKE '%Transfer%'");
                
                if(count == 0){
                    buttunUpdate +="<div class='btn btn-default'><input type='checkbox' class='deletelist' value='"+bankGaransi.getOID()+"'></div>";
                }
                ja.put(buttunUpdate);
                array.put(ja);
            }else if (dataforx.equals("listdatabankgaransihome")){
                bankGaransi = (BankGaransi) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(bankGaransi.getNoRekening()));
                ja.put(""+(bankGaransi.getCif()));
                ja.put(""+(bankGaransi.getJenisGaransi()));
                ja.put(""+(bankGaransi.getTujuanGaransi()));
                ja.put(""+(bankGaransi.getNoAkadAwal()));
                if(privView){
                    buttunUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+bankGaransi.getDebiturOid()+"&type="+bankGaransi.getDebiturType()+"&cif="+bankGaransi.getCif()+"&activetab=AjaxBankGaransi&otheroid="+bankGaransi.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                ja.put(buttunUpdate);
                array.put(ja);
            }else if(dataforx.equals("showlistbankgaransi")){
		bankGaransi = (BankGaransi) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+bankGaransi.getCif());
                ja.put(""+(bankGaransi.getNoRekening()== null ? "-" : bankGaransi.getNoRekening()));
                ja.put(""+(bankGaransi.getKodeJenisGaransi()== null ? "-" : bankGaransi.getKodeJenisGaransi()));
                if(privView){
                    buttunUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+bankGaransi.getDebiturOid()+"&type="+bankGaransi.getDebiturType()+"&cif="+bankGaransi.getCif()+"&activetab=AjaxBankGaransi&otheroid="+bankGaransi.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttunUpdate);
		
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
        
        /*BankGaransi bankGaransi = new BankGaransi();
        Date dNow = new Date();
        String tglTerbit = "";
        String tglTempo = "";
        String tglAkadAwal = "";
        String tglAkadAkhir = "";
        String tglWanPrestasi = "";
        String tglKondisi = "";
        String openDate = "";
        if (oid != 0) {
            try {
                bankGaransi = PstBankGaransi.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            tglTerbit = Formater.formatDate(bankGaransi.getTglDiterbitkan(), "yyyy-MM-dd");
            tglTempo = Formater.formatDate(bankGaransi.getTglJatuhTempo(), "yyyy-MM-dd");
            tglAkadAwal = Formater.formatDate(bankGaransi.getTglAkadAwal(), "yyyy-MM-dd");
            tglAkadAkhir = Formater.formatDate(bankGaransi.getTglAkadAkhir(), "yyyy-MM-dd");
            tglWanPrestasi = Formater.formatDate(bankGaransi.getTglWanPrestasi(), "yyyy-MM-dd");
            tglKondisi = Formater.formatDate(bankGaransi.getTglKondisi(), "yyyy-MM-dd");
            openDate = Formater.formatDate(bankGaransi.getOpenDate(), "yyyy-MM-dd");
        }
        else {
            tglTerbit = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglTempo = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglAkadAwal = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglAkadAkhir = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglWanPrestasi = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglKondisi = Formater.formatDate(dNow, "yyyy-MM-dd");
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
        }
        
        //tampilkan listBankGaransi berdasarkan cif
        Vector listBankGaransi = new Vector();
        if(!cif.equals("")){
                String where = PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"'";
                listBankGaransi = PstBankGaransi.list(0,0, ""+where, "");
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
            String where = PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"'";
            countData = PstBankGaransi.getCount(where);
        }
        
        if (cif.length()==0){
            returnData = ""
            +"<div class='row'>"
                +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Bank Garansi</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                //+drawListBankGaransi(iCommand, listBankGaransi)
                                + "<div id='tableListBankGaransi'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"
                                                + "<td>No Rekening</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Kode Jenis Garansi</td>"
                                                + "<td>Kode Tujuan Garansi</td>"
                                                + "<td>No. Akad Awal</td>"
                                                + "<td>Aksi</td>"
                                            + "</tr>"
                                        + "</thead>"             
                                    + "</table>"
                                + "</div>"
                            +"</div>"
                             +"<div class='box-header'>";
            
                                if(privAdd){
                                    returnData+="<button type='button' class='btn btn-primary btnaddgeneralgaransi' data-for ='showform2' data-target='AjaxBankGaransi' data-oid='0' data-type='0' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data Bank Garansi</button>";
                                }
                                
                                if(privDelete){
                                    returnData+=" <button type='button' class='btn btn-danger' id='delete' data-for='deleteitem' data-target='AjaxBankGaransi' data-command='"+Command.DELETEALL+"'><i class='fa fa-trash'></i> Hapus Data Bank Garansi</button>";
                                }
                                
                                returnData+=""
                            +"</div>"
                        +"</div>"
                +"</div>"
            +"</div>"
            + "<div id='bankgaransiform'></div>";          
        }else{
            if (countData>0){
                returnData = ""
                +"<div class='row'>"
                    +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Bank Garansi</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                //+drawListBankGaransi(iCommand, listBankGaransi)
                                + "<div id='tableListBankGaransi'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"
                                                + "<td>No Rekening</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Kode Jenis Garansi</td>"
                                                + "<td>Kode Tujuan Garansi</td>"
                                                + "<td>No. Akad Awal</td>"
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
        
        BankGaransi bankGaransi = new BankGaransi();
        bankGaransi.setCif(this.cif);
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        bankGaransi.setKodeKantorCabang(kodeCabang);
        Date dNow = new Date();
        String tglTerbit = "";
        String tglTempo = "";
        String tglAkadAwal = "";
        String tglAkadAkhir = "";
        String tglWanPrestasi = "";
        String tglKondisi = "";
        String openDate = "";
        long oidPeriodeWrite = 0;
        if (oid != 0) {
            try {
                bankGaransi = PstBankGaransi.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            if (bankGaransi.getTglDiterbitkan()==null){
                tglTerbit = "";
                
            }else{
                tglTerbit = Formater.formatDate(bankGaransi.getTglDiterbitkan(), "yyyy-MM-dd");
            }
            if (bankGaransi.getTglJatuhTempo()==null){
                tglTempo = "";
            }else{
                tglTempo = Formater.formatDate(bankGaransi.getTglJatuhTempo(), "yyyy-MM-dd");
            }
            if (bankGaransi.getTglAkadAwal()==null){
                tglAkadAwal = "";
            }else{
                tglAkadAwal = Formater.formatDate(bankGaransi.getTglAkadAwal(), "yyyy-MM-dd");
            }
            if (bankGaransi.getTglAkadAkhir()==null){
                tglAkadAkhir = "";
            }else{
                tglAkadAkhir = Formater.formatDate(bankGaransi.getTglAkadAkhir(), "yyyy-MM-dd");
            }
            if (bankGaransi.getTglWanPrestasi()==null){
                tglWanPrestasi = "";
            }else{
                tglWanPrestasi = Formater.formatDate(bankGaransi.getTglWanPrestasi(), "yyyy-MM-dd");
            }
            if (bankGaransi.getTglKondisi()==null){
                tglKondisi = "";
            }else{
                tglKondisi = Formater.formatDate(bankGaransi.getTglKondisi(), "yyyy-MM-dd");
            }
            if (bankGaransi.getOpenDate()==null){
                openDate = "";
            }else{
                openDate = Formater.formatDate(bankGaransi.getOpenDate(), "yyyy-MM-dd");
            }
            
            oidPeriodeWrite = bankGaransi.getPeriodeid();
        }
        else {
            tglTerbit = Formater.formatDate(dNow, "yyyy-MM-dd");
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
        
        //tampilkan listBankGaransi berdasarkan cif
        Vector listBankGaransi = new Vector();
        if(!cif.equals("")){
                String where = PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"'";
                listBankGaransi = PstBankGaransi.list(0,0, ""+where, "");
        }
	String returnData = "<input type='hidden' value='"+oidPeriodeWrite+"' name ='FRM_FIELD_PERIODE_ID' id='oidPeriode'>"
        
                 
                        
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>View Bank Garansi</h4>"
		    + "<div class='form-group'>"
                        + "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_NO_REKENING]+"'></i> "
                            + "No. Rekening <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
                            + "<input type='hidden' class='form-control' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_PERUBAHAN_DATA] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_PERUBAHAN_DATA] +"' value='0'>"
			    + "<input type='text' class='form-control' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NO_REKENING] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NO_REKENING] +"' value='"+bankGaransi.getNoRekening()+"' data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_NO_REKENING]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_CIF]+"'></i> "
                            + "CIF <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_CIF] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_CIF] +"' value='"+bankGaransi.getCif()+"' data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_CIF]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_KODE_JENIS_GARANSI]+"'></i> "
                            + "Kode Jenis Garansi <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
			    Vector vCdJenis =  PstContentDataJenisGaransi.listtanpaoid(0,0,"","");
                            Vector valueCdJenis = new Vector();
                            Vector keyCdJenis = new Vector();
                            valueCdJenis.add("");
                            keyCdJenis.add("-- Pilih --");
                            for (int i = 0; i < vCdJenis.size(); i++) {
                                ContentDataJenisGaransi kdJenis = (ContentDataJenisGaransi) vCdJenis.get(i);
                                valueCdJenis.add(String.valueOf(kdJenis.getKodeOjk()));
                                keyCdJenis.add(kdJenis.getKodeOjk()+"-"+kdJenis.getJenisGaransi());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KODE_JENIS_GARANSI], null, ""+bankGaransi.getKodeJenisGaransi(), valueCdJenis, keyCdJenis, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_KODE_JENIS_GARANSI]+"'", "form-control");
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_KODE_TUJUAN_GARANSI]+"'></i> "
                            + "Kode Tujuan Garansi <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
			     //start comboBox
			    Vector vCdTujuan =  PstContentDataTujuanGaransi.list(0,0,"","");
                            Vector valueCdTujuan = new Vector();
                            Vector keyCdTujuan = new Vector();
                            valueCdTujuan.add("");
                            keyCdTujuan.add("-- Pilih --");
                            for (int i = 0; i < vCdTujuan.size(); i++) {
                                ContentDataTujuanGaransi kdTujuan = (ContentDataTujuanGaransi) vCdTujuan.get(i);
                                valueCdTujuan.add(String.valueOf(kdTujuan.getKodeOjk()));
                                keyCdTujuan.add(kdTujuan.getKodeOjk()+"-"+kdTujuan.getTujuanGaransi());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KODE_TUJUAN_GARANSI], null, ""+bankGaransi.getKodeTujuanGaransi(), valueCdTujuan, keyCdTujuan, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_KODE_TUJUAN_GARANSI]+"'", "form-control");
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_TGL_DITERBITKAN]+"'></i> "
                            + "Tanggal Diterbitkan <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_DITERBITKAN] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_DITERBITKAN] +"' value='"+tglTerbit+"' data-required='required' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_TGL_DITERBITKAN]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_TGL_JATUH_TEMPO]+"'></i> "
                            + "Tanggal Jatuh Tempo <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_JATUH_TEMPO] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_JATUH_TEMPO] +"' value='"+tglTempo+"' data-required='required' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_TGL_JATUH_TEMPO]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_NO_AKAD_AWAL]+"'></i> "
                            + "No. Akad Awal <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NO_AKAD_AWAL] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NO_AKAD_AWAL] +"' value='"+bankGaransi.getNoAkadAwal()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" _/()'.-\" data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_NO_AKAD_AWAL]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_TGL_AKAD_AWAL]+"'></i> "
                            + "Tanggal Akad Awal <i style='color:red;'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_AKAD_AWAL] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_AKAD_AWAL] +"' value='"+tglAkadAwal+"' data-required='required' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_TGL_AKAD_AWAL]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_NO_AKAD_AKHIR]+"'></i> "
                            + "No. Akad Akhir <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NO_AKAD_AKHIR] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NO_AKAD_AKHIR] +"' value='"+bankGaransi.getNoAkadAkhir()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" _/()'.-\" data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_NO_AKAD_AKHIR]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_TGL_AKAD_AKHIR]+"'></i> "
                            + "Tanggal Akad Akhir <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_AKAD_AKHIR] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_AKAD_AKHIR] +"' value='"+tglAkadAkhir+"' data-required='required' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_TGL_AKAD_AKHIR]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_NAMA_YG_DIJAMIN]+"'></i> "
                            + "Nama Yang Dijamin <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NAMA_YG_DIJAMIN] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NAMA_YG_DIJAMIN] +"' value='"+bankGaransi.getNamaYgDijamin()+"' data-required='required' data-number='true' data-alpha='true' data-special=\" _/()'.-\" data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_NAMA_YG_DIJAMIN]+"'>"
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>&nbsp;</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_KODE_VALUTA]+"'></i> "
                            + "Kode Valuta <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdValuta =  PstContentDataKodeValuta.list(0,0,"","");
                                Vector valueCdValuta = new Vector();
                                Vector keyCdValuta = new Vector();
                                valueCdValuta.add("");
                                keyCdValuta.add("-- Pilih --");
                                for (int i = 0; i < vCdValuta.size(); i++) {
                                    ContentDataKodeValuta KdValuta = (ContentDataKodeValuta) vCdValuta.get(i);
                                    valueCdValuta.add(String.valueOf(KdValuta.getKodeOjk()));
                                    keyCdValuta.add(KdValuta.getKodeOjk()+"-"+KdValuta.getNamaValuta());
                                }

                                returnData += ControlCombo.draw(""+FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KODE_VALUTA], null, ""+bankGaransi.getKodeValuta(), valueCdValuta, keyCdValuta, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_KODE_VALUTA]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_PLAFON]+"'></i> "
                            + "Plafon <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_PLAFON] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_PLAFON] +"' value='"+Formater.formatNumber(bankGaransi.getPlafon(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_PLAFON]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_NOMINAL]+"'></i> "
                            + "Nominal (IDR) <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NOMINAL] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_NOMINAL] +"' value='"+Formater.formatNumber(bankGaransi.getNominal(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_NOMINAL]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_SETORAN_JAMINAN]+"'></i> "
                            + "Setoran Jaminan <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_SETORAN_JAMINAN] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_SETORAN_JAMINAN] +"' value='"+Formater.formatNumber(bankGaransi.getSetoranJaminan(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_SETORAN_JAMINAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_KODE_KOLEKTIBILITAS]+"'></i> "
                            + "Kode Kolektibilitas <i style='color:red;'>(m*</i></label>"
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

                                returnData += ControlCombo.draw(""+FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KODE_KOLEKTIBILITAS], null, ""+bankGaransi.getKodeKolektibilitas(), valueCdKolektibilitas, keyCdKolektibilitas, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_KODE_KOLEKTIBILITAS]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_TGL_WAN_PRESTASI]+"'></i> "
                            + "Tanggal Wan Prestasi <i style='color:red;'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_WAN_PRESTASI] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_WAN_PRESTASI] +"' value='"+tglWanPrestasi+"' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_TGL_WAN_PRESTASI]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_KODE_KONDISI]+"'></i> "
                            + "Kode Kondisi  <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdKondisi =  PstContentDataKondisiBankGaransi.listTanpaOid(0,0,"","");
                                Vector valueCdKondisi = new Vector();
                                Vector keyCdKondisi = new Vector();
                                valueCdKondisi.add("");
                                keyCdKondisi.add("-- Pilih --");
                                for (int i = 0; i < vCdKondisi.size(); i++) {
                                    ContentDataKondisiBankGaransi KdKondisi = (ContentDataKondisiBankGaransi) vCdKondisi.get(i);
                                    valueCdKondisi.add(String.valueOf(KdKondisi.getKodeOjk()));
                                    keyCdKondisi.add(KdKondisi.getKodeOjk()+"-"+KdKondisi.getNamaKondisiBankGaransi());
                                }

                                returnData += ControlCombo.draw(""+FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KODE_KONDISI], null, ""+bankGaransi.getKodeKondisi(), valueCdKondisi, keyCdKondisi, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_KODE_KONDISI]+"'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_KODE_KONDISI]+"'></i> "
                            + "Tanggal Kondisi <i style='color:red;'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_KONDISI] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_TGL_KONDISI] +"' value='"+tglKondisi+"' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_KODE_KONDISI]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_KETERANGAN]+"'></i> "
                            + "Keterangan <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KETERANGAN] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KETERANGAN] +"' value='"+bankGaransi.getKeterangan()+"'  data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_KETERANGAN]+"'>"
			+ "</div>"
		    + "</div>"
                      + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmBankGaransi.fieldQuestion[FrmBankGaransi.FRM_FIELD_KODE_KANTOR_CABANG]+"'></i> "
                            + "Kode Kantor Cabang <i style='color:red;'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			   //start comboBox
			    Vector vCdKantorCbg =  PstContentDataKantorCabang.list(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+bankGaransi.getKodeKantorCabang()+"'","");
                            ContentDataKantorCabang kdKantorCbg = new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKantorCbg.size(); i++) {
                                kdKantorCbg = (ContentDataKantorCabang) vCdKantorCbg.get(i);
                            }
                          
                            returnData += "<input type='hidden' name='"+FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+"'>"
                                    + "<input type='text' name='DISPLAY_"+FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+" "+kdKantorCbg.getNamaKantorCabang()+"' class='form-control' data-required='required' readonly='readonly' data-type='text' data-error-message='"+FrmBankGaransi.fieldError[FrmBankGaransi.FRM_FIELD_KODE_KANTOR_CABANG]+"'>";
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
//		    + "<div class='form-group'>"
//			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'>*</font></label>"
//			+ "<div class='col-sm-8'>"
//			    + "<input type='text' class='form-control' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_OPERASI_DATA] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_OPERASI_DATA] +"' value='"+bankGaransi.getOperasiData()+"' data-required='required'>"
//			+ "</div>"
//		    + "</div>"                   
//		    + "<div class='form-group'>"
//			+ "<label class='control-label col-sm-4'>Open Date<font color='red'></font></label>"
//			+ "<div class='col-sm-8'>"
//			     + "<div class='input-group'>"
//				+ "<input type='text' class='form-control datepicker' name='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_OPEN_DATE] +"' id='"+ FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_OPEN_DATE] +"' value='"+openDate+"'>"
//				+ "<div class='input-group-addon'>"
//				    + "<i class='fa fa-calendar'></i>"
//				+ "</div>"
//			    + "</div>"
//			+ "</div>"
//		    + "</div>"
                    + "<div class='form-group hidden'>"
			+ "<label class='control-label col-sm-4'>Status Data <i style='color:red;'>o</i></label>"
			+ "<div class='col-sm-8'>";
			    Vector statusDataKey = new Vector(1,1);
                            Vector statusDataVal = new Vector(1,1);
                            for(int i = 0; i < PstAgunan.statusDatas.length; i++){
                                statusDataKey.add(""+i);
                                statusDataVal.add(""+PstAgunan.statusDatas[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmBankGaransi.fieldNames[FrmBankGaransi.FRM_FIELD_STATUS_DATA], "-- Pilih --", ""+bankGaransi.getStatusData(), statusDataKey, statusDataVal, "", "form-control")
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
	
	
	return returnData;
    }
    
    public String drawListBankGaransi(int iCommand, Vector objectClass) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("No Rekening", "200px");//2
        ctrlist.addHeader("CIF", "200px");//10
        ctrlist.addHeader("Kode Jenis Garansi", "200px");//10
        ctrlist.addHeader("Kode Tujuan Garansi", "200px");//10
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
            BankGaransi bankGaransi = (BankGaransi) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+bankGaransi.getNoRekening());//2
            rowx.add(""+bankGaransi.getCif());//3
            rowx.add(""+bankGaransi.getKodeJenisGaransi());//4
            rowx.add(""+bankGaransi.getKodeTujuanGaransi());//4
            rowx.add(""+bankGaransi.getNoAkadAwal());//4
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
                    + "<label>Kode Jenis Garansi</label>"
                    + "<input type='text' name='kodejenisgaransi' class='form-control' id='kodejenisgaransi'>"
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
                            + "<th>Kode Jenis Garansit</th>"
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
                        + "<th>Kode Jenis Garansit</th>"
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
            whereClause = "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+"='0'";
        }else if(this.dataFor.equals("loadsummarynull")){
            whereClause = "bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
        }else{
            whereClause = "l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        if(userGroup != 4){
            if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
                if(!kodeCabang.equals("")){
                    whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'  ";
                }
                whereClause += " AND bg."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                if(!kodeCabang.equals("")){
                    if(whereClause.length()>0){
                        whereClause += " AND l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' ";
                    }else{
                        whereClause = " l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"' ";
                    }
                    
                }
            }
        }
        
        int summary = 0;
        if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            summary = PstBankGaransi.getCountJoin(whereClause);
        }else{
            summary = SessionProsesPerlengkapan.getCountJoinBankGaransi(whereClause);
        }
        returnData +=""+summary;
        return returnData;
    }
    
    public String otherForm(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        
        String whereClause = "l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"'";
        if(userGroup != 4){
            whereClause += " AND l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
        }
        Vector listData = SessionProsesPerlengkapan.listOtherBankGaransi(0, 0, whereClause, "l."+PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+" ASC");
        returnData+=""
        + "<span class='pull-right'><input type='checkbox' class='checkall' value='0'> Check All</span>"
        + "<table class='table table-bordered table-strped'>"
            + "<thead>"
                + "<tr>"
                    + "<th>No.</th>"
                    + "<th>CIF</th>"
                    + "<th>No Rekening</th>"
                    + "<th>Kode Jenis Garansi</th>"
                    + "<th>Aksi</th>"
                + "</tr>"
            + "</thead>"
            + "<tbody>";
                for(int i = 0; i < listData.size(); i++){
                    BankGaransi data = (BankGaransi) listData.get(i);
                    returnData+=""
                    + "<tr>"
                        + "<td>"+(i+1)+"</td>"
                        + "<td>"+data.getCif()+"</td>"
                        + "<td>"+data.getNoRekening()+"</td>"
                        + "<td>"+data.getKodeJenisGaransi()+"</td>"
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
            CtrlBankGaransi ctrlBankGaransi = new CtrlBankGaransi(request);
            ctrlBankGaransi.action(Command.DELETE, oid, 0, "");
            String msg = ctrlBankGaransi.getMessage();
            returnData = "<i class='fa fa-info'></i> "+msg;
        }
        
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        CtrlBankGaransi ctrlBankGaransi = new CtrlBankGaransi(request);
        ctrlBankGaransi.action(this.iCommand, this.oid, oidDelete);
        String msg = "<i class='fa fa-info'></i> "+ctrlBankGaransi.getMessage();
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
