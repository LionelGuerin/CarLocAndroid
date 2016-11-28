package fr.imie.bmille.carloc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.imie.bmille.carloc.entity.User;

/**
 * Created by bmille on 28/11/2016.
 */

public class UserSQLiteAdapter {

    private static final String TABLE_NAME = "person";
    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private SQLiteDatabase db;

    private static final String[] COLUMNS = new String[]{
            UserSQLiteAdapter.ID,
            UserSQLiteAdapter.FIRSTNAME,
            UserSQLiteAdapter.LASTNAME,
            UserSQLiteAdapter.USERNAME,
            UserSQLiteAdapter.PASSWORD
    };

    public static final String SCHEMA = "CREATE TABLE " + UserSQLiteAdapter.TABLE_NAME + "(" +
            UserSQLiteAdapter.TABLE_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UserSQLiteAdapter.FIRSTNAME + " TEXT, " +
            UserSQLiteAdapter.LASTNAME + " TEXT, " +
            UserSQLiteAdapter.USERNAME + " TEXT NOT NULL, " +
            UserSQLiteAdapter.PASSWORD + " TEXT NOT NULL);";

    public UserSQLiteAdapter(Context context) {
        CarLocSQLiteOpenHelper helper = new CarLocSQLiteOpenHelper(context);
        this.db = helper.open();

    }

    public User getById(int id) {
        User user = null;

        String whereClause = UserSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        Cursor cursor = this.db.query(UserSQLiteAdapter.TABLE_NAME, COLUMNS,
                whereClause, whereArgs, null, null, null);

        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getColumnIndex(UserSQLiteAdapter.ID));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.FIRSTNAME)));
            user.setLastName(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.LASTNAME)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.PASSWORD)));
        }

        return user;
    }

    public List<User> getAll() {
        User user = null;
        List<User> users = null;

        Cursor cursor = this.db.query(UserSQLiteAdapter.TABLE_NAME, COLUMNS,
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            users = new ArrayList<User>();

            do {
                users.add(this.cursorToUser(cursor));
            } while (cursor.moveToNext());
        }

        return users;
    }

    public boolean insert(User user) {
        boolean result = false;
        ContentValues values = userToContentValues(user);

        long id = db.insert(UserSQLiteAdapter.TABLE_NAME, null, values);

        if (id > 0) {
            user.setId(id);
            result = true;
        }

        return result;
    }

    public int update(User user) {
        boolean result = false;

        ContentValues values = userToContentValues(user);

        String whereClause = UserSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(user.getId())};

        return this.db.update("person", values, null, null);
    }

    public int delete(User user) {
        String whereClause = UserSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(user.getId())};

        return this.db.delete(UserSQLiteAdapter.TABLE_NAME, whereClause, whereArgs);
    }

    private ContentValues userToContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserSQLiteAdapter.FIRSTNAME, user.getFirstName());
        values.put(UserSQLiteAdapter.LASTNAME, user.getLastName());
        values.put(UserSQLiteAdapter.USERNAME, user.getUsername());
        values.put(UserSQLiteAdapter.PASSWORD, user.getPassword());

        return values;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getColumnIndex(UserSQLiteAdapter.ID));
        user.setFirstName(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.FIRSTNAME)));
        user.setLastName(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.LASTNAME)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.PASSWORD)));

        return user;
    }
}
