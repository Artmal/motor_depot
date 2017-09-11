<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.driver_dashboard.garagePage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="driverDashboard.garagePage.pageTitle"/></title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">

    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<%@include file = "../../../resources/jsp/driver_utils/driverHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/driver_utils/driverSidebar.jsp" %>
        <fmt:setBundle basename="i18n.driver_dashboard.garagePage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="driverDashboard.garagePage.header"/></h1>

            <c:if test="${not empty setOfCars}">
                <c:forEach items="${setOfCars}" var="car">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <div class="card">
                        <h3 class="card-header"> ${car.model}
                            <a href="/driver-dashboard/garage/delete?car-id=${car.id}">
                                <i style="font-size: 20px; color: crimson; margin: auto; float: right" class="btn btn-default fa fa-times fa-fw"></i>
                            </a>
                            <a href="${contextPath}/car?id=${car.id}">
                                <i style="font-size: 20px; color: darkorange; float: right;" class="btn btn-default fa fa-pencil fa-fw"></i>
                            </a>
                        </h3>
                        <div class="card-block">
                            <p class="card-text">
                                <i class="fa fa-id-card-o fa-fw"></i>
                                <fmt:message key="driverDashboard.garagePage.registrationNumber"/>: ${car.registrationNumber}
                                <br>
                                <i class="fa fa-car fa-fw"></i>
                                <fmt:message key="driverDashboard.garagePage.type"/>: ${car.type.displayName()}
                                <br>
                                <i class="fa fa-cogs fa-fw"></i>
                                <fmt:message key="driverDashboard.garagePage.condition"/>:
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
                                <fmt:message key="driverDashboard.garagePage.numberOfSeats"/>: ${car.numberOfSeats}
                                <br>
                                <i class="fa fa-tint fa-fw"></i>
                                <fmt:message key="driverDashboard.garagePage.button.addCar"/>: ${car.color}
                            </p>
                        </div>
                    </div>
                    <br>
                </c:forEach>
            </c:if>

            <br>

            <div class="card">
                <div class="card-block">
                    <form class="form-horizontal" action="/driver-dashboard/garage" accept-charset="UTF-8" method="post">
                        <label for="registration-number"><fmt:message key="driverDashboard.garagePage.registrationNumber"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-id-card-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="registration-number"
                                   name="registration-number" required>
                        </div>

                        <label for="type"><fmt:message key="driverDashboard.garagePage.type"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-car fa-fw"></i>
                            </div>

                            <fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />
                            <select class="form-control" id="type" name="type" required>
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

                        <fmt:setBundle basename="i18n.driver_dashboard.garagePage" />
                        <label for="condition"><fmt:message key="driverDashboard.garagePage.condition"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-cogs fa-fw"></i>
                            </div>

                            <select class="form-control" id="condition" name="condition" required>
                                <option><fmt:message key="driverDashboard.garagePage.broken"/></option>
                                <option><fmt:message key="driverDashboard.garagePage.repairing"/></option>
                                <option><fmt:message key="driverDashboard.garagePage.ready"/></option>
                            </select>
                        </div>

                        <label for="model">Model*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-industry fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="model"
                                   name="model" placeholder="Kia Optima" required>
                        </div>

                        <label for="number-of-seats"><fmt:message key="driverDashboard.garagePage.numberOfSeats"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-users fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="number-of-seats"
                                   name="number-of-seats" required>
                        </div>

                        <label for="color"><fmt:message key="driverDashboard.garagePage.color"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-tint fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="color" name="color"
                                   required>
                        </div>
                        <br>
                        <button class="btn btn-primary"><fmt:message key="driverDashboard.garagePage.button.addCar"/></button>
                    </form>
                </div>
            </div>
            <br>
        </main>
    </div>
</div>

<script>
    document.getElementById("garage-nav-link").classList.add("active");
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="${contextPath}/webjars/jquery/1.12.4/jquery.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>