package com.example.shivamgandhi.bouncingball.Activities;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.shivamgandhi.bouncingball.R;
import com.example.shivamgandhi.bouncingball.Utils.Vars;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    ImageView bouncinBallIv;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    Vars mVars;
    int FinalI = 1;
    int length=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeAll();
        // get the children count
        mDatabaseReference.child("Networks").child(mVars.getNetworkID()).child("players").addListenerForSingleValueEvent(new getChildValue());

        new CountDownTimer(4000,1000)
        {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Log.d("homeActivity/cnt","inside 1st cnt");
                Log.d("homeActivity/mVars.ID",mVars.getID());
                mDatabaseReference.child("Networks").child(mVars.getNetworkID()).child("id_no").addValueEventListener(new ListenForChange());
                makeLoop();
            }
        }.start();
    }

    private void makeLoop() {
        Log.d("homeActivity/makeLoop","inside makeLoop");
        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Log.d("homeActivity/insideCnt", "true");
                mDatabaseReference.child("Networks").child(mVars.getNetworkID()).child("id_no").setValue("" + FinalI);
                FinalI ++;
                if(FinalI < length+1){
                    Log.d("homeActivity/ifCon","inside if condition");
                    makeLoop();
                }
            }
        }.start();


    }


    private void initializeAll() {
        bouncinBallIv = findViewById(R.id.imageView);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        mVars = Vars.getInstance();
    }

    private class ListenForChange implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.d("homeActivity/id",dataSnapshot.getValue().toString());
            if (mVars.getID().equals(dataSnapshot.getValue().toString()))
            {
                Log.d("homeActivity/MATCH",dataSnapshot.getValue().toString());
                bouncinBallIv.setVisibility(View.VISIBLE);
                startAnimation();
            }
            else {
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }

    private void startAnimation() {
        // Load the animation
        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);

        // Start the animation
        bouncinBallIv.startAnimation(animSlide);
    }

    private class getChildValue implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            length = (int)dataSnapshot.getChildrenCount();
            Log.d("homeActivity/Noofchild",""+length);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
