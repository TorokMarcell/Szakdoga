package com.example.szakdoghozkell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "DIAKIGAZOLVANY.db";
    private static final String USERS_TABLE = "users";

    private static final  String JOBS_TABLE="jobs";





    public DatabaseHelper(@Nullable Context context) {
        super(context, "DIAKIGAZOLVANY.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        // TODO: 2024. 10. 12. 2 tablet létrehozni jobs users,gondolkozni hogyan tudnánk szét szedni még jobban
        // TODO: 2024. 10. 12. Csinálni pluszt a regisztrációhoz plusz ablakot hol tanulsz? milyen munkák érdekelnek.
        // TODO: 2024. 10. 12. javítani az ml modelt. 
        MyDatabase.execSQL("create Table users(studentid TEXT primary key,email TEXT, password TEXT,firstname TEXT,lastname TEXT,validated INTEGER,role TEXT)");
        MyDatabase.execSQL("create Table jobs(jobid INTEGER primary key,title TEXT, description TEXT,salary INTEGER,location TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertDataToUsers(String email, String password, String studentid, Integer validated, String firstName, String lastName, String role) {
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
    public Boolean insertDataToJobs(String title, String description, int salary,String location) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("salary", salary);
        contentValues.put("location", location);
        long result = MyDatabase.insert(JOBS_TABLE, null, contentValues);
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
    public Boolean checkIfAdmin(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and role = 'ADMIN'", new String[]{email});
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
    public Boolean updateName(String studentid, String firstName, String lastName) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentid", studentid);
        contentValues.put("firstname", firstName);
        contentValues.put("lastname", lastName);
            long result = MyDatabase.update(USERS_TABLE, contentValues, "studentid=?", new String[]{studentid});
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        }

    public Boolean updatePassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.update(USERS_TABLE, contentValues, "email=?", new String[]{email});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

}
