package com.example.dino.cuicochette;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KBSONE on 23/01/2018.
 */

public class DbAdapter {

    myDbHelper myhelper;

    public DbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public String getPlatById(int Id) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT nom from plat where id=" + Id + ";", new String[]{});
        String result = "nok";
        if (cur.moveToFirst()) {
            result = cur.getString(0);
        }
        cur.close();
        db.close();
        return result;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "cuitochette";    // Database Name
        private static final int DATABASE_Version = 1;   // Database Version
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("test","fdp");

            String sql = "Create TABLE MAIN.[Temp_649910154](\n" +
                    "[id] integer(10) PRIMARY KEY\n" +
                    ",[nom] varchar(50)\n" +
                    ",[type] varchar(50)\n" +
                    ",[description] varchar(500)\n" +
                    ",[restaurant] integer(10) REFERENCES [RESTAURANT] ([id]));\n" +
                    "Insert Into MAIN.[Temp_649910154] ([id],[nom],[type],[description],[restaurant]) \n" +
                    " Select [id],[nom],[type],[description],[restaurant] From MAIN.[PLAT];\n" +
                    "Drop Table MAIN.[PLAT];\n" +
                    "Alter Table MAIN.[Temp_649910154] Rename To [PLAT];\n";
            db.execSQL(sql);

            sql = "Create TABLE MAIN.[Temp_934803054](\n" +
                    "[id] integer(10) PRIMARY KEY NOT NULL\n" +
                    ",[nom] varchar(50)\n" +
                    ",[proprietaire] integer(10) REFERENCES [UTILISATEUR] ([id]));\n" +
                    "Insert Into MAIN.[Temp_934803054] ([id],[nom],[proprietaire]) \n" +
                    " Select [id],[nom],[proprietaire] From MAIN.[RESTAURANT];\n" +
                    "Drop Table MAIN.[RESTAURANT];\n" +
                    "Alter Table MAIN.[Temp_934803054] Rename To [RESTAURANT];";
            db.execSQL(sql);

            sql = "Create  TABLE MAIN.[Temp_677735911](\n" +
                    "[id] integer(10) NOT NULL\n" +
                    ",[nom] varchar(50)\n" +
                    ",[prenom] varchar(50)\n" +
                    ",[mail] varchar(100)\n" +
                    ",[password] varchar(100)\n" +
                    ",[type] varchar(50));\n" +
                    "Insert Into MAIN.[Temp_677735911] ([id],[nom],[prenom],[mail],[password],[type]) \n" +
                    " Select [id],[nom],[prenom],[mail],[password],[type] From MAIN.[UTILISATEUR];\n" +
                    "Drop Table MAIN.[UTILISATEUR];\n" +
                    "Alter Table MAIN.[Temp_677735911] Rename To [UTILISATEUR];\n";
            db.execSQL(sql);

            sql = "INSERT INTO PLAT (id,nom,type,description,restaurant)\n" +
                    "VALUES " +
                    "(1,'Salade de crevettes','entree','Une salade fraiche du marché avec des crevettes du poissonnier',1)," +
                    "(2,'Bourguignon de Boeuf','plat','Sauté de boeuf mijoté pendant 5 heures',1)," +
                    "(3,'Nems','entree','Les vrais nems du Vietnam',1)," +
                    "(4,'Mousse au Chocolat','dessert','75% de Chocolat',1)," +
                    "(5,'Columbo de Porc','plat','La vraie recette Antillaise',1)," +
                    "(6,'Gateau à l\'Ananas','dessert','Fait maison le matin même',1)," +
                    "(7,'Fanta','boisson','Canette de 33cl',1)," +
                    "(8,'Coca','boisson','Canette de 33cl',1)," +
                    "(9,'Part de Pizza','entree','La vraie Pizza de Naples',2)," +
                    "(9,'Soupe à l\'Oignon','entree','Faite par des chevaliers oignons',2)," +
                    "(11,'Souris d'Agneau','plat','Servi avec ses pâtes fraiches',2)," +
                    "(12,'Rocher Coco','dessert','De la maison Ferrerro',2);";
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion == oldVersion + 1) {
                //	db.execSQL("ALTER TABLE student_info ADD COLUMN country VARCHAR(30)");
            }
        }
    }
}

    /*
    //Insert data into table
    public void insertData(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO student_info (name, age, class_name, city) " +
                "VALUES (?,?,?,?)");
        stmt.bindString(1, student.getName());
        stmt.bindLong(2, student.getAge());
        stmt.bindString(3, student.getClassName());
        stmt.bindString(4, student.getCity());
        stmt.execute();
        stmt.close();
        db.close();
    }

    //Update data into table
    public void updateData(Student studentleDatabase();
        SQLiteStatement stmt = db.compileStatement("UPDATE student_info SET name=?, age=?, class_name=?, city=? "+
                "WHERE id = ?");
        stmt.bindString(1, student.getName());
        stmt.bindLong(2, student.getAge());
        stmt.bindString(3, student.getClassName());
        stmt.bindString(4, student.getCity());
        stmt.bindLong(5, student.getId());
        stmt.execute();
        stmt.close();
        db.close();
    }
    //Select all data from the table
    public List getStudents() {
        List students = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id, name, age, class_name, city from student_info ORDER BY id ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Student std = new Student();
            std.setId(cursor.getInt(0));
            std.setName(cursor.getString(1));
            std.setAge(cursor.getInt(2));
            std.setClassName(cursor.getString(3));
            std.setCity(cursor.getString(4));
            students.add(std);
        }
        db.close();
        return students;
    }
    //Delete data from the table for the given id
    public void deleteData(int stdId){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM student_info WHERE id = ?");
        stmt.bindLong(1, stdId);
        stmt.execute();
        stmt.close();
        db.close();
    }
    //Select data for the given id
    public Student getStudentById(int stdId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id, name, age, class_name, city FROM student_info WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(stdId)});
        cursor.moveToFirst();
        Student std = new Student();
        std.setId(cursor.getInt(0));
        std.setName(cursor.getString(1));
        std.setAge(cursor.getInt(2));
        std.setClassName(cursor.getString(3));
        std.setCity(cursor.getString(4));
        db.close();
        return std;
    }//Update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == oldVersion + 1) {
            //	db.execSQL("ALTER TABLE student_info ADD COLUMN country VARCHAR(30)");
        }
    }
    //Insert data into table
    public void insertData(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO student_info (name, age, class_name, city) " +
                "VALUES (?,?,?,?)");
        stmt.bindString(1, student.getName());
        stmt.bindLong(2, student.getAge());
        stmt.bindString(3, student.getClassName());
        stmt.bindString(4, student.getCity());
        stmt.execute();
        stmt.close();
        db.close();
    }

    //Update data into table
    public void updateData(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("UPDATE student_info SET name=?, age=?, class_name=?, city=? "+
                "WHERE id = ?");
        stmt.bindString(1, student.getName());
        stmt.bindLong(2, student.getAge());
        stmt.bindString(3, student.getClassName());
        stmt.bindString(4, student.getCity());
        stmt.bindLong(5, student.getId());
        stmt.execute();
        stmt.close();
        db.close();
    }
    //Select all data from the table
    public List getStudents() {
        List students = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id, name, age, class_name, city from student_info ORDER BY id ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Student std = new Student();
            std.setId(cursor.getInt(0));
            std.setName(cursor.getString(1));
            std.setAge(cursor.getInt(2));
            std.setClassName(cursor.getString(3));
            std.setCity(cursor.getString(4));
            students.add(std);
        }
        db.close();
        return students;
    }
    //Delete data from the table for the given id
    public void deleteData(int stdId){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM student_info WHERE id = ?");
        stmt.bindLong(1, stdId);
        stmt.execute();
        stmt.close();
        db.close();
    }
    //Select data for the given id
    public Student getStudentById(int stdId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id, name, age, class_name, city FROM student_info WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(stdId)});
        cursor.moveToFirst();
        Student std = new Student();
        std.setId(cursor.getInt(0));
        std.setName(cursor.getString(1));
        std.setAge(cursor.getInt(2));
        std.setClassName(cursor.getString(3));
        std.setCity(cursor.getString(4));
        db.close();
        return std;
    }*/
