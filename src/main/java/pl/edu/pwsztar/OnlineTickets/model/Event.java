package pl.edu.pwsztar.OnlineTickets.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @GeneratedValue
    @Id
    Long id;

    @NotBlank
    @Column
    String name;

    @NotNull
    @Column
    String description;

    @NotNull
    @Column
    String imageUrl;

    @NotNull
    @Column
    @Temporal(TemporalType.DATE)
    Calendar date;

    @NotNull
    @Column
    String city;

    @NotNull
    @Column
    boolean active;

    @NotNull
    @Column
    Double ticketPrice;

    @NotNull
    @Column
    int ticketLimit;

    @ManyToMany(mappedBy = "ownedTickets")
    Set<Account> accounts;

    public Event(String name, String description, String imageUrl, Calendar date, String city, boolean active, Double ticketPrice, int ticketLimit) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
        this.city = city;
        this.active = active;
        this.ticketPrice = ticketPrice;
        this.ticketLimit = ticketLimit;
    }
}
