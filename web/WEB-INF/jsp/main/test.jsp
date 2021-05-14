<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	String mainPage = "/mainPage.do";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" href="/css/font/notosanskr.css" type="text/css" />
<link rel="stylesheet" href="/css/font/NotoSansKR-Hestia.css" type="text/css" />
<link rel="stylesheet" href="/js/jqueryui/jquery-ui.min.css" type="text/css" />
<link rel="stylesheet" href="/css/main.css" type="text/css" />
<script type="text/javascript" src="/js/jquery/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/js/jqueryui/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/lodash/lodash.min.js"></script>
<script type="text/javascript" src="/js/util.js"></script>
<style>
body {
	background-color: #f0f0f0;
	width: 100%;
	height: 100%;
}
</style>
<script type="text/javascript">
$(document).ready(function () {
	initPage();

	$("#btnTest").click(function () {
		getMovies();
	});

	$("#btnMovie").click(function () {
		getMovieInfo();
	});

	$("#btnServer").click(function () {
		getMovieListServer();
	});
});

function initPage() {

}

function getMovies() {
	let url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
	url += "?key=809a07f4b41508becc428d67efe8446d";
	url += "&curPage=5";

	util.getService(url, function(result) {
		console.log(result);
		alert(result.movieListResult.movieList[0].movieCd)
	});
}

function getMovieInfo() {
	let url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";
	url += "?key=809a07f4b41508becc428d67efe8446d";

	util.getService(url, function(result) {
		console.log(result);
	});
}

function getMovieListServer() {
	let url = "/getMovieList";

	util.getService(url, function(result) {
		console.log(result);
	});
}

</script>
</head>
<body>
<button id="btnTest">getMovies</button>
<input type="text" id="movieCd">
<button id="btnMovie">getMovieInfo</button>
<button id="btnServer">server</button>
</body>
</html>