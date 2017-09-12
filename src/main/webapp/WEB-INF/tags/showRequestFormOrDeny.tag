<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ attribute name="trip_id" required="true" %>
<%@ attribute name="car_id" required="true" %>

<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                   url = "jdbc:mysql://localhost:3306/motor_depot"
                   user = "root"  password = "root"/>

<sql:query dataSource = "${snapshot}" var = "result">
    SELECT * from trip_requests WHERE trip_id = ${trip_id} AND ${car_id};
</sql:query>

<c:if test="${empty result}">
    <div class="card">
        <div class="card-block">
            <form class="form-horizontal" action="/driver-dashboard/trip" accept-charset="UTF-8" method="post">
                <label for="car"><fmt:message key="driverDashboard.tripInfoPage.car"/>*:</label>
                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">
                        <i class="fa fa-cogs fa-fw"></i>
                    </div>

                    <select class="form-control" id="car" name="car-id" required>
                        <c:forEach items="${setOfSuitableCars}" var="car">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <option value="${car.id}">${car.model}</option>
                        </c:forEach>
                    </select>

                    <input type="hidden" value="${trip.id}" name="trip-id" />
                </div>

                <label for="message"><fmt:message key="driverDashboard.tripInfoPage.message"/>:</label>
                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">
                        <i class="fa fa-comment-o fa-fw"></i>
                    </div>
                    <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="message"
                           name="message">
                </div>

                <br>
                <button class="btn btn-primary"><fmt:message key="driverDashboard.tripInfoPage.button.makeRequest"/></button>
            </form>
        </div>
    </div>
</c:if>

<c:if test="${not empty result}">
    <div class="card card-inverse" style="background-color: #333; border-color: #333;">
        <div class="card-block">
            <h3 class="card-title">You already made a request for the trip.</h3>
            <a href="/driver-dashboard/trips" class="btn btn-primary">Go to the trips.</a>
        </div>
    </div>
</c:if>