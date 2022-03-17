function login() {
    let username = $('#username').val();
    let password = $('#password').val();
    let user = {
        username: username,
        password: password
    };
    // goi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(user),
        url: "http://localhost:8080/api/auth/signin",
        success: function (data) {
            console.log(data)
            localStorage.setItem("token", data.jwt)
            localStorage.setItem("user", JSON.stringify(data))
                // window.location.href = "blog.html"
        }
    });
    event.preventDefault();
}


function regis() {
    let fullname = $('#fullName').val();
    let address = $('#address').val();
    let phone = $('#phone').val();
    let bithday = $('#birthday').val();
    let email = $('#email').val();
    let username = $('#username').val();
    let password = $('#pass').val();

    let newAcount = {
        username: username,
        email : email,
        password : password
    };

    let newUserInfor = {
        fullname :fullname,
        address : address,
        phone : phone,
        bithday : bithday,
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newAcount),
        url: "http://localhost:8080/api/auth/signup",
        success: function (data) {
            console.log(data)
            console.log(newUserInfor)
            createUserInfor(newUserInfor)
        }
    });
    event.preventDefault();
}

function createUserInfor(data) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(data),
        url: "http://localhost:8080/api/auth/signup-userinfor",
        success: function (d) {
            console.log(d)
        }
    });
    event.preventDefault();
}