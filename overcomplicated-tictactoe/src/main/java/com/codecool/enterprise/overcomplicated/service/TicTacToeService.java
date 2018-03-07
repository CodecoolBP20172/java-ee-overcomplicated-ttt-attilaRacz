package com.codecool.enterprise.overcomplicated.service;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TicTacToeService {

    public void setPlayerAvatar(Player player, String avatarURL) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(avatarURL, String.class);
            player.setAvatar(response.getBody());
        } catch (RestClientException e) {
            System.out.println("Avatar service is not available");
        }
    }

    public String getFunFact(String funfactURL){
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(funfactURL, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println("Fun fact service is not available");
            return "Fun fact service is not available";
        }
    }

    public Object getComic(String comicURL) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(comicURL, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println("Comic service is not available");
            return "Comic service is not available";
        }
    }
}
