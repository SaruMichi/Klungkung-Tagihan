<%-- 
    Document   : wp-listlaporan
    Created on : Sep 30, 2015, 10:11:49 PM
    Author     : Administrator
--%>


<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.wpupload.form.wpnamabadan.FrmNamaBadan"%>
<%@page import="com.dimata.wpupload.form.esptpd.FrmESPTPD"%>
<%@page import="com.dimata.wpupload.entity.esptpd.PstESPTPD"%>
<%@page import="com.dimata.wpupload.entity.wpuser.PstAppUserWP"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.wpupload.entity.esptpd.ESPTPD"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="java.util.Vector"%>
<%@include file="../main/javainit-wp.jsp" %>
<%!
    public String drawList(Vector objectClass, String approot) {
        //2
        ControlList ctrlist = new ControlList();
        
        ctrlist.addHeader("NPWPD","10%");
        ctrlist.addHeader("Masa Pajak", "10%");
        ctrlist.addHeader("Tahun Pajak", "10%");
        ctrlist.addHeader("Jumlah Omset", "10%");
        ctrlist.addHeader("Tarif", "10%");
	ctrlist.addHeader("Jumlah Pajak","10%");
	ctrlist.addHeader("Service Tax","10%");
        ctrlist.addHeader("Denda","10%");
	ctrlist.addHeader("Pengurangan","10%");
	ctrlist.addHeader("Harus Diabayar","10%");
	ctrlist.addHeader("Keterangan","10%");
	ctrlist.addHeader("Aksi","10%");
	
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
	String[] indoMonth = {
	    "Januari",
	    "Februari",
	    "Maret",
	    "April",
	    "Mei",
	    "Juni",
	    "Juli",
	    "Agustus",
	    "September",
	    "Oktober",
	    "November",
	    "Desember"
	};
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	int month = cal.get(Calendar.MONTH);
        //membuat link menuju ke edit
        //ctrlist.setLinkPrefix("javascript:cmdEdit('");
        //ctrlist.setLinkSufix("')");
        ctrlist.reset();
        
        int index = -1;
        
        Vector rowx = new Vector(1,1);
        for(int i = 0; i < objectClass.size(); i++) {
            ESPTPD esptpd = (ESPTPD) objectClass.get(i);
            
            rowx = new Vector(1,1);
	    rowx.add(""+esptpd.getENPWPD());
            
	    rowx.add(""+indoMonth[Integer.parseInt(esptpd.getEMasaPajak())-1]);
            rowx.add(""+esptpd.getETahunPajak());
	    rowx.add(""+esptpd.getEJumlahOmzet());
	    rowx.add(""+esptpd.getETarif());
	    rowx.add(""+esptpd.getEJumlahPajak());
	    rowx.add(""+esptpd.getEServiceTax());
	    rowx.add(""+esptpd.getEDenda());
	    rowx.add(""+esptpd.getEPengurangan());
	    rowx.add(""+esptpd.getEHarusBayar());
	    rowx.add(""+esptpd.getEKeterangan());
	    rowx.add("<button'"
		    + "data-masa-pajak='"+esptpd.getEMasaPajak()+"' "
		    + "data-tahun-pajak='"+esptpd.getETahunPajak()+"' "
		    + "data-npwpd='"+esptpd.getENPWPD()+"' "
		    + "data-approot='"+approot+"'"
		    + "class='editLaporan btn btn-small btn-warning'><i class='icon icon-pencil'> Edit</button>");
            
	    lstData.add(rowx);
        }
        
        
        return ctrlist.drawBootstrapStrip();
    }
%>
<%
    Vector listESPTPD = new Vector(1,1);
   
    listESPTPD = PstESPTPD.listJoin(0, 0, "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+userIdWP+"'", "");
    
    
    
    
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Daftar Pelaporan Pajak - Wajib Pajak</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet">
<link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
        rel="stylesheet">
<link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
<link href="../styles/wpupload/css/style.css" rel="stylesheet">
<link href="../styles/wpupload/css/pages/dashboard.css" rel="stylesheet">
<style type="text/css">
    .modal-lg-report{
	max-width: 1366px;
	width: 98%;
    }
    .modal{
	left:21.5%;
    }
    #widget-content-modif{
        overflow: visible;
        
    }
     
</style>
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
      <%@include file="wp-fixtop.jsp" %>
  </div>
  <!-- /navbar-inner --> 
</div>
<!-- /navbar -->
<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
	<%
	    String activeHome = "";
	    String activeNamaBadan="";
	    String activeList="active";
            String activeNamaBadanPanduan="";
	%>
	<%@include file="wp-navbar.jsp" %>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<!-- /subnavbar -->
<div class="main">
  <div class="main-inner">
    <div class="container">
      <div class="row">
        <div class="span12" id="result">

	    
          </div>
          <!-- /widget -->
	  
          
        
        </div>
        <!-- /span6 -->
        
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
    <br><br><br>
  </div>
  <!-- /main-inner --> 
  


<!-- /main -->
<div class="footer">
  <div class="footer-inner">
    <div class="container">
	<%@include file="wp-footer.jsp" %>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /footer-inner --> 
</div>
<!-- /footer --> 
<!-- Le javascript
================================================== --> 
<!-- Placed at the end of the document so the pages load faster --> 
<script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script> 
<script src="../styles/wpupload/js/excanvas.min.js"></script> 
<script src="../styles/wpupload/js/chart.min.js" type="text/javascript"></script> 
<script src="../styles/wpupload/js/bootstrap.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
	
	$("#reportSystem").modal({
	    backdrop : "static",
	    keyboard : false,
	    show : false
	});
	function autoSum(){
	    $(".calPajak").on('keyup', function() {
		var sumPajak = 0;
		var mustPaid = 0;
		var omset = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").val());
		var tarif = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF] %>").val());
		var serviceTax = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>").val());

		sumPajak = 	omset*(tarif/100);
		mustPaid = sumPajak+serviceTax;

		$("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK] %>").val(sumPajak);
		$("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>").val(mustPaid);
	    });

	    $("#<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI] %>").keyup(function(){
	       var valueGet = $(this).val();
	       $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_LOKASI] %>").val(valueGet);
	    });
	}
        
        
	
	function loadData(){
	    $("#result").html("<div id='widget'><div id='widget-content'>Harap Tunggu...</div></div>").show();
	    $.ajax({
		type    : "POST",
		url	    : "<%= approot %>/PelaporanPajakHandler?user=<%= userIdWP%>",
		data	    : {
			    "command":"<%= Command.LOAD %>",
			    "<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK] %>":"01",
			    "<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NPWPD] %>":"0",
			    "<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK] %>":"0",
			    "approot":"<%= approot %>"
			},
		cache	    : false,
		success    : function(data){
		    $("#result").html(data).show();

		},
		error : function(data){
			alert(data);
		}
	   }).done(function(){
	       editData();
              
                  //alert('test');
                  printSptLampiran();
              
	   });
	}
	loadData();
        
        
	
	//GET PERIOD DUE DATE ON LOAD
	function loadTempo(){
	    var masaPajak = parseInt($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK] %>").val());
	    var tahunPajak = parseInt($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK] %>").val());
	    var months = ["Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus","September","Oktober", "November", "Desember"];
	    var displayMasaPajak = "";
	    if(masaPajak > 11){
		displayMasaPajak = months[0];
		tahunPajak = tahunPajak+1;
	    }else{
		displayMasaPajak = months[masaPajak];
		tahunPajak = tahunPajak;
	    }
	    $("#jatuh_tempo").val("15 "+ displayMasaPajak + " "+tahunPajak);
	}
	
	function editData(){
	    $(".editLaporan").click(function(){
	       var masaPajak = $(this).data("masaPajak");
	       var npwpd = $(this).data("npwpd");
	       var tahunPajak = $(this).data("tahunPajak");
	       var approot = $(this).data("approot");
	       $("#reportMessage").html("<div id='widget'><div id='widget-content'>Harap Tunggu...</div></div>").show();
	       $(".modal-title").html("PELAPORAN PAJAK").show();
	       $("#btnSimpan").show();
	       $.ajax({
		     type	    : "POST",
		     url	    : "<%= approot %>/PelaporanPajakHandler?user=<%= userIdWP%>",
		     data	    : {
				    "command":"<%= Command.NONE %>",
				    "<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK] %>":masaPajak,
				    "<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NPWPD] %>":npwpd,
				    "<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK] %>":tahunPajak,
				    "approot":approot
				},
		     cache	    : false,
		     success    : function(data){		     
			 $("#reportMessage").html(data).fadeIn();
			 $("#reportSystem").modal("show");
		     },
		     error : function(data){
			     alert(data);
		     }
		}).done(function(){
		    loadTempo();
		});
	    });
	}
        
        function printSptLampiran(){
            $('.printLaporan').click(function(){
                var masaPajak = $(this).data("masaPajak").toString();   
                var npwpd = $(this).data("npwpd");
                var tahunPajak = $(this).data("tahunPajak").toString();
                var lampiran = $(this).data("lampiran").toString();
                
                var parameter = npwpd + ' ' + masaPajak + ' ' + tahunPajak + ' ' + lampiran;
               
                $('#reportSPT').on('show', function () {
                    $('#reportSPT').css('top','40%');
                    $('iframe').attr({"src":'<%= approot %>/wpupload/wp-rpt-spt1.jsp?param='+parameter+''});

                });
                $('#reportSPT').modal({show:true});

            });
            
        }
        
        
        
        
	
	
	$("form#<%= FrmESPTPD.FRM_NAME_ESPTPD %>").submit(function(){
	   $("#btnSimpan").attr({"value":"Mohon Tunggu...","disabled":true});
	   $.ajax({
		 type	    : "POST",
		 url	    : "<%= approot %>/PelaporanPajakHandler?user=<%= userIdWP%>",
		 data	    : $(this).serialize(),
		 cache	    : false,
		 success    : function(data){
		     $("#btnSimpan").removeAttr("disabled").attr({"value":"Simpan"});
		     $("#btnSimpan").hide();
		     $("#reportMessage").html(data).show();
		 },
		 error : function(data){
			 alert(data);
		 }
	    }).done(function(){
		loadData();
	    });
	    return false;
	});
	
	$("#reportSystem").on("shown.bs.modal",function (e){
	    autoSum();	    
	});
    });
</script>
<div id="reportSystem" class="modal fade modal-lg-report" tabindex="-1">
    <div class="modal-dialog">
	<div class="modal-content">
	    <form id="<%= FrmESPTPD.FRM_NAME_ESPTPD %>">
		<input type="hidden" name="secondCommand" value="UPDATE">
	    <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title"></h4>
	    </div>

	    <div class="modal-body">
		<div id="reportMessage"></div>
	    </div>
	    <div class="modal-footer">
		<input type="submit" value="Simpan" id="btnSimpan" class="btn btn-success">
		<button type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
	    </div>
	    </form>
	</div>
    </div>
</div>
<style>


</style>            
<div id="reportSPT"  class="modal fade modal-lg-report" tabindex="-1">
    <div class="modal-dialog">
	<div class="modal-content">
	    
	    <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title"></h4>
	    </div>

	    <div class="modal-body" style="max-height: 600px; overflow-y: hidden;">
		 <iframe src="" style="zoom:0.60" width="99.6%" height="520" frameborder="0"></iframe>
	    </div>
	    <div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
	    </div>
	    </form>
	</div>
    </div>
</div>

<script language="javascript" type="text/javascript" src="../styles/wpupload/js/full-calendar/fullcalendar.min.js"></script>
 
<script src="../styles/wpupload/js/base.js"></script> 

</body>
</html>
