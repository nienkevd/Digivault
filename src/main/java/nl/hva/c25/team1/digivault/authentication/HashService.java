// Created by antho
// Creation date 13-12-2021

package nl.hva.c25.team1.digivault.authentication;

public class HashService {

    private final PepperService pepperService;

    public HashService(PepperService pepperService) {
        this.pepperService = pepperService;
    }

    public String hash(String value) {
        // maak instantie bcrypt klasse (no args)
        // TODO: singleton voor bcrypt klasse
        // hash mbv bcrypt en use pepper
        return "";
    }

}