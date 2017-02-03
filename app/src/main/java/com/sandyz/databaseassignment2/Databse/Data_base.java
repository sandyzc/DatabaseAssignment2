package com.sandyz.databaseassignment2.Databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



/**
 * Created by santosh on 01-02-2017.
 */

public class Data_base extends SQLiteOpenHelper {

    public static final String TABLE_NAME ="entry";
    public static final String ID = "id";

    public static final String COLOUMN_NAME = "product_name";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "products.db";
    public SQLiteDatabase databse =null;



    private static String Sql_Delete_Entries = "Drop table if exists"+ TABLE_NAME;




//empty constructor
    public Data_base(Context context){
        super(context,DATABSE_NAME,null,DATABASE_VERSION);
    }


    // creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String table1 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +  " (" +
                ID + " INTEGER PRIMARY KEY," +
               COLOUMN_NAME + " TEXT)";


        db.execSQL(table1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(Sql_Delete_Entries);
        onCreate(db);

    }

    //opning the database to writable mode
    public void openDb()throws SQLException

    {
        Log.i("openDB", "Checking sqliteDb...");
        if (this.databse==null)
        {
            Log.i("open db","creating db instance");
            this.databse=this.getWritableDatabase();


        }
    }

    //closing the daabase
    public void closeDb()
    {
        if (this.databse!= null){

            if (this.databse.isOpen()){
                this.databse.close();
            }
        }
    }

    //method to add values into the columns

    public long insert_products(String productname)
    {
        ContentValues contentvalus = new ContentValues();
        Log.i(this.toString() + " - insertCountry", "Inserting: " + productname);
        contentvalus.put(COLOUMN_NAME,productname);




        return this.databse.insert(TABLE_NAME,null,contentvalus);

    }


    //creating a search query
    public String[] getAll_products()
    {
        Cursor curser = this.databse.query(TABLE_NAME,new String[]{COLOUMN_NAME},null,null,null,null,null);

        if (curser.getCount()>0)
        {
            String[] str = new String[curser.getCount()];
            int i = 0;

            while (curser.moveToNext())
            {
                str[i] = curser.getString(curser.getColumnIndex(COLOUMN_NAME));
                i++;
            }
            return str;
        }
        else
        {
            return new String[] {};
        }


    }


}
