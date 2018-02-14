package com.example.dino.cuicochette;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.common.hash.Hashing;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.nio.charset.Charset;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;

    EditText id ;
    String id_t ;
    EditText pwd ;
    String pwd_t ;

    Intent intent1;
    String test;
    JsonParser parser = new JsonParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        setContentView(R.layout.connection);

    }

        public void connect(View v) {

            id = findViewById(R.id.ID);
            id_t = id.getText().toString();
            pwd = findViewById(R.id.PDW);
            pwd_t = (Hashing.sha512().hashString(pwd.getText().toString(), Charset.defaultCharset())).toString();

            intent1 = new Intent();

            Ion.with(getApplicationContext())
                    .load("http://10.0.2.2:1234/connexion?login=" + id_t + "&mdp=" + pwd_t)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        Intent intent1;
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            Log.d("elif", "on recup le json");
                            JsonObject jObject = result;
                            Log.d("elif", jObject.get("type").getAsString());
                            if ((jObject.get("type").getAsString()).equals("table")) {
                                Log.d("elif", "on est une table");
                                intent1 = new Intent(MainActivity.this, Formules.class);
                                intent1.putExtra("numTable", jObject.get("numeroTable").getAsString());
                                intent1.putExtra("numResto", jObject.get("restaurant").getAsString());
                                MainActivity.this.startActivity(intent1);
                            } else if ((jObject.get("type").getAsString()).equals("chef")) {
                                Log.d("elif", "on est un chef");
                                intent1 = new Intent(MainActivity.this, CommandeResto.class);
                                Log.d("elif", jObject.toString());
                                intent1.putExtra("numResto", jObject.get("restaurant").getAsString());
                                MainActivity.this.startActivity(intent1);
                            }
                        }
                    });
        }
}