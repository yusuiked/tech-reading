<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>顧客詳細画面</title>
</head>
<body>
<h1>顧客詳細画面</h1>
<dl>
    <dt>名前</dt>
    <dd><c:out value="${customer.name}"/></dd>
    <dt>住所</dt>
    <dd><c:out value="${customer.address}"/></dd>
    <dt>Eメールアドレス</dt>
    <dd><c:out value="${customer.emailAddress}"/></dd>
</dl>
<c:url value="/customer" var="url"/>
<a href="${url}">一覧</a>
</body>
</html>
