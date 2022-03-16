let group = 0;

function displayFormCreate() {
    document.getElementById("form").reset();
    document.getElementById("form").hidden = false;
    document.getElementById("form-button").onclick = function () {
        addNewGroup();
    }
}

function addNewGroup(userinfo_id) {
    let name = $('#name').val();
    let newGroup = {
        name: name
    }

    $.ajax({
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(newGroup),
        url: `http://localhost:8080/api/group/create-group/2`,
        success: function () {
            getGroups();
        }
    });
    event.preventDefault();
}

function getGroups() {
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/api/group/get-group`,
        success: function (data) {
            console.log(data)
            let content = '';
            for (let i = 0; i < data.length; i++) {
                content += displayGroup(data[i]);
            }
            document.getElementById("group_list").innerHTML = content;
            document.getElementById("form").hidden = true;
        }
    });
}

function displayGroup(group) {
    return `  <li>

                                    <div class="sl_group_list">

                                        <!-- Group cover -->
                                        <a href="group-feed.html">
                                            <div class="sl_group_list_media">
                                                <img src="../static/assets/images/group/group-cover-4.jpg" alt="image">
                                            </div>
                                        </a>

                                        <!-- Group  Content -->
                                        <div class="sl_group_list_info">
                                            <h3><a href="group-feed.html">${group.name}</a></h3>
                                            <ul>
                                                <li> <span> 12 Members </span> </li>
                                                <li> <span> 2 posts </span> </li>
                                            </ul>

                                            <div class="sl_avatar-groups sl_hide_members">
                                                <a href="group-feed.html">

                                                    <img src="../static/assets/images/avatars/avatar-3.jpg" class="avatars"
                                                         alt="picture">
                                                    <img src="../static/assets/images/avatars/avatar-4.jpg" class="avatars"
                                                         alt="picture">
                                                    <img src="../static/assets/images/avatars/avatar-5.jpg" class="avatars"
                                                         alt="picture">
                                                    <img src="../static/assets/images/avatars/avatar-6.jpg" class="avatars"
                                                         alt="picture">

                                                </a><a href="group-feed.html">See all Members </a>

                                            </div>

                                            <div class="sl_group_list_info_btns">
                                                <span>
                                                    <button type="button" class="button primary small block">
                                                        <span> Join</span>
                                                    </button>
                                                </span>
                                                <span>
                                                    <button type="button" class="button light small block">
                                                        <span>View</span>
                                                    </button>
                                                </span>
                                            </div>

                                        </div>
                                    </div>

                                </li>`;
}

window.onload = getGroups;