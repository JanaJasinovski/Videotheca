<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Films</title>
</head>
<body>
<%@ include file="includs/header.jsp"%>
<h1>Список актёров:</h1>

<div class="row">
    <c:forEach var="actor" items="${requestScope.actors}">
        <div class="col-md-4">
            <div class="card film-card">
                <div class="card-body">
                    <h2 class="card-title">${actor.fullName}</h2>
                    <h5 class="card-subtitle mb-2 text-muted">${actor.birthDate}</h5>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<%@ include file="includs/footer.jsp" %>
</body>
</html>