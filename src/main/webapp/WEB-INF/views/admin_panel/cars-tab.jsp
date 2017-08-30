<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/admin-panel/admin-panel-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/admin-panel/admin-menu-style.css">
</head>
<body>
<div>
    <ul>
        <li><a id = "tripsControlPanel" href="/trips-control-panel">Trip Control Panel</a></li>
        <li><a id = "registerDrivers" href="/list-of-subjects">Register Drivers</a></li>
        <li><a id = "registerDispatchers" href="/list-of-playlists">Register Dispatchers</a></li>
        <li><a id = "cars" href="/admin-panel/cars" class="active">Cars</a></li>
    </ul>

    <c:if test="${not empty listOfCars}">
        <table>
            <tr>
                <td>Car ID</td>
                <td>Reg №</td>
                <td>Car Type</td>
                <td>Manufacturer</td>
                <td>Model</td>
                <td>Production year</td>
                <td>Number of seats</td>
                <td>Car color</td>
                <td>Mileage</td>
                <td>Car condition</td>
            </tr>
            <c:forEach items="${listOfCars}" var="car" varStatus="status">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td>${car.id}</td>
                    <td>${car.registrationNumber}</td>
                    <td>${car.carType}</td>
                    <td>${car.manufacturer}</td>
                    <td>${car.model}</td>
                    <td>${car.productionYear}</td>
                    <td>${car.numberOfSeats}</td>
                    <td>${car.color}</td>
                    <td>${car.mileage}</td>
                    <td>${car.carCondition}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
