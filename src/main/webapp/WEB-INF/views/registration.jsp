<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html">
    <title>Registration</title>
    <meta name="author" content="Jake Rocheleau">
    <link rel="shortcut icon" href="http://static.tmimgcdn.com/img/favicon.ico">
    <link rel="icon" href="http://static.tmimgcdn.com/img/favicon.ico">

    <link rel="stylesheet" type="text/css" media="all" href="${contextPath}/resources/css/registration/styles.css">
    <link rel="stylesheet" type="text/css" media="all" href="${contextPath}/resources/css/registration/switchery.min.css">
    <script type="text/javascript" src="${contextPath}/resources/js/switchery.min.js"></script>
</head>
<body>
<div id="wrapper">
    <h1>Registration</h1>

    <form action="/register" method="post">
        <div class="col-2">
            <label>
                Name
                <input placeholder="What is your full name?" id="name" name="name" tabindex="1">
            </label>
        </div>
        <div class="col-2">
            <label>
                Phone Number
                <input placeholder="What is the best # to reach you?" id="phone" name="phone" tabindex="2">
            </label>
        </div>
        <div class="col-3">
            <label>
                Email
                <input placeholder="What is your e-mail address?" id="email" name="email" tabindex="4">
            </label>
        </div>

        <div class="col-4">
            <label>
                Yrs Experience
                <input placeholder="How many years of experience?" id="experience" name="experience" tabindex="7">
            </label>
        </div>

        <div class="col-4">
            <label>
                Password
                <input placeholder="Type your password" id="password" name="password" tabindex="8">
            </label>
        </div>

        <div class="col-4">
            <label>
                Confirm password
                <input placeholder="Confirm your password" id="confirmPassword" name="confirmPassword" tabindex="9">
            </label>
        </div>

        <div class="col-submit">
            <button class="submitbtn">Submit Form</button>
        </div>

    </form>
</div>
<script type="text/javascript">
    var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

    elems.forEach(function(html) {
      var switchery = new Switchery(html);
    });
</script>
</body>
</html>
