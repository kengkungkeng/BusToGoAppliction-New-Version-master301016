package com.example.user.bustogoappliction.Detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bustogoappliction.R;
import com.example.user.bustogoappliction.Road;
import com.squareup.picasso.Picasso;

public class PlaceDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        ImageView detailImageView = (ImageView) findViewById(R.id.detailimage);
        TextView titleTextView = (TextView) findViewById(R.id.titleRoad);
        TextView nobusTextView = (TextView) findViewById(R.id.noBus);

        Button linkButton = (Button) findViewById(R.id.btnLink);

        titleTextView.setText(getIntent().getStringExtra("title"));       //รับค่าที่ส่งมาจากหน้า Road
        nobusTextView.setText(getIntent().getStringExtra("detail"));
        String image = getIntent().getStringExtra("image");
        Picasso.with(PlaceDetail.this).load(image).into(detailImageView);


    }

    public void imv_back (View view) {

        finish();
    }


    public  void link (View view){


        String lat = getIntent().getStringExtra("lat");//รับค่ามาจาก ดาต้าเบสเพื่อคลิกแล้วจะแสดงตามค่า lat + long ที่เก็บไว้
        String log = getIntent().getStringExtra("long");
        String title = getIntent().getStringExtra("title");
        Uri location= Uri.parse("http://maps.google.co.in/maps?q="+lat+","+log+"("+title+")");
        //Uri location = Uri.parse("google.navigation:q=เสาชิงช้า"); //ต้องลองใช้กับโทรศัพท์ถึงจะเหมือนสุด
        //Uri location = Uri.parse("http://maps.google.co.in/maps?q=" + "เสาชิงช้า");  // พิมพ์ชื่อสถานที่จะไปก็ไปได้เลย
        Intent mapIntent =new Intent(Intent.ACTION_VIEW,location);
        //mapIntent.setPackage("com.google.android.apps.maps"); //ไม่สามารถใช้กับจินนี่โมชันได้
        startActivity(mapIntent);

    }//call Map
}
