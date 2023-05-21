<%-- 
    Document   : erroautenticacao
    Created on : 10/05/2022, 17:34:59
    Author     : proft
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Página de Autenticação mal sucedida</h1>
        <BR>
        <% String m = (String) request.getAttribute("mensagem");%>
        <%=m%>
    </body>
</html>
