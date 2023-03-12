package Book_My_Show.BMS.Services;

import Book_My_Show.BMS.EntryDtos.MovieEntryDto;
import Book_My_Show.BMS.Model.Movie;
import Book_My_Show.BMS.Repositories.MovieRepository;
import Book_My_Show.BMS.convertors.MovieConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto)throws Exception{

        Movie movie= MovieConvertor.convertEntryDtoToMovieEntity(movieEntryDto);

        movieRepository.save(movie);
        return "Movie Added Successfully";
    }

    //My codes
    public String getMovieById(int id){
       Movie movie=movieRepository.findById(id).get();
       return movie.getMovieName();
    }
    public List<String> getAllMovies(){

        List<String> movieName=movieRepository.getAllMovies();
        return movieName;
    }
}
