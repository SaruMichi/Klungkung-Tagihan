<header class="main-header">
    <!-- Logo -->
    <a href="<%=approot%>/home_slik.jsp" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>S</b>L</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Di-</b>SLIK</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
	<span class="sr-only">Toggle navigation</span>
      </a>
      <div class="navbar-custom-menu">
	<ul class="nav navbar-nav">
	  <!-- Tasks: style can be found in dropdown.less -->
	  <!-- User Account: style can be found in dropdown.less -->
	  <li class="dropdown user user-menu">
	    <a href="<%=approot%>/dslik_logout.jsp" class="dropdown-toggle">
	      <span class="hidden-xs"><b><%= userName %></b> </span>Sign Out
	      <i class="fa fa-sign-out"></i>
	    </a>
	  </li>
	</ul>
      </div>
    </nav>
  </header>
