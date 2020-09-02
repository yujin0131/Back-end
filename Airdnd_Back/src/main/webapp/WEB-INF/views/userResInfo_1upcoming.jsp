<<<<<<< HEAD
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
	<h2>여행 - 예정된 예약</h2>
	이전 예약으로 넘어가는 기준은 체크아웃 날짜 다음 날부터입니다. 정확한 기준을 몰라서 이렇게 설정했어요 ^^)><br>
	
	<input type="button" value="예정된 예약" onclick="#">
	<input type="button" value="이전 예약" onclick="location.href='userResInfo_past'">
	<input type="button" value="취소됨" onclick="location.href='userResInfo_canceled'">
	
	<form>
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
			
			<c:forEach var="list1" items="${list1}">
			<tr>
				<td>${list1.idx}</td>
				<td>${list1.user_idx}</td>
				<td>${list1.home_idx}</td>
				<td>${list1.checkin}</td>
				<td>${list1.checkout}</td>
				<td>${list1.guest_idx}</td>
				<td>${list1.is_canceled}</td>
				<td>${list1.url}</td>
			</tr>
			</c:forEach>
		</table>
	</form>
	
	<c:forEach var="list1" items="${list1}">
		<img src="${list1.url}">
	</c:forEach>
</body>
=======
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
	<h2>여행 - 예정된 예약</h2>
	이전 예약으로 넘어가는 기준은 체크아웃 날짜 다음 날부터입니다. 정확한 기준을 몰라서 이렇게 설정했어요 ^^)><br>
	
	<input type="button" value="예정된 예약" onclick="#">
	<input type="button" value="이전 예약" onclick="location.href='userResInfo_past'">
	<input type="button" value="취소됨" onclick="location.href='userResInfo_canceled'">
	
	<form>
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
			
			<c:forEach var="list1" items="${list1}">
			<tr>
				<td>${list1.idx}</td>
				<td>${list1.user_idx}</td>
				<td>${list1.home_idx}</td>
				<td>${list1.checkin}</td>
				<td>${list1.checkout}</td>
				<td>${list1.guest_idx}</td>
				<td>${list1.is_canceled}</td>
				<td>${list1.url}</td>
			</tr>
			</c:forEach>
		</table>
	</form>
	
	<c:forEach var="list1" items="${list1}">
		<img src="${list1.url}">
	</c:forEach>
</body>
>>>>>>> refs/remotes/origin/develop
</html>