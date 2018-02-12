package com.example.dino.cuicochette;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * Created by mvalier on 10/03/2017.
 */

public class Formules extends Activity {


    DatabaseHelper helper;

    TextView textView;

    ListView listView;

    JSONAdapter jsonAdapter;


    JSONArray listPlat = new JSONArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formules);

        listPlat = new JSONArray();

        listView = (ListView)findViewById(R.id.lstText);

        jsonAdapter = new JSONAdapter(Formules.this, listPlat);

        listView.setAdapter(jsonAdapter);


    }
/*

    public String getAllPlat() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT nom from plat ;", new String[]{});
        String result = "nok";
        if (cur.moveToFirst()) {
            result = cur.getString(0);
        }
        cur.close();
        db.close();
        return result;

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

*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accueil:
                startActivity(new Intent(Formules.this, MainActivity.class));
                break;

            case R.id.formules:
                startActivity(new Intent(Formules.this, Formules.class));
                break;

        }
        return true;
    }


}
