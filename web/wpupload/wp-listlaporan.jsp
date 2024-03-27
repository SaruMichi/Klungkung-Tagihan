
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
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

<!DOCTYPE html>
<html lang="en">
<%
String submitrespon = FRMQueryString.requestString(request, "submitrespon");    
String masaPajakRespon = FRMQueryString.requestString(request, "masapajaklapor");
String tahunPajakRespon = FRMQueryString.requestString(request, "tahunpajaklapor");
String npwpdRespon = FRMQueryString.requestString(request, "npwpdpajaklapor");
String messageStringRespon = FRMQueryString.requestString(request, "messageStringRespon");
boolean submitResponUpload=false;
if(submitrespon.equals("ok")){
    submitResponUpload=true;
}
%>    
<head>
<meta charset="utf-8">
<title>Daftar Pelaporan Pajak - Wajib Pajak</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">   
<link href="../styles/wpupload/img/favicon.ico" rel="shortcut icon" />
<link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet">
<link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
<link href="../styles/wpupload/css/font-google.css" rel="stylesheet"> 
<link href="../styles/wpupload/css/style.css" rel="stylesheet">

<script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>
<script src="../styles/wpupload/js/bootstrap.js"></script>
<script src="../styles/wpupload/js/base.js"></script>
<!--Data Table -->
<link href="../styles/wpupload/datatables-plugins/integration/bootstrap/2/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
<link href="../styles/wpupload/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
<script src="../styles/wpupload/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="../styles/wpupload/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
<style type="text/css">
    
    #widget-content-modif{
        overflow: visible;
        
    }
    .alignRight{
        text-align: right;
        
    }
    .widget{
        float:left;
        width:100%;
    }
   
    
</style>
<!-- css optional untuk bootstrap, khusus untuk template ini -->
<link href="../styles/wpupload/css/pages/optional.css" rel="stylesheet">
<script type="text/javascript">
    $(document).ready(function(){
        function loadTable1(){
            $('#tabledata1').dataTable({
                "destroy": true,
                "bJQueryUI" : true,
                "sPaginationType" : "full_numbers",
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                "sAjaxSource" : "<%= approot%>/PelaporanPajakHandler?command=<%= Command.LOAD%>&table=1&user=<%= userIdWP%>",
                "aoColumns" :   [{"bSearchable" : true, "bVisible" : true, "asSorting" : [ "asc" ] },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "5%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight"},
                                {"sWidth" : "5%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "5%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "5%","bSortable" : true }
                            ],
                "initComplete": function(settings, json) {
                    editData();
                    printSptLampiran();
                    uploadDocument();
                },
                "fnDrawCallback": function( oSettings ) {
                    editData();
                    printSptLampiran();
                    uploadDocument();
                }
            });
         }
         
        function loadTable2(){
            $('#tabledata2').dataTable({
                "destroy": true,
                "bJQueryUI" : true,
                "sPaginationType" : "full_numbers",
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                
                "sAjaxSource" : "<%= approot%>/PelaporanPajakHandler?command=<%= Command.LOAD%>&table=2&user=<%= userIdWP%>",
                "aoColumns" :   [{"bSearchable" : true, "bVisible" : true, "asSorting" : [ "asc" ] },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "20%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true},
                                {"sWidth" : "10%","bSortable" : true}
                                ],
                        "initComplete": function(settings, json) {
                            printSptLampiran();
                        },
                        "fnDrawCallback": function( oSettings ) {
                            printSptLampiran();
                        }  
            });
        
        } 
        
        function loadTable3(){
            $('#tabledata3').dataTable({
                "destroy": true,
                "bJQueryUI" : true,
                "sPaginationType" : "full_numbers",
                "iDisplayLength": 10,
                "bProcessing" : true,
                "bServerSide" : true,
                
                "sAjaxSource" : "<%= approot%>/PelaporanPajakHandler?command=<%= Command.LOAD%>&table=3&user=<%= userIdWP%>",
                "aoColumns" :   [{"bSearchable" : true, "bVisible" : true, "asSorting" : [ "asc" ] },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true,sClass: "alignRight" },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true },
                                {"sWidth" : "10%","bSortable" : true}
                                ]
               
            });
        
        } 
        
	function autoSum(){
           
            $(".inputform").on('keydown', function(event) {
                if (event.which == 13 ) {
                   if(this.name=="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>"){
                       $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>").focus();
                       $('form').submit(false);
                   }else if(this.name=="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>"){
                       $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>").focus();
                       $('form').submit(false);
                   }else if(this.name=="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>"){
                        $("#btnSimpan").focus();
                   }
                }
            });
        
	    $(".calPajak").on('keyup', function() {
		var sumPajak = 0;
		var mustPaid = 0;
		var omset = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").val());
		var tarif = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF] %>").val());
		var serviceTax = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>").val());
                var jumlahService = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>").val());
    
                if(isNaN(omset)){
                    omset=0
                }
                
                if(isNaN(jumlahService)){
                    jumlahService=0;
                    serviceTax=0;
                }else{
                    serviceTax=Math.round((jumlahService*tarif)/100);
                }
                
                sumPajak = Math.round((omset*tarif)/100);
                
                if(isNaN(serviceTax)){
                    serviceTax=0;
                }
		mustPaid = Math.round(sumPajak+serviceTax);
                
                $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>").val(serviceTax);
		$("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK] %>").val(sumPajak);
		$("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>").val(mustPaid);
	    });

	    $("#<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI] %>").keyup(function(){
	       var valueGet = $(this).val();
	       $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_LOKASI] %>").val(valueGet);
	    });
	}
     
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
               /*$('.modal-lg-report').css('max-width','1366px');
               $('.modal-lg-report').css('width','98%')
               $('.modal').css('left','21.5%');*/
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
        
        function uploadDocument(){
	    $(".uploadDocument").click(function(){
	       var masaPajak =""+$(this).data("masaPajak");
	       var npwpd = $(this).data("npwpd");
	       var tahunPajak = $(this).data("tahunPajak");
	       var approot = $(this).data("approot");
               var mLeng = masaPajak.length;
               if(mLeng==1){
                   masaPajak="0"+masaPajak;
               }
               var dataupload = "<input type='hidden' name='npwpdpajaklapor' value='"+npwpd+"' id='npwpdpajaklapor'>"
                               + "<input type='hidden' name='masapajaklapor' value='"+masaPajak+"' id='masapajaklapor'>"
                               + "<input type='hidden' name='rootpro' value='<%=approot%>' id='rootpro'>"
                               + "<input type='hidden' name='messageString' value='SUCCESS' id='messageString'>"
                               + "<input type='hidden' name='posisiupload' value='1' id='posisiupload'>"
                               + "<input type='hidden' name='tahunpajaklapor' value='"+tahunPajak+"' id='tahunpajaklapor'>";
               $("#uploaddatainput").append(dataupload);
               $("#uploaddocument").modal("show");
	    });
	}
        
        function printSptLampiran(){
            $('.printLaporan').click(function(){
                var masaPajak = $(this).data("masaPajak").toString();   
                var npwpd = $(this).data("npwpd");
                var tahunPajak = $(this).data("tahunPajak").toString();
                var lampiran = $(this).data("lampiran").toString();
                var noSptpd = $(this).data("nosptpd").toString();
                
                var parameter = npwpd + ' ' + masaPajak + ' ' + tahunPajak + ' ' + lampiran + ' ' + noSptpd;
                $('#map').toggle();
                $('#reportSPT').on('show', function () {
                    
	            /*$('.modal-lg-report').css('max-width','1366px');
                    $('.modal-lg-report').css('width','98%');
                    $('.modals').css('left','21.5%');*/
                    $('#map').toggle();
                    $('#reportSPT').css('top','40%');
                    $('#reportSPT').css('width','99%');
                    $('#reportSPT').css('left','20%');
                    $('iframe').attr({"src":'<%= approot %>/wpupload/wp-rpt-spt1.jsp?param='+parameter+''});

                });
                $('#reportSPT').modal({show:true});
                $('#map').toggle();
            });
            
        }
        
	loadTable1();
        loadTable2();
        loadTable3();
	
        $("#btnSimpan").click(function(){
           //buatkan konfirmasi apakah data sudah benar
            var dataomzet = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").val());
            var dataservice = parseFloat($("#FRM_FIELD_E_JUMLAM_SERVICE").val());
            var dataketerangan = $("#FRM_FIELD_E_KETERANGAN").val();
            if(dataomzet==""){
                alert("Nilai data omzet belum di input, silahkan input terlebih dahulu");
                $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").focus();
                return false;
            }
            if(isNaN(dataservice)){
                    alert("Nilai data service belum di input, silahkan input terlebih dahulu");
                    $("#FRM_FIELD_E_JUMLAM_SERVICE").focus();
                    return false; 
            }
            if(dataketerangan==""){
                alert("Keterangan belum di isi, silahkan isi terlebih dahulu");
                $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>").focus();
                return false;
            }
        
           var datadibayar=$("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>").val();
           if (confirm('Total Pajak Yang Harus di Bayar : Rp.'+datadibayar+', Yakin melakukan proses data ?')) { 
	   $("#btnSimpan").attr({"value":"Mohon Tunggu...","disabled":true});
	   $.ajax({
		 type	    : "POST",
                      url	    : "<%= approot %>/PelaporanPajakHandler?user=<%= userIdWP%>",
                      data	    : $("#FRM_NAME_ESPTPD").serialize(),
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
		//loadData();
                loadTable1();
                loadTable2();
                loadTable3();
	    });
            }
	    return false;
	});
        
        
	$("#reportSystem").on("shown.bs.modal",function (e){
            $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").focus();
	    autoSum();	    
	});
    });
</script>
</head>
<body>
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
      <%@include file="wp-fixtop.jsp" %>
  </div>
</div>

<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
	<%
	    String activeHome = "";
            String activeManualTagihan="";
	    String activeNamaBadan="";
	    String activeList="active";
            String activeNamaBadanPanduan="";
	%>
	<%@include file="wp-navbar.jsp" %>
    </div>
  </div>
</div>
<div class="main">
    <div class="main-inner">
        <div class="container">
            <div class="row">
                <div class="span12" id="result">
                    <div class="widget " id="widget-content-modif">
                        <div class="widget-header">
                            <i class="icon-list"></i>
                            <h3>DAFTAR TAGIHAN YANG BELUM DI BAYAR</h3>
                        </div> 
                        <div class="widget-content" >
                            <div class="tabbable"> 
                                <div class="table-responsive" style="overflow-x: auto;">
                            <span class="pull-right">Hasil cetak terbaik menggunakan Chrome Browser</span>
                                <table  class="example table table-striped table-bordered table-hover display" id="tabledata1">
                                    <thead>
                                        <tr>
                                            <th>NPWPD</th>
                                            <th>Masa Pajak</th>
                                            <th>Tahun Pajak</th>
                                            <th>Jumlah Omset</th>
                                            <th>Tarif</th>
                                            <th>Jumlah Pajak</th>
                                            <th>Service Tax</th>
                                            <th>Denda</th>
                                            <th>Pengurangan</th>
                                            <th>Harus Dibayar</th>
                                            <th>Nama Document</th>
                                            <th>Status</th>
                                            <th>Aksi</th>
                                        </tr>
                                    </thead>
                                </table>
                                </div>
                            </div>

                        </div> 
                    </div> 
	    
                </div>
            </div>
            <div class="row">
                <div class="span12">      		
                    <div class="widget ">
                        <div class="widget-header">
                            <i class="icon-list"></i>
                            <h3>HISTORY PELAPORAN PAJAK</h3>

                        </div> 
                        <div class="widget-content" >
                            <div class="tabbable"> 
                                <div class="table-responsive" style="overflow-x: auto;">
                                <table  class="example table table-striped table-bordered table-hover display" id="tabledata2">
                                    <thead>
                                        <tr>
                                            <th>NPWPD</th>
                                            <th>Masa Pajak</th>
                                            <th>Tahun Pajak</th>
                                            <th>Nama</th>
                                            <th>Alamat</th>
                                            <th>Pokok</th>
                                            <th>Denda</th>
                                            <th>Jumlah</th>
                                            <th>Instansi</th>
                                            <th>Aksi</th>
                                        </tr>
                                    </thead>
                                </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="span12">      		
                    <div class="widget ">
                        <div class="widget-header">
                            <i class="icon-list"></i>
                            <h3>HISTORY PEMBAYARAN PAJAK ONLINE</h3>

                        </div> 
                        <div class="widget-content" >
                            <div class="tabbable"> 
                                <div class="table-responsive">
                                <table  class="example table table-striped table-bordered table-hover display" id="tabledata3">
                                    <thead>
                                        <tr>
                                            <th>NPWPD</th>
                                            <th>No Rekening</th>
                                            <th>Masa Pajak</th>
                                            <th>Tahun Pajak</th>
                                            <th>Jenis Setoran</th>
                                         
                                            <th>Total Omzet</th>
                                            <th>Tarif</th>
                                            <th>Total Pajak</th>
                                            <th>Keterangan</th>
                                            <th>Tanggal</th>
                                            <th>Setoran</th>
                                        </tr>
                                    </thead>
                                </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br><br><br>
</div>
<!-- /main -->
<div class="footer">
    <div class="footer-inner">
        <div class="container">
            <%@include file="wp-footer.jsp" %>
        </div>
    </div>
</div>


<style type="text/css">
        .widget{
            float:left;
            width:100%;
        }
        input, select, textarea{
            width:auto;
        }
        .container{
            width:95%;
        }
        .span6{
            width:48%;
            margin-left:1%;
        }
        .widget-content{
            padding: 20px 5px;
        }
        .span12{
            width:99%;
        }
        .example{
            width:100%;
        }
    </style>
<div id="reportSystem" class="modal fade" tabindex="-1">
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
		<input type="button" value="Simpan" id="btnSimpan" class="btn btn-success">
		<button type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
	    </div>
	    </form>
	</div>
    </div>
</div>

<!-- modal untuk report -->
<div id="reportSPT"  class="modal fade modal-lg-report modals" tabindex="-1">
    <div class="modal-dialog">
	<div class="modal-content">
	    
	    <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title"></h4>
	    </div>
	    <div class="modal-body" >
                 <div id="map" style="display:none;"> 
                    <iframe id="iniiframe" style="zoom:0.60" width="99.6%" height="520" frameborder="0"></iframe>
                 </div>
	    </div>
	    <div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
	    </div>
	</div>
    </div>
</div>

<div id="uploaddocument" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
	<div class="modal-content">
	    <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title">Proses Upload Document</h4>
	    </div>
	    <div class="modal-body">
                <form id="frm_upload_document" class="form-horizontal" action="<%=approot%>/UploadDocumentWp" method = "post" enctype = "multipart/form-data">    
                    <div id="uploaddatainput"></div>
                    <h3 class='alert alert-error'><i class='icon icon-warning-sign'></i> Upload Document</h3>
                    <p>
                        Silahkan pilih document bukti pajak yang dilaporkan<br>
                    </p>
                    <div class="control-group" id="target-11">
                        <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>">Upload Dokument <br>(pdf, jpg, jpeg, xls, xlsx) </label>
                        <div class="controls">
                            <input type = "file" name = "file" size = "50" />
                        </div>
                    </div>
                    <div class="control-group" id="target-11">
                        <div class="controls" style="margin-right: 10px">
                            <input type="submit" value="Upload Document" class="btn btn-success pull-right" id="btnSimpan">
                        </div>
                    </div> <!-- /control-grou-keterangan -->     
                </form> 
	    </div>
	    <div class="modal-footer">
	    </div>
	</div>
    </div>
</div>  

<div id="informasiuploaddocument" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
	<div class="modal-content">
	    <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title">INFORMASI</h4>
	    </div>
	    <div class="modal-body">
		<div id="reportMessage"></div>
                <%if(submitrespon.equals("ok") && !messageStringRespon.equals("EXIST")){%>
                    <h3 class='alert alert-success'><i class='icon icon-ok'></i> Berhasil Upload Data Laporan</h3><p>
                    Document laporan pajak anda dengan masa pajak bulan <b><%=masaPajakRespon%></b> dan tahun pajak <b><%=tahunPajakRespon%></b> berhasil di upload<br><br>
                <%}else if (submitrespon.equals("ok") && messageStringRespon.equals("EXIST")) {%>
                <%}else{%>
                <%}%>
	    </div>
	    <div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
	    </div>
	</div>
    </div>
</div>                        
<style>
</style>            
<script>
    <%if(submitResponUpload){%>
         $("#informasiuploaddocument").modal("show");
    <%}%>
</script>
</body>
</html>
