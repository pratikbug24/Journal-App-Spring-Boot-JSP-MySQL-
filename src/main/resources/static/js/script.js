const API_URL = "http://localhost:8080/api/journal";

/* LOAD */
async function loadEntries() {
    const res = await fetch(API_URL);
    const data = await res.json();

    const container = document.getElementById("entries");
    container.innerHTML = "";

    data.forEach(entry => {
        const div = document.createElement("div");
        div.className = "entry";

        div.innerHTML = `
            <h3>${entry.title}</h3>
            <p>${entry.content}</p>
            <button class="delete-btn" onclick="deleteEntry(${entry.id})">Delete</button>
        `;

        container.appendChild(div);
    });
}

/* ADD */
async function addEntry() {
    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;

    if (!title || !content) {
        alert("Please fill all fields");
        return;
    }

    await fetch(API_URL, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ title, content })
    });

    document.getElementById("title").value = "";
    document.getElementById("content").value = "";

    closeModal();
    loadEntries();
}

/* DELETE */
async function deleteEntry(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    loadEntries();
}

/* MODAL */
function openModal() {
    document.getElementById("modal").classList.add("active");
}

function closeModal() {
    document.getElementById("modal").classList.remove("active");
}

/* AUTO EXPAND */
function autoExpand(textarea) {
    textarea.style.height = "auto";
    textarea.style.height = textarea.scrollHeight + "px";
}

/* INIT */
loadEntries();