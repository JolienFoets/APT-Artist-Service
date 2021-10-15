package com.example.artistservice.repository;

import com.example.artistservice.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {
    List<Artist> findArtistsByName(String name);
    List<Artist> findArtistsByMBID(String MBID);
    Artist findArtistsByNameAndAndMBID(String name, String MBID);
}