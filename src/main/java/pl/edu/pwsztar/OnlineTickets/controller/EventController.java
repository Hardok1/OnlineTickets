package pl.edu.pwsztar.OnlineTickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("getAllActive")
    public ResponseEntity<List<EventDTO>> getAllActiveEvents() {
        List<EventDTO> eventDTOList = eventService.getAllActiveEvents();
        if (eventDTOList.size() > 0) {
            return new ResponseEntity<>(eventDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable("eventId") Long eventId) {
        if (eventService.getEvent(eventId) == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(eventService.getEvent(eventId), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<?> addEvent(Authentication authentication, @RequestBody EventDTO eventDTO) {
        if (eventService.addEvent(authentication.getName(), eventDTO)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("edit")
    public ResponseEntity<?> editEvent(Authentication authentication, @RequestBody EventDTO eventDTO){
        if (eventService.editEvent(authentication.getName(), eventDTO)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("delete/{eventId}")
    public ResponseEntity<?> deleteEvent(Authentication authentication, @PathVariable("eventId") Long eventId){
        if (eventService.deleteEvent(authentication.getName(),eventId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("buyTicket/{eventId}")
    public ResponseEntity<?> buyTicket(Authentication authentication, @PathVariable("eventId") Long eventId){
        if (eventService.buyTicket(authentication.getName(), eventId)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
