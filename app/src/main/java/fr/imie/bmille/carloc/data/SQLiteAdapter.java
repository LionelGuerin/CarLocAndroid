package fr.imie.bmille.carloc.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bmille on 28/11/2016.
 */

public class SQLiteAdapter {

    private SQLiteDatabase db;

    public SQLiteAdapter(Context context) {
        CarLocSQLiteOpenHelper helper = new CarLocSQLiteOpenHelper(context);
        this.db = helper.open();

    }
}
