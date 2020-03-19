<%-- 
    Document   : uploadevent
    Created on : Mar 31, 2019, 12:37:07 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
        String event=null,eid=null;
        event=request.getParameter("event");
        eid=request.getParameter("eid");
        if(event!=null && eid!=null)
        {
            session.setAttribute("image",event);
            session.setAttribute("eid",eid);
        }
       
    %>
     <jsp:include page="include/css_data.jsp"></jsp:include>
        <jsp:include page="include/header.jsp"></jsp:include>
        <jsp:include page="include/slide_menu.jsp"></jsp:include>
        <jsp:include page="include/header_data.jsp"></jsp:include>
        <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                           <div class="col-md-12">
                                <div class="overview-wrap">
                                      <body class="animsition">
    <div class="page-wrapper" style="height: 500px;">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
           <form method="post"  ACTION="event_upload.jsp" name="uploadForm" ENCTYPE='multipart/form-data'> 
               <input type="text" name="t1"><br>
          Upload Image File here :<br><br>
          <input type="file" name="uploadFile"><br><br>
            <input class="au-btn au-btn-load js-load-btn" type="submit" name="Submit" value="Submit">
            <input class="au-btn au-btn-load js-load-btn" type="reset" name="Reset" value="Reset"> 
                            </form> 
                    </div>
                </div>
            </div>
        </div>
    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        <jsp:include page="include/footer.jsp"></jsp:include>
        
    </body>
</html>
