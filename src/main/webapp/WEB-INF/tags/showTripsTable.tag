<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>

<%@ taglib uri="http://www.artmal.com/currency" prefix="tagLibrary" %>

<%@ attribute name="setOfTrips" required="true" type="java.util.Collection"%>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<c:if test="${not empty setOfTrips}">
    <table id="example" class="table table-bordered" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="tripsPage.content.table.tripId"/></th>
            <th><fmt:message key="tripsPage.content.table.dateOfCreation"/></th>
            <th><fmt:message key="tripsPage.content.table.status"/></th>
            <th><fmt:message key="tripsPage.content.table.carTypeRequired"/></th>
            <th><fmt:message key="tripsPage.content.table.carId"/></th>
            <th><fmt:message key="tripsPage.content.table.townFrom"/></th>
            <th><fmt:message key="tripsPage.content.table.townTo"/></th>
            <th><fmt:message key="tripsPage.content.table.timeOut"/></th>
            <th><fmt:message key="tripsPage.content.table.timeIn"/></th>
            <th><fmt:message key="tripsPage.content.table.payment"/></th>

            <c:if test="${sessionScope.role eq 'Admin'}">
                <th><fmt:message key="tripsPage.content.table.dispatcherId"/></th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${setOfTrips}" var="trip">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <c:choose>
                    <c:when test="${sessionScope.role eq 'Driver'}">
                        <td><a href="/driver-dashboard/trip?trip-id=${trip.id}">${trip.id}</a></td>
                    </c:when>

                    <c:when test = "${sessionScope.role eq 'Dispatcher'}">
                        <td><a href="/dispatcher-dashboard/trip?trip-id=${trip.id}">${trip.id}</a></td>
                    </c:when>

                    <c:when test = "${sessionScope.role eq 'Admin'}">
                        <td><a href="/admin-dashboard/trip?trip-id=${trip.id}">${trip.id}</a></td>
                    </c:when>
                </c:choose>

                <td>${trip.dateOfCreation}</td>
                <td>
                    <custom:printTripStatusFmt tripStatus="${trip.tripStatus}"/>
                </td>
                <td>
                    <custom:printTypeCarRequiredFmt carTypeRequired="${trip.carTypeRequired}"/>
                </td>
                <td>
                    <c:choose>
                    <c:when test="${trip.carId eq '0'}">
                    <span class="badge badge-pill badge-success"><fmt:message key="tripsPage.content.badge.freeSpot"/></span>
                    </c:when>
                    <c:otherwise>
                        ${trip.carId}
                    </c:otherwise>
                    </c:choose>
                <td>${trip.townFrom}</td>
                <td>${trip.townTo}</td>
                <td>${trip.timeOut.toString("yyyy-MM-dd HH:mm")}</td>
                <td>${trip.timeIn.toString("yyyy-MM-dd HH:mm")}</td>

                <td>
                    <tagLibrary:currencyTag locale="${language}" paymentInDollars="${trip.paymentInDollars}"/>
                </td>
                <c:if test="${sessionScope.role eq 'Admin'}">
                        <c:choose>
                            <c:when test="${trip.dispatcherId eq '0'}">
                                <td><fmt:message key="tripsPage.content.table.admin"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>${trip.dispatcherId}</td>
                            </c:otherwise>
                        </c:choose>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>