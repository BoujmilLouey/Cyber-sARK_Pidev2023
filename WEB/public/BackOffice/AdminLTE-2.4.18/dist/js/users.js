
$(document).ready(function() {
    // Sort the users by roles
    $('#sort-by-roles').on('click', function() {
        $.ajax({
            url: '/ajax/sort-users-by-roles',
            method: 'GET',
            dataType: 'json',
            success: function(users) {
                // Clear the table
                $('#users-table tbody').empty();

                // Add the sorted users to the table
                users.forEach(function(user) {
                    $('#users-table tbody').append(`
                         <tr>
                <td>${ user.username }</td>
                <td>${ user.email }</td>
                <td>    {% if 'ROLE_ADMIN' in user.roles %}
                            {{"Administrateur"}}
                        {% else %}
                            {% if 'ROLE_COACH' in user.roles %}
                            {{"Coach"}}
                            {% else %}
                            {{"Utilisateur"}}
                            {% endif %}
                        {% endif %}
                </td>
                <td>${ user.password  }</td>
                <td>${ user.isVerified ? 'Yes' : 'No' }</td>
                <td>${ user.isBanned ? 'Yes' : 'No' }</td>
                
                <td>${ user.fullname }</td>
                <td>${ user.naissance ? user.naissance|date('Y-m-d') : '' }</td>
                    `);
                });
            }
        });
    });
});
