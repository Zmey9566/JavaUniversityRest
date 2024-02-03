// Функция для отправки запроса на сервер и обновления таблицы
function newStudent() {
    fetch('/api/students') // Отправляем GET запрос на /humans
        .then(response => response.json()) // Преобразуем ответ в JSON
        .then(data => {
            const adminTableBody = document.getElementById('studentTableBody');
            adminTableBody.innerHTML = ''; // Очищаем таблицу перед добавлением новых данных
            data.forEach(student => {
                const row = document.createElement('tr');
                row.innerHTML = `
                        <td>${student.id}</td>
                        <td>${student.family}</td>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.role.name.toString().replaceAll("ROLE_", "")}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-bs-toogle="modal"
                            data-bs-target="#editModal" 
                            onclick="loadDataForEditModal(${student.id}, ${student.role.id})">Edit</button>
                        </td>
                            
                        <td>
                            <button class="btn btn-danger" data-bs-toogle="modal"
                            data-bs-target="#deleteModal" 
                            onclick="deleteModalData(${student.id}, ${student.role.id})">Delete</button>
                        </td>
                    `;
                adminTableBody.appendChild(row); // Добавляем строку в таблицу
            });
        })
        .catch(error => console.error('Ошибка при получении людей:', error));
}
window.onload = newStudent()                 // Вызываем функцию fetchHumans() после загрузки страницы