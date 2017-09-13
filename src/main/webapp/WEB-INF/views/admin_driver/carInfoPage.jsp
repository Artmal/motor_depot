<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_driver.carInfoPage" />

<fmt:message key="adminDriver.carInfoPage.button.save" var="saveButtonText" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="adminDriver.carInfoPage.pageTitle"/></title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-panel/driversPage.css" rel="stylesheet">

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

    <c:when test = "${sessionScope.role eq 'Driver'}">
        <%@include file = "../../../resources/jsp/driver_utils/driverHeader.jsp" %>

    </c:when>
</c:choose>

<fmt:setBundle basename="i18n.admin_driver.carInfoPage" />


<div class="container">
    <br>
    <br>
    <div class="container">
        <div class="row" style="margin-top:20px">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <form name="settingsChangeForm" action="/car/save" method="post">
                    <input hidden name="id" value="${param.id}">

                    <fieldset>
                        <h2><fmt:message key="adminDriver.carInfoPage.editCarNumber"/>${carInfo.id}</h2>
                        <label for="registration-number"><fmt:message key="adminDriver.carInfoPage.registrationNumber"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-id-card-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="registration-number"
                                   name="registration-number" value="${carInfo.registrationNumber}" required>
                        </div>

                        <label for="model"><fmt:message key="adminDriver.carInfoPage.model"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-industry fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="model"
                                   name="model" value="${carInfo.model}" required>
                        </div>

                        <label for="type"><fmt:message key="adminDriver.carInfoPage.type"/>:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-car fa-fw"></i>
                            </div>
                            <select class="form-control" id="type" name="type" required>adminDriver.carInfoPage
                                <optgroup label="Light">
                                    <option><fmt:message key="adminDriver.carInfoPage.option.micro"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.sedan"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.hatchback"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.roadster"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.coupe"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.supercar"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.cabriolet"/></option>
                                </optgroup>
                                <optgroup label="Medium">
                                    <option><fmt:message key="adminDriver.carInfoPage.option.minivan"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.van"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.cuv"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.suv"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.pickup"/></option>
                                </optgroup>
                                <optgroup label="Heavy">
                                    <option><fmt:message key="adminDriver.carInfoPage.option.campervan"/></option>
                                    <option value="Mini_truck"><fmt:message key="adminDriver.carInfoPage.option.miniTruck"/></option>
                                    <option><fmt:message key="adminDriver.carInfoPage.option.truck"/></option>
                                    <option value="Big_truck"><fmt:message key="adminDriver.carInfoPage.option.bigTruck"/></option>
                                </optgroup>
                            </select>
                        </div>


                        <label for="condition"><fmt:message key="adminDriver.carInfoPage.condition"/>: </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-cogs fa-fw"></i>
                            </div>
                            <select class="form-control" id="condition" name="condition" required>
                                <option value="Broken"><fmt:message key="adminDriver.carInfoPage.broken"/></option>
                                <option value="Repairing"><fmt:message key="adminDriver.carInfoPage.repairing"/></option>
                                <option value="Ready"><fmt:message key="adminDriver.carInfoPage.ready"/></option>
                            </select>
                        </div>

                        <label for="number-of-seats"><fmt:message key="adminDriver.carInfoPage.numberOfSeats"/>: </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-users fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="number-of-seats"
                                   name="number-of-seats" required value="${carInfo.numberOfSeats}">
                        </div>

                        <label for="color"><fmt:message key="adminDriver.carInfoPage.color"/>: </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-tint fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="color"
                                   name="color" required value="${carInfo.color}">
                        </div>

                        <br>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="${saveButtonText}">
                            </div>

                            <c:if test="${sessionScope.role eq 'Admin'}">
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <a href="/car/delete?car-id=${carInfo.id}" class="btn btn-lg btn-danger btn-block">
                                        <fmt:message key="adminDriver.carInfoPage.button.delete"/>
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>