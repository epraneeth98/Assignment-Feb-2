package com.epraneeth.assignmentfeb2.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Entry implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "mobile")
    public String mobile;

    public Entry(String name, String dob, String mobile) {
        this.name = name;
        this.dob = dob;
        this.mobile = mobile;
    }

    @Ignore
    public Entry(int uid, String name, String dob, String mobile) {
        this.uid = uid;
        this.name = name;
        this.dob = dob;
        this.mobile = mobile;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
