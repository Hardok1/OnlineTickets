package pl.edu.pwsztar.OnlineTickets.service;

import pl.edu.pwsztar.OnlineTickets.dto.EventDTO;
import pl.edu.pwsztar.OnlineTickets.model.Event;

import java.util.List;

public interface EventService {

    List<EventDTO> getAllActiveEvents();

    EventDTO getEvent(Long id);

    boolean addEvent(String login, EventDTO eventDTO);

    boolean editEvent(String login, EventDTO eventDTO);

    boolean buyTicket(String login, Long eventId);

    int getHowManyTicketsLeft(Event event);

    boolean deleteEvent(String login, Long eventId);
}
