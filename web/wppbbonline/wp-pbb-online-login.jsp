<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java"%>
<%@page import="com.dimata.wponline.form.wajibpajakonline.FrmWajibPajak"%>
<%@page import="com.dimata.util.Command"%>
<%!
    final static int MAX_SESSION_IDLE=100000;
    
%>

<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <title>Wajib Pajak Bumi dan Bagunan - Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 
    <link href="../styles/wpupload/img/favicon.ico" rel="shortcut icon" />
    <link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
    <link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
    <link href="../styles/wpupload/css/font-google.css" rel="stylesheet">    
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
                <div class="nav-collapse">
                    <ul class="nav pull-right">
                        <li class="">						
                            <a href="<%= request.getContextPath() %>/wppbbonline/wp-pbb-online-daftar.jsp" class="">
                                <i class="icon-large icon-user akun_baru"> Buat akun baru</i>
                            </a>
                        </li>
                        
                    </ul>
                </div>
            </div> 	
	</div> 
    </div> 
    
    <div id="login_place">
        <div  class="account-container">
            <div class="content clearfix">	
                <form name="<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>" id="<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>">
                    <input type="hidden" name="approot" value="<%= request.getContextPath() %>">
                    <input type="hidden" name="maxidle" value="<%= MAX_SESSION_IDLE %>">
                    <input type="hidden" name="Command" id="Command" value="<%= Command.RETRY%>">
                    <center>
                        <img src="../images/logodispenda.jpg" alt="Dispenda Gianyar"><br><br> 
                        <h1>ONLINE PAJAK</h1>
                        <h2>KABUPATEN GIANYAR</h2>	
                    </center>	
                    <div class="login-fields">
                        <br>
                        <div class="field">
                            <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>">Username</label>
                            <input type="text" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>" value="" placeholder="Nama User Anda" class="login username-field" />
                            <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>"></div>
                        </div> 				
                        <div class="field">
                            <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>">Password:</label>
                            <input type="password" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>" value="" placeholder="Password Anda" class="login password-field"/>
                            <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>"></div>
                        </div> 				
                        <div class="field">
                            <label for="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>">Captcha / Kode Keamanan</label>
                            <div style="position:relative;">
                                <button type="button" class="btn btn-success" style="position:absolute;top:0;right:0;z-index: 1;" id="reloadImage"><i class="icon icon-refresh"></i></button>
                                <img src="<%= request.getContextPath() %>/Kaptcha.jpg" id="kaptchaImage" width="100%" style="border-radius:5px;margin-bottom:4px;"/> 
                            </div>
                                <input type="text" name="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>" id="<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>" value="" placeholder="Captcha / Kode Keamanan" class="login password-field"/>
                            <div class="alert alert-danger" style="display: none;margin-top:4px;" id="error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>"></div>
                        </div> 		
                    </div> 

                    <div class="login-actions">
                        <button type="button" class="button btn btn-success btn-large" id="btnLogin">Masuk</button>
                    </div>
                    <center>
                        <a  href="<%= request.getContextPath() %>/wppbbonline/wp-pbb-online-daftar.jsp">Buat akun baru</a>
                        &nbsp; atau &nbsp;
                        <a id="btnForgotPassword" style="cursor: pointer;" >Lupa password anda?</a>
                        
                    </center>
                </form>
            </div>
        </div> 
        
        <div class="login-extra">
            <div id="result" style="display:none;text-align: center;"></div>
        </div>
       
    </div>
    
    <div id="modalForgotPassword" class="modal fade" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content" id="ajaxContent">
                
            </div>
        </div>
    </div>
  
<script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>
<script>
    $(document).ready(function(){    
        function reloadCaptcha(){
            $("#kaptchaImage").hide()
	      .attr('src', '<%= request.getContextPath() %>/Kaptcha.jpg?' + Math.floor(Math.random()*100) )
	      .fadeIn();      
        }
   
        function validateForm(){
            var errorValidate = false;
            var namaUser = $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>").val();
            var password =$("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>").val();
            var captcha =$("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>").val();
            
            if (namaUser.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Nama User...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER] %>").fadeOut();
	    }
            
            if (password.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Password...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD] %>").fadeOut();
	    }
            
            if (captcha.length == 0){
		errorValidate = true;
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Masukkan Captcha...").fadeIn("medium");
	    }else{
		$("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>").fadeOut();
	    }
            
            return errorValidate;
            
            
        }
        
        function clearForm(){
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP]%>").val("");
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_NAMA_USER]%>").val("");
            $("#<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_PASSWORD]%>").val("");
            $("#Command").val("<%= Command.RETRY%>");
        }
        
        function isValidEmailAddress(emailAddress) {
            var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
            return pattern.test(emailAddress);
        };
        
        clearForm();
        
        $('#reloadImage').click(function () {
	    $(this).attr({"disabled":true});
	    reloadCaptcha();
	    $(this).removeAttr("disabled");
	});
        
        $('#btnLogin').click(function(){
            var errorLogin = true;
            var errorPass = true;
            $("#Command").val("<%= Command.RETRY%>");
            $("#result").fadeOut();
                                
            if (validateForm()==false){
               
                $("#btnLogin").html("Mohon Tunggu...").attr({"disabled":true});
                $.ajax({
                    type: "POST",
                    url	: "<%= request.getContextPath() %>/AjaxWajibPajak",
                    data: $('#<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>').serialize(),
                    cache: false,
                    success: function(data){
                        //alert(data);
                        if (data>0){
                            errorLogin = true;
                            $("#error_<%= FrmWajibPajak.fieldNames[FrmWajibPajak.FRM_FIELD_TELP] %>").html("<b><i class='icon icon-warning-sign'></i> Kesalahan : </b>Captcha tidak sesuai...").fadeIn("medium");
                            reloadCaptcha();
                            $("#btnLogin").removeAttr("disabled").html("Masuk");
                        }else{
                            errorLogin = false;
                        }
                    },
                    error : function(data){
                       
                    }
                }).done(function() {
                    if (errorLogin==false){
                        $("#Command").val("<%= Command.LOGIN%>");
                        $.ajax({
                            type: "POST",
                            url	: "<%= request.getContextPath() %>/AjaxWajibPajak",
                            data: $('#<%= FrmWajibPajak.FRM_WAJIB_PAJAK%>').serialize(),
                            cache: false,
                            success: function(data){
                                if (data>0){
                                    errorPass = true;
                                }else{
                                    errorPass = false;
                                }
                            },
                            error : function(data){

                            }
                        }).done(function() {
                            if (errorPass==false){
                                window.location="<%= request.getContextPath() %>/wppbbonline/wp-pbb-online-home.jsp";       
                            }else{
                                $("#btnLogin").removeAttr("disabled").html("Masuk");
                                $("#result").html("<div class='alert alert-danger' role='alert'><i class='glyphicon glyphicon-warning-sign'></i> Kesalahan! Nama User dan Password tidak sesuai...  </div>");
                                $("#result").fadeIn();
                                
                            }
                        });
                    }
                });
            }
        });
        
        $('.login').keyup(function(){
            var id = $(this).attr('id');
            var iderror = "error_" + id;
            $('#'+iderror+'').fadeOut();
        });
        
        $( ".login" ).keypress(function( event ) {
            if ( event.which == 13 ) {
              $('#btnLogin').trigger('click')
            }
        });
        
        $('#btnForgotPassword').click(function(){
            $.ajax({
                type: "GET",
                url: "<%= request.getContextPath() %>/AjaxWajibPajak",
                data: "Command=<%= Command.FIRST%>",
                cache: false,
                success: function(data){
                   $('#ajaxContent').html(data);
                   $('#modalForgotPassword').modal({
                        backdrop: 'static',
                        keyboard: false
                    });
                },
                error : function(data){

                }
            }).done(function() {
               $('#btnKirim').click(function(){
                    var emailUser = $('#emailUser').val();
                    if (emailUser.length == 0){
                       $('#modal-error').html("<b><i class='icon-warning-sign'></i> Kesalahan :</b> Masukkan alamat email...").fadeIn();
                       $('#btnKirim').focus();
                    }else if(!isValidEmailAddress(emailUser)){
                       $('#modal-error').html("<b><i class='icon-warning-sign'></i> Kesalahan :</b> Alamat email tidak valid").fadeIn();
                       $('#btnKirim').focus();
                    }else{
                        $.ajax({
                            type: "GET",
                            url: "<%= request.getContextPath() %>/AjaxWajibPajak",
                            data: "Command=<%= Command.NEXT%>",
                            cache: false,
                            success: function(data){
                               $('#ajaxContent').html(data);
                              
                            },
                            error : function(data){

                            }
                        }).done(function() {
                             $('#btnTutup2').attr({"disabled":true});
                             $('#btnTutup1').attr({"disabled":true});
                             //proses checking email
                             
                            $.ajax({
                                type: "GET",
                                url: "<%= request.getContextPath() %>/AjaxWajibPajak",
                                data: "Command=<%= Command.SEARCH%>&email="+emailUser+"&context=<%= request.getContextPath()%>",
                                cache: false,
                                success: function(data){
                                   $('#ajaxContent').html(data);

                                },
                                error : function(data){

                                }
                            }).done(function() {
                                 

                            });
                             
                        });
                    }
                   
                   $('#emailUser').keyup(function(){
                       $('#modal-error').fadeOut('medium');
                   });
               });
            });
        });
    });
    
</script>
<script src="../styles/wpupload/js/bootstrap.js"></script>
<script src="../styles/wpupload/js/signin.js"></script>

</body>
</html>
