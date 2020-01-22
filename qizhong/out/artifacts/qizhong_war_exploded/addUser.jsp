<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/11/27
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>注册页面</title>
    </head>
    <style>
        body{
            /*设置body背景图片*/
            background-image: url("./imgs/b2.jpg");
        }
        caption{
            text-align: left;
        }
        center{
            margin-top: 100px;
            font-size: 30px;
        }
        .reminder{
            color: red;
        }


        /*最大盒子*/
        .rg_layout{
            width: 550px;
            height: 350px;
            border: 5px solid #8b8e99;
            background-color: white;
            /*水平居中*/
            margin: auto;
            /*上边距*/
        }


        /*中间盒子*/
        .rg_center{
            width: 550px;
            height: 350px;
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

        /*.td{*/
        /*border: 1px solid black;*/
        /*}*/

        form{
            width: 80%;
            height: 80%;
        }
        .please{
            font-size: 20px;
        }
    </style>
    <body>
        <script>
            document.getElementById("checkCode1").value="";
            document.getElementById("username").value="";
            document.getElementById("password").value="";
            document.getElementById("affirm").value="";
        </script>
        <center>
            <div class="rg_layout">
                <div class="rg_center">
            <form action="addUserServlet" method="post" >

                <table>
                    <tr class="please">
                        <td  class="td_left td">欢迎注册</td>
                        <td class="reminder td_right">
                            <%=request.getAttribute("please")==null ? "" : request.getAttribute("please")%>
                            <%=request.getAttribute("null_error")==null ? "" : request.getAttribute("null_error")%>
                        </td>
                    </tr>
                    <tr>
                        <td  class="td_left td">用户名:</td>
                        <td class="td_right"><input class="rg_input" type="text" name="username" id="username"></td>
                        <td class="reminder"><%=request.getAttribute("repetition_error")==null ? "" : request.getAttribute("repetition_error")%></td>
                    </tr>
                    <tr>
                        <td class="td_left td">密码:</td>
                        <td class="td_right"><input class="rg_input" type="password" name="password" id="password"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="td_left td">确认密码:</td>
                        <td class="td_right"><input class="rg_input" type="password" name="affirm" id="affirm"></td>
                        <td class="reminder"><%=request.getAttribute("affirm_error")==null ? "" : request.getAttribute("affirm_error")%></td>
                    </tr>
                    <tr>
                        <td class="td_left td">验证码</td>
                        <td class="td_right"><input class="rg_input_verification" type="text" name="checkCode" id="checkCode1">
                            <img id="checkcode" alt="验证码" src="/QZ/checkcode" >
                        </td>
                        <td><a id="change" href="" onclick="Change()">看不清换一行？</a></td>
                        <td class="reminder"><%=request.getAttribute("cc_error")==null ? "" : request.getAttribute("cc_error")%></td>


                    </tr>
                </table>
               <input class="input_submit" type="submit" value="注册" >
               <input class="input_submit" type="reset" value="重置">
            </form>
                </div>
            </div>
        </center>
    </body>
    <script>
        // 更新图片
        window.onload = function () {

            //获取图片对象
            var img = document.getElementById("checkcode");
            img.onclick = function Change() {
                //加时间戳
                img.src = "/QZ/checkcode？"+Date().getTime();
            }
        };

    </script>
</html>
