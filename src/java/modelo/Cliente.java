package modelo;

import java.util.Date;

public class Cliente {

    private int id;
    private String nome;
    private Date dtNasc;
    private String cpf;
    private String cnh;
    private String rg;
    private String telefone;
    private int situacao;
    private Endereco endereco;
//    private Usuario usuario;
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

    public Cliente(String string, java.sql.Date date, String string0, String string1, String string2, String string3, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    

    public Cliente() {
    }

    public Cliente(int id, String nome, Date dtNasc, String cpf, String cnh, String rg, String telefone, int situacao, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
        this.cnh = cnh;
        this.rg = rg;
        this.telefone = telefone;
        this.situacao = situacao;
        this.endereco = endereco;
    }

    public Cliente(int id) {
        this.id = id;
    }

    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

}
