package pl.edu.pwsztar.OnlineTickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO {
    private String eventName;
    private String eventId;
}
