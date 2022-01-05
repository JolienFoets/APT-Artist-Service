package com.example.artistservice;

import com.example.artistservice.model.Artist;
import com.example.artistservice.repository.ArtistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRepository artistRepository;

    private ObjectMapper mapper = new ObjectMapper();

    /*@Test
    public void givenArtist_whenGetArtistByArtistIdAndMBID_thenReturnJsonArtist() throws Exception {
        Artist artistArtist1Album1 = new Artist("1",001,"Justin Bieber", "0623964", 10);

        given(artistRepository.findArtistsByNameAndAndMBID("Justin Bieber","0623964")).willReturn(artistArtist1Album1);

        mockMvc.perform(get("/api/artists/{artistId}",001))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artistId",is(001)))
                .andExpect(jsonPath("$.MBID",is("0623964")))
                .andExpect(jsonPath("$.numberStreams",is(10)));
    }*/

    /*@Test
    public void givenArtist_whenGetArtistByMBID_thenReturnJsonArtists() throws Exception {
        Artist artistArtist1Album1 = new Artist("1",001,"Justin Bieber", "0623964", 10);
        Artist artistArtist2Album1 = new Artist("2",002,"Shawn Mendes", "3956297", 4);

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artistArtist1Album1);
        artistList.add(artistArtist2Album1);

        given(artistRepository.findArtistsByMBID("0623964")).willReturn(artistList);

        mockMvc.perform(get("/api/artists/{MBID}","0623964"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].artistId",is(001)))
                .andExpect(jsonPath("$[0].MBID",is("0623964")))
                .andExpect(jsonPath("$[0].numberStreams",is(10)))
                .andExpect(jsonPath("$[1].artistId",is(002)))
                .andExpect(jsonPath("$[1].MBID",is("3956297")))
                .andExpect(jsonPath("$[1].numberStreams",is(4)));
    }*/


    @Test
    void givenArtist_whenGetArtistByArtistId_thenReturnJsonArtists() throws Exception {
        Artist artistArtist1Album1 = new Artist("1",001,"Justin Bieber", "0623964", 10);
        Artist artistArtist1Album2 = new Artist("1",002,"Justin Bieber", "0623964", 4);

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artistArtist1Album1);
        artistList.add(artistArtist1Album2);

        given(artistRepository.findArtistByArtistId(001)).willReturn(artistArtist1Album1);

        mockMvc.perform(get("/api/artists/{artistId}",001))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.artistId",is(001)))
                .andExpect(jsonPath("$.mbid",is("0623964")))
                .andExpect(jsonPath("$.numberStreams",is(10)));
    }

    @Test
    void givenArtist_whenGetArtists_thenReturnJsonArtists() throws Exception {
        Artist artistArtist1Album1 = new Artist("1",001,"Justin Bieber", "0623964", 10);
        Artist artistArtist1Album2 = new Artist("1",002,"Lady Gaga", "1234567", 4);

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artistArtist1Album1);
        artistList.add(artistArtist1Album2);

        given(artistRepository.findAll()).willReturn(artistList);

        mockMvc.perform(get("/api/artists"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].artistId",is(001)))
                .andExpect(jsonPath("$[0].name",is("Justin Bieber")))
                .andExpect(jsonPath("$[0].mbid",is("0623964")))
                .andExpect(jsonPath("$[0].numberStreams",is(10)))
                .andExpect(jsonPath("$[1].artistId",is(002)))
                .andExpect(jsonPath("$[1].name",is("Lady Gaga")))
                .andExpect(jsonPath("$[1].mbid",is("1234567")))
                .andExpect(jsonPath("$[1].numberStreams",is(4)));
    }

    @Test
    void whenPostArtist_thenReturnJsonArtist() throws Exception{
        Artist artistArtist3Album1 = new Artist("3",003,"Katy Perry", "5178419", 10);

        mockMvc.perform(post("/api/artists")
                .content(mapper.writeValueAsString(artistArtist3Album1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artistId",is(003)))
                .andExpect(jsonPath("$.mbid",is("5178419")))
                .andExpect(jsonPath("$.numberStreams",is(10)));
    }

    @Test
    void givenArtist_whenPutArtist_thenReturnJsonArtist() throws Exception{
        Artist artistArtist1Album1 = new Artist("1",001,"Justin Bieber", "0623964", 10);

        given(artistRepository.findArtistByArtistId(001)).willReturn(artistArtist1Album1);

        Artist updatedArtist = new Artist("1",001,"Justin Bieber", "0623964", 5);

        mockMvc.perform(put("/api/artists/{artistId}", 001)
                .content(mapper.writeValueAsString(updatedArtist))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.artistId",is(001)))
                .andExpect(jsonPath("$.mbid",is("0623964")))
                .andExpect(jsonPath("$.numberStreams",is(5)));
    }

    @Test
    void givenArtist_whenDeleteArtist_thenStatusOk() throws Exception{
        Artist artistToBeDeleted = new Artist("999",999,"TestArtist", "1254785", 20);

        given(artistRepository.findArtistByArtistId(999)).willReturn(artistToBeDeleted);

        mockMvc.perform(delete("/api/artists/{artistId}", 999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenNoArtist_whenDeleteArtist_thenStatusNotFound() throws Exception{
        given(artistRepository.findArtistsByArtistId(5000)).willReturn(null);

        mockMvc.perform(delete("/api/artists/{artistId}",5000)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}