<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <div class="auth-card">
        <h2>Welcome Back 👋</h2>
        <p class="subtitle">Login to your account</p>

        <form action="/login" method="post">
            <label>Username</label>
            <input type="text" name="username" placeholder="Enter username" required>

            <label>Password</label>
            <input type="password" name="password" placeholder="Enter password" required>

            <button type="submit">Login</button>
        </form>

        <p>Don't have account? <a href="/register">Register</a></p>
    </div>
</div>

</body>
</html>