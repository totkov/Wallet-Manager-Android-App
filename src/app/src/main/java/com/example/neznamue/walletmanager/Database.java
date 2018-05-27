package com.example.neznamue.walletmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nezna on 5/27/2018.
 */

public class Database extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static final String DB_NAME = "wallet_manager.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_TRANSACTIONS = "transactions";
    public static final String TRANSACTIONS_ID = "_id";
    public static final String TRANSACTIONS_TYPE = "type";
    public static final String TRANSACTIONS_TITLE = "title";
    public static final String TRANSACTIONS_AMOUNT = "amount";
    private static final String CREATE_TABLE_TRANSACTIONS = "CREATE TABLE " + TABLE_TRANSACTIONS +" (" +
            "'" + TRANSACTIONS_ID + "'  INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'" + TRANSACTIONS_TYPE + "' INTEGER NOT NULL, " +
            "'" + TRANSACTIONS_TITLE +"' TEXT NOT NULL, " +
            "'" + TRANSACTIONS_AMOUNT + "' REAL NOT NULL )";
    private static final String LOG_TAG = "DBException";


    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTransaction(Transaction model){
        try{
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(TRANSACTIONS_TYPE, model.getType());
            cv.put(TRANSACTIONS_TITLE, model.getTitle());
            cv.put(TRANSACTIONS_AMOUNT, model.getAmount());


            db.insertOrThrow(TABLE_TRANSACTIONS, null, cv);
        }catch (SQLException e){
            Log.e(LOG_TAG, e.getMessage());
        }finally {
            if(db!=null){
                db.close();
            }
        }
    }


    public Cursor getAllTransactions(){
        try{
            db = this.getReadableDatabase();
            String query = "SELECT "
                    + TRANSACTIONS_ID + ", "
                    + TRANSACTIONS_TYPE + ", "
                    + TRANSACTIONS_TITLE + ", "
                    + TRANSACTIONS_AMOUNT + " FROM " + TABLE_TRANSACTIONS;
            return db.rawQuery(query, null);

        }catch (Exception e){
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }

    public void deleteTransactionById(int id) {
        try {
            db = this.getWritableDatabase();
            String q = "DELETE FROM " + TABLE_TRANSACTIONS
                    + " WHERE _id = " + id;
            db.execSQL(q);
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage());
        }
    }
}
