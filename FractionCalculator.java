import java.util.Scanner;

public class FractionCalculator {
    private static char command;
    private static Fraction fraction1;
    private static Fraction fraction2;

    public static void main (String [] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("This program is a fraction calculator\nIt will add, subtract, multiply and divide fractions until you type Q to quit.\nPlease enter your fractions in the form a/b, where a and b are integers.");
        System.out.println("----------------------------------------------------------------------------------");
        do {
            boolean validCommand = false;
            do {
                String command1;
                do {
                    System.out.print("Please enter an operation (+, -, /, *, =) or Q to quit: ");
                    command1 = input.next();
                    if (command1.length()>1){
                        System.out.println("Not a valid command!");
                    }
                } while (command1.length()>1);
                command = command1.charAt(0);
                if (command == 'q' || command == 'Q' || command == '+' || command == '-' || command == '*' || command == '/' || command == '='){
                    validCommand = true;
                } else {
                    System.out.println("Not a valid command!");
                }
            } while (!validCommand);
            if (command != 'q' && command != 'Q') {
                inputFractions();
                operations();
            }
        } while (command != 'q' && command != 'Q');
    }

    private static void inputFractions(){
        Scanner input = new Scanner(System.in);
        boolean cont;
        do {
            System.out.print("Please enter a fraction (a/b) or integer (a): ");
            String inputFraction1 = input.nextLine();
            cont = isNumeric(inputFraction1);
            if (cont && inputFraction1.contains("/")) {
                if (!isNumeric(inputFraction1.substring(0, inputFraction1.indexOf('/'))) || !isNumeric(inputFraction1.substring(inputFraction1.indexOf('/') + 1))) {
                    cont = false;
                } else {
                    int num = Integer.parseInt(inputFraction1.substring(0, inputFraction1.indexOf('/')));
                    int denom = Integer.parseInt(inputFraction1.substring(inputFraction1.indexOf('/') + 1));
                    if (denom < 0) {
                        denom *= -1;
                        num *= -1;
                    }
                    if (denom == 0) {
                        System.out.println("You cannot divide by 0!");
                        cont = false;
                    } else {
                        fraction1 = new Fraction(num, denom);
                    }
                }
            } else if (cont){
                int num = Integer.parseInt(inputFraction1);
                fraction1 = new Fraction(num);
            }
        } while (!cont);
        do {
            System.out.print("Please enter another fraction (a/b) or integer (a): ");
            String inputFraction2 = input.nextLine();
            cont = isNumeric(inputFraction2);
            if (cont && inputFraction2.contains("/")){
                if (inputFraction2.indexOf('/') == 0 || inputFraction2.indexOf('/') == inputFraction2.length() - 1) {
                    cont = false;
                } else {
                    int num = Integer.parseInt(inputFraction2.substring(0, inputFraction2.indexOf('/')));
                    int denom = Integer.parseInt(inputFraction2.substring(inputFraction2.indexOf('/') + 1));
                    if (denom < 0) {
                        denom *= -1;
                        num *= -1;
                    }
                    if (denom == 0) {
                        System.out.println("You cannot divide by 0!");
                        cont = false;
                    } else {
                        fraction2 = new Fraction(num, denom);
                    }
                }
            } else if (cont){
                int num = Integer.parseInt(inputFraction2);
                fraction2 = new Fraction(num);
            }
        } while (!cont);
    }

    private static boolean isNumeric(String str) {
        try {
            str = str.replaceFirst("/", "");
            str = str.replace("-", "");
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            System.out.println("Not a valid number or fraction, please try again!");
            return false;
        }
    }

    private static void operations(){
        String result;
        if (command == '+'){
            result = fraction1.add(fraction2).toString();
            System.out.println(fraction1.toString() + " + " + fraction2.toString() + " = " + result);
        } else if (command == '-'){
            result = fraction1.subtract(fraction2).toString();
            System.out.println(fraction1.toString() + " - " + fraction2.toString() + " = " + result);
        } else if (command == '*'){
            result = fraction1.multiply(fraction2).toString();
            System.out.println(fraction1.toString() + " * " + fraction2.toString() + " = " + result);
        } else if (command == '/'){
            result = fraction1.divide(fraction2).toString();
            System.out.println(fraction1.toString() + " / " + fraction2.toString() + " = " + result);
        } else if (command == '='){
            if (fraction1.equals(fraction2)) {
                System.out.println(fraction1.toString() + " is equal to " + fraction2.toString());
            } else {
                System.out.println(fraction1.toString() + " is not equal to " + fraction2.toString());
            }
        }
    }
}
