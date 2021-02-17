package modelo.DAO;

import Util.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Endereco;

public class ClienteDAO {

    private static final String INSERT = "INSERT INTO cliente(nome,dt_nasc,cpf,cnh, rg, telefone,situacao, endereco) values(?,?,?,?,?,?,?,?);";
    private static final String SELECT_ALL = "SELECT * FROM cliente;";
    private static final String DELETE = "UPDATE cliente where cpf=?";
    private static final String BUSCAR = "SELECT * FROM cliente WHERE cpf=?;";
    private static final String UPDATE = "UPDATE cliente SET nome=?, dt_nasc=?, cpf=?, cnh=?, rg=?, telefone=?, endereco=? WHERE id=?;";
    private static final String MOSTRAR = "select cliente.*, usuario.*, endereco.* from cliente inner join usuario on (usuario.cpf = cliente.cpf)\n"
            + "inner join endereco on (cliente.endereco = endereco.cep)\n"
            + "where usuario.login=? and usuario.senha=?";

    private Object pstm;

    public boolean cadastrar(Cliente cliente) throws SQLException, ClassNotFoundException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, cliente.getNome());
            pstmt.setDate(2, new java.sql.Date(cliente.getDtNasc().getTime()));
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getCnh());
            pstmt.setString(5, cliente.getRg());
            pstmt.setString(6, cliente.getTelefone());
            pstmt.setInt(7, cliente.getSituacao());
            pstmt.setString(8, cliente.getEndereco().getCep());

            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }
    }

    public ArrayList<Cliente> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Cliente novoCliente = new Cliente();
            novoCliente.setNome(rs.getString("nome"));
            novoCliente.setDtNasc(rs.getDate("dt_nasc"));
            novoCliente.setCpf(rs.getString("cpf"));
            novoCliente.setCnh(rs.getString("cnh"));
            novoCliente.setRg(rs.getString("rg"));
            novoCliente.setTelefone(rs.getString("telefone"));
            novoCliente.setSituacao(rs.getInt("situacao"));
            novoCliente.setEndereco(new Endereco(rs.getInt("endereco"), rs.getString("descricao")));

            //add na lista
            listaCliente.add(novoCliente);
        }
        return listaCliente;
    }

    public boolean excluir(Cliente cliente) throws SQLException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(DELETE);

            pstmt.setInt(1, cliente.getId());

            pstmt.execute();

            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }

    public Cliente buscar(Cliente cliente) {

        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setString(1, cliente.getCpf());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));

            return cliente;
        } catch (Exception e) {

            return cliente;
        }
    }

    public boolean alterar(Cliente cliente) throws SQLException {
        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, cliente.getNome());
            pstmt.setDate(2, new java.sql.Date(cliente.getDtNasc().getTime()));
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getCnh());
            pstmt.setString(5, cliente.getRg());
            pstmt.setString(6, cliente.getTelefone());
//            pstmt.setString(7, cliente.getEndereco().getCep());
            pstmt.setInt(8, cliente.getSituacao());
            pstmt.setInt(10, cliente.getId());

            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }
    }

    public Cliente mostrar(String cpf) {
        List<Cliente> cliente = new ArrayList<>();
        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            String sql = "select * from cliente where cpf=?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setString(1, cpf);
            //executa
            ResultSet rs = pstmt.executeQuery();
            // como a query ira retornar somente um registro, faremos o NEXT
            while (rs.next()) {
                cliente.add(new Cliente(
                        rs.getString("nome"),
                        rs.getDate("dt_nasc"),
                        rs.getString("cpf"),
                        rs.getString("cnh"),
                        rs.getString("rg"),
                        rs.getString("telefone"),
                        rs.getInt("endereco")));

            };
        } catch (Exception e) {

            return cliente.get(0);
        }
        return cliente.get(0);
    }
}
