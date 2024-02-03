const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('edit-id');
const family_ed = document.getElementById('edit-family');
const name_ed = document.getElementById('edit-name');
const email_ed = document.getElementById('edit-email');
const role_ed = document.getElementById('edit-role');
const password_ed = document.getElementById('edit-password');
const editModal = document.getElementById("editModal");
const closeEditButton = document.getElementById("editClose")
const bsEditModal = new bootstrap.Modal(editModal);

let userId1;
let userRoleId1;
async function loadDataForEditModal(id1, roleId1) {
    addRolesEdit();
    userRoleId1 = roleId1;
    userId1 = id1;
    let url;
    if (userRoleId1 == 1) {
        url = 'api/admins/'
    } else if (userRoleId1 == 2) {
        url = 'api/mentors/'
    } else {
        url = 'api/students/'
    }
    const  urlDataEd = url + userId1;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        // let userData =
            await usersPageEd.json().then(user => {
                console.log('userData', JSON.stringify(user))
                id_ed.value = `${user.id}`;
                family_ed.value = `${user.family}`;
                name_ed.value = `${user.name}`;
                email_ed.value = `${user.email}`;
                role_ed.value = `${user.role.name.toString().replaceAll("ROLE_", "")}`;
            })
        console.log("id_ed: " + id_ed.value + " !!")
        bsEditModal.show();
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}
async function addRolesEdit() {
    fetch('api/roles')
        .then(res => res.json())
        .then((data) => {
                let roles = data.map((role) => ({
                    value: role.id,
                    text: role.name.toString().replaceAll("ROLE_", ""),
                }));
            // const roleNameFind = data;
            // roleName = roleNameFind;
                document.querySelector("select[name=role-new]").innerHTML = roles.map(
                    (option) => `<option value="${option.value}">${option.text}</option>`
                );
            }
        )
}

async function editUser() {
    let family = family_ed.value;
    let name = name_ed.value;
    let email = email_ed.value;
    let roleId = document.getElementById("role-new").value;
    let password = password_ed.value;
    let role1 = {
        id: roleId
    };
    let url = '';
    if (roleId == 1) {
        url = 'api/admins'
    } else if (roleId == 2) {
        url = 'api/mentors'
    } else if (roleId == 3){
        url = 'api/students'
    }
    const urlEdit = url + '/add';
    let method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            family: family,
            name: name,
            email: email,
            role: role1,
            password: password
        })
    }
    console.log(urlEdit,method)
    await fetch(urlEdit,method).then(() => {
        closeEditButton.click();
        deleteAfterEdit()
        newAdmin();
        newMentor();
        newStudent();
    })
}

async function deleteAfterEdit() {
    let url;
    if (userRoleId1 == 1) {
        url = 'api/admins/'
    } else if (userRoleId1 == 2) {
        url = 'api/mentors/'
    } else {
        url = 'api/students/'
    }
    let urlDel =  url + userId1;
    let method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    }
    await fetch(urlDel, method).then(() => {
        newAdmin();
        newMentor();
        newStudent();
    })
}


//
// async function editUser() {
//     addRolesEdit();
//     let url;
//     if (userRoleId == 1) {
//         url = 'api/admins/'
//     } else if (userRoleId == 2) {
//         url = 'api/mentors/'
//     } else {
//         url = 'api/students/'
//     }
//     const urlEdit = url + id_ed.value;
//     let roleNameFind = roleName.find(role => role.id === userRoleId).name;
//     console.dir(form_ed);
//     const method = {
//         method: 'PATCH',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify({
//             id: id_ed.value,
//             family: form_ed.family.value,
//             name: name_ed.value,
//             email: form_ed.email.value,
//             role: {
//                 id: userRoleId,
//                 name: roleNameFind
//             },
//             password: form_ed.password.value
//         })
//     }
//
//     console.log(urlEdit, method);
//     await fetch(urlEdit, method)
//         .then(() => closeEditButton.click())
//         .then(newAdmin)
//         .then(newMentor)
//         .then(newStudent);
// }
//
//
