package com.codecool.enterprise.avatar.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

@RestController
public class AvatarController {

    @GetMapping(value = "/getavatar")
    public String funFact() throws IOException {
        Random rand = new Random();
        int random = rand.nextInt(99)+1;
        return String.format("https://robohash.org/%d", random);
    }

}