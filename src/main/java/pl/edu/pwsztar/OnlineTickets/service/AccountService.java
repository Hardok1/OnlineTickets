package pl.edu.pwsztar.OnlineTickets.service;

import pl.edu.pwsztar.OnlineTickets.dto.SignUpDTO;
import pl.edu.pwsztar.OnlineTickets.dto.TicketDTO;

import java.util.List;

public interface AccountService {

    boolean signUp(SignUpDTO signUpDTO);

    void createAdmin(String password);

    List<TicketDTO> getAccountTickets(String login);

    boolean isAdmin(String login);
}
