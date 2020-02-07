package pl.edu.pwsztar.OnlineTickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.OnlineTickets.dto.EventDTO;
import pl.edu.pwsztar.OnlineTickets.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/event/")
public class EventController {

    final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<EventDTO>> getAll() {
        return null;
    }

    @GetMapping("{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable("eventId") Long eventId) {
        return null;
    }

    @PostMapping("add")
    public ResponseEntity<?> addEvent(Authentication authentication, EventDTO eventDTO) {
        return null;
    }

    @PutMapping("edit")
    public ResponseEntity<?> editEvent(Authentication authentication, EventDTO eventDTO){

        return null;
    }

    @DeleteMapping("delete/{eventId}")
    public ResponseEntity<?> deleteEvent(Authentication authentication, @PathVariable("eventId") Long eventId){
        return null;
    }

    @PostMapping("buyTicket/{eventId}")
    public ResponseEntity<?> buyTicket(Authentication authentication, @PathVariable("eventId") Long eventId){
        return null;
    }

    @GetMapping("howManyTicketsLeft/{eventId}")
    public ResponseEntity<?> getHowManyTicketsLeft(@PathVariable("eventId") Long eventId){
        return null;
    }
}
