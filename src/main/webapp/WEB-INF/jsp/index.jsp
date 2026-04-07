<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Journal Dashboard</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="wrapper">

    <!-- Top Bar -->
    <div class="top-bar">
        <div class="user-info">
            👤 ${sessionScope.user.username}
        </div>
        <div class="logout">
            <a href="/logout">
                <button>Logout</button>
            </a>
        </div>
    </div>

    <div class="header">
        <h1>📔 Journal Dashboard</h1>
        <p>Capture your thoughts and ideas</p>
    </div>

    <!-- Google Keep Style Trigger -->
    <div class="note-trigger" onclick="openModal()">
        <span>📝</span>
        Take a note...
    </div>


    <!-- Entries Grid -->
    <div id="entries" class="grid"></div>

    <!-- Empty State (hidden by default) -->
    <div id="emptyState" class="empty-state" style="display: none;">
        <svg viewBox="0 0 24 24" fill="currentColor">
            <path d="M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm-5 14H7v-2h7v2zm3-4H7v-2h10v2zm0-4H7V7h10v2z"/>
        </svg>
        <h3>No entries yet</h3>
        <p>Click "Take a note..." to create your first journal entry</p>
    </div>

</div>

<!-- Modal -->
<div id="modal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>New Entry</h2>
        </div>
        <div class="input-container">
            <input type="text" id="title" placeholder="Title">
            <textarea id="content" placeholder="Take a note..."
                      oninput="autoExpand(this)"></textarea>
        </div>
        <div class="modal-actions">
            <button onclick="addEntry()">Add Entry</button>
            <button onclick="closeModal()">Close</button>
        </div>
    </div>
</div>

<script src="/js/script.js"></script>
</body>
</html>