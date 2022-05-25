package com.codecool.battleship;

import java.util.Objects;

public class Input {

    public boolean isRandomPlacement;
    public String inputX;
    public String inputY;
    public String inputDirection;
    public boolean inputHorizontal;
    public int boardSize;
    public int inputXValidated;
    public int inputYValidated;

    public boolean inputDirectionValidated;

    public boolean inputValidation(String inputX, String inputDirection){
        boolean inputValidation = false;
        //validate inputX to see if it's a number
        try
        {
            inputXValidated=Integer.parseInt(inputX);
        }
        catch(NumberFormatException e)
        {
            //If number is not integer,you wil get exception and exception message will be printed
            System.out.println(e.getMessage());
            System.out.println("Coordinates not validated. Please try again!");
            return inputValidation;
        }

        //validate inputY to see if it's a number
        try
        {
            inputYValidated=Integer.parseInt(inputY);
        }
        catch(NumberFormatException e)
        {
            //If number is not integer,you wil get exception and exception message will be printed
            System.out.println(e.getMessage());
            System.out.println("Coordinates not validated. Please try again!");
            return inputValidation;
        }

        //validate direction
        if(Objects.equals(inputDirection, "H") || Objects.equals(inputDirection, "HORIZONTAL") || Objects.equals(inputDirection, "V") || Objects.equals(inputDirection, "VERTICAL")){
            inputValidation = true;
            System.out.println("Coordinates validated!");
            if(Objects.equals(inputDirection, "H") || Objects.equals(inputDirection, "HORIZONTAL")){
                inputHorizontal = true;
            } else {
                inputHorizontal = false;
            }
            return inputValidation;
        }
        System.out.println("Coordinates not validated. Please try again!");
        return inputValidation;

    }

    public boolean isCoordinateInRange(int coordinate, int boardSize){
        return coordinate < boardSize;
    }
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean boardSizeRange(int input){
        if(input>9 && input<20){
            return true;
        }
        Display.displayMessage("Out of board range");
        return false;
    }


}
