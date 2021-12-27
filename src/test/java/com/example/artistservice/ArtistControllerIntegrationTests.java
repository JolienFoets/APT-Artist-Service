package com.example.artistservice;

import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
/*import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



/*
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
*/

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArtistRepository artistRepository;

    private Artist artistArtist1Album1 = new Artist("1", 001, "Justin Bieber", "0623964", 5);
    private Artist artistArtist1Album2 = new Artist("1", 001, "Justin Bieber", "0623964", 10);
    private Artist artistArtist2Album1 = new Artist("2", 002, "Shawn Mendes", "3956297", 4);
    private Artist artistToBeDeleted = new Artist("99", 99, "Lady Gaga", "4167318", 6);

    @BeforeEach
    public void beforeAllTests() {
        artistRepository.deleteAll();
        artistRepository.save(artistArtist1Album1);
        artistRepository.save(artistArtist1Album2);
        artistRepository.save(artistArtist2Album1);
        artistRepository.save(artistToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {
        artistRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenArtist_whenGetArtistByArtistIdAndMBID_thenReturnJsonArtist() throws Exception {
        mockMvc.perform(get("/artists/{artistId}/{MBID}", 001, "0623964"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artistId", is(001)))
                .andExpect(jsonPath("$.MBID", is("0623964")))
                .andExpect(jsonPath("$.numberStreams", is(5)));
    }

    @Test
    public void givenArtist_whenGetArtistsByMBID_thenReturnJsonArtists() throws Exception {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artistArtist1Album1);
        artistList.add(artistArtist2Album1);

        mockMvc.perform(get("/artists/{MBID}", "0623964"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].artistId", is(001)))
                .andExpect(jsonPath("$[0].MBID", is("0623964")))
                .andExpect(jsonPath("$[0].numberStreams", is(5)))
                .andExpect(jsonPath("$[1].artistId", is(002)))
                .andExpect(jsonPath("$[1].MBID", is("3956297")))
                .andExpect(jsonPath("$[1].numberStreams", is(4)));

    }

    @Test
    public void givenArtist_whenGetArtistsByArtistId_thenReturnJsonArtists() throws Exception {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artistArtist1Album1);
        artistList.add(artistArtist1Album2);

        mockMvc.perform(get("/artists/{artistId}", 001))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].artistId", is(001)))
                .andExpect(jsonPath("$[0].MBID", is("0623964")))
                .andExpect(jsonPath("$[0].numberStreams", is(5)))
                .andExpect(jsonPath("$[1].artistId", is(001)))
                .andExpect(jsonPath("$[1].MBID", is("0623964")))
                .andExpect(jsonPath("$[1].numberStreams", is(10)));
    }

    @Test
    public void whenPostArtist_thenReturnJsonArtist() throws Exception {
        Artist artistArtist3Album1 = new Artist("3", 003, "Katy Perry", "5178419", 10);

        mockMvc.perform(post("/artists")
                .content(mapper.writeValueAsString(artistArtist3Album1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artistId", is(003)))
                .andExpect(jsonPath("$.MBID", is("5178419")))
                .andExpect(jsonPath("$.numberStreams", is(10)));

    }

    @Test
    public void givenArtist_whenPutArtist_thenReturnJsonArtist() throws Exception {
        Artist updatedArtist = new Artist("1", 001, "Justin Bieber", "0623964", 9);

        mockMvc.perform(put("/artists")
                .content(mapper.writeValueAsString(updatedArtist))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artistId", is(001)))
                .andExpect(jsonPath("$.MBID", is("0623964")))
                .andExpect(jsonPath("$.numberStreams", is(9)));
    }

    @Test
    public void givenArtist_whenDeleteArtist_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/artists/{artistId}", 999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoArtist_whenDeleteArtist_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/artists/{artistId}", 888)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
