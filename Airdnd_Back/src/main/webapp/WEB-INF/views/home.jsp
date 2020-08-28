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
			<td>방번호</td>
			<td>어딘지</td>
			<td>큰제목</td>
			<td>평점</td>
			<td>리뷰개수</td>
			<td>슈퍼호스트있는지</td>
			<td>주소</td>
			<td>위도</td>
			<td>경도</td>
			<td>사진 밑 제목그거 전체 그거</td>
			<td>최대인원</td>
			<td>침실</td>
			<td>침대</td>
			<td>욕실</td>
			<td>가격</td>
			<td>호스트말</td>
			<td>거리옆에 그거 설명</td>
			
		</tr>
	<c:forEach var="list" items="${list}">
		<tr>
			<td>${list.home_idx}</td>
			<td>${list.place}</td>
			<td>${list.title}</td>
			<td>평점</td>
			<td>리뷰개수</td>
			<td>true/false</td>
			<td>${list.addr}</td>
			<td>${list.lat}</td>
			<td>${list.lng}</td>
			<td>${list.sub_title}</td>
			<td>${list.filter_max_person}</td>
			<td>${list.filter_bedroom}</td>
			<td>${list.filter_bed}</td>
			<td>${list.filter_bathroom}</td>
			<td>${list.price}</td>
			<td>${list.host_notice}</td>
			<td>${list.loc_info}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>