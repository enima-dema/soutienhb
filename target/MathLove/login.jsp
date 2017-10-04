<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/bootstrap.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/style.css" />" />
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-6">
            <h1 class="center default-space">Login</h1>
           <%-- <c:choose>
                <c:when test="${fail}">
                    <div class="alert alert-warning">
                        <strong>Bad informations !</strong> Please check your creditentials
                    </div>
                </c:when>
            </c:choose>--%>
            <form action="/login" method="POST" class="form-group default-space">
                <input type="text" class="form-control default-space" placeholder="login" id="login" name="login" value="amed">
                <input type="password" class="form-control default-space" placeholder="password" id="password" name="password" value="test123">
                <button type="submit" class="btn btn-success default-space">Login</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
