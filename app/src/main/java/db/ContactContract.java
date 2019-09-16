package db;

import android.provider.BaseColumns;

public final class ContactContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ContactContract() {}

    /* Inner class that defines the table contents */
    public static class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contatos";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_TELEFONE = "telefone";
    }
}
