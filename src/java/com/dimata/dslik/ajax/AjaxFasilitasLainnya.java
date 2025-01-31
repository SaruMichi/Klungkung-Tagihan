/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.contentdata.ContentDataJenisFasilitas;
import com.dimata.dslik.entity.contentdata.ContentDataKantorCabang;
import com.dimata.dslik.entity.contentdata.ContentDataKodeValuta;
import com.dimata.dslik.entity.contentdata.ContentDataKolektibilitas;
import com.dimata.dslik.entity.contentdata.ContentDataKondisi;
import com.dimata.dslik.entity.contentdata.ContentDataSebabMacet;
import com.dimata.dslik.entity.contentdata.ContentDataSukuBunga;
import com.dimata.dslik.entity.contentdata.ContentDataSumberPenghasilan;
import com.dimata.dslik.entity.contentdata.PstContentDataJenisFasilitas;
import com.dimata.dslik.entity.contentdata.PstContentDataKantorCabang;
import com.dimata.dslik.entity.contentdata.PstContentDataKodeValuta;
import com.dimata.dslik.entity.contentdata.PstContentDataKolektibilitas;
import com.dimata.dslik.entity.contentdata.PstContentDataKondisi;
import com.dimata.dslik.entity.contentdata.PstContentDataSebabMacet;
import com.dimata.dslik.entity.contentdata.PstContentDataSukuBunga;
import com.dimata.dslik.entity.contentdata.PstContentDataSumberPenghasilan;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.fasilitaslain.FasilitasLain;
import com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.form.contentdata.FrmContentDataJenisFasilitas;
import com.dimata.dslik.form.contentdata.FrmContentDataKantorCabang;
import com.dimata.dslik.form.contentdata.FrmContentDataKodeValuta;
import com.dimata.dslik.form.contentdata.FrmContentDataKolektibilitas;
import com.dimata.dslik.form.contentdata.FrmContentDataKondisi;
import com.dimata.dslik.form.contentdata.FrmContentDataSebabMacet;
import com.dimata.dslik.form.contentdata.FrmContentDataSukuBunga;
import com.dimata.dslik.form.contentdata.FrmContentDataSumberPenghasilan;
import com.dimata.dslik.form.fasilitaslain.CtrlFasilitasLain;
import com.dimata.dslik.form.fasilitaslain.FrmFasilitasLain;
import static com.dimata.dslik.form.pengurusataupemilik.FrmPengurusAtauPemilik.FRM_FIELD_STATUS_DATA;
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
public class AjaxFasilitasLainnya extends HttpServlet {

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
            this.htmlReturn =showForm2(request);
        }else if (this.dataFor.equals("showlistfasilitaslain")){
            this.htmlReturn =showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull")){
            this.htmlReturn = getSummary(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	if (this.dataFor.equals("showform")) {
             //history
            this.htmlReturn = saveFasilitasLain(request);
        }
    }
    
    public String saveFasilitasLain(HttpServletRequest request) {
        //history
        String returnData = "";
        CtrlFasilitasLain ctrlFasilitasLain = new CtrlFasilitasLain(request);
        oid = FRMQueryString.requestLong(request, "FRM_FIELD_FASILITAS_OID");
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        ctrlFasilitasLain.action(iCommand, oid,userId,userName);
        returnData = ctrlFasilitasLain.getMessage();
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
                PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FASILITAS_LAIN_OID],
                PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF],
                PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING],
                PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if(this.dataFor.equals("listdatafasilitas") || this.dataFor.equals("listdatafasilitashome")){
	    String[] cols = { 
                "fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FASILITAS_LAIN_OID]+"",
                "fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+"",
                "fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+"",
                "jf."+PstFasilitasLain.fieldNames[PstContentDataJenisFasilitas.FLD_JENIS_FASILITAS]+"",
                "sd."+PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN]+"",
                "fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TUNGGAKAN]+""
                
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if(this.dataFor.equals("showlistfasilitaslain")){
	    String[] cols = { 
                PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_FASILITAS_LAIN_OID],
                PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF],
                PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING],
                PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]
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
        String kodeJenisFasilitas = FRMQueryString.requestString(request, "kodejenisfasilitas");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        String whereClause = "";
        
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        
        if(dataFor.equals("listdatasearch")){
	    if(cif.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+"='"+cif+"'";
                }else{
                    whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+"='"+cif+"'";
                }
            }

            if(noRekening.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+"='"+noRekening+"'";
                }else{
                    whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+"='"+noRekening+"'";
                }
            }

            if(kodeJenisFasilitas.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+"='"+kodeJenisFasilitas+"'";
                }else{
                    whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+"='"+kodeJenisFasilitas+"'";
                }
            }
            
	    if(whereClause.length() > 0){
		whereClause += " AND (fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if (dataFor.equals("listdatafasilitas") || this.dataFor.equals("listdatafasilitashome")){
            String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
            whereClause = " 1 = 1"
                + " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" = '"+cifParam+"' "
                + " AND ("
                    + " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                    + " OR jf."+PstContentDataJenisFasilitas.fieldNames[PstContentDataJenisFasilitas.FLD_JENIS_FASILITAS]+" like '%"+this.searchTerm+"%'"
                    + " OR sd."+PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN]+" like '%"+this.searchTerm+"%'"
                    + " OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TUNGGAKAN]+" like '%"+this.searchTerm+"%'"
                + ")";            
        }else if(dataFor.equals("showlistfasilitaslain")){
            
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{
                    whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }else{
                    whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }
            }
            
	    if(whereClause.length() > 0){
		whereClause += " AND (fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+" LIKE '%"+this.searchTerm+"%')";
	    }
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstFasilitasLain.getCountJoin(whereClause);
	}else if (dataFor.equals("listdatafasilitas") || this.dataFor.equals("listdatafasilitashome")){
            total = PstFasilitasLain.getCountJoin(whereClause);
        }else if (dataFor.equals("showlistfasilitaslain")){
            total = PstFasilitasLain.getCountJoin(whereClause);
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

	
	String cif = FRMQueryString.requestString(request, "cif");
        String noRekening = FRMQueryString.requestString(request, "norekening");
        String kodeJenisFasilitas = FRMQueryString.requestString(request, "kodejenisfasilitas");
        String whereClause = "";
        String order ="";
        String appRoot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        appRoot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        
        if(userGroup != 4){
            if(whereClause.length() > 0){
                whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }else{
                whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
            }
        }
        FasilitasLain fasilitasLain = new FasilitasLain();
        
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(dataFor.equals("listdatasearch")){
	    	
                if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(noRekening.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+"='"+noRekening+"'";
                    }else{
                        whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+"='"+noRekening+"'";
                    }
                }

                if(kodeJenisFasilitas.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+"='"+kodeJenisFasilitas+"'";
                    }else{
                        whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+"='"+kodeJenisFasilitas+"'";
                    }
                }
                if(whereClause.length() > 0){
                    whereClause += " AND (fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if (dataFor.equals("listdatafasilitas") || this.dataFor.equals("listdatafasilitashome")){
                String cifParam = FRMQueryString.requestString(request, "FRM_FIELD_CIF");
                whereClause = " 1 = 1"
                    + " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" = '"+cifParam+"' "
                    + " AND ("
                        + " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" like '%"+this.searchTerm+"%'"
                        + " OR jf."+PstContentDataJenisFasilitas.fieldNames[PstContentDataJenisFasilitas.FLD_JENIS_FASILITAS]+" like '%"+this.searchTerm+"%'"
                        + " OR sd."+PstContentDataSumberPenghasilan.fieldNames[PstContentDataSumberPenghasilan.FLD_SUMBER_PENGHASILAN]+" like '%"+this.searchTerm+"%'"
                        + " OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_TUNGGAKAN]+" like '%"+this.searchTerm+"%'"
                    + ")";            
            }else if(dataFor.equals("showlistfasilitaslain")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause += " fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                }

                if(whereClause.length() > 0){
                    whereClause += " AND (fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_JENIS_FASILITAS]+" LIKE '%"+this.searchTerm+"%')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(datafor.equals("listdatasearch")){
	    listData = PstFasilitasLain.listJoin(start, amount, whereClause, order);
	}else if (datafor.equals("listdatafasilitas") || this.dataFor.equals("listdatafasilitashome")){
            listData = PstFasilitasLain.listJoin(start, colOrder, whereClause, order);
        }else if (datafor.equals("showlistfasilitaslain")){
            listData = PstFasilitasLain.listJoin(start, colOrder, whereClause, order);
        }
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
            String buttonUpdate = "";
	    if(datafor.equals("listdatasearch")){
		fasilitasLain = (FasilitasLain) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+fasilitasLain.getCif());
                ja.put(""+fasilitasLain.getNoRekening());
                ja.put(""+fasilitasLain.getKodeJenisFasilitas());
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+fasilitasLain.getDebiturOid()+"&type="+fasilitasLain.getDebiturType()+"&cif="+fasilitasLain.getCif()+"&activetab=AjaxFasilitasLainnya&otheroid="+fasilitasLain.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if (datafor.equals("listdatafasilitas")){
                fasilitasLain = (FasilitasLain) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+(fasilitasLain.getNoRekening()));
                ja.put(""+(fasilitasLain.getCif()));
                ja.put(""+(fasilitasLain.getNamaJenisFasilitas()));
                ja.put(""+(fasilitasLain.getNamaSumberDana()));
                ja.put(""+(Formater.formatNumber(fasilitasLain.getTunggakan(),"###,###,##0")));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+fasilitasLain.getDebiturOid()+"&type="+fasilitasLain.getDebiturType()+"&cif="+fasilitasLain.getCif()+"&activetab=AjaxFasilitasLainnya&otheroid="+fasilitasLain.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                int count  = PstLogSysHistory.getCount(PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+fasilitasLain.getOID()+"' "
                        + "AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" LIKE '%Transfer%'");
                
                if(count == 0){
                    buttonUpdate += "<div class='btn btn-default'><input type='checkbox' class='deletelist' value='"+fasilitasLain.getOID()+"'></div>";
                }
                ja.put(buttonUpdate);
                array.put(ja);
            }else if (datafor.equals("listdatafasilitashome")){
                fasilitasLain = (FasilitasLain) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+(fasilitasLain.getNoRekening()));
                ja.put(""+(fasilitasLain.getCif()));
                ja.put(""+(fasilitasLain.getNamaJenisFasilitas()));
                ja.put(""+(fasilitasLain.getNamaSumberDana()));
                ja.put(""+(Formater.formatNumber(fasilitasLain.getTunggakan(),"###,###,##0")));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+fasilitasLain.getDebiturOid()+"&type="+fasilitasLain.getDebiturType()+"&cif="+fasilitasLain.getCif()+"&activetab=AjaxFasilitasLainnya&otheroid="+fasilitasLain.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
                ja.put(buttonUpdate);
                array.put(ja);
            }else if(datafor.equals("showlistfasilitaslain")){
		fasilitasLain = (FasilitasLain) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+fasilitasLain.getCif());
                ja.put(""+fasilitasLain.getNoRekening());
                ja.put(""+fasilitasLain.getKodeJenisFasilitas());
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+fasilitasLain.getDebiturOid()+"&type="+fasilitasLain.getDebiturType()+"&cif="+fasilitasLain.getCif()+"&activetab=AjaxFasilitasLainnya&otheroid="+fasilitasLain.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
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
            String where = PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_CIF]+"='"+cif+"'";
            countData = PstFasilitasLain.getCount(where);
        }
        
        if (cif.length()==0){
            returnData = ""
            +"<div class='row'>"
                +"<div class='col-md-12'>"
                        +"<div class='box'>"
                            +"<div class='box-header'>"
                                +"<h3 class='box-title'>List Fasilitas Lainnya</h3>"
                            +"</div>"
                            +"<div class=\"box-body\">"
                                + "<div id='tableFasilitas'>"
                                    + "<table class='table table-bordered table-striped'>"
                                        + "<thead>"
                                            + "<tr>"
                                                + "<td>No</td>"                                           
                                                + "<td>No Rekening</td>"
                                                + "<td>CIF</td>"
                                                + "<td>Kode Jenis Fasilitas</td>"
                                                + "<td>Sumber Dana</td>"
                                                + "<td>Tunggakan</td>"                                         
                                                + "<td>Aksi</td>"
                                            + "</tr>"
                                        + "</thead>"
                                    + "</table>"
                                + "</div>"
                            +"</div>"
                             +"<div class='box-header'>";
            
                                if(privAdd){
                                    returnData +="<button type='button' class='btn btn-primary btnaddgeneralfasilitas' data-oid='0' data-for ='showform2' data-target='AjaxFasilitasLainnya' data-command='"+Command.NONE+"'><i class='fa fa-plus'></i> Tambah Data Fasilitas Lainnya</button>";
                                }
                                
                                if(privDelete){
                                    returnData +=" <button type='button' class='btn btn-danger' id='delete' data-for='deleteitem' data-target='AjaxFasilitasLainnya' data-command='"+Command.DELETEALL+"'><i class='fa fa-trash'></i> Hapus Data Fasilitas Lainnya</button>";
                                }
                                
                                returnData+=""
                            +"</div>"
                        +"</div>"
                +"</div>"
            +"</div>"
            + "<div id='fasilitasform'></div>" ;
        }else{
            if (countData>0){
                returnData = ""
                +"<div class='row'>"
                    +"<div class='col-md-12'>"
                            +"<div class='box'>"
                                +"<div class='box-header'>"
                                    +"<h3 class='box-title'>List Fasilitas Lainnya</h3>"
                                +"</div>"
                                +"<div class=\"box-body\">"
                                    + "<div id='tableFasilitas'>"
                                        + "<table class='table table-bordered table-striped'>"
                                            + "<thead>"
                                                + "<tr>"
                                                    + "<td>No</td>"                                           
                                                    + "<td>No Rekening</td>"
                                                    + "<td>CIF</td>"
                                                    + "<td>Kode Jenis Fasilitas</td>"
                                                    + "<td>Sumber Dana</td>"
                                                    + "<td>Tunggakan</td>"                                         
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
        
        FasilitasLain fasilitasLain = new FasilitasLain();
        fasilitasLain.setCif(this.cif);
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        fasilitasLain.setKodeKantorCabang(kodeCabang);
        Date dNow = new Date();
        String tglMulai = "";
        String tglTempo = "";
        String tglMacet = "";
        String tglKondisi = "";
        String openDate = "";
        long oidPeriodeWrite = 0;
        if (oid != 0) {
            try {
                fasilitasLain = PstFasilitasLain.fetchExc(oid);
            } catch (Exception e) {
                String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
                e.printStackTrace();
            }
            if (fasilitasLain.getTglMulai()==null){
                tglMulai = "";
            }else{
                tglMulai = Formater.formatDate(fasilitasLain.getTglMulai(), "yyyy-MM-dd");
            }
            if (fasilitasLain.getTglJatuhTempo()==null){
                tglTempo = "";
            }else{
                tglTempo = Formater.formatDate(fasilitasLain.getTglJatuhTempo(), "yyyy-MM-dd");
            }
            if (fasilitasLain.getTglMacet()==null){
                tglMacet = "";
            }else{
                tglMacet = Formater.formatDate(fasilitasLain.getTglMacet(), "yyyy-MM-dd");
            }
            if (fasilitasLain.getTglKondisi()==null){
                tglKondisi = "";
            }else{
                tglKondisi = Formater.formatDate(fasilitasLain.getTglKondisi(), "yyyy-MM-dd");
            }
            if (fasilitasLain.getOpenDate()==null){
                openDate = "";
            }else{
                openDate = Formater.formatDate(fasilitasLain.getOpenDate(), "yyyy-MM-dd");
            }
            
            oidPeriodeWrite = fasilitasLain.getPeriodeId();
        }
        else {
            tglMulai = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglTempo = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglMacet = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglKondisi = Formater.formatDate(dNow, "yyyy-MM-dd");
            openDate = Formater.formatDate(dNow, "yyyy-MM-dd");
            Vector listPeriode = PstPeriode.list(0, 1, " "+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
            Periode entPeriode = (Periode) listPeriode.get(0);
            oidPeriodeWrite = entPeriode.getOID();
        }
        
        
	String returnData = ""
        + " <input type='hidden' value='"+oidPeriodeWrite+"' name ='FRM_FIELD_PERIODE_ID' id='oidPeriode'>"       
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>Edit Fasilitas Lainnya</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_NO_REKENING]+"'></i> "
                            + "No. Rekening <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='hidden' class='form-control' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_STATUS_PERUBAHAN_DATA] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_STATUS_PERUBAHAN_DATA] +"' value='0' >";
                            Vector kreditKey = new Vector(1,1);
                            Vector kreditVal = new Vector(1,1);
                            Vector listKredit = PstKredit.list(0, 0, ""+PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+fasilitasLain.getCif()+"' "
                                    + "AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+fasilitasLain.getKodeKantorCabang()+"'", "");
                            
                            if(listKredit.size() > 0){
                                for(int i = 0; i < listKredit.size(); i++){
                                    Kredit kredit = (Kredit) listKredit.get(i);
                                    kreditKey.add(""+kredit.getNoRekening());
                                    kreditVal.add(""+kredit.getNoRekening());
                                }
                            }
                            returnData+=""
                            + ControlCombo.draw(FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_NO_REKENING], "-- Pilih --", fasilitasLain.getNoRekening(), kreditKey, kreditVal, "data-required='required' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_NO_REKENING]+"'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_CIF]+"'></i> "
                            + "CIF <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_CIF] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_CIF] +"' value='"+fasilitasLain.getCif()+"' data-required='required' data-required='required' data-number='true' data-alpha='true' data-special='' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_CIF]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_KODE_JENIS_FASILITAS]+"'></i> "
                            + "Kode Jenis Fasilitas  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
//			    + "<input type='text' class='form-control' name='kodejenisfasilitas'>"
//			+ "</div>"
                            Vector vCdJenFas =  PstContentDataJenisFasilitas.listWithoutOid(0,0,"","");
                            Vector valueCdJenFas = new Vector();
                            Vector keyCdJenFas = new Vector();
                            valueCdJenFas.add("");
                            keyCdJenFas.add("-- Pilih --");
                            for (int i = 0; i < vCdJenFas.size(); i++) {
                                ContentDataJenisFasilitas jenFas = (ContentDataJenisFasilitas) vCdJenFas.get(i);
                                valueCdJenFas.add(String.valueOf(jenFas.getKodeOjk()));
                                keyCdJenFas.add(""+jenFas.getJenisFasilitas());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KODE_JENIS_FASILITAS], null, ""+fasilitasLain.getKodeJenisFasilitas(), valueCdJenFas, keyCdJenFas, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_KODE_JENIS_FASILITAS]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_SUMBER_DANA]+"'></i> "
                            + "Sumber dana  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";			   
                            Vector vCdSumberDana =  PstContentDataSumberPenghasilan.listWithoutOid(0,0,"","");
                            Vector valueCdSumberDana = new Vector();
                            Vector keyCdSumberDana = new Vector();
                            valueCdSumberDana.add("");
                            keyCdSumberDana.add("-- Pilih --");
                            for (int i = 0; i < vCdSumberDana.size(); i++) {
                                ContentDataSumberPenghasilan sd = (ContentDataSumberPenghasilan) vCdSumberDana.get(i);
                                valueCdSumberDana.add(String.valueOf(sd.getKodeOjk()));
                                keyCdSumberDana.add(""+sd.getSumberPenghasilan());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_SUMBER_DANA], null, ""+fasilitasLain.getSumberDana(), valueCdSumberDana, keyCdSumberDana, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_SUMBER_DANA]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_TGL_MULAI]+"'></i> "
                            + "Tanggal Mulai  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TGL_MULAI] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TGL_MULAI] +"' value='"+tglMulai+"' data-required='required' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_TGL_MULAI]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_TGL_JATUH_TEMPO]+"'></i> "
                            + "Tanggal Jatuh Tempo  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TGL_JATUH_TEMPO] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TGL_JATUH_TEMPO] +"' value='"+tglTempo+"' data-required='required' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_TGL_JATUH_TEMPO]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_SUKU_BUNGA]+"'></i> "
                            + "Suku Bunga/Imbalan (%)  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' value='"+fasilitasLain.getSukuBunga()+"'  class='form-control' id='"+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_SUKU_BUNGA]+"' name='"+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_SUKU_BUNGA]+"' data-required='required' data-required='required' data-number='true' data-alpha='false' data-special=',' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_SUKU_BUNGA]+"'>"
			+ "</div>"
                            /*
                            Vector vCdSukuBunga =  PstContentDataSukuBunga.list(0,0,"","");
                            Vector valueCdSukuBunga = new Vector();
                            Vector keyCdSukuBunga = new Vector();
                            valueCdSukuBunga.add(""+0);
                            keyCdSukuBunga.add("-- Pilih --");
                            for (int i = 0; i < vCdSukuBunga.size(); i++) {
                                ContentDataSukuBunga sukuBunga = (ContentDataSukuBunga) vCdSukuBunga.get(i);
                                valueCdSukuBunga.add(String.valueOf(sukuBunga.getKodeOjk()));
                                keyCdSukuBunga.add(""+sukuBunga.getNamaSukuBunga());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_SUKU_BUNGA], null, ""+String.valueOf(fasilitasLain.getSukuBunga()), valueCdSukuBunga, keyCdSukuBunga, "data-required='required' onchange=\"javascript:changeFunc();\" ", "form-control");
                            returnData += "</div>"*/
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_KODE_VALUTA]+"'></i> "
                            + "Kode Valuta  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
//			    + "<input type='text' class='form-control' name='kodevaluta'>"
//			+ "</div>"/*
                            Vector vCdKodeValuta =  PstContentDataKodeValuta.listWithoutOid(0,0,"","");
                            Vector valueCdKodeValuta = new Vector();
                            Vector keyCdKodeValuta = new Vector();
                            valueCdKodeValuta.add("");
                            keyCdKodeValuta.add("-- Pilih --");
                            for (int i = 0; i < vCdKodeValuta.size(); i++) {
                                ContentDataKodeValuta kodeValuta = (ContentDataKodeValuta) vCdKodeValuta.get(i);
                                valueCdKodeValuta.add(String.valueOf(kodeValuta.getKodeOjk()));
                                keyCdKodeValuta.add(""+kodeValuta.getNamaValuta());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KODE_VALUTA], null, ""+fasilitasLain.getKodeValuta(), valueCdKodeValuta, keyCdKodeValuta, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_KODE_VALUTA]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_NOMINAL_JML_KEWAJIBAN]+"'></i> "
                            + "Nominal/Jumlah Kewajiban (IDR)  <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_NOMINAL_JML_KEWAJIBAN] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_NOMINAL_JML_KEWAJIBAN] +"' value='"+Formater.formatNumber(fasilitasLain.getNominalJmlKewajiban(),"###,###,##0")+"' data-required='required' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_NOMINAL_JML_KEWAJIBAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_NILAI_MATA_UANG_ASAL]+"'></i> "
                            + "Nilai Dalam Mata Uang Asal <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_NILAI_MATA_UANG_ASAL] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_NILAI_MATA_UANG_ASAL] +"' value='"+Formater.formatNumber(fasilitasLain.getNilaiMataUangAsal(),"###,###,##0")+"' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_NILAI_MATA_UANG_ASAL]+"'>"
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>&nbsp;</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_KODE_KOLEKTIBILITAS]+"'></i> "
                            + "Kode Kolektibilitas <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
//			    + "<select class='form-control' name='kodekolektibilitas'>"
//				+ "<option value=''> -- Pilih -- </option>"
//			    + "</select>"
//			+ "</div>"
                            Vector vCdKolek =  PstContentDataKolektibilitas.listWitoutOid(0,0,"","");
                            Vector valueCdKolek = new Vector();
                            Vector keyCdKolek = new Vector();
                            valueCdKolek.add("");
                            keyCdKolek.add("-- Pilih --");
                            for (int i = 0; i < vCdKolek.size(); i++) {
                                ContentDataKolektibilitas kolek = (ContentDataKolektibilitas) vCdKolek.get(i);
                                valueCdKolek.add(String.valueOf(kolek.getKodeOjk()));
                                keyCdKolek.add(""+kolek.getNamaKolektibilitas());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KODE_KOLEKTIBILITAS], null, ""+fasilitasLain.getKodeKolektibilitas(), valueCdKolek, keyCdKolek, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_KODE_KOLEKTIBILITAS]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_TGL_MACET]+"'></i> "
                            + "Tanggal Macet <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TGL_MACET] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TGL_MACET] +"' value='"+tglMacet+"' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_TGL_MACET]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_KODE_SEBAB_MACET]+"'></i> "
                            + "Kode Sebab Macet <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>";
//			    + "<input type='text' class='form-control' name='kodesebabmacet'>"
//			+ "</div>"
                            Vector vCdMacet =  PstContentDataSebabMacet.listWithoutOid(0,0,"","");
                            Vector valueCdMacet = new Vector();
                            Vector keyCdMacet = new Vector();
                            valueCdMacet.add("");
                            keyCdMacet.add("-- Pilih --");
                            for (int i = 0; i < vCdMacet.size(); i++) {
                                ContentDataSebabMacet macet = (ContentDataSebabMacet) vCdMacet.get(i);
                                valueCdMacet.add(String.valueOf(macet.getKodeOjk()));
                                keyCdMacet.add(""+macet.getSebabMacet());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KODE_SEBAB_MACET], null, ""+fasilitasLain.getKodeSebabMacet(), valueCdMacet, keyCdMacet, " onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_KODE_SEBAB_MACET]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_TUNGGAKAN]+"'></i> "
                            + "Tunggakan <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control numeric' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TUNGGAKAN] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TUNGGAKAN] +"' value='"+Formater.formatNumber(fasilitasLain.getTunggakan(),"###,###,##0")+"' data-required='required' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_TUNGGAKAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_JML_HARI_TUNGGAKAN]+"'></i> "
                            + "Jumlah Hari Tunggakan <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_JML_HARI_TUNGGAKAN] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_JML_HARI_TUNGGAKAN] +"' value='"+fasilitasLain.getJmlHariTunggakan()+"' data-required='required' data-required='required' data-number='true' data-alpha='false' data-special='' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_JML_HARI_TUNGGAKAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_KODE_KONDISI]+"'></i> "
                            + "Kode Kondisi <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
//			    + "<input type='text' class='form-control' name='kodekondisi'>"
//			+ "</div>"
                            Vector vCdKondisi =  PstContentDataKondisi.listTanpaOid(0,0,"","");
                            Vector valueCdKondisi = new Vector();
                            Vector keyCdKondisi = new Vector();
                            valueCdKondisi.add("");
                            keyCdKondisi.add("-- Pilih --");
                            for (int i = 0; i < vCdKondisi.size(); i++) {
                                ContentDataKondisi kondisi = (ContentDataKondisi) vCdKondisi.get(i);
                                valueCdKondisi.add(String.valueOf(kondisi.getKodeOjk()));
                                keyCdKondisi.add(""+kondisi.getNamaKondisi());
                            }
                          
                            returnData += ControlCombo.draw(""+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KODE_KONDISI], null, ""+fasilitasLain.getKodeKondisi(), valueCdKondisi, keyCdKondisi, "data-required='required' onchange=\"javascript:changeFunc();\"  data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_KODE_KONDISI]+"'", "form-control");
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_TGL_KONDISI]+"'></i> "
                            + "Tanggal Kondisi <i style='color:red'>c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' class='form-control datepicker' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TGL_KONDISI] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_TGL_KONDISI] +"' value='"+tglKondisi+"' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_TGL_KONDISI]+"'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_KETERANGAN]+"'></i> "
                            + "Keterangan <i style='color:red'>o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' class='form-control' name='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KETERANGAN] +"' id='"+ FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KETERANGAN] +"' value='"+fasilitasLain.getKeterangan()+"' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_KETERANGAN]+"'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmFasilitasLain.fieldQuestion[FrmFasilitasLain.FRM_FIELD_KODE_KANTOR_CABANG]+"'></i> "
                            + "Kode Kantor Cabang <i style='color:red'>m*</i></label>"
			+ "<div class='col-sm-8'>";
//			    + "<input type='text' class='form-control' name='kodekantorcabang'>"
//			+ "</div>"
                            Vector vCdKanCab =  PstContentDataKantorCabang.listWithoutOid(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+fasilitasLain.getKodeKantorCabang()+"'","");
                            ContentDataKantorCabang kanCab = new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKanCab.size(); i++) {
                                kanCab = (ContentDataKantorCabang) vCdKanCab.get(i);
                            }
                          
                            returnData += "<input type='hidden' name='"+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kanCab.getKodeOjk()+"'>"
                                    + "<input type='text' name='DISPLAY_"+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kanCab.getKodeOjk()+" "+kanCab.getNamaKantorCabang()+"' data-required='required' readonly='readonly' class='form-control' data-type='text' data-error-message='"+FrmFasilitasLain.fieldError[FrmFasilitasLain.FRM_FIELD_KODE_KANTOR_CABANG]+"'>";
                            returnData += "</div>"
		    + "</div>"
		    + "<div class='form-group hidden'>"
                        + "<input type='hidden' name='"+FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_STATUS_PERUBAHAN_DATA]+"' value='0'>"
			+ "<label class='control-label col-sm-4'>Operasi Data <font color='red'>*</font></label>"
			+ "<div class='col-sm-8'>";
                            Vector operasiDataKey = new Vector(1,1);
                            Vector operasiDataVal = new Vector(1,1);
                            for(int i = 0; i < PstDebitur.operasiData.length; i++){
                                operasiDataKey.add(""+PstDebitur.operasiDataKey[i]);
                                operasiDataVal.add(""+PstDebitur.operasiData[i]);
                            }
                            returnData+=""
			    + ControlCombo.draw(FrmFasilitasLain.fieldNames[FrmFasilitasLain.FRM_FIELD_OPERASI_DATA], "-- Pilih --", ""+fasilitasLain.getOperasiData(), operasiDataKey, operasiDataVal, "'", "form-control")
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	+ "</div>";
	
	
	return returnData;
    }
    
    public String drawListFasilitasLain(int iCommand, Vector objectClass) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("No Rekening", "200px");//2
        ctrlist.addHeader("CIF", "200px");//10
        ctrlist.addHeader("Kode Jenis Fasilitas", "200px");//10
        ctrlist.addHeader("Sumber Dana", "200px");//10
        ctrlist.addHeader("Tunggakan", "200px");//10
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
            FasilitasLain fasilitasLain = (FasilitasLain) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+fasilitasLain.getNoRekening());//2
            rowx.add(""+fasilitasLain.getCif());//4
            rowx.add(""+fasilitasLain.getKodeJenisFasilitas());//4
            rowx.add(""+fasilitasLain.getSumberDana());//4
            rowx.add(""+fasilitasLain.getTunggakan());//4
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
                    + "<label>Kode Jenis Fasilitas</label>"
                    + "<input type='text' name='kodejenisfasilitas' class='form-control' id='kodejenisfasilitas'>"
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
                            + "<th>Kode Jenis Fasilitas</th>"
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
                        + "<th>Kode Jenis Fasilitas</th>"
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
            whereClause = "fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+"='0'";
        }else{
            whereClause = "fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
        }
        
        if(userGroup != 4){
            whereClause += " AND fl."+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
        }
        
        int summary = PstFasilitasLain.getCountJoin(whereClause);
        returnData +=""+summary;
        return returnData;
    }
    
    public String deleteItem(HttpServletRequest request){
        String returnData = "";
        CtrlFasilitasLain ctrlFasilitasLain = new CtrlFasilitasLain(request);
        ctrlFasilitasLain.action(this.iCommand, this.oid, oidDelete);
        String msg = "<i class='fa fa-info'></i> "+ctrlFasilitasLain.getMessage();
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
