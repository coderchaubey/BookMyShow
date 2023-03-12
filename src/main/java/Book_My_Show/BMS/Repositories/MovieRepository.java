package Book_My_Show.BMS.Repositories;

import Book_My_Show.BMS.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer>{

    @Query(value = "select movie_name from movies",
            nativeQuery = true)
    List<String> getAllMovies();
}
