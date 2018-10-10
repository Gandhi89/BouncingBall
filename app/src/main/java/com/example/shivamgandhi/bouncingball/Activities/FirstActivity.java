package com.example.shivamgandhi.bouncingball.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shivamgandhi.bouncingball.R;
import com.example.shivamgandhi.bouncingball.Utils.Vars;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    Button createBtn,joinBtn,pNameBtn,pNameCancelBtn;
    EditText pNameEdt;
    Dialog dialog;
    Vars mVars;
    GameDatabase mGameDatabse;

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
        mVars = Vars.getInstance();
        mGameDatabse = new GameDatabase();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.firstActivity_createBtn:

                showDialog();

                break;
            case R.id.firstActivity_joinBtn:

                Intent i = new Intent(FirstActivity.this,JoinActivity.class);
                startActivity(i);

                break;
            case R.id.save:

                mVars.setPlayerName(pNameEdt.getText().toString());
                mGameDatabse.createNetwork(mVars.getPlayerName());
                mVars.setID("1");
                Intent i1 = new Intent(this,WaitingActivity.class);
                startActivity(i1);

                break;
            case R.id.cancel:

                dialog.cancel();

                break;
        }
    }

    private void showDialog() {
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Player Name");

        pNameEdt = dialog.findViewById(R.id.editText);
        pNameBtn = dialog.findViewById(R.id.save);
        pNameCancelBtn = dialog.findViewById(R.id.cancel);

        pNameBtn.setOnClickListener(this);
        pNameCancelBtn.setOnClickListener(this);

        dialog.show();

    }
}
