package com.example.shivamgandhi.bouncingball.Activities;

import android.support.annotation.NonNull;

import com.example.shivamgandhi.bouncingball.Utils.Player;
import com.example.shivamgandhi.bouncingball.Utils.Vars;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class GameDatabase {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    Vars mVars;
    Player mPlayer;
    int count=0;

    public void GameDatabase() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    // --------------------------------------------------------------------------------------------- //

    /**
     * Function to create game with one player inside it
     * @param playerName
     */
    public void createNetwork(String playerName) {
        mVars = Vars.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mPlayer = new Player(playerName,"1");
        mVars.setNetworkID(generateNetworkID());
        mDatabaseReference.child("Networks").child(mVars.getNetworkID()).child("players").push().setValue(mPlayer);
        mDatabaseReference.child("Networks").child(mVars.getNetworkID()).child("id_no").setValue("0");

    }

    // --------------------------------------------------------------------------------------------- //

    /**
     * Function to generate 6 digit random number
     * @return '6 digit random number'
     */
    public String generateNetworkID() {
        Random rnd = new Random();
        int number = 100000 + rnd.nextInt(900000);
        return String.valueOf(number);
    }

    // --------------------------------------------------------------------------------------------- //

    /**
     * Function to join into network
     * @param networkID
     * @param playerName
     * @param id
     */
    public void joinNetwork(String networkID,String playerName, String id){
        mPlayer = new Player(playerName,id);
        mVars = Vars.getInstance();
        mVars.setNetworkID(networkID);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.child("Networks").child(mVars.getNetworkID()).child("players").push().setValue(mPlayer);

    }

    // --------------------------------------------------------------------------------------------- //

    /**
     * Function to return child number of a game
     * @param networkID
     * @return
     */
    public int getChildCount(String networkID){

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.child("Networks").child(networkID).child("players").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = (int)dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return count;
    }
}
