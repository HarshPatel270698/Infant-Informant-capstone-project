<%-- 
    Document   : activated
    Created on : Oct 31, 2018, 6:14:12 PM
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
          String fid=null;
          fid=request.getParameter("fid");
          if(fid!=null)
          {
              session.setAttribute("fid", fid);
          }
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/ii","root","");
        st=con.createStatement();
         String isActive="1";
                            int k=st.executeUpdate("update faculty set isActive='"+isActive+"' where F_Id='"+fid+"'");
                            if(k>0)
                                    out.println("<script>alert('Faculty is Activated');document.location.href='faculties.jsp';</script>");
                            else
                                 out.println("<script>alert('Faculty is not Activated');document.location.href='faculties.jsp';</script>");
    }
    catch(Exception e)
    {
        out.println("Ex is:"+e);
    }
    %>
    </body>
</html>
