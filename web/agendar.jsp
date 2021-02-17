<%-- 
    Document   : agendar
    Created on : 20/11/2018, 23:06:23
    Author     : Dayelle
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Agendar</title>

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
    </head>
    <body>
        <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
        %>
        <h1 name="usr"> Bem vindo (a),  <%= usuario.getNome()%> </h1>
        <div class="form-control">
            <form action="controleAgendamento" method="post">

                <div class="row">
                    <div class="for-group">
                        <label>Digite o seu cpf:</label>
                        <input type="text" maxlength="14" name="cpf" OnKeyPress="formatar('###.###.###-##', this)">
                    </div>
                    <div class="row-fluid">
                        <input type="submit" name="acao" value="Agendar" class="btn btn-info" />
                    </div>
                    <div class="row-fluid">
                        <input type="submit" name="acao" value="Listar2" class="btn btn-info" />
                    </div>
                    <h2>
                        <a href="ControleUsuario?acao=Sair" >-Sair</a><br>
                    </h2>
                </div>
            </form>
        </div>
    </body>
</html>
