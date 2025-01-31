<%-- 
    Document   : cabang_bank
    Created on : Oct 4, 2016, 11:50:54 AM
    Author     : Dewa
--%>
<%@page import="com.dimata.dslik.session.proses.ProsesTransferDataBank"%>
<%@page import="com.dimata.dslik.form.admin.CtrlAppUser"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.dslik.entity.admin.PstAppUser"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%!
public static final String textListTitleHeader[][] =
{
	{"Sistem","Daftar Pemakai","ID Pemakai","Nama Lengkap","Status","Tambah Pemakai Baru","No"},
	{"System","User LIst","User ID","Full Name","Status","Add New User","No"}
};

public String drawListAppUser(int language,Vector objectClass)
{
	String temp = ""; 
	String regdatestr = "";
	
	ControlList ctrlist = new ControlList();
	ctrlist.setAreaWidth("90%");
	ctrlist.setListStyle("listgen");
	ctrlist.setTitleStyle("listgentitle");
	ctrlist.setCellStyle("listgensell");
	ctrlist.setHeaderStyle("listgentitle");
        ctrlist.addHeader(textListTitleHeader[language][6],"5%");
	ctrlist.addHeader(textListTitleHeader[language][2],"20%");
	ctrlist.addHeader(textListTitleHeader[language][3],"70%");
	ctrlist.addHeader(textListTitleHeader[language][4],"10%");

	ctrlist.setLinkRow(0);
        ctrlist.setLinkSufix("");

	Vector lstData = ctrlist.getData();

	Vector lstLinkData 	= ctrlist.getLinkData();

	ctrlist.setLinkPrefix("javascript:cmdEdit('");
	ctrlist.setLinkSufix("')");
	ctrlist.reset();
        int no=0;
	for (int i = 0; i < objectClass.size(); i++) {
		 AppUser appUser = (AppUser)objectClass.get(i);
                 no=no+1;
		 Vector rowx = new Vector();
		 rowx.add(""+no);
		 rowx.add(String.valueOf(appUser.getLoginId()));
		 rowx.add(String.valueOf(appUser.getFullName()));
		 rowx.add(String.valueOf(AppUser.getStatusTxt(appUser.getUserStatus())));		 
		 		 
		 lstData.add(rowx);
		 lstLinkData.add(String.valueOf(appUser.getOID()));
	}						

	return ctrlist.drawBootstrapStrip();
}

%>
<%

/* VARIABLE DECLARATION */
int recordToGet = 300;

String order = " " + PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID];

Vector listAppUser = new Vector(1,1);
ControlLine ctrLine = new ControlLine();

/* GET REQUEST FROM HIDDEN TEXT */
int iCommand = FRMQueryString.requestCommand(request);
int start = FRMQueryString.requestInt(request, "start"); 
long appUserOID = FRMQueryString.requestLong(request,"user_oid");
int listCommand = FRMQueryString.requestInt(request, "list_command");
if(listCommand==Command.NONE){
     listCommand = Command.LIST;
}

//proses download
if(iCommand==Command.SUBMIT){
    ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
    String periodeHost = prosesTransferDataBank.getDataUserBank("");
}

CtrlAppUser ctrlAppUser = new CtrlAppUser(request);
int vectSize = PstAppUser.getCount(""); 
start = ctrlAppUser.actionList(listCommand, start,vectSize,recordToGet);
listAppUser = PstAppUser.listPartObj(start,recordToGet, "" , order);

boolean privAdd = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_ADD);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_UPDATE);
    boolean privDelete = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_DELETE);
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_VIEW);
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Modul Master Data</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../../template-component/css-component.jsp" %>
        <script language="JavaScript">
            function cmdDownload(){
                document.userlist.command.value="<%=Command.SUBMIT%>";
                document.userlist.action="dslik_userlist.jsp";
                document.userlist.submit();
            }
            
            function addNew(){
                    document.userlist.user_oid.value="0";
                    document.userlist.list_command.value="<%=listCommand%>";
                    document.userlist.command.value="<%=Command.ADD%>";
                    document.userlist.action="useredit_new.jsp";
                    document.userlist.submit();
            }
            
            <%
                if(privUpdate){
            %>
            function cmdEdit(oid){
                    document.userlist.user_oid.value=oid;
                    document.userlist.list_command.value="<%=listCommand%>";
                    document.userlist.command.value="<%=Command.EDIT%>";
                    document.userlist.action="useredit_new.jsp";
                    document.userlist.submit();
            }
            <%
                }
            %>

            function cmdListFirst(){
                    document.userlist.command.value="<%=Command.FIRST%>";
                    document.userlist.list_command.value="<%=Command.FIRST%>";
                    document.userlist.action="userlist.jsp";
                    document.userlist.submit();
            }

            function cmdListPrev(){
                    document.userlist.command.value="<%=Command.PREV%>";
                    document.userlist.list_command.value="<%=Command.PREV%>";
                    document.userlist.action="userlist.jsp";
                    document.userlist.submit();
            }

            function cmdListNext(){
                    document.userlist.command.value="<%=Command.NEXT%>";
                    document.userlist.list_command.value="<%=Command.NEXT%>";
                    document.userlist.action="userlist.jsp";
                    document.userlist.submit();
            }

            function cmdListLast(){
                    document.userlist.command.value="<%=Command.LAST%>";
                    document.userlist.list_command.value="<%=Command.LAST%>";
                    document.userlist.action="userlist.jsp";
                    document.userlist.submit();
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
                        Mapping
                        <small>Master Data User</small>
                    </h1>
                </section>
                <!-- Main content -->
                <%
                    if(privView){
                %>
                <section class="content">
                    <!-- Small boxes (Stat box) -->
                    <form name="userlist"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%= iCommand %>">
                        <input type="hidden" name="user_oid" value="<%=appUserOID%>">
                        <input type="hidden" name="vectSize" value="<%=vectSize%>">
                        <input type="hidden" name="start" value="<%=start%>">
                        <input type="hidden" name="list_command" value="<%=listCommand%>">
                        <div class="row">
                            <div class="col-md-12">
                                <div class='box box-primary'>
                                    <div class='box-footer'>
                                        <%
                                            if(privAdd){
                                                %>
                                                    <button class="btn btn-primary btnaddgeneral" data-oid="0" data-for="showform">
                                                        <i class="fa fa-plus"></i> Add User
                                                    </button>
                                                    <button class="btn btn-danger btndownloadcabang" data-for="country" onClick="javascript:cmdDownload()" >
                                                        <i class="fa fa-trash"></i> Download User Bank 
                                                    </button>
                                                <%
                                            }
                                        %>
                                    </div>
                                    <div class="box-body">
                                        <div id="countryElement">
                                            <% if ((listAppUser!=null)&&(listAppUser.size()>0)){ %>
                                                <%=drawListAppUser(SESS_LANGUAGE, listAppUser)%> 
                                            <%}%>
                                        </div>
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
        </div><!-- ./wrapper -->
    </body>
</html>
