<%@page import="controller.handlers.RequestHandlers"%>
<%@page import="domain.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="c?a=<%= RequestHandlers.HOME.ordinal()%>">Hot Programmers</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="">Admin</a></li>
                        <li><a href="#">Questions</a></li>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Help</a></li>
                    </ul>
                    <!--form class="navbar-form navbar-right">
                        <input type="text" class="form-control" placeholder="Search...">
                    </form-->
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li class="active"><a href=""><i class="fa fa-tachometer"></i> Overview</a></li>
                        <li><a href="c?a=<%= RequestHandlers.ADMIN_ANALYTICS.ordinal()%>"><i class="fa fa-line-chart"></i> Analytics</a></li>
                        <li><a href="c?a=<%= RequestHandlers.ADMIN_SITELOG.ordinal()%>"><i class="fa fa-bug"></i> Sitelog</a></li>
                        <li><a href="c?a=<%= RequestHandlers.ADMIN_SETTING.ordinal()%>"><i class="fa fa-sliders"></i> Settings</a></li>
                    </ul>
                    <ul class="nav nav-sidebar">
                        <li><a href="c?a=<%= RequestHandlers.ADMIN_USERS.ordinal()%>"><i class="fa fa-users"></i> Users</a></li>
                        <li><a href="c?a=<%= RequestHandlers.ADMIN_QUESTIONS.ordinal()%>"><i class="fa fa-question"></i><i class="fa fa-exclamation"></i> Questions</a></li>
                    </ul>
                    <!--ul class="nav nav-sidebar">
                        <li><a href="">Nav item again</a></li>
                        <li><a href="">One more nav</a></li>
                        <li><a href="">Another nav item</a></li>
                    </ul-->
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header"><i class="fa fa-tachometer fa-lg"></i> Admin Panel - Overview</h1>

                    <div class="row placeholders">
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <a href="c?a=<%= RequestHandlers.ADMIN_QUESTIONS.ordinal()%>">
                                <!--img src="img/loading.gif" class="img-responsive" alt=""-->
                                <i class="fa fa-question fa-5x"></i><i class="fa fa-exclamation fa-5x"></i>
                                <h4>Questions</h4>
                                <span class="text-muted">Something else</span>
                            </a>
                        </div>
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <a href="c?a=<%= RequestHandlers.ADMIN_USERS.ordinal()%>">
                                <!--img src="img/loading.gif" class="img-responsive" alt=""-->
                                <i class="fa fa-users fa-5x"></i>
                                <h4>Users</h4>
                                <span class="text-muted">Something else</span>
                            </a>
                        </div>
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <a href="c?a=<%= RequestHandlers.ADMIN_ANALYTICS.ordinal()%>">
                                <!--img src="img/loading.gif" class="img-responsive" alt=""-->
                                <i class="fa fa-line-chart fa-5x"></i>
                                <h4>Analytics</h4>
                                <span class="text-muted">Something else</span>
                            </a>
                        </div>
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <a href="c?a=<%= RequestHandlers.ADMIN_SETTING.ordinal()%>">
                                <!--img src="img/loading.gif" class="img-responsive" alt=""-->
                                <i class="fa fa-sliders fa-5x"></i>
                                <h4>Settings</h4>
                                <span class="text-muted">Something else</span>
                            </a>
                        </div>
                    </div>

                    <h2 class="sub-header">Sitelog <i class="fa fa-refresh fa-spin"></i></h2>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Header</th>
                                    <th>Header</th>
                                    <th>Header</th>
                                    <th>Header</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1,001</td>
                                    <td>Lorem</td>
                                    <td>ipsum</td>
                                    <td>dolor</td>
                                    <td>sit</td>
                                </tr>
                                <tr>
                                    <td>1,002</td>
                                    <td>amet</td>
                                    <td>consectetur</td>
                                    <td>adipiscing</td>
                                    <td>elit</td>
                                </tr>
                                <tr>
                                    <td>1,003</td>
                                    <td>Integer</td>
                                    <td>nec</td>
                                    <td>odio</td>
                                    <td>Praesent</td>
                                </tr>
                                <tr>
                                    <td>1,003</td>
                                    <td>libero</td>
                                    <td>Sed</td>
                                    <td>cursus</td>
                                    <td>ante</td>
                                </tr>
                                <tr>
                                    <td>1,004</td>
                                    <td>dapibus</td>
                                    <td>diam</td>
                                    <td>Sed</td>
                                    <td>nisi</td>
                                </tr>
                                <tr>
                                    <td>1,005</td>
                                    <td>Nulla</td>
                                    <td>quis</td>
                                    <td>sem</td>
                                    <td>at</td>
                                </tr>
                                <tr>
                                    <td>1,006</td>
                                    <td>nibh</td>
                                    <td>elementum</td>
                                    <td>imperdiet</td>
                                    <td>Duis</td>
                                </tr>
                                <tr>
                                    <td>1,007</td>
                                    <td>sagittis</td>
                                    <td>ipsum</td>
                                    <td>Praesent</td>
                                    <td>mauris</td>
                                </tr>
                                <tr>
                                    <td>1,008</td>
                                    <td>Fusce</td>
                                    <td>nec</td>
                                    <td>tellus</td>
                                    <td>sed</td>
                                </tr>
                                <tr>
                                    <td>1,009</td>
                                    <td>augue</td>
                                    <td>semper</td>
                                    <td>porta</td>
                                    <td>Mauris</td>
                                </tr>
                                <tr>
                                    <td>1,010</td>
                                    <td>massa</td>
                                    <td>Vestibulum</td>
                                    <td>lacinia</td>
                                    <td>arcu</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
