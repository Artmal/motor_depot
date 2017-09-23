<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.utils.admin_utils.adminSidebar"/>

<nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id = "trips-nav-link" class="nav-link" href="/admin-dashboard/trips"><fmt:message key="adminSidebar.trips"/></a>
        </li>
        <li class="nav-item">
            <a id = "requests-nav-link" class="nav-link" href="/admin-dashboard/requests">
                <fmt:message key="adminSidebar.requests"/>
                <span class="badge badge-pill badge-default"></span>
            </a>
        </li>
    </ul>

    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id = "drivers-nav-link" class="nav-link" href="/admin-dashboard/drivers">
                <fmt:message key="adminSidebar.drivers"/>
            </a>
        </li>
        <li class="nav-item">
            <a id = "dispatchers-nav-link" class="nav-link" href="/admin-dashboard/dispatchers">
                <fmt:message key="adminSidebar.dispatchers"/>
            </a>
        </li>
        <li class="nav-item">
            <a id = "cars-nav-link" class="nav-link" href="/admin-dashboard/cars">
                <fmt:message key="adminSidebar.cars"/>
            </a>
        </li>
    </ul>

    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id = "task-nav-link" class="nav-link" href="/admin-dashboard/task">
                Task
            </a>
        </li>
        <li class="nav-item">
            <a id = "harder-task-nav-link" class="nav-link" href="/admin-dashboard/hard-task">
                Harder task
            </a>
        </li>
    </ul>
</nav>