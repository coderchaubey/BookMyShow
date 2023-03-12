package Book_My_Show.BMS.convertors;

import Book_My_Show.BMS.EntryDtos.MovieEntryDto;
import Book_My_Show.BMS.Model.Movie;

public class MovieConvertor {
    public static Movie convertEntryDtoToMovieEntity(MovieEntryDto movieEntryDto){

        Movie movie=Movie.builder().movieName(movieEntryDto.getMovieName()).
                duration(movieEntryDto.getDuration()).
                id(movieEntryDto.getId()).
                genre(movieEntryDto.getGenre()).
                language(movieEntryDto.getLanguage()).
                ratings(movieEntryDto.getRatings()).build();

        return movie;
    }
}
