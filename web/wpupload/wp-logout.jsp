<%@page  language="java" %>
<!DOCTYPE html>
<%@include file="../main/javainit-wp.jsp" %>
<%
try{
	if(userSessionWp.isLoggedIn()==true){
	    userSessionWp.printAppUser();
	    userSessionWp.doLogout(); 
	    session.removeValue(SessUserWPSession.HTTP_SESSION_WPNAME);
	}
        if(userSessionWp!=null){
            session.removeValue(SessUserWPSession.HTTP_SESSION_WPNAME);
        }
}catch (Exception exc){
  System.out.println(" ==> Exception during logout user");
}
%>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <title>Keluar Sistem - Wajib Pajak</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"> 
	<link href="../styles/wpupload/img/favicon.ico" rel="shortcut icon" />    
	<link href="../styles/wpupload/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="../styles/wpupload/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
	
	<link href="../styles/wpupload/css/font-awesome.css" rel="stylesheet">
	    
	<link href="../styles/wpupload/css/style.css" rel="stylesheet" type="text/css" />

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
			
			<a class="brand" href="<%= approot %>">
				Wajib Pajak				
			</a>		
			
			<div class="nav-collapse">
				<ul class="nav pull-right">
					
					<li class="">						
						<a href="<%= approot %>" class="">
							<i class="icon-chevron-left"></i>
							Kembali ke halaman login
						</a>
						
					</li>
				</ul>
				
			</div><!--/.nav-collapse -->	
	
		</div> <!-- /container -->
		
	</div> <!-- /navbar-inner -->
	
</div> <!-- /navbar -->



<div class="container">
	
	<div class="row">
		
		<div class="span12">
			
			<div class="error-container">
				<h2>Anda berhasil keluar dari sistem</h2>
				
				<div class="error-details">
					Terima kasih telah menggunakan sistem kami
					
				</div> <!-- /error-details -->
				
				<div class="error-actions">
					<a href="<%= approot %>" class="btn btn-large btn-primary">
						<i class="icon-chevron-left"></i>
						&nbsp;
						Kembali ke halaman Login						
					</a>
					
					
					
				</div> <!-- /error-actions -->
							
			</div> <!-- /error-container -->			
			
		</div> <!-- /span12 -->
		
	</div> <!-- /row -->
	
</div> <!-- /container -->


<script src="../styles/wpupload/js/jquery-1.7.2.min.js"></script>
<script src="../styles/wpupload/js/bootstrap.js"></script>

</body>

</html>
