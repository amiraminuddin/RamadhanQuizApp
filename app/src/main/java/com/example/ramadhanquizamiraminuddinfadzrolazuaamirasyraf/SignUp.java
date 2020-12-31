package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText edtName, edtPassword, edtId;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (EditText)findViewById(R.id.edtName);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtId = (EditText)findViewById(R.id.edtID);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please Wait.....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check if already register
                        if (dataSnapshot.child(edtId.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            //Toast.makeText(SignUp.this,"id already register",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            //mDialog.dismiss();
                            User user = new User(edtName.getText().toString(),edtPassword.getText().toString(),"0","0",edtId.getText().toString());
                            table_user.child(edtId.getText().toString()).setValue(user);
                            //Toast.makeText(SignUp.this,"successful",Toast.LENGTH_SHORT).show();
                            //finish();
                            Intent SignIN = new Intent(SignUp.this, MainActivity_amiraminuddinfadzrolazuaamirasyraf.class);
                            startActivity(SignIN);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
