package com.moviecatalog.resources;

import com.moviecatalog.model.CatalogItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @GetMapping("/{userId}")
    public List<CatalogItem> getMovies(@PathVariable("userId") String userId){
        return Collections.singletonList(
            new CatalogItem("Titanic", "water movie", 4)
        );
    }
}
