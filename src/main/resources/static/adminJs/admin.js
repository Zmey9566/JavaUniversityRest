// Функция для отправки запроса на сервер и обновления таблицы
function newAdmin() {
    fetch('/api/admins') // Отправляем GET запрос на /humans
        .then(response => response.json()) // Преобразуем ответ в JSON
        .then(data => {
            const adminTableBody = document.getElementById('adminTableBody');
            adminTableBody.innerHTML = ''; // Очищаем таблицу перед добавлением новых данных
            data.forEach(admin => {
                const row = document.createElement('tr');
                row.innerHTML = `
                        <td>${admin.id}</td>
                        <td>${admin.family}</td>
                        <td>${admin.name}</td>
                        <td>${admin.email}</td>
                        <td>${admin.role.name.toString().replaceAll("ROLE_", "")}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-bs-toogle="modal"
                            data-bs-target="#editModal" 
                            onclick="loadDataForEditModal(${admin.id}, ${admin.role.id})">Edit</button>
                        </td>
                            
                        <td>
                            <button class="btn btn-danger" data-bs-toogle="modal"
                            data-bs-target="#deleteModal" 
                            onclick="deleteModalData(${admin.id}, ${admin.role.id})">Delete</button>
                        </td>
                    `;
                adminTableBody.appendChild(row); // Добавляем строку в таблицу
                document.getElementById("navbar-email").innerHTML = admin.email
                document.getElementById("navbar-role").innerHTML = admin.role.name
            });
        })
        .catch(error => console.error('Ошибка при получении людей:', error));
}
window.onload = newAdmin()                 // Вызываем функцию fetchHumans() после загрузки страницы