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
    private static final  String ACCAPTENCE_TABLE="jobsAcceptence";



    Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "DIAKIGAZOLVANY.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(studentid TEXT primary key,email TEXT, password TEXT,firstname TEXT,lastname TEXT,birthDate DATE,validated INTEGER)");
        MyDatabase.execSQL("create Table jobs(jobid INTEGER primary key,title TEXT, description TEXT,salary INTEGER,location TEXT,adminid INTEGER,avalaible INTEGER, FOREIGN KEY(adminid)  references admin(id))");
        MyDatabase.execSQL("create Table admin(id INTEGER primary key,email TEXT, password TEXT)");
        MyDatabase.execSQL("create Table jobsAcceptence(id INTEGER primary key,studentid TEXT, jobid INTEGER,userDescreption TEXT,accepted INTEGER, FOREIGN KEY(studentid) references users(studentid), FOREIGN KEY(jobid) references jobs(id))");
        MyDatabase.execSQL("create Table applicationProcess(id INTEGER primary key,applicationProcessText TEXT, FOREIGN KEY(id) references jobsAcceptence(accepted))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertDataToUsers(String email, String password, String studentid, Integer validated, String firstName, String lastName) {
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
        if(title.equals("")||description.equals("")||salary==-1||location.equals("")|| adminid ==-1) {
            return false;
        }
        else{
            SQLiteDatabase MyDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", title);
            contentValues.put("description", description);
            contentValues.put("salary", salary);
            contentValues.put("location", location);
            contentValues.put("adminid", adminid);
            contentValues.put("avalaible", 1);
            long result = MyDatabase.insert(JOBS_TABLE, null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }

    }
    public Boolean insertDataTojobsAcceptence(String studentid, int jobid,String description) {
        if(studentid.equals("")||jobid < 0||description.equals("")) {
            return false;
        }
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentid", studentid);
        contentValues.put("jobid", jobid);
        contentValues.put("userDescreption", description);
        contentValues.put("accepted",0);
        long result = MyDatabase.insert(ACCAPTENCE_TABLE, null, contentValues);
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
    public Boolean checkEmailAdmin(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from admin where email = ?", new String[]{email});
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
    public String getStudentId(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = null;
        String studentId = null;
        try{
            cursor =  MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
            if(cursor.getCount() >0){
                cursor.moveToFirst();
                studentId = cursor.getString(cursor.getColumnIndexOrThrow("studentid"));
            }
            return studentId;
        }finally {
            cursor.close();
        }
    }
    public String getAcceptence(int id){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = null;
        String studentId = null;
        try{
            cursor =  MyDatabase.rawQuery("Select applicationProcessText from applicationProcess where id = ?", new String[]{String.valueOf(id)});
            if(cursor.getCount() >0){
                cursor.moveToFirst();
                studentId = cursor.getString(cursor.getColumnIndexOrThrow("applicationProcessText"));
            }
            return studentId;
        }finally {
            cursor.close();
        }
    }
    public String getUserDescreption(String studentId,int jobid){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = null;
        String userDescreption = null;
        try{
            cursor =  MyDatabase.rawQuery("Select * from jobsAcceptence where studentid = ? and jobid = ?", new String[]{studentId, String.valueOf(jobid)});
            if(cursor.getCount() >0){
                cursor.moveToFirst();
                userDescreption = cursor.getString(cursor.getColumnIndexOrThrow("userDescreption"));
            }
            return userDescreption;
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
    public Boolean checkIfRegisteredAlready(String studentid,int jobid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from jobsAcceptence where studentid = ? and jobid =?", new String[]{studentid, String.valueOf(jobid)});
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
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and validated = 1", new String[]{email});
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
        Cursor cursor = MyDatabase.rawQuery("Update users set validated =  1 where studentid = ?", new String[]{studentid});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean updatePasswordAdmin(String email,String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = MyDatabase.update("admin", contentValues, "email=?", new String[]{email});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean updatePasswordUser(String email,String password) {
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
    public Boolean updateAccpetedSeen(String studentid,int jobid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("accepted", 1);
        long result = MyDatabase.update(ACCAPTENCE_TABLE, contentValues, "studentid=? and jobid=?", new String[]{studentid, String.valueOf(jobid)});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean updateAcceptenceAccepted(String studentid,int jobid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("accepted", 2);
        long result = MyDatabase.update(ACCAPTENCE_TABLE, contentValues, "studentid=? and jobid=?", new String[]{studentid, String.valueOf(jobid)});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean updateAcceptenceDeclined(String studentid,int jobid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("accepted", 3);
        long result = MyDatabase.update(ACCAPTENCE_TABLE, contentValues, "studentid=? and jobid=?", new String[]{studentid, String.valueOf(jobid)});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean updateAcceptencedeclinedv(int jobid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("accepted", 4);
        long result = MyDatabase.update(ACCAPTENCE_TABLE, contentValues, "accepted=? and jobid=?", new String[]{String.valueOf(4), String.valueOf(jobid)});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean deletejob(int jobid) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("avalaible", 0);
        long result = MyDatabase.update(JOBS_TABLE,contentValues," jobid=?", new String[]{String.valueOf(jobid)});
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public SimpleCursorAdapter getDatas(int adminid){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select jobid as _id,title,description,salary,location,adminid from jobs where adminid = ?", new String[]{String.valueOf(adminid)});
        String[] columnames = new String[]{
                "title","description","salary","location","adminid"
        };
        int[] viewIds = new int[]{R.id.singleitemtitle,R.id.singleitemdescreption,R.id.singleitemtsalary,R.id.singleitemlocation};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,R.layout.activity_single_item,cursor,columnames,viewIds
        );
        return contactAdapter;
    }
    public SimpleCursorAdapter getDatasforUser(){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select jobid as _id,title,description,salary,location,adminid from jobs",new String[]{});
        String[] columnames = new String[]{
                "title","description","salary","location","adminid"
        };
        int[] viewIds = new int[]{R.id.singleitemtitle,R.id.singleitemdescreption,R.id.singleitemtsalary,R.id.singleitemlocation};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,R.layout.activity_single_item,cursor,columnames,viewIds
        );
        return contactAdapter;
    }
    public SimpleCursorAdapter getDatasforApplicants(int jobid){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select id as _id,studentid,jobid,userDescreption,accepted from jobsAcceptence where jobid = ?",new String[]{String.valueOf(jobid)});
        String[] columnames = new String[]{
               "studentid","userDescreption","accepted"
        };
        int[] viewIds = new int[]{R.id.singleitemstudentid,R.id.singleitemtuserDescreption};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,R.layout.activity_single_item_applicants,cursor,columnames,viewIds
        );
        return contactAdapter;
    }
    public SimpleCursorAdapter getDatasforApplicant(String studentid){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select id as _id,studentid,jobid,userDescreption,accepted from jobsAcceptence where studentid = ?",new String[]{String.valueOf(studentid)});
        String[] columnames = new String[]{
                "studentid","userDescreption","accepted"
        };
        int[] viewIds = new int[]{R.id.singleitemstudentid,R.id.singleitemtuserDescreption};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,R.layout.activity_single_item_applicants,cursor,columnames,viewIds
        );
        return contactAdapter;
    }

}
