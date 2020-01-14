package com.example.techwork.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the  table
        String SQL_CREATE_TABLE =  "CREATE TABLE " + Contract.Entry.TABLE_NAME + " ("
                + Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Entry.COLUMN_NAME + " TEXT NOT NULL, "
                + Contract.Entry.COLUMN_EMAIL + " TEXT NOT NULL, "
                + Contract.Entry.COLUMN_PASSWORD + " TEXT NOT NULL, "
                + Contract.Entry.COLUMN_COLLEGE + " TEXT NOT NULL);";

        // Execute the SQL statement
        String SQL_CREATE_TABLE1 =  "CREATE TABLE " + Contract.WorkEntry.TABLE_NAME + " ("
                + Contract.WorkEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.WorkEntry.COLUMN_WORKSHOP_NAME + " TEXT NOT NULL, "
                + Contract.WorkEntry.COLUMN_DATE + " TEXT NOT NULL);";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
