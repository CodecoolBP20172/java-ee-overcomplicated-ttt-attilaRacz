package com.codecool.enterprise.overcomplicated.service;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TictactoeGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class TicTacToeService {

    @Autowired
    private TictactoeGame tictactoeGame;

    public void setPlayerAvatar(Player player, String avatarURL) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(avatarURL, String.class);
            player.setAvatar(response.getBody());
        } catch (RestClientException e) {
            System.out.println("Avatar service is not available");
            player.setAvatar("");
        }
    }

    public String getFunFact(String funfactURL){
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(funfactURL, String.class).getBody();
        } catch (RestClientException e) {
            //System.out.println("Fun fact service is not available");
            return "Fun fact service is not available";
        }
    }

    public Object getComic(String comicURL) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(comicURL, String.class).getBody();
        } catch (RestClientException e) {
            //System.out.println("Comic service is not available");
            return "Comic service is not available";
        }
    }

    public void processMove(int move, boolean ai) {
        tictactoeGame.saveMove(move, ai);
    }

    public int getAiMove(String aiURL){
        RestTemplate restTemplate = new RestTemplate();
        try {
            return Character.getNumericValue(restTemplate.getForEntity(aiURL, String.class).getBody().charAt(50));
            //return restTemplate.getForEntity(aiURL, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println("Ai service is not available");
            throw new IllegalArgumentException("Couldn't find the next move of the AI.");
        }
    }

    public TictactoeGame getGame() {
        return tictactoeGame;
    }

}
