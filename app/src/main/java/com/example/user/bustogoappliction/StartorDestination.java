package com.example.user.bustogoappliction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartorDestination extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startor_destination);
    }
    public void button5 (View view) {
        Intent intent = new Intent(StartorDestination.this,Mainmenu.class);
        startActivity(intent);
    }
}
