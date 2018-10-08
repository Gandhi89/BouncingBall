package com.example.shivamgandhi.bouncingball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nIdEt;
    Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        initializeAll();
        onClickEvent();
    }

    private void initializeAll() {
        nIdEt = findViewById(R.id.JoinActivity_NID);
        joinBtn = findViewById(R.id.JoinActivity_join);
    }

    private void onClickEvent() {
        joinBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.JoinActivity_join:

                Intent intent = new Intent(JoinActivity.this,WaitingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
