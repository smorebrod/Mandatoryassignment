package com.example.mandatoryassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText EmailField;
    private EditText PasswordField;

    private Button LoginBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        EmailField = (EditText) findViewById(R.id.EmailEditText);
        PasswordField = (EditText) findViewById(R.id.PasswordEditText);

        LoginBtn = (Button) findViewById(R.id.SignInBtn);



        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    String email = EmailField.getText().toString();
                    startActivity(new Intent(MainActivity.this, AccountActivity.class));
                    Intent intent = new Intent();
                    intent.putExtra("email",email);
                }
            }
        };

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();

            }
        });



    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(authListener);
    }



    private void startSignIn(){
        String email = EmailField.getText().toString();
        String password = PasswordField.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this,"Fields are empty", Toast.LENGTH_LONG).show();

        } else {

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Sign In Problem",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }


    }







}
