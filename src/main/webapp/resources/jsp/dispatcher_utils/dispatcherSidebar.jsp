<%@ taglib prefix="mytags" uri="http://www.artmal.com/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.utils.dispatcher_utils.dispatcherSidebar"/>

<nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id="trips-nav-link" class="nav-link" href="/dispatcher-dashboard/trips"><fmt:message key="dispatcherSidebar.trips"/></a>
        </li>
        <li class="nav-item">
            <a id="requests-nav-link" class="nav-link" href="/dispatcher-dashboard/requests">
                <fmt:message key="dispatcherSidebar.requests"/>
                <span class="badge badge-pill badge-default">
                    <mytags:countPendingRequests />
                </span>
            </a>
        </li>
    </ul>
</nav>