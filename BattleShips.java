import java.util.Scanner;
import java.util.Random;

public class BattleShips {
    public static int dimension; //dimensions of the battlefield
    public static String name; //player name
    public static int player = 0; //setting the starting player score
    public static int computer = 0; //setting the starting computer score
    public static int shipNumber = 0; //number of ships on the battlefield
    public static int battlefield[][]; //array for the position information
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        do { //setting playing field size, random numbers, could be bigger or smaller
            Print("Please enter the dimensions of the playing field (max is 20): ");
            dimension = input.nextInt();
        } while (dimension > 20 || dimension < 5);
        Print("How many ships do you want to play with?\nNumber must be between 5% and 30% of number of available paying fields. ");
        do { //setting arbitrary numbers for number of ships on a field, can change
            int tempNumber = input.nextInt();
            if (tempNumber < ((dimension * dimension) * 5/100) || tempNumber > ((dimension * dimension) * 30/100)){
                Print("Wrong value, please enter again! ");
            } else {
                shipNumber = tempNumber;
                player = tempNumber;
                computer = tempNumber;
            }
        } while (shipNumber == 0);
        Print("What is your name? ");
        input.nextLine(); //because of Java bug this had to be added so it could "consume" the empty line left by previous "nextInt"
        name = input.nextLine();
        Print("\n__________________________\n");
        battlefield = new int[dimension][dimension];
        printBattlefield();
        chooseCoordinatesPlayer();
        chooseCoordinatesComputer();
        Print("Game is starting! Prepare yourself!\n");
        do {
            printBattlefield();
            playerTurn();
            if (computer > 0) { //checking to see if player has won after his/her turn
                computerTurn();
            }
            Print("\n__________________________\n");
        } while (player > 0 && computer > 0);
        if (computer == 0){
            Print("You did it! You won the game!");
        } else {
            Print("Oh no, you lost! :(");
        }
    }

    public static int[][] printBattlefield(){ //setting up a field and printing after every turn
        String row = "";
        for (int i = 0; i < (dimension + 2); i++){
            if(i == 0 || i == (dimension + 1)){
                for (int j = 0; j < dimension; j++){
                    if (j == 0){
                    row = "    0" + j + " ";
                    } else if (j == (dimension-1)){
                        if(j < 10){
                            row += "0" + j + "    ";
                        } else {
                            row += j + "    ";
                        }
                    } else {
                        if (j < 10) {
                            row += "0" + j + " ";
                        } else {
                            row += j + " ";
                        }
                    }
                }
                Print(row + "\n");
            } else {
                for (int j = 0; j < dimension+2; j++){
                    if (j == 0){
                       if (i < 11){
                           row = "0" + (i - 1) + "| ";
                       }
                       else {
                           row = (i - 1) + "| ";
                       }
                    }
                    else if (j == dimension + 1){
                        if (i < 11){
                            row += "|0" + (i - 1);
                        }
                        else {
                            row += "|" + (i - 1);
                        }
                    }
                    else {
                        if (battlefield[i-1][j-1] == 1){
                            row += " @ ";
                        } else if (battlefield[i-1][j-1] == 2 || battlefield[i-1][j-1] == 0) {
                            row += "   ";
                        } else if (battlefield[i-1][j-1] == 3) {
                            row += " X ";
                        }
                    }
                }
                Print(row + "\n");
            }
        }
        Print(name + "`s ships: " + player + " vs Computer`s ships: " + computer + "\n");
        return battlefield;
    }

    public static void chooseCoordinatesPlayer(){
        int coordinateX;
        int coordinateY;
        Print("Keep in mind that coordinates range from 0 to " + (dimension-1) + " for both X and Y!\n");
        boolean fieldTaken;
        Scanner input = new Scanner(System.in);
                for (int i = 1; i <= shipNumber; i++) {
                coordinateX = -1;
                coordinateY = -1;
                fieldTaken = true;
                do {
                    Print("Please enter X coordinate for your ship number " + i + "! ");
                    do {
                    int tempNumber = input.nextInt();
                    if (tempNumber > dimension -1 || tempNumber < 0){
                        Print("Please enter again: ");
                    } else {
                        coordinateX = tempNumber;
                    }
                    } while (coordinateX == -1);
                    Print("Please enter Y coordinate for your ship number " + i + "! ");
                    do {
                        int tempNumber = input.nextInt();
                        if (tempNumber > dimension -1 || tempNumber < 0){
                        Print("Please enter again: ");
                        } else {
                        coordinateY = tempNumber;
                    }
                    } while (coordinateY == -1);
                    if (battlefield[coordinateY][coordinateX] == 1){
                    Print("Field already taken! Please try again. \n");
                    } else {
                        fieldTaken = false;
                    }
                } while (fieldTaken);
                    battlefield[coordinateY][coordinateX] = 1;
                }
    }

    public static void chooseCoordinatesComputer(){
        int coordinateX;
        int coordinateY;
        boolean fieldTaken;
        for (int i = 1; i <= shipNumber; i++) {
            fieldTaken = true;
            do {
                Random rand = new Random();
                coordinateX = rand.nextInt(dimension);
                coordinateY = rand.nextInt(dimension);
                if (battlefield[coordinateY][coordinateX] != 1 && battlefield[coordinateY][coordinateX] != 2){
                    fieldTaken = false;
                }
            } while (fieldTaken);
            battlefield[coordinateY][coordinateX] = 2;
        }
    }

    public static void playerTurn(){
        int coordinateX;
        int coordinateY;
        Print("Keep in mind that coordinates range from 0 to " + (dimension-1) + " for both X and Y!\n");
        Scanner input = new Scanner(System.in);
        coordinateX = -1;
        coordinateY = -1;
        Print("Please enter X coordinate of your target! ");
        do {
            int tempNumber = input.nextInt();
            if (tempNumber > dimension -1 || tempNumber < 0){
                Print("Please enter again: ");
            } else {
                coordinateX = tempNumber;
            }
        } while (coordinateX == -1);
        Print("Please enter Y coordinate of your target! ");
        do {
            int tempNumber = input.nextInt();
            if (tempNumber > dimension -1 || tempNumber < 0){
                Print("Please enter again: ");
            } else {
                coordinateY = tempNumber;
            }
        } while (coordinateY == -1);
        Print(name + " aims for " + coordinateX + ":" + coordinateY + "!\nIs he going to score?\n");
        if (battlefield[coordinateY][coordinateX] == 1){
            Print("You should not aim for your own ships!\n");
        } else if (battlefield[coordinateY][coordinateX] == 3){
            Print("You hit a sunken ship!\n");
        } else if (battlefield[coordinateY][coordinateX] == 0){
            Print("Oh no, you missed! :(\n");
        } else {
            computer--;
            if (player == shipNumber){
                Print("You did it, you've sunken all of computer`s ship!");
            } else {
                Print("You did it, you've sunken computer`s ship! " + computer + " more to go!\n");
            }
            battlefield[coordinateY][coordinateX] = 3;
        }
    }

    public static void computerTurn(){
        int coordinateX;
        int coordinateY;
        Random rand = new Random();
        boolean aimAgain = true;
        do {
            coordinateX = rand.nextInt(dimension);
            coordinateY = rand.nextInt(dimension);
            if (battlefield[coordinateY][coordinateX] != 2 && battlefield[coordinateY][coordinateX] != 3){
                aimAgain = false;
            }
        } while (aimAgain);
        Print("Computer aims for " + coordinateX + ":" + coordinateY + "!\nIs it going to score?\n");
        if (battlefield[coordinateY][coordinateX] == 1){
            player--;
            if (computer == shipNumber){
                Print("Oh no, all of your ship have been sunken! :( ");
            } else {
                Print("Oh no, your ship has been sunken! :( " + player + " of your ships is left!\n");
            }
            battlefield[coordinateY][coordinateX] = 3;
        } else {
            Print("Thank god, it missed you! :)\n");
        }
    }

    public static void Print(String printing){
        System.out.print(printing);
    }
}
