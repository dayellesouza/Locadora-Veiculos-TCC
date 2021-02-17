<%-- 
    Document   : alterarcli
    Created on : 07/11/2018, 23:34:11
    Author     : Dayelle
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestão Clientes</title>
    </head>
    <h1>Alteração de Cliente</h1>
    <%        //recuperar a lista
        Cliente cliente = (Cliente) request.getAttribute("cliente");
    %>
    <body>
        <form action="ControleCliente" method="post">
            Nome:     <input type="text" name="txtNome" value="<%= cliente.getNome()%>"><br>
            CPF:      <input type="text" name="txtCpf" value="<%= cliente.getCpf()%>"><br>
            Telefone: <input type="text" name="txtTelefone" value="<%= cliente.getTelefone()%>"><br>
            Endereço: <input type="text" name="txtEndereco" value="<%= cliente.getEndereco()%>"><br>
            <input type="hidden" name="txtId" value="<%= cliente.getId()%>"/>
            <input type="submit" name="acao" value="Confirmar">
            <input type="submit" name="acao" value="Listar">
        </form>
    </body>
</html>
