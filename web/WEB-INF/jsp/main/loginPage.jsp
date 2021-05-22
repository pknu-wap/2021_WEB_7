<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/font/notosanskr.css" type="text/css" />
<link rel="stylesheet" href="/css/font/NotoSansKR-Hestia.css" type="text/css" />
<script type="text/javascript" src="/js/jquery/jquery-3.5.1.js"></script>
<script type="text/javascript" src="/js/lodash/lodash.min.js"></script>
<script type="text/javascript" src="/js/util.js"></script>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	font-family: "Noto Sans KR", "Noto Sans Korean", sans-serif;
	outline: none;
}

html, body {
	height: 100%;
	width: 100%;
}

h1 {
	margin-bottom: 10px;
}

#mainDiv {
    display: flex;
    width: 100%;
    height: 100%;
    justify-content: center;
    align-items: center;
}

div#mainDiv form input {
    width: 180px;
    height: 30px;
    line-height: 30px;
    display: inline-block;
    margin-bottom: 10px;
}

div#mainDiv form {
    width: 200px;
    height: 180px;
}

div#mainDiv form input[type="password"] {
    margin-bottom: 20px;
}

div#mainDiv form button {
    width: 180px;
    height: 40px;
	line-height: 40px;
    cursor: pointer;
	font-weight: 500;
	font-size: 20px;
}

</style>
<script>
$(document).ready(function() {
	$("form button").on("click", function() {
		fnLogin();
	})
});


function fnLogin() {
	let id = $("#id").val();
	let pw = $("#pw").val();

	if (id === "") {
		alert("아이디를 입력하세요");
		return;
	}

	if (pw === "") {
		alert("비밀번호를 입력하세요");
		return;
	}

	let data = {};
	data["USR_ID"] = id;
	data["USR_PW"] = pw;

	util.postService("/loginProcess", data, function(result) {
		if (result["RESULT_CODE"] === "00") {
			$(location).attr("href", "/mainPage.do");
		} else {
			alert("정보를 확인하세요");
			return;
		}
	});
}
</script>
</head>
<body>
	<div id="mainDiv">
		<form onsubmit="return false;">
			<h1>MovieAll</h1>
			<input id="id" type="text" placeholder="아이디" />
			<input id="pw" type="password" placeholder="비밀번호" />
			<button>로그인</button>
		</form>
	</div>
</body>
</html>