package com.example.user.bustogoappliction.Search;

import java.sql.Struct;

/**
 * Created by Keng on 3/11/2560.
 */

public class ListEnty {
    private String title;
    private String img;
    private String lon,lat,detail;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getImg(){
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getDetail(){return detail;}
    public void setDetail(String detail){this.detail = detail;}

    public String getLon(){return lon;}
    public void setLon(String lon){this.lon = lon;}

    public String getLat(){return lat;}
    public void setLat(String lat){this.lat = lat;}





}


