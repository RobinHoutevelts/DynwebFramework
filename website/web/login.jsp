<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                    <input class="form-control" type="text" spellcheck="false" placeholder="Email address">
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                    <input class="form-control" type="password" placeholder="Password">
                </div>
                <label class="checkbox">
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
        </div>
    </body>
</html>
