<%-- 
    Document   : sucessoCli
    Created on : 07/11/2018, 23:53:51
    Author     : Dayelle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <style>
            .mydiv {
                height: 10em;
                margin: 0;
                position: absolute;
                top: 50%;
                left: 50%;
                margin-right: -50%;
                transform: translate(-50%, -50%);
            }
        </style>
        <title>Sucesso</title>
    </head>
    <body>
        <div class="container">
            <div class="mydiv">
                <div>
                    <h1><strong><font color="blue">Você foi <%=request.getAttribute("mensagem")%></font></strong></h1>
                    <br>
                    <h1><strong><font color="blue">Retorne para a página de inicio clicando no botão abaixo :)</font></strong></h1>
                </div>
                    <br>
                <div align="center">
                    <a href="agendar.jsp" class="btn btn-primary">Inicio</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
