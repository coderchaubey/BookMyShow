package Book_My_Show.BMS.EntryDtos;

import lombok.Data;

@Data
public class TheatreEntryDto {

    //Attributes that are required
    private String name;
    private String location;
    private int classicSeatsCount;
    private int premiumSeatsCount;
}
