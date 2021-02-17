<%-- 
    Document   : listaServico
    Created on : 07/11/2018, 23:47:06
    Author     : Dayelle
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Servico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de Serviços</h1>

        <%        //recuperar a lista
            ArrayList<Servico> listaServico = (ArrayList<Servico>) request.getAttribute("listaServico");
        %>
        <table border="l">
            <thead>
                <tr>
                    <th>Servico</th>
                    <th>Ferramentas</th>

                </tr>
            </thead>
            <tbody>
                <%
                    for (Servico c : listaServico) {
                %>
                <tr>
                    <td><%= c.getNome()%></td>
                    <td><a href="ControleServico?acao=excluir&id=<%= c.getId()%>"><img src="img/excluir.png" alt=""/></a><a href="ControleServico?acao=alterar&id=<%= c.getId()%>"><img src="img/editar.png" alt=""/></a></td>

                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <a href="/ControleServico?acao=InterfaceCadServ">Voltar</a> 
    </body>
</html>
