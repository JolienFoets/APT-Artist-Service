package com.example.artistservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("artists")
public class Artist {
    @Id
    private String id;
    private int artistId;
    private String name;
    private String MBID;
    private int numberStreams;

    public Artist(String id, int artistId, String name, String MBID, int numberStreams){
        super();
        this.id = id;
        this.artistId = artistId;
        this.name = name;
        this.MBID = MBID;
        this.numberStreams = numberStreams;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
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

    public int getNumberStreams() {
        return numberStreams;
    }

    public void setNumberStreams(int numberStreams) {
        this.numberStreams = numberStreams;
    }
}
