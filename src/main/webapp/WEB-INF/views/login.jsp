<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.login" />

<fmt:message key="login.placeholder.email" var="emailPlaceholderText" />
<fmt:message key="login.placeholder.password" var="passwordPlaceholderText" />
<fmt:message key="login.button.signIn" var="signInButtonText" />
<fmt:message key="login.button.register" var="registerButtonText" />

<html lang="${language}">
<head>
    <title>
        <fmt:message key="login.pageTitle"/>
    </title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">

    <!-- JQuery -->
    <link rel="script" href="${contextPath}/webjars/jquery/1.12.4/jquery.min.js">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${contextPath}/resources/css/login.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" >
</head>
<body>
<div class="container">

    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form id="changeLanguageForm" hidden>
                <button id="languageChangeButton" hidden name = "languageChange" value="change"></button>
            </form>

            <form id="loginForm" action="/loginServlet" method="post">
                <fieldset>
                    <h2>
                        <fmt:message key="login.header" />

                        <a onclick="appendParameters(); return false;" style="float: right">
                            <span class="fa fa-globe"></span>
                        </a>
                    </h2>

                    <hr class="colorgraph">
                    <div class="form-group">
                        <input name="email" id="email" class="form-control input-lg required"
                               placeholder="${emailPlaceholderText}"
                               type="email" required >
                    </div>
                    <div class="form-group">
                        <input name="password" id="password" class="form-control input-lg"
                               placeholder="${passwordPlaceholderText}"
                               type="password" required>
                    </div>

                    <c:if test="${errorText ne null}">
                        <p style="color: #ff6666">${errorText}</p>
                    </c:if>

                    <a href="/forgotPassword">
                        <fmt:message key="login.button.forgot"/>
                    </a>
                    <hr class="colorgraph">
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <input id="signInButton" type="submit" class="btn btn-lg btn-success btn-block"
                                   value="${signInButtonText}">
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <a href="/registration" class="btn btn-lg btn-primary btn-block">${registerButtonText}</a>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<script>
    function appendParameters() {
        var separator = (window.location.href.indexOf("?")===-1)?"?":"&";
        if (/language/.test(window.location.href)) {
            if(/language=ru/.test(window.location.href)) {
                window.location.href = window.location.href.replace("ru", "en");
            } else if(/language=en/.test(window.location.href)) {
                window.location.href = window.location.href.replace("en", "ru");
            }
        } else {
            window.location.href = window.location.href + separator + "language=ru";
        }

    }
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>
