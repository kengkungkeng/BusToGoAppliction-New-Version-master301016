package com.example.user.bustogoappliction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.bustogoappliction.Database.BusTale;
import com.example.user.bustogoappliction.Database.PlaceTable;

public class Road extends AppCompatActivity {
    private PlaceTable objPlaceTable;
    private BusTale objBusTale;
    ListView listView;
    private String[]titles_bus,detail_bus,image_bus,latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road);
        BindWidget();
        c();
        Clistview();

    }//main Method onCreate

    private void Clistview() {

        final String[] titles = objPlaceTable.readAllPlace(1);
        final String[] detail = objPlaceTable.readAllPlace(2);
        final String[] image = objPlaceTable.readAllPlace(5);
        final String[] latt = objPlaceTable.readAllPlace(3);
        final String[] longg = objPlaceTable.readAllPlace(4);


        MyadpterRoad myadpter = new MyadpterRoad(Road.this, image, titles, detail);
        listView.setAdapter(myadpter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//คลิกจากหน้า Road จะแสดงข้อมูลการเดินทาง
                titles_bus = titles;//เก็บค่าตัวแปรจากจาก SQL
                detail_bus = detail;//เก็บค่าตัวแปรจากจาก SQL
                image_bus = image;//เก็บค่าตัวแปรจากจาก SQL
                latitude = latt;//เก็บค่าตัวแปรจากจาก SQL
                longitude = longg;//เก็บค่าตัวแปรจากจาก SQL
                Intent intent = new Intent(Road.this, GiantSwing.class);//ส่งค่าข้อมูลจากดาต้าเบสไปหน้า GiantSwing
                intent.putExtra("title", titles[position]);
                intent.putExtra("detail", detail[position]);
                intent.putExtra("image", image[position]);
                intent.putExtra("lat", latt[position]);
                intent.putExtra("long", longg[position]);
                startActivity(intent);
            }
        });


    }


    private void c() {
        objPlaceTable = new PlaceTable(this);
        objBusTale = new BusTale(this);
    }

    private void BindWidget() {
        listView = (ListView) findViewById(R.id.livRoad);
    }

    /*public void button2 (View view) {
        Intent intent = new Intent(Road.this,Mainmenu.class);
        startActivity(intent);
    }
    */

    public void btnRoad (View view) {
        Intent intent = new Intent(Road.this,Mainmenu.class);
        startActivity(intent);
    }

}



