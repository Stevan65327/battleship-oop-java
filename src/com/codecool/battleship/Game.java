package com.codecool.battleship;//Implement the com.codecool.battleship.Game class and its methods.
//The com.codecool.battleship.Game class has a loop in which players make moves.
//The com.codecool.battleship.Game class has a logic which determines the flow of round.
//The com.codecool.battleship.Game class has a condition on which game ends.
//The com.codecool.battleship.Game class provides different game modes which use different round flows.


public class Game {


    public Player playerTurn(Player currentPlayer, Board currentBoard, Player opponent, Board opponentBoard, int boardSize, Input input){

        //begin game, show opponent's board and ask player for move
        Display.displayMessage("This is the opponent board:");
        Display.displayBoard(opponentBoard, boardSize);
        currentPlayer.makeMove(currentPlayer, input);
        currentPlayer.shotSquare(input.inputXValidated, input.inputYValidated, opponentBoard);
        Ship.isShipSunk(opponent);
        if(!currentPlayer.isAlive(opponent)){
            return currentPlayer;
        } return null;
        //make move
        //check if hit, and update square
        //check if player is alive
        //move on to next turn

    }
}
