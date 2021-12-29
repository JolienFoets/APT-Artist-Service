package com.example.artistservice.controller;

import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    //ArrayList<Artist> artists = new ArrayList<>();



    @PostConstruct
    public void fillDB() {
        if (artistRepository.count() == 0) {
            //nog de juiste mbid voor vinden OF barcode gebruiken ipv imdb OF ISNI?
            //Moeten de albums overeenkomen met de artiesten? (min. 1 album dat bij een artiest hoort?)
            artistRepository.save(new Artist("0", 1, "Justin Bieber", "0623964", 200));
            artistRepository.save(new Artist("1", 2, "Katy Perry", "0734075", 150));
        }
    }

    /*public void opvullen(){
        artists.add(new Artist("0", 1,"Justin Bieber", "0623964", 200));
        artists.add(new Artist("1", 2, "Katy Perry", "0734075", 150));
    }*/


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

        //retrievedAlbum.setId(updatedAlbum.getId());
        //retrievedAlbum.setAlbumId(updatedAlbum.getAlbumId());
        //retrievedArtist.setId(updatedArtist.getId());
        retrievedArtist.setName(updatedArtist.getName());
        retrievedArtist.setName(updatedArtist.getMBID());
        retrievedArtist.setNumberStreams(updatedArtist.getNumberStreams());

        artistRepository.save(retrievedArtist);
        return retrievedArtist;
    }

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity deleteArtist(@PathVariable int artistId){
        Artist artist = artistRepository.findArtistByArtistId(artistId);
        //albumRepository.delete(album);

        if(artist!=null){
            //albumRepository.delete(album);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    /*@PutMapping("/artists/{artistId}")
    @ResponseBody
    public Artist replaceArtist(@RequestBody Artist updateArtist, @PathVariable int artistId){
        for(Artist a : artists){
            if(a.getArtistId() == artistId){
                a.setId(updateArtist.getId());
                a.setName(updateArtist.getName());
                a.setMBID(updateArtist.getMBID());
                a.setNumberStreams(updateArtist.getNumberStreams());
                return a;
            }
        }
        return null;
    }

    @DeleteMapping("/artists/{artistId}")
    @ResponseBody
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
    @ResponseBody
    Artist add(@RequestBody Artist newArtist){
        artists.add(newArtist);
        return newArtist;
    }
*/

}
