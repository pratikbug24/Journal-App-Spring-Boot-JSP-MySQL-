<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Journal Dashboard</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="wrapper">

<div class="top-bar">

    <!-- 👤 Username (LEFT) -->
    <div class="user-info">
        Welcome, ${sessionScope.user.username}
    </div>

    <!-- 🚪 Logout (RIGHT) -->
    <div class="logout">
        <a href="/logout">
            <button>Logout</button>
        </a>
    </div>

</div>
    <div class="header">
        <h1>📔 Journal Dashboard</h1>
    </div>

    <!-- Google Keep Trigger -->
    <div class="note-trigger" onclick="openModal()">
        Take a note...
    </div>

    <!-- Modal -->
    <div id="modal" class="modal">
        <div class="modal-content">

            <input type="text" id="title" placeholder="Title">

            <textarea id="content" placeholder="Take a note..." 
                      oninput="autoExpand(this)"></textarea>

            <div class="modal-actions">
                <button onclick="addEntry()">Add</button>
                <button onclick="closeModal()">Close</button>
            </div>

        </div>
    </div>

    <!-- Entries -->
    <div id="entries" class="grid"></div>

</div>

<script src="/js/script.js"></script>
</body>
</html>