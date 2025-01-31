/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.common.entity.logger.LogSysHistory;
import com.dimata.common.entity.logger.PstLogSysHistory;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AjaxPerlengkapanData extends HttpServlet {

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
    
    
    //INT
    private int iCommand = 0;
    private int iErrCode = 0;

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
	    this.htmlReturn = searchDebiturIndividu(request);
	}else if (this.dataFor.equals("searchhistory")){
            this.htmlReturn = searchHistory(request);
        }
    }
    
    public void commandSave(HttpServletRequest request){
	
    }
    
    public void commandDeleteAll(HttpServletRequest request){
	if(this.dataFor.equals("deleterateplan")){
	    
	}
    }
    
    
    
    public void commandList(HttpServletRequest request, HttpServletResponse response){
	if(this.dataFor.equals("listperlengkapandata")){
	    String[] cols = { 
                "ID"
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}else if (this.dataFor.equals("searchhistory")){
            String[] cols = { 
                ""+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_ID]+"",
                ""+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_UPDATE_DATE]+"",
                ""+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_USER_ACTION]+"",
                ""+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DETAIL]+"",
                ""+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_ID]+""
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
        
	
        
        String whereClause = "";
        
        if(dataFor.equals("listperlengkapandata")){
	    	    
	    if(whereClause.length() > 0){
		whereClause += "";
	    }else{
		whereClause += "";
	    }
	}else if (dataFor.equals("searchhistory")){
            long oid = FRMQueryString.requestLong(request, "FRM_FIELD_OID");
            whereClause = ""
                + " 1=1";
            
            whereClause += ""
                + " AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+oid+"'";
            
            whereClause += ""
                + " AND ( "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_UPDATE_DATE]+" like '%"+this.searchTerm+"%'"
                + " OR "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" like '%"+this.searchTerm+"%'"
                + " OR "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_USER_ACTION]+" like '%"+this.searchTerm+"%'"
                + " OR "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DETAIL]+" like '%"+this.searchTerm+"%' )";
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("listperlengkapandata")){
	    total = 1;//PstBookingExtra.getCount(whereClause);
	}else if (dataFor.equals("searchhistory")){
            total = PstLogSysHistory.getCount(whereClause);
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

	
	String whereClause = "";
        String order ="";
	boolean privUpdate = FRMQueryString.requestBoolean(request, "privUpdate");
        
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
        
        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(datafor.equals("listperlengkapandata")){
		 
		if(whereClause.length() > 0){
		    whereClause += "";
		}else{
		    whereClause += "";
		}
            }else if (datafor.equals("searchhistory")){
                long oid = FRMQueryString.requestLong(request, "FRM_FIELD_OID");
                whereClause += ""
                    + " 1=1";
                
                whereClause += ""
                    + " AND "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"='"+oid+"'";
                
                whereClause += ""
                    + " AND ( "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_UPDATE_DATE]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_LOGIN_NAME]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_USER_ACTION]+" like '%"+this.searchTerm+"%'"
                    + " OR "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DETAIL]+" like '%"+this.searchTerm+"%' )";
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(datafor.equals("listperlengkapandata")){
	    String[] datas = {
		"110011",
		"Steve Job",
		"Laki-laki",
		"123456",
		"US",
		"09121",
		"stevejob@gmail.com"
	    };
	    listData.add(datas);
	    
	    String[] datas2 = {
		"110012",
		"Bill Gates",
		"Laki-laki",
		"123456",
		"UK",
		"09121",
		"billgates@gmail.com"
	    };
	    listData.add(datas2);//PstBookingExtra.list(this.start, this.amount,whereClause,order);
	}else if (datafor.equals("searchhistory")){
            listData = PstLogSysHistory.listPurchaseOrder(this.start, this.amount, whereClause, order);
        }
         
        
        
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
	    if(datafor.equals("listperlengkapandata")){
		String[] data = (String[]) listData.get(i);
		ja.put(""+(this.start+i+1));
		if(data!=null){
		    for(int iData = 0; iData < data.length; iData++){
			ja.put(""+data[iData]);
		    }
		}
		ja.put("<div class='btn-group'><button type='button' class='btn btn-warning addedit' data-oid='1' data-command='"+Command.EDIT+"' data-type='"+(this.start+i+1)+"'><i class='fa fa-pencil'></i> Edit</button>");
			//+ "<button type='button' class='btn btn-primary addedit' data-oid='1' data-command='"+Command.VIEW+"' data-type='"+(this.start+i+1)+"'>View</button></div>");
		
		array.put(ja);
	    }else if (datafor.equals("searchhistory")){
                LogSysHistory logSysHistory = new LogSysHistory();
                logSysHistory = (LogSysHistory) listData.get(i);
                ja.put(""+(this.start+i+1));
                ja.put(""+(Formater.formatDate(logSysHistory.getLogUpdateDate(),"yyyy-MM-dd")));
                ja.put(""+(logSysHistory.getLogLoginName()));
                ja.put(""+(logSysHistory.getLogUserAction()));
                ja.put(""+(logSysHistory.getLogDetail()));
                if(true){
                    //ja.put("");
                }else{
                    //ja.put("<button class='btn btn-primary' class='roleBackData' data-oid='"+logSysHistory.getOID()+"'>Kembalikan Data</button>");
                }
                
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
    
    
    public String searchDebiturIndividu(HttpServletRequest request){
	String returnData = "";
	
	
	return returnData;
    }
    
    public String searchHistory(HttpServletRequest request){
        String returnData="";
        
        long oid= FRMQueryString.requestLong(request, "FRM_FIELD_OID");
        String whereHistory = ""
            + " "+PstLogSysHistory.fieldNames[PstLogSysHistory.FLD_LOG_DOCUMENT_ID]+"";
        
        
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
