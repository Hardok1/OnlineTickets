package pl.edu.pwsztar.OnlineTickets.service;

import pl.edu.pwsztar.OnlineTickets.dto.EventDTO;

import java.util.List;

public interface EventService {

    List<EventDTO> getAll();

    EventDTO getEvent(Long id);

    boolean addEvent(String login, EventDTO eventDTO);

    boolean editEvent(String login, EventDTO eventDTO);

    boolean buyTicket(String login, Long eventId);

    boolean getHowManyTicketsLeft(Long eventId);

    boolean deleteEvent(String login, Long eventId);
}
