<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="carTypeRequired" required="true" type="com.artmal.model.enums.CarType" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<c:if test="${carTypeRequired.displayName() eq 'Micro'}">
    <fmt:message key="tripsPage.content.addTripForm.option.micro"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Sedan'}">
    <fmt:message key="tripsPage.content.addTripForm.option.sedan"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Hatchback'}">
    <fmt:message key="tripsPage.content.addTripForm.option.hatchback"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Roadster'}">
    <fmt:message key="tripsPage.content.addTripForm.option.roadster"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Coupe'}">
    <fmt:message key="tripsPage.content.addTripForm.option.coupe"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Supercar'}">
    <fmt:message key="tripsPage.content.addTripForm.option.supercar"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Cabriolet'}">
    <fmt:message key="tripsPage.content.addTripForm.option.cabriolet"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Minivan'}">
    <fmt:message key="tripsPage.content.addTripForm.option.minivan"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Van'}">
    <fmt:message key="tripsPage.content.addTripForm.option.van"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'CUV'}">
    <fmt:message key="tripsPage.content.addTripForm.option.cuv"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'SUV'}">
    <fmt:message key="tripsPage.content.addTripForm.option.suv"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Pickup'}">
    <fmt:message key="tripsPage.content.addTripForm.option.pickup"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Campervan'}">
    <fmt:message key="tripsPage.content.addTripForm.option.campervan"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Mini truck'}">
    <fmt:message key="tripsPage.content.addTripForm.option.miniTruck"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Truck'}">
    <fmt:message key="tripsPage.content.addTripForm.option.truck"/>
</c:if>
<c:if test="${carTypeRequired.displayName() eq 'Big truck'}">
    <fmt:message key="tripsPage.content.addTripForm.option.bigTruck"/>
</c:if>