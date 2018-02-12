package com.example.dino.cuicochette;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));

        Log.i("test", getPlatById(2));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent myIntent = null;
                if (position == 0) {
                    myIntent  = new Intent(MainActivity.this, Formules.class);
                } else if (position == 1) {
                }

                startActivity(myIntent);
            }
        });
    }

    public String getPlatById(int Id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT nom from plat where id=" + Id + ";", new String[]{});
        String result = "nok";
        if (cur.moveToFirst()) {
            result = cur.getString(0);
        }
        cur.close();
        db.close();
        return result;
    }
}