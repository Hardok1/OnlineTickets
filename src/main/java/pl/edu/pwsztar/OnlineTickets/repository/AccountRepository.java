package pl.edu.pwsztar.OnlineTickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwsztar.OnlineTickets.model.Account;

import javax.validation.constraints.NotBlank;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByLogin(@NotBlank String login);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);


}
