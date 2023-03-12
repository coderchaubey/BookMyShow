package Book_My_Show.BMS.Model;

import Book_My_Show.BMS.Enums.SeatType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theatre_seats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

//    Mapping
    @ManyToOne
    @JoinColumn                  /**@JoinColumn(ReferencedColumnName = "The attribute that we want to become foreign key other than the Id of our parent class ")**/ //Given that the attribute should not be null and it should be unique
    private Theatre theatre;
}
