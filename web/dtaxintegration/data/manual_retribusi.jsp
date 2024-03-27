<%-- 
    Document   : manual_retribusi
    Created on : May 24, 2015, 6:09:01 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="com.dimata.dtaxintegration.entity.loghistory.LogHistoryTransaksi"%>
<%@page import="com.dimata.dtaxintegration.entity.loghistory.PstLogHistoryTransaksi"%>
<%@page import="com.dimata.dtaxintegration.entity.tagihan.TagihanDelete"%>
<%@page import="com.dimata.webclient.EchoTagihanDeleteByRecordId"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Retribusi"%>
<%@page import="com.dimata.dtaxintegration.entity.tagihan.Tagihan"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.InqueryProses"%>
<%@page import="com.dimata.webclient.Inquery"%>
<%-- 
    Document   : payment_retribusi
    Created on : May 19, 2015, 7:05:52 PM
    Author     : dimata005
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dimata.dtaxintegration.session.DTaxIntegrationMonitor"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PaymentRetribusi"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PstPaymentRetribusi"%>
<%-- 
    Document   : payment_bphtb
    Created on : May 19, 2015, 7:05:38 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.dtaxintegration.entity.payment.PstPaymentBphtb"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PaymentBphtb"%>
<%-- 
    Document   : payment_phr
    Created on : May 19, 2015, 7:00:57 PM
    Author     : dimata005
--%>

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

    public String drawListDataPaymentBpd(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("NO REKENING", "200px");//3
        ctrlist.addHeader("NAMA", "200px");//3
        ctrlist.addHeader("TANGGAL RETRIBUSI", "200px");//3
        ctrlist.addHeader("ACTION", "200px");//3
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
            LogHistoryTransaksi logHistoryTransaksi = (LogHistoryTransaksi) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+logHistoryTransaksi.getNpwd());//3
            rowx.add(""+logHistoryTransaksi.getNama());//3
            rowx.add(""+logHistoryTransaksi.getTanggalRetribusi());//8

            //rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdPostingDelete('" +tagihan.getId()+"')\"></i> DELETE </button></center>");
            rowx.add("<center></center>");
            
            lstData.add(rowx);
        }
        
        rowx = new Vector(1, 1);
        
        rowx.add("");//1
        rowx.add("");//3
        rowx.add("");//8
        rowx.add("");//10
        rowx.add("");//10
        
        lstData.add(rowx);
        
        return ctrlist.drawBootstrapStrip();
    }

    public String drawListDataPaymentDispenda(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("NO REKENING", "200px");//2
        ctrlist.addHeader("NAMA", "200px");//10
        ctrlist.addHeader("SETORAN", "200px");//10
        ctrlist.addHeader("TANGGAL PENERIMAAN", "200px");//10
        ctrlist.addHeader("ACTION", "200px");//10
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
            Retribusi retribusi = (Retribusi) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+retribusi.getNoRekening());//3
            rowx.add(""+retribusi.getNama());//4
            rowx.add(""+retribusi.getJumlahTagihan());//4
            rowx.add(""+retribusi.getTanggalPenerimaan());//4
            String whereClauseHistory = " ID='"+retribusi.getNoRekening()+"' AND  INSTANSI='"+AppSetting.INSTANSI_RETRIBUSI+"' AND TAHUN='"+retribusi.getTahun()+"' AND BULAN='"+retribusi.getBulan()+"' "+//"AND TANGGAL='"+dateLog+"'";
                  " AND "+PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TGL_RETRIBUSI]
                  +" BETWEEN TO_DATE ('"+retribusi.getTanggal()+"','YYYY-MM-DD HH24:MI:SS')"
                  +" AND TO_DATE ('"+retribusi.getTanggal()+"','YYYY-MM-DD HH24:MI:SS')";
            Vector listReportDetail = PstLogHistoryTransaksi.list(0, 0,whereClauseHistory, "");
            if(listReportDetail.size()>0){
                rowx.add("<center><input type=\"hidden\" id=\"status_"+i+"\" name=\"status_"+i+"\" value=\"'"+retribusi.getNoRekening()+"';'"+retribusi.getTanggalPenerimaan()+"'\" class=\"form-control\" size=\"10\">Data Sudah di Bank</center>");
            }else{
                rowx.add("<center><input type=\"checkbox\" id=\"status_"+i+"\" name=\"status_"+i+"\" value=\"'"+retribusi.getNoRekening()+"';'"+retribusi.getTanggalPenerimaan()+"'\" class=\"form-control\" size=\"10\"></center>");
            }
            
            
            lstData.add(rowx);
        }
        
        rowx = new Vector(1, 1);

        rowx.add("");//1
        rowx.add("");//2
        rowx.add("");//3
        rowx.add("");//4
        rowx.add("");//4
        rowx.add("");//4
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
    
    String sNoId = FRMQueryString.requestString(request, "sNoId");
    String startDate = FRMQueryString.requestString(request, "startDate");
    String oDate="";
    String idtagihan = FRMQueryString.requestString(request, "idtagihan");
    String tahun = FRMQueryString.requestString(request, "tahun");
    
    /*variable declaration*/
    String whereClause = "";
    ControlLine ctrLine = new ControlLine();
    Vector listReportDetail = new Vector(1, 1);
    
    /*Delete*/
    if(iCommand==Command.DELETE){
        EchoTagihanDeleteByRecordId echoTagihanDeleteByRecordId = new EchoTagihanDeleteByRecordId();
        TagihanDelete tagihanDelete = new TagihanDelete();
        String[] splits = idtagihan.split(",");
        for(String asset: splits){
            if(asset!=""){
                if(!asset.equals("")){
                    tagihanDelete.setsUser(AppSetting.USERNAME_RETRIBUSI);
                    tagihanDelete.setsPassword(AppSetting.PWD_RETRIBUSI);
                    tagihanDelete.setsInstansi(AppSetting.INSTANSI_RETRIBUSI);
                    tagihanDelete.setsNoId(sNoId);
                    tagihanDelete.setsRecordId(asset);
                    echoTagihanDeleteByRecordId.action(tagihanDelete);
                    idtagihan="";
                }
            }
        }
    }
    
     DTaxIntegrationMonitor dTax = new DTaxIntegrationMonitor();
    if(iCommand==Command.POSTING){
        if(!idtagihan.equals("")){
            dTax.prosesRetribusi(idtagihan);
        }
        idtagihan="";
    }
    
    Date newDay=new Date();
    Date transaksiDate =  new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    if(startDate.equals("")){
        startDate=Formater.formatDate(newDay,"yyyy-MM-dd");
        transaksiDate = formatter.parse(startDate);
        oDate = Formater.formatDate(transaksiDate,"yyyy-MM-dd HH:mm:ss");
    }else{
        String dateStringTransaksi = startDate;
        try {
                transaksiDate = formatter.parse(dateStringTransaksi);
                oDate = Formater.formatDate(transaksiDate,"yyyy-MM-dd HH:mm:ss");
                
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    /*webservice bpd*/
    /*Inquery inquery = new Inquery();
    InqueryProses inqueryProses = new InqueryProses();
    inqueryProses.setsUser("RETRIBUSI_GIANYAR");
    inqueryProses.setsPassword("123456");
    inqueryProses.setsInstansi("RETRIBUSI_GIANYAR");
    inqueryProses.setsNoId(sNoId);
    listReportDetail = inquery.InqueryRetribusi(inqueryProses);*/
    int tahunLog =transaksiDate.getYear()+1900;
    int mLog =transaksiDate.getMonth()+1;
    String monthLog="";
    if(mLog<=9){
        monthLog="0"+mLog;
    }else{
        monthLog=""+mLog;
    }
    String dateLog="";
    if(transaksiDate.getDate()<=9){
        dateLog="0"+transaksiDate.getDate();
    }else{
        dateLog=""+transaksiDate.getDate();
    }
    String whereClauseHistory = " INSTANSI='"+AppSetting.INSTANSI_RETRIBUSI+"' AND TAHUN='"+tahunLog+"' AND BULAN='"+monthLog+"' "+//"AND TANGGAL='"+dateLog+"'";
                  " AND "+PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TGL_RETRIBUSI]
                  +" BETWEEN TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS')"
                  +" AND TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS') ORDER BY ID ";
    
    String whereClauseViewHistory = " WHERE INSTANSI='"+AppSetting.INSTANSI_RETRIBUSI+"' AND "+PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_TGL_RETRIBUSI]
                  +" BETWEEN TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS')"
                  +" AND TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS')";
    
    listReportDetail = PstLogHistoryTransaksi.list(0, 0,whereClauseHistory, "");
    
    /*data table payment*/
    Vector listTagihanRetribusi = new Vector();
    //whereClause = " WHERE NO_REKENING='"+sNoId+"' "; //;//PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]+" = TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS')";
    listTagihanRetribusi = SessSimpatda.getListRetribusi(whereClauseViewHistory);
    
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
                document.driver.command.value="<%=Command.POSTING%>";
                document.driver.action="payment_retribusi.jsp";
                document.driver.submit();
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="manual_retribusi.jsp";
                document.driver.submit();
            }
             function cmdPostingDelete(idBank){
                document.driver.idbank.value=idBank;
                document.driver.command.value="<%=Command.DELETE%>";
                document.driver.action="manual_retribusi.jsp";
                document.driver.submit();
            }
            
            function cmdPosting(id, tahun){
                document.driver.idtagihan.value=id;
                document.driver.tahun.value=tahun;
                document.driver.command.value="<%=Command.POSTING%>";
                document.driver.action="manual_retribusi.jsp";
                document.driver.submit();
            }
            
            function cmdAction(){
                if (confirm('Yakin Proses ? ')) {  
                var val = '';
                <%
                for (int a=0; a<listTagihanRetribusi.size();a++){
                %>
                    if(eval("driver.status_"+<%=a%>+".checked")==true){
                        val = val + eval("driver.status_"+<%=a%>+".value") + ',';
                    }
                <%
                }
                %>
                document.driver.idtagihan.value=val;
                document.driver.command.value="<%=Command.POSTING%>";
                document.driver.action="manual_retribusi.jsp";
                document.driver.submit(); 
                lockScreen('Proses Posting tagihan RETRIBUSI ke Bank BPD, Silahkan menunggu...');
            }
            }
            
            function cmdActionDelete(){
            if (confirm('Yakin Proses ? ')) {  
                var val = '';
                <%
                for (int a=0; a<listReportDetail.size();a++){
                %>
                    if(eval("driver.delete_status_"+<%=a%>+".checked")==true){
                        val = val + eval("driver.delete_status_"+<%=a%>+".value") + ',';
                    }
                <%
                }
                %>
                document.driver.idtagihan.value=val;
                document.driver.command.value="<%=Command.DELETE%>";
                document.driver.action="manual_retribusi.jsp";
                document.driver.submit(); 
                lockScreen('Proses Delete Tagihan RETRIBUSI di BANK BPD, Silahkan menunggu...');
            }
            }
            
            function lockScreen(str)
                {
                    var lock = document.getElementById('theLockPane');
                    if (lock)
                        lock.className = 'LockOn';

                    lock.innerHTML = str;
                }
                
             function check()
                {
                    <%
                    for (int a=0; a<listTagihanRetribusi.size();a++){
                    %>
                    document.getElementById("status_<%=a%>").checked = true;
                    <%
                    }
                    %>
                }
            function uncheck()
                {
                    <%
                    for (int a=0; a<listTagihanRetribusi.size();a++){
                    %>
                    document.getElementById("status_<%=a%>").checked = false;
                    <%
                    }
                    %>
                }    
        </script>
        <style type="text/css">

            .LockOff {
                display: none;
                visibility: hidden;
            }

            .LockOn {
                display: block;
                visibility: visible;
                position: absolute;
                z-index: 999;
                top: 0px;
                left: 0px;
                width: 105%;
                height: 105%;
                background-color: #ccc;
                text-align: center;
                padding-top: 20%;
                filter: alpha(opacity=75);
                opacity: 0.75;
                font-size: 250%;
            }
        </style>
    </head>
    <body class="skin-blue">
        <div id="theLockPane" class="LockOff"></div>
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
                    </h1>
                    <!--<ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Tables</a></li>
                        <li class="active">Data tables</li>
                    </ol> -->
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="hidden_driver_id" value="<%=oidDriver%>">
                        <input type="hidden" name="idtagihan" value="<%=idtagihan%>">
                        <input type="hidden" name="tahun" value="<%=tahun%>">
                        <input type="hidden" name="menu" value="11">
                        <input type="hidden" name="tree" value="4">
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                   <div class="box-header">
                                       <label>History Upload Data Tagihan</label> 
                                   </div>    
                                   <div class="box-header">
                                       <div class="col-xs-4"><label>Tanggal</label>  
                                            <div class="input-group">   
                                                <input name="startDate" id="datepicker" type="text" class="form-control" placeholder="No Rekening" value="<%=startDate%>" size="20">
                                                <span class="input-group-btn">
                                                    <button type='button' name='seach' id='search-btn' class="btn btn-success pull-right" onclick="javascript:cmdSearch()"><i class="fa fa-search"></i></button>
                                                </span>
                                            </div>
                                       </div>
                                       <div class="col-xs-8">
                                           <%--<button  onclick="javascript:cmdActionDelete()" type="button" class="btn btn-danger pull-right">DELETE</button>--%>
                                       </div>
                                    </div>
                                    Total : <%=listReportDetail.size()%>   
                                    <%=drawListDataPaymentBpd(iCommand, listReportDetail, oidDriver)%>
                                </div><!-- /.box -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                    <div class="box-header">
                                       &nbsp;
                                   </div>
                                   <div class="box-header">
                                       <div class="col-xs-12"><label>DATA DISPENDA</label></div>
                                       <% if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_RETRIBUSI, AppObjInfo.OBJ_UPLOAD_MANUAL_RETRIBUSI, AppObjInfo.COMMAND_UPLOAD)){ %>
                                           <div class="col-xs-12">
                                                 <button  onclick="javascript:cmdAction()" type="button" class="btn btn-primary pull-right">UPLOAD</button>
                                                 
                                                 <button onclick="javascript:check()" type="button" >Pilih semua</button>
                                                 <button onclick="javascript:uncheck()" type="button" >Tidak di pilih semua</button>
                                           </div>
                                       <%}%>
                                   </div> 
                                   Total : <%=listTagihanRetribusi.size()%>  
                                   <%=drawListDataPaymentDispenda(iCommand, listTagihanRetribusi, oidDriver)%>
                                </div><!-- /.box -->
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                   <div class="box-header">
                                       &nbsp;
                                   </div>
                                   <div class="box-header">
                                        <div class="col-xs-12"><label>Log Error Data</label></div>  
                                   </div>  
                                   <%=dTax.getLogErorPosting()%><br>
                                </div><!-- /.box -->
                            </div>
                        </div>          
                    </form>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


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

