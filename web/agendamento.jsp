<%-- 
    Document   : agendamento
    Created on : 07/11/2018, 23:30:36
    Author     : Dayelle
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Agendamento"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="modelo.Cliente"%>
<%@page import="modelo.Carros"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script>
            function formatar(mascara, documento) {
                var i = documento.value.length;
                var saida = mascara.substring(0, 1);
                var texto = mascara.substring(i)

                if (texto.substring(0, 1) != saida) {
                    documento.value += texto.substring(0, 1);
                }

            }
        </script>
        <title>Agendamento</title>
    </head>
    <body>
        <%
            ArrayList<Carros> car = (ArrayList<Carros>) request.getAttribute("carros");
            Cliente cli = (Cliente) request.getAttribute("cliente");
        %>
        <h1>Agendamento</h1>
        <form action="controleAgendamento" method="POST">     

            <div class="row">
                <div class="form-group">
                    <label>CPF:</label>   
                    <input type="text" maxlength="14" name="txtCpf" OnKeyPress="formatar('###.###.###-##', this)" value=" <%= cli.getCpf()%>" >
                </div>
                <div class="form-group">
                    <label> Nome: </label>
                    <input type="text" maxlength="70" name="txtNome" value=" <%= cli.getNome()%>" >
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label label-default="" for="">Escolha um carro para a reserva</label>
                    <select  class="form-control" name="cmbCarro">
                        <%
                            for (Carros c : car) {
                        %>
                        <option value="<%= c.getId()%>"> <%= c.getNome()%> </option>
                        <%
                            }
                        %> 
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label label-default="" for="">Data:</label>
                    <div class="col-md-6">
                        <input name="txtdataI" type="date" required="true" requiredMessage="Digite a data ">
                    </div>
                </div>
                <div class="form-group">
                    <label label-default="" for="">Digite o Horario desejado:</label>
                    <div class="col-md-4">
                        <input name="txtHora" type="text" required="true" requiredMessage="Digite a hora"  maxlength="5" OnKeyPress="formatar('##:##', this)">
                    </div>
                </div>
            </div>
            <div>
                <input type="submit" name="acao" value="Salvar"/>
            </div>
        </form>
    </body>
</html>
