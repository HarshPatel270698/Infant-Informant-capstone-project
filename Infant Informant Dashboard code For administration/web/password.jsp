<%-- 
    Document   : password
    Created on : Jan 26, 2019, 10:34:54 PM
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
                                      
    <div class="page-wrapper">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                            <form action="" method="post">
                                <h3>Reset Password</h3><br>
                                    <label>Enter Password</label>
                                    <input type="password" style="display:none;">
                                <input class="au-input au-input--full" type="password" name="password">
                                 <br/> <br/>
                                 <label>Re-enter Password</label>
                                <input class="au-input au-input--full" type="password" name="password1">
                                <br/> <br/>
                                <button class="au-btn au-btn--block au-btn--blue m-b-20" type="submit" name="b1" value="Submit">submit</button>
                               </form>
                    <%
                        String action=null,password=null,password1=null;
                        password=request.getParameter("password");
                        password1=request.getParameter("password1");
                         action=request.getParameter("b1");
                         try
                              {
                                    if(action!=null)
                                    {
                                        if(action.equals("Submit"))
                                        {
                                            if(password.equals(password1))
                                            {
                                            int i=st.executeUpdate("update admin set password='"+password1+"' where username='"+session.getAttribute("uname").toString()+"'");
                                            if(i>0)
                                            { 
                                                out.println("<script>alert('Password is changed successfully');</script>");
                                            }
                                            else
                                            { 
                                                out.println("<script>alert('Password is not changed');</script>");
                                            }
                                            }
                                            else
                                            {
                                                {
                                                  out.println("<script>alert(' RETRY:Password are not matched');</script>");
                                              }
                                            }
                                   
                                    }
                              }
                              }
                              catch(Exception e)
                              {
                                  out.println("Ex is:"+e);
                              }
                        %>
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
