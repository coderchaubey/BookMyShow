package Book_My_Show.BMS.Controllers;

import Book_My_Show.BMS.EntryDtos.UserEntryDto;
import Book_My_Show.BMS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){

       try{
           String response=userService.addUser(userEntryDto);
           return new ResponseEntity<>(response, HttpStatus.CREATED);
       }catch (Exception e){

           String result="User could not be added";
           return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
       }
    }
    //My Code
    @GetMapping("/getUserName")
    public ResponseEntity getUserName(@RequestParam int id){
        try {
            return new ResponseEntity<>(userService.getUserName(id),HttpStatus.FOUND);
        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
