package com.example.win7.dakato.Model;

import android.provider.BaseColumns;

public class CatalogoContract {
    private CatalogoContract(){}

    public static abstract class CatalogoEntry implements BaseColumns {
        public static final String TABLE_NAME  = "catalogo";

        public static final String _ID = "_id";
        public static final String COLUMS_REFERENCIA = "referencia";
        public static final String COLUMS_NOME = "nome";
        public static final String COLUMS_PRECO = "preco";
        public static final String COLUMS_IMG = "img";
        public static final String COLUMS_PP = "pp";
        public static final String COLUMS_P = "p";
        public static final String COLUMS_G = "g";
        public static final String COLUMS_M = "m";
        public static final String COLUMS_GG = "gg";

    }
}
