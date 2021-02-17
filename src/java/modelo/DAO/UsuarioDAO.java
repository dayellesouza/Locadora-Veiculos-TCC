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
import modelo.Usuario;

/**
 *
 * @author user
 */
public class UsuarioDAO {
    
    private static final String INSERT = "INSERT INTO usuario(nome,login,senha,cliente) values(?,?,?,?);";
    private static final String ENTRAR = "SELECT * FROM usuario where login=? and senha=?";
    private static final String AUTENTICAUSUARIO = "SELECT * FROM usuario where login=? and senha=?";
    private static final String BUSCAR = "SELECT * FROM usuario WHERE id=?;";
    private static final String UPDATE = "UPDATE usuario SET nome=?, cpf=?, telefone=?, login=?, senha=? WHERE id=?;";
    private static final String RETORNANOME = "select nome from usuario where login = ?;";
    
    private Object pstm;
    
    public void cadastrar(Usuario user) throws SQLException, ClassNotFoundException {
        
        Connection conexao = null;
        
        try {
            
            conexao = ConectaBanco.getConexao();
            
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getLogin());
            pstmt.setString(3, user.getSenha());
            pstmt.setString(4, user.getCpf());
            
            pstmt.execute();
        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    
    public Usuario autenticaUsuario(Usuario usuario) throws ClassNotFoundException {
        
        Usuario usuarioAutenticado = null;
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conexao = ConectaBanco.getConexao();
            
            pstmt = conexao.prepareStatement(AUTENTICAUSUARIO);
            
            pstmt.setString(1, usuario.getLogin());
            pstmt.setString(2, usuario.getSenha());
            rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            if (rs.next()) {
                usuarioAutenticado = new Usuario();
                usuarioAutenticado.setNome(rs.getString("nome"));
                usuarioAutenticado.setLogin(rs.getString("login"));
                usuarioAutenticado.setSenha(rs.getString("senha"));
                usuarioAutenticado.setCpf(rs.getString("cliente"));
            }
            
        } catch (SQLException erroSql) {
            throw new RuntimeException(erroSql);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return usuarioAutenticado;
    }
    
    public Usuario buscar(Usuario user) {
        
        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);
            
            pstmt.setString(1, user.getCpf());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();
            
            user.setNome(rs.getString("nome"));
            user.setLogin(rs.getString("login"));
            user.setSenha(rs.getString("senha"));
            
            return user;
            
        } catch (Exception e) {

            //
        }
        return user;
    }
    
    public boolean alterar(Usuario user) throws SQLException {
        Connection conexao = null;
        
        try {
            
            conexao = ConectaBanco.getConexao();
            
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getLogin());
            pstmt.setString(3, user.getSenha());
            pstmt.setInt(4, user.getId());
            
            pstmt.execute();
            return true;
            
        } catch (Exception ex) {
            
            return false;
            
        } finally {
            
            conexao.close();
        }
    }
    
    public ArrayList<Usuario> listar(Usuario usuario) throws ClassNotFoundException {
        //cria uma array de obJ Cliente
        ArrayList<Usuario> listaUser = new ArrayList<Usuario>();
        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL

            PreparedStatement pstmt = conexao.prepareStatement(RETORNANOME);
            
            pstmt.setString(1, usuario.getLogin());

            //executa
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                //a cada loop
                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(rs.getString("nome"));
                novoUsuario.setLogin(rs.getString("login"));

                //add na lista
                listaUser.add(novoUsuario);
            }
        } catch (SQLException ex) {
            //
        }
        return listaUser;
        
    }
    
}
