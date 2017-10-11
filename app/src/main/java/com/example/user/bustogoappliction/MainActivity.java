package com.example.user.bustogoappliction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.example.user.bustogoappliction.Database.BusTale;
import com.example.user.bustogoappliction.Database.PlaceTable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private BusTale objBusTale;
    private PlaceTable objPlaceTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cdatabase();
       deleteAlldata();
       synJSONtoSQLite();
    }

    private void deleteAlldata() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("Bus.db", MODE_APPEND, null);
        objSqLiteDatabase.delete("bustable", null, null);
        objSqLiteDatabase.delete("placetable", null, null);

    }

    private void synJSONtoSQLite() {
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTimes = 0;
        while (intTimes <= 2) {
            InputStream objInputStream = null;
            String strJSON = null;
            String strBus = "http://5711020660005.sci.dusit.ac.th/busTABLE1.php";
            String strPlace = "http://5711020660005.sci.dusit.ac.th/placeTABLE1.php";
            HttpPost objHttpPost = null;

            //1.create InputStream
            try {
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes) {
                    case 0:
                        objHttpPost = new HttpPost(strBus);
                        break;
                    default:
                        objHttpPost = new HttpPost(strPlace);
                        break;
                }
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();
            } catch (Exception e) {
                Log.d("bus", "InputStream ==>" + e.toString());
            }
            //2.Create strJSON
            try {
                InputStreamReader objInputStreamReader = new InputStreamReader(objInputStream, "UTF-8");
                BufferedReader objBufferedReader = new BufferedReader(objInputStreamReader);
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = objStringBuilder.toString();
            } catch (Exception e) {
                Log.d("bus", "strJSON" + e.toString());
            }
            //3.Update to SQLite
            try {
                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTimes) {
                        case 0:
                            //update busTable
                            String stridbus = jsonObject.getString("bus_id");
                            String strbusname = jsonObject.getString("bus_name");
                            String strbusdetail = jsonObject.getString("bus_detail");
                            String strbuspic = jsonObject.getString("bus_pic");
                            objBusTale.AddNewBusTable(stridbus, strbusname, strbusdetail, strbuspic);
                            break;
                        default:
                            //update placetable
                            String stridplace = jsonObject.getString("place_id");
                            String strplacename = jsonObject.getString("place_name");
                            String strplacedetail = jsonObject.getString("place_detail");
                            String strplacelat = jsonObject.getString("place_lat");
                            String strplacelong = jsonObject.getString("place_long");
                            String strplacepic = jsonObject.getString("place_pic");
                            objPlaceTable.AddNewPlaceTable(stridplace, strplacename, strplacedetail, strplacelat, strplacelong, strplacepic);
                            break;
                    }
                }
            } catch (Exception e) {
                Log.d("bus", "strJSON" + e.toString());
            }
            intTimes += 1;
        }
    }

    private void Cdatabase() {
        objBusTale = new BusTale(this);
        objPlaceTable = new PlaceTable(this);
    }

    public void btn2 (View view) {
        Intent intent = new Intent(MainActivity.this,Mainmenu.class);
        startActivity(intent);
    }
}
