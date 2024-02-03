const id_del = document.getElementById('id_del');
const family_del = document.getElementById('family_del');
const name_del = document.getElementById('name_del');
const email_del = document.getElementById('email_del');
const role_del = document.getElementById("delete-role");
const deleteModal = document.getElementById("deleteModal");
const closeDeleteButton = document.getElementById("closeDelete")
const bsDeleteModal = new bootstrap.Modal(deleteModal);

let userId;
let userRoleId;

async function deleteModalData(id, role) {
    userRoleId = role;
    userId = id;
    let url;
    if (userRoleId == 1) {
        url = 'api/admins/'
    } else if (userRoleId == 2) {
        url = 'api/mentors/'
    } else {
        url = 'api/students/'
    }
    const  urlForDel1 = url + userId;
    let usersPageDel = await fetch(urlForDel1);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                family_del.value = `${user.family}`;
                name_del.value = `${user.name}`;
                email_del.value = `${user.email}`;
                role_del.value = `${user.role.name.toString().replaceAll("ROLE_", "")}`;
            })

        bsDeleteModal.show();
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}

async function deleteUser() {
    let url;
    if (userRoleId == 1) {
        url = 'api/admins/'
    } else if (userRoleId == 2) {
        url = 'api/mentors/'
    } else {
        url = 'api/students/'
    }
    let urlDel =  url + userId;
    let method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch(urlDel, method).then(() => {
        closeDeleteButton.click();
        newAdmin()
        newMentor();
        newStudent();
    })
}

