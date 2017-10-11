package com.example.user.bustogoappliction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.bustogoappliction.Database.BusTale;

public class NumberBus extends AppCompatActivity {
    private BusTale objBusTale;

    //Explicit
    ListView listView;
    /*private int [] ints = new int[] {R.drawable.one,R.drawable.two,
            R.drawable.tree,R.drawable.four,R.drawable.six,
            R.drawable.seven};*/

    private String[]titles_bus,detail_bus,image_bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_bus);

        BindWidget();
        c();
        Clistview();

    }//main Method onCreate

    private void Clistview() {

        final String[] titles = objBusTale.readAllBus(1);
        final String[] detail = objBusTale.readAllBus(3);
        final String[] image = objBusTale.readAllBus(2);



        Myadpter myadpter = new Myadpter(NumberBus.this, image, titles, detail);
        listView.setAdapter(myadpter);



        //active when click Live View ลิ้งค์ไปหน้า detail เมื่อเลือก item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                titles_bus= titles;
                detail_bus = detail;
                image_bus = image;

                Intent intent = new Intent(NumberBus.this,detail.class);
                intent.putExtra("Title",titles_bus[position]);
                intent.putExtra("Detail",detail_bus[position]);
                intent.putExtra("Image",image_bus[position]);
                startActivity(intent);
            }
        });
    }


    private void c() {
        objBusTale = new BusTale(this);
    }

    private void BindWidget() {
        listView = (ListView) findViewById(R.id.livBus);
    }

    public void button2 (View view) {
        Intent intent = new Intent(NumberBus.this,Mainmenu.class);
        startActivity(intent);
    }

}


