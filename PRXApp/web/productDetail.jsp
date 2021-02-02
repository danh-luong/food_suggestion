<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Detail</title>
        <link rel="stylesheet" href="./css/header.css"/>
        <link rel="stylesheet" href="./font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="./css//productDetail.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:set var="food" value="${requestScope.FOOD}"/>
        <div id="container">
            <div id="child-container">
                <div id="header-container">
                    <img src="${food.imageUrl}"/>
                    <div id="name">${food.name}</div>
                    <div id="characteristic">
                        <ul>
                            <c:if test="${not empty food.time}">
                                <li>
                                    <i class="fa fa-clock-o" aria-hidden="true"></i>
                                    <span>${food.time}</span>
                                </li>
                            </c:if>
                            <c:if test="${not empty food.serves}">    
                                <li>
                                    <i class="fa fa-pie-chart" aria-hidden="true"></i>
                                    <span>${food.serves}</span>
                                </li>
                            </c:if>
                            <c:if test="${not empty food.level}">    
                                <li>
                                    <i class="fa fa-level-up" aria-hidden="true"></i>
                                    <span>${food.level}</span>
                                </li>
                            </c:if>
                            <c:if test="${not empty food.cals}">
                                <li>
                                    <i class="fa fa-calculator" aria-hidden="true"></i>
                                    <span>${food.cals} calories / serving</span>
                                </li>
                            </c:if>  
                        </ul>
                    </div>
                    <div id="description">
                        ${food.description}
                    </div>
                </div>
                <hr id="division"/>
                <div id="button-add">
                    <div>
                        <span id="foodId">${food.foodId}</span>
                        <c:if test="${requestScope.isFavorite}">
                            <i class="fa fa-bookmark follow"></i>
                        </c:if>
                        <c:if test="${!requestScope.isFavorite}">
                            <i class="fa fa-bookmark"></i>
                        </c:if>
                        <span id="favorite">
                            <c:if test="${requestScope.isFavorite}">
                                UnBookMark
                            </c:if>
                            <c:if test="${!requestScope.isFavorite}">
                                BookMark
                            </c:if>
                        </span>
                    </div>
                </div>
                <div id="bottom-container">
                    <div id="leftside">
                        <h2>Ingredients</h2>
                        <div id="ingredients-container">
                            <c:forEach var="foodIngredient" items="${requestScope.INGREDIENTS}">
                                <div>
                                    <span>
                                        ${foodIngredient}
                                    </span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div id="rightside">
                        <h2>Method</h2>
                        <c:forEach var="foodStep" items="${requestScope.STEPS}" varStatus="loop">
                            <div class="steps-container">
                                <div>
                                    <div class="step">Step ${loop.index + 1}</div>
                                    <div>
                                        ${foodStep} 
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <c:if test="${not empty food.tip}">
                    <div id="tips">
                        <span>Tip:</span>
                        ${food.tip}
                    </div>
                </c:if>
            </div>
        </div>
    </body>
    <script>
        document.getElementById('button-add').addEventListener('click', function () {
            var foodId = document.getElementById('foodId').innerHTML;
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'FavoriteController');
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send("foodId=" + foodId);
            xhr.onreadystatechange = function () {
                var dataText = xhr.responseText;
                if (dataText == 'Not login') {
                    window.location.href = "http://localhost:8080/PRXApp/login.jsp";
                }
                if (dataText === 'Following') {
                    document.getElementById('favorite').innerHTML = 'UnBookMark';
                    document.getElementsByClassName('fa-bookmark')[0].classList.add("follow");
                } else {
                    document.getElementById('favorite').innerHTML = 'BookMark';
                    document.getElementsByClassName('fa-bookmark')[0].classList.remove("follow");
                }
            }
        });
    </script>
</html>