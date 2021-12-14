// Created by antho
// Creation date 13-12-2021

package nl.hva.c25.team1.digivault.authentication;

public class HashService {

    private final PepperService pepperService;

    public HashService(PepperService pepperService) {
        this.pepperService = pepperService;
    }

    public String hash(String value) {
        final int NUMBER_OF_ROUNDS = 10;
        // TODO: met peper
        return "";
    }

}