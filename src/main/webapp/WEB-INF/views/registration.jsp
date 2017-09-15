<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.registration" />

<fmt:message key="registration.button.signUp" var="signUpButtonText" />

<html lang="${language}">
<head>
    <title><fmt:message key="registration.pageTitle"/></title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <link href="${contextPath}/resource/css/registration.css" rel="stylesheet">

    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <section>
                <h1 class="entry-title"><span><fmt:message key="registration.header"/></span> </h1>

                <form action="/register" class="form-horizontal" method="post" name="signup" id="signup">
                    <label for="email"><fmt:message key="registration.label.email"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-envelope"></i>
                        </div>
                        <input type="email" class="form-control mb-2 mr-sm-2 mb-sm-0" id="email" name="email" required>
                    </div>

                    <label for="password"><fmt:message key="registration.label.password"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-key"></i>
                        </div>
                        <input type="password" class="form-control mb-2 mr-sm-2 mb-sm-0" id="password"
                               name="password" required>
                    </div>

                    <label for="password"><fmt:message key="registration.label.confirmPassword"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-key"></i>
                        </div>
                        <input type="password" class="form-control mb-2 mr-sm-2 mb-sm-0" id="cpassword" name="cpassword"
                               required>
                    </div>

                    <label for="name"><fmt:message key="registration.label.fullName"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-user-circle-o"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="name" name="name"
                               pattern="^[A-ZА-Я][a-zа-я]+ [A-ZА-Я][a-zа-я]+$" required>
                    </div>

                    <label for="passport-serial-numbers"><fmt:message key="registration.label.passportSerialNumber"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-id-card-o"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="passport-serial-numbers"
                               name="passport-serial-numbers" required>
                    </div>

                    <label for="phone-number"><fmt:message key="registration.label.phoneNumber"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-phone-square"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="phone-number" name="phone-number" required>
                    </div>

                    <label for="age"><fmt:message key="registration.label.age"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-birthday-cake"></i>
                        </div>
                        <input type="number" class="form-control mb-2 mr-sm-2 mb-sm-0" id="age" name="age">
                    </div>

                    <br>

                    <div class="g-recaptcha" data-sitekey="6LdZljAUAAAAADRyMrkM6xlEa_rsj5YtFiLYF0x0"></div>
                    <p class="text-danger">${error}</p>

                    <br>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-10">
                            <input onclick="return checkPass()" name="Submit" type="submit" value="${signUpButtonText}" class="btn btn-primary">
                        </div>
                    </div>
                </form>
        </div>
    </div>
</div>

<script>
    function checkPass()
    {
        //Store the password field objects into variables ...
        var pass1 = document.getElementById('password');
        var pass2 = document.getElementById('cpassword');

        //Store the Confimation Message Object ...
        var message = document.getElementById('confirmMessage');

        var badColor = "#ff6666";
        if(pass1.value !== pass2.value){
            pass2.style.backgroundColor = badColor;
            message.style.color = badColor;
            message.innerHTML = "Passwords Do Not Match!";
            return false;
        }
    }
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