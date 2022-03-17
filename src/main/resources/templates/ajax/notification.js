//notification like post
function notificationLikePost() {
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/api/like-post/notification/${user_id}`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        success: function (data) {
            let content = "";
            if (data !== undefined) {
                console.log(data)
                for (let i = 0; i < data.length; i++) {
                    content += displayLikePostNotification(data[i]);
                }
            }
            document.getElementById("notification_like_post").innerHTML = content;
        }
    })
}

notificationLikePost()

function displayLikePostNotification(likePost) {
    return `<li>
                                       <a onclick="checkLikePostNotification(${likePost.post.id},${likePost.userInfo.id})">
                                           <span class="notification-avatar">
                                               <img src="${likePost.userInfo.avatarUrl}" alt="">
                                           </span>
                                           <span class="notification-icon bg-gradient-primary">
                                               <i class="icon-feather-thumbs-up"></i></span>
                                           <span class="notification-text">
                                               <strong>${likePost.userInfo.fullName}</strong> Like Your Post
                                           </span>
                                       </a>
                                   </li>`
}

function checkLikePostNotification(post_id,userinfo_id) {
    $.ajax({
        type: "PUT",
        url: `http://localhost:8080/api/like-post/${post_id}/${userinfo_id}`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        success: function (data) {
            console.log(data)
            notificationLikePost()
        }
    })
}

//notification like Comment
function notificationLikeComment() {
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/api/like-comment/notification/${user_id}`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        success: function (data) {
            let content = "";
            if (data !== undefined) {
                console.log(data)
                for (let i = 0; i < data.length; i++) {
                    content += displayLikeCommentNotification(data[i]);
                }
            }
            document.getElementById("notification_like_comment").innerHTML = content;
        }
    })
}

notificationLikeComment()

function displayLikeCommentNotification(LikeComment) {
    return `<li>
                                       <a onclick="checkLikeCommentNotification(${LikeComment.comment.id},${LikeComment.userInfo.id})">
                                           <span class="notification-avatar">
                                               <img src="${LikeComment.userInfo.avatarUrl}" alt="">
                                           </span>
                                           <span class="notification-icon bg-gradient-primary">
                                               <i class="icon-feather-thumbs-up"></i></span>
                                           <span class="notification-text">
                                               <strong>${LikeComment.userInfo.fullName}</strong> Like Your Comment
                                           </span>
                                       </a>
                                   </li>`
}

function checkLikeCommentNotification(comment_id,userinfo_id) {
    $.ajax({
        type: "PUT",
        url: `http://localhost:8080/api/like-post/${comment_id}/${userinfo_id}`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        success: function (data) {
            console.log(data)
            notificationLikeComment()
        }
    })
}

//notification accept friend
function notificationAcceptFriend() {
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/api/friendship/notification/${user_id}`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        success: function (data) {
            let content = "";
            if (data !== undefined) {
                console.log(data)
                for (let i = 0; i < data.length; i++) {
                    content += displayAcceptFriendNotification(data[i]);
                }
            }
            document.getElementById("notification_accept_friend").innerHTML = content;
        }
    })
}

notificationAcceptFriend()

function displayAcceptFriendNotification(friendship) {
    return `<li>
                                       <a onclick="checkAcceptFriendNotification(${friendship.fromUser.id})">
                                           <span class="notification-avatar">
                                               <img src="${friendship.fromUser.avatarUrl}" alt="">
                                           </span>
                                           <span class="notification-icon bg-gradient-primary">
                                               <i class="icon-feather-thumbs-up"></i></span>
                                           <span class="notification-text">
                                               <strong>${friendship.fromUser.fullName}</strong> want to make friends with you
                                           </span>
                                       </a>
                                   </li>`
}

function checkAcceptFriendNotification(fromUser_id) {
    $.ajax({
        type: "PUT",
        url: `http://localhost:8080/api/friendship/${fromUser_id}/${user_id}`,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        success: function (data) {
            console.log(data)
            notificationAcceptFriend()
        }
    })
}