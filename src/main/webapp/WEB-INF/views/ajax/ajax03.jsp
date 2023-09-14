<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	function test(){
		let n = document.getElementById("name").value;
		let a = $("#age").val();
		let form = {name : n, age : a}; // key : value
		$.ajax({
			 url :"result03", type:"POST",
			 data : JSON.stringify( form ), //서버로 전송할 데이터 ( form == object >> 문자열 형식으로 바꿔줘(제이슨))
			contentType : "application/json; charset=utf-8", // 서버로 보낼 데이터 유형 
			dataType : "json", //서버로 받을 리턴 타입 지정
			success : function( data ){
			console.log("이름: "+ data.name )
			console.log("나이: "+ data.age )
			}
		 });
	}
</script>
</head>
<body>
	name : <input type="text" id="name"><br>
	age : <input type="text" id="age"><br>
	 <input type="button" onclick="test()" value="전송"><br>
</body>
</html>