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

import com.example.user.bustogoappliction.Database.BusTable;
import com.example.user.bustogoappliction.Detail.BusDetail;
import com.example.user.bustogoappliction.R;

import java.util.ArrayList;

public class SearchBus extends AppCompatActivity {
    private String[] data;
    private String[] data_detail;
    private ListView listView;
    private BusTable objBusTable;
    private ArrayList<ListEnty> data_n;
    private ArrayList<ListEnty> data_search;
    private ArrayList<ListEnty> data_detail_n;
    private ArrayList<ListEnty> data_detail_search;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bus);
        objBusTable = new BusTable(this);

        data = objBusTable.readAllBus(1);
        data_detail = objBusTable.readAllBus(3);
        //view matching
        listView = (ListView) findViewById(R.id.lvs_bus);
        //loop
        data_detail_n = new ArrayList<ListEnty>();
        for (int i=0;i<data_detail.length;i++){
            ListEnty listEnty =new ListEnty();
            listEnty.setTitle(data_detail[i]);
            data_detail_n.add(listEnty);
        }
        data_n = new ArrayList<ListEnty>();
        for (int i= 0;i<data.length;i++){
            ListEnty listEnty = new ListEnty();
            listEnty.setTitle(data[i]);
            data_n.add(listEnty);
        }
        editText = (EditText)findViewById(R.id.edits_bus);
        doListNomal();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.length() !=0){
                    data_search = new ArrayList<ListEnty>();
                    for (int k=0;k < data_n.size();k++ ){
                        if(data_n.get(k).getTitle().toLowerCase().contains(charSequence)){
                            ListEnty listEnty = new ListEnty();
                            listEnty.setTitle(data_n.get(k).getTitle());
                            data_search.add(listEnty);
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
                Toast.makeText(SearchBus.this,data_search.get(i).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchBus.this, BusDetail.class);
                intent.putExtra("Title",data_search.get(i).getTitle());
                intent.putExtra("Detail",data_detail_n.get(i).getTitle());
                startActivity(intent);
            }
        });
    }

    private void doListNomal() {
        listView.setAdapter(new CustomAdapter(data_n));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SearchBus.this,data_n.get(i).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchBus.this, BusDetail.class);
                intent.putExtra("Title",data_n.get(i).getTitle());
                intent.putExtra("Detail",data_detail_n.get(i).getTitle());
                startActivity(intent);

            }
        });
    }

    public class CustomAdapter extends BaseAdapter{

        private ArrayList<ListEnty> mData;
        private Holder holder = new Holder();
        public CustomAdapter(ArrayList<ListEnty> data){
            this.mData=data;

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
            view = View.inflate(SearchBus.this,R.layout.item_search_bus,null);
            if (view != null){
                holder.title = (TextView) view.findViewById(R.id.item_s);
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
