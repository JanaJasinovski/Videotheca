<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Видеотека</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <c:if test="${not (pageContext.request.requestURI.contains('/login') || pageContext.request.requestURI.contains('/registration'))}">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/films">Фильмы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/actors">Актёры</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/directors">Режиссёры</a>
                    </li>
                </ul>
            </c:if>
            <div class="form-inline">
                <c:if test="${not empty sessionScope.user}">
                    <div class="form-group mr-2">
                        <form action="${pageContext.request.contextPath}/logout" method="post">
                            <button type="submit" class="btn btn-outline-light">Logout</button>
                        </form>
                    </div>
                </c:if>
                <div class="form-group">
                    <form action="${pageContext.request.contextPath}/locale" method="post">
                        <button type="submit" name="lang" value="ru_RU" class="btn btn-outline-light">RU</button>
                        <button type="submit" name="lang" value="en_US" class="btn btn-outline-light">EN</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</nav>

<fmt:setLocale value="${sessionScope.lang != null
                            ? sessionScope.lang
                            : (param.lang != null ? param.lang : 'en_US')}"/>
<fmt:setBundle basename="translations" />
