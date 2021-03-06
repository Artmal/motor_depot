<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>

<fmt:setLocale value="${language}" />

<link href="${contextPath}/resources/css/validation.css" rel="stylesheet">

<fmt:setBundle basename="i18n.admin_dashboard.carsPage" />
<div class="card">
    <div class="card-block">
        <c:if test="${sessionScope.role eq 'Admin'}">
            <c:set var="actionUrl" value="/admin-dashboard/cars"/>
        </c:if>
        <c:if test="${sessionScope.role eq 'Driver'}">
            <c:set var="actionUrl" value="/driver-dashboard/garage"/>
        </c:if>

        <form id="add-car-form" action="${actionUrl}" method="post">
            <label for="registration-number"><fmt:message key="adminDashboard.carsPage.registrationNumber"/>*:</label>
            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon">
                    <i class="fa fa-id-card-o fa-fw"></i>
                </div>
                <input id="registration-number" name="registration-number" class="form-control">
            </div>

            <label for="type"><fmt:message key="adminDashboard.carsPage.type"/>*:</label>
            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon">
                    <i class="fa fa-car fa-fw"></i>
                </div>

                <fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />
                <select id="type" name="type" class="form-control">
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

            <fmt:setBundle basename="i18n.admin_dashboard.carsPage" />
            <label for="condition"><fmt:message key="adminDashboard.carsPage.condition"/>*:</label>
            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon">
                    <i class="fa fa-cogs fa-fw"></i>
                </div>

                <select id="condition" name="condition" class="form-control">
                    <option value="Broken"><fmt:message key="adminDashboard.carsPage.broken"/></option>
                    <option value="Repairing"><fmt:message key="adminDashboard.carsPage.repairing"/></option>
                    <option value="Ready"><fmt:message key="adminDashboard.carsPage.ready"/></option>
                </select>
            </div>

            <label for="model"><fmt:message key="adminDashboard.carsPage.model"/>*:</label>
            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon">
                    <i class="fa fa-industry fa-fw"></i>
                </div>
                <input id="model" name="model" class="form-control"
                        placeholder="Kia Optima">
            </div>

            <label for="number-of-seats"><fmt:message key="adminDashboard.carsPage.numberOfSeats"/>*:</label>
            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon">
                    <i class="fa fa-users fa-fw"></i>
                </div>
                <input id="number-of-seats" name="number-of-seats" class="form-control">
            </div>

            <label for="color"><fmt:message key="adminDashboard.carsPage.color"/>*:</label>
            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon">
                    <i class="fa fa-tint fa-fw"></i>
                </div>
                <input id="color" name="color" class="form-control">
            </div>

            <c:if test="${sessionScope.role eq 'Admin'}">
                <label for="owner-id"><fmt:message key="adminDashboard.carsPage.driverId"/>*:</label>
                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">
                        <i class="fa fa-user-circle-o fa-fw"></i>
                    </div>
                    <input id="owner-id" name="owner-id" class="form-control">
                </div>
            </c:if>
            <br>

            <button class="btn btn-primary"><fmt:message key="adminDashboard.carsPage.addCarForm"/></button>
        </form>
    </div>
</div>

<c:if test="${sessionScope.role eq 'Driver'}">
    <script src="${contextPath}/webjars/jquery/2.1.3/jquery.min.js"></script>
    <script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
    <script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
    <script src="${contextPath}/resources/js/validation/driver_dashboard/garagePage.min.js"></script>
</c:if>

<c:if test="${sessionScope.role eq 'Admin'}">
    <script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
    <script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
    <script src="${contextPath}/resources/js/validation/admin_dashboard/carsPage.min.js"></script>
</c:if>



