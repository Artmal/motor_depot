<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My Requests</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<%@include file = "utils/driverHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "utils/driverSidebar.jsp" %>

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1>My Requests</h1>

            <c:if test="${not empty setOfTripRequests}">
                <c:forEach items="${setOfTripRequests}" var="tripRequest">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <div class="card">
                        <div class="card-header">
                            Request №${tripRequest.id}
                            <a href="/driver-dashboard/my-requests/discard?trip-request-id=${tripRequest.id}"
                               style="float: right"
                               class="btn btn-danger btn-sm btn-space">Discard</a>


                        </div>
                        <div class="card-block">
                            <i class="fa fa-road fa-fw"></i>
                            <strong>Trip:</strong> <a href="/driver-dashboard/trip?trip-id=${tripRequest.tripInfo.id}">Trip №${tripRequest.tripInfo.id}</a><br>

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