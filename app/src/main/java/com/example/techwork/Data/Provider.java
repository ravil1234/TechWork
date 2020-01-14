package com.example.techwork.Data;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public class Provider extends ContentProvider {

    public static final String LOG_TAG = Provider.class.getSimpleName();
    private static final int student=100;
    public static final int work=102;
    private DbHelper mdbhelper;
    private  static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_Student, student);
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_workshop, work);
    }
    @Override
    public boolean onCreate() {
        mdbhelper=new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor cursor;
        SQLiteDatabase database=mdbhelper.getReadableDatabase();
        int match=sUriMatcher.match(uri);
        switch (match) {
            case student:
                cursor = database.query(Contract.Entry.TABLE_NAME, projection, selection, selectionArgs
                        , null, null, sortOrder);
                   break;
            case work:
                cursor = database.query(Contract.WorkEntry.TABLE_NAME, projection, selection, selectionArgs
                        , null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("CANNOT QUERY UNKNOWN URI " + uri);

        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case student:
                return insertstudent(uri, contentValues);
            case work:
                return insertwork(uri,contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }
    private  Uri insertstudent(Uri uri,ContentValues values)
    {
        String name = values.getAsString(Contract.Entry.COLUMN_NAME);
        if(name==null)
        {
            throw  new IllegalArgumentException("name is not valid");
        }
        String email = values.getAsString(Contract.Entry.COLUMN_EMAIL);
        if(email==null)
        {
            throw  new IllegalArgumentException("email is not valid");
        }
        String college = values.getAsString(Contract.Entry.COLUMN_COLLEGE);
        if(college==null)
        {
            throw  new IllegalArgumentException("college is not valid");
        }
        String password = values.getAsString(Contract.Entry.COLUMN_PASSWORD);
        if(password==null)
        {
            throw  new IllegalArgumentException("password is not valid");
        }
        SQLiteDatabase database = mdbhelper.getWritableDatabase();
        long id = database.insert(Contract.Entry.TABLE_NAME, null, values);
        if(id==-1)
        {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri, id);
    }
    private  Uri insertwork(Uri uri,ContentValues values)
    {
        String name = values.getAsString(Contract.WorkEntry.COLUMN_WORKSHOP_NAME);
        if(name==null)
        {
            throw  new IllegalArgumentException("WORKSHOP NAME  is not valid");
        }
        String date= values.getAsString(Contract.WorkEntry.COLUMN_DATE);
        if(date==null)
        {
            throw  new IllegalArgumentException("DATE is not valid");
        }
        SQLiteDatabase database = mdbhelper.getWritableDatabase();
        long id = database.insert(Contract.WorkEntry.TABLE_NAME, null, values);
        if(id==-1)
        {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri, id);
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
