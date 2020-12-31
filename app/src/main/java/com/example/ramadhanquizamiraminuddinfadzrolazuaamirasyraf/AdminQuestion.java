package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf.Model.Question;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminQuestion extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private DatabaseReference mDataRef;
    private ListView listView;
    private SharedPreferences pref;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_question);

        mDataRef = FirebaseDatabase.getInstance().getReference("Question");
        //mTitle = (TextView)findViewById(R.id.rowQuestionText);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1,arrayList);
        listView = (ListView) findViewById(R.id.listQuestion);
        listView.setAdapter(adapter);


        //session the question id
        pref = getSharedPreferences("Question_details", MODE_PRIVATE);

        mDataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final Question question = dataSnapshot.getValue(Question.class);
                final String value = question.getQuestion();
                arrayList.add(value);
                adapter.notifyDataSetChanged();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences.Editor editor = pref.edit();
                        //get number position of the array
                        // +1 because array start with 0
                        String p = String.valueOf(position+1);
                        editor.putString("Questionname",p);
                        editor.commit();
                        Intent i = new Intent(AdminQuestion.this, QuestionView.class);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
