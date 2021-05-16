package cz.czechitas.java2webapps.ukol2.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomNumberService {

    public int getRandomNumber(int upperbound) {
        Random rand = new Random();
        return rand.nextInt(upperbound);
    }
}
