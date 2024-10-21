<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Films</title>
</head>
<body>
<%@ include file="includs/header.jsp"%>

<h2>Добавить директора:</h2>
<form action="${pageContext.request.contextPath}/add-director" method="post">
    <input type="text" name="fullName" placeholder="Полное имя" required />
    <input type="date" name="birthDate" required />
    <button type="submit">Добавить</button>
</form>

<h1>Список актёров:</h1>

<div class="row">
    <c:forEach var="director" items="${requestScope.directors}">
        <div class="col-md-4">
            <div class="card film-card">
                <div class="card-body">
                    <h2 class="card-title">${director.fullName}</h2>
                    <h5 class="card-subtitle mb-2 text-muted">${director.birthDate}</h5>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<%@ include file="includs/footer.jsp" %>
</body>
</html>