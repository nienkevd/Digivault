// Created by antho
// Creation date 13-12-2021

package nl.hva.c25.team1.digivault.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    private final PepperService pepperService;

    @Autowired
    public HashService(PepperService pepperService) {
        this.pepperService = pepperService;
    }

    public String hash(String value) {
        return new BCryptPasswordEncoder().encode(value + pepperService.getPepper());
    }

}