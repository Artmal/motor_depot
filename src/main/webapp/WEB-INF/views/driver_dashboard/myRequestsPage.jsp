<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.driver_dashboard.myRequestsPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="driverDashboard.myRequestsPage.pageTitle"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
</head>

<body>
<%@include file = "../../../resources/jsp/driver_utils/driverHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/driver_utils/driverSidebar.jsp" %>
        <fmt:setBundle basename="i18n.driver_dashboard.myRequestsPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="driverDashboard.myRequestsPage.header"/></h1>

            <c:if test="${not empty setOfTripRequests}">
                <c:forEach items="${setOfTripRequests}" var="tripRequest">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <div class="card">
                        <div class="card-header">
                            <fmt:message key="driverDashboard.myRequestsPage.requestNumber"/>${tripRequest.id}
                            <c:choose>
                                <c:when test="${tripRequest.tripInfo.tripStatus.displayName() eq 'Open'
                                                or tripRequest.tripInfo.tripStatus.displayName() eq 'In progress'}">
                                    <a href="/driver-dashboard/my-requests/discard?trip-request-id=${tripRequest.id}"
                                       style="float: right"
                                       class="btn btn-danger btn-sm btn-space"><fmt:message key="driverDashboard.myRequestPage.discard"/></a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/driver-dashboard/my-requests/discard?trip-request-id=${tripRequest.id}"
                                       style="float: right"
                                       class="btn btn-danger btn-sm btn-space disabled"><fmt:message key="driverDashboard.myRequestPage.discard"/></a>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="card-block">
                            <i class="fa fa-road fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myRequestsPage.label.trip"/>:</strong> <a href="/driver-dashboard/trip?trip-id=${tripRequest.tripInfo.id}">
                            <fmt:message key="driverDashboard.myRequestsPage.label.trip"/> №${tripRequest.tripInfo.id}</a><br>

                            <i class="fa fa-tasks fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myRequestsPage.label.status"/>:</strong>
                            <custom:printTripStatusFmt tripStatus="${tripRequest.tripInfo.tripStatus}"/><br>

                            <i class="fa fa-car fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myRequestsPage.label.carModel"/>: </strong> ${tripRequest.carInfo.model}<br>

                            <i class="fa fa-users fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myRequestsPage.label.numberOfSeats"/>: </strong> ${tripRequest.carInfo.numberOfSeats}<br>

                            <i class="fa fa-tint fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myRequestsPage.label.color"/>: </strong> ${tripRequest.carInfo.color}<br>

                            <i class="fa fa-clock-o fa-fw"></i>
                            <strong><fmt:message key="driverDashboard.myRequestsPage.label.color"/>:</strong> ${tripRequest.dateOfCreation.toString("yyyy-MM-dd HH:mm")}<br>
                        </div>
                    </div>
                    <br>
                </c:forEach>
            </c:if>
        </main>
    </div>
</div>

<script>
    document.getElementById("my-requests-nav-link").classList.add("active");
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