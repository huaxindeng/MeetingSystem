<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/4
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <script type="text/javascript">
        function base64 (content) {
            return window.btoa(unescape(encodeURIComponent(content)));
        }
        /*
        * *@tableId: table的Id
         *@fileName: 要生成excel文件的名字（不包括后缀，可随意填写）
        */
        function tableToExcel(tableID,fileName){
            var table = document.getElementById(tableID);
            var excelContent = table.innerHTML;
            var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
            excelFile += "<head></head>";
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
        <table id="item" border="0" cellspacing="0" cellpadding="0" >
            <thead >
            <td>姓名</td>
            <td>年龄</td>
            <td>生日</td>
            <td>电话</td>
            <td>QQ</td>
            <td>学生班级</td>
            <td>学生小组</td>
            <td>学生就业城市</td>
            <td>学生标签</td>
            </thead>
            <c:forEach items="${requestScope.students}" var="stu">
                <tr>
                    <td>${stu.name}</td>
                    <td>${stu.age}</td>
                    <td><fmt:formatDate value="${stu.bir}" pattern="yyyy-MM-dd"/></td>
                    <td>${stu.phone}</td>
                    <td>${stu.qq}</td>
                    <td>${stu.clazz.name}</td>
                    <td>${stu.team.name}</td>
                    <td>${stu.city.name}</td>
                    <td>
                        <c:forEach items="${stu.tags}" var="tag">
                            ${tag.name}&nbsp;
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <hr/>
        <a href="#" οnclick="tableToExcel('item','data')">导出学生信息</a>
    </body>
</html>


