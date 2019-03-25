<%--
  Created by IntelliJ IDEA.
  User: jhonny
  Date: 15.04.18
  Time: 4:03
  To change this template use File | Settings | File Templates.
--%>

<%@page import="com.friendsfinder.facebook.FBConnection" %>
<%@ page import="com.friendsfinder.vkontakte.VKConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%
    FBConnection fbConnection = new FBConnection();
    VKConnection vkConnection = new VKConnection();
%>

<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>Friend Finder</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta property="og:title" content="">
    <meta property="og:type" content="website">
    <meta property="og:url" content="">
    <meta property="og:site_name" content="">
    <meta property="og:description" content="">

    <!-- Styles -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900|Montserrat:400,700' rel='stylesheet' type='text/css'>


    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/modernizr-2.7.1.js"></script>

</head>

<body>

<header>
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <a href="/"><img src="img/logo3.png" alt="Logo"></a>
            </div>
            <%--<div class="col-xs-6 signin text-right navbar-nav">--%>
            <%--<a href="#pricing" class="scroll">Pricing</a>&nbsp; &nbsp;<a href="#">Sign in</a>--%>
            <%--</div>--%>
        </div>
        <form method="post" action="index.jsp">

        <div class="row header-info">
            <div class="col-sm-10 col-sm-offset-1 text-center">
                <h2  style="color: #74716e; margin-top: -25%">Здравствуй, <%=session.getAttribute("name")%>!</h2>
                <br />
                <br class="lead wow fadeIn" data-wow-delay="0s" style="color: #79838f">К сожалению, в данный момент функция поиска через Facebook недоступна:(</br>
                Она появится в ближайшее время, а пока просим прощения </br>
                    за предоставленные неудобства.</p>
                <p></p>
                <br />
                <button type="submit" href="index.jsp" class="btn btn-primary btn-lg">Назад</button>

            </div>

        </div>
        </form>

    </div>


</header>

    <div class="container">

        <div class="row">
            <div class="col-sm-8 margin-20">
                <ul class="list-inline social">
                    <li>Свяжитесь с нами: </li>
                    <li><a href="#"><i  class="fa fa-mail-reply-all">krylova.OA@mail.ru</i></a></li>
                    <li><a href="#"><i class="fa fa-mobile-phone">+7(985)663-54-25</i></a></li>
                </ul>
            </div>

            <div class="col-sm-4 text-right">
                <p><small>Copyright &copy; 2018. Все права защищены. <br>
                </small></p>
            </div>
        </div>

    </div>
</footer>


<!-- Javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')</script>
<script src="js/wow.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>


</body>
</html>
