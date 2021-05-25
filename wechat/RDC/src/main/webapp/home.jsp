<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/21
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="host" value="http//localhost:8080/weChat"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>首页</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        #moment_div{
            width: 400px;
        }
        .talk_con{
            width:900px;
            height:550px;
            border:1px solid #666;
            margin:10px 10px 50px 10px;
            background: #f9f9f9;
        }
        .talk_show{
            width:880px;
            height:420px;
            border:1px solid #666;
            background:#fff;
            margin:10px auto 0;
            overflow:auto;
        }
        .talk_input{
            width:880px;
            height: 100px;
            margin:10px;
        }
        .talk_word{
            width:780px;
            height:76px;
            padding:0px;
            float:left;
            margin-left:10px;
            outline:none;
            text-indent:10px;
        }
        .talk_sub{
            width:56px;
            height:30px;
            float:left;
            margin-left:10px;
        }
        .atalk{
            margin:10px;
        }
        .atalk span{
            display:inline-block;
            background:#0181cc;
            border-radius:10px;
            color:#fff;
            padding:5px 10px;
        }
        .btalk{
            margin:10px;
            text-align:right;
        }
        .btalk span{
            display:inline-block;
            background:#ef8201;
            border-radius:10px;
            color:#fff;
            padding:5px 10px;
        }
        .talk_friend{
            width: 180px;
            height: 550px;
            border:1px solid #0f0f0f;
            margin:10px 10px 50px 10px;
            float: left;
        }
        .talk_friend2{
            width: 300px;
            height: 550px;
            border:1px solid #0f0f0f;
            margin:10px 10px 50px 10px;
            float: left;
        }
        td, th {
            text-align: center;
        }
        #allinformation{
            width: 400px;
        }

        #black_friend{
            width: 299px;
        }
        #black_black{
            width: 299px;
        }

        #add_friend{
            width: 299px;
        }
        #add_group{
            width: 299px;
        }

        #notice_div{
            width: 499px;
        }
        #notice_twe_div{
            width: 499px;
        }
    </style>
    <script>
        function friends (){
            $("#notice").attr("class",null);
            $("#cretagroup").attr("class",null);
            $("#friend").attr("class","active");
            $("#groups").attr("class",null);
            $("#information").attr("class",null);
            $("#blacklist").attr("class",null);
            $("#moments").attr("class",null);

            $("#create_groups").attr("style","display:none;");
            $("#tell").attr("style","display:block;");
            $("#allinformation").attr("style","display:none;");
            $("#black_list").attr("style","display:none");
            $("#add_list").attr("style","display:none");
            $("#notices_div").attr("style","display:none;");
            $("#moment_div").attr("style","display:none;");

            flag = false;

            $("#friendtables").html("");
            $("#friendtables").append("<tr id=\"success\" class=\"success\">"+
                "<th style=\"text-align: center\">好友列表</th>"+
                "</tr>")

            $.get(
                "${pageContext.request.contextPath}/wechat/friend?method=listfriend.do",
                {userid:${data.wechatId}},
                function (data) {
                    for (var i = 0;data[i]!=null;i++){
                        $("#friendtables").append("<tr><th><button class=\"btn btn-default\" type=\"submit\" onclick=\"link("+data[i].wechatId+")\" style=\"width: 159px ;height: auto ; border:0;padding: 0;\">"+data[i].name+"</button></th></th>");
                    }
                });
        }

        function groups(){
            $("#notice").attr("class",null);
            $("#cretagroup").attr("class",null);
            $("#groups").attr("class","active");
            $("#friend").attr("class",null);
            $("#information").attr("class",null);
            $("#blacklist").attr("class",null);
            $("#moments").attr("class",null);

            $("#create_groups").attr("style","display:none;");
            $("#tell").attr("style","display:block;");
            $("#allinformation").attr("style","display:none;");
            $("#black_list").attr("style","display:none");
            $("#add_list").attr("style","display:none");
            $("#notices_div").attr("style","display:none;");
            $("#moment_div").attr("style","display:none;");

            flag = true;

            $("#friendtables").html("");
            $("#friendtables").append("<tr id=\"success\" class=\"success\">"+
                "<th style=\"text-align: center\">群聊列表</th>"+
                "</tr>")
            $.get(
                "${pageContext.request.contextPath}/wechat/group?method=listgroup.do",
                {userid:${data.wechatId}},
                function (data) {
                    for (var i = 0;data[i]!=null;i++){
                        $("#friendtables").append("<tr id=\"gtr"+"i\"><th><button class=\"btn btn-default\" type=\"submit\" onclick=\"link("+data[i].groupId+")\" style=\"width: 159px ;height: auto ; border:0;padding: 0;\">"+data[i].groupname+"</button></th></th>");
                    }
                });

        }


        function moments(){
            $("#notice").attr("class",null);
            $("#cretagroup").attr("class",null);
            $("#friend").attr("class",null);
            $("#information").attr("class",null);
            $("#blacklist").attr("class",null);
            $("#groups").attr("class",null);
            $("#moments").attr("class","active");

            $("#create_groups").attr("style","display:none;");
            $("#tell").attr("style","display:none;");
            $("#allinformation").attr("style","display:none;");
            $("#black_list").attr("style","display:none");
            $("#add_list").attr("style","display:none");
            $("#notices_div").attr("style","display:none;");
            $("#moment_div").attr("style","display:block;");
            $("#moment_div_div").html("");

            $.get(
                "${pageContext.request.contextPath}/wechat/moment?method=listmoment.do",
                {},
                function (data) {
                    if (data.data!=null){
                        for (var i = 0;data.data[i]!=null;i++){
                            $("#moment_div_div").append('<div>'+
                                '<div style=\"width: 400px;height: 50px\">'+
                                data.data[i].uid+
                                '<br>'+
                                data.data[i].talk+
                                '<img src=\"http://localhost:8080/weChat'+
                                data.data[i].imgs[0]+
                                '\">'+
                                '</div>'+
                                '        <div style=\"width:400px;height: 100px\">'+
                                '            <button style=\"width: 80px;height: 20px\" value=\"点赞\">点赞</button>'+
                                '            <button style=\"width: 80px;height: 20px\" value=\"取消点赞\">取消点赞</button>'+
                                '            <button style=\"width: 80px;height: 20px\" value=\"评论\">评论</button>'+
                                '            <input disabled type=\"text\" style=\"width: 400px;height: 80px\">'+
                                '        </div>'+
                                '        <hr><br>'+
                                '        </div>')}
                    }else {
                        alert(data.message);
                    }
                });

        }

        function information(){
            $("#notice").attr("class",null);
            $("#cretagroup").attr("class",null);
            $("#information").attr("class","active");
            $("#friend").attr("class",null);
            $("#groups").attr("class",null);
            $("#blacklist").attr("class",null);
            $("#moments").attr("class",null);

            $("#create_groups").attr("style","display:none;");
            $("#tell").attr("style","display:none;");
            $("#allinformation").attr("style","display:block;");
            $("#black_list").attr("style","display:none");
            $("#add_list").attr("style","display:none");
            $("#notices_div").attr("style","display:none;");
            $("#moment_div").attr("style","display:none;");
        }

        function blacklist(){
            $("#notice").attr("class",null);
            $("#cretagroup").attr("class",null);
            $("#blacklist").attr("class","active");
            $("#friend").attr("class",null);
            $("#groups").attr("class",null);
            $("#information").attr("class",null);
            $("#moments").attr("class",null);

            $("#create_groups").attr("style","display:none;");
            $("#tell").attr("style","display:none;");
            $("#allinformation").attr("style","display:none;");
            $("#black_list").attr("style","display:block");
            $("#add_list").attr("style","display:none");
            $("#notices_div").attr("style","display:none;");
            $("#moment_div").attr("style","display:none;");


            $("#black_friend_table").html("");
            $("#black_friend_table").append("<tr id=\"success\" class=\"success\">"+
                "<th style=\"text-align: center\">好友列表</th>"+
                "<th style=\"text-align: center\">操作</th>"+
                "</tr>")

            $.get(
                "${pageContext.request.contextPath}/wechat/friend?method=listfriend.do",
                {userid:${data.wechatId}},
                function (data) {
                    for (var i = 0;data[i]!=null;i++){
                        $("#black_friend_table").append("<tr id=\"bftr"+"i\">"+
                            "<th>"+data[i].name+"</th>"+
                                "<th><button class=\"btn btn-default\" type=\"submit\" onclick='addblack("+data[i].wechatId+")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">加入黑名单</button></th></tr>");
                    }
                });

            $("#black_black_table").html("");
            $("#black_black_table").append("<tr id=\"success\" class=\"success\">"+
                "<th style=\"text-align: center\">黑名单</th>"+
                "<th style=\"text-align: center\">操作</th>"+
                "</tr>")

            $.get(
                "${pageContext.request.contextPath}/wechat/black?method=listblack.do",
                {userid:${data.wechatId}},
                function (data) {
                    for (var i = 0;data[i]!=null;i++){
                        $("#black_black_table").append("<tr id=\"bbtr"+"i\">"+
                            "<th>"+data[i].name+"</th>"+
                            "<td><button class=\"btn btn-default btn-sm\" type=\"submit\" onclick='offblack("+data[i].wechatId+")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">拉出黑名单</button></td>"+
                            "</tr>");
                    }
                });
        }


        function search(){
            $("#create_groups").attr("style","display:none;");
            $("#tell").attr("style","display:none;");
            $("#allinformation").attr("style","display:none;");
            $("#add_list").attr("style","display:block");
            $("#black_list").attr("style","display:none");
            $("#notices_div").attr("style","display:none;");
            $("#moment_div").attr("style","display:none;");

            var search = $("#search").val();

            $("#add_friend_table").html("");
            $("#add_friend_table").append("<tr id=\"success\" class=\"success\">"+
                "<th style=\"text-align: center\">用户列表</th>"+
                "<th style=\"text-align: center\">操作</th>"+
                "</tr>")

            $.get("${pageContext.request.contextPath}/wechat/friend?method=searchfriend.do",
                {search:search},
                function (data) {
                    if(data.data!==null){
                        for (var i = 0;data.data[i]!=null;i++){
                            $("#add_friend_table").append("<tr>"+
                                "<th>"+data.data[i].name+"</th>"+
                                "<td><button class=\"btn btn-default btn-sm\" type=\"submit\" onclick='addfriendnotice("+data.data[i].wechatId+")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">好友申请</button></td>"+
                                "</tr>");
                        }
                    }else {
                        alert(data.message);
                    }
                });

            $("#add_group_table").html("");
            $("#add_group_table").append("<tr id=\"success\" class=\"success\">"+
                "<th style=\"text-align: center\">群组列表</th>"+
                "<th style=\"text-align: center\">操作</th>"+
                "</tr>")
            $.get("${pageContext.request.contextPath}/wechat/group?method=searchgroup.do",
                {search:search},
                function (data) {
                    if(data.data!==null) {
                        for (var i = 0; data.data[i] != null; i++) {
                            $("#add_group_table").append("<tr>" +
                                "<th>" + data.data[i].groupname + "</th>" +
                                "<td><button class=\"btn btn-default btn-sm\" type=\"submit\" onclick='addgroupnotice(" + data.data[i].groupId + ")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">群组申请</button></td>" +
                                "</tr>");
                        }
                    }else {
                        alert(data.message);
                    }
                });
        }

        function creategroup(){
            $("#notice").attr("class",null);
            $("#blacklist").attr("class",null);
            $("#cretagroup").attr("class","active");
            $("#friend").attr("class",null);
            $("#groups").attr("class",null);
            $("#information").attr("class",null);
            $("#moments").attr("class",null);

            $("#notices_div").attr("style","display:none;");
            $("#create_groups").attr("style","display:block;");
            $("#tell").attr("style","display:none;");
            $("#allinformation").attr("style","display:none;");
            $("#add_list").attr("style","display:none");
            $("#black_list").attr("style","display:none");
            $("#moment_div").attr("style","display:none;");
        }

        function notice(){
            $("#notice").attr("class","active");
            $("#blacklist").attr("class",null);
            $("#cretagroup").attr("class",null);
            $("#friend").attr("class",null);
            $("#groups").attr("class",null);
            $("#information").attr("class",null);
            $("#moments").attr("class",null);


            $("#notices_div").attr("style","display:block;");
            $("#create_groups").attr("style","display:none;");
            $("#tell").attr("style","display:none;");
            $("#allinformation").attr("style","display:none;");
            $("#add_list").attr("style","display:none");
            $("#black_list").attr("style","display:none");
            $("#moment_div").attr("style","display:none;");

            $("#notice_table").html("");
            $("#notice_table").append("<tr id=\"success\" class=\"success\">"+
                "<th style=\"text-align: center\">申请</th>"+
                "<th style=\"text-align: center;width: 30%;\">操作</th>"+
                "</tr>")


            $.get("${pageContext.request.contextPath}/wechat/notice?method=noticelist.do",
                {},
                function (data) {
                    if(data.message!==null) {
                        for (var i = 0; data.data[i] != null; i++) {
                            if (data.data[i].type!=0){
                                $("#notice_table").append("<tr >" +
                                    "<th>" + data.data[i].name + "申请加入群聊:"+data.data[i].username+"</th>" +
                                    "<td>"+
                                    "<button class=\"btn btn-default btn-sm\" type=\"submit\" onclick='addgroup(" + data.data[i].id+","+data.data[i].sendid +","+"\""+ data.data[i].name + "\""+","+data.data[i].userid +","+"\""+data.data[i].username +"\""+")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">同意</button>" +
                                    "<button class=\"btn btn-default btn-sm\" type=\"submit\" onclick='offgroup(" + data.data[i].id+","+data.data[i].sendid +","+"\""+ data.data[i].name + "\""+","+"\""+data.data[i].username +"\""+ ")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">拒绝</button>" +
                                    "</td>" +
                                    "</tr>");
                            }else {
                                $("#notice_table").append("<tr >" +
                                    "<th>" + data.data[i].name + "申请与您成为好友</th>" +
                                    "<td>"+
                                    "<button class=\"btn btn-default btn-sm\" type=\"submit\" onclick='addfriend(" + data.data[i].sendid+","+ data.data[i].id + ")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">同意</button>" +
                                    "<button class=\"btn btn-default btn-sm\" type=\"submit\" onclick='offfriend(" + data.data[i].sendid +","+ data.data[i].id+ ")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">拒绝</button>" +
                                    "</td>" +
                                    "</tr>");
                            }
                        }
                    }else {
                        alert(data.message);
                    }
                });

            $("#notice_twe_table").html("");
            $("#notice_twe_table").append("<tr id=\"success\" class=\"success\">"+
                "<th style=\"text-align: center\">通知</th>"+
                "<th style=\"text-align: center;width: 30%;\">操作</th>"+
                "</tr>")


            $.get("${pageContext.request.contextPath}/wechat/notice?method=noticedlist.do",
                {},
                function (data) {
                    for (var i = 0; data.data[i] != null; i++) {
                        $("#notice_twe_table").append("<tr id=\"ggtr" + "i\">" +
                            "<th>" + data.data[i].name + data.data[i].contant +data.data[i].groupname+"</th>" +
                            "<td>"+
                            "<button class=\"btn btn-default btn-sm\" type=\"submit\" onclick='offs(" + data.data[i].id + ")' style=\"width: 50% ;height: auto ; border:0;padding: 0;\">删除该信息</button>" +
                            "</td>" +
                            "</tr>");
                    }
                });

        }


        <c:if test="${message!=null}">
        alert("系统提示：${message}");
        </c:if>
    </script>
</head>
<body onload="friends()">
<div class="container" id="main">
    <div class="collapse navbar-collapse">
        <ul class="nav nav-pills">
            <li role="presentation" id="friend" class="active"><a href="#" onclick="friends()">好友列表</a></li>
            <li role="presentation" id="groups"><a href="#" onclick="groups()" >群聊列表</a></li>
            <li role="presentation" id="moments"><a href="#" onclick="moments()"  >朋友圈</a></li>
            <li role="presentation" id="information"><a href="#" onclick="information()">个人信息</a></li>
            <li role="presentation" id="blacklist"><a href="#" onclick="blacklist()">黑名单</a></li>

            <div class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" id="search" name="search" class="form-control" placeholder="Search">
                </div>
                <button type="button" onclick="search()" class="btn btn-default">搜索</button>
            </div>
            <li role="presentation" style="float: right" ><a href="${pageContext.request.contextPath}/login.jsp">退出</a></li>
            <li role="presentation" id="cretagroup" style="float: right" ><a href="#" onclick="creategroup()">创建群聊</a></li>
            <li role="presentation" id="notice" style="float: right" ><a href="#" onclick="notice()">通知</a></li>
        </ul>
    </div>

<%--    聊天框--%>
    <div id="tell">
        <div class="talk_con" style="float: right" id="talk">
            <div class="talk_show" id="words">

            </div>
            <div class="talk_input">
                <input autocomplete="off" type="text" class="talk_word" id="talkwords">
                <button class="btn btn-info talk_sub" onclick="sendMessage()" disabled="disabled" id="talksub">发送</button>
            </div>
        </div>

        <div class="talk_friend" role="group" id="alllist" >
            <table border="1" style="width: 179px" id="friendtables"  class="table table-bordered table-hover">

            </table>
        </div>
    </div>
    <%--    聊天框--%>

    <%--    用户信息表--%>
    <div class="container" style="display: none" id="allinformation">
        <h3 style="text-align: center;">用户信息</h3>
        <form action="${pageContext.request.contextPath}/wechat/user?method=modify.do" method="post">

            <div class="form-group">
                <label for="username">账号：</label>
                <input disabled type="text" name="username" class="form-control" id="username" value="${data.username}" />
            </div>

            <div class="form-group">
                <label for="name">名称：</label>
                <input type="text" name="name" class="form-control" id="name" value="${data.name}" placeholder="请输入用户名"/>
            </div>

            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
            </div>

            <div class="form-group">
                <label for="password">确定密码：</label>
                <input type="password" name="password2" class="form-control" id="password2" placeholder="请输入密码"/>
            </div>


            <div class="form-group">
                <label for="phone">手机号码：</label>
                <input type="text" name="phone" value="${data.phone}" class="form-control" id="phone" placeholder="请输入手机号码"/>
            </div>

            <hr/>
            <div class="form-group" style="text-align: center;">
                <input class="btn btn btn-primary" onclick="modify()"  type="submit" value="修改">
                <input class="btn btn btn-primary" type="reset" value="重置">
            </div>
        </form>
    </div>
    <%--    用户信息表--%>

    <%--    创建群聊表--%>
    <div class="container" style="display: none" id="create_groups">
        <h3 style="text-align: center;">群聊信息信息</h3>
            <div class="form-group">
                <label for="groupname">群聊名称：</label>
                <input type="text" name="groupname" class="form-control" id="groupname" placeholder="请输入群聊名"/>
            </div>

            <hr/>

            <div class="form-group">
                <label for="introduce">群介绍：</label>
                <input type="text" name="introduce" class="form-control" id="introduce" placeholder="请输入简介"/>
            </div>

            <div class="form-group" style="text-align: center;">
                <input class="btn btn btn-primary" onclick="create_group()"  type="submit" value="创建">
                <input class="btn btn btn-primary" type="reset" value="重置">
            </div>
    </div>
    <%--    创建群聊表--%>


    <%--    黑名单列表--%>
    <div id="black_list" style="display: none">
        <div class="talk_friend" role="group" id="black_friend" >
            <table border="1" id="black_friend_table"  class="table table-bordered table-hover">

            </table>
        </div>

        <div class="talk_friend" role="group" style="float: right;" id="black_black" >
            <table border="1" id="black_black_table"  class="table table-bordered table-hover">

            </table>
        </div>
    </div>
    <%--    黑名单列表--%>


    <%--    查询列表--%>
    <div id="add_list" style="display: none">
        <div class="talk_friend" role="group" id="add_friend" >
            <table border="1" id="add_friend_table"  class="table table-bordered table-hover">

            </table>
        </div>

        <div class="talk_friend" role="group" style="float: right;" id="add_group" >
            <table border="1" id="add_group_table"  class="table table-bordered table-hover">

            </table>
        </div>
    </div>
    <%--    查询列表--%>

    <%--    通知列表--%>
    <div id="notices_div">
        <div class="talk_friend" role="group" style="float: right;" id="notice_div" >
            <table border="1" id="notice_table"  class="table table-bordered table-hover">

            </table>
        </div>
        <div class="talk_friend" role="group" style="float: right;" id="notice_twe_div" >
            <table border="1" id="notice_twe_table"  class="table table-bordered table-hover">

            </table>
        </div>
    </div>
    <%--    通知列表--%>


    <%--    朋友圈--%>
    <div id="moment_div" style="display: none" class="container">
        <div class="talk_show" id="moment_div_div" style=" overflow-y: scroll; overflow-x: hidden;height: 500px;text-align: center;width: 420px">

        </div>
        <form action="${pageContext.request.contextPath}/wechat/moment?method=addmoment.do" method="post" enctype="multipart/form-data">
            <div style="width:400px;height: 200px;text-align: center">
                <input style="width: 80px;height: 20px" type="submit" value="发朋友圈">
                <input name="moments_photo" id="moments_photo" style="text-align: center; width: 400px;height: 20px" type="file" value="上传照片">
                <input type="text" name="moments_word" id="moments_word" style="width: 400px;height:80px">
            </div>
        </form>
    </div>
    <%--    朋友圈--%>

</div>
<script>
    function addblack(index){
        $.get("${pageContext.request.contextPath}/wechat/black?method=addblack.do",
            {blackid:index},
            function (data) {
                if(data.isorno){
                    alert("添加成功!")
                }else{
                    alert("添加失败!")
                }
            });
    }

    function offblack(index){
        $.get("${pageContext.request.contextPath}/wechat/black?method=offblack.do",
            {friendid:index},
            function (data) {
                if(data.isorno){
                    alert("操作成功!")
                }else{
                    alert("操作失败!")
                }
            });
    }

    function create_group(){
        if ($("#groupname").val()!==""){
            $.get("${pageContext.request.contextPath}/wechat/group?method=creategroup.do",
                {groupUser:${data.wechatId},
                    groupname:$("#groupname").val(),
                    introduce:$("#introduce").val()
                },
                function (data) {
                    alert(data.message);
                });
            return;
        }
        alert("名称不得为空！");
    }

    function addfriendnotice(index){
        $.get("${pageContext.request.contextPath}/wechat/notice?method=addfriendnotice.do",
            {sendid:${data.wechatId},
            userid:index
               },
            function (data) {
                alert(data.message);
            });
    }

    function addgroupnotice(index){
        $.get("${pageContext.request.contextPath}/wechat/notice?method=addgroupnotice.do",
            {sendid:${data.wechatId},
                userid:index
            },
            function (data) {
                alert(data.message);
            });
    }


    function addfriend(index,index1){
        $.get("${pageContext.request.contextPath}/wechat/friend?method=addfriend.do",
            {sendid:${data.wechatId},
                userid:index,
                noticeid:index1
            },
            function (data) {
                alert(data.message);
            });
    }

    function addgroup(id,sendid,name,userid,username){
        $.get("${pageContext.request.contextPath}/wechat/group?method=addgroup.do",
            {   id:id,
                sendid:sendid,
                name:name,
                userid:userid,
                username:username
            },
            function (data) {
                alert(data.message);
            });
    }

    function offfriend(index,index1){
        $.get("${pageContext.request.contextPath}/wechat/friend?method=offfriend.do",
            {sendid:${data.wechatId},
                userid:index,
                noticeid:index1
            },
            function (data) {
                alert(data.message);
            });
    }
    function offgroup(id,sendid,name,username){
        alert(name);
        $.get("${pageContext.request.contextPath}/wechat/group?method=offgroup.do",
            {   id:id,
                sendid:sendid,
                name:name,
                username:username
            },
            function (data) {
                alert(data.message);
            });
    }

    function offs(index){
        $.get("${pageContext.request.contextPath}/wechat/notice?method=offnoticed.do",
            {   noticedid:index},
            function (data) {
                alert(data.message);
            });
    }
</script>
<script>
    var wechatId;
    var flag = false;
    var talkwords = document.getElementById('talkwords');
    const words = $('#words');
    const button=$('#talksub');
    var socket = new WebSocket("ws://localhost:8080/weChat/websocket/${data.wechatId}");

    socket.addEventListener('open',function (){
        alert("登录成功,${data.name},欢迎您的上线！");
    })

    function sendMessage(){
        words.html(words.html()+"<div class='btalk'><span>我："+talkwords.value+"</span></div>")
        if (flag===false){
            private();
        }else {
            public();
        }
        talkwords.value=null;
    }

    socket.addEventListener("message",function (e){
        words.html(words.html()+"<div class='atalk'><span>"+e.data+"</span></div>")
    })

    function public(){
        socket.send("@!%${data.wechatId}@!%"+wechatId+"@!%"+talkwords.value+"@!%public");
    }

    function private(){
        socket.send("@!%${data.wechatId}@!%"+wechatId+"@!%"+talkwords.value+"@!%private");
    }

    function link(index){
        button.removeAttr("disabled");
        words.empty();
        wechatId = index;
        if (flag){
            $.get("${pageContext.request.contextPath}/wechat/message?method=groupsmessage.do",
                {
                    id:index,
                },
                function (data) {
                if (data.data!=null){
                    for (var i = 0;data.data[i]!=null;i++){
                        words.append("<div class='atalk'><span>"+data.data[i].content+"</span></div>");
                    }
                }
                });
        }else {
            $.get("${pageContext.request.contextPath}/wechat/message?method=friendmessage.do",
                {
                    id:index,
                },
                function (data) {
                    if (data.data!=null) {
                        for (var i = 0;data.data[i]!=null;i++){
                            words.append("<div class='atalk'><span>"+data.data[i].content+"</span></div>");
                        }
                    }
                });
        }
    }
</script>
</body>
</html>
