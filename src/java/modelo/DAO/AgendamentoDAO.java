/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import Util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modelo.Agendamento;
import modelo.Cliente;
import modelo.Endereco;
import modelo.Horario;
import modelo.Servico;

/**
 *
 * @author user
 */
public class AgendamentoDAO {

    private static final String SELECT_PORDATA = "select agenda.*,cliente.cpf as idcliente,cliente.telefone,cliente.nome as nomecli,servico.nome as nomeserv,from agenda \n"
            + "inner join cliente on (agenda.cliente = cliente.cpf)\n"
            + "inner join servico on (agenda.servico = servico.id)\n"
            + "WHERE data=? order by data asc,hora asc,datafinal asc,horafinal asc;";
    private static final String SALVAR = "insert into agenda (cliente,data,hora,carros) values (?,?,?,?)";
    private static final String BUSCAR_HORARIOS = "select hora from agendamento where data = ? and carro = ?;";
    private static final String BUSCAR_TODOS_HORARIOS = "select * from horario;";

    private static final String SELECT = "select agenda.*,cliente.cpf as idcliente,cliente.telefone,cliente.nome as nomecli,servico.nome as nomeserv,from agenda \n"
            + "inner join cliente on (agenda.cliente = cliente.cpf)\n"
            + "inner join servico on (agenda.servico = servico.id)\n"
            + "WHERE cpf=? order by data asc,hora asc;";
    private Object pstm;

    public boolean cadastrar(Agendamento agendamento) throws SQLException, ClassNotFoundException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SALVAR);

            pstmt.setString(1, agendamento.getCliente().getCpf());
            pstmt.setDate(2, new java.sql.Date(agendamento.getData().getTime()));
            pstmt.setString(3, agendamento.getHora());
            pstmt.setInt(4, agendamento.getCarros().getId());
            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }
    }

    public ArrayList<Agendamento> listar2(Agendamento agenda) throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        ArrayList<Agendamento> listaAgendamento = new ArrayList<Agendamento>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT);
        pstmt.setString(1, agenda.getCliente().getCpf());
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Agendamento novoAgendamento = new Agendamento();
            novoAgendamento.setId(rs.getInt("id"));
            novoAgendamento.setCliente(new Cliente(rs.getInt("cliente"), rs.getString("nomecli"), rs.getString("telefone")));
            novoAgendamento.setServico(new Servico(rs.getInt("servico"), rs.getString("nomeserv")));
            novoAgendamento.setData(rs.getDate("data"));
            novoAgendamento.setHora(rs.getString("hora"));

            //add na lista
            listaAgendamento.add(novoAgendamento);
        }
        return listaAgendamento;
    }

    public ArrayList<Agendamento> listar(Agendamento agendamento) throws ClassNotFoundException, SQLException {
        //cria uma array de obJ Cliente
        ArrayList<Agendamento> listaAgendamento = new ArrayList<Agendamento>();
        Connection conexao = null;

        try {
            //Conexao
            conexao = ConectaBanco.getConexao();
            //cria comando SQL

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_PORDATA);
            Calendar cal = Calendar.getInstance();
            cal.setTime(agendamento.getData());

            pstmt.setDate(1, new java.sql.Date((cal.getTime()).getTime()));

            //executa
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                //a cada loop
                Agendamento novoAgendamento = new Agendamento();
                novoAgendamento.setId(rs.getInt("id"));
                novoAgendamento.setCliente(new Cliente(rs.getInt("cliente"), rs.getString("nomecli"), rs.getString("telefone")));
                novoAgendamento.setServico(new Servico(rs.getInt("servico"), rs.getString("nomeserv")));
                novoAgendamento.setData(rs.getDate("data"));
                novoAgendamento.setHora(rs.getString("hora"));

                //add na lista
                listaAgendamento.add(novoAgendamento);
            }
        } catch (SQLException ex) {
            //
        } finally {
            conexao.close();
        }
        return listaAgendamento;
    }

    public List<Horario> listar_Hora(Date data, int carros) throws SQLException {
        ArrayList<Date> listadata = new ArrayList<>();
        ArrayList<Horario> listahora = new ArrayList<>();
        Agendamento agenda = new Agendamento();
        agenda.setData(data);
        try {

            Connection conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR_HORARIOS);

            pstmt.setDate(1, new java.sql.Date(agenda.getData().getTime()));
            pstmt.setInt(2, carros);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                //a cada loop
                Horario horario = new Horario();
                listadata.add(rs.getTime("hora"));

            }

            conexao.close();

            conexao = null;
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt2 = conexao.prepareStatement(BUSCAR_TODOS_HORARIOS);
            ResultSet rs2 = pstmt2.executeQuery();

            while (rs2.next()) {

                //a cada loop
                Horario horario = new Horario();
                horario.setHora(rs2.getTime("hora"));
                boolean teste2 = listahora.contains(horario.getHora());

                if (teste2 == true) {
                } else {
                    listahora.add(horario);
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }

        return listahora;

    }

}
