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
			
			var home_idx = document.getElementById("new_home_idx").value.trim();
			
			if(home_idx == null || home_idx == "") {
				location.href = "bookmark_insert?bookmark_list_title=" + bookmark_list_title;
			} else {
				location.href = "bookmark_insert?bookmark_list_title=" + bookmark_list_title + "&home_idx=" + home_idx;
			}
		}
		
		function addHome(idx) {
			var home_idx = document.getElementById("add_home_idx").value.trim();
			
			location.href = "bookmark_insertHome?idx=" + idx + "&home_idx=" + home_idx;
		}
		
		function deleteHome(idx, bookmark_idx) {
			location.href = "bookmark_deleteHome?idx=" + idx + "&bookmark_idx=" + bookmark_idx;
		}
		
		function deleteBookmark(idx) {
			location.href = "bookmark_delete?idx=" + idx;
			
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
	${res}
	<h2>저는 북마크bookmark-입니다. ^^ 남마크 서마크 동마크 ㅋㅋ ㅈㅅ</h2>
	<form>
		<input id="bookmark_list_title" placeholder="북마크 이름(필수)">
		<input id="new_home_idx" placeholder="추가할 숙소 idx(선택)">
		<input type="button" value="새 북마크 추가" onclick="insertBookmark();"> <- 북마크 이름 입력<br><br>
		
		<table border="1">
			<tr>
				<td>idx</td>
				<td>해당 유저놈 idx</td>
				<td>저장한 이름</td>
				<td>체크인</td>
				<td>체크아웃</td>
				<td>숙소 몇 개여?</td>
				<td>수정 날짜</td>
				<td colspan="2">추가</td>
				<td>북마크 삭제</td>
			</tr>
			
			<c:forEach var="list" items="${list}" varStatus="status">
			<tr>
				<td>${list.idx}</td>
				<td>${list.user_idx}</td>
				<td>${list.bookmark_list_title}</td>
				
				<c:if test="${(list.checkin ne null and list.checkin ne '') and (list.checkout ne null or list.checkout ne '')}">
					<td>${list.checkin}</td>
					<td>${list.checkout}</td>
				</c:if>
				<c:if test="${(list.checkin eq null or list.checkin eq '') and (list.checkout eq null or list.checkout eq '')}">
					<td colspan="2">날짜 상관없음</td>
				</c:if>
				<td>${list.home_count}개</td>
				<td>${list.update_date_time}</td>
				<td colspan="2"><input id="add_home_idx"><input type="button" value="추가" onclick="addHome(${list.idx});" /></td>
				<td>
					<input type="button" value="삭제" onclick="deleteBookmark(${list.idx});" />
				</td>
			</tr>
			</c:forEach>
		</table>
		
		<table border="1">
			<c:forEach var="homes" items="${homes}">
			<tr>
				<td>${homes.idx}</td>
				<td>${homes.bookmark_idx}</td>
				<td>${homes.home_idx}</td>
				<td>${homes.url}</td>
				
				<c:if test="${homes.home_idx ne 0}">
				<td>
					<input type="button" value="삭제" onclick="deleteHome(${homes.idx}, ${homes.bookmark_idx});" />
				</td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
		
		<c:forEach var="homes" items="${homes}">
			<img src="${homes.url}">
		</c:forEach>
	</form>
=======
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
>>>>>>> refs/remotes/origin/develop
	
<<<<<<< HEAD
	<!-- 
		<td>메인 사진</td>
		<td>기존 북마크에 숙소 추가(숙소에 하트 누름)</td>
		<td>숙소 삭제(하트(좋아요) 취소)</td>
		<td>수정 날짜</td>
	 -->
=======
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
>>>>>>> refs/remotes/origin/develop
</body>
</html>