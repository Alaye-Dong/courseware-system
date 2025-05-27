<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>xxx课件管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="js/calendar.js">
    </script>

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
                <li><a href="subjectList.html">科目管理</a></li>
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
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="user" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${user.id}">

                <div>
                    <label>真实姓名：</label>
                    <input type="text" name="realname" value="${user.realname}" required>
                </div>

                <div>
                    <label>性别：</label>
                    <select name="sex" required>
                        <option value="1" ${user.sex == '1' ? 'selected' : ''}>男</option>
                        <option value="2" ${user.sex == '2' ? 'selected' : ''}>女</option>
                    </select>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input id="birthday" type="text" name="birthday"
                           value="${user.birthday}" readonly="readonly"
                           onClick="SelectDate(this,'yyyy-MM-dd')"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="tel">用户电话：</label>
                    <input type="text" name="tel" id="tel"
                           value="${user.tel}" required/>
                    <span>*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="address" id="userAddress"
                           value="${user.address}" required/>
                </div>
                <div>
                    <label>用户类别：</label>
                    <input type="radio" name="type" value="1" ${user.type == '1' ? 'checked' : ''}/>学员
                    <input type="radio" name="type" value="2" ${user.type == '2' ? 'checked' : ''}/>老师
                    <input type="radio" name="type" value="3" ${user.type == '3' ? 'checked' : ''}/>管理员
                </div>
                <div class="providerAddBtn">
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="window.location.href='user?action=list'"/>
                </div>
            </form>
        </div>
    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>