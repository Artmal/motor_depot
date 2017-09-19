<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>


<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dashboard.carsPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="adminDashboard.carsPage.header"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page-->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-panel/driversPage.css" rel="stylesheet">

    <!-- Data tables imports -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<%@include file = "../../../resources/jsp/admin_utils/adminHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/admin_utils/adminSidebar.jsp" %>

        <fmt:setBundle basename="i18n.admin_dashboard.carsPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="adminDashboard.carsPage.header"/></h1>

            <c:choose>
                <c:when test="${not empty setOfCars}">
                    <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th><fmt:message key="adminDashboard.carsPage.carId"/></th>
                            <th><fmt:message key="adminDashboard.carsPage.registrationNumber"/></th>
                            <th><fmt:message key="adminDashboard.carsPage.type"/></th>
                            <th><fmt:message key="adminDashboard.carsPage.condition"/></th>
                            <th><fmt:message key="adminDashboard.carsPage.condition"/></th>
                            <th><fmt:message key="adminDashboard.carsPage.numberOfSeats"/></th>
                            <th><fmt:message key="adminDashboard.carsPage.color"/></th>
                            <th><fmt:message key="adminDashboard.carsPage.driverId"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${setOfCars}" var="car">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td><a href="/admin-dashboard/cars/car?id=${car.id}">${car.id}</a></td>
                                <td>${car.registrationNumber}</td>
                                <td>
                                    <custom:printTypeCarRequiredFmt carTypeRequired="${car.type}"/>
                                </td>
                                <td>
                                    <c:if test="${car.condition eq 'Broken'}">
                                        <span class="badge badge-danger">
                                            <fmt:message key="adminDashboard.carsPage.broken"/>
                                        </span>
                                    </c:if>
                                    <c:if test="${car.condition eq 'Repairing'}">
                                        <span class="badge badge-warning">
                                            <fmt:message key="adminDashboard.carsPage.repairing"/>
                                        </span>
                                    </c:if>
                                    <c:if test="${car.condition eq 'Ready'}">
                                        <span class="badge badge-success">
                                            <fmt:message key="adminDashboard.carsPage.ready"/>
                                        </span>
                                    </c:if>
                                </td>
                                <td>${car.model}</td>
                                <td>${car.numberOfSeats}</td>
                                <td>${car.color}</td>
                                <td><a href="${contextPath}/drivers/profile?id=${car.ownerId}">${car.ownerId}</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        <fmt:message key="adminDashboard.carsPage.noCarsYet"/>
                    </div>
                </c:otherwise>
            </c:choose>
            <br>

            <custom:addCarForm/>
            <br>
        </main>
    </div>
</div>

<script>
    document.getElementById("cars-nav-link").classList.add("active");
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