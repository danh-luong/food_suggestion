<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Favorite</title>
        <link rel="stylesheet" href="./css/header.css"/>
        <link rel="stylesheet" href="./css/myFavorite.css"/>
        <link rel="stylesheet" href="./font-awesome-4.7.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="search">
            <input type="text" placeholder="Search Your Favorite Food..." id="search-input"/>
            <i class="fa fa-search" aria-hidden="true"></i>
        </div>
        <div id="container">
            <c:forEach var="food" items="${requestScope.LISTFOOD}">
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
    </body>
    <script>
        var content = "${requestScope.LISTFOODXML}";
        window.localStorage.setItem('xmlFile', content);
        document.getElementById('search-input').addEventListener('input', function () {
            var searchValue = document.getElementById('search-input').value;
            var containerChild = document.getElementsByClassName('container-child');
            let element = containerChild.length - 1;
            if (element >= 0) {
                while (element >= 0) {
                    containerChild[element].remove();
                    element--;
                }
            }
            var dataText = window.localStorage.getItem('xmlFile');
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
                            href = "ProductDetailController/?foodId=" + tmpChildValue.innerHTML;
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
                var tmpName = name.toLowerCase();
                if (tmpName.indexOf(searchValue) != -1) {
                    divChildContainer.classList.add("container-child");
                    divChildContainer.appendChild(imageUrl);
                    divChildContainer.appendChild(divDetail);
                    var container = document.getElementById("container");
                    container.appendChild(divChildContainer);
                }
            }
        });
    </script>
</html>