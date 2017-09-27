<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" scope="request" class="love.math.business.User"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="text/css" rel="stylesheet" href="<c:url value="/static/css/bootstrap.css" />" />
    <title>Home</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">

        </div>
    </div>
</div>
<h1>Welcome ${user.login}</h1>
<br>
<form action="User" method="GET">
    <input type="submit" value="getUser"/>
</form>
<form action="Login" method="GET">
    <input type="submit" value="Login"/>
</form>
<br>
</body>
</html>
