package com.example.artistservice.controller;

import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    ArrayList<Artist> artists = new ArrayList<>();

    @PostConstruct
    public void opvullen(){
        artists.add(new Artist(0, "Justin Bieber", "0623964"));
        artists.add(new Artist(1, "Katy Perry", "0734075"));
    }


    @GetMapping("/artists")
    public List<Artist> all(){
        return artists;
    }

    @GetMapping("/artists/{id}")
    public Artist one(@PathVariable int id){
        for(Artist a : artists){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    @PutMapping("/artists/{id}")
    public Artist replaceArtist(@RequestBody Artist updateArtist, @PathVariable int id){
        for(Artist a : artists){
            if(a.getId() == id){
                a.setName(updateArtist.getName());
                a.setMBID(updateArtist.getMBID());
                return a;
            }
        }
        return null;
    }

    @DeleteMapping("/artists/{id}")
    public void deleteArtist(@PathVariable int id){
        for(Artist a : artists){
            if(a.getId() == id){
                artists.remove(a);
            }
        }
    }

//    @GetMapping("/artists/{name}")
//    public List<Artist> getArtistsByName(@PathVariable String name){
//        return artistRepository.findArtistsByName(name);
//    }
//
//    @GetMapping("/artists/{ISBN}")
//    public List<Artist> getArtistsByMBID(@PathVariable String MBID){
//        return artistRepository.findArtistsByMBID(MBID);
//    }
    
    @PostMapping("/artists")
    Artist add(@RequestBody Artist newArtist){
        artists.add(newArtist);
        return newArtist;
    }


}
