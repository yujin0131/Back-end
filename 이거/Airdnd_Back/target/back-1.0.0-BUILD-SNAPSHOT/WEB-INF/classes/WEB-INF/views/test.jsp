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
	<c:forEach var="vo" items="${vo}">
		<tr>
			<td>방이름</td>
			<td>가격</td>
			<td>별점</td>
			<td>리뷰개수</td>
			<td>방유형</td>
			<td>옵션</td>
		</tr>
		<tr>
			<td>${vo.room_name}</td>
			<td>${vo.room_price}</td>
			<td>${vo.room_score}</td>
			<td>${vo.room_review_num}</td>
			<td>${vo.room_type}</td>
			<td>${vo.room_option}</td>
		</tr>
	</table>
	
	</c:forEach>
</body>
</html>