package pl.edu.pwsztar.OnlineTickets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwsztar.OnlineTickets.dto.SignUpDTO;
import pl.edu.pwsztar.OnlineTickets.dto.TicketDTO;
import pl.edu.pwsztar.OnlineTickets.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account/")
public class AccountController {

    private PasswordEncoder passwordEncoder;

    final AccountService accountService;

    @Autowired
    public AccountController(PasswordEncoder passwordEncoder, AccountService accountService) {
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO){
        signUpDTO.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        if (accountService.signUp(signUpDTO)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("myTickets")
    public ResponseEntity<List<TicketDTO>> getAccountTickets(Authentication authentication){
        List<TicketDTO> ticketDTOList = accountService.getAccountTickets(authentication.getName());
        if (ticketDTOList.size() > 0) {
            return new ResponseEntity<>(ticketDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
