package com.moviecatalog.resources;

import com.moviecatalog.model.CatalogItem;
import com.moviecatalog.model.Movie;
import com.moviecatalog.model.Rating;
import com.moviecatalog.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getMovies(@PathVariable("userId") String userId){

        UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId,
                UserRating.class);

        return userRating.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "water movie", rating.getRating());
                })
                .collect(Collectors.toList());
    }

//    Movie movie = webClientBuilder.build()
//            .get()
//            .uri("http://localhost:8082/movies/" + rating.getMovieId())
//            .retrieve()
//            .bodyToMono(Movie.class)
//            .block();

}
