package Book_My_Show.BMS.Services;

import Book_My_Show.BMS.EntryDtos.ShowEntryDto;
import Book_My_Show.BMS.Enums.SeatType;
import Book_My_Show.BMS.Model.*;
import Book_My_Show.BMS.Repositories.MovieRepository;
import Book_My_Show.BMS.Repositories.ShowRepository;
import Book_My_Show.BMS.Repositories.TheatreRepository;
import Book_My_Show.BMS.convertors.ShowConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto){

        //1. Creating a show entity
        Show show= ShowConvertor.convertEntryToEntity(showEntryDto);

        int movieId= showEntryDto.getMovieId();
        int theatreId=showEntryDto.getTheatreId();

        //Here we have taken the entities of which movie will be shown at which theatre
        Movie movie=movieRepository.findById(movieId).get();
        Theatre theatre=theatreRepository.findById(theatreId).get();

        //Setting the foreign key attributes
        show.setTheatre(theatre);
        show.setMovie(movie);

        //Pending attributes that is listOfShowSeatsEntity
        List<ShowSeat> seatEntityList=createShowSeatEntity(showEntryDto,show);
        show.setShowSeatList(seatEntityList);



        //Now we also need to update the parent Entity
//        List<Show> showList=movie.getShowList();
//        showList.add(show);
//        movie.setShowList(showList);
//
//        movieRepository.save(movie);
//
//        List<Show> showList1=theatre.getShowList();
//        showList1.add(show);
//        theatre.setShowList(showList1);
//
//        theatreRepository.save(theatre);

        //Now we also need to update the parent Entity
        show=showRepository.save(show);  //Here we are saving the show object and getting the updated one to perform any changes to the updated object

        movie.getShowList().add(show);
        theatre.getShowList().add(show);


        movieRepository.save(movie);
        theatreRepository.save(theatre);

        return "The show has been added successfully";
    }



    private List<ShowSeat> createShowSeatEntity(ShowEntryDto showEntryDto,Show show){


        //Now the goal is to create the showSeat entity
        //We need to set its attribute
        List<TheatreSeats> theatreSeatsList=show.getTheatre().getTheatreSeatsList();

        List<ShowSeat> seatList=new ArrayList<>();

        for (TheatreSeats theatreSeats:theatreSeatsList){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theatreSeats.getSeatNo());
            showSeat.setSeatType(theatreSeats.getSeatType());

            if(theatreSeats.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showEntryDto.getClassicSeatPrice());
            else showSeat.setPrice(showEntryDto.getPremiumSeatPrice());

            showSeat.setBooked(false);
            showSeat.setShow(show); //parent : foreign key for the showSeat Entity

            seatList.add(showSeat); //Adding it to the List
        }
        return seatList;
    }

    //My code
    public LocalTime getShowTime(int movieId, int theatreId)throws Exception{
        Movie movie=movieRepository.findById(movieId).get();
        Theatre theatre=theatreRepository.findById(theatreId).get();

        List<Show> showList=movie.getShowList();
        for(Show show:showList){
            if(show.getTheatre().getId()==theatreId) return show.getShowTime();
        }
        throw new Exception("Sorry the component is not found");
    }

    public String getMovieMaximunShows(){
        List<Show> showList=showRepository.findAll();
        Map<Integer,Integer> freq=new HashMap<>();
        int maxNo=0;
        int maxFreq=0;
        for (Show show:showList){

                freq.put(show.getMovie().getId(),freq.getOrDefault(show.getMovie().getId(),0)+1);
                maxFreq=Math.max(freq.get(show.getMovie().getId()),maxFreq);

        }
        for (Integer i: freq.keySet()){
            if (maxFreq==freq.get(i)) {
                maxNo=i;
                break;
            }
        }
        Movie movie=movieRepository.findById(maxNo).get();
        return movie.getMovieName();
    }
}
