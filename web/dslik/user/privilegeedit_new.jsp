<%-- 
    Document   : privilegeedit_new
    Created on : Jul 17, 2015, 12:01:55 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.admin.AppPrivilegeObj"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.dslik.entity.admin.PstAppPriv"%>
<%@page import="com.dimata.dslik.entity.admin.AppPriv"%>
<%@page import="com.dimata.dslik.form.admin.FrmAppPrivilegeObj"%>
<%@page import="com.dimata.dslik.form.admin.CtrlAppPrivilegeObj"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.entity.admin.PstAppPrivilegeObj"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@ page language="java" %>

<%@ include file = "../..//main/javainit_slik.jsp" %>
<% int  appObjCode = AppObjInfo.composeObjCode(AppObjInfo.G1_ADMIN , AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE); %>
<%@ include file = "../../main/checkuser_slik.jsp" %>


<%!
public static final String listTextGlobal[][] = {
	{"Managemen Pemakai","Hak Akses","Daftar","Edit","Nama Hak Akses","Deskripsi","Hak Akses Modul",
	 "Daftar Hak Akses Modul","Tidak ada Hak Akses"},
	{"User Management","Privilege","List","Edit","Privilege Name","Description","Module Access",
	 "Module Access List","No Access available"}
};

public static final String listTextTableHeader[][] = {
	{"No.","Modul","Kelompok 1","Kelompok 2","Hak Akses"},
	{"No.","Modul","Group 1","Group 2", "Privilege"}
};

public String drawListPrivObj(int language, int start, Vector objectClass) {
	String temp = "";
	String regdatestr = "";

	ControlList ctrlist = new ControlList();
	ctrlist.setAreaWidth("100%");
	ctrlist.setListStyle("listgen");
	ctrlist.setTitleStyle("listgentitle");
	ctrlist.setCellStyle("listgensell");
	ctrlist.setHeaderStyle("listgentitle");
	ctrlist.setCellSpacing("1");
	
	ctrlist.addHeader(listTextTableHeader[language][0], "3%");
	ctrlist.addHeader(listTextTableHeader[language][1], "20%");
	ctrlist.addHeader(listTextTableHeader[language][2], "20%");
	ctrlist.addHeader(listTextTableHeader[language][3], "20%");	
	ctrlist.addHeader(listTextTableHeader[language][4], "27%");
	ctrlist.addHeader("Action","10%");

	ctrlist.setLinkRow(1);
        ctrlist.setLinkSufix("");
	
	Vector lstData = ctrlist.getData();

	Vector lstLinkData 	= ctrlist.getLinkData();		
	ctrlist.reset();
	
	for (int i = 0; i < objectClass.size(); i++) {
		 AppPrivilegeObj appPrivObj = (AppPrivilegeObj) objectClass.get(i);

		 Vector rowx = new Vector();
		 rowx.add(String.valueOf(i+1));
		 rowx.add(AppObjInfo.getTitleObject(appPrivObj.getCode()));
		 rowx.add(AppObjInfo.getTitleGroup1(appPrivObj.getCode()));
		 rowx.add(AppObjInfo.getTitleGroup2(appPrivObj.getCode()));
		 
		 
		 Vector cmdInts = appPrivObj.getCommands();
		 String cmdStr = new String("");
		 for(int ic=0;ic< cmdInts.size() ; ic++){
			cmdStr =cmdStr + AppObjInfo.getStrCommand(((Integer)cmdInts.get(ic)).intValue())+", ";
		 }
		 if(cmdStr.length()>0)
			cmdStr = cmdStr.substring(0, cmdStr.length()-2);
		 
		 rowx.add(cmdStr);
		 rowx.add("<button class='btn btn-warning' type='button' onclick='javascript:cmdEdit(\""+appPrivObj.getOID()+"\")'><i class='fa fa-pencil'></i> Edit</button>");
		 lstData.add(rowx);
	}						

	return ctrlist.drawBootstrapStrip();
}

%>
<%
 
/* VARIABLE DECLARATION */

int recordToGet = 10;
String order = " " + PstAppPrivilegeObj.fieldNames[PstAppPrivilegeObj.FLD_CODE];

Vector listAppPrivObj = new Vector(1,1);


/* GET REQUEST FROM HIDDEN TEXT */
int iCommand = FRMQueryString.requestCommand(request);
int start = FRMQueryString.requestInt(request, "start"); 
int listCommand = FRMQueryString.requestInt(request, "list_command");
if(listCommand==Command.NONE)
	listCommand = Command.FIRST;
long appPrivOID = FRMQueryString.requestLong(request,"appriv_oid");
long appPrivObjOID = FRMQueryString.requestLong(request,"apprivobj_oid");

CtrlAppPrivilegeObj ctrlAppPrivObj = new CtrlAppPrivilegeObj(request);
FrmAppPrivilegeObj frmAppPrivObj = ctrlAppPrivObj.getForm();
String cmdIdxString[] = request.getParameterValues("cmd_assigned");

AppPrivilegeObj x = ctrlAppPrivObj.getAppPrivObj();
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>.."+x.getCode());
/* GET OBJECT */ 
AppPriv appPriv = PstAppPriv.fetch(appPrivOID);
int iErrCode = ctrlAppPrivObj.action(iCommand, appPrivObjOID);
AppPrivilegeObj appPrivObj= ctrlAppPrivObj.getAppPrivObj(); 
int vectSize = PstAppPrivilegeObj.getCountByPrivOID_GroupByObj(appPrivOID);


/* GET Modules Access */
int appObjG1 = FRMQueryString.requestInt(request,FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G1_IDX]);
int appObjG2 = FRMQueryString.requestInt(request,FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G2_IDX]);
int appObjIdx = FRMQueryString.requestInt(request,FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_OBJ_IDX]);

if(iCommand == Command.EDIT){  
  appObjG1 =(AppObjInfo.getIdxGroup1(appPrivObj.getCode()));
  appObjG2 =(AppObjInfo.getIdxGroup2(appPrivObj.getCode()));
  appObjIdx =(AppObjInfo.getIdxObject(appPrivObj.getCode())); 
  System.out.println(" IDX "+ appObjG1+ " "+ appObjG2+ " "+ appObjIdx +" " + appPrivObj.getCommands());
  appObjG1 = appObjG1<0 ? 0 : appObjG1;
  appObjG2 = appObjG2<0 ? 0 : appObjG2;
  appObjIdx = appObjIdx<0 ? 0 : appObjIdx;
}

String msgString = ctrlAppPrivObj.getMessage();
start=ctrlAppPrivObj.actionList(listCommand,start,vectSize,recordToGet);
order=	PstAppPrivilegeObj.fieldNames[PstAppPrivilegeObj.FLD_CODE];	
listAppPrivObj = PstAppPrivilegeObj.listWithCmd_ByPrivOID_GroupByObj(start,recordToGet, appPrivOID , order);

    boolean privAdd = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_ADD);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_UPDATE);
    boolean privDelete = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_DELETE);
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE, AppObjInfo.COMMAND_VIEW);


%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Privelege</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../../template-component/css-component.jsp" %>
	<script type="text/javascript">
	    function cmdPrint(){
		window.print();
	    }
	</script>

         <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="../styles/bootstrap3.1/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="../styles/bootstrap3.1/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <script language="JavaScript">
        function cmdAdd(){
                document.frmList.command.value="<%=Command.ADD%>"; 
                document.frmList.list_command.value="<%=Command.LIST%>";
                document.frmList.submit();	
        }

        function cmdEdit(oid){
                document.frmList.command.value="<%=Command.EDIT%>"; 
                document.frmList.apprivobj_oid.value=oid;
                document.frmList.list_command.value="<%=Command.LIST%>";
                document.frmList.submit();	
        }

        function cmdSave(){
                document.frmEdit.command.value="<%=Command.SAVE%>"; 
                document.frmEdit.list_command.value="<%=Command.LIST%>";
                document.frmEdit.submit();	
        }

        function gotoManagementMenu(){
                document.frmList.action="<%=approot%>/management/main_man.jsp";
                document.frmList.submit();
        }


        <% if(privDelete) {%>
        function cmdCancel(){
                document.frmEdit.command.value="<%=Command.EDIT%>"; 
                document.frmEdit.list_command.value="<%=Command.LIST%>";
                document.frmEdit.submit();	
        }

        function cmdAsk(){
                document.frmEdit.command.value="<%=Command.ASK%>"; 
                document.frmEdit.list_command.value="<%=Command.LIST%>";
                document.frmEdit.submit();	
        }

        function cmdDelete(){
                document.frmEdit.command.value="<%=Command.DELETE%>"; 
                document.frmEdit.list_command.value="<%=Command.LIST%>";
                document.frmEdit.submit();	
        }
        <%}%>
        function changeG1(){
                document.frmEdit.command.value="<%=iCommand%>"; 
                document.frmEdit.list_command.value="<%=Command.LIST%>";
                document.frmEdit.<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G2_IDX]%>.value=0;
                document.frmEdit.<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_OBJ_IDX]%>.value=0;
                document.frmEdit.submit();
        }

        function changeG2(){
                document.frmEdit.command.value="<%=iCommand%>"; 
                document.frmEdit.list_command.value="<%=Command.LIST%>";
                document.frmEdit.<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_OBJ_IDX]%>.value=0;
                document.frmEdit.submit();	
        }

        function changeModule(){
                document.frmEdit.command.value="<%=iCommand%>"; 
                document.frmEdit.list_command.value="<%=Command.LIST%>";
                document.frmEdit.submit();	
        }


        function cmdListFirst(){
                document.frmList.list_command.value="<%=Command.FIRST%>";
                document.frmList.command.value="<%=Command.NONE%>";
                document.frmList.action="privilegeedit_new.jsp";
                document.frmList.submit();
        }

        function cmdListPrev(){
                document.frmList.list_command.value="<%=Command.PREV%>";
                document.frmList.command.value="<%=Command.NONE%>";
                document.frmList.action="privilegeedit_new.jsp";
                document.frmList.submit();
        }

        function cmdListNext(){
                document.frmList.list_command.value="<%=Command.NEXT%>";
                document.frmList.command.value="<%=Command.NONE%>";
                document.frmList.action="privilegeedit_new.jsp";
                document.frmList.submit();
        }
        function cmdListLast(){
                document.frmList.list_command.value="<%=Command.LAST%>";
                document.frmList.command.value="<%=Command.NONE%>";
                document.frmList.action="privilegeedit_new.jsp";
                document.frmList.submit();
        }

        function goPrivilege(){
                document.frmList.command.value="<%=Command.BACK%>";
                document.frmList.action="privilagelist_new.jsp";
                document.frmList.submit();
        }

        function cmdBack(){
                document.frmEdit.command.value="<%=Command.BACK%>";
                document.frmEdit.action="privilegeedit_new.jsp";
                document.frmEdit.submit();
        }

        //<!--
        function hideObjectForEmployee(){
        } 

        function hideObjectForLockers(){ 
        }

        function hideObjectForCanteen(){
        }

        function hideObjectForClinic(){
        }

        function hideObjectForMasterdata(){
        }

        function showObjectForMenu(){
        }

        function MM_swapImgRestore() { //v3.0
          var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
        }

        function MM_preloadImages() { //v3.0
          var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
            var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
            if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
        }

        function MM_findObj(n, d) { //v4.0
          var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
            d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
          if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
          for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
          if(!x && document.getElementById) x=document.getElementById(n); return x;
        }

        function MM_swapImage() { //v3.0
          var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
           if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
        }
        //-->
        </script>
    </head>
    <body class="<%= skin %>">
        <!-- header logo: style can be found in header.less -->
	<input type="hidden" name="approot" id="approot" value="<%= approot %>">
	<input type="hidden" name="command" id="command" value="<%= Command.NONE %>">
        <div class="wrapper row-offcanvas row-offcanvas-left nonprint">
            <!-- Left side column. contains the logo and sidebar -->
            <%@ include file="../../template-component/header-component.jsp" %> 
            <%@ include file="../../template-component/sidebar-component.jsp" %> 
            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Tables
                        <small>Privilege</small>
                    </h1>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="box">
                        <div class="box-body table-responsive">
                         <table width="100%">
                          <tr> 
                            <td colspan="2" valign="top"> 
                              <form name="frmList" method="post" action="privilegeedit_new.jsp">
                                <table width="100%">
                                  <tr>
                                                            <td colspan="2">&nbsp;</td>
                                                      </tr>
                                                      <tr> 
                                    <td width="15%" nowrap><strong><%=listTextGlobal[SESS_LANGUAGE][4]%></strong></td>
                                    <td width="85%" nowrap><strong>:</strong> &nbsp;<%=appPriv.getPrivName()%></td>
                                  </tr>
                                  <tr> 
                                    <td width="15%"><strong><%=listTextGlobal[SESS_LANGUAGE][5]%></strong></td>
                                    <td width="85%" nowrap><strong>:</strong> &nbsp;<%=appPriv.getDescr()%></td>
                                  </tr>
                                  <% if(listAppPrivObj != null && listAppPrivObj.size()>0){%>
                                  <tr>
                                                            <td colspan="2">&nbsp;</td>
                                                      </tr>
                                  <tr> 
                                    <td colspan="2" class="listtitle"><%=listTextGlobal[SESS_LANGUAGE][7]%></td>
                                  </tr>
                                  <tr> 
                                    <td colspan="2" align="center"><%=drawListPrivObj(SESS_LANGUAGE, start, listAppPrivObj)%> </td>
                                  </tr>
                                  <% }else{%>
                                  <tr> 
                                    <td colspan="2" class="comment"><%=listTextGlobal[SESS_LANGUAGE][8]%></td>
                                  </tr>
                                  <%}%>
                                  <tr> 
                                    <td colspan="2" class="command"> 
                                      <% ControlLine ctrLine = new ControlLine(); %>
                                      <%
                                                                            ctrLine.setLocationImg(approot+"/images");
                                                                            ctrLine.initDefault();						   					   
                                                              %>
                                      <%=ctrLine.drawImageListLimit(listCommand,vectSize,start,recordToGet)%> </td>
                                  </tr>
                                  <tr> 
                                    <td colspan="2" class="command">
                                      <% if(iCommand == Command.NONE || iCommand == Command.LIST || iCommand == Command.SAVE || iCommand == Command.DELETE || iCommand == Command.BACK || iCommand == Command.FIRST || iCommand == Command.PREV || iCommand == Command.NEXT || iCommand == Command.LAST ){%>
                                      <table width="45%" border="0" cellspacing="1" cellpadding="2">
                                        <% if(privAdd && privUpdate){%>
                                        <tr> 
                                          <td width="9%">
					      <!--<a href="javascript:cmdAdd()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image100111','','<%=approot%>/images/BtnNewOn.jpg',1)">
						    <img name="Image100111" border="0" src="<%=approot%>/images/BtnNew.jpg" width="24" height="24" alt="<%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][6],ctrLine.CMD_ADD,true)%>">
					      </a>-->
					    </td>
					    <td nowrap width="41%">
						<button onclick="javascript:cmdAdd()" class="command btn btn-primary">
						    <i class='fa fa-plus'></i> <%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][6],ctrLine.CMD_ADD,true)%>
						</button>
					    </td>
                                          <td width="7%">
					      <!--<a href="javascript:goPrivilege()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('G2','','<%=approot%>/images/BtnBackOn.jpg',1)">
						    <img name="G2" border="0" src="<%=approot%>/images/BtnBack.jpg" width="24" height="24" alt="<%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][1],ctrLine.CMD_BACK,true)%>">
					      </a>-->
					</td>
                                          <td class="command" width="43%">
					      <button onclick="javascript:goPrivilege()" class="btn btn-primary">
						    <i class='fa fa-arrow-left'></i> <%=ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][1],ctrLine.CMD_BACK,true)%>
					      </button>
					</td>
                                        </tr>
                                        <% }%>
                                      </table>
                                      <%}%>
                                      <input type="hidden" name="appriv_oid" value="<%=appPrivOID%>">
                                      <input type="hidden" name="apprivobj_oid" value="<%=appPrivObjOID%>">
                                      <input type="hidden" name="command" value="<%=iCommand%>">
                                      <input type="hidden" name="list_command" value="<%=listCommand%>">
                                      <input type="hidden" name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G1_IDX]%>" value="<%=appObjG1%>">
                                      <input type="hidden" name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G2_IDX]%>" value="<%=appObjG2%>">
                                      <input type="hidden" name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_OBJ_IDX]%>" value="<%=appObjIdx%>">
                                      <input type="hidden" name="start" value="<%=start%>">
                                    </td>
                                  </tr>
                                </table>
                              </form>
                            </td>
                          </tr>
                          <%if(((iCommand==Command.SAVE)&&(frmAppPrivObj.errorSize()>0))||(iCommand==Command.ADD)||(iCommand==Command.EDIT)||(iCommand==Command.ASK)){%>
                          <tr> 
                            <td colspan="2" valign="top"> 
                              <form name="frmEdit" method="post" action="">
                                <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                  <tr> 
				      <td colspan="3" width="100%">
					  <div class="row">
					      <div class="col-md-12">
						  <div class="col-md-3">
						      <strong><%=listTextTableHeader[SESS_LANGUAGE][1]%></strong>
						      <input type="hidden" name="appriv_oid" value="<%=appPrivOID%>">
							<input type="hidden" name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_PRIV_ID]%>" value="<%=appPrivOID%>">
							<input type="hidden" name="apprivobj_oid" value="<%=appPrivObjOID%>">
							<input type="hidden" name="command" value="<%=iCommand%>">
							<input type="hidden" name="list_command" value="<%=listCommand%>">
							<input type="hidden" name="start" value="<%=start%>">
						  </div>
						  <div class="col-md-6">
						      <% if (iCommand==Command.ADD) {%>
							    <select name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G1_IDX]%>" class="elemenForm form-control" onChange="javascript:changeG1()">
								<%
								for(int ig1=0;ig1< AppObjInfo.titleG1.length; ig1++){
									String select = (appObjG1 == ig1) ? "selected" : "";
									try {
								%>
										<option value="<%=ig1%>" <%=select%>><%=AppObjInfo.titleG1[ig1]%></option>
								<% 
									} catch(Exception exc) {
										System.out.println(" CREATE LIST ==> privilegeedit_new.jsp. G1 exc"+exc);
									}
								}
								%>
							    </select>
							<% } else { %>
							    <%=AppObjInfo.titleG1[appObjG1]%> 
							    <input type="hidden" name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G1_IDX]%>" value="<%=appObjG1%>">
							<% } %>
						  </div>
					      </div>
					  </div>
						  
					<div class="row">
					      <div class="col-md-12">
						  <div class="col-md-3">
						      <strong><%=listTextTableHeader[SESS_LANGUAGE][2]%></strong>
						  </div>
						  <div class="col-md-6">
						      <% if (iCommand==Command.ADD) {%>
							    <select name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G2_IDX]%>" onChange="javascript:changeG2()" class="form-control">
							    <%
							    for(int ig2=0;ig2< AppObjInfo.titleG2[appObjG1].length; ig2++){
								    String select = (appObjG2 == ig2) ? "selected" : "";
								    try {
							    %>
									    <option value="<%=ig2%>" <%=select%>><%=AppObjInfo.titleG2[appObjG1][ig2]%></option>
							    <% 
								    }
								    catch(Exception exc) {
									    System.out.println(" CREATE LIST ==> privilegeedit_new.jsp. G2 exc"+exc);
								    }
							    }
							    %>
							    </select>
						    <% }
						       else { %>
							     <%=AppObjInfo.titleG2[appObjG1][appObjG2]%> 
							     <input type="hidden" name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_G2_IDX]%>" value="<%=appObjG2%>">
						    <% } %>
						  </div>
					      </div>
					  </div>
					
					<div class="row">
					      <div class="col-md-12">
						  <div class="col-md-3">
						      <strong><%=listTextTableHeader[SESS_LANGUAGE][3]%></strong>
						  </div>
						  <div class="col-md-6">
						      <% if (iCommand==Command.ADD) {%>
							    <select name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_OBJ_IDX]%>" onChange="javascript:changeModule()" class="form-control">
							    <%
							    for(int iobj=0;iobj< AppObjInfo.objectTitles[appObjG1][appObjG2].length; iobj++) {
								    String select = (appObjIdx == iobj) ? "selected" : "";
								    try {
							    %>
									    <option value="<%=iobj%>" <%=select%>><%=AppObjInfo.objectTitles[appObjG1][appObjG2][iobj]%></option>
							    <%
								    }
								    catch(Exception exc) {
									    System.out.println(" CREATE LIST ==> privilegeedit_new.jsp. Object exc"+exc);
								    }
							    }
							    %>
							    </select>
						    <% }
						       else { %>
							     <%=AppObjInfo.objectTitles[appObjG1][appObjG2][appObjIdx]%> 
							     <input type="hidden" name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_OBJ_IDX]%>" value="<%=appObjIdx%>">
						    <% } %>
						  </div>
					      </div>
					  </div>
                                      
					<div class="row">
					      <div class="col-md-12">
						  <div class="col-md-3">
						      <strong><%=listTextTableHeader[SESS_LANGUAGE][4]%></strong>
						  </div>
						  <div class="col-md-6">
						      <%
							for(int id=0;id< AppObjInfo.objectCommands[appObjG1][appObjG2][appObjIdx].length; id++) {
								int iCmd= AppObjInfo.objectCommands[appObjG1][appObjG2][appObjIdx][id];
								String checked = appPrivObj.existCommand(iCmd) ? "checked" : "";
							%>
								<input type="checkbox" name="<%=FrmAppPrivilegeObj.fieldNames[FrmAppPrivilegeObj.FRM_COMMANDS]%>" value="<%=iCmd%>" <%=checked%>>
								<%=AppObjInfo.strCommand[iCmd]%> &nbsp;&nbsp;&nbsp;&nbsp; 
							<% } %>
						  </div>
					      </div>
					  </div>
                                    </td>
				  </tr>
                                  <tr> 
                                    <td width="167">&nbsp;</td>
                                    <td width="679" nowrap>&nbsp;</td>
                                  </tr>
                                  <tr> 
                                    <td colspan="2"> 
                                      <%
                                                                    String cmdTitle = listTextGlobal[SESS_LANGUAGE][6];

                                                                    ctrLine.setSaveImageAlt(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_SAVE,true));
                                                                    ctrLine.setDeleteImageAlt(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_ASK,true));
                                                                    ctrLine.setBackImageAlt(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_BACK,true));
                                                                    ctrLine.setEditImageAlt(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_CANCEL,false));

                                                                    ctrLine.setLocationImg(approot+"/images");
                                                                    ctrLine.initDefault();
                                                                    ctrLine.setTableWidth("80%");
                                                                    ctrLine.setCommandStyle("command");
                                                                    ctrLine.setColCommStyle("command");
                                                                    String scomDel = "javascript:cmdAsk('"+appPrivObjOID+"')";
                                                                    String sconDelCom = "javascript:cmdDelete('"+appPrivObjOID+"')";
                                                                    String scancel = "javascript:cmdCancel('"+appPrivObjOID+"')";

                                                                    ctrLine.setBackCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_BACK,true));
                                                                    ctrLine.setSaveCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_SAVE,true));
                                                                    ctrLine.setDeleteCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_ASK,true));
                                                                    ctrLine.setConfirmDelCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_DELETE,true));
                                                                    ctrLine.setAddCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_ADD,true));
                                                                    ctrLine.setCancelCaption(ctrLine.getCommand(SESS_LANGUAGE,cmdTitle,ctrLine.CMD_CANCEL,false));

                                                                    if (privDelete){
                                                                            ctrLine.setConfirmDelCommand(sconDelCom);
                                                                            ctrLine.setDeleteCommand(scomDel);
                                                                            ctrLine.setEditCommand(scancel);
                                                                    }else{ 
                                                                            ctrLine.setConfirmDelCaption("");
                                                                            ctrLine.setDeleteCaption("");
                                                                            ctrLine.setEditCaption("");
                                                                    }

                                                                    if(privAdd == false  && privUpdate == false){
                                                                            ctrLine.setSaveCaption("");
                                                                    }

                                                                    if (privAdd == false){
                                                                            ctrLine.setAddCaption("");
                                                                    }

                                                                    %>
                                      <!--%= ctrLine.drawImage(iCommand, iErrCode, msgString)%-->
				      <button class="btn btn-success" type="button" onclick="javascript:cmdSave()"><i class="fa fa-check"></i> <%= ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][6],ctrLine.CMD_SAVE,true) %></button>
				      <button class="btn btn-primary" type="button" onclick="javascript:cmdBack()"><i class="fa fa-arrow-left"></i> <%= ctrLine.getCommand(SESS_LANGUAGE,listTextGlobal[SESS_LANGUAGE][6],ctrLine.CMD_BACK,true) %></button>
				    </td>
                                  </tr>
                                  <tr> 
                                    <td colspan="2"> </td>
                                  </tr>
                                  <tr> 
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                  </tr>
                                </table>
                              </form>
                            </td>
                          </tr>
                          <% } %>
                        <!--</div>-->
                    </table>
                   </div>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <%@include file="../../template-component/plugins-component.jsp" %>
	<script type="text/javascript">
	    $(document).ready(function(){
		//SET ACTIVE MENU
		var activeMenu = function(parentId, childId){
		    $(parentId).addClass("active").find(".treeview-menu").slideDown();
		    $(childId).addClass("active");
		}

		activeMenu("#system", "#masterprivilege");
	    })
	</script>
    </body>
</html>