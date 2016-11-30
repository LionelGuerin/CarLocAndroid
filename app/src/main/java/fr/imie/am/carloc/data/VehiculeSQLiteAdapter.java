package fr.imie.am.carloc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.imie.am.carloc.entity.User;
import fr.imie.am.carloc.entity.Vehicule;

/**
 * Created by tact on 28/11/16.
 */

public class VehiculeSQLiteAdapter extends SQLiteAdapter{

    private static final String TABLENAME = "vehicule";
    private static final String ID = "id";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String DATE_CREATED = "dateCreated";
    private static final String USER_ID = "userId";
    private static final String VEHICULE_USER = TABLENAME + "_" + UserSQLiteAdapter.TABLENAME;

    private static final String[] COLUMNS = new String[]{
            VehiculeSQLiteAdapter.ID,
            VehiculeSQLiteAdapter.BRAND,
            VehiculeSQLiteAdapter.MODEL,
            VehiculeSQLiteAdapter.DATE_CREATED,
            VehiculeSQLiteAdapter.VEHICULE_USER,
    };

    public static final String SCHEMA = "CREATE TABLE " + VehiculeSQLiteAdapter.TABLENAME + " (" +
            VehiculeSQLiteAdapter.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            VehiculeSQLiteAdapter.BRAND + " TEXT, " +
            VehiculeSQLiteAdapter.MODEL + " TEXT, " +
            VehiculeSQLiteAdapter.DATE_CREATED + " TEXT NOT NULL, "+
            VehiculeSQLiteAdapter.VEHICULE_USER + " INTEGER, " +
            " FOREIGN KEY ("+ VEHICULE_USER +") REFERENCES "+UserSQLiteAdapter.TABLENAME+"("+UserSQLiteAdapter.ID+"));";

    public VehiculeSQLiteAdapter(Context context) {
        super(context);
    }

    public Vehicule getById(long id) {
        Vehicule vehicule = null;

        String whereClause = VehiculeSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        Cursor cursor = this.db.query(VehiculeSQLiteAdapter.TABLENAME, COLUMNS,
                whereClause, whereArgs, null, null, null);

        if(cursor.moveToFirst()) {
            vehicule = this.cursorToVehicule(cursor);
        }

        return vehicule;
    }

    public List<Vehicule> getByUserId(long userId) {
        List<Vehicule> vehicules = null;

        String whereClause = VehiculeSQLiteAdapter.VEHICULE_USER + " = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};

        Cursor cursor = this.db.query(VehiculeSQLiteAdapter.TABLENAME, COLUMNS,
                whereClause, whereArgs, null, null, null);

        if(cursor.moveToFirst()) {
            vehicules = new ArrayList<Vehicule>();

            do {
                vehicules.add(this.cursorToVehicule(cursor));
            } while (cursor.moveToNext());

        }

        return vehicules;
    }

    public List<Vehicule> getAll() {
        List<Vehicule> vehicules = null;

        Cursor cursor = this.db.query(VehiculeSQLiteAdapter.TABLENAME, COLUMNS,
                null, null, null, null, null);

        if(cursor.moveToFirst()) {
            vehicules = new ArrayList<Vehicule>();

            do {
                vehicules.add(this.cursorToVehicule(cursor));
            } while (cursor.moveToNext());

        }

        return vehicules;
    }

    public boolean insert(Vehicule vehicule) {
        boolean result = false;

        ContentValues values = this.vehiculeToContentValues(vehicule);

        long id = this.db.insert(VehiculeSQLiteAdapter.TABLENAME, null, values);

        if (id > 0) {
            vehicule.setId(id);
            result = true;
        }

        return result;
    }

    public int update(Vehicule vehicule) {
        ContentValues values = this.vehiculeToContentValues(vehicule);

        String whereClause = VehiculeSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(vehicule.getId())};

        return this.db.update(VehiculeSQLiteAdapter.TABLENAME, values, null, null);
    }

    public int delete(Vehicule vehicule) {
        String whereClause = VehiculeSQLiteAdapter.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(vehicule.getId())};

        return this.db.delete(VehiculeSQLiteAdapter.TABLENAME, whereClause, whereArgs);
    }

    private ContentValues vehiculeToContentValues(Vehicule vehicule) {
        ContentValues values = new ContentValues();
        values.put(VehiculeSQLiteAdapter.BRAND, vehicule.getBrand());
        values.put(VehiculeSQLiteAdapter.MODEL, vehicule.getModel());

        Date date = new Date();
        vehicule.setDateCreated(date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        values.put(VehiculeSQLiteAdapter.DATE_CREATED, formatter.format(vehicule.getDateCreated()));

        if (vehicule.getUser() != null) {
            values.put(VehiculeSQLiteAdapter.VEHICULE_USER, vehicule.getUser().getId());
        }

        return values;
    }

    private Vehicule cursorToVehicule(Cursor cursor) {
        Vehicule vehicule = new Vehicule();
        vehicule.setId(cursor.getInt(cursor.getColumnIndex(VehiculeSQLiteAdapter.ID)));
        vehicule.setBrand(cursor.getString(cursor.getColumnIndex(VehiculeSQLiteAdapter.BRAND)));
        vehicule.setModel(cursor.getString(cursor.getColumnIndex(VehiculeSQLiteAdapter.MODEL)));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            vehicule.setDateCreated(formatter.parse(cursor.getString(cursor.getColumnIndex(VehiculeSQLiteAdapter.DATE_CREATED))));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(VehiculeSQLiteAdapter.VEHICULE_USER)));
        vehicule.setUser(user);

        return vehicule;
    }
}
