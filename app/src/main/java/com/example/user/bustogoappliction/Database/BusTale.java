package com.example.user.bustogoappliction.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 8/9/2560.
 */

public class BusTale {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String BUS_TABLE = "bustable";
    public static final String BUS_ID = "id_bus";
    public static final String BUS_NAME = "bus_name";
    public static final String BUS_PIC = "pic_bus";
    public static final String BUS_DETAIL = "detail_bus";

    public BusTale(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long AddNewBusTable(String bus_id,String bus_name,String bus_detail,String bus_pic) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.BUS_ID, bus_id);
        objContentValues.put(objMySQLiteOpenHelper.BUS_NAME, bus_name);
        objContentValues.put(objMySQLiteOpenHelper.BUS_DETAIL, bus_detail);
        objContentValues.put(objMySQLiteOpenHelper.BUS_PIC, bus_pic);
        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.BUS_TABLE, null, objContentValues);
    }

    public String[] readAllBus(int colum) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(BUS_TABLE, new String[]{BUS_ID, BUS_NAME, BUS_PIC, BUS_DETAIL}, null, null, null, null, null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    int num = objCursor.getCount();
                    strResult = new String[num];
                    for(int i =0;i<num;i++){
                        strResult[i] = objCursor.getString(colum);
                        objCursor.moveToNext();
                    }
                }
            }
            objCursor.close();
            return strResult;
        } catch (Exception e) {
            return null;
        }
    }
}

