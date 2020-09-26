package com.teamfive.argeography;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {
Button registra;
EditText etcpass;
EditText etpass;
EditText etmail;
FirebaseAuth mAuth;
    private Object AuthResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registra = (Button)findViewById(R.id.btnregistra);
        etcpass = (EditText)findViewById(R.id.etcpass);
        etpass = (EditText)findViewById(R.id.etpass);
        etmail = (EditText)findViewById(R.id.etmail);
        getSupportActionBar().hide();
        registra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ir();
            }
        });

    }
    public void ir(){
        mAuth = FirebaseAuth.getInstance();

        String email= etmail.getText().toString();
        String pass = etpass.getText().toString();
        String ccpass = etcpass.getText().toString();

        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(register.this, "Campos vacios", Toast.LENGTH_SHORT).show();
        }
        else{
            if(ccpass.equals(pass)){
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(register.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                            home();
                        } else  if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(register.this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(register.this, "No pudo registrar", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

            }

            else{
                Toast.makeText(register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }

        }
        //if(ccpass != pass){
          //  Toast.makeText(register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        //}

        }
        public void home(){
            String mail = etmail.getText().toString();
            String pss = etpass.getText().toString();
            Intent intent1 = new Intent(this, MainActivity.class);
            intent1.putExtra("correo",mail);
            intent1.putExtra("contra",pss);
            startActivity(intent1);

        }

}
