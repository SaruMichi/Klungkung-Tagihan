<%-- 
    Document   : pbb
    Created on : May 22, 2015, 6:09:28 PM
    Author     : dimata005
--%>

<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="com.dimata.dtaxintegration.session.DTaxManagerPbb"%>
<%@page import="com.dimata.dtaxintegration.entity.tagihan.FileSent"%>
<%@page import="com.dimata.webclient.UploadFile"%>
<%@page import="com.dimata.dtaxintegration.entity.inquery.Pbb"%>
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

    public String drawList(int iCommand, Vector objectClass, long gradeId) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");

        ctrlist.addHeader("No", "200px");//1
        ctrlist.addHeader("NOP", "200px");//2
        ctrlist.addHeader("NAMA", "200px");//3
        ctrlist.addHeader("JUMLAH_TAGIHAN", "200px");//4
        ctrlist.addHeader("DENDA", "200px");//14
        ctrlist.addHeader("ALAMAT_WP", "200px");//5
        ctrlist.addHeader("LETAK", "200px");//6
        ctrlist.addHeader("TAHUN", "200px");//7
        ctrlist.addHeader("JATUH_TEMPO", "200px");//8
        ctrlist.addHeader("LUAS_BUMI_SPPT", "200px");//9
        ctrlist.addHeader("LUAS_BNG_SPPT", "200px");//10
        ctrlist.addHeader("NJOP_BUMI_SPPT", "200px");//11
        ctrlist.addHeader("NJOP_BNG_SPPT", "200px");//12
        ctrlist.addHeader("NJOPTKP_SPPT", "200px");//13

        if (iCommand != Command.EDIT) {
            ctrlist.setLinkRow(0);
        }

        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count = 0;
        Vector rowx = new Vector(1, 1);
        double total = 0.0;

        for (int i = 0; i < objectClass.size(); i++) {

            Pbb pbb = (Pbb) objectClass.get(i);

            count = count + 1;
            rowx = new Vector(1, 1);

            rowx.add("" + count);//1
            rowx.add("" + pbb.getId());//2
            rowx.add("" + pbb.getNama());//3
            rowx.add("" + pbb.getJumlahTagihan());//4
            rowx.add("" + pbb.getDenda());//10
            rowx.add("" + pbb.getAlamat());//5
            rowx.add("" + pbb.getLetakObjectPajak());//6
            rowx.add("" + pbb.getTahun());//7
            rowx.add("" + pbb.getTglJatuhTempo());//8
            rowx.add("" + pbb.getLuasBumi());//9
            rowx.add("" + pbb.getLuasBangunan());//10
            rowx.add("" + pbb.getnJOPBumi());//10
            rowx.add("" + pbb.getnJOPBangunan());//10
            rowx.add("" + pbb.getnJOPTKP());//10

            lstData.add(rowx);
        }
        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidDriver = FRMQueryString.requestLong(request, "hidden_driver_id");
    
    String respon = "";
    
    String tahunStart = FRMQueryString.requestString(request, "yearStart");
    String tahunEnd = FRMQueryString.requestString(request, "yearEnd");
     
    DTaxManagerPbb dTaxManagerPbb = new DTaxManagerPbb();

    switch (iCommand) {

        case Command.START:

            FileSent fileSent = new FileSent();
            
            fileSent.setsUser(AppSetting.USERNAME_PBB);//1
            fileSent.setsPassword(AppSetting.PWD_PBB);//2
            fileSent.setsInstansi(AppSetting.INSTANSI_PBB);//6
            fileSent.setLocation(AppSetting.PBB_LOCATION_FILE);
            fileSent.setFileName(AppSetting.PBB_FILE_ZIP_NAME);
            
            fileSent.setTahunStart(tahunStart);
            fileSent.setTahunEnd(tahunEnd);
            dTaxManagerPbb.startTransfer(fileSent);

            break;

        case Command.STOP:

            dTaxManagerPbb.stopMonitor();

            break;

    }
    boolean running = dTaxManagerPbb.getStatus();
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
            
            function cmdStart(){
                document.driver.command.value="<%=Command.START%>";
                document.driver.hidden_driver_id.value="0";
                document.driver.prev_command.value="<%=prevCommand%>";
                document.driver.action="pbb.jsp";
                document.driver.submit();
            }
            
            function cmdStop(){

                document.driver.command.value="<%=Command.STOP%>";
                document.driver.start.value="0";
                document.driver.action="pbb.jsp";
                document.driver.submit();
	 
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
                        <input type="hidden" name="menu" value="4">
                        <input type="hidden" name="tree" value="2">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="box-header">
                                        &nbsp;
                                    </div> 
                                    <div class="box-header">
                                        <div class="col-xs-2"><label>Show From Year</label>
                                            <input name="yearStart" type="text" class="form-control" value="">
                                        </div>
                                        <div class="col-xs-2"><label>To Year</label>
                                            <input name="yearEnd" type="text" class="form-control" value="">
                                        </div>
                                       <div class="col-xs-2"><label>&nbsp;</label></div>
                                       <div class="col-xs-2"><label>&nbsp;</label></div>
                                       <div class="col-xs-4"><label>&nbsp;</label></div>
                                    </div> 
                                    <div class="box-header">
                                        <div class="col-xs-12"><label>Monitoring PBB Service</label>
                                            <table width="85%" border="0" cellspacing="1" cellpadding="1" align="center">
                                                <tr> 
                                                    <td colspan="3" class="title"><b>Jumlah Yang Sudah ke Temporary : <%=dTaxManagerPbb.getCountQuery()%> / <%=dTaxManagerPbb.getCountTotal()%></b></td>
                                                </tr>
                                                <tr> 
                                                    <td colspan="3" class="title"><b>Jumlah Yang Sudah ke File Data : <%=dTaxManagerPbb.getCount()%> / <%=dTaxManagerPbb.getCountTotal()%></b></td>
                                                </tr>
                                                <tr> 
                                                    <td colspan="3" class="title"><b>Status <%=dTaxManagerPbb.getStart()%> / <%=dTaxManagerPbb.getEnd()%></b></td>
                                                </tr>
                                                
                                                <tr> 
                                                    <td colspan="3" class="title">Proses  : <%=dTaxManagerPbb.getProses()%></td>
                                                </tr>
                                                <tr> 
                                                    <td width="40%">&nbsp;</td>
                                                    <td width="4%">&nbsp;</td>
                                                    <td width="56%">&nbsp;</td>
                                                </tr>
                                                <tr> 
                                                    <td colspan="2">&nbsp;</td>
                                                    <td width="56%">&nbsp;</td>
                                                </tr>
                                                <tr> 
                                                    <td width="40%"> 
                                                        <div align="right"><b>STATUS&nbsp;&nbsp;</b></div>
                                                    </td>
                                                    <td colspan="2"> 

                                                        <% if (running) {%>

                                                        <font color="#009900">Running...</font> 

                                                        <%} else {%>

                                                        <font color="#FF0000">Stopped</font> 

                                                        <%}%>

                                                    </td>

                                                </tr>
                                                <% //if(userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_INTEGRASI,AppObjInfo.G2_PBB,AppObjInfo.OBJ_UPLOAD_FILE_PBB, AppObjInfo.COMMAND_UPLOAD)){ 
                                                    if(true){
                                                %>
                                                    <tr> 
                                                        <td width="40%">&nbsp;</td>
                                                        <td colspan="2">
                                                            <% String stopSts = "";
                                                                String startSts = "";
                                                                if (running) {
                                                                    startSts = "disabled=\"true\"";
                                                                    stopSts = "";
                                                                } else {
                                                                    startSts = "";
                                                                    stopSts = "disabled=\"true\"";
                                                                                }%>
                                                            <input type="button" name="Button" value="  Start  " onClick="javascript:cmdStart()" class="formElemen" <%=startSts%>>
                                                            <input type="button" name="Submit2" value="  Stop  " onClick="javascript:cmdStop()" class="formElemen" <%=stopSts%>>
                                                        </td>
                                                    </tr>
                                                <%}%>
                                                <tr> 
                                                    <td colspan="3"> 
                                                        <hr>
                                                    </td>
                                                </tr>
                                                <tr> 
                                                    <td colspan="3" align="right"> 
                                                        <table width="26%" border="0" cellspacing="2" cellpadding="3" align="left">
                                                            <tr> 
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
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
