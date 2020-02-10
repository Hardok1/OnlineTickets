package pl.edu.pwsztar.OnlineTickets.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.OnlineTickets.dto.EventDTO;
import pl.edu.pwsztar.OnlineTickets.model.Account;
import pl.edu.pwsztar.OnlineTickets.model.Event;
import pl.edu.pwsztar.OnlineTickets.repository.AccountRepository;
import pl.edu.pwsztar.OnlineTickets.repository.EventRepository;
import pl.edu.pwsztar.OnlineTickets.service.AccountService;
import pl.edu.pwsztar.OnlineTickets.service.EventService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    final EventRepository eventRepository;
    final AccountRepository accountRepository;
    final AccountService accountService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, AccountRepository accountRepository, AccountService accountService) {
        this.eventRepository = eventRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @Override
    public List<EventDTO> getAllActiveEvents() {
        List<EventDTO> eventDTOList = new ArrayList<>();
        List<Event> events = eventRepository.findAllByActive(true);
        for (Event event : events) {
            eventDTOList.add(new EventDTO(event.getId(), event.getName(), event.getDescription(), event.getImageUrl(),
                    dateFormatter(event.getDate().getTime()), event.getCity(), event.isActive(), event.getTicketPrice(),
                    event.getTicketLimit(), getHowManyTicketsLeft(event)));
        }
        return eventDTOList;
    }


    @Override
    public EventDTO getEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(value -> new EventDTO(value.getId(), value.getName(), value.getDescription(),
                value.getImageUrl(), dateFormatter(value.getDate().getTime()), value.getCity(),
                value.isActive(), value.getTicketPrice(), value.getTicketLimit(), getHowManyTicketsLeft(value))).orElse(null);
    }

    @Override
    public boolean addEvent(String login, EventDTO eventDTO) {
        if (accountService.isAdmin(login) && !eventRepository.existsByNameAndDate(eventDTO.getName(), dateFormatter(eventDTO.getDate()))) {
            Event event = new Event(eventDTO.getName(), eventDTO.getDescription(), eventDTO.getImageUrl(), dateFormatter(eventDTO.getDate()),
                    eventDTO.getCity(), eventDTO.isActive(), eventDTO.getTicketPrice(), eventDTO.getTicketLimit());
            eventRepository.save(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean editEvent(String login, EventDTO eventDTO) {
        Optional<Event> event = eventRepository.findById(eventDTO.getId());
        if (event.isPresent() && accountService.isAdmin(login)) {
            if (!Objects.equals(eventDTO.getName(), event.get().getName()) || !Objects.equals(eventDTO.getDate(), dateFormatter(event.get().getDate().getTime()))) {
                if (!eventRepository.existsByNameAndDate(eventDTO.getName(), dateFormatter(eventDTO.getDate()))) {
                    event.get().setName(eventDTO.getName());
                    event.get().setDate(dateFormatter(eventDTO.getDate()));
                }
            }
            event.get().setActive(eventDTO.isActive());
            event.get().setCity(eventDTO.getCity());
            event.get().setDescription(eventDTO.getDescription());
            event.get().setImageUrl(eventDTO.getImageUrl());
            event.get().setTicketPrice(eventDTO.getTicketPrice());
            if (event.get().getBoughtTickets().size() <= eventDTO.getTicketLimit()) {
                event.get().setTicketLimit(eventDTO.getTicketLimit());
            }
            eventRepository.save(event.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean buyTicket(String login, Long eventId) {
        Account account = accountRepository.findByLogin(login);
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()){
            if (getHowManyTicketsLeft(event.get()) <= 0){
                return false;
            }
            for (Event ev : account.getOwnedTickets()){
                if (ev.getId().equals(eventId)){
                    return false;
                }
            }
            account.addTicket(event.get());
            accountRepository.save(account);
            eventRepository.save(event.get());
            return true;
        }
        return false;
    }

    @Override
    public int getHowManyTicketsLeft(Event event) {

        return event.getTicketLimit() - event.getBoughtTickets().size();
    }

    @Override
    public boolean deleteEvent(String login, Long eventId) {
        if (accountService.isAdmin(login)) {
            eventRepository.deleteById(eventId);
            return true;
        }
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