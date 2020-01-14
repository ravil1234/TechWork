package com.example.techwork.Data;
import android.net.Uri;
import android.provider.BaseColumns;
public final class Contract {
    private Contract() {}

    public static final String CONTENT_AUTHORITY="com.example.techwork";
    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);
    public  static final String PATH_Student="student";
    public static final class Entry implements BaseColumns
    {
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_Student);
        public final static String TABLE_NAME = "students";
        public  static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME ="name";
        public final static String COLUMN_EMAIL = "email";
        public final static String COLUMN_PASSWORD= "password";
        public final static String COLUMN_COLLEGE = "college";
    }
    public static final String PATH_workshop = "workshop";
    public static final class WorkEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_workshop);
        public final static String TABLE_NAME = "workshop";
        public static String _ID = BaseColumns._ID;
        public final static String COLUMN_WORKSHOP_NAME = "workshopname";
        public final static String COLUMN_DATE = "date";
    }

}