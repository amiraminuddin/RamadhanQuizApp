package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {

    private Button btnUser;
    private Button btnQuestion;
    private Button btnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnUser = findViewById(R.id.btnuser);
        Button btnQuestion = findViewById(R.id.btnquestion);
        Button btnScore = findViewById(R.id.btnscore);

        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionView = new Intent(Admin.this,AdminQuestion.class);
                startActivity(questionView);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserView = new Intent(Admin.this,AdminUser.class);
                startActivity(UserView);
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserScore = new Intent(Admin.this,AdminScore.class);
                startActivity(UserScore);
            }
        });
    }
}
