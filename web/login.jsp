
<!------ Include the above in your HEAD tag ---------->


<html>
    <head>  
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/> 
        <link href="csslogin/meuestilo.css" rel="stylesheet" type="text/css"/>
        <!------ Include the above in your HEAD tag ---------->
        
    </head>
    <body id="LoginForm">
            <div style="background-color:#f1f1f1">
                <center>
                    <h1 class="form-heading">Área de login</h1>
                    <div class="login-form">
                        <h2>Faça o login </h2>
                        <%        //recuperar a lista
                            String msg = (String) request.getAttribute("msg");
                            if (msg != null) {
                        %>
                        <font color="red"> <%=msg%></font>
                        <%}%>
                        <form class="form-horizontal" role="form" id="Login" action="ControleUsuario"method="post">
                            <div class="container" >
                                <div class="filho">
                                    <div class="row">
                                        <div class="form-group col-20 ">
                                            <input type="email" class="form-control" name="email"  required="true" requiredMessage="Digite o email" maxlength="100" placeholder="Endereço de e-mail">
                                        </div>
                                        <div class="form-group col-20">
                                            <input type="password" name="senha" class="form-control"  required="true" requiredMessage="Digite a senha" maxlength="20" placeholder="Senha">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container" style="background-color:#f1f1f1">
                               <!-- <div class="forgot"> -->
                                 <!-- <a href="reset.html">Esqueci a senha?</a> -->
                                <!-- </div> -->
                                <button type="submit" name="acao"value="entrar" class="btn btn-primary">ENTRAR</button>
                                <a href="index.html" class="btn cancelbtn">CANCELAR</a>                                
                                <div class="forgot">
                                    <a href="cadastrarcli.jsp">Cadastrar-se</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </center>
            </div>
    </body>
</html>
