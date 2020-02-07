package pl.edu.pwsztar.OnlineTickets.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
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
}
