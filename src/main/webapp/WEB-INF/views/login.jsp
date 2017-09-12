<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/login.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form id="changeLanguageForm" hidden>
                <button id="languageChangeButton" hidden name = "languageChange" value="change"></button>
            </form>

            <form action="/loginServlet" method="post">
                <fieldset>
                    <h2>
                        <fmt:message key="login.header" />

                        <a href = "javascript:document.getElementById('languageChangeButton').click();" style="float: right">
                            <span class="fa fa-globe"></span>
                        </a>
                    </h2>

                    <hr class="colorgraph">
                    <div class="form-group">
                        <input type="email" name="email" id="email" class="form-control input-lg"
                               placeholder="${emailPlaceholderText}">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="${passwordPlaceholderText}">
                    </div>

                    <c:if test="${errorText ne null}">
                        <p style="color: #ff6666">${errorText}</p>
                    </c:if>

                    <a href="#">
                        <fmt:message key="login.button.forgot"/>
                    </a>
                    </span>
                    <hr class="colorgraph">
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <input type="submit" class="btn btn-lg btn-success btn-block"
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

<link href="${contextPath}/resources/js/login.js" rel="script">

<script>
    function changeLanguage() {
        var form = document.createElement("form");
        var button = document.createElement("button");

        button.name = "languageChange";
        button.value = "change";

        form.appendChild(button);
        document.appendChild(form)
    }
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/3.2.1/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>
