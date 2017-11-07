package com.example.user.bustogoappliction.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bustogoappliction.Database.PlaceTable;
import com.example.user.bustogoappliction.Detail.PlaceDetail;
import com.example.user.bustogoappliction.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchPlace extends AppCompatActivity {
     private String[] data;
     private String[] data_detail,data_img,data_lon,data_lat;
     private ListView listView;
     private PlaceTable objPlaceTable;
     private ArrayList<ListEnty> data_search,data_search_detail,data_search_img,data_search_lon,data_search_lat;
     private ArrayList<ListEnty> data_n,data_n_detail,data_n_img,data_n_lon,data_n_lat;
     private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);


        objPlaceTable = new PlaceTable(this);
        data = objPlaceTable.readAllPlace(1);
        data_detail = objPlaceTable.readAllPlace(2);
        data_lat = objPlaceTable.readAllPlace(3);
        data_lon = objPlaceTable.readAllPlace(4);
        data_img = objPlaceTable.readAllPlace(5);

        data_n = new ArrayList<ListEnty>();
        for (int i=0 ;i<data.length;i++){
            ListEnty listEnty = new ListEnty();
            listEnty.setTitle(data[i]);
            data_n.add(listEnty);
        }
        data_n_detail = new ArrayList<ListEnty>();
        for (int i=0 ;i<data_detail.length;i++){
            ListEnty listEnty = new ListEnty();
            listEnty.setDetail(data_detail[i]);
            data_n_detail.add(listEnty);
        }
        data_n_img = new ArrayList<ListEnty>();
        for (int i=0;i<data_img.length;i++){
            ListEnty listEnty=new ListEnty();
            listEnty.setImg(data_img[i]);
            data_n_img.add(listEnty);
        }
        data_n_lat = new ArrayList<ListEnty>();
        for (int i=0;i<data_lat.length;i++){
            ListEnty listEnty=new ListEnty();
            listEnty.setLat(data_lat[i]);
            data_n_lat.add(listEnty);
        }
        data_n_lon = new ArrayList<ListEnty>();
        for (int i=0;i<data_lon.length;i++){
            ListEnty listEnty=new ListEnty();
            listEnty.setLon(data_lon[i]);
            data_n_lon.add(listEnty);
        }


        editText = (EditText) findViewById(R.id.edits_place);
        listView = (ListView)findViewById(R.id.lvs_place);
        doListNomal();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.length() != 0){
                    data_search = new ArrayList<ListEnty>();
                    data_search_detail = new ArrayList<ListEnty>();
                    data_search_img = new ArrayList<ListEnty>();
                    data_search_lat = new ArrayList<ListEnty>();
                    data_search_lon =new ArrayList<ListEnty>();
                    for (int k=0; k<data_n.size();k++){
                        if (data_n.get(k).getTitle().toLowerCase().contains(charSequence)){
                            ListEnty listEnty = new ListEnty();
                            ListEnty listEnty_detail = new ListEnty();
                            ListEnty listEnty_img = new ListEnty();
                            ListEnty listEnty_lat = new ListEnty();
                            ListEnty listEnty_lon = new ListEnty();
                            listEnty.setTitle(data_n.get(k).getTitle());
                            listEnty_detail.setDetail(data_n_detail.get(k).getDetail());
                            listEnty_img.setImg(data_n_img.get(k).getImg());
                            listEnty_lon.setLon(data_n_lon.get(k).getLon());
                            listEnty_lat.setLat(data_n_lat.get(k).getLat());
                            data_search.add(listEnty);
                            data_search_detail.add(listEnty_detail);
                            data_search_img.add(listEnty_img);
                            data_search_lon.add(listEnty_lon);
                            data_search_lat.add(listEnty_lat);
                        }
                    }

                    doListSearch();
                }else {
                    doListNomal();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void doListSearch() {
        listView.setAdapter(new CustomAdapter(data_search));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SearchPlace.this,data_search.get(i).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchPlace.this, PlaceDetail.class);
                intent.putExtra("title",data_search.get(i).getTitle());
                intent.putExtra("detail",data_search_detail.get(i).getDetail());
                intent.putExtra("image",data_search_img.get(i).getImg());
                intent.putExtra("lat",data_search_lat.get(i).getLat());
                intent.putExtra("long",data_search_lon.get(i).getLon());
                startActivity(intent);
            }
        });
    }

    private void doListNomal() {
        listView.setAdapter(new CustomAdapter(data_n));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SearchPlace.this,data_n.get(i).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchPlace.this,PlaceDetail.class);
                intent.putExtra("title",data_n.get(i).getTitle());
                intent.putExtra("detail",data_n_detail.get(i).getDetail());
                intent.putExtra("image",data_n_img.get(i).getImg());
                intent.putExtra("lat",data_n_lat.get(i).getLat());
                intent.putExtra("long",data_n_lon.get(i).getLon());
                startActivity(intent);
            }
        });
    }

    public class CustomAdapter extends BaseAdapter{
            private ArrayList<ListEnty> mData;
            private Holder holder = new Holder();
            public CustomAdapter (ArrayList<ListEnty> data){
                this.mData = data;
            }

            @Override
            public int getCount() {
                return mData.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = View.inflate(SearchPlace.this,R.layout.item_search_place,null);
                    if (view != null){
                        holder.title = (TextView) view.findViewById(R.id.item_search_place);

                        holder.title.setText(mData.get(i).getTitle());

                        view.setTag(holder);

                    }else {
                        view = (View) view.getTag();
                    }
                return view;
            }
                public class Holder{
                    public TextView title;
                }

        }

     public void imv_back(View view){
        finish();
     }
}
