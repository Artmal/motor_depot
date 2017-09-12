<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Trips</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

    <!-- Data tables -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<%@include file = "../../../resources/jsp/driver_utils/driverHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/driver_utils/driverSidebar.jsp" %>

        <fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />
        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="tripsPage.content.trips"/></h1>

            <c:if test="${not empty setOfTrips}">
            <table id="example" class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><fmt:message key="tripsPage.content.table.tripId"/></th>
                    <th><fmt:message key="tripsPage.content.table.dateOfCreation"/></th>
                    <th><fmt:message key="tripsPage.content.table.status"/></th>
                    <th><fmt:message key="tripsPage.content.table.carTypeRequired"/></th>
                    <th><fmt:message key="tripsPage.content.table.carId"/></th>
                    <th><fmt:message key="tripsPage.content.table.townFrom"/></th>
                    <th><fmt:message key="tripsPage.content.table.townTo"/></th>
                    <th><fmt:message key="tripsPage.content.table.timeOut"/></th>
                    <th><fmt:message key="tripsPage.content.table.timeIn"/></th>
                    <th><fmt:message key="tripsPage.content.table.payment"/></th>
                    <th><fmt:message key="tripsPage.content.table.dispatcherId"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${setOfTrips}" var="trip">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td><a href="/driver-dashboard/trip?trip-id=${trip.id}">${trip.id}</a></td>
                        <td>${trip.dateOfCreation}</td>
                        <td>
                            <c:if test="${trip.tripStatus.displayName() eq 'Open'}">
                                <span class="badge badge-success"><fmt:message key="tripsPage.content.option.open"/></span>
                            </c:if>
                            <c:if test="${trip.tripStatus.displayName() eq 'In progress'}">
                                <span class="badge badge-warning"><fmt:message key="tripsPage.content.option.inProgress"/></span>
                            </c:if>
                            <c:if test="${trip.tripStatus.displayName() eq 'Closed'}">
                                <span class="badge badge-default"><fmt:message key="tripsPage.content.option.closed"/></span>
                            </c:if>
                            <c:if test="${trip.tripStatus.displayName() eq 'Canceled'}">
                                <span class="badge badge-danger"><fmt:message key="tripsPage.content.option.canceled"/></span>
                            </c:if>
                        </td>
                        <td>${trip.carTypeRequired.displayName()}</td>
                        <td>
                            <c:choose>
                            <c:when test="${trip.carId eq '0'}">
                            <span class="badge badge-pill badge-success"><fmt:message key="tripsPage.content.badge.freeSpot"/></span>
                            </c:when>
                            <c:otherwise>
                                ${trip.carId}
                            </c:otherwise>
                            </c:choose>
                        <td>${trip.townFrom}</td>
                        <td>${trip.townTo}</td>
                        <td>${trip.timeOut.toString("yyyy-MM-dd HH:mm")}</td>
                        <td>${trip.timeIn.toString("yyyy-MM-dd HH:mm")}</td>

                        <td>${trip.paymentInDollars}</td>
                        <td>
                            <c:choose>
                                <c:when test="${trip.dispatcherId eq 0}">
                                    Admin
                                </c:when>
                                <c:otherwise>
                                    ${trip.dispatcherId}</td>
                                </c:otherwise>
                            </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </c:if>
            <br>
        </main>
    </div>
</div>

<script>
    document.getElementById("trips-nav-link").classList.add("active");
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
