<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.utils.driver_utils.driverHeader"/>

<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
    <button class="navbar-toggler navbar-toggler-right hidden-lg-up" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="/driver-dashboard/trips"><fmt:message key="driverHeader.dashboard"/></a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/my-settings"><fmt:message key="driverHeader.settings"/></a>
            </li>

            <li class = "nav-item">
                <a class="nav-link" onclick="appendParameters(); return false;" style="float: right">
                    <span class="fa fa-globe"></span>
                </a>
            </li>
        </ul>

        <ul class="navbar-nav" style="float: right">
            <li class="nav-item">
                <a class="nav-link" href="/logout"><fmt:message key="driverHeader.logout"/></a>
            </li>
        </ul>
    </div>
</nav>

<script src="/resources/js/appendLanguageParamToUrl.min.js"></script>
