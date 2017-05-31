package com.example.win7.dakato.Model;

import android.provider.BaseColumns;

/**
 * Created by WIN7 on 30/05/2017.
 */

public class VerPedidosContract {
    private VerPedidosContract(){}

    public static abstract class VerPedidosEntry implements BaseColumns {
        public static final String TABLE_NAME  = "verpedidos";

        public static final String _VP_ID = "_id";
        public static final String COLUMS_VP_REFERENCIA = "pedido_id";
        public static final String COLUMS_VP_NOME = "tbitemcatalogo_id";
        public static final String COLUMS_VP_PP = "pp";
        public static final String COLUMS_VP_P = "p";
        public static final String COLUMS_VP_G = "g";
        public static final String COLUMS_VP_M = "m";
        public static final String COLUMS_VP_GG = "gg";
        public static final String COLUMS_VP_obs = "obs";

    }
}
