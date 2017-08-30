<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin Panel</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/admin-panel/admin-panel-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/admin-panel/admin-menu-style.css">
</head>
<body>
    <ul>
        <li><a id = "tripsControlPanel" href="/trips-control-panel">Trip Control Panel</a></li>
        <li><a id = "registerDrivers" href="/list-of-subjects">Register Drivers</a></li>
        <li><a id = "registerDispatchers" href="/list-of-playlists">Register Dispatchers</a></li>
        <li><a id = "cars" href="/admin-panel/cars">Cars</a></li>
        <li id = "logoutli"><a id = "logout" href="/logout">Logout</a></li>
    </ul>
</body>
</html>
