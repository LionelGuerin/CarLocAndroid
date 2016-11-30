package fr.imie.am.carloc.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tact on 28/11/16.
 */

public class SQLiteAdapter {

    protected SQLiteDatabase db;

    public SQLiteAdapter(Context context) {
        CarLocSQLiteOpenHelper helper = new CarLocSQLiteOpenHelper(context);
        this.db = helper.open();
    }

}
