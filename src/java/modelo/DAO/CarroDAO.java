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
import modelo.Carros;
import modelo.Cliente;

/**
 *
 * @author Dayelle
 */
public class CarroDAO {

    private static final String SELECT_ALL = "SELECT * FROM carros ;";
    private static final String BUSCAR = "Select * from carros where id=?;";
    
    private Object pstm;

    public ArrayList<Carros> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        ArrayList<Carros> listaCarro = new ArrayList<Carros>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Carros novoCarro = new Carros();
            novoCarro.setId(rs.getInt("id"));
            novoCarro.setNome(rs.getString("nome"));
            
            //add na lista
            listaCarro.add(novoCarro);
        }
        return listaCarro;
    }
    
     public Carros buscar(Carros carros) {

        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(BUSCAR);

            pstmt.setInt(1, carros.getId());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();

            carros.setNome(rs.getString("nome"));

            return carros;
        } catch (Exception e) {

            return carros;
        }
    }
}
