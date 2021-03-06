<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.dispatcher_profile.dispatcherProfilePage" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="adminDispatcher.dispatcherProfile.pageTitle"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/profile.min.css" rel="stylesheet">

    <!-- Data tables -->
    <%@include file = "../../../../resources/dataTablesScriptsImport.jsp" %>
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
<fmt:setBundle basename="i18n.admin_dispatcher.dispatcher_profile.dispatcherProfilePage" />


<div class="container">
    <div class="row profile">
        <div class="col-md-3">
            <div class="profile-sidebar">
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name">
                        ${dispatcher.name}
                    </div>
                    <div class="profile-usertitle-job">
                        <fmt:message key="adminDispatcher.dispatcherProfile.dispatcher"/>
                    </div>
                </div>

                <div class="profile-usermenu">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                <fmt:message key="adminDispatcher.dispatcherProfile.createdTrips"/>
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="col-md-9">
            <div class="profile-content">
                <h2><fmt:message key="adminDispatcher.dispatcherProfile.createdTrips"/></h2><br>

                <c:choose>
                    <c:when test="${not empty setOfCreatedTrips}">
                        <table id="trips-table" class="table table-bordered" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th><fmt:message key="adminDispatcher.dispatcherProfile.tripId"/></th>
                                <th><fmt:message key="adminDispatcher.dispatcherProfile.dateOfCreation"/></th>
                                <th><fmt:message key="adminDispatcher.dispatcherProfile.status"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${setOfCreatedTrips}" var="trip">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test = "${sessionScope.role eq 'Admin'}">
                                                <a href="/admin-dashboard/trip?trip-id=${trip.id}">${trip.id}</a>
                                            </c:when>

                                            <c:when test = "${sessionScope.role eq 'Dispatcher'}">
                                                <a href="/dispatcher-dashboard/trip?trip-id=${trip.id}">${trip.id}</a>
                                            </c:when>
                                        </c:choose>
                                    </td>

                                    <td>${trip.dateOfCreation}</td>
                                    <td>
                                        <c:if test="${trip.tripStatus.displayName() eq 'Open'}">
                                            <span class="badge badge-success">
                                                <fmt:message key="adminDispatcher.dispatcherProfile.badge.open"/>
                                            </span>
                                        </c:if>
                                        <c:if test="${trip.tripStatus.displayName() eq 'In progress'}">
                                            <span class="badge badge-warning">
                                                <fmt:message key="adminDispatcher.dispatcherProfile.badge.open"/>
                                            </span>
                                        </c:if>
                                        <c:if test="${trip.tripStatus.displayName() eq 'Closed'}">
                                            <span class="badge badge-default">
                                                <fmt:message key="adminDispatcher.dispatcherProfile.badge.closed"/>
                                            </span>
                                        </c:if>
                                        <c:if test="${trip.tripStatus.displayName() eq 'Canceled'}">
                                            <span class="badge badge-danger">
                                                <fmt:message key="adminDispatcher.dispatcherProfile.badge.closed"/>
                                            </span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>

                    <c:otherwise>
                        <div class="alert alert-info" role="alert">
                            <fmt:message key="adminDispatcher.dispatcherProfile.noCreatedTripsYet"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#trips-table').DataTable({
            <c:if test="${language.getLanguage() eq 'ru'}">
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Russian.json"
            }
            </c:if>
        });
    } );
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<link href="${contextPath}/webjars/tether/1.4.0/dist/js/tether.min.js" rel="script">
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>