<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="tripStatus" required="true" type="com.artmal.model.enums.TripStatus" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<c:if test="${tripStatus.displayName() eq 'Open'}">
    <span class="badge badge-success"><fmt:message key="tripsPage.content.option.open"/></span>
</c:if>
<c:if test="${tripStatus.displayName() eq 'In progress'}">
    <span class="badge badge-warning"><fmt:message key="tripsPage.content.option.inProgress"/></span>
</c:if>
<c:if test="${tripStatus.displayName() eq 'Closed'}">
    <span class="badge badge-default"><fmt:message key="tripsPage.content.option.closed"/></span>
</c:if>
<c:if test="${tripStatus.displayName() eq 'Canceled'}">
    <span class="badge badge-danger"><fmt:message key="tripsPage.content.option.canceled"/></span>
</c:if>