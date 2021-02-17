/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Agendamento;
import modelo.Carros;
import modelo.Cliente;
import modelo.DAO.AgendamentoDAO;
import modelo.DAO.CarroDAO;
import modelo.DAO.ClienteDAO;
import modelo.DAO.HorarioDAO;
import modelo.DAO.UsuarioDAO;
import modelo.Horario;

/**
 *
 * @author Dayelle
 */
public class controleAgendamento extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */

            String acao = request.getParameter("acao");
            if (acao.equals("Listar")) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date data = formato.parse(request.getParameter("txtdata"));

                Agendamento agendamento = new Agendamento();
                agendamento.setData(data);

                AgendamentoDAO agdao = new AgendamentoDAO();
                ArrayList<Agendamento> arragendamentos = new ArrayList<>();
                arragendamentos = agdao.listar(agendamento);

                request.setAttribute("listaAgendamentos", arragendamentos);
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("listaAgendamento.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Agendar")) {
                String cpf = request.getParameter("cpf");

                ClienteDAO cdao = new ClienteDAO();
                Cliente cliente = new Cliente();
                cliente.setCpf(cpf);
                cliente = cdao.buscar(cliente);

                CarroDAO carDAO = new CarroDAO();
                ArrayList<Carros> car = carDAO.listar();

                request.setAttribute("carros", car);
                request.setAttribute("cliente", cliente);
                RequestDispatcher rd = request.getRequestDispatcher("agendamento.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Salvar")) {
                
                String novadata = request.getParameter("txtdataI");
                String idcpf = request.getParameter("txtCpf");
                String nome = request.getParameter("txtNome");
                int idcarro = Integer.parseInt(request.getParameter("cmbCarro"));
                String hora = request.getParameter("txtHora");

                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                Date data = formato.parse(novadata);

                CarroDAO cardao = new CarroDAO();
                Carros carros = new Carros();
                carros.setId(idcarro);
                carros = cardao.buscar(carros);

                ClienteDAO cdao = new ClienteDAO();
                Cliente cliente = new Cliente();
                cliente.setCpf(idcpf);
                cliente = cdao.buscar(cliente);

                cliente.setCpf(idcpf);
                cliente.setNome(nome);

                Agendamento agenda = new Agendamento();
                agenda.setCliente(cliente);
                agenda.setData(data);
                agenda.setHora(hora);
                agenda.setCarros(carros);
                

                request.setAttribute("nome", cliente.getNome());
                request.setAttribute("cpf", cliente.getCpf());
                request.setAttribute("data", novadata);
                request.setAttribute("carro", carros.getNome());
                request.setAttribute("horario", hora);
                RequestDispatcher rd = request.getRequestDispatcher("sucesso_1.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Cadastrar")) {
                String novadata = request.getParameter("txtData");
                String cpf = request.getParameter("txtCpf");
                String nome = request.getParameter("txtNome");
                int idcarro = Integer.parseInt(request.getParameter("txtCarro"));
                String idhora = request.getParameter("cmbHora");

                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                Date data = formato.parse(novadata);

                SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
                Date datas = formatador.parse(idhora);
                Time time = new Time(datas.getTime());

                CarroDAO cardao = new CarroDAO();
                Carros carros = new Carros();
                carros.setId(idcarro);
                carros = cardao.buscar(carros);

                HorarioDAO hdao = new HorarioDAO();
                Horario hora = new Horario();
                hora.setHora(time);
                hora = hdao.buscar(hora);

                Cliente cliente = new Cliente();
                cliente.setCpf(cpf);
                cliente.setNome(nome);

                Agendamento agenda = new Agendamento();
                agenda.setData(data);
                agenda.setCarros(carros);
                agenda.setCliente(cliente);

                AgendamentoDAO adao = new AgendamentoDAO();
                adao.cadastrar(agenda);

                request.setAttribute("mensagem", " Voce obteve sucesso");
                RequestDispatcher rd = request.getRequestDispatcher("Sucesso.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Listar2")) {
                String cpf = request.getParameter("cpf");

                ClienteDAO cdao = new ClienteDAO();
                Cliente cliente = new Cliente();
                cliente.setCpf(cpf);
                cliente = cdao.buscar(cliente);

                Agendamento agendamento = new Agendamento();
                agendamento.setCliente(cliente);

                AgendamentoDAO agdao = new AgendamentoDAO();
                ArrayList<Agendamento> arragendamentos = new ArrayList<Agendamento>();
                arragendamentos = agdao.listar2(agendamento);

                request.setAttribute("listaAgendamentos", arragendamentos);
                //encaminha o request para o J SP
                RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
                rd.forward(request, response);
            }

        } catch (ParseException ex) {
            Logger.getLogger(controleAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(controleAgendamento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controleAgendamento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controleAgendamento.class.getName()).log(Level.SEVERE, null, ex);
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
