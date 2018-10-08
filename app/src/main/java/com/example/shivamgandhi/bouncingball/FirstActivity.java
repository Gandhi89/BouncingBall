package com.example.shivamgandhi.bouncingball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    Button createBtn,joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initializeAll();
        onClickEvents();
    }

    private void onClickEvents() {
        createBtn.setOnClickListener(this);
        joinBtn.setOnClickListener(this);
    }

    private void initializeAll() {
        createBtn = findViewById(R.id.firstActivity_createBtn);
        joinBtn = findViewById(R.id.firstActivity_joinBtn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.firstActivity_createBtn:

                Intent intent = new Intent(FirstActivity.this,WaitingActivity.class);
                startActivity(intent);

                break;
            case R.id.firstActivity_joinBtn:

                Intent i = new Intent(FirstActivity.this,JoinActivity.class);
                startActivity(i);

                break;
        }
    }
}
