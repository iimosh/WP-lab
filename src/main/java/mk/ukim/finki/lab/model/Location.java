package mk.ukim.finki.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "location")
    private List<Event> events;

    public Location(String name, String address, String capacity, String description) {
//        this.id=(long)(Math.random()*1000);
        this.name = name;
        this.events=new ArrayList<>();
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }
}
