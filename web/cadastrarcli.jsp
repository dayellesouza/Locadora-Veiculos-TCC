<%-- 
    Document   : cadastrarcli
    Created on : 07/11/2018, 23:37:00
    Author     : Dayelle
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="js/function.js" type="text/javascript"></script>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <!-- Adicionando Javascript -->
        <script type="text/javascript" >

            function limpa_formulário_cep() {
                //Limpa valores do formulário de cep.
                document.getElementById('rua').value = ("");
                document.getElementById('bairro').value = ("");
                document.getElementById('cidade').value = ("");
                document.getElementById('uf').value = ("");
            }

            function meu_callback(conteudo) {
                if (!("erro" in conteudo)) {
                    //Atualiza os campos com os valores.
                    document.getElementById('rua').value = (conteudo.logradouro);
                    document.getElementById('bairro').value = (conteudo.bairro);
                    document.getElementById('cidade').value = (conteudo.localidade);
                    document.getElementById('uf').value = (conteudo.uf);
                } //end if.
                else {
                    //CEP não Encontrado.
                    limpa_formulário_cep();
                    alert("CEP não encontrado.");
                }
            }

            function pesquisacep(valor) {

                //Nova variável "cep" somente com dígitos.
                var cep = valor.replace(/\D/g, '');
                //Verifica se campo cep possui valor informado.
                if (cep !== "") {

                    //Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;
                    //Valida o formato do CEP.
                    if (validacep.test(cep)) {

                        //Preenche os campos com "..." enquanto consulta webservice.
                        document.getElementById('rua').value = "...";
                        document.getElementById('bairro').value = "...";
                        document.getElementById('cidade').value = "...";
                        document.getElementById('uf').value = "...";
                        //Cria um elemento javascript.
                        var script = document.createElement('script');
                        //Sincroniza com o callback.
                        script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';
                        //Insere script no documento e carrega o conteúdo.
                        document.body.appendChild(script);
                    } //end if.
                    else {
                        //cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } //end if.
                else {
                    //cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
            }
            ;

            function formatar(mascara, documento) {
                var i = documento.value.length;
                var saida = mascara.substring(0, 1);
                var texto = mascara.substring(i)

                if (texto.substring(0, 1) != saida) {
                    documento.value += texto.substring(0, 1);
                }

            }
        </script>
        <title>Gestão Clientes</title>
    </head>
    <h1>Cadastro de Clientes</h1>
    <body>
        <div class="container-fluid container_reset">
            <div class="row-fluid align-items-center">
                <form name=index class="form-horizontal" role="form" action="ControleCliente" method="post">
                    <script src="js/function.js" type="text/javascript"></script>

                    <div class="row">
                        <div class="form-group col-md-6">                        
                            <label> Nome: </label>  
                            <input type="text" name="txtNome" maxlength="70"  required="true" requiredMessage="Digite o nome" class="form-control" autofocus><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-3">
                            <label> Data de nascimento:  </label>   
                            <input type="date" name="txtDtNasc" maxlength="10" OnKeyPress="formatar('##/##/####', this)" class="form-control" requiredMessage="Insira a data de nascimento" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" min="1950-01-01" max="1999-12-31"/><br>
                        </div>
                        <div class="form-group col-md-3">                            
                            <label> Telefone: </label> 
                            <input type="text" name="txtTelefone" OnKeyPress="formatar('##-#####-####', this)" placeholder="Ex.: (xx)xxxxx-xxxx" maxlength="13" class="form-control" requiredMessage="Insira seu telefone"><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-3">
                            <label> CPF: </label>      
                            <input type="text" name="txtCpf" placeholder="Ex.: xxx.xxx.xxx-xx" maxlength="14"  OnKeyPress="formatar('###.###.###-##', this)" class="form-control" requiredMessage="Insira seu CPF"><br>
                        </div>
                        <div class="form-group col-md-3">
                            <label> CNH: </label>      
                            <input type="text" name="txtCnh" maxlength="11" class="form-control" requiredMessage="Insira sua CNH"><br>
                        </div>
                        <div class="form-group col-md-3">
                            <label> RG : </label>      
                            <input type="text" name="txtRg" placeholder="Ex.: xx.xxx.xxx-x" maxlength="12" OnKeyPress="formatar('##.###.###-#', this)"class="form-control" requiredMessage="Insira seu RG"><br>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label> Email: </label>     
                            <input type="email" name="txtLogin" class="form-control" maxlength="100" requiredMessage="Digite o email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"><br> 
                        </div>
                        <div class="form-group col-md-3">                        
                            <label> Senha:  </label>    
                            <input type="password" id="txtSenha" name="txtSenha" class="form-control" maxlength="20" requiredMessage="Digite a senha"><br>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-3">                        
                            <label> CEP: </label>
                            <div class="input-group mb-2">
                                <input type="text" id="cep" name="txtCep" id="cep" value="" size="10" maxlength="9"
                                       onblur="pesquisacep(this.value);"  class="form-control" requiredMessage="Digite um cep para busca">
                                <div class="input-group-prepend">
                                    <div class="input-group-append">
                                        <input type="button" id="btnbuscacep" class="btn btn-info" value="Buscar"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-6">
                            <label> Logradouro: </label>
                            <input type="text" id="rua" name="txtRua" size="70" class="form-control" maxlength="10">
                        </div>

                        <div class="form-group col-md-1">
                            <label> Numero: </label>
                            <input type="text" name="txtNumero" size="6" class="form-control" maxlength="5" requiredMessage="insira o numero">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-3">
                            <label> Bairro: </label>
                            <input type="text" id="bairro" name="txtBairro" size="50" class="form-control" maxlength="30">
                        </div>

                        <div class="form-group col-md-3">
                            <label> Cidade: </label>
                            <input type="text" id="cidade" name="txtCidade" size="50" class="form-control" maxlength="30">
                        </div>

                        <div class="form-group col-2">
                            <label> UF: </label>
                            <input type="text" id="uf" name="txtUF" size="2" class="form-control" maxlength="2">
                        </div>
                    </div>

                    <input type="submit" class="btn btn-primary" name="acao" value="Cadastrar">
                </form>
            </div>
        </div>
    </div>  
</body>
</html>
