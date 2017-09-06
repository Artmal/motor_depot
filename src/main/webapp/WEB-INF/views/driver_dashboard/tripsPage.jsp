<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Trips</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

    <!-- Data tables -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<%@include file = "utils/driverHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "utils/driverSidebar.jsp" %>

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1>Trips</h1>

            <c:if test="${not empty setOfTrips}">
            <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Trip ID</th>
                    <th>Date of creation</th>
                    <th>Status</th>
                    <th>Car Type Required</th>
                    <th>Car ID</th>
                    <th>Town from</th>
                    <th>Town to</th>
                    <th>Time in</th>
                    <th>Time out</th>
                    <th>Payment</th>
                    <th>Dispatcher ID</th>
                </tr>
                </thead>
                <%--<tfoot>--%>
                <%--<tr>--%>
                    <%--<th>Trip ID</th>--%>
                    <%--<th>Date of creation</th>--%>
                    <%--<th>Status</th>--%>
                    <%--<th>Car Type Required</th>--%>
                    <%--<th>Car ID</th>--%>
                    <%--<th>Town from</th>--%>
                    <%--<th>Town to</th>--%>
                    <%--<th>Time in</th>--%>
                    <%--<th>Time out</th>--%>
                    <%--<th>Payment</th>--%>
                    <%--<th>Dispatcher ID</th>--%>
                <%--</tr>--%>
                <%--</tfoot>--%>
                <tbody>
                <c:forEach items="${setOfTrips}" var="trip">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td>${trip.id}</td>
                        <td>${trip.dateOfCreation}</td>
                        <td>
                            <c:if test="${trip.tripStatus eq 'Open'}">
                                <span class="badge badge-success">Open</span>
                            </c:if>
                            <c:if test="${trip.tripStatus eq 'In_progress'}">
                                <span class="badge badge-warning">In Progress</span>
                            </c:if>
                            <c:if test="${trip.tripStatus eq 'Closed'}">
                                <span class="badge badge-default">Closed</span>
                            </c:if>
                            <c:if test="${trip.tripStatus eq 'Canceled'}">
                                <span class="badge badge-danger">Canceled</span>
                            </c:if>
                        </td>
                        <td>${trip.carTypeRequired}</td>
                        <td>
                            <c:choose>
                            <c:when test="${trip.carId eq '0'}">
                            <span class="badge badge-pill badge-success">Free spot</span>
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
                        <td>${trip.dispatcherId}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </c:if>

            <%--<c:if test="${not empty setOfTrips}">--%>
                <%--<div class="table-responsive">--%>


                    <%--<table class="table table-striped">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th>Trip ID</th>--%>
                            <%--<th>Date of creation</th>--%>
                            <%--<th>Status</th>--%>
                            <%--<th>Car Type Required</th>--%>
                            <%--<th>Car ID</th>--%>
                            <%--<th>Town from</th>--%>
                            <%--<th>Town to</th>--%>
                            <%--<th>Time in</th>--%>
                            <%--<th>Time out</th>--%>
                            <%--<th>Payment</th>--%>
                            <%--<th>Dispatcher ID</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<c:forEach items="${setOfTrips}" var="trip">--%>
                            <%--<c:set var="count" value="${count + 1}" scope="page"/>--%>
                            <%--<tr>--%>
                                <%--<td>${trip.id}</td>--%>
                                <%--<td>${trip.dateOfCreation}</td>--%>
                                <%--<td>--%>
                                    <%--<c:if test="${trip.tripStatus eq 'Open'}">--%>
                                        <%--<span class="badge badge-success">Open</span>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${trip.tripStatus eq 'In_progress'}">--%>
                                        <%--<span class="badge badge-warning">In Progress</span>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${trip.tripStatus eq 'Closed'}">--%>
                                        <%--<span class="badge badge-default">Closed</span>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${trip.tripStatus eq 'Canceled'}">--%>
                                        <%--<span class="badge badge-danger">Canceled</span>--%>
                                    <%--</c:if>--%>
                                <%--</td>--%>
                                <%--<td>${trip.carTypeRequired}</td>--%>
                                <%--<td>--%>
                                    <%--<c:choose>--%>
                                    <%--<c:when test="${trip.carId eq '0'}">--%>
                                    <%--<span class="badge badge-pill badge-success">Free spot</span>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <%--${trip.carId}--%>
                                    <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                                <%--<td>${trip.townFrom}</td>--%>
                                <%--<td>${trip.townTo}</td>--%>
                                <%--<td>${trip.timeOut.toString("yyyy-MM-dd HH:mm")}</td>--%>
                                <%--<td>${trip.timeIn.toString("yyyy-MM-dd HH:mm")}</td>--%>

                                <%--<td>${trip.paymentInDollars}</td>--%>
                                <%--<td>${trip.dispatcherId}</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
            <%--</c:if>--%>
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
<%--<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>--%>
<%--<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/3.2.1/jquery.min.js"><\/script>')</script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
