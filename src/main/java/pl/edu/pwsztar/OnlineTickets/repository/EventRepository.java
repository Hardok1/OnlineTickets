package pl.edu.pwsztar.OnlineTickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwsztar.OnlineTickets.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
