
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

import com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminUser extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private DatabaseReference mDataRef;
    private ListView listView;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        mDataRef = FirebaseDatabase.getInstance().getReference("User");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1,arrayList);
        listView = (ListView) findViewById(R.id.userList);
        listView.setAdapter(adapter);

        //get id user
        pref = getSharedPreferences("User_details", MODE_PRIVATE);

        mDataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //get id user
                final User user = dataSnapshot.getValue(User.class);
                final String value = user.getId();
                if(user.getAdmin().equals("0")) {
                    arrayList.add(value);
                    adapter.notifyDataSetChanged();


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SharedPreferences.Editor editor = pref.edit();
                            String idUser = arrayList.get(position);
                            editor.putString("userId", idUser);
                            editor.commit();
                            Intent i = new Intent(AdminUser.this, UserView.class);
                            startActivity(i);

                        }
                    });
                }

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
