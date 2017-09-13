<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.driver_dashboard.tripInfoPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="driverDashboard.tripInfoPage.pageTitle"/>${trip.id}</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/dispatcherDashboard/tripInfoPageStyle.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Data tables -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<%@include file = "../../../resources/jsp/driver_utils/driverHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/driver_utils/driverSidebar.jsp" %>
        <fmt:setBundle basename="i18n.driver_dashboard.tripInfoPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <div class="card card-outline-info">
                <div class="card-block">
                    <h4 class="card-title"><fmt:message key="driverDashboard.tripInfoPage.header"/>${trip.id}</h4>
                    <i class="fa fa-calendar-plus-o fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.dateOfCreation"/>:</strong> ${trip.dateOfCreation}
                    <br>
                    <i class="fa fa-tasks fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.status"/>:</strong>
                    <custom:printTripStatusFmt tripStatus="${trip.tripStatus}"/>
                    <br>
                    <i class="fa fa-car fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.carTypeRequired"/>:</strong>
                    <custom:printTypeCarRequiredFmt carTypeRequired="${trip.carTypeRequired}"/>
                    <br>
                    <i class="fa fa-id-card-o fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.carId"/>:</strong>
                    <c:choose>
                        <c:when test="${trip.carId eq '0'}">
                            <span class="badge badge-pill badge-success"><fmt:message key="driverDashoard.tripInfoPage.badge.freeSpot"/></span>
                        </c:when>
                        <c:otherwise>
                            ${trip.carId}
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <i class="fa fa-building-o fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.townFrom"/>:</strong> ${trip.townFrom}
                    <br>
                    <i class="fa fa-building fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.townTo"/>:</strong> ${trip.townTo}
                    <br>
                    <i class="fa fa-calendar fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.timeOut"/>:</strong> ${trip.timeIn.toString("yyyy-MM-dd HH:mm")}
                    <br>
                    <i class="fa fa-calendar-check-o fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.timeIn"/>:</strong> ${trip.timeOut.toString("yyyy-MM-dd HH:mm")}
                    <br>
                    <i class="fa fa-usd fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.payment"/>:</strong> ${trip.paymentInDollars}
                    <br>
                    <i class="fa fa-user-plus fa-fw"></i>
                    <strong><fmt:message key="driverDashboard.tripInfoPage.dispatcherId"/>:</strong> ${trip.dispatcherId}
                </div>
            </div>

            <br>

            <c:choose>

                <c:when test = "${trip.tripStatus.displayName() ne 'Open'}">
                    <div class="card card-inverse" style="background-color: #333; border-color: #333;">
                        <div class="card-block">
                            <h3 class="card-title"><fmt:message key="driverDashboard.tripInfoPage.closedRequest"/></h3>
                            <a href="/driver-dashboard/trips" class="btn btn-primary"><fmt:message key="driverDashboard.tripInfoPage.button.goToTrips"/></a>
                        </div>
                    </div>
                </c:when>
                
                <c:when test="${empty setOfSuitableCars}">
                    <div class="card card-inverse" style="background-color: #333; border-color: #333;">
                        <div class="card-block">
                            <h3 class="card-title"><fmt:message key="driverDashboard.tripInfoPage.closedRequest"/></h3>
                            <p><fmt:message key="driverDashoard.tripInfoPage.noSuitableCar"/></p>
                            <a href="/driver-dashboard/trips" class="btn btn-primary"><fmt:message key="driverDashboard.tripInfoPage.button.goToTrips"/></a>
                        </div>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="card">
                        <div class="card-block">
                            <form class="form-horizontal" action="/driver-dashboard/trip" accept-charset="UTF-8" method="post">
                                <label for="car"><fmt:message key="driverDashboard.tripInfoPage.car"/>*:</label>
                                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                    <div class="input-group-addon">
                                        <i class="fa fa-cogs fa-fw"></i>
                                    </div>

                                    <select class="form-control" id="car" name="car-id" required>
                                        <c:forEach items="${setOfSuitableCars}" var="car">
                                            <c:set var="count" value="${count + 1}" scope="page"/>
                                            <option value="${car.id}">${car.model}</option>
                                        </c:forEach>
                                    </select>

                                    <input type="hidden" value="${trip.id}" name="trip-id" />
                                </div>

                                <label for="message"><fmt:message key="driverDashboard.tripInfoPage.message"/>:</label>
                                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                    <div class="input-group-addon">
                                        <i class="fa fa-comment-o fa-fw"></i>
                                    </div>
                                    <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="message"
                                           name="message">
                                </div>

                                <br>
                                <button class="btn btn-primary"><fmt:message key="driverDashboard.tripInfoPage.button.makeRequest"/></button>
                            </form>
                        </div>
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

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>