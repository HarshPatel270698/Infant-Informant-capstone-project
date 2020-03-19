<%-- 
    Document   : index
    Created on : Sep 26, 2018, 7:15:46 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INFANT INFORMANT</title>
    </head>
    <body>
        <%
           if(session.getAttribute("uname")==null)
            {
                out.println("<script>alert('Must be Login');document.location.href='index.jsp';</script>");
            }
        %>
        <jsp:include page="include/css_data.jsp"></jsp:include>
        <jsp:include page="include/header.jsp"></jsp:include>
        <jsp:include page="include/slide_menu.jsp"></jsp:include>
        <jsp:include page="include/header_data.jsp"></jsp:include>
        <br><br><br><br><br><br><br>
    <center><img src="images/icon/logo2.png"  alt="INFANT INFORMANT" />
            </center>                          
<br><br><br><br><br>
        
    <%--<jsp:include page="include/main.jsp"></jsp:include>>--%>
        <jsp:include page="include/footer.jsp"></jsp:include>
    </body>
</html>
