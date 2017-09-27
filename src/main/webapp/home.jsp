<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="users" scope="request" class="java.util.ArrayList"/>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/bootstrap.css" />"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/style.css" />"/>
    <title>Hello !</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <c:choose>
                <c:when test="${sessionScope.user ne null}">
                    <h1>Welcome ${sessionScope.user.login}</h1>
                    <div class="row">
                        <c:forEach items="${users}" var="user">
                            <div class="col-md-3">
                                <div class="card" style="padding: 5px; margin: 5px">
                                    <img class="card-img-top"
                                         src="static/img/how-to-draw-my-little-pony-step-by-step.jpg"
                                         alt="Card image cap">
                                    <div class="card-block">
                                        <h4 class="card-title">${user.name} ${user.lastname}</h4>
                                        <p class="card-text">${user.description}</p>
                                        <form action="User" method="get">
                                            <input type="hidden" name="userId" value="${user.id}">
                                            <input class="btn btn-primary" type="submit">
                                        </form>
                                    </div>
                                    <div class="card-footer text-muted">
                                        ${user.compatibility}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:redirect url="Login">Redirect...</c:redirect>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
