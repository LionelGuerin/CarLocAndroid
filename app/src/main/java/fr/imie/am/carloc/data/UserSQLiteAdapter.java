package fr.imie.am.carloc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.imie.am.carloc.entity.User;

/**
 * Created by tact on 28/11/16.
 */

public class UserSQLiteAdapter extends SQLiteAdapter{

    public static final String TABLENAME = "person";
    public static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private static final String[] COLUMNS = new String[]{
            UserSQLiteAdapter.ID,
            UserSQLiteAdapter.FIRSTNAME,
            UserSQLiteAdapter.LASTNAME,
            UserSQLiteAdapter.USERNAME,
            UserSQLiteAdapter.PASSWORD
    };

    public static final String SCHEMA = "CREATE TABLE " + UserSQLiteAdapter.TABLENAME + " (" +
            UserSQLiteAdapter.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UserSQLiteAdapter.FIRSTNAME + " TEXT, " +
            UserSQLiteAdapter.LASTNAME + " TEXT, " +
            UserSQLiteAdapter.USERNAME + " TEXT NOT NULL, " +
            UserSQLiteAdapter.PASSWORD + " TEXT NOT NULL);";

    public UserSQLiteAdapter(Context context) {
        super(context);
    }

    public User getById(long id) {
        User user = null;

        String whereClause = UserSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        Cursor cursor = this.db.query(UserSQLiteAdapter.TABLENAME, COLUMNS,
                whereClause, whereArgs, null, null, null);

        if(cursor.moveToFirst()) {
            user = this.cursorToUser(cursor);
        }

        return user;
    }

    public List<User> getAll() {
        List<User> users = null;

        Cursor cursor = this.db.query(UserSQLiteAdapter.TABLENAME, COLUMNS,
                null, null, null, null, null);

        if(cursor.moveToFirst()) {
            users = new ArrayList<User>();

            do {
                users.add(this.cursorToUser(cursor));
            } while (cursor.moveToNext());

        }

        return users;
    }

    public boolean insert(User user) {
        boolean result = false;

        ContentValues values = this.userToContentValues(user);

        long id = this.db.insert(UserSQLiteAdapter.TABLENAME, null, values);

        if (id > 0) {
            user.setId(id);
            result = true;
        }

        return result;
    }

    public int update(User user) {
        ContentValues values = this.userToContentValues(user);

        String whereClause = UserSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(user.getId())};

        return this.db.update(UserSQLiteAdapter.TABLENAME, values, null, null);
    }

    public int delete(User user) {
        String whereClause = UserSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(user.getId())};

        return this.db.delete(UserSQLiteAdapter.TABLENAME, whereClause, whereArgs);
    }

    private ContentValues userToContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserSQLiteAdapter.FIRSTNAME, user.getFirstname());
        values.put(UserSQLiteAdapter.LASTNAME, user.getLastname());
        values.put(UserSQLiteAdapter.USERNAME, user.getUsername());
        values.put(UserSQLiteAdapter.PASSWORD, user.getPassword());

        return values;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(UserSQLiteAdapter.ID)));
        user.setFirstname(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.FIRSTNAME)));
        user.setLastname(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.LASTNAME)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(UserSQLiteAdapter.PASSWORD)));

        return user;
    }
}
