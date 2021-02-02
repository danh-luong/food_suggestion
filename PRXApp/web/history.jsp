<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>History</title>
        <link rel="stylesheet" href="./css/history.css"/>
        <link rel="stylesheet" href="./css/header.css"/>
        <link rel="stylesheet" href="./font-awesome-4.7.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <table id="table">
            <thead>
            <th class="table-header">Date</th>
            <th class="table-header">Weight(kg)</th>
            <th class="table-header">Height(cm)</th>
            <th class="table-header">Activity</th>
            <th class="table-header">Calories</th>
        </thead>
        <tbody id="tbody">
            <c:forEach var="ele" items="${requestScope.LIST}">
                <tr class="table-row">
                    <td class="table-data">${ele.dateCreated}</td>
                    <td class="table-data">${ele.weight}</td>
                    <td class="table-data">${ele.height}</td>
                    <td class="table-data">${ele.activity}</td>
                    <td class="table-data">${ele.calories}</td>
                    <td class="table-data remove">
                        <a href="${ele.historyId}" class="href-remove">
                            <i class="fa fa-trash" aria-hidden="true"></i>    
                        </a> 
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <script>
        var multipleRemove = document.getElementsByClassName('href-remove');
        for (var i = 0; i < multipleRemove.length; i++) {
            multipleRemove[i].addEventListener('click', function (e) {
                e.preventDefault();
                var containerChild = document.getElementsByClassName('table-row');
                let element = containerChild.length - 1;
                while (element >= 0) {
                    containerChild[element].remove();
                    element--;
                }
                var arrHistoryId = this.href.split('/');
                var historyId = arrHistoryId[arrHistoryId.length - 1];
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'RemoveHistoryController');
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send("historyId=" + historyId);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4) {
                        var dataText = xhr.responseText;
                        dataText.replace(/^\s+|\s+$/g, '');
                        var parser = new DOMParser();
                        var xmlDoc = parser.parseFromString(dataText, 'text/xml');
                        var date;
                        var weight;
                        var height;
                        var activity;
                        var calories;
                        var href;
                        for (let i = 0; i < xmlDoc.getElementsByTagName("historyOfUserListDTO")[0].childNodes.length; i++) {
                            tableRow = document.createElement('tr');
                            tableRow.classList.add('table-row')
                            for (let j = 0; j < xmlDoc.getElementsByTagName('list')[i].childNodes.length; j++) {
                                var tmpChildNode = xmlDoc.getElementsByTagName('list')[i].childNodes[j].nodeName;
                                var tmpChildValue = xmlDoc.getElementsByTagName('list')[i].childNodes[j];
                                switch (tmpChildNode) {
                                    case 'activity':
                                        activity = document.createElement('td');
                                        activity.classList.add('table-data');
                                        activity.innerHTML = tmpChildValue.innerHTML;
                                        break;
                                    case 'calories':
                                        calories = document.createElement('td');
                                        calories.classList.add('table-data');
                                        calories.innerHTML = tmpChildValue.innerHTML;
                                        break;
                                    case 'dateCreated':
                                        date = document.createElement('td');
                                        date.classList.add('table-data');
                                        date.innerHTML = tmpChildValue.innerHTML;
                                        break;
                                    case 'height':
                                        height = document.createElement('td');
                                        height.classList.add('table-data');
                                        height.innerHTML = tmpChildValue.innerHTML;
                                        break;
                                    case 'historyId':
                                        href = tmpChildValue.innerHTML;
                                        break;
                                    case 'weight':
                                        weight = document.createElement('td');
                                        weight.classList.add('table-data');
                                        weight.innerHTML = tmpChildValue.innerHTML;
                                        break;
                                }
                            }
                            tableRow.appendChild(date);
                            tableRow.appendChild(weight);
                            tableRow.appendChild(height);
                            tableRow.appendChild(activity);
                            tableRow.appendChild(calories);
                            var icon = document.createElement('i');
                            icon.classList.add('fa');
                            icon.classList.add('fa-trash');
                            icon.setAttribute('aria-hidden', 'true');
                            var removeColumn = document.createElement('td');
                            removeColumn.classList.add('table-data');
                            var a = document.createElement('a');
                            a.href = href;
                            a.classList.add('href-remove');
                            a.appendChild(icon);
                            removeColumn.append(a);
                            tableRow.appendChild(removeColumn);
                            document.getElementById('tbody').appendChild(tableRow);
                        }
                        reRun();
                    }
                }
            });
        }
        var reRun = function () {
            for (var i = 0; i < multipleRemove.length; i++) {
                multipleRemove[i].addEventListener('click', function (e) {
                    e.preventDefault();
                    var containerChild = document.getElementsByClassName('table-row');
                    let element = containerChild.length - 1;
                    while (element >= 0) {
                        containerChild[element].remove();
                        element--;
                    }
                    var arrHistoryId = this.href.split('/');
                    var historyId = arrHistoryId[arrHistoryId.length - 1];
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', 'RemoveHistoryController');
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.send("historyId=" + historyId);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState == 4) {
                            var dataText = xhr.responseText;
                            dataText.replace(/^\s+|\s+$/g, '');
                            var parser = new DOMParser();
                            var xmlDoc = parser.parseFromString(dataText, 'text/xml');
                            var date;
                            var weight;
                            var height;
                            var activity;
                            var calories;
                            var href;
                            for (let i = 0; i < xmlDoc.getElementsByTagName("historyOfUserListDTO")[0].childNodes.length; i++) {
                                tableRow = document.createElement('tr');
                                tableRow.classList.add('table-row')
                                for (let j = 0; j < xmlDoc.getElementsByTagName('list')[i].childNodes.length; j++) {
                                    var tmpChildNode = xmlDoc.getElementsByTagName('list')[i].childNodes[j].nodeName;
                                    var tmpChildValue = xmlDoc.getElementsByTagName('list')[i].childNodes[j];
                                    switch (tmpChildNode) {
                                        case 'activity':
                                            activity = document.createElement('td');
                                            activity.classList.add('table-data');
                                            activity.innerHTML = tmpChildValue.innerHTML;
                                            break;
                                        case 'calories':
                                            calories = document.createElement('td');
                                            calories.classList.add('table-data');
                                            calories.innerHTML = tmpChildValue.innerHTML;
                                            break;
                                        case 'dateCreated':
                                            date = document.createElement('td');
                                            date.classList.add('table-data');
                                            date.innerHTML = tmpChildValue.innerHTML;
                                            break;
                                        case 'height':
                                            height = document.createElement('td');
                                            height.classList.add('table-data');
                                            height.innerHTML = tmpChildValue.innerHTML;
                                            break;
                                        case 'historyId':
                                            href = tmpChildValue.innerHTML;
                                            break;
                                        case 'weight':
                                            weight = document.createElement('td');
                                            weight.classList.add('table-data');
                                            weight.innerHTML = tmpChildValue.innerHTML;
                                            break;
                                    }
                                }
                                tableRow.appendChild(date);
                                tableRow.appendChild(weight);
                                tableRow.appendChild(height);
                                tableRow.appendChild(activity);
                                tableRow.appendChild(calories);
                                var icon = document.createElement('i');
                                icon.classList.add('fa');
                                icon.classList.add('fa-trash');
                                icon.setAttribute('aria-hidden', 'true');
                                var removeColumn = document.createElement('td');
                                removeColumn.classList.add('table-data');
                                var a = document.createElement('a');
                                a.href = href;
                                a.classList.add('href-remove');
                                a.appendChild(icon);
                                removeColumn.append(a);
                                tableRow.appendChild(removeColumn);
                                document.getElementById('tbody').appendChild(tableRow);
                            }
                        }
                    }
                });
            }
        }
    </script>
</body>
</html>