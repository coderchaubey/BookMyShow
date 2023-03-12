package Book_My_Show.BMS.convertors;

import Book_My_Show.BMS.EntryDtos.TicketEntryDto;
import Book_My_Show.BMS.Model.Ticket;

public class TicketConvertors {

    public static Ticket convertEntryToEntity(TicketEntryDto ticketEntryDto){
        Ticket ticket=new Ticket();
        return ticket;
    }
}
