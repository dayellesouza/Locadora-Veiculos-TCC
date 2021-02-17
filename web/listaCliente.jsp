<%-- 
    Document   : listaCliente
    Created on : 07/11/2018, 23:44:40
    Author     : Dayelle
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <title>Senna Rent A Car</title>
    </head>
    <body>
    <hl>Lista de Clientes</hl>
        <%        //recuperar a lista
            ArrayList<Cliente> listaCliente = (ArrayList<Cliente>) request.getAttribute("listaCliente");
        %>
    <table border="l">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Ferramentas</th>

            </tr>
        </thead>
        <tbody>
            <%
                for (Cliente c : listaCliente) {
            %>
            <tr>
                <td><%= c.getId()%></td>
                <td><%= c.getNome()%> </td>
                <td><%= c.getTelefone()%> </td>

                <td><a href="ControleCliente?acao=excluir&id=<%= c.getId()%>"><img src="img/excluir.png" alt=""/></a><a href="ControleCliente?acao=alterar&id=<%= c.getId()%>"><img src="img/editar.png" alt=""/></a></td>

            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
