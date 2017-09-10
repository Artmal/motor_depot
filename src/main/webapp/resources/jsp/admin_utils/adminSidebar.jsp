<%@ taglib prefix="mytags" uri="http://www.artmal.com/tags"%>

<nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id = "trips-nav-link" class="nav-link" href="/admin-dashboard/trips">Trips</a>
        </li>
        <li class="nav-item">
            <a id = "requests-nav-link" class="nav-link" href="/admin-dashboard/requests">
                Requests
                <span class="badge badge-pill badge-default">
                    <mytags:countPendingRequests />
                </span>
            </a>
        </li>
    </ul>

    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id = "drivers-nav-link" class="nav-link" href="/admin-dashboard/drivers">Drivers</a>
        </li>
        <li class="nav-item">
            <a id = "dispatchers-nav-link" class="nav-link" href="/admin-dashboard/dispatchers">Dispatchers</a>
        </li>
        <li class="nav-item">
            <a id = "cars-nav-link" class="nav-link" href="/admin-dashboard/cars">Cars</a>
        </li>
    </ul>
</nav>