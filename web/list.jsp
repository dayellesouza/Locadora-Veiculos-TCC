<%-- 
    Document   : list
    Created on : 27/11/2018, 07:45:18
    Author     : Dayelle
--%>

<%@page import="modelo.Agendamento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
            private ArrayList<Agendamento> listaAgendamento;
        %>
        <%       //recuperar a lista

            listaAgendamento = (ArrayList<Agendamento>) request.getAttribute("listaAgendamentos");


        %>

        <h1>Hello World!</h1>
        <% for (Agendamento ag : listaAgendamento) {%>
    <td> <%= ag.getCliente()%></td>  
    <td> <%= ag.getData()%></td>  
    <td> <%= ag.getHora()%></td>  
    <td> <%= ag.getServico()%></td> 
    <td> <%= ag.getId()%></td>  
    <%}%>
</body>
</html>
