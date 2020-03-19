<%-- 
    Document   : faculties
    Created on : Oct 27, 2018, 8:02:37 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INFANT INFORMANT</title>
       <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
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
                                    <div class="page-wrapper" style="height: 1300px;">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <form action="" method="post">
                                <center><h3>ADD EVENTS</h3></center><br/>
                                <br>
                                    <input class="au-input au-input--40" placeholder="Event Name" type="text" name="ename">
                                   <br/><br/>
                                   <div class="form-group">
                                   <label>Event Date</label><br/>
                                   <input class="au-input au-input--40" type="date" name="edt">
                                   <br/><br/>
                                   <label>Event Time</label><br/>
                                   <input class="au-input au-input--40" type="time" name="etime">
                                   <br/><br/>
                                   <textarea rows="3" cols="25" placeholder="Enter Event Desciption Here" name="edetail"></textarea>
                                   <br/><br/>
                                 <input class="au-btn au-btn--block au-btn--blue m-b-20" type="submit" name="b1" value="Submit">
                                   </div>
                    </form>    
                        <%
                            
                                String action=null,edate=null,edetail=null,etime=null,ename=null;
                                action=request.getParameter("b1");
                                
                              try
                              {
                                if(action!=null)
                                    {
                                      
                                        if(action.equals("Submit"))
                                        {
                                            edate=request.getParameter("edt");
                                            edetail=request.getParameter("edetail");
                                            etime=request.getParameter("etime");
                                            ename=request.getParameter("ename");
                                            int i=st.executeUpdate("insert into event(E_Name,E_Date,E_Details,E_Time)values('"+ename+"','"+edate+"','"+edetail+"','"+etime+"')");
                                            if(i>0)
                                            { 
                                                out.println("<script>alert('Record is Submitted');</script>");
                                            }
                                            else
                                            { 
                                                out.println("<script>alert('Record is not Submitted');</script>");
                                            }
                                        }
                                   
                                    }
                              }
                              catch(Exception e)
                              {
                                  out.println("Ex is:"+e);
                              }
                            %>
                                                 <center><h3>All Event Data</h3></center><br/>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="table-responsive table--no-card m-b-30">
                                    <table class="table table-borderless table-striped table-earning" id="example">
                                        
                                        <thead>
                                            <br/>
                                            <tr>
                                                <th style="text-align: center;">Event Name</th>
                                                <th style="text-align: center;">Date</th>
                                                <th style="text-align: center;">Time</th>
                                                <th style="text-align: center;">Description</th>
                                                <th style="text-align: center;">Image 1</th>
                                                <th style="text-align: center;">Image 2</th>
                                                <th style="text-align: center;">Image 3</th>
                                                <th style="text-align: center;">Delete</th>
                                            </tr>
                                        </thead>
                                        <%
                                                rs=st.executeQuery("select *from event");
                                                 while(rs.next())
                                                    {
                                                        
                                                        
                                                        out.println("<tr>"
                                                               + "<td style=text-align:center;>"+rs.getString(2)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(3)+"</td>" 
                                                               +"<td style=text-align: center;width:200px;>"+rs.getString(5)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(4)+ "</td>"
                                                               + "<td style=text-align:center;><a href=uploadevent.jsp?eid="+rs.getString(1)+"&&event=Image1>Image1</a></td>"
                                                               +"<td style=text-align: center;><a href=uploadevent.jsp?eid="+rs.getString(1)+"&&event=Image2>Image2</a></td>" 
                                                               +"<td style=text-align: center;><a href=uploadevent.jsp?eid="+rs.getString(1)+"&&event=Image3>Image3</a></td>" 
                                                               + "<td style=text-align: center;><a href=eventdelete.jsp?eid="+rs.getString(1)+">Delete</a></td></tr>");
                                                       
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
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
  <script>
  $(function(){
    $("#example").dataTable();
  });
  </script>
</html>
