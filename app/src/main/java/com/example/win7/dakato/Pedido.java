package com.example.win7.dakato;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by WIN7 on 27/05/2017.
 */

public class Pedido  implements Serializable {

    private int id;
    private String emissao;
    private String status;
    private String cpf;
/*
    public Pedido(String emissao, String status, String cpf) {
        this.emissao = emissao;
        this.status = status;
        this.cpf = cpf;
    }
*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmissao() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
