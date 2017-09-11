<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.utils.driver_utils.driverSidebar"/>

<nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id = "trips-nav-link" class="nav-link" href="/driver-dashboard/trips"><fmt:message key="driverSidebar.trips"/></a>
        </li>
    </ul>

    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id="my-trips-nav-link" class="nav-link" href="/driver-dashboard/my-trips"><fmt:message key="driverSidebar.myTrips"/></a>
        </li>
        <li class="nav-item">
            <a id="my-requests-nav-link" class="nav-link" href="/driver-dashboard/my-requests"><fmt:message key="driverSidebar.myRequests"/></a>
        </li>
        <li class="nav-item">
            <a id = "garage-nav-link" class="nav-link" href="/driver-dashboard/garage"><fmt:message key="driverSidebar.garage"/></a>
        </li>
    </ul>
</nav>