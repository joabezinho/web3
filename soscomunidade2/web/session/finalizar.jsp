<%
    if (request.getSession().getAttribute("autenticado") != null) {
        request.getSession().invalidate();
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="2; url=../index.html"> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Finalizando sessão...</p>

    </body>
</html>
