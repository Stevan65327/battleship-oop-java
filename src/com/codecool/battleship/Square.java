package com.codecool.battleship;

public class Square {

    protected int xCoordinate;
    protected int yCoordinate;
    protected char squareDisplayChar = 'O';


//    previous implementation
    public boolean squareOccupied = false;
    protected boolean squareHit = false;
    protected boolean squareMiss = false;


    public void updateSquareDisplayChar() {

        if (squareHit) {
            this.squareDisplayChar = 'X';
        } else if (squareMiss) {
            this.squareDisplayChar = 'M';
        }
    }


    //created enum by Emerson
    enum squareStatus {
        empty,
        ship,
        hit,
        miss,
        sunk
    }
    squareStatus status = squareStatus.empty; //assign


    public void squareDisplayChar(){
        switch (status) {
            case empty -> this.squareDisplayChar = 'O'; //standard value, just mentioned it here for explanation
            case ship -> this.squareDisplayChar = '*'; //does not show there's a ship
            case hit -> this.squareDisplayChar = 'X';
            case miss -> this.squareDisplayChar = 'M';
            case sunk -> this.squareDisplayChar = 'S';
            default -> this.squareDisplayChar = 'O';
        }

    }



    public char GetCharacter(){
        return this.squareDisplayChar;
    }
}
