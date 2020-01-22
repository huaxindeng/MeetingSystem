<%@ page import="ncu.stu.beans.MyUser" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/3
  Time: 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>人员信息</title>
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
    <script type="text/javascript">
        function base64 (content) {
            return window.btoa(unescape(encodeURIComponent(content)));
        }
        /*
        *@tableId: table的Id
        *@fileName: 要生成excel文件的名字（不包括后缀，可随意填写）
        */
        function tableToExcel(tableID,fileName){
            var table = document.getElementById(tableID);
            var excelContent = table.innerHTML;
            var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
            excelFile += "<head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head>";
            excelFile += "<body><table>";
            excelFile += excelContent;
            excelFile += "</table></body>";
            excelFile += "</html>";
            var link = "data:application/vnd.ms-excel;base64," + base64(excelFile);
            var a = document.createElement("a");
            a.download = fileName+".xls";
            a.href = link;
            a.click();
        }
    </script>
    <body>
        <%
            MyUser user = new MyUser();
            user = (MyUser) session.getAttribute("user");
            pageContext.setAttribute("name",user.getUserNmae());
        %>
        <center>
            <div class="div3">
                <center>
                    欢迎<%= pageContext.getAttribute("name")%>
                </center>
            </div>
            <div class="div1">
                <div class="div12">
                    <table id="tab2">
                        <%--<caption>人员名单</caption>--%>
                        <tr>
                            <th ><b>姓名</b></th>
                            <th ><b>工作地点</b></th>
                            <th ><b>身份证号</b></th>
                            <th ><b>联系方式</b></th>
                            <th ><b>参会时间</b></th>
                            <th><b>性别</b></th>
                            <th><b>是否需要房间</b></th>
                        </tr>

                        <c:forEach items="${attendees}" var="item" varStatus="status">
                            <tr>
                                <td>${item.name}</td>
                                <td>${item.workplace}</td>
                                <td>${item.identify_id}</td>
                                <td>${item.phone}</td>
                                <td>${item.meetTime}</td>
                                <td>${item.sex}</td>
                                <td>${item.room}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="div11">
                    <td><a href="findAllServlet">返回</a></td>
                    <td><a href="ExcelServlet" >导出名单</a></td>
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
