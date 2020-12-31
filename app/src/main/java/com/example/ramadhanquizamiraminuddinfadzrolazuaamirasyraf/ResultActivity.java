package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {

    TextView tTotal, tCorrect, tWrong, idTxt;
    SharedPreferences prf;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tTotal = (TextView)findViewById(R.id.textViewtotal);
        tCorrect = (TextView)findViewById(R.id.textViewCorrect);
        tWrong = (TextView)findViewById(R.id.textViewWrong);
        logout = (Button)findViewById(R.id.btnBack);
        idTxt =(TextView)findViewById(R.id.txtID);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        Intent i=getIntent();

        String question = i.getStringExtra("total");
        String correct = i.getStringExtra("correct");
        String wrong = i.getStringExtra("wrong");
        final String id = i.getStringExtra("id");

        tTotal.setText(question);
        tCorrect.setText(correct);
        tWrong.setText(wrong);
        idTxt.setText(id);

        final String result = tTotal.getText().toString();
        final String idUser = idTxt.getText().toString();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        table_user.child(idUser).child("result").setValue(result);
                        Intent main = new Intent(ResultActivity.this, MainActivity_amiraminuddinfadzrolazuaamirasyraf.class);
                        startActivity(main);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
