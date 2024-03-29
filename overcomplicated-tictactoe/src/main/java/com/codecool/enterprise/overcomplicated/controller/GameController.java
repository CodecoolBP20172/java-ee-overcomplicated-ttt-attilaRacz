package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TictactoeGame;
import com.codecool.enterprise.overcomplicated.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@SessionAttributes({"player", "game", "avatar_uri"})
public class GameController {

    @Autowired
    private TicTacToeService ticTacToeService;

    private static final String AVATARURL = "http://localhost:60001/getavatar";

    private static final String COMICURL = "http://localhost:60002/getcomic";

    private static final String FUNFACTURL = "http://localhost:60003/getfunfact";

    @ModelAttribute("player")
    public Player getPlayer() {
        return new Player();
    }

    @ModelAttribute("game")
    public TictactoeGame getGame() {
        return ticTacToeService.getGame();
    }

    @ModelAttribute("avatar_uri")
    public String getAvatarUri(@ModelAttribute Player player) {
        ticTacToeService.setPlayerAvatar(player, AVATARURL);
        return player.getAvatar();
    }

    @GetMapping(value = "/")
    public String welcomeView(@ModelAttribute Player player) {
        return "welcome";
    }

    @PostMapping(value="/changeplayerusername")
    public String changPlayerUserName(@ModelAttribute Player player) {
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player, Model model) {
        model.addAttribute("funfact", ticTacToeService.getFunFact(FUNFACTURL));
        model.addAttribute("comic_uri", ticTacToeService.getComic(COMICURL));
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move) {
        ticTacToeService.processMove(move, false);
        String gameState = ticTacToeService.getGame().gameStateToString();
        char playerSymbol = ticTacToeService.getGame().getSymbol();
        String aiURL = String.format("http://tttapi.herokuapp.com/api/v1/%s/%s", gameState, playerSymbol);
        if (ticTacToeService.getGame().gameStateToString().contains("-")) {
            ticTacToeService.processMove(ticTacToeService.getAiMove(aiURL), true);
        }
        ticTacToeService.winCheck();
        return "redirect:/game";
    }

    @GetMapping(value = "/game-new")
    public String emptyGameStatus(@ModelAttribute("player") Player player, Model model) {
        ticTacToeService.getGame().emptyGameState();
        ticTacToeService.getGame().setGameOver(false);
        model.addAttribute("funfact", ticTacToeService.getFunFact(FUNFACTURL));
        model.addAttribute("comic_uri", ticTacToeService.getComic(COMICURL));
        return "game";
    }
}
