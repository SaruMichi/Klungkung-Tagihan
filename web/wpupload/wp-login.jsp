<%@page import="com.dimata.webclient.AppSetting"%>
<%@page import="com.dimata.wpupload.session.SessUserWPSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@page import="com.dimata.wpupload.form.wpuser.FrmAppUserWP"%>
<%!
    final static int MAX_SESSION_IDLE=100000;
    
%>

<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <title>Wajib Pajak - Login</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 
    <link href="../styles/wpupload/img/favicon.ico" rel="shortcut icon" />
<link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />

<link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
    <!--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">-->
    
<link href="../styles/wpupload/css/style.css" rel="stylesheet" type="text/css">
<link href="../styles/wpupload/css/pages/signin.css" rel="stylesheet" type="text/css">

</head>

<body>
	
	<div class="navbar navbar-fixed-top">
	
	<div class="navbar-inner">
		
		<div class="container">
			
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			
			<a class="brand" href="#">
				Wajib Pajak				
			</a>		
			
			
	
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->



<div class="account-container">
	
	<div class="content clearfix">
		
	    <form name="<%= FrmAppUserWP.FRM_NAME_APPUSERWP %>" id="<%= FrmAppUserWP.FRM_NAME_APPUSERWP %>">
			<input type="hidden" name="approot" value="<%= request.getContextPath()%>">
			<input type="hidden" name="maxidle" value="<%= MAX_SESSION_IDLE %>">
			<center><img src="../imgcompany/dimata_system_logo.png" alt="<%=AppSetting.NAMA_KABUPATEN%>"><br><br> 
			<h1>E-SPTPD</h1>
			<h2><%=AppSetting.NAMA_KABUPATEN%></h2>	
			</center>	
			<div class="login-fields">
			    <br>
				<div class="field">
                                    <label for="<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_USER_ID] %>">Username</label>
                                    <input type="text" id="<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_USER_ID] %>" name="<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_USER_ID] %>" value="" placeholder="User ID Anda" class="login username-field" />
                                    <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_USER_ID] %>"></div>
				</div> <!-- /field -->
				
				<div class="field">
					<label for="<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_PASSWORD] %>">Password:</label>
					<input type="password" id="<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_PASSWORD] %>" name="<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_PASSWORD] %>" value="" placeholder="Password Anda" class="login password-field"/>
					<div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_PASSWORD] %>"></div>
				</div> <!-- /password -->
				
				<div class="field">
					<label for="<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_NO_TELP] %>">Captcha / Kode Keamanan</label>
					
					<div style="position:relative;">
					    <button type="button" class="btn btn-success" style="position:absolute;top:0;right:0;z-index: 1;" id="reloadImage"><i class="icon icon-refresh"></i></button>
					    <img src="<%= request.getContextPath() %>/Kaptcha.jpg" id="kaptchaImage" width="100%" style="border-radius:5px;margin-bottom:4px;"/> 
					    
					</div>
					<input type="text" id="kaptcha" name="kaptcha" value="" placeholder="Captcha / Kode Keamanan" class="login password-field"/>
										
					<div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_kaptcha"></div>
				</div> <!-- /phone -->
				
			</div> <!-- /login-fields -->
			
			<div class="login-actions">
				
				<!--
				<span class="login-checkbox" style>
					<input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
					<label class="choice" for="Field">Keep me signed in</label>
				</span>
				-->
				
				<button class="button btn btn-success btn-large" id="btnLogin">Masuk</button>
				
			</div> <!-- .actions -->
		</form>
		
	</div> <!-- /content -->
	
</div> <!-- /account-container -->
<div class="login-extra">
    <div id="result" style="display:none;text-align: center;"></div>
</div>


<!--
<div class="login-extra">
	<a href="#">Reset Password</a>
</div>
--> <!-- /login-extra -->


<script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
	
	$('#reloadImage').click(function () {
	    
	    $(this).attr({"disabled":true});
	    $("#kaptchaImage").hide()
	      .attr('src', '<%= request.getContextPath() %>/Kaptcha.jpg?' + Math.floor(Math.random()*100) )
	      .fadeIn(); 
	    $(this).removeAttr("disabled");
	});
	$("form#<%= FrmAppUserWP.FRM_NAME_APPUSERWP %>").submit(function(){
	    var errorLogin = false;
	    var username = $("#<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_USER_ID] %>").val();
	    var password = $("#<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_PASSWORD] %>").val();
	    var kaptcha = $("#kaptcha").val();
	    
	    if (username.length == 0){
		errorLogin = true;
		$("#error_<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_USER_ID] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan User ID Anda").fadeIn("medium");
	    }else{
		$("#error_<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_USER_ID] %>").fadeOut();
	    }
	    
	    if (password.length == 0){
		errorLogin = true;
		$("#error_<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_PASSWORD] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Password Anda").fadeIn("medium");
	    }else{
		$("#error_<%= FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_PASSWORD] %>").fadeOut();
	    }
	    
	    if (kaptcha.length == 0){
		errorLogin = true;
		$("#error_kaptcha").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Captcha / Kode Keamanan").fadeIn("medium");
	    }else{
		$("#error_kaptcha").fadeOut();
	    }
	    
	    if(errorLogin == false){
		
		$("#result").hide();
		$("#btnLogin").html("Mohon Tunggu...").attr({"disabled":true});
		$.ajax({
			type	: "POST",
			url	: "<%= request.getContextPath() %>/WPLoginHandler",
			data	: $(this).serialize(),
			cache	: false,
			success	: function(data){
			    $("#btnLogin").removeAttr("disabled").html("Masuk");
			    if(data == <%= SessUserWPSession.DO_LOGIN_OK %>){
				$("#result").html("<b><i class='icon icon-ok-sign'></i> Sukses : </b>Login Berhasil<br>Memuat Halaman Utama").removeClass("alert alert-error").addClass("alert alert-success").fadeIn("medium");
				window.location = "<%= request.getContextPath() %>/wpupload/wp-home.jsp";
			    }else{
				$("#result").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Login Gagal<br>User ID, Password atau Captcha/Kode Keamanan Anda Salah").removeClass("alert alert-success").addClass("alert alert-error").fadeIn("medium");
			    }
			    
			    
			},
			error : function(data){
				$("#result").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Login Gagal ");
				$("#btnLogin").removeAttr("disabled").html("Masuk");
			}
		});
	    }
	    return false;
	});
    })
</script>
<script src="../styles/wpupload/js/bootstrap.js"></script>

<script src="../styles/wpupload/js/signin.js"></script>

</body>

</html>
