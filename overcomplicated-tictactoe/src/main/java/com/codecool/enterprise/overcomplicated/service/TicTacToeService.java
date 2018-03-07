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

    private Random rand = new Random();

    //avatar in progress
    /*public void setPlayerAvatar(Player player, String avatarUrl, boolean overWrite) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(avatarUrl, String.class);
            if (overWrite) {
                player.changeAvatar(response.getBody());
            } else {
                player.setEmptyAvatarUrl(response.getBody());
            }
        } catch (RestClientException e) {
            System.out.println("Avatar service unreachable");
        }
    }*/

    /*public Map<String, String> getComic(String comicUrl){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new URL(comicUrl), HashMap.class);
        } catch (IOException e) {
            System.out.println("comic service unreachable");
            return new HashMap<>();
        }
    }*/

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
