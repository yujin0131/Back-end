<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
yujintest

<table border="1">
		<tr>
			<td>방이름</td>
			<td>가격</td>
			<td>별점</td>
			<td>리뷰개수</td>
			<td>방유형</td>
			<td>옵션</td>
		</tr>
	<c:forEach var="list" items="${list}">
		<tr>
			
			<td>${list.room_name}</td>
			<td>${list.room_price}</td>
			<td>${list.room_score}</td>
			<td>${list.room_review_num}</td>
			<td>${list.room_type}</td>
			<td>${list.room_option}</td>
		</tr>
	</c:forEach>
	</table>
	
</body>
</html>