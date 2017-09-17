<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="tripsPage.content.addTripForm.button.editTrip"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page-->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-panel/driversPage.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/validation.css" rel="stylesheet">
</head>

<body>
<c:choose>
    <c:when test = "${sessionScope.role eq 'Admin'}">
        <%@include file = "../../../resources/jsp/admin_utils/adminHeader.jsp" %>
        <c:set var="editHref" value = "/admin-dashboard/edit-trip?id=${param.id}"/>
    </c:when>

    <c:when test = "${sessionScope.role eq 'Dispatcher'}">
        <%@include file = "../../../resources/jsp/dispatcher_utils/dispatcherHeader.jsp" %>
        <c:set var="editHref" value = "/dispatcher-dashboard/edit-trip?id=${param.id}"/>
    </c:when>
</c:choose>

<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<div class="container">
    <br>
    <br>
    <div class="container">
        <div class="row" style="margin-top:20px">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <form id = "edit-trip-form" action="${editHref}" method="post">
                    <input hidden name="id" value="${param.id}">

                    <label for="status"><fmt:message key="tripsPage.content.label.status"/>*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-tasks fa-fw"></i>
                        </div>
                        <select id="status" name="status" class="form-control" >
                            <option value="Open"><fmt:message key="tripsPage.content.option.open"/></option>
                            <option value="In_progress"><fmt:message key="tripsPage.content.option.inProgress"/></option>
                            <option value="Closed"><fmt:message key="tripsPage.content.option.closed" /></option>
                            <option value="Canceled"><fmt:message key="tripsPage.content.option.canceled"/></option>
                        </select>
                    </div>

                    <label for="car-type-required"><fmt:message key="tripsPage.content.addTripForm.label.carTypeRequired"/>*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-car fa-fw"></i>

                        </div>
                        <select class="form-control" id="car-type-required" name="car-type-required">
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
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="town-from" name="town-from"
                               placeholder="Kharkov" value="${trip.townFrom}">
                    </div>

                    <label for="town-to"><fmt:message key="tripsPage.content.addTripForm.label.townTo"/>*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-building fa-fw"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="town-to" name="town-to"
                               placeholder="Kiev" value="${trip.townTo}">
                    </div>


                    <label for="time-out"><fmt:message key="tripsPage.content.addTripForm.label.timeOut"/>*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar fa-fw"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="time-out"
                               name="time-out" placeholder="YYYY-MM-DD HH:MM:SS">
                    </div>


                    <label for="time-in"><fmt:message key="tripsPage.content.addTripForm.label.timeIn"/>*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar-check-o fa-fw"></i>
                        </div>
                        <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="time-in" name="time-in"
                               placeholder="YYYY-MM-DD HH:MM:SS">
                    </div>

                    <label for="payment-in-dollars"><fmt:message key="tripsPage.content.addTripForm.label.payment"/>($)*:</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon">
                            <i class="fa fa-usd fa-fw"></i>
                        </div>
                        <input id="payment-in-dollars" name="payment-in-dollars" class="form-control mb-2 mr-sm-2 mb-sm-0"
                               value="${trip.paymentInDollars}">
                    </div>
                    <br>
                    <button id="submit-button" class="btn btn-primary"><fmt:message key="tripsPage.content.addTripForm.button.editTrip"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/webjars/jquery/2.1.3/jquery.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
<script src="${contextPath}/resources/js/validation/admin_dispatcher/tripEditPage.js"></script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>