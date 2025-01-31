<%-- 
    Document   : home_marketing
    Created on : May 5, 2016, 11:23:02 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.dslik.entity.bankgaransi.BankGaransi"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.PstBankGaransi"%>
<%@page import="com.dimata.dslik.entity.kredit.Kredit"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="com.dimata.dslik.entity.debitur.Debitur"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<%@include file="../../main/checkuser_slik.jsp" %>
<%
    boolean privView = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PERLENGKAPAN_DATA, AppObjInfo.G2_PROSES_PERLENGKAPAN_DATA, AppObjInfo.OBJ_PROSES_PERLENGKAPAN_DATA, AppObjInfo.COMMAND_VIEW);
    boolean privUpdate = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PERLENGKAPAN_DATA, AppObjInfo.G2_PROSES_PERLENGKAPAN_DATA, AppObjInfo.OBJ_PROSES_PERLENGKAPAN_DATA, AppObjInfo.COMMAND_UPDATE);
    boolean privDelete = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PERLENGKAPAN_DATA, AppObjInfo.G2_PROSES_PERLENGKAPAN_DATA, AppObjInfo.OBJ_PROSES_PERLENGKAPAN_DATA, AppObjInfo.COMMAND_DELETE);
    boolean privAdd = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PERLENGKAPAN_DATA, AppObjInfo.G2_PROSES_PERLENGKAPAN_DATA, AppObjInfo.OBJ_PROSES_PERLENGKAPAN_DATA, AppObjInfo.COMMAND_ADD);
    boolean privDownload = userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_PERLENGKAPAN_DATA, AppObjInfo.G2_PROSES_PERLENGKAPAN_DATA, AppObjInfo.OBJ_PROSES_PERLENGKAPAN_DATA, AppObjInfo.COMMAND_DOWNLOAD);
%>
<%
    
    int iCommand = FRMQueryString.requestCommand(request);
    long oid = FRMQueryString.requestLong(request, "oid");
    int type = FRMQueryString.requestInt(request, "type");
    String cif = FRMQueryString.requestString(request, "cif");
    String activeTab = FRMQueryString.requestString(request, "activetab");
    long otherOid = FRMQueryString.requestLong(request, "otheroid");
    String notif = "";
    if(type != 1 && cif.length() > 0){
        //CEK PENGURUS
        int count = PstPengurusAtauPemilik.getCount(""+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"='"+cif+"'");
        if(count == 0){
           notif ="<div class='col-md-4 notifications bottom-right' id='notifpengurus'>"
           + "</div>";
        }
    }
    String contentHeader = ""
    + "<li>"
	+ "Perlengkapan Data"
    + "</li>";
    
    if(iCommand == Command.EDIT){
	contentHeader = ""
	+ "<li>"
	    + "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp'>Perlengkapan Data</a>"
	+ "</li>"
	+ "<li>"
	    + "Edit Perlengkapan Data"
	+ "</li>";
    }else if(iCommand == Command.VIEW){
	contentHeader = ""
	+ "<li>"
	    + "<a href='"+approot+"/dslik/perlengkapandata/perlengkapandata.jsp'>Perlengkapan Data</a>"
	+ "</li>"
	+ "<li>"
	    + "View Perlengkapan Data"
	+ "</li>";
    }
    
    
    //TYPE 1 = INDIVIDU
    //TYPE 2 = BADAN USAHA
    Vector listTabName = new Vector();
    Vector listTabTarget = new Vector();
    Vector listColor = new Vector();
    Vector tipePencarianKey = new Vector();
    Vector tipePencarianVal = new Vector();
    //TAB COLOR 
    listColor.add("background-color:#02b8fa;");
    listColor.add("background-color:#1a439b;");
    listColor.add("background-color:#88c426;");
    listColor.add("background-color:#bcd63f;");
    listColor.add("background-color:#d00405;");
    listColor.add("background-color:#ff6711;");
    listColor.add("background-color:#febf02;");
    listColor.add("background-color:#a737fb;");
    listColor.add("background-color:#f82b4c;");
    listColor.add("background-color:#b25c11;");
    listColor.add("background-color:#555555;");

    //TAB NAME
    if(type != 1){
        listTabName.add("Debitur Badan Usaha");
        listTabTarget.add("AjaxDebiturBadanUsaha");
        
        listTabName.add("Pengurus/Pemilik");
        listTabTarget.add("AjaxPengurusPemilik");
    }else{
        if(cif.equals("")){
            listTabName.add("Debitur");
        }else{
            listTabName.add("Debitur Individu");
        }
        listTabTarget.add("AjaxDebiturIndividu");
    }
    
    listTabName.add("Kredit");
    listTabTarget.add("AjaxKredit");

    listTabName.add("Bank Garansi");
    listTabTarget.add("AjaxBankGaransi");

    listTabName.add("Agunan");
    listTabTarget.add("AjaxAgunan");

    listTabName.add("Penjamin");
    listTabTarget.add("AjaxPenjamin");

    if(type != 1){
        //TAB NAME
        listTabName.add("Laporan Keuangan Debitur");
        //TAB TARGET
        listTabTarget.add("AjaxLaporanKeuanganDebitur");
    }    
    listTabName.add("Surat Berharga");
    listTabTarget.add("AjaxSuratBerharga");

    listTabName.add("Kredit Join");
    listTabTarget.add("AjaxKreditJoin");

    listTabName.add("Irrevocable L/C");
    listTabTarget.add("AjaxIrrevocable");

    listTabName.add("Fasilitas Lainnya");
    listTabTarget.add("AjaxFasilitasLainnya");
    
    
    
    tipePencarianVal.add("Debitur Individu");
    tipePencarianKey.add("AjaxDebiturIndividu");
    tipePencarianVal.add("Debitur Badan Usaha");
    tipePencarianKey.add("AjaxDebiturBadanUsaha");
    tipePencarianVal.add("Pengurus/Pemilik");
    tipePencarianKey.add("AjaxPengurusPemilik");
    tipePencarianVal.add("Kredit");
    tipePencarianKey.add("AjaxKredit");
    tipePencarianVal.add("Bank Garansi");
    tipePencarianKey.add("AjaxBankGaransi");
    tipePencarianVal.add("Agunan");
    tipePencarianKey.add("AjaxAgunan");
    tipePencarianVal.add("Penjamin");
    tipePencarianKey.add("AjaxPenjamin");
    tipePencarianVal.add("Laporan Keuangan Debitur");
    tipePencarianKey.add("AjaxLaporanKeuanganDebitur");
    tipePencarianVal.add("Surat Berharga");
    tipePencarianKey.add("AjaxSuratBerharga");
    tipePencarianVal.add("Kredit Join");
    tipePencarianKey.add("AjaxKreditJoin");
    tipePencarianVal.add("Irrevocable L/C");
    tipePencarianKey.add("AjaxIrrevocable");
    tipePencarianVal.add("Fasilitas Lainnya");
    tipePencarianKey.add("AjaxFasilitasLainnya");
%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>SLIK | PERLENGKAPAN DATA</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <%@include file="../../template-component/css-component.jsp" %>
    <style type="text/css">
	.active{
	    color : #444;
	}
	.ac
    </style>
     <script language="JavaScript">

          function cmdIndividu(){
              document.formperlengkapandatax.command.value="<%=Command.VIEW%>";
              document.formperlengkapandatax.action="perlengkapandata.jsp?command=8&oid=1&type=1";
              document.formperlengkapandatax.submit();
          }

          function cmdBadanUsaha(){
              document.formperlengkapandatax.command.value="<%=Command.VIEW%>";
              document.formperlengkapandatax.action="perlengkapandata.jsp?command=8&oid=1&type=2";
              document.formperlengkapandatax.submit();

          }
      </script>  
  </head>
  <body class="<%=skin%>">
    <div class="wrapper">
        <input type="hidden" name="usergroup" id="usergroup" value="<%= getUserGroup %>">
        <input type="hidden" name="kodecabang" id="kodecabang" value="<%= kd_bank %>">
        <input type="hidden" name="otheroid" id="otheroid" value="<%= otherOid %>">
        <input type="hidden" name="activetab" id="activetab" value="<%= activeTab %>">
        <input type="hidden" name="privupdate" id="privupdate" value="<%= privUpdate %>">
        <input type="hidden" name="privdelete" id="privdelete" value="<%= privDelete %>">
        <input type="hidden" name="privview" id="privview" value="<%= privView %>">
        <input type="hidden" name="privadd" id="privadd" value="<%= privAdd %>">
	<%@include file="../../template-component/header-component.jsp" %>
        <%@include file="../../template-component/sidebar-component.jsp" %>
        <!--script type="text/javascript" src="../../styles/numberformat.js"></script-->
       
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Perlengkapan Data
          </h1>
          <ol class="breadcrumb">
            <li><a href="<%= approot %>/home_slik.jsp"><i class="fa fa-home"></i> Home</a></li>
	    <li>Modul Perlengkapan Data</li>
	    <%= contentHeader %>
          </ol>
        </section>

        <!-- Main content -->
	<%
            if(privView){
        %>
        <form id="formperlengkapandata">
	    <input type="hidden" name="command" id="command" value="<%= Command.SAVE %>">
            
            <input type="hidden" name="type" id="type" value="<%= type %>">
            <input type="hidden" id="servletdestination">
            <input type="hidden" name="FRM_FIELD_DATA_FOR" id="dataforperlengkapan">
            <input type="hidden" name="FRM_FIELD_OID" id="oid" value="<%= oid %>">
            <input type="hidden" name="FRM_FIELD_OID_KREDIT" id="oidkredit">
            <input type="hidden" name="FRM_FIELD_OID_BANK_GARANSI" id="oidBankGaransi">
            <input type="hidden" name ="FRM_FIELD_CIF_RETURN" id="cif" value="<%= cif %>">
            <input type="hidden" name ="FRM_FIELD_AGUNAN_OID" id="oidAgunan">
            <input type="hidden" name ="FRM_FIELD_PENJAMIN_OID" id="oidPenjamin">
            <input type="hidden" name ="FRM_FIELD_SURAT_BERHARGA_OID" id="oidSuratBerharga">
            <input type="hidden" name ="FRM_FIELD_KREDIT_JOIN_OID" id="oidKreditJoin">
            <input type="hidden" name ="FRM_FIELD_IRREVOCABLE_OID" id="oidIrrevocable">
            <input type="hidden" name ="FRM_FIELD_FASILITAS_OID" id="oidFasilitas">
            <input type="hidden" name ="FRM_FIELD_PENGURUS_OID" id="oidPengurus">
            <input type="hidden" name ="FRM_FIELD_LAPORAN_OID" id="oidLaporan">
            <input type="hidden" name="useroid" id="useroid" value="<%= userId%>">
            <input type="hidden" name="username" id="username" value="<%= userName %>">
            <input type="hidden" name="editSwap" id="editSwap" value="0">
	    <section class="content">
	      <!-- Small boxes (Stat box) -->
	      <div class="row">
		<div class="col-md-12">
		    
		    <%
			if(iCommand == Command.NONE){
			    %>
				<div class='box box-primary'>
				    <div class="box-body">
					<div class="row">
					    <div class="col-md-12">
						<div id="perlengkapanDataElement">
						    <table class="table table-bordered table-striped">
							<thead>
							    <tr>
								<td>No.</td>
								<td>NIK</td>
								<td>Nama Identitas</td>
								<td>Jenis Kelamin</td>
								<td>NPWP</td>
								<td>Alamat</td>
								<td>Telp / Hp</td>
								<td>Email</td>
								<td>Aksi</td>
							    </tr>
							</thead>
						    </table>
						</div>
					    </div>
					</div>
				    </div>
				</div>
			    <%
			}else{
			    
			    %>
				<!-- Custom Tabs -->
				
				<div class="row">
				    <div class="col-md-12">
					<button type="button" class="btn btn-primary pull-right" id="searchdebitur" data-command="<%= Command.NONE %>" data-target="AjaxPerlengkapanData" data-for="searchform"><i class="fa fa-search"></i> Pencarian Data Per Segment</button><br><br>
				    </div>
				</div>
				<div class="row">
				    <div class="col-md-12">
					<div class="nav-tabs-custom">
					    <ul class="nav nav-tabs" id="tabmenu">
					      <%
						  String tabs = "";
						  String tabsOther = "";
						  if(listTabName != null){
						      for(int i = 0; i < listTabName.size();i++){
							  String data = (String) listTabName.get(i);
							  String target = (String) listTabTarget.get(i);
							  String color = (String) listColor.get(i);
							  String active = "";
							  String aStyle = "style='color:white;'";
                                                          if(activeTab.length() > 0){
                                                              if(activeTab.equals(listTabTarget.get(i))){
                                                                  active = "active";
                                                                  aStyle ="";
                                                              }
                                                          }else{
                                                              if(i == 0){
                                                                active = "active";
                                                                aStyle ="";
                                                            }
                                                          }
							  



							  /*if(i > 7){
							      tabsOther+=""
							      + "<li role='presentation'>"
								  + "<a role='menuitem' tabindex='-1' href='#' data-target='"+target+"' class='tabmenu'>"+data+"</a>"
							      + "</li>";
							  }else{*/
							      tabs +=""
							      + "<li class='"+active+" menutab' style='cursor:pointer;margin-right:0px;"+color+"' data-target='"+target+"' data-for='showform' data-oid='"+i+" data-command='"+Command.NONE+"' data-for-save=''>"
								  + "<a hre='#' data-toggle='tab' class='tabmenu' "+aStyle+">"+data+"</a>"
							      + "</li>";
							  /*}*/
						      }
						      if(tabsOther.length() > 0){
							  tabsOther=""
							  + "<li class='dropdown'>"
							      + "<a class='dropdown-toggle' data-toggle='dropdown' href='#'>"
								  + "Lainnya <span class='caret'></span>"
							      + "</a>"
							      + "<ul class='dropdown-menu'>"
								  + tabsOther
							      + "</ul>"
							  + "</li>";
						      }
						  }
					      %>
					      <%= tabs+""+tabsOther %>
					    </ul>
					    <div class="tab-content">
                                                <div class="tab-pane active" id="tabscontent" style="background-color:#f1f1f1;"></div><!-- /.tab-pane -->
                                                <div class="row" style="margin-top:10px;">
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <%
                                                                if(privAdd || privUpdate){
                                                                    %>
                                                                        <button type="submit" class="btn btn-primary" id="btnSave"><i class="fa fa-save"></i> Simpan</button>
                                                                    <%
                                                                }
                                                                
                                                                if(privDownload && !cif.equals("")){
                                                                    %>
                                                                        <button type="button" class="btn btn-info " id="btndownload"><i class="fa fa-download"></i> Download Data</button>
                                                                    <%
                                                                }
                                                                
                                                                if(privDelete && !cif.equals("")){
                                                                    Vector vNoRekening  = PstKredit.list(0, 0, PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"' AND "+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periode.getOID()+"'", ""+PstKredit.fieldNames[PstKredit.FLD_TGL_MULAI]+" ASC ");
                                                                    String noRekening="";
                                                                    if(vNoRekening.size()>0){
                                                                         for (int v = 0; v < vNoRekening.size(); v++) {
                                                                             Kredit entKredit =  (Kredit) vNoRekening.get(v);
                                                                             noRekening = noRekening+" "+entKredit.getNoRekening();
                                                                         }
                                                                    }

                                                                    Vector vBankGaransi  = PstBankGaransi.list(0, 0, PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"' AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periode.getOID()+"'", ""+PstBankGaransi.fieldNames[PstBankGaransi.FLD_TGL_DITERBITKAN]+" ASC ");
                                                                    if(vBankGaransi.size()>0){
                                                                         for (int v = 0; v < vBankGaransi.size(); v++) {
                                                                             BankGaransi bankGaransi =  (BankGaransi) vBankGaransi.get(v);
                                                                             noRekening = noRekening+" "+bankGaransi.getNoRekening();
                                                                         }
                                                                    }
                                                                    if(noRekening.equals("")){
                                                                    %>
                                                                            <button type="button" class="btn btn-danger" id="btndelete"><i class="fa fa-save"></i> Hapus</button>
                                                                    <%
                                                                    }
                                                                    
                                                                }
                                                            %>
                                                            <button type="button" class="btn btn-info pull-right" id="openhistory" data-command="<%= Command.LIST %>" data-target="AjaxPerlengkapanData" data-for="searchhistory"><i class="fa fa-history"></i> History Perubahan Data</button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row" style="margin-top:10px;">
                                                    <div class="col-md-12">
                                                            <b>Catatan:</b><br>
                                                             <i style="font-weight:bold;color: red;">*</i> = Data dapat diupdate di sistem core/Data diambil di Core<br>
                                                             <i style="font-weight:bold;color: red;">**</i> = Data diambil di SID<br>
                                                             <i style="font-weight:bold;color: red;">m</i> = Mandatory<br>
                                                             <i style="font-weight:bold;color: red;">c</i> = Conditional<br>
                                                             <i style="font-weight:bold;color: red;">o</i> = Optional
                                                    </div> 
                                                    <div class="col-md-12" id="panelForDetailTabel">
                                                    </div>
                                                </div>
					    </div><!-- /.tab-content -->
					  </div><!-- nav-tabs-custom -->
				    </div>
				</div>
				
			    <%
			}
		    %>
		</div><!-- ./col -->
	      </div><!-- /.row -->

	    </section><!-- /.content -->
	</form>
        <%
            }
        %>
      </div><!-- /.content-wrapper -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class='control-sidebar-bg'></div>
      <div id="searchmodal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-lg">
	    <div class="modal-content">
		<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h4 class="modal-title"></h4>
		</div>
		<form id="generalform">
		    <input type="hidden" name="FRM_FIELD_DATA_FOR" id="datafor">
		    <input type="hidden" name="command" value="<%= Command.SAVE %>">
		    <input type="hidden" name="FRM_FIELD_OID" id="oid">
		    <div class="modal-body ">
			<div class="row">
                            <div class="col-md-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-2"> Pencarian untuk </label>
                                        <div class="col-sm-10">
                                            <%= ControlCombo.draw("tipepencarian", null, "", tipePencarianKey, tipePencarianVal, "", "form-control") %>
                                        </div>
                                    </div>
                                </div>
                                <hr style="border:2px solid #ccc;">
                            </div>
                        </div>
			<div class="row">
			    <div class="col-md-12">
				<div class="box-body addeditgeneral-body">
				    
				</div>
			    </div>
			</div>
		    </div>
		    <div class="modal-footer">
			<button type="button" class="btn btn-primary" id="btngeneralform" data-dismiss="modal"><i class="fa fa-check"></i> Select </button>
			<button type="button" data-dismiss="modal" class="btn btn-danger"><i class="fa fa-ban"></i> Tutup</button>
		    </div>
		</form>
	    </div>
	</div>
    </div>
                    
     <div id="openhistorydata" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-lg">
	    <div class="modal-content">
		<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    <h4 class="modal-title"></h4>
		</div>
		<form id="generalhistoryform">
		    <input type="hidden" name="FRM_FIELD_DATA_HISTORY" id="datafor">
		    <input type="hidden" name="command" value="<%= Command.SAVE %>">
                    
		    <input type="hidden" name="FRM_FIELD_OID" id="oid">
		    <div class="modal-body ">
			<div class="row">
			    <div class="col-md-12">
				<div class="box-body addeditgeneral-body">
                                    <div id="historyTableElement">
                                        <table class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <td>No.</td>
                                                    <td>Tanggal</td>
                                                    <td>User</td>
                                                    <td>Tipe Perubahan</td>
                                                    <td>Detail Perubahan</td>
                                                </tr>
                                            </thead>                                           
                                        </table>
                                    </div>
				</div>
			    </div>
			</div>
		    </div>
		</form>
	    </div>
	</div>
    </div>               
    
      <%@include file="../../template-component/footer-component.jsp" %>
      
      <%@include file="../../template-component/plugins-component.jsp" %>
      <script type="text/javascript">
	$(document).ready(function(){
	    
	    var approot = "<%= approot %>";
	    var otheroid = $("#otheroid").val();
            var activetab = $("#activetab").val();
	    //SET ACTIVE MENU
	    var activeMenu = function(parentId, childId){
		$(parentId).addClass("active").find(".treeview-menu").slideDown();
		$(childId).addClass("active");
	    };
            
            var setData = function(otheroid, activetab){
                $("#otheroid").val(otheroid);
                $("#avtivetab").val(activetab);
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
	    
            function getDatFilter(servletName){
                switch(servletName){
                    case "AjaxDebiturIndividu" :
                        var cif = $("#searchmodal #cif").val();
                        var npwp = $("#searchmodal #npwp").val();
                        var nama = $("#searchmodal #nama").val();
                        var type = $("#searchmodal #type").val();
                        return "&cif="+cif+"&npwp="+npwp+"&nama="+nama+"&type="+type;
                    break;
                    case "AjaxDebiturBadanUsaha" :
                        var cif = $("#searchmodal #cif").val();
                        var noakteawal = $("#searchmodal #noakteawal").val();
                        var nama = $("#searchmodal #nama").val();
                        var type = $("#searchmodal #type").val();
                        return "&cif="+cif+"&noakteawal="+noakteawal+"&nama="+nama+"&type="+type;
                    break;
                    case "AjaxPengurusPemilik" :
                        var cif = $("#searchmodal #cif").val();
                        var nama = $("#searchmodal #nama").val();
                        var alamat = $("#searchmodal #alamat").val();
                        return "&cif="+cif+"&nama="+nama+"&alamat="+alamat;
                    break;
                    case "AjaxKredit" :
                        var cif = $("#searchmodal #cif").val();
                        var norekening = $("#searchmodal #norekening").val();
                        var kodejeniskredit = $("#searchmodal #kodejeniskredit").val();
                        return "&cif="+cif+"&norekening="+norekening+"&kodejeniskredit="+kodejeniskredit;
                    break;
                    case "AjaxBankGaransi" :
                        var cif = $("#searchmodal #cif").val();
                        var norekening = $("#searchmodal #norekening").val();
                        var kodejenisgaransi = $("#searchmodal #kodejenisgaransi").val();
                        return "&cif="+cif+"&norekening="+norekening+"&kodejenisgaransi="+kodejenisgaransi;
                    break;
                    case "AjaxAgunan" :
                        var cif = $("#searchmodal #cif").val();
                        var nama = $("#searchmodal #nama").val();
                        var alamat = $("#searchmodal #alamat").val();
                        return "&cif="+cif+"&nama="+nama+"&alamat="+alamat;
                    break;
                    case "AjaxPenjamin" :
                        var cif = $("#searchmodal #cif").val();
                        var noidentitas = $("#searchmodal #noidentitas").val();
                        var alamat = $("#searchmodal #alamat").val();
                        return "&cif="+cif+"&noidentitas="+noidentitas+"&alamat="+alamat;
                    break;
                    case "AjaxLaporanKeuanganDebitur" :
                        var cif = $("#searchmodal #cif").val();
                        var aset = $("#searchmodal #aset").val();
                        var pinjamanjangkapanjang = $("#searchmodal #pinjamanjangkapanjang").val();
                        return "&cif="+cif+"&aset="+aset+"&pinjamanjangkapanjang="+pinjamanjangkapanjang;
                    break;
                    case "AjaxSuratBerharga" :
                        var cif = $("#searchmodal #cif").val();
                        var norekening = $("#searchmodal #norekening").val();
                        var kodejenissuratberharga = $("#searchmodal #kodejenissuratberharga").val();
                        return "&cif="+cif+"&norekening="+norekening+"&kodejenissuratberharga="+kodejenissuratberharga;
                    break;
                    case "AjaxKreditJoin" :
                        var cif = $("#searchmodal #cif").val();
                        var norekening = $("#searchmodal #norekening").val();
                        var kodejeniskredit = $("#searchmodal #kodejeniskredit").val();
                        return "&cif="+cif+"&norekening="+norekening+"&kodejeniskredit="+kodejeniskredit;
                    break;
                    case "AjaxIrrevocable" :
                        var cif = $("#searchmodal #cif").val();
                        var norekening = $("#searchmodal #norekening").val();
                        var kodejenislc = $("#searchmodal #kodejenislc").val();
                        return "&cif="+cif+"&norekening="+norekening+"&kodejenislc="+kodejenislc;
                    break;
                    case "AjaxFasilitasLainnya" :
                        var cif = $("#searchmodal #cif").val();
                        var norekening = $("#searchmodal #norekening").val();
                        var kodejenisfasilitas = $("#searchmodal #kodejenisfasilitas").val();
                        return "&cif="+cif+"&norekening="+norekening+"&kodejenisfasilitas="+kodejenisfasilitas;
                    break;
                default : 
                    return "";
                    
                }
            }
	    function dataTablesOptions(elementIdParent, elementId, servletName, dataFor, callBackDataTables){
                var oid = $("#formperlengkapandata #oid").val();
                var kodecabang = $("#kodecabang").val();
                var usergroup = $("#usergroup").val();
                var privUpdate = $("#privupdate").val();
                var privAdd = $("#privadd").val();
                var privDelete = $("#privdelete").val();
                var privView = $("#privview").val();
                //var approot = "<%= approot %>";
                
                if (dataFor==="searchhistory"){
                    
                    var tab = $("#servletdestination").val();
                    
                    switch (tab) {
                        case "AjaxDebiturIndividu":
                            oid = $("#formperlengkapandata #oid").val();
                        break;
                        case "AjaxDebiturBadanUsaha":
                            oid = $("#formperlengkapandata #oid").val();
                        break;
                        case "AjaxKredit":
                            oid = $("#formperlengkapandata #oidkredit").val();
                        break;
                        case "AjaxBankGaransi":
                            oid = $("#formperlengkapandata #oidBankGaransi").val();
                        break;
                        case "AjaxAgunan":
                            oid = $("#formperlengkapandata #oidAgunan").val();
                        break;
                        case "AjaxPenjamin":
                            oid = $("#formperlengkapandata #oidPenjamin").val();
                        break;
                        case "AjaxSuratBerharga":
                            oid = $("#formperlengkapandata #oidSuratBerharga").val();
                        break;
                        case "AjaxKreditJoin":
                            oid = $("#formperlengkapandata #oidKreditJoin").val();
                        break;
                        case "AjaxIrrevocable":
                            oid = $("#formperlengkapandata #oidIrrevocable").val();
                        break;
                        case "AjaxFasilitasLainnya":
                            oid = $("#formperlengkapandata #oidFasilitas").val();
                        break;
                        case "AjaxPengurusPemilik" :
                            oid = $("#formperlengkapandata #oidPengurus").val();   
                        break;
                        case "AjaxLaporanKeuanganDebitur" :
                            oid = $("#formperlengkapandata #oidLaporan").val();    
                        break;
                    }
                    //alert(oid);
                }
                var datafilter = getDatFilter(servletName);
                var cif = $("#formperlengkapandata #cif").val();
		$(elementIdParent).find('table').addClass('table-bordered table-striped table-hover').attr({'id':elementId});
		$("#"+elementId).dataTable({"bDestroy": true,
		    "iDisplayLength": 10,
		    "bProcessing" : true,
                    "order" : [[0, "desc"]],
		    "oLanguage" : {
			"sProcessing" : "<div class='progress progress-striped active'><div class='progress-bar progress-bar-primary' style='width: 100%'><b>Please Wait...</b></div></div>"
		    },
		    "bServerSide" : true,
		    "sAjaxSource" : "<%= approot %>/"+servletName+"?command=<%= Command.LIST%>&FRM_FIELD_DATA_FOR="+dataFor+"&FRM_FIELD_OID="+oid+""+datafilter+"&FRM_FIELD_CIF="+cif+"&usergroup="+usergroup+"&kodecabang="+kodecabang+"&privupdate="+privUpdate+"&privadd="+privAdd+"&privdelete="+privDelete+"&privview="+privView+"&FRM_FIELD_APPROOT=<%= approot %>",
		    aoColumnDefs: [
			{
			   bSortable: false,
			   aTargets: [ -1 ]
			}
		      ],
		    "initComplete": function(settings, json) {
			if(callBackDataTables != null){
			    callBackDataTables();
			}
		    },
		    "fnDrawCallback": function( oSettings ) {
			if(callBackDataTables != null){
			    callBackDataTables();
			}
		    },
		    "fnPageChange" : function(oSettings){

		    }
		});

		$(elementIdParent).find("#"+elementId+"_filter").find("input").addClass("form-control");
		$(elementIdParent).find("#"+elementId+"_length").find("select").addClass("form-control");
		$("#"+elementId).css("width","100%");
	    }
	    
	    function runDataTable(){
		dataTablesOptions("#perlengkapanDataElement", "tablePerlengkapanDataElement", "AjaxPerlengkapanData", "listperlengkapandata", null);
	    }
            
            
	    
	    function vieweditdata(tableElementId, elementId){
		$(tableElementId).on("click", elementId, function(){
		    var oid = $(this).data("oid");
		    var command = $(this).data("command");
		    var type = $(this).data("type");
                    var cif = $(this).data("cif");
                    var target = $(this).data("target");
                    var otherOid = $(this).data("otherOid");
                    window.location = "perlengkapandata.jsp?command=8&oid="+oid+"&type="+type+"&cif="+cif+"&activetab="+target+"&otheroid="+otherOid;
		});
	    }
	    
	    function tabMenu(selector){
		$(selector).click(function(){
                    setData("","");
		    var target = $(this).data("target");
		    var oid = $("#oid").val();
		    var datafor = $(this).data("for");
		    var command = $(this).data("command");
                    var privadd = $("#privadd").val();
                    var privupdate = $("#privupdate").val();
                    var privdelete = $("#privdelete").val();
                    var kodecabang = $("#kodecabang").val();
		    $(".menutab").find("a").attr("style", "color:white;");
                    $("#servletdestination").val(target);
                    $("#dataforperlengkapan").val(datafor);
                    if (target=="AjaxDebiturIndividu" || target=="AjaxDebiturBadanUsaha"){
                        $("#btnSave").show();
                        $("#btndelete").hide();
                    }else{
                        $("#btnSave").hide();
                        $("#btndelete").hide();
                    }
                    
                    if (target=="AjaxDebiturIndividu" || target=="AjaxDebiturBadanUsaha" || target=="AjaxKredit" || target=="AjaxBankGaransi" || target=="AjaxAgunan"){
                        $("#btndownload").show();
                    }else{
                        $("#btndownload").hide();
                    }
                    
		    $(this).find("a").removeAttr("style");
                    ///alert(getCif);
		    var dataSend = {
			"FRM_FIELD_OID" : oid,
			"FRM_FIELD_DATA_FOR" : datafor,
			"command" : command,
                        "privadd" : privadd,
                        "privupdate" : privupdate,
                        "privdelte" : privdelete,
                        "FRM_KODE_CABANG" : kodecabang
		    };
		    
		    var onDone = function(data){
                        
                    
                        if(oid != 0){
                            validation($("#formperlengkapandata"));
                        }
			datePicker(".datepicker","yyyy-mm-dd");
                        $("#panelForDetailTabel").html("");
                        switch (target) {
                            case "AjaxDebiturIndividu":
                                var cif = $("#FRM_FIELD_CIF").val();
                                if (cif.length>0){
                                    $("#panelForDetailTabel").html(""
                                        + "<div id='panelDetailForKredit'></div>"
                                        + "<div id='panelDetailForBankGaransi'></div>"
                                        + "<div id='panelDetailForAgunan'></div>"
                                        + "<div id='panelDetailForPenjamin'></div>"
                                        + "<div id='panelDetailForSuratBerharga'></div>"
                                        + "<div id='panelDetailForKreditJoin'></div>"
                                        + "<div id='panelDetailForIrrevocable'></div>"
                                        + "<div id='panelDetailForFasilitasLainnya'></div>"
                                    +"");
                                    loadDetailForTable("AjaxKredit", "showform", "<%= Command.NONE %>","#panelDetailForKredit");
                                    loadDetailForTable("AjaxBankGaransi", "showform", "<%= Command.NONE %>","#panelDetailForBankGaransi");
                                    loadDetailForTable("AjaxAgunan", "showform", "<%= Command.NONE %>","#panelDetailForAgunan");
                                    loadDetailForTable("AjaxPenjamin", "showform", "<%= Command.NONE %>","#panelDetailForPenjamin");
                                    loadDetailForTable("AjaxSuratBerharga", "showform", "<%= Command.NONE %>","#panelDetailForSuratBerharga");
                                    loadDetailForTable("AjaxKreditJoin", "showform", "<%= Command.NONE %>","#panelDetailForKreditJoin");
                                    loadDetailForTable("AjaxIrrevocable", "showform", "<%= Command.NONE %>","#panelDetailForIrrevocable");
                                    loadDetailForTable("AjaxFasilitasLainnya", "showform", "<%= Command.NONE %>","#panelDetailForFasilitasLainnya");
                                }
                            break;
                            case "AjaxDebiturBadanUsaha":
                                var cif = $("#FRM_FIELD_CIF").val();
                                if (cif.length>0){
                                    $("#panelForDetailTabel").html(""
                                        + "<div id='panelDetailForKredit'></div>"
                                        + "<div id='panelDetailForBankGaransi'></div>"
                                        + "<div id='panelDetailForAgunan'></div>"
                                        + "<div id='panelDetailForPenjamin'></div>"
                                        + "<div id='panelDetailForLaporanKeuanganDebitur'></div>"
                                        + "<div id='panelDetailForSuratBerharga'></div>"
                                        + "<div id='panelDetailForKreditJoin'></div>"
                                        + "<div id='panelDetailForIrrevocable'></div>"
                                        + "<div id='panelDetailForFasilitasLainnya'></div>"
                                    +"");
                                    loadDetailForTable("AjaxKredit", "showform", "<%= Command.NONE %>","#panelDetailForKredit");
                                    loadDetailForTable("AjaxBankGaransi", "showform", "<%= Command.NONE %>","#panelDetailForBankGaransi");
                                    loadDetailForTable("AjaxAgunan", "showform", "<%= Command.NONE %>","#panelDetailForAgunan");
                                    loadDetailForTable("AjaxPenjamin", "showform", "<%= Command.NONE %>","#panelDetailForPenjamin");
                                    loadDetailForTable("AjaxLaporanKeuanganDebitur", "showform", "<%= Command.NONE %>","#panelDetailForLaporanKeuanganDebitur");
                                    loadDetailForTable("AjaxSuratBerharga", "showform", "<%= Command.NONE %>","#panelDetailForSuratBerharga");
                                    loadDetailForTable("AjaxKreditJoin", "showform", "<%= Command.NONE %>","#panelDetailForKreditJoin");
                                    loadDetailForTable("AjaxIrrevocable", "showform", "<%= Command.NONE %>","#panelDetailForIrrevocable");
                                    loadDetailForTable("AjaxFasilitasLainnya", "showform", "<%= Command.NONE %>","#panelDetailForFasilitasLainnya");
                                }
                            break;
                            case "AjaxKredit":
                                datafor = "listkreditperlengkapandata";
                                dataTablesOptions("#tableListKreditElement", "tableKreditListElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralkredit");
                            break;
                            case "AjaxBankGaransi" :
                                datafor = "listdatabankgaransi";
                                dataTablesOptions("#tableListBankGaransi", "tableBankGaransiElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralgaransi");
                            break;
                            case "AjaxAgunan" :
                                datafor = "listdataagunan";
                                dataTablesOptions("#tableListAgunan", "tableListAgunanElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralagunan");
                            break;
                            case "AjaxPenjamin" :
                                datafor = "listdatapenjamin";
                                dataTablesOptions("#tableListPenjamin", "tableListPenjaminElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralpenjamin");
                            break;
                            case "AjaxSuratBerharga" :
                                datafor = "listdatasuratberharga";
                                dataTablesOptions("#tableListSuratBerharga", "tableListSuratBerhargaElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralsuratberharga");
                            break;
                            case "AjaxKreditJoin" :
                                datafor = "listkreditjoin";
                                dataTablesOptions("#tableKreditJoin", "tableKreditJoinElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralkreditjoin");
                            break;
                            case "AjaxIrrevocable" :
                                datafor = "listdatairrevocable";
                                dataTablesOptions("#tableIrrevocable", "tableIrrevocableElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgenerairrevocable");
                            break;
                            case "AjaxFasilitasLainnya" :
                                datafor = "listdatafasilitas";
                                dataTablesOptions("#tableFasilitas", "tableFasilitasElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralfasilitas");
                            break;
                            case "AjaxPengurusPemilik" :
                                datafor = "listdatapengurus";
                                dataTablesOptions("#tablePengurusElement", "tablePengurusElementElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralpengurus");
                            break;
                            case "AjaxLaporanKeuanganDebitur" :
                                datafor = "listdatalaporan";
                                dataTablesOptions("#tableLaporanKeuangan", "tableLaporanKeuanganElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgenerallaporan");
                            break;
                        }
                        if (target==="AjaxDebiturIndividu" || target ==="AjaxDebiturBadanUsaha"){
                            $("#openhistory").show();
                        }else{
                            $("#openhistory").hide();
                        }
                        deletegeneral("#delete", ".deletelist");
		    };
		    
		    var onSuccess = function(data){
		    };
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, target, "#tabscontent", false);
		});
	    }
            
            function loadDetailForTable(servletTarget, dataFor, command,appendTarget){
                var oid = $("#oid").val();
                var cif = $("#FRM_FIELD_CIF").val();
                var dataSend = {
                    "FRM_FIELD_OID" : oid,
                    "FRM_FIELD_DATA_FOR" : dataFor,
                    "FRM_FIELD_NEED_ADD" : "1",
                    "FRM_FIELD_CIF"      : cif,
                    "command" : command
                };
                var onSuccess = function(data){
                };
                var onDone = function(data){
                    switch (servletTarget) {
                        case "AjaxKredit":
                            var datafor = "listkreditperlengkapandatahome";
                            dataTablesOptions("#tableListKreditElement", "tableKreditListElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);
                        break;
                        case "AjaxBankGaransi" :
                            var datafor = "listdatabankgaransihome";
                            dataTablesOptions("#tableListBankGaransi", "tableBankGaransiElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);                      
                        break;
                        case "AjaxAgunan" :
                            var datafor = "listdataagunanhome";
                            dataTablesOptions("#tableListAgunan", "tableListAgunanElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);                         
                        break;
                        case "AjaxPenjamin" :
                            var datafor = "listdatapenjaminhome";
                            dataTablesOptions("#tableListPenjamin", "tableListPenjaminElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);
                        break;
                        case "AjaxSuratBerharga" :
                            var datafor = "listdatasuratberhargahome";
                            dataTablesOptions("#tableListSuratBerharga", "tableListSuratBerhargaElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);                          
                        break;
                        case "AjaxKreditJoin" :
                            var datafor = "listkreditjoinhome";
                            dataTablesOptions("#tableKreditJoin", "tableKreditJoinElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);
                        break;
                        case "AjaxIrrevocable" :
                            var datafor = "listdatairrevocablehome";
                            dataTablesOptions("#tableIrrevocable", "tableIrrevocableElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);                   
                        break;
                        case "AjaxFasilitasLainnya" :
                            var datafor = "listdatafasilitashome";
                            dataTablesOptions("#tableFasilitas", "tableFasilitasElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);
                        break;
                        case "AjaxPengurusPemilik" :
                            datafor = "listdatapengurushome";
                            dataTablesOptions("#tablePengurusElement", "tablePengurusElementElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable);                         
                        break;
                        case "AjaxLaporanKeuanganDebitur" :
                            datafor = "listdatalaporanhome";
                            dataTablesOptions("#tableLaporanKeuangan", "tableLaporanKeuanganElement", ""+servletTarget+"", ""+datafor+"", showFormNoModalForTable); 
                        break;
                    }
                    
                    
                };
                getDataFunction(onDone, onSuccess, approot, command, dataSend, servletTarget, appendTarget, false);
                
            }
	    
	    function loadActive(selector){
		var element = $(selector).find(".active");
		if(element.length > 0){
		    var target = element.data("target");
		    var oid = $("#oid").val();
		    var datafor = element.data("for");
		    var command = element.data("command");
                    var privadd = $("#privadd").val();
                    var privupdate = $("#privupdate").val();
                    var privdelete = $("#privdelete").val();
                    var kodecabang = $("#kodecabang").val();
                    if (target=="AjaxDebiturIndividu" || target=="AjaxDebiturBadanUsaha"){
                        $("#btnSave").show();
                        $("#btndelete").show();
                    }else{
                        $("#btnSave").hide();
                        $("#btndelete").hide();
                    }
                    
                    if (target=="AjaxDebiturIndividu" || target=="AjaxDebiturBadanUsaha" || target=="AjaxKredit" || target=="AjaxBankGaransi" || target=="AjaxAgunan"){
                        $("#btndownload").show();
                    }else{
                        $("#btndownload").hide();
                    }
                    
                    $("#servletdestination").val(target);
                    $("#dataforperlengkapan").val(datafor);
		    var dataSend = {
			"FRM_FIELD_OID" : oid,
			"FRM_FIELD_DATA_FOR" : datafor,
			"command" : command,
                        "privadd" : privadd,
                        "privupdate" : privupdate,
                        "privdelete" : privdelete,
                        "FRM_KODE_CABANG" : kodecabang
		    };

		    var onDone = function(data){
                        
                    
                        if(oid != 0){
                            validation($("#formperlengkapandata"));
                        }
			datePicker(".datepicker","yyyy-mm-dd");
                        $("#panelForDetailTabel").html("");
                        switch (target) {
                            case "AjaxDebiturIndividu":
                                var cif = $("#FRM_FIELD_CIF").val();
                                if (cif.length>0){
                                    $("#panelForDetailTabel").html(""
                                        + "<div id='panelDetailForKredit'></div>"
                                        + "<div id='panelDetailForBankGaransi'></div>"
                                        + "<div id='panelDetailForAgunan'></div>"
                                        + "<div id='panelDetailForPenjamin'></div>"
                                        + "<div id='panelDetailForSuratBerharga'></div>"
                                        + "<div id='panelDetailForKreditJoin'></div>"
                                        + "<div id='panelDetailForIrrevocable'></div>"
                                        + "<div id='panelDetailForFasilitasLainnya'></div>"
                                    +"");
                                    loadDetailForTable("AjaxKredit", "showform", "<%= Command.NONE %>","#panelDetailForKredit");
                                    loadDetailForTable("AjaxBankGaransi", "showform", "<%= Command.NONE %>","#panelDetailForBankGaransi");
                                    loadDetailForTable("AjaxAgunan", "showform", "<%= Command.NONE %>","#panelDetailForAgunan");
                                    loadDetailForTable("AjaxPenjamin", "showform", "<%= Command.NONE %>","#panelDetailForPenjamin");
                                    loadDetailForTable("AjaxSuratBerharga", "showform", "<%= Command.NONE %>","#panelDetailForSuratBerharga");
                                    loadDetailForTable("AjaxKreditJoin", "showform", "<%= Command.NONE %>","#panelDetailForKreditJoin");
                                    loadDetailForTable("AjaxIrrevocable", "showform", "<%= Command.NONE %>","#panelDetailForIrrevocable");
                                    loadDetailForTable("AjaxFasilitasLainnya", "showform", "<%= Command.NONE %>","#panelDetailForFasilitasLainnya");
                                }
                            break;
                            
                            case "AjaxDebiturBadanUsaha":
                                var cif = $("#FRM_FIELD_CIF").val();
                                if (cif.length>0){
                                    $("#panelForDetailTabel").html(""
                                        + "<div id='panelDetailForKredit'></div>"
                                        + "<div id='panelDetailForBankGaransi'></div>"
                                        + "<div id='panelDetailForAgunan'></div>"
                                        + "<div id='panelDetailForPenjamin'></div>"
                                        + "<div id='panelDetailForLaporanKeuanganDebitur'></div>"
                                        + "<div id='panelDetailForSuratBerharga'></div>"
                                        + "<div id='panelDetailForKreditJoin'></div>"
                                        + "<div id='panelDetailForIrrevocable'></div>"
                                        + "<div id='panelDetailForFasilitasLainnya'></div>"
                                    +"");
                                    loadDetailForTable("AjaxKredit", "showform", "<%= Command.NONE %>","#panelDetailForKredit");
                                    loadDetailForTable("AjaxBankGaransi", "showform", "<%= Command.NONE %>","#panelDetailForBankGaransi");
                                    loadDetailForTable("AjaxAgunan", "showform", "<%= Command.NONE %>","#panelDetailForAgunan");
                                    loadDetailForTable("AjaxPenjamin", "showform", "<%= Command.NONE %>","#panelDetailForPenjamin");
                                    loadDetailForTable("AjaxLaporanKeuanganDebitur", "showform", "<%= Command.NONE %>","#panelDetailForLaporanKeuanganDebitur");
                                    loadDetailForTable("AjaxSuratBerharga", "showform", "<%= Command.NONE %>","#panelDetailForSuratBerharga");
                                    loadDetailForTable("AjaxKreditJoin", "showform", "<%= Command.NONE %>","#panelDetailForKreditJoin");
                                    loadDetailForTable("AjaxIrrevocable", "showform", "<%= Command.NONE %>","#panelDetailForIrrevocable");
                                    loadDetailForTable("AjaxFasilitasLainnya", "showform", "<%= Command.NONE %>","#panelDetailForFasilitasLainnya");
                                }
                            break;
                            case "AjaxKredit":
                                datafor = "listkreditperlengkapandata";
                                dataTablesOptions("#tableListKreditElement", "tableKreditListElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralkredit");
                            break;
                            case "AjaxBankGaransi" :
                                datafor = "listdatabankgaransi";
                                dataTablesOptions("#tableListBankGaransi", "tableBankGaransiElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralgaransi");
                            break;
                            case "AjaxAgunan" :
                                datafor = "listdataagunan";
                                dataTablesOptions("#tableListAgunan", "tableListAgunanElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralagunan");
                            break;
                            case "AjaxPenjamin" :
                                datafor = "listdatapenjamin";
                                dataTablesOptions("#tableListPenjamin", "tableListPenjaminElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralpenjamin");
                            break;
                            
                            case "AjaxSuratBerharga" :
                                datafor = "listdatasuratberharga";
                                dataTablesOptions("#tableListSuratBerharga", "tableListSuratBerhargaElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralsuratberharga");
                            break;
                            case "AjaxKreditJoin" :
                                datafor = "listkreditjoin";
                                dataTablesOptions("#tableKreditJoin", "tableKreditJoinElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralkreditjoin");
                            break;
                            case "AjaxIrrevocable" :
                                datafor = "listdatairrevocable";
                                dataTablesOptions("#tableIrrevocable", "tableIrrevocableElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgenerairrevocable");
                            break;
                            case "AjaxFasilitasLainnya" :
                                datafor = "listdatafasilitas";
                                dataTablesOptions("#tableFasilitas", "tableFasilitasElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralfasilitas");
                            break;
                            case "AjaxPengurusPemilik" :
                                datafor = "listdatapengurus";
                                dataTablesOptions("#tablePengurusElement", "tablePengurusElementElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgeneralpengurus");
                            break;
                            case "AjaxLaporanKeuanganDebitur" :
                                datafor = "listdatalaporan";
                                dataTablesOptions("#tableLaporanKeuangan", "tableLaporanKeuanganElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                showFormNoModal(".btnaddgenerallaporan");
                            break;
                        }
                        if (target==="AjaxDebiturIndividu" || target ==="AjaxDebiturBadanUsaha"){
                            $("#openhistory").show();
                        }else{
                            $("#openhistory").hide();
                        }
                        autoShowForm(otheroid, activetab);
                        deletegeneral("#delete", ".deletelist");
		    };

		    var onSuccess = function(data){
		    };
		    getDataFunction(onDone, onSuccess, approot, command, dataSend, target, "#tabscontent", false);
		}
	    }
            
            function loadSearchActive(selector, selectorPlacement){
                var tipepencarian = $(selector).val();
                var dataSend = {
                    "command" : <%= Command.NONE %>,
                    "FRM_FIELD_DATA_FOR" : "searchform"
                };
                var onSuccess = function(data){
                };
                var onDone = function(data){
                    dataTablesOptions("#searchElement", "tableSearchElement", tipepencarian, "listdatasearch", null);
                    $("#btnsearch").click(function(){
                        dataTablesOptions("#searchElement", "tableSearchElement", tipepencarian, "listdatasearch", null);
                    });
                    vieweditdata("#tableSearchElement", ".edit");
                };
                getDataFunction(onDone, onSuccess, approot, <%= Command.NONE %>, dataSend, tipepencarian, selectorPlacement, false);
            };
	    
	    //DATE PICKER FUNCTION
	    var datePicker = function(contentId, formatDate){
		$(contentId).datepicker({
		    format : formatDate
		});
		$(contentId).on('changeDate', function(ev){
		    $(this).datepicker('hide');
		});
	    };
	    
	    function showForm(selector, modal){
		$(selector).click(function(){
		    $(modal).modal("show");
		    var datafor = $(this).data("for");
		    var oid = $("#oid").val();
		    var command = $(this).data("command");
		    var target = $(this).data("target");
		    var modaltitle = "";
		    var datasend = {};
		    var onDone = function(data){
                        
		    };
		    var onSuccess = function(data){
		    };
		    if(datafor == "searchform"){
			modaltitle = "Pencarian Data Per Segment";
                        loadSearchActive("#tipepencarian", "#searchmodal .addeditgeneral-body");
			datasend = {
			    "FRM_FIELD_OID" : oid,
			    "FRM_FIELD_DATA_FOR" : datafor,
			    "command" : command
			};
			getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#formcontent", false);
		    }else if(datafor == "searchhistory"){
                        modaltitle = "Histori Perubahan Data";
			
                        dataTablesOptions("#historyTableElement", "tableHistoryDataElement", ""+target+"", ""+datafor+"", null);
                       
                    }
		    $(modal).find(".modal-title").html(modaltitle);
                    //alert(JSON.stringify(datasend));
		    
		    
		});
	    };
            
            function showFormNoModal(selector){
                $(selector).click(function(){
                    var datafor = $(this).data("for");
		    var oid = $(this).data("oid");
		    var command = $(this).data("command");
		    var target = $(this).data("target");
                    var kodecabang = $("#kodecabang").val();
                    setData(oid, target);
                    var datasend = {};
		    var onDone = function(data){
                        
                    
                        if(oid != 0){
                            validation($("#formperlengkapandata"));
                        }
                        datePicker(".datepicker","yyyy-mm-dd");
                        moneyFormat();
                        switch (target) {
                            case "AjaxKredit":
                                $("#FRM_FLD_NO_REKENING").focus();
                            break;
                            case "AjaxBankGaransi":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxKredit":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxPenjamin":
                                $("#FRM_FIELD_NO_ID_PENJAMIN").focus();
                            break;
                            case "AjaxSuratBerharga":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxKreditJoin":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxFasilitasLainnya":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxLaporanKeuanganDebitur":
                                $("#FRM_FIELD_CIF").focus();
                            break;
                        }
                        $("#openhistory").show();
		    };
		    var onSuccess = function(data){
                        
		    };
                    
                    switch (target) {
                        case "AjaxKredit":
                            $("#oidkredit").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#datakreditform", false);
                        break;
                        case "AjaxBankGaransi":
                            //alert("test");
                            $("#oidBankGaransi").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#bankgaransiform", false);
                        break;
                        case "AjaxAgunan":
                            //alert("test");
                            $("#oidAgunan").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#agunanform", false);
                        break;
                        case "AjaxPenjamin":
                            //alert("test");
                            $("#oidPenjamin").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#penjaminform", false);
                        break;
                        case "AjaxSuratBerharga":
                            //alert("test");
                            $("#oidSuratBerharga").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#suratberhargaform", false);
                        break;
                        case "AjaxKreditJoin":
                            //alert("test");
                            $("#oidKreditJoin").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#kreditjoinform", false);
                        break;
                        case "AjaxIrrevocable":
                            //alert("test");
                            $("#oidIrrevocable").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#irrevocableform", false);
                        break;
                        case "AjaxFasilitasLainnya":
                            //alert("test");
                            $("#oidFasilitas").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#fasilitasform", false);
                        break;
                        
                        case "AjaxPengurusPemilik":
                            //alert("test");
                            $("#oidPengurus").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#pengurusform", false);
                        break;
                        case "AjaxLaporanKeuanganDebitur":
                            //alert("test");
                            $("#oidLaporan").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command,
                                "FRM_KODE_CABANG" : kodecabang,
                                "FRM_FIELD_CIF" : "<%= cif %>"
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#formlaporan", false);
                        break;
                        
                    }   
                });
            }
            
            function autoShowForm(otherOid, activeTab){
                var datafor = "showform2";
                var oid = otherOid;
                var command = <%= Command.NONE %>;
                var target = activeTab;

                var datasend = {};
                if(activeTab.length > 0 && activeTab != "undefined" && oid.length > 0 && oid != "undefined"){
                    var onDone = function(data){
                        if(oid != 0){
                            validation($("#formperlengkapandata"));
                        }
                        datePicker(".datepicker","yyyy-mm-dd");
                        moneyFormat();
                        switch (target) {
                            case "AjaxKredit":
                                $("#FRM_FLD_NO_REKENING").focus();
                            break;
                            case "AjaxBankGaransi":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxKredit":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxPenjamin":
                                $("#FRM_FIELD_NO_ID_PENJAMIN").focus();
                            break;
                            case "AjaxSuratBerharga":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxKreditJoin":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxFasilitasLainnya":
                                $("#FRM_FIELD_NO_REKENING").focus();
                            break;
                            case "AjaxLaporanKeuanganDebitur":
                                $("#FRM_FIELD_CIF").focus();
                            break;
                        }
                        $("#openhistory").show();
                    };
                    var onSuccess = function(data){

                    };

                    switch (target) {
                        case "AjaxKredit":
                            $("#oidkredit").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#datakreditform", false);
                        break;
                        case "AjaxBankGaransi":
                            //alert("test");
                            $("#oidBankGaransi").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#bankgaransiform", false);
                        break;
                        case "AjaxAgunan":
                            //alert("test");
                            $("#oidAgunan").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#agunanform", false);
                        break;
                        case "AjaxPenjamin":
                            //alert("test");
                            $("#oidPenjamin").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#penjaminform", false);
                        break;
                        case "AjaxSuratBerharga":
                            //alert("test");
                            $("#oidSuratBerharga").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#suratberhargaform", false);
                        break;
                        case "AjaxKreditJoin":
                            //alert("test");
                            $("#oidKreditJoin").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#kreditjoinform", false);
                        break;
                        case "AjaxIrrevocable":
                            //alert("test");
                            $("#oidIrrevocable").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#irrevocableform", false);
                        break;
                        case "AjaxFasilitasLainnya":
                            //alert("test");
                            $("#oidFasilitas").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#fasilitasform", false);
                        break;

                        case "AjaxPengurusPemilik":
                            //alert("test");
                            $("#oidPengurus").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#pengurusform", false);
                        break;
                        case "AjaxLaporanKeuanganDebitur":
                            //alert("test");
                            $("#oidLaporan").val(oid);
                            $("#btnSave").show();
                            datasend = {
                                "FRM_FIELD_OID" : oid,
                                "FRM_FIELD_DATA_FOR" : datafor,
                                "command" : command
                            };
                            getDataFunction(onDone, onSuccess, approot, command, datasend, target, "#formlaporan", false);
                        break;

                    }
                }
                   
            }
            
            function showFormNoModalForTable(){
                showFormNoModal(".btnaddgeneralkredit");
                showFormNoModal(".btnaddgeneralgaransi");
                showFormNoModal(".btnaddgeneralagunan");
                showFormNoModal(".btnaddgeneralpenjamin");
                showFormNoModal(".btnaddgeneralsuratberharga");
                showFormNoModal(".btnaddgeneralkreditjoin");
                showFormNoModal(".btnaddgenerairrevocable");
                showFormNoModal(".btnaddgeneralfasilitas");
                showFormNoModal(".btnaddgeneralpengurus");
                showFormNoModal(".btnaddgenerallaporan");
            }
	    
	    //MODAL SETTING
	    var modalSetting = function(elementId, backdrop, keyboard, show){
		$(elementId).modal({
		    backdrop	: backdrop,
		    keyboard	: keyboard,
		    show	: show
		});
	    };
	    
	    function btnback(selector, url){
		$(selector).click(function(){
		    window.location = url;
		});
	    }
	    
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
            
            function moneyFormat(){
                //$(".money").maskMoney({allowZero:true,decimal:'.'});
                $('.numeric').keyup(function(){
                    var value = $(this).val();
                    var conValue = parserNumber(value,false,0,'');
                    $(this).val(conValue);
                });
            }
            
            //DELETE GENERAL
            deletegeneral = function(elementId, checkboxelement){
                $(elementId).click(function(){
                    var dataFor = $(this).data("for");
                    var checkBoxes = (checkboxelement);
                    var vals = "";
                    var target = $(this).data("target");
                    $(checkboxelement).each(function(i){

                        if($(this).is(":checked")){
                            if(vals.length == 0){
                                vals += ""+$(this).val();
                            }else{
                                vals += ","+$(this).val();
                            }
                        }
                    });
                    var confirmText = "Apakah anda yakin untuk menghapus data ini?";
                    if(vals.length == 0){
                        alert("Mohon pilih data terlebih dahulu");
                    }else{
                        var command = <%= Command.DELETEALL %>;
                        var currentHtml = $(this).html();
                        $(this).html("Menghapus...").attr({"disabled":true});
                        if(confirm(confirmText)){
                            var dataSend = {
                                "FRM_FIELD_DATA_FOR"	    : dataFor,
                                "FRM_FIELD_OID_DELETE"	    : vals,
                                "command"		    : command
                            };
                            onSuccess = function(data){

                            };
                            if(dataFor == "deleteitem"){
                                onDone = function(data){
                                    switch(target){
                                        case "AjaxKredit":
                                                var datafor = "listkreditperlengkapandata";
                                                dataTablesOptions("#tableListKreditElement", "tableKreditListElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgeneralkredit");
                                            break;
                                            case "AjaxBankGaransi" :
                                                var datafor = "listdatabankgaransi";
                                                dataTablesOptions("#tableListBankGaransi", "tableBankGaransiElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                               //showFormNoModal(".btnaddgeneralgaransi");
                                            break;
                                            case "AjaxAgunan" :
                                                var datafor = "listdataagunan";
                                                dataTablesOptions("#tableListAgunan", "tableListAgunanElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgeneralagunan");
                                            break;
                                            case "AjaxPenjamin" :
                                                var datafor = "listdatapenjamin";
                                                dataTablesOptions("#tableListPenjamin", "tableListPenjaminElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgeneralpenjamin");
                                            break;

                                            case "AjaxSuratBerharga" :
                                                var datafor = "listdatasuratberharga";
                                                dataTablesOptions("#tableListSuratBerharga", "tableListSuratBerhargaElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgeneralsuratberharga");
                                            break;
                                            case "AjaxKreditJoin" :
                                                var datafor = "listkreditjoin";
                                                dataTablesOptions("#tableKreditJoin", "tableKreditJoinElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgeneralkreditjoin");
                                            break;
                                            case "AjaxIrrevocable" :
                                                var datafor = "listdatairrevocable";
                                                dataTablesOptions("#tableIrrevocable", "tableIrrevocableElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgenerairrevocable");
                                            break;
                                            case "AjaxFasilitasLainnya" :
                                                var datafor = "listdatafasilitas";
                                                dataTablesOptions("#tableFasilitas", "tableFasilitasElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgeneralfasilitas");
                                            break;
                                            case "AjaxPengurusPemilik" :
                                                var datafor = "listdatapengurus";
                                                dataTablesOptions("#tablePengurusElement", "tablePengurusElementElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgeneralpengurus");
                                            break;
                                            case "AjaxLaporanKeuanganDebitur" :
                                                var datafor = "listdatalaporan";
                                                dataTablesOptions("#tableLaporanKeuangan", "tableLaporanKeuanganElement", ""+target+"", ""+datafor+"", showFormNoModalForTable);
                                                //showFormNoModal(".btnaddgenerallaporan");
                                            break;
                                    }
                                };
                            }
                            getDataFunction(onDone, onSuccess, approot, command, dataSend, target, null, true);
                            $(this).removeAttr("disabled").html(currentHtml);
                        }else{
                            $(this).removeAttr("disabled").html(currentHtml);
                        }
                    }

                });
            }
            
            function validation(element){
                var isError = 0;
                element.find("input[type=text], select").each(function(){
                    var isRequired = $(this).data("required");
                    var errMessage = "<i class='fa fa-exclamation-circle'></i> "+$(this).data("errorMessage");
                    var type = $(this).data("type");
                    var getVal = $(this).val();
                    var isCorrect = true;
                    switch (type){
                        case "text" :
                            if(isRequired == "required" && getVal.length == 0){
                                isCorrect = false;
                            }
                               
                        break;
                        
                        case "email" :
                            if(isRequired == "required" && getVal.length == 0){
                                isCorrect = false;
                            }else if((getVal.indexOf("@") == -1 || getVal.indexOf(".") == -1) && getVal.length > 0){
                                isCorrect = false;
                                errMessage = "<i class='fa fa-exclamation-circle'></i> Format email salah.";
                            }
                    }
                    if(isCorrect){
                        $(this).parents(".form-group").removeClass("has-error");
                        $(this).parents(".form-group").find(".msg").fadeOut("fast");
                    }else{
                        isError+=1;
                        $(this).parents(".form-group").addClass("has-error");
                        var msg = $(this).parents(".form-group").find(".msg");
                        if(msg.length > 0){
                            msg.html(errMessage).fadeIn("medium");
                        }else{
                            $(this).parents(".col-sm-8").append("<div class='msg clearfix'>"+errMessage+"</div>").fadeIn("medium");
                        }
                    }
                    
                });
                
                return isError;
            }
            
            $("#formperlengkapandata").submit(function(){
		var currentBtnHtml = $("#btnSave").html();
                var servletTarget = $("#servletdestination").val();
                var command = "<%= Command.SAVE%>";
                onSuccess = function(data){
		};
                var isCorrect = validation($(this));
                
                
                onDone = function(data){
                    if (servletTarget==="AjaxDebiturIndividu" || servletTarget==="AjaxDebiturBadanUsaha"){
                        //alert(JSON.stringify(data));
                        $("#formperlengkapandata #oid").val(data.FRM_FIELD_RETURN_OID); 
                        $("#formperlengkapandata #cif").val(data.FRM_FIELD_CIF_RETURN); 
                        
                    }else if (servletTarget==="AjaxKredit"){
                        loadActive("#tabmenu");
                        
                    }else if (servletTarget==="AjaxBankGaransi"){
                        loadActive("#tabmenu");
                    }else if (servletTarget==="AjaxAgunan"){
                        loadActive("#tabmenu");
                    }else if (servletTarget==="AjaxPenjamin"){
                        loadActive("#tabmenu");
                    }else if (servletTarget==="AjaxSuratBerharga"){
                        loadActive("#tabmenu");
                    }else if (servletTarget==="AjaxKreditJoin"){
                        loadActive("#tabmenu");
                    }else if (servletTarget==="AjaxIrrevocable"){
                        loadActive("#tabmenu");
                    }else if (servletTarget==="AjaxFasilitasLainnya"){
                        loadActive("#tabmenu");
                    }else if (servletTarget==="AjaxPengurusPemilik"){
                        loadActive("#tabmenu");
                    }else if (servletTarget==="AjaxLaporanKeuanganDebitur"){
                        loadActive("#tabmenu");
                    }
                    
                    $("#btnSave").removeAttr("disabled").html(currentBtnHtml);
                };
                
                var dataSend = $(this).serialize();
                if(isCorrect == 0){
                    $("#btnSave").html("Saving...").attr({"disabled":"true"});
                    getDataFunction(onDone, onSuccess, approot, command, dataSend, ""+servletTarget+"", null, true, "json");
                }else{
                    var focused = $(this).find(".has-error").find("input:first, select:first");
                    focused.focus();
                    $("html, body").animate({ scrollTop: focused.offset().top }, "slow");
                }
                //
		return false;
	    });
            
	    	    
	    btnback(".btnback","<%= approot %>/dslik/perlengkapandata/perlengkapandata.jsp");
	    modalSetting("#searchmodal","static",false,false);
	    showForm("#searchdebitur","#searchmodal");
            showForm("#openhistory","#openhistorydata");
            
	    runDataTable();
	    
	    tabMenu(".menutab");
	    activeMenu("#modperlengkapandata", "#perlengkapandata");
	    loadActive("#tabmenu");
            $("#tipepencarian").change(function(){
                loadSearchActive("#tipepencarian", "#searchmodal .addeditgeneral-body");
            });
            vieweditdata("#panelForDetailTabel", ".edit");
            $("#btndelete").click(function(){
                var currentHtml = $(this).html();
                var servletTarget = $("#servletdestination").val();
                var cif = $("#cif").val();
                var dataSend = {
                    "FRM_FIELD_DATA_FOR" : "deleteitem",
                    "cif" : cif,
                    "command" : <%= Command.DELETE %>
                };
                
                onDone = function(data){
                    $("#btndelete").removeAttr("disabled").html(currentHtml);
                    if(data.FRM_FIELD_RETURN_OID == 1){
                        window.location = '<%= approot %>/dslik/perlengkapandata/perlengkapandata.jsp?command=<%= Command.VIEW %>&oid=0&type=1';
                    }
                };
                
                onSuccess = function(data){
                };
                
                
                var confirmText = "Anda yakin untuk menghapus data ini?";
                if(confirm(confirmText)){
                    $(this).html("Menghapus...").attr("disabled",true);
                    getDataFunction(onDone, onSuccess, approot, <%= Command.DELETE %>, dataSend, ""+servletTarget+"", null, true, "json");
                }
            });
            
            
            $("#btndownload").click(function(){
                var currentHtml = $(this).html();
                var servletTarget = $("#servletdestination").val();
                var cif = $("#cif").val();
                if(cif==""){
                    if (servletTarget==="AjaxDebiturIndividu" || servletTarget==="AjaxDebiturBadanUsaha"){
                        cif=$("#FRM_FIELD_CIF").val();
                    }    
                }
                var idRekeningPendukung="";
                var koderegister="";
                if (servletTarget=="AjaxKredit"){
                    idRekeningPendukung = $("#FRM_FLD_NO_REKENING").val();
                }else if( servletTarget=="AjaxBankGaransi" ){
                    idRekeningPendukung = $("#FRM_FIELD_NO_REKENING").val();
                }else if( servletTarget=="AjaxAgunan")    {
                    koderegister = $("#FRM_FIELD_KODE_REGISTER_AGUNAN").val();
                    idRekeningPendukung = $("#FRM_FIELD_NO_REKENING").val();
                }else{
                    
                }
                var dataSend = {
                    "FRM_FIELD_DATA_FOR" : "downloaditem",
                    "cif" : cif,
                    "idrekening" : idRekeningPendukung,
                    "kodeagunan" : koderegister,
                    "command" :<%=Command.DOWNLOADDATA%>   //data : $('#form').serialize() + "&par1=1&par2=2&par3=232"
                };
                
                onDone = function(data){
                    $("#btndownload").removeAttr("disabled").html(currentHtml);
                    if(data.FRM_FIELD_RETURN_OID == 1){
                        window.location = '<%= approot %>/dslik/perlengkapandata/perlengkapandata.jsp?command=<%= Command.VIEW %>&oid=0&type=1';
                    }
                };
                
                onSuccess = function(data){
                    loadActive("#tabmenu");
                };
                
                
                var confirmText = "Anda yakin untuk download data ini?";
                if(confirm(confirmText)){
                    $(this).html("Download Data...").attr("disabled",true);
                    getDataFunction(onDone, onSuccess, approot, <%= Command.DOWNLOADDATA %>, dataSend, ""+servletTarget+"", null, true, "json");
                }
            });
            
            $("#notifpengurus").notify({
                type    : "warning",
                transition : "fade",
                message : {
                    html : "Data segmen pengurus / pemilik tidak ada, mohon untuk diisi"
                },
                fadeOut: {
                    enabled: true,
                    delay: 86400
                }
            }).show();
            
	});
      </script>
  
    </div><!-- ./wrapper -->
    <%= notif %>
  </body>
</html>
