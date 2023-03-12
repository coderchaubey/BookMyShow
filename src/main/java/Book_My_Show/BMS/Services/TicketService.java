package Book_My_Show.BMS.Services;

import Book_My_Show.BMS.EntryDtos.TicketEntryDto;
import Book_My_Show.BMS.Model.Show;
import Book_My_Show.BMS.Model.ShowSeat;
import Book_My_Show.BMS.Model.Ticket;
import Book_My_Show.BMS.Model.User;
import Book_My_Show.BMS.Repositories.ShowRepository;
import Book_My_Show.BMS.Repositories.TicketRepository;
import Book_My_Show.BMS.Repositories.UserRepository;
import Book_My_Show.BMS.convertors.TicketConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto)throws Exception{
        //1. Create Ticket entity from the entryDto---->conversion

        Ticket ticket= TicketConvertors.convertEntryToEntity(ticketEntryDto);

        //Validation: Check if the requested seats are available or not?
        boolean isValidRequest=checkValidityOfRequestedSeats(ticketEntryDto);
        if (isValidRequest==false){
            throw new Exception("Requested Seats are not available");
        }

        //We assume that the requestedSeats are valid
        //Calculate the total amount
        Show show=showRepository.findById(ticketEntryDto.getShowId()).get();

        List<ShowSeat> seatList=show.getShowSeatList();

        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();

        int totalAmount=0;
        for (ShowSeat showSeat:seatList){

            if (requestedSeats.contains(showSeat.getSeatNo())){
                totalAmount=totalAmount+ showSeat.getPrice();
                showSeat.setBooked(true);
                showSeat.setBookedAt(new Date());
            }
        }

        ticket.setTotalAmount(totalAmount);

        //Setting the other required attributes for the Ticket Entity
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTiming(show.getShowTime());
        ticket.setTheatreName(show.getTheatre().getName());

        //We need to set that string that talked about requested Seats
        String allocatedSeats=getAllocatedSeats(requestedSeats);
        ticket.setBookedSeats(allocatedSeats);

        //Setting the foreign Key attributes
        User user=userRepository.findById(ticketEntryDto.getUserId()).get();

        ticket.setUser(user);
        ticket.setShow(show);

        //Save the parent
        ticket=ticketRepository.save(ticket);// Getting the updated object of ticket


        List<Ticket> ticketList=show.getListOfBookedTickets();
        ticketList.add(ticket);
        show.setListOfBookedTickets(ticketList);

        showRepository.save(show);

        List<Ticket> ticketList1=user.getBookedTickets();
        ticketList1.add(ticket);
        user.setBookedTickets(ticketList1);

        userRepository.save(user);

        return "Ticket has been successfully added";

    }

    private String getAllocatedSeats(List<String> requestedSeats ){
        String result="";

        for (String seat:requestedSeats){
            result =result+seat+", ";
        }
        return result;
    }
    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){
        int showId= ticketEntryDto.getShowId();

        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();

        Show show=showRepository.findById(showId).get();

        List<ShowSeat> listOfSeats=show.getShowSeatList();

        //Iterating over the list of seats for the particular show
        for (ShowSeat showSeat:listOfSeats){
            String seatNo= showSeat.getSeatNo();

            if (requestedSeats.contains(seatNo)){
                if (showSeat.isBooked())
                    return false; //Since this seat can't be occupied : returning false
            }
        }
        //All the seats requested were available
        return true;
    }
}
