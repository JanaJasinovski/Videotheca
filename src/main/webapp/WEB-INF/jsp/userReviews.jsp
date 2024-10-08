<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Отзывы пользователя</title>
</head>
<body>
<%@ include file="includs/header.jsp"%>
<h1>Отзывы пользователя:</h1>

<ul>
    <c:forEach var="review" items="${requestScope.reviews}">
        <li>
            <h2>Рейтинг: ${review.rating}</h2>
            <h3>${review.text}</h3>
        </li>
    </c:forEach>
</ul>

<%@ include file="includs/footer.jsp" %>
</body>
</html>
