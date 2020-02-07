package pl.edu.pwsztar.OnlineTickets.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.OnlineTickets.dto.SignUpDTO;
import pl.edu.pwsztar.OnlineTickets.dto.TicketDTO;
import pl.edu.pwsztar.OnlineTickets.model.Account;
import pl.edu.pwsztar.OnlineTickets.repository.AccountRepository;
import pl.edu.pwsztar.OnlineTickets.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean signUp(SignUpDTO signUpDTO) {
        if (accountRepository.existsByEmail(signUpDTO.getEmail()) || accountRepository.existsByLogin(signUpDTO.getLogin())){
            return false;
        }
        accountRepository.save(new Account(signUpDTO));
        return true;
    }

    @Override
    public List<TicketDTO> getAccountTickets() {
        return null;
    }
}
