package com.example.user.bustogoappliction.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.bustogoappliction.Database.BusTale;
import com.example.user.bustogoappliction.Detail.BusDetail;
import com.example.user.bustogoappliction.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchBus extends AppCompatActivity {
    private BusTale objBusTale;
    private String[] title_bus,detail_bus,image_bus;
    private String[] titlesql_bus,detailsql_bus,imagesql_bus;

    EditText elb;
    ListView lvb;
    ArrayAdapter<String> adapterb;
    ArrayList<String> arrayListb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bus);

        connectb();
        elb = (EditText)findViewById(R.id.searchb);
        lvb = (ListView)findViewById(R.id.lvsb);

        title_bus = objBusTale.readAllBus(1);
        image_bus = objBusTale.readAllBus(2);
        detail_bus = objBusTale.readAllBus(3);

        arrayListb = new ArrayList<>(Arrays.asList(title_bus));
        adapterb = new ArrayAdapter<>(this,R.layout.bussearch,R.id.namebus,arrayListb);
        lvb.setTextFilterEnabled(true);
        lvb.setAdapter(adapterb);

        lvb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                titlesql_bus = title_bus;
                imagesql_bus = image_bus;
                detailsql_bus = detail_bus;
                Intent intent = new Intent(SearchBus.this, BusDetail.class);
                intent.putExtra("Image", image_bus[i]);
                intent.putExtra("Title", title_bus[i]);
                intent.putExtra("Detail", detail_bus[i]);
                startActivity(intent);
            }
        });
        elb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SearchBus.this.adapterb.getFilter().filter(charSequence);
                adapterb.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void connectb() {
        objBusTale = new  BusTale(this);
    }
}
