<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked admin-menu" >
                <li><a href="" data-target-id="change-password"><i class="glyphicon glyphicon-lock"></i> Change Password</a></li>
                <li><a href="" data-target-id="change-email"><i class="glyphicon glyphicon-cog"></i> Change Email</a></li>
                <li><a href="" data-target-id="logout"><i class="glyphicon glyphicon-log-out"></i> Logout</a></li>
            </ul>
        </div>

        <div class="col-md-9  admin-content" id="change-email">
            <div class="panel panel-info" style="margin: 1em;">
                <div class="panel-heading">
                    <h3 class="panel-title">Notification</h3>
                </div>
                <div class="panel-body">
                    <div class="label label-success">allowed</div>
                </div>
            </div>
            <div class="panel panel-info" style="margin: 1em;">
                <div class="panel-heading">
                    <h3 class="panel-title">Newsletter</h3>
                </div>
                <div class="panel-body">
                    <div class="badge">Monthly</div>
                </div>
            </div>
            <div class="panel panel-info" style="margin: 1em;">
                <div class="panel-heading">
                    <h3 class="panel-title">Admin</h3>

                </div>
                <div class="panel-body">
                    <div class="label label-success">yes</div>
                </div>
            </div>

        </div>

        <div class="col-md-9  admin-content" id="change-password">
            <form action="/password" method="post">


                <div class="panel panel-info" style="margin: 1em;">
                    <div class="panel-heading">
                        <h3 class="panel-title"><label for="new_password" class="control-label panel-title">New Password</label></h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password" id="new_password" >
                            </div>
                        </div>

                    </div>
                </div>


                <div class="panel panel-info" style="margin: 1em;">
                    <div class="panel-heading">
                        <h3 class="panel-title"><label for="confirm_password" class="control-label panel-title">Confirm password</label></h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password_confirmation" id="confirm_password" >
                            </div>
                        </div>
                    </div>
                </div>


                <div class="panel panel-info border" style="margin: 1em;">
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="pull-left">
                                <input type="submit" class="form-control btn btn-primary" name="submit" id="submit">
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>

        <div class="col-md-9  admin-content" id="logout">
            <div class="panel panel-info" style="margin: 1em;">
                <div class="panel-heading">
                    <h3 class="panel-title">Confirm Logout</h3>
                </div>
                <div class="panel-body">
                    Do you really want to logout ?  
                    <a  href="#" class="label label-danger"
                        onclick="event.preventDefault();
                                                     document.getElementById('logout-form').submit();">
                        <span >   Yes   </span>
                    </a>    
                    <a href="/account" class="label label-success"> <span >  No   </span></a>
                </div>
                <form id="logout-form" action="#" method="POST" style="display: none;">
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function()
    {
        var navItems = $('.Admin-menu li > a');
        var navListItems = $('.Admin-menu li');
        var allWells = $('.Admin-content');
        var allWellsExceptFirst = $('.Admin-content:not(:first)');
        allWellsExceptFirst.hide();
        navItems.click(function(e)
        {
            e.preventDefault();
            navListItems.removeClass('active');
            $(this).closest('li').addClass('active');
            allWells.hide();
            var target = $(this).attr('data-target-id');
            $('#' + target).show();
        });
    });
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/3.2.1/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
