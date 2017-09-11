<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.driver_dashboard.myTripsPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="driverDashboard.myTripsPage.pageTitle"/></title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <style>
        .btn-space {
            margin-right: 5px;
        }
    </style>
</head>

<body>
<%@include file = "../../../resources/jsp/driver_utils/driverHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/driver_utils/driverSidebar.jsp" %>
        <fmt:setBundle basename="i18n.driver_dashboard.myTripsPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="driverDashboard.myTripsPage.header"/></h1>

            <c:if test="${not empty setOfTrips}">
                <c:forEach items="${setOfTrips}" var="trip">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <div class="card">
                        <div class="card-header">
                            <fmt:message key="driverDashboard.myTripsPage.tripNumber"/>${trip.id}
                            <a id = "completeButton${count}" href="/driver-dashboard/my-trips/refuse?trip-id=${trip.id}"
                               style="float: right"
                               class="btn btn-danger btn-sm btn-space"><fmt:message key="driverDashboard.myTripsPage.refuse"/></a>
                            <a id = "refuseButton${count}" href="/driver-dashboard/my-trips/complete?trip-id=${trip.id}"
                               style="float: right"
                               class="btn btn-success btn-sm btn-space"><fmt:message key="driverDashboard.myTripsPage.markAsCompleted"/></a>

                            <script>
                                if(${trip.tripStatus.displayName() ne 'In progress'}) {
                                    var completeButton = document.getElementById("completeButton${count}");
                                    completeButton.classList.add("disabled");

                                    var refuseButton = document.getElementById("refuseButton${count}")
                                    refuseButton.classList.add("disabled");
                                }
                            </script>

                        </div>
                        <div class="card-block">
                            <h4 class="card-title">Info</h4>
                            <i class="fa fa-calendar-plus-o fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.dateOfCreation"/>:</strong> ${trip.dateOfCreation}
                            <br>
                            <i class="fa fa-tasks fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.status"/>:</strong>
                            <c:if test="${trip.tripStatus.displayName() eq 'Open'}">
                                <span class="badge badge-success">Open</span>
                            </c:if>
                            <c:if test="${trip.tripStatus.displayName() eq 'In progress'}">
                                <span class="badge badge-warning">In Progress</span>
                            </c:if>
                            <c:if test="${trip.tripStatus.displayName() eq 'Closed'}">
                                <span class="badge badge-default">Closed</span>
                            </c:if>
                            <c:if test="${trip.tripStatus.displayName() eq 'Canceled'}">
                                <span class="badge badge-danger">Canceled</span>
                            </c:if>
                            <br>
                            <i class="fa fa-car fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.carTypeRequired"/>:</strong> ${trip.carTypeRequired.displayName()}
                            <br>
                            <i class="fa fa-id-card-o fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.carId"/>:</strong>
                            <c:choose>
                                <c:when test="${trip.carId eq '0'}">
                                    <span class="badge badge-pill badge-success">Free spot</span>
                                </c:when>
                                <c:otherwise>
                                    ${trip.carId}
                                </c:otherwise>
                            </c:choose>
                            <br>
                            <i class="fa fa-building-o fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.townFrom"/>:</strong> ${trip.townFrom}
                            <br>
                            <i class="fa fa-building fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.townTo"/>:</strong> ${trip.townTo}
                            <br>
                            <i class="fa fa-calendar fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.timeOut"/>:</strong> ${trip.timeIn.toString("yyyy-MM-dd HH:mm")}
                            <br>
                            <i class="fa fa-calendar-check-o fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.timeIn"/>:</strong> ${trip.timeOut.toString("yyyy-MM-dd HH:mm")}
                            <br>
                            <i class="fa fa-usd fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.payment"/>:</strong> ${trip.paymentInDollars}
                            <br>
                            <i class="fa fa-user-plus fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myTripsPage.dispatcherId"/>:</strong> ${trip.dispatcherId}
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </main>
    </div>
</div>

<script>
    document.getElementById("my-trips-nav-link").classList.add("active");
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/3.2.1/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>