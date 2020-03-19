<%-- 
    Document   : logout
    Created on : Mar 15, 2019, 7:13:45 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
            
         session.removeAttribute("uname");
         out.println("<script>alert('Logout Sucessfully');document.location.href='index.jsp';</script>");
    %>
    </body>
</html>
