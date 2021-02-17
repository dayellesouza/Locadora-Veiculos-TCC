/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author user
 */
public class Agendamento {
    
    private int id;
    private Cliente cliente;
    private Servico servico;
    private Carros carros;
    private Date data;
    private String hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Carros getCarros() {
        return carros;
    }

    public void setCarros(Carros carros) {
        this.carros = carros;
    } 

    public Agendamento() {
    }

    public Agendamento(Date data) {
        this.data = data;
    }

    public Agendamento(int id, Cliente cliente, Servico servico, Carros carros, Date data, String hora) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.carros = carros;
        this.data = data;
        this.hora = hora;
    }

  
}
