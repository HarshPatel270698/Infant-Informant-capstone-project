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
                                <center><h3>ADD FACULTY</h3></center><br/>
                                <div class="form-group">
                                    
                                    <input class="au-input au-input--30" type="text" name="ffn" placeholder="First Name">
                                    &emsp;
                                    <input class="au-input au-input--30" type="text" name="fmn" placeholder="Middle Name">
                                   &emsp;
                                   <input class="au-input au-input--30" type="text" name="fln" placeholder="Last Name">
                                </div>
                               <div class="form-group"> 
                                   <label>Gender :</label>
                                    <input type="radio" name="gender" value="Male" checked> Male
                                    <input type="radio" name="gender" value="Female"> Female<br>
                               </div>
                                <div class="form-group">
                                    <label>Contact Number</label>&nbsp;
                                    <input class="au-input au-input--30" type="text" name="fcn" placeholder="Contact Number">
                                   &emsp; <label>Email</label>&nbsp;
                                   <input class="au-input au-input--40" type="text" name="femail" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <label>Joining Date</label>&nbsp;
                                    <input class="au-input au-input--20" type="date" name="fjd">
                                    &emsp;<label>Birth Date</label>&nbsp;
                                    <input class="au-input au-input--20" type="date" name="fdob">
                                </div>
                                <div class="form-group">
                                    <label>Faculty Address</label>
                                    <input class="au-input au-input--80" type="text" name="fadress" placeholder="Faculty Address">
                                    <input type="text" style="display:none;">
                                </div>
                                <div class="form-group">&nbsp;
                                    <label>Faculty password</label>
                                    <input type="password" style="display:none;">
                                    <input class="au-input au-input--30" type="password" name="fp" placeholder="Password">
                                </div>
                                <input class="au-btn au-btn--block au-btn--blue m-b-20" type="submit" name="b1" value="Submit">
                                
                            </form>
                        <%
                            
                                String action=null,ffn=null,fmn=null,fln=null,fcn=null,gender=null,femail=null,faddress=null,fjd=null,fdob=null,fpass=null;
                                action=request.getParameter("b1");
                              try
                              {
                                if(action!=null)
                                    {
                                      
                                        if(action.equals("Submit"))
                                        {
                                            int isActive =1;
                                            ffn=request.getParameter("ffn");
                                            fmn=request.getParameter("fmn");
                                            fln=request.getParameter("fln");
                                            fcn=request.getParameter("fcn");
                                            gender=request.getParameter("gender");
                                            femail=request.getParameter("femail");
                                            faddress=request.getParameter("fadress");
                                            fjd=request.getParameter("fjd");
                                            fdob=request.getParameter("fdob");
                                            fpass=request.getParameter("fp");
                                            int i=st.executeUpdate("insert into faculty(F_First_Name,F_Middle_Name,F_Last_Name,F_Gender,F_Contact_Number,F_Email,F_Joining_DATE,F_DATE_Of_Birth,F_Address,F_password,isActive)values('"+ffn+"','"+fmn+"','"+fln+"','"+gender+"','"+fcn+"','"+femail+"','"+fjd+"','"+fdob+"','"+faddress+"','"+fpass+"','"+isActive+"')");
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
                                                 <center><h3>All Faculty Data</h3></center><br/>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="table-responsive table--no-card m-b-30">
                                    <table class="table table-borderless table-striped table-earning" id="example">
                                        
                                        <thead>
                                            <br/>
                                            <tr>
                                                <th style="text-align: center;">First Name</th>
                                                <th style="text-align: center;">Middle Name</th>
                                                <th style="text-align: center;">Last name</th>
                                                <th style="text-align: center;">Gender</th>
                                                <th style="text-align: center;">Contact Number</th>
                                                <th style="text-align: center;">Email</th>
                                                <th style="text-align: center;">Joining Date</th>
                                                <th style="text-align: center;">DOB</th>
                                                <th style="text-align: center;">Address</th>
                                                <th style="text-align: center;">Status</th>
                                                <th style="text-align: center;">Update Status</th>
                                            </tr>
                                        </thead>
                                        
                                        
                                           <%
                                                rs=st.executeQuery("select *from faculty");
                                                 while(rs.next())
                                                    {
                                                        String status=null;
                                                        status=rs.getString(12);
                                                        if(status.equals("0"))
                                                        {
                                                            status="DeActive";
                                                        }
                                                        else
                                                        {
                                                            status="Active";
                                                        }
                                                        out.println("<tr>"
                                                               + "<td style=text-align:center;>"+rs.getString(2)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(3)+"</td>" 
                                                               +"<td style=text-align: center;>"+rs.getString(4)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(5)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(6)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(7)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(8)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(9)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(10)+ "</td>"
                                                               + "<td style=text-align: center;>"+status+"</td>"
                                                               + "<td style=text-align: center;><a href=activated.jsp?fid="+rs.getString(1)+">Active</a>/<a href=deactivated.jsp?fid="+rs.getString(1)+">Deactive</a></td></tr>");
                                                       
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
