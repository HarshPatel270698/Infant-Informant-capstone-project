<%-- 
    Document   : time_table_upload
    Created on : Mar 25, 2019, 7:12:44 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <% 
        String contentType = request.getContentType(); 
   
    String imageSave=null; 
    byte dataBytes[]=null; 
    String saveFile=null; 
    String str=null;
   
   
    if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) 
    { 
        DataInputStream in = new DataInputStream(request.getInputStream()); 
        int formDataLength = request.getContentLength(); 
      
        
        dataBytes = new byte[formDataLength]; 
        int byteRead = 0; 
        int totalBytesRead = 0; 
        while (totalBytesRead < formDataLength) 
        { 
            byteRead = in.read(dataBytes, totalBytesRead, formDataLength); 
            totalBytesRead += byteRead; 
        } 
        String file = new String(dataBytes); 
        saveFile = file.substring(file.indexOf("filename=\"") + 10); 
        saveFile = saveFile.substring(0, saveFile.indexOf("\n")); 
        saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\"")); 
// out.print(dataBytes); 
        int lastIndex = contentType.lastIndexOf("="); 
        String boundary = contentType.substring(lastIndex + 1, contentType.length()); 
// out.println(boundary); 
        int pos; 
        pos = file.indexOf("filename=\""); 
        pos = file.indexOf("\n", pos) + 1; 
        pos = file.indexOf("\n", pos) + 1; 
        pos = file.indexOf("\n", pos) + 1; 
        int boundaryLocation = file.indexOf(boundary, pos) - 4; 
        int startPos = ((file.substring(0, pos)).getBytes()).length; 
        int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length; 
        try 
        { 
            FileOutputStream fileOut = new FileOutputStream("D:/"+saveFile); 
// fileOut.write(dataBytes); 
            fileOut.write(dataBytes, startPos, (endPos - startPos)); 
            fileOut.flush(); 
            fileOut.close(); 
           
        }
        catch (Exception e) 
        { 
            out.println ("Error writing to file"); 
            imageSave="Failure"; 
        }
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/ii","root","");
            st=con.createStatement();
            int i=st.executeUpdate("insert into timetable(Standard,IMAGE_name)values('"+session.getAttribute("std")+"','"+saveFile+"')");
            if(i>0)
                out.println("<script>alert('Timetable Uploaded Sucessfully');document.location.href='timetable.jsp';</script>");
            else
                out.println("<script>alert('Timetable is not Upload');document.location.href='timetable.jsp';</script>");
        }
        catch(Exception e)
        {
            out.println("Ex is:"+e);
        }
   } 
%> 
    </body>
</html>
