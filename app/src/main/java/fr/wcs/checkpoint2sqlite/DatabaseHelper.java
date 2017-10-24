package fr.wcs.checkpoint2sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bastienwcs on 24/10/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String SQL_CREATE_CAR_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.CarEntry.TABLE_NAME + " (" +
                    DatabaseContract.CarEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.CarEntry.COLUMN_NAME_BRAND + " TEXT," +
                    DatabaseContract.CarEntry.COLUMN_NAME_NAME + " TEXT," +
                    DatabaseContract.CarEntry.COLUMN_NAME_PLATE + " TEXT," +
                    DatabaseContract.CarEntry.COLUMN_NAME_KM + " INTEGER)";

    private static final String SQL_DELETE_CAR_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.CarEntry.TABLE_NAME;

    private static final String SQL_CREATE_ROAD_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.RoadEntry.TABLE_NAME + " (" +
                    DatabaseContract.RoadEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.RoadEntry.COLUMN_NAME_NAME + " TEXT)";

    private static final String SQL_DELETE_ROAD_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.RoadEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CAR_ENTRIES);
        db.execSQL(SQL_CREATE_ROAD_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_CAR_ENTRIES);
        db.execSQL(SQL_DELETE_ROAD_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}