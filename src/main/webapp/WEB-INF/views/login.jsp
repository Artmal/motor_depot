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

    <!-- Bootstrap CSS & Font Awesome -->
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" >

    <!-- Styles for the page -->
    <link rel="stylesheet" href="${contextPath}/resources/css/login.css">
    <link rel="script" href="${contextPath}/resources/js/validation/login.js">
</head>
<body>
<div class="container">

    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <form id="login-form" action="/loginServlet" method="post">
                <fieldset>
                    <h2>
                        <fmt:message key="login.header" />

                        <a onclick="appendParameters(); return false;" style="float: right">
                            <span class="fa fa-globe"></span>
                        </a>
                    </h2>

                    <hr class="colorgraph">
                    <div class="form-group">
                        <input name="email" id="email" class="form-control input-lg"
                               placeholder="${emailPlaceholderText}">
                    </div>
                    <div id="password-div" class="form-group">
                        <input name="password" id="password" class="form-control input-lg"
                               placeholder="${passwordPlaceholderText}"
                               type="password">
                    </div>

                    <div id="error">
                        <span style = "color: #ff6666"></span>
                    </div>

                    <c:if test="${errorText ne null}">
                        <p id = "error-text" style="color: #ff6666">${errorText}</p>
                    </c:if>

                    <a href="/forgotPassword">
                        <fmt:message key="login.button.forgot"/>
                    </a>
                    <hr class="colorgraph">
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <input id="submit" type="submit" name="submit" class="btn btn-lg btn-success btn-block"
                                   value="${signInButtonText}">
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <a href="/registration" class="btn btn-lg btn-primary btn-block">${registerButtonText}</a>
                        </div>

                        <div class="result"></div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>




<script src="${contextPath}/webjars/jquery/2.1.3/jquery.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
<script src="${contextPath}/resources/js/validation/login.js"></script>

<script src="/resources/js/appendLanguageParamToUrl.js"></script>

<script>
    $(document).on("submit", "#login-form", function(event) {
        var $form = $(this);

        $.post($form.attr("action"), $form.serialize(), function(response) {
            if (response.redirect) {
                window.location = response.redirect;
                return;
            }

            var errorText = $(response).find('#error-text').text();

            $('#error').find('span').text(errorText);
        });

        event.preventDefault();
    });
</script>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>
