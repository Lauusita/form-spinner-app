package com.laura.formulario_campos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spn_category;
    EditText edt_user;
    EditText edt_age;
    Button btn_submit;
    List<CharSequence> values;
    public static final String dataUserCache = "dataUser";
    public static final String categoryCache = "category";
    private static final int private_mode = Context.MODE_PRIVATE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        values = List.of("Music", "Deport", "Cine");

        ArrayAdapter<CharSequence> options = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, values);

        edt_age = findViewById(R.id.edt_age);
        edt_user = findViewById(R.id.edt_user);
        btn_submit = findViewById(R.id.btn_submit);
        spn_category = (Spinner) findViewById(R.id.spn_category);

        spn_category.setAdapter(options);

        sharedPreferences = getSharedPreferences(dataUserCache, private_mode);
        editor = sharedPreferences.edit();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_age.getText().toString().isEmpty() || edt_user.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("user", edt_user.getText().toString());
                    editor.putString("category", spn_category.getSelectedItem().toString());

                    editor.commit();

                    if ( spn_category.getSelectedItem().toString().equalsIgnoreCase("Music")) i = new Intent(MainActivity.this, Music.class);
                    else if (spn_category.getSelectedItem().toString().equalsIgnoreCase("Cine"))  i = new Intent(MainActivity.this, Cine.class);
                    else i = new Intent(MainActivity.this, Deport.class);
                    startActivity(i);

                }
            }
        });


    }
}