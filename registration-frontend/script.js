const socket = new WebSocket("ws://localhost:8080/ws");

// Обработчик открытия соединения
socket.onopen = function() {
    console.log("✅ Подключено к WebSocket-серверу");
    addMessageToChat("🔵 Соединение установлено!");
};

// Обработчик получения сообщений
socket.onmessage = function(event) {
    console.log("📩 Получено сообщение: ", event.data);
    addMessageToChat("🟢 " + event.data);
};

// Обработчик ошибок
socket.onerror = function(error) {
    console.error("❌ Ошибка WebSocket: ", error);
    addMessageToChat("🔴 Ошибка соединения!");
};

// Обработчик закрытия соединения
socket.onclose = function(event) {
    console.log("⚠️ Соединение закрыто", event);
    addMessageToChat("🟡 Соединение разорвано!");
};

// Функция для отправки сообщений
function sendMessage() {
    const input = document.getElementById("messageInput");
    const message = input.value.trim();

    if (message) {
        console.log("📤 Отправлено: ", message);
        socket.send(message);
        addMessageToChat("📝 Вы: " + message);
        input.value = "";
    }
}

// Функция для добавления сообщений в чат
function addMessageToChat(text) {
    const chat = document.getElementById("chat");
    const messageElement = document.createElement("p");
    messageElement.textContent = text;
    chat.appendChild(messageElement);
    chat.scrollTop = chat.scrollHeight;
}
