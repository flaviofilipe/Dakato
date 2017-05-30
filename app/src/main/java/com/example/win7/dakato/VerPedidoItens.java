package com.example.win7.dakato;

import java.io.Serializable;

/**
 * Created by WIN7 on 29/05/2017.
 */

public class VerPedidoItens implements Serializable {
    private int id;
    private int pedido_id;
    private int tbitemcatalogo_id;
    private int pp;
    private int p;
    private int m;
    private int g;
    private int gg;
    private String obs;

    public VerPedidoItens(int id, int pedido_id, int tbitemcatalogo_id, int pp, int p, int m, int g, int gg, String obs) {
        this.id = id;
        this.pedido_id = pedido_id;
        this.tbitemcatalogo_id = tbitemcatalogo_id;
        this.pp = pp;
        this.p = p;
        this.m = m;
        this.g = g;
        this.gg = gg;
        this.obs = obs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getTbitemcatalogo_id() {
        return tbitemcatalogo_id;
    }

    public void setTbitemcatalogo_id(int tbitemcatalogo_id) {
        this.tbitemcatalogo_id = tbitemcatalogo_id;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getGg() {
        return gg;
    }

    public void setGg(int gg) {
        this.gg = gg;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
