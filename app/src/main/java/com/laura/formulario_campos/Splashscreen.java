package com.laura.formulario_campos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splashscreen extends AppCompatActivity {
    public static final String dataUserCache = "dataUser";
    public static final String categoryCache = "category";
    private static final int private_mode = Context.MODE_PRIVATE;
    String user;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        checkValidation();
    }
    private void checkValidation() {
        user = getApplicationContext().getSharedPreferences(dataUserCache, private_mode).getString("user", "0");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if (user.equalsIgnoreCase("0")) {
                    i = new Intent(Splashscreen.this, MainActivity.class);
                    startActivity(i);
                } else {
                    category = getApplicationContext().getSharedPreferences(dataUserCache, private_mode).getString("category", "none");

                    if ( category.equalsIgnoreCase("Music")) i = new Intent(Splashscreen.this, Music.class);
                    else if (category.equalsIgnoreCase("Cine"))  i = new Intent(Splashscreen.this, Cine.class);
                    else  i = new Intent(Splashscreen.this, Deport.class);
                    startActivity(i);
                }
            }
        }, 2000);
    }
}