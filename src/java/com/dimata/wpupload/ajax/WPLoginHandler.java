/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.ajax;

import com.dimata.qdep.form.FRMQueryString;
import com.dimata.wpupload.entity.wpuser.AppUserWP;
import com.dimata.wpupload.form.wpuser.FrmAppUserWP;
import com.dimata.wpupload.session.SessUserWPSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ardiadi
 */
public class WPLoginHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	
	int dologin = 0;
	String kaptchaExpected = (String)request.getSession().getAttribute(com.dimata.wpupload.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	String kaptchaReceived = request.getParameter("kaptcha");

	
	boolean dayAssign=false;
	String userId = FRMQueryString.requestString(request, FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_USER_ID]);
	String userPassword = FRMQueryString.requestString(request, FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_PASSWORD]);
	String userNoTelp = FRMQueryString.requestString(request, FrmAppUserWP.fieldNames[FrmAppUserWP.FRM_FIELD_NO_TELP]);
	String remoteIP = request.getRemoteAddr();
	int maxidle = FRMQueryString.requestInt(request, "maxidle");
	SessUserWPSession userWPSession = new SessUserWPSession(remoteIP );
	
	if (kaptchaReceived != null && kaptchaExpected != null && kaptchaExpected.equals(kaptchaReceived))
	{
	    dologin=userWPSession.doLogin(userId, userPassword, userNoTelp);
	    HttpSession session = request.getSession();
	    if(dologin == SessUserWPSession.DO_LOGIN_OK){
                AppUserWP user = userWPSession.getAppUser();
		session.setMaxInactiveInterval(maxidle);
		session.putValue(SessUserWPSession.HTTP_SESSION_WPNAME, userWPSession);
                try{
                        userWPSession = (SessUserWPSession) session.getValue(SessUserWPSession.HTTP_SESSION_WPNAME);
                }catch(Exception ex){}
	    }
	}else{
	    dologin = SessUserWPSession.DO_LOGIN_NOT_VALID;
	}
	response.getWriter().println(dologin);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>
}
