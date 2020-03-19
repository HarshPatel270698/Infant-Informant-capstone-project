<%-- 
    Document   : timetabledelete
    Created on : Apr 29, 2019, 7:28:38 PM
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
    <body>
         <%
          String std=null;
          std=request.getParameter("std");
          if(std!=null)
          {
              session.setAttribute("std", std);
          }
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    int j;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/ii","root","");
        st=con.createStatement();
                           j=st.executeUpdate("delete from timetable where Standard='"+std+"'");
                            if(j>0)
                                out.println("<script>alert('Timetable is Deleted');document.location.href='timetable.jsp';</script>");
                            else
                                out.println("<script>alert('Timetable is not Deleted');document.location.href='timetable.jsp';</script>");
    }
    catch(Exception e)
    {
        out.println("Ex is:"+e);
    }
    %>
    </body>
</html>
