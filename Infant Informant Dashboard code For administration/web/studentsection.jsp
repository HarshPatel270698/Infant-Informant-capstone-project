<%-- 
    Document   : studentsection
    Created on : Oct 10, 2018, 6:00:09 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date;"%>
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
                                     
                                    <div class="page-wrapper" style=" height: 1300px">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        
                        <form action="" method="post" autocomplete="off" class="active has-sub">
                                <center><h3>ADD STUDENT</h3></center><br/>
                                <div class="form-group">
                                       <input class="au-input au-input--30" type="text" name="gn" placeholder="GR Number">
                                </div>
                                 <div class="form-group">
                                   <ul>
                                       <input class="au-input au-input--30" type="text" name="sfn" placeholder="First Name">
                                    &emsp;
                                    <input class="au-input au-input--30" type="text" name="smn" placeholder="Middle Name">
                                   &emsp;
                                    <input class="au-input au-input--30" type="text" name="sln" placeholder="Last Name"></ul>
                                </div>
                                <div class="form-group">
                                    <label>Contact Number</label>&nbsp;
                                        <input class="au-input au-input--30" type="text" name="scn" placeholder="Contact Number">
                                        </div>
                                 <div class="form-group">
                                     <ul><label>Admission Date</label>
                                    <input class="au-input au-input--20" type="date" name="sad">
                                    &emsp;<label>Birth Date</label>&nbsp;
                                    <input class="au-input au-input--20" type="date" name="sbd"></ul>
                                </div>
                                <div class="form-group">
                                    <ul>
                                   <label>Standard</label>
                                   &nbsp;
                                   <select name="sc"  style="line-height: 43px;border: 1px solid #e5e5e5;font-size: 14px;color: #666;padding: 0 17px;width: 100px;border-radius: 3px;transition: all 0.5s ease;">
                                  <option value="KG.1">KG.1</option>
                                  <option value="KG.2">KG.2</option>
                                   </select>
                                    &emsp;<label>Blood group</label>&nbsp;
                                   <select name="sbg"  style="line-height: 43px;border: 1px solid #e5e5e5;font-size: 14px;color: #666;padding: 0 17px;width: 100px;border-radius: 3px;transition: all 0.5s ease;">
                                  <option value="A+">A+</option>
                                  <option value="A-">A-</option>
                                  <option value="B+">B+</option>
                                  <option value="B-">B-</option>
                                  <option value="O+">O+</option>
                                  <option value="O-">O-</option>
                                  <option value="AB+">AB+</option>
                                  <option value="AB-">AB-</option>
                              </select>
                                     &emsp;
                                    <input type="radio" name="gender" value="Male" checked> Male 
                                    <input type="radio" name="gender" value="Female"> Female
                                    </ul>
                                </div>
                                <div class="form-group">
                                    <label>Student Address</label>
                                    <input class="au-input au-input--80" type="text" name="sadress" placeholder="Student Address">
                                    <input type="text" style="display:none;">
                                </div>
                                <div class="form-group">
                                    <ul>
                                        <label>student password</label>&nbsp;
                                        <input type="password" style="display:none;">
                                    <input class="au-input au-input--30" type="password" placeholder="Password" name="sp">
                                    </ul>
                                </div>
                                <input class="au-btn au-btn--block au-btn--blue m-b-20" type="submit" name="b1" value="Submit">
                              </form>
                         <%
                            
                                String sdob=null,sadd_date=null,gender=null,action=null,gn=null,sfn=null,smn=null,sln=null,scn=null,sbg=null,saddress=null,sclass=null,spass=null;
                                action=request.getParameter("b1");
                                
                              try
                              {
                                if(action!=null)
                                    {
                                      
                                        if(action.equals("Submit"))
                                        {
                                            gn=request.getParameter("gn");
                                            sfn=request.getParameter("sfn");
                                            smn=request.getParameter("smn");
                                            sln=request.getParameter("sln");
                                            scn=request.getParameter("scn");
                                            gender=request.getParameter("gender");
                                            sbg=request.getParameter("sbg");
                                            saddress=request.getParameter("sadress");
                                            sadd_date=request.getParameter("sad");
                                            sdob=request.getParameter("sbd");
                                            sclass=request.getParameter("sc");
                                            spass=request.getParameter("sp");
                                            int i=st.executeUpdate("insert into student(S_GR_Number,S_First_Name,S_Middle_Name,S_Last_Name,S_Gender,S_Contact_Number,S_Admission_Date,S_DATE_Of_Birth,S_Blood_Group,S_Address,Standard,password)values('"+gn+"','"+sfn+"','"+smn+"','"+sln+"','"+gender+"','"+scn+"','"+sadd_date+"','"+sdob+"','"+sbg+"','"+saddress+"','"+sclass+"','"+spass+"')");
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
                           <center><h3>All Student Data</h3></center><br/>
                            <div class="row">
                            <div class="col-lg-12">
                                <div class="table-responsive table--no-card m-b-30">
                                    <table class="table table-borderless table-striped table-earning" id="example">
                                        <thead>
                                            <tr>
                                                <th style="text-align: center;">GR</th>
                                                <th style="text-align: center;">First Name</th>
                                                <th style="text-align: center;">Middle Name</th>
                                                <th style="text-align: center;">Last name</th>
                                                <th style="text-align: center;">Gender</th>
                                                <th style="text-align: center;">Contact Number</th>
                                                <th style="text-align: center;">Admission date</th>
                                                <th style="text-align: center;">DOB</th>
                                                <th style="text-align: center;">Blood Group</th>
                                                <th style="text-align: center;">Address</th>
                                                <th style="text-align: center;">Class</th>
                                                <th style="text-align: center;">Parents Details</th>
                                                <th style="text-align: center;">Result</th>
                                                <th style="text-align: center;">Fees</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                             <%
                                               try{
                                                rs=st.executeQuery("select *from student");
                                                 while(rs.next())
                                                    {
                                                         Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(7));
                                                         Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(8));
                                                         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                                        out.println("<tr><td style=text-align: center;>" 
                                                                +rs.getString(1)+"</td>"
                                                               + "<td style=text-align:center;>"+rs.getString(2)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(3)+ "</td>" 
                                                               +"<td style=text-align: center;>"+rs.getString(4)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(5)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(6)+ "</td>"
                                                               +"<td style=text-align: center;>"+(formatter.format(date1))+ "</td>"
                                                               +"<td style=text-align: center;>"+(formatter.format(date2))+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(9)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(10)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(11)+ "</td>"
                                                               + "<td style=text-align: center;><a href=parents.jsp?grno="+rs.getString(1)+">Parents Details</a></td>"
                                                               + "<td style=text-align: center;><a href=result.jsp?grno="+rs.getString(1)+">Result</a></td>"
                                                               + "<td style=text-align: center;><a href=fees.jsp?grno="+rs.getString(1)+">Fees</a></td></tr>");
                                                       
                                                    }
                                               }
                                               catch (Exception e)
                                               {
                                                     out.println("Ex is:"+e);
                                                  }
                                            %>
                                        </tbody>
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
