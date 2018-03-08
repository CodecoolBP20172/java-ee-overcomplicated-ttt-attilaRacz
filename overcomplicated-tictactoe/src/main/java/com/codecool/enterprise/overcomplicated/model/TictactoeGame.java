package com.codecool.enterprise.overcomplicated.model;

import org.springframework.stereotype.Component;

@Component
public class TictactoeGame {

    private char[] gameState = new char[9];

    private char symbol = 'O';

    public char[] getGameState() {
        return gameState;
    }

    public void saveMove(int move) {
        switchSymbol();
        this.gameState[move] = symbol;
    }

    public String gameStateToString() {
        return new String(gameState);
    }

    private void switchSymbol() {
        if (this.symbol == 'O') {
            this.symbol = 'X';
        } else {
            this.symbol = 'O';
        }
    }

}
