$(async function() {
    await thisUserView();
});
async function thisUserView() {
    fetch("http://localhost:8080/api/viewUser")
        .then(res => res.json())
        .then(userData => {
            $('#headerUsername').append(userData.username);
            let roles = userData.roles.map(role => "" + role.name.substring(5));
            $('#headerRoles').append(roles);
            let user = `$(
            <tr>
                <td>${userData.id}</td>
                <td>${userData.firstName}</td>
                <td>${userData.lastName}</td>
                <td>${userData.age}</td>
                <td>${userData.username}</td>
                <td>${roles}</td>)`;
            $('#userPanelBody').append(user);
        })
}



