package com.example.zomato.db;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;


/**
 * Created by Rajat Sangrame on 22/4/20.
 * http://github.com/rajatsangrame
 */

@Entity(tableName = "restaurant")
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "desc")
    private String desc;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private Long timestamp;

    public Restaurant(@NonNull String title, @NonNull String desc, @NonNull Long timestamp) {
        this.title = title;
        this.desc = desc;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }

    public void setDesc(@NonNull String desc) {
        this.desc = desc;
    }

    @NonNull
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull Long timestamp) {
        this.timestamp = timestamp;
    }

    public String toJsonString() {
        return new Gson().toJson(this);
    }

}
