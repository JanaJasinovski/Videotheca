<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Films</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            overflow-x: hidden; /* Скрыть горизонтальные скроллы */
        }
        .film-card {
            margin: 10px 0; /* Отступы между карточками */
        }
    </style>
</head>
<body>
<%@ include file="includs/header.jsp" %>
<h1>Список фильмов:</h1>

<div class="mb-4">
    <form method="GET" action="${pageContext.request.contextPath}/films" class="d-inline">
        <label for="year">Введите год:</label>
        <input type="number" name="year" id="year" min="1900" max="2100" required>
        <input type="submit" value="Поиск" class="btn btn-primary">
    </form>

    <form method="GET" action="${pageContext.request.contextPath}/films" class="d-inline">
        <label for="actorName">Введите имя актёра:</label>
        <input type="text" name="actorName" id="actorName">
        <input type="submit" value="Поиск по имени актёра" class="btn btn-primary">
    </form>

    <form method="GET" action="${pageContext.request.contextPath}/user-reviews" class="d-inline">
        <label for="userId">Выберите пользователя:</label>
        <select name="userId" id="userId">
            <c:forEach var="user" items="${requestScope.users}">
                <option value="${user.id}">${user.fullName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Просмотреть отзывы" class="btn btn-primary">
    </form>
</div>

<div class="row">
    <c:forEach var="film" items="${requestScope.films}">
        <div class="col-md-4">
            <div class="card film-card">
                <div class="card-body">
                    <h2 class="card-title">${film.name}</h2>
                    <h5 class="card-subtitle mb-2 text-muted">${film.releaseDate}</h5>
                    <h5 class="card-subtitle mb-2 text-muted">${film.country}</h5>
                    <h5 class="card-subtitle mb-2 text-muted">${film.genre}</h5>
                    <form method="GET" action="${pageContext.request.contextPath}/filmActors" class="d-inline">
                        <input type="hidden" name="filmId" value="${film.id}"/>
                        <input type="submit" value="Показать актёров" class="btn btn-secondary btn-sm">
                    </form>
                    <form method="GET" action="${pageContext.request.contextPath}/reviews" class="d-inline">
                        <input type="hidden" name="filmId" value="${film.id}">
                        <input type="submit" value="Просмотреть отзывы" class="btn btn-secondary btn-sm">
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<%@ include file="includs/footer.jsp" %>
</body>
</html>
