const API_URL = "http://localhost:8080/api/journal";

let allEntries = [];

// Initialize on load
document.addEventListener('DOMContentLoaded', () => {
    loadThemeFromStorage();
    loadEntries();
});

/* LOAD THEME FROM STORAGE */
function loadThemeFromStorage() {
    const isDark = localStorage.getItem('journalDarkTheme') === 'true';
    if (isDark) {
        document.body.classList.add('dark-theme');
    }
}

/* THEME TOGGLE */
function toggleTheme() {
    document.body.classList.toggle('dark-theme');
    const isDark = document.body.classList.contains('dark-theme');
    localStorage.setItem('journalDarkTheme', isDark);
}

/* SETTINGS MODAL */
function showSettings() {
    document.getElementById('settingsModal').classList.add('active');
}

function closeSettings() {
    document.getElementById('settingsModal').classList.remove('active');
}

function saveSettings() {
    const username = document.getElementById('settingsUsername').value.trim();
    if (username) {
        document.getElementById('usernameDisplay').textContent = username;
        closeSettings();
    } else {
        alert('Please enter a name');
    }
}

/* LOAD ENTRIES */
async function loadEntries() {
    try {
        const res = await fetch(API_URL);
        const data = await res.json();
        
        // Sort by newest first by default
        allEntries = data.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
        
        renderEntries(allEntries);
        updateStats();
    } catch (error) {
        console.error("Error loading entries:", error);
        document.getElementById("emptyState").style.display = "block";
    }
}

/* UPDATE STATS */
function updateStats() {
    document.getElementById('totalEntries').textContent = allEntries.length;
    
    if (allEntries.length > 0) {
        const latestDate = new Date(allEntries[0].createdAt);
        document.getElementById('latestDate').textContent = formatDate(latestDate);
    } else {
        document.getElementById('latestDate').textContent = '-';
    }
}

/* FORMAT DATE */
function formatDate(date) {
    const options = { month: 'short', day: 'numeric', year: 'numeric' };
    return date.toLocaleDateString('en-US', options);
}

/* RENDER ENTRIES */
function renderEntries(entries) {
    const container = document.getElementById("entries");
    const emptyState = document.getElementById("emptyState");
    
    container.innerHTML = "";

    if (entries.length === 0) {
        emptyState.style.display = "block";
        return;
    }

    emptyState.style.display = "none";

    entries.forEach((entry, index) => {
        const div = document.createElement("div");
        div.className = "entry";
        div.style.animationDelay = `${index * 0.05}s`;

        const date = entry.createdAt ? new Date(entry.createdAt) : new Date();
        const tagsHtml = entry.tags && entry.tags.length > 0 
            ? `<div class="entry-tags">${entry.tags.map(tag => `<span class="tag">${tag}</span>`).join('')}</div>` 
            : '';

        div.innerHTML = `
            ${tagsHtml}
            <h3>${entry.title}</h3>
            <p>${entry.content}</p>
            <div class="entry-footer">${formatDate(date)}</div>
            <button class="delete-btn" onclick="deleteEntry(${entry.id})">
                <svg viewBox="0 0 24 24" fill="currentColor">
                    <path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/>
                </svg>
                Delete
            </button>
        `;

        container.appendChild(div);
    });
}

/* FILTER ENTRIES (Search) */
function filterEntries() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    
    const filtered = allEntries.filter(entry => {
        return entry.title.toLowerCase().includes(searchTerm) ||
               entry.content.toLowerCase().includes(searchTerm) ||
               (entry.tags && entry.tags.some(tag => tag.toLowerCase().includes(searchTerm)));
    });
    
    renderEntries(filtered);
}

/* SORT ENTRIES */
function sortEntries() {
    const sortBy = document.getElementById('sortSelect').value;
    
    let sorted = [...allEntries];
    
    switch (sortBy) {
        case 'newest':
            sorted.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
            break;
        case 'oldest':
            sorted.sort((a, b) => new Date(a.createdAt) - new Date(b.createdAt));
            break;
        case 'title':
            sorted.sort((a, b) => a.title.localeCompare(b.title));
            break;
    }
    
    renderEntries(sorted);
}

/* ADD ENTRY */
async function addEntry() {
    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;
    const tagsInput = document.getElementById("tags").value;
    
    if (!title || !content) {
        alert("Please fill all fields");
        return;
    }
    
    const tags = tagsInput ? tagsInput.split(',').map(t => t.trim()).filter(t => t) : [];

    await fetch(API_URL, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ title, content, tags })
    });

    document.getElementById("title").value = "";
    document.getElementById("content").value = "";
    document.getElementById("tags").value = "";

    closeModal();
    loadEntries();
}

/* DELETE ENTRY */
async function deleteEntry(id) {
    if (confirm('Are you sure you want to delete this entry?')) {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        loadEntries();
    }
}

/* MODAL FUNCTIONS */
function openModal() {
    document.getElementById("modal").classList.add("active");
}

function closeModal() {
    document.getElementById("modal").classList.remove("active");
}

/* AUTO EXPAND TEXTAREA */
function autoExpand(textarea) {
    textarea.style.height = "auto";
    textarea.style.height = textarea.scrollHeight + "px";
}

/* INIT */
loadEntries();