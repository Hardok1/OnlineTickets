package pl.edu.pwsztar.OnlineTickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EventDTO {
    Long id;
    String name;
    String description;
    String imageUrl;
    String date;
    String city;
    boolean active;
    Double ticketPrice;
    int ticketLimit;
}
