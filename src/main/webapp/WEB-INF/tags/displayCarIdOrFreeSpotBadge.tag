<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="carId" required="true" type="java.lang.Integer" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<c:choose>
    <c:when test="${trip.carId eq '0'}">
        <span class="badge badge-pill badge-success"><fmt:message key="driverDashboard.myTripsPage.badge.freeSpot"/></span>
    </c:when>
    <c:otherwise>
        ${trip.carId}
    </c:otherwise>
</c:choose>