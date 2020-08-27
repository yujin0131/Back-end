<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Airdnd</title>
</head>
<body>
<h1>
	 Test
</h1>

<table border="1">
		<tr>
			<td>사진url</td>
			<td>사진 밑 제목그거 전체 그거</td>
			<td>큰제목</td>
			<td>최대인원</td>
			<td>침실</td>
			<td>침대</td>
			<td>욕실</td>
			<td>가격</td>
			<td>평점</td>
			<td>리뷰개수</td>
			
		</tr>
	<c:forEach var="list" items="${list}">
		<tr>
			<td>사진</td>
			<td>${list.sub_title}</td>
			<td>${list.title}</td>
			<td>${list.filter_max_person}</td>
			<td>${list.filter_bedroom}</td>
			<td>${list.filter_bed}</td>
			<td>${list.filter_bathroom}</td>
			<td>${list.price}</td>
			<td>${list.score}</td>
			<td>${list.review_num}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>