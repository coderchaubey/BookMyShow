package Book_My_Show.BMS.convertors;

import Book_My_Show.BMS.EntryDtos.TheatreEntryDto;
import Book_My_Show.BMS.Model.Theatre;

public class TheatreConvertor {

    public static Theatre convertDtoToTheatreEntity(TheatreEntryDto theatreEntryDto){

        return Theatre.builder()
                .location(theatreEntryDto.getLocation())
                .name(theatreEntryDto
                .getName()).build();
    }
}
