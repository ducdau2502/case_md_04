function getAllRequest(group_id) {
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/api/group-member/get-all-request/${group_id}`,
        success: function (data) {
            let content = '<tr>\n' +
                '<th>Name</th>\n' +
                '<th>Image</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += displayMember(data[i]);
            }
            document.getElementById("all_request").hidden = false;
            document.getElementById("all_request").innerHTML = content;
        }
    })
}

function displayMember(member) {
    return `<tr><td>${member.userinfo.fullName}</td><td><img style="width:100px; height:100px" src="${member.userinfo.avatarUrl}" alt=""></td>
                    <td><button class="btn btn-danger" onclick="acceptMember(${member.id})">Accept</button></td>
                    <td><button class="btn btn-warning" onclick="cancelMember(${member.id})">Cancel</button></td></tr>`;
}

// function getAll(group_id) {
//     getAllRequest(group_id);
// }