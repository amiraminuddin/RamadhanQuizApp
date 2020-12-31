package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_amiraminuddinfadzrolazuaamirasyraf extends AppCompatActivity {

    Button btnSignIN, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_amiraminuddinfadzrolazuaamirasyraf);

        //declare button
        btnSignIN = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(MainActivity_amiraminuddinfadzrolazuaamirasyraf.this,SignIn.class);
                startActivity(signIn);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(MainActivity_amiraminuddinfadzrolazuaamirasyraf.this,SignUp.class);
                startActivity(signUp);

            }
        });
    }
}
