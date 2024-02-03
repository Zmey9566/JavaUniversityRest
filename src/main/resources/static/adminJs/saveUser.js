const form_new = document.getElementById('formForNewUser');

form_new.addEventListener('submit', saveUser);

async function saveUser(event) {
    let family = document.getElementById("family").value;
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let roleId = document.getElementById("role").value;
    let password = document.getElementById("password").value;
    let role = {
        id: roleId
    };
    event.preventDefault();
    let url = '';
    if (roleId == 1) {
        url = 'api/admins'
    } else if (roleId == 2) {
        url = 'api/mentors'
    } else {
        url = 'api/students'
    }
    const urlNew = url + '/add';
    let method = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            family: family,
            name: name,
            email: email,
            role: role,
            password: password
        })
    }
    await fetch(urlNew,method).then(() => {
        form_new.reset();
        newAdmin();
        newMentor();
        newStudent();
        var triggerTabList = [].slice.call(document.querySelectorAll('#Admin_panel-tab a'))
        triggerTabList.forEach(function (triggerEl) {
            var tabTrigger = new bootstrap.Tab(triggerEl)

            triggerEl.addEventListener('click', function (event) {
                event.preventDefault()
                tabTrigger.show()
            })
        })
        var triggerEl = document.querySelector('#Admin_panel-tab a[href="#user_table"]')
        bootstrap.Tab.getInstance(triggerEl).show() // Select tab by name
    });

}


async function addRoles() {
    fetch('api/roles')
        .then(res => res.json())
        .then((data) => {
                let roles = data.map((role) => ({
                    value: role.id,
                    text: role.name.toString().replaceAll("ROLE_", "")
                }));

                document.querySelector("select[name=role]").innerHTML = roles.map(
                    (option) => `<option value="${option.value}">${option.text}</option>`
                );
            }
        )
}

window.onload = addRoles()