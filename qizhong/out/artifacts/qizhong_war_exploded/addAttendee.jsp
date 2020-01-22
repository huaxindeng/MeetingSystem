<%@ page import="ncu.stu.beans.MyUser" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/2
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>会议管理系统</title>
        <style>
            <!--选择所有元素-->
            *{
                margin: 0px;
                padding: 0px;
                /*固定大小*/
                box-sizing: border-box;
            }
            body{
                /*设置body背景图片*/
                background-image: url("./imgs/b2.jpg");
            }
            /*最大盒子*/
            .rg_layout{
                width: 800px;
                height: 750px;
                border: 5px solid #8b8e99;
                background-color: white;
                /*水平居中*/
                margin: auto;
                /*上边距*/
            }


            /*新用户注册*/
            .rg_top {
                color: #528dc8;
                font-size: 30px;
                margin-top: 60px;
            }

            /*中间盒子*/
            .rg_center{
                width: 600px;
                height: 800px;
            }
            /*中间盒子的label*/
            .td_left{
                width: 20%;
                /*文本右对齐*/
                text-align: center;
                height: 30px;
            }
            /*中间盒子的input*/
            .td_right{
                /*为何是设置内边距:设置td与input的距离，故为内边距*/
                padding: 15px;

            }

            /*input*/
            .rg_input{
                width: 250px;
                height: 30px;
                border: 1px solid #a6a6a6;
                /*设置边框圆角*/
                border-radius: 5px;
                /*设置placeholder与边框的距离*/
                padding-left: 5px;
            }
            /*验证码*/
            .rg_input_verification{
                width: 120px;
                height: 30px;
                border: 1px solid #a6a6a6;
                /*设置边框圆角*/
                border-radius: 5px;
                padding-left: 5px;
            }
            /*图片*/
            #checkcode{
                /*垂直居中*/
                vertical-align: middle;
                width:100px;
                height:50px;
            }
            /*注册*/
            .input_submit{
                background-color: #ffd026;
                width: 80px;
                height: 40px;
                font-size: 25px;
                /*设置边框圆角*/
                border-radius: 5px;
            }

            .td{
                border: 1px solid black;
            }

            form{
                width: 80%;
                height: 80%;
            }
            .table1{
                width: 500px;
                height: 600px;
            }
            .table1 tr{
                display: none;
            }
            .table2{
                width: 500px;
                height: 100px;
            }
        </style>
    </head>
    <%
        MyUser user = new MyUser();
        user = (MyUser) session.getAttribute("user");

        pageContext.setAttribute("name",user.getUserNmae());
    %>
    <body>
        <center>
            <div class="rg_top">
                <p>参会信息</p><br>
            </div>
            <div class="rg_layout">
                <div class="rg_center">
                    <form method="post" action="addAttendeeServlet">
                        <table class="table1">
                            <tr id="tr0">
                                <td class="td_left td" ><label for="name">姓名</label></td>
                                <td  class="td_right td" > <input class="rg_input" type="text" placeholder="姓名" id="name" name="name"></td>
                            </tr>
                            <tr id="tr1">
                                <td class="td_left td" ><label for="workplace">工作单位</label></td>
                                <td  class="td_right td" > <input class="rg_input" type="text" placeholder="工作单位" id="workplace" name="workplace"></td>
                            </tr>
                            <tr id="tr2">
                                <td class="td_left td" > <label for="identify_id">身份证号</label></td>
                                <td class="td_right td" > <input class="rg_input" type="text" placeholder="身份证号" name="identify_id" id="identify_id"></input></td>
                            </tr>
                            <tr id="tr3">
                                <td class="td_left td" > <label for="phone">联系方式</label></td>
                                <td class="td_right td" > <input class="rg_input"type="text" placeholder="联系方式" name="phone" id="phone"></input></td>
                            </tr>
                            <tr id="tr4">
                                <td class="td_left td" > <label dor="meetTime">参会时间</label></td>
                                <td class="td_right td" > <input class="rg_input"type="text" placeholder="参会时间" name="meetTime" id="meetTime"></input></td>
                            </tr>
                            <tr id="tr5">
                                <td class="td_left td" >性别</td>
                                <td  class="td_right td" >
                                    <input type="radio" name="sex" value="男">男
                                    <input type="radio" name="sex" value="女">女
                                </td>
                            </tr>
                            <tr id="tr6">
                                <td class="td_left td" >是否需要宾馆</td>
                                <td  class="td_right td" >
                                    <input type="radio" name="room" value="需要">需要
                                    <input type="radio" name="room" value="不需要">不需要
                                </td>
                            </tr>

                        </table>
                        <table class="table2">
                            <tr>
                                <td class="td_left " ><label for="checkcode1">验证码</label></td>
                                <td class="td_right" ><input class="rg_input_verification"type="text" name="checkCode" id="checkcode1" placeholder="请输入验证码">
                                    <img id="checkcode"src="/QZ/checkcode"  alt="验证码"></td>
                                <td><a id="change" href="" onclick="Change()">看不清换一行？</a></td>
                                <td  class="reminder"><%=request.getAttribute("cc_error")==null ? "" : request.getAttribute("cc_error")%></td>
                            </tr>
                            <tr>
                                <td  colspan="3" align="center"> <input class="input_submit"type="submit" value="添加"></td>
                            </tr>
                        </table>
                    </form>
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
            var tr = document.getElementsByTagName("tr");
            //默认类型为number
            var info = ${infos};
            //强转类型为string，利用typeof（）判断变量类型
            var infos = info.toString();

            for(var i=0;i<7;i++){
                //==数据类型不同可以强转之后再比较
                //===数据类型不同直接返回false
                if(infos.charAt(i)=='1'){
                    //block显示
                    //inherit继承父类属性
                    document.getElementById("tr"+i).style.display = "inherit";
                }
            }
        }

    </script>
</html>
