<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${request.contextPath}"/>

<html>
<head>
    <title>Login</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/login.css">
</head>

<body>
<div class="container">
    <section id="content">
        <form method="post" action="/loginServlet">
            <h1>Login Form</h1>
            <div>
                <input type="text" placeholder="Username" required="" id="username" name="username" />
            </div>
            <div>
                <input type="password" placeholder="Password" required="" id="password" name="password" />
            </div>
            <div>
                <input type="submit" value="Log in" />
                <a href="#">Lost your password?</a>
                <a href="/registration">Register</a>
            </div>
        </form>
    </section>
</div>
</body>
</html>