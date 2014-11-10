<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>確認画面</title>
</head>
<body>
<h1>確認画面</h1>
<form method="post">
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
    <button type="submit" name="_event_confirmed">更新</button>
    <button type="submit" name="_evnet_revise">再入力</button>
</form>
</body>
</html>
