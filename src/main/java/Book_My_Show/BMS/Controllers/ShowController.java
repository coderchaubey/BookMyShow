package Book_My_Show.BMS.Controllers;

import Book_My_Show.BMS.EntryDtos.ShowEntryDto;
import Book_My_Show.BMS.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity addShow(@RequestBody ShowEntryDto showEntryDto){

        try {

            String response=showService.addShow(showEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){

            String response=showService.addShow(showEntryDto);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getShowTime")
    public ResponseEntity getShowTime(@RequestParam int movieId,@RequestParam int theatreId){
        try{
            return new ResponseEntity(showService.getShowTime(movieId,theatreId),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getMovieMaximunShows")
    public ResponseEntity getMovieMaximunShows(){
        try {
            return new ResponseEntity<>(showService.getMovieMaximunShows(),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(showService.getMovieMaximunShows(),HttpStatus.BAD_REQUEST);
        }
    }
}
