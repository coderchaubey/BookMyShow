package Book_My_Show.BMS.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int price;
    private String movieName;
    private LocalTime showTiming;
    private LocalDate showDate;
    private int totalAmount;
    private String ticketId= UUID.randomUUID().toString();
    private String theatreName;
    private String bookedSeats;


    //Mapping
    //This is child with respect to user
    @ManyToOne
    @JoinColumn
    private User user;

    //This is child wrt to Show entity
    @ManyToOne
    @JoinColumn
    private Show show;
}
