<%-- 
    Document   : timetable
    Created on : Oct 3, 2018, 6:33:30 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INFANT INFORMANT</title>
    </head>
     <%
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/ii","root","");
        st=con.createStatement();
    }
    catch(Exception e)
    {
        out.println("Ex is:"+e);
    }
%>
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
         <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                           <div class="col-md-12">
                                <div class="overview-wrap">
                                      <body class="animsition">
    <div class="page-wrapper" style="height: 800px;">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                            <form ACTION="upload.jsp" method="post" name="uploadForm" ENCTYPE='multipart/form-data'>
                                <h3>Upload Timetable Image</h3><br>
                                <table class="table table-borderless table-striped table-earning">
                                    <tbody>
                                    <tr>
                                        <td style="text-align: center;"><a href="upload.jsp?std=KG.1">KG.1</a></td>
                                        <td style="text-align: center;"><a href="upload.jsp?std=KG.2">KG.2</a></td>
                                    </tr>
                                </body>
                                </table>
                            </form>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="table-responsive table--no-card m-b-30">
                                    <table class="table table-borderless table-striped table-earning" id="example">
                                        
                                        <thead>
                                            <br/>
                                            <tr>
                                                <th style="text-align: center;">Class</th>
                                                <th style="text-align: center;">Delete</th>
                                            </tr>
                                        </thead>
                                        <%
                                                rs=st.executeQuery("select *from timetable");
                                                 while(rs.next())
                                                    {
                                                        out.println("<tr>"
                                                               + "<td style=text-align:center;>"+rs.getString(2)+ "</td>"
                                                               + "<td style=text-align: center;><a href=timetabledelete.jsp?std="+rs.getString(2)+">Delete</a></td></tr>");
                                                       
                                                    }
                                            %>
                                    </table>
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
                    </div>
                </div>
        </div>
        <jsp:include page="include/footer.jsp"></jsp:include>
        
    </body>
</html>
