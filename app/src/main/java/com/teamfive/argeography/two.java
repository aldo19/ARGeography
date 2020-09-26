package com.teamfive.argeography;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;

public class two extends AppCompatActivity {

Button bth;
FirebaseAuth cerr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bth = (Button) findViewById(R.id.bth);
        cerr = FirebaseAuth.getInstance();

        bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   cerr.signOut();
                startActivity(new Intent(two.this, ereaAct.class));
             //   finish();
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menun,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.itemSalir:
                    finishAffinity();
                    break;
                case R.id.itemCerrarS:
                    cerr = FirebaseAuth.getInstance();
                    cerr.signOut();
                    startActivity(new Intent(two.this, MainActivity.class));
                    finish();

            }

        return super.onOptionsItemSelected(item);
    }
}
