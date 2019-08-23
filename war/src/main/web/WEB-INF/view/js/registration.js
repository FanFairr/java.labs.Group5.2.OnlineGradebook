function reg() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;

    if (login == '') {
        alert("Login must be not null");
    } else if (password == '') {
        alert("Password must be not null");
    } else if (name == '') {
        alert("Name must be not null");
    } else if (email == '') {
        alert("email must be not null");
    } else if (login.length > 20) {
        alert("Login must be less or equals 20");
    } else if (name.length > 20) {
        alert("Name must be less or equals 20");
    } else if (email.length > 20) {
        alert("email must be less or equals 30");
    } else {
        document.location.href = 'checkRegistration?login=' + login + "&password=" + password + "&name=" + name + "&email=" + email;
    }
}