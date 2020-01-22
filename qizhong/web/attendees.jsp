<%@ page import="ncu.stu.beans.MyUser" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/11/28
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>会议管理系统</title>
        <style>
            body{
                /*设置body背景图片*/
                background-image: url("./imgs/b2.jpg");
            }
            #tab2{
                border: 1px solid #ccaaff;
            }

            #tab2 tr td{
                border: 1px solid #ccaaff;
                height: 25px;
                width: 100px;
                margin: auto;
                text-align: center;
            }
            center{
                margin-top: 200px;
                font-size: 30px;
                color: orangered;
            }
            caption{
                font-size: 20px;
                color: #1b6d85;
            }
            .div1{
                width: 800px;
                height: 400px;
                background-color: white;
                border: 5px solid #8b8e99;
            }

            .div3{
                margin-bottom: 50px;
            }

        </style>
    </head>
    <body>

        <%
            MyUser user = new MyUser();
            user = (MyUser) session.getAttribute("user");
            pageContext.setAttribute("name",user.getUserNmae());
        %>
        <center>
            <div class="div3">
                <center>
                    欢迎<%= pageContext.getAttribute("name")%>登录
                </center>
            </div>
            <div class="div1">
                <div class="div12">
                    <table id="tab2">
                        <caption>会议清单</caption>
                        <tr>
                            <td ><b>编号</b></td>
                            <td ><b>日期</b></td>
                            <td ><b>时间</b></td>
                            <td ><b>地点</b></td>
                            <td ><b>宾馆</b></td>
                            <td><b>操作</b></td>

                        </tr>

                        <c:forEach items="${meetings}" var="item" varStatus="status">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.time}</td>
                                <td>${item.hour}</td>
                                <td>${item.place}</td>
                                <td>${item.hotel}</td>

                                <td><a href="findOneMeetServlet?id=${item.id}" onclick="">参加</a></td>

                            </tr>

                        </c:forEach>

                    </table>
                </div>
                <div class="div11">
                    <td><a href="findMyServlet">我的会议</a></td>
                </div>
            </div>
        </center>

    </body>
    <script>

        window.onload = function () {

            //获取图片对象
            var img = document.getElementById("checkcode");
            img.onclick = function Change() {
                //加时间戳
                img.src = "/QZ/checkcode？"+Date().getTime();
            }
        }

    </script>
</html>
