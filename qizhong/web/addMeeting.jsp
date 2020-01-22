<%@ page import="ncu.stu.beans.MyUser" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/1
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>添加会议</title>
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
                <p>会议信息</p><br>
            </div>
            <div class="rg_layout">
                <div class="rg_center">
                    <form method="post" action="addMeetingServlet">
                        <table class="table1">
                            <tr>
                                <td class="td_left td" ><label for="time">会议日期</label></td>
                                <td  class="td_right td" > <input class="rg_input" type="text" placeholder="会议日期，格式：xxxx-xx-xx" id="time" name="time"></td>
                            </tr>
                            <tr>
                                <td class="td_left td" > <label for="hour">会议时间</label></td>
                                <td class="td_right td" > <input class="rg_input" type="text" placeholder="具体时间：如19" name="hour" id="hour"></input></td>
                            </tr>
                            <tr>
                                <td class="td_left td" > <label for="place">会议地点</label></td>
                                <td class="td_right td" > <input class="rg_input"type="text" placeholder="会议地点" name="place" id="place"></input></td>
                            </tr>
                            <tr>
                                <td rowspan="1" class="td_left td" > <label dor="hotel">住宿宾馆</label></td>
                                <td rowspan="1" class="td_right td" > <input class="rg_input"type="text" placeholder="宾馆名称" name="hotel" id="hotel"></input></td>
                            </tr>
                            <tr >
                                <td rowspan="8" class="td_left td" >必填信息项</td>
                                <td rowspan="8"class="td">
                                    <table class="tab_info">
                                        <tr>
                                            <td class="td_right" > <input type="checkbox" name="info" value="1">姓名</td>

                                            <td class="td_right" > <input type="checkbox" name="info" value="2">工作单位</td>
                                        </tr>
                                        <tr>
                                            <td class="td_right" > <input type="checkbox" name="info" value="3">身份证号</td>

                                            <td class="td_right" > <input type="checkbox" name="info" value="4">电话</td>
                                        </tr>
                                        <tr>
                                            <td class="td_right" > <input type="checkbox" name="info" value="5">参会时间</td>

                                            <td class="td_right" > <input type="checkbox" name="info" value="6">性别</td>
                                        </tr>
                                        <tr>
                                            <td class="td_right" > <input type="checkbox" name="info" value="7">是否需要安排住房</td>

                                        </tr>
                                    </table>
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
        }

    </script>
</html>
