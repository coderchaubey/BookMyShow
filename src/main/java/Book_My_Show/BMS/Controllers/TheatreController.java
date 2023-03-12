package Book_My_Show.BMS.Controllers;

import Book_My_Show.BMS.EntryDtos.TheatreEntryDto;
import Book_My_Show.BMS.Model.Theatre;
import Book_My_Show.BMS.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity addTheatre(@RequestBody TheatreEntryDto theatreEntryDto) {

        try {
            String response = theatreService.addTheatre(theatreEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //My code
    @GetMapping("/getTheaterList")
    public List<String> getTheaterList() {

        return theatreService.getTheaterList();

    }

    @GetMapping("/getAllMoviesInATheatre")
    public List<String> getAllMoviesInATheatre() {

        return theatreService.getAllMoviesInATheatre();

    }
}