package pl.edu.pwsztar.OnlineTickets.service.impl;

import org.springframework.stereotype.Service;
import pl.edu.pwsztar.OnlineTickets.dto.EventDTO;
import pl.edu.pwsztar.OnlineTickets.service.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public List<EventDTO> getAll() {
        return null;
    }

    @Override
    public EventDTO getEvent(Long id) {
        return null;
    }

    @Override
    public boolean addEvent(String login, EventDTO eventDTO) {
        return false;
    }

    @Override
    public boolean editEvent(String login, EventDTO eventDTO) {
        return false;
    }

    @Override
    public boolean buyTicket(String login, Long eventId) {
        return false;
    }

    @Override
    public boolean getHowManyTicketsLeft(Long eventId) {
        return false;
    }

    @Override
    public boolean deleteEvent(String login, Long eventId) {
        return false;
    }
}
