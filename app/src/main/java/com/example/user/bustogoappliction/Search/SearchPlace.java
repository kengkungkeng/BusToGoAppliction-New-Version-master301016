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

import com.example.user.bustogoappliction.Database.PlaceTable;
import com.example.user.bustogoappliction.Detail.PlaceDetail;
import com.example.user.bustogoappliction.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchPlace extends AppCompatActivity {
    private PlaceTable objPlaceTable;
    private String[] title_place,image_place,latt_place,lonn_place,detail_place;
    private String[] titlesql_place,detailsql_place,imagesql_place,lattsql_place,lonnsql_place;

    EditText el;
    ListView lv;
    ArrayAdapter<String> adapter2;
    ArrayList<String> arrayList2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);
        connect();
        el = (EditText)findViewById(R.id.searchp);
        lv = (ListView)findViewById(R.id.lvsp);

        title_place = objPlaceTable.readAllPlace(1);
        image_place = objPlaceTable.readAllPlace(5);
        latt_place = objPlaceTable.readAllPlace(3);
        lonn_place = objPlaceTable.readAllPlace(4);
        detail_place = objPlaceTable.readAllPlace(2);



        arrayList2 = new ArrayList<>(Arrays.asList(title_place));
        adapter2 = new ArrayAdapter<>(this,R.layout.placesearch,R.id.nameplace,arrayList2);
        lv.setTextFilterEnabled(true);
        lv.setAdapter(adapter2);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                titlesql_place = title_place;//เก็บค่าตัวแปรจากจาก SQL
                detailsql_place = detail_place;//เก็บค่าตัวแปรจากจาก SQL
                imagesql_place = image_place;//เก็บค่าตัวแปรจากจาก SQL
                lattsql_place = latt_place;//เก็บค่าตัวแปรจากจาก SQL
                lonnsql_place = lonn_place;//เก็บค่าตัวแปรจากจาก SQL
                Intent intent = new Intent(SearchPlace.this, PlaceDetail.class);//ส่งค่าข้อมูลจากดาต้าเบสไปหน้า GiantSwing
                intent.putExtra("title", title_place[i]);
                intent.putExtra("detail", detail_place[i]);
                intent.putExtra("image", image_place[i]);
                intent.putExtra("lat", latt_place[i]);
                intent.putExtra("long", lonn_place[i]);
                startActivity(intent);
            }
        });

        el.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SearchPlace.this.adapter2.getFilter().filter(charSequence);
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void connect() {

        objPlaceTable = new PlaceTable(this);
    }
}
