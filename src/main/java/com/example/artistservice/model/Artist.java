package com.example.artistservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("artists")
public class Artist {
    @Id
    private int id;
    private String name;
    private String MBID;

    public Artist(int id, String name, String MBID){
        super();
        this.id = id;
        this.name = name;
        this.MBID = MBID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMBID() {
        return MBID;
    }

    public void setMBID(String MBID) {
        this.MBID = MBID;
    }
}
