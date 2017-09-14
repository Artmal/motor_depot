<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.requestsPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="adminDispatcher.requestsPage.pageTitle"/></title>

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
        <fmt:setBundle basename="i18n.admin_dispatcher.requestsPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="adminDispatcher.requestsPage.header"/></h1>

            <c:if test="${not empty setOfTripRequests}">
                <table id="example" class="table table-bordered" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th><fmt:message key="adminDispatcher.requestsPage.tripRequestId"/></th>
                        <th><fmt:message key="adminDispatcher.requestsPage.tripRequestId"/></th>
                        <th><fmt:message key="adminDispatcher.requestsPage.carId"/></th>
                        <th><fmt:message key="adminDispatcher.requestsPage.dateOfCreation"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${setOfTripRequests}" var="tripRequest">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <td>${tripRequest.id}</td>
                            <c:choose>
                                <c:when test = "${sessionScope.role eq 'Admin'}">
                                    <td>
                                        <a href="/admin-dashboard/trip?trip-id=${tripRequest.tripInfo.id}">
                                            ${tripRequest.tripInfo.id}
                                        </a>
                                    </td>
                                </c:when>

                                <c:when test = "${sessionScope.role eq 'Dispatcher'}">
                                    <td>
                                        <a href="/dispatcher-dashboard/trip?trip-id=${tripRequest.tripInfo.id}">
                                            ${tripRequest.tripInfo.id}
                                        </a>
                                    </td>
                                </c:when>
                            </c:choose>

                            <td>${tripRequest.carInfo.id}</td>
                            <td>${tripRequest.tripInfo.dateOfCreation}</td>
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
    document.getElementById("requests-nav-link").classList.add("active");
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