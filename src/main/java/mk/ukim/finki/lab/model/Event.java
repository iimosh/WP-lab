package mk.ukim.finki.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    double popularityScore;
    @ManyToOne
    private Location location;

    public Event(String name, String description, double popularityScore,Location location) {
        //this.id=(long)(Math.random()*1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }
}
