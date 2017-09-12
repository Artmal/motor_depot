<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<fmt:message key="login.placeholder.email" var="emailPlaceholderText" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="tripsPage.title"/></title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

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
                        
                        <c:if test="${sessionScope.role eq 'Admin'}">
                            <th><fmt:message key="tripsPage.content.table.dispatcherId"/></th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${setOfTrips}" var="trip">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <c:choose>
                                <c:when test = "${sessionScope.role eq 'Admin'}">
                                    <td><a href="/admin-dashboard/trip?trip-id=${trip.id}">${trip.id}</a></td>
                                </c:when>

                                <c:when test = "${sessionScope.role eq 'Dispatcher'}">
                                    <td><a href="/dispatcher-dashboard/trip?trip-id=${trip.id}">${trip.id}</a></td>
                                </c:when>
                            </c:choose>


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

                            <c:if test="${sessionScope.role eq 'Admin'}">
                                <td>${trip.dispatcherId}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <br>

            <div class="card">
                <div class="card-block">
                        <c:choose>
                            <c:when test = "${sessionScope.role eq 'Admin'}">
                                <form class="form-horizontal" action="/admin-dashboard/trips" method="post">
                            </c:when>

                            <c:when test = "${sessionScope.role eq 'Dispatcher'}">
                                <form class="form-horizontal" action="/dispatcher-dashboard/trips" method="post">
                            </c:when>
                        </c:choose>

                        <label for="status"><fmt:message key="tripsPage.content.label.status"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-tasks fa-fw"></i>
                            </div>
                            <select class="form-control" id="status" name="status" required>
                                <option><fmt:message key="tripsPage.content.option.open"/></option>
                                <option value="In_progress"><fmt:message key="tripsPage.content.option.inProgress"/></option>
                                <option><fmt:message key="tripsPage.content.option.closed" /></option>
                                <option><fmt:message key="tripsPage.content.option.canceled"/></option>
                            </select>
                        </div>

                        <label for="car-type-required"><fmt:message key="tripsPage.content.addTripForm.label.carTypeRequired"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-car fa-fw"></i>

                            </div>
                            <select class="form-control" id="car-type-required" name="car-type-required" required>
                                <optgroup label="Light">
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.micro"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.sedan"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.hatchback"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.roadster"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.coupe"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.supercar"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.cabriolet"/></option>
                                </optgroup>
                                <optgroup label="Medium">
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.minivan"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.van"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.cuv"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.suv"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.pickup"/></option>
                                </optgroup>
                                <optgroup label="Heavy">
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.campervan"/></option>
                                    <option value="Mini_truck"><fmt:message key="tripsPage.content.addTripForm.option.miniTruck"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.truck"/></option>
                                    <option value="Big_truck"><fmt:message key="tripsPage.content.addTripForm.option.bigTruck"/></option>
                                </optgroup>
                            </select>
                        </div>

                        <label for="town-from"><fmt:message key="tripsPage.content.addTripForm.label.townFrom"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-building-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="town-from" name="town-from" required>
                        </div>

                        <label for="town-to"><fmt:message key="tripsPage.content.addTripForm.label.townTo"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-building fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="town-to"
                                   name="town-to" required>
                        </div>


                        <label for="time-out"><fmt:message key="tripsPage.content.addTripForm.label.timeOut"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="time-out"
                                   name="time-out" placeholder="YYYY-MM-DD HH:MM:SS" required>
                        </div>


                        <label for="time-in"><fmt:message key="tripsPage.content.addTripForm.label.timeIn"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar-check-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="time-in"
                                   name="time-in" placeholder="YYYY-MM-DD HH:MM:SS">
                        </div>

                        <label for="payment-in-dollars"><fmt:message key="tripsPage.content.addTripForm.label.payment"/>($)*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-usd fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="payment-in-dollars"
                                   name="payment-in-dollars">
                        </div>
                        <br>
                        <button class="btn btn-primary"><fmt:message key="tripsPage.content.addTripForm.button.addTrip"/></button>
                    </form>
                </div>
            </div>
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