<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="host" value="http//localhost:8080/weChat"/>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        //切换验证码
        function refreshCode(){
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");

            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
        }
    </script>
    <script>
        $("#user").blur(function (){
            const username = $(this).val();
            alert(username)
            if(username===null){
                var span = $("#s_username");
                span.css("color","red");
                span.html("账号不能为空");
                return
            }
            $.get("${pageContext.request.contextPath}/findUserServlet",
                {username:username},
                function (data) {
                    var span = $("#s_username");
                    if(data.userExsit){
                        //用户名存在
                        span.css("color","red");
                    }else{
                        //用户名不存在
                        span.css("color","green");
                    }
                    span.html(data.msg);
                });
        });
        <c:if test="${message!=null}">
        alert("系统提示：${message}");
        </c:if>
    </script>
</head>
<body>
<div class="container" style="width: 400px;" id="regist">
    <h3 style="text-align: center;">用户注册</h3>
    <form action="${pageContext.request.contextPath}/wechat/user?method=regist.do" method="post">
        <div class="form-group">
            <label for="name">名称：</label>
            <input type="text" name="name" class="form-control" id="name" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="username">账号：</label>
            <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名"/>
            <span id="s_username"></span>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>
        <div class="form-group">
            <label for="password2">确定密码：</label>
            <input type="password" name="password2" class="form-control" id="password2" placeholder="请输入密码"/>
        </div>

        <div class="form-group">
            <label for="phone">手机号码：</label>
            <input type="text" name="phone" class="form-control" id="phone" placeholder="请输入手机号码"/>
        </div>

        <div>
            <label for="email">邮箱：</label>
            <input type="email" name="email" class="form-control" id="email" placeholder="请输入邮箱"/>
        </div>

        <br>

        <div class="form-inline">
            <label for="verified">验证码：</label>
            <input type="text" name="verified" class="form-control" id="verified" placeholder="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode()"><img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/></a>
        </div>

        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="注册">
            <input class="btn btn btn-primary" type="reset" value="重置">
        </div>
    </form>
    <div class="form-group" style="text-align: center;">
        <a class="btn btn btn-primary" href="${pageContext.request.contextPath}/login.jsp" style="text-align: center">返回</a>
    </div>
</div>
</body>
</html>
