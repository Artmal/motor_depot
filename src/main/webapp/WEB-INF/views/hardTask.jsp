<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>


<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dashboard.carsPage" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="adminDashboard.carsPage.header"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page-->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-panel/driversPage.min.css" rel="stylesheet">

    <!-- Data tables imports -->
    <%@include file = "../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<%@include file = "../../resources/jsp/admin_utils/adminHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../resources/jsp/admin_utils/adminSidebar.jsp" %>

        <fmt:setBundle basename="i18n.admin_dashboard.carsPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1>Number of trips by driver</h1>

            <table id="result-table" class="table table-striped table-bordered" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Driver ID</th>
                    <th>Amount of trips</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resultMap}" var="entry">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td>
                            <a href="/drivers/profile?id=${entry.key}">
                                    ${entry.key}
                            </a>

                        </td>
                        <td>${entry.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
        </main>
    </div>
</div>

<script>
    document.getElementById("task-nav-link").classList.add("active");
</script>

<script>
    var table = $('#result-table').DataTable({
        responsive: true,
        <c:if test="${language.getLanguage() eq 'ru'}">
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Russian.json"
        }
        </c:if>
    });

    table
        .column( '0:visible' )
        .order( 'asc' )
        .draw();
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<link href="${contextPath}/webjars/tether/1.4.0/dist/js/tether.min.js" rel="script">
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>