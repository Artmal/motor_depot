<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cars in the system</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/admin-panel/driversPage.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Data tables -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<%@include file = "../../../resources/jsp/admin_utils/adminHeader.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@include file = "../../../resources/jsp/admin_utils/adminSidebar.jsp" %>

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1>Cars in the system</h1>

            <c:if test="${not empty setOfCars}">
                <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Car ID</th>
                    <th>Registration Number</th>
                    <th>Type</th>
                    <th>Condition</th>
                    <th>Model</th>
                    <th>Number of seats</th>
                    <th>Color</th>
                    <th>Driver ID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${setOfCars}" var="car">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td><a href="${contextPath}/car?id=${car.id}">${car.id}</a></td>
                        <td>${car.registrationNumber}</td>
                        <td>${car.type.displayName()}</td>
                        <td>
                            <c:if test="${car.condition eq 'Broken'}">
                                <span class="badge badge-danger">Broken</span>
                            </c:if>
                            <c:if test="${car.condition eq 'Repairing'}">
                                <span class="badge badge-warning">Repairing</span>
                            </c:if>
                            <c:if test="${car.condition eq 'Ready'}">
                                <span class="badge badge-success">Ready</span>
                            </c:if>
                        </td>
                        <td>${car.model}</td>
                        <td>${car.numberOfSeats}</td>
                        <td>${car.color}</td>
                        <td><a href="${contextPath}/admin-dashboard/drivers/profile?id=${car.ownerId}">${car.ownerId}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </c:if>

            <br>

            <div class="card">
                <div class="card-block">
                    <form class="form-horizontal" action="/admin-dashboard/cars" method="post">
                        <label for="registration-number">Registration Number*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-id-card-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="registration-number"
                                   name="registration-number" required>
                        </div>

                        <label for="type">Type*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-car fa-fw"></i>
                            </div>

                            <select class="form-control" id="type" name="type" required>
                                <optgroup label="Light">
                                    <option>Micro</option>
                                    <option>Sedan</option>
                                    <option>Hatchback</option>
                                    <option>Roadster</option>
                                    <option>Coupe</option>
                                    <option>Supercar</option>
                                    <option>Cabriolet</option>
                                </optgroup>
                                <optgroup label="Medium">
                                    <option>Minivan</option>
                                    <option>Van</option>
                                    <option>CUV</option>
                                    <option>SUV</option>
                                    <option>Pickup</option>
                                </optgroup>
                                <optgroup label="Heavy">
                                    <option>Campervan</option>
                                    <option value="Mini_truck">Mini truck</option>
                                    <option>Truck</option>
                                    <option value="Big_truck">Big truck</option>
                                </optgroup>
                            </select>
                        </div>

                        <label for="condition">Condition*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-cogs fa-fw"></i>
                            </div>

                            <select class="form-control" id="condition" name="condition" required>
                                <option>Broken</option>
                                <option>Repairing</option>
                                <option>Ready</option>
                            </select>
                        </div>

                        <label for="model">Model*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-industry fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="model"
                                   name="model" placeholder="Kia Optima" required>
                        </div>

                        <label for="number-of-seats">Number of seats*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-users fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="number-of-seats"
                                   name="number-of-seats" required>
                        </div>

                        <label for="color">Car color*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-tint fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="color" name="color"
                                   required>
                        </div>

                        <label for="owner-id">Driver ID*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-user-circle-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="owner-id" name="owner-id"
                                   required>
                        </div>
                        <br>
                        <button class="btn btn-primary">Add car</button>
                    </form>
                </div>
            </div>
            <br>
        </main>
    </div>
</div>

<script>
    document.getElementById("cars-nav-link").classList.add("active");
</script>

<script>
    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>