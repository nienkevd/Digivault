// Created by antho
// Creation date 13-12-2021

package nl.hva.c25.team1.digivault.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashService {

    private final PepperService pepperService;

    public HashService(PepperService pepperService) {
        this.pepperService = pepperService;
    }

    public String hash(String value) {
        return new BCryptPasswordEncoder().encode(value + pepperService.getPepper());
    }

}