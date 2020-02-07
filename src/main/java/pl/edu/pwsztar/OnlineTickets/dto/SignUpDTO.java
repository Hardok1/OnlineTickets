package pl.edu.pwsztar.OnlineTickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignUpDTO {
    private String login;
    private String password;
    private String email;
}
