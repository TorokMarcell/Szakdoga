package com.example.szakdoghozkell;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "DIAKIGAZOLVANY.db";
    private static final String USERS_TABLE = "users";

    private static final  String JOBS_TABLE="jobs";



    Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "DIAKIGAZOLVANY.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(studentid TEXT primary key,email TEXT, password TEXT,firstname TEXT,lastname TEXT,validated INTEGER,role TEXT)");
        MyDatabase.execSQL("create Table jobs(jobid INTEGER primary key,title TEXT, description TEXT,salary INTEGER,location TEXT,adminid INTEGER, FOREIGN KEY(adminid)  references admin(id))");
        MyDatabase.execSQL("create Table admin(id INTEGER primary key,email TEXT, password TEXT)");
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
    public Boolean insertDataToJobs(String title, String description, int salary,String location,int adminid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("salary", salary);
        contentValues.put("location", location);
        contentValues.put("adminid",adminid);
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
    public int getAdminId(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = null;
        int adminid = 0;
       try {
          cursor =  MyDatabase.rawQuery("Select * from admin where email = ?", new String[]{email});
          if(cursor.getCount() >0){
              cursor.moveToFirst();
              adminid = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
          }
          return adminid;
       }finally {
           cursor.close();
       }
    }
    public Boolean checkAdminEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from admin where email = ?", new String[]{email});
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
    }public Boolean checkAdminPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from admin where email = ? and password = ?", new String[]{email, password});
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
        Cursor cursor = MyDatabase.rawQuery("Select * from admin where email = ?", new String[]{email});
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

    public Boolean updatePassword(String email,String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = MyDatabase.update(USERS_TABLE, contentValues, "email=?", new String[]{email});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public SimpleCursorAdapter getDatas(int adminid){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from jobs where adminid = ?", new String[]{String.valueOf(adminid)});
        String[] columnames = new String[]{
                "jobid","title","description","salary","location","adminid"
        };
        int[] viewIds = new int[]{R.id.updatejobs_title,R.id.updatejobs_description,R.id.updatejobs_salary,R.id.updatejobs_location};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,R.layout.activity_updatejobs,cursor,columnames,viewIds
        );
        return contactAdapter;
    }

}
