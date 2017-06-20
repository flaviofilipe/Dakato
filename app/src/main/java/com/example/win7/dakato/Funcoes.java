package com.example.win7.dakato;

import java.math.BigDecimal;

/**
 * Created by WIN7 on 20/06/2017.
 */

public class Funcoes {

    public static double precoEntrada(String preco){
        preco =preco.replace(",",".");
        Double valor = Double.parseDouble(preco);

        return valor;
    }
    public static String precoSaida(Double preco){

        String p1 = String.format("%.2f", preco); //2 casas decimais
        String p = String.valueOf(p1).replace(".",",");
        return p;
    }

}
