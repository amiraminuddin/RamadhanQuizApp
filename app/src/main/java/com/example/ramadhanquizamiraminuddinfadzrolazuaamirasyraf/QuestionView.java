package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestionView extends AppCompatActivity {

    EditText eQuestion, eOp1, eOp2, eOp3, eOp4, eAnswer;
    Button bUpdate;
    SharedPreferences pref;
    String id;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);

        eQuestion = (EditText)findViewById(R.id.editName);
        eOp1 = (EditText)findViewById(R.id.editOption1);
        eOp2 = (EditText)findViewById(R.id.editOption2);
        eOp3 = (EditText)findViewById(R.id.editOption3);
        eOp4 = (EditText)findViewById(R.id.editOption4);
        eAnswer = (EditText)findViewById(R.id.editAnswer);
        bUpdate = (Button) findViewById(R.id.buttonUpdate);

        pref = getSharedPreferences("Question_details", MODE_PRIVATE);
        id = pref.getString("Questionname",null);

        //eQuestion.setText(id);
        //value = eQuestion.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_question = database.getReference("Question").child(id);

        //Intent i = getIntent();
        //final String Idquestion = i.getStringExtra("IDQuestion");

        table_question.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Question question = dataSnapshot.getValue(Question.class);
                eQuestion.setText(question.getQuestion());
                eOp1.setText(question.getOption1());
                eOp2.setText(question.getOption2());
                eOp3.setText(question.getOption3());
                eOp4.setText(question.getOption4());
                eAnswer.setText(question.getAnswer());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gOp1, gOp2, gOp3, gOp4, gAnswer, gQuestion;
                gOp1 = eOp1.getText().toString();
                gOp2 = eOp2.getText().toString();
                gOp3 = eOp3.getText().toString();
                gOp4 = eOp4.getText().toString();
                gAnswer = eAnswer.getText().toString();
                gQuestion = eQuestion.getText().toString();

                table_question.child("option1").setValue(gOp1);
                table_question.child("option2").setValue(gOp2);
                table_question.child("option3").setValue(gOp3);
                table_question.child("option4").setValue(gOp4);
                table_question.child("question").setValue(gQuestion);
                table_question.child("answer").setValue(gAnswer);
            }
        });
    }
}
