<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="./css/header.css"/>
        <link rel="stylesheet" href="./css/home.css"/>
        <link rel="stylesheet" href="./css/loading.css"/>
        <link rel="stylesheet" href="./font-awesome-4.7.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="search">
            <input type="text" placeholder="Search Your Favorite Food..." id="input-search"/>
            <i class="fa fa-search" aria-hidden="true"></i>
        </div>
        <div id="drop-down-category">
            <select name="category" id="category">
                <option value="default" selected>Category</option>
                <c:forEach var="category" items="${requestScope.CATEGORY}">
                    <option value="${category.categoryId}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="loader loader-hidden">Loading...</div>
        <div id="container">
            <c:forEach var="food" items="${requestScope.FOODCONTAINER}">
                <div class="container-child">
                    <img src="${food.imageUrl}"/>
                    <div class="detail">
                        <div class="name">
                            <a href="ProductDetailController?foodId=${food.foodId}">
                                ${food.name}
                            </a>
                        </div>
                        <div class="description">
                            ${food.description}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div id="offset">${requestScope.OFFSET}</div>
        <div id="loadMore">LOAD MORE</div>
    </body>
    <script>
        document.getElementById('loadMore').addEventListener('click', function () {
            var offset = document.getElementById('offset').innerHTML;
            var category = document.getElementById('category');
            var searchValue = document.getElementById('input-search').value;
            var selectedValue = category.options[category.selectedIndex].value;
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'LoadMoreController');
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send("offset=" + offset + "&" + "categoryId=" + selectedValue + "&" + "inputSearch=" + searchValue);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    var dataText = xhr.responseText;
                    var parser = new DOMParser();
                    var xmlDoc = parser.parseFromString(dataText, 'text/xml');
                    var divName;
                    var imageUrl;
                    var description;
                    var href;
                    var name;
                    for (let i = 0; i < xmlDoc.getElementsByTagName("foodlist")[0].childNodes.length; i++) {
                        var containerChild = document.createElement("div");
                        containerChild.classList.add('container');
                        for (let j = 0; j < xmlDoc.getElementsByTagName("food")[i].childNodes.length; j++) {
                            var tmpChildNode = xmlDoc.getElementsByTagName("food")[i].childNodes[j].nodeName;
                            var tmpChildValue = xmlDoc.getElementsByTagName("food")[i].childNodes[j];
                            switch (tmpChildNode) {
                                case 'name':
                                    divName = document.createElement("div");
                                    divName.classList.add("name");
                                    name = tmpChildValue.innerHTML;
                                    break;
                                case 'imageUrl':
                                    imageUrl = document.createElement("img");
                                    imageUrl.src = tmpChildValue.innerHTML;
                                    break;
                                case 'description':
                                    description = document.createElement("div");
                                    description.classList.add("description");
                                    description.innerHTML = tmpChildValue.innerHTML;
                                    break;
                                case 'foodId':
                                    href = "ProductDetailController?foodId=" + tmpChildValue.innerHTML;
                                    break;
                            }
                        }
                        var a = document.createElement("a");
                        a.href = href;
                        a.innerHTML = name;
                        divName.appendChild(a);
                        var divDetail = document.createElement("div");
                        divDetail.classList.add("detail");
                        divDetail.appendChild(divName);
                        divDetail.appendChild(description);
                        var divChildContainer = document.createElement("div");
                        divChildContainer.classList.add("container-child");
                        divChildContainer.appendChild(imageUrl);
                        divChildContainer.appendChild(divDetail);
                        var container = document.getElementById("container");
                        container.appendChild(divChildContainer);
                    }
                }
            }
            document.getElementById("offset").innerHTML = parseInt(document.getElementById("offset").innerHTML) + 10;
        });

        document.getElementById('category').addEventListener('change', function () {
            var containerChild = document.getElementsByClassName('container-child');
            let element = containerChild.length - 1;
            while (element >= 0) {
                containerChild[element].remove();
                element--;
            }
            document.getElementsByClassName("loader")[0].classList.remove('loader-hidden');
            setTimeout(function () {
                document.getElementById("offset").innerHTML = 0;
                var category = document.getElementById('category');
                var searchValue = document.getElementById('input-search').value;
                var selectedValue = category.options[category.selectedIndex].value;
                var offset = document.getElementById("offset").innerHTML;
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'SearchController');
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("categoryId=" + selectedValue + "&" + "inputSearch=" + searchValue + "&" + "offset=" + offset);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4) {
                        var dataText = xhr.responseText;
                        if (dataText == 'Home') {
                            window.location.href = "http://localhost:8080/PRXApp/HomeController";
                        }
                        var parser = new DOMParser();
                        var xmlDoc = parser.parseFromString(dataText, 'text/xml');
                        var divName;
                        var imageUrl;
                        var description;
                        var href;
                        var name;
                        for (let i = 0; i < xmlDoc.getElementsByTagName("foodlist")[0].childNodes.length; i++) {
                            var containerChild = document.createElement("div");
                            containerChild.classList.add('container');
                            for (let j = 0; j < xmlDoc.getElementsByTagName("food")[i].childNodes.length; j++) {
                                var tmpChildNode = xmlDoc.getElementsByTagName("food")[i].childNodes[j].nodeName;
                                var tmpChildValue = xmlDoc.getElementsByTagName("food")[i].childNodes[j];
                                switch (tmpChildNode) {
                                    case 'name':
                                        divName = document.createElement("div");
                                        divName.classList.add("name");
                                        name = tmpChildValue.innerHTML;
                                        break;
                                    case 'imageUrl':
                                        imageUrl = document.createElement("img");
                                        imageUrl.src = tmpChildValue.innerHTML;
                                        break;
                                    case 'description':
                                        description = document.createElement("div");
                                        description.classList.add("description");
                                        description.innerHTML = tmpChildValue.innerHTML;
                                        break;
                                    case 'foodId':
                                        href = "ProductDetailController?foodId=" + tmpChildValue.innerHTML;
                                        break;
                                }
                            }
                            var a = document.createElement("a");
                            a.href = href;
                            a.innerHTML = name;
                            divName.appendChild(a);
                            var divDetail = document.createElement("div");
                            divDetail.classList.add("detail");
                            divDetail.appendChild(divName);
                            divDetail.appendChild(description);
                            var divChildContainer = document.createElement("div");
                            divChildContainer.classList.add("container-child");
                            divChildContainer.appendChild(imageUrl);
                            divChildContainer.appendChild(divDetail);
                            var container = document.getElementById("container");
                            container.appendChild(divChildContainer);
                        }
                    }
                }
                document.getElementsByClassName("loader")[0].classList.add('loader-hidden');
                document.getElementById("offset").innerHTML = parseInt(document.getElementById("offset").innerHTML) + 10;
            }, 2000);
        });

        document.getElementById('input-search').addEventListener('input', function (e) {
            var containerChild = document.getElementsByClassName('container-child');
            let element = containerChild.length - 1;
            while (element >= 0) {
                containerChild[element].remove();
                element--;
            }
            document.getElementsByClassName("loader")[0].classList.remove('loader-hidden');
            setTimeout(function () {
                document.getElementById("offset").innerHTML = 0;
                var category = document.getElementById('category');
                var selectedValue = category.options[category.selectedIndex].value;
                var searchValue = document.getElementById('input-search').value;
                var offset = document.getElementById("offset").innerHTML;
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'SearchController');
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("categoryId=" + selectedValue + "&" + "inputSearch=" + searchValue + "&" + "offset=" + offset);
                xhr.onreadystatechange = function () {
                    var containerChild = document.getElementsByClassName('container-child');
                    let element = containerChild.length - 1;
                    while (element >= 0) {
                        containerChild[element].remove();
                        element--;
                    }
                    var containerChild = document.getElementsByClassName('container-child');
                    for (var i = 0; i < containerChild.length; i++) {
                        containerChild[i].remove();
                    }
                    if (xhr.readyState == 4) {
                        var dataText = xhr.responseText;
                        var parser = new DOMParser();
                        var xmlDoc = parser.parseFromString(dataText, 'text/xml');
                        var divName;
                        var imageUrl;
                        var description;
                        var href;
                        var name;
                        for (let i = 0; i < xmlDoc.getElementsByTagName("foodlist")[0].childNodes.length; i++) {
                            var containerChild = document.createElement("div");
                            containerChild.classList.add('container');
                            for (let j = 0; j < xmlDoc.getElementsByTagName("food")[i].childNodes.length; j++) {
                                var tmpChildNode = xmlDoc.getElementsByTagName("food")[i].childNodes[j].nodeName;
                                var tmpChildValue = xmlDoc.getElementsByTagName("food")[i].childNodes[j];
                                switch (tmpChildNode) {
                                    case 'name':
                                        divName = document.createElement("div");
                                        divName.classList.add("name");
                                        name = tmpChildValue.innerHTML;
                                        break;
                                    case 'imageUrl':
                                        imageUrl = document.createElement("img");
                                        imageUrl.src = tmpChildValue.innerHTML;
                                        break;
                                    case 'description':
                                        description = document.createElement("div");
                                        description.classList.add("description");
                                        description.innerHTML = tmpChildValue.innerHTML;
                                        break;
                                    case 'foodId':
                                        href = "ProductDetailController?foodId=" + tmpChildValue.innerHTML;
                                        break;
                                }
                            }
                            var a = document.createElement("a");
                            a.href = href;
                            a.innerHTML = name;
                            divName.appendChild(a);
                            var divDetail = document.createElement("div");
                            divDetail.classList.add("detail");
                            divDetail.appendChild(divName);
                            divDetail.appendChild(description);
                            var divChildContainer = document.createElement("div");
                            divChildContainer.classList.add("container-child");
                            divChildContainer.appendChild(imageUrl);
                            divChildContainer.appendChild(divDetail);
                            var container = document.getElementById("container");
                            container.appendChild(divChildContainer);
                        }
                    }
                    document.getElementsByClassName("loader")[0].classList.add('loader-hidden');
                }
                document.getElementById("offset").innerHTML = parseInt(document.getElementById("offset").innerHTML) + 10;
            }, 2000);
        });
    </script>
</html>