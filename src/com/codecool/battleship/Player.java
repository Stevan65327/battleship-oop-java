package com.codecool.battleship;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public List<Ship> playerShips = new ArrayList<>();
    public String name;

    public void addShipToList(Ship ship){
        playerShips.add(ship);
    }


    public boolean isAlive(Player opponentPlayer) {
        for (Ship playerShip : opponentPlayer.playerShips) {
            if (!playerShip.isSunk) {
                return true;
            }
        }
        Display.displayMessage("WE HAVE A WINNER!!");
        return false;
    }

    public void makeMove(Player player, Input input) {
        Display.displayMessage(player.name + ", what are the coordinates for your shot?");
        if(Display.getNCoordinate('X', input)){
            if(Display.getNCoordinate('Y', input)){
                Display.displayMessage("Coordinates done");
            }
        }
    }

    public void shotSquare(int xCoordinate, int yCoordinate, Board opponentBoard){
        //check if this the square has a ship
        //if yes, register ship hit
        Square.squareStatus squareStatus = opponentBoard.ocean[xCoordinate][yCoordinate].status;
        switch (squareStatus){
            case empty:
                System.out.println("Empty spot!");
                opponentBoard.ocean[xCoordinate][yCoordinate].status = Square.squareStatus.miss;
                break;
            case ship:
                System.out.println("com.codecool.battleship.Ship was hit!");
                opponentBoard.ocean[xCoordinate][yCoordinate].status = Square.squareStatus.hit;
                break;
            case miss:
                System.out.println("Miss again!");;
                opponentBoard.ocean[xCoordinate][yCoordinate].status = Square.squareStatus.miss;
                break;
            case hit:
                System.out.println("Hit again!");;
                opponentBoard.ocean[xCoordinate][yCoordinate].status = Square.squareStatus.hit;
                break;

        }
    }
}




//        for (com.codecool.battleship.Square[] squares : board) {
//            for (int j = 0; j < board.length; j++) {
//                com.codecool.battleship.Square tempSquare = squares[j];
//                if (tempSquare.xCoordinate == x && tempSquare.yCoordinate == y) {
//                    if (squares[j].squareOccupied) {
//                        squares[j].squareHit = true;
//                    } else {
//                        tempSquare.squareMiss = true;
//                    }
//                }
//            }
//        }
//    }

