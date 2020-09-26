package com.teamfive.argeography;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.client.AuthUiInitProvider;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.security.AuthProvider;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 123;
    Button botonaceptar;
EditText etusuario;
EditText etcontra;
Button btninicia;
SignInButton btngoogle;
FirebaseAuth muth, mAuth;
FirebaseAuth.AuthStateListener mAuthListener;
GoogleSignInClient  mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        botonaceptar = (Button)findViewById(R.id.registrar);
        etusuario = (EditText)findViewById(R.id.etusuario);
        etcontra = (EditText)findViewById(R.id.etcontra);
        btninicia = (Button)findViewById(R.id.btninicia);


        btngoogle = (SignInButton)findViewById(R.id.btngoogle);
        // Set the dimensions of the sign-in button.
       // btngoogle.setSize(SignInButton.SIZE_STANDARD);



        botonaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ir();
            }
        });
        btngoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


gooogle();


                }



        });
        btninicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ini();
            }
        });
        datos();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                startActivity(new Intent(MainActivity.this, two.class));
              finish();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
public void gooogle(){
    // Choose authentication providers
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build());


    startActivityForResult(
            AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
            RC_SIGN_IN);

}


    public void ini(){
        muth = FirebaseAuth.getInstance();
        String user = etusuario.getText().toString();
        String contr = etcontra.getText().toString();
        if(user.isEmpty() || contr.isEmpty()){
            Toast.makeText(MainActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
        }
        else{
            muth.signInWithEmailAndPassword(user, contr).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        work();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }

                    // ...
                }
            });
        }

    }

    public void work(){
        String datou = etusuario.getText().toString();
        String datoc = etcontra.getText().toString();
        Intent intent3 = new Intent(this, two.class);
        intent3.putExtra("use",datou);
        intent3.putExtra("con",datoc);
        startActivity(intent3);
        //finish();

    }
    public void ir(){
        Intent intent1 = new Intent(this, register.class);
        startActivity(intent1);
    }



    public void datos(){
        Intent intent2 = getIntent();
        String corre = intent2.getStringExtra("correo");
        String pas = intent2.getStringExtra("contra");
        etusuario.setText(corre);
        etcontra.setText(pas);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usr = mAuth.getCurrentUser();
        if(usr !=null)
        {
            startActivity(new Intent(MainActivity.this, two.class));
            finish();

        }
    }
}
