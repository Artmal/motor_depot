<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripInfoPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Trip Info</title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/dispatcherDashboard/tripInfoPageStyle.min.css" rel="stylesheet">

    <!-- Data tables -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<c:choose>
    <c:when test = "${sessionScope.role eq 'Admin'}">
        <%@include file = "../../../resources/jsp/admin_utils/adminHeader.jsp" %>
    </c:when>

    <c:when test = "${sessionScope.role eq 'Dispatcher'}">
        <%@include file = "../../../resources/jsp/dispatcher_utils/dispatcherHeader.jsp" %>
    </c:when>
</c:choose>

<div class="container-fluid">
    <div class="row">
        <c:choose>
            <c:when test = "${sessionScope.role eq 'Admin'}">
                <%@include file = "../../../resources/jsp/admin_utils/adminSidebar.jsp" %>
            </c:when>

            <c:when test = "${sessionScope.role eq 'Dispatcher'}">
                <%@include file = "../../../resources/jsp/dispatcher_utils/dispatcherSidebar.jsp" %>
            </c:when>
        </c:choose>

        <fmt:setBundle basename="i18n.admin_dispatcher.tripInfoPage" />

        <c:choose>
            <c:when test="${sessionScope.role eq 'Admin'}">
                <c:set var="editTripHref" value="/admin-dashboard/edit-trip?id=${trip.id}"/>
                <c:set var="deleteTripHref" value="/admin-dashboard/delete-trip?id=${trip.id}"/>
            </c:when>
            <c:when test="${sessionScope.role eq 'Dispatcher'}">
                <c:set var="editTripHref" value="/dispatcher-dashboard/edit-trip?id=${trip.id}"/>
                <c:set var="deleteTripHref" value="/dispatcher-dashboard/delete-trip?id=${trip.id}"/>
            </c:when>
        </c:choose>

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <div id = "trip-info" class="card card-outline-info">
                <div class="card-block">
                    <h3 class="card-title"><fmt:message key="adminDispatcher.tripInfoPage.header"/>${trip.id}
                        <a href="${deleteTripHref}">
                            <i style="font-size: 20px; color: crimson; margin: auto; float: right" class="btn btn-default fa fa-times fa-fw"></i>
                        </a>
                        <a href="${editTripHref}">
                            <i style="font-size: 20px; color: darkorange; float: right;" class="btn btn-default fa fa-pencil fa-fw"></i>
                        </a>
                    </h3>

                    <i class="fa fa-calendar-plus-o fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.dateOfCreation"/>:</strong>
                    ${trip.dateOfCreation}
                    <br>

                    <i class="fa fa-tasks fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.status"/>:</strong>
                    <custom:printTripStatusFmt tripStatus="${trip.tripStatus}"/>
                    <br>

                    <i class="fa fa-car fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.carTypeRequired"/>:</strong>
                    <custom:printTypeCarRequiredFmt carTypeRequired="${trip.carTypeRequired}"/>
                    <br>

                    <i class="fa fa-id-card-o fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.carId"/>:</strong>
                    <c:choose>
                        <c:when test="${trip.carId eq '0'}">
                            <span class="badge badge-pill badge-success">
                                <fmt:message key="adminDispatcher.tripInfoPage.content.badge.freeSpot"/>
                            </span>
                        </c:when>
                        <c:otherwise>
                            ${trip.carId}
                        </c:otherwise>
                    </c:choose>
                    <br>

                    <i class="fa fa-building-o fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.townFrom"/>:</strong> ${trip.townFrom}
                    <br>

                    <i class="fa fa-building fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.townTo"/>:</strong> ${trip.townTo}
                    <br>

                    <i class="fa fa-calendar fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.timeOut"/>:</strong>
                        ${trip.timeOut.toString("yyyy-MM-dd HH:mm")}
                    <br>

                    <i class="fa fa-calendar-check-o fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.timeIn"/>:</strong>
                        ${trip.timeIn.toString("yyyy-MM-dd HH:mm")}
                    <br>

                    <i class="fa fa-usd fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.payment"/>:</strong>
                        ${trip.paymentInDollars}
                    <br>

                    <i class="fa fa-user-plus fa-fw"></i>
                    <strong><fmt:message key="adminDispatcher.tripInfoPage.dispatcherId"/>:</strong>
                        ${trip.dispatcherId}
                </div>
            </div>

            <hr>
            <h3><fmt:message key="adminDispatcher.tripInfoPage.requests"/></h3>

            <c:choose>
                <c:when test="${not empty setOfTripRequests}">
                    <c:forEach items="${setOfTripRequests}" var="tripRequest">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <div class="card">
                            <div class="card-header">
                                <c:if test="${trip.carId eq tripRequest.carInfo.id}">
                                    <i class="fa fa-check-square-o fa-fw"></i>
                                </c:if>
                                <fmt:message key="adminDispatcher.tripInfoPage.requestHeader"/>${tripRequest.id}
                                <c:choose>
                                    <c:when test="${sessionScope.role eq 'Admin'}">
                                        <a href="/admin-dashboard/trip/deny?trip-request-id=${tripRequest.id}&trip-id=${trip.id}"
                                           style="float: right"
                                           class="btn btn-danger btn-sm btn-space">
                                            <fmt:message key="adminDispatcher.tripInfoPage.deny"/>
                                        </a>
                                        <a href="/admin-dashboard/trip/accept?trip-request-id=${tripRequest.id}&trip-id=${trip.id}"
                                           style="float: right"
                                           class="btn btn-success btn-sm btn-space">
                                            <fmt:message key="adminDispatcher.tripInfoPage.accept"/>
                                        </a>
                                    </c:when>
                                    <c:when test="${sessionScope.role eq 'Dispatcher'}">
                                        <a href="/dispatcher-dashboard/trip/deny?trip-request-id=${tripRequest.id}&trip-id=${trip.id}"
                                           style="float: right"
                                           class="btn btn-danger btn-sm btn-space">
                                            Deny
                                        </a>
                                        <a href="/dispatcher-dashboard/trip/accept?trip-request-id=${tripRequest.id}&trip-id=${trip.id}"
                                           style="float: right"
                                           class="btn btn-success btn-sm btn-space">
                                            Accept
                                        </a>
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="card-block">
                                <i class="fa fa-user-circle-o fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.tripInfoPage.driver"/>:</strong>
                                <a href="/drivers/profile?id=${tripRequest.carInfo.ownerId}">
                                    Driver №${tripRequest.carInfo.ownerId}
                                </a><br>

                                <i class="fa fa-car fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.tripInfoPage.carModel"/> </strong>
                                    ${tripRequest.carInfo.model}<br>

                                <i class="fa fa-users fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.tripInfoPage.numberOfSeats"/> </strong>
                                    ${tripRequest.carInfo.numberOfSeats}<br>

                                <i class="fa fa-tint fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.tripInfoPage.carColor"/> </strong>
                                    ${tripRequest.carInfo.color}<br>

                                <i class="fa fa-clock-o fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.tripInfoPage.dateOfCreation"/>:</strong>
                                    ${tripRequest.dateOfCreation.toString("yyyy-MM-dd HH:mm")}<br>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        <fmt:message key="adminDispatcher.tripInfoPage.noRequestsYet"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </main>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>

<%-- Disable Accept buttons if there is the car for the trip--%>
<script>
    var acceptButtons = document.getElementsByClassName("btn-success");
    var denyButtons = document.getElementsByClassName("btn-danger");

    if(${trip.tripStatus.displayName() eq 'Canceled' or trip.tripStatus.displayName() eq 'Closed'}) {
        for(var i = 0; i < acceptButtons.length; i++) {
            acceptButtons[i].classList.add("disabled");
            denyButtons[i].classList.add("disabled");
        }
    }
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<link href="${contextPath}/webjars/tether/1.4.0/dist/js/tether.min.js" rel="script">
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>