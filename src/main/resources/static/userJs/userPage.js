const userurl = '/api/user';

const authUser = fetch(userurl).then(response => response.json())
authUser.then(user => {
        let role = ''
        user.role.forEach(role => {
            role += ' '
            role += role.name
        })
        document.getElementById("navbar-email").innerHTML = user.email
        document.getElementById("navbar-roles").innerHTML = role
    }
)

async function getUserPage() {
    let page = await fetch(userurl);

    if(page.ok) {
        let user = await  page.json();
        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}
function  getInformationAboutUser(user) {
    const tableBody = document.getElementById('usertbody');
    let dataHtml = '';
    let role = [];
    console.log('userSata', JSON.stringify(user))
    for (let role of user.role){
        role.push(" " + role.name.toString()
            .replaceAll("ROLE_", ""))
    }
    dataHtml =
`<tr>
    <td>${user.id}</td>
    <td>${user.family}</td>
    <td>${user.name}</td>
    <td>${user.email}</td>
    <td>${role}</td>   
</tr>`

    tableBody.innerHTML = dataHtml;
}
getUserPage();