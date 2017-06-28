package br.com.redewsouza.win7.dkatto.Model;

import android.provider.BaseColumns;


public class VerPedidosContract {
    private VerPedidosContract(){}

    public static abstract class VerPedidosEntry implements BaseColumns {
        public static final String TABLE_NAME  = "verpedidos";

        public static final String _VP_ID = "_id";
        public static final String COLUMS_VP_PEDIDO_ID = "pedido_id";
        public static final String COLUMS_VP_ITEMCATALOGO_ID = "itemcatalogo_id";
        public static final String COLUMS_VP_PP = "pp";
        public static final String COLUMS_VP_P = "p";
        public static final String COLUMS_VP_G = "g";
        public static final String COLUMS_VP_M = "m";
        public static final String COLUMS_VP_GG = "gg";
        public static final String COLUMS_VP_OBS = "obs";
        public static final String COLUMS_VP_PRECO = "preco";

        public static final String SCRIPT_DEL_TABELA =  "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
