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
import java.util.ArrayList;
import modelo.Endereco;

/**
 *
 * @author Dayelle
 */
public class EnderecoDAO {

    private static final String INSERT = "INSERT INTO endereco(cep,logradouro,numero,bairro,cidade,uf) values(?,?,?,?,?,?);";
    private static final String SELECT_ALL = "SELECT * FROM endereco;";
    private static final String BUSCAR = "SELECT * FROM endereco WHERE cep=?;";
    private static final String UPDATE = "UPDATE endereco SET cep=?, logradouro=?, numero=?, bairro=?, cidade=?, estado=?, uf=? WHERE cep=?;";

    private Object pstm;

    public boolean cadastrar(Endereco endereco) throws SQLException, ClassNotFoundException {

        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, endereco.getCep());
            pstmt.setString(2, endereco.getRua());
            pstmt.setString(3, endereco.getNumero());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getUf());

            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }
    }

    public ArrayList<Endereco> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Endereco
        ArrayList<Endereco> listaEndereco = new ArrayList<Endereco>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Endereco novoEndereco = new Endereco();
            novoEndereco.setCep(rs.getString("cep"));
            novoEndereco.setRua(rs.getString("logradouro"));
            novoEndereco.setNumero(rs.getString("numero"));
            novoEndereco.setBairro(rs.getString("bairro"));
            novoEndereco.setCidade(rs.getString("cidade"));
            novoEndereco.setUf(rs.getString("uf"));
            //add na lista
            listaEndereco.add(novoEndereco);
        }
        return listaEndereco;
    }

    public void buscar(Endereco endereco) {

        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setString(1, endereco.getCep());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            endereco.setCep(rs.getString("cep"));
            endereco.setRua(rs.getString("rua"));
            endereco.setNumero(rs.getString("numero"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setUf(rs.getString("uf"));

        } catch (Exception e) {

            //
        }
    }

    public boolean alterar(Endereco endereco) throws SQLException {
        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, endereco.getCep());
            pstmt.setString(2, endereco.getRua());
            pstmt.setString(3, endereco.getNumero());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getUf());

            pstmt.execute();
            return true;

        } catch (Exception ex) {

            return false;

        } finally {

            conexao.close();
        }

    }

}
