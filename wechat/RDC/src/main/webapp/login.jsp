<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="host" value="http//localhost:8080/weChat"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>登录</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        function refreshCode(){
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");
            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
        }

        <c:if test="${message!=null}">
        alert("系统提示：${message}");
        </c:if>

        <%
        Cookie[] cookies = request.getCookies();
        String username=null;
        String password=null;
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("user_username")){
                username = cookie.getValue();
            } else if (cookie.getName().equals("user_password")) {
                password = cookie.getValue();
            }
        }
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        %>
        window.onload = function username(){
            $("#username").val(${username});
            $("#password").val(${password});
            if ($("#username").val()!=null){
                $("#option").attr("checked","checked");
            }
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">用户登录</h3>
    <form action="${pageContext.request.contextPath}/wechat/user?method=login.do" method="post">
        <div class="form-group">
            <label for="username">账号：</label>
            <input type="text" name="username" class="form-control" id="username" placeholder="请输入密码"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="verified">验证码：</label>
            <input type="text" name="verified" class="form-control" id="verified" placeholder="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/></a>
        </div>

        <hr/>

        <div class="remember-me">
            <input id="option" name="auto_login" type="checkbox" value="true">记住密码
        </div>

        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">
            <input class="btn btn btn-primary" type="reset" value="重置">
            <a class="btn btn btn-primary" href="${pageContext.request.contextPath}/regist.jsp">注册</a>
        </div>
    </form>
</div>
</body>
</html>
