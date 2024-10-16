<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
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
        <h2 class="text-center mb-4"><fmt:message key="page.login.button" /></h2>
        <div class="card">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="email"><fmt:message key="page.login.email" />:</label>
                         <input type="text" class="form-control" name="email" id="email" value="${param.email}" required>
                     </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="page.login.password" />:</label>
                        <input type="password" class="form-control" name="password" id="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block mb-2"><fmt:message key="page.login.submit.button" /></button>
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <span><fmt:message key="page.login.error" /></span>
                        </div>
                    </c:if>
                </form>
                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/registration" class="btn btn-link"><fmt:message key="page.registration.button" /></a>
                </div>
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
