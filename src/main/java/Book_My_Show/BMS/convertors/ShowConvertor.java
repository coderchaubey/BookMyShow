package Book_My_Show.BMS.convertors;

import Book_My_Show.BMS.EntryDtos.ShowEntryDto;
import Book_My_Show.BMS.Model.Show;

public class ShowConvertor {

    public static Show convertEntryToEntity(ShowEntryDto showEntryDto){

        Show show=Show.builder().showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType())
                .build();
        return show;

    }
}
