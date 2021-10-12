package com.example.artistservice.controller;

import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    public void opvullen(){
        Artist artist1 = new Artist();
        artist1.setName("Justin Bieber");
        artist1.setMBID("0623964");
        artistRepository.save(artist1);
    }

    @GetMapping("/artists/{name}")
    public List<Artist> getArtistsByName(@PathVariable String name){
        return artistRepository.findArtistByName(name);
    }

    @GetMapping("/artists/{MBID}")
    public List<Artist> getArtistsByMBID(@PathVariable String MBID){
        return artistRepository.findArtistByMBID(MBID);
    }

    @PostMapping("/artists")
    public Artist addArtist(@RequestBody Artist artist){
        artistRepository.save(artist);
        return artist;
    }
}
