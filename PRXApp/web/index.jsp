<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Calculate your calories</title>
        <link rel="stylesheet" href="./css/index.css"/>
        <link rel="stylesheet" href="./css/header.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="wrapper">
            <form action="CalculateController" method="post" novalidate>
                <h1>Calculate my calories for weights loss</h1>
                <div class="tagGenders" id="gender">  
                    <p>What is your gender? </p>
                    <input type="radio" id="male" value="male" name="gender" required/>
                    <label for="male">Male</label>
                    <input type="radio" id="female" value="female" name="gender"/>
                    <label for="female">Female</label>
                </div>
                <div class="inputText">
                    <label for="height">Height (cm)</label>
                    <input type="text" id="height" name="height" required/>
                </div>
                <div class="error" id="errorHeight">Please input valid height</div>
                <div class="inputText">
                    <label for="weight">Weight (kg)</label>
                    <input type="text" id="weight" name="weight" required/>
                </div>
                <div class="error" id="errorWeight">Please input valid weight</div>
                <div class="inputText" id="yob">
                    <label for="age">Year of Birth</label>
                    <input type="text" id="age" name="age"/>
                </div>
                <div class="error" id="errorAge">Please input valid age</div>    
                <div class="inputText">
                    <label for="activity">Activity</label>
                    <select name="activity" id="activity">
                        <option value="light">Light: Office worker getting little or no exercise</option>
                        <option value="moderately">Moderately: Construction worker or person running one hour daily</option>
                        <option value="active">Vigorously active: Agricultural worker (non mechanized) or person swimming two hours daily	</option>
                    </select>
                </div>
                <c:if test="${not empty sessionScope.USER}">
                    <input type="checkbox" id="me" name="me" value="me"/><span id="span-me">Calculate for me</span>
                </c:if>
                <c:if test="${empty sessionScope.USER}">
                    <input type="checkbox" id="me" class="me-display-none" name="me" value="me"/><span class="me-display-none" id="span-me">Calculate for me</span>
                </c:if>
                <input name="result" id="result" value=""/>
                <div id="wrapperSubmitBtn">
                    <input id="btnSubmit" type="submit" value="Calculate"/>
                </div>
            </form>
        </div>
        <input value="${sessionScope.USER.year}" id="alt-age"/>
        <input value="${sessionScope.USER.sex}" id="alt-sex"/>
    </body>
    <script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
    <script>
        $('#btnSubmit').click(function (event) {
            var meCheck = document.getElementById("me").checked;
            var check = false;
            var height = $('#height').val();
            var weight = $('#weight').val();
            var age;
            var weight = document.getElementById('weight').value;
            var height = document.getElementById('height').value;
            var sex;
            if (!meCheck) {
                age = $('#age').val();
                sex = document.getElementsByName('gender');
                for (var i = 0; i < sex.length; i++) {
                    if (sex[i].checked) {
                        sex = sex[i].value;
                    }
                }
            } else {
                sex = document.getElementById('alt-sex').value;
                age = document.getElementById('alt-age').value;
            }
            if (isNaN(+height) || (+height < 0) || height == '') {
                $('#errorHeight').addClass('errorDisplay');
                check = false;
            } else {
                $('#errorHeight').removeClass('errorDisplay');
                check = true;
            }
            if (isNaN(+weight) || (+weight < 0) || weight == '') {
                $('#errorWeight').addClass('errorDisplay');
                check = false;
            } else {
                $('#errorWeight').removeClass('errorDisplay');
                check = true;
            }
            if (isNaN(+age) || (+age < 0) || (+age < 1920) || age == '') {
                $('#errorAge').addClass('errorDisplay');
                check = false;
            } else {
                $('#errorAge').removeClass('errorDisplay');
                check = true;
            }
            if (!check) {
                event.preventDefault();
            }
                var typeActivity = document.getElementById('activity').value;
                var result = calculateBMR(weight, height, age, sex, typeActivity);
                document.getElementById('result').value = result;
                console.log(document.getElementById('result').value);
        });

        var calculateBMR = (weight, height, age, sex, typeActivity) => {
            var result;
            var year = new Date().getFullYear();
            var currentAge = year - age;
            if (sex.toLowerCase() === 'male') {
                result = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * currentAge);
            } else {
                result = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * currentAge);
            }
            switch (typeActivity) {
                case 'light':
                    result = result * 1.53;
                    break;
                case 'moderately':
                    result = result * 1.76;
                    break;
                case 'active':
                    result = result * 2.25;
                    break;
            }
            return Math.round(result);
        }

        document.getElementById("me").addEventListener('change', function () {
            var isCheck = document.getElementById("me").checked;
            if (isCheck) {
                document.getElementById("yob").classList.add("yob-gone");
                document.getElementById("gender").classList.add("gender-gone");
                document.getElementById("errorAge").classList.add("gender-gone");
            } else {
                document.getElementById("yob").classList.remove("yob-gone");
                document.getElementById("gender").classList.remove("gender-gone");
                document.getElementById("errorAge").classList.remove("gender-gone");
            }
        });
    </script>
</html>