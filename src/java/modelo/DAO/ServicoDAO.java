package modelo.DAO;

import Util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Servico;


public class ServicoDAO {

    private static final String INSERT = "INSERT INTO servico(nome, situacao,) values(?,?);";
    private static final String SELECT_ALL = "SELECT servico.*,situacao.nome FROM servico inner join situacao on (servico.situacao = situacao.id and servico.situacao<>3);";
    private static final String DELETE = "UPDATE servico set situacao=3 where id=?";
    private static final String BUSCAR = "SELECT * FROM servico WHERE id=?;";
    private static final String UPDATE = "UPDATE servico set nome=?, situacao=? WHERE id=?";

    private Object pstm;

    public boolean cadastrar(Servico servico) throws SQLException, ClassNotFoundException {

        Connection conexao = null;
        

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, servico.getNome());
            pstmt.setInt(2, servico.getSituacao());
            pstmt.execute();
            return true;

        } catch (Exception ex) {
            

            return false;

        } finally {

            conexao.close();
        }
    }

    public ArrayList<Servico> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Servico
        ArrayList<Servico> listaServico = new ArrayList<Servico>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Servico novoServico = new Servico();
            novoServico.setId(rs.getInt("id"));
            novoServico.setNome(rs.getString("nome"));
            novoServico.setSituacao(rs.getInt("situacao"));
            //add na lista
            listaServico.add(novoServico);
        }
        return listaServico;
    }

    public boolean excluir(Servico servico) throws SQLException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, servico.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }

    public void buscar(Servico servico) {

        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setInt(1, servico.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            servico.setNome(rs.getString("nome"));
            servico.setSituacao(rs.getInt("situacao"));

        } catch (Exception e) {

            //
        }
    }

    public boolean alterar(Servico servico) throws SQLException {
        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, servico.getNome());
            pstmt.setInt(2, servico.getSituacao());
            pstmt.setInt(3, servico.getId());

            
            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }
}
