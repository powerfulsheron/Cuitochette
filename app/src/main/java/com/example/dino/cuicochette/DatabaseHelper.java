package com.example.dino.cuicochette;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "cuitochette";

    // Table Names
    private static final String TABLE_PLAT = "PLAT";
    private static final String TABLE_UTILISATEUR = "UTILISATEUR";
    private static final String TABLE_RESTAURANT = "RESTAURANT";

    // Table Create Statements
    private static final String CREATE_TABLE_PLAT = "CREATE TABLE "
            + TABLE_PLAT + " (id integer(10) PRIMARY KEY, nom varchar(50),type varchar(50),description varchar(500),restaurant integer(10) REFERENCES " + TABLE_RESTAURANT + " (id));";

    private static final String CREATE_TABLE_UTILISATEUR = "CREATE TABLE "
            + TABLE_UTILISATEUR + "(id integer(10) PRIMARY KEY,nom varchar(50),prenom varchar(50),mail varchar(100),password varchar(100),type varchar(50));";

    private static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE "
            + TABLE_RESTAURANT + " (id integer(10) PRIMARY KEY,nom varchar(50),proprietaire integer(10) REFERENCES "+TABLE_UTILISATEUR+" (id));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_UTILISATEUR);
        db.execSQL(CREATE_TABLE_RESTAURANT);
        db.execSQL(CREATE_TABLE_PLAT);

        String sql = "INSERT INTO UTILISATEUR (id,nom,prenom,mail,password,type)\n" +
                "VALUES " +
                "(1,'Meryl','Valier','cuitochette1','12345','client')," +
                "(2,'Meryl','Valier','cuitochette2','12345','client')," +
                "(3,'Meryl','Valier','cuitochette3','12345','restaurateur')," +
                "(4,'Meryl','Valier','cuitochette4','12345','restaurateur')";
        db.execSQL(sql);

        sql = "INSERT INTO RESTAURANT (id,nom,proprietaire)\n" +
                "VALUES " +
                "(1,'Le coq en Slip',3)," +
                "(2,'Le coq en croute',4)";
        db.execSQL(sql);

        sql = "INSERT INTO PLAT (id,nom,type,description,restaurant)\n" +
                                    "VALUES " +
                                    "(1,'Salade de crevettes','entree','Une salade fraiche du marché avec des crevettes du poissonnier',1)," +
                                    "(2,'Bourguignon de Boeuf','plat','Sauté de boeuf mijoté pendant 5 heures',1)," +
                                    "(3,'Nems','entree','Les vrais nems du Vietnam',1)," +
                                    "(4,'Mousse au Chocolat','dessert','75% de Chocolat',1)," +
                                    "(5,'Columbo de Porc','plat','La vraie recette Antillaise',1)," +
                                    "(6,'Gateau à l''Ananas','dessert','Fait maison le matin même',1)," +
                                    "(7,'Fanta','boisson','Canette de 33cl',1)," +
                                    "(8,'Coca','boisson','Canette de 33cl',1)," +
                                    "(9,'Part de Pizza','entree','La vraie Pizza de Naples',2)," +
                                    "(10,'Soupe à l''Oignon','entree','Faite par des chevaliers oignons',2)," +
                                    "(11,'Souris d''Agneau','plat','Servi avec ses pâtes fraiches',2)," +
                                    "(12,'Rocher Coco','dessert','De la maison Ferrerro',2);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILISATEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        // create new tables
        onCreate(db);
    }

}