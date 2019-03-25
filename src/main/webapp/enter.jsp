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

        <div class="row header-info">
            <div class="col-sm-10 col-sm-offset-1 text-center">
                <h2  style="color: #74716e; margin-top: -25%">Добро пожаловать в Friend Finder!</h2>
                <br />
                <p class="lead wow fadeIn" data-wow-delay="0s" style="color: #79838f">Пожалуйста, выберите вход через:</p>
                <br />

                <div class="row">
                    <div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">
                        <div class="row">
                            <div class="col-xs-6 text-right wow fadeInUp" data-wow-delay="1s" >
                                <a href="<%=fbConnection.getFBAuthCodeUrl()%>" class="btn btn-secondary btn-lg" style="color: #74716e"> Facebook</a>
                            </div>
                            <!--1.4s-->
                            <div class="col-xs-6 text-left wow fadeInUp" data-wow-delay="1.4s">
                                <a href="<%=vkConnection.getVKAuthCodeUrl()%>" class="btn btn-primary btn-lg">Вконтакте</a>
                            </div>
                        </div><!--End Button Row-->
                    </div>
                </div>

            </div>
        </div>
    </div>


</header>

<div class="mouse-icon hidden-xs">
    <div class="scroll"></div>
</div>

<section id="be-the-first" class="pad-xl">
    <div class="container">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2 text-center margin-30 wow fadeIn" data-wow-delay="0.6s">
                <h2>Friend Finder это</h2>
                <p class="lead">приложение, ориентированное на поиск единомышленников, людей со схожими интересами и увлечениями. Friend Finder поможет познакомиться с любителями котиков или фанатом компьютерных всего за пару минут. Ищи, узнавай, дружи и получай удовольствие :) </p>
            </div>
        </div>

        <div class="iphone wow fadeInUp" data-wow-delay="1s">
            <%--<img src="iphone.png">--%>
        </div>
    </div>
</section>

<footer>
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
