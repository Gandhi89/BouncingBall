package com.example.shivamgandhi.bouncingball.Activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shivamgandhi.bouncingball.R;
import com.example.shivamgandhi.bouncingball.Utils.Player;
import com.example.shivamgandhi.bouncingball.Utils.Vars;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nIdEt,pNameEt;
    Button joinBtn;
    Vars mVars;
    Player mPlayer;
    GameDatabase mGameDatabase;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        initializeAll();
        onClickEvent();
    }

    private void initializeAll() {
        nIdEt = findViewById(R.id.JoinActivity_NID);
        pNameEt = findViewById(R.id.JoinActivity_pName);
        joinBtn = findViewById(R.id.JoinActivity_join);

        mVars = Vars.getInstance();
        mPlayer = new Player();
        mGameDatabase = new GameDatabase();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    private void onClickEvent() {
        joinBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.JoinActivity_join:

                mVars.setPlayerName(pNameEt.getText().toString());
                mVars.setNetworkID(nIdEt.getText().toString());
                //count = mGameDatabase.getChildCount(mVars.getNetworkID());


                mDatabaseReference.child("Networks").child(mVars.getNetworkID()).child("players").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        count = (int)dataSnapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                new CountDownTimer(5000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        Log.d("length",count+"");
                        mVars.setID((count+1)+"");
                        mGameDatabase.joinNetwork(nIdEt.getText().toString(),mVars.getPlayerName(), String.valueOf(count+1));
                        Intent intent = new Intent(JoinActivity.this,WaitingActivity.class);
                        startActivity(intent);
                    }
                }.start();


                break;
        }
    }
}
