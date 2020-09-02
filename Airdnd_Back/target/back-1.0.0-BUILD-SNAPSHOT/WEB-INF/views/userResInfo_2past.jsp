<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("euc-kr"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<script>

	</script>
</head>
<body>
	<h2>여행 - 이전 예약</h2>
	과거의 유물들... ㅇr름다운 추억...☆★
	
	<form>
		<input type="button" value="예정된 예약" onclick="location.href='userResInfo_upcoming'">
		<input type="button" value="이전 예약" onclick="">
		<input type="button" value="취소됨" onclick="location.href='userResInfo_canceled'">
		
		<table border="1">
			<tr>
				<td>idx</td>
				<td>해당 유저놈 idx</td>
				<td>숙소 idx 대표 이미지 가져올 용도</td>
				<td>췤 인 날짜</td>
				<td>체크아웃</td>
				<td>가취 간 게스트 idx..</td>
				<td>예약, 취소하신?</td>
				<td>메인 사진</td>
			</tr>
			
			<c:forEach var="list2" items="${list2}">
			<tr>
				<td>${list2.idx}</td>
				<td>${list2.user_idx}</td>
				<td>${list2.home_idx}</td>
				<td>${list2.checkin}</td>
				<td>${list2.checkout}</td>
				<td>${list2.guest_idx}</td>
				<td>${list2.is_canceled}</td>
				<td>${list2.url}</td>
			</tr>
			</c:forEach>
		</table>
	</form>
	
	<c:forEach var="list2" items="${list2}">
		<img src="${list2.url}">
	</c:forEach>
</body>
</html>