<%-- 
    Document   : eventdelete
    Created on : Mar 19, 2019, 2:18:03 PM
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
          String eid=null;
          eid=request.getParameter("eid");
          if(eid!=null)
          {
              session.setAttribute("eid", eid);
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
                           j=st.executeUpdate("delete from event where E_Id='"+eid+"'");
                            if(j>0)
                                out.println("<script>alert('Event is Deleted');document.location.href='event.jsp';</script>");
                            else
                                out.println("<script>alert('Event is not Deleted');document.location.href='event.jsp';</script>");
    }
    catch(Exception e)
    {
        out.println("Ex is:"+e);
    }
    %>
    </body>
</html>
