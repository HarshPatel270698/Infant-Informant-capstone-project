<%-- 
    Document   : fees
    Created on : Oct 3, 2018, 6:34:34 PM
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
    <div class="page-wrapper" style="height: 1300px;">
        <div class="page-content--bge5">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <div class="form-group">
                            <form action="" method="post" class="active has-sub">
                                <center><h3>Fees</h3></center>
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
                                 <div class="form-group"> 
                                    <label>Standard :</label>
                                    <%
                                              rs=st.executeQuery("select * from student where S_GR_Number='"+grno+"'");
                                               while(rs.next())
                                                    {
                                                        out.print(rs.getString(11));
                                                    }
                                   
                                 %>
                                </div>
                                <div class="form-group">
                                  <label>Pay Fees Of Standard</label>
                                   <select name="standard"  style="line-height: 43px;border: 1px solid #e5e5e5;font-size: 14px;color: #666;padding: 0 17px;width: 100px;border-radius: 3px;transition: all 0.5s ease;">
                                  <option value="KG.1">KG.1</option>
                                  <option value="KG.2">KG.2</option>
                                  <option value="1">1</option>
                                   </select>
                                </div>
                                <div class="form-group"> <label>Mode of payment</label><br>
                                    
                                    <ul>
                                        <input type="radio" name="mode" value="Cash">&nbsp;&nbsp;Cash&emsp;
                                        <input type="radio" name="mode" value="Cheque" checked>&nbsp;&nbsp;Cheque&emsp;
                                        <input type="radio" name="mode" value="Debit Card">&nbsp;&nbsp;Debit Card&emsp;
                                        <input type="radio" name="mode" value="Demand Draft">&nbsp;&nbsp;Demand Draft</ul>    
                               </div>
                                <div class="form-group">
                                    <label>Transaction Number</label>
                                   <input class="au-input au-input--20" type="text" name="transaction">
                                </div>
                                <div class="form-group">
                                    <label>Enter Fee Amount</label>
                                   <input class="au-input au-input--20" type="text" name="feeamount">  ₹
                                </div>
                                <br><button class="au-btn au-btn--block au-btn--blue m-b-20" type="submit" name="b1" value="Submit">submit</button>
                               </form>
                        <%
                           String standard=null,mode=null,feeamount=null,transaction=null,action=null;
                                action=request.getParameter("b1");
                               
                              try
                              {
                                if(action!=null)
                                    {
                                      
                                        if(action.equals("Submit"))
                                        {
                                            standard=request.getParameter("standard");
                                            mode=request.getParameter("mode");
                                            feeamount=request.getParameter("feeamount");
                                            transaction=request.getParameter("transaction");
                                            Date date = new Date(); 
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                            int i=st.executeUpdate("insert into fees(famount,fdate,foption,T_no,S_GR_Number,std)values('"+feeamount+"','"+formatter.format(date)+"','"+mode+"','"+transaction+"','"+grno+"','"+standard+"')");
                                            if(i>0)
                                            { 
                                                out.println("<script>alert('Fee is Submitted');</script>");
                                            }
                                            else
                                            { 
                                                out.println("<script>alert('Fee is not Submitted');</script>");
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
                                                <th style="text-align: center;">Standard</th>
                                                <th style="text-align: center;">Date of Payment</th>
                                                <th style="text-align: center;">Amount</th>
                                                <th style="text-align: center;">Mode OF Payment</th>
                                                <th style="text-align: center;">Transaction ID</th>
                                                </tr>
                                        </thead>
                                        <tbody>
                                           <%
                                               try{
                                                rs=st.executeQuery("select *from fees where S_GR_Number='"+grno+"'");
                                                 while(rs.next())
                                                    {
                                                         Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
                                                         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
                                                        out.println(
                                                                "<tr><td style=text-align:center;>"+rs.getString(7)+ "</td>"
                                                               +"<td style=text-align: center;>"+(formatter.format(date1))+"</td>" 
                                                               +"<td style=text-align: center;>"+rs.getString(2)+" ₹</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(4)+ "</td>"
                                                               +"<td style=text-align: center;>"+rs.getString(5)+ "</td></tr>"
                                                        );
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
