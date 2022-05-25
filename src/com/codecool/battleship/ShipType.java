package com.codecool.battleship;

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

