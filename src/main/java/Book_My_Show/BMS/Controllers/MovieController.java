package Book_My_Show.BMS.Controllers;


import Book_My_Show.BMS.EntryDtos.MovieEntryDto;
import Book_My_Show.BMS.Model.Movie;
import Book_My_Show.BMS.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){

        try {

          String result=  movieService.addMovie(movieEntryDto);
          return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e){
            String response="Movie Not Added";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

        }

    }

    //My Codes
    @GetMapping("/getMovieId")
    public String getMovieById(@RequestParam int id){
            String movie =movieService.getMovieById(id);
            return movie;
    }

    @GetMapping("/getAllMovies")
    public List<String> getAllMovies(){
           List<String> movieName = movieService.getAllMovies();
           return movieName;
    }
}
