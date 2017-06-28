package br.com.redewsouza.win7.dkatto.Model;

import android.provider.BaseColumns;

/**
 * Created by WIN7 on 30/05/2017.
 */

public class PedidoContract {
    private PedidoContract(){}

    public static abstract class PedidoEntry implements BaseColumns{
        public static final String TABLE_NAME  = "pedidos";

        public static final String _ID = "_id";
        public static final String COLUMS_EMISSAO = "emissao";
        public static final String COLUMS_CPF = "cpf";
        public static final String COLUMS_TOTAL = "total";


        public static final String SCRIPT_DEL_TABELA =  "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
