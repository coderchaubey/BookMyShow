package Book_My_Show.BMS.EntryDtos;

import Book_My_Show.BMS.Enums.Genre;
import Book_My_Show.BMS.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {


    private int id;

    private String movieName;
    private double ratings;
    private int duration;
    private Language language;
    private Genre genre;


}
