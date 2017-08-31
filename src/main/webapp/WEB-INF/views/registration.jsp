<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html">
    <title>Registration</title>

    <link rel="stylesheet" type="text/css" media="all" href="${contextPath}/resources/css/registration/styles.css">
    <link rel="stylesheet" type="text/css" media="all" href="${contextPath}/resources/css/registration/switchery.min.css">
</head>
<body>
<div id="wrapper">
    <h1>Registration for drivers</h1>
    <form action="/register" method="post">
        <div class="col-2">
            <label>
                Name
                <input placeholder="What is your full name?" id="name" name="name" tabindex="1" required>
            </label>
        </div>
        <div class="col-2">
            <label>
                Passport Serial Number
                <input placeholder="What is your passport serial number?" id="passportSerialNumber"
                       name="passportSerialNumber" tabindex="2" required>
            </label>
        </div>
        <div class="col-2">
            <label>
                Phone Number
                <input placeholder="What is the best # to reach you?" id="phone" name="phone" tabindex="3" required>
            </label>
        </div>
        <div class="col-4">
            <label>
                Email
                <input placeholder="What is your e-mail address?" id="email" name="email" tabindex="4" required>
            </label>
        </div>

        <div class="col-4">
            <label>
                Username
                <input placeholder="What username do you want?" id="username" name="username" tabindex="5" required>
            </label>
        </div>

        <div class="col-4">
            <label>
                Yrs Experience
                <input placeholder="How long have you been driving?" id="experience" name="experience" tabindex="6"
                       required>
            </label>
        </div>

        <div class="col-4">
            <label>
                Date of birth
                <input type="date" placeholder="YYYY-MM-DD" id="dateOfBirth" name="dateOfBirth" tabindex="7" required>
            </label>
        </div>

        <div class="col-4">
            <label>
                Password
                <input type="password" placeholder="Type your password" id="password" name="password" tabindex="8" required>
            </label>
        </div>

        <div class="col-4">
            <label>
                Confirm password
                <input type="password" placeholder="Confirm your password" id="confirmPassword" name="confirmPassword" tabindex="9"
                       required>
            </label>
        </div>

        <div class="col-submit">
            <button class="submitbtn">Submit Form</button>
        </div>

    </form>
</div>
</body>
</html>
