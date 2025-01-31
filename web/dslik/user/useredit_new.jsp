<%-- 
    Document   : useredit_new
    Created on : Jul 17, 2015, 5:10:08 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.admin.PstAppUser"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.dslik.form.admin.CtrlAppUser"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlLine"%>
<%@page import="com.dimata.dslik.form.admin.FrmAppUser"%>
<%@page import="com.dimata.dslik.entity.admin.PstUserGroup"%>
<%@page import="com.dimata.dslik.entity.admin.AppGroup"%>
<%@page import="com.dimata.dslik.entity.admin.PstAppGroup"%>
<%@page import="com.dimata.gui.jsp.ControlCheckBox"%>
<%@ include file="../../main/javainit_slik.jsp"%>
<% int  appObjCode = AppObjInfo.composeObjCode(AppObjInfo.G1_ADMIN , AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER); %>
<%@ include file="../../main/checkuser_slik.jsp" %>
<!-- JSP Block -->
<%!
    public static final String textListTitle[][] =
    {
        {"User","Harus diisi"},
        {"User","required"}
    };

public static final String textListTitleHeader[][]=
{
	//0,                    1,          2,          3,                  4,              5,      6,          7,              8,              9,              10,                 11,                     12                               13                          14                                15              16                 17                                        18
        {"Manajemen Pemakai","ID Pemakai","Pasword","Konfirmasi Pasword","Nama Lengkap","Email","Keterangan","Status Pemakai","Grup Pemakai","Simpan Pemakai","Hapus Pemakai","Kembali ke Daftar Pemakai","Proses OK..kembali ke list","Assign Final Document Transaction Location","Assign Data Exchange Location"," Assign Day Login","Assign Time","Assign View Sales/Stock Report Location","Nama Perusahaan","Assign Create Location Document"},
	{"User Management","User ID","Password","Confirm Password","Full Name","Email","Description","User Status","User Group","Save User","Delete User","Back to User List","Processing OK..back to list","Assign Final Document Transaction Location","Assign Data Exchange Location","Assign Day Login","Assign Time","Assign View Sales/Stock Report Location","Company","Assign Create Location Document"}
};

public String ctrCheckBox(long userID)
{
	ControlCheckBox chkBx=new ControlCheckBox();
	chkBx.setCellSpace("0");
	chkBx.setCellStyle("");
	chkBx.setWidth(3);
	chkBx.setTableAlign("left");
	chkBx.setCellWidth("10%");

        try
		{
            Vector checkValues = new Vector(1,1);
            Vector checkCaptions = new Vector(1,1);
            Vector allGroups = PstAppGroup.list(0, 0, "", "");

            if(allGroups!=null)
			{
                int maxV = allGroups.size();
                for(int i=0; i< maxV; i++)
				{
                    AppGroup appGroup = (AppGroup) allGroups.get(i);
                    checkValues.add(Long.toString(appGroup.getOID()));
                    checkCaptions.add(appGroup.getGroupName());
                }
            }

            Vector checkeds = new Vector(1,1);
            PstUserGroup pstUg = new PstUserGroup(0);
            Vector groups = SessAppUser.getUserGroup(userID);

            if(groups!=null)
			{
                int maxV = groups.size();
                for(int i=0; i< maxV; i++)
				{
                    AppGroup appGroup = (AppGroup) groups.get(i);
                    checkeds.add(Long.toString(appGroup.getOID()));
                }
            }

            chkBx.setTableWidth("100%");

            String fldName = FrmAppUser.fieldNames[FrmAppUser.FRM_USER_GROUP];
            return chkBx.draw(fldName,checkValues,checkCaptions,checkeds);

        } catch (Exception exc){
            return "No group assigned";
        }

}

%>
<%

/* VARIABLE DECLARATION */

ControlLine ctrLine = new ControlLine();

/* GET REQUEST FROM HIDDEN TEXT */
int iCommand = FRMQueryString.requestCommand(request);

long appUserOID = FRMQueryString.requestLong(request,"user_oid");
int start = FRMQueryString.requestInt(request, "start");

AppUser appUser = new AppUser();
CtrlAppUser ctrlAppUser = new CtrlAppUser(request);
FrmAppUser frmAppUser = ctrlAppUser.getForm();
int excCode = FRMMessage.NONE;
String msgString =  "";
if(iCommand == Command.SAVE){
	frmAppUser.requestEntityObject(appUser);
	String pwd = FRMQueryString.requestString(request,frmAppUser.fieldNames[frmAppUser.FRM_PASSWORD]);
	String repwd  = FRMQueryString.requestString(request,frmAppUser.fieldNames[frmAppUser.FRM_CFRM_PASSWORD]);
	if(!pwd.equals(repwd)){
		excCode = FRMMessage.ERR_PWDSYNC;
		msgString = FRMMessage.getMessage(excCode);
	}
}
if(excCode == FRMMessage.NONE){
	excCode = ctrlAppUser.action(iCommand,appUserOID,request);
	msgString =  ctrlAppUser.getMessage();
	appUser = ctrlAppUser.getAppUser();
    if(excCode<0)
        excCode = 0;
}
if   ((iCommand!=Command.LIST)){
 if(((iCommand==Command.SAVE)||(iCommand==Command.DELETE))&&(frmAppUser.errorSize()<1)){
	%>
<jsp:forward page="dslik_userlist.jsp">
<jsp:param name="start" value="<%=start%>" />
<jsp:param name="user_oid" value="<%=appUser.getOID()%>" />
</jsp:forward>
<%
	}
}

boolean privAdd = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_ADD);
boolean privUpdate = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_UPDATE);
boolean privDelete = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_DELETE);
boolean privView = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_ADMIN, AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER, AppObjInfo.COMMAND_VIEW);
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Data Tables</title>
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
            <!--
            function cmdCancel(){
                    document.frmAppUser.command.value="<%=Command.EDIT%>";
                    document.frmAppUser.action="useredit_new.jsp";
                    document.frmAppUser.submit();
            }

            <%if(privAdd || privUpdate) {%>
            function cmdSave(){
                    document.frmAppUser.command.value="<%=Command.SAVE%>";
                    document.frmAppUser.action="useredit_new.jsp";
                    document.frmAppUser.submit();
            }
            <%}%>

            <% if(privDelete) {%>
            function cmdDelete(oid){
                    document.frmAppUser.user_oid.value=oid;
                    document.frmAppUser.command.value="<%=Command.ASK%>";
                    document.frmAppUser.action="useredit_new.jsp";
                    document.frmAppUser.submit();
            }

            function changePasalUmum(){


                document.frmAppUser.command.value="<%=Command.EDIT%>";
                document.frmAppUser.action = "useredit_new.jsp";
                document.frmAppUser.submit();

            }

            function cmdConfirmDelete(oid){
                    document.frmAppUser.user_oid.value=oid;
                    document.frmAppUser.command.value="<%=Command.DELETE%>";
                    document.frmAppUser.action="useredit_new.jsp";
                    document.frmAppUser.submit();
            }
            <%}%>


            function cmdSearch() {
                            document.frmAppUser.command.value="<%=Command.LIST%>";
                            document.frmAppUser.action="useredit_new.jsp";
                            document.frmAppUser.submit();


            }

            function cmdBack(oid){
                    document.frmAppUser.user_oid.value=oid;
                    document.frmAppUser.command.value="<%=Command.LIST%>";
                    document.frmAppUser.action="dslik_userlist.jsp";
                    document.frmAppUser.submit();
            }

            function MM_swapImgRestore() { //v3.0
                    var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
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
        <%@ include file="../../template-component/header-component.jsp" %>
	<input type="hidden" name="approot" id="approot" value="<%= approot %>">
	<input type="hidden" name="command" id="command" value="<%= Command.NONE %>">
        <div class="wrapper row-offcanvas row-offcanvas-left nonprint">
            <!-- Left side column. contains the logo and sidebar -->
            <%@ include file="../../template-component/sidebar-component.jsp" %> 
             
            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Tables
                        <small>User List</small>
                    </h1>
                </section>
                <!-- Main content -->
                <section class="content">
                     <div class="box box-primary">
                         <div class="box-body table-responsive">
                             <form name="frmAppUser" method="post" action="">
                                  <input type="hidden" name="command" value="">
                                  <input type="hidden" name="user_oid" value="<%=appUserOID%>">
                                  <input type="hidden" name="start" value="<%=start%>">
                                  <table width="100%" cellpadding="0" border="0" cellspacing="2">
                                    <%if(((iCommand==Command.SAVE)&&(frmAppUser.errorSize()>0))||(iCommand==Command.ADD)||(iCommand==Command.EDIT)||(iCommand==Command.ASK) || (iCommand==Command.DELETE && excCode>0)){%>
                                    
				    
				    <tr>
                                      <td colspan="3" class="txtheading1"></td>
                                    </tr>
                                    <tr>
                                      <td colspan="3" height="26" class="bigtitleflash">
                                        <hr size="1">
                                      </td>
                                    </tr>
                                    <tr>
					<td width="100%" height="26" colspan="3">
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							<%=textListTitleHeader[SESS_LANGUAGE][1]%> * 
						    </div>
						    <div class="col-md-4">
							<input type="text" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_LOGIN_ID] %>" value="<%=appUser.getLoginId()%>" class="formElemen form-control">
							&nbsp;<%= frmAppUser.getErrorMsg(frmAppUser.FRM_LOGIN_ID) %>     
						    </div>
						</div>
					    </div>
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							<%=textListTitleHeader[SESS_LANGUAGE][2]%> * 
						    </div>
						    <div class="col-md-4">
							<input type="password" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_PASSWORD] %>" value="<%=appUser.getPassword()%>" class="formElemen form-control">
							 &nbsp;<%= frmAppUser.getErrorMsg(frmAppUser.FRM_PASSWORD) %>     
						    </div>
						</div>
					    </div>
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							<%=textListTitleHeader[SESS_LANGUAGE][3]%> * 
						    </div>
						    <div class="col-md-4">
							<input type="password" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_CFRM_PASSWORD] %>" value="<%=appUser.getPassword()%>" class="formElemen form-control">
							 &nbsp;<%= frmAppUser.getErrorMsg(frmAppUser.FRM_CFRM_PASSWORD) %>    
						    </div>
						</div>
					    </div>
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							<%=textListTitleHeader[SESS_LANGUAGE][4]%> * 
						    </div>
						    <div class="col-md-4">
							<input type="text" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_FULL_NAME] %>" value="<%=appUser.getFullName()%>" class="formElemen form-control" size="40">
							 &nbsp;<%= frmAppUser.getErrorMsg(frmAppUser.FRM_FULL_NAME) %>    
						    </div>
						</div>
					    </div>
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							<%=textListTitleHeader[SESS_LANGUAGE][5]%> 
						    </div>
						    <div class="col-md-4">
							<input type="text" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_EMAIL] %>" value="<%=appUser.getEmail()%>" size="48" class="formElemen form-control">
							&nbsp;<%= frmAppUser.getErrorMsg(frmAppUser.FRM_EMAIL) %>    
						    </div>
						</div>
					    </div>
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							<%=textListTitleHeader[SESS_LANGUAGE][6]%> 
						    </div>
						    <div class="col-md-4">
							<textarea name="<%=frmAppUser.fieldNames[frmAppUser.FRM_DESCRIPTION] %>" cols="48" rows="3" class="formElemen form-control"><%=appUser.getDescription()%></textarea>
							&nbsp;<%= frmAppUser.getErrorMsg(frmAppUser.FRM_DESCRIPTION) %>    
						    </div>
						</div>
					    </div>
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							<%=textListTitleHeader[SESS_LANGUAGE][7]%> 
						    </div>
						    <div class="col-md-4">
							<%
							    ControlCombo cmbox = new ControlCombo();
							    Vector sts = AppUser.getStatusTxts();
							    Vector stsVals = AppUser.getStatusVals();
							%>
							<%=cmbox.draw(frmAppUser.fieldNames[frmAppUser.FRM_USER_STATUS] ,"formElemen form-control",
							    null, Integer.toString(appUser.getUserStatus()), stsVals, sts)%> &nbsp;<%= frmAppUser.getErrorMsg(frmAppUser.FRM_USER_STATUS) %>    
						    </div>
						</div>
					    </div>
                                                            <%--
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							<%=textListTitleHeader[SESS_LANGUAGE][8]%> 
						    </div>
						    <div class="col-md-4">
							<%
							    ControlCombo cmbox1 = new ControlCombo();
							    Vector sts1 = PstAppUser.listUserGroupNewTypeKey();
							    Vector stsVals1 = PstAppUser.listUserGroupTypeValue();
							%>
							<input type="hidden" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_USER_GROUP_NEW] %>" value="0" size="48" class="formElemen form-control">
						    </div>
						</div>
					    </div>--%>
                                            <input type="hidden" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_USER_GROUP_NEW] %>" value="0" size="48" class="formElemen form-control">                
					    <div class="row">
						&nbsp;
					    </div>
					    <div class="row">
						<div class="col-md-12">
						    <div class="col-md-3">
							Group Assigned
						    </div>
						    <div class="col-md-4">
							<%=ctrCheckBox(appUserOID)%>    
						    </div>
						</div>
					    </div>
					</td>
                                    </tr>
                                    <tr>
                                      <td width="14%" valign="top" height="14" nowrap>&nbsp;</td>
                                      <td width="2%" height="14">&nbsp;</td>
                                      <td width="84%" height="14">&nbsp;</td>
                                    </tr>
                                    <tr>
                                      <td colspan="3" class="command">
                                        <%
                                        ctrLine.initDefault();
                                        ctrLine.setLocationImg(approot+"/images");
                                        ctrLine.initDefault(SESS_LANGUAGE,textListTitle[SESS_LANGUAGE][0]);
                                        ctrLine.setTableWidth("80%");
                                        ctrLine.setSaveImageAlt(ctrLine.getCommand(SESS_LANGUAGE,textListTitle[SESS_LANGUAGE][0],ctrLine.CMD_SAVE,true));
                                        ctrLine.setBackImageAlt(SESS_LANGUAGE==com.dimata.util.lang.I_Language.LANGUAGE_DEFAULT ? ctrLine.getCommand(SESS_LANGUAGE,textListTitle[SESS_LANGUAGE][0],ctrLine.CMD_BACK,true) : ctrLine.getCommand(SESS_LANGUAGE,textListTitle[SESS_LANGUAGE][0],ctrLine.CMD_BACK,true)+" List");
                                        ctrLine.setDeleteImageAlt(ctrLine.getCommand(SESS_LANGUAGE,textListTitle[SESS_LANGUAGE][0],ctrLine.CMD_ASK,true));
                                        ctrLine.setEditImageAlt(ctrLine.getCommand(SESS_LANGUAGE,textListTitle[SESS_LANGUAGE][0],ctrLine.CMD_CANCEL,false));

                                        //ctrLine.setLocationImg(approot+"/images");
                                        //ctrLine.initDefault();
                                        //ctrLine.initDefault(SESS_LANGUAGE,"Pemakai");
                                        //ctrLine.setTableWidth("100%");
                                        String scomDel = "javascript:cmdDelete('"+appUserOID+"')";
                                        String sconDelCom = "javascript:cmdConfirmDelete('"+appUserOID+"')";
                                        String scancel = "javascript:cmdCancel('"+appUserOID+"')";
                                        ctrLine.setBackCaption(textListTitleHeader[SESS_LANGUAGE][11]);
                                        ctrLine.setCommandStyle("command");
                                        ctrLine.setColCommStyle("command");
                                        ctrLine.setSaveCaption(textListTitleHeader[SESS_LANGUAGE][9]);
                                        ctrLine.setDeleteCaption(textListTitleHeader[SESS_LANGUAGE][10]);
                                        //ctrLine.setAddCaption("");

                                        if (privDelete){
                                                ctrLine.setConfirmDelCommand(sconDelCom);
                                                ctrLine.setDeleteCommand(scomDel);
                                                ctrLine.setEditCommand(scancel);
                                        }else{
                                                //ctrLine.setConfirmDelCaption("");
                                                //ctrLine.setDeleteCaption("");
                                                //ctrLine.setEditCaption("");
                                        }

                                        if(privAdd == false  && privUpdate == false){
                                                // ctrLine.setSaveCaption("");
                                        }

                                        if (privAdd == false){
                                                //ctrLine.setAddCaption("");
                                        }

                                        // System.out.println("akjsdjfahjsdhfjdskfaksdf");
                                        //  System.out.println("iCommand :"+iCommand);
                                        System.out.println("excCode :"+excCode);
                                        // System.out.println("msgString :"+msgString);
                                                                            %>
                                     <!--%= ctrLine.drawImage(Command.ADD, excCode, msgString)%-->
					<button class="btn btn-success" type="button" onclick="javascript:cmdSave()"><i class="fa fa-check"></i> <%= ctrLine.getCommand(SESS_LANGUAGE,textListTitle[SESS_LANGUAGE][0],ctrLine.CMD_SAVE,true)%></button>
					<button class="btn btn-primary" type="button" onclick="javascript:cmdBack()"><i class="fa fa-arrow-left"></i> <%= ctrLine.getCommand(SESS_LANGUAGE,textListTitle[SESS_LANGUAGE][0],ctrLine.CMD_BACK,true)%></button>
				      </td>
                                    </tr>
                                    <%} else {%>
                                    <tr>
                                      <td colspan="3">
					  &nbsp; <%=textListTitleHeader[SESS_LANGUAGE][10]%> &nbsp;
                                        <a href="javascript:cmdBack()">click here</a>
                                        <script language="JavaScript">
                                            cmdBack();
                                        </script>
                                      </td>
                                    </tr>
                                    <% }
                                        %>
                                    <tr>
                                      <td width="14%">&nbsp;</td>
                                      <td width="2%">&nbsp;</td>
                                      <td width="84%">&nbsp;</td>
                                    </tr>
                                  </table>
                                </form>
                         </div>
                     </div>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <!-- jQuery 2.0.2 -->
        <%@include file="../../template-component/plugins-component.jsp" %>
	<script type="text/javascript">
	    $(document).ready(function(){
		//SET ACTIVE MENU
		var activeMenu = function(parentId, childId){
		    $(parentId).addClass("active").find(".treeview-menu").slideDown();
		    $(childId).addClass("active");
		}

		activeMenu("#system", "#masteruser");
	    })
	</script>
    </body>
</html>