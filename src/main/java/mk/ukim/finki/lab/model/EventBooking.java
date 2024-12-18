package mk.ukim.finki.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@Entity
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    User user;
    @ManyToOne
    Event event;

//    String attendeeName;
//    String attendeeAddress;
    Long numberOfTickets;

    public EventBooking(Event event, User user, Long numberOfTickets) {
        this.event = event;
        this.user = user;
        this.numberOfTickets = numberOfTickets;
    }
}
