package com.example.mandatoryassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppCompatActivity {
     private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // String email =data.getStringExtra("email");
    }


    public void signout(View view) {
        mAuth.getInstance().signOut();
        finish();

     }

    public void GetReservation(View view) {
    }
}
