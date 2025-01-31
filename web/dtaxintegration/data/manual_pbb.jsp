<%-- 
    Document   : payment_pbb
    Created on : May 19, 2015, 7:05:00 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dtaxintegration.session.SessPbbIprotax"%>
<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="com.dimata.webclient.EchoTagihanDeleteByRecordId"%>
<%@page import="com.dimata.dtaxintegration.entity.tagihan.TagihanDelete"%>
<%@page import="com.dimata.webclient.EchoTagihanDeleteById"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Pbb"%>
<%@page import="com.dimata.dtaxintegration.entity.tagihan.Tagihan"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.InqueryProses"%>
<%@page import="com.dimata.webclient.Inquery"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PaymentPbb"%>
<%@page import="com.dimata.dtaxintegration.entity.payment.PstPaymentPbb"%>
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
        ctrlist.addHeader("ID BANK", "200px");//2
        ctrlist.addHeader("NOP", "200px");//3
        ctrlist.addHeader("NAMA", "200px");//3
        ctrlist.addHeader("INSTANSI", "200px");//4
        ctrlist.addHeader("ALAMAT", "200px");//5
        ctrlist.addHeader("TAHUN", "200px");//7
        ctrlist.addHeader("DENDA", "200px");//9
        ctrlist.addHeader("JUMLAH_BAYAR", "200px");//10
        ctrlist.addHeader("STATUS", "200px");//10
        ctrlist.addHeader("ACTION", "200px");
        
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
            Tagihan tagihan = (Tagihan) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+tagihan.getId());//2
            rowx.add(""+tagihan.getNoId());//3
            rowx.add(""+tagihan.getNama());//3
            rowx.add(""+tagihan.getInstansiId());//4
            rowx.add(""+tagihan.getAlamat());//5
            rowx.add(""+tagihan.getTahun());//7
            rowx.add(""+tagihan.getDenda());//9
            rowx.add(""+tagihan.getTagihan());//10
            rowx.add(""+tagihan.getStsBayar());
            //rowx.add("DELETE");
            //rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdPostingDelete('" +tagihan.getId()+"')\"></i> DELETE </button></center>");
            rowx.add("<center><input type=\"checkbox\" name=\"delete_status_"+i+"\" value=\"'"+tagihan.getId()+"'\" class=\"form-control\" size=\"10\"></center>");
            
            total=total+Double.parseDouble(tagihan.getTagihan());
            lstData.add(rowx);
        }
        
        rowx = new Vector(1, 1);
        
        rowx.add("");//1
        rowx.add("");//2
        rowx.add("");//3
        rowx.add("");//3
        rowx.add("");//4
        rowx.add("");//5
        rowx.add("");//7
        rowx.add("<b>Total</b>");//9
        rowx.add(""+FRMHandler.userFormatStringDecimal(total));//10
        rowx.add("");
        
        lstData.add(rowx);
        
        
        return ctrlist.drawBootstrapStrip();
    }

    public String drawListDataPaymentPhrDispenda(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("NOP", "200px");//2
        ctrlist.addHeader("NAMA", "200px");//3
        ctrlist.addHeader("JUMLAH_TAGIHAN", "200px");//7
        ctrlist.addHeader("DENDA", "200px");//7
        ctrlist.addHeader("ALAMAT_WP", "200px");//10
        ctrlist.addHeader("LETAK", "200px");//10
        ctrlist.addHeader("TAHUN", "200px");//10
        ctrlist.addHeader("JATUH_TEMPO", "200px");//10
        ctrlist.addHeader("ACTION", "200px");//10
        //ctrlist.addHeader("LUAS_BUMI_SPPT", "200px");//10
        //ctrlist.addHeader("LUAS_BNG_SPPT", "200px");//10
        //ctrlist.addHeader("NJOP_BUMI_SPPT", "200px");//10
        //ctrlist.addHeader("NJOP_BNG_SPPT", "200px");//10
        //ctrlist.addHeader("NJOPTKP_SPPT", "200px");//10
        
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
            Pbb pbb = (Pbb) objectClass.get(i);
            count=count+1;
            rowx = new Vector(1, 1);
            
            rowx.add(""+count);//1
            rowx.add(""+pbb.getId());//2
            rowx.add(""+pbb.getNama());//3
            if(AppSetting.TYPE_APP_BACKOFFICE == AppSetting.APP_IPROTAX){
                /*get previous payment on this SPPT, count faktor pengurang*/
                double totPambayaran = SessPbbIprotax.PerhitunganPbbYangHarusDibayar(pbb.getId(), pbb.getTahun(), pbb.getJumlahTagihan());
                /*count denda adm sppt*/
                double denda=SessPbbIprotax.PerhitunganDenda(pbb.getId(),  pbb.getTahun(), pbb.getTglJatuhTempo(), pbb.getJumlahTagihan(),totPambayaran);
                double ygHarusDibayar=totPambayaran+denda;
                pbb.setJumlahTagihan(""+ygHarusDibayar);
                rowx.add(""+ygHarusDibayar);//4
                rowx.add(""+denda);//5
            }else{
                rowx.add(""+pbb.getJumlahTagihan());//4
                rowx.add(""+pbb.getDenda());//5
            }
            rowx.add(""+pbb.getAlamat());//6
            rowx.add(""+pbb.getLetakObjectPajak());//7
            rowx.add(""+pbb.getTahun());//8

            if(AppSetting.TYPE_APP_BACKOFFICE == AppSetting.APP_IPROTAX){
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                try {
                    date = formatter.parse(pbb.getTglJatuhTempo());
                } catch (Exception e) {
                   e.printStackTrace();
                }
                rowx.add(""+pbb.getTglJatuhTempo());//9
            }else{
                rowx.add(""+pbb.getTglJatuhTempo());//9
            }
            
            //rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdPosting('" +pbb.getId()+"','"+pbb.getTahun()+"')\"></i> UPLOAD </button></center>");
            rowx.add("<center><input type=\"checkbox\" name=\"status_"+i+"\" value=\"'"+pbb.getId()+"';'"+pbb.getTahun()+"'\" class=\"form-control\" size=\"10\"></center>");
            
            //rowx.add(""+pbb.getLuasBumi());//10
            //rowx.add(""+pbb.getLuasBangunan());//11
            //rowx.add(""+pbb.getnJOPBumi());//12
            //rowx.add(""+pbb.getnJOPBangunan());//13
            //rowx.add(""+pbb.getnJOPTKP());//14
            
            total=total+Double.parseDouble(pbb.getJumlahTagihan());
            
            lstData.add(rowx);
        }
        rowx = new Vector(1, 1);
        
        rowx.add("");//1
        rowx.add("");//2
        rowx.add("<b>Total</b>");//4
        rowx.add(""+FRMHandler.userFormatStringDecimal(total));//6
        rowx.add("");
        rowx.add("");
        rowx.add("");
        rowx.add("");
        rowx.add("");
        //rowx.add("");
        //rowx.add("");
        //rowx.add("");
        //rowx.add("");
        //rowx.add("");
        
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
    String sNoId = FRMQueryString.requestString(request, "sNoId");
    String idbank = FRMQueryString.requestString(request, "idbank");
    
    String idtagihan = FRMQueryString.requestString(request, "idtagihan");
    String tahun = FRMQueryString.requestString(request, "tahun");
    
    String whereClause = "";
    Vector listReportDetail = new Vector(1, 1);
    
    /*Delete*/
    if(iCommand==Command.DELETE){
        EchoTagihanDeleteByRecordId echoTagihanDeleteByRecordId = new EchoTagihanDeleteByRecordId();
        //EchoTagihanDeleteById echoTagihanDeleteByRecordId = new EchoTagihanDeleteById();
        TagihanDelete tagihanDelete = new TagihanDelete();
        String[] splits = idtagihan.split(",");
        for(String asset: splits){
            if(asset!=""){
                if(!asset.equals("")){
                    tagihanDelete.setsUser(AppSetting.USERNAME_PBB);
                    tagihanDelete.setsPassword(AppSetting.PWD_PBB);
                    tagihanDelete.setsInstansi(AppSetting.INSTANSI_PBB);
                    tagihanDelete.setsNoId(sNoId);
                    tagihanDelete.setsRecordId(asset);
                    echoTagihanDeleteByRecordId.action(tagihanDelete);
                    idtagihan="";
                }
            }
        }
    }
    
    if(iCommand==Command.POSTING){
        DTaxIntegrationMonitor dTax = new DTaxIntegrationMonitor();
        if(!idtagihan.equals("")){
            dTax.prosesBrr(idtagihan);
        }
        idtagihan="";
        /*DTaxIntegrationMonitor dTax = new DTaxIntegrationMonitor();
        String whereSent= " WHERE NOP='"+idtagihan+"' AND TAHUN='"+tahun+"'";
        dTax.sentPBB(whereSent);
        idtagihan="";
        tahun="";*/
    }
    
    /*webservice bpd*/
    Inquery inquery = new Inquery();
    InqueryProses inqueryProses = new InqueryProses();
    inqueryProses.setsUser(AppSetting.USERNAME_PBB);
    inqueryProses.setsPassword(AppSetting.PWD_PBB);
    inqueryProses.setsInstansi(AppSetting.INSTANSI_PBB);
    inqueryProses.setsNoId(sNoId);
    listReportDetail = inquery.InqueryPBB(inqueryProses);
    
    /*data table payment*/
    Vector listTagihanPbb = new Vector();
    whereClause = " WHERE NOP='"+sNoId+"' "; ;//PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]+" = TO_DATE ('"+oDate+"','YYYY-MM-DD HH24:MI:SS')";
    listTagihanPbb = SessSimpatda.getListPBB(whereClause);
    
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
                document.driver.action="payment_pbb.jsp";
                document.driver.submit();
            }
            
            function cmdSearch(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="manual_pbb.jsp";
                document.driver.submit();
            }
            function cmdPostingDelete(idBank){
                document.driver.idbank.value=idBank;
                document.driver.command.value="<%=Command.DELETE%>";
                document.driver.action="manual_pbb.jsp";
                document.driver.submit();
            }
            
            function cmdPosting(id, tahun){
                document.driver.idtagihan.value=id;
                document.driver.tahun.value=tahun;
                document.driver.command.value="<%=Command.POSTING%>";
                document.driver.action="manual_pbb.jsp";
                document.driver.submit();
            }
            
            function cmdAction(){
                if (confirm('Yakin Proses ? ')) {  
                var val = '';
                <%
                for (int a=0; a<listTagihanPbb.size();a++){
                %>
                    if(eval("driver.status_"+<%=a%>+".checked")==true){
                        val = val + eval("driver.status_"+<%=a%>+".value") + ',';
                    }
                <%
                }
                %>
                document.driver.idtagihan.value=val;
                document.driver.command.value="<%=Command.POSTING%>";
                document.driver.action="manual_pbb.jsp";
                document.driver.submit(); 
                lockScreen('Proses Posting tagihan PBB ke Bank BPD, Silahkan menunggu...');
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
                document.driver.action="manual_pbb.jsp";
                document.driver.submit(); 
                lockScreen('Proses Delete Tagihan PBB di BANK BPD, Silahkan menunggu...');
            }
            }
            
            function lockScreen(str)
                {
                    var lock = document.getElementById('theLockPane');
                    if (lock)
                        lock.className = 'LockOn';

                    lock.innerHTML = str;
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
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="hidden_driver_id" value="<%=oidDriver%>">
                        <input type="hidden" name="idbank" value="<%=idbank%>">
                        <input type="hidden" name="idtagihan" value="<%=idtagihan%>">
                        <input type="hidden" name="tahun" value="<%=tahun%>">
                        <input type="hidden" name="menu" value="5">
                        <input type="hidden" name="tree" value="2">
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                   <div class="box-header">
                                       &nbsp;
                                   </div>    
                                   <div class="box-header">
                                       <div class="col-xs-12"><label>DATA BANK</label></div>  
                                       <div class="col-xs-4"><label> NOP </label>  
                                            <div class="input-group">   
                                                <input name="sNoId" type="text" class="form-control" placeholder="ID NPWPD" value="<%=sNoId%>" size="20">
                                                <span class="input-group-btn">
                                                    <button type='button' name='seach' id='search-btn' class="btn btn-success pull-right" onclick="javascript:cmdSearch()"><i class="fa fa-search"></i></button>
                                                </span>
                                            </div>
                                       </div>
                                       <% if(true){ %>
                                           <div class="col-xs-8">
                                               <button  onclick="javascript:cmdActionDelete()" type="button" class="btn btn-danger pull-right">DELETE</button>
                                           </div>
                                       <%}%>         
                                    </div>
                                    <%=drawListDataPaymentPhrBpd(iCommand, listReportDetail, oidDriver)%>
                                </div>
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
                                        <% if(true){ %>
                                            <div class="col-xs-12">
                                                 <button  onclick="javascript:cmdAction()" type="button" class="btn btn-primary pull-right">UPLOAD</button>
                                           </div>
                                       <%}%>
                                   </div>  
                                   <%=drawListDataPaymentPhrDispenda(iCommand, listTagihanPbb, oidDriver)%>
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
