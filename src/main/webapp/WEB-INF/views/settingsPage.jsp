<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.settings" />

<fmt:message key="settings.button.save" var="saveButtonText" />

<html>
<head>
    <title><fmt:message key="settings.pageTitle"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <link href="${contextPath}/resources/css/validation.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <br>
    <br>
    <div class="container">
        <div class="row" style="margin-top:20px">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <form id="settings-form" name="settings-form" action="/my-settings/save" method="post">
                    <fieldset>
                        <h2><fmt:message key="settings.header"/></h2>
                        <label for="email"><fmt:message key="settings.label.email"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-envelope"></i>
                            </div>
                            <input id="email" name="email" class="form-control mb-2 mr-sm-2 mb-sm-0"
                                   value="${user.email}">
                        </div>

                        <label for="password"><fmt:message key="settings.label.password"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-key"></i>
                            </div>
                            <input id="password" name="password" type="password"
                                   class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="confirm-password"><fmt:message key="settings.label.confirmPassword"/></label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-key"></i>
                            </div>
                            <input id="confirm-password" name="confirm-password" type="password"
                                   class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div><br>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <input type="submit" class="btn btn-lg btn-success btn-block"
                                       value="${saveButtonText}">
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/webjars/jquery/2.1.3/jquery.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
<script src="${contextPath}/resources/js/validation/settingsPage.min.js"></script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<link href="${contextPath}/webjars/tether/1.4.0/dist/js/tether.min.js" rel="script">
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="stylesheet">
</body>
</html>
