package com.example.user.bustogoappliction;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }
    public void imvd (View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.bmta.co.th/?q=th/home"));
        startActivity(intent);
    }
    public void imva (View view) {
        Intent intent = new Intent(Mainmenu.this,NumberBus.class);
        startActivity(intent);
    }

    public void imvb (View view) {
        Intent intent = new Intent(Mainmenu.this,Road.class);
        startActivity(intent);
    }
    public void imvc (View view) {
        Intent intent = new Intent(Mainmenu.this,StartorDestination.class);
        startActivity(intent);
    }
}
