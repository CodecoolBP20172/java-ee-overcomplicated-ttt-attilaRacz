package com.codecool.enterprise.comic.controller;

import com.codecool.enterprise.comic.service.JsonReader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

@RestController
public class ComicController {

    @Autowired
    private JsonReader jsonReader;

    @GetMapping(value = "/getcomic")
    public String funFact() throws IOException {
        Random rand = new Random();
        int random = rand.nextInt(1928)+1;
        JSONObject comicJson = jsonReader.readJsonFromUrl(String.format("https://xkcd.com/%d/info.0.json", random));
        return comicJson.toMap().get("img").toString();
    }

}