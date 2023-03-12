package Book_My_Show.BMS.Model;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private int age;

    @NonNull
    @Column(unique = true)
    private String mobNo;

    private String address;

    //Mapping with child ticket
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Ticket> bookedTickets=new ArrayList<>();
}
