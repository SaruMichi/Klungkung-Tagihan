<%-- 
    Document   : report_wp
    Created on : Aug 21, 2015, 9:32:37 PM
	update : 2017-01-24
    Author     : Administrator
--%>
<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="com.dimata.wpupload.entity.esptpd.ESPTPD"%>
<%@page import="com.dimata.wpupload.entity.esptpd.PstESPTPD"%>
<%@page import="com.dimata.dtaxintegration.session.SessReportWp"%>
<%-- 
    Document   : payment_phr
    Created on : May 19, 2015, 7:00:57 PM
    Author     : dimata005
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dimata.dtaxintegration.session.DTaxIntegrationMonitor"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PaymentPhr"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PstPaymentPhr"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Payment"%>
<%@page import="com.dimata.webclient.EchoLaporanPaymentDetail"%>
<%@page import="com.dimata.dtaxintegration.entity.laporan.LaporanPayment"%>
<%-- 
    Document   : report_bbm_summary
    Created on : May 1, 2015, 10:06:28 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Simpatda"%>
<%@page import="com.dimata.dtaxintegration.session.SessSimpatda"%>
<%@page import="com.dimata.dtaxintegration.session.SrcReport"%>
<%@page import="com.dimata.qdep.form.FRMHandler"%>
<%@page import="com.dimata.gui.jsp.ControlDate"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<%@ include file = "../../main/javainit.jsp" %>
<% //int  appObjCode = AppObjInfo.composeObjCode(AppObjInfo.G1_LOGIN, AppObjInfo.G2_LOGIN, AppObjInfo.OBJ_LOGIN_LOGIN); %>
<%@ include file = "../../main/checkuser.jsp" %>


<%!//proses yang akan ditampilkan

    public String drawListDataPaymentPhrBpd(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("SSPD", "200px");//2
        ctrlist.addHeader("NAMA", "200px");//3
        ctrlist.addHeader("DATE", "200px");//4
        ctrlist.addHeader("ALAMAT", "200px");//5
        ctrlist.addHeader("BULAN", "200px");//6
        ctrlist.addHeader("TAHUN", "200px");//7
        ctrlist.addHeader("POKOK", "200px");//8
        ctrlist.addHeader("DENDA", "200px");//9
        ctrlist.addHeader("JUMLAH_BAYAR", "200px");//10
        ctrlist.addHeader("STATUS", "200px");//10
        
        if (iCommand != Command.EDIT) {
            ctrlist.setLinkRow(0);
        }
        
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        
        for (int i = 0; i < objectClass.size(); i++) {
            
            //Simpatda simpatda = (Simpatda) objectClass.get(i);
            Payment payment = (Payment) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+payment.getId());//2
            rowx.add(""+payment.getNama());//3
            rowx.add(""+payment.getInstansi());//4
            rowx.add(""+payment.getAlamatWp());//5
            rowx.add(""+payment.getBulan());//6
            rowx.add(""+payment.getTahun());//7
            rowx.add(""+payment.getPokok());//8
            rowx.add(""+payment.getDenda());//9
            rowx.add(""+payment.getTagihan());//10
            rowx.add(""+payment.getStsReversal());//10
            
            total=total+Double.parseDouble(payment.getTagihan());
            
            lstData.add(rowx);
        }
        
        rowx = new Vector(1, 1);
        
        //rowx.add("");
        //rowx.add("");
        //rowx.add("<b>Total<b>");
       // rowx.add(""+FRMHandler.userFormatStringDecimal(total));
        
        rowx.add("");//1
        rowx.add("");//2
        rowx.add("");//3
        rowx.add("");//4
        rowx.add("");//5
        rowx.add("");//6
        rowx.add("");//7
        rowx.add("");//8
        rowx.add("<b>Total<b>");//9
        rowx.add(""+FRMHandler.userFormatStringDecimal(total));//10
        rowx.add("");//10
            
        lstData.add(rowx);

        return ctrlist.drawBootstrapStrip();
    }

    public String drawListDataPaymentPhrDispenda(int iCommand, Vector objectClass, long gradeId, String approot) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("NPWPD", "200px");//2
        ctrlist.addHeader("NAMA BADAN", "200px");//3
        ctrlist.addHeader("TANGGAL", "200px");//4
        ctrlist.addHeader("MASA PAJAK", "200px");//5
        ctrlist.addHeader("TAHUN", "200px");//6
        ctrlist.addHeader("OMZET", "200px");//7
        ctrlist.addHeader("TARIF", "200px");//8
        ctrlist.addHeader("JUMLAH PAJAK", "200px");//9
        ctrlist.addHeader("SERVICE", "200px");//10
        ctrlist.addHeader("JUMLAH BAYAR", "200px");//11
        ctrlist.addHeader("ACTION", "200px");//11 

        if (iCommand != Command.EDIT) {
            ctrlist.setLinkRow(0);
        }
        
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        
        for (int i = 0; i < objectClass.size(); i++) {
            ESPTPD eSPTPD = (ESPTPD) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+eSPTPD.getENPWPD());//2
            rowx.add(""+eSPTPD.getNamaBadan());//3
            rowx.add(""+eSPTPD.getETglRekam());//4
            rowx.add(""+eSPTPD.getEMasaPajak());//5
            rowx.add(""+eSPTPD.getETahunPajak());//6
            rowx.add(""+eSPTPD.getEJumlahOmzet());//7
            rowx.add(""+eSPTPD.getETarif());//8
            rowx.add(""+FRMHandler.userFormatStringDecimal(eSPTPD.getEJumlahPajak()));//9
            rowx.add(""+FRMHandler.userFormatStringDecimal(eSPTPD.getEServiceTax()));//10
            rowx.add(""+FRMHandler.userFormatStringDecimal(eSPTPD.getEHarusBayar()));//11
            /*String button = "<div class=\"dropdown\">\n" 
                    + "<button class=\"btn btn-success dropdown-toggle\" type=\"button\" id=\"menu1\" data-toggle=\"dropdown\">Action\n" 
                    + "</button>\n" 
                    + "<ul class=\"dropdown-menu \" role=\"menu\" aria-labelledby=\"menu1\" style=\"position: absolute;top:-10%;margin-left:-200%\">\n"
                    + "<li class='editLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getETahunPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getENPWPD()+"' "
                    + "data-approot='"+approot+"'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-pencil\"></i> Edit</a></li>\n" 
                    + "<li class=\"divider\"></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getETahunPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getENPWPD()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='1'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 1</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getETahunPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getENPWPD()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='2'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 2</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getETahunPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getENPWPD()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='3'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 3</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getETahunPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getENPWPD()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='4'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 4</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getETahunPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getETahunPajak()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='5'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 5</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getETahunPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getETahunPajak()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='6'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 6</a></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getEMasaPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getEMasaPajak()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='7'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" style='cursor:pointer'>"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 7</a></li>\n" 
                    + "</ul>\n" 
                    + "</div>";*/
            String datalampiran= "<div class=\"dropdown\">\n" 
                    + "<button class=\"btn btn-success dropdown-toggle\" type=\"button\" id=\"menu1\" data-toggle=\"dropdown\">Action\n" 
                    + "</button>\n" 
                    + "<ul class=\"dropdown-menu \" role=\"menu\" aria-labelledby=\"menu1\" style=\"position: absolute;top:-10%;margin-left:-200%\">\n"
                    + "<li class=\"divider\"></li>\n" 
                    + "<li class='printLaporan' data-masa-pajak='"+eSPTPD.getEMasaPajak()+"'"
                    + "data-tahun-pajak='"+eSPTPD.getETahunPajak()+"' "
                    + "data-npwpd='"+eSPTPD.getENPWPD()+"' "
                    + "data-approot='"+approot+"'"
                    + "data-lampiran='1'"
                    + "role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"
                    + "<i class=\"icon icon-print\"></i> Cetak Lampiran 1</a></li>\n" ;     
            rowx.add(""+datalampiran);//11
            total=total+eSPTPD.getEHarusBayar();//10
            
            lstData.add(rowx);
        }
        
        rowx = new Vector(1, 1);
        
        rowx.add("");//1
        rowx.add("");//1
        rowx.add("");//2
        rowx.add("");//3
        rowx.add("");//4
        rowx.add("");//6
        rowx.add("");//7
        rowx.add("");//8
        rowx.add("");//9
        rowx.add("<b>Total<b>");//10
        rowx.add(""+FRMHandler.userFormatStringDecimal(total));//11
        rowx.add("");//9
        lstData.add(rowx);

        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidDriver = FRMQueryString.requestLong(request, "hidden_driver_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");
    
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";//FRMQueryString.requestString(request, "tanggalEnd");
    
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    String oEnd = "";//FRMQueryString.requestString(request, "tanggalEnd");
    
    double exchangeRate = FRMQueryString.requestDouble(request, "exchangeRate");
    double pemotongan = FRMQueryString.requestDouble(request, "pemotongan");
    
    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = "";//PstTransportServiceOrder.fieldNames[PstTransportServiceOrder.FLD_ID];
    ControlLine ctrLine = new ControlLine();
    Vector listReportDetail = new Vector(1, 1);

    //membuat command menjadi none kembali setelah menjalankan delete all
    if (iCommand == Command.DELETEALL && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;
    }

    /* get record to display */
    Date newDay=new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    if(startDate.equals("")){
        startDate=Formater.formatDate(newDay,"yyyy-MM-dd");
        startEnd=Formater.formatDate(newDay,"yyyy-MM-dd");
        Date transaksiDate = formatter.parse(startDate);
        oDate = Formater.formatDate(transaksiDate,"yyyy-MM-dd HH:mm:ss");
        oEnd = Formater.formatDate(transaksiDate,"yyyy-MM-dd HH:mm:ss");
    }else{
        String dateStringTransaksi = startDate;
        String dateEndStringTransaksi = startEnd;
        try {
                Date transaksiDate = formatter.parse(dateStringTransaksi);
                oDate = Formater.formatDate(transaksiDate,"yyyy-MM-dd HH:mm:ss");
                
                Date transaksiEndDate = formatter.parse(dateEndStringTransaksi);
                oEnd = Formater.formatDate(transaksiEndDate,"yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    /*data table payment*/
    Vector listReportWp = new Vector();
    if(AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
        whereClause = ""+PstESPTPD.fieldNames[PstESPTPD.FLD_TGL_REKAM]
                  +" BETWEEN TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS')"
                  +" AND TO_DATE ('"+oEnd+"','YYYY-MM-DD HH24:MI:SS')";
    }else{
        whereClause = ""+PstESPTPD.fieldNames[PstESPTPD.FLD_TGL_REKAM]
                  +" BETWEEN '"+startDate+"'"
                  +" AND '"+startEnd+"'";
    }
    
    listReportWp = SessReportWp.list(0, 0, whereClause, "");
    
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pajak dan Retribusi Daerah Terintegrasi Pembayaran Online</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link rel="stylesheet" href="../../styles/datepicker/css/jquery.ui.all.css">
        <link href="../../styles/bootstrap3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="../../styles/bootstrap3.1/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../../styles/bootstrap3.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- DATA TABLES -->
        <link href="../../styles/bootstrap3.1/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="../../styles/bootstrap3.1/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="../../styles/bootstrap3.1/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="../../styles/bootstrap3.1/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <script language="JavaScript">
            
            function cmdPrint(){
                document.driver.command.value="<%=Command.PRINT%>";
                document.driver.action="report_wp.jsp";
                document.driver.submit();
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="report_wp.jsp";
                document.driver.submit();
            }
            
            function cmdPrintExcel(){
                var myWindow = window.open("report_wp_excel.jsp?tanggalStart=<%=oDate%>&tanggalEnd=<%=oEnd%>&exchangeRate=<%=exchangeRate%>&pemotongan=<%=pemotongan%>", "MsgWindow", "toolbar=no, scrollbars=yes, resizable=yes, top=500, left=500, width=1500, height=800");
                if (window.focus) 
                 {
                   myWindow.focus();
                 }
            }
            
            function cmdPrintPdf(){
                
            }
            
        </script>
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <%@ include file = "../../header_mobile.jsp" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <%@ include file = "../../menu_left_mobile.jsp" %> 

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Pajak dan Retribusi Daerah Terintegrasi Pembayaran Online
                        <small>Report Wajib Pajak Online</small>
                    </h1>
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="hidden_driver_id" value="<%=oidDriver%>">
                        <input type="hidden" name="menu" value="60">
                        <input type="hidden" name="tree" value="8">
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                   <div class="box-header">
                                       &nbsp;
                                   </div>    
                                   <div class="box-header">
                                       <div class="col-xs-2"><label>Show From Date</label>
                                           <input name="tanggalStart" id="datepicker" type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=startDate%>">
                                       </div>
                                       <div class="col-xs-2"><label>To Date Date</label>
                                           <div class="input-group">  
                                           <input name="tanggalEnd" id="datepickerdua" type="text" class="form-control" placeholder="yyyy-MM-dd" value="<%=startEnd%>">
                                            <span class="input-group-btn">
                                                <button type='button' name='seach' id='search-btn' class="btn btn-success pull-right"  onclick="javascript:cmdSearch()"><i class="fa fa-search"></i></button>
                                            </span>
                                            </div>
                                       </div>
                                       <div class="col-xs-2"><label>&nbsp;</label>  
                                       </div>
                                       <% if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_REPORT,AppObjInfo.G2_REPORT_PHR, AppObjInfo.OBJ_REPORT_DETAIL_PHR, AppObjInfo.COMMAND_PRINT)){ %>
                                           <div class="col-xs-2"><label>&nbsp;</label>  
                                               <button  onclick="javascript:cmdPrintExcel()" type="button" class="btn btn-danger pull-right">Export Excel</button>
                                           </div>
                                           <div class="col-xs-2"><label>&nbsp;</label>  
                                               <button  onclick="javascript:cmdPrintPdf()" type="button" class="btn btn-info pull-right">Export Pdf</button>
                                           </div>
                                       <%}%>     
                                    </div>
                                    <%//=drawListDataPaymentPhrBpd(iCommand, listReportDetail, oidDriver)%>
                                    <%=drawListDataPaymentPhrDispenda(iCommand, listReportWp, oidDriver,approot)%>
                                </div><!-- /.box -->
                            </div>
                        </div>     
                    </form>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <div id="reportSPT"  class="modal fade modal-lg-report modals" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title"></h4>
                    </div>

                    <div class="modal-body" overflow-y: hidden;">
                         <iframe style="zoom:0.60" width="99.6%" height="520" frameborder="0"></iframe>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery 2.0.2 -->
        <script src="../../styles/bootstrap3.1/js/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="../../styles/bootstrap3.1/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- DATA TABES SCRIPT -->
        <script src="../../styles/bootstrap3.1/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="../../styles/bootstrap3.1/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="../../styles/bootstrap3.1/js/AdminLTE/app.js" type="text/javascript"></script>

        <!-- page script -->
        <script src="../../styles/datepicker/js/jquery.ui.core.js"></script>
        <script src="../../styles/datepicker/js/jquery.ui.widget.js"></script>
        <script src="../../styles/datepicker/js/jquery.ui.datepicker.js"></script>

        <script type="text/javascript">
            $(function() {
                //$("#example1").dataTable();
                $('#example2').dataTable({
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": true,
                    "bAutoWidth": false
                });
            });
            
            function printSptLampiran(){
                $('.printLaporan').click(function(){
                    var masaPajak = $(this).data("masaPajak").toString();   
                    var npwpd = $(this).data("npwpd");
                    var tahunPajak = $(this).data("tahunPajak").toString();
                    var lampiran = $(this).data("lampiran").toString();

                    var parameter = npwpd + ' ' + masaPajak + ' ' + tahunPajak + ' ' + lampiran;
                        $('.modal-lg-report').css('max-width','1366px');
                        $('.modal-lg-report').css('width','98%');
                        $('.modals').css('left','1%');
                        $('#reportSPT').css('top','3%');
                        $('iframe').attr({"src":'<%= approot %>/wpupload/wp-rpt-spt1-back.jsp?param='+parameter+''});

                    $('#reportSPT').modal({show:true});

                });
            }
            
            printSptLampiran();
        </script>
        <script>
            $(function() {
                    $( "#datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
            });
	</script>
        <script>
            $(function() {
                    $( "#datepickerdua" ).datepicker({ dateFormat: "yy-mm-dd" });
            });
	</script>
         <script>
            $(function() {
                    $( "#datepickertiga" ).datepicker({ dateFormat: "yy-mm-dd" });
            });
	</script>
    </body>
</html>
