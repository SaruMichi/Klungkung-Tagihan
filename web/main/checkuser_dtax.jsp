
<%
    if (userSession == null || isLoggedIn == false) {
        response.sendRedirect(approot + "/login_new.jsp");
    }
%>
