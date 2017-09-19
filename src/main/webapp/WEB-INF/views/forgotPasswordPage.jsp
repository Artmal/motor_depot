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

    <!-- reCaptcha -->
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <section>
                <h1 class="entry-title"><span>Password reset</span> </h1><br>

                <form action="/forgotPassword" class="form-horizontal" method="post" name="signup" id="signup">
                    <label for="email"><fmt:message key="registration.label.email"/></label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-envelope"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="email" name="email"
                               type="email">
                    </div><br>

                    <div class="g-recaptcha" data-sitekey="6LdZljAUAAAAADRyMrkM6xlEa_rsj5YtFiLYF0x0"></div>
                    <p class="text-danger">${error}</p><br>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-10">
                            <input name="Submit" type="submit" value="Send new password" class="btn btn-primary">
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/3.2.1/jquery.min.js"><\/script>')</script>
<link href="${contextPath}/webjars/tether/1.4.0/dist/js/tether.min.js" rel="script">
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="stylesheet">
</body>
</html>