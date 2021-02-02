<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Result Page</title>
        <link rel="stylesheet" href="./css/header.css"/>
        <link rel="stylesheet" href="./css/result.css"/>
        <link rel="stylesheet" href="./css/loading.css"/>
        <link rel="stylesheet" href="./font-awesome-4.7.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="result">Result</div>
        <div id="content">
            <div class="chid-content">
                <div class="child weight" id="maintain">Maintain weight</div>
                <div class="arrow"></div>
                <div class="child calories">
                    <span class="calories-number">${requestScope.CALODAY}</span> <span class="percent">100%</span> <span class="calories-day">Calories/day</span>
                </div>
                <div class="arrow caloriesArrow"></div>
                <div class="child day" id="maintain-day">
                    <span class="calories-number" id="calo-maintain">${requestScope.CALOMEAL}</span> <span class="calories-day">Calories/meal</span>
                </div>
            </div>
            <div class="chid-content">
                <div class="child weight"><span class="title">Weight loss</span> <span class="title-child">0.5 kg/week</span></div>
                <div class="arrow"></div>
                <div class="child calories">
                    <span class="calories-number" id="calories-mild-day">${requestScope.CALODAY50}</span> <span class="percent">${requestScope.PERCENT50}%</span> <span class="calories-day">Calories/day</span>
                </div>
                <div class="arrow caloriesArrow"></div>
                <div class="child day" id="middle-lose-weight-day">
                    <span class="calories-number" id="calo-middle">${requestScope.CALOMEAL50}</span> <span class="calories-day">Calories/meal</span>
                </div>
            </div>
            <div class="chid-content">
                <div class="child weight"><span class="title">Extreme weight loss</span><span class="title-child">1 kg/week</span></div>
                <div class="arrow"></div>
                <div class="child calories">
                    <span class="calories-number" id="calories-extremely-day">${requestScope.CALODAY1}</span> <span class="percent">${requestScope.PERCENT1}%</span> <span class="calories-day">Calories/day</span>
                </div>
                <div class="arrow caloriesArrow"></div>
                <div class="child day" id="extremely-lose-weight">
                    <span class="calories-number" id="calo-extreme">${requestScope.CALOMEAL1}</span> <span class="calories-day">Calories/meal</span>
                </div>
            </div>
            <div id='notice' class="notice-gone">
                <div>Please read here first!</div>
            </div>
            <div id="parent-note"><span id="note">Note:</span> Base on your information that you provided. ${requestScope.ADVISE}</div>
        </div>
        <hr/>
        <h2 id="title-suggestion">${requestScope.TITLE}</h2>
        <div id="search">
            <input type="text" placeholder="Search Your Favorite Food..." id="input-search"/>
            <i class="fa fa-search" aria-hidden="true"></i>
        </div>
        <div id="advance-search-button">
            <button class="btn-search advance-search-button">
                <b id='advance-search-title'>Show More</b>&nbsp;<i class="fa fa-angle-double-up"></i>
            </button>
        </div>
        <div id='advance-search-board' class='advance-search advance-search-board-close'>
            <div>
                <input type="number" placeholder="Addition calories" id="addition-calories"/>
            </div>
            <div class="drop-down-category">
                <select name="category" class="category">
                    <option value="default" selected>Category</option>
                    <c:forEach var="category" items="${requestScope.CATEGORY}">
                        <option value="${category.categoryId}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="drop-down-category">
                <select name="category" class="category">
                    <option value="Whatever" selected>Whatever</option>
                    <option value="Easy">Easy</option>
                    <option value="Be">A little bit effort</option>
                </select>
            </div>
        </div>
        <!--        <div id="drop-down-category">
                    <select name="category" id="category">
                        <option value="default" selected>Food</option>
                        <option value="set">Set Of Foods Suggestion</option>
                    </select>
                </div>-->
        <div class="loader loader-hidden">Loading...</div>
        <div id="container">
            <c:forEach var="food" items="${requestScope.LISTPRODUCT}">
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
        <div id="loadMore">LOAD MORE</div>
        <div id='hidden-layout' class='hidden-backdrop-gone'>&nbsp;</div>
        <div id='content-zigzag' class='content-zigzag-animation'>
            <div id='title-content-zigzag'>
                <h1>Why I see this notice?</h1>
            </div>
            <span class='notice-description'>Please notice before you choose this option! As a general rule, people need a minimum of 1,200 calories daily to stay healthy.  If you have reduced your calorie intake below 1,200 calories a day, you could be hurting your body in addition to your weight-loss plans. Please have advise for doctor to weight if you want to lose too much weight!</span><br/>
            <div id='container-accept-button'>
                <button id="accept-button">I understand What I have read</button>
            </div>
        </div>
        <div id="offset">${requestScope.OFFSET}</div>
        <div id="current-tag">Maintain</div>
        <div id="title-search">maintain</div>

    </body>
    <script>
        document.getElementById('advance-search-button').addEventListener('click', function () {
            var advanceSearchTitle = document.getElementById('advance-search-title');
            var searchBoard = document.getElementById('advance-search-board');
            if (advanceSearchTitle.innerHTML.indexOf('More') != -1) {
                advanceSearchTitle.innerHTML = 'Show Less';
                var iconSearchAdvance = document.getElementsByClassName('fa-angle-double-up')[0];
                iconSearchAdvance.classList.remove('fa-angle-double-up');
                iconSearchAdvance.classList.add('fa-angle-double-down');
                searchBoard.classList.remove('advance-search-board-close');
                searchBoard.classList.add('advance-search-board-show');

            } else {
                advanceSearchTitle.innerHTML = 'Show More';
                var iconSearchAdvance = document.getElementsByClassName('fa-angle-double-down')[0];
                iconSearchAdvance.classList.remove('fa-angle-double-down');
                iconSearchAdvance.classList.add('fa-angle-double-up');
                searchBoard.classList.remove('advance-search-board-show');
                searchBoard.classList.add('advance-search-board-close');
            }
        });

        var content = "${requestScope.MAINTAIN}";
        window.localStorage.setItem('xmlFile', content);

        document.getElementById('input-search').addEventListener('input', function (e) {
            var titleSearch = document.getElementById('title-search').innerHTML;
            var valueCalo;
            switch (titleSearch) {
                case 'maintain':
                    valueCalo = document.getElementById('calo-maintain').innerHTML;
                    break;
                case 'middle':
                    valueCalo = document.getElementById('calo-middle').innerHTML;
                    break;
                case 'extreme':
                    valueCalo = document.getElementById('calo-extreme').innerHTML;
                    break;
            }

            var containerChild = document.getElementsByClassName('container-child');
            let element = containerChild.length - 1;
            while (element >= 0) {
                containerChild[element].remove();
                element--;
            }
            document.getElementsByClassName("loader")[0].classList.remove('loader-hidden');
            setTimeout(function () {
                document.getElementById("offset").innerHTML = 0;
//                var category = document.getElementById('category');
//                var selectedValue = category.options[category.selectedIndex].value;
                var searchValue = document.getElementById('input-search').value;
                var offset = document.getElementById("offset").innerHTML;
                var xhr = new XMLHttpRequest();
                var advanceSearch = document.getElementById('advance-search-title').innerHTML;

                if (advanceSearch.indexOf('More') != -1) {
                    xhr.open('POST', 'SearchResultController');
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + valueCalo);
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
                } else {
                    var category = document.getElementsByClassName('category')[0];
                    var categoryId = category.options[category.selectedIndex].value;

                    var level = document.getElementsByClassName('category')[1];
                    var levelId = level.options[level.selectedIndex].value;

                    var additionCalories = document.getElementById("addition-calories").value;

                    if (parseInt(additionCalories) < 0 && !isNaN(additionCalories)) {
                        additionCalories = additionCalories.split('-')[0].trim();
                    } else if (parseInt(additionCalories) > 0 && !isNaN(additionCalories)) {
                        additionCalories = parseInt(additionCalories);
                    } else {
                        additionCalories = 0;
                    }

                    var tmpCalo = valueCalo - additionCalories;

                    xhr.open('POST', 'SearchAdvanceResultController');
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    if (tmpCalo >= 0) {
                        xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + tmpCalo + "&" + "categoryId=" + categoryId + "&" + "level=" + levelId);
                    } else {
                        xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + valueCalo + "&" + "categoryId=" + categoryId + "&" + "level=" + levelId);
                    }

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
                }
                document.getElementById("offset").innerHTML = parseInt(document.getElementById("offset").innerHTML) + 10;
            }, 2000);
        });

        document.getElementById('loadMore').addEventListener('click', function () {
            var titleSearch = document.getElementById('title-search').innerHTML;
            var valueCalo;
            switch (titleSearch) {
                case 'maintain':
                    valueCalo = document.getElementById('calo-maintain').innerHTML;
                    break;
                case 'middle':
                    valueCalo = document.getElementById('calo-middle').innerHTML;
                    break;
                case 'extreme':
                    valueCalo = document.getElementById('calo-extreme').innerHTML;
                    break;
            }
            var offset = document.getElementById('offset').innerHTML;
//            var searchValue = document.getElementById('input-search').value;
//            var selectedValue = category.options[category.selectedIndex].value;
            var xhr = new XMLHttpRequest();
            var searchValue = document.getElementById('input-search').value;
            var advanceSearch = document.getElementById('advance-search-title').innerHTML;

            if (advanceSearch.indexOf('More') != -1) {
                xhr.open('POST', 'SearchResultController');
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + valueCalo);
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
            } else {
                var category = document.getElementsByClassName('category')[0];
                var categoryId = category.options[category.selectedIndex].value;

                var level = document.getElementsByClassName('category')[1];
                var levelId = level.options[level.selectedIndex].value;

                var additionCalories = document.getElementById("addition-calories").value;

                if (parseInt(additionCalories) < 0 && !isNaN(additionCalories)) {
                    additionCalories = additionCalories.split('-')[0].trim();
                } else if (parseInt(additionCalories) > 0 && !isNaN(additionCalories)) {
                    additionCalories = parseInt(additionCalories);
                } else {
                    additionCalories = 0;
                }

                var tmpCalo = valueCalo - additionCalories;

                xhr.open('POST', 'SearchAdvanceResultController');
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                if (tmpCalo >= 0) {
                    xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + tmpCalo + "&" + "categoryId=" + categoryId + "&" + "level=" + levelId);
                } else {
                    xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + valueCalo + "&" + "categoryId=" + categoryId + "&" + "level=" + levelId);
                }
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
            }
            document.getElementById("offset").innerHTML = parseInt(document.getElementById("offset").innerHTML) + 10;
        });

        document.getElementById('maintain-day').addEventListener('click', function () {
            document.getElementsByClassName('category')[0].selectedIndex = '0';
            document.getElementsByClassName('category')[1].selectedIndex = '0';
            document.getElementById("addition-calories").value = '';
            
            document.getElementById('notice').classList.add('notice-gone');

            document.getElementById('title-search').innerHTML = 'maintain';
            document.getElementById('title-suggestion').innerHTML = 'Maintain Weight Suggestion Foods For 3 main meals';
            var titleSearch = document.getElementById('title-search').innerHTML;
            switch (titleSearch) {
                case 'maintain':
                    valueCalo = document.getElementById('calo-maintain').innerHTML;
                    break;
                case 'middle':
                    valueCalo = document.getElementById('calo-middle').innerHTML;
                    break;
                case 'extreme':
                    valueCalo = document.getElementById('calo-extreme').innerHTML;
                    break;
            }
            var containerChild = document.getElementsByClassName('container-child');
            let element = containerChild.length - 1;
            while (element >= 0) {
                containerChild[element].remove();
                element--;
            }
            document.getElementsByClassName("loader")[0].classList.remove('loader-hidden');
            setTimeout(function () {
                document.getElementById("offset").innerHTML = 0;
                var searchValue = document.getElementById('input-search').value;
                var offset = document.getElementById("offset").innerHTML;
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'SearchResultController');
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + valueCalo);
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

        document.getElementById('middle-lose-weight-day').addEventListener('click', function () {
            document.getElementsByClassName('category')[0].selectedIndex = '0';
            document.getElementsByClassName('category')[1].selectedIndex = '0';
            document.getElementById("addition-calories").value = '';

            var caloriesByDay = document.getElementById('calories-mild-day').innerHTML;
            if (parseInt(caloriesByDay) <= 1200) {
                document.getElementById('notice').classList.remove('notice-gone');
            } else {
                document.getElementById('notice').classList.add('notice-gone');
            }

            document.getElementById('title-search').innerHTML = 'middle';
            document.getElementById('title-suggestion').innerHTML = 'Lose Middle Weight Suggestion Foods For 3 main meals';
            var titleSearch = document.getElementById('title-search').innerHTML;
            switch (titleSearch) {
                case 'maintain':
                    valueCalo = document.getElementById('calo-maintain').innerHTML;
                    break;
                case 'middle':
                    valueCalo = document.getElementById('calo-middle').innerHTML;
                    break;
                case 'extreme':
                    valueCalo = document.getElementById('calo-extreme').innerHTML;
                    break;
            }
            var containerChild = document.getElementsByClassName('container-child');
            let element = containerChild.length - 1;
            while (element >= 0) {
                containerChild[element].remove();
                element--;
            }
            document.getElementsByClassName("loader")[0].classList.remove('loader-hidden');
            setTimeout(function () {
                document.getElementById("offset").innerHTML = 0;
                var searchValue = document.getElementById('input-search').value;
                var offset = document.getElementById("offset").innerHTML;
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'SearchResultController');
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + valueCalo);
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

        document.getElementById('extremely-lose-weight').addEventListener('click', function () {
            document.getElementsByClassName('category')[0].selectedIndex = '0';
            document.getElementsByClassName('category')[1].selectedIndex = '0';
            document.getElementById("addition-calories").value = '';

            var caloriesByDay = document.getElementById('calories-extremely-day').innerHTML;
            if (parseInt(caloriesByDay) <= 1200) {
                document.getElementById('notice').classList.remove('notice-gone');
            } else {
                document.getElementById('notice').classList.add('notice-gone');
            }

            document.getElementById('title-search').innerHTML = 'extreme';
            document.getElementById('title-suggestion').innerHTML = 'Lose Extremely Weight Suggestion Foods For 3 main meals';
            var titleSearch = document.getElementById('title-search').innerHTML;
            switch (titleSearch) {
                case 'maintain':
                    valueCalo = document.getElementById('calo-maintain').innerHTML;
                    break;
                case 'middle':
                    valueCalo = document.getElementById('calo-middle').innerHTML;
                    break;
                case 'extreme':
                    valueCalo = document.getElementById('calo-extreme').innerHTML;
                    break;
            }
            var containerChild = document.getElementsByClassName('container-child');
            let element = containerChild.length - 1;
            while (element >= 0) {
                containerChild[element].remove();
                element--;
            }
            document.getElementsByClassName("loader")[0].classList.remove('loader-hidden');
            setTimeout(function () {
                document.getElementById("offset").innerHTML = 0;
                var searchValue = document.getElementById('input-search').value;
                var offset = document.getElementById("offset").innerHTML;
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'SearchResultController');
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("inputSearch=" + searchValue + "&" + "offset=" + offset + "&" + "calo=" + valueCalo);
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

        document.getElementById('accept-button').addEventListener('click', function () {
            document.getElementById('hidden-layout').classList.add('hidden-backdrop-gone');
            document.getElementById('content-zigzag').classList.add('content-zigzag-animation');
        });

        document.getElementById('notice').addEventListener('click', function () {
            document.getElementById('hidden-layout').classList.remove('hidden-backdrop-gone');
            document.getElementById('content-zigzag').classList.remove('content-zigzag-animation');
        });

//        document.getElementById('category').addEventListener('change', function () {
//            var titleSearch = document.getElementById('title-search').innerHTML;
//            var valueCalo;
//            switch (titleSearch) {
//                case 'maintain':
//                    valueCalo = document.getElementById('calo-maintain').innerHTML;
//                    break;
//                case 'middle':
//                    valueCalo = document.getElementById('calo-middle').innerHTML;
//                    break;
//                case 'extreme':
//                    valueCalo = document.getElementById('calo-extreme').innerHTML;
//                    break;
//            }
//            var containerChild = document.getElementsByClassName('container-child');
//            let element = containerChild.length - 1;
//            while (element >= 0) {
//                containerChild[element].remove();
//                element--;
//            }
//            document.getElementsByClassName("loader")[0].classList.remove('loader-hidden');
//            document.getElementById('input-search').value = '';
//            setTimeout(function () {
//                document.getElementById("offset").innerHTML = 0;
//                var category = document.getElementById('category');
//                var selectedValue = category.options[category.selectedIndex].value;
//                var offset = document.getElementById("offset").innerHTML;
//                if (selectedValue == 'default') {
//                    var xhr = new XMLHttpRequest();
//                    xhr.open('POST', 'SearchResultController');
//                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//                    xhr.send("inputSearch=" + "" + "&" + "offset=" + offset + "&" + "calo=" + valueCalo);
//                    xhr.onreadystatechange = function () {
//                        var containerChild = document.getElementsByClassName('container-child');
//                        let element = containerChild.length - 1;
//                        while (element >= 0) {
//                            containerChild[element].remove();
//                            element--;
//                        }
//                        var containerChild = document.getElementsByClassName('container-child');
//                        for (var i = 0; i < containerChild.length; i++) {
//                            containerChild[i].remove();
//                        }
//                        if (xhr.readyState == 4) {
//                            var dataText = xhr.responseText;
//                            var parser = new DOMParser();
//                            var xmlDoc = parser.parseFromString(dataText, 'text/xml');
//                            var divName;
//                            var imageUrl;
//                            var description;
//                            var href;
//                            var name;
//                            for (let i = 0; i < xmlDoc.getElementsByTagName("foodlist")[0].childNodes.length; i++) {
//                                var containerChild = document.createElement("div");
//                                containerChild.classList.add('container');
//                                for (let j = 0; j < xmlDoc.getElementsByTagName("food")[i].childNodes.length; j++) {
//                                    var tmpChildNode = xmlDoc.getElementsByTagName("food")[i].childNodes[j].nodeName;
//                                    var tmpChildValue = xmlDoc.getElementsByTagName("food")[i].childNodes[j];
//                                    switch (tmpChildNode) {
//                                        case 'name':
//                                            divName = document.createElement("div");
//                                            divName.classList.add("name");
//                                            name = tmpChildValue.innerHTML;
//                                            break;
//                                        case 'imageUrl':
//                                            imageUrl = document.createElement("img");
//                                            imageUrl.src = tmpChildValue.innerHTML;
//                                            break;
//                                        case 'description':
//                                            description = document.createElement("div");
//                                            description.classList.add("description");
//                                            description.innerHTML = tmpChildValue.innerHTML;
//                                            break;
//                                        case 'foodId':
//                                            href = "ProductDetailController?foodId=" + tmpChildValue.innerHTML;
//                                            break;
//                                    }
//                                }
//                                var a = document.createElement("a");
//                                a.href = href;
//                                a.innerHTML = name;
//                                divName.appendChild(a);
//                                var divDetail = document.createElement("div");
//                                divDetail.classList.add("detail");
//                                divDetail.appendChild(divName);
//                                divDetail.appendChild(description);
//                                var divChildContainer = document.createElement("div");
//                                divChildContainer.classList.add("container-child");
//                                divChildContainer.appendChild(imageUrl);
//                                divChildContainer.appendChild(divDetail);
//                                var container = document.getElementById("container");
//                                container.appendChild(divChildContainer);
//                            }
//                        }
//                        document.getElementsByClassName("loader")[0].classList.add('loader-hidden');
//                    }
//                    document.getElementById("offset").innerHTML = parseInt(document.getElementById("offset").innerHTML) + 10;
//                } else {
//                    
//                }
//            }, 2000);
//        });
    </script>
</html>