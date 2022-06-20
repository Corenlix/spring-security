function generateUsersTable() {
    fetch("/api/users/")
        .then(response => response.json())
        .then(users => {
            buildUsersTable(users);
        });
}

function buildUsersTable(users) {
    console.log('xx');
    let table = "<table>";

    $.each(users, function (index, user) {
        table += `<tr>` +
            `<td>${user.id}</td>` +
            `<td>${user.name}</td>` +
            `<td>${user.password}</td>` +
            `<td>${user.roles.map(role => role.name).join(", ")}</td>` +
            `<td><a class="btn btn-info edit-button" onclick="openEditModal(${user.id})">Edit</a></td>` +
            `<td><a class="btn btn-danger delete-button" onclick="openDeleteModal(${user.id})">Delete</a></td>` +
            `</tr>`;
    });
    table += "</table>";
    $('#users-table-body').html(table);
}

function openEditModal(userId) {
    let url = '/api/users/' + userId;
    fetch(url)
        .then(response => response.json())
        .then(user => {
            $('#edit-id').val(user.id);
            $('#edit-name').val(user.name);
            $('#edit-password').val(user.password);
            $('[id^=edit-role-]').prop("checked", false);
            user.roles.forEach((role) => {
                $("#edit-role-" + role.id).prop("checked", true);
            });

            $('#edit-form').submit(function (e) {
                e.preventDefault();

                $.ajax({
                    url: '/api/users/' + userId,
                    type: 'put',
                    data: $('#edit-form').serialize(),
                    success: function () {
                        generateUsersTable();
                        $('#edit-modal').modal('hide');
                    }
                });
            });
        });
    $('#edit-form').prop('action', url);
    $('#edit-modal').modal('show');
}

function openDeleteModal(userId) {
    {
        let url = '/api/users/' + userId;
        fetch(url)
            .then(response => response.json())
            .then(user => {
                $('#delete-id').val(user.id);
                $('#delete-name').val(user.name);
                $('#delete-password').val(user.password);
                $('[id^=delete-role-]').prop("checked", false);
                user.roles.forEach((role) => {
                    $("#delete-role-" + role.id).prop("checked", true);
                });

                $('#delete-form').submit(function (e) {
                    e.preventDefault();

                    fetch('/api/users/' + user.id, {method: 'DELETE'})
                        .then(response => {
                            generateUsersTable();
                            $('#delete-modal').modal('hide');
                        });
                });
            });
        $('#delete-form').prop('action', url);
        $('#delete-modal').modal('show');
    }
}

$(document).ready(function () {
    generateUsersTable();
    $('#new-user-form').submit(function (e) {
        e.preventDefault();
        $.ajax({
            url: '/api/users',
            type: 'post',
            data: $('#new-user-form').serialize(),
            success: function () {
                generateUsersTable();
                $('#new-user-form')[0].reset();
            }
        });
    });
});