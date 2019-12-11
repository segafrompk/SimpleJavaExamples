import java.util.*;
public class OddsAndEvens {
    static String name;
    static String playAgain = "Y";
    static int scoreComputer = 0;
    static int scoreUser = 0;
    public static void main(String[] args) {
        System.out.println("Let’s play a game called “Odds and Evens“");
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        name = input.nextLine();
        System.out.println("Nice to meet you, " + name + "! Would you like to play odds or evens?");
        do {
            game();
            if (scoreUser == 3) {
                System.out.println(name + " wins this game!");
            } else {
                System.out.println("Computer wins this game! Better luck next game, " + name);
            }
            scoreUser = 0;
            scoreComputer = 0;
            System.out.println("Do you want to play another game?");
            System.out.println("Enter Y to start another game or any other key to quit.");
            playAgain = input.nextLine();
        } while (playAgain.equalsIgnoreCase("Y"));
    }
    public static void game() {
        while (scoreUser<3 && scoreComputer<3) {
            Scanner input = new Scanner(System.in);
            String choice;
            int wrongCommand = 1;
            do {
                System.out.print("Enter O for odds or E for evens: ");
                choice = input.next();
                if (choice.equalsIgnoreCase("o") || choice.equalsIgnoreCase("e")) {
                    wrongCommand = 0;
                } else {
                    System.out.println("You have chosen wrong command, please try again.");
                }
            } while (wrongCommand == 1);
            if (choice.equalsIgnoreCase("e")) {
                choice = "Evens";
                System.out.println(name + " chose to play Evens! Computer will be playing Odds.");
            } else {
                choice = "Odds";
                System.out.println(name + " chose to play Odds! Computer will be playing Evens.");
            }
            System.out.println("---------------------------------------------------------");
            System.out.println("How many “fingers” do you put out? (enter a number between 1 and 5)");
            int fingers;
            do {
                fingers = input.nextInt();
                System.out.println("Lets try that again. Enter a number between 1 and 5!");
            } while (fingers < 1 || fingers > 5);
            Random rand = new Random();
            int computer = rand.nextInt(6);
            System.out.println("The computer plays number “fingers”.");
            System.out.println("It played " + computer + " fingers.");
            System.out.println("---------------------------------------------------------");
            int sum = fingers + computer;
            int oddOrEven = sum % 2;
            String state;
            if (oddOrEven == 1) {
                state = "Odd";

            } else {
                state = "Even";
            }
            System.out.println(fingers + " + " + computer + " = " + sum + "\n" + sum + " is an " + state.toLowerCase() + " number!");
            if (oddOrEven == 1) {
                if (choice.equals("Odds")) {
                    System.out.println(name + " wins this round! :)");
                    scoreUser++;
                } else {
                    System.out.println("Computer wins this round! :)");
                    scoreComputer++;
                }
            } else {
                if (choice.equals("Evens")) {
                    System.out.println(name + " wins this round! :)");
                    scoreUser++;
                } else {
                    System.out.println("Computer wins this round! :)");
                    scoreComputer++;
                }
            }
            System.out.println(name + ": " + scoreUser + " vs computer: " + scoreComputer);
            System.out.println("---------------------------------------------------------");
        }
    }
}
