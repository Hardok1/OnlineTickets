package pl.edu.pwsztar.OnlineTickets.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.OnlineTickets.dto.EventDTO;
import pl.edu.pwsztar.OnlineTickets.model.Account;
import pl.edu.pwsztar.OnlineTickets.model.Event;
import pl.edu.pwsztar.OnlineTickets.repository.AccountRepository;
import pl.edu.pwsztar.OnlineTickets.repository.EventRepository;
import pl.edu.pwsztar.OnlineTickets.service.EventService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    final EventRepository eventRepository;
    final AccountRepository accountRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, AccountRepository accountRepository) {
        this.eventRepository = eventRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<EventDTO> getAllActiveEvents() {
        List<EventDTO> eventDTOList = new ArrayList<>();
        List<Event> events = eventRepository.findAllByActive(true);
        for (Event event : events) {
            eventDTOList.add(new EventDTO(event.getId(), event.getName(), event.getDescription(), event.getImageUrl(),
                    dateFormatter(event.getDate().getTime()), event.getCity(), event.isActive(), event.getTicketPrice(), event.getTicketLimit()));

        }
        return eventDTOList;
    }


    @Override
    public EventDTO getEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(value -> new EventDTO(value.getId(), value.getName(), value.getDescription(),
                value.getImageUrl(), dateFormatter(value.getDate().getTime()), value.getCity(),
                value.isActive(), value.getTicketPrice(), value.getTicketLimit())).orElse(null);
    }

    @Override
    public boolean addEvent(String login, EventDTO eventDTO) {
        if (accountRepository.findByLogin(login).isAdmin()) {
            Event event = new Event(eventDTO.getName(), eventDTO.getDescription(), eventDTO.getImageUrl(), dateFormatter(eventDTO.getDate()),
                    eventDTO.getCity(), eventDTO.isActive(), eventDTO.getTicketPrice(), eventDTO.getTicketLimit());
            eventRepository.save(event);
            return true;
        }

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

    private String dateFormatter(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    private Calendar dateFormatter(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}