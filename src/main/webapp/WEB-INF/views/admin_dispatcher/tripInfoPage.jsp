<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Trip Info</title>

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

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <div id = "trip-info" class="card card-outline-info">
                <div class="card-block">
                    <h4 class="card-title">Trip №${trip.id} Info</h4>
                    <i class="fa fa-calendar-plus-o fa-fw"></i>
                    <strong>Date of creation:</strong> ${trip.dateOfCreation}
                    <br>
                    <i class="fa fa-tasks fa-fw"></i>
                    <strong>Status:</strong>
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
                    <strong>Car Type Required:</strong> ${trip.carTypeRequired.displayName()}
                    <br>
                    <i class="fa fa-id-card-o fa-fw"></i>
                    <strong>Car ID:</strong>
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
                    <strong>Town from:</strong> ${trip.townFrom}
                    <br>
                    <i class="fa fa-building fa-fw"></i>
                    <strong>Town to:</strong> ${trip.townTo}
                    <br>
                    <i class="fa fa-calendar fa-fw"></i>
                    <strong>Time out:</strong> ${trip.timeIn.toString("yyyy-MM-dd HH:mm")}
                    <br>
                    <i class="fa fa-calendar-check-o fa-fw"></i>
                    <strong>Time in:</strong> ${trip.timeOut.toString("yyyy-MM-dd HH:mm")}
                    <br>
                    <i class="fa fa-usd fa-fw"></i>
                    <strong>Payment:</strong> ${trip.paymentInDollars}
                    <br>
                    <i class="fa fa-user-plus fa-fw"></i>
                    <strong>Dispatcher ID:</strong> ${trip.dispatcherId}
                </div>
            </div>

            <hr>
            <h3>Requests</h3>

            <c:if test="${not empty setOfTripRequests}">
                <c:forEach items="${setOfTripRequests}" var="tripRequest">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <div class="card">
                        <div class="card-header">
                            <c:if test="${trip.carId eq tripRequest.carInfo.id}">
                                <i class="fa fa-check-square-o fa-fw"></i>
                            </c:if>
                            Request №${tripRequest.id}
                            <c:choose>
                                <c:when test="${sessionScope.role eq 'Admin'}">
                                    <a href="/admin-dashboard/trip/deny?trip-request-id=${tripRequest.id}&trip-id=${trip.id}"
                                       style="float: right"
                                       class="btn btn-danger btn-sm btn-space">Deny</a>
                                    <a href="/admin-dashboard/trip/accept?trip-request-id=${tripRequest.id}&trip-id=${trip.id}"
                                       style="float: right"
                                       class="btn btn-success btn-sm btn-space">Accept</a>
                                </c:when>
                                <c:when test="${sessionScope.role eq 'Dispatcher'}">
                                    <a href="/dispatcher-dashboard/trip/deny?trip-request-id=${tripRequest.id}&trip-id=${trip.id}"
                                       style="float: right"
                                       class="btn btn-danger btn-sm btn-space">Deny</a>
                                    <a href="/dispatcher-dashboard/trip/accept?trip-request-id=${tripRequest.id}&trip-id=${trip.id}"
                                       style="float: right"
                                       class="btn btn-success btn-sm btn-space">Accept</a>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="card-block">
                            <i class="fa fa-user-circle-o fa-fw"></i>
                            <strong>Driver:</strong> <a href="/drivers/profile?id=${tripRequest.carInfo.ownerId}">Driver №${tripRequest.carInfo.ownerId}</a><br>

                            <i class="fa fa-car fa-fw"></i>
                            <strong>Car model: </strong> ${tripRequest.carInfo.model}<br>

                            <i class="fa fa-users fa-fw"></i>
                            <strong>Number of seats: </strong> ${tripRequest.carInfo.numberOfSeats}<br>

                            <i class="fa fa-tint fa-fw"></i>
                            <strong>Car color: </strong> ${tripRequest.carInfo.color}<br>

                            <i class="fa fa-clock-o fa-fw"></i>
                            <strong>Date of creation:</strong> ${tripRequest.dateOfCreation.toString("yyyy-MM-dd HH:mm")}<br>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>