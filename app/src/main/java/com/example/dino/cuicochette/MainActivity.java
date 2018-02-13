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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;

    EditText id ;
    String id_t ;
    EditText pwd ;
    String pwd_t ;


    Intent intent1;

    JsonParser parser = new JsonParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        setContentView(R.layout.connection);


        Button button = (Button) findViewById(R.id.valider);
        button.setOnClickListener(newListener);

    }

    private View.OnClickListener newListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            id = findViewById(R.id.ID);
            id_t = id.getText().toString();
            pwd = findViewById(R.id.PDW);
            pwd_t = pwd.getText().toString();

            if (id_t.equals("client"))
                intent1 = new Intent(MainActivity.this, Formules.class);
            else
                intent1 = new Intent(MainActivity.this, CommandeResto.class);

         /*   Log.d("loginenvoye", id_t + "  " + pwd_t + "  " + id + "  " + pwd);
            intent1 = new Intent(MainActivity.this, Formules.class);

            Ion.with(getApplicationContext())
                    .load("http://10.0.2.2:3306/connexion?login="+id_t+"&mdp="+pwd_t)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            JsonObject jObject = parser.parse(String.valueOf(result)).getAsJsonObject();

                            if (jObject.get("type").equals("table")) {
                                intent1.putExtra("num_table", jObject.get("numeroTable").toString());
                                intent1.putExtra("numResto", jObject.get("restaurant").toString());
                                setContentView(R.layout.formules);
                            } else if (jObject.get("type").equals("chef")) {
                                intent1.putExtra("numResto", jObject.get("restaurant").toString());
                                setContentView(R.layout.plats);
                            }
                        }
                    });

*/

            startActivity(intent1);


        }
    };



}