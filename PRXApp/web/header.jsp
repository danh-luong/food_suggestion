<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="header">
    <a href="HomeController">Home</a>
    <a href="index.jsp">Calculate</a>
    <c:if test="${sessionScope.USER != null}">
        <a href="MyFavoriteController">My Favorite</a>
        <a href="HistoryController">History</a>
        <a href="ProfileController">${USER.firstname} ${USER.lastname}</a>
    </c:if>
    <c:if test="${sessionScope.USER == null}">
        <a href="login.jsp">Login</a>
    </c:if>
</div>
