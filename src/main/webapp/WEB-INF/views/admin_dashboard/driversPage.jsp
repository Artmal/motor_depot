<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-panel/driversPage.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/validation.css" rel="stylesheet">

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

            <c:choose>
                <c:when test="${not empty setOfDrivers}">
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
                </c:when>

                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        <fmt:message key="adminDashboard.driversPage.noDriversYet"/>
                    </div>
                </c:otherwise>
            </c:choose>

            <br>

            <div class="card">
                <div class="card-block">
                    <form id="add-driver-form" class="form-horizontal" action="/admin-dashboard/addDriverServlet" method="post">
                        <label for="email"><fmt:message key="adminDashboard.driversPage.addDriverForm.email"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-envelope fa-fw"></i>
                            </div>
                            <input id="email" name="email" class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="password"><fmt:message key="adminDashboard.driversPage.addDriverForm.password"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-key fa-fw"></i>
                            </div>
                            <input id="password" name="password" class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="full-name"><fmt:message key = "adminDashboard.driversPage.addDriverForm.fullName"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-user-circle-o fa-fw"></i>
                            </div>
                            <input id="full-name" name="full-name" class="form-control mb-2 mr-sm-2 mb-sm-0" >
                        </div>

                        <label for="passport-serial-numbers"><fmt:message key="adminDashboard.driversPage.passportSerialNumbers"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-id-card-o fa-fw"></i>
                            </div>
                            <input id="passport-serial-numbers" name="passport-serial-numbers"
                                   class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="phone-number"><fmt:message key="adminDashboard.driversPage.phoneNumber"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-phone-square fa-fw"></i>
                            </div>
                            <input id="phone-number" name="phone-number" class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="age"><fmt:message key="adminDashboard.driversPage.addDriverForm.age"/>:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-birthday-cake fa-fw"></i>
                            </div>
                            <input id="age" name="age" class="form-control mb-2 mr-sm-2 mb-sm-0" >
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

<script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
<script src="${contextPath}/resources/js/validation/admin_dashboard/driversPage.js"></script>

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