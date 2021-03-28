package com.tarun.internassignment.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crew_table")
public class RoomCrewModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "agency")
    private String agency;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    @ColumnInfo(name = "wiki")
    private String wiki;

    public RoomCrewModel(CrewDataModel crewDataModel, byte[] bytes){
        this.name = crewDataModel.name;
        this.agency = crewDataModel.agency;
        this.status = crewDataModel.status;
        this.image = bytes;
        this.wiki = crewDataModel.wikipedia;
    }
    public RoomCrewModel(String name, String agency, String status, byte[] image, String wiki) {
        this.name = name;
        this.agency = agency;
        this.status = status;
        this.image = image;
        this.wiki = wiki;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getStatus() {
        return status;
    }

    public byte[] getImage() {
        return image;
    }

    public String getWiki() {
        return wiki;
    }
}
