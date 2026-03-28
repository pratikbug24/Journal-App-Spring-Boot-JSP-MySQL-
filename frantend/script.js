const API_URL = "http://localhost:8080/api/journal";

// Load all entries
async function loadEntries() {
  const response = await fetch(API_URL);
  const data = await response.json();

  const container = document.getElementById("entries");
  container.innerHTML = "";

  data.forEach((entry) => {
    const div = document.createElement("div");
    div.className = "entry";

    div.innerHTML = `
    <h3>${entry.title}</h3>
    <p>${entry.content}</p>
    <button class="delete-btn" onclick="deleteEntry(${entry.id})">
        🗑 Delete
    </button>
`;

    container.appendChild(div);
  });
}

// Add new entry
async function addEntry() {
  const title = document.getElementById("title").value;
  const content = document.getElementById("content").value;

  await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ title, content }),
  });

  document.getElementById("title").value = "";
  document.getElementById("content").value = "";

  loadEntries();
}

// Delete entry
async function deleteEntry(id) {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });

  loadEntries();
}

// Initial load
loadEntries();
