<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.registration" />

<fmt:message key="registration.button.signUp" var="signUpButtonText" />

<html lang="${language}">
<head>
    <title><fmt:message key="registration.pageTitle"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" >

    <!-- Styles for the page -->
    <link rel="stylesheet" href="${contextPath}/resources/css/registration.css">
    <link href="${contextPath}/resources/css/validation.css" rel="stylesheet">

    <!-- reCaptcha -->
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <section>
                <h1 class="entry-title"><span><fmt:message key="registration.header"/></span> </h1>
                <form id="registration-form" action="/registration" name="signup" method="post">
                    <label for="email"><fmt:message key="registration.label.email"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-envelope"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="email" name="email">
                    </div>

                    <label for="password"><fmt:message key="registration.label.password"/></label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-key"></i>
                        </div>
                        <input type="password" name="password" class="form-control mb-2 mr-sm-2 mb-sm-0" id="password">
                    </div>

                    <label for="password"><fmt:message key="registration.label.confirmPassword"/></label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-key"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="cpassword" name="confirm-password"
                               type="password">
                    </div>

                    <label for="name"><fmt:message key="registration.label.fullName"/></label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-user-circle-o"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="name" name="full-name">
                    </div>

                    <label for="passport-serial-numbers"><fmt:message key="registration.label.passportSerialNumber"/></label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-id-card-o"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="passport-serial-numbers"
                               name="passport-serial-numbers">
                    </div>

                    <label for="phone-number"><fmt:message key="registration.label.phoneNumber"/></label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-phone-square"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="phone-number" name="phone-number">
                    </div>

                    <label for="age"><fmt:message key="registration.label.age"/></label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-birthday-cake"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="age" name="age">
                    </div>

                    <br>

                    <div class="g-recaptcha" data-sitekey="6LdZljAUAAAAADRyMrkM6xlEa_rsj5YtFiLYF0x0"></div>
                    <p class="text-danger">${error}</p>

                    <br>
                    <div class="input-group">
                        <div class="col-xs-offset-3 col-xs-10">
                            <input  name="submit" type="submit" value="${signUpButtonText}" class="btn btn-primary">
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>

<script src="${contextPath}/webjars/jquery/2.1.3/jquery.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
<script src="${contextPath}/resources/js/validation/registration.js"></script>

<!-- Bootstrap core JavaScript
================================================== -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="stylesheet">
</body>
</html>