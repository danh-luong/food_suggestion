<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile</title>
        <link rel="stylesheet" href="./css/header.css"/>
        <link rel="stylesheet" href="./css/profile.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:set var="user" value="${requestScope.PROFILE}"/>
        <div id="container">
            <img id="user" src="./image/cbd0b1a6345a44b58dda0f6a355eb39ce4e8a56a.png"/>
            <form action="UpdateProfileController" id="profile-form">
                <div class="input-group">
                    <label name="email" for="email">Email:</label>
                    <input type="text" id="email" value="${user.email}" name="email" required/>
                </div>
                <div id="email-error" class="error-view">Invalid email address</div>
                <div class="input-group">
                    <label name="firstName" for="firstName">First Name:</label>
                    <input type="text" id="firstName" value="${user.firstname}" name="firstName" required/>
                </div>
                <div class="input-group">
                    <label name="lastName" for="lastName">Last Name:</label>
                    <input type="text" id="lastName" value="${user.lastname}" name="lastName" required/>
                </div>
                <div class="input-group">
                    <label for="gender">Gender: </label>
                    <select name="gender" id="gender">
                        <c:if test="${not empty user.sex}">
                            <c:if test="${user.sex == 'male'}">
                                <option value="male" selected>Male</option>
                                <option value="female">Female</option>
                            </c:if>
                            <c:if test="${user.sex == 'female'}">
                                <option value="male">Male</option>
                                <option value="female" selected>Female</option>
                            </c:if>    
                        </c:if>
                    </select>
                </div>
                <div class="input-group">
                    <label for="yob">Year Of Birth: </label>
                    <input type="text" id="yob" value="${user.year}" name="yob" required/>
                </div>
                <div class="error-view" id="invalid-yob">Invalid Year Of Birth</div>
                <div id="wrapperSubmitBtn">
                    <input id="btnSubmit" type="submit" value="Save"/>
                    <a id="btnLogOut" href="LogOutController"/>LogOut</a>
                </div>
            </form>
        </div>
    </body>
    <script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
    <script>
        document.getElementById("btnSubmit").addEventListener('click', function (e) {
            var email = document.getElementById('email').value;
            var regexEmail = /\w+@\w+\.com/;
            var yob = document.getElementById('yob').value;
            var isValid = false;
            if (isNaN(yob) || yob == '') {
                isValid = false;
                $('#invalid-yob').addClass('error');
                $('#invalid-yob').removeClass('error-view');
            } else {
                isValid = true;
                $('#invalid-yob').addClass('error-view');
            }
            if (regexEmail.test(email)) {
                isValid = true;
                $('#email-error').addClass('error-view');
            } else {
                isValid = false;
                $('#email-error').addClass('error');
                $('#email-error').removeClass('error-view');
            }
            if (!isValid) {
                e.preventDefault();
            }
        });
    </script>
</html>