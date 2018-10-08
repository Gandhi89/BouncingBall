package com.example.shivamgandhi.bouncingball.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shivamgandhi.bouncingball.R;
import com.example.shivamgandhi.bouncingball.Utils.Player;
import com.example.shivamgandhi.bouncingball.Utils.Vars;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaitingActivity extends AppCompatActivity implements View.OnClickListener {

    TextView networkIdTv,playersTv;
    Button startBtn;
    Vars mVars;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        initializeAll();
        onClickEvents();

        // set networID on top
        networkIdTv.setText(mVars.getNetworkID());

        mDatabaseReference.child("Networks").child(mVars.getNetworkID()).child("players").addValueEventListener(new watchForNewPlayer());
    }

    private void onClickEvents() {
        startBtn.setOnClickListener(this);
    }

    private void initializeAll() {

        networkIdTv = findViewById(R.id.WaitingActivity_displayID);
        playersTv = findViewById(R.id.WaitingActivity_playersTv);
        startBtn = findViewById(R.id.WaitingActivity_startBtn);
        mVars = Vars.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.WaitingActivity_startBtn:
                Intent intent = new Intent(WaitingActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class watchForNewPlayer implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.d("Waiting",dataSnapshot.toString());
            Player mPlayer = dataSnapshot.getValue(Player.class);

            playersTv.setText("player:- "+mPlayer.name);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
