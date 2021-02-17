/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.util.ArrayList;
import modelo.Agendamento;

/**
 *
 * @author Dayelle
 */
public interface IAgendamentoDAO {

    public ArrayList<Agendamento> listar(Agendamento agendamento);

    public void buscar(Agendamento agendamento);

    public boolean alterar(Agendamento agendamento);

    public boolean excluir(Agendamento agendamento);

    public boolean cadastrar(Agendamento agendamento);

}
