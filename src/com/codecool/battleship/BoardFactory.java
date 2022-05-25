package com.codecool.battleship;

import java.util.Random;

public class BoardFactory {

    //should I instantiate the board here?

    //accept the ocean board, randomly place things into it's Squares
    public void randomPlacement(Board board, int boardSize, Player player) {
        Square[][] ocean = board.ocean;
        int limit = player.playerShips.size();

        //initializing a new ship to be placed
        for (int i = 0; i < limit; i++) {
            //initializing randoms
            Random random = new Random();
            int randomX = random.nextInt(boardSize);
            int randomY = random.nextInt(boardSize);
            boolean horizontal = random.nextBoolean();
            int shipSize = player.playerShips.get(i).shipType.shipLength;

            //first check if it's possible to use the randomX or randomY (depending on horizontal  bool value)
            if(checkIfShipFits(horizontal, randomY, randomX, shipSize, boardSize)){ //if coordinate + shipSize > boardSize, gets new random coordinates
                i = i - 1;
                continue; //it will run again to generate new random X and Y
            }

            //check if any of the size is occupied
            boolean isOccupied = true;
            isOccupied = isSquareOccupied(shipSize, ocean, randomX, randomY, horizontal);

            //place the coordinates to the ship
            if (!isOccupied) {
                placeShip(shipSize,horizontal, player, board,ocean, randomX, randomY, i);
            } else {
                i = i - 1;//it will run again to generate new random X and Y
            }
        }
    }


    public void manualPlacement(Board board, int boardSize, Player player, Input input) {
        //for each ship (loop)
        int shipIndex = 0;
        int limit = player.playerShips.size();
        String outOfRangeMessage = "The ship will go out of board. Please restart coordinates";
        String occupiedMessage = "Coordinates already occupied. Please select new coordinates";
        String outOfBoardSize = "Coordinates are out of board size. Please select again.";
        Square[][] ocean = board.ocean;


        while (shipIndex < limit) {
        int shipLength = player.playerShips.get(shipIndex).shipType.shipLength;
            //ask coordinates
            Display.placementCoordinates(board, shipIndex, player, input);

            //validate coordinates if within the range of the board
            boolean xinRange = input.isCoordinateInRange(input.inputXValidated, boardSize);
            boolean yinRange = input.isCoordinateInRange(input.inputYValidated, boardSize);
            if (!xinRange || !yinRange) {
                Display.displayMessage(outOfBoardSize);
                continue; //it will restart the iteration to ask new coordinates
            }

            //check if coordinate + length is possible
            int temp_coordinate;
            if(input.inputHorizontal) {temp_coordinate = input.inputYValidated;} else {temp_coordinate = input.inputXValidated;}
            if(!checkIfShipFitsManual(temp_coordinate, shipLength, boardSize)) {
                continue; //restart the process
            }

            //check if there's no ship along the selected coordinates
            //horizontal
            boolean isOccupied = isSquareOccupied(shipLength, ocean, input.inputXValidated, input.inputYValidated, input.inputHorizontal);
            if(isOccupied){
                Display.displayMessage(occupiedMessage);
                continue;
            }

            //if everything is fine, place the  ship
            placeShip(shipLength, input.inputHorizontal, player, board, ocean, input.inputXValidated, input.inputYValidated, shipIndex);

        if(shipIndex<limit-1) Display.displayBoard(board, boardSize); //just to not display board twice since it's already being called on the main
        shipIndex++;

        }

    }

    //--------------------------------- Private methods used -----------------------------------------------------

    private void placeShip(int shipSize, boolean horizontal, Player player,  Board board, Square[][] ocean,  int randomX, int randomY, int i){
        for (int j = 0; j < shipSize; j++) {
            if (horizontal) {
                player.playerShips.get(i).registerCoordinates(ocean[randomX][randomY + j]);
                board.ocean[randomX][randomY + j].status = Square.squareStatus.ship;
            } else {
                player.playerShips.get(i).registerCoordinates(ocean[randomX + j][randomY]);
                board.ocean[randomX + j][randomY].status = Square.squareStatus.ship;
            }
        }
    }

    //check if ship fits into the board according to direction and size
    private boolean checkIfShipFits(boolean horizontal, int randomY, int randomX, int shipSize, int boardSize){
        int coordinate;
        if (horizontal) {coordinate = randomY; } else {coordinate = randomX;}
        return coordinate + shipSize > boardSize;
    }

    //check if any of the size is occupied
    private boolean isSquareOccupied(int shipSize, Square[][] ocean, int randomX, int randomY, boolean horizontal){
        for (int j = 0; j < shipSize; j++) {
            if (horizontal) {
                if (ocean[randomX][randomY + j].status != Square.squareStatus.empty) {
                    return true; //should not continue if any of the places is occupied.
                }
            } else { //if vertical
                if (ocean[randomX + j][randomY].status != Square.squareStatus.empty) {
                    return true; //should not continue if any of the places is occupied.
                }
            }
        }
        return false;
    }


    //to check if the ship fits into the board range
    private boolean checkIfShipFitsManual(int temp_coordinate, int shipLength, int boardSize) {
        final String outOfRangeMessage = "The ship will go out of board. Please restart coordinates";
        if (temp_coordinate + shipLength > boardSize) {
            Display.displayMessage(outOfRangeMessage);
            return false;
        }
        return true;
    }




}