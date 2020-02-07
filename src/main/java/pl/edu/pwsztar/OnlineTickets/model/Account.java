package pl.edu.pwsztar.OnlineTickets.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwsztar.OnlineTickets.dto.SignUpDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @GeneratedValue
    @Id
    Long id;

    @NotBlank
    @Column
    String login;

    @NotBlank
    @Column
    String email;

    @NotBlank
    @Column
    String password;

    @NotNull
    @Column
    boolean admin;

    @ManyToMany
    @JoinTable(
            name = "ticket",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    Set<Event> ownedTickets;

    public Account(SignUpDTO signUpDTO) {
        this.login = signUpDTO.getLogin();
        this.email = signUpDTO.getEmail();
        this.password = signUpDTO.getPassword();
        this.admin = false;
    }

}
