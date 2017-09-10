<%@ taglib prefix="mytags" uri="http://www.artmal.com/tags"%>

<nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a id="trips-nav-link" class="nav-link" href="/dispatcher-dashboard/trips">Trips</a>
        </li>
        <li class="nav-item">
            <a id="requests-nav-link" class="nav-link" href="/dispatcher-dashboard/requests">
                Requests
                <span class="badge badge-pill badge-default">
                    <mytags:countPendingRequests />
                </span>
            </a>
        </li>
    </ul>
</nav>