package com.example.user.bustogoappliction.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.bustogoappliction.R;

public class SearchMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);
    }
    public void sb(View view){
        Intent intent = new Intent(SearchMenu.this,SearchBus.class);
        startActivity(intent);
    }
    public void  sp(View view){
        Intent intent = new Intent(SearchMenu.this,SearchPlace.class);
        startActivity(intent);
    }
}
