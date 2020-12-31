package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class UserView extends AppCompatActivity {

    EditText eName, ePassword;
    Button bUpdate, bDelete;
    SharedPreferences pref;
    String id;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        eName = (EditText)findViewById(R.id.editName);
        ePassword = (EditText)findViewById(R.id.editOption1);
        bUpdate = (Button) findViewById(R.id.buttonUpdate);
        bDelete = (Button)findViewById(R.id.buttonDelete);

        pref = getSharedPreferences("User_details", MODE_PRIVATE);
        id = pref.getString("userId",null);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User").child(id);

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                eName.setText(user.getName());
                ePassword.setText(user.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gUsername, gPassword;
                gUsername = eName.getText().toString();
                gPassword = ePassword.getText().toString();

                table_user.child("name").setValue(gUsername);
                table_user.child("password").setValue(gPassword);
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.removeValue();
                Intent userView = new Intent(UserView.this,AdminUser.class);
                startActivity(userView);
                //Toast.makeText(UserView.this, "user delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
