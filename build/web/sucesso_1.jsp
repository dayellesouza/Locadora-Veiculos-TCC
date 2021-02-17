<%-- 
    Document   : sucesso
    Created on : 09/03/2018, 19:41:13
    Author     : 11162500307
--%>

<%@page import="modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucesso</title>
    </head>
    <body>
        <%
            Cliente cli = (Cliente) request.getAttribute("cliente");
        %>
        <strong><font color="blue">O serviço foi <%=request.getAttribute("mensagem")%></font></strong>
        <form>
            <input type="text" value="<%= cli.getNome()%>">
            <input type="text" value="<%= cli.getCpf()%>">
            <input type="text" value="<%= request.getAttribute("data")%>">
            <input type="text" value="<%= request.getAttribute("carro")%>">
            <input type="text" value="<%= request.getAttribute("horario")%>">
        </form>

    </body>
</html>


