package com.example.pepii.calculate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pepii on 19-May-18.
 */

public class Database extends SQLiteOpenHelper {
    SQLiteDatabase db;

    public static final String DB_NAME = "surfaces.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_HISTORY = "history";
    public static final String HISTORY_ID = "_id";
    public static final String HISTORY_SIDE = "side";
    public static final String HISTORY_HEIGHT = "height";
    public static final String HISTORY_SURFACE = "surface";
    private static final String CREATE_TABLE_HISTORY = "CREATE TABLE " + TABLE_HISTORY +" (" +
            "'" + HISTORY_ID + "'  INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'" + HISTORY_SIDE + "' REAL NOT NULL, " +
            "'" + HISTORY_HEIGHT +"' REAL NOT NULL, " +
            "'" + HISTORY_SURFACE + "' REAL NOT NULL )";
    private static final String LOG_TAG = "DBException";


    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void InsertTriangle(Triangle triangle){
        try{
            db= getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(HISTORY_SIDE, triangle.getSide());
            cv.put(HISTORY_HEIGHT, triangle.getHeight());
            cv.put(HISTORY_SURFACE, triangle.getSurface());


            db.insertOrThrow(TABLE_HISTORY, null, cv);
        }catch (SQLException e){
            Log.e(LOG_TAG, e.getMessage());
        }finally {
            if(db!=null){
                db.close();
            }
        }
    }


    public Cursor getAllSurfaces(){
        try{
            db = this.getReadableDatabase();
            String query = "SELECT "
                    + HISTORY_ID + ", "
                    + HISTORY_SIDE + ", "
                    + HISTORY_HEIGHT + ", "
                    + HISTORY_SURFACE + " FROM " + TABLE_HISTORY;
            return db.rawQuery(query, null);

        }catch (Exception e){
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }

    public void deleteAllSurfaces() {
        try {
            db = this.getReadableDatabase();
            String q = "DELETE FROM " + TABLE_HISTORY;
            db.execSQL(q);
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage());
        }
    }

    public void deleteSurface(int id) {
        try {
            db = this.getWritableDatabase();
            String q = "DELETE FROM " + TABLE_HISTORY
                    + " WHERE _id = " + id;
            db.execSQL(q);
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage());
        }
    }
}
