<%@ page import="ncu.stu.beans.MyUser" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/11/28
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>发起会议人</title>
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
            .div11{
                margin-top: 30px;

            }
            #add{
                width: 100px;
                height: 30px;
                font-size: 20px;
                background-color: #2aabd2;
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
                    欢迎<%= pageContext.getAttribute("name")%>发起人登录
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
                            <td><b>名单</b></td>
                            <%--<td><b>操作</b></td>--%>

                        </tr>

                        <c:forEach items="${meetings}" var="item" varStatus="status">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.time}</td>
                                <td>${item.hour}</td>
                                <td>${item.place}</td>
                                <td>${item.hotel}</td>
                                <td><a href="findAttendeesServlet?id=${item.id}">查看</a></td>
                                <%--<td><a href="javascript:void(0)" onclick="delTr(this)">删除</a></td>--%>
                            </tr>

                        </c:forEach>

                    </table>
                </div>
                <div class="div11">
                    <td><a href="addMeeting.jsp">添加会议</a></td>
                </div>
            </div>
        </center>

        <script>
            /*
            1.添加
                1. 给添加按钮绑定事件
                2. 获取文本框的内容
                3. 创建td，设置td的文本为文本框的内容
                4. 创建tr
                5. 将td添加到tr中
                6. 获取table，将tr添加到table中
            2.删除
                1.确定点击的是哪一个超链接
                    <a href="javascript:void(0)" onclick="delTr(this)">删除</a>
                2.怎么删除
                    removeChild（）：通过父节点删除子节点
             */
            // number = 0;
            var add = document.getElementById("add");
            add.onclick = function(){

                var tab2 = document.getElementById("tab2");
                var add_tr = document.createElement("tr");
                // add_tr.setAttribute("id",number);
                var tdKey = document.createElement("td");
                var tdName = document.createElement("td");
                var tdSex = document.createElement("td");
                var tdRemove = document.createElement("td");
                var a = document.createElement("a");
                a.setAttribute("href","javascript:void(0)");
                a.setAttribute("onclick","delTr(this)");
                a.innerText = "删除";
                tdKey.innerText = document.getElementById("key").value;
                tdName.innerHTML = document.getElementById("name").value;
                tdSex.innerHTML = sex = document.getElementById("sex").value;
                tdRemove.appendChild(a);
                add_tr.appendChild(tdKey);
                add_tr.appendChild(tdName);
                add_tr.appendChild(tdSex);

                add_tr.appendChild(tdRemove);
                tab2.appendChild(add_tr);
            }
            function delTr(obj){
                var table = obj.parentNode.parentNode.parentNode;
                var tr = obj.parentNode.parentNode;

                table.removeChild(tr);
            }
        </script>
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
    </body>
</html>
