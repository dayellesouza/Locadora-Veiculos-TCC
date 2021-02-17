<%-- 
    Document   : alteraruser
    Created on : 07/11/2018, 23:35:37
    Author     : Dayelle
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
    </head>
    <body>
        <h1>Alteração de Usuário</h1>
    
    <%        //recuperar a lista
        Usuario user = (Usuario) request.getAttribute("user");
    %>
        <form action="ControleUsuario" method="post">
            Nome:     <input type="text" name="txtNome" value="<%= user.getNome()%>"><br>
            Login: <input type="text" name="txtLogin" value="<%= user.getLogin()%>"><br>
            Senha: <input type="text" name="txtSenha" value="<%= user.getSenha()%>"><br>
            <input type="hidden" name="txtId" value="<%= user.getId()%>"/>
            <input type="submit" name="acao" value="Confirmar">
        </form>
    </body>
</html>
