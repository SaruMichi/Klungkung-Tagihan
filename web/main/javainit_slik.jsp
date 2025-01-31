<%@page import="com.dimata.dslik.entity.masterdata.PstPeriode"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dslik.entity.masterdata.Periode"%>
<%@page import="com.dimata.dslik.session.admin.SessUserSession"%>
<%@page import="com.dimata.dslik.entity.admin.AppUser"%>
<%@page import="com.dimata.dslik.session.admin.SessAppUser"%>
<%!
    final static int MODUS_USER_ONLINE=0;
    final static int MODUS_USER_OFFLINE=1;
    static int MODUS_USER_LOGIN =MODUS_USER_OFFLINE;
%>    
<%
    
     
    
    String skin = "skin-green-light fixed";
    System.out.println("request.getRequestedSessionId() : "+request.getRequestedSessionId());
    System.out.println("request.getRequestedSessionId() : "+request.getRemoteHost());
    System.out.println("request.getRequestedSessionId() : "+request.getRemoteHost());
    System.out.println("request.getRequestedSessionId() : "+request.getServerName());

    // application path
    String approot=request.getContextPath();
    String printrootx=approot+"/servlet/com.dimata.dtaxintegration";

    int SETTING_DEFAULT = 0;
    int SETTING_COLONIAS = 1;
    int specsetting = SETTING_COLONIAS;

    /** Variabel jenis Menu */
    int MENU_DEFAULT = 0;
    int MENU_PER_TRANS = 1;
    int MENU_ICON = 2;

    // user is logging in or not
    boolean isLoggedIn = false;
    int userGroupNewStatus = -1;
    String userName = "";
    long userId = 0;
    long accountId = 0;
    String kd_bank="";
    int getUserGroup = 0;
    //add session 20120711 by mirahu 
    session.setMaxInactiveInterval(60 * 60 * 2);
    SessUserSession userSession = (SessUserSession) session.getValue(SessUserSession.HTTP_SESSION_NAME);
    try{
	if(userSession==null){
	    userSession= new SessUserSession();
	}else{
	    if(userSession.isLoggedIn()==true){
		isLoggedIn  = true;
		AppUser appUser = userSession.getAppUser();
		userGroupNewStatus = appUser.getUserGroupNew();
		userName = appUser.getFullName();
		userId = appUser.getOID();
                kd_bank=appUser.getKdCabangBank();
                getUserGroup = appUser.getUserGroupNew();
	    }
	}
    }catch (Exception exc){
	System.out.println(" >>> Exception during check login");
    }

    /**
     * set language
     */
    String strLanguage = "";
    if (session.getValue("APPLICATION_LANGUAGE") != null) {
	strLanguage = String.valueOf(session.getValue("APPLICATION_LANGUAGE"));
    }
    int langDefault = com.dimata.util.lang.I_Language.LANGUAGE_DEFAULT;
    int langForeign = com.dimata.util.lang.I_Language.LANGUAGE_FOREIGN;
    int SESS_LANGUAGE = (strLanguage!=null && strLanguage.length() > 0) ? Integer.parseInt(strLanguage) : 0;
    
    Periode periode = new Periode();
    try{
        Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_POSTED]+"='0'", "");
        if(listPeriode != null){
            periode = (Periode) listPeriode.get(0);
        }
    }catch(Exception ex){
    
    }
    
    String urlC = request.getRequestURL().toString();
    String baseURL = urlC.substring(0, urlC.length() - request.getRequestURI().length()) + request.getContextPath() + "";

    
%>