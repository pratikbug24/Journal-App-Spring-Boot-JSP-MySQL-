<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <h2>Register</h2>

    <form action="/register" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>

        <button type="submit">Register</button>
    </form>

    <p>Already have account? <a href="/login">Login</a></p>
</div>

</body>
</html>