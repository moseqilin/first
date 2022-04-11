<%--
  Created by IntelliJ IDEA.
  User: 15508
  Date: 2022/4/10
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>十宗罪</title>
    <script src="jquery-3.6.0.js"></script>
    <script>

        $(function (){

            $("#bb").click(function (){
                $.ajax({
                    url:"${pageContext.request.getContextPath()}/next.do",
                    dataType:"html",
                    success: function (res){
                        $("#s").html(res)
                    },
                    error: function (){
                        alert(2)
                    }
                })
            })
        })
        $(function (){
            $("#b").click(function (){
                $.ajax({
                    url:"${pageContext.request.getContextPath()}/pre.do",
                    dataType:"html",
                    success: function (res){
                        if((String(res).length) > 90) {
                            $("#s").html(res)
                        } else {
                            alert(res)
                        }
                    },
                    error: function (){
                        alert(2)
                    }
                })
            })
        })
            <%--$.ajax({--%>
            <%--    url:"${pageContext.request.getContextPath()}+'/next.do'",--%>
            <%--    dataType:"html",--%>
            <%--    type:"post",--%>
            <%--    success: function (data){--%>
            <%--        document.getElementById("s").html(data)--%>
            <%--    }--%>
            <%--})--%>
        <%--    var i = 0--%>
        <%--    window.location.href="${pageContext.request.getContextPath()}/next.do"--%>
        <%--}--%>

        <%--})--%>
    </script>
</head>
<body>

<span id="s" style="width: 100%;
    white-space: pre-wrap">${data}</span><br>
<button id="b" style="position: absolute;left: 40%">上一页</button>
<button id="bb" style="position: absolute;right: 40%">下一页</button>
</body>
</html>


