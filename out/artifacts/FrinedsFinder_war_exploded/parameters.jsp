<%--
  Created by IntelliJ IDEA.
  User: jhonny
  Date: 07.04.18
  Time: 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE HTML>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<title>FFinder</title>--%>

<%--</head>--%>
<%--<body>--%>

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

    </div>

<%--<form method="service" action="/settings">--%>
    <%--<div class="row header-info">--%>
        <%--<div class="col-sm-10 col-sm-offset-1 text-center">--%>
            <%--<h2 class="wow fadeIn" style="color: #74716e; margin-top: -25%"> Пожалуйста, введите параметры!</h2>--%>
    <%--&lt;%&ndash;<p><h1>Пожалуйста, введите параметры!</h1>&ndash;%&gt;--%>
        <%--<Br>Глубина: <input type="text" name = "depth" value ="3"> <Br></p></Br>--%>
    <%--<Br>Ширина: <input type="text" name = "length" value ="4"><Br></p></Br>--%>
            <%--<div class="row">--%>

            <%--<div class="col-xs-6 text-right wow fadeInUp" data-wow-delay="1s" >--%>
                <%--<a href="#be-the-first" class="btn btn-secondary btn-lg scroll" style="color: #74716e">Что это</a>--%>
            <%--</div>--%>
            <%--<div class="col-xs-6 text-left wow fadeInUp" data-wow-delay="1s" style="margin-right: 70%">--%>
                <%--<a class="btn btn-primary btn-lg">Искать</a>--%>
            <%--</div>--%>
            <%--</div>--%>
    <%--&lt;%&ndash;<p><button onclick=" с">Ввести</button></p>&ndash;%&gt;--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</form>--%>
    <div class="row header-info">
        <div class="col-sm-10 col-sm-offset-1 text-center">
            <h3 class="wow fadeIn" style="color: #74716e; margin-top: -30%">Пожалуйста, введите параметры</h3>
            <br />
            <form role="form" method="service" action="/settings">
            <div class="row" >
                <div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">
                    <div class="row">
                        <%--<div class="col-xs-6 text-right wow fadeInUp" data-wow-delay="1s" style=" margin-left: 50%" >--%>
                            <%--<a href="#be-the-first" class="btn btn-secondary btn-lg scroll" style="color: #74716e;">Что это</a>--%>
                        <%--</div>--%>
                            <div class="row">
                                <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                                    <%--<form role="form">--%>
                                        <div class="form-group" >
                                            <input style="color: #5b5957"  type="number" min="1" max="5" name = "depth" value="3" class="form-control" placeholder="введите число" required>
                                        </div>
                                        <%--<button type="submit" class="btn btn-primary btn-lg">Request Invite</button>--%>
                                    <%--</form>--%>
                                </div>
                            </div><!--End Form row-->
                            <div class="row">
                                <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                                    <%--<form role="form" method="service" action="/settings">--%>
                                        <div class="form-group">
                                            <input style="color: #5b5957" type="number" min="1" max="20" name="length" value="5" class="form-control" placeholder="введите число" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary btn-lg" data-wow-delay="1s" >Готово</button>
                                    <%--</form>--%>
                                </div>
                            </div><!--End Form row-->
                        <!--1.4s-->
                            <%--<input type="range" min="0" max="20" value="10" />--%>
                            <%--<input type="range" min="0" max="5" value="3" />--%>
                        <%--<div class="col-xs-6 text-left wow fadeInUp" data-wow-delay="1.4s" style=" margin-left: 50%">--%>
                            <%--<a  class="btn btn-primary btn-lg">Готово</a>--%>
                        <%--</div>--%>
                    </div><!--End Button Row-->
                </div>
            </div>
            </form>
        </div>
    </div>

    <section id="main-info" class="pad-xl">
        <div class="container">
            <div class="row">
                <div class="col-sm-4 wow fadeIn" data-wow-delay="0.4s" >
                    <h3 style="margin-top: -15%; opacity: 0.5;">Как далеко?</h3>
                    <p style="opacity: 0.5">Здесь вы должны обозначить, насколько далеко вы хотите искать. Если среди своих друзей,
                        число должно быть равно 1. Друзей друзей - 2 и т.д. Максимальный уровень = 5</p>
                    <hr class="line purple" style="opacity: 0.7">

                </div>
                <%--<div class="col-sm-4 wow fadeIn" data-wow-delay="0.8s" style="margin-bottom: 20%">--%>
                    <%--<hr >--%>
                    <%--<h3></h3>--%>
                    <%--<p></p>--%>
                <%--</div>--%>
                <div class="col-sm-4 wow fadeIn" data-wow-delay="1.2s" style="margin-left: 68%; ">
                    <hr  class="line yellow" style="margin-top: 15%; opacity: 0.7" >
                    <h3 style="opacity: 0.5">Как широко?</h3>
                    <p style="opacity: 0.5">Здесь вы должны ввести количество друзей, которое будет браться у каждого человека. Максимально количество = 20</p>
                </div>
            </div>
        </div>
    </section>
</header>
</body>



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
                <p><small>Copyright &copy; 2014. All rights reserved. <br>
                    Created by <a href="http://visualsoldiers.com">Visual Soldiers</a></small></p>
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
