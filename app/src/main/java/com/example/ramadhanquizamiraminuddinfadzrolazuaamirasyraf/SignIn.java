package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText edtId, edtPassword;
    Button btnSignIn;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtId = (EditText)findViewById(R.id.edtID);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Wait.....");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check existing user
                        if(dataSnapshot.child(edtId.getText().toString()).exists()) {
                            //get User Information
                            mDialog.dismiss();
                            String username = edtId.getText().toString();
                            User user = dataSnapshot.child(edtId.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())&& user.getAdmin().equals("0")) {
                                //continue to QuizActivity
                                Toast.makeText(SignIn.this, "Sign IN Succes", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("username",username);
                                editor.commit();
                                Intent Quiz = new Intent(SignIn.this,QuizActivity.class);
                                startActivity(Quiz);
                            }
                            else if(user.getPassword().equals(edtPassword.getText().toString()) && user.getAdmin().equals("1"))
                            {
                                Intent admin = new Intent(SignIn.this,Admin.class);
                                startActivity(admin);
                            }
                            else {
                                Toast.makeText(SignIn.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,"User not exits",Toast.LENGTH_SHORT).show();
                            Intent SignUp = new Intent(SignIn.this,SignUp.class);
                            startActivity(SignUp);
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
