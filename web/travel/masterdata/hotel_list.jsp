<!DOCTYPE html>
<%@page import="com.dimata.travel.entity.hotel.Hotel"%>
<%@page import="com.dimata.travel.form.hotel.FrmHotel"%>
<%@page import="com.dimata.travel.form.hotel.CtrlHotel"%>
<%@page import="com.dimata.travel.entity.hotel.PstHotel"%>
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

<%!
    public String drawList(int iCommand, Vector objectClass, long gradeId, FrmHotel frmObject) {
        
        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("No", "200px");
        ctrlist.addHeader("Hotel Code", "200px");
        ctrlist.addHeader("Hotel Name", "200px");
        ctrlist.addHeader("Action", "200px");
        //ctrlist.addHeader("", "200px"); 

        if (iCommand != Command.EDIT) {
            //membuat link dirow 0
            ctrlist.setLinkRow(0);
        }
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();

        //ctrlist.setLinkPrefix("javascript:cmdEdit('");

        //ctrlist.setLinkSufix("')");

        ctrlist.reset();

        int index = -1;

        Vector rowx = new Vector(1, 1);
        int count =0;
        for (int i = 0; i < objectClass.size(); i++) {
            Hotel hotel = (Hotel) objectClass.get(i);

            rowx = new Vector(1, 1);
            count=count+1;
            rowx.add(""+count);
            rowx.add(""+hotel.getCode());
            rowx.add(""+hotel.getName());
            rowx.add("<center><button type=\"button\" class=\"btn btn-success\" onclick=\"javascript:cmdEdit('" + hotel.getOID() + "','"+hotel.getCode()+"','"+hotel.getName()+"')\"></i> Edit </button></center>");
            lstData.add(rowx);
        }
        return ctrlist.drawBootstrapStrip();
    }
%>

<% //proses yang tidak akan ditampilkan hanya diserver side

    int iCommand = FRMQueryString.requestCommand(request);
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    long oidHotel = FRMQueryString.requestLong(request, "hidden_hotel_id");
    String gradeDelete = FRMQueryString.requestString(request, "gradeDelete");

    /*variable declaration*/

    int recordToGet = 15;
    String msgString = "";
    int iErrCode = FRMMessage.NONE;
    String whereClause = "";
    String orderClause = PstHotel.fieldNames[PstHotel.FLD_ID];
    CtrlHotel ctrlHotel = new CtrlHotel(request);
    ControlLine ctrLine = new ControlLine();
    Vector listGuide = new Vector(1, 1);

    /*switch statement */

    iErrCode = ctrlHotel.action(iCommand, oidHotel);

    FrmHotel frmHotel = ctrlHotel.getForm();

    //membuat command menjadi none kembali setelah menjalankan delete all
    if (iCommand == Command.DELETEALL && iErrCode == FRMMessage.NONE) {
        iCommand = Command.NONE;
    }

    /* end switch*/

    int vectSize = PstHotel.getCount(whereClause);

    /*switch list Student*/

    if (iCommand == Command.FIRST || iCommand == Command.NEXT || iCommand == Command.PREV || iCommand == Command.LAST) {
        start = 0;//ctrlHotel.action(iCommand, start, vectSize, recordToGet);
    }

    /* end switch list*/

    Hotel hotel = ctrlHotel.getHotel();
    msgString = ctrlHotel.getMessage();

    /* get record to display */

    listGuide = PstHotel.listAll();
%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Data Tables</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
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
             function cmdAdd(){
                document.hotelList.command.value="<%=Command.ADD%>";
                document.hotelList.hidden_hotel_id.value="0";
                document.hotelList.<%=frmHotel.fieldNames[frmHotel.FRM_FIELD_CODE]%>.value="";
                document.hotelList.<%=frmHotel.fieldNames[frmHotel.FRM_FIELD_NAME]%>.value="";
                document.getElementById('togglee').style.visibility = 'hidden';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdEdit(oid,code,name){
                document.hotelList.command.value="<%=Command.ADD%>";
                document.hotelList.hidden_hotel_id.value=oid;
                document.hotelList.<%=frmHotel.fieldNames[frmHotel.FRM_FIELD_CODE]%>.value=code;
                document.hotelList.<%=frmHotel.fieldNames[frmHotel.FRM_FIELD_NAME]%>.value=name;
                document.getElementById('togglee').style.visibility = 'visible';
                $(document).ready(function(){
                    $('#compose-modal').modal('show');
                });
            }
            
            function cmdDelete()
            {
                if (confirm('Yakin Menghapus Data Hotel ? ')) {
                    document.hotelList.command.value="<%=Command.DELETE%>";
                    document.hotelList.action="hotel_list.jsp";
                    document.hotelList.submit();
                }
               
            }
            function cmdSave(){
                var oidDriver = document.hotelList.hidden_hotel_id.value;
                if(oidDriver!=0){
                    document.hotelList.command.value="<%=Command.SAVE%>";
                    //document.hotelList.action="hotel_list.jsp";
                    //document.hotelList.submit();
                }else{
                    document.hotelList.command.value="<%=Command.SAVE%>";
                    document.hotelList.hidden_hotel_id.value="0";
                    //document.hotelList.action="hotel_list.jsp";
                    //document.hotelList.submit();
                }
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
                        Tables
                        <small>Hotel List</small>
                    </h1>
                    <!--<ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Tables</a></li>
                        <li class="active">Data tables</li>
                    </ol> -->
                </section>
                <!-- Main content -->
                <section class="content">
                    <form name="hotelList"  method ="post" action="" role="form">
                        <input type="hidden" name="command" value="<%=iCommand%>">
                        <input type="hidden" name="hidden_hotel_id" value="<%=oidHotel%>">  
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                     <div class="box-header">
                                        &nbsp;
                                    </div>
                                    <div class="box-header">
                                        <div class="col-xs-2">
                                        </div>
                                        <div class="col-xs-2">
                                        </div>
                                        <div class="col-xs-8">
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary pull-right">Add New</button>
                                        </div>
                                    </div>
                                    <%=drawList(iCommand, listGuide, oidHotel, frmHotel)%>
                                </div><!-- /.box -->
                            </div>
                        </div>
                        <div class="row">
                        <div class="col-md-12">
                            <div class="box-footer">
                                <%
                                    //jika command == none -> munculkan button add dan delete
                                    // jika command == add atau command == edit -> munculkan button save dan cancel
                                    //jika command == cancel maka munculkan button add dan delete
                                    // jika command == save dan eror ==0 -> maka munculkan command add dan hide command save
                                    // jika command == delete -> maka munculkan command save
                                        if(iCommand==Command.NONE){ %>
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                        <%} else if(iCommand==Command.ADD || iCommand==Command.EDIT){%>
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                        <%} else if(iCommand==Command.CANCEL && iCommand==Command.NONE) {%>
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                        <%} else if(iCommand==Command.SAVE && iErrCode!=0) {%>    
                                             <div class="alert alert-success alert-dismissable">
                                                <i class="fa fa-check"></i>
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                                <b><%=msgString%></b>
                                            </div>
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                        <%} else if(iCommand==Command.SAVE && iErrCode==0) {%>
                                            <div class="alert alert-success alert-dismissable">
                                                <i class="fa fa-check"></i>
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                                <b><%=msgString%></b>
                                            </div>
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                        <%} else if(iCommand==Command.DELETE) {%>
                                            <div class="alert alert-danger alert-dismissable">
                                                <i class="fa fa-ban"></i>
                                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                                <b><%=msgString%></b>
                                            </div>
                                            <button  onclick="javascript:cmdAdd()" type="button" class="btn btn-primary">Add New</button>
                                        <%}%>
                            </div>
                        </div>
                    </div> 
                      <div class="modal fade" id="compose-modal" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"></i>Input Hotel</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Hotel Code</span>
                                                    <input name="<%=frmHotel.fieldNames[frmHotel.FRM_FIELD_CODE]%>" type="text" class="form-control" placeholder="Hotel Code" required>
                                                </div>
                                            </div>  
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <span class="input-group-addon">Hotel Name</span>
                                                    <input name="<%=frmHotel.fieldNames[frmHotel.FRM_FIELD_NAME]%>" type="text" class="form-control" placeholder="Hotel Name" required>
                                                </div>
                                            </div>   
                                        </div> 
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="modal-footer clearfix">
                                                <input type="submit" value="Save" class="btn btn-primary pull-left" onclick="javascript:cmdSave()" >
                                                <button type="button" class="btn btn-danger pull-left" onclick="javascript:cmdDelete()" id="togglee"></i> Delete</button>
                                            </div>
                                        </div>   
                                    </div> 
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->        
                    </form>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
         <script src="../../styles/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="../../styles/bootstrap3.1/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- DATA TABES SCRIPT -->
        <script src="../../styles/bootstrap3.1/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="../../styles/bootstrap3.1/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="../../styles/bootstrap3.1/js/AdminLTE/app.js" type="text/javascript"></script>

        <!-- page script -->
        <script type="text/javascript">
            $(function() {
                $("#example1").dataTable();
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
    </body>
</html>