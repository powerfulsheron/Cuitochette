package com.example.dino.cuicochette;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mvalier on 10/03/2017.
 */

public class Formules extends Activity {

    private String localPlatString = "{\"commande\":[{\"table\":1,\"status\":\"commande\",\"plats\":{\"Bourguignon de Boeuf\":3,\"Mousse au Chocolat\":3}}]}";

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formules);

        initList();
        listView = (ListView)findViewById(R.id.listView1);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, countryList, android.R.layout.simple_list_item_1, new String[] {"commande"}, new int[] {android.R.id.text1});

        listView.setAdapter(simpleAdapter);


    }

    List<Map<String,String>> countryList = new ArrayList<Map<String,String>>();
    private void initList(){

        try{
            JSONObject jsonResponse = new JSONObject(localPlatString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("commande");

            for(int i = 0; i<jsonMainNode.length();i++){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String name = jsonChildNode.optString("table");
                String number = jsonChildNode.optString("plats");
                String outPut = name + " " +number;
                countryList.add(plat("commande", outPut));
            }
        }
        catch(JSONException e){
            Log.d("error", e.toString());
        }
    }

    private HashMap<String, String> plat(String name, String number){
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }


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
