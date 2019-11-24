package pl.dsyou.movieapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.dsyou.movieapp.data.movie.MovieService;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoviesApiControllerShould {

    private static final String URI = "/movies";

    @Mock
    private MovieService movieService;

    private MoviesApiController moviesApiController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);

        moviesApiController = new MoviesApiController(movieService);
        mockMvc = MockMvcBuilders.standaloneSetup(moviesApiController)
                .build();
    }

    @Test
    public void getMovie() throws Exception {
        // given
        String id = "5dc9b360a69bf4138ed4a6ce";
        var movieDetails = new MovieDetails(id, "Matrix", "Action", "01-01-1999", 0.1f);
        when(movieService.getMovie(id)).thenReturn(movieDetails);

        // when
        mockMvc.perform(get(URI + "/{id}", id))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Matrix"))
                .andExpect(jsonPath("$.genre").value("Action"))
                .andExpect(jsonPath("$.productionDate").value("01-01-1999"))
                .andExpect(jsonPath("$.score").value(0.1));
    }

    @Test
    public void getMovies() throws Exception {
        // given
        var movie1 = new MovieDetails("1", "Matrix I", "Action", "01-01-1999", 10f);
        var movie2 = new MovieDetails("2", "Matrix II", "Comedy", "01-20-2000", 8f);
        var movie3 = new MovieDetails("3", "Matrix III", "Fable", "01-30-2002", 7f);
        when(movieService.getMovies()).thenReturn(List.of(movie1, movie2, movie3));

        // when
        mockMvc.perform(get(URI))
                // then
                // movie-1
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value("Matrix I"))
                .andExpect(jsonPath("$[0].genre").value("Action"))
                .andExpect(jsonPath("$[0].productionDate").value("01-01-1999"))
                .andExpect(jsonPath("$[0].score").value(10f))
                // movie-2
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].title").value("Matrix II"))
                .andExpect(jsonPath("$[1].genre").value("Comedy"))
                .andExpect(jsonPath("$[1].productionDate").value("01-20-2000"))
                .andExpect(jsonPath("$[1].score").value(8f))
                // movie-3
                .andExpect(jsonPath("$[2].id").value("3"))
                .andExpect(jsonPath("$[2].title").value("Matrix III"))
                .andExpect(jsonPath("$[2].genre").value("Fable"))
                .andExpect(jsonPath("$[2].productionDate").value("01-30-2002"))
                .andExpect(jsonPath("$[2].score").value(7f));
    }

    @Test
    void createMovie() throws Exception {
        // given
        String id = "5dc9b360a69bf4138ed4a6ce";
        var requests = new MovieRegistration("Matrix II", "Action", "01-01-1999");
        var responses = new MovieDetails(id, "Matrix II", "Action", "01-01-1999", 0.1f);
        when(movieService.createMovie(requests)).thenReturn(responses);

        // when
        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requests)))
                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Matrix II"))
                .andExpect(jsonPath("$.genre").value("Action"))
                .andExpect(jsonPath("$.productionDate").value("01-01-1999"))
                .andExpect(jsonPath("$.score").value(0.1));
    }

    @Test
    void editMovie() throws Exception {
        // given
        String id = "5dc9b360a69bf4138ed4a6ce";
        var requests = new MovieUpdate("Matrix I", "Action", "01-01-1999");
        var responses = new MovieDetails(id, "Matrix II", "Action", "01-01-1999", 0.1f);
        when(movieService.editMovie(requests, id)).thenReturn(responses);

        // when
        mockMvc.perform(put(URI + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requests)))
                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Matrix II"))
                .andExpect(jsonPath("$.genre").value("Action"))
                .andExpect(jsonPath("$.productionDate").value("01-01-1999"))
                .andExpect(jsonPath("$.score").value(0.1));
    }

    @Test
    void deleteMovie() throws Exception {
        // given
        String id = "5dc9b360a69bf4138ed4a6ce";
        doNothing().when(movieService).deleteMovie(id);

        // when
        mockMvc.perform(delete(URI + "/{id}", id))
                // then
                .andExpect(status().isNoContent());

        verify(movieService, times(1)).deleteMovie(id);
    }

//    @Test
//    void addMovieRank() {
//        // todo dj
//    }
}