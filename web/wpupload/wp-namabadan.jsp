<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.wpupload.form.esptpd.FrmESPTPD"%>
<%@page import="com.dimata.wpupload.form.wpnamabadan.FrmNamaBadan"%>
<%@page import="com.dimata.wpupload.entity.esptpd.PstESPTPD"%>
<%@page import="com.dimata.wpupload.entity.wpuser.PstAppUserWP"%>
<%@page import="com.dimata.wpupload.entity.wpnamabadan.PstNamaBadan"%>
<%@page import="com.dimata.wpupload.entity.wpnamabadan.NamaBadan"%>
<%@page import="com.dimata.wpupload.entity.esptpd.ESPTPD"%>
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
    Vector listNamaBadan = new Vector(1,1);
    Vector listESPTPD = new Vector (1,1);
    
    
    ESPTPD esptpd = new ESPTPD();
    NamaBadan namaBadan = new NamaBadan();
    
    listNamaBadan = PstNamaBadan.listJoin(0, 0, 
	    "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+ userIdWP +"'", "");
    
    listESPTPD = PstESPTPD.listJoin(0, 0, 
	    "viewUser."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_USER_ID]+"='"+ userIdWP +"'", "");
    
    if(listNamaBadan.size() != 0){
	namaBadan = (NamaBadan) listNamaBadan.get(0);
    }
    
    if(listESPTPD.size() != 0){
	esptpd = (ESPTPD) listESPTPD.get(0);
    }
    
    boolean submitResponUpload=false;
    if(submitrespon.equals("ok")){
        submitResponUpload=true;
    }
%>
  
<head>
    <meta charset="utf-8">
    <title>Pelaporan Pajak - Wajib Pajak</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">    
    <link href="../styles/wpupload/img/favicon.ico" rel="shortcut icon" />
    <link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet">
    <link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet">
    
    <!--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">-->
    <link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
    
    <link href="../styles/wpupload/css/style.css" rel="stylesheet">
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
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->
    
<div class="subnavbar">

	<div class="subnavbar-inner">
	
		<div class="container">

			<%
			    String activeHome = "";
                            String activeManualTagihan="";
			    String activeNamaBadan="active";
			    String activeList="";
                            String activeNamaBadanPanduan="";
			%>
			<%@include file="wp-navbar.jsp" %>

		</div> <!-- /container -->
	
	</div> <!-- /subnavbar-inner -->

</div> <!-- /subnavbar -->
    
    
<div class="main">
	
	<div class="main-inner">

	    <div class="container">
	
	      <div class="row">	      	
	      	<div class="span6">
	      		
	      		<div class="widget">
			    <div class="widget-header">
                                <button type="button" class="btn btn-sm btn-default showhide" style="margin-left:5px;"><i class="icon icon-caret-down" style="margin-left:0px;"></i></button>
				<!--i class="icon icon-info-sign"></i--> 
				<h3>INFORMASI NAMA BADAN</h3>
			    </div>
	      			<div class="widget-content showhidecontent">
				    <div class="form-horizontal">
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NPWPD] %>">No NPWPD</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NPWPD] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NPWPD] %>" class="span4" value="<%= namaBadan.getNbNPWPD() %>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-NPWPD -->
					    
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA] %>">Nama Wajib Pajak</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA] %>" class="span4" value="<%= namaBadan.getNbNama()%>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-nama-wajib-pajak -->
					
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA_BADAN] %>">Nama Badan</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA_BADAN] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_NAMA_BADAN] %>" class="span4" value="<%= namaBadan.getNbNamaBadan()%>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-nama-badan -->
					
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_ALAMAT] %>">Alamat</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_ALAMAT] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_ALAMAT] %>" class="span4" value="<%= namaBadan.getNbAlamat()%>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-alamat -->
					
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KELURAHAN] %>">Kelurahan</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KELURAHAN] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KELURAHAN] %>" class="span4"  value="<%= namaBadan.getNbKelurahan()%>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-kelurahan -->
					
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KECAMATAN] %>">Kecamatan</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KECAMATAN] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KECAMATAN] %>" class="span4" value="<%= namaBadan.getNbKecamatan()%>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-kecamatan -->
					
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KABUPATEN] %>">Kabupaten</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KABUPATEN] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KABUPATEN] %>" class="span4" value="<%= namaBadan.getNbKabupaten()%>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-kabupaten -->
					
					<div class="control-group">
					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TGL_REKAM] %>">Tanggal</label>
					    <div class="controls">
						<input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TGL_REKAM] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TGL_REKAM] %>" class="span4" value="<%= Formater.formatDate(new Date(), "yyyy-MM-dd")%>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-tgl rekam -->
					
					<div class="control-group">

					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_REKAM] %>">User ID Rekam</label>
					    <div class="controls">
						<input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_REKAM] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_REKAM] %>" class="span4" value="<%= namaBadan.getNbUserId()%>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-tgl rekam -->
				    </div>
		      		</div> <!-- /widget-content -->
		      		
	      		</div> <!-- /widget -->
			<div class="widget">
			    <div class="widget-header">
                                <button type="button" class="btn btn-sm btn-default showhide" style="margin-left:5px;"><i class="icon icon-caret-down" style="margin-left:0px;"></i></button>
				<!--i class="icon icon-info-sign"></i-->
				<h3>INFORMASI PENTING</h3>
			    </div>
	      			<div class="widget-content showhidecontent">
				   
				    <div class="form-horizontal">
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_USAHA] %>">Pajak</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_USAHA] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_USAHA] %>" class="span1" readonly="readonly" value="<%= namaBadan.getNbKDJenisUsaha() %>"> &nbsp; 
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_JENIS_USAHA] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_JENIS_USAHA] %>" class="span3" readonly="readonly" value="<%= namaBadan.getNbJenisUsaha()%>">
					    </div>
					</div> <!-- /control-group-pajak -->
					
					<div class="control-group">

					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_WP] %>">Rekening</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_WP] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KODE_JENIS_WP] %>" class="span1" readonly="readonly" value="<%= namaBadan.getNbKDJenisWP()%>"> &nbsp; 
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_SUBJENIS_WP] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_SUBJENIS_WP] %>" class="span3" readonly="readonly" value="<%= namaBadan.getNamaJenisWP()%>">
					    </div>
					</div> <!-- /control-group-Rekening -->
					
					<div class="control-group">
					    <label class="control-label" for="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI] %>">Sub Rekening</label>
					    <div class="controls">
						<input type="text" id="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI] %>" name="<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI] %>" class="span1" readonly="readonly" value="<%= namaBadan.getNoSubRekening()%>"> &nbsp; 
						<input type="text" id="<%= "sublokasi" %>" name="<%= "sublokasi" %>" class="span3" readonly="readonly" value="<%= namaBadan.getNbSubjenisWP()%>">
					    </div>
					</div> <!-- /control-group-Rekening -->
                                        <div class="control-group">
					    <label class="control-label">Lokasi</label>
					    <div class="controls">
						<input type="text" id="xx" class="span1" readonly="readonly" value="<%=namaBadan.getKdLokasi()%>">&nbsp; 
					    </div>
					</div> <!-- /control-group-Rekening -->
				    </div>
		      		</div> <!-- /widget-content -->
		      		
	      		</div> <!-- /widget -->
	      		
      		</div> <!-- /span6 -->
		
		<div class="span6">
	      		
	      		<div class="widget">
			    <div class="widget-header">
                                <button type="button" class="btn btn-sm btn-default showhide" style="margin-left:5px;"><i class="icon icon-caret-down" style="margin-left:0px;"></i></button>
				<!--i class="icon icon-reorder"></i--> 
				<h3>PELAPORAN PAJAK</h3>
			    </div>
	      			<div class="widget-content showhidecontent" id="pelaporanpajak">
				    <form  class="form-horizontal" id="<%= FrmESPTPD.FRM_NAME_ESPTPD %>" method="post">
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NPWPD] %>" value="<%= namaBadan.getNbNPWPD() %>">
					<input type="hidden" name="command" value="<%= Command.SAVE %>">
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NIP_REKAM] %>" value="<%= namaBadan.getNbNip() %>">
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NIP_UBAH] %>" value="<%= namaBadan.getNbNip() %>">
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_REKAM] %>" value="<%= namaBadan.getNbUserId() %>">
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_ID_UBAH] %>" value="<%= namaBadan.getNbUserId() %>">   
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_PAJAK] %>" value="<%= namaBadan.getNbKDJenisUsaha()%>">
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_REKENING] %>" value="<%= namaBadan.getNbKDJenisWP()%>">
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_SUBREKENING] %>" value="<%= namaBadan.getNoSubRekening()%>">
					<input type="hidden" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_LOKASI] %>" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_LOKASI] %>" value="<%=namaBadan.getKdLokasi()%>">
					<input type="hidden" name="approot" value="<%= approot %>">
					<div class="control-group">

					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_SPTPD] %>">No SPTPD</label>
					    <div class="controls">
						<input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_SPTPD] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_NO_SPTPD] %>" class="span4" required="required" disabled="true" placeholder="Otomatis">
					    </div>
					</div> <!-- /control-group-NPWPD -->
					    
					<div class="control-group">

					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK] %>">Masa Pajak</label>
					    <div class="controls" id="target-1">
						<%
						    Calendar cal = Calendar.getInstance();
						    cal.setTime(new Date());
						    int month = cal.get(Calendar.MONTH);
						    String monthDisplay = "";
							int currentYear =  cal.get(Calendar.YEAR);//Calendar.getInstance().get(Calendar.YEAR);
							if(month==0){
								monthDisplay=""+12;
								month=12;
								currentYear=currentYear-1;
							}else{
								if(month<10){
								monthDisplay="0"+month;
								}else{
								monthDisplay=""+month;
								}
							}
						    
						    Vector month_key = new Vector(1,1);
						    Vector month_value = new Vector(1,1);
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
							"Desember"};
						    for(int i=0;i<12;i++){
							int getDisplayKey = i+1;
							if(getDisplayKey<10){
							    month_key.add("0"+getDisplayKey);
							}else{
							    month_key.add(""+getDisplayKey);
							}
							month_value.add(""+indoMonth[i]);
						    }
						%>
						<%= ControlCombo.drawBootsratap(FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK], null, monthDisplay, month_key, month_value, "required='required'", "span4 period") %>
					    </div>
					</div> <!-- /control-group-masa-pajak -->
					
					<div class="control-group" id="target-2">

					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK] %>">Tahun</label>
					    <div class="controls">
						<%
						    //int currentYear =  cal.get(Calendar.YEAR);//Calendar.getInstance().get(Calendar.YEAR);
						    Vector years_key = new Vector(1,1);
						    Vector years_value = new Vector(1,1);
						    for(int i=currentYear-5;i<=currentYear;i++){
							years_key.add(""+i);
							years_value.add(""+i);
						    }
						%>
						<%= ControlCombo.drawBootsratap(FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK], null, String.valueOf(currentYear), years_key, years_value, "required='required'", "span4 period") %>
					    </div>
					</div> <!-- /control-group-tahun-pajak -->
					
					<div class="control-group" id="target-3">

					    <label class="control-label" for="<%= "jatuh_tempo" %>">Tanggal Jatuh Tempo</label>
					    <div class="controls">
						<input type="text" id="<%= "jatuh_tempo" %>" name="<%= "jatuh_tempo" %>" class="span4" readonly="readonly" required="required">
					    </div>
					</div> <!-- /control-group-jatuh-tempo -->
					
					<div class="control-group" id="target-4">
					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>">Jumlah Omset</label>
					    <div class="controls">
                                                <input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>" class="span4 calPajak inputform" >
					    </div>
					</div> <!-- /control-group-jumlah-omzet -->
                                        <!-- jumlah service -->
                                        <div class="control-group" id="target-5">
					    <label class="control-label" for="<%=FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>">Jumlah Service</label>
					    <div class="controls">
						<input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>" class="span4 calPajak inputform" >
					    </div>
					</div>
					<div class="control-group" id="target-6">

					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF] %>">Tarif</label>
					    <div class="controls">
						<input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF] %>" class="span4 calPajak" required="required" value="<%= namaBadan.getNbTarif() %>" readonly="readonly">
					    </div>
					</div> <!-- /control-group-tarif -->
					
					<div class="control-group" id="target-7">

					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK] %>">Jumlah Pajak</label>
					    <div class="controls">
						<input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK] %>" class="span4 calPajak" required="required" value="0" readonly='readonly'>
					    </div>
					</div> <!-- /control-group-jumlah-pajak -->
					
					<div class="control-group" id="target-8">

					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>">Service Tax</label>
					    <div class="controls">
						<input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>" class="span4 calPajak" required="required" value="0" readonly="readonly">
					    </div>
					</div> <!-- /control-group-tgl rekam -->
					
					<div class="control-group" id="target-9">

					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>">Harus Dibayar</label>
					    <div class="controls">
						<input type="text" id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>" class="span4" required="required" readonly='readonly'>
					    </div>
					</div> <!-- /control-group-tgl rekam -->
					
					<div class="control-group" id="target-10">
					    <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>">Keterangan</label>
					    <div class="controls">
						<textarea id="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>" name="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>" class="span4 inputform"  ></textarea>
					    </div>
                                        </div>
				    </form>
                                            
                                    <form id="frm_upload_document" class="form-horizontal" action="<%=approot%>/UploadDocumentWp" method = "post" enctype = "multipart/form-data">    
					<div class="control-group" id="target-11">
                                            <label class="control-label" for="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>">Upload Dokument Bukti Pelaporan dari Penjualan<br>(pdf, jpg, jpeg, xls, xlsx) </label>
					    <div class="controls">
						<input type = "file" name = "file" size = "50" id="fileupload" />
					    </div>
                                        </div>
                                    </form>       
                                    
                                    <div class="control-group" id="target-11">
                                        <div class="controls" style="margin-right: 10px">
						<input type="button" value="Simpan" class="btn btn-success pull-right" id="btnSimpan">
					    </div>
					</div> <!-- /control-grou-keterangan -->
		      		</div> <!-- /widget-content -->
		      		
	      		</div> <!-- /widget -->
	      		
      		</div> <!-- /span6 -->
	      </div> <!-- /row -->
	    </div> <!-- /container -->
	</div> <!-- /main-inner -->
</div> <!-- /main -->
<div class="footer">
	
	<div class="footer-inner">
		
		<div class="container">
			
		    <%@include file="wp-footer.jsp" %>
    		
		</div> <!-- /container -->
		
	</div> <!-- /footer-inner -->
	
</div> <!-- /footer -->
    

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>

<script src="../styles/wpupload/js/bootstrap.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
	
	//MODAL OPTIONS SETTING
	$("#reportSystem").modal({
	    backdrop : "static",
	    keyboard : false,
	    show : false
	});
         
	//GET PERIOD DUE DATE ON LOAD
	function loadTempo(){
	    var masaPajak = parseInt($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK] %>").val());
	    var tahunPajak = parseInt($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK] %>").val());
	    var months = ["Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus","September","Oktober", "November", "Desember"];
	    var displayMasaPajak = "";
            //alert(masaPajak+" - "+tahunPajak)
	    if(masaPajak > 11){
		displayMasaPajak = months[0];
		tahunPajak = tahunPajak+1;
	    }else{
		displayMasaPajak = months[masaPajak];
		tahunPajak = tahunPajak;
	    }
	    $("#jatuh_tempo").val("15 "+ displayMasaPajak + " "+tahunPajak);
            $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").focus();
	}
	loadTempo();
	
	
	//GET PERIOD DUE DATE ON MASA PAJAK OR TAHUN CHANGED
	$(".period").change(function(){
	    loadTempo();
	})
	
        $(".inputform").on('keydown', function(event) {
            if (event.which == 13 ) {
               if(this.name=="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>"){
                  // alert("1");
                   $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>").focus();
                   
               }else if(this.name=="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>"){
                   //alert("2");
                   $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>").focus();
                   
               }else if(this.name=="<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>"){
                  // alert("3");
                    $("#btnSimpan").focus();
               }
            }
        });
        
	//CALCULATION AMOUNT OF PAJAK
	$(".calPajak").on('keyup', function(event) {
	    var sumPajak = 0;
	    var mustPaid = 0;
	    var omset = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").val());
	    var tarif = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TARIF] %>").val());
	    var serviceTax = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>").val());
	    var jumlahService = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_SERVICE] %>").val());
    
            //alert("omser "+omset);
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
            
            /*if(isNaN(serviceTax)){
                serviceTax=0;
            }else{
                jumlahService=Math.round((serviceTax*tarif));
            }*/
            
	    mustPaid = Math.round(sumPajak+serviceTax);
            
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>").val(serviceTax);
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK] %>").val(sumPajak);
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>").val(mustPaid);
	});
	
	
	//DUPLICATE VALUE OF KODE LOKASI AND RELOCATE TO ANOTHER FIELD
	$("#<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI] %>").keyup(function(){
	   var valueGet = $(this).val();
	   $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_LOKASI] %>").val(valueGet);
	});
	
	
	function resetForm(){
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KODE_LOKASI] %>").val("");
	    $("#<%= FrmNamaBadan.fieldNames[FrmNamaBadan.FRM_FIELD_KD_LOKASI] %>").val("");
	    $("#sublokasi").val("");
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK] %>").val("<%= monthDisplay %>");
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK] %>").val("<%= currentYear %>");
	    loadTempo();
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").val("");
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAH_PAJAK] %>").val("");
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_SERVICE_TAX] %>").val("");
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>").val("");
	    $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_KETERANGAN] %>").val("");
	    
	}
	
	//SAVE DATA
	$("#btnSimpan").click(function(){
            //buatkan konfirmasi apakah data sudah benar
            var dataomzet = parseFloat($("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_JUMLAM_OMZET] %>").val());
            var dataservice = parseFloat($("#FRM_FIELD_E_JUMLAM_SERVICE").val());
            var dataketerangan = $("#FRM_FIELD_E_KETERANGAN").val();
            var fileupload = $("#fileupload").val();
            if(isNaN(dataomzet)){    
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
            
            /*if(fileupload==""){
                alert("Document belum di pilih, silahkan browse document terlebih dahulu");
                $("#fileupload").focus();
                return false;
            }*/
            
            var datadibayar=$("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_HARUS_DIBAYAR] %>").val();
            if (confirm('Total pajak yang harus di laporkan : Rp.'+datadibayar+' ?')) {  
                var error = false
                var masaPajak = $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_MASA_PAJAK] %>").val();
                var tahunPajak = $("#<%= FrmESPTPD.fieldNames[FrmESPTPD.FRM_FIELD_E_TAHUN_PAJAK] %>").val();
                var comparePeriod = tahunPajak+""+masaPajak;
                var currentPeriod = <%= Formater.formatDate(new Date(),"yyyyMM") %>;

                if(currentPeriod <= comparePeriod){
                    error = true;
                    var getErrorMessage = $("#errorMessage").html();
                    $("#reportMessage").html(getErrorMessage).show();
                    $("#reportSystem").modal("show");
                }
                if(error == false){
                    $("#btnSimpan").attr({"value":"Mohon Tunggu...","disabled":true});
                    $.ajax({
                          type	    : "POST",
                          url	    : "<%= approot %>/PelaporanPajakHandler?user=<%= userIdWP%>",
                          data	    : $("#FRM_NAME_ESPTPD").serialize(),
                          cache	    : false,
                          success    : function(data){
                              $("#btnSimpan").removeAttr("disabled").attr({"value":"Simpan"});
                              $("#reportMessage").html(data).show();
                              $("#loadSystem").modal("show");
                              $("#frm_upload_document").append(data);
                              $('#frm_upload_document').submit();
                              //$("#reportSystem").modal("show");
                          },
                          error : function(data){
                                  alert(data);
                          }
                     }).done(function(){
                         var msgString = $("#msgString").val();
                         if(msgString != "EXIST"){
                            resetForm(); 
                        }
                     });
                }
            }  
	    return false;
	});
        
        $(".showhide").click(function(){
            var caret = $(this).find(".icon-caret-down").length;
            if(caret > 0){
                $(this).html("<i class='icon icon-caret-right' style='margin-left:0px;'></i>");
            }else{
                $(this).html("<i class='icon icon-caret-down' style='margin-left:0px;'></i>");
            }
            $(this).parents(".widget").find(".showhidecontent").slideToggle();
        });
        var width = $(window).width();
        if(width < 768){
            $(".showhidecontent").hide();
            $(".showhide").html("<i class='icon icon-caret-right' style='margin:0px;'></i>");
            $("#pelaporanpajak").parent().find(".showhide").html("<i class='icon icon-caret-down' style='margin:0px;'></i>");
            $("#pelaporanpajak").show();
        }
    });
</script>
<div id="errorMessage" style="display:none;">
    <h3 class='alert alert-error'><i class='icon icon-warning-sign'></i> Gagal Menyimpan Laporan Pajak</h3>
    <p>
	Tidak bisa menyimpan laporan pajak anda pada periode berjalan dan periode kedepan<br>
	Periode berjalan saat ini adalah <b><%= indoMonth[month-1] %> <%= currentYear %></b>
    </p>
</div>
<div id="reportSystem" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
	<div class="modal-content">
	    <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title">INFORMASI</h4>
	    </div>
	    <div class="modal-body">
		<div id="reportMessage"></div>
                <%if(submitrespon.equals("ok") && !messageStringRespon.equals("EXIST")){%>
                    <h3 class='alert alert-success'><i class='icon icon-ok'></i> Berhasil Menyimpan Laporan Pajak</h3><p>
                    Laporan pajak anda dengan masa pajak bulan <b><%=masaPajakRespon%></b> dan tahun pajak <b><%=tahunPajakRespon%></b> berhasil disimpan<br><br>
                    Daftar laporan pajak anda dapat dilihat pada halaman <b><a href="<%=approot%>/wpupload/wp-listlaporan.jsp">Daftar Pelaporan Pajak</a></b>
                <%}else if (submitrespon.equals("ok") && messageStringRespon.equals("EXIST")) {%>
                    <h3 class='alert alert-error'><i class='icon icon-warning-sign'></i> Gagal Menyimpan Laporan Pajak</h3>
		    <p>
			Laporan pajak anda dengan masa pajak bulan  <b><%=masaPajakRespon%></b> dan tahun pajak <b><%=tahunPajakRespon%></b> sudah ada, mohon cek kembali laporan pajak anda<br><br>
			Daftar laporan pajak anda dapat dilihat pada halaman <b><a href="<%=approot%>/wpupload/wp-listlaporan.jsp">Daftar Pelaporan Pajak</a></b>
		    </p>
		    <input type='hidden' name='messageString' value='"+msgString+"' id='msgString'>
                <%}else{%>
                    <h3 class='alert alert-error'><i class='icon icon-warning-sign'></i> Gagal Menyimpan Laporan Pajak</h3>
                    <p>
                        Tidak bisa menyimpan laporan pajak anda pada periode berjalan dan periode kedepan<br>
                    </p>
                <%}%>
	    </div>
	    <div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-danger">Tutup</button>
	    </div>
	</div>
    </div>
</div>
            
<div id="loadSystem" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
	<div class="modal-content">
	    <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="modal-title">Mohon Menunggu</h4>
	    </div>
	    <div class="modal-body">
		<div id="loadSystem"></div>
	    </div>
	    <div class="modal-footer">
	    </div>
	</div>
    </div>
</div>  
            
<script>
    <%if(submitResponUpload){%>
         $("#reportSystem").modal("show");
    <%}%>
</script>
<script src="../styles/wpupload/js/base.js"></script>

  </body>
</html>
