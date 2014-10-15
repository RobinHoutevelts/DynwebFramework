<%@page import="controller.handlers.RequestHandlers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/cover.css" rel="stylesheet">
    </head>
    <body>
        <div class="site-wrapper">
            <div class="site-wrapper-inner">
                <div class="cover-container">
                    <div class="masthead clearfix">
                        <div class="inner">
                            <h3 class="masthead-brand">Hot Programmers</h3>
                            <ul class="nav masthead-nav">
                                <li class="active"><a href="#">Home</a></li>
                                <li><a href="c?a=<%= RequestHandlers.ADMIN_OVERVIEW.ordinal()%>">Admin</a></li>
                                <li><a href="c?a=<%= RequestHandlers.QUESTIONS.ordinal()%>">Questions</a></li>
                                <li><a href="c?a=<%= RequestHandlers.LOGIN.ordinal()%>">Login</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="inner cover">
                        <h1 class="cover-heading">Cover your page.</h1>
                        <p class="lead">Cover is a one-page template for building simple and beautiful home pages. Download, edit the text, and add your own fullscreen background photo to make it your own.</p>
                        <p class="lead">
                            <a href="#" class="btn btn-lg btn-default">Learn more</a>
                        </p>
                    </div>
                    <div class="mastfoot">
                        <div class="inner">
                            <p>Template by <a href="http://getbootstrap.com">Bootstrap</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
