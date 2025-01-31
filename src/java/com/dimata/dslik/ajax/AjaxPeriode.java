/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.form.masterdata.CtrlPeriode;
import com.dimata.dslik.form.masterdata.FrmPeriode;
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
 * @author Dewa
 */
public class AjaxPeriode extends HttpServlet {

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
        this.oidReturn = 0;

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

        switch (this.iCommand) {
            case Command.SAVE:
                commandSave(request);
                break;

            case Command.LIST:
                commandList(request, response);
                break;

            case Command.DELETEALL:
                commandDeleteAll(request);
                break;

            case Command.DELETE:
                commandDelete(request);
                break;

            default:
                commandNone(request);
        }
        try {

            this.jSONObject.put("FRM_FIELD_HTML", this.htmlReturn);
            this.jSONObject.put("FRM_FIELD_RETURN_OID", this.oidReturn);
            this.jSONObject.put("FRM_FIELD_DATE_START", this.dateStart);
            this.jSONObject.put("FRM_FIELD_DATE_END", this.dateEnd);
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }

        response.getWriter().print(this.jSONObject);

    }

    public void commandNone(HttpServletRequest request) {
        if (this.dataFor.equals("showform")) {
            this.htmlReturn = showForm(request);
        }
    }

    public void commandSave(HttpServletRequest request) {
        if (this.dataFor.equals("showform")) {
            this.htmlReturn = savePeriode(request);
        }
    }

    public void commandDeleteAll(HttpServletRequest request) {
        if (this.dataFor.equals("deleteAll")) {
            this.htmlReturn = deleteAll(request);
        }
    }

    public void commandList(HttpServletRequest request, HttpServletResponse response) {
        if (this.dataFor.equals("list")) {
            String[] cols = {
                PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID],
                PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL],
                PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR],
                PstPeriode.fieldNames[PstPeriode.FLD_NAMA],
                PstPeriode.fieldNames[PstPeriode.FLD_KETERANGAN],
                PstPeriode.fieldNames[PstPeriode.FLD_POSTED],
                PstPeriode.fieldNames[PstPeriode.FLD_TGL_TERAKHIR_ENTRY]
            };
            jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }
    }

    public void commandDelete(HttpServletRequest request) {
        if (this.dataFor.equals("delete")) {
            this.htmlReturn = deleteAll(request);
        }
    }

    public JSONObject listDataTables(HttpServletRequest request, HttpServletResponse response, String[] cols, String dataFor, JSONObject result) {
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
            if (col < 0) {
                col = 0;
            }
        }
        if (sdir != null) {
            if (!sdir.equals("asc")) {
                dir = "desc";
            }
        }

        String whereClause = "";

        if (dataFor.equals("list")) {

            if (whereClause.length() > 0) {
                whereClause += "AND (" + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL] + " LIKE '%" + searchTerm + "%' "
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_NAMA] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_KETERANGAN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_POSTED] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_TGL_TERAKHIR_ENTRY] + " LIKE '%" + searchTerm + "%')";
            } else {
                whereClause += " (" + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL] + " LIKE '%" + searchTerm + "%' "
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_NAMA] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_KETERANGAN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_POSTED] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_TGL_TERAKHIR_ENTRY] + " LIKE '%" + searchTerm + "%')";
            }
        }

        String colName = cols[col];
        int total = -1;

        if (dataFor.equals("list")) {
            total = PstPeriode.getCount(whereClause);
        }

        this.amount = amount;

        this.colName = colName;
        this.dir = dir;
        this.start = start;
        this.colOrder = col;

        try {
            result = getData(total, request, dataFor);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return result;
    }

    public JSONObject getData(int total, HttpServletRequest request, String datafor) {

        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        Periode periode = new Periode();
        String whereClause = "";
        String order = "";
        boolean privUpdate = FRMQueryString.requestBoolean(request, "privupdate");
        boolean privDelete = FRMQueryString.requestBoolean(request, "privdelete");

        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");

        if (this.searchTerm == null) {
            whereClause += "";
        } else {
            if (datafor.equals("list")) {

                if (whereClause.length() > 0) {
                    whereClause += "AND (" + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL] + " LIKE '%" + searchTerm + "%' "
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_NAMA] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_KETERANGAN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_POSTED] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_TGL_TERAKHIR_ENTRY] + " LIKE '%" + searchTerm + "%')";
                } else {
                    whereClause += " (" + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL] + " LIKE '%" + searchTerm + "%' "
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_NAMA] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_KETERANGAN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_POSTED] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstPeriode.fieldNames[PstPeriode.FLD_TGL_TERAKHIR_ENTRY] + " LIKE '%" + searchTerm + "%')";
                }
            }
        }

        if (this.colOrder >= 0) {
            order += "" + colName + " " + dir + "";
        }

        Vector listData = new Vector(1, 1);
        if (datafor.equals("list")) {
            listData = PstPeriode.list(start, amount, whereClause, order);
        }

        for (int i = 0; i <= listData.size() - 1; i++) {
            JSONArray ja = new JSONArray();
            if (datafor.equals("list")) {
                periode = (Periode) listData.get(i);
                ja.put("" + (this.start + i + 1));
                ja.put("" + periode.getTglAwal());
                ja.put("" + periode.getTglAkhir());
                ja.put("" + periode.getNama());
                //ja.put("" + periode.getKeterangan());
                String posted="";
                if(periode.getPosted()==0){
                    posted="Periode Terbuka";
                }else{
                    posted="Periode Tertutup";
                }
                ja.put("" + posted);
                ja.put("" + periode.getTglTerakhirEntry());
                if(periode.getPosted()!=1){
                    String buttonUpdate = "";
                    if(privUpdate){
                        buttonUpdate += "<button type='button' class='btn btn-warning btneditgeneral' data-oid='" + periode.getOID() + "' data-for='showform'><i class='fa fa-pencil'></i> Buka Periode </button>";
                    }
                    //cek apakah ada data di periode tsb
                    int xxx = PstDebiturIndividu.getCount(PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERIODE_ID]+"='"+periode.getOID()+"'");
                    if(xxx<=0 && privDelete == true){
                        buttonUpdate=buttonUpdate+"<button class='btn btn-danger button-delete pull-right' type='button' data-oid='" + periode.getOID() + "' data-for='delete' data-command = '" + Command.DELETE + "'>Delete</button> </div>";
                    }
                    
                    ja.put(""+buttonUpdate);
                }else{
                    String buttonUpdate = "";
                    if(privUpdate){
                        buttonUpdate += "<button type='button' class='btn btn-default btneditgeneral' data-oid='" + periode.getOID() + "' data-for='showform'><i class='fa fa-close'></i> Tutup Periode </button>";
                    }
                    ja.put(""+buttonUpdate);
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

    public String showForm(HttpServletRequest request) {
        Periode periode = new Periode();
        String tglAwal = "";
        String tglAkhir = "";
        String tglEntry = "";
        String selectedOpen="";
        String selectedClosed="";
        if (oid != 0) {
            try {
                periode = PstPeriode.fetchExc(oid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tglAwal = Formater.formatDate(periode.getTglAwal(), "yyyy-MM-dd");
            tglAkhir = Formater.formatDate(periode.getTglAkhir(), "yyyy-MM-dd");
            tglEntry = Formater.formatDate(periode.getTglTerakhirEntry(), "yyyy-MM-dd");
            if(periode.getPosted()==0){
                selectedOpen="";
                selectedClosed="selected";
            }else{
                selectedOpen="selected";
                selectedClosed="";
            }
        }else {
            Date dNow = new Date();
            tglAwal = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglAkhir = Formater.formatDate(dNow, "yyyy-MM-dd");
            tglEntry = Formater.formatDate(dNow, "yyyy-MM-dd");
        }
        
        
        
        String returnData = ""
            + "<div class='row'>"
                + "<div class='col-md-12'>"
                    + "<div class='form-group'>"
                        + "<label>Tanggal Awal</label>"
                        //+ "<input type='text' class='form-control' name='" + FrmPeriode.fieldNames[FrmPeriode.FRM_FIELD_TGL_AWAL] + "' value='" + periode.getTglAwal()+ "'>"
                        + "<div class='input-group'>"
                            + "<input type='text' class='form-control datepicker' name='" + FrmPeriode.fieldNames[FrmPeriode.FRM_FIELD_TGL_AWAL] + "' value='" + tglAwal + "'>"
                            + "<div class='input-group-addon'>"
                                + "<i class='fa fa-calendar'></i>"
                            + "</div>"
                        + "</div>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Tanggal Akhir</label>"
                        //+ "<input type='text' class='form-control' name='" + FrmPeriode.fieldNames[FrmPeriode.FRM_FIELD_TGL_AKHIR] + "' value='" + periode.getTglAkhir()+ "'>"
                        + "<div class='input-group'>"
                            + "<input type='text' class='form-control datepicker' name='" + FrmPeriode.fieldNames[FrmPeriode.FRM_FIELD_TGL_AKHIR] + "' value='" + tglAkhir + "'>"
                            + "<div class='input-group-addon'>"
                                + "<i class='fa fa-calendar'></i>"
                            + "</div>"
                        + "</div>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Nama</label>"
                        + "<input type='text' class='form-control' name='" + FrmPeriode.fieldNames[FrmPeriode.FRM_FIELD_NAMA] + "' value='" + periode.getNama()+ "'>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Keterangan</label>"
                        + "<input type='text' class='form-control' name='" + FrmPeriode.fieldNames[FrmPeriode.FRM_FIELD_KETERANGAN] + "' value='" + periode.getKeterangan()+ "'>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Status</label>"
                        + "<select name='"+ FrmPeriode.fieldNames[FrmPeriode.FRM_FIELD_POSTED] +"' class='form-control' value='"+periode.getPosted()+"' required='required'>"
				+ "<option value='0' "+selectedOpen+" > Buka Periode</option>"
				+ "<option value='1'"+selectedClosed+" > Tutup Periode</option>"
			    + "</select>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Tanggal Terakhir Entry</label>"
                        + "<div class='input-group'>"
                            + "<input type='text' class='form-control datepicker' name='" + FrmPeriode.fieldNames[FrmPeriode.FRM_FIELD_TGL_TERAKHIR_ENTRY] + "' value='" + tglEntry + "'>"
                            + "<div class='input-group-addon'>"
                                + "<i class='fa fa-calendar'></i>"
                            + "</div>"
                        + "</div>"
                    + "</div>"
                + "</div>"
            + "</div>";

        return returnData;
    }

    public String savePeriode(HttpServletRequest request) {
        String returnData = "";
        CtrlPeriode ctrlPeriode = new CtrlPeriode(request);
        ctrlPeriode.action(iCommand, oid, oidDelete);
        returnData = ctrlPeriode.getMessage();
        return returnData;
    }

    public String deleteAll(HttpServletRequest request) {
        String returnData = "";
        CtrlPeriode ctrlPeriode = new CtrlPeriode(request);
        ctrlPeriode.action(iCommand, oid, oidDelete);
        returnData = ctrlPeriode.getMessage();
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
        processRequest(request, response);
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
