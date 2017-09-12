<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.driver_profile.driverProfilePage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="adminDispatcher.driverProfile.pageTitle"/></title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/profile.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<c:choose>
    <c:when test = "${sessionScope.role eq 'Admin'}">
        <%@include file = "../../../../resources/jsp/admin_utils/adminHeader.jsp" %>
    </c:when>

    <c:when test = "${sessionScope.role eq 'Dispatcher'}">
        <%@include file = "../../../../resources/jsp/dispatcher_utils/dispatcherHeader.jsp" %>
    </c:when>
</c:choose>
<fmt:setBundle basename="i18n.admin_dispatcher.driver_profile.driverProfilePage" />

<div class="container">
    <div class="row profile">
        <div class="col-md-3">
            <div class="profile-sidebar">
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name">
                        ${driver.name}
                    </div>
                    <div class="profile-usertitle-job">
                        <fmt:message key="adminDispatcher.driverProfile.driver"></fmt:message>
                    </div>
                </div>

                <div class="profile-usermenu">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="/drivers/profile?id=${driver.id}"><fmt:message key="adminDispatcher.driverProfile.lastTrips"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/drivers/profile/garage?driver-id=${driver.id}"><fmt:message key="adminDispatcher.driverProfile.garage"/></a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>

        <div class="col-md-9">
            <div class="profile-content">
                <c:if test="${not empty setOfTrips}">
                    <c:forEach items="${setOfTrips}" var="trip">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <div class="card">
                            <div class="card-header">
                                <fmt:message key="adminDispatcher.driverProfile.header"/>${trip.id}
                            </div>
                            <div class="card-block">
                                <h4 class="card-title">Info</h4>
                                <i class="fa fa-calendar-plus-o fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.dateOfCreation"/>:</strong> ${trip.dateOfCreation}
                                <br>
                                <i class="fa fa-tasks fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.status"/>:</strong>
                                <c:if test="${trip.tripStatus.displayName() eq 'Open'}">
                                    <span class="badge badge-success"><fmt:message key="adminDispatcher.driverProfile.badge.open"/></span>
                                </c:if>
                                <c:if test="${trip.tripStatus.displayName() eq 'In progress'}">
                                    <span class="badge badge-warning"><fmt:message key="adminDispatcher.driverProfile.badge.inProgress"/></span>
                                </c:if>
                                <c:if test="${trip.tripStatus.displayName() eq 'Closed'}">
                                    <span class="badge badge-default"><fmt:message key="adminDispatcher.driverProfile.badge.closed"/></span>
                                </c:if>
                                <c:if test="${trip.tripStatus.displayName() eq 'Canceled'}">
                                    <span class="badge badge-danger"><fmt:message key="adminDispatcher.driverProfile.badge.canceled"/></span>
                                </c:if>
                                <br>
                                <i class="fa fa-car fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.carTypeRequired"/>:</strong> ${trip.carTypeRequired.displayName()}
                                <br>
                                <i class="fa fa-id-card-o fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.carId"/>:</strong>
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
                                <strong><fmt:message key="adminDispatcher.driverProfile.townFrom"/>:</strong> ${trip.townFrom}
                                <br>
                                <i class="fa fa-building fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.townTo"/>:</strong> ${trip.townTo}
                                <br>
                                <i class="fa fa-calendar fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.timeOut"/>:</strong> ${trip.timeIn.toString("yyyy-MM-dd HH:mm")}
                                <br>
                                <i class="fa fa-calendar-check-o fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.timeIn"/>:</strong> ${trip.timeOut.toString("yyyy-MM-dd HH:mm")}
                                <br>
                                <i class="fa fa-usd fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.payment"/>:</strong> ${trip.paymentInDollars}
                                <br>
                                <i class="fa fa-user-plus fa-fw"></i>
                                <strong><fmt:message key="adminDispatcher.driverProfile.dispatcherId"/>:</strong> ${trip.dispatcherId}
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/3.2.1/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>