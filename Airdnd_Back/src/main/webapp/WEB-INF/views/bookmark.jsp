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
		function insertBookmark() {
			var bookmark_list_title = document.getElementById("bookmark_list_title").value.trim();
			
			location.href = "bookmark_insert?bookmark_list_title=" + bookmark_list_title;
		}
		
		function updateHome(idx) {
			var home_idx = document.getElementById("new_home_idx").value.trim();
			
			location.href = "bookmark_updateHome?idx=" + idx + "&home_idx=" + home_idx;
		}
		
		function deleteHome(idx) {
			location.href = "bookmark_deleteHome?idx=" + idx;
		}
	</script>
</head>
<body>
	<h2>저는 북마크bookmark-입니다. ^^ 남마크 서마크 동마크 ㅋㅋ ㅈㅅ</h2>
	<form>
		<input id="bookmark_list_title">
		<input type="button" value="새 북마크 추가" onclick="insertBookmark();"> <- 북마크 이름 입력<br><br>
		
		<table border="1">
			<tr>
				<td>idx</td>
				<td>해당 유저놈 idx</td>
				<td>숙소 idx</td>
				<td>후기idx(나중에 없앨 수도 있음)</td>
				<td>저장한 이름</td>
				<td>체크인</td>
				<td>체크아웃</td>
				<td>메인 사진</td>
				<td>기존 북마크에 숙소 추가(숙소에 하트 누름)</td>
				<td>숙소 삭제(하트(좋아요) 취소)</td>
				<td>수정 날짜</td>
			</tr>
			
			<c:forEach var="list" items="${list}" varStatus="status">
				<tr>
					<td>${list.idx}</td>
					<td>${list.user_idx}</td>
	
					<c:if test="${list.home_idx eq 0}">
						<td>저장된 항목 없음</td>
					</c:if>
					<c:if test="${list.home_idx ne 0}">
						<td>${list.home_idx}(숙소 1개)</td>
					</c:if>
					
					<td>${list.review_idx}</td>
					<td>${list.bookmark_list_title}</td>
					
					<c:if test="${(list.checkin ne null and list.checkin ne '') and (list.checkout ne null or list.checkout ne '')}">
						<td>${list.checkin}</td>
						<td>${list.checkout}</td>
					</c:if>
					<c:if test="${(list.checkin eq null or list.checkin eq '') and (list.checkout eq null or list.checkout eq '')}">
						<td colspan="2">날짜 상관없음</td>
					</c:if>
					
					<td>${list.url}</td>
					
					<td>
						<c:if test="${list.home_idx eq 0}">
							<input id="new_home_idx"><input type="button" value="추가" onclick="updateHome(${list.idx});"> <- 꼭 DB에 있는 걸로 넣어야 해요
						</c:if>
					</td>
					
					<td>
						<c:if test="${list.home_idx ne 0}">
							<input type="button" value="삭제" onclick="deleteHome(${list.idx});">
						</c:if>
					</td>
					<td>${list.update_date_time}</td>
				</tr>
			</c:forEach>
		</table>
		
		<c:forEach var="list" items="${list}">
			<img src="${list.url}">
		</c:forEach>
	</form>
</body>
</html>