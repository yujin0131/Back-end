<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>KIN chatting KIN</title>
</head>
<body>
	Her_k test ㅋㅋㅋvV<br>
	한글도 테스트 중 특수문자도 ☆★
	<form>
		<!-- if you pressed the send button or pushed the enter key -->
		<input type="text" size="100" name="content" onkeypress="if(event.keyCode==13) {send(this.form);}">
		<input type="button" value="SEND" onclick="send(this.form);">
		
		<table border="1">
			<tr>
				<td>채팅idx</td>
				<td>호스트 닉네임</td>
				<td>호스트 사진</td>
				<td>유저 닉네임</td>
				<td>유저 사진</td>
				<td>내용</td>
				<td>첨부 이미지</td>
				<td>날짜</td>
				<td>시간</td>
				<td>메시지 숨겼어염?</td>
			</tr>
			
			<c:forEach var="list" items="${list}">
			<tr>
				<td>${list.idx}</td>
				<td>${list.host_idx}(추후 수정)</td>
				<td></td>
				<td>${list.user_idx}(추후 수정)</td>
				<td></td>
				<td>${list.content}</td>
				<td>${list.image_url}</td>
				<td>${list.send_date_time}(추후 수정)</td>
				<td></td>
				<td>${list.msg_hidden_or_not}</td>
			</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>하</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>