<%@ page import="java.util.ArrayList" %>
<%@ page import="com.friendsfinder.vkontakte.VKProfile" %><%--
  Created by IntelliJ IDEA.
  User: jhonny
  Date: 08.04.18
  Time: 4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList< VKProfile> friends = (ArrayList<VKProfile>)session.getAttribute("friends");
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

    <script>
        function getdetails(obj) {
            alert(obj.id);
        }
    </script>


<body>
<title>FFinder</title>

<style>
    .layer {
        overflow: scroll; /* Добавляем полосы прокрутки */
        width: 100%; /* Ширина блока */
    }
</style>

<header class="layer">
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <a href="/"><img src="img/logo3.png" alt="Logo"></a>
            </div>
        </div>

    </div>
    <div class="container">
<div class="row">
    <div class="col-md-4">
    <%--<div class="layer">--%>
    <body>
    <form method="get" action="/profile" style="margin-top: 30pt">
<%int i = 0;%>
   <% for (VKProfile current : friends) { %>
        <div class="row">
        <div class="col-xs-4 text-left wow fadeInUp" >
            <a data-wow-delay="0.2s" class="btn btn-primary btn-lg" style="width:500px" name="but" href="/profile?i=<%=i++%>">  <%= current.getProfileInfo().get("first_name") %>
                <%= current.getProfileInfo().get("last_name") %></a>
        </div>
        </div>
    </Br>
    <% } %></form>
    </body>
    <%--</div>--%>
    </div>

    <div class="col-sm-4 col-md-offset-3" data-wow-delay="1.2s">
            <hr  class="line yellow" style="margin-top: 15%; opacity: 0.6" >
            <h3 style="opacity: 0.4">Список Ваших друзей</h3>
            <p style="opacity: 0.4">Здесь представлен список всех найденных друзей! Сверху располагаются самые "близкие" друзья, а чем ниже, тем большая цепочка друзей вас связывает. Чтобы узнать информаицию о человеке, просто кликните на его имя</p>
    </div>

</div>
</head>
    </div>
</header>


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
