// Created by antho
// Creation date 15-12-2021

package nl.hva.c25.team1.digivault.authentication;

import nl.hva.c25.team1.digivault.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class LoginService {

    private HashService hashService;
    private TokenService tokenService;
    private AccountDAO accountDAO;

    @Autowired
    public LoginService(HashService hashService, TokenKlantPaarDAO tokenKlantPaarDAO, AccountDAO accountDAO) {
        this.hashService = hashService;
        this.tokenService = tokenService;
        this.accountDAO = accountDAO;
    }

    public String login(String emailAdres, String password) {
        String token = null;
        String storedHash = accountDAO.vindAccountOpEmailAdres(emailAdres).getWachtwoord();
        if(hashService.matches(password, storedHash)) token = tokenService.maakJWT(emailAdres);
        return token;
    }

}