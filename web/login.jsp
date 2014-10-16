<%@page import="controller.handlers.AjaxHandlers"%>
<%@page import="controller.handlers.RequestHandlers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="redirect-location" location="c?a=<%= RequestHandlers.HOME.ordinal()%>">
        <meta name="login-handler" handler="c?a=<%= AjaxHandlers.LOGIN.ordinal()%>">
        
        <title>Login</title>
        
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/login.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">

            <form class="form-signin" role="form">
                <h2 class="form-signin-heading">Please sign in</h2>
                <div class="input-group margin-bottom-sm">
                    <span class="input-group-addon"><i class="fa fa-envelope-o fa-fw"></i></span>
                    <input id="loginEmail" class="form-control" type="text" spellcheck="false" placeholder="Email address">
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                    <input id="loginPassword" class="form-control" type="password" placeholder="Password">
                </div>
                <p id="loginError" class="hidden"></p>
                <label class="registerLabel"><p>Need an account? <a>Register now!</a></p></label>
                <button onclick="login()" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
            </form>
        </div>
    </body>
    <script src="js/login.js"></script>
</html>
