/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.dimata.dslik.entity.masterdata.OutletConnection;
import com.dimata.dslik.entity.masterdata.PstConnection;
import com.dimata.dslik.form.masterdata.CtrlConnection;
import com.dimata.dslik.form.masterdata.FrmConnection;
import com.dimata.dslik.session.proses.Configurasi;
import com.dimata.gui.jsp.ControlCombo;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author dimata005
 */
public class AjaxDatabaseConnection extends HttpServlet {

    
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
    
    
    //INT
    private int iCommand = 0;
    private int iErrCode = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, JSONException {
        
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
	    default : commandNone(request);
	}
	try{
	    
	    this.jSONObject.put("FRM_FIELD_HTML", this.htmlReturn);
	    this.jSONObject.put("FRM_FIELD_RETURN_OID", this.oidReturn);
	}catch(JSONException jSONException){
	    jSONException.printStackTrace();
	}
	
	response.getWriter().print(this.jSONObject);
        
    }
    
    public void commandNone(HttpServletRequest request){
	if(this.dataFor.equals("showform")){
	    this.htmlReturn = chanelForm(request);
	}
    }
    
    public void commandSave(HttpServletRequest request){
	CtrlConnection ctrlConnection = new CtrlConnection(request);
	this.iErrCode = ctrlConnection.action(this.iCommand, this.oid, this.oidDelete);
	String message = ctrlConnection.getMessage();
	this.htmlReturn = "<i class='fa fa-info'></i> "+message;
    }
    
    public void commandDeleteAll(HttpServletRequest request){
	CtrlConnection ctrlConnection = new CtrlConnection(request);
	this.iErrCode = ctrlConnection.action(this.iCommand, this.oid, this.oidDelete);
	String message = ctrlConnection.getMessage();
	this.htmlReturn = "<i class='fa fa-info'></i> "+message;
    }
    
    public void commandList(HttpServletRequest request, HttpServletResponse response){
	if(this.dataFor.equals("list")){
	    String[] cols = { 
                PstConnection.fieldNames[PstConnection.FLD_DBDRIVER],
		PstConnection.fieldNames[PstConnection.FLD_DBDRIVER]
            };
	    jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
	}
    }
    
    public JSONObject listDataTables (HttpServletRequest request, HttpServletResponse response, String[] cols, String dataFor, JSONObject result){
        this.searchTerm = FRMQueryString.requestString(request, "sSearch");
        
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");
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
        
        if(dataFor.equals("list")){
	    //whereClause += " ("+PstMarketingPlan.fieldNames[PstMarketingPlan.FLD_MARKETINGPLANID]+" LIKE '%"+this.searchTerm+"%' "
            //	    + "OR "+PstMarketingPlan.fieldNames[PstMarketingPlan.FLD_MARKETINGPLANNAME]+" LIKE '%"+this.searchTerm+"%')";
        }
	
        String colName = cols[col];
        int total = -1;
	
	if(dataFor.equals("list")){
	    total = PstConnection.getCount(whereClause);
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
        OutletConnection outletConnection = new OutletConnection();

	
	String whereClause = "";
        String order ="";
	
        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");

        if (this.searchTerm == null){
            whereClause += "";                  
        }else{
	     if(dataFor.equals("list")){
                //whereClause += " ("+PstMarketingPlan.fieldNames[PstMarketingPlan.FLD_MARKETINGPLANID]+" LIKE '%"+this.searchTerm+"%' "
                //        + "OR "+PstMarketingPlan.fieldNames[PstMarketingPlan.FLD_MARKETINGPLANNAME]+" LIKE '%"+this.searchTerm+"%')";
            }
        }
        
        if (this.colOrder>=0){
            order += ""+colName+" "+dir+"";
        }
	
        Vector listData = new Vector(1,1);
	if(datafor.equals("list")){
	    listData = PstConnection.list(this.start, this.amount,whereClause,order);
	}
         
        for (int i =0 ; i<=listData.size()-1;i++){
	    JSONArray ja = new JSONArray();
	    if(datafor.equals("list")){
		outletConnection = (OutletConnection) listData.get(i);
		ja.put(""+(this.start+i+1));//1
		ja.put(""+outletConnection.getDbdriver());//2
                ja.put(""+outletConnection.getDburl());//3
                ja.put(""+outletConnection.getDbuser());//4
                if(outletConnection.getTypeConnection()==0){
                    ja.put("Aktive");//5
                }else{
                    ja.put("Non Aktive");//5
                }
                
		String buttonUpdate = "";
		if(true){
		    buttonUpdate = "<button class='btn btn-warning btneditgeneral' data-oid='"+outletConnection.getOID()+"' data-for='showform' type='button'><i class='fa fa-pencil'></i> Edit</button> ";
		}
                buttonUpdate += "";
		ja.put(buttonUpdate+"<div class='btn btn-default' type='button'><input type='checkbox' name='"+FrmConnection.fieldNames[FrmConnection.FRM_FIELD_OID]+"' class='"+FrmConnection.fieldNames[FrmConnection.FRM_FIELD_OID]+"' value='"+outletConnection.getOID()+"'> Delete</div>");
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
    
    public String chanelForm(HttpServletRequest request){
	
	//CHECK DATA
	OutletConnection outletConnection = new OutletConnection();
	if(this.oid != 0){
	    try{
		outletConnection = PstConnection.fetchExc(this.oid);
	    }catch(Exception ex){
		ex.printStackTrace();
	    }
	}
        
        String textListHeader[][] = {
            {"No", "dbdriver", "dburl", "dbuser", "dbpasswd", "dbminconn","dbmaxconn","logconn","logapp","logsize","fordate","fordecimal","forcurrency","lokasi kasir","status koneksi"},
            {"No", "dbdriver", "dburl", "dbuser", "dbpasswd", "dbminconn","dbmaxconn","logconn","logapp","logsize","fordate","fordecimal","forcurrency","lokasi kasir","connection status"}
        };
        
        Vector valTypeConnection = new Vector();
        Vector viewTypeConnection = new Vector();
        valTypeConnection.add("0");
        viewTypeConnection.add("Aktive");
        valTypeConnection.add("1");
        viewTypeConnection.add("Non Aktive");
        
        
        Vector valJenisLJK=new Vector();
        Vector keyJenisLJK = new Vector();
        for (int i = 0 ; i< Configurasi.valJenisLJK.length;i++){
             valJenisLJK.add(""+Configurasi.valJenisLJK[i]);
             keyJenisLJK.add(""+Configurasi.keyJenisLJK[i]);
        }
        
        Vector valKodeLJK=new Vector();
        Vector keyKodeLJK = new Vector();
        for (int i = 0 ; i< Configurasi.valKodeLJK.length;i++){
             valKodeLJK.add(""+Configurasi.valKodeLJK[i]);
             keyKodeLJK.add(""+Configurasi.keyKodeLJK[i]);
        }
        
        int SESS_LANGUAGE=0;
	String returnData = ""
	+ "<div class='row'>"
	    + "<div class='col-md-6'>"
                + "<input type='hidden' name='"+FrmConnection.fieldNames[FrmConnection.FRM_FIELD_OID]+"'  id='"+FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBDRIVER]+"' class='form-control' value='"+outletConnection.getDbdriver()+"'>"
                 + "<div class='form-group'>"
		    + "<label>CONFIGURASI DATABASE</label>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][1]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBDRIVER]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBDRIVER]+"' class='form-control' value='"+outletConnection.getDbdriver()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][2]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBURL]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBURL]+"' class='form-control' value='"+outletConnection.getDburl()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>Database MASTER</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DATABASE]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DATABASE]+"' class='form-control' value='"+outletConnection.getDbDatabase()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>Database DBLMONT</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DATABASE_BACKUP]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DATABASE_BACKUP]+"' class='form-control' value='"+outletConnection.getDbDatabaseBackup()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][3]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBUSER]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBUSER]+"' class='form-control' value='"+outletConnection.getDbuser()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][4]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBPASSWD]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBPASSWD]+"' class='form-control' value='"+outletConnection.getDbpasswd()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][5]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBMINCONN]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBMINCONN]+"' class='form-control' value='"+outletConnection.getDbminconn()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][6]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBMAXCONN]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_DBMAXCONN]+"' class='form-control' value='"+outletConnection.getDbmaxconn()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][7]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_LOGCONN]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_LOGCONN]+"' class='form-control' value='"+outletConnection.getLogconn()+"'>"
		+ "</div>"
                 + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][8]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_LOGAPP]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_LOGAPP]+"' class='form-control' value='"+outletConnection.getLogapp()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][9]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_LOGSIZE]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_LOGSIZE]+"' class='form-control' value='"+outletConnection.getLogsize()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][10]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_FORDATE]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_FORDATE]+"' class='form-control' value='"+outletConnection.getFordate()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][11]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_FORDECIMAL]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_FORDECIMAL]+"' class='form-control' value='"+outletConnection.getFordecimal()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][12]+"</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_FORCURRENCY]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_FORCURRENCY]+"' class='form-control' value='"+outletConnection.getForcurrency()+"'>"
		+ "</div>"
                 + "<div class='form-group'>"
		    + "<label>"+textListHeader[SESS_LANGUAGE][14]+"</label>"
		    //+ "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_FORDECIMAL]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_FORDECIMAL]+"' class='form-control' value='"+outletConnection.getForcurrency()+"'>"
                    +ControlCombo.draw(FrmConnection.fieldNames[FrmConnection.FRM_FIELD_TYPE_CONNECTION], null,""+outletConnection.getTypeConnection(), valTypeConnection, viewTypeConnection, "", "form-control")
		+ "</div>"
	    + "</div>"
            + "<div class='col-md-6'>"
                + "<div class='form-group'>"
		    + "<label>CONFIGURASI DELIMITED TEXT</label>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>Jenis LJK</label>"
		    +ControlCombo.draw(FrmConnection.fieldNames[FrmConnection.FRM_FIELD_JENIS_LJK], null,""+outletConnection.getJenisLJK(), valJenisLJK, keyJenisLJK, "", "form-control")
		+ "</div>"
                 + "<div class='form-group'>"
		    + "<label>Kode LJK</label>"
		    //+ControlCombo.draw(FrmConnection.fieldNames[FrmConnection.FRM_FIELD_KODE_LJK], null,""+outletConnection.getKodeLJK(), valKodeLJK, keyKodeLJK, "", "form-control")
                + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_KODE_LJK]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_KODE_LJK]+"' class='form-control' value='"+outletConnection.getKodeLJK()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>Path Delimited Text</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_PATH_DILIMITED_TEXT]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_PATH_DILIMITED_TEXT]+"' class='form-control' value='"+outletConnection.getPathDelimitedText()+"'>"
		+ "</div>"
                + "<div class='form-group'>"
		    + "<label>Path Delimited Text Summary</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_PATH_DILIMITED_TEXT_SUMMARY]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_PATH_DILIMITED_TEXT_SUMMARY]+"' class='form-control' value='"+outletConnection.getPathDelimitedTextSummary()+"'>"
		+ "</div>"
                 + "<div class='form-group'>"
		    + "<label>Jenis Create Delimited (pelatihan =1, real=0)</label>"
		    + "<input type='text' name='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_PATH_DILIMITED_TEXT_SUMMARY]+"'  id='"+ FrmConnection.fieldNames[FrmConnection.FRM_FIELD_PATH_DILIMITED_TEXT_SUMMARY]+"' class='form-control' value='"+outletConnection.getPathDelimitedTextSummary()+"'>"
		+ "</div>"
            + "</div>"     
	+ "</div>";
	return returnData;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(AjaxDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(AjaxDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
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
