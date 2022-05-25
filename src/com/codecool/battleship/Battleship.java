package com.codecool.battleship;

public class Battleship {
    private static final int NUMBER_OF_SHIPS_PER_PLAYER = 5;


    private static void createShips(Board board, Player player){
        Ship.ShipType[] shipTypes = Ship.ShipType.values();
        for(int i=0;i<NUMBER_OF_SHIPS_PER_PLAYER;i++){
            Ship tempShip = new Ship();
            tempShip.shipType = shipTypes[i];
            player.addShipToList(tempShip);
            }
    }


    public static void main(String[] args) {
        boolean run = true;
        while(run){
            //instantiate board
            BoardFactory boardFactory = new BoardFactory();
            Input input = new Input(); //why not being used?

            //getting board Size
            boolean validBoardSize = false;
            do {
                validBoardSize = Display.askBoardSize(input);
                if (validBoardSize){
                    validBoardSize = input.boardSizeRange(input.boardSize);
                }
            } while(!validBoardSize);
            int boardSize = input.boardSize;


            //instantiate players and their boards and ships
            Player playerOne = new Player();
            Board boardPlayer1 = new Board();
            boardPlayer1.fillOcean(boardSize); //fill board with squares
            createShips(boardPlayer1, playerOne); //creating the ships, but not adding it to any address


            Player playerTwo = new Player();
            Board boardPlayer2 = new Board();
            boardPlayer2.fillOcean(boardSize); //fill board with squares
            createShips(boardPlayer2, playerTwo); //creating the ships, but not adding it to any address

            //ship placement
            //player 1
            //ask player if they would like to randomly or manually place their ships
            Display.getPlayerName(playerOne);
            Display.isRandomPlacement(input);
            if (input.isRandomPlacement) {
                boardFactory.randomPlacement(boardPlayer1, boardSize, playerOne); //place ships randomly -- needs a lot of work
            } else{
                //manual placement

                boardFactory.manualPlacement(boardPlayer1, boardSize, playerOne, input);
            }
            //player 2 (is the AI so the placement is automatic and no question needed)
            playerTwo.name = "AI";
            boardFactory.randomPlacement(boardPlayer2,boardSize, playerTwo);

            //game logic
            //starts with player 1
            Game game = new Game();
            Player currentPlayer = playerOne;
            Player opponentPlayer = playerTwo;
            Board currentBoard = boardPlayer1;
            Board opponentBoard = boardPlayer2;
            Player winner = null;

            do{
                winner = game.playerTurn(currentPlayer, currentBoard, opponentPlayer, opponentBoard, boardSize, input);

                //switch players
                Player tempPlayer = currentPlayer;
                currentPlayer = opponentPlayer;
                opponentPlayer = tempPlayer;

                //switch boards
                Board tempBoard = currentBoard;
                currentBoard = opponentBoard;
                opponentBoard = tempBoard;


            } while(winner == null);
            Display.declareWinner(winner);
            run = Display.playAgain();

        }




//--------------------------------testing area --------------------------------------------
//everything here is "for fun"

//boardPlayer1.ocean[1][9].status = com.codecool.battleship.Square.squareStatus.hit;
//        com.codecool.battleship.Display.displayBoard(boardPlayer1, boardSize);


    }
}
