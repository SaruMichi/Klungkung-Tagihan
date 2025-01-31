<%-- 
    Document   : pelaporan_per_segment
    Created on : Oct 2, 2016, 6:41:31 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.ajax.AjaxPelaporan"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.dslik.session.proses.ManagerTransferData"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include  file="../../main/checkuser_slik.jsp" %>
<%    
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_PER_SEGMENT_SUMMARY, AppObjInfo.OBJ_PER_SEGMENT_SUMMARY, AppObjInfo.COMMAND_VIEW);
    boolean privPrint = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_LAPORAN, AppObjInfo.G2_PER_SEGMENT_SUMMARY, AppObjInfo.OBJ_PER_SEGMENT_SUMMARY, AppObjInfo.COMMAND_PRINT);
    boolean privViewCheck = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_BULANAN, AppObjInfo.OBJ_BULANAN, AppObjInfo.COMMAND_VIEW);
    
    int iCommand = FRMQueryString.requestCommand(request);
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";//FRMQueryString.requestString(request, "tanggalEnd");
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    String oEnd = "";//FRMQueryString.requestString(request, "tanggalEnd");
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    
    Vector cabangKey = new Vector(1,1);
    Vector cabangVal = new Vector(1,1);
    if(privViewCheck) {
        cabangKey.add("");
        cabangVal.add("Semua Cabang/Capem");
    }
    String whereCabang="";
    if(kd_bank!=null){
        if(!kd_bank.equals("") && privViewCheck==false){
            whereCabang=PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+kd_bank+"'";
        }
    }
    Vector listCabang = PstCabangBank.list(0, 0, ""+whereCabang, ""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
    if(listCabang.size() > 0){
        for(int i = 0; i < listCabang.size(); i++){
            CabangBank cabangBank = (CabangBank) listCabang.get(i);
            cabangKey.add(""+cabangBank.getKodeCabang());
            cabangVal.add("("+cabangBank.getKodeCabang()+") "+cabangBank.getNamaCabang());
        }
    }
    
    Vector periodeKey = new Vector(1,1);
    Vector periodeVal = new Vector(1,1);
    Vector listPeriode = PstPeriode.list(0, 0, "", ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AKHIR]+" DESC");
    if(listPeriode.size() > 0){
        for(int i = 0; i < listPeriode.size(); i++){
            Periode entPeriode = (Periode) listPeriode.get(i);
            periodeKey.add(""+entPeriode.getOID());
            periodeVal.add(""+entPeriode.getNama());
        }
    }
    
    Vector segmentKey = new Vector(1,1);
    Vector segmentVal = new Vector(1,1);
    for(int i = 0; i < AjaxPelaporan.segmenNames.length; i++){
        segmentKey.add(""+i);
        segmentVal.add(""+AjaxPelaporan.segmenNames[i]);
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Laporan Per Segmen Summary</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../../template-component/css-component.jsp" %>
        <script language="JavaScript">

            function cmdStart() {
                document.driver.command.value = "<%=Command.START%>";
                document.driver.hidden_driver_id.value = "0";
                document.driver.action = "pbb.jsp";
                document.driver.submit();
            }

            function cmdStop() {

                document.driver.command.value = "<%=Command.STOP%>";
                document.driver.start.value = "0";
                document.driver.action = "pbb.jsp";
                document.driver.submit();

            }
            
            function cmdSearchExcel(){
                document.driver.command.value="<%=Command.SEARCH%>";
                document.driver.prev_command.value="<%=prevCommand%>";
                var cabang = document.getElementById("cabang").value;
                var periode = document.getElementById("periodesummary").value;
                document.driver.action="pelaporan_per_segment_excel.jsp?cabangpersegment="+cabang+"&periodepersegment="+periode+"";
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
    </head>
    <body class="<%= skin%>">
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <div class="wrapper">

            <%@include file="../../template-component/header-component.jsp" %>
            <%@include file="../../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Laporan 
                        <small>Per Segmen Summary</small>
                    </h1>
                </section>
                <!-- Main content -->
                <%
                    if(privView){
                %>
               <section class="content">
                    <form name="driver"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="prev_command" value="<%=prevCommand%>">
                        <input type="hidden" name="menu" value="16">
                        <input type="hidden" name="tree" value="5">
                        <div class="row">
                            <div class="col-xs-12">
                               <div class="box">
                                    <div class="box-body">
                                        <div class="row">
                                            <div class="col-xs-6"><label>Cabang/Capem</label>
                                                <%=ControlCombo.drawBootsratap("cabang", null, "", cabangKey, cabangVal, "required='required'", "form-control") %>
                                            </div>
                                            <div class="col-xs-6"><label>Periode</label>  
                                                <div class="input-group"> 
                                                    <%= ControlCombo.draw("periodesummary", null, "", periodeKey, periodeVal, "", "form-control") %>

                                                 <span class="input-group-btn">
                                                     <button type='button' name='seach' id='search-btn' class="btn btn-success pull-right" ><i class="fa fa-search"></i></button>
                                                 </span>
                                                 </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h3 class="box-title">Laporan Per Segmen Summary </h3>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12" id="reportsummary"></div>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top:10px;">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <%
                                                    if(privPrint){
                                                        %>
                                                        <button class="btn btn-primary" onclick="javascript:cmdSearchExcel()"><i class="fa fa-save"></i> Export Excel</button>
                                                            <button class="btn btn-danger btnback hidden" type="button"><i class="fa fa-save"></i> Export Pdf</button>
                                                        <%
                                                    }
                                                %>
                                                
                                              </div>
                                        </div>
                                    </div>        
                                </div><!-- /.box -->
                                
                            </div>
                        </div>         
                    </form>
                </section><!-- /.content -->
                <%
                    }
                %>
            </div><!-- /.content-wrapper -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
            <script type="text/javascript">
                $(document).ready(function(){

                    var approot = "<%= approot %>";

                    //SET ACTIVE MENU
                    var activeMenu = function(parentId, childId){
                        $(parentId).addClass("active").find(".treeview-menu").slideDown();
                        $(childId).addClass("active");
                    };

                    var getDataFunction = function(onDone, onSuccess, approot, command, dataSend, servletName, dataAppendTo, notification){
                        /*
                         * getDataFor	: # FOR PROCCESS FILTER
                         * onDone	: # ON DONE FUNCTION,
                         * onSuccess	: # ON ON SUCCESS FUNCTION,
                         * approot	: # APPLICATION ROOT,
                         * dataSend	: # DATA TO SEND TO THE SERVLET,
                         * servletName  : # SERVLET'S NAME
                         */
                        $(this).getData({
                           onDone	: function(data){
                               onDone(data);
                           },
                           onSuccess	: function(data){
                                onSuccess(data);
                           },
                           approot	: approot,
                           dataSend	: dataSend,
                           servletName	: servletName,
                           dataAppendTo	: dataAppendTo,
                           notification : notification
                        });
                    }

                    //DATE PICKER FUNCTION
                    var datePicker = function(contentId, formatDate){
                        $(contentId).datepicker({
                            format : formatDate
                        });
                        $(contentId).on('changeDate', function(ev){
                            $(this).datepicker('hide');
                        });
                    };

                    //MODAL SETTING
                    var modalSetting = function(elementId, backdrop, keyboard, show){
                        $(elementId).modal({
                            backdrop	: backdrop,
                            keyboard	: keyboard,
                            show	: show
                        });
                    };

                    function iCheckBox(){
                        $("input[type='checkbox'], input[type='radio']").iCheck({
                            checkboxClass: 'icheckbox_minimal',
                            radioClass: 'iradio_minimal'
                        });

                        $(".tickdelete").click(function(){
                           var checked = $(this).find(":checkbox:checked").length; 
                           if(checked == 0){
                               $(this).find(":checkbox").iCheck('check');
                           }else{
                               $(this).find(":checkbox").iCheck('uncheck');
                           }
                        });
                    }
                    datePicker(".datepicker", "yyyy-mm-dd");
                    $("#search-btn").click(function(){
                       var command = <%= Command.SEARCH %>;
                       var datafor = "searchsummerysegmen";
                       var periode = $("#periodesummary").val();
                       var cabang = $("#cabang").val();
                       var dataSend = {
                           "FRM_FIELD_DATA_FOR" : datafor,
                           "command" : command,
                           "periode" : periode,
                           "cabang" : cabang
                       };
                       var onDone = function(data){
                       };
                       
                       var onSuccess = function(data){
                       };
                       getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxPelaporan", "#reportsummary", false);
                    });
                    
                    $("#search-btn-segment").click(function(){
                       var command = <%= Command.SEARCH %>;
                       var datafor = "searchpersegment";
                       var periode = $("#periodepersegment").val();
                       var cabang = $("#cabangpersegment").val();
                       var segment = $("#segment").val();
                       var dataSend = {
                           "FRM_FIELD_DATA_FOR" : datafor,
                           "command" : command,
                           "periode" : periode,
                           "cabang" : cabang,
                           "segment" : segment
                       };
                       var onDone = function(data){
                       };
                       
                       var onSuccess = function(data){
                       };
                       getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxPelaporan", "#reportsummarypersegment", false);
                    });
                });
              </script>
        </div><!-- ./wrapper -->
        
        
    </body>
</html>
