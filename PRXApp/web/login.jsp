<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="./css/header.css"/>
        <link rel="stylesheet" href="./css/login.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="login-page">
            <div class="form">
                <form class="register-form" action="RegisterController" method="post">
                    <input type="text" placeholder="First Name" name="firstName" id="firstName"/>
                    <div id="first-name-error" class="error-view">Please input first name</div>
                    <input type="text" placeholder="Last Name" name="lastName" id="lastName"/>
                    <div id="last-name-error" class="error-view">Please input last name</div>
                    <input type="text" placeholder="Email address" name="email" id="email"/>
                    <div id="email-error" class="error-view">Invalid email address</div>
                    <input type="text" placeholder="Year Of Birth" id="yob"/>
                    <div class="error-view" id="invalid-yob">Invalid Year Of Birth</div>
                    <div>
                        <select name="gender" id="gender">
                            <option value="male" selected>Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                    <input type="password" placeholder="Password" name="password" id="password"/>
                    <div id="password-error" class="error-view">Please input password</div>
                    <input type="password" placeholder="Confirm Password" id="confirmPassword"/>
                    <div id="confirm-password-error" class="error-view">Invalid confirm password</div>
                    <div class="error-register error"></div>
                    <button type="submit" id="register">create</button>
                    <p class="message">Already registered? <a href="#">Sign In</a></p>
                </form>
                <form action="LoginController" class="login-form" method="post">
                    <input type="text" placeholder="Email" name="email" required/>
                    <input type="password" placeholder="Password" name="password" required/>
                    <c:if test="${not empty ERROR}">
                        <div class="error">${ERROR}</div>    
                    </c:if>
                    <button type="submit">login</button>
                    <p class="message">Not registered? <a href="#">Create an account</a></p>
                </form>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
    <script>
        $('.message a').click(function () {
            $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
        });
        $('#register').click(function (e) {
            e.preventDefault();
            var firstName = $('#firstName').val();
            var lastName = $('#lastName').val();
            var email = $('#email').val();
            var password = $('#password').val();
            var confirmPassword = $('#confirmPassword').val();
            var regexEmail = /\w+@\w+\.com/;
            var yob = $('#yob').val();
            var isValid = false;
            if (isNaN(yob) || yob == '') {
                isValid = false;
                $('#invalid-yob').addClass('error');
                $('#invalid-yob').removeClass('error-view');
            } else {
                isValid = true;
                $('#invalid-yob').addClass('error-view');
            }
            if (firstName) {
                isValid = true;
                $('#first-name-error').addClass('error-view');
            } else {
                isValid = false;
                $('#first-name-error').addClass('error');
                $('#first-name-error').removeClass('error-view');
            }
            if (lastName) {
                isValid = true;
                $('#last-name-error').addClass('error-view');
            } else {
                isValid = false;
                $('#last-name-error').addClass('error');
                $('#last-name-error').removeClass('error-view');
            }
            if (email) {
                isValid = true;
                $('#email-error').addClass('error-view');
            } else {
                isValid = false;
                $('#email-error').addClass('error');
                $('#email-error').removeClass('error-view');
            }
            if (password) {
                isValid = true;
                $('#password-error').addClass('error-view');
            } else {
                isValid = false;
                $('#password-error').addClass('error');
                $('#password-error').removeClass('error-view');
            }
            if (password === confirmPassword) {
                isValid = false;
                $('#confirm-password-error').addClass('error-view');
            } else {
                isValid = true;
                $('#confirm-password-error').addClass('error');
                $('#confirm-password-error').removeClass('error-view');
            }
            if (regexEmail.test(email)) {
                isValid = true;
                $('#email-error').addClass('error-view');
            } else {
                isValid = false;
                $('#email-error').addClass('error');
                $('#email-error').removeClass('error-view');
            }
            if (isValid) {
                $.ajax({
                    url: 'RegisterController',
                    type: 'POST',
                    data: {
                        firstName: $('#firstName').val(),
                        lastName: $('#lastName').val(),
                        email: $('#email').val(),
                        password: $('#password').val(),
                        yob: $('#yob').val(),
                        gender: $('#gender').find(":selected").text()
                    },
                    success: function (error) {
                        if (error == "Email is currently used") {
                            $('.error-register').text(error);
                        } else {
                            window.location.href = "http://localhost:8080/PRXApp/HomeController";
                        }
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
        });
    </script>
</html>