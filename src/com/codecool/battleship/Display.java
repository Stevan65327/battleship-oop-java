package com.codecool.battleship;

import java.util.Scanner;

public class Display extends Input {

    public static void displayMessage(String message){
        System.out.println(message);
    }

    public static boolean askBoardSize(Input input){
        Scanner scan = new Scanner(System.in); //Creates a new scanner
        System.out.println("What is the board size you'd like to play with?");
        String inputTxt = scan.nextLine().toUpperCase(); //Waits for input
        if(!input.isNumeric(inputTxt)){
            displayMessage("This number is not valid. Please select a new value.");
            return false;
        } else {
            input.boardSize =  Integer.parseInt(inputTxt);
        }
    return true;



    }

    public static void displayBoard(Board board, int boardSize){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                board.ocean[i][j].squareDisplayChar();
                System.out.print(board.ocean[i][j].squareDisplayChar  + " ");
            } System.out.println();
        }
    }

    public static void isRandomPlacement(Input input) {
        Scanner scan = new Scanner(System.in); //Creates a new scanner
        Display.displayMessage("Would you like me to place your ships for you?");
        String inputTxt = scan.nextLine().toUpperCase(); //Waits for input
        if(inputTxt.equals("YES")){
            input.isRandomPlacement = true;
        } else{
            input.isRandomPlacement = false;
        }
         //sending the input.isRandomPlacement bool to True if answer is yes


    }
    public static void placementCoordinates(Board board, int shipIndex, Player player, Input input){
        boolean validCoordinates = false;
        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("This is the placement of the ship type " + player.playerShips.get(shipIndex).shipType + " with size: " + player.playerShips.get(shipIndex).shipType.shipLength);
            System.out.println("What is your desired X coordinate?");
            input.inputX = scan.nextLine().toUpperCase();
            System.out.println("What is your desired Y coordinate?");
            input.inputY = scan.nextLine().toUpperCase();
            System.out.println("What is the direction?");
            input.inputDirection = scan.nextLine().toUpperCase();
            validCoordinates = input.inputValidation(input.inputX,input.inputDirection);
        }while(!validCoordinates);
    }

    public static void getPlayerName(Player player) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Greetings, player.  What would" +
                "you like to be called?");
        player.name = scan.nextLine();
    }

    public static boolean getNCoordinate(char N, Input input) {
        int temp_N_Coordinate;

        //gets input
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a " + N +"-Coordinate: ");
        String inputTxt = scan.nextLine();

        //validate string to integer
        if(!input.isNumeric(inputTxt)){
            System.out.println("Not a numeric coordinate. Please select again.");
            return false;
        } temp_N_Coordinate = Integer.parseInt(inputTxt);

        //make sure that it's within the size of the board
        if(input.isCoordinateInRange( temp_N_Coordinate, input.boardSize)) {
            if (N == 'X') {
                input.inputXValidated = temp_N_Coordinate;
                return true;
            } else if (N == 'Y') {
                input.inputYValidated = temp_N_Coordinate;
                return true;
            }

        }
        Display.displayMessage("Coordinate out of range. Please select again.");
        return false;

    }

    public static int getYCoordinate() {

        System.out.println("Enter an Y-Coordinate: ");
        //convert string to integer
        //make sure that it's within the size of the board
        return 10;

    }

    //testing function to check how to handle input
    public static void myScanner() {
        Scanner scan = new Scanner(System.in); //Creates a new scanner
        System.out.println("Who is on the 5 dollar bill?"); //Asks question
        String input = scan.nextLine(); //Waits for input
        if (input.equalsIgnoreCase("Lincoln")) { //If the input is Lincoln (or any case variant of Lincoln)
            System.out.println("Correct!");
        }
        else { //If the input is anything else
            System.out.println("Incorrect!");
        }
    }

    public static void declareWinner(Player player){
        System.out.println("The player " + player.name + " is the winner! YEYYYY");
    }

    public static boolean playAgain(){
        Scanner scan = new Scanner(System.in); //Creates a new scanner
        System.out.println("Would you like to play again?"); //Asks question
        String input = scan.nextLine().toUpperCase(); //Waits for input
        return input.equals("YES");
    }
}

