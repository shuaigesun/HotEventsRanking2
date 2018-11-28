<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Haohao
  Date: 2018-11-13
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/page.js"></script>
</head>
<body>
    <form action="HotEventsServlet?action=list" method="post">
        <table border="1px">
            <tr>
                <td colspan="3">关键字:<input type="text" name="keyWord" value="${keyWord}"><input type="submit" value="查询"/></td>
            </tr>
            <tr>
                <td colspan="3"><h2>热点事件排行榜</h2></td>
            </tr>
                <tr>
                    <td>关键词</td>
                    <td>搜索指数</td>
                    <td>创建时间</td>
                </tr>
            <c:forEach items="${list}" var="list">
                <tr>
                    <td><a href="HotEventsServlet?action=findById&id=${list.id}">${list.keyWord}</a></td>
                    <td>${list.searchSum}</td>
                    <td><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <input  type="hidden" name="pageNo" value="${pageBean.pageNo}"/>
                    <input  type="hidden" name="pageTotal" value="${pageBean.pageTotal}"/>
                    <a href="javascript:void(0);" onclick="first(1)">首页</a>
                    <a href="javascript:void(0);" onclick="prev(${pageBean.pageNo})">上一页</a>
                    <a href="javascript:void(0);" onclick="next(${pageBean.pageNo})">下一页</a>
                    <a href="javascript:void(0);" onclick="last(${pageBean.pageTotal})">末页</a>
                    第${pageBean.pageNo}页/共${pageBean.pageTotal}页
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
