<%@ page import="egovframework.platform.module.main.vo.ProgramVO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
	List<ProgramVO> menuList = (List<ProgramVO>) request.getAttribute("menuList");
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>화산스틸</title>
<%@ include file="/common/programIncludeMain.jsp" %>
<script type="text/javascript">
function onMenuClick(pgmPath) {
	$("#programFrame").attr("src", pgmPath + "?project=" + $("#networkList").getVal());
}

$(function () {
	$("#sidebar ul li").each(function () {
		$(this).click(function () {
			$(this).addClass("selected");
			$(this).siblings().removeClass("selected");
		});
	});

	$("#btnLogout").on("click", function (evt) {
		if (confirm("로그아웃 하시겠습니까?")) {
			$(location).attr("href", "/logout.do");
		}
	});

	$("#btnUserInfo").on("click", function(evt) {
		getProjectList();
	})

});

$(document).ready(function () {
	util.hideLoading();
});
</script>
<style>
</style>
</head>
<body>
<div id="mainContainer">
	<nav id="sidebar">
		<div class="sidebar-header">
			<button id="btnAddNewNetwork">화산스틸</button>
		</div>
		<ul id="menuList" class="list-unstyled components">
<%
	for (ProgramVO program : menuList) {
		if (program.IS_RUN_YN.equals("Y")) {
%>
			<li><a data-url="<%=program.PGM_PATH%>" href="javascript:void(0);" onclick="onMenuClick('<%=program.PGM_PATH%>');" target="programFrame"><%=program.PGM_NM%></a></li>
<%
		}
	}
%>
		</ul>
	</nav>
	<div id="programContainer">
		<nav id="header">
			<div>
			</div>
			<div>
				<span id="userId"><%=USR_NM%>(<%=USR_ID%>)</span>
				<button id="btnUserInfo" style="display:none;">내정보</button>
				<button id="btnLogout">LOGOUT</button>
			</div>
		</nav>
		<iframe id="programFrame" name="programFrame"></iframe>
	</div>
</div>
<div id="loading" style="background-color:rgba(0,0,0,0.1); width: 100%; height: 100%; z-index:99999;position:absolute;left:0; top: 0; display:flex; justify-content: center; align-items: center;">
	<img src="/images/main/loading.gif" />
</div>
</body>
</html>

