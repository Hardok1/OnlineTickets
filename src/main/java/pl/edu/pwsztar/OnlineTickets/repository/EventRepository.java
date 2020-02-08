package pl.edu.pwsztar.OnlineTickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwsztar.OnlineTickets.model.Event;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByActive(@NotNull boolean active);
}
