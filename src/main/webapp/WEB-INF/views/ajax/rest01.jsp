<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	function getF(){
		$.ajax({ url: "rest", type : "get",
			success : function( msg ){
				console.log( msg );
				$("#txt").html( msg )}, 
			error : function(){
				console.log("error");
			}
		})
	}
	function postF(){
		$.ajax({ url: "rest", type : "post",
			success : function( msg ){
				console.log( msg );
				$("#txt").html( msg )}
		})
	}	
	function putF(){
		$.ajax({ url: "rest", type : "put",
			success : function( msg ){
				console.log( msg );
				$("#txt").html( msg )}
			}
		})
	}
	function delF(){
		$.ajax({ url: "rest", type : "delete",
			success : function( msg ){
				console.log( msg );
				$("#txt").html( msg )}
			}
		})
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<h3 id="txt"></h3>
	<button onclick="getF()">get</button>
	<button onclick="postF()">post</button>
	<button onclick="putF()">put</button>
	<button onclick="delF()">delete</button>
</body>
</html>