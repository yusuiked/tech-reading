<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>顧客一覧画面</title>
</head>
<body>
<h2>顧客一覧画面</h2>
<c:if test="${editCustomer != null}">
    以下の顧客が更新されました。
    <dl>
        <dt>名前</dt>
        <dd>
            <c:out value="${editCustomer.name}"/>
        </dd>
        <dt>住所</dt>
        <dd>
            <c:out value="${editCustomer.address}"/>
        </dd>
        <dt>Eメールアドレス</dt>
        <dd>
            <c:out value="${editCustomer.emailAddress}"/>
        </dd>
    </dl>
</c:if>
<table border="1">
    <tr>
        <th>ID</th>
        <th>名前</th>
        <th>住所</th>
        <th>Eメールアドレス</th>
        <th></th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td><c:out value="${customer.id}"/></td>
            <td><c:out value="${customer.name}"/></td>
            <td><c:out value="${customer.address}"/></td>
            <td><c:out value="${customer.emailAddress}"/></td>
            <td>
                <c:url value="/customer/${customer.id}" var="url"/>
                <a href="${url}">詳細</a>
                <c:url value="/customer/${customer.id}/edit" var="url"/>
                <a href="${url}">編集</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
