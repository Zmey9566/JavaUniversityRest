// Функция для отправки запроса на сервер и обновления таблицы
function newMentor() {
    fetch('/api/mentors') // Отправляем GET запрос на /humans
        .then(response => response.json()) // Преобразуем ответ в JSON
        .then(data => {
            const adminTableBody = document.getElementById('mentorTableBody');
            adminTableBody.innerHTML = ''; // Очищаем таблицу перед добавлением новых данных
            data.forEach(mentor => {
                const row = document.createElement('tr');
                row.innerHTML = `
                        <td>${mentor.id}</td>
                        <td>${mentor.family}</td>
                        <td>${mentor.name}</td>
                        <td>${mentor.email}</td>
                        <td>${mentor.role.name.toString().replaceAll("ROLE_", "")}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-bs-toogle="modal"
                            data-bs-target="#editModal" 
                            onclick="loadDataForEditModal(${mentor.id}, ${mentor.role.id})">Edit</button>
                        </td>
                            
                        <td>
                            <button class="btn btn-danger" data-bs-toogle="modal"
                            data-bs-target="#deleteModal" 
                            onclick="deleteModalData(${mentor.id}, ${mentor.role.id})">Delete</button>
                        </td>
                    `;
                adminTableBody.appendChild(row); // Добавляем строку в таблицу
            });
        })
        .catch(error => console.error('Ошибка при получении людей:', error));
}
window.onload = newMentor()                 // Вызываем функцию fetchHumans() после загрузки страницы