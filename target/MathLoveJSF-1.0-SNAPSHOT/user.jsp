<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" scope="request" class="love.math.business.User"/>
<jsp:useBean id="questions" scope="request" class="java.util.LinkedHashMap"/>
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
                    <h1>You are on ${user.name}'s page</h1>
                    <h2>Compatibility :  ${user.compatibility}</h2>
                    <img src="static/img/how-to-draw-my-little-pony-step-by-step.jpg" style="width: 100%">
                    <div class="row">
                        <c:forEach items="${questions}" var="question">
                            <div class="col-md-12">
                                <h4>${question.key.text}</h4>
                                <h5>${question.value.text}</h5>
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
