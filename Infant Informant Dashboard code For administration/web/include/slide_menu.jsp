<%-- 
    Document   : slide_menu
    Created on : Sep 26, 2018, 7:22:15 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="#">
                    <img src="images/icon/logo2.png" alt="INFANT INFORANT" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li class="active has-sub">
                            <a class="js-arrow" href="home.jsp">
                                <i class="fas fa-desktop"></i>Home</a>
                        </li>
                        <li>
                            <a href="faculties.jsp">
                                <i class="fas fa-chart-bar"></i>Faculty Section</a>
                        </li>
                        <li>
                            <a href="studentsection.jsp">
                                <i class="far fa-check-square"></i>Student Section</a>
                        </li>
                        <li>
                            <a href="timetable.jsp">
                                <i class="fas fa-table"></i>Timetable</a>
                        </li>
                        <li>
                            <a href="event.jsp">
                                <i class="fas fa-bullhorn"></i>Event</a>
                        </li>
                </nav>
            </div>
        </aside>
    </body>
</html>
