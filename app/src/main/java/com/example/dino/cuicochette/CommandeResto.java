package com.example.dino.cuicochette;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dino on 13/02/2018.
 */

public class CommandeResto extends AppCompatActivity {

    //Ne pas oublier de changer le port
    private String localCommandeString = "http://10.0.2.2:3306/commandes?restaurant=1";

    ListView listView;
    Button valider;
    Button reset;
    SimpleAdapter simpleAdapter;
    String plat;
    List<String> courant = new ArrayList<String>();
    static List<String> resumeFormules = new ArrayList<String>();
    Gson g = new Gson();
    JsonParser parser = new JsonParser();
    String label;
    String quantite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commanderesto);

        initList();
        listView = (ListView) findViewById(R.id.listView1);
        courant = new ArrayList<String>(Arrays.asList("none"));
        valider = (Button) findViewById(R.id.valider);
        reset = (Button) findViewById(R.id.reset);

        simpleAdapter = new SimpleAdapter(this, countryList, android.R.layout.simple_list_item_1, new String[]{"commande"}, new int[]{android.R.id.text1});

        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                if (courant.contains(Integer.toString(position))) {
                    while (courant.contains(Integer.toString(position))) {
                        courant.remove(Integer.toString(position));
                    }
                    v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                } else {
                    courant.add(Integer.toString(position));
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }
            }
        });
/*
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (resumeFormules.size() > 0) {
                    resumeFormules.remove(0);
                }
                for (int i = 0; i < courant.size( ) - 1; i++) {

                }
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(null);
                simpleAdapter = new SimpleAdapter(getApplicationContext(), countryList, android.R.layout.simple_list_item_1, new String[]{"commande"}, new int[]{android.R.id.text1});
                listView.setAdapter(simpleAdapter);
                while (courant.size() > 1) {
                    courant.remove(1);
                }
            }
        });*/

    }

    List<Map<String, String>> countryList = new ArrayList<Map<String, String>>();

    private void initList() {

        Ion.with(getApplicationContext())
                .load(localCommandeString)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        JsonObject jObject = parser.parse(String.valueOf(result)).getAsJsonObject();
                        for (Map.Entry<String, JsonElement> entry: jObject.entrySet()) {
                            countryList.add(plat("commande", "Commande n°"+entry.getKey()));
                            JsonObject child = parser.parse(String.valueOf(entry.getValue())).getAsJsonObject();
                            String status = String.valueOf(child.get("status"));
                            Log.i("status", status);
                            String plats= String.valueOf(child.get("plats"));
                            Log.i("plats", plats);
                            JsonArray jsonArray = (JsonArray) child.get("plats");
                            for (Object object: jsonArray){
                                JsonObject jsonObject = (JsonObject) object;
                                label = String.valueOf(((JsonObject) object).get("label"));
                                quantite = String.valueOf(((JsonObject) object).get("quantite"));
                                Log.i("label", label);
                                Log.i("quantite",  quantite);
                                Log.i("array", String.valueOf(jsonArray));
                                countryList.add(plat("commande", label + " Quantité : "+quantite));
                            }
                        }
                    }
                });


    }

    private HashMap<String, String> plat(String name, String number) {
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
                startActivity(new Intent(CommandeResto.this, MainActivity.class));
                break;

        }
        return true;
    }


    public static boolean checkCo(String id_t, String pwd_t){

        Boolean result = false;
        String localLoginString = "{\"utilisateur\":[{\"id\":2,\"login\":\"chef\",\"mdp\":\"12345\",\"type\":\"chef\"}]}";


        try{
            JSONObject jsonResponse = new JSONObject(localLoginString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("utilisateur");



            JSONObject jsonChildNode = jsonMainNode.getJSONObject(0);
            String name = jsonChildNode.optString("login");
            String number = jsonChildNode.optString("mdp");
            Log.d("login",name+"  "+number);
            Log.d("login",id_t+"  "+pwd_t);

            if(name.equals(id_t) && number.equals(pwd_t)){


                result = true;
            }


        }
        catch(JSONException e){
            Log.d("error", e.toString());
        }


        return result;
    }


}
