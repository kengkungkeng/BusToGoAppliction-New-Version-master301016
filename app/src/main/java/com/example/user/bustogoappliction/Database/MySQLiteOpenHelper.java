package com.example.user.bustogoappliction.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by USER on 8/9/2560.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private final String TAG = getClass().getSimpleName();
    private SQLiteOpenHelper sqLiteOpenHelper;

    public static final String Database_Name = "Bus.db";
    public static final int Database_Version = 1;

    public static final String BUS_TABLE = "BusTABLE";
    public static final String BUS_ID = "id_bus";
    public static final String BUS_NAME = "bus_name";
    public static final String BUS_PIC = "pic_bus";
    public static final String BUS_DETAIL = "detail_bus";
    public String CREATE_BUS_TABLE = "create table "+BUS_TABLE+"("+BUS_ID+" text primary key,"+BUS_NAME+" text,"+BUS_PIC+" text,"+BUS_DETAIL+" text);";


    public static final String PLACE_TABLE = "PlaceTABLE";
    public static final String PLACE_ID = "id_place";
    public static final String PLACE_NAME = "name_place";
    public static final String PLACE_PIC = "pic_place";
    public static final String PLACE_DETAIL = "detail_place";
    public static final String PLACE_LAT = "lat_place";
    public static final String PLACE_LONG = "long_place";
    private static final String CREATE_PLACE_TABLE = "create table " + PLACE_TABLE + "("+PLACE_ID+" text primary key,"+" " +
            ""+PLACE_NAME+" text,"+PLACE_DETAIL+" text,"+PLACE_LAT+" text,"+PLACE_LONG+" text,"+PLACE_PIC+" text);";

    public MySQLiteOpenHelper(Context context) {
        super(context,Database_Name,null,Database_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, CREATE_BUS_TABLE);
        Log.i(TAG, CREATE_PLACE_TABLE);
        db.execSQL(CREATE_BUS_TABLE);
        db.execSQL(CREATE_PLACE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
