/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author Dayelle
 */
public interface IUsuarioDAO {

    public ArrayList<Usuario> listar(Usuario usuario);

    public void buscar(Usuario usuario);

    public boolean alterar(Usuario usuario);

    public boolean excluir(Usuario usuario);

    public boolean cadastrar(Usuario usuario);
}
