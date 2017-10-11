package com.example.user.bustogoappliction.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 8/9/2560.
 */

public class PlaceTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String PLACE_TABLE = "PlaceTABLE";
    public static final String PLACE_ID = "id_place";
    public static final String PLACE_NAME = "name_place";
    public static final String PLACE_PIC = "pic_place";
    public static final String PLACE_DETAIL = "detail_place";
    public static final String PLACE_LAT = "lat_place";
    public static final String PLACE_LONG = "long_place";

    public PlaceTable(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }
    public long AddNewPlaceTable(String place_id,String place_name,String place_detail
            ,String place_lat,String place_long, String place_pic) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.PLACE_ID, place_id);
        objContentValues.put(objMySQLiteOpenHelper.PLACE_NAME, place_name);
        objContentValues.put(objMySQLiteOpenHelper.PLACE_DETAIL, place_detail);
        objContentValues.put(objMySQLiteOpenHelper.PLACE_LAT, place_lat);
        objContentValues.put(objMySQLiteOpenHelper.PLACE_LONG, place_long) ;
        objContentValues.put(objMySQLiteOpenHelper.PLACE_PIC, place_pic);
        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.PLACE_TABLE, null, objContentValues);
    }
    public String[] readAllPlace(int intcolum) {
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(PLACE_TABLE, new String[]{PLACE_ID, PLACE_NAME, PLACE_DETAIL, PLACE_LAT, PLACE_LONG, PLACE_PIC}, null, null, null, null, null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    int num = objCursor.getCount();
                    strResult = new String[num];
                    for(int i =0;i<num;i++){
                        strResult[i] = objCursor.getString(intcolum);
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
