// Created by antho
// Creation date 16-12-2021

package nl.hva.c25.team1.digivault.authentication;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        super();
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginHandler(@RequestBody Account account) {
        return ResponseEntity.ok(loginService.login(account.getEmailadres(), account.getWachtwoord()));
    }

}