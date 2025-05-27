<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>xxx课件管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>xxx课件管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> Admin</span> , 欢迎你！</p>
        <a href="login.html">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li ><a href="subjectList.html">科目管理</a></li>
                <li><a href="studyList.html">课件管理</a></li>
                <li><a href="userList.html">用户管理</a></li>
                <li><a href="password.html">密码修改</a></li>
                <li><a href="login.html">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>用户账号：</strong><span>${user.username}</span></p>
            <p><strong>真实姓名：</strong><span>${user.realname}</span></p>
            <p><strong>用户性别：</strong>
                <c:choose>
                    <c:when test="${user.sex == '1'}">男</c:when>
                    <c:when test="${user.sex == '2'}">女</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose>
            </p>
            <p><strong>出生日期：</strong><span>${user.birthday}</span></p>
            <p><strong>用户电话：</strong><span>${user.tel}</span></p>
            <p><strong>用户地址：</strong><span>${user.address}</span></p>
            <p><strong>用户类别：</strong>
                <c:choose>
                    <c:when test="${user.type == '1'}">学生</c:when>
                    <c:when test="${user.type == '2'}">老师</c:when>
                    <c:when test="${user.type == '3'}">管理员</c:when>
                    <c:otherwise>未知</c:otherwise>
                </c:choose>
            </p>

            <a href="${pageContext.request.contextPath}/user?action=list">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>