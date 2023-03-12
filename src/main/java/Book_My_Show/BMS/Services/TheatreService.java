package Book_My_Show.BMS.Services;

import Book_My_Show.BMS.EntryDtos.TheatreEntryDto;
import Book_My_Show.BMS.Enums.SeatType;
import Book_My_Show.BMS.Model.Show;
import Book_My_Show.BMS.Model.Theatre;
import Book_My_Show.BMS.Model.TheatreSeats;
import Book_My_Show.BMS.Repositories.TheatreRepository;
import Book_My_Show.BMS.Repositories.TheatreSeatsRepository;
import Book_My_Show.BMS.convertors.TheatreConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    TheatreSeatsRepository theatreSeatsRepository;

    public String addTheatre(TheatreEntryDto theatreEntryDto)throws Exception{
        /*
        1.create theatreSeats
        2.I need to save the theater : I need theatreEntity
        3. Always set the attribute before saving.
         */

        //Can do some validations
        if(theatreEntryDto.getName()==null || theatreEntryDto.getLocation()==null){
            throw new Exception("Name and location should be valid");
        }

        Theatre theatre= TheatreConvertor.convertDtoToTheatreEntity(theatreEntryDto);


        List<TheatreSeats> theatreSeatsList=createTheatreSeatsList(theatreEntryDto,theatre);

        theatre.setTheatreSeatsList(theatreSeatsList);


        theatreRepository.save(theatre);

        return "Theatre added successfully";
    }

    private List<TheatreSeats> createTheatreSeatsList(TheatreEntryDto theatreEntryDto,Theatre theatre){

        int noOfClassicSeats= theatreEntryDto.getClassicSeatsCount();
        int noOfPremiumSeats= theatreEntryDto.getPremiumSeatsCount();

        List<TheatreSeats> theatreSeatsList=new ArrayList<>();

        //Created the classic seats
        for (int count=1;count<=noOfClassicSeats;count++){

            //We need to make a new TheaterSeatEntity without using the NEW keyword
            //Here we are creating it for Classic seat
            TheatreSeats theatreSeats=TheatreSeats.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C").theatre(theatre).build();

            theatreSeatsList.add(theatreSeats);
        }

        //Create the premium seats
        for (int count=1;count<=noOfPremiumSeats;count++){

            //We need to make a new TheaterSeatEntity without using the NEW keyword
            //Here we are creating it for Premium seat
            TheatreSeats theatreSeats=TheatreSeats.builder().
                    seatType(SeatType.PREMIUM).seatNo(count+"P").theatre(theatre).build();

            theatreSeatsList.add(theatreSeats);
        }

//        theatreSeatsRepository.saveAll(theatreSeatsList); //To save the list we use saveAll

        return theatreSeatsList;
    }

    //My code

    public List<String> getTheaterList(){
        List<String> theatreName=new ArrayList<>();
        List<Theatre> theatreList=theatreRepository.findAll();
        for (Theatre theatre:theatreList){
            theatreName.add(theatre.getName());
        }
        return theatreName;
    }

    public List<String> getAllMoviesInATheatre(){

        List<String> movieNameInATheatreList=new ArrayList<>();
          List<Theatre> theatreList=theatreRepository.findAll();
     for(Theatre theatre:theatreList){
         List<Show> showList=theatre.getShowList();
         for(Show show:showList){
             movieNameInATheatreList.add(show.getMovie().getMovieName());
         }
     }
     return movieNameInATheatreList;
    }
}
