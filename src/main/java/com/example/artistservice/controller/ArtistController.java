package com.example.artistservice.controller;

import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @PostConstruct
    public void fillDB() {
        if (artistRepository.count() == 0) {
            artistRepository.save(new Artist("0", 1, "Justin Bieber", "0623964", 200));
            artistRepository.save(new Artist("1", 2, "Katy Perry", "0734075", 150));
        }
    }

    @GetMapping("/artists")
    @ResponseBody
    public List<Artist> getAllArtists(){
        return artistRepository.findAll();
    }

    @GetMapping("/artists/{artistId}")
    public Artist findArtistByArtistId(@PathVariable int artistId) {
        return artistRepository.findArtistByArtistId(artistId);
    }

    @PostMapping("/artists")
    public Artist addArtist(@RequestBody Artist artist){
        artistRepository.save(artist);
        return artist;
    }

    @PutMapping("/artists/{artistId}")
    @ResponseBody
    public Artist updateArtist(@RequestBody Artist updatedArtist, @PathVariable int artistId){
        Artist retrievedArtist = artistRepository.findArtistByArtistId(updatedArtist.getArtistId());

        retrievedArtist.setName(updatedArtist.getName());
        retrievedArtist.setmbid(updatedArtist.getmbid());
        retrievedArtist.setNumberStreams(updatedArtist.getNumberStreams());

        artistRepository.save(retrievedArtist);
        return retrievedArtist;
    }

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity deleteArtist(@PathVariable int artistId){
        Artist artist = artistRepository.findArtistByArtistId(artistId);

        if(artist!=null){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
