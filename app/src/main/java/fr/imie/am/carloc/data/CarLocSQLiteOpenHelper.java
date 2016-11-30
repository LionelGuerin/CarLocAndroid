package fr.imie.am.carloc.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tact on 28/10/16.
 */

public class CarLocSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.sqlite";
    public static final int DATABASE_VERSION = 1;
    private Context ctx;

    public CarLocSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserSQLiteAdapter.SCHEMA);
        db.execSQL(VehiculeSQLiteAdapter.SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }

    public void close(SQLiteDatabase db) {
        db.close();
    }
}
