<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>500 - Internal Server Error</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="error-container">
    <div class="error-card">
        <div class="error-icon">⚙️</div>
        <h1>500</h1>
        <h2>Internal Server Error</h2>
        <p>Something went wrong on our end. Please try again later.</p>
        <% if (exception != null) { %>
            <p class="error-detail"><%= exception.getMessage() %></p>
        <% } %>
        <a href="/" class="error-btn">Go to Login</a>
    </div>
</div>

</body>
</html>