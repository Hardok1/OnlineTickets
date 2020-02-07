package pl.edu.pwsztar.OnlineTickets.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.OnlineTickets.model.Account;
import pl.edu.pwsztar.OnlineTickets.repository.AccountRepository;

import static java.util.Collections.emptyList;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountRepository accountRepository;


    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(login);
        if (account == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(account.getLogin(), account.getPassword(), emptyList());
    }
}
