<%-- 
    Document   : marcarhorario
    Created on : 25/11/2018, 18:16:06
    Author     : Dayelle
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Horario"%>
<%@page import="modelo.Agendamento"%>
<%@page import="modelo.Cliente"%>
<%@page import="modelo.Carros"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Marcar Hora</title>
    </head>
    <body>
        <%
            Carros car = (Carros) request.getAttribute("carro");
            Agendamento agenda = (Agendamento) request.getAttribute("data");
            ArrayList<Horario> hora = (ArrayList<Horario>) request.getAttribute("hora");
        %>
        <h1>Estamos quase lá!</h1>
        <form class="form-area" action="ControleAgendamento" method="post">
            <div class="row">
                <div class="form-group col-md-5">
                    <label> Nome: </label>
                    <input type="text" maxlength="70" name="txtNome"value="<%= request.getAttribute("nome")%>" >
                </div>
                <div class="form-group col-md-5">
                    <label> CPF: </label>
                    <input type="text" maxlength="70" name="txtCpf" value="<%= request.getAttribute("cpf")%>" >
                </div>
                 <%
                    SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                %>
                <div class="form-group col-md-5">
                    <label> Data: </label>
                    <input type="text" maxlength="10" name="txtDataI" value="<%= fmt.format(agenda.getData())%>" >
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-4">
                    <label> Carro: </label>
                    <input type="text" maxlength="70" name="txtCarro" value="<%= car.getNome()%>" >
                </div>
               
                <div class="form-group col-md-4">
                    <div class="col-md-5">
                        <label label-default="" for="">Escolha uma hora</label>
                        <select  class="form-control" name="cmbHora">
                            <%
                                for (Horario h : hora) {
                            %>
                            <option value="<%= h.getId()%>"> <%= h.getHora()%> </option>
                            <%
                                }
                            %> 
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <input type="submit" class="btn btn-primary" name="acao" value="Cadastrar" >
            </div>
        </form>
    </body>
</html>
