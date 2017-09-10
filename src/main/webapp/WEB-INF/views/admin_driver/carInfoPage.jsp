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

<div class="container">
    <br>
    <br>
    <div class="container">

        <div class="row" style="margin-top:20px">
            <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
                <form name="settingsChangeForm" action="/car/save" method="post">
                    <input hidden name="id" value="${param.id}">

                    <fieldset>
                        <h2>Edit Car №${carInfo.id} info</h2>
                        <label for="registration-number">Email*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-id-card-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="registration-number"
                                   name="registration-number" value="${carInfo.registrationNumber}" required>
                        </div>

                        <label for="model">Model*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-industry fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="model"
                                   name="model" value="${carInfo.model}" required>
                        </div>

                        <label for="type">Type:</label>
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


                        <label for="condition">Condition: </label>
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

                        <label for="number-of-seats">Number of seats: </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-users fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="number-of-seats"
                                   name="number-of-seats" required value="${carInfo.numberOfSeats}">
                        </div>

                        <label for="color">Car color: </label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-tint fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="color"
                                   name="color" required value="${carInfo.color}">
                        </div>

                        <br>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Save">
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>