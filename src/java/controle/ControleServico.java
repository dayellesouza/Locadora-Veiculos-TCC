/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Servico;
import modelo.DAO.ServicoDAO;


/**
 *
 * @author 11162101222
 */
@WebServlet(name = "ControleServico", urlPatterns = {"/ControleServico"})
public class ControleServico extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset:=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //recupera param acao
            String acao = request.getParameter("acao");
            if (acao.equals("Cadastrar")) {
                String nome = request.getParameter("txtNome");
                String cpf = request.getParameter("txtCpf");
                String telefone = request.getParameter("txtTelefone");
                int situacao = 1;
                int id_tipo = Integer.parseInt(request.getParameter("cmbtipoServ"));

                Servico servico = new Servico();
                servico.setNome(nome);
              
                servico.setSituacao(situacao);

                ServicoDAO cdao = new ServicoDAO();

                cdao.cadastrar(servico);
                //redireciono
                request.setAttribute("mensagem", " Serviço cadastrado com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("sucesso.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Listar")) {
                //cria objeto
                ServicoDAO cDAO = new ServicoDAO();
                //executa o método listar
                ArrayList<Servico> servicos = cDAO.listar();
                //add a lista no obJ eto request   
                request.setAttribute("listaServico", servicos);
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("listaServico.jsp");
                rd.forward(request, response);

            } else if (acao.equals("excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Servico servico = new Servico();
                servico.setId(id);

                ServicoDAO cdao = new ServicoDAO();

                cdao.excluir(servico);
                //redireciono
                request.setAttribute("mensagem", " excluido com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("sucesso.jsp");
                rd.forward(request, response);

            } else if (acao.equals("alterar")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Servico servico = new Servico();
                servico.setId(id);

                ServicoDAO cdao = new ServicoDAO();

                cdao.buscar(servico);
                //redireciono
                request.setAttribute("servico", servico);
                
              
                
                RequestDispatcher rd = request.getRequestDispatcher("alterar.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Confirmar")) {
                
                String nome = request.getParameter("txtNome");
                String cpf = request.getParameter("txtCpf");
                String telefone = request.getParameter("txtTelefone");
                int situacao = Integer.parseInt(request.getParameter("txtSituacao"));
                int id_tipo = Integer.parseInt(request.getParameter("cmbtipoServ"));
                int id = Integer.parseInt(request.getParameter("txtId"));
                

                Servico servico = new Servico();
                servico.setNome(nome);
              
                servico.setSituacao(situacao);
                servico.setId(id);

                ServicoDAO cdao = new ServicoDAO();
                cdao.alterar(servico);
                //redireciono
                request.setAttribute("mensagem", " alterado com sucesso!");
                RequestDispatcher rd = request.getRequestDispatcher("sucesso.jsp");
                rd.forward(request, response);

            }else if (acao.equals("InterfaceCadServ")) {
                
                
               
                //encaminha o request para o JSP
                RequestDispatcher rd = request.getRequestDispatcher("viewServico.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControleServico.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControleServico.class.getName()).log(Level.SEVERE, null, ex);
        }
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
