<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="custom" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

<fmt:message key="login.placeholder.email" var="emailPlaceholderText" />

<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><fmt:message key="tripsPage.title"/></title>

    <!-- Bootstrap CSS & Font Awesome -->
    <link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Styles for the page-->
    <link href="${contextPath}/resources/css/admin-panel/dashboard.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/validation.css" rel="stylesheet">

    <!-- Datetime picker -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />

    <!-- Data tables -->
    <%@include file = "../../../resources/dataTablesScriptsImport.jsp" %>
</head>

<body>
<c:choose>
    <c:when test = "${sessionScope.role eq 'Admin'}">
        <%@include file = "../../../resources/jsp/admin_utils/adminHeader.jsp" %>
    </c:when>

    <c:when test = "${sessionScope.role eq 'Dispatcher'}">
        <%@include file = "../../../resources/jsp/dispatcher_utils/dispatcherHeader.jsp" %>
    </c:when>
</c:choose>

<div class="container-fluid">
    <div class="row">
        <c:choose>
            <c:when test = "${sessionScope.role eq 'Admin'}">
                <%@include file = "../../../resources/jsp/admin_utils/adminSidebar.jsp" %>
            </c:when>

            <c:when test = "${sessionScope.role eq 'Dispatcher'}">
                <%@include file = "../../../resources/jsp/dispatcher_utils/dispatcherSidebar.jsp" %>
            </c:when>
        </c:choose>

        <fmt:setBundle basename="i18n.admin_dispatcher.tripsPage" />

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><fmt:message key="tripsPage.content.trips"/></h1>

            
            <c:choose>
                <c:when test="${not empty setOfTrips}">
                    <custom:showTripsTable setOfTrips="${setOfTrips}"/>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info" role="alert">
                        <fmt:message key="tripsPage.noTripsYet"/>
                    </div>
                </c:otherwise>
            </c:choose>

            <br>

            <div class="card">
                <div class="card-block">
                    <c:if test="${sessionScope.role eq 'Admin'}">
                        <c:set var="actionUrl" value="/admin-dashboard/trips"/>
                    </c:if>
                    <c:if test="${sessionScope.role eq 'Dispatcher'}">
                        <c:set var="actionUrl" value="/dispatcher-dashboard/trips"/>
                    </c:if>

                    <form id = "add-trip-form" action="${actionUrl}" method="post">
                        <label for="status"><fmt:message key="tripsPage.content.label.status"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-tasks fa-fw"></i>
                            </div>
                            <select class="form-control" id="status" name="status" required>
                                <option value="Open"><fmt:message key="tripsPage.content.option.open"/></option>
                                <option value="In_progress"><fmt:message key="tripsPage.content.option.inProgress"/></option>
                                <option value="Closed"><fmt:message key="tripsPage.content.option.closed" /></option>
                                <option value="Canceled"><fmt:message key="tripsPage.content.option.canceled"/></option>
                            </select>
                        </div>

                        <label for="car-type-required"><fmt:message key="tripsPage.content.addTripForm.label.carTypeRequired"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-car fa-fw"></i>

                            </div>
                            <select class="form-control" id="car-type-required" name="car-type-required" required>
                                <optgroup label="Light">
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.micro"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.sedan"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.hatchback"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.roadster"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.coupe"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.supercar"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.cabriolet"/></option>
                                </optgroup>
                                <optgroup label="Medium">
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.minivan"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.van"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.cuv"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.suv"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.pickup"/></option>
                                </optgroup>
                                <optgroup label="Heavy">
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.campervan"/></option>
                                    <option value="Mini_truck"><fmt:message key="tripsPage.content.addTripForm.option.miniTruck"/></option>
                                    <option><fmt:message key="tripsPage.content.addTripForm.option.truck"/></option>
                                    <option value="Big_truck"><fmt:message key="tripsPage.content.addTripForm.option.bigTruck"/></option>
                                </optgroup>
                            </select>
                        </div>

                        <label for="town-from"><fmt:message key="tripsPage.content.addTripForm.label.townFrom"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-building-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="town-from" name="town-from"
                                   placeholder="Kharkov">
                        </div>

                        <label for="town-to"><fmt:message key="tripsPage.content.addTripForm.label.townTo"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-building fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="town-to" name="town-to"
                                   placeholder="Kiev">
                        </div>


                        <label for="time-out"><fmt:message key="tripsPage.content.addTripForm.label.timeOut"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="time-out"
                                   name="time-out">
                        </div>

                        <label for="time-in"><fmt:message key="tripsPage.content.addTripForm.label.timeIn"/>*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar-check-o fa-fw"></i>
                            </div>
                            <input class="form-control mb-2 mr-sm-2 mb-sm-0" id="time-in" name="time-in">
                        </div>

                        <label for="payment-in-dollars"><fmt:message key="tripsPage.content.addTripForm.label.payment"/>($)*:</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon">
                                <i class="fa fa-usd fa-fw"></i>
                            </div>
                            <input id="payment-in-dollars" name="payment-in-dollars" class="form-control mb-2 mr-sm-2 mb-sm-0">
                        </div>
                        <br>
                        <button id="submit-button" class="btn btn-primary"><fmt:message key="tripsPage.content.addTripForm.button.addTrip"/></button>
                    </form>
                </div>
            </div>
            <br>
        </main>
    </div>
</div>

<script src="${contextPath}/webjars/jquery-validation/1.17.0/jquery.validate.min.js"></script>
<script src="${contextPath}/webjars/jquery-validation/1.17.0/additional-methods.min.js"></script>
<script src="${contextPath}/resources/js/validation/admin_dispatcher/tripsPage.js"></script>

<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>

<script>
    document.getElementById("trips-nav-link").classList.add("active");
</script>

<script>
    var table = $('#trips-table').DataTable({
        "iDisplayLength": 50
    });

    table
        .column( '0:visible' )
        .order( 'asc' )
        .iDisplayLength(100)
        .draw();
</script>

<script type="text/javascript">
    $(function() {
        $('input[name="time-out"]').daterangepicker({
                singleDatePicker: true,
                showDropdowns: true,
                timePicker: true,
                timePicker24Hour: true,
                drops: 'up',
                locale: {
                    format: 'YYYY-MM-DD HH:mm'
                },
                minDate: moment().format('YYYY-MM-DD HH:mm')
            }
        );
    });

    $('#time-out').on('apply.daterangepicker', function(ev, picker) {
        var minDate2 = picker.startDate.format('YYYY-MM-DD HH:mm');

        $('input[name="time-in"]').daterangepicker({
            singleDatePicker: true,
            showDropdowns: true,
            timePicker: true,
            timePicker24Hour: true,
            drops: 'up',
            locale: {
                format: 'YYYY-MM-DD HH:mm'
            },
            minDate: minDate2
        });
    });

    $(function() {
        $('input[name="time-in"]').daterangepicker({
                singleDatePicker: true,
                showDropdowns: true,
                timePicker: true,
                timePicker24Hour: true,
                drops: 'up',
                locale: {
                    format: 'YYYY-MM-DD HH:mm'
                },
                minDate: moment().format('YYYY-MM-DD HH:mm')
            }
        );
    });
</script>

<script>
    $(document).on("submit", "#add-trip-form", function(event) {
        var $form = $(this);

        $.post($form.attr("action"), $form.serialize(), function(response) {
            var lastRow = $(response).find('tr:last').attr('id');
            console.log(lastRow);

            $('#trips-table').find("> tbody").append(lastRow);
            table.destroy();

            $(document).ready(function() {
                $('#trips-table').DataTable({
                    "iDisplayLength": 50
                });
                table
                    .column( '0:visible' )
                    .order( 'asc' )
                    .iDisplayLength(100)
                    .draw();

            } );

        });

        event.preventDefault();
    });
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<link href="${contextPath}/webjars/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" rel="script">
</body>
</html>