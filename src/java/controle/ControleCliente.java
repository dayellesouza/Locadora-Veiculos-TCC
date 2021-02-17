/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.DAO.ClienteDAO;
import modelo.DAO.EnderecoDAO;
import modelo.DAO.UsuarioDAO;
import modelo.Endereco;
import modelo.Usuario;

/**
 *
 * @author Dayelle
 */
public class ControleCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //recupera param acao

            String acao = request.getParameter("acao");

            if (acao.equals("Cadastrar")) {
                String nome = request.getParameter("txtNome");
                String nasc = request.getParameter("txtDtNasc");
                String telefone = request.getParameter("txtTelefone");
                String cpf = request.getParameter("txtCpf");
                String cnh = request.getParameter("txtCnh");
                String rg = request.getParameter("txtRg");
                String login = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                String cep = request.getParameter("txtCep");
                String rua = request.getParameter("txtRua");
                String numero = request.getParameter("txtNumero");
                int situacao = 1;
                String bairro = request.getParameter("txtBairro");
                String cidade = request.getParameter("txtCidade");
                String uf = request.getParameter("txtUF");

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date dtNasc = formato.parse(nasc);

                Endereco end = new Endereco();
                end.setCep(cep);
                end.setRua(rua);
                end.setNumero(numero);
                end.setBairro(bairro);
                end.setCidade(cidade);
                end.setUf(uf);
                

                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setDtNasc(dtNasc);
                cliente.setCpf(cpf);
                cliente.setCnh(cnh);
                cliente.setRg(rg);
                cliente.setTelefone(telefone);
                cliente.setSituacao(situacao);
                cliente.setEndereco(end);
                
                Usuario user = new Usuario();
                user.setNome(nome);
                user.setLogin(login);
                user.setSenha(senha);
                user.setCpf(cpf);

                EnderecoDAO edao = new EnderecoDAO();
                edao.cadastrar(end);

                ClienteDAO cdao = new ClienteDAO();
                cdao.cadastrar(cliente);        
                
                UsuarioDAO udao = new UsuarioDAO();
                udao.cadastrar(user);
                //redireciono
                request.setAttribute("mensagem", " cadastrado com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("sucessoCli.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Listar")) {
                //cria objeto
                ClienteDAO cDAO = new ClienteDAO();
                //executa o método listar
                ArrayList<Cliente> clientes = cDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaCliente", clientes);
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("listaCliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("alterar")) {
                int id = Integer.parseInt(request.getParameter("id"));

                Cliente cliente = new Cliente();
                cliente.setId(id);

                ClienteDAO cdao = new ClienteDAO();

                cdao.buscar(cliente);
                //redireciono
                request.setAttribute("cliente", cliente);

                RequestDispatcher rd = request.getRequestDispatcher("alterarcli.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Confirmar")) {

                String nome = request.getParameter("txtNome");
                String nasc = request.getParameter("txtDtNasc");
                String telefone = request.getParameter("txtTelefone");
                String cpf = request.getParameter("txtCpf");
                String cnh = request.getParameter("txtCnh");
                String rg = request.getParameter("txtRg");
                String login = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                String cep = request.getParameter("txtCep");
                String rua = request.getParameter("txtRua");
                String numero = request.getParameter("txtNumero");
                int situacao = 1;
                String bairro = request.getParameter("txtBairro");
                String cidade = request.getParameter("txtCidade");
                String uf = request.getParameter("txtUF");

                int id = Integer.parseInt(request.getParameter("txtId"));

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date dtNasc = formato.parse(nasc);

                Endereco end = new Endereco();
                end.setCep(cep);
                end.setRua(rua);
                end.setNumero(numero);
                end.setBairro(bairro);
                end.setCidade(cidade);
                end.setUf(uf);

                Usuario user = new Usuario();
                user.setNome(nome);
                user.setLogin(login);
                user.setSenha(senha);
                user.setCpf(cpf);
                

                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setDtNasc(dtNasc);
                cliente.setCpf(cpf);
                cliente.setCnh(cnh);
                cliente.setRg(rg);
                cliente.setTelefone(telefone);
                cliente.setSituacao(situacao);
                cliente.setEndereco(end);

                EnderecoDAO edao = new EnderecoDAO();
                edao.cadastrar(end);

                ClienteDAO cdao = new ClienteDAO();
                cdao.alterar(cliente);        
                
                UsuarioDAO udao = new UsuarioDAO();
                udao.alterar(user);
                //redireciono
                request.setAttribute("mensagem", " cadastrado com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("sucessoCli.jsp");
                rd.forward(request, response);
                //redireciono
                
                

            } else if (acao.equals("interfaceCADCLI")) {
                RequestDispatcher rd = request.getRequestDispatcher("cadastrarcli.jsp");
                rd.forward(request, response);

            }
        } catch (Exception e) {
            request.setAttribute("erro", e);
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        try {
            if (acao.equals("interfaceCADCLI")) {
                RequestDispatcher rd = request.getRequestDispatcher("cadastrarcli.jsp");
                rd.forward(request, response);

            }
        } catch (Exception e) {
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
