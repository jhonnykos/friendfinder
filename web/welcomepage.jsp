<%--
  Created by IntelliJ IDEA.
  User: jhonny
  Date: 29.03.18
  Time: 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=windows-1251" language="java"
         pageEncoding="utf-8" %>

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
<title>FFinder</title>

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


<form method="post" action="/sections">
    <div class="row header-info">
        <div class="col-sm-10 col-sm-offset-1 text-center">
            <h3 class="wow fadeIn" style="color: #74716e; margin-top: -30%">Здравствуй, <%=session.getAttribute("name")%>!</h3>
            <br />
            <p class="lead wow fadeIn" data-wow-delay="0.5s" style="color: #79838f">Введите список интересующих тем:</p>
            <br />
            <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                <form role="form">
                    <div class="form-group">
                        <input type="text" name = "keyword" class="form-control" placeholder="ключевые темы" required>
                    </div>
                </form>
                <p class="lead wow fadeIn" data-wow-delay="0.5s" style="color: #79838f">Выберите, где искать:</p>
                <br />

            <div class="col-sm-2 col-sm-offset-2 col-md-4 col-md-offset-3" style="font-size: 17pt; text-align: left;">
                    <div class="form-group" data-wow-delay="0.5s"  style="color: #79838f" aria-required="true">
                        <input type="checkbox" name = "interests" style="font-size: 16pt"> Интересы <Br>
                        <input type="checkbox" name = "about" style="font-size: 16pt"> О себе <Br>
                        <input type="checkbox" name = "careers" style="font-size: 16pt"> Профессия<Br>
                        <input type="checkbox" name = "wall" style="font-size: 16pt"> Стена <Br>
                    </div>
            </div>
                <button type="submit" onclick="/find" class="btn btn-primary btn-lg">Найти</button>
            </div>
        </div>
    </div>
</form>
</header>


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
