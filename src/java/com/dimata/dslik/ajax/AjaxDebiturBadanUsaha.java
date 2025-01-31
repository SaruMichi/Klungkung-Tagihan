/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.dslik.entity.agunan.PstAgunan;
import com.dimata.dslik.entity.bankgaransi.BankGaransi;
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.form.debiturbdnusaha.*;
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.debiturbdnusaha.DebiturBdnUsaha;
import com.dimata.dslik.entity.debiturbdnusaha.PstDebiturBdnUsaha;
import com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain;
import com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.kreditjoinaccount.PstKreditJoinAccount;
import com.dimata.dslik.entity.laporankeuangandebitur.PstLaporanKeuanganDebitur;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik;
import com.dimata.dslik.entity.penjamin.PstPenjamin;
import com.dimata.dslik.entity.suratberharga.PstSuratBerharga;
import com.dimata.dslik.session.proses.ProsesTransferDataBank;
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
public class AjaxDebiturBadanUsaha extends HttpServlet {

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
    private String cifReturn = "";
    
    private boolean privAdd = false;
    private boolean privDelete = false;
    private boolean privUpdate = false;
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
	
	//INT
	this.iCommand = FRMQueryString.requestCommand(request);
	this.iErrCode = 0;
        
        //BOOLEAN
        this.privAdd = FRMQueryString.requestBoolean(request, "privadd");
        this.privDelete = FRMQueryString.requestBoolean(request, "privdelete");
        this.privUpdate = FRMQueryString.requestBoolean(request, "privupdate");
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
    
    public String downloadItem(HttpServletRequest request){
        String returnData = "";
        String cif = FRMQueryString.requestString(request, "cif");
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
        if(!cif.equals("")){
             debitur = prosesTransferDataBank.actionTransferDataDebitur(""+cif,periodeId, 1, 1);
        }
        
        this.htmlReturn =""+debitur;
        return debitur;
    }
    
    public void commandNone(HttpServletRequest request){
	if(this.dataFor.equals("showform")){
	    this.htmlReturn = showForm(request);
	}else if(this.dataFor.equals("searchform")){
            this.htmlReturn = searchForm(request);
        }else if(this.dataFor.equals("showlistbadanusaha") || this.dataFor.equals("showlisttanpapengurus")){
            this.htmlReturn = showListForm(request);
        }else if(this.dataFor.equals("loadsummary") || this.dataFor.equals("loadsummarynull") || this.dataFor.equals("loadtanpapengurus")){
            this.htmlReturn = getSummary(request);
        }else if(dataFor.equals("loadniksama")){
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
            this.htmlReturn = saveDebiturBdnUsaha(request);
        }
    }
    
    public String saveDebiturBdnUsaha(HttpServletRequest request) {
        //history
        userId = FRMQueryString.requestLong(request, "useroid");
        userName = FRMQueryString.requestString(request, "username");
        String returnData = "";
        CtrlDebiturBdnUsaha ctrlDebiturBdnUsaha = new CtrlDebiturBdnUsaha(request);
        ctrlDebiturBdnUsaha.action(iCommand, oid,userId,userName);
        returnData = ctrlDebiturBdnUsaha.getMessage();
        DebiturBdnUsaha entDebiturBdnUsaha = new DebiturBdnUsaha();
        entDebiturBdnUsaha = ctrlDebiturBdnUsaha.getDebiturBdnUsaha();
        this.oidReturn = entDebiturBdnUsaha.getOID();
        this.cifReturn = entDebiturBdnUsaha.getCif();
        return returnData;
    }
    
    public void commandDeleteAll(HttpServletRequest request){
	if(this.dataFor.equals("deleterateplan")){
	    
	}
    }
    
    
    
    public void commandList(HttpServletRequest request, HttpServletResponse response){
	if(this.dataFor.equals("listdatasearch")){
	    String[] cols = { 
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_DEBITUR_BDN_USHA_OID],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if(this.dataFor.equals("showlistbadanusaha")){
	    String[] cols = { 
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_DEBITUR_BDN_USHA_OID],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if(this.dataFor.equals("showlisttanpapengurus")){
	    String[] cols = { 
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_DEBITUR_BDN_USHA_OID],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if(this.dataFor.equals("listdatasearchglobal")){
	    String[] cols = { 
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_DEBITUR_BDN_USHA_OID],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS],
                PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]
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
        String noakte = FRMQueryString.requestString(request, "noakterawal");
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
        if(userGroup != 4){
            if(whereClause.length() > 0){
                 whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                 whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }
        }
        
        if(dataFor.equals("listdatasearch")){
	    
            if(cif.length() > 0){
                if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                    }
                }

            if(noakte.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+"='"+noakte+"'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+"='"+noakte+"'";
                }
            }

            if(nama.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+nama+"%'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+nama+"%'";
                }
            }

            if(type == 1){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='"+1+"'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='"+1+"'";
                }
            }

	    if(whereClause.length() > 0){
		whereClause += " AND (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}else if(dataFor.equals("showlistbadanusaha")){
            if(status.equals("0")){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                }
            }
            if(whereClause.length() > 0){
		whereClause += " AND (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
	    }
            
            whereClause+=" AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1'";
        }else if(dataFor.equals("showlisttanpapengurus")){
            if(whereClause.length() > 0){
                whereClause +=" AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1' "
                + " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }else{
                whereClause +=" debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1' "
                + " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
            }
            
//            if(kodeCabang.length() > 0){
//                if(whereClause.length() > 0){
//                    whereClause+=" AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
//                }else{
//                    whereClause+=" debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
//                }
//            }
            
            if(whereClause.length() > 0){
		whereClause += " AND (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
	    }
        }else if(dataFor.equals("listdatasearchglobal")){
	    
            if(cif.length() > 0){
                if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                    }
                }

            if(noakte.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+"='"+noakte+"'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+"='"+noakte+"'";
                }
            }

            if(nama.length() > 0){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+nama+"%'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+nama+"%'";
                }
            }

            if(type == 1){
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                }
            }else{
                if(whereClause.length() > 0){
                    whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='"+1+"'";
                }else{
                    whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='"+1+"'";
                }
            }

	    if(whereClause.length() > 0){
		whereClause += " AND (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
	    }else{
		whereClause += " (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                        + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
	    }
	}
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listdatasearch")){
	    total = PstDebitur.getCountJoin(whereClause,""+periodeId,kodeCabang);
	}else if(dataFor.equals("showlistbadanusaha")){
	    total = PstDebitur.getCountJoin(whereClause,""+periodeId,kodeCabang);
	}else if(dataFor.equals("showlisttanpapengurus")){
            total = PstDebiturBdnUsaha.getCountTidakAdaPengurus(whereClause,""+periodeId,kodeCabang);
        }else if(dataFor.equals("listdatasearchglobal")){
            if(!cif.equals("") || !noakte.equals("") || !nama.equals("")){
                total = PstDebiturBdnUsaha.getCountGlobal(whereClause,""+periodeId,kodeCabang);
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
        DebiturBdnUsaha debitur = new DebiturBdnUsaha();
        Vector listData = new Vector();

	String cif = FRMQueryString.requestString(request, "cif");
        String noakte = FRMQueryString.requestString(request, "noakteawal");
        String nama = FRMQueryString.requestString(request, "nama");
        int type = FRMQueryString.requestInt(request, "type");
        String status = FRMQueryString.requestString(request, "FRM_FIELD_STATUS_DATA");
        String whereClause = "";
        String order ="";
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        privView = FRMQueryString.requestBoolean(request, "privview");
        
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
            whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }else{
            whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(datafor.equals("listdatasearch")){
		if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(noakte.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+"='"+noakte+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+"='"+noakte+"'";
                    }
                }

                if(nama.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+nama+"%'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+nama+"%'";
                    }
                }

                if(type == 1){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='"+1+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='"+1+"'";
                    }
                }
		if(whereClause.length() > 0){
                    whereClause += " AND (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if(dataFor.equals("showlistbadanusaha")){
                if(status.equals("0")){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_STATUS_PERUBAHAN_DATA]+"='"+status+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL";
                    }
                }
                if(whereClause.length() > 0){
                    whereClause += " AND (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
                }

                whereClause+=" AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1'";
                
            }else if(dataFor.equals("showlisttanpapengurus")){
                
                if(whereClause.length() > 0){
                    whereClause +=" AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1' "
                    + " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
                }else{
                    whereClause +=" debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1' "
                    + " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
                }

//                if(kodeCabang.length() > 0){
//                    if(whereClause.length() > 0){
//                        whereClause+=" AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
//                    }else{
//                        whereClause+=" debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]+"='"+kodeCabang+"'";
//                    }
//                }

                if(whereClause.length() > 0){
                    whereClause += " AND (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
                }
            }else if(datafor.equals("listdatasearchglobal")){
		if(cif.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                    }
                }

                if(noakte.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+"='"+noakte+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+"='"+noakte+"'";
                    }
                }

                if(nama.length() > 0){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+nama+"%'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+nama+"%'";
                    }
                }

                if(type == 1){
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"='"+type+"'";
                    }
                }else{
                    if(whereClause.length() > 0){
                        whereClause += " AND debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='"+1+"'";
                    }else{
                        whereClause += " debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='"+1+"'";
                    }
                }
		if(whereClause.length() > 0){
                    whereClause += " AND (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
                }else{
                    whereClause += " (debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NO_IDENTITAS]+" LIKE '%"+this.searchTerm+"%' "
                            + "OR debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_NAMA]+" LIKE '%"+this.searchTerm+"%')";
                }
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
        
        if(datafor.equals("showlisttanpapengurus")){
            listData = PstDebiturBdnUsaha.listJoinTanpaPengurus(start, amount, whereClause, order,""+periodeId,kodeCabang);
        }else{
            //listData = PstDebiturBdnUsaha.listJoin(start, amount, whereClause, order,""+periodeId,kodeCabang);
            if(datafor.equals("listdatasearchglobal")){
                if(!cif.equals("") || !noakte.equals("") || !nama.equals("")){
                     listData = PstDebiturBdnUsaha.list(start, amount, whereClause, order,""+periodeId,kodeCabang);
                }else{
                     listData = new Vector();
                }
            }else{
                listData = PstDebiturBdnUsaha.listJoin(start, amount, whereClause, order,""+periodeId,kodeCabang);
            }
        }
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
            String buttonUpdate = "";
	    if(datafor.equals("listdatasearch")){
		debitur = (DebiturBdnUsaha) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+debitur.getCif());
                ja.put(""+(debitur.getNoIdentitas() == null ? "-" : debitur.getNoIdentitas()));
                ja.put(""+(debitur.getNama() == null ? "-" : debitur.getNama()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+debitur.getOID()+"&type="+debitur.getKodeJenisNsb()+"&cif="+debitur.getCif()+"&activetab=AjaxDebiturBadanUsaha&otheroid="+debitur.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if(datafor.equals("showlistbadanusaha") || datafor.equals("showlisttanpapengurus")){
		debitur = (DebiturBdnUsaha) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+debitur.getCif());
                ja.put(""+(debitur.getNoIdentitas() == null ? "-" : debitur.getNoIdentitas()));
                ja.put(""+(debitur.getNama() == null ? "-" : debitur.getNama()));
                if(privView){
                    buttonUpdate += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+debitur.getOID()+"&type="+debitur.getKodeJenisNsb()+"&cif="+debitur.getCif()+"&activetab=AjaxDebiturBadanUsaha&otheroid="+debitur.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                }
		ja.put(buttonUpdate);
		
		array.put(ja);
	    }else if(datafor.equals("listdatasearchglobal")){
		debitur = (DebiturBdnUsaha) listData.get(i);
		ja.put(""+(this.start+i+1));
                ja.put(""+debitur.getCif());
                ja.put(""+(debitur.getNoIdentitas() == null ? "-" : debitur.getNoIdentitas()));
                ja.put(""+(debitur.getNama() == null ? "-" : debitur.getNama()));
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
                        //noRekening += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+debitur.getOID()+"&type="+debitur.getKodeJenisNsb()+"&cif="+debitur.getCif()+"&activetab=AjaxDebiturIndividu&otheroid="+debitur.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>"; 
                        noRekening += "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command=8&oid="+debitur.getOID()+"&type="+debitur.getKodeJenisNsb()+"&cif="+debitur.getCif()+"&activetab=AjaxDebiturBadanUsaha&otheroid="+debitur.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a>";
                    }
                }
                 
		ja.put(noRekening);
		
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
        Date dNow = new Date();
        String kodeCabang = FRMQueryString.requestString(request, "FRM_KODE_CABANG");
        DebiturBdnUsaha debiturBdnUsaha = new DebiturBdnUsaha();
        debiturBdnUsaha.setKodeKtrCabang(kodeCabang);
        String tglAktePendirian = "";
        String tglAktePerubahan = "";
        String tglPemeringkat = "";
        if (oid != 0) {
            try {
                debiturBdnUsaha = PstDebiturBdnUsaha.fetchExc(oid);
            } catch (Exception e) {
               String returnData = "alert('Terjadi Kesalahan "+e.getStackTrace()+"')";
               e.printStackTrace();
            }
            if (debiturBdnUsaha.getTglAktePendirian()==null){
                tglAktePendirian = "";
            }else{
                tglAktePendirian = Formater.formatDate(debiturBdnUsaha.getTglAktePendirian(), "yyyy-MM-dd");
            }
            if (debiturBdnUsaha.getTglAktePerubahan()==null){
                tglAktePerubahan = "";
            }else{
                tglAktePerubahan = Formater.formatDate(debiturBdnUsaha.getTglAktePerubahan(), "yyyy-MM-dd");
            }
            if (debiturBdnUsaha.getTglPemeringkat()==null){
                tglPemeringkat = "";
            }else{
                tglPemeringkat = Formater.formatDate(debiturBdnUsaha.getTglPemeringkat(), "yyyy-MM-dd");
            }
            
        }
        else {
            tglAktePendirian = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglAktePerubahan = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglPemeringkat = Formater.formatDate(dNow, "yyyy-MM-dd");
        }
        
        //tampilkan listIrrevocableLc berdasarkan cif
        Vector listDebiturBdnUsaha = new Vector();
        if(!cif.equals("")){
                String where = PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"'";
                listDebiturBdnUsaha = PstDebiturBdnUsaha.list(0,0, ""+where, "");
        }
        
	String returnData = ""
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>Basic Information</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_CIF]+"'></i> "
                            + "CIF <i style='color:red;'>(m*</i>"
                        + "</label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_CIF] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_CIF] +"' value='"+debiturBdnUsaha.getCif()+"' class='form-control nospecial' data-required='required' data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_CIF]+"' data-type='text' data-number='true' data-alpha='true' data-special=\"\">"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_NO_IDENTITAS]+"'></i> "
                                + "Nomor Identitas Badan Usaha <i style='color:red;'>(m*</i>"
                        + "</label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NO_IDENTITAS] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NO_IDENTITAS] +"' value='"+debiturBdnUsaha.getNoIdentitas()+"' class='form-control nospecial' data-required='required' data-number='true' data-alpha='true' data-special=\"\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_NO_IDENTITAS]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_NAMA]+"'></i> "
                            + "Nama Badan Usaha <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NAMA] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NAMA] +"' value='"+debiturBdnUsaha.getNama()+"' class='form-control' data-required='required' data-number='true' data-alpha='true' data-special=\" -'.,&@()\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_NAMA]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KODE_JENIS]+"'></i> "
                            + "Kode Bentuk Badan Usaha <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>";
                                Vector vBntkBdnUsaha =  PstContentDataBentukBadanUsaha.listWithoutOid(0,0,"","");
                                Vector valueBntkBdnUsaha = new Vector();
                                Vector keyBntkBdnUsaha = new Vector();
                                valueBntkBdnUsaha.add("");
                                keyBntkBdnUsaha.add("-- Pilih --");
                                for (int i = 0; i < vBntkBdnUsaha.size(); i++) {
                                    ContentDataBentukBadanUsaha KdKab = (ContentDataBentukBadanUsaha) vBntkBdnUsaha.get(i);
                                    valueBntkBdnUsaha.add(String.valueOf(KdKab.getKodeOjk()));
                                    keyBntkBdnUsaha.add(KdKab.getKodeOjk()+"-"+KdKab.getBentukBadanUsaha());
                                }
                                returnData += ControlCombo.draw(""+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_JENIS], null, ""+debiturBdnUsaha.getKodeJenis(), valueBntkBdnUsaha, keyBntkBdnUsaha, "data-required='required'  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KODE_JENIS]+"' data-type='text'", "form-control");
                                //returnData += "</div>"
			    //+ "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_JENIS] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_JENIS] +"' value='"+debiturBdnUsaha.getKodeJenis()+"' class='form-control' data-required='required'>"
			returnData +=""
                        + "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_TEMPAT]+"'></i> "
                                + "Tempat Pendirian <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TEMPAT] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TEMPAT] +"' value='"+debiturBdnUsaha.getTempat()+"' class='form-control' data-required='required' data-number='false' data-alpha='true' data-special=\" -'.,\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_TEMPAT]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_NO_AKTE]+"'></i> "
                                + "No Akte Awal <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NO_AKTE] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NO_AKTE] +"' value='"+debiturBdnUsaha.getNoAkte()+"' class='form-control nospecial' data-required='required' data-number='true' data-alpha='true' data-special=\"_/()'-.,\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_NO_AKTE]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_TGL_AKTE_PENDIRIAN]+"'></i> "
                                + "Tanggal Akte Pendirian <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TGL_AKTE_PENDIRIAN] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TGL_AKTE_PENDIRIAN] +"' value='"+tglAktePendirian+"' class='form-control datepicker' data-required='required'  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_TGL_AKTE_PENDIRIAN]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_NO_AKTE_PERUBAHAN]+"'></i> "
                                + "No Akte Perubahan Terakhir KTP <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NO_AKTE_PERUBAHAN] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NO_AKTE_PERUBAHAN] +"' value='"+debiturBdnUsaha.getNoAktePerubahan()+"' class='form-control nospecial' data-required='required' data-number='true' data-alpha='true' data-special=\"-\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_NO_AKTE_PERUBAHAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
                    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_TGL_AKTE_PERUBAHAN]+"'></i> "
                                + "Tanggal Akte Perubahan Terakhir <i style='color:red;'>m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TGL_AKTE_PERUBAHAN] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TGL_AKTE_PERUBAHAN] +"' value='"+tglAktePerubahan+"' class='form-control datepicker' data-required='required'  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_TGL_AKTE_PERUBAHAN]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_TELEPON]+"'></i> "
                                + "Telepon <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TELEPON] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TELEPON] +"' value='"+debiturBdnUsaha.getTelepon()+"' class='form-control numericonly' data-required='required' data-number='true' data-alpha='false' data-special=\"\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_TELEPON]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
                    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_HP]+"'></i> "
                                + "No. Telephone Seluler <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_HP] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_HP] +"' value='"+debiturBdnUsaha.getTeleponSeluler()+"' class='form-control numericonly' data-number='true' data-alpha='false' data-special=\"\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_HP]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_EMAIL]+"'></i> "
                                + "Alamat Email <i style='color:red;'>(o*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_EMAIL] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_EMAIL] +"' value='"+debiturBdnUsaha.getEmail()+"' class='form-control' data-number='true' data-alpha='true' data-special=\"@._\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_EMAIL]+"' data-type='email'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_ALAMAT]+"'></i> "
                                + "Alamat <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_ALAMAT] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_ALAMAT] +"' value='"+debiturBdnUsaha.getAlamat()+"' class='form-control' data-required='required' data-number='true' data-alpha='true' data-special=\" -/&,()'.\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_ALAMAT]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KELURAHAN]+"'></i> "
                                + "Kelurahan <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KELURAHAN] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KELURAHAN] +"' value='"+debiturBdnUsaha.getKelurahan()+"' class='form-control' data-required='required' data-number='true' data-alpha='true' data-special=\" -'/.\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KELURAHAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KECAMATAN]+"'></i> "
                                + "Kecamatan <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KECAMATAN] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KECAMATAN] +"' value='"+debiturBdnUsaha.getKecamatan()+"' class='form-control' data-required='required' data-number='true' data-alpha='true' data-special=\" -'/.\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KECAMATAN]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KODE_KAB]+"'></i> "
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

                                returnData += ControlCombo.draw(""+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_KAB], null, ""+debiturBdnUsaha.getKodeKab(), valueCdKab, keyCdKab, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KODE_KAB]+"' data-type='text'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KODE_POS]+"'></i> "
                                + "Kode Pos <i style='color:red;'>(m*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_POS] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_POS] +"' value='"+debiturBdnUsaha.getKodePos()+"' class='form-control numericonly' data-required='required' data-number='true' data-alpha='false' data-special=\"\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KODE_POS]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                                + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KODE_NEGARA]+"'></i> "
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

                                returnData += ControlCombo.draw(""+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_NEGARA], null, ""+debiturBdnUsaha.getKodeNegara(), valueCdNegara, keyCdNegara, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KODE_NEGARA]+"' data-type='text'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KODE_BIDANG_USAHA]+"'></i> "
                            + "Kode Bidang Usaha <i style='color:red;'>(m**</i></label>"
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

                                returnData += ControlCombo.draw(""+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_BIDANG_USAHA], null, ""+debiturBdnUsaha.getKodeBidangUsaha(), valueCdBidang, keyCdBidang, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KODE_BIDANG_USAHA]+"' data-type='text'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KODE_HUB_LJK]+"'></i> "
                            + "Kode Hubungan dengan Pelapor <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>";
                                Vector listHubunganPelapor = PstContentDataHubDgnPelapor.listWithoutOid(0, 0, "", "");
                                Vector hubunganKey = new Vector(1,1);
                                Vector hubunganVal = new Vector(1,1);
                                if(listHubunganPelapor.size() > 0){
                                    for(int i = 0; i < listHubunganPelapor.size(); i++){
                                        ContentDataHubDgnPelapor contentDataHubDgnPelapor = (ContentDataHubDgnPelapor) listHubunganPelapor.get(i);
                                        hubunganKey.add(""+contentDataHubDgnPelapor.getKodeOjk());
                                        hubunganVal.add(contentDataHubDgnPelapor.getKodeOjk()+"-"+contentDataHubDgnPelapor.getHubunganDgnPelapor());
                                    }
                                }
				returnData+=""
			    + ControlCombo.draw(FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_HUB_LJK], "-- Pilih --", ""+debiturBdnUsaha.getKodeHubLjk(), hubunganKey, hubunganVal, "data-required='required'  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KODE_HUB_LJK]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		+ "</div>"
	    + "</div>"
	    + "<div class='col-md-6'>"
		+ "<div class='form-horizontal' style='padding:10px;background:#fff;border:5px solid #f1f1f1;'>"
		    + "<h4>Other Information</h4>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_MELANGGAR_BMPK]+"'></i> "
                            + "Melanggar BMPK/BMPD/BMPP <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>";
                                Vector pisahHargaKey = new Vector(1,1);
                                Vector pisahHargaVal = new Vector(1,1);
                                for(int i = 0; i < PstDebitur.pisahHargaKey.length; i++){
                                    pisahHargaKey.add(""+PstDebitur.pisahHargaKey[i]);
                                    pisahHargaVal.add(""+PstDebitur.pisahHargaVal[i]);
                                }
                                returnData+=""
                                + ControlCombo.draw(FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_MELANGGAR_BMPK], "-- Pilih --", ""+debiturBdnUsaha.getMelanggarBmpk(), pisahHargaKey, pisahHargaVal, "data-required='required' data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_MELANGGAR_BMPK]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_MELAMPAUI_BMPK]+"'></i> "
                            + "Melampaui BMPK/BMPD/BMPP <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + ControlCombo.draw(FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_MELAMPAUI_BMPK], "-- Pilih --", ""+debiturBdnUsaha.getMelampauiBmpk(), pisahHargaKey, pisahHargaVal, "data-required='required' data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_MELAMPAUI_BMPK]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_GO_PUBLIC]+"'></i> "
                            + "Go Public <i style='color:red;'>(m**</i></label>"
			+ "<div class='col-sm-8'>"
			    + ControlCombo.draw(FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_GO_PUBLIC], "-- Pilih --", ""+debiturBdnUsaha.getMelampauiBmpk(), pisahHargaKey, pisahHargaVal, "data-required='required' data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_GO_PUBLIC]+"' data-type='text'", "form-control")
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KODE_GOL]+"'></i> "
                            + "Kode Golongan Debitur <i style='color:red;'>(m**</i></label>"
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

                                returnData += ControlCombo.draw(""+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_GOL], null, ""+debiturBdnUsaha.getKodeGol(), valueCdGolDeb, keyCdGolDeb, "data-required='required' onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KODE_GOL]+"' data-type='text'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_PERINGKAT]+"'></i> "
                            + "Peringkat/Ranking Debitur <i style='color:red;'>(o**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_PERINGKAT] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_PERINGKAT] +"' value='"+debiturBdnUsaha.getPeringkat()+"' class='form-control nospecial' data-number='true' data-alpha='true' data-special=\"-/()_+\" data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_PERINGKAT]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_LEMBAGA_PEMERINGKAT]+"'></i> "
                            + "Lembaga Pemeringkat/Rating <i style='color:red;'>(c**</i></label>"
			+ "<div class='col-sm-8'>";
                                //start comboBox
                                Vector vCdLembaga =  PstContentDataLembagaPemeringkat.listWithoutOid(0,0,"","");
                                Vector valueCdLembaga = new Vector();
                                Vector keyCdLembaga = new Vector();
                                valueCdLembaga.add("");
                                keyCdLembaga.add("-- Pilih --");
                                for (int i = 0; i < vCdLembaga.size(); i++) {
                                    ContentDataLembagaPemeringkat KdLembaga = (ContentDataLembagaPemeringkat) vCdLembaga.get(i);
                                    valueCdLembaga.add(String.valueOf(KdLembaga.getKodeOjk()));
                                    keyCdLembaga.add(KdLembaga.getKodeOjk()+"-"+KdLembaga.getNamaLembagaPemeringkat());
                                }
                                returnData += ControlCombo.draw(""+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_LEMBAGA_PEMERINGKAT], null, ""+debiturBdnUsaha.getLembagaPemeringkat(), valueCdLembaga, keyCdLembaga, " onchange=\"javascript:changeFunc();\"  data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_LEMBAGA_PEMERINGKAT]+"' data-type='text'", "form-control");
                                returnData += "</div>"
                                //end comboBox
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_TGLPEMERINGKAT]+"'></i> "
                            + "Tanggal Pemeringkat <i style='color:red;'>(c*</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<div class='input-group'>"
				+ "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TGLPEMERINGKAT] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_TGLPEMERINGKAT] +"' value='"+tglPemeringkat+"' class='form-control datepicker' data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_TGLPEMERINGKAT]+"' data-type='text'>"
				+ "<div class='input-group-addon'>"
				    + "<i class='fa fa-calendar'></i>"
				+ "</div>"
			    + "</div>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_NAMAGROUP]+"'></i> "
                            + "Nama Group Debitur <i style='color:red;'>(o**</i></label>"
			+ "<div class='col-sm-8'>"
			    + "<input type='text' name='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NAMAGROUP] +"' id='"+ FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_NAMAGROUP] +"' value='"+debiturBdnUsaha.getNamaGroup()+"' class='form-control' data-number='true' data-alpha='true' data-special=\" &-',.()/\" data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_NAMAGROUP]+"' data-type='text'>"
			+ "</div>"
		    + "</div>"
		    + "<div class='form-group'>"
			+ "<label class='control-label col-sm-4'>"
                            + "<i class='fa fa-question-circle' data-toggle='tooltip' data-placement='right' title='"+FrmDebiturBdnUsaha.fieldQuestion[FrmDebiturBdnUsaha.FRM_FIELD_KODE_KANTOR_CABANG]+"'></i> "
                            + "Kode Kantor Cabang <i style='color:red;'>m*</i></label>"
			+ "<div class='col-sm-8'>";
			    //start comboBox
			    Vector vCdKantorCbg =  PstContentDataKantorCabang.list(0,0,""+PstContentDataKantorCabang.fieldNames[PstContentDataKantorCabang.FLD_KODE_OJK]+"='"+debiturBdnUsaha.getKodeKtrCabang()+"'","");
                            ContentDataKantorCabang kdKantorCbg = new ContentDataKantorCabang();
                            for (int i = 0; i < vCdKantorCbg.size(); i++) {
                                kdKantorCbg = (ContentDataKantorCabang) vCdKantorCbg.get(i);
                            }
                          
                            returnData += "<input type='hidden' name='"+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_KANTOR_CABANG]+"' value='"+kdKantorCbg.getKodeOjk()+"'>"
                            + "<input type='text' name='"+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_KANTOR_CABANG]+"' class='form-control' value='"+kdKantorCbg.getKodeOjk()+" "+kdKantorCbg.getNamaKantorCabang()+"' readonly='readonly' data-required='required' data-error-message='"+FrmDebiturBdnUsaha.fieldError[FrmDebiturBdnUsaha.FRM_FIELD_KODE_KANTOR_CABANG]+"' data-type='text'>";
                            returnData += "</div>"
                            //end comboBox
		    + "</div>"
		    + "<div class='form-group hidden'>"
			+ "<label class='control-label col-sm-4'>Operasi Data<font color='red'>*</font></label>"
			+ "<div class='col-sm-8'>";
                            Vector operasiDataKey = new Vector(1,1);
                            Vector operasiDataVal = new Vector(1,1);
                            for(int i = 0; i < PstDebitur.operasiData.length; i++){
                                operasiDataKey.add(""+PstDebitur.operasiDataKey[i]);
                                operasiDataVal.add(""+PstDebitur.operasiData[i]);
                            }
			    returnData+=""
                            + ControlCombo.draw(FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_OPERASI_DATA], "-- Pilih --", ""+debiturBdnUsaha.getStatusOperasiData(), operasiDataKey, operasiDataVal, "data-required='required'", "form-control")
                            + "<input type='hidden' name='"+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_STATUS_DATA]+"' value='0'>"
                            + "<input type='hidden' name='"+FrmDebiturBdnUsaha.fieldNames[FrmDebiturBdnUsaha.FRM_FIELD_KODE_JENIS_NSB]+"' value='"+debiturBdnUsaha.getKodeJenisNsb()+"'>"        
			+ "</div>"
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
                    + "<input type='text' name='cif' class='form-control' id='cif'>"
                    + "<input type='hidden' name='type' id='type' value='2'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>No Identitas</label>"
                    + "<input type='text' name='noakteawal' class='form-control' id='noakteawal'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Nama Badan Usaha</label>"
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
                            + "<th>No Identitas</th>"
                            + "<th>Nama Badan Usaha</th>"
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
                    + "<input type='text' name='cif' class='form-control' id='cif'>"
                    + "<input type='hidden' name='type' id='type' value='2'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>No Identitas</label>"
                    + "<input type='text' name='noakteawal' class='form-control' id='noakteawal'>"
                + "</div>"
            + "</div>"
            + "<div class='col-sm-3'>"
                + "<div class='form-group'>"
                    + "<label>Nama Badan Usaha</label>"
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
                            + "<th>No Identitas</th>"
                            + "<th>Nama Badan Usaha</th>"
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
                        + "<th>No Identitas</th>"
                        + "<th>Nama Badan Usaha</th>"
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
        
        String whereClause = "";
        if(this.dataFor.equals("loadsummary")){
            whereClause = "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+"='0' AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1'";
        }else if(this.dataFor.equals("loadtanpapengurus")){
            whereClause = " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1' ";
        }else{
            whereClause = "debitur."+PstDebitur.fieldNames[PstDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]+"!='1'";
        }
        
        if(whereClause.length()>0){
            whereClause += " AND debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }else{
            whereClause += " debitur."+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
        }
        
        int summary = 0;
        if(this.dataFor.equals("loadtanpapengurus")){
            summary = PstDebiturBdnUsaha.getCountTidakAdaPengurus(whereClause,""+periodeId, kodeCabang);
        }else{
            summary = PstDebitur.getCountJoin(whereClause,""+periodeId, kodeCabang);
        }
        
        returnData +=""+summary;
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
            DebiturBdnUsaha debiturBdnUsaha = new DebiturBdnUsaha();
            Vector listData = PstDebiturBdnUsaha.list(0, 0, ""+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+cif+"' AND "+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_PERIODE_ID]+"='"+periodeId+"'", "");
            if(listData.size() > 0){
                debiturBdnUsaha = (DebiturBdnUsaha) listData.get(0);
            }
            CtrlDebiturBdnUsaha ctrlDebiturBdnUsaha = new CtrlDebiturBdnUsaha(request);
            if(debiturBdnUsaha.getOID()!=0){
                ctrlDebiturBdnUsaha.action(iCommand, debiturBdnUsaha.getOID(), 0, "");
            }
            returnData +=""+ctrlDebiturBdnUsaha.getMessage();
            this.oidReturn = 1;
        }
        
        
        return returnData;
    }
    
    public String nikSamaForm(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        Vector listData = PstDebiturBdnUsaha.listDoubleNik(0, 0, kodeCabang, periodeId);
        returnData+=""
        + "<table class='table table-bordered table-strped'>"
            + "<thead>"
                + "<tr>"
                    + "<th>No.</th>"
                    + "<th>No Rekening</th>"
                    + "<th>CIF</th>"
                    + "<th>No Identitas</th>"
                    + "<th>Nama</th>"
                    + "<th>Aksi</th>"
                + "</tr>"
            + "</thead>"
            + "<tbody>";
                for(int i = 0; i < listData.size(); i++){
                    DebiturBdnUsaha data = (DebiturBdnUsaha) listData.get(i);
                    Vector listOid = PstDebiturBdnUsaha.list(0, 1, ""+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+"='"+data.getCif()+"' AND "+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_PERIODE_ID]+"='"+periodeId+"'", "");
                    DebiturBdnUsaha debiturBdnUsaha = new DebiturBdnUsaha();
                    
                    if(listOid.size() > 0){
                        debiturBdnUsaha = (DebiturBdnUsaha) listOid.get(0);
                    }
                    returnData+=""
                    + "<tr>"
                        + "<td>"+(i+1)+"</td>"
                        + "<td>"+data.getNoRekening()+"</td>"
                        + "<td>"+data.getCif()+"</td>"
                        + "<td>"+data.getNoIdentitas()+"</td>"
                        + "<td>"+data.getNama()+"</td>"
                        + "<td><a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp?command="+Command.VIEW+"&oid="+debiturBdnUsaha.getOID()+"&type="+debiturBdnUsaha.getKodeJenisNsb()+"&cif="+data.getCif()+"&activetab=AjaxDebiturBadanUsaha&otheroid="+debiturBdnUsaha.getOID()+"' class='btn btn-warning'><i class='fa fa-pencil'></i> View</a></td>"
                    + "</tr>";
                }
                returnData+= 
                ""
            + "</tbody>"    
        + "</table>";
        return returnData;
    }
    public String getNikSama(HttpServletRequest request){
        String returnData = "";
        int userGroup = FRMQueryString.requestInt(request, "usergroup");
        String kodeCabang = FRMQueryString.requestString(request, "kodecabang");
        long periodeId = FRMQueryString.requestLong(request, "periode");
        Vector listSama = PstDebiturBdnUsaha.listDoubleNik(0, 0, kodeCabang, periodeId);
        
        returnData +=""+listSama.size();
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
