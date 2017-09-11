<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dashboard.driversPage" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="adminDashboard.driversPage.pageTitle"/></title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-panel/driversPage.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Data tables -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<%@include file = "../../../resources/jsp/admin_utils/adminHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/admin_utils/adminSidebar.jsp" %>
        <fmt:setBundle basename="i18n.admin_dashboard.driversPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="adminDashboard.driversPage.header"/></h1>

            <c:if test="${not empty setOfDrivers}">
                <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th><fmt:message key="adminDashboard.driversPage.table.driverId"/></th>
                            <th><fmt:message key="adminDashboard.driversPage.name"/></th>
                            <th><fmt:message key="adminDashboard.driversPage.passportSerialNumbers"/></th>
                            <th><fmt:message key="adminDashboard.driversPage.phoneNumber"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${setOfDrivers}" var="driver">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td>${driver.id}</td>
                                <td><a href="${contextPath}/drivers/profile?id=${driver.id}">${driver.name}</a></td>
                                <td>${driver.passportSerialNumbers}</td>
                                <td>${driver.phoneNumber}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                </table>
            </c:if>

            <br>

            <div class="card">
                <div class="card-block">
                    <form class="form-horizontal" action="/admin-dashboard/addDriverServlet" method="post">
                        <label for="email"><fmt:message key="adminDashboard.driversPage.addDriverForm.email"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-envelope fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="email" name="email" required>
                        </div>

                        <label for="password"><fmt:message key="adminDashboard.driversPage.addDriverForm.password"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-key fa-fw"></i>
                            </div>
                            <input type="password" class="form-control mb-2 mr-sm-2 mb-sm-0" id="password" name="password"
                                   required>
                        </div>

                        <label for="name"><fmt:message key = "adminDashboard.driversPage.addDriverForm.fullName"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-user-circle-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="name" name="name" required>
                        </div>

                        <label for="passport-serial-numbers"><fmt:message key="adminDashboard.driversPage.passportSerialNumbers"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-id-card-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="passport-serial-numbers"
                                   name="passport-serial-numbers" required>
                        </div>

                        <label for="phone-number"><fmt:message key="adminDashboard.driversPage.phoneNumber"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-phone-square fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="phone-number"
                                   name="phone-number" required>
                        </div>

                        <label for="age"><fmt:message key="adminDashboard.driversPage.addDriverForm.age"/>:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-birthday-cake fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="age" name="age">
                        </div>
                        <br>
                        <button class="btn btn-primary"><fmt:message key="adminDashboard.driversPage.button.addDriver"/></button>
                </form>
                </div>
            </div>
            <br>
        </main>
    </div>
</div>

<script>
    document.getElementById("drivers-nav-link").classList.add("active");
</script>

<script>
    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>