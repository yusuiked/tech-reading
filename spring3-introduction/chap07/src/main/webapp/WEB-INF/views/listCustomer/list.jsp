<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顧客一覧画面</title>
</head>
<body>
	<h1>顧客一覧画面</h1>
	<c:if test="${editedCustomer != null}">
以下の顧客が更新されました。
<dl>
			<dt>名前</dt>
			<dd>
				<c:out value="${editedCustomer.name}" />
			</dd>
			<dt>住所</dt>
			<dd>
				<c:out value="${editedCustomer.address}" />
			</dd>
			<dt>Eメールアドレス</dt>
			<dd>
				<c:out value="${editedCustomer.emailAddress}" />
			</dd>
		</dl>
	</c:if>
	<form action="${flowExecutionUrl}" method="post">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>住所</th>
				<th>電話番号</th>
				<td></td>
			</tr>
			<c:forEach items="${customerList}" var="customer">
				<tr>
					<td><c:out value="${customer.id}" /></td>
					<td><c:out value="${customer.name}" /></td>
					<td><c:out value="${customer.address}" /></td>
					<td><c:out value="${customer.emailAddress}" /></td>
					<td><a
						href="${flowExecutionUrl}&_eventId=edit&id=${customer.id}"> 更新
					</a></td>
				</tr>
			</c:forEach>
		</table>
		<button type="submit" name="_eventId_list">検索</button>
	</form>
</body>
</html>