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
	${res}
	
	<h2>여행 - 취소된 예약</h2>
	예약 취소 컬럼에 값 1이 있으면 취소된 예약
	
	<form>
		<input type="button" value="예정된 예약" onclick="location.href='userResInfo_upcoming'">
		<input type="button" value="이전 예약" onclick="location.href='userResInfo_past'">
		<input type="button" value="취소됨" onclick="#">
		
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
			
			<c:forEach var="list3" items="${list3}">
			<tr>
				<td>${list3.idx}</td>
				<td>${list3.user_idx}</td>
				<td>${list3.home_idx}</td>
				<td>${list3.checkin}</td>
				<td>${list3.checkout}</td>
				<td>${list3.guest_idx}</td>
				<td>${list3.is_canceled}</td>
				<td>${list3.url}</td>
			</tr>
			</c:forEach>
		</table>
	</form>
	
	<c:forEach var="list3" items="${list3}">
		<img src="${list3.url}">
	</c:forEach>
</body>
</html>