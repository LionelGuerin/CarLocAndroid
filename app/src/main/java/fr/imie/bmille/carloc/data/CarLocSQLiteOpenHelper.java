package fr.imie.bmille.carloc.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bmille on 28/10/2016.
 */

import android.database.sqlite.SQLiteOpenHelper;

public class CarLocSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.sqlite";
    private static final int DATABASE_VERSION = 1;
    private Context ctx;

    public CarLocSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserSQLiteAdapter.SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase open() {

        return this.getWritableDatabase();
    }

    public void close(SQLiteDatabase db) {
        db.close();
    }
}
