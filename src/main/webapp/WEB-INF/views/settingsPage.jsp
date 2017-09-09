<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <style>
        #leftPanel{
            background-color:#0079ac;
            color:#fff;
            text-align: center;
        }

        #rightPanel{
            min-height:415px;
        }

        /* Credit to bootsnipp.com for the css for the color graph */
        .colorgraph {
            height: 5px;
            border-top: 0;
            background: #c4e17f;
            border-radius: 5px;
            background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
            background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
            background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
            background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
        }
    </style>
</head>
<body>

<div class="container">
    <br>
    <br>
    <div class="row" id="main">
        <div class="col-md-8 well" id="rightPanel">
            <div class="row">
                <div class="col-md-12">
                    <h2>Edit your profile.<small>It's always easy</small></h2>
                    <form action="/my-settings/save" method="post">
                        <hr class="colorgraph">
                        <label for="email">Email*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-envelope"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="email" name="email" value="${user.email}"
                                   required>
                        </div>

                        <label for="password">Password*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-key"></i>
                            </div>
                            <input type="password" class="form-control mb-2 mr-sm-2 mb-sm-0" id="password"
                                   name="password" required>
                        </div>

                        <label for="password">Confirm password*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-key"></i>
                            </div>
                            <input type="password" class="form-control mb-2 mr-sm-2 mb-sm-0" id="cpassword" name="cpassword"
                                   required>
                        </div>

                        <br>
                        <hr class="colorgraph">

                        <div class="form-group">
                            <div class="col-xs-12 col-md-6">
                                <input name="Submit" type="submit" value="Save" class="btn btn-success btn-block btn-lg">
                            </div>
                        </div>

                        <%--<div class="row">--%>
                            <%--<div class="col-xs-12 col-md-6"></div>--%>
                            <%--<button hclass="col-xs-12 col-md-6"><a href="/my-settings/save" class="btn btn-success btn-block btn-lg">Save</a></div>--%>
                        <%--</div>--%>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/3.2.1/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="stylesheet">
</body>
</html>
