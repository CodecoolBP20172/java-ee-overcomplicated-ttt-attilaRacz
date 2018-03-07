package com.codecool.enterprise.funfact.controller;

import com.codecool.enterprise.funfact.service.JsonReader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class FunFactController {

    @Autowired
    private JsonReader jsonReader;

    @GetMapping(value = "/getfunfact")
    public String funFact() throws IOException {
        JSONObject funFactJson = jsonReader.readJsonFromUrl("https://api.chucknorris.io/jokes/random");
        return funFactJson.toMap().get("value").toString();
    }

}