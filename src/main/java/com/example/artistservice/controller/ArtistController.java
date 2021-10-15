package com.example.artistservice.controller;

import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("artists/{name}")
    public List<Artist> getArtistsByName(@PathVariable String name){
        return artistRepository.findArtistsByName(name);
    }

    @GetMapping("artists/{ISBN}")
    public List<Artist> getArtistsByMBID(@PathVariable String MBID){
        return artistRepository.findArtistsByMBID(MBID);
    }

    @PostMapping("/artists")
    public Artist addArtist(@RequestBody Artist artist){
        artistRepository.save(artist);
        return artist;
    }

    @PutMapping("/artists")
    public Artist updateArtist(@RequestBody Artist updatedArtist){
        Artist opgehaaldeArtist = artistRepository.findArtistsByNameAndAndMBID(updatedArtist.getName(), updatedArtist.getMBID());

        opgehaaldeArtist.setName(updatedArtist.getName());
        opgehaaldeArtist.setMBID(updatedArtist.getMBID());

        artistRepository.save(opgehaaldeArtist);

        return opgehaaldeArtist;
    }

    @PostConstruct
    public void opvullen(){
        Artist artist1 = new Artist();
        artist1.setName("Justin Bieber");
        artist1.setMBID("0623964");
        artistRepository.save(artist1);

        Artist artist2 = new Artist();
        artist2.setName("Shawn Mendes");
        artist2.setMBID("0285749");

        System.out.println("Artists test " + artistRepository.findArtistsByMBID("0623964").size());
    }

//    ArrayList<Artist> artists = new ArrayList<>();
//
////    public void opvullen(){
////        Artist artist1 = new Artist();
////        artist1.setName("Justin Bieber");
////        artist1.setMBID("0623964");
////        artistRepository.save(artist1);
////    }
//
//    @PostConstruct
//    public void opvullen() {
//        Artist artist1 = new Artist();
//        artist1.setName("Justin Bieber");
//        artist1.setMBID("0623964");
//        artistRepository.save(artist1);
//    }
//
//    @GetMapping("/artists")
//    public List<Artist> all() {
//        return artists;
//    }
//
//    @GetMapping("/artists/{name}")
//    public List<Artist> getArtistsByName(@PathVariable String name){
//        return artistRepository.findArtistByName(name);
//    }
//
//    @GetMapping("/artists/{MBID}")
//    public List<Artist> getArtistsByMBID(@PathVariable String MBID){
//        return artistRepository.findArtistByMBID(MBID);
//    }
//
//    @PostMapping("/artists")
//    public Artist addArtist(@RequestBody Artist artist){
//        artistRepository.save(artist);
//        return artist;
//    }


}
