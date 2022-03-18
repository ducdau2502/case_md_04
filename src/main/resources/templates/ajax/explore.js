function getAllUsers() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/user`,
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += displayAllUser(data[i]);
            }
            document.getElementById("user_list").innerHTML = content;
        }
    });
}

function displayAllUser(userInfo) {
    return `<div class="sl_find_frns_user">
                                <div class="sl_find_frns_user_cover"> 
                                    <img src="../static/images/Gethight.png" alt="">
                                </div>
                                <div class="sl_find_frns_user_info">
                                    <div class="sl_find_frns_user_avatar"> 
                                        <a href="timeline.html">
                                            <img src="${userInfo.avatarUrl}" alt="">
                                        </a>
                                    </div>
                                    <h4> <a href="timeline.html"> ${userInfo.fullName} </a> </h4>
                                </div>
                                <div class="sl_find_frns_user_btns">
                                    <span>
                                        <button type="button" class="btn button small primary">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                                stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                                <path d="M16 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
                                                <circle cx="8.5" cy="7" r="4"></circle>
                                                <line x1="20" y1="8" x2="20" y2="14"></line>
                                                <line x1="23" y1="11" x2="17" y2="11"></line>
                                            </svg>
                                            <span onclick="sendInvitations()"> Add Friend </span>
                                        </button>
                                    </span>
                                    <span>
                                        <button type="button" class="btn button small light">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                                stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                                class="feather feather-message-square">
                                                <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"></path>
                                            </svg>
                                            <span> Message </span>
                                        </button> 
                                    </span>
                                </div>
    </div>`;
}

function sendInvitations() {
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/api/friendship/{from_user_id}/{to_user_id}`,
        success: function () {
            getAllUsers()
        }
    });
}

function searchUser() {
    let search = document.getElementById("search_friend").value;
    $.ajax({
       type: "GET",
       url: `http://localhost:8080/api/user/search?search=${search}`,
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += displayAllUser(data[i]);
            }
            document.getElementById("user_list").innerHTML = content;
            document.getElementById("search_friend").reset();
        }
    });
    event.preventDefault();
}

window.onload = getAllUsers
