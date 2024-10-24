<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Добавить отзыв</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .container {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        footer {
            text-align: center;
            padding: 10px 0;
        }
    </style>
</head>
<body>
<%@ include file="includs/header.jsp" %>

<div class="container">
    <div>
        <h2 class="text-center mb-4">Добавить отзыв</h2>
        <div class="card">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/addReview" method="post">
                    <input type="hidden" name="filmId" value="${filmId}"/>
                    <div class="form-group">
                        <label for="rating">Рейтинг:</label>
                        <input type="number" class="form-control" name="rating" id="rating" min="1" max="5" required/>
                    </div>
                    <div class="form-group">
                        <label for="text">Отзыв:</label>
                        <textarea class="form-control" name="text" id="text" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block mb-2">Добавить отзыв</button>
                    <c:if test="${not empty param.message}">
                        <div class="alert alert-success">
                            <span>${param.message}</span>
                        </div>
                    </c:if>
                    <c:if test="${not empty param.error}">
                        <div class="alert alert-danger">
                            <span>${param.error}</span>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="includs/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
