package com.example.artistservice.repository;

import com.example.artistservice.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {
    List<Artist> findArtistsByName(String name);
    List<Artist> findArtistsByMbid(String mbid);
    List<Artist> findArtistsByArtistId(int artistId);
    Artist findArtistsByNameAndAndMbid(String name, String mbid);
    Artist findArtistByArtistId(int artistId);
}
