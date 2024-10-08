<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Films</title>
</head>
<body>
<%@ include file="includs/header.jsp"%>
<h1>Список актёров, снимавшихся в выбранном фильме:</h1>

<ul>
    <c:forEach var="actor" items="${requestScope.actors}">
        <li>
            <h2>${actor.fullName}</h2>
            <h3>Дата рождения: ${actor.birthDate}</h3>
        </li>

    </c:forEach>
</ul>

<%@ include file="includs/footer.jsp" %>
</body>
</html>