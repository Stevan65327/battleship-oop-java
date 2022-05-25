package com.codecool.battleship;

import java.util.ArrayList;

public class Ship {


    protected ArrayList<Square> shipCoordinates = new ArrayList<Square>();
    protected boolean isSunk = false;
    public int shipLength;
    public enum ShipType {
        CARRIER(1),
        CRUISER(2),
        BATTLESHIP(3),
        SUBMARINE(4),
        DESTROYER(5);

        public final int shipLength;

        private ShipType(int shipLength) {
            this.shipLength = shipLength;
        }
    }
    ShipType shipType = ShipType.CARRIER; //just to initialize all the ships with carrier type

    protected void registerCoordinates(Square square){
        shipCoordinates.add(square);
    }

    protected void registerHit(int x, int y) {

        for (Square tempSquare : shipCoordinates) {
            if (tempSquare.xCoordinate == x && tempSquare.yCoordinate == y) {
                tempSquare.squareHit = true;
                if (isShipSinking()) {
                    this.isSunk = true;
                }
            }
        }
    }

    public static void isShipSunk(Player opponentPlayer){
        int numberOfShips = opponentPlayer.playerShips.size();
        for(int shipIndex=0;shipIndex<numberOfShips;shipIndex++){

            int shipSize = opponentPlayer.playerShips.get(shipIndex).shipType.shipLength;
            int hitParts = 0;
            for(int shipPart=0;shipPart<shipSize;shipPart++){

                if(opponentPlayer.playerShips.get(shipIndex).shipCoordinates.get(shipPart).status == Square.squareStatus.hit){
                    //com.codecool.battleship.Display.displayMessage("com.codecool.battleship.Ship " + opponentPlayer.playerShips.get(i).shipType + " is not sunk");
                    hitParts++;
                }
                if(hitParts==shipSize){
                    sunkShip(opponentPlayer, shipIndex);
                    opponentPlayer.playerShips.get(shipIndex).isSunk = true;
                    break;
                }
            }
        }
    }


    protected boolean isShipSinking() {

        for (Square shipCoordinate : shipCoordinates) {
            if (!shipCoordinate.squareHit) {
                return false;
            }
        }
        return true;
    }

    protected static void sunkShip(Player player, int shipIndex){
        for(int i=0; i<player.playerShips.get(shipIndex).shipCoordinates.size();i++){
            player.playerShips.get(shipIndex).shipCoordinates.get(i).status = Square.squareStatus.sunk;
        }

    }



}
