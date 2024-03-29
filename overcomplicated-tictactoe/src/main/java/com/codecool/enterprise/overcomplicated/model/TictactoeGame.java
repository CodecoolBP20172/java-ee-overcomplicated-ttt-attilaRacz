package com.codecool.enterprise.overcomplicated.model;

import org.springframework.stereotype.Component;

@Component
public class TictactoeGame {

    private char[] gameState = new char[9];

    private char symbol = 'X';

    private boolean gameOver = false;

    public char[] getGameState() {
        return gameState;
    }

    public void emptyGameState() {
        this.gameState = new char[9];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void saveMove(int move, boolean ai) {
        if (!ai) {
            this.gameState[move] = symbol;
        } else {
            switchSymbol();
            this.gameState[move] = symbol;
            switchSymbol();
        }

    }

    public String gameStateToString() {
        char[] gameStateString = new char[9];
        System.arraycopy( this.gameState, 0, gameStateString, 0, gameStateString.length );
        for (int i=0; i<gameStateString.length; i++) {
            if (gameStateString[i] != 'X' && gameStateString[i] != 'O') {
                gameStateString[i] = '-';
            }
        }
        return new String(gameStateString);
    }

    public char getSymbol() {
        return symbol;
    }

    private void switchSymbol() {
        if (this.symbol == 'O') {
            this.symbol = 'X';
        } else {
            this.symbol = 'O';
        }
    }

}
