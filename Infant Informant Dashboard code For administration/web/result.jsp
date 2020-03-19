<%-- 
    Document   : result
    Created on : Oct 3, 2018, 6:34:25 PM
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
                                      <body class="animsition">
    <div class="page-wrapper" style="height: 900px;">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <div class="form-group">
                            <form action="" method="post">
                                <center><h3>Result</h3></center>
                                <br>
                                <div class="form-group">
                                <label>Student GR Number : </label>
                                <%
                                              out.println(" "+grno);
                                 %>
                                </div>
                                <div class="form-group"> 
                                    <label>Student Name :</label>
                                    <%
                                              rs=st.executeQuery("select * from student where S_GR_Number='"+grno+"'");
                                               while(rs.next())
                                                    {
                                                        out.print(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
                                                    }
                                   
                                 %>
                                </div>
                                    <label>Exam name</label><select name="exam"  style="line-height: 43px;border: 1px solid #e5e5e5;font-size: 14px;color: #666;padding: 0 17px;width: 100px;border-radius: 3px;transition: all 0.5s ease;">
                                  <option value="MID-1">MID-1</option>
                                  <option value="MID-2">MID-2</option>
                                  <option value="MID-3">MID-3</option>
                                  <option value="MID-4">MID-4</option>
                                  <option value="MID-5">MID-5</option>
                                  <option value="FINAL-1">FINAL-1</option>
                                  <option value="FINAL-2">FINAL-2</option>
                                  <option value="FINAL-3">FINAL-3</option>
                              </select><br>
                             
                              <input class="au-input au-input--30" type="text" name="maths" placeholder="Maths">&nbsp;                          
                              
                              <input class="au-input au-input--30" type="text" name="gujarati" placeholder="Gujarati"><br><br>
                                   <label>Final Grade</label><select class="au-input au-input--full" name="finalgrade"  style="line-height: 43px;border: 1px solid #e5e5e5;font-size: 14px;color: #666;padding: 0 17px;width: 100px;border-radius: 3px;transition: all 0.5s ease;">
                                  <option value="AA">AA</option>
                                  <option value="AB">AB</option>
                                  <option value="BB">BB</option>
                                  <option value="BC">BC</option>
                                  <option value="CC">CC</option>
                                  <option value="CD">CD</option>
                                  <option value="DD">DD</option>
                                  <option value="FF">FF</option>
                              </select>
                                   <br>
                                   <label>Rank in class</label>
                                   <input class="au-input au-input--full" type="text" name="rank">
                                   <br>
                                <br><button class="au-btn au-btn--block au-btn--blue m-b-20" type="submit" name="b1" value="Submit">submit</button>
                               </form>
                        <%
                             String exam=null,maths=null,gujarati=null,total=null,action=null,mathsgrade=null,gujaratigrade=null,finalgrade=null,rank=null;
                                action=request.getParameter("b1");
                              try
                              {
                                if(action!=null)
                                    {
                                      
                                        if(action.equals("Submit"))
                                        {
                                           
                                            exam=request.getParameter("exam");
                                            maths=request.getParameter("maths");
                                            int tmaths=Integer.parseInt(maths);
                                            {
                                               if(tmaths <=100 &&  tmaths>=95)
                                               {
                                                   mathsgrade= "AA";
                                               }
                                               else if(tmaths <95 && tmaths>=80)
                                               {
                                                    mathsgrade= "AB";
                                               }
                                               else if(tmaths <80 && tmaths>=75)
                                               {
                                                    mathsgrade= "BB";
                                               }
                                               else if(tmaths <75 && tmaths>=60)
                                               {
                                                     mathsgrade= "BC";
                                               }
                                               else if(tmaths <60 && tmaths>=50)
                                               {
                                                     mathsgrade= "CC";
                                               }
                                               else if(tmaths <50 && tmaths>=35)
                                               {
                                                     mathsgrade= "CD";
                                               }
                                                 else if(tmaths <35 && tmaths>33)
                                               {
                                                     mathsgrade= "DD";
                                               }
                                               else
                                                 {
                                                      mathsgrade= "FF";
                                                 }
                                            }
                                            gujarati=request.getParameter("gujarati");
                                            int tgujarati=Integer.parseInt(gujarati);
                                            {
                                               if(tgujarati <=100 && tgujarati>=95)
                                               {
                                                  gujaratigrade= "AA";
                                               }
                                               else if(tgujarati <95 && tgujarati>=80)
                                               {
                                                    gujaratigrade= "AB";
                                               }
                                               else if(tgujarati <80 && tgujarati>=75)
                                               {
                                                     gujaratigrade= "BB";
                                               }
                                               else if(tgujarati <75 && tgujarati>=60)
                                               {
                                                 gujaratigrade= "BC";
                                               }
                                               else if(tgujarati <60 && tgujarati>=50)
                                               {
                                                 gujaratigrade= "CC";
                                               }
                                               else if(tgujarati <50 && tgujarati>=35)
                                               {
                                                 gujaratigrade= "CD";
                                               }
                                                 else if(tgujarati <35 && tgujarati>33)
                                               {
                                                 gujaratigrade= "DD";
                                               }
                                               else
                                                 {
                                                      gujaratigrade= "FF";
                                                 }
                                            }
                                            total=(tmaths+tgujarati)+"";
                                            finalgrade=request.getParameter("finalgrade");
                                            rank=request.getParameter("rank");
                                            int i=st.executeUpdate("insert into result_detail(S_GR_Number,Exam,Maths,Maths_Grade,Gujarati,Gujarati_Grade,Total,Total_Grade,Rank)values('"+session.getAttribute("ugrno")+"','"+exam+"','"+maths+"','"+mathsgrade+"','"+gujarati+"','"+gujaratigrade+"','"+total+"','"+finalgrade+"','"+rank+"')");
                                            if(i>0)
                                            { 
                                                session.removeAttribute("ugrno");
                                                out.println("<script>alert('Result is Submitted');</script>"); 
                                            }
                                            else
                                            { 
                                                out.println("<script>alert('Result is not Submitted');</script>");
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
                                            <tr>
                                                <th style="text-align: center;">Exam</th>
                                                <th style="text-align: center;">Maths</th>
                                                <th style="text-align: center;">Maths Grade</th>
                                                <th style="text-align: center;">Gujarati</th>
                                                <th style="text-align: center;">Gujarati Grade</th>
                                                <th style="text-align: center;">Total</th>
                                                <th style="text-align: center;">Final Grade</th>
                                                <th style="text-align: center;">Rank in Class</th>
                                                </tr>
                                        </thead>
                                        <tbody>
                                           <%
                                                rs=st.executeQuery("select *from result_detail where S_GR_Number='"+grno+"'");
                                                 while(rs.next())
                                                    {
                                                        out.println(
                                                                "<tr><td style=text-align: center;>"+rs.getString(3)+"</td>" 
                                                               +"<td style=text-align: center;>"+rs.getString(4)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(5)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(6)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(7)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(8)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(9)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(10)+ "</td></tr>"
                                                        );
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
