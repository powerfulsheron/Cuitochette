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

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;

    EditText id;
    String id_t;
    EditText pwd;
    String pwd_t;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        helper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        setContentView(R.layout.connection);


        Log.i("test", getPlatById(2));


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
            Log.d("loginenvoye", id_t + "  " + pwd_t + "  " + id + "  " + pwd);

            Intent intent1 = new Intent(MainActivity.this, Formules.class);


            if (login.Companion.checkCo(id_t, pwd_t)) {

                if (login.Companion.getRole().equals("client")) {
                    setContentView(R.layout.formules);
                }else{
                    setContentView(R.layout.plats);
                }
                intent1.putExtra("restaurant",login.Companion.getResto());
                intent1.putExtra("num_table",login.Companion.getTable());



            } else {
                Context context = getApplicationContext();
                CharSequence text = "Erreur d'authentification";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            startActivity(intent1);

        }
    };

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