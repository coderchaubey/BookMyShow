package Book_My_Show.BMS.Controllers;

import Book_My_Show.BMS.EntryDtos.TicketEntryDto;
import Book_My_Show.BMS.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public  String bookTicket(@RequestBody TicketEntryDto ticketEntryDto){

        try {
            String response=ticketService.addTicket(ticketEntryDto);
            return response;

        }catch (Exception e){

            return e.getMessage();
        }
    }
}
