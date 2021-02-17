/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.util.ArrayList;
import modelo.Endereco;

/**
 *
 * @author Dayelle
 */
public interface IEnderecoDAO {

    public ArrayList<Endereco> listar(Endereco endereco);

    public void buscar(Endereco endereco);

    public boolean alterar(Endereco endereco);

    public boolean excluir(Endereco endereco);

    public boolean cadastrar(Endereco endereco);

}
