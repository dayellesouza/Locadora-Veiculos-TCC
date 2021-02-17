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
import modelo.Cliente;
import modelo.Endereco;
import modelo.Horario;

/**
 *
 * @author Dayelle
 */
public class HorarioDAO {

    private static final String SELECT_ALL = "SELECT * FROM horario;";
    private static final String SELECT_HORA = "SELECT * from horario id=?;";

    public ArrayList<Horario> listar() throws SQLException, ClassNotFoundException {
        //cria uma array de obJ Cliente
        ArrayList<Horario> listaHora = new ArrayList<Horario>();

        //Conexao
        Connection conexao = ConectaBanco.getConexao();
        //cria comando SQL
        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
        //executa
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            //a cada loop
            Horario novaHora = new Horario();
            novaHora.setHora(rs.getTime("hora"));

            //add na lista
            listaHora.add(novaHora);
        }
        return listaHora;
    }

    public Horario buscar(Horario horario) {

        try {
            //Conexao
            Connection conexao = ConectaBanco.getConexao();
            //cria comando SQL
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_HORA);

            pstmt.setTime(1, horario.getHora());
            //executa
            ResultSet rs = pstmt.executeQuery();

            // como a query ira retornar somente um registro, faremos o NEXT
            rs.next();
            horario.setHora(rs.getTime("hora"));

            return horario;
        } catch (Exception e) {

            return horario;
        }
    }

}
