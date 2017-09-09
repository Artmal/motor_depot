<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <link href="${contextPath}/resource/css/registration.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <section>
                <h1 class="entry-title"><span>Sign Up</span> </h1>

                <form action="/register" class="form-horizontal" method="post" name="signup" id="signup"
                      onsubmit="validateInput()">

                    <label for="email">Email*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-envelope"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="email" name="email" required>
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

                    <label for="name">Full Name*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-user-circle-o"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="name" name="name" required>
                    </div>

                    <label for="passport-serial-numbers">Passport Serial Numbers*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-id-card-o"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="passport-serial-numbers"
                               name="passport-serial-numbers" required>
                    </div>

                    <label for="phone-number">Phone Number*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-phone-square"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="phone-number" name="phone-number" required>
                    </div>

                    <label for="age">Age:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-birthday-cake"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="age" name="age">
                    </div>

                    <%--<label for="age">Security code:</label>--%>
                    <%--<div class="input-group mb-2 mr-sm-2 mb-sm-0">--%>
                        <%--<div class="input-group-addon">--%>
                            <%--<i class="fa fa-shield"></i>--%>
                        <%--</div>--%>
                        <%----%>
                        <%--<input name="captchaCode" id = "captchaCode" class="form-control mb-2 mr-sm-2 mb-sm-0">--%>
                    <%--</div>--%>

                    <br>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-10">
                            <input name="Submit" type="submit" value="Sign Up" class="btn btn-primary">
                        </div>
                    </div>
                </form>
        </div>
    </div>
</div>

<script>
//    var password = document.getElementById("password");
//    var confirm_password = document.getElementById("cpassword");
//
//    function validatePassword(){
//        if(password.value != confirm_password.value) {
//            confirm_password.setCustomValidity("Passwords Don't Match");
//        } else {
//            confirm_password.setCustomValidity('');
//        }
//    }
//
//    password.onchange = validatePassword;
//    confirm_password.onkeyup = validatePassword;
//
//    var email = document.getElementById("email");
//    function isEmail(email) {
//        var regex = /^([a-zA-Z0-9_.+-])+@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
//        if(!regex.test(email)) {
//            email.setCustomValidity("Please Enter Valid Email")
//        } else {
//            email.setCustomValidity('');
//        }
//    }
//
//    email.onchange = isEmail();
//
//    function validateInput() {
//        validatePassword();
//        isEmail();
//    }
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/3.2.1/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="stylesheet">
</body>
</html>
