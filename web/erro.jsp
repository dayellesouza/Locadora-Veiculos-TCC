<%-- 
    Document   : erro
    Created on : 09/03/2018, 19:41:23
    Author     : 11162500307
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Erro!</h2>
        
        <%      
            Exception erro = (Exception) request.getAttribute("erro");
          %>
        <%=erro.getMessage() %>
    </body>
</html>

