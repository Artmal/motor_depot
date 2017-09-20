<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tagLibrary" uri="http://www.artmal.com/currency" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dashboard.dispatchersPage" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="adminDashboard.dispatchersPage.pageTitle"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-panel/driversPage.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/validation.min.css" rel="stylesheet">

    <!-- Data tables -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<%@include file = "../../../resources/jsp/admin_utils/adminHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/admin_utils/adminSidebar.jsp" %>
        <fmt:setBundle basename="i18n.admin_dashboard.dispatchersPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="adminDashboard.dispatchersPage.header"/></h1>

            <c:choose>
                <c:when test="${not empty setOfDispatchers}">
                    <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th><fmt:message key="adminDashboard.dispatchersPage.table.dispatcherId"/></th>
                            <th><fmt:message key="adminDashboard.dispatchersPage.addDispatcherForm.fullName"/></th>
                            <th><fmt:message key="adminDashboard.dispatchersPage.table.passportSerialNumbers"/></th>
                            <th><fmt:message key="adminDashboard.dispatchersPage.table.phoneNumber"/></th>
                            <th><fmt:message key="adminDashboard.dispatchersPage.table.salary"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${setOfDispatchers}" var="dispatcher">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td>${dispatcher.id}</td>
                                <td>
                                    <a href="${contextPath}/dispatchers/profile?id=${dispatcher.id}">
                                            ${dispatcher.name}
                                    </a>
                                </td>
                                <td>${dispatcher.passportSerialNumbers}</td>
                                <td>${dispatcher.phoneNumber}</td>

                                <td><tagLibrary:currencyTag locale="${language}" paymentInDollars="${dispatcher.salaryInDollars}"/>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        <fmt:message key="adminDashboard.dispatchersPage.noDispatchersYet"/>
                    </div>
                </c:otherwise>
            </c:choose>

            <br>

            <div class="card">
                <div class="card-block">
                    <form id="add-dispatcher-form" action="/admin-dashboard/dispatcherServlet" method="post">
                        <label for="email">
                            <fmt:message key="adminDashboard.dispatchersPage.addDispatcherForm.email"/>*:
                        </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-envelope fa-fw"></i>
                            </div>
                            <input id="email" name="email" class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="password">
                            <fmt:message key="adminDashboard.dispatchersPage.addDispatcherForm.password"/>*:
                        </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-key fa-fw"></i>
                            </div>
                            <input id="password" name="password" type="password"
                                   class="form-control mb-2 mr-sm-2 mb-sm-0" >
                        </div>

                        <label for="full-name">
                            <fmt:message key="adminDashboard.dispatchersPage.addDispatcherForm.fullName"/>*:
                        </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-user-circle-o fa-fw"></i>
                            </div>
                            <input id="full-name" name="full-name" class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="passport-serial-numbers">
                            <fmt:message key="adminDashboard.dispatchersPage.table.passportSerialNumbers"/>*:
                        </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-id-card-o fa-fw"></i>
                            </div>
                            <input id="passport-serial-numbers" name="passport-serial-numbers"
                                   class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="phone-number">
                            <fmt:message key="adminDashboard.dispatchersPage.table.phoneNumber"/>*:
                        </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-phone-square fa-fw"></i>
                            </div>
                            <input id="phone-number" name="phone-number" class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>

                        <label for="salary-in-dollars">
                            <fmt:message key="adminDashboard.dispatchersPage.table.salary"/>:
                        </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-usd fa-fw"></i>
                            </div>
                            <input id="salary-in-dollars" name="salary-in-dollars"
                                   class="form-control mb-2 mr-sm-2 mb-sm-0" >
                        </div><br>

                        <button class="btn btn-primary">
                            <fmt:message key="adminDashboard.dispatchersPage.button.addDispatcher"/>
                        </button>
                    </form>
                </div>
            </div>
            <br>
        </main>
    </div>
</div>

<script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
<script src="${contextPath}/resources/js/validation/admin_dashboard/dispatchersPage.min.js"></script>

<script>
    document.getElementById("dispatchers-nav-link").classList.add("active");
</script>

<script>
    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<link href="${contextPath}/webjars/tether/1.4.0/dist/js/tether.min.js" rel="script">
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>