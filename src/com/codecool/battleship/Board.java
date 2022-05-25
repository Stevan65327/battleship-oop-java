package com.codecool.battleship;

import java.util.ArrayList;
import java.util.List;

public class Board {

    protected Square[][] ocean;
    public List<Ship> shipsList = new ArrayList<>();

    public void fillOcean(int boardSize) {
        {
            ocean = new Square[boardSize][boardSize];
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                ocean[i][j] = new Square();
                ocean[i][j].xCoordinate = i;
                ocean[i][j].yCoordinate = j;
                ocean[i][j].status = Square.squareStatus.empty;
            }
        }
    }


    public void registerShip(Ship ship){
        shipsList.add(ship);
    }
}

