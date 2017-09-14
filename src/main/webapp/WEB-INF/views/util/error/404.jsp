<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.errorPages.errorPage404" />

<html lang="${language}">
<head>
    <title><fmt:message key="errorPage404.pageNotFound"/></title>

    <style>
        .center {text-align: center;
            margin: auto;
        }
    </style>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="span12">
            <div class="hero-unit center">
                <h1><fmt:message key="errorPage404.pageNotFound"/> <small><font face="Tahoma" color="red">
                    <fmt:message key="errorPage404.errorCode"/></font></small>
                </h1>
                <br />
                <p>
                    <fmt:message key="errorPage404.firstMessage"/>
                    <fmt:message key="errorPage404.secondMessage"/>
                </p>
            </div>
            <br />
        </div>
    </div>
</div>

</body>
</html>
