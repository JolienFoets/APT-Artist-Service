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
        artists.add(new Artist("0", 1,"Justin Bieber", "0623964", 200));
        artists.add(new Artist("1", 2, "Katy Perry", "0734075", 150));
    }


    @GetMapping("/artists")
    public List<Artist> all(){
        return artists;
    }

    @GetMapping("/artists/{artistId}")
    public Artist one(@PathVariable int artistId){
        for(Artist a : artists){
            if(a.getArtistId() == artistId){
                return a;
            }
        }
        return null;
    }

    @PutMapping("/artists/{artistId}")
    public Artist replaceArtist(@RequestBody Artist updateArtist, @PathVariable int artistId){
        for(Artist a : artists){
            if(a.getArtistId() == artistId){
                a.setId(updateArtist.getId());
                a.setName(updateArtist.getName());
                a.setmbid(updateArtist.getmbid());
                a.setNumberStreams(updateArtist.getNumberStreams());
                return a;
            }
        }
        return null;
    }

    @DeleteMapping("/artists/{artistId}")
    public void deleteArtist(@PathVariable int artistId){
        for(Artist a : artists){
            if(a.getArtistId() == artistId){
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
