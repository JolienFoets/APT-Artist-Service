package com.example.artistservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("artists")
public class Artist {
    @Id
    private String id;
    private int artistId;
    private String name;
    private String mbid;
    private int numberStreams;

    public Artist(String id, int artistId, String name, String mbid, int numberStreams){
        super();
        this.id = id;
        this.artistId = artistId;
        this.name = name;
        this.mbid = mbid;
        this.numberStreams = numberStreams;
    }

    public int getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getmbid() {
        return mbid;
    }

    public void setmbid(String mbid) {
        this.mbid = mbid;
    }

    public int getNumberStreams() {
        return numberStreams;
    }

    public void setNumberStreams(int numberStreams) {
        this.numberStreams = numberStreams;
    }
}
