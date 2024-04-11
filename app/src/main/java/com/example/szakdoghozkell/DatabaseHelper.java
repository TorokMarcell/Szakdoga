package com.example.szakdoghozkell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "DIAKIGAZOLVANY.db";
    private static final String TABLE_NAME = "users";

    private static final String ID_COL = "email";

    private static final String PASSWORD_COL = "password";

    private static final String STUDENTID_COL = "studentid";

    private static final String FIRSTNAME_COL = "firstname";

    private static final String LASTNAME_COL = "lastname";



    public DatabaseHelper(@Nullable Context context) {
        super(context, "DIAKIGAZOLVANY.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT,studentid TEXT,firstname TEXT,lastname TEXT,validated TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String password, String studentid, String validated, String firstName, String lastName) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("studentid", studentid);
        contentValues.put("validated", validated);
        contentValues.put("firstname", firstName);
        contentValues.put("lastname", lastName);

        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkStudentID(String studentid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where studentid = ?", new String[]{studentid});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean checkValidated(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and validated = 'I'", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean checkFirstAndLastNameString(String studentid,String firstname,String lastname ) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where studentid = ? and firstName = ? and lastname = ?", new String[]{studentid,firstname,lastname});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean updatevalidated(String studentid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Update users set validated =  'I' where studentid = ?", new String[]{studentid});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean updateAllDatas(String email, String password, String studentid, String firstName, String lastName) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        contentValues.put("studentid", studentid);
        contentValues.put("firstname", firstName);
        contentValues.put("lastname", lastName);
            long result = MyDatabase.update(TABLE_NAME, contentValues, "email=?", new String[]{email});
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        }

}
