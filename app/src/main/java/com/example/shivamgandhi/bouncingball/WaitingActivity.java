package com.example.shivamgandhi.bouncingball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class WaitingActivity extends AppCompatActivity implements View.OnClickListener {

    TextView networkIdTv,playersTv;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        initializeAll();
        onClickEvents();

    }

    private void onClickEvents() {
        startBtn.setOnClickListener(this);
    }

    private void initializeAll() {

        networkIdTv = findViewById(R.id.WaitingActivity_displayID);
        playersTv = findViewById(R.id.WaitingActivity_playersTv);
        startBtn = findViewById(R.id.WaitingActivity_startBtn);

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
}
