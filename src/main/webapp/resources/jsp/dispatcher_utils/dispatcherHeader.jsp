<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.utils.dispatcher_utils.dispatcherHeader"/>

<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
    <button class="navbar-toggler navbar-toggler-right hidden-lg-up" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="/dispatcher-dashboard/trips"><fmt:message key="dispatcherHeader.dashboard"/></a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/my-settings"><fmt:message key="dispatcherHeader.settings"/></a>
            </li>

            <form id="changeLanguageForm" hidden>
                <button id="languageChangeButton" hidden name = "languageChange" value="change"></button>
            </form>

            <a class="nav-link" href = "javascript:document.getElementById('languageChangeButton').click();" style="float: right">
                <span class="fa fa-globe"></span>
            </a>

            <%--<li class = "nav-item">--%>
                <%--<a class="nav-link" style="float: right" href="#">--%>
                    <%--<i class="fa fa-globe"></i>--%>
                <%--</a>--%>
            <%--</li>--%>
        </ul>

        <ul class="navbar-nav" style="float: right">
            <li class="nav-item">
                <a class="nav-link" href="/logout"><fmt:message key="dispatcherHeader.logout"/></a>
            </li>
        </ul>
    </div>
</nav>