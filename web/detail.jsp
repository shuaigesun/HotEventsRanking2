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
</head>
<body>
      <h2>${hotEvents.keyWord}</h2>
      <div>创建于<fmt:formatDate value="${hotEvents.createDate}" pattern="yyyy-MM-dd"/></div>
      <div>
          ${hotEvents.hotContent}
      </div>
      <h2>评论</h2>

      <form action="">
      <input type="hidden" name="id" value="${hotEvents.id}"/>
      <div id="comment">
           <c:forEach var="list" items="${list}">
               <div style="background:darkgray">
                   评论时间:<fmt:formatDate value="${list.commentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <br/>
                   ${list.content}
               </div>
               <br/>
           </c:forEach>
      </div>


    <tr><td>快速评论</td></tr>
    <tr>
        <textarea name="content" id="" cols="30" rows="10"></textarea><span id="pankong" style="color:red"></span>
    </tr>
          <br/>
    <tr>
        <td><input type="button" value="提交"/></td>
        <td><a href="HotEventsServlet?action=list">返回首页</a></td>
    </tr>
</form>
<script type="text/javascript">
    $(function(){
        $("input[type='button']").click(function(){
            var content = $("[name=content]").val();
            if(content==""){
                 $("#pankong").html("请填写评论内容");
                 return false;
            }
            $.ajax({
                url:"HotEventsServlet?action=addComments",
                type:"post",
                data:$("form").serialize(),
                dataType:"json",
                success:function(data){
                    $("form").prepend("<tr><td>评论时间:"+data.commentDate+"<br/>"+data.content+"</td></tr>");
                }
            });
            $("#comment").prepend()
        });
    });
</script>

</body>
</html>
