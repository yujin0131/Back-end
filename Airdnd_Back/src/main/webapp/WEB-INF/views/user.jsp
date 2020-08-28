<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
		<tr>
			<td>이메일</td>
			<td>비밀번호</td>
			<td>성</td>
			<td>이름</td>
			<td>생일</td>
			<td>이미지사진</td>
			<td>전화번호</td>
			<td>가입날짜</td>
			<td>부가설명</td>
		</tr>
	<c:forEach var="list" items="${list}">
		<tr>
			<td>${list.email}</td>
			<td>${list.pwd}</td>
			<td>${list.last_name}</td>
			<td>${list.first_name}</td>
			<td>${list.birthday}</td>
			<td>${list.profileImg}</td>
			<td>${list.phone}</td>
			<td>${list.signupDate}</td>
			<td>${list.description}</td>
		</tr>
	</c:forEach>
	</table>

</body>
</html>