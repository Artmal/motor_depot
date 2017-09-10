<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Driver's Garage</title>

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

<div class="container">
    <div class="row profile">
        <div class="col-md-3">
            <div class="profile-sidebar">
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name">
                        ${driver.name}
                    </div>
                    <div class="profile-usertitle-job">
                        DRIVER
                    </div>
                </div>

                <div class="profile-usermenu">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="/drivers/profile?id=${driver.id}">Last Trips</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/drivers/profile/garage?driver-id=${driver.id}">Garage</a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>

        <div class="col-md-9">
            <div class="profile-content">
                <c:if test="${not empty setOfCars}">
                    <c:forEach items="${setOfCars}" var="car">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <div class="card">
                            <h3 class="card-header"> ${car.model}
                            </h3>
                            <div class="card-block">
                                <p class="card-text">
                                    <i class="fa fa-id-card-o fa-fw"></i>
                                    Registration number: ${car.registrationNumber}
                                    <br>
                                    <i class="fa fa-car fa-fw"></i>
                                    Type: ${car.type.displayName()}
                                    <br>
                                    <i class="fa fa-cogs fa-fw"></i>
                                    Condition:
                                    <c:if test="${car.condition eq 'Broken'}">
                                        <span class="badge badge-danger">Broken</span>
                                    </c:if>
                                    <c:if test="${car.condition eq 'Repairing'}">
                                        <span class="badge badge-warning">Repairing</span>
                                    </c:if>
                                    <c:if test="${car.condition eq 'Ready'}">
                                        <span class="badge badge-success">Ready</span>
                                    </c:if>
                                    <br>
                                    <i class="fa fa-users fa-fw"></i>
                                    Number of seats: ${car.numberOfSeats}
                                    <br>
                                    <i class="fa fa-tint fa-fw"></i>
                                    Car color: ${car.color}
                                </p>
                            </div>
                        </div>
                        <br>
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