<%-- 
    Document   : delimited_text
    Created on : Sep 27, 2016, 11:53:48 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.masterdata.OutletConnection"%>
<%@page import="com.dimata.dslik.session.proses.ProsesTransferDataBank"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="com.dimata.dslik.session.proses.FileSent"%>
<%@page import="com.dimata.dslik.session.proses.ManagerDilimitedText"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_BULANAN, AppObjInfo.OBJ_BULANAN, AppObjInfo.COMMAND_VIEW);
    boolean privStart = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_BULANAN, AppObjInfo.OBJ_BULANAN, AppObjInfo.COMMAND_START);
    boolean privStop = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PROSES_DELIMITED_TEXT, AppObjInfo.G2_BULANAN, AppObjInfo.OBJ_BULANAN, AppObjInfo.COMMAND_STOP);
    
    int iCommand = FRMQueryString.requestCommand(request);
 
    ManagerDilimitedText managerDilimitedText = new ManagerDilimitedText();
    ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
    OutletConnection outletConnection = prosesTransferDataBank.getConfigurasiConnection();
    String locFile="";
    if(outletConnection.equals("")){
        locFile=""+approot;
    }else{
        locFile=outletConnection.getPathDelimitedText();
    }
    FileSent fileSent = new FileSent();
    fileSent = new FileSent();
    fileSent.setFileName("");
    fileSent.setTahun(""+Formater.formatDate(periode.getTglAwal(), "yyyy"));
    fileSent.setBulan(""+Formater.formatDate(periode.getTglAwal(), "MM"));
    fileSent.setOperasiData("U");
    fileSent.setPeriodeId(periode.getOID());
    boolean checkFolderIsExist = fileSent.checkDirectori(locFile, Formater.formatDate(periode.getTglAwal(), "MM"), Formater.formatDate(periode.getTglAwal(), "yyyy"));
    String locationDocumentzip="";
    if(checkFolderIsExist){
        locationDocumentzip=approot+"/dslik/dilimited_text_slik/"+fileSent.getBulan()+"_"+fileSent.getTahun()+".zip";
    }
    switch (iCommand) {
        case Command.START:
            //create file
            fileSent.create(locFile, fileSent.getBulan(), fileSent.getTahun());
            String locationDocument=locFile+""+fileSent.getBulan()+"_"+fileSent.getTahun();
            fileSent.setLocation(locationDocument);
            managerDilimitedText.startDilimitedText(fileSent,outletConnection);
            break;

        case Command.STOP:

            managerDilimitedText.stopMonitor();

            break;

    }
    // boolean running = managerDilimitedText.getStatus();
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | Delimited Text Bulanan</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <script language="JavaScript">
            
        function cmdStart(){
            document.driver.command.value="<%=Command.START%>";
            document.driver.hidden_driver_id.value="0";
            document.driver.action="delimited_text.jsp";
            document.driver.submit();
        }

        function cmdStop(){

            document.driver.command.value="<%=Command.STOP%>";
            document.driver.start.value="0";
            document.driver.action="delimited_text.jsp";
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
  <body class="<%= skin %>">
      <input type="hidden" name="command" id="command" value="<%= Command.NONE %>">
      <input type="hidden" name="approot" id="approot" value="<%= approot %>">
      <input type="hidden" name="privstart" id="privstart" value="<%= privStart %>">
      <input type="hidden" name="privstop" id="privstop" value="<%= privStop %>">
    <div class="wrapper">
      
	<%@include file="../../template-component/header-component.jsp" %>
        <%@include file="../../template-component/sidebar-component.jsp" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Delimited Text
            <small>Data Bulanan</small>
          </h1>
        </section>
        <!-- Main content -->
        <%
            if(privView){
        %>
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <form name="driver"  method ="post" action="" role="form">
            <input type="hidden" name="command" value="<%=iCommand%>">
                <div class="row">
                  <div class="col-md-12">
                      <div class='box box-primary'>
                          <div class='box-header'>
                              Delimited Text Data Bulanan
                          </div>
                          <div class="box-body">
                              <div id="countryElement">
                                  <%if(checkFolderIsExist){%>
                                        Data Delimited Text Sudah berhasil Download file di <a href="<%=locationDocumentzip%>" target="_blank">Sini</a>
                                  <%}%>
                              </div>
                          </div>
                          <%  
                              /*String stopSts = "";
                              String startSts = "";
                              if (running) {
                                  startSts = "disabled=\"true\"";
                                  stopSts = "";
                              } else {
                                  startSts = "";
                                  stopSts = "disabled=\"true\"";
                              }*/
                          %>
                          <%--
                            <div class='box-footer'>
                              <button class="btn btn-primary btnaddgeneral" onClick="javascript:cmdStart()" data-oid="0" data-for="showform" <%=startSts%>>
                                  <i class="fa fa-star"></i> Start
                              </button>
                              <button class="btn btn-danger btndeleteareatype" onClick="javascript:cmdStop()" data-for="country"  <%=stopSts%>>
                                  <i class="fa fa-stop"></i> Stop
                              </button>
                          </div>
                          --%>
                           <div id="dynamicOrderButton">
                           </div>       
                           <div id="dynamicOrder">
                           </div>        
                      </div>
                  </div><!-- ./col -->
                </div><!-- /.row -->
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
      <script language="JavaScript">
        function ajaxProsesTransfer(url,data,type,appendTo,another,optional){
            $.ajax({
                url : ""+url+"",
                data: ""+data+"",
                type : ""+type+"",
                async : false,
                cache: false,
                success : function(data) {
                    if (appendTo!=""){
                        $(''+appendTo+'').html(data);
                    }
                }
            }).done(function(){
                
            });
        }
        
        function loadData(){
            var privstart = document.getElementById("privstart").value;
            var privstop = document.getElementById("privstop").value;
            var data="datatype=7&privstart="+privstart+"&privstop="+privstop;
            ajaxProsesTransfer("<%=approot%>/AjaxProsesTransfer",data,"GET","#dynamicOrder","loadData","");
        }
        
        function loadButton(){
            var privstart = document.getElementById("privstart").value;
            var privstop = document.getElementById("privstop").value;
            var data="datatype=7&dataview=1&privstart="+privstart+"&privstop="+privstop;
            ajaxProsesTransfer("<%=approot%>/AjaxProsesTransfer",data,"GET","#dynamicOrderButton","loadButton","");
        }
        
        //load data pertama kalinya
        loadData();
        loadButton();
        
        //untuk membuat load data
        setInterval(function() {
            loadData();
            loadButton();
        },1000);
      </script>
    </div><!-- ./wrapper -->
  </body>
</html>
