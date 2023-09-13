<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>수정수정</h3>
	<form action="modify" method="post" enctype="multipart/form-data">
		<input type="text" readonly name="id" value="${dto.id}"><br>
		<input type="text" name="name" value="${dto.name}"><br>
		<img src="download?file=${dto.imgName }" width="100" height="100"><br>
		${dto.imgName }<br>
		<input type="file" name="file"><br>
		<input type="submit" value="전송">
	</form>	
</body>
</html>