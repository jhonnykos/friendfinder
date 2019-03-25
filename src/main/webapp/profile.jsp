<%@ page import="com.friendsfinder.vkontakte.VKProfile" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%--
  Created by IntelliJ IDEA.
  User: jhonny
  Date: 08.04.18
  Time: 4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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


<%
    ArrayList< VKProfile> friends = (ArrayList<VKProfile>)session.getAttribute("friends");
   String i = session.getAttribute("number").toString();
    System.out.println("i = " + i);
    VKProfile profile = friends.get(Integer.parseInt(i));
    String name = profile.getProfileInfo().get("first_name") + " " + profile.getProfileInfo().get("last_name");
    ArrayList<String> parents = new ArrayList<>();
     String pcount = profile.getProfileInfo().get("postcount");
     String fcount = profile.getProfileInfo().get("friendcount");
     String hr = "http://vk.com/id" + profile.getProfileInfo().get("id");
     String about ="";
     Set<String> keys = profile.getProfileInfo().keySet();
     for(String key: keys){
         if(key.equals("about")){ about = profile.getProfileInfo().get("about"); break;}
     }

     String sections="";
     String sec = profile.getSections();
     if(sec.contains("wall")) sections+="Стена, ";
     if(sec.contains("about")) sections+="О себе, ";
     if(sec.contains("interests")) sections+="Интересы, ";
     if(sec.contains("career")) sections+="Карьера, ";
     if(sections.length()>0) sections = String.copyValueOf(sections.toCharArray(),0,sections.length()-2);

     ArrayList<VKProfile> chain = VKProfile.getParents(profile);
     System.out.println("chain size = " + chain.size());

%>



<body>
<title>FFinder</title>

<style>
    .layer {
        overflow: scroll; /* Добавляем полосы прокрутки */
        width: 100%; /* Ширина блока */
        height: 50pt; /* Высота блока */
        padding: 5px; /* Поля вокруг текста */
    }
</style>

<header>
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <a href="/"><img src="img/logo3.png" alt="Logo"></a>
            </div>
        </div>

    </div>


    <div class="row center-block" style="margin-top: 50pt">
        <div class="col-md-offset-2  col-md-4 col-lg-offset-3 col-lg-6">
            <div class="well profile">
                <div class="col-sm-12">
                    <div class="col-xs-12 col-sm-8" style="color: #74716e">
                        <h2 style="color: #5b5957"><%=name%></h2>
                        <p><strong>Город:</strong> <%=profile.getProfileInfo().get("city")%> </p>
                        <p><strong>Обо мне: </strong> <div class="layer"><%=about%> </div></p>
                        <p><strong>Совпадения в разделах:</strong> <%=sections%> </p>
                        <form>
                        <p><strong>Цепочка друзей: </strong></p>
                        <% for (VKProfile current : chain) { %>
                            <%String hre = "http://vk.com/id" + current.getProfileInfo().get("id");%>
                            <%String n = current.getProfileInfo().get("first_name") + " " + current.getProfileInfo().get("last_name"); %>
                        <a href=<%=hre%>><%=n%>-> </a>
                        <% } %>
                        </form>
                    </div>
                    <div class="col-xs-12 col-sm-4 text-center">
                        <figure>
                            <img style="width: 100pt; height: 100pt" src=<%=profile.getProfileInfo().get("photo")%> alt="user" class="img-circle img-responsive">

                        </figure>
                    </div>
                </div>
                <div class="col-xs-12 divider text-center">
                    <div class="col-xs-12 col-sm-4 emphasis">
                        <h2 style="color: #74716e"><strong> <%=fcount%> </strong></h2>
                        <p><small>Друзей</small></p>
                    </div>
                    <div class="col-xs-12 col-sm-4 emphasis">
                        <h2 style="color: #74716e"><strong><%=pcount%></strong></h2>
                        <p><small>Записей</small></p>
                        <form method="post" action=<%=hr%>>
                        <button type="submit" onclick="<%=hr%>" class="btn btn-primary btn-lg"><span class="fa fa-user" ></span>Перейти в профиль</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

        </head>

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
