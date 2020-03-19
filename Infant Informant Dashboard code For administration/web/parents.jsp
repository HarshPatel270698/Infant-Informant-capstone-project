<%-- 
    Document   : parents
    Created on : Oct 19, 2018, 8:09:14 PM
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
          String grno=null;
          grno=request.getParameter("grno");
          if(grno!=null)
          {
              session.setAttribute("ugrno", grno);
          }
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
                                <center><h3>ADD PARENTS DETAILS</h3></center>
                                <div class="form-group"><br>
                                    <input class="au-input au-input--30" type="text" name="parents_first_name" placeholder="First Name">
                                   
                                    <input class="au-input au-input--30" type="text" name="parents_middle_name" placeholder="Middle Name">
                                    
                                    <input class="au-input au-input--30" type="text" name="parents_last_name" placeholder="Last Name">
                                </div>
                                <div class="form-group">
                                 <input class="au-input au-input--30" type="text" name="parents_contact_number" placeholder="Contact Number">
                               </div>  
                               <div class="form-group"> 
                                   <input type="radio" name="gender" value="Male" checked> Male
                                    <input type="radio" name="gender" value="Female"> Female<br>   
                               </div>  
                                <div class="form-group">
                                    <input class="au-input au-input--50" type="text" name="parents_mail" placeholder="Email Id">
                                </div>
                                <div class="form-group">
                                    <input class="au-input au-input--80" type="text" name="Parents_address" placeholder="Address">
                                </div>
                                <input class="au-btn au-btn--block au-btn--blue m-b-20" type="submit" name="b1" value="Submit">
                                </form>
                         <%
                            
                                String action=null,parents_first_name=null,parents_middle_name=null,parents_last_name=null,gender=null,parents_contact_number=null,parents_mail=null,Parents_address=null;
                                action=request.getParameter("b1");
                               
                              try
                              {
                                if(action!=null)
                                    {
                                      
                                        if(action.equals("Submit"))
                                        {
                                            parents_first_name=request.getParameter("parents_first_name");
                                            parents_middle_name=request.getParameter("parents_middle_name");
                                            parents_last_name=request.getParameter("parents_last_name");
                                            gender=request.getParameter("gender");
                                            parents_contact_number=request.getParameter("parents_contact_number");
                                            parents_mail=request.getParameter("parents_mail");
                                            Parents_address=request.getParameter("Parents_address");
                                           // int i=st.executeUpdate("insert into parents(P_First_Name='parents_first_name',P_Middle_Name='parents_middle_name',P_Last_Name='parents_last_name',P_Gender='gender',P_Contact_Number='parents_contact_number',P_Email='parents_mail',P_Address='Parents_address',S_GR_Number='"+grno+"'");
                                            //int i=st.executeUpdate("insert into student(S_GR_Number,S_First_Name,S_Middle_Name,S_Last_Name,S_Gender,S_Contact_Number,S_Admission_Date,S_DATE_Of_Birth,S_Blood_Group,S_Address,Standard,password)values('"+gn+"','"+sfn+"','"+smn+"','"+sln+"','"+gender+"','"+scn+"','"+sadd_date+"','"+sdob+"','"+sbg+"','"+saddress+"','"+sclass+"','"+spass+"')");
                                            int i=st.executeUpdate("insert into parents(P_First_Name,P_Middle_Name,P_Last_Name,P_Gender,P_Contact_Number,P_Email,P_Address,S_GR_Number)values('"+parents_first_name+"','"+parents_middle_name+"','"+parents_last_name+"','"+gender+"','"+parents_contact_number+"','"+parents_mail+"','"+Parents_address+"','"+grno+"')");
                                            
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
                                                <th style="text-align: center;">Address</th>
                                            </tr>
                                        </thead>
                                        
                                        
                                           <%
                                                rs=st.executeQuery("select *from parents where S_GR_Number='"+grno+"'");
                                                while(rs.next())
                                                    {
                                                        out.println("<tr>"
                                                               + "<td style=text-align:center;>"+rs.getString(2)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(3)+"</td>" 
                                                               +"<td style=text-align: center;>"+rs.getString(4)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(5)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(6)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(7)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(8)+ "</td></tr>");  
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
