function findUserById() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/user/1`,
        success: function (data) {
            return data;
        }
    })
}

function deletePost(id) {
    $.ajax({
        type: "delete",
        url: `http://localhost:8080/api/post/${id}`,
        success: function () {
            showAllPost();
        }
    });
}

function countlikePost(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/like-post/${id}`,
        success: function (data) {
            console.log(data)
            document.getElementById("likeCount" + id ).innerHTML = data;
        }
    });
}

function showAllPost() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/post/get-new-feeds/1`,
        success: function (data) {
            console.log(data)
            let content = "";
            if (data !== undefined) {
                for (let i = 0; i < data.length; i++) {
                    content += displayPost(data[i]);
                }
            }
            document.getElementById("postList").innerHTML = content;
        }
    })
}

function displayPost(post) {
    let content = "";
    content += `<div class="post">
                                <div class="post-heading">
                                    <div class="post-avature">
                                        <img src="../static/assets/images/avatars/avatar-6.jpg" alt="">
                                    </div>
                                    <div class="post-title">
                                        <h4> ${post.userInfo.fullName} </h4>
                                        <p> ${post.dateCreated} <i class="uil-users-alt"></i> </p>
                                    </div>
                                    <div class="post-btn-action">
                                        <span class="icon-more uil-ellipsis-h"></span>
                                        <div class="mt-0 p-2" uk-dropdown="pos: top-right;mode:hover ">
                                            <ul class="uk-nav uk-dropdown-nav">
                                                <li><a onclick="editPost()"> <i class="uil-edit-alt mr-1"></i> Edit Post </a></li>
                                           
                                                <li><a onclick="deletePost(${post.id})" class="text-danger"> <i class="uil-trash-alt mr-1"></i>
                                                    Delete </a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="post-description">
                                    <div class="fullsizeimg">
                                    <p>${post.content}</p>`;
    if (post.imgUrl != null) {
        content += `<img src="${post.imgUrl}" alt="">`;
    }
    content += `</div>
                                </div>
                                <div class="post-state">
                                    <div class="post-state-btns"> <i class="uil-thumbs-up"></i> <span id="likeCount"> </span> <span> Liked </span>
                                    </div>
                                </div>

                                <!-- post comments -->
                                <div class="post-comments">
                                    <a onclick="showComments(${post.id})" class="view-more-comment"> View Comments</a>
                                    <div id="commentList"> 
                                        
                                    </div>
                                    
                                    <div class="post-add-comment">
                                        <div class="post-add-comment-text-area">
                                            <input type="text" placeholder="Write your comment...">
                                        </div>

                                    </div>

                                </div>
                            </div>`

    countlikePost(post.id);
    return content;
}

function showComments(id) {
    console.log(id)
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/comment/${id}`,
        success: function (data) {
            let content = "";
            if (data !== undefined) {
                for (let i = 0; i < data.length; i++) {
                    content += displayContent(data[i]);
                }
            }
            document.getElementById("commentList").innerHTML = content;
        }
    })
}

function displayContent(comment) {
   return `<div class="post-comments-single">
        <div class="post-comment-avatar">
            <img src="${comment.userInfo.avatarUrl}" alt="">
        </div>
        <div class="post-comment-text">
            <div class="post-comment-text-inner">
                <h6> ${comment.userInfo.fullName} </h6>
                <p> ${comment.content} </p>
            </div>
            <div class="uk-text-small">
                <a onclick="likeComment()" class="text-danger mr-1"> 
                <i class="uil-thumbs-up"></i> like
                </a>
            </div>
        </div>
    </div>`
}

window.onload = showAllPost;