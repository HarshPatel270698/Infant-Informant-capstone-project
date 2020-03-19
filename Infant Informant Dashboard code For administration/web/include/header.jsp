<%-- 
    Document   : header
    Created on : Sep 26, 2018, 7:18:23 PM
    Author     : Harsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="animsition">
        <div class="page-wrapper">
        <!-- HEADER MOBILE-->
        <header class="header-mobile d-block d-lg-none">
            <div class="header-mobile__bar">
                <div class="container-fluid">
                    <div class="header-mobile-inner">
                        <a class="logo" href="home.jsp">
                            <img src="images/icon/logo2.png" alt="INFANT INFORMANT" />
                        </a>
                        <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <nav class="navbar-mobile">
                <div class="container-fluid">
                    <ul class="navbar-mobile__list list-unstyled">
                        <li class="has-sub">
                            <a class="js-arrow" href="home.jsp">
                                <i class="fas fa-tachometer-alt"></i>home</a>
                        </li>
                        <li class="active has-sub">
                            <a href="faculties.jsp">
                                <i class="fas fa-chart-bar"></i>Faculty Section</a>
                        </li>
                        <li class="active has-sub">
                            <a href="studentsection.jsp">
                                <i class="far fa-check-square"></i>Student Section</a>
                        </li>
                        <li class="active has-sub">
                            <a href="timetable.jsp">
                                <i class="fas fa-table"></i>Timetable</a>
                        </li>
                        <li class="active has-sub">
                            <a href="event.jsp">
                                <i class="fas fa-calendar-alt"></i>Event</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    </body>
</html>
