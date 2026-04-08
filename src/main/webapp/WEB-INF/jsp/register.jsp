<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <div class="auth-card">
        <h2>Create Account 🚀</h2>
        <p class="subtitle">Start your journaling journey</p>

        <form action="/register" method="post">
            <label>Username</label>
            <input type="text" name="username" placeholder="Enter username" required>

            <label>Password</label>
            <input type="password" name="password" placeholder="Enter password" required>

            <button type="submit">Register</button>
        </form>

        <p>Already have account? <a href="/login">Login</a></p>
    </div>
</div>

</body>
</html>