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
		$.ajax({ 
			url : "result02",
			type : "get",
			success : function( data ){
				$("#txt").text( data + "<br>aa"); //text는 말그대로 text형태로 들어감 >> html으로하면 <br>적용됨
			},
			error : ()=>{
				console.log("문제발생 ㅠ ")
			}
		}) 
		// 패치(fetch)랑 같은거 , 위의 jquery가 없으면 $문법 사용할수없음, (안에는 설정값 넣기)
		// fetch("ajax_test");
		
		
	}
	</script>
</head>
<body>
	<h1>1111</h1>	<h1>1111</h1>	<h1>1111</h1>
	<h1>1111</h1>	<h1>1111</h1>	<h1>1111</h1>
	<h1>1111</h1>	<h1>1111</h1>	<h1>1111</h1>
	<button type="button" onclick="test()">클릭</button>
	<hr>
	<b id="txt"></b>
</body>
</html>