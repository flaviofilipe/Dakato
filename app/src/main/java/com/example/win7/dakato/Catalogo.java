package com.example.win7.dakato;

import java.io.Serializable;

/**
 * Created by WIN7 on 29/05/2017.
 */

public class Catalogo implements Serializable {
    private String id;
    private String referencia;
    private String nome;
    private Double preco;
    private int img;
    private Boolean pp;
    private Boolean p;
    private Boolean m;
    private Boolean g;
    private Boolean gg;

    public Catalogo(String id, String referencia, String nome, Double preco, int img, Boolean pp, Boolean p, Boolean m, Boolean g, Boolean gg) {
        this.id = id;
        this.referencia = referencia;
        this.nome = nome;
        this.preco = preco;
        this.img = img;
        this.pp = pp;
        this.p = p;
        this.m = m;
        this.g = g;
        this.gg = gg;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Boolean getPp() {
        return pp;
    }

    public void setPp(Boolean pp) {
        this.pp = pp;
    }

    public Boolean getP() {
        return p;
    }

    public void setP(Boolean p) {
        this.p = p;
    }

    public Boolean getM() {
        return m;
    }

    public void setM(Boolean m) {
        this.m = m;
    }

    public Boolean getG() {
        return g;
    }

    public void setG(Boolean g) {
        this.g = g;
    }

    public Boolean getGg() {
        return gg;
    }

    public void setGg(Boolean gg) {
        this.gg = gg;
    }
}
